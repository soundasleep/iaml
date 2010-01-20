/**
 * 
 */
package org.openiaml.verification.crocopat.tests;

import java.util.List;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openiaml.verification.nusmv.NuSMVViolation;
import org.openiaml.verification.nusmv.VerificationEngine;
import org.openiaml.verification.nusmv.VerificationException;

/**
 * @author jmwright
 *
 */
public class NuSMVTestCase extends TestCase {
	
	// prepare IAML package (so it may be loaded by EMF)
	
	/**
	 * Test the given model file for NuSMV validation.
	 * 
	 * @param modelFile the model file to load
	 * @param violations the violations to expect
	 * @return a list of the violations found
	 * @throws VerificationException 
	 */
	protected List<NuSMVViolation> assertValid(String modelFile, int violationsSize) throws VerificationException {
		EObject model = loadModelDirectly(modelFile);
		assertNotNull(model);
		
		VerificationEngine engine = new VerificationEngine(true);
		IStatus status = engine.verify(model, new NullProgressMonitor());
		
		List<NuSMVViolation> violations = engine.getViolations();
		try {
			if (violationsSize == 0) {
				assertTrue("status was not OK: " + status, status.isOK());
			} else {
				assertFalse("status was OK: " + status, status.isOK());
			}
			assertNotNull(violations);
			assertEquals("Unexpected violations: " + violations, violationsSize, violations.size());
		} catch (AssertionFailedError e) {
			printViolations(violations);
			printLog(engine);
			throw e;
		}
		
		return violations;
	}

	public void testNormalOperationLoop() throws Exception {		
		assertValid("models/NormalOperationLoop.iaml", 0);
	}

	public void testInfiniteOperationLoop() throws Exception {
		assertValid("models/InfiniteOperationLoop.iaml", 1);

		// TODO: check that the violation is correct
	}

	public void testDecisionOperationLoop() throws Exception {
		assertValid("models/DecisionOperationLoop.iaml", 0);

		// TODO: check that the violation is correct
	}
	
	public void testDecisionOperationLoopInfinite() throws Exception {
		assertValid("models/DecisionOperationLoopInfinite.iaml", 1);

		// TODO: check that the violation is correct
	}

	/**
	 * Print out the log of the given engine to stdout.
	 * 
	 * @param engine
	 */
	private void printLog(VerificationEngine engine) {
		for (String s : engine.getLog()) {
			System.out.println(s);
		}
	}

	/**
	 * Print out all of the given violations to stderr.
	 * 
	 * @param violations
	 */
	private void printViolations(List<NuSMVViolation> violations) {
		for (NuSMVViolation v : violations) {
			System.err.println("*** Violation:");
			System.err.println(v);
		}
	}

	/**
	 * Load a model file directly.
	 * Assumes that it will only contain one element (and tests this with JUnit).
	 * 
	 * <p>If "A registered resource factory is needed" is thrown, add the
	 * <code>org.eclipse.emf.ecore.xmi</code> plugin as a dependency.</p>
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
