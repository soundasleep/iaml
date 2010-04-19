/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_1;

import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 *
 * @author jmwright
 */
public class ViewDatabaseTypes extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(ViewDatabaseTypes.class);
	}
	
	/**
	 * Check the types of the initial attributes, i.e. not generated.
	 * 
	 * @throws Exception
	 */
	public void testDatabaseTypes() throws Exception {
		DomainStore store = assertHasDomainStore(root, "Database");
		DomainObject obj = assertHasDomainObject(store, "Container");
		
		DomainAttribute aString = assertHasDomainAttribute(obj, "string");
		DomainAttribute aInteger = assertHasDomainAttribute(obj, "integer");
		DomainAttribute aDate = assertHasDomainAttribute(obj, "date");
		DomainAttribute aEmail = assertHasDomainAttribute(obj, "email");
		DomainAttribute aAddress = assertHasDomainAttribute(obj, "address");
		
		assertEquals(aString.getType(), BuiltinDataTypes.getTypeString());
		assertEquals(aInteger.getType(), BuiltinDataTypes.getTypeInteger());
		assertEquals(aDate.getType(), BuiltinDataTypes.getTypeDateTime());
		assertEquals(aEmail.getType(), BuiltinDataTypes.getTypeEmail());
		assertEquals(aAddress.getType(), BuiltinDataTypes.getTypeAddress());
		
		assertNotGenerated(store);
		assertNotGenerated(obj);
		assertNotGenerated(aString);
		assertNotGenerated(aInteger);
		assertNotGenerated(aDate);
		assertNotGenerated(aEmail);
		assertNotGenerated(aAddress);
	}
	
	/**
	 * The AttributeInstances should have the same data types as
	 * the Attribute definition.
	 * 
	 * @throws Exception
	 */
	public void testAttributeInstancesHaveCorrectTypes() throws Exception {
		DomainStore store = assertHasDomainStore(root, "Database");
		DomainObject obj = assertHasDomainObject(store, "Container");
		
		DomainAttribute aString = assertHasDomainAttribute(obj, "string");
		DomainAttribute aInteger = assertHasDomainAttribute(obj, "integer");
		DomainAttribute aDate = assertHasDomainAttribute(obj, "date");
		DomainAttribute aEmail = assertHasDomainAttribute(obj, "email");
		DomainAttribute aAddress = assertHasDomainAttribute(obj, "address");
		
		Frame view = assertHasFrame(root, "View");
		DomainObjectInstance instance = assertHasDomainObjectInstance(view, "Viewed");
		
		DomainAttributeInstance iString = assertHasDomainAttributeInstance(instance, "string");
		DomainAttributeInstance iInteger = assertHasDomainAttributeInstance(instance, "integer");
		DomainAttributeInstance iDate = assertHasDomainAttributeInstance(instance, "date");
		DomainAttributeInstance iEmail = assertHasDomainAttributeInstance(instance, "email");
		DomainAttributeInstance iAddress = assertHasDomainAttributeInstance(instance, "address");
		
		// all generated
		assertGenerated(iString);
		assertGenerated(iInteger);
		assertGenerated(iDate);
		assertGenerated(iEmail);
		assertGenerated(iAddress);
		
		// equal types
		assertEquals(aString.getType(), iString.getType());
		assertEquals(aInteger.getType(), iInteger.getType());
		assertEquals(aDate.getType(), iDate.getType());
		assertEquals(aEmail.getType(), iEmail.getType());
		assertEquals(aAddress.getType(), iAddress.getType());
		
		// test the field values of the attribute instances, as well
		{
			Property value = assertHasFieldValue(iString);
			assertEquals(value.getType(), iString.getType());
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(iInteger);
			assertEquals(value.getType(), iInteger.getType());
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(iDate);
			assertEquals(value.getType(), iDate.getType());
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(iEmail);
			assertEquals(value.getType(), iEmail.getType());
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(iAddress);
			assertEquals(value.getType(), iAddress.getType());
			assertGenerated(value);
		}
		
	}
	
	/**
	 * The fieldValues for each Label generated should have the
	 * same data types as the AttributeInstance.
	 * 
	 * @throws Exception
	 */
	public void testFormHasCorrectTypes() throws Exception {
		Frame view = assertHasFrame(root, "View");
		DomainObjectInstance instance = assertHasDomainObjectInstance(view, "Viewed");
		
		DomainAttributeInstance iString = assertHasDomainAttributeInstance(instance, "string");
		DomainAttributeInstance iInteger = assertHasDomainAttributeInstance(instance, "integer");
		DomainAttributeInstance iDate = assertHasDomainAttributeInstance(instance, "date");
		DomainAttributeInstance iEmail = assertHasDomainAttributeInstance(instance, "email");
		DomainAttributeInstance iAddress = assertHasDomainAttributeInstance(instance, "address");
		
		InputForm form = assertHasInputForm(view, "View");
		
		Label tString = assertHasLabel(form, "string");
		Label tInteger = assertHasLabel(form, "integer");
		Label tDate = assertHasLabel(form, "date");
		Label tEmail = assertHasLabel(form, "email");
		Label tAddress = assertHasLabel(form, "address");
		
		// all generated
		assertGenerated(iString);
		assertGenerated(iInteger);
		assertGenerated(iDate);
		assertGenerated(iEmail);
		assertGenerated(iAddress);
		
		// equal types
		assertEquals(tString.getType(), iString.getType());
		assertEquals(tInteger.getType(), iInteger.getType());
		assertEquals(tDate.getType(), iDate.getType());
		assertEquals(tEmail.getType(), iEmail.getType());
		assertEquals(tAddress.getType(), iAddress.getType());
		
		// test the field values of the attribute instances, as well
		{
			Property value = assertHasFieldValue(tString);
			assertEquals(value.getType(), iString.getType());
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(tInteger);
			assertEquals(value.getType(), iInteger.getType());
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(tDate);
			assertEquals(value.getType(), iDate.getType());
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(tEmail);
			assertEquals(value.getType(), iEmail.getType());
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(tAddress);
			assertEquals(value.getType(), iAddress.getType());
			assertGenerated(value);
		}
		
	}

}
