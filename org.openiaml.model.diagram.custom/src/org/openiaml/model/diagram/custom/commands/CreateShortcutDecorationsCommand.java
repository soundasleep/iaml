package org.openiaml.model.diagram.custom.commands;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;

/**
 * Based on generated org.openiaml.model.model.diagram.edit.commands.IamlCreateShortcutDecorationsCommand
 * 
 */
public class CreateShortcutDecorationsCommand extends
		AbstractTransactionalCommand {

	private List<CreateViewRequest.ViewDescriptor> myDescriptors;
	private String modelId;

	/**
	 * @param modelEditPart the ID to use for the annotation, e.g. PageEditPart.MODEL_ID
	 */
	public CreateShortcutDecorationsCommand(
			TransactionalEditingDomain editingDomain, View parentView,
			List<CreateViewRequest.ViewDescriptor> viewDescriptors,
			String modelId) {
		super(editingDomain, "Create Shortcuts", getWorkspaceFiles(parentView)); //$NON-NLS-1$
		myDescriptors = viewDescriptors;
		this.modelId = modelId;
		
	}

	/**
	 * @param modelEditPart the ID to use for the annotation, e.g. PageEditPart.MODEL_ID
	 */
	public CreateShortcutDecorationsCommand(
			TransactionalEditingDomain editingDomain, View parentView,
			CreateViewRequest.ViewDescriptor viewDescriptor,
			String modelId) {
		this(editingDomain, parentView, Collections
				.singletonList(viewDescriptor), modelId);
	}

	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		for (CreateViewRequest.ViewDescriptor nextDescriptor : myDescriptors) {
			View view = (View) nextDescriptor.getAdapter(View.class);
			if (view != null && view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE
						.createEAnnotation();
				shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
				shortcutAnnotation.getDetails().put(
						"modelID", modelId); //$NON-NLS-1$
				view.getEAnnotations().add(shortcutAnnotation);
			}
		}
		return CommandResult.newOKCommandResult();
	}
}
