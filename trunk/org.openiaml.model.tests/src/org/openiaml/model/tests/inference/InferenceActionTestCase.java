/**
 * 
 */
package org.openiaml.model.tests.inference;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.jaxen.JaxenException;
import org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction;
import org.openiaml.model.tests.InferenceTestCase;

/**
 * An abstract test case to simpify checking inference actions which
 * are also triggerable by UI actions.
 * 
 * @author jmwright
 *
 */
public abstract class InferenceActionTestCase extends InferenceTestCase {

	/**
	 * The current test case. This will be used to select the 
	 * model file used in the test cases.
	 * 
	 * @return
	 */
	protected abstract Class<? extends InferenceTestCase> getTestClass();
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	/**
	 * Tests from the initial model that we are inferring from.
	 * 
	 * @throws Exception
	 */
	protected abstract void initialTests() throws Exception;
	
	/**
	 * Make sure the model is loaded properly.
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(getTestClass());
		
		initialTests();		
	}
	
	/**
	 * Complete model inference.
	 * 
	 * @throws Exception
	 */
	public void testDefaultInference() throws Exception {
		root = loadAndInfer(getTestClass());
		checkInferredKnowledge();
	}
	
	/**
	 * Inference through the custom action.
	 * 
	 * @throws JaxenException
	 */
	public void testActionInference() throws Exception {
		root = loadDirectly(getTestClass());		
		for (UpdateWithDroolsAction action : getActionList()) {
			action.refreshMappings(root, createHandler(), new NullProgressMonitor());
		}
		
		checkInferredKnowledge();
	}
	
	/**
	 * Get a list of actions to execute in order. By default, only
	 * returns {@link #getAction()}.
	 * 
	 * @return
	 */
	protected List<UpdateWithDroolsAction> getActionList() {
		List<UpdateWithDroolsAction> result = new ArrayList<UpdateWithDroolsAction>();
		result.add(getAction());
		return result;
	}

	/**
	 * The custom action to test against.
	 * 
	 * @return
	 */
	public abstract UpdateWithDroolsAction getAction();
	
	/**
	 * Test that the correct new knowledge has been added.
	 * 
	 * @param root
	 * @throws Exception
	 */
	protected abstract void checkInferredKnowledge() throws Exception;
	
}
