/**
 * 
 */
package org.openiaml.model.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.model.ModelPackage;

/**
 * @author jmwright
 *
 */
public class IdentifyUnusedMetamodelFeatures extends TestCase {
	
	private class FeatureCountMap extends HashMap<EClass, Map<EStructuralFeature, Integer>> {

		private static final long serialVersionUID = 1L;

		/**
		 * A feature instance has been found.
		 * 
		 * @param cls
		 * @param ft
		 */
		public void featureInstance(EClass cls, EStructuralFeature ft) {
			if (!containsKey(cls)) {
				put(cls, new HashMap<EStructuralFeature, Integer>());
			}
			if (!get(cls).containsKey(ft)) {
				get(cls).put(ft, 0);
			}
			get(cls).put(ft, get(cls).get(ft) + 1);
		}

		/**
		 * @param cls
		 * @param ft
		 * @return
		 */
		public int getFeatureInstanceCount(EClass cls, EStructuralFeature ft) {
			if (!containsKey(cls))
				return 0;
			
			if (!get(cls).containsKey(ft))
				return 0;
			
			return get(cls).get(ft);
		}
		
	}
	
	private class UniqueClassMap extends HashMap<EClass, Integer> {
		
		private static final long serialVersionUID = 1L;

		/**
		 * Found a unique instance of this class.
		 * 
		 * @param eClass
		 */
		public void uniqueInstance(EClass c) {
			if (!containsKey(c)) {
				put(c, 0);
			}
			put(c, get(c) + 1);
		}

		/**
		 * @param cls
		 * @return
		 */
		public int getCount(EClass cls) {
			if (!containsKey(cls))
				return 0;
			
			return get(cls);
		}
	
	}
	
	private class UniqueAttributeMap extends HashMap<EClass, Map<EAttribute, Set<Object>>> {
		
		private static final long serialVersionUID = 1L;

		/**
		 * Found an instance of this attribute in this class, with the
		 * provided value.
		 * 
		 * @param cls
		 * @param attr
		 * @param value
		 */
		public void attributeValue(EClass cls, EAttribute attr, Object value) {
			if (!containsKey(cls)) {
				put(cls, new HashMap<EAttribute, Set<Object>>());
			}
			if (!get(cls).containsKey(attr)) {
				get(cls).put(attr, new HashSet<Object>());
			}
			get(cls).get(attr).add(value);
		}

		/**
		 * @param cls
		 * @param attr
		 * @return
		 */
		public int getUniqueAttributes(EClass cls, EAttribute attr) {
			if (!containsKey(cls)) {
				return 0;
			}
			if (!get(cls).containsKey(attr)) {
				return 0;
			}
			return get(cls).get(attr).size();
		}

	}
	
	private class SupertypeUsageMap extends HashMap<EClass, Map<EClass, Integer>> {
	
		private static final long serialVersionUID = 1L;

		/**
		 * All of the given classes have been used in an instance of the
		 * given class.
		 * 
		 * @param cls given classs
		 * @param set used references
		 */
		public void usedClasses(EClass cls, Set<EClass> set) {
			if (!containsKey(cls)) {
				put(cls, new HashMap<EClass, Integer>());
			}
			for (EClass c : set) {
				if (!get(cls).containsKey(c)) {
					get(cls).put(c, 0);
				}
				get(cls).put(c, get(cls).get(c) + 1);
			}
		}

		/**
		 * @param cls
		 * @param supertype
		 * @return
		 */
		public int getUsage(EClass cls, EClass supertype) {
			if (!containsKey(cls))
				return 0;
			
			if (!get(cls).containsKey(supertype))
				return 0;
			
			return get(cls).get(supertype);
		}
		
	}
	
	private class IdentifyState {
		
		// each class has a collection of local & inherited
		// features; count the number of class instances that
		// possess non-default values for these features
		public FeatureCountMap featureCount =
			new FeatureCountMap();
		
		// keep track of how many direct supertype references are actually used
		public SupertypeUsageMap supertypeUsageMap =
			new SupertypeUsageMap();
		
		// a count of the number of unique instances of this class,
		// ignoring subtypes
		public UniqueClassMap uniqueClasses =
			new UniqueClassMap();
		
		// a count of the number of unique instances of
		// attributes for a particular class, ignoring subtypes
		public UniqueAttributeMap uniqueAttributes =
			new UniqueAttributeMap();

		// a count of all object instances
		public int allInstancesCount = 0;
		
	}
	
