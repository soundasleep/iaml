/**
 *
 */
package org.openiaml.model.tests.inference.model0_5;

import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Inference of the ExitGate model completion rules.
 *
 * @author jmwright
 *
 */
public class InputTextFieldDataTypeOverridden extends ValidInferenceTestCase {

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return InputTextFieldDataTypeOverridden.class;
	}
	
	/**
	 * Test the initial model.
	 */
	public void testInitial() throws Exception {	
		
		root = loadDirectly(InputTextFieldDataTypeOverridden.class);
		
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
	 * Since each text field is overridden, no contained
	 * fieldValues will be created.
	 */
	public void testInferredPropertyTypes() throws Exception {
		
		root = loadAndInfer(InputTextFieldDataTypeOverridden.class);
		
		Frame home = assertHasFrame(root, "Home");

		InputTextField def = assertHasInputTextField(home, "Default");
		assertHasNoFieldValue(def);

		InputTextField integer = assertHasInputTextField(home, "Integer");
		assertHasNoFieldValue(integer);
		
		InputTextField str = assertHasInputTextField(home, "String");
		assertHasNoFieldValue(str);
		
		InputTextField dt = assertHasInputTextField(home, "Date/Time");
		assertHasNoFieldValue(dt);

		InputTextField email = assertHasInputTextField(home, "Email");
		assertHasNoFieldValue(email);

		
	}


}
