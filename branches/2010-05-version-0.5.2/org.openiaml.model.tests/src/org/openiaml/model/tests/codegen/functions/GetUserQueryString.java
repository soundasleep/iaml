/**
 * 
 */
package org.openiaml.model.tests.codegen.functions;

import org.openiaml.model.model.users.Role;

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
		
		Role a = assertHasRole(root, "A");
		Role b = assertHasRole(root, "B");
		Role c = assertHasRole(root, "C");
		Role d = assertHasRole(root, "D");
		
		// reverse order from extends
		assertEquals("a = :a", getHelper().getUserQueryString(a));
		assertEquals("b = :b and a = :a", getHelper().getUserQueryString(b));
		assertEquals("b = :b and a = :a", getHelper().getUserQueryString(c));
		assertEquals("d = :d and b = :b and a = :a", getHelper().getUserQueryString(d));
		
	}
	
}
