package org.openiaml.model.diagram.custom.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.inference.EmfInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;

/**
 * An action which infers the entire model, but only includes
 * elements that can be contained within the currently selected
 * element.
 * 
 * That is:
 * - Directly contained nodes
 * - Directly contained relationships
 * 
 * Perhaps there are more node/relationship types we can add? 
 * Not sure if this is very user friendly.
 * 
 * @author jmwright
 */
public class InferContainedElementsAction implements IViewActionDelegate {

	public static final String PLUGIN_ID = "org.openiaml.model.diagram.custom";
	Object[] selection;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	@Override
	public void init(IViewPart view) {
		// does nothing
	}
		
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		if (selection == null)
			return;
		
		for (Object o : selection) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart part = (ShapeNodeEditPart) o;
				IStatus status = inferContainedElements(part, action, new NullProgressMonitor());
				if (!status.isOK()) {
					String message = "Could not infer contained elements for " + o + ": " + status.getMessage();

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
	 * Select and create the Drools engine for updating.
	 * This will usually be a specific engine implementation
	 * which only selects a small subset of rule files (i.e.
	 * it does not select all of the rules at once).
	 * 
	 * @return The engine to use
	 */
	public DroolsInferenceEngine getEngine(ICreateElements handler) {
		// get the normal inference engine
		// TODO refactor this out properly
		return new CreateMissingElementsWithDrools(handler);
	}
	
	/**
	 * Extend the normal inference handler to allow us to remove elements
	 * that aren't contained directly later.
	 * 
	 * @author jmwright
	 *
	 */
	public class CreateElementsWithinContainer extends EmfInferenceHandler {

		private EObject container;
		
		/**
		 * Elements to delete later through {@link #removeUncontainedElements()}
		 */
		private List<EObject> toDelete = new ArrayList<EObject>();
		private Map<EObject, EObject> deleteContainer = new HashMap<EObject, EObject>();
		private Map<EObject, EStructuralFeature> deleteFeature = new HashMap<EObject, EStructuralFeature>();

		/**
		 * Calls super constructor.
		 * 
		 * @param container the element to select as the containing element
		 */
		public CreateElementsWithinContainer(
				EObject container,
				TransactionalEditingDomain editingDomain,
				List<?> affectedFiles, IProgressMonitor monitor,
				IAdaptable info, Resource resource) {
			super(editingDomain, affectedFiles, monitor, info, resource);
			this.container = container;
		}

		/**
		 * Remove any elements added during inference, that weren't contained
		 * by the given element.
		 * @throws InferenceException 
		 */
		public void removeUncontainedElements() throws InferenceException {
			for (EObject obj : toDelete) {
				deleteElement(obj, deleteContainer.get(obj), deleteFeature.get(obj));
			}
		}
		
		@Override
		public EObject createElement(EObject container, EClass elementType,
				EStructuralFeature containerFeature) throws InferenceException {
		
			// get parent to create the element
			EObject result = super.createElement(container, elementType, containerFeature);
			System.out.println("Creating element " + result);
			
			if (!isContainedBy(this.container, container)) {
				// add the element to delete later
				toDelete.add(result);
				deleteContainer.put(result, container);
				deleteFeature.put(result, containerFeature);
			}
			
			return result;

		}

		/**
		 * Does the given 'container' element contain, either directly or
		 * indirectly, the given element?
		 * 
		 * @param container which might contain 'element'
		 * @param element the element whose containment is in question
		 * @return
		 */
		private boolean isContainedBy(EObject container, EObject element) {
			if (element == null || container == null) {
				return false;
			}
			if (container.equals(element)) {
				return true;
			}
			return isContainedBy(container, element.eContainer());
		}

		@Override
		public EObject createRelationship(EObject container,
				EClass elementType, EObject source, EObject target,
				EStructuralFeature containerFeature,
				EStructuralFeature sourceFeature,
				EStructuralFeature targetFeature) throws InferenceException {

			// TODO Auto-generated method stub
			return super.createRelationship(container, elementType, source, target,
					containerFeature, sourceFeature, targetFeature);
			
		}
		
		
		
	}
	
	/**
	 * Refresh the mappings manually. Also useful for test cases.
	 * 
	 * @param root the root element, i.e. the element to begin inference from
	 * @throws InferenceException 
	 */
	public void refreshContainedMappings(EObject root,
			CreateElementsWithinContainer handler, IProgressMonitor monitor) throws InferenceException {

		// infer like normal...
		DroolsInferenceEngine engine = getEngine(handler);
		engine.create(root, new SubProgressMonitor(monitor, 100));
		
		// but our handler will remove any incorrect elements
		handler.removeUncontainedElements();
		
	}

	/**
	 * Wrap the action with monitor updates.
	 * 
	 * @param part The edit part to work with
	 * @param monitor 
	 * @return 
	 */
	protected IStatus inferContainedElements(ShapeNodeEditPart part, IAction action, IProgressMonitor monitor) {
		try {
			monitor.beginTask("Inferring contained elements", 200);
			
			EObject container = part.resolveSemanticElement();
			
			// select the actual target (the absolute root)
			EObject root = getRoot(container);
			
			/*
			// we need a copy of the root
			Copier copier = new Copier();
			EObject rootCopy = copier.copy(root);
			Collection<EObject> results = copier.copyAll(getCollection(root.eAllContents()));
			copier.copyReferences();
			
			// the root element should be distinct but equal
			assert(root != rootCopy);
			assert(root.equals(rootCopy));
			*/
			
			refreshContainedMappings(root, new CreateElementsWithinContainer(
					container,
					part.getEditingDomain(), 
					new ArrayList<Object>(), /* affected files */
					new SubProgressMonitor(monitor, 100), 
					null /* IAdapter == null */,
					container.eResource()	/* eResource */
			), monitor);
			
			monitor.done();
				
			return Status.OK_STATUS;
	
		} catch (InferenceException e) {
			return new Status(IStatus.ERROR, PLUGIN_ID, "Inference failed: " + e.getMessage(), e);
		}

	}
	
	/**
	 * From an iterator, create a collection of its contents.
	 * 
	 * @param allContents
	 * @return
	 */
	private Collection<EObject> getCollection(TreeIterator<EObject> it) {
		Collection<EObject> c = new ArrayList<EObject>();
		while (it.hasNext()) {
			c.add(it.next());
		}
		return c;
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
