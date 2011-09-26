/**
 * 
 */
package org.openiaml.model.drools;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.inference.ICreateElements;

/**
 * An interface to create {@link ICreateElement} implementations.
 * 
 * @author jmwright
 *
 */
public interface ICreateElementsFactory {

	/**
	 * Create the {@link ICreateElement} interface for the given
	 * {@link EObject model}.
	 * 
	 * @return A new ICreateElement implementation
	 */
	public ICreateElements createHandler(EObject model);
	
}
