/**
 *
 */
package org.openiaml.model.diagram.custom.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.openiaml.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.inference.EcoreCreateElementsHelper;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;

/**
 * A GMF-based inference handler. Extracted from old InferMissingElementsCommand.
 *
 * @author jmwright
 *
 */
public class GmfInferenceHandler extends EcoreCreateElementsHelper implements ICreateElements {

	private IProgressMonitor monitor;
	private IAdaptable info;
	private String editorId;
	private TransactionalEditingDomain editingDomain;

	/**
	 * Create a new handler using the required information.
	 *
	 * @param monitor
	 * @param info
	 * @param editorId
	 * @param editingDomain
	 */
	public GmfInferenceHandler(IProgressMonitor monitor, IAdaptable info,
			String editorId, TransactionalEditingDomain editingDomain) {
		super();
		this.monitor = monitor;
		this.info = info;
		this.editorId = editorId;
		this.editingDomain = editingDomain;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#createElement(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@Override
	public EObject createElement(final EObject container, EClass elementType,
			EStructuralFeature containerFeature) throws InferenceException {
		try {
			if (container == null)
				// return null;
				throw new InferenceException("Cannot create an element in a null container");

			IElementType et = getDiagramEditType(elementType);

			// GMF 2.2: XXXCreateCommands no longer extend CreateElementCommand, so
			// we must get the newly created element from the CreateElementRequest instead
			CreateElementRequest request = new CreateElementRequest(getEditingDomain(), container, et );
			EditElementCommand cc = getDiagramCreateNodeCommand(request, elementType );
			if (cc == null) {
				// we can't do anything because the diagram editor won't allow us to create it currently
				//return null;
				throw new InferenceException("Cannot create an element " + elementType + " in the editing domain " + getEditingDomain());
			}
			doExecute(cc);

			//EObject createdElement = cc.getNewElement();
			EObject createdElement = request.getNewElement();

			return createdElement;
		} catch (ExecutionException e) {
			throw new InferenceException(e);
		}
	}

	/**
	 * @return The editing domain to operate in
	 */
	protected TransactionalEditingDomain getEditingDomain() {
		return editingDomain;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#createRelationship(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@Override
	public EObject createRelationship(EObject container, EClass elementType,
			EObject source, EObject target,
			EStructuralFeature containerFeature,
			EStructuralFeature sourceFeature, EStructuralFeature targetFeature)
			throws InferenceException {
		try {
			if (container == null || source == null || target == null)
				//return null;
				throw new InferenceException("Cannot create a relationship in a null container, source or target");

			// GMF 2.2: XXXCreateCommands no longer extend CreateElementCommand, so
			// we must get the newly created element from the CreateElementRequest instead
			CreateRelationshipRequest request = new CreateRelationshipRequest(getEditingDomain(), container, source, target, getDiagramEditType(elementType) );
			EditElementCommand cc = getDiagramCreateRelationshipCommand(request, elementType, source, target );
			if (cc == null) {
				// we can't do anything because the diagram editor won't allow us to create it currently
				//return null;
				throw new InferenceException("Cannot create a relationship " + elementType + " in the editing domain " + getEditingDomain());
			}
			doExecute(cc);

			//EObject createdElement = cc.getNewElement();
			EObject createdElement = request.getNewElement();

			return createdElement;
		} catch (ExecutionException e) {
			throw new InferenceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#setValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
	 */
	@Override
	public void setValue(EObject element, EStructuralFeature reference,
			Object value) throws InferenceException {
		try {
			if (element == null)
				//return;
				throw new InferenceException("Cannot set a value on a null element");

			SetValueCommand sv = new SetValueCommand(new SetRequest(getEditingDomain(), element, reference, value));
			if (sv == null) {
				// we can't do anything because the diagram editor won't allow us to create it currently
				//return;
				throw new InferenceException("Cannot set a value " + reference + " on the element " + element);
			}
			doExecute(sv);
		} catch (ExecutionException e) {
			throw new InferenceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#setValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
	 */
	@Override
	public void addReference(EObject element, EStructuralFeature reference,
			Object value) throws InferenceException {
		try {
			if (element == null)
				//return;
				throw new InferenceException("Cannot set a value on a null element");

			AddReferenceCommand sv = new AddReferenceCommand(new SetRequest(getEditingDomain(), element, reference, value));
			if (sv == null) {
				// we can't do anything because the diagram editor won't allow us to create it currently
				//return;
				throw new InferenceException("Cannot set a value " + reference + " on the element " + element);
			}
			doExecute(sv);
		} catch (ExecutionException e) {
			throw new InferenceException(e);
		}
	}

	/**
	 * TODO this class needs testing
	 *
	 * @author jmwright
	 */
	public class AddReferenceCommand extends EditElementCommand {

		private SetRequest request;

		/**
		 * @param setRequest
		 */
		public AddReferenceCommand(SetRequest setRequest) {
			super("Add reference command", setRequest.getElementToEdit(), setRequest);
			this.request = setRequest;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
		 */
		@Override
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
				IAdaptable info) throws ExecutionException {

			EList<Object> existing = (EList<Object>) request.getElementToEdit().eGet(request.getFeature());
			existing.add(request.getValue());
			return CommandResult.newOKCommandResult();

		}

	}

	/**
	 * A helper method to help us execute lots of steps easily
	 *
	 * @param command the command to execute
	 * @throws ExecutionException if an execution exception occured
	 * @see doExecuteWithResult()
	 */
	protected void doExecute(ICommand command) throws ExecutionException {
		IStatus status = OperationHistoryFactory.getOperationHistory().execute(command,
				monitor, info);

		if (status.getSeverity() == IStatus.ERROR) {
			// log message
			IamlDiagramEditorPlugin.getInstance().logError(
					status.getMessage(), status.getException());

			if (status.getException() != null) {
				throw new ExecutionException(status.getMessage(), status.getException());
			}
			throw new ExecutionException(status.getMessage());
		}

		// else: warn
		if (!status.isOK()) {
			IamlDiagramEditorPlugin.getInstance().logError(
					status.getMessage(), status.getException());
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
	protected EditElementCommand getDiagramCreateRelationshipCommand(
			CreateRelationshipRequest request,
			EClass elementType, EObject source, EObject target) throws ExecutionException {
		/*
		 * If any of these methods do not exist, make sure dynamic templates
		 * are enabled for the .gmfgens.
		 */
		if (this.editorId.equals(org.openiaml.model.diagram.element.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.diagram.element.providers.IamlElementTypes.getCreateEdgeCommand(request, elementType, source, target);
		}
		if (this.editorId.equals(org.openiaml.model.diagram.visual.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.diagram.visual.providers.IamlElementTypes.getCreateEdgeCommand(request, elementType, source, target);
		}
		if (this.editorId.equals(org.openiaml.model.diagram.schema.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.diagram.schema.providers.IamlElementTypes.getCreateEdgeCommand(request, elementType, source, target);
		}
		if (this.editorId.equals(org.openiaml.model.diagram.iterator.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.diagram.iterator.providers.IamlElementTypes.getCreateEdgeCommand(request, elementType, source, target);
		}
		if (this.editorId.equals(org.openiaml.model.diagram.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.diagram.providers.IamlElementTypes.getCreateEdgeCommand(request, elementType, source, target);
		}

		throw new ExecutionException("Unknown editor ID: "  + this.editorId);

	}

	/**
	 * @param elementType
	 * @return
	 * @throws ExecutionException
	 */
	protected IElementType getDiagramEditType(EClass elementType) throws ExecutionException {
		/*
		 * If any of these methods do not exist, make sure dynamic templates
		 * are enabled for the .gmfgens.
		 */
		if (this.editorId.equals(org.openiaml.model.diagram.element.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.diagram.element.providers.IamlElementTypes.getElementType(elementType);
		}
		if (this.editorId.equals(org.openiaml.model.diagram.visual.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.diagram.visual.providers.IamlElementTypes.getElementType(elementType);
		}
		if (this.editorId.equals(org.openiaml.model.diagram.schema.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.diagram.schema.providers.IamlElementTypes.getElementType(elementType);
		}
		if (this.editorId.equals(org.openiaml.model.diagram.iterator.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.diagram.iterator.providers.IamlElementTypes.getElementType(elementType);
		}
		if (this.editorId.equals(org.openiaml.model.diagram.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.diagram.providers.IamlElementTypes.getElementType(elementType);
		}

		throw new ExecutionException("Unknown editor ID: "  + this.editorId);
	}

	/**
	 * @param createElementRequest
	 * @param elementType
	 * @return
	 * @throws ExecutionException
	 */
	protected EditElementCommand getDiagramCreateNodeCommand(
			CreateElementRequest request, EClass elementType) throws ExecutionException {
		/*
		 * If any of these methods do not exist, make sure dynamic templates
		 * are enabled for the .gmfgens.
		 */
		if (this.editorId.equals(org.openiaml.model.diagram.visual.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.diagram.visual.providers.IamlElementTypes.getCreateNodeCommand(request, elementType);
		}
		if (this.editorId.equals(org.openiaml.model.diagram.schema.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.diagram.schema.providers.IamlElementTypes.getCreateNodeCommand(request, elementType);
		}
		if (this.editorId.equals(org.openiaml.model.diagram.iterator.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.diagram.iterator.providers.IamlElementTypes.getCreateNodeCommand(request, elementType);
		}
		if (this.editorId.equals(org.openiaml.model.diagram.part.IamlDiagramEditorPlugin.ID)) {
			return org.openiaml.model.diagram.providers.IamlElementTypes.getCreateNodeCommand(request, elementType);
		}

		throw new ExecutionException("Unknown editor ID: "  + this.editorId);

	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#deleteElement(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@Override
	public void deleteElement(EObject object, EObject container,
			EStructuralFeature containerFeature)
			throws InferenceException {

		// TODO implement
		throw new InferenceException("Cannot yet delete from GmfInferenceHandler");
	}

}
