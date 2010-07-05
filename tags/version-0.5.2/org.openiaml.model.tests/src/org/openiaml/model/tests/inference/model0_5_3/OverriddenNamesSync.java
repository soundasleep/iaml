/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_3;

import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Tests the "overridden names" helper property for model generation.
 * 
 * @author jmwright
 */
public class OverriddenNamesSync extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(OverriddenNamesSync.class);
	}

	/**
	 * Test the initial model.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		
		InputForm left = assertHasInputForm(home, "form left");
		InputForm right = assertHasInputForm(home, "form right");
		InputForm mid = assertHasInputForm(home, "form mid");
		
		assertHasSyncWire(root, left, mid);
		assertHasSyncWire(root, right, mid);
		assertHasNoSyncWire(root, left, right);
		
		// contents of left
		assertHasInputTextField(left, "a");
		assertHasInputTextField(left, "b");
		assertHasInputTextField(left, "c");
		assertHasNoInputTextField(left, "d");
		assertHasNoInputTextField(left, "e");

		// contents of right
		assertHasLabel(right, "c");
		assertHasLabel(right, "d");
		assertHasLabel(right, "e");
		assertHasNoLabel(right, "a");
		assertHasNoLabel(right, "b");

		// test the things that must be prevented
		assertCollectionEquals(mid.getOverriddenNames(), "b", "c", "d");
		
	}
	
	/**
	 * Test the middle form.
	 * 
	 * @throws Exception
	 */
	public void testMiddleForm() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm mid = assertHasInputForm(home, "form mid");
		
		// contents of left
		assertHasInputTextField(mid, "a");
		assertHasLabel(mid, "e");
		
		// things that should not be created
		assertHasNoInputTextField(mid, "b");
		assertHasNoInputTextField(mid, "c");
		assertHasNoInputTextField(mid, "d");
		assertHasNoInputTextField(mid, "e");
		assertHasNoLabel(mid, "a");
		assertHasNoLabel(mid, "b");
		assertHasNoLabel(mid, "c");
		assertHasNoLabel(mid, "d");
		
	}
	
	/**
	 * Because we're connected by SyncWires, the left form will have
	 * elements created too.
	 * 
	 * @throws Exception
	 */
	public void testLeftFormInference() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm left = assertHasInputForm(home, "form left");
		
		// contents of left
		assertHasInputTextField(left, "a");
		assertHasInputTextField(left, "b");
		assertHasInputTextField(left, "c");
		assertHasNoInputTextField(left, "d");
		assertHasLabel(left, "e");
		
	}
	
	/**
	 * Because we're connected by SyncWires, the right form will have
	 * elements created too.
	 * 
	 * @throws Exception
	 */
	public void testRightFormInference() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm right = assertHasInputForm(home, "form right");
		
		// contents of left
		assertHasLabel(right, "c");
		assertHasLabel(right, "d");
		assertHasLabel(right, "e");
		assertHasInputTextField(right, "a");
		assertHasNoLabel(right, "b");
		
	}
	
}
