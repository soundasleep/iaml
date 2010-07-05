/**
 *
 */
package org.openiaml.model.tests.inference.model0_5;

import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 *
 * @author jmwright
 */
public class SyncWireTestCaseOverridden extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(SyncWireTestCaseOverridden.class);
	}

	/**
	 * Because sync1 is overridden, Name1.onEdit won't call Name2.update.
	 *
	 * @throws Exception
	 */
	public void testName1toName2() throws Exception {
		Frame page = assertHasFrame(root, "on-page");

		InputTextField name1 = assertHasInputTextField(page, "name1");
		InputTextField name2 = assertHasInputTextField(page, "name2");

		EventTrigger onEdit = name1.getOnChange();
		assertGenerated(onEdit);
		Operation update = assertHasOperation(name2, "update");
		assertGenerated(update);

		assertHasNoRunAction(root, onEdit, update);
	}

	/**
	 * Because sync2 is <em>not</em> overridden, Name2.onEdit will call Name3.update.
	 *
	 * @throws Exception
	 */
	public void testName2toName3() throws Exception {
		Frame page = assertHasFrame(root, "on-page");

		InputTextField name2 = assertHasInputTextField(page, "name2");
		InputTextField name3 = assertHasInputTextField(page, "name3");

		EventTrigger onEdit = name2.getOnChange();
		assertGenerated(onEdit);
		Operation update = assertHasOperation(name3, "update");
		assertGenerated(update);

		assertHasRunAction(root, onEdit, update);
	}

}
