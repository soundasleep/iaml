/**
 * 
 */
package org.openiaml.docs.generation;

import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

import org.openiaml.docs.generation.semantics.HandleInlineJavadoc;
import org.openiaml.docs.generation.semantics.ITagHandler;
import org.openiaml.docs.generation.semantics.SemanticFinder;
import org.openiaml.docs.generation.semantics.SemanticHandlerException;
import org.openiaml.docs.modeldoc.DroolsRule;
import org.openiaml.docs.modeldoc.JavaElement;
import org.openiaml.docs.modeldoc.JavadocFragment;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.docs.modeldoc.Reference;

/**
 * @author jmwright
 *
 */
public class BasicJavadocParser {
	
	private List<ITagHandler> semanticTagHandlers;

	/**
	 * @param semanticTagHandlers
	 */
	public BasicJavadocParser(List<ITagHandler> semanticTagHandlers) {
		super();
		this.semanticTagHandlers = semanticTagHandlers;
	}

	/**
	 * <p>Parse the given semantic rule line, e.g.
	 * <code>This element {@model Element} ...</code>,
	 * and place it into JavadocFragments, which are inserted
	 * into the JavadocTagElement <code>e</code>.</p>
	 * 
	 * <p>This is a very basic parser, and won't handle anything
	 * complicated (e.g. additional javadoc block characters).</p>
	 * 
	 * @param e the element where {@link JavadocFragment}s will be inserted
	 */
	public void parseSemanticLineIntoFragments(String line, ModeldocFactory factory,
			JavadocTagElement e) {
		
		HandleInlineJavadoc inline = new HandleInlineJavadoc(factory, e);
		
		int pos = 0;
		while (true) {
			// find a {
			int next = line.indexOf('{', pos);
			if (next != -1) {
				// found one
				String text = line.substring(pos, next);
				inline.handleText(text);
				
				int next2 = line.indexOf('}', next);
				String tag = line.substring(next + 1, next2);
				if (tag.startsWith("@") && tag.indexOf(" ") != -1) {
					String tagName = tag.substring(0, tag.indexOf(" "));
					String tagText = tag.substring(tag.indexOf(" ") + 1);
					
					// if the next two characters are 's ' (i.e. making it
					// a plural) then modify appropriately
					// e.g. {@model Foo}s -> {@model Foo Foos}
					// but not if there is a space within the tag
					if (line.length() > next2 + 2) {
						if (!tagText.contains(" ")) {
							String s2 = line.substring( next2 + 1, next2 + 3 );
							if (s2 != null && s2.length() == 2 && s2.charAt(0) == 's' && isWordSeperator(s2.charAt(1))) {
								next2++;	// keep whitespace
								tagText += " " + tagText + "s";
							}
						}
					}
					
					inline.handleTag(tagName, tagText);
				} else {
					inline.handleText("{" + tag + "}"); 
				}
				
				pos = next2 + 1;
			} else {
				// found the end; break
				break;
			}
		}
		
		// handle remaining test
		String text = line.substring(pos);
		inline.handleText(text);
		
	}
	
	/**
	 * Is the given character a word separator character? A word separator character
	 * is one of the following:
	 * 
	 * <ul>
	 * <li>{@link Character#isWhitespace(char)}
	 * <li>One of the following: <code>.,;:()[]'"!?-</code>
	 * </ul>
	 */
	private boolean isWordSeperator(char c) {
		return Character.isWhitespace(c) ||
			c == '.' || c == ',' || c == ';' || c == ':' || 
			c == '(' || c == ')' || c == '[' || c == ']' ||
			c == '\'' || c == '"' || c == '!' || c == '?' ||
			c == '-';
	}

