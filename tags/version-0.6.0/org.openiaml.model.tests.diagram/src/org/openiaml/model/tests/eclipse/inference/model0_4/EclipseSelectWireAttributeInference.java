/**
 *
 */
package org.openiaml.model.tests.eclipse.inference.model0_4;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.model.diagram.custom.actions.RefreshFormMappingsWithDrools;
import org.openiaml.model.diagram.custom.actions.RefreshObjectInstanceMappingsWithDrools;
import org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction;
import org.openiaml.model.tests.eclipse.inference.InferenceActionTestCase;
import org.openiaml.model.tests.inference.EclipseInheritanceInterface;
import org.openiaml.model.tests.inference.model0_4.SelectWireAttributeInference;

/**
 * Issue 68: SelectWire does not synchronise Attributes between Object and Instance
 *
 * @author jmwright
 *
 */
public class EclipseSelectWireAttributeInference extends InferenceActionTestCase {

	@Override
	public Class<? extends EclipseInheritanceInterface> getTestClass() {
		return SelectWireAttributeInference.class;
	}

	@Override
	public List<UpdateWithDroolsAction> getActionList() {
		List<UpdateWithDroolsAction> result = new ArrayList<UpdateWithDroolsAction>();
		result.add(new RefreshObjectInstanceMappingsWithDrools());
		result.add(new RefreshFormMappingsWithDrools());
		return result;
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

}
