/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_1;

import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.domain.DomainAttribute;
import org.openiaml.model.model.domain.DomainAttributeInstance;
import org.openiaml.model.model.domain.DomainInstance;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
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
		DomainSchema obj = assertHasDomainSchema(root, "Container");
		
		DomainAttribute aString = assertHasDomainAttribute(obj, "string");
		DomainAttribute aInteger = assertHasDomainAttribute(obj, "integer");
		DomainAttribute aDate = assertHasDomainAttribute(obj, "date");
		DomainAttribute aEmail = assertHasDomainAttribute(obj, "email");
		DomainAttribute aAddress = assertHasDomainAttribute(obj, "address");
		
		assertEqualType(aString.getEType(), BuiltinDataTypes.getTypeString());
		assertEqualType(aInteger.getEType(), BuiltinDataTypes.getTypeInteger());
		assertEqualType(aDate.getEType(), BuiltinDataTypes.getTypeDateTime());
		assertEqualType(aEmail.getEType(), BuiltinDataTypes.getTypeEmail());
		assertEqualType(aAddress.getEType(), BuiltinDataTypes.getTypeAddress());
		
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
		DomainSchema obj = assertHasDomainSchema(root, "Container");
		
		DomainAttribute aString = assertHasDomainAttribute(obj, "string");
		DomainAttribute aInteger = assertHasDomainAttribute(obj, "integer");
		DomainAttribute aDate = assertHasDomainAttribute(obj, "date");
		DomainAttribute aEmail = assertHasDomainAttribute(obj, "email");
		DomainAttribute aAddress = assertHasDomainAttribute(obj, "address");
		
		Frame view = assertHasFrame(root, "View");
		DomainIterator iterator = assertHasDomainIterator(view, "Viewed");
		DomainInstance instance = iterator.getCurrentInstance();
		assertGenerated(instance);
		
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
		assertEqualType(aString, iString);
		assertEqualType(aInteger, iInteger);
		assertEqualType(aDate, iDate);
		assertEqualType(aEmail, iEmail);
		assertEqualType(aAddress, iAddress);
		
		// test the field values of the attribute instances, as well
		{
			Property value = assertHasFieldValue(iString);
			assertEqualType(value, iString);
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(iInteger);
			assertEqualType(value, iInteger);
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(iDate);
			assertEqualType(value, iDate);
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(iEmail);
			assertEqualType(value, iEmail);
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(iAddress);
			assertEqualType(value, iAddress);
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
		DomainIterator iterator = assertHasDomainIterator(view, "Viewed");
		DomainInstance instance = iterator.getCurrentInstance();
		assertGenerated(instance);
		
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
		assertEqualType(tString, iString);
		assertEqualType(tInteger, iInteger);
		assertEqualType(tDate, iDate);
		assertEqualType(tEmail, iEmail);
		assertEqualType(tAddress, iAddress);
		
		// test the field values of the attribute instances, as well
		{
			Property value = assertHasFieldValue(tString);
			assertEqualType(value, tString);
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(tInteger);
			assertEqualType(value, tInteger);
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(tDate);
			assertEqualType(value, tDate);
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(tEmail);
			assertEqualType(value, tEmail);
			assertGenerated(value);
		}
		{
			Property value = assertHasFieldValue(tAddress);
			assertEqualType(value, tAddress);
			assertGenerated(value);
		}
		
		
	}

}
