/**
 * 
 */
package org.openiaml.docs.generation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openiaml.docs.generation.semantics.HandleExampleTag;
import org.openiaml.docs.generation.semantics.HandleImplementationTag;
import org.openiaml.docs.generation.semantics.HandleInferenceTag;
import org.openiaml.docs.generation.semantics.HandleOperationalTag;
import org.openiaml.docs.generation.semantics.ITagHandler;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
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
			ILoader loader = new LoadEMFClasses(
					ModelPackage.eINSTANCE, 
					"org.openiaml.model", 
					"org.openiaml.model."
			);
			loader.load(factory, root);
		}
		
		// load additional documentation
		{
			ILoader loader = new LoadEMFDescription(
					"../org.openiaml.model/model/docs" /* doc base */, 
					"org.openiaml.model" /* plugin */, 
					"model.docs" /* package base */
			);
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
			ILoader loader = new LoadOAWConstraints(
					"../org.openiaml.model.codegen.oaw/src/metamodel/Checks.chk",
					"org.openiaml.model.codegen.oaw",
					"src.metamodel",
					"Checks.chk"
			);
			loader.load(factory, root);
		}
		
		// get all model extensions
		{
			ILoader loader = new LoadOAWExtensions(
					"../org.openiaml.model.codegen.oaw/src/metamodel/Extensions.ext",
					"org.openiaml.model.codegen.oaw",
					"src.metamodel",
					"Extensions.ext"
			);
			loader.load(factory, root);
		}
		{
			ILoader loader = new LoadOAWExtensions(
					"../org.openiaml.model.codegen.oaw/src/template/GeneratorExtensions.ext",
					"org.openiaml.model.codegen.oaw",
					"src.template",
					"GeneratorExtensions.ext"
			);
			loader.load(factory, root);
		}
		
		// create Semantics handlers
		List<ITagHandler> semanticTags = new ArrayList<ITagHandler>();
		semanticTags.add(new HandleOperationalTag(root, factory));
		semanticTags.add(new HandleInferenceTag(root, factory));
		semanticTags.add(new HandleImplementationTag(root, factory));
		semanticTags.add(new HandleExampleTag(root, factory));
		
		// load all test cases for operational semantics
		{
			ILoader loader = new LoadSemanticsFromTests(
					new File("../org.openiaml.model.tests/src/org") /* source folder */,
					"org.openiaml.model.tests" /* plugin */, 
					"org" /* starting package */, 
					semanticTags
			);
			loader.load(factory, root);
		}
		
		// load all rule files for inference semantics
		{
			ILoader loader = new LoadSemanticsFromRules(
					new CreateMissingElementsWithDrools(null, false) /* engine */,
					"org.openiaml.model.drools" /* plugin */,
					"../org.openiaml.model.drools/" /* engine base */,
					semanticTags
			);
			loader.load(factory, root);
		}
		
		// load all OAW templates for implementation notes
		{
			ILoader loader = new LoadOAWImplementationNotes(
					"org.openiaml.model.codegen.oaw" /* plugin */,
					"../org.openiaml.model.codegen.oaw/" /* engine base */,
					semanticTags
			);
			loader.load(factory, root);
		}
		
		// get all icons
		{
			ILoader loader = new LoadIcons(
					new File("../org.openiaml.model.edit/icons/full/obj16/") /* icon base */,
					"org.openiaml.model.edit" /* plugin */,
					"icons.full.obj16" /* package base */
			);
			loader.load(factory, root);
		}
		
		return root;
	}
	
}
