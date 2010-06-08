/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * Executing an external operation through an OperationCallNode.
 * 
 * @example OperationCallNode
 * 		Using an {@model OperationCallNode} to virtually call another
 * 		{@model Operation} located anywhere within the {@model InternetApplication}.
 * 
 * @author jmwright
 *
 */
public class OperationCallNode extends CodegenTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(OperationCallNode.class);
	}
	
	/**
	 * Check the initial state of the application.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		beginAtSitemapThenPage("Home");
		
		assertNoProblem();
		
		{
			String field = getLabelIDForText("target field");
			assertLabeledFieldEquals(field, "");
		}
	}

	/**
	 * We can execute an operation call. 
	 * 
	 * @throws Exception
	 */
	public void testOperationCall() throws Exception {
		beginAtSitemapThenPage("Home");
		
		String field = getLabelIDForText("target field");
		assertLabeledFieldEquals(field, "");
		
		// click the button
		assertButtonPresentWithText("do operation call");
		clickButtonWithText("do operation call");
		assertNoProblem();
		
		// the value should have changed
		assertLabeledFieldEquals(field, "new value");
	}
	
	
}
