/**
 * 
 */
package org.openiaml.emf.properties;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

/**
 * @author jmwright
 *
 */
public interface IEMFElementSelector {

	public boolean ignoreClass(EClass ref);
	
	public boolean ignoreReference(EReference ref);
	
	public boolean ignoreAttribute(EAttribute ref);
	
	/**
	 * Default implementation; does not ignore any elements.
	 * 
	 * @author jmwright
	 */
	public class DefaultElementSelector implements IEMFElementSelector {

		public boolean ignoreAttribute(EAttribute ref) {
			return false;
		}

		public boolean ignoreClass(EClass ref) {
			return false;
		}

		public boolean ignoreReference(EReference ref) {
			return false;
		}
		
	}
	
}
