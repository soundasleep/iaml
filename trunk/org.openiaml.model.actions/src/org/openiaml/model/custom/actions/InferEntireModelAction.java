package org.openiaml.model.custom.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.ModelLoader.ModelLoadException;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;

/**
 * A temporary action which infers the entire model, and places
 * it all back within the same model file. This isn't really designed
 * to be used in the real world, because we should be working with
 * models that do not require such inference to understand their
 * operation.
 * 
 * @see org.openiaml.model.codegen.php
 * @author jmwright
 *
 */
public class InferEntireModelAction extends IamlFileAction {

	private EObject model;
	
	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(IFile individual, String message) {
		return "Could not infer model from '" + individual.getName() + "': " + message;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Infer entire model";
	}

	/**
	 * Select and create the Drools engine for updating.
	 * This will usually be a specific engine implementation
	 * which only selects a small subset of rule files (i.e.
	 * it does not select all of the rules at once).
	 * 
	 * @return The engine to use
	 */
	public DroolsInferenceEngine getEngine(ICreateElements handler) {
		return new CreateMissingElementsWithDrools(handler, false);
	}
	
	/**
	 * @param o Both the source file and the target file.
	 * @param monitor 
	 * @return 
	 * @throws InferenceException 
	 * @throws IOException 
	 * @throws CoreException 
	 */
	@Override
	public IStatus doExecute(IFile o, IProgressMonitor monitor) throws InferenceException, FileNotFoundException, IOException, CoreException {
		monitor.beginTask("Inferring model in file '" + o.getName() + "'", 70);
		
		// try and load the file directly
		monitor.subTask("Loading model");
		try {
			model = ModelLoader.load(o);
		} catch (ModelLoadException e) {
			return errorStatus(e);
		}
		monitor.worked(10);
		
		// load the inference elements manager
		EcoreInferenceHandler handler = new EcoreInferenceHandler(model.eResource());

		// do inference on the model
		monitor.subTask("Perfoming inference");
		DroolsInferenceEngine ce = getEngine(handler);
		ce.create(model, new SubProgressMonitor(monitor, 45));

		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

		monitor.subTask("Writing to temporary file");
		// output the temporary changed model to an external file
		// so we can move it back later
		IFile tempFile = null;
		for (int i = 0; i < 1000; i++) {
			tempFile = o.getProject().getFile("temp-iaml-gen" + i + ".iaml");
			if (!tempFile.exists()) {
				break;
			}
		}

		if (tempFile == null || tempFile.exists()) {
			return new Status(Status.ERROR, PLUGIN_ID, "Could not create temporary file.");
		}
		
		// create a temporary file to output to
		File tempJavaFile = File.createTempFile("temp-iaml", ".iaml");
		Map<?,?> options = model.eResource().getResourceSet().getLoadOptions();
		model.eResource().save(new FileOutputStream(tempJavaFile), options);
		
		monitor.worked(5);
		monitor.subTask("Saving to IFile");
		
		// now load it in as an IFile
		tempFile.create(new FileInputStream(tempJavaFile), true, monitor);
		
		monitor.worked(5);
		monitor.subTask("Replacing original model");

		// rename the tempFile
		o.delete(true, monitor);
		tempFile.move(o.getFullPath(), true, monitor);
		tempJavaFile.delete();
		
		// finished
		monitor.done();
		
		return Status.OK_STATUS;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.IamlFileAction#getLoadedModel()
	 */
	@Override
	protected EObject getLoadedModel() {
		return model;
	}

}
