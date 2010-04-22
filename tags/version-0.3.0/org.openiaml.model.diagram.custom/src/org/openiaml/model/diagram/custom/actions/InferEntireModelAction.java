package org.openiaml.model.diagram.custom.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
 * @see org.openiaml.model.codegen.oaw
 * @author jmwright
 *
 */
public class InferEntireModelAction extends ProgressEnabledAction<IFile> {

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
	 */
	public IStatus execute(IFile o, IProgressMonitor monitor) {
		try {
			if (o.getFileExtension().equals("iaml")) {
				monitor.beginTask("Inferring model in file '" + o.getName() + "'", 60);
				
				// try and load the file directly
				ResourceSet resourceSet = new ResourceSetImpl();
				Resource resource = resourceSet.getResource(URI.createFileURI(o.getLocation().toString()), true);
				
				// load the inference elements manager
				EcoreInferenceHandler handler = new EcoreInferenceHandler(resource);
				
				// we can only do one model
				if (resource.getContents().size() != 1) {
					return new Status(IStatus.ERROR, PLUGIN_ID, "Could not transform model: unexpected number of model elements in file (expected: 1, found: " + resource.getContents().size() + ")");
				}
				
				// do inference on the model
				EObject model = resource.getContents().get(0);
				DroolsInferenceEngine ce = getEngine(handler);
				ce.create(model, new SubProgressMonitor(monitor, 45));
				
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
				Map<?,?> options = resourceSet.getLoadOptions();
				resource.save(new FileOutputStream(tempJavaFile), options);
				
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
			} else {
				return errorStatus("File '" + o.getName() + "' does not end in '.iaml' extension.");
			}
		} catch (InferenceException e) {
			return errorStatus("Inference failed", e);
		} catch (IOException e) {
			return errorStatus("IO exception", e);
		} catch (CoreException e) {
			return errorStatus("Core exception", e);
		}
	}

}