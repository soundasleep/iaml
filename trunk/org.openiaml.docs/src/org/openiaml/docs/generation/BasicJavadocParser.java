/**
 * 
 */
package org.openiaml.docs.generation;

import org.openiaml.docs.generation.semantics.HandleInlineJavadoc;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.ModeldocFactory;

/**
 * @author jmwright
 *
 */
public class BasicJavadocParser {
	
	/**
	 * <p>Parse the given semantic rule line, e.g.
	 * <code>This element {@model Element} ...</code>,
	 * and place it into JavadocFragments.</p>
	 * 
	 * <p>This is a very basic parser, and won't handle anything
	 * complicated (e.g. additional javadoc block characters).</p>
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
				if (tag.startsWith("@")) {
					String tagName = tag.substring(0, tag.indexOf(" "));
					String tagText = tag.substring(tag.indexOf(" ") + 1);
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
	
}
