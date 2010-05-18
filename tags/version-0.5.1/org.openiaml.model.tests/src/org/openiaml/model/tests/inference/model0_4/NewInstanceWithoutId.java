/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import junit.framework.AssertionFailedError;

import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Generated primary keys should have the 'integer' data type.
 *
 * @author jmwright
 *
 */
public class NewInstanceWithoutId extends ValidInferenceTestCase {

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return NewInstanceWithoutId.class;
	}

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(NewInstanceWithoutId.class);
		
		DomainSchema schema = assertHasDomainSchema(root, "domain object");
		assertNotGenerated(schema);
		DomainAttribute name = assertHasDomainAttribute(schema, "name");
		assertNotGenerated(name);
		assertFalse(name.isPrimaryKey());
		
		assertHasNoDomainAttribute(schema, "generated primary key");
		assertHasNoDomainAttribute(schema, "id");

	}

	/**
	 * Test that an ID is generated, and it is of type integer.
	 *
	 * @throws Exception
	 */
	public void testGeneratedPKIsInteger() throws Exception {
		root = loadAndInfer(NewInstanceWithoutId.class);
		
		DomainSchema schema = assertHasDomainSchema(root, "domain object");
		
		DomainAttribute pk = assertHasDomainAttribute(schema, "generated primary key");
		assertTrue(pk.isPrimaryKey());
		assertEquals(BuiltinDataTypes.getTypeInteger().getURI(), pk.getType().getURI());
		
		// check that assertHasNoDomainAttribute() works
		try {
			assertHasNoDomainAttribute(schema, "generated primary key");
			throw new RuntimeException("assertHasNoDomainAttribute() did not throw an assertion");
		} catch (AssertionFailedError e) {
			// expected
		}

	}


}
