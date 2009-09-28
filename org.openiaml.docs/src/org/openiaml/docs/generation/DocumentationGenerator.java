/**
 * 
 */
package org.openiaml.docs.generation;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.docs.generation.semantics.HandleExampleTag;
import org.openiaml.docs.generation.semantics.HandleSemantics;
import org.openiaml.docs.generation.semantics.HandleSemanticsTag;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.model.model.ModelPackage;

/**
 * Collects documentation information from multiple sources into a
 * modeldoc model.
 * 
 * @author jmwright
 *
 */
public class DocumentationGenerator {
	public ModelDocumentation createDocumentation() throws Exception {
		final ModeldocFactory factory = ModeldocFactory.eINSTANCE;
		
		final ModelDocumentation root = factory.createModelDocumentation();
		
		// load all EMF classes
		{
			ILoader loader = new LoadEMFClasses(ModelPackage.eINSTANCE);
			loader.load(factory, root);
		}
		
		// set up inheritance
		{
			ILoader loader = new LoadInheritance();
			loader.load(factory, root);
		}

		// set up references
		{
			ILoader loader = new LoadEMFReferences();
			loader.load(factory, root);
		}

		// get all constraints
		{
			ILoader loader = new LoadOAWConstraints();
			loader.load(factory, root);
		}
		
		// get all model extensions
		{
			ILoader loader = new LoadOAWExtensions();
			loader.load(factory, root);
		}
		
		// create Semantics handlers
		List<HandleSemantics> semanticTags = new ArrayList<HandleSemantics>();
		semanticTags.add(new HandleSemanticsTag(root, factory));
		semanticTags.add(new HandleExampleTag(root, factory));
		
		// load all test cases for operational semantics
		{
			ILoader loader = new LoadSemanticsFromTests(semanticTags);
			loader.load(factory, root);
		}
		
		// load all rule files for inference semantics
		{
			ILoader loader = new LoadSemanticsFromRules(semanticTags);
			loader.load(factory, root);
		}
		
		// get all icons
		{
			ILoader loader = new LoadIcons();
			loader.load(factory, root);
		}
		
		return root;
	}
	
}
