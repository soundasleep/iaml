/**
 *
 */
package org.openiaml.model.tests.eclipse.inference.model0_4;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.jaxen.JaxenException;
import org.openiaml.model.diagram.custom.actions.InferContainedElementsAction;
import org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction;
import org.openiaml.model.diagram.custom.actions.InferContainedElementsAction.CreateElementsWithinContainer;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.tests.eclipse.inference.InferenceActionTestCase;
import org.openiaml.model.tests.inference.EclipseInheritanceInterface;
import org.openiaml.model.tests.inference.model0_4.DomainInstanceSaveOperation;

/**
 *  Issue 62: Inference on non-autosave DomainObjectInstance does not create 'save' operation
 *
 * @author jmwright
 *
 */
public class EclipseDomainInstanceSaveOperation extends InferenceActionTestCase {

	@Override
	protected Class<? extends EclipseInheritanceInterface> getTestClass() {
		return DomainInstanceSaveOperation.class;
	}

	/**
	 * Should not be used. Throws an exception.
	 *
	 * @deprecated
	 * @see org.openiaml.model.tests.inference.InferenceActionTestCase#getAction()
	 */
	@Override
	public UpdateWithDroolsAction getAction() {
		throw new UnsupportedOperationException("getAction() is not supported.");
	}

	/**
	 * Override to use our action, since we have to specify which
	 * element we want to infer within.
	 */
	@Override
	public void testActionInference() throws Exception {
		root = loadDirectly(getTestClass());
		InferContainedElementsAction action = new InferContainedElementsAction();
		action.refreshContainedMappings(root, createContainerHandler(action), new NullProgressMonitor());

		getTestInterface().checkInferredKnowledge(root);
	}

	/**
	 * We override the handler method to allow us to pass an
	 * EObject container.
	 * @throws JaxenException
	 */
	protected CreateElementsWithinContainer createContainerHandler(InferContainedElementsAction action) throws JaxenException {
		Page page = assertHasPage(root, "container");
		DomainObjectInstance di = assertHasDomainObjectInstance(page, "domain object instance");

		return action.new CreateElementsWithinContainer(di, super.createHandler(root.eResource()));
	}

}
