package org.openiaml.model.diagram.custom.commands.generation;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
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
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;

public class InferMissingElementsCommand extends AbstractTransactionalCommand implements ICreateElements {

	private EObject rootObject;
	private GraphicalEditPart selectedElement;
	private View parentView;
	private PreferencesHint prefHint;
	private IProgressMonitor monitor;
	private IAdaptable info;
	private String editorId;
	
	/**
	 * Temporary toggle switch to disable automatic element inferring  
	 */
	public boolean isDisabled = true;
	
	public InferMissingElementsCommand(
			GraphicalEditPart root,
			EObject rootObject,
			TransactionalEditingDomain editingDomain, 
			View parentView,
			PreferencesHint prefHint,
			String editorId) {			
		super(editingDomain, "Infer missing model elements", getWorkspaceFiles(parentView)); //$NON-NLS-1$
		selectedElement = root;
		this.parentView = parentView;
		this.rootObject = rootObject;
		this.prefHint = prefHint;
		this.editorId = editorId;
	}

	public InferMissingElementsCommand(GraphicalEditPart root,
			EObject object, PreferencesHint prefHint, String editorId) {
		this(root, object, root.getEditingDomain(), (View) root.getModel(), prefHint, editorId);
	}

	public InferMissingElementsCommand(GraphicalEditPart root, PreferencesHint prefHint, String editorId) {
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
		
		if (!isDisabled) {
			CreateMissingElementsWithDrools ce = new CreateMissingElementsWithDrools(this);
			try {
				ce.create(rootObject);
			} catch (InferenceException e) {
				throw new ExecutionException("unexpected exception when trying to infer missing elements", e);
			}
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


	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#setValue(org.openiaml.model.model.wires.SyncWire, org.eclipse.emf.ecore.EReference, java.lang.Object)
	 */
	@Override
	public void setValue(EObject element, EStructuralFeature reference, Object value) throws InferenceException {
		try {
			if (element == null)
				return;
			
			SetValueCommand sv = new SetValueCommand(new SetRequest(getEditingDomain(), element, reference, value));
			if (sv == null) {
				// we can't do anything because the diagram editor won't allow us to create it currently
				return;
			}
			doExecute(sv);
		} catch (ExecutionException e) {
			throw new InferenceException(e);
		}
		
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#createElement(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EClass)
	 */
	@Override
	public EObject createElement(EObject container, EClass elementType, EStructuralFeature ignored)
			throws InferenceException {
		try {
			if (container == null)
				return null;
			
			CreateElementCommand cc = getDiagramCreateNodeCommand(new CreateElementRequest(getEditingDomain(), container, getDiagramEditType(elementType) ), elementType );
			if (cc == null) {
				// we can't do anything because the diagram editor won't allow us to create it currently
				return null;
			}
			doExecute(cc);
			
			EObject createdElement = cc.getNewElement();
			
			return createdElement;
		} catch (ExecutionException e) {
			throw new InferenceException(e);
		}
	}


	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#createRelationship(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public EObject createRelationship(EObject container, EClass elementType,
			EObject source, EObject target, EStructuralFeature ignored, EStructuralFeature ignored2, EStructuralFeature ignored3) throws InferenceException {
		try {
			if (container == null || source == null || target == null)
				return null;
			
			CreateElementCommand cc = getDiagramCreateRelationshipCommand(new CreateRelationshipRequest(getEditingDomain(), container, source, target, getDiagramEditType(elementType) ), elementType, source, target );
			if (cc == null) {
				// we can't do anything because the diagram editor won't allow us to create it currently
				return null;
			}
			doExecute(cc);
			
			EObject createdElement = cc.getNewElement();
			
			return createdElement;
		} catch (ExecutionException e) {
			throw new InferenceException(e);
		}
	}

	/**
	 * @param createRelationshipRequest
	 * @param source
	 * @param target
	 * @param target 
	 * @return
	 * @throws ExecutionException 
	 */
	private CreateElementCommand getDiagramCreateRelationshipCommand(
			CreateRelationshipRequest request,
			EClass elementType, EObject source, EObject target) throws ExecutionException {
		/*
		 * If any of these methods do not exist, make sure dynamic templates
		 * are enabled for the .gmfgens.
		 */
		if (this.editorId.equals(org.openiaml.model.model.diagram.element.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.element.providers.IamlElementTypes.getCreateEdgeCommand(request, elementType, source, target);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.wire.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.wire.providers.IamlElementTypes.getCreateEdgeCommand(request, elementType, source, target);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.visual.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.visual.providers.IamlElementTypes.getCreateEdgeCommand(request, elementType, source, target);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.domainstore.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.domainstore.providers.IamlElementTypes.getCreateEdgeCommand(request, elementType, source, target);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.domain_object.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.domain_object.providers.IamlElementTypes.getCreateEdgeCommand(request, elementType, source, target);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.providers.IamlElementTypes.getCreateEdgeCommand(request, elementType, source, target);
		}
		
		throw new ExecutionException("Unknown editor ID: "  + this.editorId);

	}

	/**
	 * @param elementType
	 * @return
	 * @throws ExecutionException 
	 */
	private IElementType getDiagramEditType(EClass elementType) throws ExecutionException {
		/*
		 * If any of these methods do not exist, make sure dynamic templates
		 * are enabled for the .gmfgens.
		 */
		if (this.editorId.equals(org.openiaml.model.model.diagram.element.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.element.providers.IamlElementTypes.getElementType(elementType);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.wire.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.wire.providers.IamlElementTypes.getElementType(elementType);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.visual.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.visual.providers.IamlElementTypes.getElementType(elementType);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.domainstore.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.domainstore.providers.IamlElementTypes.getElementType(elementType);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.domain_object.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.domain_object.providers.IamlElementTypes.getElementType(elementType);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.providers.IamlElementTypes.getElementType(elementType);
		}
		
		throw new ExecutionException("Unknown editor ID: "  + this.editorId);
	}

	/**
	 * @param createElementRequest
	 * @param elementType
	 * @return
	 * @throws ExecutionException 
	 */
	private CreateElementCommand getDiagramCreateNodeCommand(
			CreateElementRequest request, EClass elementType) throws ExecutionException {
		/*
		 * If any of these methods do not exist, make sure dynamic templates
		 * are enabled for the .gmfgens.
		 */
		if (this.editorId.equals(org.openiaml.model.model.diagram.visual.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.visual.providers.IamlElementTypes.getCreateNodeCommand(request, elementType);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.domainstore.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.domainstore.providers.IamlElementTypes.getCreateNodeCommand(request, elementType);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.domain_object.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.domain_object.providers.IamlElementTypes.getCreateNodeCommand(request, elementType);
		}
		if (this.editorId.equals(org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.model.diagram.providers.IamlElementTypes.getCreateNodeCommand(request, elementType);
		}
		
		throw new ExecutionException("Unknown editor ID: "  + this.editorId);
		
	}
	
}
