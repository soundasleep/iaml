package org.openiaml.model.diagram.custom.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.openiaml.model.codegen.ICodeGenerator;
import org.openiaml.model.codegen.jet.JetCodeGenerator;
import org.openiaml.model.inference.CreateMissingElements;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;

/**
 * Action to generate code from an .iaml file
 * 
 * @see org.openiaml.model.codegen.jet
 * @author jmwright
 *
 */
public class GenerateCodeAction implements IViewActionDelegate {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IFile) {
					IStatus status = generateCodeFrom((IFile) o, action, new NullProgressMonitor());
					if (!status.isOK()) {
						// TODO remove this reference to the plugin and remove the reference in plugin.xml
						IamlDiagramEditorPlugin.getInstance().logError(
								"Could not generate code for " + o + ": " + status.getMessage(), status.getException()); //$NON-NLS-1$
					}
				}
			}
		}

	}
	
	/**
	 * @param o
	 * @param monitor 
	 * @return 
	 */
	private IStatus generateCodeFrom(IFile o, IAction action, IProgressMonitor monitor) {
		try {
			if (o.getFileExtension().equals("iaml")) {
				
				// try and load the file directly
				ResourceSet resourceSet = new ResourceSetImpl();
				Resource resource = resourceSet.getResource(URI.createFileURI(o.getLocation().toString()), true);
				
				// load the inference elements manager
				ICreateElements handler = new EcoreInferenceHandler(resource);
				
				// do inference on the model
				for (EObject model : resource.getContents()) {
					CreateMissingElements ce = new CreateMissingElements(handler);
					ce.create(model);
				}
				
				// output the temporary changed model to an external file
				// so we can do code generation
				File tempFile;
				try {
					tempFile = File.createTempFile("temp-iaml-gen", ".iaml");
				} catch (IOException e) {
					return new Status(Status.ERROR, PLUGIN_ID, "Could not create temporary file.", e);
				}
				
				// save to xmi with EMF				
				Map<?,?> options = resourceSet.getLoadOptions();
				try {
					resource.save(new FileOutputStream(tempFile), options);
				} catch (FileNotFoundException e) {
					return new Status(Status.ERROR, PLUGIN_ID, "File not found: " + tempFile, e);
				} catch (IOException e) {
					return new Status(Status.ERROR, PLUGIN_ID, "IO Exception", e);
				}
				
				// create code generator instance
				ICodeGenerator codegen = new JetCodeGenerator(monitor);
				IStatus status = codegen.generateCode(tempFile.getAbsolutePath(), o.getProject().getName());
				
				// now delete the generated model file
				if (!tempFile.delete() && status.isOK()) {
					// return new MultiStatus(PLUGIN_ID, Status.WARNING, new IStatus[] { status }, "Could not delete temporary file: " + modelFile, null);
					return new Status(Status.WARNING, PLUGIN_ID, "Could not delete temporary file: " + tempFile);
				}
				
				return status;
			}
		} catch (InferenceException e) {
			// int severity, String pluginId, String message, Throwable exception
			return new Status(IStatus.ERROR, PLUGIN_ID, "Inference failed", e);
		}
		
		return null;
	}
	
	public static final String PLUGIN_ID = "org.openiaml.model.diagram.custom";

	Object[] selection;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = null;
		if (selection instanceof IStructuredSelection) {
			this.selection = ((IStructuredSelection) selection).toArray();
		}
	}

}
