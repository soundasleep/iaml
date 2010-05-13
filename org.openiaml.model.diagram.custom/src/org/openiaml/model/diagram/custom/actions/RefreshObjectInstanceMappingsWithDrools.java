package org.openiaml.model.diagram.custom.actions;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.openiaml.model.diagram.frame.edit.parts.DomainIteratorEditPart;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.drools.ICreateElementsFactory;
import org.openiaml.model.model.domain.DomainIterator;

/**
 * Refresh {@link DomainObjectInstance} mappings with Drools.
 *
 * @author jmwright
 *
 */
public class RefreshObjectInstanceMappingsWithDrools extends UpdateWithDroolsAction {

	public class RefreshObjectInstanceMappings extends DroolsInferenceEngine {

		public RefreshObjectInstanceMappings(ICreateElementsFactory handler) {
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
		@Override
		public List<String> getRuleFiles() {
			return ruleFiles;
		}

	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction#getEditPartClass()
	 */
	@Override
	public Class<? extends ShapeNodeEditPart> getEditPartClass() {
		return DomainIteratorEditPart.class;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction#getExpectedEObjectClass()
	 */
	@Override
	public Class<? extends EObject> getExpectedEObjectClass() {
		return DomainIterator.class;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction#getEngine(org.openiaml.model.inference.ICreateElements)
	 */
	@Override
	public DroolsInferenceEngine getEngine(ICreateElementsFactory handler) {
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
		return "DomainIterator";
	}

}
