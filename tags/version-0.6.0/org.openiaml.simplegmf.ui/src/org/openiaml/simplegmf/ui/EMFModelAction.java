package org.openiaml.simplegmf.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Abstract class for interacting with EMF model instances.
 * 
 * @see org.openiaml.model.codegen.php
 * @author jmwright
 *
 */
public abstract class EMFModelAction extends ProgressEnabledAction<IFile> {
	
	/**
	 * The expected extension of model instances loaded, e.g. "iaml".
	 * 
	 * @return
	 */
	public abstract String getExpectedExtension();

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
		throws IOException, CoreException;
	
	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#execute(java.lang.Object, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus execute(IFile o, IProgressMonitor monitor) {
		try {
			if (o.getFileExtension().equals(getExpectedExtension())) {
				return doExecute(o, monitor);				
			} else {
				return new Status(IStatus.ERROR, PLUGIN_ID, "File '" + o.getName() + "' does not have an ." + getExpectedExtension() + " extension.");
			}
		} catch (IOException e) {
			return errorStatus("IO exception: " + getMessage(e), e);
		} catch (CoreException e) {
			return errorStatus("Core exception: " + getMessage(e), e);
		} catch (RuntimeException e) {
			return errorStatus("Runtime exception: " + getMessage(e), e);
		}
	}
	
	private String getMessage(Throwable e) {
		if (e.getMessage() == null || e.getMessage().isEmpty()) {
			return e.getClass().getSimpleName();
		}
		return e.getMessage();
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
