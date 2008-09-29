package org.openiaml.model.diagram.custom.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.commands.CreateCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.openiaml.model.model.WireEdge;

/**
 * This class is now abstract so that we may have multiple similar commands
 * for each separate editor, e.g. we have both the Visual and Root element 
 * editors both pointing towards the same Command.
 * 
 * Most of this code is based on http://www.jevon.org/wiki/GMF_Code_Samples
 * 
 * @author jmwright
 *
 */
public abstract class AbstractCreateMissingShortcutsCommand extends AbstractTransactionalCommand {

	private EObject rootObject;
	private GraphicalEditPart selectedElement;
	private View parentView;
	private PreferencesHint prefHint;

	public AbstractCreateMissingShortcutsCommand(
			GraphicalEditPart root,
			EObject rootObject,
			TransactionalEditingDomain editingDomain, 
			View parentView,
			PreferencesHint prefHint) {			
		super(editingDomain, "Refresh element view", getWorkspaceFiles(parentView)); //$NON-NLS-1$
		selectedElement = root;
		this.parentView = parentView;
		this.rootObject = rootObject;
		this.prefHint = prefHint;
	}

	public AbstractCreateMissingShortcutsCommand(GraphicalEditPart root,
			EObject object, PreferencesHint prefHint) {
		this(root, object, root.getEditingDomain(), (View) root.getModel(), prefHint);
	}

	public AbstractCreateMissingShortcutsCommand(GraphicalEditPart root, PreferencesHint prefHint) {
		this(root, (EObject) ((Diagram) root.getModel()), prefHint);
	}

	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		
		// quick sanity check
		assert(getEditPartModelId() != null);
		
		// get the command to create the shortcuts
		ICommand command = getCreateShortcutsCommand();

		// try executing it
		// exceptions will pass through
		OperationHistoryFactory.getOperationHistory().execute(command,
				monitor, info);
		
		// after creating everything, issue a new command: refresh elements

		// refresh the view
		// from Uml3DiagramUpdateCommand
		ICommand command2 = new RefreshElementCommand(rootObject,
				selectedElement.getEditingDomain(),
				parentView);
		
		OperationHistoryFactory.getOperationHistory().execute(command2,
				monitor, info);

		return CommandResult.newOKCommandResult();
	}

	/**
	 * For a given EObject, get all the WireEdges that go out of it
	 * 
	 * @param object
	 * @return
	 */
	protected abstract List<WireEdge> getEdgesOut(EObject object);
	
	/**
	 * For a given EObject, get all the WireEdges that come into it
	 * 
	 * @param object
	 * @return
	 */
	protected abstract List<WireEdge> getEdgesIn(EObject object);
	
	/**
	 * The MODEL_ID to be placed in the shortcut annotation. 
	 * For example, PageEditPart.MODEL_ID. 
	 * Must not be null.
	 * 
	 * @return
	 */
	protected abstract String getEditPartModelId();
	
	/**
	 * Generate the command for creating the shortcuts.
	 */
	protected ICommand getCreateShortcutsCommand() {

		Assert.isTrue(selectedElement.getModel() instanceof Diagram);
		
		// get all the nodes in the model tree
		final Diagram rootView = (Diagram) selectedElement.getModel();
		EObject rootObject = rootView.getElement();
		
		// we create a composition of the commands necessary
		ICommand command = new RefreshElementCommand(rootObject, 
				selectedElement.getEditingDomain(), 
				rootView);

		// find all connections that should exist for these nodes
		List<WireEdge> connectionsOut = this.getEdgesOut(rootObject); 
		List<WireEdge> connectionsIn = this.getEdgesIn(rootObject);

		List<EObject> toAdd = new ArrayList<EObject>();

		// get all connections outwards that we need to add
		for (WireEdge edge : connectionsOut) {
			// if the connection is not already displayed with a shortcut
			boolean isVisible = false;
			for (Object o : selectedElement.getChildren()) {
				if (o instanceof EditPart && ((EditPart) o).getModel() instanceof Node) {
					Node onode = (Node) ((EditPart) o).getModel();
					
					if (edge.getTo() == onode.getElement()) {
						isVisible = true;
						break;
					}
				}
			}
			
			if (!isVisible && !toAdd.contains(edge.getTo())) {
				toAdd.add(edge.getTo());
			}
		}
		
		// get all connections inwards that we need to add
		for (WireEdge edge : connectionsIn) {
			// if the connection is not already displayed with a shortcut
			boolean isVisible = false;
			for (Object o : selectedElement.getChildren()) {
				if (o instanceof EditPart && ((EditPart) o).getModel() instanceof Node) {
					Node onode = (Node) ((EditPart) o).getModel();
					
					if (edge.eContainer() == onode.getElement()) {
						isVisible = true;
						break;
					}
				}
			}
			
			if (!isVisible && !toAdd.contains(edge.eContainer())) {
				toAdd.add(edge.eContainer());
			}
		}

		// create a shortcut displaying it
		// we now have a list of Actions that we want to add to the current view
		for (EObject newObject : toAdd) {
			CreateViewRequest.ViewDescriptor viewDescriptor = new CreateViewRequest.ViewDescriptor(
					new EObjectAdapter(newObject), Node.class, null,
					prefHint);
			
			// TODO: it would be nice if the newly created elements are not just placed at (0,0)
			command = command.compose(new CreateCommand(
					selectedElement.getEditingDomain(), viewDescriptor, parentView));
			command = command.compose(new CreateShortcutDecorationsCommand(
					selectedElement.getEditingDomain(), parentView, viewDescriptor, this.getEditPartModelId()));
			
		}

		return command;
		
	}

}