	/**
	 * Get the first comment of the file, or does nothing if there
	 * is no first comment of the file. The first comment is then parsed
	 * for sub-tags such as <code>{@model ...}</code>.
	 *
	 * @param file the file to load
	 * @param ref the {@link Reference} that will be provided to all of the 
	 * 	sub-tags in the first comment
	 * @param root the root {@link ModelDocumentation} element
	 * @return the first found comment as a JavadocFragment, or <code>null</code> if none was found 
	 */
	public JavadocFragment findFirstCommentOfFile(File file, DocumentationHelper helper,
			ModeldocFactory factory, Reference ref, ModelDocumentation root) throws IOException, SemanticHandlerException {
		
		// read file into array of lines
		CharBuffer buf = CharBuffer.wrap(helper.readFile(file));
		String[] lines = buf.toString().split("\n");
		
		String firstComment = getFirstJavadocComment(lines);
		if (firstComment == null || firstComment.trim().isEmpty())
			return null;
		
		// create a new fragment for this comment
		JavadocTagElement e = factory.createJavadocTagElement();
		e.setName("summary");
		
		// parse the line into javadoc elements
		// (the line needs to be parsed into fragments before we can find semantic references)
		parseSemanticLineIntoFragments(firstComment, factory, e);
		
		// identify semantic rules back
		handleModelReferences(e, ref, root, helper);
		
		// return the fragment
		return e;
		
	}
	
	/**
	 * <p>We are reading in a given text file, which may 
	 * contain lines of either of the following formats:</p>
	 * 
	 * <li><code># @tag ...</code>
	 * <li><code>/** @tag ... </code>
	 * 
	 * <p>This method will search for these types of tags,
	 * parse out the tag, and call the given callback
	 * with the new tag information. The callback can then
	 * parse this new tag for new semantic information.</p>
	 * 
	 * @throws IOException 
	 * @return the source of the file, in terms of String[]
	 */
	public String[] findJavadocTagsInTextFile(File file, DocumentationHelper helper,
			ModeldocFactory factory, ModelDocumentation root,
			IJavadocReferenceCreator creator) throws IOException, SemanticHandlerException {
		
		// read file into array of lines
		CharBuffer buf = CharBuffer.wrap(helper.readFile(file));
		String[] lines = buf.toString().split("\n");
		
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();
			
			// check for one-line comments
			String key = "# @";
			if (line.startsWith(key)) {
				line = line.substring(key.length()).trim();
				if (!line.contains(" ")) {
					throw new RuntimeException("Malformed Javadoc line, expected a space: '" + line + "'");
				}
				
				// extract tag
				String tagName = "@" + line.substring(0, line.indexOf(' '));
				// extract line
				line = line.substring(line.indexOf(' ') + 1);
				
				JavaElement ref = creator.createReference(lines, i + 1);
				if (ref != null) {
				
					// a root tag for @tagName
					JavadocTagElement e = factory.createJavadocTagElement();
					e.setJavaParent(ref);
					e.setName(tagName);
					
					// parse the line into javadoc elements
					// (the line needs to be parsed into fragments before we can find semantic references)
					parseSemanticLineIntoFragments(line, factory, e);
					
					// identify semantic rules back
					handleModelReferences(e, ref, root, helper);
				}
			} else if (line.startsWith("/**")) {
				
				// get all @tags
				String[] tags = getJavadocTags(lines, i);
				
				for (String tag : tags) {
					if (!tag.contains(" ")) {
						throw new RuntimeException("Malformed Javadoc tag, expected a space: '" + line + "'");
					}
					
					// extract tag
					String tagName = tag.substring(0, tag.indexOf(' '));
					// extract line
					tag = tag.substring(tag.indexOf(' ') + 1);
					
					JavaElement ref = creator.createReference(lines, i + 1);
					if (ref != null) {
					
						// a root tag for @tagName
						JavadocTagElement e = factory.createJavadocTagElement();
						e.setJavaParent(ref);
						e.setName(tagName);
						
						// parse the line into javadoc elements
						// (the line needs to be parsed into fragments before we can find semantic references)
						parseSemanticLineIntoFragments(tag, factory, e);
						
						// identify semantic rules back
						handleModelReferences(e, ref, root, helper);
					}
				}
				
			}
		}
		
