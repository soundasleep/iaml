package org.openiaml.model.diagram.custom.actions;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.diagram.edit.parts.DomainStoreEditPart;
import org.openiaml.model.model.domain.DomainStoreTypes;

/**
 * Refreshes DomainStores when connected to Properties files.
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
		DomainStore ds = (DomainStore) object;
		if (!ds.getType().equals(DomainStoreTypes.PROPERTIES_FILE)) {
			throw new InferenceException("Can only refresh mappings of Properties files: actual type was '" + ds.getType() + "'");	
		}
	}
	
	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction#getEditPartClass()
	 */
	@Override
	public Class<? extends ShapeNodeEditPart> getEditPartClass() {
		return DomainStoreEditPart.class;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction#getExpectedEObjectClass()
	 */
	@Override
	public Class<? extends EObject> getExpectedEObjectClass() {
		return DomainStore.class;
	}
	
	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction#getEngine(org.openiaml.model.inference.ICreateElements)
	 */
	@Override
	public DroolsInferenceEngine getEngine(ICreateElements handler) {
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
		return "Properties-based DomainStore";
	}

	/**
	 * Uses Drools to refresh data stores.
	 * 
	 * @author jmwright
	 *
	 */
	public class RefreshDataStores extends DroolsInferenceEngine {
		
		ICreateElements handler;

		public RefreshDataStores(ICreateElements handler) {
			super(handler, false);
			this.handler = handler;
		}

		private List<String> ruleFiles = Arrays.asList(
				"/rules/runtime/file-domain-object.drl"
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

}
