/**
 * 
 */
package org.openiaml.model.inference;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.wires.SyncWire;

/**
 * @author jmwright
 *
 */
public interface ICreateElements {

	/**
	 * @return
	 */
	public SyncWire createSyncWire(ContainsWires container, WireEdgesSource source,
			WireEdgeDestination target) throws InferenceException;

	/**
	 * @param element
	 * @param reference
	 * @param value
	 */
	void setValue(EObject element, EStructuralFeature reference, Object value)
	 throws InferenceException;
	
	
}
