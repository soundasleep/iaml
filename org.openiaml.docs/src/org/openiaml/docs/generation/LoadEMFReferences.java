/**
 * 
 */
package org.openiaml.docs.generation;

import java.util.HashMap;
import java.util.Map;

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
		
		Map<EReference,EMFReference> allRefs = new HashMap<EReference,EMFReference>();
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
				newRef.setTypeName(refDest.getName());
				
				source.getReferences().add(newRef);
				
				// save copy
				allRefs.put(ref, newRef);
				
			}
			
		}
		
		// once all references have been loaded, create opposite references
		for (EReference ref : allRefs.keySet()) {
			EMFReference target = allRefs.get(ref);
			
			if (ref.getEOpposite() != null) {
				if (allRefs.containsKey(ref.getEOpposite())) {
					// found it
					target.setOpposite( allRefs.get(ref.getEOpposite()) );
				}
			}
		}
		
	}
	
}
