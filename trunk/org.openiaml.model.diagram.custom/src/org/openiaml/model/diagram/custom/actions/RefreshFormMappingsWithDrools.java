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
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.inference.EmfInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.model.diagram.visual.edit.parts.InputFormEditPart;
import org.openiaml.model.model.visual.InputForm;

/**
 * Refresh {@link InputForm} mappings with Drools.
 * 
 * @see org.openiaml.model.drools
 * @author jmwright
 *
 */
public class RefreshFormMappingsWithDrools implements IViewActionDelegate {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof InputFormEditPart) {
					InputFormEditPart part = (InputFormEditPart) o;
					IStatus status = refreshMappings(part, action, new NullProgressMonitor());
					if (!status.isOK()) {
						// TODO remove this reference to the plugin and remove the reference in plugin.xml
						IamlDiagramEditorPlugin.getInstance().logError(
								"Could not refresh mappings for " + o + ": " + status.getMessage(), status.getException()); //$NON-NLS-1$
						MessageDialog.openError(IamlDiagramEditorPlugin.getInstance().getWorkbench().getDisplay().getActiveShell(), "Error", status.getMessage());
					}
				}
			}
		}

	}
	
	/**
	 * Refresh the mappings manually. Also useful for test cases.
	 * 
	 * @throws InferenceException 
	 */
	public void refreshMappings(EObject root, ICreateElements handler, IProgressMonitor monitor) throws InferenceException {
		DroolsInferenceEngine engine = new RefreshFormMappings(handler);
		engine.create(root, new SubProgressMonitor(monitor, 100));
	}
	
	/**
	 * @param part The edit part to work with
	 * @param monitor 
	 * @return 
	 */
	protected IStatus refreshMappings(InputFormEditPart part, IAction action, IProgressMonitor monitor) {
		try {
			monitor.beginTask("Refreshing InputForm mappings", 200);
			
			EObject obj = part.resolveSemanticElement();
			if (!(obj instanceof InputForm))
				throw new InferenceException("Object was not a InputForm");	
			
			InputForm resolved = (InputForm) obj;
						
			EObject root = getRoot(resolved);
			
			refreshMappings(root, new EmfInferenceHandler(
					part.getEditingDomain(), 
					new ArrayList<Object>(), /* affected files */
					new SubProgressMonitor(monitor, 100), 
					null /* IAdapter == null */,
					resolved.eResource()	/* eResource */
			), monitor);
			
			monitor.done();
				
			return Status.OK_STATUS;
	
		} catch (InferenceException e) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Inference failed: " + e.getMessage(), e);
		}
			/*
		} catch (IOException e) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "IO exception", e);
		} catch (CoreException e) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Core exception", e);
		} */
		
	}
	
	/**
	 * Iterate through the object until we find the root object, if any.
	 * 
	 * @param resolved
	 * @return
	 */
	protected EObject getRoot(EObject resolved) {
		if (resolved.eContainer() == null)
			return resolved;
		return getRoot(resolved.eContainer());
	}

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
	
	public static final String PLUGIN_ID = "org.openiaml.model.diagram.custom";
	Object[] selection;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = null;
		if (selection instanceof IStructuredSelection) {
			this.selection = ((IStructuredSelection) selection).toArray();
		}
	}

}
