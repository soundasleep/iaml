/**
 * 
 */
package org.openiaml.model.diagram.helpers;

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
	 * Allows elements in a breadcrumb to be linked if necessary.
	 * 
	 * @author jmwright
	 *
	 */
	static public class BreadcrumbLinker {
		
		/**
		 * By default, just returns the same string.
		 * 
		 * @param object
		 * @param s
		 * @return
		 */
		public String link(EObject object, String s) {
			return s;
		}
		
	}
	
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
		return breadcrumb(object, limit, new BreadcrumbLinker());
	}
	
	/**
	 * Get a breadcrumb string illustrating its location
	 * to the root object, rendering potential links with the
	 * given linker.
	 * 
	 * @param object
	 * @param limit the maximum number of items to get
	 * @return
	 */
	public static String breadcrumb(EObject object, int limit, BreadcrumbLinker linker) {
		String result = linker.link(object, getEObjectBreadcrumbString(object));
		
		// halt if we have hit the root of the heirarchy
		if (object.eContainer() == null)
			return result;
		
		// more elements?
		if (limit > 1) {
			result = breadcrumb(object.eContainer(), limit - 1, linker) + " > " + result;
		} else {
			// we are definitely not the root, so there are more children
			result = "... > " + result;
		}
		
		return result;
	}
	
	/**
	 * Just return the specific breadcrumb bit for the given object.
	 * The {@link #breadcrumb(EObject)} method joins these together
	 * into a breadcrumb string.
	 * 
	 * @param object
	 * @return
	 */
	public static String getEObjectBreadcrumbString(EObject object) {
		if (object.eContainer() == null) {
			// root
			if (object instanceof NamedElement) {
				NamedElement e = (NamedElement) object;
				if (e.getName() != null && !e.getName().isEmpty()) {
					return "'" + e.getName() + "'";		// 'root'
				} else {
					return e.eClass().getName();			// InternetApplication
				}
			} else {
				return object.eClass().getName();			// EObject
			}
		} else {
			// child
			if (object instanceof NamedElement) {
				NamedElement e = (NamedElement) object;
				if (e.getName() != null && !e.getName().isEmpty()) {
					return e.eClass().getName() + ": '" + e.getName() + "'";		// Page: 'foo'
				} else {
					return e.eClass().getName();		// Page
				}
			} else {
				return object.eClass().getName();		// EObject
			}
		}
	}
	
}
