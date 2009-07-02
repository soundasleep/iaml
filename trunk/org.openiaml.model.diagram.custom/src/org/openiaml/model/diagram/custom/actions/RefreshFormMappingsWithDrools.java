package org.openiaml.model.diagram.custom.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.openiaml.model.diagram.custom.actions.RefreshObjectInstanceMappingsWithDrools.RefreshObjectInstanceMappings;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.inference.EmfInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.model.diagram.visual.edit.parts.DomainObjectInstanceEditPart;
import org.openiaml.model.model.diagram.visual.edit.parts.InputFormEditPart;
import org.openiaml.model.model.visual.InputForm;

/**
 * Refresh {@link InputForm} mappings with Drools.
 * 
 * @author jmwright
 *
 */
public class RefreshFormMappingsWithDrools extends UpdateWithDroolsAction{

	public class RefreshFormMappings extends DroolsInferenceEngine {
		
		public RefreshFormMappings(ICreateElements handler) {
			super(handler);
		}

		private List<String> ruleFiles = Arrays.asList(
				"/rules/sync-wires.drl"
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
		return InputFormEditPart.class;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction#getExpectedEObjectClass()
	 */
	@Override
	public Class<? extends EObject> getExpectedEObjectClass() {
		return InputForm.class;
	}
	
	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.UpdateWithDroolsAction#getEngine(org.openiaml.model.inference.ICreateElements)
	 */
	@Override
	public DroolsInferenceEngine getEngine(ICreateElements handler) {
		return new RefreshFormMappings(handler);
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
