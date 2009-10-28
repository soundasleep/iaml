package org.openiaml.model.custom.actions;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openiaml.model.helpers.IamlBreadcrumb;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.verification.crocopat.VerificationEngine;
import org.openiaml.verification.crocopat.VerificationException;
import org.openiaml.verification.crocopat.VerificationViolation;

/**
 * Verify the .iaml file with Crocopat.
 * 
 * @see org.openiaml.verification.crocopat
 * @author jmwright
 *
 */
public class VerificationWithCrocopatAction extends IamlFileAction {
	
	private EObject model;
		
	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#execute(java.lang.Object, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus doExecute(IFile o, IProgressMonitor monitor) throws InferenceException, IOException, CoreException {
		monitor.beginTask("Verifying model '" + o.getName() + "'", 10);
		
		monitor.subTask("Loading model");
		// try and load the file directly
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(URI.createFileURI(o.getLocation().toString()), true);
		
		// we can only do one model
		if (resource.getContents().size() != 1) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Could not transform model: unexpected number of model elements in file (expected: 1, found: " + resource.getContents().size() + ")");
		}

		// do inference on the model
		model = resource.getContents().get(0);
		monitor.worked(1);

		// do verification
		VerificationEngine verify = new VerificationEngine();
		IStatus status;
		try {
			status = verify.verify(model, new SubProgressMonitor(monitor, 9));
		} catch (VerificationException e) {
			return errorStatus(e);
		}
		
		// check for violation exceptions
		if (!verify.getViolations().isEmpty()) {			
			StringBuffer buf = new StringBuffer(); 
			
			for (int i = 0; i < 5 && i < verify.getViolations().size(); i++) {
				VerificationViolation violation = verify.getViolations().get(i); 
				if (i != 0)
					buf.append("\n");
				
				buf.append(violation.getMessage())
					.append(": ");
				
				for (int j = 0; j < violation.getObjects().size(); j++) {
					if (j != 0)
						buf.append("; ");
					buf.append(IamlBreadcrumb.breadcrumb(violation.getObjects().get(j)));
				}
			}
			
			if (verify.getViolations().size() > 5) {
				// there are more
				buf.append("\n(...")
					.append(verify.getViolations().size() - 5)
					.append(" more)");				
			}
			
			// return the error
			return errorStatus("Verification failed:\n\n" + buf.toString());
		}
		
		// finished
		monitor.done();
		
		return status;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(IFile individual, String message) {
		return "Could not verify '" + individual.getName() + "': " + message;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Verifying";
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.IamlFileAction#getLoadedModel()
	 */
	@Override
	protected EObject getLoadedModel() {
		return model;
	}

	/**
	 * We want error messages to be displayed to the user.
	 */
	@Override
	public boolean shouldDisplayErrorToUser() {
		return true;
	}
	
}
