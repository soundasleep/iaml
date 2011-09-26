/**
 * 
 */
package org.openiaml.model.tests.codegen.functions;

import org.openiaml.model.model.BuiltinOperation;
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
		BuiltinOperation o1 = assertHasBuiltinOperation(home, "o1");
		BuiltinOperation o2 = assertHasBuiltinOperation(home, "o2");
		BuiltinOperation o3 = assertHasBuiltinOperation(home, "o3");
		BuiltinOperation o4 = assertHasBuiltinOperation(home, "o4");
		
		assertEquals(o3, getHelper().lastChainedOperation(o1));
		assertEquals(o3, getHelper().lastChainedOperation(o2));
		assertEquals(o3, getHelper().lastChainedOperation(o3));
		assertEquals(o4, getHelper().lastChainedOperation(o4));
		
	}
	
}
