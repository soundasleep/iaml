package org.openiaml.model.custom.actions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.ModelLoader.ModelLoadException;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.inference.InfiniteSubProgressMonitor;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.Wire;

/**
 * Looks through the model and finds edges which either have no 'from'
 * or 'to', and removes them.
 * 
 * See issue 63.
 * 
 * @see org.openiaml.model.codegen.php
 * @author jmwright
 *
 */
public class RemovePhantomEdgesAction extends IamlFileAction {

	private EObject loadedModel;
	
	@Override
	public EObject getLoadedModel() {
		return loadedModel;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(IFile individual, String message) {
		return "Could not remove phantom edges from '" + individual.getName() + "': " + message;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Remove phantom edges";
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
	 * @param o
	 * @param monitor 
	 * @return 
	 * @throws InferenceException 
	 * @throws IOException 
	 * @throws CoreException 
	 */
	@Override
	public IStatus doExecute(IFile o, IProgressMonitor monitor2) throws InferenceException, FileNotFoundException, IOException, CoreException {
		IProgressMonitor monitor = new InfiniteSubProgressMonitor(monitor2, 100);
		
		monitor.beginTask("Removing phantom edges: '" + o.getName() + "'", 60);
		
		monitor.subTask("Loading model");
		try {
			loadedModel = ModelLoader.load(o);
		} catch (ModelLoadException e) {
			return errorStatus(e);
		}
		monitor.worked(10);
		
		// load the handler to remove elements
		EcoreInferenceHandler handler = new EcoreInferenceHandler(loadedModel.eResource());
		
		// we need to store elements to delete in a buffer, or else
		// deleting elements will affect the iterator and cause exceptions
		List<EObject> elementsToDelete = new ArrayList<EObject>();

		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

		monitor.subTask("Identifying phantom edges");
		Iterator<EObject> it = loadedModel.eAllContents();
		while (it.hasNext()) {
			EObject obj = it.next();
			if (obj instanceof Wire && (((Wire) obj).getFrom() == null || ((Wire) obj).getTo() == null)) {
				// remove this one
				elementsToDelete.add(obj);
			} else if (obj instanceof ExecutionEdge && (((ExecutionEdge) obj).getFrom() == null || ((ExecutionEdge) obj).getTo() == null)) {
				// remove this one
				elementsToDelete.add(obj);
			} else if (obj instanceof DataFlowEdge && (((DataFlowEdge) obj).getFrom() == null || ((DataFlowEdge) obj).getTo() == null)) {
				// remove this one
				elementsToDelete.add(obj);
			}
			// TODO issue 156: does not check all types of Edge
		}
		monitor.worked(30);
		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;
		
		monitor.subTask("Removing phantom edges");
		for (EObject obj : elementsToDelete) {
			if (obj.eContainer() != null) {
				handler.deleteElement(obj, obj.eContainer(), obj.eContainingFeature());
			}
		}
		monitor.worked(10);

		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;

		// save it
		monitor.subTask("Saving");
		loadedModel.eResource().save(getSaveOptions());
		
		// finished
		monitor.done();
		
		return Status.OK_STATUS;
	}
	
	/**
	 * Copied from generated {@link IamlDiagramEditorUtil#getSaveOptions()}.
	 * 
	 * TODO add a test case to check this is identical to the generated version
	 */
	public static Map<?,?> getSaveOptions() {
		Map<Object,Object> saveOptions = new HashMap<Object,Object>();
		saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
				Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		return saveOptions;
	}


}
