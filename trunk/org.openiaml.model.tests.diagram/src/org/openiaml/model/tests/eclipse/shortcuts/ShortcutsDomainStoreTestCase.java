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

	@Override
	public String getModel() {
		return "shortcuts-domain_store.iaml";
	}
	
	protected DiagramDocumentEditor editor_page;
	protected DiagramDocumentEditor editor_store;
	
	public void testLoadModel() throws Exception {
		initializeModelFile();

		// there should be two children
		assertEditorHasChildren(2, editor);
		
		// check the contents
		ShapeNodeEditPart page = assertHasFrame(editor, "page");
		ShapeNodeEditPart store = assertHasDomainStore(editor, "domain store");

		// open the domain store
		editor_store = openDiagram(store);

		assertEditorDomainStore(editor_store);
		
		// it should have a domain attribute connected to an event trigger
		assertEditorHasChildren(3, editor_store);
		ShapeNodeEditPart child = assertHasDomainObject(editor_store, "child");
		ShapeNodeEditPart event1 = assertHasEventTrigger(editor_store, true, "onAccess");
		ShapeNodeEditPart event2 = assertHasEventTrigger(editor_store, "et2", false);
		
		// they should be connected
		assertHasSyncWire(editor_store, event1, child, "syncWire");
		assertNotShortcut(child);
		assertShortcut(event1);
		assertNotShortcut(event2);
		
		editor_store.close(false);
		editor_store = null;
		
		// open 'page' editor
		editor_page = openDiagram(page);
		
		assertEditorFrame(editor_page);
		
		// it should have a domain attribute connected to an event trigger
		assertEditorHasChildren(2, editor_page);
		ShapeNodeEditPart et1 = assertHasEventTrigger(editor_page, false, "onAccess");
		ShapeNodeEditPart obj = assertHasDomainObject(editor_page, "child");

		// they should be connected
		assertHasSyncWire(editor_page, et1, obj, "syncWire");
		assertNotShortcut(et1);
		assertShortcut(obj);
		
		// they should be the same element
		assertSameReferencedElement(et1, event1);
		assertSameReferencedElement(child, obj);
		
		editor_page.close(false);
		editor_page = null;
		
	}
	
	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	@Override
	public void tearDown() throws Exception {
		if (editor_store != null)
			editor_store.close(false);
		
		if (editor_page != null)
			editor_page.close(false);
		
		super.tearDown();
	}

}
