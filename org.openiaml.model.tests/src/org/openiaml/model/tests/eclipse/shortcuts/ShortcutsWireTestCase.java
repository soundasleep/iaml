/**
 * 
 */
package org.openiaml.model.tests.eclipse.shortcuts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;

/**
 * Tests wire shortcuts.
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class ShortcutsWireTestCase extends AbstractShortcutsTestCase {

	public String getModel() {
		return "shortcuts-wire.iaml";
	}
	
	protected DiagramDocumentEditor editor_page;
	protected DiagramDocumentEditor editor_wire;
	
	public void testLoadModel() throws Exception {

		// there should be four children
		assertEquals("there should be 1 children", 1, editor.getDiagramEditPart().getChildren().size());
		
		// check the contents
		ShapeNodeEditPart page = assertHasPage(editor, "container");

		// open the domain store
		editor_page = openDiagram(page);

		assertEquals("active editor is the visual plugin", 
				"org.openiaml.model.model.diagram.visual.part.IamlDiagramEditor", 
				editor_page.getClass().getName());

		// there are lots of children because the event triggers here
		// also reference the operations inside the wire
		assertEquals("there should be 5 children", 5, editor_page.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart co = assertHasOperation(editor_page, "co");
		ShapeNodeEditPart et = assertHasEventTrigger(editor_page, "et");
		ShapeNodeEditPart etnew = assertHasEventTrigger(editor_page, "et new");
		ShapeNodeEditPart op1 = assertHasOperation(editor_page, "op1");
		ShapeNodeEditPart op2 = assertHasOperation(editor_page, "op2");
		
		// they should be connected
		ConnectionNodeEditPart wire = assertHasRunInstanceWire(editor_page, et, co, "wire");
		assertHasRunInstanceWire(editor_page, etnew, op2, "shortcut1");
		assertHasRunInstanceWire(editor_page, et, op1, "shortcut2");
		assertNotShortcut(co);
		assertNotShortcut(et);
		assertNotShortcut(etnew);
		assertShortcut(op1);
		assertShortcut(op2);
		
		// open up wire
		editor_wire = openDiagram(wire);
		
		assertEquals("active editor is the wire plugin", 
				"org.openiaml.model.model.diagram.wire.part.IamlDiagramEditor", 
				editor_wire.getClass().getName());
		
		// it should have two operations and two event triggers
		assertEquals("there should be 4 children", 4, editor_wire.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart et2 = assertHasEventTrigger(editor_wire, "et");
		ShapeNodeEditPart etnew2 = assertHasEventTrigger(editor_wire, "et new");
		ShapeNodeEditPart op12 = assertHasOperation(editor_wire, "op1");
		ShapeNodeEditPart op22 = assertHasOperation(editor_wire, "op2");

		// they should be connected
		assertHasRunInstanceWire(editor_wire, etnew2, op22, "shortcut1");
		assertHasRunInstanceWire(editor_wire, et2, op12, "shortcut2");
		assertShortcut(et2);
		assertShortcut(etnew2);
		assertNotShortcut(op12);
		assertNotShortcut(op22);
		
		assertSameReferencedElement(op1, op12);
		assertSameReferencedElement(op2, op22);
		assertSameReferencedElement(et, et2);
		assertSameReferencedElement(etnew, etnew2);

	}
	
	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	public void tearDown() throws Exception {

		if (editor_wire != null)
			editor_wire.close(false);
		
		if (editor_page != null)
			editor_page.close(false);
		
		super.tearDown();
	}

}
