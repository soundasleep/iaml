/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.Set;

import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.PrimitiveCondition;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.SimpleCondition;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.tests.inference.InferenceTestCase;
import org.openiaml.model.tests.inference.model0_2.SessionSyncWires;

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
		Event edit = source.getOnChange();
		assertGenerated(edit);

		Value value = assertHasFieldValue(source);
		assertGenerated(value);

		// and an 'update' event in target
		PrimitiveOperation update = assertHasPrimitiveOperation(target, "update");
		assertGenerated(update);

		// connected with a run wire
		ECARule run = assertHasRunAction(page, edit, update, "run");
		assertGenerated(run);

		// which has a parameter
		Parameter param = assertHasParameter(page, value, run);
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
		Event edit = target.getOnChange();
		assertGenerated(edit);

		Value value = assertHasFieldValue(target);
		assertGenerated(value);

		// and an 'update' event in target
		PrimitiveOperation update = assertHasPrimitiveOperation(source, "update");
		assertGenerated(update);
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
		Event access = target.getOnAccess();
		assertGenerated(access);

		Value value = assertHasFieldValue(source);
		assertGenerated(value);

		// and an 'update' event in target
		PrimitiveOperation init = assertHasPrimitiveOperation(target, "init");
		assertGenerated(init);

		// connected with a run wire
		ECARule run = assertHasRunAction(page, access, init, "run");
		assertGenerated(run);

		// which has a parameter
		Parameter param = assertHasParameter(page, value, run);
		assertGenerated(param);

		// but the ActivityParameter of 'target' is not connected to the run wire
		Value value2 = assertHasFieldValue(target);
		assertGenerated(value2);

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
		Event access = target.getOnAccess();
		PrimitiveOperation init = assertHasPrimitiveOperation(target, "init");
		ECARule run = assertHasRunAction(page, access, init, "run");

		// newly created condition
		PrimitiveCondition cond = assertHasPrimitiveCondition(source, "fieldValue is set");
		assertGenerated(cond);

		SimpleCondition cw = assertHasSimpleCondition(root, cond, run);
		assertGenerated(cw);
	}

}
