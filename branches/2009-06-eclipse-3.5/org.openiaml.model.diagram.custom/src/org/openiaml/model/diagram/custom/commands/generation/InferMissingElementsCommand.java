package org.openiaml.model.diagram.custom.commands.generation;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.openiaml.model.diagram.custom.commands.GmfInferenceHandler;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.inference.EcoreCreateElementsHelper;
import org.openiaml.model.inference.InferenceException;

public class InferMissingElementsCommand extends AbstractTransactionalCommand {

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

		EcoreCreateElementsHelper helper = new GmfInferenceHandler(monitor, info, editorId, getEditingDomain());

		if (!isDisabled) {
			CreateMissingElementsWithDrools ce = new CreateMissingElementsWithDrools(helper, false);
			try {
				ce.create(rootObject, monitor);
			} catch (InferenceException e) {
				throw new ExecutionException("Unexpected exception when trying to infer missing elements", e);
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
	
}
