/**
 * 
 */
package org.openiaml.docs.generation.semantics;

import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.InferenceSemantic;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.docs.modeldoc.Reference;

/**
 * Deal with @operational semantics tags.
 * 
 * @author jmwright
 */
public class HandleOperationalTag implements ITagHandler {
	
	private ModeldocFactory factory;
	private ModelDocumentation root;
	
	public HandleOperationalTag(ModelDocumentation root, ModeldocFactory factory) {
		this.root = root;
		this.factory = factory;
	}
	
	public void handleSemanticLink(String name,
			JavadocTagElement description,
			EMFClass target, Reference reference) {
		
		if (name.equals("@operational")) {
			
			// make an inference semantics link
			InferenceSemantic semantic = factory.createInferenceSemantic();
			semantic.setDescription(description);
			semantic.setReference(reference);
			
			// add it to the EMFclass
			target.getInferenceSemantics().add(semantic);
			
		}
	}
	
	public ModeldocFactory getFactory() {
		return factory;
	}

	public ModelDocumentation getDocumentationRoot() {
		return root;
	}
	
}