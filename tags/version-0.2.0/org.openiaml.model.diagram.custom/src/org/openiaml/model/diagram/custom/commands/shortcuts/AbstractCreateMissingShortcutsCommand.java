package org.openiaml.model.diagram.custom.commands.shortcuts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.commands.CreateCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.openiaml.model.diagram.custom.commands.RefreshElementCommand;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
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
		// we used to only refresh the EObject of the container, not the actual EObject instance
		this(root, (EObject) ((Diagram) root.getModel()).getElement(), prefHint);
	}

	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		
		// quick sanity check
		assert(getEditPartModelId() != null);
		
		// get the command to create the shortcuts
		// this will also execute the commands
		getCreateShortcutsCommand();

		// after creating everything, issue a new command: refresh elements
		/*

		// refresh the view
		// from Uml3DiagramUpdateCommand
		ICommand command2 = new RefreshElementCommand(rootObject,
				selectedElement.getEditingDomain(),
				parentView);
		
		OperationHistoryFactory.getOperationHistory().execute(command2,
				monitor, info);
		*/

		// TODO: make this CommandResult actually reflect something useful
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
	 * For a given EObject, get all the ExecutionEdges that go out of it.
	 * Default empty because not all diagram editors can display these edges.
	 * 
	 * @param object
	 * @return
	 */
	protected List<ExecutionEdge> getExecutionEdgesOut(EObject object) {
		return new ArrayList<ExecutionEdge>();	// default empty
	}
	
	/**
	 * For a given EObject, get all the ExecutionEdges that come into it.
	 * Default empty because not all diagram editors can display these edges.
	 * 
	 * @param object
	 * @return
	 */
	protected List<ExecutionEdge> getExecutionEdgesIn(EObject object) {
		return new ArrayList<ExecutionEdge>();	// default empty		
	}
	
	/**
	 * For a given EObject, get all the DataFlowEdges that go out of it.
	 * Default empty because not all diagram editors can display these edges.
	 * 
	 * @param object
	 * @return
	 */
	protected List<DataFlowEdge> getFlowEdgesOut(EObject object) {
		return new ArrayList<DataFlowEdge>();	// default empty		
	}
	
	/**
	 * For a given EObject, get all the DataFlowEdges that come into it.
	 * Default empty because not all diagram editors can display these edges.
	 * 
	 * @param object
	 * @return
	 */
	protected List<DataFlowEdge> getFlowEdgesIn(EObject object) {
		return new ArrayList<DataFlowEdge>();	// default empty		
	}
	
	protected List<WireEdge> getGeneratedWires(EObject object) {
		List<WireEdge> list = new ArrayList<WireEdge>();
		
		if (object instanceof GeneratesElements) {
			// add all the edges that we generated
			for (GeneratedElement g : ((GeneratesElements) object).getGeneratedElements()) {
				if (g instanceof WireEdge)
					list.add((WireEdge) g);
			}
		}
		
		return list;
	}
	
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
	 * @throws ExecutionException 
	 */
	protected void getCreateShortcutsCommand() throws ExecutionException {

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
		
		// hack
		connectionsOut.addAll(connectionsIn);
		connectionsIn.addAll(connectionsOut);

		// add all generated wires
		connectionsOut.addAll( this.getGeneratedWires(rootObject) );
		connectionsIn.addAll( this.getGeneratedWires(rootObject) );

		List<EObject> toAdd = new ArrayList<EObject>();
		List<EObject> toAddEdge = new ArrayList<EObject>();
		
		// get all the nodes and edges we want to add
		addWireEdgesOut(toAdd, toAddEdge, connectionsOut);
		addWireEdgesIn(toAdd, toAddEdge, connectionsIn);
		addExecutionEdgesOut(toAdd, toAddEdge, getExecutionEdgesOut(rootObject));
		addExecutionEdgesIn(toAdd, toAddEdge, getExecutionEdgesIn(rootObject));
		addFlowEdgesOut(toAdd, toAddEdge, getFlowEdgesOut(rootObject));
		addFlowEdgesIn(toAdd, toAddEdge, getFlowEdgesIn(rootObject));
		
		List<CreateViewRequest.ViewDescriptor> viewAdapters = new ArrayList<CreateViewRequest.ViewDescriptor>();
		
		// create a shortcut displaying it
		// we now have a list of Actions that we want to add to the current view
		for (EObject newObject : toAdd) {
			CreateViewRequest.ViewDescriptor viewDescriptor = new CreateViewRequest.ViewDescriptor(
					new EObjectAdapter(newObject), Node.class, null,  // tried null->"" with no effect
					prefHint);
			
			// create element
			command = new CreateCommand(
					selectedElement.getEditingDomain(), viewDescriptor, parentView);
			
			// add shortcut
			command = command.compose(new CreateShortcutDecorationsCommand(
					selectedElement.getEditingDomain(), parentView, viewDescriptor, this.getEditPartModelId()));
			
			// execute
			doExecute(command);		// execute it now

			// save the descriptor for later
			viewAdapters.add(viewDescriptor);

		}
		
		for (EObject newObjectEdge : toAddEdge) {
			// we should create a relationship edge now too
			// not sure if this actually works
			//String type = ElementTypeRegistry.getInstance().getType("org.openiaml.model.diagram.wire.RunInstanceWire_3002");
			
			CreateConnectionViewRequest.ConnectionViewDescriptor viewDescriptorEdge = new CreateConnectionViewRequest.ConnectionViewDescriptor(
					new EObjectAdapter(newObjectEdge), null,  // tried null->"" with no effect
					prefHint);
			
			command = new CreateCommand(
					selectedElement.getEditingDomain(), viewDescriptorEdge, parentView);
			doExecute(command);

			// save the descriptor for later
			viewAdapters.add(viewDescriptorEdge);

		}
		
		// now that we've created all of our elements, we can move the generated ones
		// this will automatically reposition elements into a line,
		// AND also refresh the view
		// AND also show all of the connections between shortcut elements
		DeferredLayoutCommand dlc = new DeferredLayoutCommand(selectedElement.getEditingDomain(),
				viewAdapters,
				selectedElement);
		doExecute(dlc);

		return;
		
	}

	/**
	 * Get all the EObjects (nodes and edges) that we want to add.
	 * 
	 * TODO refactor and clean up this class. 
	 * 
	 * @param toAdd
	 * @param toAddEdge
	 * @param connectionsOut
	 */
	protected void addWireEdgesOut(List<EObject> toAdd, List<EObject> toAddEdge, List<WireEdge> connectionsOut) {
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

			// if the edge is not already displayed with a shortcut
			boolean isVisibleEdge = false;
			for (Object o : selectedElement.getChildren()) {
				if (o instanceof EditPart && ((EditPart) o).getModel() instanceof Edge) {
					Edge onode = (Edge) ((EditPart) o).getModel();
					
					if (edge == onode.getElement()) {
						isVisibleEdge = true;
						break;
					}
				}
			}
			
			if (!isVisibleEdge && !toAddEdge.contains(edge)) {
				toAddEdge.add(edge);
			}
		}		
	}


	protected void addWireEdgesIn(List<EObject> toAdd, List<EObject> toAddEdge, List<WireEdge> connectionsIn) {
		// get all connections inwards that we need to add
		for (WireEdge edge : connectionsIn) {
			// if the connection is not already displayed with a shortcut
			boolean isVisible = false;
			for (Object o : selectedElement.getChildren()) {
				if (o instanceof EditPart && ((EditPart) o).getModel() instanceof Node) {
					Node onode = (Node) ((EditPart) o).getModel();
					
					if (edge.getFrom() == onode.getElement()) {
						isVisible = true;
						break;
					}
				}
			}
			
			if (!isVisible && !toAdd.contains(edge.getFrom())) {
				toAdd.add(edge.getFrom());
			}

			// if the edge is not already displayed with a shortcut
			boolean isVisibleEdge = false;
			for (Object o : selectedElement.getChildren()) {
				if (o instanceof EditPart && ((EditPart) o).getModel() instanceof Edge) {
					Edge onode = (Edge) ((EditPart) o).getModel();
					
					if (edge == onode.getElement()) {
						isVisibleEdge = true;
						break;
					}
				}
			}
			
			if (!isVisibleEdge && !toAddEdge.contains(edge)) {
				toAddEdge.add(edge);
			}
		
		}
	}
	

	/**
	 * Get all the EObjects (nodes and edges) that we want to add.
	 * 
	 * TODO refactor and clean up this class. 
	 * 
	 * @param toAdd
	 * @param toAddEdge
	 * @param connectionsOut
	 */
	protected void addExecutionEdgesOut(List<EObject> toAdd, List<EObject> toAddEdge, List<ExecutionEdge> connectionsOut) {
		// get all connections outwards that we need to add
		for (ExecutionEdge edge : connectionsOut) {
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

			// if the edge is not already displayed with a shortcut
			boolean isVisibleEdge = false;
			for (Object o : selectedElement.getChildren()) {
				if (o instanceof EditPart && ((EditPart) o).getModel() instanceof Edge) {
					Edge onode = (Edge) ((EditPart) o).getModel();
					
					if (edge == onode.getElement()) {
						isVisibleEdge = true;
						break;
					}
				}
			}
			
			if (!isVisibleEdge && !toAddEdge.contains(edge)) {
				toAddEdge.add(edge);
			}
		}		
	}


	protected void addExecutionEdgesIn(List<EObject> toAdd, List<EObject> toAddEdge, List<ExecutionEdge> connectionsIn) {
		// get all connections inwards that we need to add
		for (ExecutionEdge edge : connectionsIn) {
			// if the connection is not already displayed with a shortcut
			boolean isVisible = false;
			for (Object o : selectedElement.getChildren()) {
				if (o instanceof EditPart && ((EditPart) o).getModel() instanceof Node) {
					Node onode = (Node) ((EditPart) o).getModel();
					
					if (edge.getFrom() == onode.getElement()) {
						isVisible = true;
						break;
					}
				}
			}
			
			if (!isVisible && !toAdd.contains(edge.getFrom())) {
				toAdd.add(edge.getFrom());
			}

			// if the edge is not already displayed with a shortcut
			boolean isVisibleEdge = false;
			for (Object o : selectedElement.getChildren()) {
				if (o instanceof EditPart && ((EditPart) o).getModel() instanceof Edge) {
					Edge onode = (Edge) ((EditPart) o).getModel();
					
					if (edge == onode.getElement()) {
						isVisibleEdge = true;
						break;
					}
				}
			}
			
			if (!isVisibleEdge && !toAddEdge.contains(edge)) {
				toAddEdge.add(edge);
			}
		
		}
	}

	/**
	 * Get all the EObjects (nodes and edges) that we want to add.
	 * 
	 * TODO refactor and clean up this class. 
	 * 
	 * @param toAdd
	 * @param toAddEdge
	 * @param connectionsOut
	 */
	protected void addFlowEdgesOut(List<EObject> toAdd, List<EObject> toAddEdge, List<DataFlowEdge> connectionsOut) {
		// get all connections outwards that we need to add
		for (DataFlowEdge edge : connectionsOut) {
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

			// if the edge is not already displayed with a shortcut
			boolean isVisibleEdge = false;
			for (Object o : selectedElement.getChildren()) {
				if (o instanceof EditPart && ((EditPart) o).getModel() instanceof Edge) {
					Edge onode = (Edge) ((EditPart) o).getModel();
					
					if (edge == onode.getElement()) {
						isVisibleEdge = true;
						break;
					}
				}
			}
			
			if (!isVisibleEdge && !toAddEdge.contains(edge)) {
				toAddEdge.add(edge);
			}
		}		
	}


	protected void addFlowEdgesIn(List<EObject> toAdd, List<EObject> toAddEdge, List<DataFlowEdge> connectionsIn) {
		// get all connections inwards that we need to add
		for (DataFlowEdge edge : connectionsIn) {
			// if the connection is not already displayed with a shortcut
			boolean isVisible = false;
			for (Object o : selectedElement.getChildren()) {
				if (o instanceof EditPart && ((EditPart) o).getModel() instanceof Node) {
					Node onode = (Node) ((EditPart) o).getModel();
					
					if (edge.getFrom() == onode.getElement()) {
						isVisible = true;
						break;
					}
				}
			}
			
			if (!isVisible && !toAdd.contains(edge.getFrom())) {
				toAdd.add(edge.getFrom());
			}

			// if the edge is not already displayed with a shortcut
			boolean isVisibleEdge = false;
			for (Object o : selectedElement.getChildren()) {
				if (o instanceof EditPart && ((EditPart) o).getModel() instanceof Edge) {
					Edge onode = (Edge) ((EditPart) o).getModel();
					
					if (edge == onode.getElement()) {
						isVisibleEdge = true;
						break;
					}
				}
			}
			
			if (!isVisibleEdge && !toAddEdge.contains(edge)) {
				toAddEdge.add(edge);
			}
		
		}
	}

	/**
	 * A helper method to help us execute lots of steps easily
	 * 
	 * @param command the command to execute
	 * @throws ExecutionException
	 * @see doExecuteWithResult()
	 */
	protected void doExecute(ICommand command) throws ExecutionException {
		IProgressMonitor monitor = new NullProgressMonitor();
		IAdaptable info = null;
		OperationHistoryFactory.getOperationHistory().execute(command,
				monitor, info);
	}

}