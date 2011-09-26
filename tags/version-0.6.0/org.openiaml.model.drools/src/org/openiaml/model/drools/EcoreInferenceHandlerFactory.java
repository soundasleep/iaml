/**
 * 
 */
package org.openiaml.model.drools;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;

/**
 * Uses the {@link EcoreInferenceHandler}.
 * 
 * @author jmwright
 *
 */
public class EcoreInferenceHandlerFactory implements ICreateElementsFactory {

	@Override
	public ICreateElements createHandler(EObject model) {
		return new EcoreInferenceHandler(model.eResource());
	}
	
}