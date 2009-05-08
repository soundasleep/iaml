package org.openiaml.model.diagram.custom.actions;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.openiaml.model.inference.EmfInferenceHandler;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.diagram.edit.parts.DomainStoreEditPart;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.model.domain.DomainStoreTypes;

/**
 * A temporary action which infers the entire model, and places
 * it all back within the same model file. This isn't really designed
 * to be used in the real world, because we should be working with
 * models that do not require such inference to understand their
 * operation.
 * 
 * @see org.openiaml.model.codegen.oaw
 * @author jmwright
 *
 */
public class RefreshMappingsAction implements IViewActionDelegate {

	/**
	 * The loaded model.
	 */
	private EObject model = null;

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
		model = null;
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof DomainStoreEditPart) {
					DomainStoreEditPart fd = (DomainStoreEditPart) o;
					IStatus status = refreshMappings(fd, action, new NullProgressMonitor());
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
	 * @param fd The edit part to work with
	 * @param monitor 
	 * @return 
	 */
	protected IStatus refreshMappings(DomainStoreEditPart fd, IAction action, IProgressMonitor monitor) {
		try {
		
			EObject obj = fd.resolveSemanticElement();
			if (!(obj instanceof DomainStore))
				throw new InferenceException("Object was not a DomainStore");	
			
			DomainStore fds = (DomainStore) obj;
			
			if (!fds.getType().equals(DomainStoreTypes.PROPERTIES_FILE)) {
				throw new InferenceException("Can only refresh mappings of Properties files: actual type was '" + fds.getType() + "'");	
			}
			
			/*
			fds.refreshMappings(new GmfInferenceHandler(
					monitor,
					null,	// IAdapter == null, though it should be linked to the monitor somehow
					IamlDiagramEditorPlugin.ID,
					fd.getEditingDomain()
			));
			*/
			fds.refreshMappings(new EmfInferenceHandler(
					fd.getEditingDomain(), 
					new ArrayList<Object>(), /* affected files */
					monitor, 
					null /* IAdapter == null */,
					obj.eResource()	/* eResource */
			));
				
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

	public EObject getLoadedModel() {
		return model;
	}

}
