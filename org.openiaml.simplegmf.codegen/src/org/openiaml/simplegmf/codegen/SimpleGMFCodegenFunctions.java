/**
 * 
 */
package org.openiaml.simplegmf.codegen;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.openiaml.simplegmf.model.simplegmf.FigureConfiguration;
import org.openiaml.simplegmf.model.simplegmf.GMFConfiguration;


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
	
	public static String toStringOrNull(String s) {
		return s == null ? "" : s;
	}
	
	/**
	 * Get the first shape classifier that is not default, that
	 * matches the given classifier.
	 * 
	 * @param root
	 * @param classifier
	 * @return
	 */
	public static String getShapeType(GMFConfiguration root, EClass classifier) {
		// do any figure configurations exist for this classifier?
		for (FigureConfiguration fc : root.getFigureConfigurations()) {
			if (fc.getClassifier() != null 
					&& fc.getClassifier().isSuperTypeOf(classifier)
					&& (
						/* a default shape */ !fc.getShape().toString().equals("DEFAULT"))) {
				return fc.getShape().toString();
			}
		}
		
		// get the default shape
		for (FigureConfiguration fc : root.getFigureConfigurations()) {
			if (fc.isIsDefault()) {
				return fc.getShape().toString();
			}
		}
		
		throw new RuntimeException("Found no default FigureConfigurations for " + classifier);
		
	}
	
	// counter functions
	private static Map<String,Integer> counterMap = new HashMap<String,Integer>();
	
	public static void counterReset(String key) {
		System.out.println("reset " + key);
		counterMap.put(key, 0);
	}
	
	public static String counterGet(String key) {
		System.out.println("get " + key);
		return counterMap.get(key).toString();
	}
	
	public static void counterIncrement(String key) {
		System.out.println("increment " + key);
		counterMap.put(key, counterMap.get(key) + 1);
	}
	
}
