package org.openiaml.model.diagram.custom.actions;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.openiaml.model.diagram.edit.parts.DomainSourceEditPart;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.drools.ICreateElementsFactory;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.domain.DomainSource;
import org.openiaml.model.model.domain.DomainStoreTypes;

/**
 * Refreshes DomainSources when connected to Properties files.
 *
 * @author jmwright
 *
 */
public class RefreshDomainStoreMappingsWithDrools extends UpdateWithDroolsAction {

	/**
	 * We only want to refresh Properties file mappings.
	 *
	 * @param object
	 * @throws InferenceException
	 */
	@Override
	public void checkModelElement(EObject object) throws InferenceException {
		DomainSource ds = (DomainSource) object;
		if (!ds.getType().equals(DomainStoreTypes.PROPERTIES_FILE)) {
			throw new InferenceException("Can only refresh mappings of Properties files: actual type was '" + ds.getType() + "'");
		}
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction#getEditPartClass()
	 */
	@Override
	public Class<? extends ShapeNodeEditPart> getEditPartClass() {
		return DomainSourceEditPart.class;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction#getExpectedEObjectClass()
	 */
	@Override
	public Class<? extends EObject> getExpectedEObjectClass() {
		return DomainSource.class;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction#getEngine(org.openiaml.model.inference.ICreateElements)
	 */
	@Override
	public DroolsInferenceEngine getEngine(ICreateElementsFactory handler) {
		return new RefreshDataStores(handler);
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
		return "Properties-based DomainSource";
	}

	/**
	 * Uses Drools to refresh data stores.
	 *
	 * @author jmwright
	 *
	 */
	public class RefreshDataStores extends DroolsInferenceEngine {

		ICreateElementsFactory handler;

		public RefreshDataStores(ICreateElementsFactory handler) {
			super(handler, false);
			this.handler = handler;
		}

		private List<String> ruleFiles = Arrays.asList(
				"/rules/runtime/domain.drl",
				"/rules/runtime/file-domain-object.drl"
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

}
