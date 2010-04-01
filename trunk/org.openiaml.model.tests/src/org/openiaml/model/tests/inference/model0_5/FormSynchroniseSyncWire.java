/**
 *
 */
package org.openiaml.model.tests.inference.model0_5;

import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * {@model Form}s connected together with a {@model SyncWire} should have
 * {@model InputTextField}s also duplicated and synced together.
 * 
 * @author jmwright
 */
public class FormSynchroniseSyncWire extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(FormSynchroniseSyncWire.class);
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
		
		assertNotGenerated(assertHasSyncWire(root, form1, form2));
		assertNotGenerated(assertHasSyncWire(root, form2, form3));
		
		// not a circle
		assertHasNoSyncWire(root, form1, form3);
		
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
	 * Test Form 1.
	 * 
	 * @throws Exception
	 */
	public void testForm1() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		
		InputForm form1 = assertHasInputForm(home, "form 1");
		InputForm form2 = assertHasInputForm(home, "form 2");
		InputForm form3 = assertHasInputForm(home, "form 3");
		
		//InputTextField field1 = assertHasInputTextField(form1, "field 1");
		//InputTextField field2 = assertHasInputTextField(form1, "field 2");
		InputTextField field3 = assertHasInputTextField(form2, "field 3");
		InputTextField field4 = assertHasInputTextField(form2, "field 4");
		InputTextField field5 = assertHasInputTextField(form3, "field 5");
		InputTextField field6 = assertHasInputTextField(form3, "field 6");

		// created on this form
		InputForm target = form1;
		//InputTextField new1 = assertHasInputTextField(target, "field 1");
		//InputTextField new2 = assertHasInputTextField(target, "field 2");
		InputTextField new3 = assertHasInputTextField(target, "field 3");
		InputTextField new4 = assertHasInputTextField(target, "field 4");
		InputTextField new5 = assertHasInputTextField(target, "field 5");
		InputTextField new6 = assertHasInputTextField(target, "field 6");
		
		// and connected
		//assertHasSyncWire(root, field1, new1);
		//assertHasSyncWire(root, field2, new2);
		assertHasSyncWire(root, field3, new3);
		assertHasSyncWire(root, field4, new4);
		
		// form1.field5 does not -> form3.field5;
		// it goes form1.field5 -> form2.field5 -> form3.field5
		assertHasNoSyncWire(root, field5, new5);
		assertHasNoSyncWire(root, field6, new6);
		
		// check
		InputTextField field5_a = assertHasInputTextField(form2, "field 5");
		InputTextField field6_a = assertHasInputTextField(form2, "field 6");

		assertHasSyncWire(root, new5, field5_a);
		assertHasSyncWire(root, new6, field6_a);

		assertHasSyncWire(root, field5_a, field5);
		assertHasSyncWire(root, field6_a, field6);
		
	}
	
	/**
	 * Test Form 2.
	 * 
	 * @throws Exception
	 */
	public void testForm2() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		
		InputForm form1 = assertHasInputForm(home, "form 1");
		InputForm form2 = assertHasInputForm(home, "form 2");
		InputForm form3 = assertHasInputForm(home, "form 3");
		
		InputTextField field1 = assertHasInputTextField(form1, "field 1");
		InputTextField field2 = assertHasInputTextField(form1, "field 2");
		//InputTextField field3 = assertHasInputTextField(form2, "field 3");
		//InputTextField field4 = assertHasInputTextField(form2, "field 4");
		InputTextField field5 = assertHasInputTextField(form3, "field 5");
		InputTextField field6 = assertHasInputTextField(form3, "field 6");

		// created on this form
		InputForm target = form2;
		InputTextField new1 = assertHasInputTextField(target, "field 1");
		InputTextField new2 = assertHasInputTextField(target, "field 2");
		//InputTextField new3 = assertHasInputTextField(target, "field 3");
		//InputTextField new4 = assertHasInputTextField(target, "field 4");
		InputTextField new5 = assertHasInputTextField(target, "field 5");
		InputTextField new6 = assertHasInputTextField(target, "field 6");
		
		// and connected
		assertHasSyncWire(root, field1, new1);
		assertHasSyncWire(root, field2, new2);
		//assertHasSyncWire(root, field3, new3);
		//assertHasSyncWire(root, field4, new4);
		assertHasSyncWire(root, field5, new5);
		assertHasSyncWire(root, field6, new6);
		
	}
	
	/**
	 * Test Form 3.
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
		//InputTextField field5 = assertHasInputTextField(form3, "field 5");
		//InputTextField field6 = assertHasInputTextField(form3, "field 6");

		// created on this form
		InputForm target = form3;
		InputTextField new1 = assertHasInputTextField(target, "field 1");
		InputTextField new2 = assertHasInputTextField(target, "field 2");
		InputTextField new3 = assertHasInputTextField(target, "field 3");
		InputTextField new4 = assertHasInputTextField(target, "field 4");
		//InputTextField new5 = assertHasInputTextField(target, "field 5");
		//InputTextField new6 = assertHasInputTextField(target, "field 6");
		
		// and connected
		assertHasSyncWire(root, field3, new3);
		assertHasSyncWire(root, field4, new4);
		//assertHasSyncWire(root, field5, new5);
		//assertHasSyncWire(root, field6, new6);
		
		// form3.field1 does not -> form1.field1;
		// it goes form3.field1 -> form2.field1 -> form1.field1
		assertHasNoSyncWire(root, field1, new1);
		assertHasNoSyncWire(root, field2, new2);

		// check
		InputTextField field1_a = assertHasInputTextField(form2, "field 1");
		InputTextField field2_a = assertHasInputTextField(form2, "field 2");

		assertHasSyncWire(root, new1, field1_a);
		assertHasSyncWire(root, new2, field2_a);

		assertHasSyncWire(root, field1_a, field1);
		assertHasSyncWire(root, field2_a, field2);
		
	}

}
