package org.openiaml.model.diagram.custom.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.openiaml.model.custom.actions.ProgressEnabledAction;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.inference.EcoreCreateElementsHelper;
import org.openiaml.model.inference.EmfInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;

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
public class InferContainedElementsAction extends ProgressEnabledAction<GraphicalEditPart> {

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(GraphicalEditPart individual, String message) {
		return "Could not infer contained elements for '" + individual + "': " + message;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Inferring contained elements";
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
	 * Select and create the Drools engine for updating.
	 * This will usually be a specific engine implementation
	 * which only selects a small subset of rule files (i.e.
	 * it does not select all of the rules at once).
	 * 
	 * @return The engine to use
	 */
	public DroolsInferenceEngine getEngine(ICreateElements handler) {
		// we add trackInsertions=true so we can actually get tracked insertions
		return new CreateMissingElementsWithDrools(handler, true);
	}
	
	/**
	 * Extend the normal inference handler to allow us to remove elements
	 * that aren't contained directly later.
	 * 
	 * @author jmwright
	 *
	 */
	public class CreateElementsWithinContainer extends EcoreCreateElementsHelper {

		private EObject container;
		private ICreateElements parent;
		
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
				ICreateElements parent) {
			this.parent = parent;
			this.container = container;
		}

		/**
		 * Remove any elements added during inference, that weren't contained
		 * by the given element.
		 * 
		 * This also goes through the DroolsInsertionQueue to get elements that
		 * were added in the same activation. Any elements created in the same activation
		 * are also removed, otherwise our property that allows us to remove elements
		 * will not hold.
		 * 
		 * Consider:
		 * f(a), f(b) <- a, b
		 * 
		 * If we delete f(a), the rule may not fire again now that f(b) exists, so
		 * we need to delete f(b) as well.
		 * @param engine 
		 * @param monitor 
		 * 
		 * @throws InferenceException 
		 */
		public void removeUncontainedElements(DroolsInferenceEngine engine, IProgressMonitor monitor) throws InferenceException {
			monitor.beginTask("Removing uncontained elements", toDelete.size() + 1);
			
			for (EObject obj : toDelete) {
				deleteElement(obj, deleteContainer.get(obj), deleteFeature.get(obj));
				
				// are there any other elements in the queue which we need to
				// delete, i.e. created by the same activation?
				Object a = engine.getDroolsInsertionQueue().getActivationFor(obj);
				if (a == null) {
					throw new NullPointerException("The EObject '" + obj + "' did not have a matching activation in the queue.");					
				}
				for (EObject other : engine.getDroolsInsertionQueue().getInsertedObjectsForActivation(a)) {
					deleteElement(other, deleteContainer.get(other), deleteFeature.get(other));
				}
				
				monitor.worked(1);
			}
			
			monitor.done();
		}
		
		@Override
		public EObject createElement(EObject container, EClass elementType,
				EStructuralFeature containerFeature) throws InferenceException {
		
			// get parent to create the element
			EObject result = parent.createElement(container, elementType, containerFeature);
			
			if (!isContainedBy(this.container, container)) {
				// add the element to delete later
				toDelete.add(result);
			}
			
			// save a copy of all containers and features
			// (may be needed later when we delete inserted EObjects that were
			// inserted in the same Activation)
			deleteContainer.put(result, container);
			deleteFeature.put(result, containerFeature);
			
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
			return parent.createRelationship(container, elementType, source, target,
					containerFeature, sourceFeature, targetFeature);
			
		}

		/* (non-Javadoc)
		 * @see org.openiaml.model.inference.ICreateElements#deleteElement(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature)
		 */
		@Override
		public void deleteElement(EObject object, EObject container,
				EStructuralFeature containerFeature) throws InferenceException {
			parent.deleteElement(object, container, containerFeature);
		}

		/* (non-Javadoc)
		 * @see org.openiaml.model.inference.ICreateElements#setValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
		 */
		@Override
		public void setValue(EObject element, EStructuralFeature reference,
				Object value) throws InferenceException {
			parent.setValue(element, reference, value);
		}		
		
		/* (non-Javadoc)
		 * @see org.openiaml.model.inference.ICreateElements#setValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
		 */
		@Override
		public void addReference(EObject element, EStructuralFeature reference,
				Object value) throws InferenceException {
			parent.addReference(element, reference, value);
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
		monitor.beginTask("Inferring contained elements", 150);
		
		// infer like normal...
		DroolsInferenceEngine engine = getEngine(handler);
		engine.create(root, new SubProgressMonitor(monitor, 100));
		
		// but our handler will remove any incorrect elements
		monitor.subTask("Removing uncontained elements");
		handler.removeUncontainedElements(engine, new SubProgressMonitor(monitor, 50));
		
		monitor.done();
	}

	/**
	 * Wrap the action with monitor updates.
	 * 
	 * @param part The edit part to work with
	 * @param monitor 
	 * @return 
	 */
	public IStatus execute(GraphicalEditPart part, IProgressMonitor monitor) {
		try {
			EObject container = part.resolveSemanticElement();

			// select the actual target (the absolute root)
			EObject root = getRoot(container);
			
			refreshContainedMappings(root, new CreateElementsWithinContainer(
					container,
					new EmfInferenceHandler(
						part.getEditingDomain(), 
						new ArrayList<Object>(), /* affected files */
						new SubProgressMonitor(monitor, 100), 
						null /* IAdapter == null */,
						container.eResource()	/* eResource */
					)
			), monitor);
				
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

}
