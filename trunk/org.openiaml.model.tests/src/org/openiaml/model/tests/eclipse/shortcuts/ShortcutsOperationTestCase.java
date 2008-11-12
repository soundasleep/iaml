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

		// there should be four children
		assertEquals("there should be 1 children", 1, editor.getDiagramEditPart().getChildren().size());
		
		// check the contents
		ShapeNodeEditPart page = assertHasPage(editor, "container");

		// open the page
		editor_page = openDiagram(page);

		assertEquals("active editor is the visual plugin", 
				"org.openiaml.model.model.diagram.visual.part.IamlDiagramEditor", 
				editor_page.getClass().getName());

		// note that even though there is an executionedge from chained -> final,
		// it should not display
		assertEquals("there should be 2 children", 2, editor_page.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart composite = assertHasOperation(editor_page, "composite");
		ShapeNodeEditPart opFinal = assertHasOperation(editor_page, "final");
		
		// open up the operation
		editor_operation = openDiagram(composite);

		assertEquals("active editor is the operation plugin", 
				"org.openiaml.model.model.diagram.operation.part.IamlDiagramEditor", 
				editor_operation.getClass().getName());
		
		// it should have a domain attribute connected to an event trigger
		assertEquals("there should be 3 children", 3, editor_operation.getDiagramEditPart().getChildren().size());
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
