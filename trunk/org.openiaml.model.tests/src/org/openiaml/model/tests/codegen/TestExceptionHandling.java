/**
 * 
 */
package org.openiaml.model.tests.codegen;

import junit.framework.AssertionFailedError;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/** 
 * If an exception is thrown in the code generation templates,
 * we should catch it.
 * 
 * @author jmwright
 *
 */
public class TestExceptionHandling extends CodegenTestCase {
	
	protected InternetApplication root;
	
	@Override
	protected void setUp() throws Exception {
		// do nothing
	}

	/**
	 * The site should have a login page.
	 * @throws Exception 
	 */
	public void testExceptionThrowing() throws Exception {
		// method is done in setUp();

		boolean passed = false;
		try {
			root = loadAndCodegen(ROOT + "codegen/ThrowException.iaml");
		} catch (AssertionFailedError e) {
			// pass
			passed = true;
		}
		assertTrue("An exception should have been thrown.", passed);
	}


}