		return lines;
		
	}
	
	/**
	 * Find all occurances of Javadoc tags from the given source, and
	 * return them separately.
	 * 
	 * @param lines
	 * @param line
	 * @return
	 */
	protected String[] getJavadocTags(String[] lines, int line) {
		List<String> result = new ArrayList<String>();
		
		boolean inTag = false;
		String currentString = "";
		for (int j = line; j < lines.length; j++) {
			String cur = lines[j].trim();
			
			// jump over first
			if (j == line) {
				cur = cur.substring(cur.indexOf("/**") + 3).trim();
			}

			// bail if we get to the end
			if (cur.startsWith("**/")) {
				break;
			}

			// skip over any initial stars
			while (cur.startsWith("*")) {
				cur = cur.substring(1).trim();
			}
			
			// does this line start with a @?
			if (cur.startsWith("@")) {
				if (inTag) {
					// we are currently in a tag; close it and start a new one
					result.add(currentString.trim());
					currentString = "";
				} else {
					// we are not yet in a tag; we are now
					inTag = true;
				}
			}
			
			// does this line end with a **/, i.e. we need to stop?
			boolean shouldStop = false;
			if (cur.startsWith("/") /* we previously got rid of the other '*'s */) {
				cur = ""; // empty;
				shouldStop = true;
			} else if (cur.endsWith("*/")) {
				cur = cur.substring(0, cur.indexOf("*/")).trim();
				shouldStop = true;
			}
			if (inTag) {
				currentString += cur + " ";
			}
			
			// does the current line end with a **/?
			if (shouldStop)
				break;
			
		}
		
		// add last tag if not yet saved
		if (inTag) {
			result.add(currentString.trim());
		}
		return result.toArray(new String[] {});
	}

	/**
	 * Get the first Javadoc comment of the given set of lines.
	 * If there is some actual content, returns the empty string.
	 * 
	 * <p>Does not parse it for tags.
	 */
	protected String getFirstJavadocComment(String[] lines) {
		StringBuffer buf = new StringBuffer();
		
		// skip until we find either the first /**, or some non-empty text
		int line = 0;
		for (line = 0; line < lines.length; line++) {
			if (lines[line].trim().isEmpty())
				continue;
			if (lines[line].trim().startsWith("/**"))
				break;
			
			// found something else
			return "";
		}
		
		boolean isEmpty = true;
		for (int j = line; j < lines.length; j++) {
			String cur = lines[j].trim();
			
			// jump over first
			if (j == line) {
				cur = cur.substring(cur.indexOf("/**") + 3).trim();
			}

			// bail if we get to the end
			if (cur.startsWith("*/")) {
				break;
			}

			// skip over any initial stars
			while (cur.startsWith("*")) {
				cur = cur.substring(1).trim();
			}
			
			// add it to the buffer
			if (!isEmpty) buf.append(" ");
			buf.append(cur);
			isEmpty = false;
			
		}

		return buf.toString();
	}

	/**
	 * Iterate over all semantic handlers in {@link LoadSemanticsFromTests#getSemanticTagHandlers()}
	 * and identify potential semantic tags.
	 * 
	 * @param e
	 * @param reference
	 * @param root 
	 */
	protected void handleModelReferences(JavadocTagElement e, Reference reference, ModelDocumentation root, DocumentationHelper helper) throws SemanticHandlerException {
		SemanticFinder finder = new SemanticFinder();
		for (ITagHandler sem : getSemanticTagHandlers()) {
			finder.findSemanticReferences(helper, root, e, reference, sem);
		}
	}
	
	/**
	 * Creates {@link JavaElements} from textual source code.
	 * 
	 * @author jmwright
	 *
	 */
	public interface IJavadocReferenceCreator {
		
		/**
		 * <p>Try and create a {@link JavaElement} reference to some interesting 
		 * feature from the current line number. The reference
		 * should be saved into the current Resource if necessary.</p>
		 * 
		 * <p>For example, this could parse a .drl file
		 * and find a {@link DroolsRule} to refer to.</p>
		 * 
		 * <p>This reference will then be appended with any
		 * model references through {@link 
		 */
		public JavaElement createReference(String[] lines, int line);
		
	}
	
	public List<ITagHandler> getSemanticTagHandlers() {
		return semanticTagHandlers;
	}
	
}
