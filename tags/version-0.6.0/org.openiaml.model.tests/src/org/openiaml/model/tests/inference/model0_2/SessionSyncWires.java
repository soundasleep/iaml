/**
 *
 */
package org.openiaml.model.tests.inference.model0_2;

import org.openiaml.model.model.BuiltinProperty;
import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.SimpleCondition;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.tests.inference.InferenceTestCase;
import org.openiaml.model.tests.inference.model0_4.SetWireClient;

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

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(SessionSyncWires.class, true);
	}

	public void testInference() throws Exception {
		// initial elements
		Frame outside = assertHasFrame(root, "outside");
		Session session = assertHasSession(root, "session");
		Frame inside = assertHasFrame(session, "inside");
		assertNotSame(outside, inside);
		InputTextField field1 = assertHasInputTextField(outside, "target");
		InputTextField field2 = assertHasInputTextField(inside, "target");
		assertNotSame(field1, field2);

		// generated events and operations
		// all part of SyncWire elements generation
		Event edit = field1.getOnChange();
		Operation update = assertHasOperation(field2, "update");
		ECARule rw = assertHasRunAction(root, edit, update);

		Value fieldValue = assertHasFieldValue(field1);
		assertGenerated(getParameterFromTo(root, fieldValue, rw));

		// session should have an 'init' event
		Event init = session.getOnInit();

		// it should be connected to 'update'
		ECARule rw2 = assertHasRunAction(root, init, update);
		assertGenerated(rw2);

	}

	/**
	 * In version 0.4.1, all access->init operations that are based
	 * on a session variable should also have a Function to check that
	 * the variable has been set.
	 *
	 * @see SetWireClient#testSetWireCondition()
	 * @throws Exception
	 */
	public void testSessionParamterAddsCheckCondition() throws Exception {
		Frame outside = assertHasFrame(root, "outside");
		Session session = assertHasSession(root, "session");
		Frame inside = assertHasFrame(session, "inside");
		InputTextField field1 = assertHasInputTextField(outside, "target");
		InputTextField field2 = assertHasInputTextField(inside, "target");

		Event access = field1.getOnAccess();
		assertGenerated(access);
		Operation init = assertHasOperation(field1, "init");
		assertGenerated(init);

		Value value = assertHasFieldValue(field2);
		assertGenerated(value);

		ECARule run = assertHasRunAction(field1, access, init, "run");
		assertGenerated(run);

		Parameter param = assertHasParameter(field1, value, run);
		assertGenerated(param);

		// newly created condition
		BuiltinProperty cond = assertHasBuiltinProperty(field2, "fieldValue is set");
		assertGenerated(cond);

		SimpleCondition cw = assertHasSimpleCondition(root, cond, run);
		assertGenerated(cw);

	}

}
