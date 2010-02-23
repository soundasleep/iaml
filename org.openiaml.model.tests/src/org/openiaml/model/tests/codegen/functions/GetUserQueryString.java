/**
 * 
 */
package org.openiaml.model.tests.codegen.functions;

import org.openiaml.model.model.users.Role;
import org.openiaml.model.model.users.UserStore;

/**
 * 
 * @author jmwright
 *
 */
public class GetUserQueryString extends DroolsHelperFunctionsTestCase {

	@Override
	protected Class<? extends DroolsHelperFunctionsTestCase> getTestClass() {
		return GetUserQueryString.class;
	}

	public void testGetUserQueryString() throws Exception {
		
		UserStore store = assertHasUserStore(root, "User Store");
		Role a = assertHasRole(store, "A");
		Role b = assertHasRole(store, "B");
		Role c = assertHasRole(store, "C");
		Role d = assertHasRole(store, "D");
		
		// reverse order from extends
		assertEquals("a = :a", getHelper().getUserQueryString(a));
		assertEquals("b = :b and a = :a", getHelper().getUserQueryString(b));
		assertEquals("b = :b and a = :a", getHelper().getUserQueryString(c));
		assertEquals("d = :d and b = :b and a = :a", getHelper().getUserQueryString(d));
		
	}
	
}
