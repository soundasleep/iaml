/**
 * 
 */
package org.openiaml.docs.generation;

import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.List;

import org.openiaml.docs.generation.semantics.ITagHandler;
import org.openiaml.docs.generation.semantics.SemanticFinder;
import org.openiaml.docs.modeldoc.DroolsPackage;
import org.openiaml.docs.modeldoc.DroolsRule;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.docs.modeldoc.Reference;
import org.openiaml.model.drools.DroolsInferenceEngine;

/**
 * @author jmwright
 *
 */
public class LoadSemanticsFromRules extends DocumentationHelper implements ILoader {
	
	private DroolsInferenceEngine engine;
	
	private String plugin;
	
	private String ruleBase;
	
	private List<ITagHandler> semanticTagHandlers;
	
	/**
	 * @param engine
	 * @param plugin
	 * @param ruleBase
	 * @param semanticTagHandlers
	 */
	public LoadSemanticsFromRules(DroolsInferenceEngine engine, String plugin,
			String ruleBase, List<ITagHandler> semanticTagHandlers) {
		super();
		this.engine = engine;
		this.plugin = plugin;
		this.ruleBase = ruleBase;
		this.semanticTagHandlers = semanticTagHandlers;
	}



	public List<ITagHandler> getSemanticTagHandlers() {
		return semanticTagHandlers;
	}
	
	/**
	 * @param factory
	 * @param root
	 * @throws IOException 
	 */
	public void load(ModeldocFactory factory, ModelDocumentation root) throws DocumentationGenerationException {

		// for each rule file
		for (String file : engine.getRuleFiles()) {
			// load the inference rules
			String[] bits = getSimpleFilename(file);
			try {
				
				loadInferenceSemantics(factory, 
						root,
						plugin /* plugin */,
						bits[0] /* package */,
						bits[1] /* name */,
						new File(ruleBase + file ) );
				
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
					new BasicJavadocParser().parseSemanticLineIntoFragments(line, factory, e);
					
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
		for (ITagHandler sem : getSemanticTagHandlers()) {
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
	
}
