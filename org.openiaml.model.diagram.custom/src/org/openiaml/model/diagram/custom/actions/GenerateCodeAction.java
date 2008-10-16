package org.openiaml.model.diagram.custom.actions;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jet.JET2Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;

/**
 * Action to generate code from an .iaml file
 * 
 * @see org.openiaml.model.codegen.jet
 * @author jmwright
 *
 */
public class GenerateCodeAction implements IViewActionDelegate {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IFile) {
					IStatus status = generateCodeFrom((IFile) o, action, new NullProgressMonitor());
					if (!status.isOK()) {
						// TODO remove this reference to the plugin and remove the reference in plugin.xml
						IamlDiagramEditorPlugin.getInstance().logError(
								"Could not generate code for " + o, status.getException()); //$NON-NLS-1$
					}
				}
			}
		}

	}
	
	/**
	 * @param o
	 * @param monitor 
	 * @return 
	 */
	private IStatus generateCodeFrom(IFile o, IAction action, IProgressMonitor monitor) {
		if (o.getFileExtension().equals("iaml")) {
			Map<String,Object> variables = new HashMap<String,Object>();
			variables.put("somevar", "a variable");
			return JET2Platform.runTransformOnResource("org.openiaml.model.codegen.jet", o, variables, monitor);
		}
		
		return null;
	}

	Object[] selection;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = null;
		if (selection instanceof IStructuredSelection) {
			this.selection = ((IStructuredSelection) selection).toArray();
		}
	}

}
