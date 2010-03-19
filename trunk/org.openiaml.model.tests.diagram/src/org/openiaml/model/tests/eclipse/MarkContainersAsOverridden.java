/**
 * 
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.visual.Frame;

/**
 * Issue 130: Automatically mark containers as overridden when deleting
 * contained generated elements.
 * 
 * @author jmwright
 *
 */
public class MarkContainersAsOverridden extends EclipseTestCaseHelper {
	
	private DiagramDocumentEditor initialize() throws Exception {
		// initialise and load
		DiagramDocumentEditor editor = initializeAndLoad(
				"MarkContainersAsOverridden.iaml",
				"src/org/openiaml/model/tests/eclipse/");
		
		assertEditorRoot(editor);
		
		return editor;
	}
	
	/**
	 * Test the initial model.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		// initialise and load
		DiagramDocumentEditor editor = initialize();
		
		// there should be 4 children
		assertEditorHasChildren(4, editor);
		
		{
			ShapeNodeEditPart part = assertHasFrame(editor, "Home");
			Frame e = (Frame) part.resolveSemanticElement();
			assertFalse(e.isIsGenerated());
			assertFalse(e.isOverridden());
		}
		
		{
			ShapeNodeEditPart part = assertHasOperation(editor, "Target operation");
			CompositeOperation e = (CompositeOperation) part.resolveSemanticElement();
			assertTrue(e.isIsGenerated());
			assertFalse(e.isOverridden());
		}
		
		{
			ShapeNodeEditPart part = assertHasOperation(editor, "containing operation");
			CompositeOperation e = (CompositeOperation) part.resolveSemanticElement();
			assertFalse(e.isIsGenerated());
			assertFalse(e.isOverridden());
		}
		
		{
			ShapeNodeEditPart part = assertHasFrame(editor, "containing frame");
			Frame e = (Frame) part.resolveSemanticElement();
			assertFalse(e.isIsGenerated());
			assertFalse(e.isOverridden());
		}
		
		// finally, save
		editor.close(false);
	}

	/**
	 * Tests that InternetApplication will be marked as overridden.
	 * 
	 * @throws Exception
	 */
	public void testMarkRoot() throws Exception {
		// initialise and load
		DiagramDocumentEditor editor = initialize();
		
		// find the operation
		ShapeNodeEditPart part = assertHasOperation(editor, "Target operation");
		CompositeOperation e = (CompositeOperation) part.resolveSemanticElement();
		assertTrue(e.isIsGenerated());
		assertFalse(e.isOverridden());
		
		// I don't know how to create a new DestroyElementRequest programatically
		
	}
	
}
