package org.openiaml.model.custom.actions;

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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.ModelLoader.ModelLoadException;
import org.openiaml.model.actions.QuestionDialogResult;
import org.openiaml.model.codegen.DefaultRuntimeProperties;
import org.openiaml.model.codegen.ICodeGenerator;
import org.openiaml.model.codegen.php.CheckModelInstance;
import org.openiaml.model.codegen.php.OawCodeGeneratorWithRuntime;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.drools.EcoreInferenceHandlerFactory;
import org.openiaml.model.inference.InferenceException;

/**
 * Action to generate code from an .iaml file
 * 
 * @see org.openiaml.model.codegen.php
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
		monitor.beginTask("Generating code for file '" + o.getName() + "'", 140);

		// first, load the model
		monitor.subTask("Loading model");
		try {
			model = ModelLoader.load(o);
		} catch (ModelLoadException e) {
			return errorStatus(e);
		}
		monitor.worked(10);
		
		// first, run OAW checks to check that the initial model instance is valid
		monitor.subTask("Checking initial model instance");
		CheckModelInstance check = new CheckModelInstance();
		final IStatus result = check.checkModel(model, o.getProject(), new SubProgressMonitor(monitor, 30));
		final QuestionDialogResult answer = new QuestionDialogResult();
		
		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

		if (!result.isOK()) {
			// log the result
			getDefaultPlugin().log(result);
			
			if (couldBePhantomEdges(result)) {
	    		// issue 132: automatically suggest the removal of phantom edges
	    		
				// get user confirmation
				Display.getDefault().syncExec(new Runnable() {
				    @Override
				    public void run() {
				    	answer.setResult(MessageDialog.openConfirm(null, 
								"Possible phantom edges",
								"It appears that there may be phantom edges in your model:\n\n" + 
									getErrorMessage(result) +
									"\n\nWould you like to automatically remove these edges?"));
				    }
				  });
				
				if (answer.getResult()) {
					// remove phantom edges
					RemovePhantomEdgesAction phantom = new RemovePhantomEdgesAction();
					phantom.doExecute(o, new SubProgressMonitor(monitor, 10));
					
					// refresh project
					if (o.getParent() != null) {
						o.getParent().refreshLocal(IFile.DEPTH_ONE, new SubProgressMonitor(monitor, 10));
					}
					
					// and execute again
					return doExecute(o, new SubProgressMonitor(monitor, 80));
				}
	    		
	    	}
			
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

		// do inference on the model
		CreateMissingElementsWithDrools ce = new CreateMissingElementsWithDrools(new EcoreInferenceHandlerFactory(), false);
		ce.create(model, new SubProgressMonitor(monitor, 45));
		
		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

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
		IStatus status = codegen.generateCode(model, o.getProject(), new SubProgressMonitor(monitor, 50), runtimeProperties);

		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

		// finished
		monitor.done();
		
		return status;
	}

	/**
	 * Could the given result status be due to phantom edges?
	 * 
	 * @param result
	 * @return
	 */
	private boolean couldBePhantomEdges(IStatus result) {
		if (result.isMultiStatus()) {
			for (IStatus status : result.getChildren()) {
				if (couldBePhantomEdges(status))
					return true;
			}
		}
		
		if (result.getMessage().startsWith("A WireEdge must have a 'to'"))
			return true;
		
		if (result.getMessage().startsWith("A WireEdge must have a 'from'"))
			return true;

		return false;
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
	 * @see DefaultRuntimeProperties#getDefaultProperties()
	 * @return
	 */
	protected Map<String, String> getDefaultProperties() {
		return new DefaultRuntimeProperties().getDefaultProperties();
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
