/**
 * 
 */
package org.openiaml.docs.generation.semantics;

import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.ImplementationNote;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.docs.modeldoc.Reference;

/**
 * Deal with @implementation semantics tags.
 * 
 * @author jmwright
 */
public class HandleImplementationTag implements ITagHandler {
	
	private ModeldocFactory factory;
	private ModelDocumentation root;
	
	public HandleImplementationTag(ModelDocumentation root, ModeldocFactory factory) {
		this.root = root;
		this.factory = factory;
	}
	
	public void handleSemanticLink(String name,
			JavadocTagElement description,
			EMFClass target, Reference reference) {
		
		if (name.equals("@implementation")) {
			
			// make an implementation semantics link
			ImplementationNote note = factory.createImplementationNote();
			note.setDescription(description);
			note.setReference(reference);
			
			// add it to the EMFclass
			target.getImplementationNotes().add(note);
			
		}
	}
	
	public ModeldocFactory getFactory() {
		return factory;
	}

	public ModelDocumentation getDocumentationRoot() {
		return root;
	}
	
}