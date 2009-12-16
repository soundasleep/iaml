/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.Set;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

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

		Page page = assertHasPage(root, "Home");
		assertNotGenerated(page);
		
		InputTextField source = assertHasInputTextField(page, "source");
		assertNotGenerated(source);
		InputTextField target = assertHasInputTextField(page, "target");
		assertNotGenerated(target);
		
		Set<WireEdge> wires = assertHasWiresFromTo(1, root, source, target);
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

		Page page = assertHasPage(root, "Home");
		InputTextField source = assertHasInputTextField(page, "source");
		InputTextField target = assertHasInputTextField(page, "target");

		// there should be an 'edit' event in source
		EventTrigger edit = assertHasEventTrigger(source, "edit");
		assertGenerated(edit);
		
		ApplicationElementProperty value = assertHasApplicationElementProperty(source, "fieldValue");
		assertGenerated(value);
		
		// and an 'update' event in target
		CompositeOperation update = assertHasCompositeOperation(target, "update");
		assertGenerated(update);
	
		// connected with a run wire
		Set<WireEdge> w1 = assertHasWiresFromTo(1, page, edit, update);
		RunInstanceWire run = (RunInstanceWire) w1.iterator().next();
		assertEquals("run", run.getName());
		assertGenerated(run);
		
		// which has a parameter
		Set<WireEdge> w2 = assertHasWiresFromTo(1, page, value, run);
		ParameterWire param = (ParameterWire) w2.iterator().next();
		assertGenerated(param);
	}

	/**
	 * Target should not have elements generated to source.
	 * 
	 * @throws Exception
	 */
	public void testTargetToSource() throws Exception {
		root = loadAndInfer(SetWireClient.class);

		Page page = assertHasPage(root, "Home");
		InputTextField source = assertHasInputTextField(page, "source");
		InputTextField target = assertHasInputTextField(page, "target");

		// there should be an 'edit' event in target
		EventTrigger edit = assertHasEventTrigger(target, "edit");
		assertGenerated(edit);
		
		ApplicationElementProperty value = assertHasApplicationElementProperty(target, "fieldValue");
		assertGenerated(value);
		
		// and an 'update' event in target
		CompositeOperation update = assertHasCompositeOperation(source, "update");
		assertGenerated(update);
		
		// but NOT connected with a run wire
		assertHasNoWiresFromTo(page, edit, update);
	}

}
