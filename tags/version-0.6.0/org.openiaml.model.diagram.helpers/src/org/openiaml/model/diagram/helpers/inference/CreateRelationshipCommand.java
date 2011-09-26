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
 * Wraps {@link EmfInferenceHandler#createRelationship(EObject, EClass, EObject, EObject, EStructuralFeature, EStructuralFeature, EStructuralFeature)} with
 * an {@link AbstractTransactionalCommand}. 
 * 
 * @author jmwright
 */
public class CreateRelationshipCommand extends AbstractTransactionalCommand {

	private final EmfInferenceHandler emfInferenceHandler;

	public CreateRelationshipCommand(EmfInferenceHandler emfInferenceHandler, TransactionalEditingDomain domain,
			List<?> affectedFiles,
			EObject container, EClass elementType, EStructuralFeature containerFeature,
			EObject source, EObject target, 
			EStructuralFeature sourceFeature, EStructuralFeature targetFeature) {
		super(domain, "Create relationship command", affectedFiles);
		this.emfInferenceHandler = emfInferenceHandler;
		this.container = container;
		this.elementType = elementType;
		this.containerFeature = containerFeature;
		this.source = source;
		this.target = target;
		this.sourceFeature = sourceFeature;
		this.targetFeature = targetFeature;
	}

	private EObject created;
	private EObject container;
	private EClass elementType;
	private EStructuralFeature containerFeature;
	private EObject source;
	private EObject target;
	private EStructuralFeature sourceFeature;
	private EStructuralFeature targetFeature;
	
	@Override
	protected CommandResult doExecuteWithResult(
			IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		
		// just pass it along
		EcoreInferenceHandler eih = new EcoreInferenceHandler(this.emfInferenceHandler.resource);
		try {
			created = eih.createRelationship(container, elementType, source, target, containerFeature, sourceFeature, targetFeature);
		} catch (InferenceException e) {
			throw new ExecutionException(e.getMessage(), e);
		}
		return CommandResult.newOKCommandResult(created);
		
	}

	public EObject getCreatedObject() {
		return created;
	}
		
}