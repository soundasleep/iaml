/**
 * 
 */
package org.openiaml.model.tests;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.HintedDiagramLinkStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.RunInstanceWire;

/**
 * Adds some helper assert...() methods to the EclipseTestCase.
 * 
 * @see EclipseTestCase
 * @author jmwright
 *
 */
public abstract class EclipseTestCaseHelper extends EclipseTestCase {

	/**
	 * Assert that the given edit part is a shortcut element.
	 */
	protected void assertShortcut(ShapeNodeEditPart part) {
		View view = (View) part.getModel();
		assertNotNull("part '" + part + "' does not have a shortcut annotation", view.getEAnnotation("Shortcut"));
	}

	/**
	 * Assert that the given edit part is a shortcut element.
	 */
	protected void assertNotShortcut(ShapeNodeEditPart part) {
		View view = (View) part.getModel();
		assertNull("part '" + part + "' has a shortcut annotation", view.getEAnnotation("Shortcut"));
	}
	
	/**
	 * Assert that the given edit part is openable, that is, it has an Open Diagram Hint
	 */
	protected void assertOpenable(ShapeNodeEditPart part) {
		View view = (View) part.getModel();
		Style link = view.getStyle(NotationPackage.eINSTANCE
				.getHintedDiagramLinkStyle());
		if (link == null || !(link instanceof HintedDiagramLinkStyle))
			fail("part '" + part + "' is not a shortcut, it should be (link=" + link + ")");
	}
	
	/**
	 * Assert that the given edit part is not openable, that is, it does not have an Open Diagram Hint
	 */
	protected void assertNotOpenable(ShapeNodeEditPart part) {
		View view = (View) part.getModel();
		Style link = view.getStyle(NotationPackage.eINSTANCE
				.getHintedDiagramLinkStyle());
		if (!(link == null || !(link instanceof HintedDiagramLinkStyle)))
			fail("part '" + part + "' is not a shortcut, it should be (link=" + link + ")");
	}
	
	/**
	 * Look at the editor's children to see if a Page is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	protected ShapeNodeEditPart assertHasPage(DiagramDocumentEditor root, String pageName) {
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
		fail("assertHasPage: no page '" + pageName + "' found.");
		return null;
	}

	/**
	 * Look at the editor's children to see if a Domain Store is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	protected ShapeNodeEditPart assertHasDomainStore(DiagramDocumentEditor root, String storeName) {
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
		fail("assertHasDomainStore: no domain store '" + storeName + "' found. Found: " + found);
		return null;
	}

	/**
	 * Look at the editor's children to see if an Event Trigger is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	protected ShapeNodeEditPart assertHasEventTrigger(DiagramDocumentEditor root, String eventName) {
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
		fail("assertHasEventTrigger: no event trigger '" + eventName + "' found. Found: " + found);
		return null;
	}
	
	/**
	 * Look at the editor's children to see if a Domain Attribute is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	protected ShapeNodeEditPart assertHasDomainAttribute(DiagramDocumentEditor root, String attrName) {
		String found = "";
		
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof DomainAttribute) {
					DomainAttribute p = (DomainAttribute) obj;
					if (p.getName().equals(attrName))
						return s;
					found += p.getName() + ",";
				}
			}
		}
		// failed
		fail("assertHasDomainAttribute: no domain attribute '" + attrName + "' found. Found: " + found);
		return null;
	}

	/**
	 * Look at the editor's children to see if a Domain Object is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	protected ShapeNodeEditPart assertHasDomainObject(DiagramDocumentEditor root, String objectName) {
		String found = "";
		
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof DomainObject) {
					DomainObject p = (DomainObject) obj;
					if (p.getName().equals(objectName))
						return s;
					found += p.getName() + ",";
				}
			}
		}
		// failed
		fail("assertHasDomainObject: no domain object '" + objectName + "' found. Found: " + found);
		return null;
	}
	
	/**
	 * Look at the editor's children to see if a Domain Store is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	protected ShapeNodeEditPart assertHasOperation(DiagramDocumentEditor root, String operationName) {
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
		fail("assertHasOperation: no operation '" + operationName + "' found. Found: " + found);
		return null;
	}


	/**
	 * Assert that a RunInstanceWire exists between two elements in the editor. 
	 */
	protected ConnectionNodeEditPart assertHasRunInstanceWire(DiagramDocumentEditor editor, EditPart source, EditPart target, String name) {
		String found = "";
		
		for (Object c : editor.getDiagramEditPart().getConnections()) {
			if (c instanceof ConnectionNodeEditPart) {
				ConnectionNodeEditPart connection = (ConnectionNodeEditPart) c;
				EObject element = connection.resolveSemanticElement();
				if (element instanceof RunInstanceWire) {
					RunInstanceWire w = (RunInstanceWire) element;
					if (connection.getSource().equals(source) && 
							connection.getTarget().equals(target) && w.getName().equals(name))
						return connection;	// found it
					found += ", " + w.getName();
				}
			}
		}
		
		fail("assertHasRunInstanceWire: no connection found between '" + source + "' and '" + target + "'. found: " + found);
		return null;
	}
	
}
