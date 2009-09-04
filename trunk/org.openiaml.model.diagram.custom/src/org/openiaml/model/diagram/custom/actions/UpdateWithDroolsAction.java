/**
 * 
 */
package org.openiaml.model.diagram.custom.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.openiaml.model.custom.actions.ProgressEnabledAction;
import org.openiaml.model.diagram.helpers.inference.EmfInferenceHandler;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.wires.SyncWire;

/**
 * Abstract class of actions which update or refresh selected
 * model elements, based on the current selection.
 * 
 * Sometimes it is desirable to refresh the entire model, because
 * model inference rules may be using elements that are not
 * contained directly within the current selection (e.g.
 * {@link SyncWire}s connecting two objects).
 * 
 * @author jmwright
 *
 */
public abstract class UpdateWithDroolsAction extends ProgressEnabledAction<GraphicalEditPart> {

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(GraphicalEditPart individual, String message) {
		return "Could not update " + getTitle() + " with Drools for '" + individual + "': " + message;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Updating " + getTitle();
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getSelection(java.lang.Object[])
	 */
	@Override
	public List<GraphicalEditPart> getSelection(Object[] selection) {
		final List<GraphicalEditPart> ifiles = new ArrayList<GraphicalEditPart>();
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof GraphicalEditPart) {
					ifiles.add((GraphicalEditPart) o);
				}
			}
		}
		
		return ifiles;
	}
	
	/**
	 * What type of edit parts should this action be concerned with?
	 * The edit part should resolve EObjects of type
	 * {@link #getExpectedEObjectClass()}.
	 *  
	 * @see #getExpectedEObjectClass()
	 * @return An edit part type class
	 */
	public abstract Class<? extends GraphicalEditPart> getEditPartClass();

	/**
	 * What type do we expect the selected EditPart's EObject will resolve to?
	 * 
	 * @return An EObject class
	 */
	public abstract Class<? extends EObject> getExpectedEObjectClass();
	
	/**
	 * The title of the update action. This is used to construct
	 * progress messages.
	 * 
	 * e.g. "form"
	 * 
	 * @return A title string
	 */
	public abstract String getTitle();
	
	/**
	 * Which EObject should the inference start with? By default,
	 * it will select the currently selected EObject; but for some
	 * use cases, the root of the model may be required (e.g. SyncWires).
	 * In this case, {@link #getRoot(EObject)} may be required. 
	 * 
	 * By default, simply returns the current object.
	 * 
	 * @see #getRoot(EObject)
	 * @param element
	 * @return
	 */
	public EObject selectRootElement(EObject element) {
		return element;
	}
	
	/**
	 * Select and create the Drools engine for updating.
	 * This will usually be a specific engine implementation
	 * which only selects a small subset of rule files (i.e.
	 * it does not select all of the rules at once).
	 * 
	 * @return The engine to use
	 */
	public abstract DroolsInferenceEngine getEngine(ICreateElements handler);
	
	/**
	 * Refresh the mappings manually. Also useful for test cases.
	 * 
	 * @throws InferenceException 
	 */
	public void refreshMappings(EObject resolved, ICreateElements handler, IProgressMonitor monitor) throws InferenceException {
		DroolsInferenceEngine engine = getEngine(handler);
		engine.create(resolved, new SubProgressMonitor(monitor, 100));
	}
	
	/**
	 * Wrap the mapping refresh action with monitor updates, and check
	 * that the selected type resolves to the correct object type.
	 * 
	 * @param part The edit part to work with
	 * @param monitor 
	 * @return 
	 */
	public IStatus execute(GraphicalEditPart part, IProgressMonitor monitor) {
		try {
			monitor.beginTask("Refreshing " + getTitle() + " mappings", 100);
			
			EObject obj = part.resolveSemanticElement();
			if (!getExpectedEObjectClass().isAssignableFrom(obj.getClass())) {
				throw new InferenceException("Resolved EObject from EditPart was not of expected type " + getExpectedEObjectClass().getSimpleName() + ": was " + obj.getClass().getSimpleName());
			}
			
			// select the actual target
			obj = selectRootElement(obj);
						
			refreshMappings(obj, new EmfInferenceHandler(
					part.getEditingDomain(), 
					new ArrayList<Object>(), /* affected files */
					new SubProgressMonitor(monitor, 100), 
					null /* IAdapter == null */,
					obj.eResource()	/* eResource */
			), monitor);
			
			monitor.done();
				
			return Status.OK_STATUS;
	
		} catch (InferenceException e) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Inference failed: " + e.getMessage(), e);
		}

	}
	
	/**
	 * Iterate through the object until we find the root object, if any.
	 * 
	 * @param resolved
	 * @return the root EObject of the given object
	 */
	public EObject getRoot(EObject resolved) {
		if (resolved.eContainer() == null)
			return resolved;
		return getRoot(resolved.eContainer());
	}

	/**
	 * Any additional model element checks?
	 * 
	 * @param object
	 * @throws InferenceException if something is wrong with the model element
	 */
	public void checkModelElement(EObject object) throws InferenceException {
		// by default, does nothing
	}

}