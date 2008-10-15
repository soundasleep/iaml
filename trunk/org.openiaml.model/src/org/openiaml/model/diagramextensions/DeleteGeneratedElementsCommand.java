/**
 * 
 */
package org.openiaml.model.diagramextensions;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * @author jmwright
 *
 */
public class DeleteGeneratedElementsCommand extends Command {

	private DestroyElementRequest request;
	private EditPart editPart;
	private TransactionalEditingDomain editingDomain;
	
	/**
	 * We need both the delete request (to get the deleted element)
	 * and the editPart (to get the view)
	 * 
	 * @param req
	 * @param editPart 
	 */
	public DeleteGeneratedElementsCommand(DestroyElementRequest req, EditPart editPart) {
		this.request = req;
		this.editPart = editPart;
		// calculate the proper editPart
		if (editPart instanceof GraphicalEditPart) {
			GraphicalEditPart part = (GraphicalEditPart) editPart;
			this.editingDomain = part.getEditingDomain();
			this.editPart = part.getParent();
		} else if (editPart instanceof ConnectionEditPart) {
			ConnectionEditPart part = (ConnectionEditPart) editPart;
			this.editingDomain = part.getEditingDomain();
			this.editPart = part.getParent();
		} else {
			throw new RuntimeException("I don't know what to do with editParts of type " + editPart.getClass());
		}
	}


	@Override
	public void execute() {
		System.out.println("deleting " + request + " editPart= " + editPart);
	
		// sanity checks
		Assert.isNotNull(request);
		Assert.isNotNull(editPart);
		
		// now do our remove generated elements command
		ICommand command = new RemoveGeneratedElementsCommand( editPart,
				request.getElementToDestroy(),
				editingDomain,
				new PreferencesHint("org.openiaml.model.diagram")	// hack toget an ID
				);
	
		try {
			OperationHistoryFactory.getOperationHistory().execute(command,
					new NullProgressMonitor(), null);
		} catch (ExecutionException e) {
			/*
			IamlDiagramEditorPlugin.getInstance().logError(
					"Unable to possibly remove generated elements", e); //$NON-NLS-1$
					*/
			e.printStackTrace();
		}
	}

}
