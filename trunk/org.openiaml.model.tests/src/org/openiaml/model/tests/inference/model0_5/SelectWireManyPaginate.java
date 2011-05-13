/**
 *
 */
package org.openiaml.model.tests.inference.model0_5;

import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.Function;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.domain.DomainAttributeInstance;
import org.openiaml.model.model.domain.DomainInstance;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.operations.ActivityFunction;
import org.openiaml.model.model.operations.ActivityOperation;
import org.openiaml.model.model.operations.Arithmetic;
import org.openiaml.model.model.operations.ArithmeticOperationTypes;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DataFlowEdge;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.ExternalValue;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.SetNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.Label;
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
		Function hasNext = instance.getHasNext();

		Button next = assertHasButton(form, "Next");

		Event onClick = next.getOnClick();
		assertGenerated(onClick);

		ECARule run = assertHasRunAction(root, onClick, inext);
		assertGenerated(run);

		assertGenerated(assertHasSimpleCondition(root, hasNext, run));

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
		Function hasPrevious = instance.getHasPrevious();

		Button previous = assertHasButton(form, "Previous");

		Event onClick = previous.getOnClick();
		assertGenerated(onClick);

		ECARule run = assertHasRunAction(root, onClick, iprevious);
		assertGenerated(run);

		assertGenerated(assertHasSimpleCondition(root, hasPrevious, run));

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

		// we need to reverse 'empty' into Function 'not empty'
		ActivityFunction notEmpty = assertHasActivityFunction(instance, "not empty");
		assertGenerated(notEmpty);

		Button first = assertHasButton(form, "First");

		Event onClick = first.getOnClick();
		assertGenerated(onClick);

		ECARule run = assertHasRunAction(root, onClick, ireset);
		assertGenerated(run);

		assertGenerated(assertHasSimpleCondition(root, notEmpty, run));

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

		// we need to reverse 'empty' into Function 'not empty'
		ActivityFunction notEmpty = assertHasActivityFunction(instance, "not empty");
		assertGenerated(notEmpty);

		// we need to convert 'results' into a jump target (results-1)
		Button last = assertHasButton(form, "Last");

		Event onClick = last.getOnClick();
		assertGenerated(onClick);

		ECARule run = assertHasRunAction(root, onClick, ijump);
		assertGenerated(run);

		assertGenerated(assertHasSimpleCondition(root, notEmpty, run));

		// has a parameter: the position to jump to
		Value target = assertHasValue(last, "target");
		assertGenerated(target);

		Parameter param = assertHasParameter(root, target, run);
		assertGenerated(param);

	}

	/**
	 * When the 'Last' button is pressed, it must first update the
	 * target Value 'target'.
	 *
	 * @throws Exception
	 */
	public void testLastButtonUpdatesTargetProperty() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "view news");
		DomainIterator instance = assertHasDomainIterator(home, "view news");
		Operation ijump = instance.getJump();

		// we need to reverse 'empty' into Function 'not empty'
		ActivityFunction notEmpty = assertHasActivityFunction(instance, "not empty");
		assertGenerated(notEmpty);

		// we need to convert 'results' into a jump target (results-1)
		Button last = assertHasButton(form, "Last");

		Event onClick = last.getOnClick();

		ActivityOperation op = assertHasActivityOperation(last, "update target");
		assertGenerated(op);

		ECARule update = assertHasRunAction(root, onClick, op);

		// it must have a higher priority than the action to the instance
		ECARule run = assertHasRunAction(root, onClick, ijump);
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
		Value results = instance.getResults();

		Button last = assertHasButton(form, "Last");
		ActivityOperation op = assertHasActivityOperation(last, "update target");
		Value target = assertHasValue(last, "target");

		StartNode start = assertHasStartNode(op);
		FinishNode finish = assertHasFinishNode(op);

		SetNode set = assertHasSetNode(op);

		assertGenerated(assertHasExecutionEdge(op, start, set));
		assertGenerated(assertHasExecutionEdge(op, set, finish));

		Arithmetic arith = assertHasArithmetic(op);
		Value one = assertHasValue(op, "one");
		assertTrue(one.isReadOnly());
		assertEquals(arith.getOperationType(), ArithmeticOperationTypes.SUBTRACT);

		// arith <- ExternalValue <- results
		// arith <- ExternalValue <- one
		{
			assertEquals(2, arith.getInFlows().size());
			boolean fromResults = false;
			boolean fromOne = false;
			for (DataFlowEdge ee : arith.getInFlows()) {
				assertInstanceOf(ExternalValue.class, ee.getFrom());
				ExternalValue ev = (ExternalValue) ee.getFrom();
				
				if (one.equals(ev.getExternalValueEdges().getValue())) {
					assertFalse(fromOne);
					fromOne = true;
				} else if (results.equals(ev.getExternalValueEdges().getValue())) {
					assertFalse(fromResults);
					fromResults = true;
				} else {
					fail("Unknown source: " + ev.getExternalValueEdges().getValue());
				}
			}
			assertTrue(fromOne);
			assertTrue(fromResults);
		}
		
		// arith -> ExternalValue -> set
		{
			assertEquals(1, arith.getOutFlows().size());
			assertEquals(set, arith.getOutFlows().get(0).getTo());
		}

		assertGenerated(assertHasDataFlowEdge(op, arith, set));
		
		// set -> ExternalValue -> target
		{
			assertEquals(1, set.getOutFlows().size());
			ExternalValue ev = (ExternalValue) set.getOutFlows().get(0).getTo();
			assertEquals(target, ev.getExternalValueEdges().getValue());
		}

	}

	/**
	 * Test the contents of the generated "not empty" condition.
	 *
	 * @throws Exception
	 */
	public void testContentsOfNotEmpty() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		DomainIterator instance = assertHasDomainIterator(home, "view news");
		Function empty = instance.getEmpty();

		// we need to reverse 'empty' into Function 'not empty'
		ActivityFunction notEmpty = assertHasActivityFunction(instance, "not empty");

		StartNode start = assertHasStartNode(notEmpty);
		FinishNode finish = assertHasFinishNode(notEmpty);
		CancelNode cancel = assertHasCancelNode(notEmpty);

		DecisionNode decision = assertHasDecisionNode(notEmpty, "true?");

		assertGenerated(assertHasSimpleCondition(root, empty, decision));

		assertHasExecutionEdge(notEmpty, start, decision);

		// not empty = pass
		assertHasExecutionEdge(notEmpty, decision, finish, "n");

		// empty = fail
		assertHasExecutionEdge(notEmpty, decision, cancel, "y");

	}

	/**
	 * The 'results' Value should be Set to the 'Results'
	 * label.
	 *
	 * @throws Exception
	 */
	public void testResultsLabel() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "view news");
		DomainIterator instance = assertHasDomainIterator(home, "view news");
		Value results = instance.getResults();
		Label labelResults = assertHasLabel(form, "Results");

		// onChange updates the Label
		Event onChange = instance.getOnChange();
		Operation update = assertHasOperation(labelResults, "update");

		ECARule run = assertHasRunAction(root, onChange, update);

		// with the given parameter
		assertGenerated(assertHasParameter(root, results, run));

	}

	/**
	 * Attribute.onChange needs to call Label.update().
	 *
	 * @throws Exception
	 */
	public void testAttributeCallsLabelUpdate() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm form = assertHasInputForm(home, "view news");
		DomainIterator iterator = assertHasDomainIterator(home, "view news");
		DomainInstance instance = iterator.getCurrentInstance();
		assertGenerated(instance);

		DomainAttributeInstance a1 = assertHasDomainAttributeInstance(instance, "title");
		assertGenerated(a1);

		Label t1 = assertHasLabel(form, "title");
		assertGenerated(t1);

		// connected by SetWire
		assertGenerated(assertHasSetWire(root, a1, t1));

		Event onChange = a1.getOnChange();
		assertGenerated(onChange);

		Operation update = assertHasOperation(t1, "update");
		assertGenerated(update);

		ECARule run = assertHasRunAction(root, onChange, update);
		assertGenerated(run);

		Value a1value = assertHasFieldValue(a1);
		assertGenerated(a1value);

		assertGenerated(assertHasParameter(root, a1value, run));

	}

}
