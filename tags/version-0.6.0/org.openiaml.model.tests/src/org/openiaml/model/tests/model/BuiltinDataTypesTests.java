/**
 * 
 */
package org.openiaml.model.tests.model;

import junit.framework.TestCase;

import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.openiaml.model.datatypes.BuiltinDataTypes;

/**
 * Make sure that we can load the built-in data types.
 * 
 * @author jmwright
 *
 */
public class BuiltinDataTypesTests extends TestCase {
	
	public void testInteger() {
		String uri = BuiltinDataTypes.TYPE_INTEGER;
		XSDSimpleTypeDefinition loaded = BuiltinDataTypes.getTypeInteger();
		
		assertNotNull(uri);
		assertNotNull(loaded);
		assertEquals(uri, loaded.getURI());
	}

	public void testString() {
		String uri = BuiltinDataTypes.TYPE_STRING;
		XSDSimpleTypeDefinition loaded = BuiltinDataTypes.getTypeString();
		
		assertNotNull(uri);
		assertNotNull(loaded);
		assertEquals(uri, loaded.getURI());
	}

	public void testEmail() {
		String uri = BuiltinDataTypes.TYPE_EMAIL;
		XSDSimpleTypeDefinition loaded = BuiltinDataTypes.getTypeEmail();
		
		assertNotNull(uri);
		assertNotNull(loaded);
		assertEquals(uri, loaded.getURI());
	}

	public void testDateTime() {
		String uri = BuiltinDataTypes.TYPE_DATETIME;
		XSDSimpleTypeDefinition loaded = BuiltinDataTypes.getTypeDateTime();
		
		assertNotNull(uri);
		assertNotNull(loaded);
		assertEquals(uri, loaded.getURI());
	}
	
	/**
	 * Make sure that we can fail.
	 */
	public void testWeCanFail() {
		String uri = BuiltinDataTypes.TYPE_INTEGER;
		XSDSimpleTypeDefinition loaded = BuiltinDataTypes.getTypeString();
		
		assertNotNull(uri);
		assertNotNull(loaded);
		assertFalse(uri.equals(loaded.getURI()));
	}

}
