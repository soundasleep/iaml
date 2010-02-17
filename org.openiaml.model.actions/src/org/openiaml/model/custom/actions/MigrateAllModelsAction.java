package org.openiaml.model.custom.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Iterate over all .iaml files in the current project, migrate them,
 * and then make sure they can be loaded using EMF.
 * 
 * <p>TODO make this extend ProgressEnabledAction
 * 
 * @author jmwright
 *
 */
public class MigrateAllModelsAction implements IObjectActionDelegate {
	
	private List<IContainer> selection;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(final IAction action) {

		for (IContainer project : selection) {
			MigrateAllModelsJob job = new MigrateAllModelsJob(project);
			job.schedule();
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = new ArrayList<IContainer>();
		if (selection instanceof IStructuredSelection) {
			this.selection = getSelection(((IStructuredSelection) selection).toArray());
		}
	}
	
	/**
	 * Compose an array of selected objects into a list of IProjects.
	 * 
	 * @param selection
	 * @return
	 */
	public List<IContainer> getSelection(Object[] selection) {
		final List<IContainer> ifiles = new ArrayList<IContainer>();
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IContainer) {
					ifiles.add((IContainer) o);
				}
			}
		}
		
		return ifiles;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// empty
	}

}
