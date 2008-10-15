package org.openiaml.model.diagram.custom.edit.providers;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.diagram.visual.edit.commands.SyncWireCreateCommand;
import org.openiaml.model.model.diagram.visual.providers.IamlElementTypes;
import org.openiaml.model.model.wires.SyncWire;

public class CreateMissingVisualElementsCommand extends AbstractTransactionalCommand {

	private EObject rootObject;
	private GraphicalEditPart selectedElement;
	private View parentView;
	private PreferencesHint prefHint;
	private IProgressMonitor monitor;
	private IAdaptable info;

	public CreateMissingVisualElementsCommand(
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

	public CreateMissingVisualElementsCommand(GraphicalEditPart root,
			EObject object, PreferencesHint prefHint) {
		this(root, object, root.getEditingDomain(), (View) root.getModel(), prefHint);
	}

	public CreateMissingVisualElementsCommand(GraphicalEditPart root, PreferencesHint prefHint) {
		// we used to only refresh the EObject of the container, not the actual EObject instance
		this(root, (EObject) ((Diagram) root.getModel()).getElement(), prefHint);
	}

	/**
	 * A helper method to help us execute lots of steps easily
	 * 
	 * @param command the command to execute
	 * @throws ExecutionException
	 * @see doExecuteWithResult()
	 */
	protected void doExecute(ICommand command) throws ExecutionException {
		OperationHistoryFactory.getOperationHistory().execute(command,
				monitor, info);
	}

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {

		this.monitor = monitor;
		this.info = info;
		
		Assert.isTrue(rootObject instanceof VisibleThing);
		
		VisibleThing vt = (VisibleThing) rootObject;
		
		// debug message
		// MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Warning", "Not yet implemented. But here are the objects we have: object=" + this.rootObject + " view=" + this.parentView);
		
		for (ApplicationElement e : vt.getChildren()) {
			
			// get all the input forms
			if (e instanceof ApplicationElementContainer) {
				ApplicationElementContainer f = (ApplicationElementContainer) e;
				
				// get all the wires
				for (WireEdge w : f.getOutEdges()) {
					// get all the sync wires
					
					if (w instanceof SyncWire && ((SyncWire) w).getTo() instanceof ApplicationElementContainer) {
						// sync up these elements
						doSyncWires((ApplicationElementContainer) w.getFrom(), (ApplicationElementContainer) w.getTo());
						// and back again
						doSyncWires((ApplicationElementContainer) w.getTo(), (ApplicationElementContainer) w.getFrom());
					}
				}
				
			}
			
		}
		
		/*
		// get all the nodes in the model tree
		final View rootView = (View) childElement.getModel();
		LinkComponent rootObject = (LinkComponent) rootView.getElement();

		// create an element Event
		CreateElementCommand cc = new CreateElementCommand(new CreateElementRequest(rootObject, Uml3ElementTypes.Event_1003));
		doExecute(cc);		// exceptions will pass through 

		Event eo = (Event) cc.getNewElement();
		
		// another version: SetValueCommand sv = new SetValueCommand(new SetRequest(eo, eo.eClass().getEStructuralFeature("name"), "test"));
		SetValueCommand sv = new SetValueCommand(new SetRequest(eo, ModelPackage.eINSTANCE.getAction_Name(), "test"));
		doExecute(sv);
		*/
		
		return CommandResult.newOKCommandResult();
		
	}
	
	/**
	 * A SyncWire between a source and a target: all of the components in the
	 * source should be linked up with the target. needs to be called twice to
	 * sync up bidirectionally.
	 * 
	 * @param source
	 * @param target
	 * @throws ExecutionException 
	 */
	protected void doSyncWires(ApplicationElementContainer source, ApplicationElementContainer target) throws ExecutionException {
		// first, source --> target
		for (ApplicationElement c : source.getChildren()) {
			ApplicationElement mapTarget = getChildMatch(c, target);
			if (mapTarget != null) {
				// we have an element to map to
				// is it already mapped?
				if (!elementsAreAlreadySyncWire(c, mapTarget)) {
					// map them together
					//CreateRelationshipCommand cc = new CreateRelationshipCommand(new CreateRelationshipRequest( c, c, mapTarget, IamlElementTypes.SyncWire_3001, ModelPackage.eINSTANCE.getContainsWires_Wires() ));
					CreateElementCommand cc = new SyncWireCreateCommand(new CreateRelationshipRequest( rootObject, c, mapTarget, IamlElementTypes.SyncWire_3001 ), c, mapTarget );
					doExecute(cc);
					
					SyncWire createdElement = (SyncWire) cc.getNewElement();
					Assert.isTrue(createdElement != null); 	// must have been created
					
					// set sync wire parameters
					SetValueCommand sv = new SetValueCommand(new SetRequest(createdElement, ModelPackage.eINSTANCE.getNamedElement_Name(), "sync[generated]"));
					doExecute(sv);
				}
			}
		}
	}
	
	/**
	 * are two elements already sync wired together?
	 * 
	 * @param c
	 * @param mapTarget
	 * @return
	 */
	private boolean elementsAreAlreadySyncWire(ApplicationElement c,
			ApplicationElement mapTarget) {
		
		for (WireEdge w : c.getOutEdges()) {
			if (w.getTo().equals(mapTarget) && w instanceof SyncWire)
				return true;
		}
		for (WireEdge w : c.getInEdges()) {
			if (w.getFrom().equals(mapTarget) && w instanceof SyncWire)
				return true;
		}
		
		return false;
		
	}

	/**
	 * Get a child in the targetParent that matches the source element
	 * 
	 * @see #childrenMapUp(ApplicationElement, ApplicationElement)
	 * @param source
	 * @param targetParent
	 * @return an ApplicationElement, or null if none match
	 */
	private ApplicationElement getChildMatch(ApplicationElement source, ApplicationElementContainer targetParent) {
		for (ApplicationElement c : targetParent.getChildren()) {
			if (childrenMapUp(source, c))
				return c;
		}
		return null;
	}
	
	/**
	 * Do two elements match up, for sync purposes? In our case, we say they
	 * do if they have the same name (case insensitive)
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	private boolean childrenMapUp(ApplicationElement source, ApplicationElement target) {
		return source.getName().toLowerCase().equals(target.getName().toLowerCase());
	}

}
