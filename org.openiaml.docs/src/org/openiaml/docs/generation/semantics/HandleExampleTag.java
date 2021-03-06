/**
 * 
 */
package org.openiaml.docs.generation.semantics;

import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.Example;
import org.openiaml.docs.modeldoc.FileReference;
import org.openiaml.docs.modeldoc.JavaClass;
import org.openiaml.docs.modeldoc.JavaMethod;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModelReference;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.docs.modeldoc.Reference;

/**
 * Default implementation of HandleSemantics. 
 * 
 * Can deal with @example.
 * 
 * @author jmwright
 */
public class HandleExampleTag implements ITagHandler {
	
	private ModeldocFactory factory;
	private ModelDocumentation root;
	
	// location of the examples, e.g. "org.openiaml.examples"
	private String examplePlugin;
	
	public HandleExampleTag(ModelDocumentation root, ModeldocFactory factory,
			String examplePlugin) {
		this.root = root;
		this.factory = factory;
		this.examplePlugin = examplePlugin;
	}
	
	public void handleSemanticLink(String name,
			JavadocTagElement description,
			EMFClass target, Reference reference) throws SemanticHandlerException {
		
		if (name.equals("@example")) {
			
			if (reference instanceof JavaClass || reference instanceof JavaMethod) {
				JavaClass jc = null;
				if (reference instanceof JavaClass) {
					jc = (JavaClass) reference;
				} else {
					jc = ((JavaMethod) reference).getJavaClass();
				}

				// make an inference semantics link
				Example example = factory.createExample();
				example.setDescription(description);
				example.setReference(reference);
				
				// make a new ModelReference
				ModelReference ref = factory.createModelReference();
				ref.setPlugin(jc.getPlugin());
				ref.setPackage(jc.getPackage());
				ref.setName(jc.getName());
				root.getReferences().add(ref);
				
				example.setExampleModel(ref);

				// make a new FileReference for the example .html
				// as per issue 128, this will be in a new location
				FileReference file = factory.createFileReference();
				file.setPlugin(examplePlugin);
				file.setPackage(jc.getName());
				file.setName(jc.getName() + ".html");
				root.getReferences().add(file);
				
				example.setExampleLocation(file);

				target.getExamples().add(example);

			} else {
				throw new SemanticHandlerException("Unknown example reference: " + reference + ", class: " + reference.getClass());
			}

		}
		
	}

	public ModeldocFactory getFactory() {
		return factory;
	}

	public ModelDocumentation getDocumentationRoot() {
		return root;
	}
	
}