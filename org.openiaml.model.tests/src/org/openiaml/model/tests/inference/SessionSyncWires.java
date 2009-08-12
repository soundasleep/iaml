/**
 *
 */
package org.openiaml.model.tests.inference;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests sessions: sync wires across session boundaries.
 *
 * In particular, the session should create an "init" event, connected to
 * the "update" operation, from the SyncWire that was connected.
 *
 * @author jmwright
 *
 */
public class SessionSyncWires extends InferenceTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(SessionSyncWires.class, true);
	}

	public void testInference() throws Exception {
		// initial elements
		Page outside = (Page) queryOne(root, "//iaml:children[iaml:name='outside']");
		Page inside = (Page) queryOne(root, "//iaml:children[iaml:name='inside']");
		assertNotSame(outside, inside);
		InputTextField field1 = assertHasInputTextField(outside, "target");
		InputTextField field2 = assertHasInputTextField(inside, "target");
		assertNotSame(field1, field2);
		Session session = (Session) queryOne(root, "//iaml:sessions");

		// generated events and operations
		// all part of SyncWire elements generation
		EventTrigger edit = assertHasEventTrigger(field1, "edit");
		Operation update = assertHasOperation(field2, "update");
		RunInstanceWire rw = (RunInstanceWire) getWireFromTo(root, edit, update);

		ApplicationElementProperty fieldValue = assertHasApplicationElementProperty(field1, "fieldValue");
		ParameterWire pw = (ParameterWire) getWireFromTo(root, fieldValue, rw);
		assertNotNull(pw);

		// session should have an 'init' event
		EventTrigger init = assertHasEventTrigger(session, "init");

		// it should be connected to 'update'
		RunInstanceWire rw2 = (RunInstanceWire) getWireFromTo(root, init, update);

		// there should only be one RunWire out of 'init'
		assertEquals("There should only be one out edge from init", 1, init.getOutEdges().size());

		// with the source fieldvalue as a parameter
		ParameterWire pw2 = (ParameterWire) getWireFromTo(session, fieldValue, rw2);
		assertNotNull(pw2);

	}

}
