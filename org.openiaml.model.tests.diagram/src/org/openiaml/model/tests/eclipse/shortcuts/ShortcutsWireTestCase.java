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
	
	protected DiagramDocumentEditor editor_wire;
	
	public void testLoadModel() throws Exception {
		initializeModelFile();
		
		// there should be three children
		assertEditorHasChildren(4, editor);
		
		// check elements
		ShapeNodeEditPart p1 = assertHasFrame(editor, "Home", false);
		ShapeNodeEditPart p2 = assertHasFrame(editor, "Frame 2", false);
		ShapeNodeEditPart target = assertHasPrimitiveOperation(editor, "target", false);
		ShapeNodeEditPart random = assertHasPrimitiveOperation(editor, "random", true);
		
		// the sync wire 
		ConnectionNodeEditPart wire = assertHasSyncWire(editor, p1, p2, "sync");
		
		// the run wire, displayed from the shortcut
		ConnectionNodeEditPart run2 = assertHasRunInstanceWire(editor, random, target, "run");
		
		// open the wire editor
		editor_wire = openDiagram(wire);
		assertEditorWire(editor_wire);
		
		// only two children
		assertEditorHasChildren(2, editor_wire);
		
		// it should have two operations
		ShapeNodeEditPart target2 = assertHasPrimitiveOperation(editor_wire, "target", true);
		ShapeNodeEditPart random2 = assertHasPrimitiveOperation(editor_wire, "random", false);
		
		// connected by a Run wire
		ConnectionNodeEditPart run = assertHasRunInstanceWire(editor_wire, random2, target2, "run");
		
		// the same elements
		assertSameReferencedElement(target, target2);
		assertSameReferencedElement(random, random2);
		assertSameReferencedElement(run, run2);

	}
	
	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	public void tearDown() throws Exception {

		if (editor_wire != null)
			editor_wire.close(false);
		
		super.tearDown();
	}

}
