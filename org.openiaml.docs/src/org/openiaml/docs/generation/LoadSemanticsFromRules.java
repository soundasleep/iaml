/**
 * 
 */
package org.openiaml.docs.generation;

import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.List;

import org.openiaml.docs.generation.semantics.HandleInlineJavadoc;
import org.openiaml.docs.generation.semantics.HandleSemantics;
import org.openiaml.docs.generation.semantics.SemanticFinder;
import org.openiaml.docs.modeldoc.DroolsPackage;
import org.openiaml.docs.modeldoc.DroolsRule;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.docs.modeldoc.Reference;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.drools.DroolsInferenceEngine;

/**
 * @author jmwright
 *
 */
public class LoadSemanticsFromRules extends DocumentationHelper implements ILoader {
	
	private List<HandleSemantics> semanticTagHandlers;
	
	public LoadSemanticsFromRules(List<HandleSemantics> semanticTagHandlers) {
		this.semanticTagHandlers = semanticTagHandlers;
	}
	
	public List<HandleSemantics> getSemanticTagHandlers() {
		return semanticTagHandlers;
	}
	
	/**
	 * @param factory
	 * @param root
	 * @throws IOException 
	 */
	public void load(ModeldocFactory factory, ModelDocumentation root) throws DocumentationGenerationException {
	
		// get the default inference engine
		DroolsInferenceEngine engine = new CreateMissingElementsWithDrools(null, false);
		
		// for each rule file
		for (String file : engine.getRuleFiles()) {
			// load the inference rules
			String[] bits = getSimpleFilename(file);
			try {
				
				loadInferenceSemantics(factory, 
						root,
						"org.openiaml.model.drools" /* plugin */,
						bits[0] /* package */,
						bits[1] /* name */,
						new File("../org.openiaml.model.drools/" + file ) );
				
			} catch (IOException e) {
				throw new DocumentationGenerationException(e);
			}
		}
		
	}

	/**
	 * Parse the given Drools file for inference semantics, of
	 * the format
	 * <code># @semantics ...{@model Element} ...</code>
	 * 
	 * @param factory
	 * @param root
	 * @param plugin
	 * @param file
	 * @throws IOException 
	 */
	protected void loadInferenceSemantics(final ModeldocFactory factory,
			ModelDocumentation root, String plugin, String pkg,
			String name, File file) throws IOException {
		
		// create a package for this rule file
		DroolsPackage drools = factory.createDroolsPackage();
		drools.setPlugin(plugin);
		drools.setPackage(pkg);
		drools.setName(name);
		root.getReferences().add(drools);

		CharBuffer buf = CharBuffer.wrap(readFile(file));
		String[] lines = buf.toString().split("\n");
		
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();
			
			String key = "# @semantics";
			if (line.startsWith(key)) {
				line = line.substring(key.length() + 1).trim();
				
				DroolsRule rule = createDroolsRule(factory, i + 1, lines);
				if (rule != null) {
					drools.getRules().add(rule);

					// a root tag for @semantics
					JavadocTagElement e = factory.createJavadocTagElement();
					e.setJavaParent(rule);
					e.setName("@semantics");
					
					// parse the line into javadoc elements
					// (the line needs to be parsed into fragments before we can find semantic references)
					parseSemanticLineIntoFragments(line, factory, e);
					
					// identify semantic rules back
					handleModelReferences(e, rule, root);
				}
			}
		}
		
	}

	/**
	 * Iterate over all semantic handlers in {@link LoadSemanticsFromTests#getSemanticTagHandlers()}
	 * and identify potential semantic tags.
	 * 
	 * @param e
	 * @param reference
	 * @param root 
	 */
	protected void handleModelReferences(JavadocTagElement e, Reference reference, ModelDocumentation root) {
		SemanticFinder finder = new SemanticFinder();
		for (HandleSemantics sem : getSemanticTagHandlers()) {
			finder.findSemanticReferences(LoadSemanticsFromRules.this, root, e, reference, sem);
		}
	}
	
	/**
	 * Parse down until we find a line starting with 
	 * <code>rule "rule title"</code>.
	 *  
	 * @return a newly created rule, or null if none could be found
	 */
	private DroolsRule createDroolsRule(ModeldocFactory factory, int i, String[] lines) {
		for (int j = i; j < lines.length; j++) {
			if (lines[i].trim().startsWith("rule ")) {
				// found a rule
				String ruleName = lines[i].trim();
				if (!ruleName.contains("\""))
					throw new RuntimeException("Rule does not contain \": '" + lines[i] + "'");
				ruleName = ruleName.substring(ruleName.indexOf("\"") + 1);
				if (!ruleName.contains("\""))
					throw new RuntimeException("Rule does not contain \" twice: '" + lines[i] + "'");
				ruleName = ruleName.substring(0, ruleName.lastIndexOf("\""));
				
				DroolsRule rule = factory.createDroolsRule();
				rule.setName(ruleName);
				rule.setLine(j);
				return rule;
			}
		}
		
		return null;
	}

	/**
	 * <p>Parse the given semantic rule line, e.g.
	 * <code>This element {@model Element} ...</code>,
	 * and place it into JavadocFragments.</p>
	 * 
	 * <p>This is a very basic parser, and won't handle anything
	 * complicated (e.g. additional javadoc block characters).</p>
	 */
	protected void parseSemanticLineIntoFragments(String line, ModeldocFactory factory,
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
