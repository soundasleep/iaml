/**
 *
 */
package org.openiaml.model.tests.inference.model0_4_4;

import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ExecutionEdgesSource;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.operations.CastNode;
import org.openiaml.model.model.operations.DecisionOperation;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.OperationCallNode;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Label;
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

		EventTrigger onEdit = integer.getOnEdit();
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
		assertHasStartNode(validate);
		DecisionOperation check = assertHasDecisionOperation(validate, "can cast?");
		
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
		assertEquals("Invalid input.", warning.getValue());
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


}
