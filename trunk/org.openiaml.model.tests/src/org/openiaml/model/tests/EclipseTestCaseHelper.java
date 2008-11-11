/**
 * 
 */
package org.openiaml.model.tests;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.diagram.part.IamlDiagramEditor;
import org.openiaml.model.model.visual.Page;

/**
 * Adds some helper assert...() methods to the EclipseTestCase.
 * 
 * @see EclipseTestCase
 * @author jmwright
 *
 */
public abstract class EclipseTestCaseHelper extends EclipseTestCase {
	
	/**
	 * Look at the editor's children to see if a Page is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	protected ShapeNodeEditPart assertHasRootPage(IamlDiagramEditor root, String pageName) {
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof Page) {
					Page p = (Page) obj;
					if (p.getName().equals(pageName))
						return s;
				}
			}
		}
		// failed
		fail("assertHasRootPage: no page '" + pageName + "' found.");
		return null;
	}

	/**
	 * Look at the editor's children to see if a Domain Store is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	protected ShapeNodeEditPart assertHasRootDomainStore(IamlDiagramEditor root, String storeName) {
		String found = "";
		
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof DomainStore) {
					DomainStore p = (DomainStore) obj;
					if (p.getName().equals(storeName))
						return s;
					found += p.getName() + ",";
				}
			}
		}
		// failed
		fail("assertHasRootDomainStore: no domain store '" + storeName + "' found. Found: " + found);
		return null;
	}

	/**
	 * Look at the editor's children to see if a Domain Store is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	protected ShapeNodeEditPart assertHasRootEventTrigger(IamlDiagramEditor root, String eventName) {
		String found = "";
		
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof EventTrigger) {
					EventTrigger p = (EventTrigger) obj;
					if (p.getName().equals(eventName))
						return s;
					found += p.getName() + ",";
				}
			}
		}
		// failed
		fail("assertHasRootEventTrigger: no event trigger '" + eventName + "' found. Found: " + found);
		return null;
	}

	/**
	 * Look at the editor's children to see if a Domain Store is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	protected ShapeNodeEditPart assertHasRootOperation(IamlDiagramEditor root, String operationName) {
		String found = "";
		
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof Operation) {
					Operation p = (Operation) obj;
					if (p.getName().equals(operationName))
						return s;
					found += p.getName() + ",";
				}
			}
		}
		// failed
		fail("assertHasRootOperation: no operation '" + operationName + "' found. Found: " + found);
		return null;
	}
	
}
