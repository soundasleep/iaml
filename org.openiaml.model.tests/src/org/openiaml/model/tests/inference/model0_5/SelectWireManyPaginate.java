/**
 *
 */
package org.openiaml.model.tests.inference.model0_5;

import org.openiaml.model.model.ActionEdge;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.operations.Arithmetic;
import org.openiaml.model.model.operations.ArithmeticOperationTypes;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 *
 * @author jmwright
 */
public class SelectWireManyPaginate extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(SelectWireManyPaginate.class);
	}

	public void testInitial() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		assertNotGenerated(home);

		DomainIterator instance = assertHasDomainIterator(home, "view news");
		assertNotGenerated(instance);

		InputForm form = assertHasInputForm(home, "view news");
		assertNotGenerated(form);

		DomainSchema object = assertHasDomainSchema(root, "News Item");
		assertNotGenerated(object);

		assertEquals(5, instance.getLimit());

		SetWire wire = assertHasSetWire(root, instance, form);
		assertNotGenerated(wire);

	}

	/**
	 * The Form is populated with labels.
	 *
	 * @throws Exception
	 */
	public void testLabelsCreated() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "view news");

		Label t1 = assertHasLabel(form, "title");
		Label t2 = assertHasLabel(form, "content");

		assertGenerated(t1);
		assertGenerated(t2);

		// no text fields
		assertHasNoInputTextField(form, "title");
		assertHasNoInputTextField(form, "content");

	}

	/**
	 * Test that all of the elements in the DomainObjectInstance are generated.
	 *
	 * @throws Exception
	 */
	public void testInstanceCreated() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		DomainIterator instance = assertHasDomainIterator(home, "view news");

		assertNotNull(instance.getOnIterate());
		assertNotNull(instance.getPrevious());
		assertNotNull(instance.getNext());
		assertNotNull(instance.getReset());
		assertNotNull(instance.getSkip());
		assertNotNull(instance.getJump());
		assertNotNull(instance.getHasPrevious());
		assertNotNull(instance.getHasNext());
		assertNotNull(instance.getEmpty());
		assertNotNull(instance.getResults());

	}

	/**
	 * The InputForm should have navigation buttons created.
	 *
	 * @throws Exception
	 */
	public void testFormHasNavigationButtons() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "view news");

		Button next = assertHasButton(form, "Next");
		Button previous = assertHasButton(form, "Previous");
		Button first = assertHasButton(form, "First");
		Button last = assertHasButton(form, "Last");

		assertGenerated(next);
		assertGenerated(previous);
		assertGenerated(first);
		assertGenerated(last);

		// and a Label called 'Results'
		Label labelResults = assertHasLabel(form, "Results");
		assertGenerated(labelResults);

	}

	/**
	 * Test the Next button.
	 *
	 * @throws Exception
	 */
	public void testButtonNext() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "view news");

		DomainIterator instance = assertHasDomainIterator(home, "view news");

		Operation inext = instance.getNext();
		Condition hasNext = instance.getHasNext();

		Button next = assertHasButton(form, "Next");

		EventTrigger onClick = next.getOnClick();
		assertGenerated(onClick);

		ActionEdge run = assertHasRunAction(root, onClick, inext);
		assertGenerated(run);

		assertGenerated(assertHasConditionEdge(root, hasNext, run));

	}

	/**
	 * Test the Previous button.
	 *
	 * @throws Exception
	 */
	public void testButtonPrevious() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "view news");

		DomainIterator instance = assertHasDomainIterator(home, "view news");
		Operation iprevious = instance.getPrevious();
		Condition hasPrevious = instance.getHasPrevious();

		Button previous = assertHasButton(form, "Previous");

		EventTrigger onClick = previous.getOnClick();
		assertGenerated(onClick);

		ActionEdge run = assertHasRunAction(root, onClick, iprevious);
		assertGenerated(run);

		assertGenerated(assertHasConditionEdge(root, hasPrevious, run));

	}

	/**
	 * Test the First button.
	 *
	 * @throws Exception
	 */
	public void testButtonFirst() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "view news");

		DomainIterator instance = assertHasDomainIterator(home, "view news");
		Operation ireset = instance.getReset();

		// we need to reverse 'empty' into condition 'not empty'
		CompositeCondition notEmpty = assertHasCompositeCondition(instance, "not empty");
		assertGenerated(notEmpty);

		Button first = assertHasButton(form, "First");

		EventTrigger onClick = first.getOnClick();
		assertGenerated(onClick);

		ActionEdge run = assertHasRunAction(root, onClick, ireset);
		assertGenerated(run);

		assertGenerated(assertHasConditionEdge(root, notEmpty, run));

	}

	/**
	 * Test the Last button.
	 *
	 * @throws Exception
	 */
	public void testButtonLast() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "view news");

		DomainIterator instance = assertHasDomainIterator(home, "view news");
		Operation ijump = instance.getJump();

		// we need to reverse 'empty' into condition 'not empty'
		CompositeCondition notEmpty = assertHasCompositeCondition(instance, "not empty");
		assertGenerated(notEmpty);

		// we need to convert 'results' into a jump target (results-1)
		Button last = assertHasButton(form, "Last");

		EventTrigger onClick = last.getOnClick();
		assertGenerated(onClick);

		ActionEdge run = assertHasRunAction(root, onClick, ijump);
		assertGenerated(run);

		assertGenerated(assertHasConditionEdge(root, notEmpty, run));

		// has a parameter: the position to jump to
		Property target = assertHasProperty(last, "target");
		assertGenerated(target);

		ParameterEdge param = assertHasParameterEdge(root, target, run);
		assertGenerated(param);

	}

	/**
	 * When the 'Last' button is pressed, it must first update the
	 * target property 'target'.
	 *
	 * @throws Exception
	 */
	public void testLastButtonUpdatesTargetProperty() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "view news");
		DomainIterator instance = assertHasDomainIterator(home, "view news");
		Operation ijump = instance.getJump();

		// we need to reverse 'empty' into condition 'not empty'
		CompositeCondition notEmpty = assertHasCompositeCondition(instance, "not empty");
		assertGenerated(notEmpty);

		// we need to convert 'results' into a jump target (results-1)
		Button last = assertHasButton(form, "Last");

		EventTrigger onClick = last.getOnClick();

		CompositeOperation op = assertHasCompositeOperation(last, "update target");
		assertGenerated(op);

		ActionEdge update = assertHasRunAction(root, onClick, op);

		// it must have a higher priority than the action to the instance
		ActionEdge run = assertHasRunAction(root, onClick, ijump);
		assertGreater(run.getPriority(), update.getPriority());

	}

	/**
	 * Test the contents of 'update target' operation.
	 * @throws Exception
	 */
	public void testContentsOfUpdateTarget() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "view news");
		DomainIterator instance = assertHasDomainIterator(home, "view news");
		Property results = instance.getResults();

		Button last = assertHasButton(form, "Last");
		CompositeOperation op = assertHasCompositeOperation(last, "update target");
		Property target = assertHasProperty(last, "target");

		StartNode start = assertHasStartNode(op);
		FinishNode finish = assertHasFinishNode(op);

		PrimitiveOperation set = assertHasPrimitiveOperation(op, "set");

		assertGenerated(assertHasExecutionEdge(op, start, set));
		assertGenerated(assertHasExecutionEdge(op, set, finish));

		Arithmetic arith = assertHasArithmetic(op);
		StaticValue one = assertHasStaticValue(op, "one");
		assertGenerated(assertHasDataFlowEdge(op, one, arith));
		assertEquals(arith.getOperationType(), ArithmeticOperationTypes.SUBTRACT);

		assertGenerated(assertHasDataFlowEdge(op, results, arith));
		assertGenerated(assertHasDataFlowEdge(op, arith, set));
		assertGenerated(assertHasDataFlowEdge(op, set, target));


	}

	/**
	 * Test the contents of the generated "not empty" condition.
	 *
	 * @throws Exception
	 */
	public void testContentsOfNotEmpty() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		DomainIterator instance = assertHasDomainIterator(home, "view news");
		Condition empty = instance.getEmpty();

		// we need to reverse 'empty' into condition 'not empty'
		CompositeCondition notEmpty = assertHasCompositeCondition(instance, "not empty");

		StartNode start = assertHasStartNode(notEmpty);
		FinishNode finish = assertHasFinishNode(notEmpty);
		CancelNode cancel = assertHasCancelNode(notEmpty);

		DecisionNode decision = assertHasDecisionNode(notEmpty, "true?");

		assertGenerated(assertHasConditionEdge(root, empty, decision));

		assertHasExecutionEdge(notEmpty, start, decision);

		// not empty = pass
		assertHasExecutionEdge(notEmpty, decision, finish, "n");

		// empty = fail
		assertHasExecutionEdge(notEmpty, decision, cancel, "y");

	}

	/**
	 * The 'results' property should be Set to the 'Results'
	 * label.
	 *
	 * @throws Exception
	 */
	public void testResultsLabel() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "view news");
		DomainIterator instance = assertHasDomainIterator(home, "view news");
		Property results = instance.getResults();
		Label labelResults = assertHasLabel(form, "Results");

		// onChange updates the Label
		EventTrigger onChange = instance.getOnChange();
		Operation update = assertHasOperation(labelResults, "update");

		ActionEdge run = assertHasRunAction(root, onChange, update);

		// with the given parameter
		assertGenerated(assertHasParameterEdge(root, results, run));

	}

	/**
	 * Attribute.onChange needs to call Label.update().
	 *
	 * @throws Exception
	 */
	public void testAttributeCallsLabelUpdate() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "view news");
		DomainIterator instance = assertHasDomainIterator(home, "view news");

		DomainAttributeInstance a1 = assertHasDomainAttributeInstance(instance, "title");
		assertGenerated(a1);

		Label t1 = assertHasLabel(form, "title");
		assertGenerated(t1);

		// connected by SetWire
		assertGenerated(assertHasSetWire(root, a1, t1));

		EventTrigger onChange = a1.getOnChange();
		assertGenerated(onChange);

		Operation update = assertHasOperation(t1, "update");
		assertGenerated(update);

		ActionEdge run = assertHasRunAction(root, onChange, update);
		assertGenerated(run);

		Property a1value = assertHasFieldValue(a1);
		assertGenerated(a1value);

		assertGenerated(assertHasParameterEdge(root, a1value, run));

	}

}
