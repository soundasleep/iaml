/**
 * 
 */
package org.openiaml.model.inference;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.wires.SyncWire;

/**
 * @author jmwright
 *
 */
public class CreateMissingElements {
	
	private ICreateElements parent;

	public CreateMissingElements(ICreateElements parent) {
		this.parent = parent;
	}
	
	/**
	 * Use inference (or whatever) to create missing elements in the model.
	 * The ICreateElements parent will actually create any elements needed.
	 * 
	 * @param parent
	 * @param rootObject
	 * @throws InferenceException 
	 */
	public void create(EObject rootObject) throws InferenceException {

		if (rootObject instanceof InternetApplication) {
			InternetApplication vt = (InternetApplication) rootObject;

			for (ApplicationElement e : vt.getChildren()) {
				handleChild(e);
				
				// recursive
				create(e);
			}
		}
		if (rootObject instanceof ApplicationElementContainer) {
			ApplicationElementContainer vt = (ApplicationElementContainer) rootObject;

			for (ApplicationElement e : vt.getChildren()) {
				handleChild(e);
				
				// recursive
				create(e);
			}
		}
		if (rootObject instanceof DomainStore) {
			DomainStore vt = (DomainStore) rootObject;

			for (ApplicationElement e : vt.getChildren()) {
				handleChild(e);
				
				// recursive
				create(e);
			}
		}
		if (rootObject instanceof DomainObject) {
			DomainObject vt = (DomainObject) rootObject;

			for (ApplicationElement e : vt.getAttributes()) {
				handleChild(e);
				
				// recursive
				create(e);
			}
		}
				
	}
	
	private void handleChild(GeneratesElements e) throws InferenceException {
		// don't generate elements if it has been overridden
		if (!e.isOverridden()) {
			
			// get all the input forms
			if (e instanceof ApplicationElementContainer) {
				ApplicationElementContainer f = (ApplicationElementContainer) e;
				
				// get all the wires
				for (WireEdge w : f.getOutEdges()) {
					// get all the sync wires
					
					if (w instanceof SyncWire && ((SyncWire) w).getTo() instanceof ApplicationElementContainer) {
						// sync up these elements
						doSyncWires(f, (ApplicationElementContainer) w.getFrom(), (ApplicationElementContainer) w.getTo(), (SyncWire) w);
						// and back again
						doSyncWires(f, (ApplicationElementContainer) w.getTo(), (ApplicationElementContainer) w.getFrom(), (SyncWire) w);
					}
				}
				
			}
		}
	}
		

	/**
	 * A SyncWire between a source and a target: all of the components in the
	 * source should be linked up with the target. needs to be called twice to
	 * sync up bidirectionally.
	 * 
	 * @param source
	 * @param target
	 * @throws InferenceException 
	 */
	protected void doSyncWires(WireEdgesSource container, ApplicationElementContainer source, ApplicationElementContainer target, SyncWire generatedBy) throws InferenceException {
		// map each of the children in the source
		for (ApplicationElement c : source.getChildren()) {
			ApplicationElement mapTarget = getChildMatch(c, target);
			if (mapTarget != null) {
				// we have an element to map to
				// is it already mapped?
				if (!elementsAreAlreadySyncWire(c, mapTarget)) {
					// map them together
					
					SyncWire wire = parent.createSyncWire(container, c, mapTarget);
					Assert.isTrue(wire != null);
					
					// set sync wire parameters
					parent.setValue(wire, ModelPackage.eINSTANCE.getNamedElement_Name(), "sync[generated]");
					// set isGenerated parameter
					parent.setValue(wire, ModelPackage.eINSTANCE.getGeneratedElement_IsGenerated(), true);
					// set generatedBy parameter
					parent.setValue(wire, ModelPackage.eINSTANCE.getGeneratedElement_GeneratedBy(), generatedBy);

				}
			}
		}
	}
	
	
	/**
	 * are two elements already sync wired together?
	 * 
	 * @param c
	 * @param mapTarget
	 * @return
	 */
	private boolean elementsAreAlreadySyncWire(ApplicationElement c,
			ApplicationElement mapTarget) {
		
		for (WireEdge w : c.getOutEdges()) {
			if (w.getTo().equals(mapTarget) && w instanceof SyncWire)
				return true;
		}
		for (WireEdge w : c.getInEdges()) {
			if (w.getFrom().equals(mapTarget) && w instanceof SyncWire)
				return true;
		}
		
		return false;
		
	}

	/**
	 * Get a child in the targetParent that matches the source element
	 * 
	 * @see #childrenMapUp(ApplicationElement, ApplicationElement)
	 * @param source
	 * @param targetParent
	 * @return an ApplicationElement, or null if none match
	 */
	private ApplicationElement getChildMatch(ApplicationElement source, ApplicationElementContainer targetParent) {
		for (ApplicationElement c : targetParent.getChildren()) {
			if (childrenMapUp(source, c))
				return c;
		}
		return null;
	}
	
	/**
	 * Do two elements match up, for sync purposes? In our case, we say they
	 * do if they have the same name (case insensitive)
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	private boolean childrenMapUp(ApplicationElement source, ApplicationElement target) {
		return source.getName().toLowerCase().equals(target.getName().toLowerCase());
	}
	
	
}
