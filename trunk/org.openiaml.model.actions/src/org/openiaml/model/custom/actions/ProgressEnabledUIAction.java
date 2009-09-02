package org.openiaml.model.custom.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * Extends {@link ProgressEnabledAction} to run the runnable thread within the current
 * UI thread, rather than in a new thread.
 * 
 * This should only be used if the given action <i>has</i> to be run from the
 * UI thread.
 *
 */
public abstract class ProgressEnabledUIAction<T> extends ProgressEnabledAction<T> {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(final IAction action) {
		// get all the appropriate selected objects of type T
		final List<T> result = getSelection(selection);
		
		/**
		 * Create a progress display monitor, and actually
		 * execute the code generation.
		 */
		try {
			IRunnableContext context = new ProgressMonitorDialog(Display.getDefault().getActiveShell());
			PlatformUI.getWorkbench().getProgressService().runInUI(context,
					getRunnable(result), null /* ISchedulingRule */);
		} catch (InvocationTargetException e) {
			getDefaultPlugin().logError(e.getMessage(), e);
		} catch (InterruptedException e) {
			getDefaultPlugin().logError(e.getMessage(), e);
		}

	}
}
