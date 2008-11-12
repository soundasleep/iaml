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
public class ShortcutsDomainStoreTestCase extends AbstractShortcutsTestCase {

	public String getModel() {
		return "shortcuts-domain_store.iaml";
	}
	
	protected DiagramDocumentEditor editor_page;
	protected DiagramDocumentEditor editor_store;
	
	public void testLoadModel() throws Exception {

		// there should be two children
		assertEquals("there should be 2 children", 2, editor.getDiagramEditPart().getChildren().size());
		
		// check the contents
		ShapeNodeEditPart page = assertHasPage(editor, "page");
		ShapeNodeEditPart store = assertHasDomainStore(editor, "domain store");

		// open the domain store
		editor_store = openDiagram(store);

		assertEditorDomainStore(editor_store);
		
		// it should have a domain attribute connected to an event trigger
		assertEquals("there should be 3 children", 3, editor_store.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart child = assertHasDomainObject(editor_store, "child");
		ShapeNodeEditPart event1 = assertHasEventTrigger(editor_store, "et1");
		ShapeNodeEditPart event2 = assertHasEventTrigger(editor_store, "et2");
		
		// they should be connected
		assertHasSyncWire(editor_store, event1, child, "syncWire");
		assertNotShortcut(child);
		assertShortcut(event1);
		assertNotShortcut(event2);
		
		// open 'page' editor
		editor_page = openDiagram(page);
		
		assertEditorVisual(editor_page);
		
		// it should have a domain attribute connected to an event trigger
		assertEquals("there should be 2 children", 2, editor_page.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart et1 = assertHasEventTrigger(editor_page, "et1");
		ShapeNodeEditPart obj = assertHasDomainObject(editor_page, "child");

		// they should be connected
		assertHasSyncWire(editor_page, et1, obj, "syncWire");
		assertNotShortcut(et1);
		assertShortcut(obj);
		
		// they should be the same element
		assertSameReferencedElement(et1, event1);
		assertSameReferencedElement(child, obj);
		
	}
	
	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	public void tearDown() throws Exception {
		if (editor_store != null)
			editor_store.close(false);
		
		if (editor_page != null)
			editor_page.close(false);
		
		super.tearDown();
	}

}
