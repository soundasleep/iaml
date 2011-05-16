/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_3;

import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.messaging.Email;
import org.openiaml.model.model.operations.ActivityOperation;
import org.openiaml.model.model.operations.ActivityParameter;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.CastNode;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.ExternalValue;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.SetNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Issue 209: Allow Properties to be set directly by SetWires or SyncWires
 *
 * @example SetWire
 * 		Using a {@model SetWire} to set {@model Value Properties} directly.
 */
public class SetPropertiesDirectly extends ValidInferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(getInferenceClass());
	}

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {

		Frame home = assertHasFrame(root, "Home");

		InputForm form = assertHasInputForm(home, "Input Form");

		InputTextField text1 = assertHasInputTextField(form, "field 1");
		InputTextField text2 = assertHasInputTextField(form, "field 2");

		Email email = assertHasEmail(home, "Target");

		Label label1 = assertHasLabel(email, "field 1");
		Value toSet = assertHasValue(email, "set directly");
		Value notSet = assertHasValue(email, "not set");

		// all not generated
		assertNotGenerated(home, form, text1, text2, email, label1, toSet, notSet);

		// a label2 should be generated

	}

	/**
	 * Since we are setting an Email using a SetWire, a
	 * Label 'field 2' should be generated.
	 *
	 * @throws Exception
	 */
	public void testField2LabelCreated() throws Exception {

		Frame home = assertHasFrame(root, "Home");

		InputForm form = assertHasInputForm(home, "Input Form");

		InputTextField text1 = assertHasInputTextField(form, "field 1");
		InputTextField text2 = assertHasInputTextField(form, "field 2");

		Email email = assertHasEmail(home, "Target");

		Label label1 = assertHasLabel(email, "field 1");
		Label label2 = assertHasLabel(email, "field 2");
		assertGenerated(label2);

		// connected by set wires
		assertGenerated(assertHasSetWire(root, text1, label1));
		assertGenerated(assertHasSetWire(root, text2, label2));
	}

	/**
	 * Operations are created for each Value in Email.
	 *
	 * @throws Exception
	 */
	public void testOperationsCreated() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		Email email = assertHasEmail(home, "Target");

		Operation update1 = assertHasActivityOperation(email, "set value set directly");
		assertGenerated(update1);

		// since the other Value does not have an incoming SetWire, no
		// such operation is generated
		assertHasNoOperation(email, "set value not set");

	}

	/**
	 * The label Field 1 in the Email should, on change, update
	 * the target Value with its current value.
	 *
	 * @throws Exception
	 */
	public void testField1OnchangeUpdatesProperty() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		Email email = assertHasEmail(home, "Target");
		Label label1 = assertHasLabel(email, "field 1");

		Event onChange = label1.getOnChange();
		assertGenerated(onChange);

		Operation update = assertHasActivityOperation(email, "set value set directly");
		assertGenerated(update);

		ECARule edge = assertHasECARule(root, onChange, update);
		assertGenerated(edge);

		// with the field value
		Value fieldValue = assertHasFieldValue(label1);
		assertGenerated(fieldValue);
		assertGenerated(assertHasParameter(root, fieldValue, edge));

	}

	/**
	 * 'fieldValue' in the Label already has an operation ('update');
	 * we shouldn't create a new 'set Value fieldValue' operation
	 * for it.
	 *
	 * @throws Exception
	 */
	public void testNoSetFieldValuePropertyOperation() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		Email email = assertHasEmail(home, "Target");
		Label label1 = assertHasLabel(email, "field 1");

		assertHasNoOperation(label1, "set value fieldValue");

	}

	/**
	 * Test the contents of the generated set Value operation.
	 *
	 * @throws Exception
	 */
	public void testContentsOfSetPropertyOperation() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		Email email = assertHasEmail(home, "Target");
		ActivityOperation update = assertHasActivityOperation(email, "set value set directly");

		// -- traverse from start node --
		StartNode start = assertHasStartNode(update);
		FinishNode finish = assertHasFinishNode(update);
		CancelNode cancel = assertHasCancelNode(update);

		DecisionNode check = assertHasDecisionNode(update, "can cast?");
		CastNode cast = assertHasCastNode(update);

		SetNode set = assertHasSetNode(update);

		assertHasExecutionEdge(update, start, check);
		assertHasExecutionEdge(update, check, cancel, "no");
		assertHasExecutionEdge(update, check, set, "yes");
		assertHasExecutionEdge(update, set, finish);

		// data flow edges
		ActivityParameter param = assertHasActivityParameter(update, "setValueTo");
		assertHasDataFlowEdge(update, param, cast);
		assertHasDataFlowEdge(update, cast, check);
		assertHasDataFlowEdge(update, cast, set);

		assertEquals(1, set.getOutFlows().size());
		ExternalValue ev_f2 = (ExternalValue) set.getOutFlows().get(0).getTo();
		Value f2 = ev_f2.getExternalValueEdges().getValue();
		assertEquals("set directly", f2.getName());

	}

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return getClass();
	}

}
