/**
 * 
 */
package org.openiaml.docs.tools;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * Some helper methods for jobs.
 * 
 * @author jmwright
 *
 */
public abstract class AbstractIAMLDocJob extends Job {

	public AbstractIAMLDocJob(String name) {
		super(name);
	}
	
	/**
	 * Construct an error IStatus with the given message.
	 */
	protected IStatus errorStatus(String message) {
		return new Status(IStatus.ERROR, DocToolsPlugin.PLUGIN_ID, message);
	}
	
	/**
	 * Construct an error IStatus with the given throwable.
	 */
	protected IStatus errorStatus(Throwable e) {
		return new Status(IStatus.ERROR, DocToolsPlugin.PLUGIN_ID, e.getMessage(), e);
	}


	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus run(final IProgressMonitor monitor) {
			
		  Display.getDefault().syncExec(new Runnable() {
		    @Override
		    public void run() {
		    	try {
			    	IStatus status = runActual(monitor);
			    	
			    	if (!status.isOK()) {
						DocToolsPlugin.getInstance().getLog().log(status);

				    	// display error
						ErrorDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), 
								getName(), 
								status.getMessage(),
								status);
			    	}
			    	
		    	} catch (CoreException e) {
					IStatus error = new Status(Status.ERROR, DocToolsPlugin.PLUGIN_ID, "An exception occured: " + e.getMessage(), e);
					DocToolsPlugin.getInstance().getLog().log(error);
 					
					// display error
					ErrorDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), 
							getName(), 
							error.getMessage(),
							error);
				}
		    }

		  });
			
		// can't get the real status yet
		return Status.OK_STATUS;
			
	}

	/**
	 * The actual implementation of the executable job.
	 * 
	 * @param monitor
	 * @return
	 */
	protected abstract IStatus runActual(IProgressMonitor monitor) throws CoreException;
}
