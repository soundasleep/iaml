/**
 * 
 */
package org.openiaml.model.tests.codegen.functions;

import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;

/**
 * 
 * @author jmwright
 *
 */
public class HasDomainAttribute extends DroolsHelperFunctionsTestCase {

	@Override
	protected Class<? extends DroolsHelperFunctionsTestCase> getTestClass() {
		return HasDomainAttribute.class;
	}

	public void testHasDomainAttribute() throws Exception {
		
		DomainObject d1 = assertHasDomainObject(root, "Not Empty");
		DomainObject d2 = assertHasDomainObject(root, "Empty");
		
		assertTrue(getHelper().hasDomainAttribute(d1));
		assertFalse(getHelper().hasDomainAttribute(d2));
		
	}
	
	public void testNotPrimaryKey() throws Exception {
		
		DomainObject d1 = assertHasDomainObject(root, "Not Empty");
		DomainAttribute a1 = assertHasDomainAttribute(d1, "attr1");
		DomainObject d3 = assertHasDomainObject(root, "Generated Primary Key");
		DomainAttribute a2 = assertHasDomainAttribute(d3, "generated primary key");
		
		assertTrue(getHelper().notPrimaryKey(a1));
		assertFalse(getHelper().notPrimaryKey(a2));
		
	}
	
}
