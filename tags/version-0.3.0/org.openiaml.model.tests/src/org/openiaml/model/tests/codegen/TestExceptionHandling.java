/**
 * 
 */
package org.openiaml.model.tests.codegen;

import org.openiaml.model.tests.CodegenTestCase;

/** 
 * If an exception is thrown in the code generation templates,
 * we should catch it.
 * 
 * @author jmwright
 *
 */
public class TestExceptionHandling extends CodegenTestCase {

	/**
	 * Make sure that when an exception is thrown, operation halts. 
	 */
	public void testExceptionThrowing() throws Exception {
		boolean passed = false;
		try {
			root = loadAndCodegen(ROOT + "codegen/ThrowException.iaml");
		} catch (TransformationException e) {
			// pass
			passed = true;
		}
		assertTrue("An exception should have been thrown.", passed);
	}

	/**
	 * When an exception is thrown, we should be able to read it.
	 */
	public void testExceptionInvestgation() throws Exception {
		TransformationException ex = null;
		try {
			root = loadAndCodegen(ROOT + "codegen/ThrowException.iaml");
		} catch (TransformationException e) {
			// pass
			ex = e;
		}
		assertNotNull("An exception should have been thrown.", ex);

		assertNotNull(ex.getCause());
		assertEquals("We should be able to catch this exception.", ex.getCause().getMessage());
		
	}

}
