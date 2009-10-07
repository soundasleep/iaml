/**
 * 
 */
package org.openiaml.verification.drools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openiaml.model.custom.actions.ProgressEnabledAction;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.verification.drools.VerifyHandler.VerificationException;

/**
 *
 * 
 * @author jmwright
 *
 */
public class ValidateIAMLWithDrools extends ProgressEnabledAction<IFile> {

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#execute(java.lang.Object, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus execute(IFile individual, IProgressMonitor monitor) {
		// need to load model
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(URI.createFileURI(individual.getLocation().toString()), true);
			
		// we can only do one model
		if (resource.getContents().size() != 1) {
			return errorStatus("Could not transform model: unexpected number of model elements in file (expected: 1, found: " + resource.getContents().size() + ")");
		}

		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

		// do inference on the model
		EObject model = resource.getContents().get(0);
		
		// do the verification
		VerifyHandler verify = new VerifyHandler();
		try {
			VerifyWithDrools action = new VerifyWithDrools(verify, false);
			
			action.verify(model, monitor);
		} catch (InferenceException e) {
			return errorStatus(e);
		}
		
		if (verify.isFailed()) {
			return errorStatus("Verification failed: " + verify.getViolations());
		}

		return Status.OK_STATUS; 
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(IFile individual, String message) {
		return "Could not validate file '" + individual + "': " + message;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Validate IAML with Drools";
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getSelection(java.lang.Object[])
	 */
	@Override
	public List<IFile> getSelection(Object[] selection) {
		final List<IFile> ifiles = new ArrayList<IFile>();
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IFile) {
					ifiles.add((IFile) o);
				}
			}
		}
		
		return ifiles;
	}
	
}
