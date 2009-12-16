/**
 * 
 */
package org.openiaml.docs.generation;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.EMFReference;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;

/**
 * @author jmwright
 *
 */
public class LoadEMFReferences extends DocumentationHelper implements ILoader {
	
	/**
	 * Set up references.
	 * 
	 * @param einstance
	 * @param factory
	 * @param root
	 */
	public void load(ModeldocFactory factory, ModelDocumentation root) {
		
		for (EMFClass source : root.getClasses()) {
			
			for (EReference ref : source.getTargetClass().getEReferences()) {
				
				// get the destination type
				EClass refDest = ref.getEReferenceType();
				// find the corresponding EMFClass
				EMFClass refDestEMF = getEMFClassFor(root, refDest);
				
				// create a new EMFReference
				EMFReference newRef = factory.createEMFReference();
				newRef.setName(ref.getName());
				newRef.setLowerBound(ref.getLowerBound());
				newRef.setUpperBound(ref.getUpperBound());
				newRef.setContainment(ref.isContainment());
				if (refDestEMF != null) {
					newRef.setType(refDestEMF);
				}
				
				source.getReferences().add(newRef);
				
			}
			
		}
		
	}
	
}
