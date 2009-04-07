/**
 * 
 */
package org.openiaml.model.inference;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * <p>Wraps {@link EcoreInferenceHandler} with {@link AbstractTransactionalCommand}s,
 * allowing EMF-based operations (such as {@link #setValue(EObject, EStructuralFeature, Object)},
 * {@link #createElement(EObject, EClass, EStructuralFeature)}) to execute
 * within a transaction.</p>
 * 
 * <p>This will prevent "Cannot modify resource set without a write transaction" exceptions
 * when trying to access the EMF objects directly.</p>
 * 
 * @author jmwright
 * @see EcoreInferenceHandler
 *
 */
public class EmfInferenceHandler extends EcoreCreateElementsHelper implements ICreateElements {
	
	final protected Resource resource;

	/**
	 * Wraps {@link EmfInferenceHandler#createElement(EObject, EClass, EStructuralFeature)} with
	 * an {@link AbstractTransactionalCommand}. 
	 * 
	 * @author jmwright
	 */
	protected class CreateElementCommand extends AbstractTransactionalCommand {

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
	
	/**
	 * Wraps {@link EmfInferenceHandler#createRelationship(EObject, EClass, EObject, EObject, EStructuralFeature, EStructuralFeature, EStructuralFeature)} with
	 * an {@link AbstractTransactionalCommand}. 
	 * 
	 * @author jmwright
	 */
	protected class CreateRelationshipCommand extends AbstractTransactionalCommand {

		public CreateRelationshipCommand(TransactionalEditingDomain domain,
				List<?> affectedFiles,
				EObject container, EClass elementType, EStructuralFeature containerFeature,
				EObject source, EObject target, 
				EStructuralFeature sourceFeature, EStructuralFeature targetFeature) {
			super(domain, "Create relationship command", affectedFiles);
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
			EcoreInferenceHandler eih = new EcoreInferenceHandler(resource);
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
	
	/**
	 * Wraps {@link EmfInferenceHandler#setValue(EObject, EStructuralFeature, Object)} with
	 * an {@link AbstractTransactionalCommand}. 
	 * 
	 * @author jmwright
	 */
	protected class SetValueCommand extends AbstractTransactionalCommand {

		public SetValueCommand(TransactionalEditingDomain domain,
				List<?> affectedFiles,
				EObject element, EStructuralFeature reference, Object value) {
			super(domain, "Create element command", affectedFiles);
			this.element = element;
			this.reference = reference;
			this.value = value;
		}

		private EObject element;
		private EStructuralFeature reference;
		private Object value;
		
		@Override
		protected CommandResult doExecuteWithResult(
				IProgressMonitor monitor, IAdaptable info)
				throws ExecutionException {
			
			// just pass it along
			EcoreInferenceHandler eih = new EcoreInferenceHandler(resource);
			try {
				eih.setValue(element, reference, value);
			} catch (InferenceException e) {
				throw new ExecutionException(e.getMessage(), e);
			}
			return CommandResult.newOKCommandResult();
			
		}
			
	}
	
	private TransactionalEditingDomain editingDomain;
	private List<?> affectedFiles;
	private IProgressMonitor monitor;
	private IAdaptable info;
	
	/**
	 * Default constructor. If you do not have access to an editing
	 * domain perhaps you do not need to wrap your commands with
	 * {@link AbstractTransactionalCommand}s -- see 
	 * {@link EcoreInferenceHandler} instead.
	 * 
	 * @param editingDomain
	 * @param affectedFiles
	 * @param monitor
	 * @param info
	 */
	public EmfInferenceHandler(TransactionalEditingDomain editingDomain,
			List<?> affectedFiles, IProgressMonitor monitor, IAdaptable info,
			Resource resource) {
		super();
		this.editingDomain = editingDomain;
		this.affectedFiles = affectedFiles;
		this.monitor = monitor;
		this.info = info;
		this.resource = resource;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#createElement(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@Override
	public EObject createElement(final EObject container, final EClass elementType,
			final EStructuralFeature containerFeature) throws InferenceException {
		
		CreateElementCommand command = new CreateElementCommand(editingDomain, affectedFiles, container, elementType, containerFeature);
		
		try {
			IStatus r = command.execute(monitor, info);
			if (!r.isOK()) {
				throw new InferenceException("Status was not OK: " + r.getMessage(), r.getException());
			}
		} catch (ExecutionException e) {
			throw new InferenceException(e);
		}	
		
		return command.getCreatedObject();
		
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
		
		CreateRelationshipCommand command = new CreateRelationshipCommand(editingDomain, affectedFiles, container, elementType, containerFeature, source, target, sourceFeature, targetFeature);
		
		try {
			IStatus r = command.execute(monitor, info);
			if (!r.isOK()) {
				throw new InferenceException("Status was not OK: " + r.getMessage(), r.getException());
			}
		} catch (ExecutionException e) {
			throw new InferenceException(e);
		}	
		
		return command.getCreatedObject();
		
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#setValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
	 */
	@Override
	public void setValue(EObject element, EStructuralFeature reference,
			Object value) throws InferenceException {
		
		SetValueCommand command = new SetValueCommand(editingDomain, affectedFiles, element, reference, value);
		
		try {
			IStatus r = command.execute(monitor, info);
			if (!r.isOK()) {
				throw new InferenceException("Status was not OK: " + r.getMessage(), r.getException());
			}
		} catch (ExecutionException e) {
			throw new InferenceException(e);
		}	
		
		return;
		
	}

}
