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
 * Wraps {@link EmfInferenceHandler#createElement(EObject, EClass, EStructuralFeature)} with
 * an {@link AbstractTransactionalCommand}. 
 * 
 * @author jmwright
 */
public class CreateElementCommand extends AbstractTransactionalCommand {

	public CreateElementCommand(TransactionalEditingDomain domain,
			List<?> affectedFiles,
			EObject container, EClass elementType, EStructuralFeature containerFeature) {
		super(domain, "Create element command", affectedFiles);
		this.container = container;
		this.elementType = elementType;
		this.containerFeature = containerFeature;
	}

	private EObject created;
	private EObject container;
	private EClass elementType;
	private EStructuralFeature containerFeature;
	
	@Override
	protected CommandResult doExecuteWithResult(
			IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		
		// just pass it along
		EcoreInferenceHandler eih = new EcoreInferenceHandler(container.eResource());
		try {
			created = eih.createElement(container, elementType, containerFeature);
		} catch (InferenceException e) {
			throw new ExecutionException(e.getMessage(), e);
		}
		return CommandResult.newOKCommandResult(created);
		
	}

	public EObject getCreatedObject() {
		return created;
	}
		
}