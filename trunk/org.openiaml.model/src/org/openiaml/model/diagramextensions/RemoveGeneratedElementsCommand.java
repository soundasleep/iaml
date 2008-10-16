package org.openiaml.model.diagramextensions;

import java.util.ArrayList;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.CompositeEMFOperation;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.ModelPackage;

public class RemoveGeneratedElementsCommand extends AbstractTransactionalCommand {

	private EObject rootObject;
	private EditPart selectedElement;
	private PreferencesHint prefHint;
	private IProgressMonitor monitor;
	private IAdaptable info;

	public RemoveGeneratedElementsCommand(
			EditPart root,
			EObject rootObject,
			TransactionalEditingDomain editingDomain, 
			PreferencesHint prefHint) {			
		super(editingDomain, "Removing generated elements", getWorkspaceFiles( new ArrayList() )); //$NON-NLS-1$
		selectedElement = root;
		this.rootObject = rootObject;
		this.prefHint = prefHint;
	}

	/**
	 * A helper method to help us execute lots of steps easily
	 * 
	 * @param command the command to execute
	 * @throws ExecutionException
	 * @see doExecuteWithResult()
	 */
	protected void doExecute(AbstractOperation command) throws ExecutionException {
		OperationHistoryFactory.getOperationHistory().execute(command,
				monitor, info);
	}
	
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {

		this.monitor = monitor;
		this.info = info;	
		
		if (rootObject instanceof GeneratedElement && ((GeneratedElement) rootObject).isIsGenerated()) {
			// is this element generated?
			// if it is, then we have to get it's source and mark all of its
			// generated elements as _not_ generated
			
			deleteGeneratedElement((GeneratedElement) rootObject);
		} else if (rootObject instanceof GeneratesElements && !((GeneratesElements) rootObject).getGeneratedElements().isEmpty() ) {
			// otherwise if we are deleting an element that has generated
			// elements of its own, we have to delete all of its
			// generated elements
			// (this will probably break some edges...)
			// (but EMF may just delete these edges by itself, if we don't do anything.)

			deleteGeneratesElements((GeneratesElements) rootObject);
		} 
		// otherwise it's just a normal element, so we can delete as normal
		
		return CommandResult.newOKCommandResult();
		
	}

	/**
	 * Delete a generated element.
	 * We will have to get it's source and mark all of its generated
	 * elements as _not_ generated.
	 * 
	 * @param element
	 * @throws ExecutionException 
	 */
	private void deleteGeneratedElement(GeneratedElement element) throws ExecutionException {
		
		for (GeneratedElement e : element.getGeneratedBy().getGeneratedElements()) {
			// ignore the current one, since we are deleting it anyway
			if (!element.equals(e)) {
				System.out.println("un-isGenerating " + e);

				// set isGenerated to false
				SetValueCommand sv = new SetValueCommand(new SetRequest(e, ModelPackage.eINSTANCE.getGeneratedElement_IsGenerated(), false));
				doExecute(sv);

				// set generatedBy to empty
				// (this should also remove the EOpposite reference)
				DestroyReferenceCommand dr = new DestroyReferenceCommand(new DestroyReferenceRequest(e, ModelPackage.eINSTANCE.getGeneratedElement_GeneratedBy(), e.getGeneratedBy(), false));
				doExecute(dr);
			}
		}
		
	}


	/**
	 * Delete an element that generates elements. We will have to delete
	 * all of its generated elements as well.
	 * 
			// otherwise if we are deleting an element that has generated
			// elements of its own, we have to delete all of its
			// generated elements
			// (this will probably break some edges...)
			// (but EMF may just delete these edges by itself, if we don't do anything.)
	 * 
	 * @param element
	 * @throws ExecutionException 
	 */
	private void deleteGeneratesElements(GeneratesElements element) throws ExecutionException {
		
		CompositeEMFOperation cc = new CompositeEMFOperation(this.getEditingDomain(), "delete related elements");
		
		for (GeneratedElement e : element.getGeneratedElements()) {
			DestroyElementCommand de = new DestroyElementCommand(new DestroyElementRequest(e, false));
			cc.add(de);			
		}
		
		doExecute(cc);
		
	}

}
