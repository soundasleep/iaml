/**
 * 
 */
package org.openiaml.docs.generation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openiaml.docs.generation.LoadFileMetrics.FileMetricAccumulator;
import org.openiaml.docs.generation.semantics.HandleExampleTag;
import org.openiaml.docs.generation.semantics.HandleImplementationTag;
import org.openiaml.docs.generation.semantics.HandleInferenceTag;
import org.openiaml.docs.generation.semantics.HandleOperationalTag;
import org.openiaml.docs.generation.semantics.ITagHandler;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.docs.modeldoc.ModeldocPackage;
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
	
	private List<ITagHandler> semanticTagHandlers;
	
	public List<ITagHandler> getSemanticTagHandlers() {
		return semanticTagHandlers;
	}
		
	public ModelDocumentation createDocumentation() throws Exception {
		final ModeldocFactory factory = ModeldocFactory.eINSTANCE;
		
		final ModelDocumentation root = factory.createModelDocumentation();
		
		// create Semantics handlers
		semanticTagHandlers = new ArrayList<ITagHandler>();
		semanticTagHandlers.add(new HandleOperationalTag(root, factory));
		semanticTagHandlers.add(new HandleInferenceTag(root, factory));
		semanticTagHandlers.add(new HandleImplementationTag(root, factory));
		semanticTagHandlers.add(new HandleExampleTag(root, factory,
					"examples" /* plugin */));
		
		// load all EMF classes
		{
			ILoader loader = new LoadEMFClasses(
					ModelPackage.eINSTANCE, 
					"org.openiaml.model", 
					"org.openiaml.model.",
					this
			);
			loader.load(factory, root);
		}

		// load additional documentation
		{
			ILoader loader = new LoadEMFDescription(
					"../org.openiaml.model/model/docs" /* doc base */, 
					"org.openiaml.model" /* plugin */, 
					"model.docs" /* package base */,
					this
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
			ILoader loader = new LoadEMFReferences(this);
			loader.load(factory, root);
		}
		
		// get all constraints
		{
			ILoader loader = new LoadOAWConstraints(
					"../org.openiaml.model.codegen.php/src/metamodel/Checks.chk",
					"org.openiaml.model.codegen.php",
					"src.metamodel",
					"Checks.chk"
			);
			loader.load(factory, root);
		}
		
		// get all model extensions
		{
			ILoader loader = new LoadOAWExtensions(
					"../org.openiaml.model.codegen.php/src/metamodel/Extensions.ext",
					"org.openiaml.model.codegen.php",
					"src.metamodel",
					"Extensions.ext"
			);
			loader.load(factory, root);
		}
		{
			ILoader loader = new LoadOAWExtensions(
					"../org.openiaml.model.codegen.php/src/template/GeneratorExtensions.ext",
					"org.openiaml.model.codegen.php",
					"src.template",
					"GeneratorExtensions.ext"
			);
			loader.load(factory, root);
		}
		
		// load all test cases for operational semantics
		{
			ILoader loader = new LoadSemanticsFromTests(
					new File("../org.openiaml.model.tests/src/org") /* source folder */,
					"org.openiaml.model.tests" /* plugin */, 
					"org" /* starting package */, 
					this
			);
			loader.load(factory, root);
		}	
		
		// load all rule files for inference semantics
		{
			ILoader loader = new LoadSemanticsFromRules(
					new CreateMissingElementsWithDrools(null, false) /* engine */,
					"org.openiaml.model.drools" /* plugin */,
					"../org.openiaml.model.drools/" /* engine base */,
					this
			);
			loader.load(factory, root);
		}
		
		// load all OAW templates for implementation notes
		{
			ILoader loader = new LoadOAWImplementationNotes(
					new File("../org.openiaml.model.codegen.php/src/template") /* source folder */,
					"org.openiaml.model.codegen.php" /* plugin */, 
					"template" /* starting package */, 
					this
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

		// get all icons
		{
			ILoader loader = new LoadGMFEditors(
					new File("../org.openiaml.model.tests.elements/") /* editor base */,
					"org.openiaml.model.tests.elements" /* plugin */,
					"" /* package base */
			);
			loader.load(factory, root);
		}

		// get all metrics
		{
			ILoader loader = new LoadMetamodelMetrics(
					ModelPackage.eINSTANCE /* root package */
			);
			loader.load(factory, root);
		}
		
		// get all metrics of the Modeldoc package
		{
			ILoader loader = new LoadMetamodelMetrics(
					ModeldocPackage.eINSTANCE, /* modeldoc package */
					"Modeldoc"
			);
			loader.load(factory, root);
		}
		
		// file metrics
		{
			FileMetricAccumulator acc = new FileMetricAccumulator();
			{
				LoadFileMetrics loader = new LoadFileMetrics(
						new File("../org.openiaml.model/") /* file root */,
						"org.openiaml.model" /* plugin name */
				);
				loader.load(factory, root);
				acc.add(loader);
			}
			{
				LoadFileMetrics loader = new LoadFileMetrics(
						new File("../org.openiaml.model.codegen.php/") /* file root */,
						"org.openiaml.model.codegen.php" /* plugin name */
				);
				loader.load(factory, root);
				acc.add(loader);
			}
			{
				LoadFileMetrics loader = new LoadFileMetrics(
						new File("../org.openiaml.model.drools/") /* file root */,
						"org.openiaml.model.drools" /* plugin name */
				);
				loader.load(factory, root);
				acc.add(loader);
			}
			{
				LoadFileMetrics loader = new LoadFileMetrics(
						new File("../org.openiaml.model.runtime/") /* file root */,
						"org.openiaml.model.runtime" /* plugin name */
				);
				loader.load(factory, root);
				acc.add(loader);
			}
			{
				LoadFileMetrics loader = new LoadFileMetrics(
						new File("../org.openiaml.model.tests/") /* file root */,
						"org.openiaml.model.tests" /* plugin name */
				);
				loader.load(factory, root);
				acc.add(loader);
			}
			{
				LoadFileMetrics loader = new LoadFileMetrics(
						new File("../org.openiaml.model.tests.diagram/") /* file root */,
						"org.openiaml.model.tests.diagram" /* plugin name */
				);
				loader.load(factory, root);
				acc.add(loader);
			}
			
			// publish total metrics
			acc.publish(factory, root);
		}
		
		// file metrics of just /src folders in tests
		{
			FileMetricAccumulator acc = new FileMetricAccumulator(".src");
			{
				LoadFileMetrics loader = new LoadFileMetrics(
						new File("../org.openiaml.model/src/") /* file root */,
						"org.openiaml.model.src" /* plugin name */
				);
				loader.load(factory, root);
				acc.add(loader);
			}
			{
				LoadFileMetrics loader = new LoadFileMetrics(
						new File("../org.openiaml.model.codegen.php/src/") /* file root */,
						"org.openiaml.model.codegen.php.src" /* plugin name */
				);
				loader.load(factory, root);
				acc.add(loader);
			}
			{
				LoadFileMetrics loader = new LoadFileMetrics(
						new File("../org.openiaml.model.drools/src/") /* file root */,
						"org.openiaml.model.drools.src" /* plugin name */
				);
				loader.load(factory, root);
				acc.add(loader);
			}
			{
				LoadFileMetrics loader = new LoadFileMetrics(
						new File("../org.openiaml.model.runtime/src/") /* file root */,
						"org.openiaml.model.runtime.src" /* plugin name */
				);
				loader.load(factory, root);
				acc.add(loader);
			}
			{
				LoadFileMetrics loader = new LoadFileMetrics(
						new File("../org.openiaml.model.tests/src/") /* file root */,
						"org.openiaml.model.tests.src" /* plugin name */
				);
				loader.load(factory, root);
				acc.add(loader);
			}
			{
				LoadFileMetrics loader = new LoadFileMetrics(
						new File("../org.openiaml.model.tests.diagram/src/") /* file root */,
						"org.openiaml.model.tests.diagram.src" /* plugin name */
				);
				loader.load(factory, root);
				acc.add(loader);
			}
			
			// publish total metrics
			acc.publish(factory, root);
		}

		return root;
	}
	
}
