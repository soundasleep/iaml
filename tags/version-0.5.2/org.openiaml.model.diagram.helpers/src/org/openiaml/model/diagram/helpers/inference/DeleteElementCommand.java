package org.openiaml.model.diagram.helpers.inference;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.InferenceException;

/**
 * Wraps {@link EmfInferenceHandler#deleteElement(EObject, EObject, EClass, EStructuralFeature)} with
 * an {@link AbstractTransactionalCommand}. 
 * 
 * @author jmwright
 */
public class DeleteElementCommand extends AbstractTransactionalCommandHelper {

	private final EmfInferenceHandler emfInferenceHandler;
	
	private EObject object;
	private EObject container;
	private EStructuralFeature containerFeature;

	public DeleteElementCommand(EmfInferenceHandler emfInferenceHandler, TransactionalEditingDomain domain,
			List<?> affectedFiles,
			EObject object, EObject container,
			EStructuralFeature containerFeature) {
		super(domain, "Delete element command", affectedFiles);
		this.emfInferenceHandler = emfInferenceHandler;
		
		this.object = object;
		this.container = container;
		this.containerFeature = containerFeature;
	}
	
	/**
	 * Gets the list of affected files from the <code>container</code>.
	 */
	public DeleteElementCommand(TransactionalEditingDomain editingDomain,
			EObject object, EObject container, EStructuralFeature containerFeature) {
		this(editingDomain, getWorkspaceFiles(container), object, container, containerFeature);
	}

	public DeleteElementCommand(TransactionalEditingDomain editingDomain,
			List workspaceFiles, EObject object2, EObject container2,
			EStructuralFeature containerFeature2) {
		this(newHandler(editingDomain, workspaceFiles, container2.eResource()),
				editingDomain, workspaceFiles, object2, container2, containerFeature2);
	}

	@Override
	protected CommandResult doExecuteWithResult(
			IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		
		// just pass it along
		EcoreInferenceHandler eih = new EcoreInferenceHandler(this.emfInferenceHandler.resource);
		try {
			eih.deleteElement(object, container, containerFeature);
		} catch (InferenceException e) {
			throw new ExecutionException(e.getMessage(), e);
		}
		return CommandResult.newOKCommandResult();
		
	}
		
}