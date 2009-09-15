/**
 * 
 */
package org.openiaml.model.tests.model;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * Issue 96.
 * 
 * Some model elements can contain the same type in different structures; for
 * example, some model elements may be able to store a DomainAttribute as
 * either part of the 'attributes' or 'children' containment feature.
 * 
 * This test case checks to see that this is not possible.
 * 
 * @author jmwright
 *
 */
public class ContainmentTestCase extends TestCase {
	
	private Map<EClass,EFactory> cachedGetAllClasses = null;
	
	/**
	 * Get all of the classes in this project, linked to their
	 * factories.
	 * 
	 * Does not return abstract classes;
	 * 
	 * @return
	 */
	public Map<EClass,EFactory> getAllClasses() {
		if (cachedGetAllClasses == null) {
			cachedGetAllClasses = new HashMap<EClass,EFactory>();

			// for every element in every package,
			Map<EPackage,EFactory> factories = ModelTestCase.getFactoryMap();
			for (EPackage pkg : factories.keySet()) {
				// for every class in this package
				for (EClassifier classifier : pkg.getEClassifiers()) {
					if (classifier instanceof EClass) {
						EClass cls = (EClass) classifier;
						
						// that isn't abstract
						if (!cls.isAbstract()) {
							// put this into the list
							cachedGetAllClasses.put(cls, factories.get(pkg));
						}
					}
				}
			}
		}
		
		return cachedGetAllClasses;
	}
	
	/**
	 * Iterate over all elements twice, checking to see they
	 * can only belong to one containment feature.
	 */
	public void testContainment() {
		String result = "";
		
		for (EClass test : getAllClasses().keySet()) {
			// for all containment features
			for (EClass c : getAllClasses().keySet()) {
				// see that all classes only belong to one containment
				Map<EClass,EReference> containmentMap = new HashMap<EClass,EReference>();
				for (EReference containment : test.getEAllContainments()) {
					// see that all classes only belong to one containment
					if (containment.getEType() instanceof EClass) {
						EClass containmentType = (EClass) containment.getEType();
						if (containmentType.isSuperTypeOf(c)) {
							// it can contain class 'c'
							if (containmentMap.containsKey(c)) {
								// it can be contained in something else!
								String message = "Class '" + c.getName() 
									+ "' can be contained twice in '" 
									+ test.getName() + "': '" 
									+ containment.getName() + "' and '" 
									+ containmentMap.get(c).getName() + "'";
								result += message + "\n";
								System.err.println(message);
							}
							containmentMap.put(c, containment);
						}
					}
				}
			}
		}
		
		if (!result.isEmpty()) {
			throw new RuntimeException(result.trim());
		}
		
	}
	
	/**
	 * {@link #getAllClasses()} should not be empty.
	 */
	public void testClassesArentEmpty() {
		assertFalse(getAllClasses().keySet().isEmpty());
	}
	
}
