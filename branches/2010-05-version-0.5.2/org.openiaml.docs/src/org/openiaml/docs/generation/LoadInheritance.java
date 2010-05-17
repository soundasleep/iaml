/**
 * 
 */
package org.openiaml.docs.generation;

import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;

/**
 * @author jmwright
 *
 */
public class LoadInheritance implements ILoader {
	
	/**
	 * Set up inheritance.
	 * 
	 * @param einstance
	 * @param factory
	 * @param root
	 */
	public void load(ModeldocFactory factory, ModelDocumentation root) {
		
		for (EMFClass source : root.getClasses()) {
			for (EMFClass target : root.getClasses()) {
				// ignore self
				if (!source.equals(target)) {
					
					if (source.getTargetClass().getESuperTypes().contains(target.getTargetClass())) {
						// this class extends directly
						source.getSupertypes().add(target);
					}
					
				}
			}
		}
		
	}
	
}
