package org.openiaml.model.diagram.custom.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.codegen.ICodeGenerator;
import org.openiaml.model.codegen.oaw.OawCodeGenerator;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;

/**
 * Action to generate code from an .iaml file
 * 
 * @see org.openiaml.model.codegen.oaw
 * @author jmwright
 *
 */
public class GenerateCodeAction implements IViewActionDelegate {

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
	public void run(final IAction action) {
		model = null;
		
		final List<IFile> ifiles = new ArrayList<IFile>();
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IFile) {
					ifiles.add((IFile) o);
				}
			}
		}
		
		/**
		 * Create a progress display monitor, and actually
		 * execute the code generation.
		 */
		try {
			PlatformUI.getWorkbench().getProgressService().
			busyCursorWhile(new IRunnableWithProgress() {
			    public void run(IProgressMonitor monitor) {
			    	int scale = 4000;
			    	
			    	monitor.beginTask("Generating code", ifiles.size() * scale);
			    	
			    	for (IFile f : ifiles) {
			    		// create a new sub-progress
			    		IProgressMonitor subMonitor = new SubProgressMonitor(monitor, 1 * scale);
			    		
						IStatus status = generateCodeFrom(f, action, subMonitor);
						if (!status.isOK()) {
							IStatus multi = new MultiStatus(PLUGIN_ID, Status.ERROR, new IStatus[] { status }, "Could not generate code for '" + f.getName() + "': " + status.getMessage(), status.getException());							
							Platform.getLog(getDefaultPlugin().getBundle()).log(multi);
							
							monitor.done();
							return;
						}
			    	}
			    	
			    	monitor.done();
			    }
			});
		} catch (InvocationTargetException e) {
			getDefaultPlugin().logError(e.getMessage(), e);
		} catch (InterruptedException e) {
			getDefaultPlugin().logError(e.getMessage(), e);
		}

	}
	
	/**
	 * Get the default editor plugin, which we will use to log errors and the like.
	 * 
	 * TODO remove this direct reference and remove IamlDiagramEditorPlugin from the plugin.xml.
	 * 
	 * @return
	 */
	public IamlDiagramEditorPlugin getDefaultPlugin() {
		return IamlDiagramEditorPlugin.getInstance();
	}
	
	/**
	 * @param o
	 * @param monitor 
	 * @return 
	 */
	protected IStatus generateCodeFrom(IFile o, IAction action, IProgressMonitor monitor) {
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
			return new Status(IStatus.ERROR, PLUGIN_ID, "Inference failed", e);
		} catch (IOException e) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "IO exception", e);
		} catch (CoreException e) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Core exception", e);
		}
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
