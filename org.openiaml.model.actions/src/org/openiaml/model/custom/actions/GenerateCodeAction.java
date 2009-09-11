package org.openiaml.model.custom.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.openiaml.model.actions.QuestionDialogResult;
import org.openiaml.model.codegen.ICodeGenerator;
import org.openiaml.model.codegen.oaw.CheckModelInstance;
import org.openiaml.model.codegen.oaw.OawCodeGeneratorWithRuntime;
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
public class GenerateCodeAction extends IamlFileAction {
	
	private EObject model;
	
	/**
	 * Get a helpful list of error messages from the given status.
	 * 
	 * @param status
	 * @return
	 */
	private String getErrorMessage(IStatus status) {
		if (status.isMultiStatus()) {
			// get the first 4 errors
			MultiStatus multi = (MultiStatus) status;
			String result = "";
			for (int i = 0; i < 4 && i < multi.getChildren().length; i++) {
				result += (i == 0 ? "" : "\n") + multi.getChildren()[i].getMessage();
			}
			if (multi.getChildren().length > 4) {
				result += "\n(... " + (multi.getChildren().length - 4) + " more)";
			}
			return result;
		} else {
			return status.getMessage();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#execute(java.lang.Object, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus doExecute(IFile o, IProgressMonitor monitor) throws InferenceException, IOException, CoreException {
		monitor.beginTask("Generating code for file '" + o.getName() + "'", 130);
		
		// first, run OAW checks to check that the initial model instance is valid
		monitor.subTask("Checking initial model instance");
		CheckModelInstance check = new CheckModelInstance();
		final IStatus result = check.checkModel(o, new SubProgressMonitor(monitor, 30));
		final QuestionDialogResult answer = new QuestionDialogResult();
		
		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

		if (!result.isOK()) {
			// log the result
			getDefaultPlugin().log(result);
			
			// get user confirmation
			Display.getDefault().syncExec(new Runnable() {
			    @Override
			    public void run() {
			    	answer.setResult(MessageDialog.openConfirm(null, 
							"Initial validation failed",
							"An error occured when validating the initial model:\n\n" + 
								getErrorMessage(result) +
								"\n\nWould you still like to continue with code generation?"));
			    }
			  });

			if (!answer.getResult()) {
				// user canceled
				return Status.CANCEL_STATUS;
			}
		}

		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

		// try and load the file directly
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(URI.createFileURI(o.getLocation().toString()), true);
		
		// load the inference elements manager
		EcoreInferenceHandler handler = new EcoreInferenceHandler(resource);
		
		// we can only do one model
		if (resource.getContents().size() != 1) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Could not transform model: unexpected number of model elements in file (expected: 1, found: " + resource.getContents().size() + ")");
		}

		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

		// do inference on the model
		model = resource.getContents().get(0);
		CreateMissingElementsWithDrools ce = new CreateMissingElementsWithDrools(handler, false);
		ce.create(model, new SubProgressMonitor(monitor, 45));
		
		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;
		
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
		
		// does a properties file exist?
		// (used to define the location of runtime libraries)
		IFile properties = null;
		if (o.getParent() instanceof IFolder) {
			properties = ((IFolder) o.getParent()).getFile("runtime.properties");
		} else if (o.getParent() instanceof IProject) {
			properties = ((IProject) o.getParent()).getFile("runtime.properties");
		}
		
		// load the properties file if it does
		Map<String,String> runtimeProperties = getDefaultProperties();
		if (properties != null && properties.exists()) {
			// read it
			runtimeProperties = readProperties(properties);
		}

		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

		// create code generator instance
		ICodeGenerator codegen = new OawCodeGeneratorWithRuntime();
		IStatus status = codegen.generateCode(tempFile, new SubProgressMonitor(monitor, 50), runtimeProperties);

		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

		// now delete the generated model file
		// TODO this would probably go well in a finally block
		tempFile.delete(false, monitor);
		tempJavaFile.delete();
		
		// finished
		monitor.done();
		
		return status;
	}

	/**
	 * Read in an IFile into a Map.
	 * 
	 * @param properties
	 * @return
	 * @throws CoreException 
	 * @throws IOException 
	 */
	protected Map<String, String> readProperties(IFile properties) throws IOException, CoreException {
		
		Properties p = new Properties();
		p.load(properties.getContents());
		
		Map<String,String> result = new HashMap<String,String>();
		for (Object key : p.keySet()) {
			result.put((String) key, (String) p.get(key));
		}
		
		return result;
		
	}

	/**
	 * Get the default runtime properties.
	 * 
	 * @see ICodeGenerator#generateCode(IFile, IProgressMonitor, Map)
	 * @return
	 */
	protected Map<String, String> getDefaultProperties() {
		Map<String, String> properties = new HashMap<String,String>();
		
		properties.put("include_runtime", "true");
		
		return properties;
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
	 * @see org.openiaml.model.diagram.custom.actions.IamlFileAction#getLoadedModel()
	 */
	@Override
	protected EObject getLoadedModel() {
		return model;
	}

}
