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

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(FailingOperation.class);
	}
	
	/**
	 * @implementation ExecutionEdge,CancelNode An {@model ExecutionEdge operation flow} that goes to a {@model CancelNode failure node} will cause the operation to fail.
	 * @throws Exception
	 */
	public void testFailingOperation() throws Exception {
		try {
			beginAtSitemapThenPage("page");
			fail("We should not be able to load page 'page'");
		} catch (FailingHttpStatusCodeException e) {
			// we should instantly have an exception occur
			// expected
			checkExceptionContains(e, "an expected failure message");
			
			assertTextPresent("an expected failure message");
			assertProblem();
		}
	}
	
}
