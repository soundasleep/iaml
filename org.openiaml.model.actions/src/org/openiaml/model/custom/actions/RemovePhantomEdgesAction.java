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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.inference.InfiniteSubProgressMonitor;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.WireEdge;

/**
 * Looks through the model and finds edges which either have no 'from'
 * or 'to', and removes them.
 * 
 * See issue 63.
 * 
 * @see org.openiaml.model.codegen.oaw
 * @author jmwright
 *
 */
public class RemovePhantomEdgesAction extends IamlFileAction {

	private EObject loadedModel;
	
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
	public IStatus doExecute(IFile o, IProgressMonitor monitor2) throws InferenceException, FileNotFoundException, IOException, CoreException {
		IProgressMonitor monitor = new InfiniteSubProgressMonitor(monitor2, 100);
		
		monitor.beginTask("Removing phantom edges: '" + o.getName() + "'", 60);
		
		// try and load the file directly
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(URI.createFileURI(o.getLocation().toString()), true);
		
		// load the handler to remove elements
		EcoreInferenceHandler handler = new EcoreInferenceHandler(resource);
		
		// we can only do one model
		if (resource.getContents().size() != 1) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Could not transform model: unexpected number of model elements in file (expected: 1, found: " + resource.getContents().size() + ")");
		}
		
		// remove phantom edges
		loadedModel = resource.getContents().get(0);
		
		// we need to store elements to delete in a buffer, or else
		// deleting elements will affect the iterator and cause exceptions
		List<EObject> elementsToDelete = new ArrayList<EObject>();
		
		Iterator<EObject> it = loadedModel.eAllContents();
		while (it.hasNext()) {
			EObject obj = it.next();
			if (obj instanceof WireEdge && (((WireEdge) obj).getFrom() == null || ((WireEdge) obj).getTo() == null)) {
				// remove this one
				elementsToDelete.add(obj);
			} else if (obj instanceof ExecutionEdge && (((ExecutionEdge) obj).getFrom() == null || ((ExecutionEdge) obj).getTo() == null)) {
				// remove this one
				elementsToDelete.add(obj);
			} else if (obj instanceof DataFlowEdge && (((DataFlowEdge) obj).getFrom() == null || ((DataFlowEdge) obj).getTo() == null)) {
				// remove this one
				elementsToDelete.add(obj);
			}
			monitor.worked(1);
		}
		for (EObject obj : elementsToDelete) {
			handler.deleteElement(obj, obj.eContainer(), obj.eContainingFeature());
		}

		// save it
		monitor.subTask("Saving");
		resource.save(getSaveOptions());
		
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
