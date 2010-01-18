package org.openiaml.docs.tools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Export out IAML example models. In particular:
 * 
 * <ol>
 *  <li>Check that an "<code>examples</code>" project is open and accessible
 *  <li>Search for an "<code>examplesList.txt</code>" in the root of this folder
 *  <li>Load this file and get only unique models
 *  <li>Check that each model exists in the project
 *  <li>For each test model:
 *  <ol>
 *  <li>Try initialising the diagram
 *  <li>Try opening this diagram
 *  <li>Try exporting all of the content to the "<code>examples</code>" project
 *  <li>Delete initialised diagram
 * </ol>
 * 
 * @author jmwright
 *
 */
public class ExportIAMLExamplesAction implements IObjectActionDelegate {
	
	private List<IProject> selection;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(final IAction action) {

		for (IProject project : selection) {
			ExportIAMLExamplesJob job = new ExportIAMLExamplesJob(project);
			job.schedule();
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = new ArrayList<IProject>();
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
	public List<IProject> getSelection(Object[] selection) {
		final List<IProject> ifiles = new ArrayList<IProject>();
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IProject) {
					ifiles.add((IProject) o);
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
