package org.openiaml.simplegmf.ui;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.simplegmf.codegen.ModelLoader;
import org.openiaml.simplegmf.codegen.SimpleGMFCodeGenerator;
import org.openiaml.simplegmf.codegen.ModelLoader.ModelLoadException;
import org.openiaml.simplegmf.model.simplegmf.GMFConfiguration;

/**
 * Wraps the SimpleGMF codegen action to generate the actual code from the
 * source .simplegmf files.
 * 
 * @author jmwright
 *
 */
public class GenerateGMFMappingsAction extends EMFModelAction {

	/**
	 * Actually implements the code generation action.
	 * 
	 * @see org.openiaml.simplegmf.ui.EMFModelAction#doExecute(org.eclipse.core.resources.IFile, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus doExecute(IFile o, IProgressMonitor monitor)
			throws IOException, CoreException {
		monitor.beginTask("Generating GMF mappings", 100);
		
		// load model
		EObject model;
		try {
			monitor.subTask("Loading model instance");
			model = ModelLoader.load(o);
		} catch (ModelLoadException e) {
			return errorStatus("Could not load model " + o.getName(), e);
		}
		monitor.worked(20);
		
		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;
		
		// check instance
		if (!(model instanceof GMFConfiguration)) {
			return errorStatus("Loaded model was not an instance of GMFConfiguration: " + model.eClass().getName());
		}
		
		// check that container is a folder
		monitor.subTask("Generating source code");
		SimpleGMFCodeGenerator gen = new SimpleGMFCodeGenerator();
		IStatus status = gen.generateCode(model, o.getParent().getLocation().toString());
		monitor.worked(60);
		
		// refresh parent
		monitor.subTask("Refreshing file system");
		o.getParent().refreshLocal(IResource.DEPTH_INFINITE, new SubProgressMonitor(monitor, 20));
		
		monitor.done();
		return status;
	}

	@Override
	public String getExpectedExtension() {
		return "simplegmf";
	}

	@Override
	public String getErrorMessage(IFile individual, String message) {
		return "Could not generate GMF mappings from " + individual.getName() + ": " + message;
	}

	@Override
	public String getProgressMessage() {
		return "Generating GMF mappings";
	}
	
}
