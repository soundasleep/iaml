/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_3;

import org.openiaml.model.model.ActionEdge;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.CastNode;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.Email;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Issue 209: Allow Properties to be set directly by SetWires or SyncWires
 * 
 * @example SetWire
 * 		Using a {@model SetWire} to set {@model Property Properties} directly.
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
		Property toSet = assertHasProperty(email, "set directly");
		Property notSet = assertHasProperty(email, "not set");
		
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
	 * Operations are created for each property in Email.
	 * 
	 * @throws Exception
	 */
	public void testOperationsCreated() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		Email email = assertHasEmail(home, "Target");

		Operation update1 = assertHasCompositeOperation(email, "set property set directly");
		assertGenerated(update1);
		
		// since the other property does not have an incoming SetWire, no
		// such operation is generated
		assertHasNoOperation(email, "set property not set");
		
	}
	
	/**
	 * The label Field 1 in the Email should, on change, update
	 * the target property with its current value.
	 * 
	 * @throws Exception
	 */
	public void testField1OnchangeUpdatesProperty() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		Email email = assertHasEmail(home, "Target");
		Label label1 = assertHasLabel(email, "field 1");
		
		EventTrigger onChange = label1.getOnChange();
		assertGenerated(onChange);
		
		Operation update = assertHasCompositeOperation(email, "set property set directly");
		assertGenerated(update);
		
		ActionEdge edge = assertHasActionEdge(root, onChange, update);
		assertGenerated(edge);
		
		// with the field value
		Property fieldValue = assertHasFieldValue(label1);
		assertGenerated(fieldValue);
		assertGenerated(assertHasParameterEdge(root, fieldValue, edge));
		
	}
	
	/**
	 * 'fieldValue' in the Label already has an operation ('update');
	 * we shouldn't create a new 'set property fieldValue' operation
	 * for it.
	 * 
	 * @throws Exception
	 */
	public void testNoSetFieldValuePropertyOperation() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		Email email = assertHasEmail(home, "Target");
		Label label1 = assertHasLabel(email, "field 1");
		
		assertHasNoOperation(label1, "set property fieldValue");
		
	}
	
	/**
	 * Test the contents of the generated set property operation. 
	 * 
	 * @throws Exception
	 */
	public void testContentsOfSetPropertyOperation() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		Email email = assertHasEmail(home, "Target");
		CompositeOperation update = assertHasCompositeOperation(email, "set property set directly");
		
		// -- traverse from start node --
		StartNode start = assertHasStartNode(update);
		FinishNode finish = assertHasFinishNode(update);
		CancelNode cancel = assertHasCancelNode(update);
		
		DecisionNode check = assertHasDecisionNode(update, "can cast?");
		CastNode cast = assertHasCastNode(update);
		
		PrimitiveOperation set = assertHasPrimitiveOperation(update, "set");
		
		assertHasExecutionEdge(update, start, check);
		assertHasExecutionEdge(update, check, cancel, "no");
		assertHasExecutionEdge(update, check, set, "yes");
		assertHasExecutionEdge(update, set, finish);
		
		// data flow edges
		Parameter param = assertHasParameter(update, "setValueTo");
		assertHasDataFlowEdge(update, param, cast);
		assertHasDataFlowEdge(update, cast, check);
		assertHasDataFlowEdge(update, cast, set);

		assertEquals(1, set.getOutFlows().size());
		Property f2 = (Property) set.getOutFlows().get(0).getTo();
		assertEquals("set directly", f2.getName());
		
	}
	
	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return getClass();
	}
	
}
