/**
 * 
 */
package org.openiaml.model.tests.codegen.functions;

import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.visual.Frame;

/**
 * 
 * @author jmwright
 *
 */
public class LastChainedOperation extends DroolsHelperFunctionsTestCase {

	@Override
	protected Class<? extends DroolsHelperFunctionsTestCase> getTestClass() {
		return LastChainedOperation.class;
	}

	public void testLastChainedOperation() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		PrimitiveOperation o1 = assertHasPrimitiveOperation(home, "o1");
		PrimitiveOperation o2 = assertHasPrimitiveOperation(home, "o2");
		PrimitiveOperation o3 = assertHasPrimitiveOperation(home, "o3");
		PrimitiveOperation o4 = assertHasPrimitiveOperation(home, "o4");
		
		assertEquals(o3, getHelper().lastChainedOperation(o1));
		assertEquals(o3, getHelper().lastChainedOperation(o2));
		assertEquals(o3, getHelper().lastChainedOperation(o3));
		assertEquals(o4, getHelper().lastChainedOperation(o4));
		
	}
	
}
