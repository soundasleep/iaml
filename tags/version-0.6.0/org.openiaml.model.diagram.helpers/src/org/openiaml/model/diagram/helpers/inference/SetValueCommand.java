package org.openiaml.model.diagram.helpers.inference;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.InferenceException;

/**
 * Wraps {@link EmfInferenceHandler#setValue(EObject, EStructuralFeature, Object)} with
 * an {@link AbstractTransactionalCommand}. 
 * 
 * @author jmwright
 */
public class SetValueCommand extends AbstractTransactionalCommandHelper {

	private EmfInferenceHandler emfInferenceHandler;

	public SetValueCommand(EmfInferenceHandler emfInferenceHandler, TransactionalEditingDomain domain,
			List<?> affectedFiles,
			EObject element, EStructuralFeature reference, Object value) {
		super(domain, "Set value command", affectedFiles);
		this.emfInferenceHandler = emfInferenceHandler;
		this.element = element;
		this.reference = reference;
		this.value = value;
	}

	/**
	 * Creates a new {@link EmfInferenceHandler}.
	 */
	public SetValueCommand(TransactionalEditingDomain editingDomain,
			List workspaceFiles, EObject container,
			EStructuralFeature reference, Object value) {
		
		this(newHandler(editingDomain, workspaceFiles, container.eResource()),
			editingDomain,
			workspaceFiles,
			container,
			reference,
			value);
		
	}

	/**
	 * Gets the list of affected files from the <code>container</code>.
	 */
	public SetValueCommand(TransactionalEditingDomain editingDomain,
			EObject container, EStructuralFeature reference, Object value) {
		this(editingDomain, getWorkspaceFiles(container), container, reference, value);
	}

	private EObject element;
	private EStructuralFeature reference;
	private Object value;
	
	@Override
	protected CommandResult doExecuteWithResult(
			IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		
		this.emfInferenceHandler.setMonitor(monitor);
		this.emfInferenceHandler.setInfo(info);
		
		// just pass it along
		EcoreInferenceHandler eih = new EcoreInferenceHandler(this.emfInferenceHandler.resource);
		try {
			eih.setValue(element, reference, value);
		} catch (InferenceException e) {
			throw new ExecutionException(e.getMessage(), e);
		}
		return CommandResult.newOKCommandResult();
		
	}
		
}