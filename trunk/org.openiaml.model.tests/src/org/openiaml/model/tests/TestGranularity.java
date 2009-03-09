/**
 * 
 */
package org.openiaml.model.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.model.model.ModelPackage;


/**
 * @author jmwright
 *
 */
public class TestGranularity extends XmlTestCase {

	public static final String ROOT = "../org.openiaml.model/model/";

	List<EClass> loaded = new ArrayList<EClass>();
	Map<EClass, Integer> granularity = new HashMap<EClass, Integer>();

	private EReference lastReference;
	
	protected void loadAllPackages(EPackage p) {
		for (EObject o : p.eContents()) {
			if (o instanceof EPackage) {
				loadAllPackages((EPackage) o);
			} else if (o instanceof EClass) {
				loaded.add((EClass) o);
			} else if (o instanceof EAnnotation || o instanceof EEnum || o instanceof EDataType) {
				// ignore
			} else {
				throw new RuntimeException("Cannot load package element of type " + o.getClass() + ": " + o);
			}
		}
	}
	
	public void testGranularity() throws Exception {
		loadAllPackages(ModelPackage.eINSTANCE);

		// start with InternetApplication
		granularise("InternetApplication", 0);
		
		// print everything out
		for (EClass c : granularity.keySet()) {
			int g = granularity.get(c);
			System.out.println(c.getName() + ": " + g);
		}
		
	}

	/**
	 * @param name name of class to granularise
	 * @param i
	 */
	private void granularise(String name, int i) {
		for (EClass c : loaded) {
			if (c.getName().equals(name)) {
				// found it
				granularise(c, i);
			}
		}
	}

	/**
	 * @param c
	 * @param i
	 */
	private void granularise(EClass c, int i) {
		List<EClass> stack = new ArrayList<EClass>();
		granularise(c, i, stack);
	}

	/**
	 * @param c
	 * @param i
	 */
	private void granularise(EClass c, int i, List<EClass> stack) {
		// ignore GeneratedElement and GeneratesElements
		if (c.equals( ModelPackage.eINSTANCE.getGeneratedElement() ) ||
				c.equals( ModelPackage.eINSTANCE.getGeneratesElements() )) {
			// skip
			return;
		}

		// bail on infinite loops
		if (!stack.isEmpty() && c.equals( stack.get(stack.size() - 1) )) {
			for (EClass s : stack) {
				System.out.println("> " + s);
			}
			System.out.println("last reference: " + lastReference + " belonging to " + lastReference.getContainerClass());
			throw new RuntimeException("Double loop with class " + c);
		}
		
		if (i > 150) {
			for (EClass s : stack) {
				System.out.println("> " + s);
			}
			throw new RuntimeException("Infinite granularity loop with class " + c);
		}
		
		// if non-abstract, increase granularity
		if (!c.isAbstract() && !c.isInterface()) {
			i++;
		}

		// do we already have it?
		if (granularity.containsKey(c)) {
			// only replace it if the granularity is lower
			if (granularity.get(c) > i) {
				System.out.println("replaced granularity for " + c + " from " + granularity.get(c) + " to " + i);
				granularity.put(c, i);
			}
		} else {
			// put it in
			granularity.put(c, i);
		}
		
		// all super types should be +1
		for (EClass type : c.getEAllSuperTypes()) {
			List<EClass> s2 = new ArrayList<EClass>(stack);
			s2.add(c);
			granularise(type, i + 1, s2);
		}

		// find all subtypes
		/*
		 * possibly not necessary to force?
		 * 
		for (EClass type : loaded) {
			if (!type.equals(c) && type.getEAllSuperTypes().contains(c) ) {
				// a subtype
				List<EClass> s2 = new ArrayList<EClass>(stack);
				s2.add(c);
				granularise(type, i, s2);
			}
		}
		*/
		
		// get all references
		for (EReference r : c.getEAllReferences()) {
			
			// don't do references of multiplicity 1 that have
			// EOpposite set.
			
			// reasoning: this is the 
			// GeneratedElement/GeneratesElements problem.
			
			if (!(r.getUpperBound() == 1 && r.getEOpposite() != null)) {
				
				// don't do self-contained references (e.g. a Session can contain Sessions)
				if (!r.getEReferenceType().equals(c)) {
					
					List<EClass> s2 = new ArrayList<EClass>(stack);
					s2.add(c);
					lastReference = r;
					granularise(r.getEReferenceType(), i + 1, s2);
					
				}
			}
		}
	}

	
}
