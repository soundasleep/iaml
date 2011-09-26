/**
 *
 */
package org.openiaml.model.tests.inference.model0_4_4;

import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.model.BuiltinOperation;
import org.openiaml.model.model.BuiltinProperty;
import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.EXSDDataType;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.Function;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.SimpleCondition;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.operations.ActivityOperation;
import org.openiaml.model.model.operations.CastNode;
import org.openiaml.model.model.operations.DataFlowEdge;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.ExecutionEdgesSource;
import org.openiaml.model.model.operations.ExternalValue;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.OperationCallNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Label;
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
		assertEquals(((EXSDDataType) integer.getType()).getDefinition().getURI(), BuiltinDataTypes.TYPE_INTEGER);

		InputTextField str = assertHasInputTextField(home, "String");
		assertNotGenerated(str);
		assertHasNoFieldValue(str);
		assertEquals(((EXSDDataType) str.getType()).getDefinition().getURI(), BuiltinDataTypes.TYPE_STRING);

		InputTextField dt = assertHasInputTextField(home, "Date");
		assertNotGenerated(dt);
		assertHasNoFieldValue(dt);
		assertEquals(((EXSDDataType) dt.getType()).getDefinition().getURI(), BuiltinDataTypes.TYPE_DATETIME);

		InputTextField email = assertHasInputTextField(home, "Email");
		assertNotGenerated(email);
		assertHasNoFieldValue(email);
		assertEquals(((EXSDDataType) email.getType()).getDefinition().getURI(), BuiltinDataTypes.TYPE_EMAIL);

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

			BuiltinOperation show = assertHasBuiltinOperation(annotation, "show");
			assertGenerated(show);
			BuiltinOperation hide = assertHasBuiltinOperation(annotation, "hide");
			assertGenerated(hide);

			// and a fieldValue
			assertGenerated(assertHasFieldValue(annotation));
		}

		InputTextField integer = assertHasInputTextField(home, "Integer");
		{
			Label annotation = assertHasLabel(integer, "Warning");

			BuiltinOperation show = assertHasBuiltinOperation(annotation, "show");
			assertGenerated(show);
			BuiltinOperation hide = assertHasBuiltinOperation(annotation, "hide");
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
			ActivityOperation validate = assertHasActivityOperation(email, "validate");
			assertGenerated(validate);
		}

		InputTextField integer = assertHasInputTextField(home, "Integer");
		{
			ActivityOperation validate = assertHasActivityOperation(integer, "validate");
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
			ActivityOperation validate = assertHasActivityOperation(def, "validate");
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
		ActivityOperation validate = assertHasActivityOperation(integer, "validate");

		Event onEdit = integer.getOnChange();
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
		Value fieldValue = assertHasFieldValue(integer);
		ActivityOperation validate = assertHasActivityOperation(integer, "validate");

		Label annotation = assertHasLabel(integer, "Warning");
		BuiltinOperation show = assertHasBuiltinOperation(annotation, "show");
		BuiltinOperation hide = assertHasBuiltinOperation(annotation, "hide");
		BuiltinOperation update = assertHasBuiltinOperation(annotation, "update");

		// target
		InputTextField email2 = assertHasInputTextField(home, "Email 2");
		Value fieldValue2 = assertHasFieldValue(email2);

		// now we can check the contents
		StartNode start = assertHasStartNode(validate);
		DecisionNode check = assertHasDecisionNode(validate, "can cast?");
		assertHasExecutionEdge(validate, start, check);

		CastNode cast = assertHasCastNode(validate);
		
		// cast <- ExternalValue <- fieldValue
		{
			assertEquals(1, cast.getInFlows().size());
			ExternalValue ev = (ExternalValue) cast.getInFlows().get(0).getFrom();
			assertEquals(fieldValue, ev.getValue());
		}
		
		// cast -> ExternalValue -> fieldValue2
		{
			assertEquals(2 /* two outgoing edges */, cast.getOutFlows().size());
			// we need to find the correct one
			ExternalValue ev = null;
			for (DataFlowEdge edge : cast.getOutFlows()) {
				if (edge.getTo() instanceof ExternalValue) {
					ev = (ExternalValue) edge.getTo();
				}
			}
			assertNotNull(ev);
			assertEquals(fieldValue2, ev.getValue());
		}
		
		// cast -> check
		assertHasDataFlowEdge(validate, cast, check);

		// 'y' runs 'hide'
		OperationCallNode callHide = assertHasOperationCallNode(validate, "call hide");
		assertHasRunAction(validate, callHide, hide);
		assertHasExecutionEdge(validate, check, callHide, "yes");

		// 'n' first sets the value of the label
		OperationCallNode callUpdate = assertHasOperationCallNode(validate, "update label");
		ECARule runUpdate = assertHasRunAction(validate, callUpdate, update);
		assertHasExecutionEdge(validate, check, callUpdate, "no");

		// with a parmameter of the exception
		Value warning = assertHasValue(validate, "failure message");
		assertTrue(warning.isReadOnly());
		assertEquals("Warning: Invalid input.", warning.getDefaultValue());
		assertHasParameter(validate, warning, runUpdate);

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
	 * All text fields should have a 'can cast?' Function created.
	 *
	 * @throws Exception
	 */
	public void testCreatesCanCastCondition() throws Exception {

		root = loadAndInfer(InputTextFieldDataTypeSync.class);
		Frame home = assertHasFrame(root, "Home");

		InputTextField email = assertHasInputTextField(home, "Email 2");
		{
			BuiltinProperty canCast = assertHasBuiltinProperty(email, "can cast?");
			assertGenerated(canCast);
		}

		InputTextField integer = assertHasInputTextField(home, "Integer");
		{
			BuiltinProperty canCast = assertHasBuiltinProperty(integer, "can cast?");
			assertGenerated(canCast);
		}

		InputTextField def = assertHasInputTextField(home, "Default");
		{
			BuiltinProperty canCast = assertHasBuiltinProperty(def, "can cast?");
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
		Value emailValue = assertHasFieldValue(email);
		InputTextField integer = assertHasInputTextField(home, "Integer");

		BuiltinProperty canCast = assertHasBuiltinProperty(integer, "can cast?");

		Event onEdit = email.getOnChange();
		Operation update = assertHasOperation(integer, "update");

		ECARule run = assertHasRunAction(root, onEdit, update);
		assertGenerated(run);

		// should be an incoming Function edge
		SimpleCondition edge = assertHasSimpleCondition(root, canCast, run);

		// and the edge should have the current Email.Value as a parameter
		assertHasParameter(root, emailValue, edge);

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
		Value intValue = assertHasFieldValue(integer);
		InputTextField email = assertHasInputTextField(home, "Email 2");

		BuiltinProperty canCast = assertHasBuiltinProperty(email, "can cast?");

		Event onEdit = integer.getOnChange();
		Operation update = assertHasOperation(email, "update");

		ECARule run = assertHasRunAction(root, onEdit, update);
		assertGenerated(run);

		// should be an incoming Function edge
		SimpleCondition edge = assertHasSimpleCondition(root, canCast, run);

		// and the edge should have the current Email.Value as a parameter
		assertHasParameter(root, intValue, edge);

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

		Event onAccess = integer.getOnAccess();
		Operation init = assertHasOperation(integer, "init");
		Value emailValue = assertHasFieldValue(email);

		// Integer.onInit should call Integer.update(Email.fieldValue)
		ECARule run = assertHasRunAction(root, onAccess, init);
		assertHasParameter(root, emailValue, run);

		// now make sure that the Function is connected
		BuiltinProperty canCast = assertHasBuiltinProperty(integer, "can cast?");

		SimpleCondition edge = assertHasSimpleCondition(root, canCast, run);
		assertHasParameter(root, emailValue, edge);

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

		Event onAccess = integer.getOnAccess();
		Operation init = assertHasOperation(integer, "init");

		// Integer.onInit should call Integer.update(Email.fieldValue)
		ECARule runInit = assertHasRunAction(root, onAccess, init);

		// validate operation is also called
		ActivityOperation validate = assertHasActivityOperation(integer, "validate");
		ECARule runValidate = assertHasRunAction(root, onAccess, validate);

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
		Event onAccess = integer.getOnAccess();

		// validate operation is also called
		ActivityOperation validate = assertHasActivityOperation(integer, "validate");
		ECARule runValidate = assertHasRunAction(root, onAccess, validate);

		// the 'fieldValue is set' condition
		Function isSet = assertHasFunction(integer, "fieldValue is set");
		assertHasSimpleCondition(root, isSet, runValidate);

	}

}
