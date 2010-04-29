/**
 *
 */
package org.openiaml.model.tests.eclipse.inference.model0_3;

import org.openiaml.model.diagram.custom.actions.RefreshObjectInstanceMappingsWithDrools;
import org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction;
import org.openiaml.model.tests.eclipse.inference.InferenceActionTestCase;
import org.openiaml.model.tests.inference.EclipseInheritanceInterface;
import org.openiaml.model.tests.inference.model0_3.NewInstanceWireMapping;

/**
 * Tests automatic mapping of NewInstanceWires from DomainObjects
 * to DomainObjectInstances through NewInstanceWires
 *
 * @author jmwright
 *
 */
public class EclipseNewInstanceWireMapping extends InferenceActionTestCase {

	@Override
	protected Class<? extends EclipseInheritanceInterface> getTestClass() {
		return NewInstanceWireMapping.class;
	}

	@Override
	public UpdateWithDroolsAction getAction() {
		return new RefreshObjectInstanceMappingsWithDrools();
	}

}
