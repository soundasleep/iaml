package org.openiaml.model.diagram.custom.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.inference.InferenceException;

/**
 * Abstract class for interacting with .iaml files.
 * 
 * @see org.openiaml.model.codegen.oaw
 * @author jmwright
 *
 */
public abstract class IamlFileAction extends ProgressEnabledAction<IFile> {

	/**
	 * @return For {@link GenerateCodeActionAndView}, we need to get the
	 * loaded model.
	 */
	protected abstract EObject getLoadedModel();
	
	/**
	 * Execute the action. The IFile extension is guaranteed to
	 * equal "iaml".
	 * 
	 * monitor.beginTask() and monitor.done() should be used.
	 * 
	 * @param o
	 * @param monitor
	 * @return
	 */
	public abstract IStatus doExecute(IFile o, IProgressMonitor monitor)
		throws InferenceException, IOException, CoreException;
	
	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#execute(java.lang.Object, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus execute(IFile o, IProgressMonitor monitor) {
		try {
			if (o.getFileExtension().equals("iaml")) {
				return doExecute(o, monitor);				
			} else {
				return new Status(IStatus.ERROR, PLUGIN_ID, "File '" + o.getName() + "' does not have an .iaml extension.");
			}
		} catch (InferenceException e) {
			return errorStatus("Inference failed: " + e.getMessage(), e);
		} catch (IOException e) {
			return errorStatus("IO exception: " + e.getMessage(), e);
		} catch (CoreException e) {
			return errorStatus("Core exception: " + e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getSelection(java.lang.Object[])
	 */
	@Override
	public List<IFile> getSelection(Object[] selection) {
		final List<IFile> ifiles = new ArrayList<IFile>();
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IFile) {
					ifiles.add((IFile) o);
				}
			}
		}
		
		return ifiles;
	}

}
