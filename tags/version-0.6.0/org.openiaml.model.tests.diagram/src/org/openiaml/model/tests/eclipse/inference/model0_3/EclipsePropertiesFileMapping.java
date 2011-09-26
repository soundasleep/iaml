/**
 *
 */
package org.openiaml.model.tests.eclipse.inference.model0_3;

import org.openiaml.model.diagram.custom.actions.RefreshDomainStoreMappingsWithDrools;
import org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction;
import org.openiaml.model.tests.eclipse.inference.InferenceActionTestCase;
import org.openiaml.model.tests.inference.EclipseInheritanceInterface;
import org.openiaml.model.tests.inference.model0_3.PropertiesFileMapping;

/**
 * Tests automatic mapping of DomainStores when connected to
 * Properties files
 *
 * @author jmwright
 *
 */
public class EclipsePropertiesFileMapping extends InferenceActionTestCase {

	@Override
	protected Class<? extends EclipseInheritanceInterface> getTestClass() {
		return PropertiesFileMapping.class;
	}

	@Override
	public UpdateWithDroolsAction getAction() {
		return new RefreshDomainStoreMappingsWithDrools();
	}

}
