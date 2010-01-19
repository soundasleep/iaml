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

/**
 * @author jmwright
 *
 */
public class NuSMVTestCase extends TestCase {
	
	// prepare IAML package (so it may be loaded by EMF)

	public void testNormalOperationLoop() throws Exception {
		
		EObject model = loadModelDirectly("models/NormalOperationLoop.iaml");
		assertNotNull(model);
		
		VerificationEngine engine = new VerificationEngine(true);
		IStatus status = engine.verify(model, new NullProgressMonitor());
		
		List<NuSMVViolation> violations = engine.getViolations();
		try {
			assertTrue("status was not OK: " + status, status.isOK());
			assertNotNull(violations);
			assertEquals("Unexpected violations: " + violations, 0, violations.size());
		} catch (AssertionFailedError e) {
			printViolations(violations);
			printLog(engine);
			throw e;
		}
	}

	public void testInfiniteOperationLoop() throws Exception {
		
		EObject model = loadModelDirectly("models/InfiniteOperationLoop.iaml");
		assertNotNull(model);
		
		VerificationEngine engine = new VerificationEngine(true);
		IStatus status = engine.verify(model, new NullProgressMonitor());
		
		List<NuSMVViolation> violations = engine.getViolations();
		try {
			assertFalse("status was OK: " + status, status.isOK());
			assertNotNull(violations);
			assertEquals("Unexpected violations: " + violations, 1, violations.size());
		} catch (AssertionFailedError e) {
			printViolations(violations);
			printLog(engine);
			throw e;
		}
		
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
