/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import org.openiaml.model.tests.CodegenTestCase;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

/**
 * Tests a failing operation.
 * 
 * @author jmwright
 *
 */
public class FailingOperation extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(FailingOperation.class);
	}
	
	public void testFailingOperation() throws Exception {
		try {
			beginAtSitemapThenPage("page");
			fail("We should not be able to load page 'page'");
		} catch (FailingHttpStatusCodeException e) {
			// we should instantly have an exception occur
			// expected
			
			assertTextPresent("an expected failure message");
			assertProblem();
		}
	}
	
}
