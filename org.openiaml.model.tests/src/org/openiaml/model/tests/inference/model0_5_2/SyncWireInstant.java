/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_2;

import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 *
 * @author jmwright
 */
public class SyncWireInstant extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(SyncWireInstant.class);
	}

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {

		Frame home = assertHasFrame(root, "Home");

		InputTextField normal1 = assertHasInputTextField(home, "normal 1");
		InputTextField normal2 = assertHasInputTextField(home, "normal 2");

		InputTextField instant1 = assertHasInputTextField(home, "instant 1");
		InputTextField instant2 = assertHasInputTextField(home, "instant 2");

		InputTextField mixed1 = assertHasInputTextField(home, "mixed 1");
		InputTextField mixed2 = assertHasInputTextField(home, "mixed 2");
		InputTextField mixed3 = assertHasInputTextField(home, "mixed 3");

		SyncWire s1 = assertHasSyncWire(root, normal1, normal2);
		assertFalse(s1.isExecuteOnInput());

		SyncWire s2 = assertHasSyncWire(root, instant1, instant2);
		assertTrue(s2.isExecuteOnInput());

		SyncWire s3a = assertHasSyncWire(root, mixed1, mixed2);
		assertTrue(s3a.isExecuteOnInput());

		SyncWire s3b = assertHasSyncWire(root, mixed2, mixed3);
		assertFalse(s3b.isExecuteOnInput());

	}

	/**
	 * Each of the text fields have currentInput and onInput.
	 *
	 * @throws Exception
	 */
	public void testInputTextFieldsHaveCurrentInput() throws Exception {

		Frame home = assertHasFrame(root, "Home");

		InputTextField normal1 = assertHasInputTextField(home, "normal 1");
		InputTextField normal2 = assertHasInputTextField(home, "normal 2");

		InputTextField instant1 = assertHasInputTextField(home, "instant 1");
		InputTextField instant2 = assertHasInputTextField(home, "instant 2");

		InputTextField mixed1 = assertHasInputTextField(home, "mixed 1");
		InputTextField mixed2 = assertHasInputTextField(home, "mixed 2");
		InputTextField mixed3 = assertHasInputTextField(home, "mixed 3");

		InputTextField[] fields = new InputTextField[] {
			normal1, normal2, instant1, instant2, mixed1, mixed2, mixed3
		};
		for (InputTextField f : fields) {
			assertNotGenerated(f);

			Value currentInput = assertHasCurrentInput(f);
			assertGenerated(currentInput);

			Event onInput = f.getOnInput();
			assertGenerated(onInput);
		}

	}

	/**
	 * Since instant1 is connected to instant2 by an instant sync wire,
	 * instant1.onInput will call instant2.update(currentInput).
	 *
	 * @throws Exception
	 */
	public void testInstantEventTriggers1() throws Exception {

		Frame home = assertHasFrame(root, "Home");

		InputTextField instant1 = assertHasInputTextField(home, "instant 1");
		InputTextField instant2 = assertHasInputTextField(home, "instant 2");

		Value currentInput = assertHasCurrentInput(instant1);

		Event onInput = instant1.getOnInput();
		assertGenerated(onInput);

		Operation update = assertHasOperation(instant2, "update");
		assertGenerated(update);

		// RunAction connecting the two
		ECARule run = assertHasRunAction(root, onInput, update, "run");
		assertGenerated(run);

		// with the currentInput as parameter
		Parameter param = assertHasParameter(root, currentInput, run);
		assertGenerated(param);

	}

	/**
	 * The opposite of {@link #testInstantEventTriggers1()}.
	 *
	 * @throws Exception
	 */
	public void testInstantEventTriggers2() throws Exception {

		Frame home = assertHasFrame(root, "Home");

		InputTextField instant1 = assertHasInputTextField(home, "instant 1");
		InputTextField instant2 = assertHasInputTextField(home, "instant 2");

		Value currentInput = assertHasCurrentInput(instant2);

		Event onInput = instant2.getOnInput();
		assertGenerated(onInput);

		Operation update = assertHasOperation(instant1, "update");
		assertGenerated(update);

		// RunAction connecting the two
		ECARule run = assertHasRunAction(root, onInput, update, "run");
		assertGenerated(run);

		// with the currentInput as parameter
		Parameter param = assertHasParameter(root, currentInput, run);
		assertGenerated(param);

		// NOT the fieldValue
		Value fieldValue = assertHasFieldValue(instant2);
		assertHasNoParameter(root, fieldValue, run);

	}

	/**
	 * Since instant1 is connected to instant2 by an instant sync wire,
	 * instant1.onChange will still call instant2.update.
	 *
	 * @throws Exception
	 */
	public void testInstantStillCallsOnChange() throws Exception {

		Frame home = assertHasFrame(root, "Home");

		InputTextField instant1 = assertHasInputTextField(home, "instant 1");
		InputTextField instant2 = assertHasInputTextField(home, "instant 2");

		Value fieldValue = assertHasFieldValue(instant1);

		Event onChange = instant1.getOnChange();
		assertGenerated(onChange);

		Operation update = assertHasOperation(instant2, "update");
		assertGenerated(update);

		// RunAction connecting the two
		ECARule run = assertHasRunAction(root, onChange, update, "run");
		assertGenerated(run);

		// with the currentInput as parameter
		Parameter param = assertHasParameter(root, fieldValue, run);
		assertGenerated(param);

		// NOT the currentInput
		Value currentInput = assertHasCurrentInput(instant1);
		assertHasNoParameter(root, currentInput, run);

	}

	/**
	 * A SyncWire that does not have the 'instant' Value set will not
	 * have the onInput event connected up.
	 *
	 * @throws Exception
	 */
	public void testNonInstantDoesntConnectOnInput() throws Exception {

		Frame home = assertHasFrame(root, "Home");

		InputTextField normal1 = assertHasInputTextField(home, "normal 1");
		InputTextField normal2 = assertHasInputTextField(home, "normal 2");

		{
			Event onInput = normal1.getOnInput();
			assertGenerated(onInput);

			Operation update = assertHasOperation(normal2, "update");
			assertGenerated(update);

			// RunAction connecting the two
			assertHasNoRunAction(root, onInput, update);
		}

		// or the other way around
		{
			Event onInput = normal2.getOnInput();
			assertGenerated(onInput);

			Operation update = assertHasOperation(normal1, "update");
			assertGenerated(update);

			// RunAction connecting the two
			assertHasNoRunAction(root, onInput, update);
		}

	}

	/**
	 * Mixed2 will instantly update Mixed1, but not Mixed3.
	 *
	 * @throws Exception
	 */
	public void testMixedSyncWires() throws Exception {

		Frame home = assertHasFrame(root, "Home");

		InputTextField mixed1 = assertHasInputTextField(home, "mixed 1");
		InputTextField mixed2 = assertHasInputTextField(home, "mixed 2");
		InputTextField mixed3 = assertHasInputTextField(home, "mixed 3");

		// mixed2.onInput -> mixed1.update
		{
			Value currentInput = assertHasCurrentInput(mixed2);
			Event onInput = mixed2.getOnInput();
			Operation update = assertHasOperation(mixed1, "update");

			// RunAction connecting the two
			ECARule run = assertHasRunAction(root, onInput, update, "run");

			// with the currentInput as parameter
			Parameter param = assertHasParameter(root, currentInput, run);
			assertGenerated(param);

			// NOT the fieldValue
			Value fieldValue = assertHasFieldValue(mixed2);
			assertHasNoParameter(root, fieldValue, run);
		}

		// mixed2.onInput !-> mixed1.update
		{
			Event onInput = mixed2.getOnInput();
			Operation update = assertHasOperation(mixed3, "update");

			// NO RunAction connecting the two
			assertHasNoRunAction(root, onInput, update);
		}

	}

}
