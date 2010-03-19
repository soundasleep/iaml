/**
 *
 */
package org.openiaml.model.tests.eclipse.inference.model0_3;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.jaxen.JaxenException;
import org.openiaml.model.diagram.custom.actions.RefreshFormMappingsWithDrools;
import org.openiaml.model.diagram.custom.actions.RefreshObjectInstanceMappingsWithDrools;
import org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction;
import org.openiaml.model.tests.CachedModelInferer;
import org.openiaml.model.tests.eclipse.inference.InferenceActionTestCase;
import org.openiaml.model.tests.inference.EclipseInheritanceInterface;
import org.openiaml.model.tests.inference.model0_3.InputFormInstanceMapping;

/**
 * Tests automatic mapping of SyncWires between InputForms and
 * DomainObjectInstances
 *
 * @author jmwright
 *
 */
public class EclipseInputFormInstanceMapping extends InferenceActionTestCase {

	/**
	 * Not used; see {@link #getActionList()}.
	 */
	@Override
	@Deprecated
	public UpdateWithDroolsAction getAction() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	protected List<UpdateWithDroolsAction> getActionList() {
		List<UpdateWithDroolsAction> list = new ArrayList<UpdateWithDroolsAction>();
		list.add(new RefreshObjectInstanceMappingsWithDrools());
		list.add(new RefreshFormMappingsWithDrools());
		return list;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.eclipse.inference.InferenceActionTestCase#getTestClass()
	 */
	@Override
	protected Class<? extends EclipseInheritanceInterface> getTestClass() {
		return InputFormInstanceMapping.class;
	}
	
	/**
	 * Inference through only the custom action. Doesn't do
	 * anything, because SyncWires are separate to the
	 * DomainObjectInstance mapping.
	 *
	 * @throws JaxenException
	 */
	@Override
	public void testActionInference() throws Exception {
		root = loadDirectly(InputFormInstanceMapping.class);
		RefreshFormMappingsWithDrools action =
			new RefreshFormMappingsWithDrools();

		action.refreshMappings(root, CachedModelInferer.getInstance().createCreateElementsFactory(), new NullProgressMonitor());

		InputFormInstanceMapping parent = new InputFormInstanceMapping();
		parent.checkNotInferredKnowledge(root);
	}

}
