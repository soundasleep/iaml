/**
 *
 */
package org.openiaml.model.tests.inference.model0_4_4;

import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ExecutionEdgesSource;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.CastNode;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.OperationCallNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.RunAction;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Inference of validation, annotations etc.
 *
 * @author jmwright
 *
 */
public class InputTextFieldDataTypeSync extends ValidInferenceTestCase {

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return InputTextFieldDataTypeSync.class;
	}

	/**
	 * Test the initial model.
	 */
	public void testInitial() throws Exception {

		root = loadDirectly(InputTextFieldDataTypeSync.class);

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

		InputTextField dt = assertHasInputTextField(home, "Date");
		assertNotGenerated(dt);
		assertHasNoFieldValue(dt);
		assertEquals(dt.getType().getURI(), BuiltinDataTypes.TYPE_DATETIME);

		InputTextField email = assertHasInputTextField(home, "Email");
		assertNotGenerated(email);
		assertHasNoFieldValue(email);
		assertEquals(email.getType().getURI(), BuiltinDataTypes.TYPE_EMAIL);

	}

	/**
	 * Each text field will have an 'warning' label created.
	 */
	public void testInferredPropertyTypes() throws Exception {

		root = loadAndInfer(InputTextFieldDataTypeSync.class);

		Frame home = assertHasFrame(root, "Home");

		InputTextField def = assertHasInputTextField(home, "Default");
		{
			Label annotation = assertHasLabel(def, "Warning");
			assertGenerated(annotation);
		}

		InputTextField integer = assertHasInputTextField(home, "Integer");
		{
			Label annotation = assertHasLabel(integer, "Warning");
			assertGenerated(annotation);
		}

		InputTextField str = assertHasInputTextField(home, "String");
		{
			Label annotation = assertHasLabel(str, "Warning");
			assertGenerated(annotation);
		}

		InputTextField dt = assertHasInputTextField(home, "Date");
		{
			Label annotation = assertHasLabel(dt, "Warning");
			assertGenerated(annotation);
		}

		InputTextField email = assertHasInputTextField(home, "Email");
		{
			Label annotation = assertHasLabel(email, "Warning");
			assertGenerated(annotation);
		}

	}

	/**
	 * Each label has a "show" and "hide" primitive operations created.
	 */
	public void testLabelsHaveShowHide() throws Exception {

		root = loadAndInfer(InputTextFieldDataTypeSync.class);

		Frame home = assertHasFrame(root, "Home");

		InputTextField def = assertHasInputTextField(home, "Default");
		{
			Label annotation = assertHasLabel(def, "Warning");

			PrimitiveOperation show = assertHasPrimitiveOperation(annotation, "show");
			assertGenerated(show);
			PrimitiveOperation hide = assertHasPrimitiveOperation(annotation, "hide");
			assertGenerated(hide);

			// and a fieldValue
			assertGenerated(assertHasFieldValue(annotation));
		}

		InputTextField integer = assertHasInputTextField(home, "Integer");
		{
			Label annotation = assertHasLabel(integer, "Warning");

			PrimitiveOperation show = assertHasPrimitiveOperation(annotation, "show");
			assertGenerated(show);
			PrimitiveOperation hide = assertHasPrimitiveOperation(annotation, "hide");
			assertGenerated(hide);

			// and a fieldValue
			assertGenerated(assertHasFieldValue(annotation));
		}

	}

	/**
	 * Each text field has a 'validate' operation generated.
	 */
	public void testTextFieldHasValidateOperation() throws Exception {

		root = loadAndInfer(InputTextFieldDataTypeSync.class);

		Frame home = assertHasFrame(root, "Home");

		InputTextField email = assertHasInputTextField(home, "Email 2");
		{
			CompositeOperation validate = assertHasCompositeOperation(email, "validate");
			assertGenerated(validate);
		}

		InputTextField integer = assertHasInputTextField(home, "Integer");
		{
			CompositeOperation validate = assertHasCompositeOperation(integer, "validate");
			assertGenerated(validate);
		}

	}

	/**
	 * A 'default' input source still needs to have a 'validate' operation
	 *
	 * @throws Exception
	 */
	public void testDefaultHasValidateOperation() throws Exception {
		root = loadAndInfer(InputTextFieldDataTypeSync.class);

		Frame home = assertHasFrame(root, "Home");

		InputTextField def = assertHasInputTextField(home, "Default");
		{
			CompositeOperation validate = assertHasCompositeOperation(def, "validate");
			assertGenerated(validate);
		}
	}


	/**
	 * The field.onEdit should call the 'validate' operation.
	 */
	public void testEditCallsValidate() throws Exception {

		root = loadAndInfer(InputTextFieldDataTypeSync.class);
		Frame home = assertHasFrame(root, "Home");

		// source
		InputTextField integer = assertHasInputTextField(home, "Integer");
		CompositeOperation validate = assertHasCompositeOperation(integer, "validate");

		EventTrigger onEdit = integer.getOnChange();
		assertGenerated(assertHasRunAction(root, onEdit, validate));
	}

	/**
	 * Tests the contents of the validate operation on Integer.
	 */
	public void testValidateOperationContents() throws Exception {

		root = loadAndInfer(InputTextFieldDataTypeSync.class);
		Frame home = assertHasFrame(root, "Home");

		// source
		InputTextField integer = assertHasInputTextField(home, "Integer");
		Property fieldValue = assertHasFieldValue(integer);
		CompositeOperation validate = assertHasCompositeOperation(integer, "validate");

		Label annotation = assertHasLabel(integer, "Warning");
		PrimitiveOperation show = assertHasPrimitiveOperation(annotation, "show");
		PrimitiveOperation hide = assertHasPrimitiveOperation(annotation, "hide");
		PrimitiveOperation update = assertHasPrimitiveOperation(annotation, "update");

		// target
		InputTextField email2 = assertHasInputTextField(home, "Email 2");
		Property fieldValue2 = assertHasFieldValue(email2);

		// now we can check the contents
		StartNode start = assertHasStartNode(validate);
		DecisionNode check = assertHasDecisionNode(validate, "can cast?");
		assertHasExecutionEdge(validate, start, check);

		CastNode cast = assertHasCastNode(validate);
		assertHasDataFlowEdge(validate, fieldValue, cast);
		assertHasDataFlowEdge(validate, cast, fieldValue2);
		assertHasDataFlowEdge(validate, cast, check);

		// 'y' runs 'hide'
		OperationCallNode callHide = assertHasOperationCallNode(validate, "call hide");
		assertHasRunAction(validate, callHide, hide);
		assertHasExecutionEdge(validate, check, callHide, "yes");

		// 'n' first sets the value of the label
		OperationCallNode callUpdate = assertHasOperationCallNode(validate, "update label");
		RunAction runUpdate = assertHasRunAction(validate, callUpdate, update);
		assertHasExecutionEdge(validate, check, callUpdate, "no");

		// with a parmameter of the exception
		StaticValue warning = assertHasStaticValue(validate, "failure message");
		assertEquals("Warning: Invalid input.", warning.getValue());
		assertHasParameterEdge(validate, warning, runUpdate);

		// and then 'n' calls 'show'
		OperationCallNode callShow = assertHasOperationCallNode(validate, "call show");
		assertHasRunAction(validate, callShow, show);
		assertHasExecutionEdge(validate, callUpdate, callShow);

		// 'callHide' then terminates
		assertGenerated(assertGoesToFinishNode(callHide));

		// 'callShow' also terminates
		assertGenerated(assertGoesToFinishNode(callShow));

	}

	/**
	 * The given source of execution edges terminates with a
	 * Finish node.
	 *
	 * @return the FinishNode expected
	 * @param callHide
	 */
	private FinishNode assertGoesToFinishNode(ExecutionEdgesSource e) {
		assertEquals(1, e.getOutExecutions().size());
		FinishNode node = (FinishNode) e.getOutExecutions().get(0).getTo();
		return node;
	}

	/**
	 * All text fields should have a 'can cast?' condition created.
	 *
	 * @throws Exception
	 */
	public void testCreatesCanCastCondition() throws Exception {

		root = loadAndInfer(InputTextFieldDataTypeSync.class);
		Frame home = assertHasFrame(root, "Home");

		InputTextField email = assertHasInputTextField(home, "Email 2");
		{
			CompositeCondition canCast = assertHasCompositeCondition(email, "can cast?");
			assertGenerated(canCast);
		}

		InputTextField integer = assertHasInputTextField(home, "Integer");
		{
			CompositeCondition canCast = assertHasCompositeCondition(integer, "can cast?");
			assertGenerated(canCast);
		}

		InputTextField def = assertHasInputTextField(home, "Default");
		{
			CompositeCondition canCast = assertHasCompositeCondition(def, "can cast?");
			assertGenerated(canCast);
		}

	}

	/**
	 * Integer.onEdit --> Email.update() should have an incoming ConditionEdge
	 * making sure that the cast can be done.
	 *
	 * @throws Exception
	 */
	public void testEditRunUpdateCallsCondition() throws Exception {

		root = loadAndInfer(InputTextFieldDataTypeSync.class);
		Frame home = assertHasFrame(root, "Home");

		InputTextField email = assertHasInputTextField(home, "Email 2");
		Property emailValue = assertHasFieldValue(email);
		InputTextField integer = assertHasInputTextField(home, "Integer");

		CompositeCondition canCast = assertHasCompositeCondition(integer, "can cast?");

		EventTrigger onEdit = email.getOnChange();
		Operation update = assertHasOperation(integer, "update");

		RunAction run = assertHasRunAction(root, onEdit, update);
		assertGenerated(run);

		// should be an incoming condition edge
		ConditionEdge edge = assertHasConditionEdge(root, canCast, run);

		// and the edge should have the current Email.property as a parameter
		assertHasParameterEdge(root, emailValue, edge);

	}

	/**
	 * The reverse of {@link #testEditRunUpdateCallsCondition()}.
	 *
	 * @throws Exception
	 */
	public void testEditRunUpdateCallsConditionReverse() throws Exception {

		root = loadAndInfer(InputTextFieldDataTypeSync.class);
		Frame home = assertHasFrame(root, "Home");

		InputTextField integer = assertHasInputTextField(home, "Integer");
		Property intValue = assertHasFieldValue(integer);
		InputTextField email = assertHasInputTextField(home, "Email 2");

		CompositeCondition canCast = assertHasCompositeCondition(email, "can cast?");

		EventTrigger onEdit = integer.getOnChange();
		Operation update = assertHasOperation(email, "update");

		RunAction run = assertHasRunAction(root, onEdit, update);
		assertGenerated(run);

		// should be an incoming condition edge
		ConditionEdge edge = assertHasConditionEdge(root, canCast, run);

		// and the edge should have the current Email.property as a parameter
		assertHasParameterEdge(root, intValue, edge);

	}

	/**
	 * Test the contents of the 'can cast?' condition.
	 *
	 * @throws Exception
	 */
	public void testCanCastConditionContents() throws Exception {

		root = loadAndInfer(InputTextFieldDataTypeSync.class);
		Frame home = assertHasFrame(root, "Home");

		InputTextField integer = assertHasInputTextField(home, "Integer");
		Property integerValue = assertHasFieldValue(integer);

		CompositeCondition canCast = assertHasCompositeCondition(integer, "can cast?");

		StartNode start = assertHasStartNode(canCast);
		Parameter param = assertHasParameter(canCast, "value");
		// parameter is of 'any' type
		assertNull(param.getType());

		DecisionNode check = assertHasDecisionNode(canCast, "can cast?");

		CastNode cast = assertHasCastNode(canCast);
		assertHasDataFlowEdge(canCast, param, cast);	// in
		assertHasDataFlowEdge(canCast, cast, integerValue);	// out
		assertHasDataFlowEdge(canCast, cast, check);	// check

		CancelNode cancel = assertHasCancelNode(canCast);
		FinishNode finish = assertHasFinishNode(canCast);

		assertHasExecutionEdge(canCast, start, check);
		assertHasExecutionEdge(canCast, check, cancel, "no");
		assertHasExecutionEdge(canCast, check, finish, "yes");

	}

	/**
	 * onAccess should only call 'init' if the source value can be
	 * cast correctly.
	 *
	 * @throws Exception
	 */
	public void testOnAccessCallsCanCastCondition() throws Exception {

		root = loadAndInfer(InputTextFieldDataTypeSync.class);
		Frame home = assertHasFrame(root, "Home");

		InputTextField integer = assertHasInputTextField(home, "Integer");
		InputTextField email = assertHasInputTextField(home, "Email 2");

		EventTrigger onAccess = integer.getOnAccess();
		Operation init = assertHasOperation(integer, "init");
		Property emailValue = assertHasFieldValue(email);

		// Integer.onInit should call Integer.update(Email.fieldValue)
		RunAction run = assertHasRunAction(root, onAccess, init);
		assertHasParameterEdge(root, emailValue, run);

		// now make sure that the condition is connected
		CompositeCondition canCast = assertHasCompositeCondition(integer, "can cast?");

		ConditionEdge edge = assertHasConditionEdge(root, canCast, run);
		assertHasParameterEdge(root, emailValue, edge);

	}

	/**
	 * onAccess should call 'validate', but only <em>after</em>
	 * the other onInit calls have executed (i.e. a lower priority).
	 *
	 * @throws Exception
	 */
	public void testOnAccessCallsValidate() throws Exception {

		root = loadAndInfer(InputTextFieldDataTypeSync.class);
		Frame home = assertHasFrame(root, "Home");

		InputTextField integer = assertHasInputTextField(home, "Integer");

		EventTrigger onAccess = integer.getOnAccess();
		Operation init = assertHasOperation(integer, "init");

		// Integer.onInit should call Integer.update(Email.fieldValue)
		RunAction runInit = assertHasRunAction(root, onAccess, init);

		// validate operation is also called
		CompositeOperation validate = assertHasCompositeOperation(integer, "validate");
		RunAction runValidate = assertHasRunAction(root, onAccess, validate);

		// it should be a lower priority
		assertTrue("Run priority '" + runValidate.getPriority() + "' should be lower than '" + runInit.getPriority() + "'",
				runValidate.getPriority() < runInit.getPriority());

	}

	/**
	 * {@link #testOnAccessCallsValidate()} should only execute if
	 * the fieldValue is set.
	 *
	 * @throws Exception
	 */
	public void testOnAccessCallsValidateIfFieldValueIsSet() throws Exception {

		root = loadAndInfer(InputTextFieldDataTypeSync.class);
		Frame home = assertHasFrame(root, "Home");

		InputTextField integer = assertHasInputTextField(home, "Integer");
		EventTrigger onAccess = integer.getOnAccess();

		// validate operation is also called
		CompositeOperation validate = assertHasCompositeOperation(integer, "validate");
		RunAction runValidate = assertHasRunAction(root, onAccess, validate);

		// the 'fieldValue is set' condition
		Condition isSet = assertHasCondition(integer, "fieldValue is set");
		assertHasConditionEdge(root, isSet, runValidate);

	}

	/**
	 * There should be a cast node within the 'update' operation.
	 * We will leave other inference test methods to check that the operation is
	 * constructed correctly.
	 *
	 * @throws Exception
	 */
	public void testInputsAreCastInUpdate() throws Exception {

		root = loadAndInfer(InputTextFieldDataTypeSync.class);
		Frame home = assertHasFrame(root, "Home");

		InputTextField integer = assertHasInputTextField(home, "Integer");
		CompositeOperation update = assertHasCompositeOperation(integer, "update");

		assertGenerated(assertHasCastNode(update));

	}

	/**
	 * There should be a cast node within the 'init' operation.
	 * We will leave other inference test methods to check that the operation is
	 * constructed correctly.
	 *
	 * @throws Exception
	 */
	public void testInputsAreCastInInit() throws Exception {

		root = loadAndInfer(InputTextFieldDataTypeSync.class);
		Frame home = assertHasFrame(root, "Home");

		InputTextField integer = assertHasInputTextField(home, "Integer");
		CompositeOperation init = assertHasCompositeOperation(integer, "init");

		assertGenerated(assertHasCastNode(init));

	}

}
