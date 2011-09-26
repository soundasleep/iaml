/**
 *
 */
package org.openiaml.model.tests.eclipse.inference.model0_4;

import org.openiaml.model.diagram.custom.actions.RefreshDomainStoreMappingsWithDrools;
import org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction;
import org.openiaml.model.tests.eclipse.inference.InferenceActionTestCase;
import org.openiaml.model.tests.inference.EclipseInheritanceInterface;
import org.openiaml.model.tests.inference.model0_4.GeneratedPrimaryKey;

/**
 * DomainObjects that do not define a DomainAttribute with a primary
 * key should get one generated automatically.
 *
 * @author jmwright
 *
 */
public class EclipseGeneratedPrimaryKey extends InferenceActionTestCase {

	@Override
	public Class<? extends EclipseInheritanceInterface> getTestClass() {
		return GeneratedPrimaryKey.class;
	}

	@Override
	public UpdateWithDroolsAction getAction() {
		return new RefreshDomainStoreMappingsWithDrools();
	}

}
