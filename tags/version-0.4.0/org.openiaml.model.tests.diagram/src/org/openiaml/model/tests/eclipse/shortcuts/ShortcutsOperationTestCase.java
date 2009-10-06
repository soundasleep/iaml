/**
 * 
 */
package org.openiaml.model.tests.eclipse.shortcuts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;

/**
 * Tests operation shortcuts.
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class ShortcutsOperationTestCase extends AbstractShortcutsTestCase {

	public String getModel() {
		return "shortcuts-operation.iaml";
	}
	
	protected DiagramDocumentEditor editor_page;
	protected DiagramDocumentEditor editor_operation;
	
	public void testLoadModel() throws Exception {
		initializeModelFile();

		// there should be four children
		assertEditorHasChildren(1, editor);
		
		// check the contents
		ShapeNodeEditPart page = assertHasPage(editor, "container");

		// open the page
		editor_page = openDiagram(page);

		assertEditorVisual(editor_page);

		// note that even though there is an executionedge from chained -> final,
		// it should not display
		assertEditorHasChildren(2, editor_page);
		ShapeNodeEditPart composite = assertHasOperation(editor_page, "composite");
		ShapeNodeEditPart opFinal = assertHasOperation(editor_page, "final");
		
		// open up the operation
		editor_operation = openDiagram(composite);

		assertEditorOperation(editor_operation);
		
		// it should have a domain attribute connected to an event trigger
		assertEditorHasChildren(3, editor_operation);
		ShapeNodeEditPart startNode = assertHasStartNode(editor_operation);
		ShapeNodeEditPart chained = assertHasOperation(editor_operation, "chained");
		ShapeNodeEditPart opFinal2 = assertHasOperation(editor_operation, "final");
		
		// they should be connected
		assertHasExecutionEdge(editor_operation, startNode, chained);
		assertHasExecutionEdge(editor_operation, chained, opFinal2);
		assertNotShortcut(startNode);
		assertNotShortcut(chained);
		assertShortcut(opFinal2);

		// the final operations are the same
		assertSameReferencedElement(opFinal, opFinal2);

	}
	
	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	public void tearDown() throws Exception {
		
		if (editor_operation != null)
			editor_operation.close(false);
		
		if (editor_page != null)
			editor_page.close(false);
		
		super.tearDown();
	}

}
