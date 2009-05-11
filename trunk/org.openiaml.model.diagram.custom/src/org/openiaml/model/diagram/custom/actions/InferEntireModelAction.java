package org.openiaml.model.diagram.custom.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
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
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;

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
public class InferEntireModelAction implements IViewActionDelegate {

	/**
	 * The loaded model.
	 */
	private EObject model = null;

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
		model = null;
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IFile) {
					IStatus status = inferEntireModel((IFile) o, action, new NullProgressMonitor());
					if (!status.isOK()) {
						// TODO remove this reference to the plugin and remove the reference in plugin.xml
						IamlDiagramEditorPlugin.getInstance().logError(
								"Could not infer entire model for " + o + ": " + status.getMessage(), status.getException()); //$NON-NLS-1$
					}
				}
			}
		}

	}
	
	/**
	 * @param o Both the source file and the target file.
	 * @param monitor 
	 * @return 
	 */
	protected IStatus inferEntireModel(IFile o, IAction action, IProgressMonitor monitor) {
		try {
			if (o.getFileExtension().equals("iaml")) {
				monitor.beginTask("Inferring model in file '" + o.getName() + "'", 50);
				
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
				CreateMissingElementsWithDrools ce = new CreateMissingElementsWithDrools(handler);
				ce.create(model, new SubProgressMonitor(monitor, 45));

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
				
				// now load it in as an IFile
				tempFile.create(new FileInputStream(tempJavaFile), true, monitor);
		
				// rename the tempFile
				o.delete(true, monitor);
				tempFile.move(o.getFullPath(), true, monitor);
				tempJavaFile.delete();
				
				// finished
				monitor.done();
				
				return Status.OK_STATUS;
			}
		} catch (InferenceException e) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Inference failed", e);
		} catch (IOException e) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "IO exception", e);
		} catch (CoreException e) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Core exception", e);
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

	public EObject getLoadedModel() {
		return model;
	}

}
