/**
 *
 */
package org.openiaml.model.tests.inference.model0_5;

import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * {@model Form}s connected together with a {@model SetWire} should have
 * {@model InputTextField}s also duplicated and set together - but since
 * {@model SetWire} is unidirectional, it is only one way.
 * 
 * @author jmwright
 */
public class FormSynchroniseSetWire extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(FormSynchroniseSetWire.class);
	}
	
	/**
	 * Test the initial model.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		
		InputForm form1 = assertHasInputForm(home, "form 1");
		InputForm form2 = assertHasInputForm(home, "form 2");
		InputForm form3 = assertHasInputForm(home, "form 3");
		
		InputTextField field1 = assertHasInputTextField(form1, "field 1");
		InputTextField field2 = assertHasInputTextField(form1, "field 2");
		InputTextField field3 = assertHasInputTextField(form2, "field 3");
		InputTextField field4 = assertHasInputTextField(form2, "field 4");
		InputTextField field5 = assertHasInputTextField(form3, "field 5");
		InputTextField field6 = assertHasInputTextField(form3, "field 6");
		
		assertNotGenerated(assertHasSetWire(root, form1, form2));
		assertNotGenerated(assertHasSetWire(root, form2, form3));
		
		// not a circle
		assertHasNoSetWire(root, form1, form3);
		
		assertNotGenerated(form1);
		assertNotGenerated(form2);
		assertNotGenerated(form3);
		assertNotGenerated(field1);
		assertNotGenerated(field2);
		assertNotGenerated(field3);
		assertNotGenerated(field4);
		assertNotGenerated(field5);
		assertNotGenerated(field6);
		
	}

	/**
	 * Test Form 1. It is at the start, so it has no extra fields.
	 * 
	 * @throws Exception
	 */
	public void testForm1() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		
		InputForm form1 = assertHasInputForm(home, "form 1");

		// nothing created on this form
		InputForm target = form1;
		assertHasNoInputTextField(target, "field 3");
		assertHasNoInputTextField(target, "field 4");
		assertHasNoInputTextField(target, "field 5");
		assertHasNoInputTextField(target, "field 6");
		assertHasNoLabel(target, "field 3");
		assertHasNoLabel(target, "field 4");
		assertHasNoLabel(target, "field 5");
		assertHasNoLabel(target, "field 6");

	}
	
	/**
	 * Test Form 2. It is in the middle, so it has fields from Form 1.
	 * 
	 * @throws Exception
	 */
	public void testForm2() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		
		InputForm form1 = assertHasInputForm(home, "form 1");
		InputForm form2 = assertHasInputForm(home, "form 2");
		
		InputTextField field1 = assertHasInputTextField(form1, "field 1");
		InputTextField field2 = assertHasInputTextField(form1, "field 2");

		// created on this form
		InputForm target = form2;
		Label new1 = assertHasLabel(target, "field 1");
		Label new2 = assertHasLabel(target, "field 2");
		
		// not created on this form
		assertHasNoLabel(target, "field 5");
		assertHasNoLabel(target, "field 6");
		
		// and connected
		assertHasSetWire(root, field1, new1);
		assertHasSetWire(root, field2, new2);

		// no text fields on this form
		assertHasNoInputTextField(target, "field 1");
		assertHasNoInputTextField(target, "field 2");
		assertHasNoInputTextField(target, "field 5");
		assertHasNoInputTextField(target, "field 6");

	}
	
	/**
	 * Test Form 3. Is the final destination, so has all fields.
	 * 
	 * @throws Exception
	 */
	public void testForm3() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		
		InputForm form1 = assertHasInputForm(home, "form 1");
		InputForm form2 = assertHasInputForm(home, "form 2");
		InputForm form3 = assertHasInputForm(home, "form 3");
		
		InputTextField field1 = assertHasInputTextField(form1, "field 1");
		InputTextField field2 = assertHasInputTextField(form1, "field 2");
		InputTextField field3 = assertHasInputTextField(form2, "field 3");
		InputTextField field4 = assertHasInputTextField(form2, "field 4");

		// created on this form
		InputForm target = form3;
		Label new1 = assertHasLabel(target, "field 1");
		Label new2 = assertHasLabel(target, "field 2");
		Label new3 = assertHasLabel(target, "field 3");
		Label new4 = assertHasLabel(target, "field 4");
		
		// and connected
		assertHasSetWire(root, field3, new3);
		assertHasSetWire(root, field4, new4);
		
		// form3.field1 does not -> form1.field1;
		// it goes form3.field1 -> form2.field1 -> form1.field1
		assertHasNoSetWire(root, field1, new1);
		assertHasNoSetWire(root, field2, new2);

		// check
		Label field1_a = assertHasLabel(form2, "field 1");
		Label field2_a = assertHasLabel(form2, "field 2");

		assertHasSetWire(root, field1, field1_a);
		assertHasSetWire(root, field2, field2_a);

		assertHasSetWire(root, field1_a, new1);
		assertHasSetWire(root, field2_a, new2);
		
		// no text fields on this form
		assertHasNoInputTextField(target, "field 1");
		assertHasNoInputTextField(target, "field 2");
		assertHasNoInputTextField(target, "field 3");
		assertHasNoInputTextField(target, "field 4");
		
	}

}
