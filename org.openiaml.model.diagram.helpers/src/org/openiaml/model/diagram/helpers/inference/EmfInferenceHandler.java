/**
 * 
 */
package org.openiaml.model.diagram.helpers.inference;

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
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.openiaml.model.inference.EcoreCreateElementsHelper;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;

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
		
		CreateRelationshipCommand command = new CreateRelationshipCommand(this, editingDomain, affectedFiles, container, elementType, containerFeature, source, target, sourceFeature, targetFeature);
		
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
		
		SetValueCommand command = new SetValueCommand(this, editingDomain, affectedFiles, element, reference, value);
		
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

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#deleteElement(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@Override
	public void deleteElement(EObject object, EObject container,
			EStructuralFeature containerFeature) throws InferenceException {

		DeleteElementCommand command = new DeleteElementCommand(this, editingDomain, affectedFiles, object, container, containerFeature);
		
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

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#addReference(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
	 */
	@Override
	public void addReference(EObject element, EStructuralFeature reference,
			Object value) throws InferenceException {
		
		AddReferenceCommand command = new AddReferenceCommand(this, editingDomain, affectedFiles, element, reference, value);
		
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

	public void setMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}

	public void setInfo(IAdaptable info) {
		this.info = info;
	}
	
}
