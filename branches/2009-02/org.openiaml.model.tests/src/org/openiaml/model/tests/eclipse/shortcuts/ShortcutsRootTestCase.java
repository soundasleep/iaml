/**
 * 
 */
package org.openiaml.model.tests.eclipse.shortcuts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;

/**
 * Tests root shortcuts.
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class ShortcutsRootTestCase extends AbstractShortcutsTestCase {

	public String getModel() {
		return "shortcuts-root.iaml";
	}
	
	protected DiagramDocumentEditor editor_page;
	
	public void testLoadModel() throws Exception {

		// there should be four children
		assertEquals("there should be 4 children", 4, editor.getDiagramEditPart().getChildren().size());
		
		// check the contents
		ShapeNodeEditPart page = assertHasPage(editor, "page");
		ShapeNodeEditPart op = assertHasOperation(editor, "op");
		ShapeNodeEditPart et = assertHasEventTrigger(editor, "et");
		ShapeNodeEditPart chained = assertHasEventTrigger(editor, "chained");

		// they should be connected
		assertHasRunInstanceWire(editor, et, op, "run1");
		assertHasRunInstanceWire(editor, chained, op, "run2");
		assertShortcut(chained);
		assertNotShortcut(page);
		assertNotShortcut(op);
		assertNotShortcut(et);

		// open the page
		editor_page = openDiagram(page);

		assertEditorVisual(editor_page);

		// it should have a domain object
		assertEquals("there should be 2 children", 2, editor_page.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart chained2 = assertHasEventTrigger(editor_page, "chained");
		ShapeNodeEditPart op2 = assertHasOperation(editor_page, "op");

		// they should be connected
		assertHasRunInstanceWire(editor_page, chained2, op2, "run2");
		assertShortcut(op2);
		assertNotShortcut(chained2);

		assertSameReferencedElement(chained, chained2);
		assertSameReferencedElement(op, op2);

	}
	
	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	public void tearDown() throws Exception {

		if (editor_page != null)
			editor_page.close(false);
		
		super.tearDown();
	}

}
