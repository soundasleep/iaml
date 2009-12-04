/**
 * 
 */
package org.openiaml.verification.crocopat.tests;

import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.tests.ModelInferenceTestCase;
import org.openiaml.verification.crocopat.VerificationEngine;
import org.openiaml.verification.crocopat.VerificationViolation;

/**
 * @author jmwright
 *
 */
public class CrocopatTestCase extends TestCase {

	public void test1() throws Exception {
		// load model
		EObject model = loadModelDirectly("src/org/openiaml/verification/crocopat/tests/BigInfiniteLoop-10.iaml");
		
		VerificationEngine engine = new VerificationEngine();
		IStatus result = engine.verify(model, new NullProgressMonitor());
		ModelInferenceTestCase.assertStatusIsNotOK(result);
	
		// there should be violations
		List<VerificationViolation> violations = engine.getViolations();
		assertNotNull("Violations should not be null", violations);
		
		// there should be ten
		assertEquals("there should be 10 violations", 10, violations.size());
		
		for (VerificationViolation violation : violations) {
			System.out.println(violation);
			
			// none of the violations should have empty objects
			assertEquals(1, violation.getObjects().size());
			
			// they should all be of class Page
			assertTrue("EObject should be Page but was: " + violation.getObjects().get(0).getClass().getSimpleName(),
					violation.getObjects().get(0) instanceof Page);			
		}
		
	}

	/**
	 * Load a model file directly.
	 * Assumes that it will only contain one element (and tests this with JUnit).
	 */
	protected EObject loadModelDirectly(String filename) {
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(filename);
		Resource resource = resourceSet.getResource(uri, true);
		assertNotNull(resource);
		assertEquals("there should only be one contents in the model file", 1, resource.getContents().size());
		return resource.getContents().get(0);
	}
		
}
