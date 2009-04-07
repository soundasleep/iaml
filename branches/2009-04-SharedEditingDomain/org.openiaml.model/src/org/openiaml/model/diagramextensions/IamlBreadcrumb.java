/**
 * 
 */
package org.openiaml.model.diagramextensions;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.NamedElement;

/**
 * Helper class to convert an EObject reference into
 * a breadcrumb string illustrating its location
 * to the root object, e.g.
 * 
 * "'root' > Page 'one' > Form 'test'"  
 * 
 * @author jmwright
 */
public class IamlBreadcrumb {
	
	/**
	 * Get a breadcrumb string illustrating its location
	 * to the root object.
	 * 
	 * @param object
	 * @return
	 */
	public static String breadcrumb(EObject object) {
		return breadcrumb(object, 3);
	}

	/**
	 * Get a breadcrumb string illustrating its location
	 * to the root object.
	 * 
	 * @param object
	 * @param limit the maximum number of items to get
	 * @return
	 */
	public static String breadcrumb(EObject object, int limit) {
		String result;
		if (object.eContainer() == null) {
			// root
			if (object instanceof NamedElement) {
				NamedElement e = (NamedElement) object;
				if (e.getName() != null && !e.getName().isEmpty()) {
					result = "'" + e.getName() + "'";		// 'root'
				} else {
					result = e.eClass().getName();			// InternetApplication
				}
			} else {
				result = object.eClass().getName();			// EObject
			}
			return result;		// can't go any further
		} else {
			// child
			if (object instanceof NamedElement) {
				NamedElement e = (NamedElement) object;
				if (e.getName() != null && !e.getName().isEmpty()) {
					result = e.eClass().getName() + ": '" + e.getName() + "'";		// Page: 'foo'
				} else {
					result = e.eClass().getName();		// Page
				}
			} else {
				result = object.eClass().getName();		// EObject
			}
		}
		
		// more elements?
		if (limit > 1) {
			result = breadcrumb(object.eContainer(), limit - 1) + " > " + result;
		} else {
			// we are definitely not the root, so there are more children
			result = "... > " + result;
		}
		
		return result;
	}
	
}
