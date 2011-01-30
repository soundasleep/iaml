/**
 * 
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.openiaml.model.diagram.helpers.IAccessibleTextAwareEditPart;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.visual.Frame;

/**
 * Issue 162: Overridden elements should be displayed in bold.
 * 
 * @author jmwright
 *
 */
public class OverriddenPropertyMarker extends EclipseTestCaseHelper {
	
	private DiagramDocumentEditor initialize() throws Exception {
		// initialise and load
		DiagramDocumentEditor editor = initializeAndLoad(
				"OverriddenPropertyMarker.iaml",
				"src/org/openiaml/model/tests/eclipse/");
		
		assertEditorRoot(editor);
		
		return editor;
	}
	
	/**
	 * Test the root editor.
	 */
	public void testRoot() throws Exception {
		// initialise and load
		DiagramDocumentEditor editor = initialize();
		
		{
			ShapeNodeEditPart part = assertHasFrame(editor, "Home");
			Frame e = (Frame) part.resolveSemanticElement();
			assertFalse(e.isIsGenerated());
			assertFalse(e.isOverridden());
			
			GraphicalEditPart name = (GraphicalEditPart) part.getChildren().get(0);
			
			// check the label name
			assertEquals("Home", ((IAccessibleTextAwareEditPart) name).getLabelText());
			
			// and check that it is NOT bold
			assertIsBold(name.getFigure().getFont().getFontData(), false);
		}
		
		{
			ShapeNodeEditPart part = assertHasFrame(editor, "Overridden Frame");
			Frame e = (Frame) part.resolveSemanticElement();
			assertFalse(e.isIsGenerated());
			assertTrue(e.isOverridden());
			
			GraphicalEditPart name = (GraphicalEditPart) part.getChildren().get(0);
			
			// check the label name
			assertEquals("Overridden Frame", ((IAccessibleTextAwareEditPart) name).getLabelText());
			
			// and check that it is bold
			assertIsBold(name.getFigure().getFont().getFontData(), true);
		}
		
	}

	/**
	 * Test the frame editor.
	 */
	public void testFrame() throws Exception {
		// initialise and load
		DiagramDocumentEditor editor = initialize();
		
		ShapeNodeEditPart home = assertHasFrame(editor, "Home");
		DiagramDocumentEditor frame = openDiagram(home);
		
		assertEditorFrame(frame);
		
		{
			ShapeNodeEditPart part = assertHasOperation(frame, "Normal Operation");
			CompositeOperation e = (CompositeOperation) part.resolveSemanticElement();
			assertFalse(e.isIsGenerated());
			assertFalse(e.isOverridden());
			
			GraphicalEditPart name = (GraphicalEditPart) part.getChildren().get(0);
			
			// check the label name
			assertEquals("Normal Operation", ((IAccessibleTextAwareEditPart) name).getLabelText());
			
			// and check that it is NOT bold
			assertIsBold(name.getFigure().getFont().getFontData(), false);
		}
		
		{
			ShapeNodeEditPart part = assertHasOperation(frame, "Overridden Operation");
			CompositeOperation e = (CompositeOperation) part.resolveSemanticElement();
			assertFalse(e.isIsGenerated());
			assertTrue(e.isOverridden());
			
			GraphicalEditPart name = (GraphicalEditPart) part.getChildren().get(0);
			
			// check the label name
			assertEquals("Overridden Operation", ((IAccessibleTextAwareEditPart) name).getLabelText());
			
			// and check that it is bold
			assertIsBold(name.getFigure().getFont().getFontData(), true);
		}
		
	}
	
	/**
	 * Check every font data in the given array of FontDatas for {@link #assertIsBold(FontData, boolean)}. 
	 * 
	 * @see Font#getFontData()
	 */
	private void assertIsBold(FontData[] data, boolean isBold) {
		for (FontData d : data) {
			assertIsBold(d, isBold);
		}
	}
	
	/**
	 * Assert that the given FontData is bold - <code>isBold</code>.
	 */
	private void assertIsBold(FontData data, boolean isBold) {
		if (isBold) {
			assertFalse("FontData '" + data + "' is not bold: " + data + " (style=" + data.getStyle() + ")", (data.getStyle() & SWT.BOLD) == 0);
		} else {
			assertTrue("FontData '" + data + "' is bold: " + data + " (style=" + data.getStyle() + ")", (data.getStyle() & SWT.BOLD) == 0);
		}
	}
	
}
