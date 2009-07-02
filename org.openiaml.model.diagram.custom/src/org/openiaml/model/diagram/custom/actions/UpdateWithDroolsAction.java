/**
 * 
 */
package org.openiaml.model.diagram.custom.actions;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.inference.EmfInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;
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
public abstract class UpdateWithDroolsAction implements IViewActionDelegate {

	public static final String PLUGIN_ID = "org.openiaml.model.diagram.custom";
	Object[] selection;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	@Override
	public void init(IViewPart view) {
		// does nothing
	}
	
	/**
	 * What type of edit parts should this action be concerned with?
	 * The edit part should resolve EObjects of type
	 * {@link #getExpectedEObjectClass()}.
	 *  
	 * @see #getExpectedEObjectClass()
	 * @return An edit part type class
	 */
	public abstract Class<? extends ShapeNodeEditPart> getEditPartClass();

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
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		if (selection == null)
			return;
		
		for (Object o : selection) {
			if (getEditPartClass().isInstance(o)) {
				ShapeNodeEditPart part = (ShapeNodeEditPart) o;
				IStatus status = refreshMappings(part, action, new NullProgressMonitor());
				if (!status.isOK()) {
					String message = "Could not refresh " + getTitle() + " mappings for " + o + ": " + status.getMessage();

					// TODO remove this reference to the plugin and remove the reference in plugin.xml
					IamlDiagramEditorPlugin.getInstance().logError(message, status.getException());
					MessageDialog.openError(getActiveShell(), "Error", status.getMessage());
				}
			}
		}

	}
	
	private Shell getActiveShell() {
		return IamlDiagramEditorPlugin.getInstance().getWorkbench().getDisplay().getActiveShell();
	}
	
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
	protected IStatus refreshMappings(ShapeNodeEditPart part, IAction action, IProgressMonitor monitor) {
		try {
			monitor.beginTask("Refreshing " + getTitle() + " mappings", 200);
			
			EObject obj = part.resolveSemanticElement();
			if (getExpectedEObjectClass().isInstance(obj)) {
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