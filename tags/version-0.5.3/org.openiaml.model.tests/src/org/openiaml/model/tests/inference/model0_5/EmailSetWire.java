/**
 *
 */
package org.openiaml.model.tests.inference.model0_5;

import org.openiaml.model.model.Property;
import org.openiaml.model.model.scopes.Email;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * An {@model Email} connected to an {@model InputForm} via a {@model SetWire}
 * should have {@model Property Properties} created for every field within the
 * {@model InputForm}.
 *
 * @author jmwright
 */
public class EmailSetWire extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(EmailSetWire.class);
	}

	/**
	 * @throws Exception
	 */
	public void testEmailHasLabels() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		Email email = assertHasEmail(home, "Email");

		InputForm form = assertHasInputForm(home, "form");

		InputTextField field1 = assertHasInputTextField(form, "field 1");
		InputTextField field2 = assertHasInputTextField(form, "field 2");
		InputTextField field3 = assertHasInputTextField(form, "field 3");

		// the following should be generated
		Label label1 = assertHasLabel(email, "field 1");
		Label label2 = assertHasLabel(email, "field 2");
		Label label3 = assertHasLabel(email, "field 3");

		// and they should be connected by SetWire
		assertGenerated(assertHasSetWire(root, field1, label1));
		assertGenerated(assertHasSetWire(root, field2, label2));
		assertGenerated(assertHasSetWire(root, field3, label3));

		// but not the other way round
		assertHasNoSetWire(root, label1, field1);
		assertHasNoSetWire(root, label2, field2);
		assertHasNoSetWire(root, label3, field3);

		// or in weird ways
		assertHasNoSetWire(root, field1, label2);
		assertHasNoSetWire(root, field2, label1);
		assertHasNoSetWire(root, field3, label1);
		assertHasNoSetWire(root, field1, label3);

	}

	/**
	 * @throws Exception
	 */
	public void testLabelContents() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		Email email = assertHasEmail(home, "Email");

		// the following should be generated
		Label label = assertHasLabel(email, "field 1");

		// the label should have a fieldValue
		Property labelValue = assertHasFieldValue(label);
		assertGenerated(labelValue);

		// event
		assertGenerated(label.getOnChange());

		// and an operation 'update'
		assertGenerated(assertHasOperation(label, "update"));

	}

}
