/**
 *
 */
package org.openiaml.model.tests.eclipse.inference;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.jaxen.JaxenException;
import org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction;
import org.openiaml.model.tests.inference.EclipseInheritanceInterface;
import org.openiaml.model.tests.inference.InferenceTestCase;

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
	 * model file used in the test cases. This is also the
	 * test class that has the actual inference test cases.
	 *
	 * @return
	 */
	protected abstract Class<? extends EclipseInheritanceInterface> getTestClass();

	/**
	 * Instantiate the actual test case for testing the inference.
	 * 
	 * @see #getTestClass()
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public EclipseInheritanceInterface getTestInterface() throws InstantiationException, IllegalAccessException {
		return getTestClass().newInstance();
	}
	
	/**
	 * Make sure the model is loaded properly.
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(getTestClass());

		getTestInterface().checkNotInferredKnowledge(root);
	}

	/**
	 * Inference through the custom action.
	 *
	 * @throws JaxenException
	 */
	public void testActionInference() throws Exception {
		root = loadDirectly(getTestClass());
		
		for (UpdateWithDroolsAction action : getActionList()) {
			action.refreshMappings(root, createHandler(root.eResource()), new NullProgressMonitor());
		}

		getTestInterface().checkInferredKnowledge(root);
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

}
