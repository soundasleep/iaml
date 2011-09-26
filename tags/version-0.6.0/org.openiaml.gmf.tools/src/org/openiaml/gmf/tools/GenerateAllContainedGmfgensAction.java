package org.openiaml.gmf.tools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * (issue 153) Iterate over all directly contained .gmfgens in this container, and
 * generate the code.
 * 
 * @author jmwright
 *
 */
public class GenerateAllContainedGmfgensAction implements IObjectActionDelegate {

	private List<IContainer> selection;
	private IWorkbenchPart targetPart;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(final IAction action) {
		
		try {
			// construct an ISelection of all files
			ISelection sel = new StructuredSelection(getAllContainedGmfgens(selection));
		
			GenerateMultipleDiagramCodeAction a2 = new GenerateMultipleDiagramCodeAction();
			a2.setActivePart(action, this.targetPart);
			a2.selectionChanged(action, sel);
			a2.run(action);

		} catch (CoreException e) {
			// log the exception
			GmfToolsPlugin.getInstance().logError("Core exception: " + e.getMessage(), e);
		}
	
	}

	/**
	 * Get all .gmfgens in the given collections of IContainers.
	 * 
	 * @param selection2
	 * @return
	 * @throws CoreException 
	 */
	private List<IFile> getAllContainedGmfgens(List<IContainer> sel) throws CoreException {
		
		List<IFile> result = new ArrayList<IFile>();
		for (IContainer c : sel) {
			for (IResource res : c.members()) {
				if (res instanceof IFile) {
					IFile f = (IFile) res;
					if (f.getFileExtension().toLowerCase().equals("gmfgen")) {
						result.add(f);
					}
				}
			}
		}
		return result;
		
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
	 * Compose an array of selected objects into a list of IFiles.
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
		this.targetPart = targetPart;
	}
}
