/**
 * 
 */
package org.openiaml.model.custom.actions;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;
import org.openiaml.model.EmfToDot;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.ModelLoader.ModelLoadException;
import org.openiaml.model.inference.InferenceException;

/**
 * @author jmwright
 *
 */
public class ExportDotGraphAction extends IamlFileAction {

	private EObject model;
	protected IFile target;
	
	/**
	 * Extending this method allows us to pass the second parameter
	 * to {@link EmfToDot#toDot(EObject, EObject)}. The default
	 * implementation just loads the same model.
	 * 
	 * <p>We have to use the original IFile, so that the given model
	 * has an eResource that it can operate on.
	 * 
	 * @param monitor 
	 * @throws InferenceException 
	 * @throws ModelLoadException 
	 */
	public EObject getTargetModel(IFile source, IProgressMonitor monitor) throws InferenceException, ModelLoadException {
		try {
			return ModelLoader.load(source);
		} finally {
			monitor.done();
		}
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.IamlFileAction#doExecute(org.eclipse.core.resources.IFile, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus doExecute(final IFile source, IProgressMonitor monitor)
			throws InferenceException, IOException, CoreException {
		
		// first, get the target filename
		// need to run this in the UI thread
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				target = MigrateModelAction.askForFilename(source, "dot");
			}
			
		});
		
		// this will only resume once we have a filename
		if (target == null)
			return Status.CANCEL_STATUS;	// cancelled
		
		// now we begin the export
		monitor.beginTask(getProgressMessage(), 100);
		
		monitor.subTask("Loading model");
		try {
			model = ModelLoader.load(source);
		} catch (ModelLoadException e) {
			return errorStatus("Could not load model", e);
		}
		
		monitor.worked(10);
		if (monitor.isCanceled()) return Status.CANCEL_STATUS;
		
		// do any processing
		monitor.subTask("Processing source model");
		EObject targetModel;
		try {
			targetModel = getTargetModel(source, new SubProgressMonitor(monitor, 50));
		} catch (ModelLoadException e) {
			return errorStatus("Could not load target model", e);
		}
		
		// now export as DOT
		monitor.subTask("Exporting model");
		EmfToDot exporter = new EmfToDot();
		String output = exporter.toDot(model, targetModel);
		monitor.worked(80);
		
		// and save to file
		target.create(new ByteArrayInputStream(output.getBytes("UTF-8")), true, new SubProgressMonitor(monitor, 10));
		
		return Status.OK_STATUS;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.IamlFileAction#getLoadedModel()
	 */
	@Override
	protected EObject getLoadedModel() {
		return model;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.ProgressEnabledAction#getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(IFile individual, String message) {
		return "Could not export DOT graph for '" + individual.getName() + "': " + message;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.ProgressEnabledAction#getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Exporting DOT graph";
	}

}
