/**
 * 
 */
package org.openiaml.model.tests.inference.model0_4;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.jaxen.JaxenException;
import org.openiaml.model.diagram.custom.actions.InferContainedElementsAction;
import org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction;
import org.openiaml.model.diagram.custom.actions.InferContainedElementsAction.CreateElementsWithinContainer;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.tests.InferenceTestCase;
import org.openiaml.model.tests.inference.InferenceActionTestCase;

/**
 *  Issue 62: Inference on non-autosave DomainObjectInstance does not create 'save' operation
 * 
 * @author jmwright
 *
 */
public class DomainInstanceSaveOperation extends InferenceActionTestCase {

	@Override
	protected Class<? extends InferenceTestCase> getTestClass() {
		return DomainInstanceSaveOperation.class;
	}

	@Override
	protected void initialTests() throws Exception {
		
		Page page = (Page) queryOne(root, "iaml:children[iaml:name='container']");
		DomainObjectInstance di = (DomainObjectInstance) queryOne(page, "iaml:children[iaml:name='domain object instance']");
		
		// page is otherwise empty
		assertEquals(1, page.getChildren().size());
		
		// instance is empty
		assertEquals(0, di.getAttributes().size());
		assertEquals(0, di.getOperations().size());
				
	}
	
	@Override
	protected void checkInferredKnowledge() throws Exception {

		Page page = (Page) queryOne(root, "iaml:children[iaml:name='container']");
		DomainObjectInstance di = (DomainObjectInstance) queryOne(page, "iaml:children[iaml:name='domain object instance']");
		
		// page is otherwise empty
		assertEquals(1, page.getChildren().size());
		
		// instance now contains a 'save' method and 'exists?' method
		assertEquals(2, di.getOperations().size());
		
		assertContainsNamedElement(di.getOperations(), "save", true);
		assertContainsNamedElement(di.getOperations(), "exists?", true);
		
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
		
		checkInferredKnowledge();
	}

	/**
	 * We override the handler method to allow us to pass an
	 * EObject container.
	 * @throws JaxenException 
	 */
	protected CreateElementsWithinContainer createContainerHandler(InferContainedElementsAction action) throws JaxenException {
		Page page = (Page) queryOne(root, "iaml:children[iaml:name='container']");
		DomainObjectInstance di = (DomainObjectInstance) queryOne(page, "iaml:children[iaml:name='domain object instance']");
		
		return action.new CreateElementsWithinContainer(di, super.createHandler()); 
	}
	
}
