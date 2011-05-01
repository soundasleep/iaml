/**
 * 
 */
package org.openiaml.simplegmf.codegen;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * Runtime functions for SimpleGMF codegen.
 * 
 * @author jmwright
 *
 */
public class SimpleGMFCodegenFunctions {
	
	public static String debug(EObject o) {
		if (o != null && o.eIsProxy())
			EcoreUtil.resolve(o, CurrentModel.getCurrentModel().eResource() );
		
		return o == null ? "null" : o.toString();
	}
	
}
