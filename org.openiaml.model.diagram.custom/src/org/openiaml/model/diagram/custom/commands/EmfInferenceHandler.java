/**
 * 
 */
package org.openiaml.model.diagram.custom.commands;

import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.openiaml.model.inference.EcoreCreateElementsHelper;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;

/**
 * @author jmwright
 *
 */
public class EmfInferenceHandler extends EcoreCreateElementsHelper implements ICreateElements {
	
	protected class CreateElementCommand extends AbstractTransactionalCommand {

		public CreateElementCommand(TransactionalEditingDomain domain,
				List affectedFiles,
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
	
	private TransactionalEditingDomain editingDomain;
	private List affectedFiles;
	private IProgressMonitor monitor;
	private IAdaptable info;
	
	/**
	 * @param editingDomain
	 * @param affectedFiles
	 * @param monitor
	 * @param info
	 */
	public EmfInferenceHandler(TransactionalEditingDomain editingDomain,
			List affectedFiles, IProgressMonitor monitor, IAdaptable info) {
		super();
		this.editingDomain = editingDomain;
		this.affectedFiles = affectedFiles;
		this.monitor = monitor;
		this.info = info;
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
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.inference.ICreateElements#setValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
	 */
	@Override
	public void setValue(EObject element, EStructuralFeature reference,
			Object value) throws InferenceException {
		// TODO Auto-generated method stub

	}

}
