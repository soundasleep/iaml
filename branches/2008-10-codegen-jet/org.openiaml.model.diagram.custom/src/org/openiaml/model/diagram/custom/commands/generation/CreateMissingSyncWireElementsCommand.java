package org.openiaml.model.diagram.custom.commands.generation;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.openiaml.model.inference.CreateMissingElements;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.wires.SyncWire;

public class CreateMissingSyncWireElementsCommand extends AbstractTransactionalCommand implements ICreateElements {

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
		super(editingDomain, "Create missing SyncWire elements", getWorkspaceFiles(parentView)); //$NON-NLS-1$
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
		
		CreateMissingElements ce = new CreateMissingElements();
		try {
			ce.create(this, rootObject);
		} catch (InferenceException e) {
			throw new ExecutionException("unexpected exception when trying to infer missing elements", e);
		}
		
		// debug message
		// MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Warning", "Not yet implemented. But here are the objects we have: object=" + this.rootObject + " view=" + this.parentView);

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


	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#setValue(org.openiaml.model.model.wires.SyncWire, org.eclipse.emf.ecore.EReference, java.lang.Object)
	 */
	@Override
	public void setValue(EObject element, EStructuralFeature reference, Object value) throws InferenceException {
		try {
			SetValueCommand sv = new SetValueCommand(new SetRequest(element, reference, value));
			doExecute(sv);
		} catch (ExecutionException e) {
			throw new InferenceException(e);
		}
		
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#createSyncWire(org.openiaml.model.model.ContainsWires, org.openiaml.model.model.WireEdgesSource, org.openiaml.model.model.WireEdgeDestination)
	 */
	@Override
	public SyncWire createSyncWire(ContainsWires container,
			WireEdgesSource source, WireEdgeDestination target)
			throws InferenceException {
		try {
			CreateElementCommand cc = getSyncWireCreateCommand(new CreateRelationshipRequest( container, source, target, getSyncWireEditType() ), source, target );
			doExecute(cc);
			
			SyncWire createdElement = (SyncWire) cc.getNewElement();
			
			return createdElement;
		} catch (ExecutionException e) {
			throw new InferenceException(e);
		}
	}

}
