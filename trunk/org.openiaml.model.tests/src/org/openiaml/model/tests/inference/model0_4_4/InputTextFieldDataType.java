/**
 *
 */
package org.openiaml.model.tests.inference.model0_4_4;

import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.drools.DroolsHelperFunctions;
import org.openiaml.model.model.EXSDDataType;
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
		assertEquals(((EXSDDataType) integer.getType()).getDefinition().getURI(), BuiltinDataTypes.TYPE_INTEGER);
		
		InputTextField str = assertHasInputTextField(home, "String");
		assertNotGenerated(str);
		assertHasNoFieldValue(str);
		assertEquals(((EXSDDataType) str.getType()).getDefinition().getURI(), BuiltinDataTypes.TYPE_STRING);
		
		InputTextField dt = assertHasInputTextField(home, "Date/Time");
		assertNotGenerated(dt);
		assertHasNoFieldValue(dt);
		assertEquals(((EXSDDataType) dt.getType()).getDefinition().getURI(), BuiltinDataTypes.TYPE_DATETIME);
		
		InputTextField email = assertHasInputTextField(home, "Email");
		assertNotGenerated(email);
		assertHasNoFieldValue(email);
		assertEquals(((EXSDDataType) email.getType()).getDefinition().getURI(), BuiltinDataTypes.TYPE_EMAIL);
		
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
			EXSDDataType exsd = (EXSDDataType) fieldValue.getType();
			assertEquals(exsd.getDefinition().getURI(), BuiltinDataTypes.TYPE_INTEGER);
			assertTrue(DroolsHelperFunctions.equalDataTypes(
					exsd.getDefinition(), 
					BuiltinDataTypes.getTypeInteger()));
		}
		
		InputTextField str = assertHasInputTextField(home, "String");
		{
			Property fieldValue = assertHasFieldValue(str);
			EXSDDataType exsd = (EXSDDataType) fieldValue.getType();
			assertEquals(exsd.getDefinition().getURI(), BuiltinDataTypes.TYPE_STRING);
			assertTrue(DroolsHelperFunctions.equalDataTypes(
					exsd.getDefinition(), 
					BuiltinDataTypes.getTypeString()));
		}
		
		InputTextField dt = assertHasInputTextField(home, "Date/Time");
		{
			Property fieldValue = assertHasFieldValue(dt);
			EXSDDataType exsd = (EXSDDataType) fieldValue.getType();
			assertEquals(exsd.getDefinition().getURI(), BuiltinDataTypes.TYPE_DATETIME);
			assertTrue(DroolsHelperFunctions.equalDataTypes(
					exsd.getDefinition(), 
					BuiltinDataTypes.getTypeDateTime()));
		}
		
		InputTextField email = assertHasInputTextField(home, "Email");
		{
			Property fieldValue = assertHasFieldValue(email);
			EXSDDataType exsd = (EXSDDataType) fieldValue.getType();
			assertEquals(exsd.getDefinition().getURI(), BuiltinDataTypes.TYPE_EMAIL);
			assertTrue(DroolsHelperFunctions.equalDataTypes(
					exsd.getDefinition(), 
					BuiltinDataTypes.getTypeEmail()));
		}
		
	}


}
