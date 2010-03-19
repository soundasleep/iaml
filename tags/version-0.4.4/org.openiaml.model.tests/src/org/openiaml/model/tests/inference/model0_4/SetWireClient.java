/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.Set;

import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionCondition;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.RunAction;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.tests.inference.InferenceTestCase;
import org.openiaml.model.tests.inference.SessionSyncWires;

/**
 * Inference of SetWires.
 *
 * @author jmwright
 *
 */
public class SetWireClient extends InferenceTestCase {

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(SetWireClient.class);

		Frame page = assertHasFrame(root, "Home");
		assertNotGenerated(page);

		InputTextField source = assertHasInputTextField(page, "source");
		assertNotGenerated(source);
		InputTextField target = assertHasInputTextField(page, "target");
		assertNotGenerated(target);

		Set<Wire> wires = assertHasWiresFromTo(1, root, source, target);
		SetWire set = (SetWire) wires.iterator().next();
		assertNotGenerated(set);

		// there should be no events in either source or target
		assertHasNone(source, "iaml:eventTriggers");
		assertHasNone(source, "iaml:operations");
		assertHasNone(target, "iaml:eventTriggers");
		assertHasNone(target, "iaml:operations");
	}

	/**
	 * There should be elements generated from source to target.
	 *
	 * @throws Exception
	 */
	public void testSourceToTarget() throws Exception {
		root = loadAndInfer(SetWireClient.class);

		Frame page = assertHasFrame(root, "Home");
		InputTextField source = assertHasInputTextField(page, "source");
		InputTextField target = assertHasInputTextField(page, "target");

		// there should be an 'edit' event in source
		EventTrigger edit = source.getOnEdit();
		assertGenerated(edit);

		Property value = assertHasProperty(source, "fieldValue");
		assertGenerated(value);

		// and an 'update' event in target
		CompositeOperation update = assertHasCompositeOperation(target, "update");
		assertGenerated(update);

		// connected with a run wire
		RunAction run = assertHasRunAction(page, edit, update, "run");
		assertGenerated(run);

		// which has a parameter
		ParameterEdge param = assertHasParameterEdge(page, value, run);
		assertGenerated(param);
	}

	/**
	 * Target should not have elements generated to source.
	 *
	 * @throws Exception
	 */
	public void testTargetToSource() throws Exception {
		root = loadAndInfer(SetWireClient.class);

		Frame page = assertHasFrame(root, "Home");
		InputTextField source = assertHasInputTextField(page, "source");
		InputTextField target = assertHasInputTextField(page, "target");

		// there should be an 'edit' event in target
		EventTrigger edit = target.getOnEdit();
		assertGenerated(edit);

		Property value = assertHasProperty(target, "fieldValue");
		assertGenerated(value);

		// and an 'update' event in target
		CompositeOperation update = assertHasCompositeOperation(source, "update");
		assertGenerated(update);

		// but NOT connected with a run wire
		assertHasNoWiresFromTo(page, edit, update);
	}

	/**
	 * There should be elements generated from source to target, in
	 * particular the access/init pair.
	 *
	 * @throws Exception
	 */
	public void testSourceToTargetInit() throws Exception {
		root = loadAndInfer(SetWireClient.class);

		Frame page = assertHasFrame(root, "Home");
		InputTextField source = assertHasInputTextField(page, "source");
		InputTextField target = assertHasInputTextField(page, "target");

		// there should be an 'edit' event in target
		EventTrigger access = target.getOnAccess();
		assertGenerated(access);

		Property value = assertHasProperty(source, "fieldValue");
		assertGenerated(value);

		// and an 'update' event in target
		CompositeOperation init = assertHasCompositeOperation(target, "init");
		assertGenerated(init);

		// connected with a run wire
		RunAction run = assertHasRunAction(page, access, init, "run");
		assertGenerated(run);

		// which has a parameter
		ParameterEdge param = assertHasParameterEdge(page, value, run);
		assertGenerated(param);

		// but the parameter of 'target' is not connected to the run wire
		Property value2 = assertHasProperty(target, "fieldValue");
		assertGenerated(value2);

		assertHasNoWiresFromTo(page, value2, run);
	}

	/**
	 * Like SyncWire, the source.access -> source.init should only be called if the
	 * target value is actually set.
	 *
	 * @see SessionSyncWires#testSessionParamterAddsCheckCondition()
	 * @throws Exception
	 */
	public void testSetWireCondition() throws Exception {
		root = loadAndInfer(SetWireClient.class);

		Frame page = assertHasFrame(root, "Home");
		InputTextField source = assertHasInputTextField(page, "source");
		InputTextField target = assertHasInputTextField(page, "target");

		// there should be an 'edit' event in target
		EventTrigger access = target.getOnAccess();
		CompositeOperation init = assertHasCompositeOperation(target, "init");
		RunAction run = assertHasRunAction(page, access, init, "run");

		// newly created condition
		CompositeCondition cond = assertHasCompositeCondition(source, "fieldValue is set");
		assertGenerated(cond);

		ConditionEdge cw = assertHasConditionEdge(root, cond, run);
		assertGenerated(cw);
	}

	/**
	 * Tests the contents of Page.target.[fieldValue is set]
	 *
	 * @throws Exception
	 */
	public void testSetWireConditionContents() throws Exception {
		root = loadAndInfer(SetWireClient.class);

		Frame page = assertHasFrame(root, "Home");
		InputTextField source = assertHasInputTextField(page, "source");
		Property value = assertHasProperty(source, "fieldValue");
		CompositeCondition cond = assertHasCompositeCondition(source, "fieldValue is set");

		// check contents of condition
		StartNode start = assertHasStartNode(cond);
		FinishNode finish = assertHasFinishNode(cond);
		CancelNode cancel = assertHasCancelNode(cond);

		DecisionCondition check = assertHasDecisionCondition(cond, "is set?");

		assertHasExecutionEdge(cond, start, check);
		assertHasExecutionEdge(cond, check, finish);
		assertHasExecutionEdge(cond, check, cancel);

		assertHasDataFlowEdge(cond, value, check);
	}

}
