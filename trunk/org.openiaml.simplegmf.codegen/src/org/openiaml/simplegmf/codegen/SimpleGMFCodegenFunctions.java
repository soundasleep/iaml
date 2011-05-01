/**
 * 
 */
package org.openiaml.simplegmf.codegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	private static SimpleGMFCodegenFunctions instance;
	
	/**
	 * Reset all of the fields used in this static container; i.e.
	 * reset the singleton.
	 */
	public static void reset() {
		instance = null;
	}
	
	public static SimpleGMFCodegenFunctions getInstance() {
		if (instance == null) {
			instance = new SimpleGMFCodegenFunctions();
		}
		return instance;
	}
	
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
	private Map<String,Integer> counterMap = new HashMap<String,Integer>();
	
	public static void counterReset(String key) {
		getInstance().counterMap.put(key, 0);
	}
	
	public static String counterGet(String key) {
		return getInstance().counterMap.get(key).toString();
	}
	
	public static void counterIncrement(String key) {
		getInstance().counterMap.put(key, getInstance().counterMap.get(key) + 1);
	}
	
	private static class SavedReference {
		private String figureName;
		private String labelName;
		private String href;
		private boolean elementIcon;

		public SavedReference(String figureName, String labelName, String href,
				boolean elementIcon) {
			super();
			this.figureName = figureName;
			this.labelName = labelName;
			this.href = href;
			this.elementIcon = elementIcon;
		}

	}
	
	private List<SavedReference> savedReferences = new ArrayList<SavedReference>();
	
	// for saving back references to labels
	public static void labelSave(String figureName, String labelName, String href, Boolean elementIcon) {
		SavedReference ref = new SavedReference(figureName, labelName, href, elementIcon);
		getInstance().savedReferences.add(ref);
	}

	public static List<Integer> savedLabelList() {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < getInstance().savedReferences.size(); i++) {
			result.add(i);
		}
		return result;
	}
	
	public static String savedLabelFigureName(Integer i) {
		return getInstance().savedReferences.get(i).figureName;
	}
	
	public static String savedLabelName(Integer i) {
		return getInstance().savedReferences.get(i).labelName;
	}
	
	public static String savedLabelHref(Integer i) {
		return getInstance().savedReferences.get(i).href;
	}
	
	public static Boolean savedLabelElementIcon(Integer i) {
		return getInstance().savedReferences.get(i).elementIcon;
	}
	
}

