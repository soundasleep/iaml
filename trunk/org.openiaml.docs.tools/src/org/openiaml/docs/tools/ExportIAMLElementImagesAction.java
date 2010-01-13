package org.openiaml.docs.tools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.openiaml.model.model.ModelPackage;

/**
 * Export out IAML images. In particular:
 * 
 * <ol>
 * 	<li>Check that the incoming project has a *.iaml* file for some IAML model object
 * 	<li>Go over all IAML model objects that are not abstract or interfaces
 *  <li>Search for some file in the root <code>object_name.iaml*</code>
 *  <li>Try initialising the diagram
 *  <li>Try opening this diagram
 *  <li>Try exporting this image out to <code>object_name.png</code>
 *  <li>Delete initialised diagram
 * </ol>
 * 
 * @author jmwright
 *
 */
public class ExportIAMLElementImagesAction implements IObjectActionDelegate {
	
	private List<IProject> selection;
	private IWorkbenchPart targetPart;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(final IAction action) {

		for (IProject project : selection) {
			ExportIAMLImagesJob job = new ExportIAMLImagesJob(project);
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
		this.targetPart = targetPart;
	}

}
