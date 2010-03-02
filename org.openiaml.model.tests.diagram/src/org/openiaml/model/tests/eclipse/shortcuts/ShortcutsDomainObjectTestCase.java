/**
 * 
 */
package org.openiaml.model.tests.eclipse.shortcuts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.model.ModelPackage;

/**
 * Tests domain object shortcuts.
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class ShortcutsDomainObjectTestCase extends AbstractShortcutsTestCase {

	@Override
	public String getModel() {
		return "shortcuts-domain_object.iaml";
	}
	
	protected DiagramDocumentEditor editor_page;
	protected DiagramDocumentEditor editor_store;
	protected DiagramDocumentEditor editor_object;
	
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

		// it should have a domain object
		assertEditorHasChildren(1, editor_store);
		ShapeNodeEditPart object = assertHasDomainObject(editor_store, "domain object");
		
		// open up the domain object
		editor_object = openDiagram(object);

		assertEditorDomainObject(editor_object);
		
		// it should have a domain attribute connected to an event trigger (shortcut)
		assertEditorHasChildren(2, editor_object);
		ShapeNodeEditPart attribute = assertHasDomainAttribute(editor_object, "domain attribute");
		ShapeNodeEditPart event = assertHasEventTrigger(editor_object, true, ModelPackage.eINSTANCE.getAccessible_OnAccess());
		
		// they should be connected
		assertHasRunInstanceWire(editor_object, event, attribute, "runWire");
		assertShortcut(event);
		assertNotShortcut(attribute);
		
		// open 'page' editor
		editor_page = openDiagram(page);
		
		assertEditorFrame(editor_page);
		
		// it should have a domain attribute (shortcut) connected to an event trigger
		assertEditorHasChildren(2, editor_page);
		ShapeNodeEditPart attribute2 = assertHasDomainAttribute(editor_page, "domain attribute");
		ShapeNodeEditPart event2 = assertHasEventTrigger(editor_page, false, ModelPackage.eINSTANCE.getAccessible_OnAccess());

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
	@Override
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
