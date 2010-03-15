/**
 *
 */
package org.openiaml.model.tests.inference.model0_4_4;

import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Inference of the ExitGate model completion rules.
 *
 * @author jmwright
 *
 */
public class InputTextFieldDataType extends ValidInferenceTestCase {

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return InputTextFieldDataType.class;
	}
	
	/**
	 * Test the initial model.
	 */
	public void testInitial() throws Exception {	
		
		root = loadDirectly(InputTextFieldDataType.class);
		
		Frame home = assertHasFrame(root, "Home");
		assertNotGenerated(root);
		
		InputTextField def = assertHasInputTextField(home, "Default");
		assertNotGenerated(def);
		assertHasNoFieldValue(def);
		assertNull(def.getType());
		
		InputTextField integer = assertHasInputTextField(home, "Integer");
		assertNotGenerated(integer);
		assertHasNoFieldValue(integer);
		assertEquals(integer.getType().getURI(), BuiltinDataTypes.TYPE_INTEGER);
		
		InputTextField str = assertHasInputTextField(home, "String");
		assertNotGenerated(str);
		assertHasNoFieldValue(str);
		assertEquals(str.getType().getURI(), BuiltinDataTypes.TYPE_STRING);
		
		InputTextField dt = assertHasInputTextField(home, "Date/Time");
		assertNotGenerated(dt);
		assertHasNoFieldValue(dt);
		assertEquals(dt.getType().getURI(), BuiltinDataTypes.TYPE_DATETIME);
		
		InputTextField email = assertHasInputTextField(home, "Email");
		assertNotGenerated(email);
		assertHasNoFieldValue(email);
		assertEquals(email.getType().getURI(), BuiltinDataTypes.TYPE_EMAIL);
		
	}

	/**
	 * Inference will automatically create property types identical
	 * to the types of the parent text fields.
	 */
	public void testInferredPropertyTypes() throws Exception {
		
		root = loadAndInfer(InputTextFieldDataType.class);
		
		Frame home = assertHasFrame(root, "Home");

		InputTextField def = assertHasInputTextField(home, "Default");
		{
			Property fieldValue = assertHasFieldValue(def);
			assertNull(fieldValue.getType());
		}

		InputTextField integer = assertHasInputTextField(home, "Integer");
		{
			Property fieldValue = assertHasFieldValue(integer);
			assertEquals(fieldValue.getType().getURI(), BuiltinDataTypes.TYPE_INTEGER);
		}
		
		InputTextField str = assertHasInputTextField(home, "String");
		{
			Property fieldValue = assertHasFieldValue(str);
			assertEquals(fieldValue.getType().getURI(), BuiltinDataTypes.TYPE_STRING);
		}
		
		InputTextField dt = assertHasInputTextField(home, "Date/Time");
		{
			Property fieldValue = assertHasFieldValue(dt);
			assertEquals(fieldValue.getType().getURI(), BuiltinDataTypes.TYPE_DATETIME);
		}
		
		InputTextField email = assertHasInputTextField(home, "Email");
		{
			Property fieldValue = assertHasFieldValue(email);
			assertEquals(fieldValue.getType().getURI(), BuiltinDataTypes.TYPE_EMAIL);
		}
		
	}


}
