/**
 * 
 */
package org.openiaml.model.tests.eclipse.shortcuts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;

/**
 * Tests domain object shortcuts.
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class ShortcutsDomainObjectTestCase extends AbstractShortcutsTestCase {

	public String getModel() {
		return "shortcuts-domain_object.iaml";
	}
	
	protected DiagramDocumentEditor editor_page;
	protected DiagramDocumentEditor editor_store;
	protected DiagramDocumentEditor editor_object;
	
	public void testLoadModel() throws Exception {
		initializeModelFile();

		// there should be four children
		assertEquals("there should be 2 children", 2, editor.getDiagramEditPart().getChildren().size());
		
		// check the contents
		ShapeNodeEditPart page = assertHasPage(editor, "page");
		ShapeNodeEditPart store = assertHasDomainStore(editor, "domain store");

		// open the domain store
		editor_store = openDiagram(store);

		assertEditorDomainStore(editor_store);

		// it should have a domain object
		assertEquals("there should be 1 children", 1, editor_store.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart object = assertHasDomainObject(editor_store, "domain object");
		
		// open up the domain object
		editor_object = openDiagram(object);

		assertEditorDomainObject(editor_object);
		
		// it should have a domain attribute connected to an event trigger
		assertEquals("there should be 2 children", 2, editor_object.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart attribute = assertHasDomainAttribute(editor_object, "domain attribute");
		ShapeNodeEditPart event = assertHasEventTrigger(editor_object, "event trigger");
		
		// they should be connected
		assertHasRunInstanceWire(editor_object, event, attribute, "runWire");
		assertShortcut(event);
		assertNotShortcut(attribute);
		
		// open 'page' editor
		editor_page = openDiagram(page);
		
		assertEditorVisual(editor_page);
		
		// it should have a domain attribute connected to an event trigger
		assertEquals("there should be 2 children", 2, editor_page.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart attribute2 = assertHasDomainAttribute(editor_page, "domain attribute");
		ShapeNodeEditPart event2 = assertHasEventTrigger(editor_page, "event trigger");

		// they should be connected
		assertHasRunInstanceWire(editor_page, event2, attribute2, "runWire");
		assertNotShortcut(event2);
		assertShortcut(attribute2);

		assertSameReferencedElement(event, event2);
		assertSameReferencedElement(attribute, attribute2);

	}
	
	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	public void tearDown() throws Exception {
		if (editor_object != null)
			editor_object.close(false);
		
		if (editor_store != null)
			editor_store.close(false);
		
		if (editor_page != null)
			editor_page.close(false);
		
		super.tearDown();
	}

}