	public void testIdentifyUnusedMetamodelFeatures() throws Exception {
		
		IdentifyState state = new IdentifyState();
		
		// now go through all model instances in /infer-output
		// File dir = new File("src/org/openiaml/model/tests/codegen/functions/");
		File dir = new File("infer-output/");
		File[] files = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".iaml");
			}
			
		});
		
		int filesDone = 0;
		for (File f : files) {
			System.out.println("Loading " + f + " (" + filesDone + "/" + files.length + ") ...");
			filesDone++;
			
			// load the model
			EObject model = ModelLoader.load(f);
			
			// handle root object
			handleEObject(state, model);
			
			// iterate through its contents
			Iterator<EObject> it = model.eAllContents();
			while (it.hasNext()) {
				EObject obj = it.next();
				
				handleEObject(state, obj);
			}
		}
		
		// now output everything, based on the metamodel description
		System.out.println("Generating output...");
		generateOutput(state, ModelPackage.eINSTANCE);
	}
	
	/**
	 * Actually generates 
	 * 
	 * @param state
	 * @throws IOException 
	 */
	private void generateOutput(IdentifyState state, EPackage pkg) throws IOException {

		StringBuffer buf = new StringBuffer();
		buf.append("<html>");
		buf.append("<link rel=\"stylesheet\" href=\"unused.css\" type=\"text/css\" />");
		buf.append("<table>");
		buf.append("<tr class=\"legend\">");
		buf.append("<td class=\"name\">name</td>");
		buf.append("<td class=\"instances\">instances</td>");
		buf.append("<td class=\"percent\">%</td>");
		buf.append("<td class=\"unique\">unique values</td>");
		buf.append("</tr>\n");
		buf.append("</table>\n");
		
		// get all classes in the package and subpackage
		Set<EClass> allClasses = getAllClasses(pkg);
		
		for (EClass cls : sortedClasses(allClasses)) {
			// ignore interface, abstract classes
			if (cls.isInterface() || cls.isAbstract())
				continue;
			
			int c3 = state.uniqueClasses.getCount(cls);
			String pct3 = getPercent(c3, state.allInstancesCount);
			
			buf.append("<table class=\"class\">\n");
			buf.append("<tr class=\"main class\"><th class=\"name\">");
			if (cls.isAbstract() || cls.isInterface()) buf.append("<i>");
			buf.append(cls.getName());
			if (cls.isAbstract() || cls.isInterface()) buf.append("</i>");
			buf.append("</th><td class=\"instances count").append(c3).append("\">");
			buf.append(c3);
			buf.append("</td><td class=\"percent pct").append(pct3).append("\">");
			buf.append(pct3);
			buf.append("</td><td class=\"unique\">");
			buf.append("</td></tr>\n");
			
			// for every local structural feature
			for (EStructuralFeature ft : sortedFeatures(cls.getEStructuralFeatures())) {
				int count = state.featureCount.getFeatureInstanceCount(cls, ft);
				String pct = getPercent(count, c3);
				
				buf.append("<tr class=\"feature\">");
				buf.append("<th class=\"name\">");
				buf.append(ft.getName());
				buf.append("</th><td class=\"instances count").append(count).append("\">");
				buf.append(count);
				buf.append("</td><td class=\"percent pct").append(pct).append("\">");
				buf.append(pct);
				// if it's an attribute
				if (ft instanceof EAttribute) {
					EAttribute attr = (EAttribute) ft;
					int counta = state.uniqueAttributes.getUniqueAttributes(cls, attr);
					buf.append("</td><td class=\"unique uniq").append(counta).append("\">");
					buf.append(counta);
				} else {
					buf.append("</td><td class=\"unique\">");
				}
				buf.append("</td></tr>\n");
				
			}

			// get all the defined supertypes first
			for (EClass supertype : sortedClasses(cls.getEAllSuperTypes())) {
				int c2 = state.supertypeUsageMap.getUsage(cls, supertype);
				String pct2 = getPercent(c2, c3);
				
				buf.append("<tr class=\"super class\"><th class=\"name\">");
				if (supertype.isAbstract() || supertype.isInterface()) buf.append("<i>");
				buf.append(supertype.getName());
				if (supertype.isAbstract() || supertype.isInterface()) buf.append("</i>");
				buf.append("</th><td class=\"instances count").append(c2).append("\">");
				buf.append(c2);
				buf.append("</td><td class=\"percent pct").append(pct2).append("\">");
				buf.append(pct2);
				buf.append("</td><td class=\"unique\">");
				buf.append("</td></tr>\n");
				
				// for every local structural feature
				for (EStructuralFeature ft : sortedFeatures(supertype.getEStructuralFeatures())) {
					int count = state.featureCount.getFeatureInstanceCount(cls, ft);
					String pct = getPercent(count, c2);
					
					buf.append("<tr class=\"feature\">");
					buf.append("<th class=\"name\">");
					buf.append(ft.getName());
					buf.append("</th><td class=\"instances count").append(count).append("\">");
					buf.append(count);
					buf.append("</td><td class=\"percent pct").append(pct).append("\">");
					buf.append(pct);
					// if it's an attribute
					if (ft instanceof EAttribute) {
						EAttribute attr = (EAttribute) ft;
						int counta = state.uniqueAttributes.getUniqueAttributes(cls, attr);
						buf.append("</td><td class=\"unique uniq").append(counta).append("\">");
						buf.append(counta);
					} else {
						buf.append("</td><td class=\"unique\">");
					}
					buf.append("</td></tr>\n");
					
				}

			}

			buf.append("</table>\n");

		}
		
		buf.append("</table>\n");
		buf.append("</html>\n");

		// write to file
		System.out.println("Writing to file...");
		FileWriter fw = new FileWriter(new File("unused-metamodel-features.html"));
		fw.write(buf.toString());
		fw.close();

	}

	/**
	 * Actually analyses a given EObject, and stores
	 * the results in the given state.
	 * 
	 * @param state
	 * @param model
	 */
	private void handleEObject(IdentifyState state, EObject obj) {

		// this is a unique instance of this class type
		EClass cls = obj.eClass();
		state.uniqueClasses.uniqueInstance(cls);
		state.allInstancesCount++;
		
		// keep track of how many supertype references are actually used
		Set<EClass> usedSupertypeReferences = new HashSet<EClass>();
		
		// get all attribute instances
		for (EAttribute attr : cls.getEAllAttributes()) {
			// is it set?
			if (obj.eIsSet(attr)) {
				Object value = obj.eGet(attr);
				
				// is it default?
				if (value != null && !value.equals(attr.getDefaultValue())) {
					// put the value
					state.uniqueAttributes.attributeValue(cls, attr, value);
					
					// keep track of direct container
					usedSupertypeReferences.add(attr.getEContainingClass());
					// and all supertypes
					for (EClass supertype : attr.getEContainingClass().getEAllSuperTypes()) {
						usedSupertypeReferences.add(supertype);
					}
					
				}
			}
		}
		
		// get all EStructuralFeatures
		for (EStructuralFeature ft : cls.getEAllStructuralFeatures()) {
			// is it set?
			if (obj.eIsSet(ft)) {
				// register an instance
				state.featureCount.featureInstance(cls, ft);

				// keep track of direct container
				usedSupertypeReferences.add(ft.getEContainingClass());
				// and all supertypes
				for (EClass supertype : ft.getEContainingClass().getEAllSuperTypes()) {
					usedSupertypeReferences.add(supertype);
				}

			}
		}
		
		// now mark supertype usage
		state.supertypeUsageMap.usedClasses(cls, usedSupertypeReferences);
	}

	/**
	 * Get a percent out of the two numbers
	 * 
	 * @param c3
	 * @param allInstancesCount
	 * @return
	 */
	private String getPercent(int a, int b) {
		if (b == 0)
			return "-";
		return Integer.toString((100 * a) / b);
	}

	/**
	 * Sort the given list of classes based on name.
	 * 
	 * @param allClasses
	 * @return
	 */
	private List<EClass> sortedClasses(Collection<EClass> set) {
		List<EClass> result = new ArrayList<EClass>();
		result.addAll(set);
		Collections.sort(result, new Comparator<EClass>() {

			@Override
			public int compare(EClass o1, EClass o2) {
				return o1.getName().compareTo(o2.getName());
			}
			
		});
		return result;
	}
	
	/**
	 * Sort the given list of structural features based on name.
	 * 
	 * @param allClasses
	 * @return
	 */
	private List<EStructuralFeature> sortedFeatures(Collection<EStructuralFeature> set) {
		List<EStructuralFeature> result = new ArrayList<EStructuralFeature>();
		result.addAll(set);
		Collections.sort(result, new Comparator<EStructuralFeature>() {

			@Override
			public int compare(EStructuralFeature o1, EStructuralFeature o2) {
				return o1.getName().compareTo(o2.getName());
			}
			
		});
		return result;
	}

	/**
	 * Get all classes in this package and contained sub-packages.
	 * 
	 * @param einstance
	 * @return
	 */
	private Set<EClass> getAllClasses(EPackage p) {
		Set<EClass> result = new HashSet<EClass>();
		for (EPackage pkg : p.getESubpackages()) {
			result.addAll(getAllClasses(pkg));
		}
		
		for (EClassifier cls : p.getEClassifiers()) {
			if (cls instanceof EClass) {
				result.add((EClass) cls);
			}
		}

		return result;
	}
	
	
}
