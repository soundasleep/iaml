/**
 * More information on this code: http://www.jevon.org/wiki/GMF_Code_Samples
 */
package org.openiaml.model.diagram.custom.commands;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;


/**
 * Refresh all the connections from a given EObject.
 * Based on generated XXXDiagramUpdateCommand and XXXCreateShortcutDecorationsCommand.
 * 
 * @author jmwright
 *
 */
public class RefreshElementCommand extends AbstractTransactionalCommand {

	private EObject rootObject;
	private View parentView;

	public RefreshElementCommand(
			EObject rootObject,
			TransactionalEditingDomain editingDomain, View parentView) {			
		super(editingDomain, "Refresh element view", getWorkspaceFiles(parentView)); //$NON-NLS-1$
		this.rootObject = rootObject;
		this.parentView = parentView;
	}

	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		
		// refresh visibility
		/*
		 * the problem with this code is that it calls eSet(VISIBLE)
		 * which then actually changes the state of the EMF model --
		 * registering the view (and all other views) as changed.
		 * 
		 * not sure if we can get away with refresh without this.
		 *
		parentView.setVisible(false);
		parentView.setVisible(true);
		// */
		
		// from generated DiagramUpdateCommand
		/* *
		List editPolicies = CanonicalEditPolicy
			.getRegisteredEditPolicies(rootObject);
		for (Iterator it = editPolicies.iterator(); it.hasNext(); ) {
			CanonicalEditPolicy nextEditPolicy = (CanonicalEditPolicy) it.next();
			nextEditPolicy.refresh();
		}
		// */

		return CommandResult.newOKCommandResult();
	}
		
}