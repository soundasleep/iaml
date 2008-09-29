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
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.diagram.visual.edit.commands.IamlCreateShortcutDecorationsCommand;
import org.openiaml.model.model.diagram.visual.edit.parts.PageEditPart;

/**
 * Most of this code is based on http://www.jevon.org/wiki/GMF_Code_Samples
 * 
 * @author jmwright
 *
 */
public class CreateMissingShortcutsCommand extends AbstractTransactionalCommand {

	private EObject rootObject;
	private GraphicalEditPart selectedElement;
	private View parentView;
	private PreferencesHint prefHint;

	public CreateMissingShortcutsCommand(
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

	public CreateMissingShortcutsCommand(GraphicalEditPart root,
			EObject object, PreferencesHint prefHint) {
		this(root, object, root.getEditingDomain(), (View) root.getModel(), prefHint);
	}

	public CreateMissingShortcutsCommand(GraphicalEditPart root, PreferencesHint prefHint) {
		this(root, (EObject) ((Diagram) root.getModel()), prefHint);
	}

	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		
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
	 * Generate the command for creating the shortcuts
	 */
	protected ICommand getCreateShortcutsCommand() {

		Assert.isTrue(selectedElement.getModel() instanceof Diagram);
		
		// get all the nodes in the model tree
		final Diagram rootView = (Diagram) selectedElement.getModel();
		VisibleThing rootObject = (VisibleThing) rootView.getElement();	// we could actually go further up the heirarchy, i.e. ApplicationElementContainer
		
		// we create a composition of the commands necessary
		ICommand command = new RefreshElementCommand(rootObject, 
				selectedElement.getEditingDomain(), 
				rootView);

		// find all connections that should exist for these nodes
		List<WireEdge> connectionsOut = new ArrayList<WireEdge>();
		List<WireEdge> connectionsIn = new ArrayList<WireEdge>();
		List<EObject> toAdd = new ArrayList<EObject>();
		
		// ApplicationElement (incl VisualThing and Page)
		for (ApplicationElement child : rootObject.getChildren()) {
			connectionsOut.addAll( child.getEdges() );
			// we also look at the backwards references to add incoming shortcuts
			connectionsIn.addAll( child.getInEdges() );
		}
		
		// EventTrigger
		for (EventTrigger child : rootObject.getEventTriggers()) {
			connectionsOut.addAll( child.getEdges() );
			// EventTriggers can't have incoming connections
		}
		
		// Operation (incl ChainedOperation)
		for (Operation child : rootObject.getOperations()) {
			if (child instanceof WireEdgesSource) {
				connectionsIn.addAll( ((WireEdgesSource) child).getEdges() );
			}
			// we also look at the backwards references to add incoming shortcuts
			connectionsIn.addAll( child.getInEdges() );
		}
		
		// ApplicationElementProperty
		for (ApplicationElementProperty child : rootObject.getProperties()) {
			connectionsOut.addAll( child.getEdges() );
			// new: we now also look at the backwards references to add incoming shortcuts
			connectionsIn.addAll( child.getInEdges() );
		}
		
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
					selectedElement.getEditingDomain(), parentView, viewDescriptor, PageEditPart.MODEL_ID));
			
		}

		return command;
		
	}


}
