/**
 *
 */
package org.openiaml.model.tests.inference.model0_5;

import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.RunAction;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 *
 * @author jmwright
 */
public class IteratedSyncWires extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(IteratedSyncWires.class);
	}

	public void testInitial() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		assertNotGenerated(home);

		DomainIterator instance = assertHasDomainIterator(home, "iterable");
		assertNotGenerated(instance);

		InputForm form = assertHasInputForm(home, "View News");
		assertNotGenerated(form);

		SyncWire wire = assertHasSyncWire(root, instance, form);
		assertNotGenerated(wire);

	}

	/**
	 * The Form is populated with text fields.
	 *
	 * @throws Exception
	 */
	public void testTextFieldsCreated() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "View News");

		InputTextField t1 = assertHasInputTextField(form, "title");
		InputTextField t2 = assertHasInputTextField(form, "content");
		InputTextField t3 = assertHasInputTextField(form, "posted");

		assertGenerated(t1);
		assertGenerated(t2);
		assertGenerated(t3);

	}

	/**
	 * The text fields are connected to attribute instances by SyncWires.
	 *
	 * @throws Exception
	 */
	public void testAttributesCreated() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		DomainIterator instance = assertHasDomainIterator(home, "iterable");
		InputForm form = assertHasInputForm(home, "View News");

		InputTextField t1 = assertHasInputTextField(form, "title");
		InputTextField t2 = assertHasInputTextField(form, "content");
		InputTextField t3 = assertHasInputTextField(form, "posted");

		assertGenerated(t1);
		assertGenerated(t2);
		assertGenerated(t3);

		DomainAttributeInstance a1 = assertHasDomainAttributeInstance(instance, "title");
		DomainAttributeInstance a2 = assertHasDomainAttributeInstance(instance, "content");
		DomainAttributeInstance a3 = assertHasDomainAttributeInstance(instance, "posted");

		assertGenerated(a1);
		assertGenerated(a2);
		assertGenerated(a3);

		assertGenerated(assertHasSyncWire(root, t1, a1));
		assertGenerated(assertHasSyncWire(root, t2, a2));
		assertGenerated(assertHasSyncWire(root, t3, a3));

	}

	/**
	 * TextField.onEdit calls Attribute.update
	 * @throws Exception
	 */
	public void testTextFieldEditCallsAttributeUpdate() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		DomainIterator instance = assertHasDomainIterator(home, "iterable");
		InputForm form = assertHasInputForm(home, "View News");
		InputTextField t1 = assertHasInputTextField(form, "title");
		DomainAttributeInstance a1 = assertHasDomainAttributeInstance(instance, "title");

		EventTrigger edit = t1.getOnChange();
		Operation update = assertHasOperation(a1, "update");

		RunAction run = assertHasRunAction(root, edit, update);
		System.out.println(run);

		assertGenerated(run);

	}

	/**
	 * Attribute.onEdit calls TextField.update
	 * @throws Exception
	 */
	public void testAttributeEditCallsTextFieldUpdate() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		DomainIterator instance = assertHasDomainIterator(home, "iterable");
		InputForm form = assertHasInputForm(home, "View News");
		InputTextField t1 = assertHasInputTextField(form, "title");
		DomainAttributeInstance a1 = assertHasDomainAttributeInstance(instance, "title");

		EventTrigger edit = a1.getOnChange();
		Operation update = assertHasOperation(t1, "update");

		RunAction run = assertHasRunAction(root, edit, update);

		assertGenerated(run);

	}

	/**
	 * Compared to {@link SelectWireManyPaginate}, the navigation
	 * buttons should <strong>NOT</strong> be created (since we are
	 * only selecting one).
	 *
	 * @throws Exception
	 */
	public void testNavigationButtonsNotCreated() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "View News");

		assertHasNoButton(form, "Next");
		assertHasNoButton(form, "Previous");
		assertHasNoButton(form, "First");
		assertHasNoButton(form, "Last");
		assertHasNoLabel(form, "Results");
	}

}
