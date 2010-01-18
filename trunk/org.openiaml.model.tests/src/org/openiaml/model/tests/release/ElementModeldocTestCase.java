/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.openiaml.model.tests.XmlTestCase;
import org.openiaml.model.tests.model.ModelTestCase;

/**
 * Check that all non-abstract, non-interface classes in the model
 * have corresponding .png files in <code>org.openiaml.model.tests.elements</code>.
 * 
 * @author jmwright
 *
 */
public class ElementModeldocTestCase extends XmlTestCase {
	
	/**
	 * Check that all non-abstract, non-interface classes in the model
	 * have corresponding .png files in <code>org.openiaml.model.tests.elements</code>.
	 */
	public void testElementImages() {
		String TARGET_PROJECT = "../org.openiaml.model.tests.elements/";
		
		List<String> missing = new ArrayList<String>();
		
		Map<EPackage,EFactory> factories = ModelTestCase.getFactoryMap();
		for (EPackage pkg : factories.keySet()) {
			for (EClassifier classifier : pkg.getEClassifiers()) {
				if (classifier instanceof EClass) {
					EClass cls = (EClass) classifier;
					
					// ignore abstract classes
					if (cls.isAbstract())
						continue;
					
					// ignore interface classes
					if (cls.isInterface())
						continue;
					
					// check that a .png file exists
					File f = new File(TARGET_PROJECT + cls.getName() + ".png");
					if (!f.exists()) {
						missing.add(cls.getName());
					}
				}
			}
		}
		
		if (!missing.isEmpty()) {
			for (String s : missing) {
				System.err.println("Missing: " + s);
			}
			assertEquals("Some non-abstract, non-interface model elements do not have a corresponding generated diagram: " + missing, 0, missing.size());
		}
	}
	
}
