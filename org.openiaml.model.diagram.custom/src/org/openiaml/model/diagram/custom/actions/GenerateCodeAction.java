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
import org.openiaml.model.codegen.ICodeGenerator;
import org.openiaml.model.codegen.oaw.OawCodeGenerator;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.InferenceException;

/**
 * Action to generate code from an .iaml file
 * 
 * @see org.openiaml.model.codegen.oaw
 * @author jmwright
 *
 */
public class GenerateCodeAction extends ProgressEnabledAction<IFile> {

	private EObject model;

	/**
	 * @return For {@link GenerateCodeActionAndView}, we need to get the
	 * loaded model.
	 */
	protected EObject getLoadedModel() {
		return model;
	}

	
	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#execute(java.lang.Object, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus execute(IFile o, IProgressMonitor monitor) {
		try {
			if (o.getFileExtension().equals("iaml")) {
				monitor.beginTask("Generating code for file '" + o.getName() + "'", 100);
				
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
				model = resource.getContents().get(0);
				CreateMissingElementsWithDrools ce = new CreateMissingElementsWithDrools(handler, false);
				ce.create(model, new SubProgressMonitor(monitor, 45));
				
				// output the temporary changed model to an external file
				// so we can do code generation
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
				
				// now load it in as an IFile
				tempFile.create(new FileInputStream(tempJavaFile), true, new SubProgressMonitor(monitor, 5));
		
				// create code generator instance
				ICodeGenerator codegen = new OawCodeGenerator();
				IStatus status = codegen.generateCode(tempFile, new SubProgressMonitor(monitor, 50));
				
				// now delete the generated model file
				// TODO this would probably go well in a finally block
				tempFile.delete(false, monitor);
				tempJavaFile.delete();
				
				// finished
				monitor.done();
				
				return status;
			} else {
				return new Status(IStatus.ERROR, PLUGIN_ID, "File '" + o.getName() + "' does not have an .iaml extension.");
			}
		} catch (InferenceException e) {
			return errorStatus("Inference failed", e);
		} catch (IOException e) {
			return errorStatus("IO exception", e);
		} catch (CoreException e) {
			return errorStatus("Core exception", e);
		}
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(IFile individual, String message) {
		return "Could not generate code for '" + individual.getName() + "': " + message;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Generating code";
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
