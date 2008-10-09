/**
 * More information on this code: http://www.jevon.org/wiki/GMF_Code_Samples
 */
package org.openiaml.model.diagram.custom.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.model.diagram.edit.parts.InternetApplicationEditPart;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;


/**
 * 
 * @author jmwright
 *
 */
public class TestDeriveElementsCommand 
 	implements IObjectActionDelegate {

	private InternetApplicationEditPart selectedElement;

	private class DeriveElementsCommand extends AbstractTransactionalCommand {
		private EObject rootObject;
		private View parentView;

		public DeriveElementsCommand(
				EObject rootObject,
				TransactionalEditingDomain editingDomain, 
				View parentView) {			
			super(editingDomain, "Refresh element view", getWorkspaceFiles(parentView)); //$NON-NLS-1$
			this.rootObject = rootObject;
			this.parentView = parentView;
		}

		protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
				IAdaptable info) throws ExecutionException {
			
			// do something here
			MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Warning", "Not yet implemented. But here are the objects we have: object=" + this.rootObject + " view=" + this.parentView);

			return CommandResult.newOKCommandResult();
		}

	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// empty
	}

	@Override
	public void run(IAction action) {
		if (selectedElement != null) {
			ICommand command = new DeriveElementsCommand(
					selectedElement.resolveSemanticElement(), 
					selectedElement.getEditingDomain(), 
					selectedElement.getDiagramView());
			
			try {
				OperationHistoryFactory.getOperationHistory().execute(command,
						new NullProgressMonitor(), null);
			} catch (ExecutionException e) {
				IamlDiagramEditorPlugin.getInstance().logError(
						"Unable to derive elements", e); //$NON-NLS-1$
			}
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		selectedElement = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.getFirstElement() instanceof InternetApplicationEditPart) {
				selectedElement = (InternetApplicationEditPart) structuredSelection.getFirstElement();
			}
		}
		
	}
		
}