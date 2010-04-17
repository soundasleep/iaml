package org.openiaml.gmf.tools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.gmf.internal.codegen.popup.actions.ExecuteTemplatesAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Wraps the GMF codegen action to allow generating diagram code from
 * multiple sources at once. It uses internal GMF code so it is
 * likely to break in the future.
 * 
 * @author jmwright
 *
 */
@SuppressWarnings("restriction")
public class GenerateMultipleDiagramCodeAction implements IObjectActionDelegate {

	private List<IFile> selection;
	private IWorkbenchPart targetPart;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(final IAction action) {
		
		for (IFile file : selection) {
			ExecuteTemplatesAction generator = new ExecuteTemplatesAction();
			generator.setActivePart(action, targetPart);
			generator.selectionChanged(action, new StructuredSelection(file));
			generator.run(action);
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = new ArrayList<IFile>();
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

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

}
