package org.openiaml.model.diagram.custom.actions;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.diagram.visual.edit.parts.DomainObjectInstanceEditPart;

/**
 * Refresh {@link DomainObjectInstance} mappings with Drools.
 *
 * @author jmwright
 *
 */
public class RefreshObjectInstanceMappingsWithDrools extends UpdateWithDroolsAction {

	public class RefreshObjectInstanceMappings extends DroolsInferenceEngine {

		public RefreshObjectInstanceMappings(ICreateElements handler) {
			super(handler, false);
		}

		private List<String> ruleFiles = Arrays.asList(
				"/rules/runtime/domain.drl",
				"/rules/runtime/new-instance.drl"
				);

		/**
		 * Get the list of rule files used.
		 *
		 * @see #addRuleFile(String)
		 * @return
		 */
		public List<String> getRuleFiles() {
			return ruleFiles;
		}

	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction#getEditPartClass()
	 */
	@Override
	public Class<? extends ShapeNodeEditPart> getEditPartClass() {
		return DomainObjectInstanceEditPart.class;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction#getExpectedEObjectClass()
	 */
	@Override
	public Class<? extends EObject> getExpectedEObjectClass() {
		return DomainObjectInstance.class;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction#getEngine(org.openiaml.model.inference.ICreateElements)
	 */
	@Override
	public DroolsInferenceEngine getEngine(ICreateElements handler) {
		return new RefreshObjectInstanceMappings(handler);
	}

	/**
	 * We want to use the root element, not the selected element.
	 */
	@Override
	public EObject selectRootElement(EObject element) {
		return getRoot(element);
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction#getTitle()
	 */
	@Override
	public String getTitle() {
		return "DomainObjectInstance";
	}

}
