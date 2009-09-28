/**
 * 
 */
package org.openiaml.docs.generation.semantics;

import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.JavadocTagElement;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.docs.modeldoc.Reference;

/**
 * A generic interface to handle semantics.
 * 
 * @author jmwright
 *
 */
public interface HandleSemantics {
	
	/**
	 * 
	 * @param tag the javadoc tag, e.g. '@example' or '@semantics'
	 * @param description the source @semantics tag for description purposes   
	 * @param target the target EMFclass to add the semantic type to
	 * @param reference a reference to the source of the initial @semantics tag
	 */
	public void handleSemanticLink(String tag, JavadocTagElement description, EMFClass target, Reference reference); 
	
	public ModelDocumentation getDocumentationRoot();
	
	public ModeldocFactory getFactory();
	
}
