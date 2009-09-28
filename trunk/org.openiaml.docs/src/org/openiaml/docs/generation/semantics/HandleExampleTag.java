/**
 * 
 */
package org.openiaml.docs.generation.semantics;

import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.Example;
import org.openiaml.docs.modeldoc.JavaClass;
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
	
	public HandleExampleTag(ModelDocumentation root, ModeldocFactory factory) {
		this.root = root;
		this.factory = factory;
	}
	
	public void handleSemanticLink(String name,
			JavadocTagElement description,
			EMFClass target, Reference reference) {
		
		if (name.equals("@example")) {
			
			if (reference instanceof JavaClass) {
				JavaClass jc = (JavaClass) reference;

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
				
				target.getExamples().add(example);

			} else {
				throw new IllegalArgumentException("Unknown example reference: " + reference + ", class: " + reference.getClass());
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