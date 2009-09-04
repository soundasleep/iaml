/**
 * 
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.diagram.helpers.IAccessibleTextAwareEditPart;
import org.openiaml.model.tests.eclipse.actions.AbstractActionTestCase;

/**
 * Issue 92: Generated elements should have a derived '/' marker
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class DerivedPropertyMarker extends AbstractActionTestCase {

	@Override
	public String getRoot() {
		return "src/org/openiaml/model/tests/eclipse/";
	}

	public String getModel() {
		return "DerivedPropertyMarker.iaml";
	}
	
	protected DiagramDocumentEditor editor_page;
	protected DiagramDocumentEditor editor_operation;
	
	/**
	 * Test that derived properties are rendered.
	 * 
	 * @throws Exception
	 */
	public void testDerivedProperties() throws Exception {
		initializeModelFile();

		ShapeNodeEditPart page1 = assertHasPage(editor, "Home");
		ShapeNodeEditPart page2 = assertHasPage(editor, "generated page");
		
		assertNotHasDerivedMarker(page1);
		assertHasDerivedMarker(page2);
		
		// open page
		editor_page = openDiagram(page1);
		assertEditorVisual(editor_page);
		
		ShapeNodeEditPart op1 = assertHasOperation(editor_page, "non-generated");
		ShapeNodeEditPart op2 = assertHasOperation(editor_page, "generated");
		
		assertNotHasDerivedMarker(op1);
		assertHasDerivedMarker(op2);
		
		// open operation
		editor_operation = openDiagram(op2);
		assertEditorOperation(editor_operation);
		
		ShapeNodeEditPart dop1 = assertHasOperation(editor_operation, "non-generated");
		ShapeNodeEditPart dop2 = assertHasOperation(editor_operation, "generated");
		
		assertNotHasDerivedMarker(dop1);
		assertHasDerivedMarker(dop2);

	}

	
	/**
	 * Does the given string have a derived marker?
	 * 
	 * @param s
	 * @return
	 */
	protected boolean hasDerivedMarker(String s) {
		return s.startsWith("/");
	}
	
	/**
	 * The given edit part should have a "derived" marker.
	 * 
	 * @param p
	 */
	protected void assertHasDerivedMarker(ShapeNodeEditPart p) {
		assertHasDerivedMarker(p, true);
	}
	
	/**
	 * The given edit part should not have a "derived" marker.
	 * 
	 * @param p
	 */
	protected void assertNotHasDerivedMarker(ShapeNodeEditPart p) {
		assertHasDerivedMarker(p, false);
	}
	
	protected void assertHasDerivedMarker(ShapeNodeEditPart p, boolean check) {
		boolean found = false;
		for (Object o : p.getChildren()) {
			if (o instanceof IAccessibleTextAwareEditPart) {
				IAccessibleTextAwareEditPart text = (IAccessibleTextAwareEditPart) o;
				
				boolean c = hasDerivedMarker(text.getLabelText());
				if (check) {
					assertTrue("The label '" + text + " (" + text.getLabelText() + ") should be derived", c);
				} else {
					assertFalse("The label '" + text + " (" + text.getLabelText() + ") should not be derived", c);
				}
				found = true;
			}
		}
		assertTrue("No IAccessibleTextAwareEditPart was ever found on " + p, found);
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
