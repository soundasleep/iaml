/**
 *
 */
package org.openiaml.model.tests.inference.model0_6;

import org.openiaml.model.model.Operation;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Issue 223: Add 'visibility' boolean property to VisibleThings
 * 
 */
public class VisibleThingVisibilityProperty extends ValidInferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(getInferenceClass());
	}

	/**
	 * Test the initial model.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		
		Button target = assertHasButton(home, "target");
		Button set = assertHasButton(home, "set visibility");
		
		// they have properties
		assertHasProperty(target, "visible");
		assertHasProperty(set, "visible");
		
		Operation setTarget = assertHasOperation(target, "set visible");
		assertInstanceOf(PrimitiveOperation.class, setTarget);
		
	}

	/**
	 * Since there is a property named 'visible' in the Button named 'set visibility',
	 * an Operation called 'set visible' should be generated
	 * 
	 * @throws Exception
	 */
	public void testSetVisibleOperationGenerated() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		
		Button set = assertHasButton(home, "set visibility");
		
		// they have properties
		assertHasProperty(set, "visible");

		// no operation generated for 'set visibility' button yet
		Operation setVis = assertHasOperation(set, "set visible");
		
		// it is a primitive operation
		assertInstanceOf(PrimitiveOperation.class, setVis);
		assertGenerated(setVis);
	}

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return getClass();
	}
	
}
