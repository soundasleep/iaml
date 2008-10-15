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
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.wires.SyncWire;

public class CreateMissingSyncWireElementsCommand extends AbstractTransactionalCommand {

	private EObject rootObject;
	private GraphicalEditPart selectedElement;
	private View parentView;
	private PreferencesHint prefHint;
	private IProgressMonitor monitor;
	private IAdaptable info;
	private String editorId;

	public CreateMissingSyncWireElementsCommand(
			GraphicalEditPart root,
			EObject rootObject,
			TransactionalEditingDomain editingDomain, 
			View parentView,
			PreferencesHint prefHint,
			String editorId) {			
		super(editingDomain, "Refresh element view", getWorkspaceFiles(parentView)); //$NON-NLS-1$
		selectedElement = root;
		this.parentView = parentView;
		this.rootObject = rootObject;
		this.prefHint = prefHint;
		this.editorId = editorId;
	}

	public CreateMissingSyncWireElementsCommand(GraphicalEditPart root,
			EObject object, PreferencesHint prefHint, String editorId) {
		this(root, object, root.getEditingDomain(), (View) root.getModel(), prefHint, editorId);
	}

	public CreateMissingSyncWireElementsCommand(GraphicalEditPart root, PreferencesHint prefHint, String editorId) {
		// we used to only refresh the EObject of the container, not the actual EObject instance
		this(root, (EObject) ((Diagram) root.getModel()).getElement(), prefHint, editorId);
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
		
		// debug message
		// MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Warning", "Not yet implemented. But here are the objects we have: object=" + this.rootObject + " view=" + this.parentView);
		if (rootObject instanceof InternetApplication) {
			InternetApplication vt = (InternetApplication) rootObject;

			for (ApplicationElement e : vt.getChildren()) {
				handleChild(e);
			}
		}
		if (rootObject instanceof ApplicationElementContainer) {
			ApplicationElementContainer vt = (ApplicationElementContainer) rootObject;

			for (ApplicationElement e : vt.getChildren()) {
				handleChild(e);
			}
		}
		if (rootObject instanceof DomainStore) {
			DomainStore vt = (DomainStore) rootObject;

			for (ApplicationElement e : vt.getChildren()) {
				handleChild(e);
			}
		}
		if (rootObject instanceof DomainObject) {
			DomainObject vt = (DomainObject) rootObject;

			for (ApplicationElement e : vt.getAttributes()) {
				handleChild(e);
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
	
	private void handleChild(ApplicationElement e) throws ExecutionException {
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
					CreateElementCommand cc = getSyncWireCreateCommand(new CreateRelationshipRequest( rootObject, c, mapTarget, getSyncWireEditType() ), c, mapTarget );
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
	 * Because we don't want to be duplicating logic, we use this to select which command
	 * in particular we actually want for a given editor.
	 * 
	 * @see #editorId
	 * @param cr
	 * @param source
	 * @param target
	 * @return
	 * @throws ExecutionException if editorId was unexpected
	 */
	private CreateElementCommand getSyncWireCreateCommand(CreateRelationshipRequest cr, EObject source, EObject target) throws ExecutionException {
		if (this.editorId.equals(org.openiaml.model.model.diagram.visual.part.IamlDiagramEditorPlugin.ID)) {
			return new org.openiaml.model.model.diagram.visual.edit.commands.SyncWireCreateCommand(cr, source, target);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.domainstore.part.IamlDiagramEditorPlugin.ID)) {
			return new org.openiaml.model.model.diagram.domainstore.edit.commands.SyncWireCreateCommand(cr, source, target);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.domain_object.part.IamlDiagramEditorPlugin.ID)) {
			return new org.openiaml.model.model.diagram.domain_object.edit.commands.SyncWireCreateCommand(cr, source, target);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin.ID)) {
			return new org.openiaml.model.model.diagram.edit.commands.SyncWireCreateCommand(cr, source, target);
		}
		
		throw new ExecutionException("Unknown editor ID: "  + this.editorId);
	}
	
	/**
	 * Because we don't want to be duplicating logic, we use this to select which element type
	 * in particular we actually want for a given editor.
	 * 
	 * @see #editorId
	 * @return
	 * @throws ExecutionException if editorId was unexpected
	 */
	private IElementType getSyncWireEditType() throws ExecutionException {
		if (this.editorId.equals(org.openiaml.model.model.diagram.visual.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.visual.providers.IamlElementTypes.SyncWire_3001;
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.domainstore.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.domainstore.providers.IamlElementTypes.SyncWire_3001;
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.domain_object.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.domain_object.providers.IamlElementTypes.SyncWire_3002;
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.providers.IamlElementTypes.SyncWire_3001;
		}
		
		throw new ExecutionException("Unknown editor ID: "  + this.editorId);
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
