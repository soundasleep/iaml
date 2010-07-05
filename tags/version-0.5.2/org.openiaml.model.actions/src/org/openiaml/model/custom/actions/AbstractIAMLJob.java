/**
 * 
 */
package org.openiaml.model.custom.actions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.actions.IamlActionsPlugin;

/**
 * Some helper methods for jobs.
 * TODO this is a copy of org.openiaml.docs.tool.AbstractIAMLDocJob
 * 
 * @author jmwright
 *
 */
public abstract class AbstractIAMLJob extends Job {

	public AbstractIAMLJob(String name) {
		super(name);
	}
	
	/**
	 * Construct an error IStatus with the given message.
	 */
	public static IStatus errorStatus(String message) {
		return new Status(IStatus.ERROR, IamlActionsPlugin.PLUGIN_ID, message);
	}
	
	/**
	 * Construct an error IStatus with the given throwable.
	 */
	public static  IStatus errorStatus(Throwable e) {
		return new Status(IStatus.ERROR, IamlActionsPlugin.PLUGIN_ID, e.getMessage(), e);
	}
	
	/**
	 * Construct an error IStatus with the given message and throwable.
	 */
	public static  IStatus errorStatus(String message, Throwable e) {
		return new Status(IStatus.ERROR, IamlActionsPlugin.PLUGIN_ID, message, e);
	}
	
	/**
	 * Construct a new empty MultiStatus with the given message.
	 */
	public static  MultiStatus multiStatus(String message, Throwable e) {
		return new MultiStatus(IamlActionsPlugin.PLUGIN_ID, IStatus.ERROR, message, e);
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
			    		IamlActionsPlugin.getInstance().getLog().log(status);

				    	// display error
						ErrorDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), 
								getName(), 
								status.getMessage(),
								status);
			    	}
			    	
		    	} catch (CoreException e) {
					IStatus error = new Status(Status.ERROR, IamlActionsPlugin.PLUGIN_ID, "An exception occured: " + e.getMessage(), e);
					IamlActionsPlugin.getInstance().getLog().log(error);
 					
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
