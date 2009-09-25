/**
 * 
 */
package org.openiaml.emf.properties;

import org.eclipse.emf.ecore.EObject;

/**
 * Interface for a property investigator.
 * 
 * @author jmwright
 *
 */
public interface IPropertyInvestigator {
	public String getName();
	public Object evaluate(EObject root);
}