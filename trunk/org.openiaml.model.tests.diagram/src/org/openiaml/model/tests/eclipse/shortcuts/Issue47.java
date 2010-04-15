/**
 * 
 */
package org.openiaml.model.tests.eclipse.shortcuts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.model.ModelPackage;

/**
 * Issue 47: InputTextField editor does not render shortcutted elements
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class Issue47 extends AbstractShortcutsTestCase {

	@Override
	public String getModel() {
		return "issue47.iaml";
	}
	
	protected DiagramDocumentEditor editor_page;
	protected DiagramDocumentEditor editor_text;
	
	public void testEclipseShortcuts() throws Exception {
		initializeModelFile();

		// there should be four children
		assertEditorHasChildren(1, editor);
		
		// check the contents
		ShapeNodeEditPart page = assertHasFrame(editor, "container");

		// open the page
		editor_page = openDiagram(page);
		assertEditorFrame(editor_page);
		
		// there should be two children (text fields) 
		assertEditorHasChildren(2, editor_page);
		ShapeNodeEditPart text1 = assertHasInputTextField(editor_page, "text field 1");
		assertNotShortcut(text1);
		assertNotGenerated(text1);
		ShapeNodeEditPart text2 = assertHasInputTextField(editor_page, "text field 2");
		assertNotShortcut(text2);
		assertNotGenerated(text2);
		ConnectionNodeEditPart sync = assertHasSyncWire(editor_page, text1, text2, "sync");
		assertNotGenerated(sync);

		// open the text field
		editor_text = openDiagram(text1);
		assertEditorVisual(editor_text);
		
		// should contain a field value
		ShapeNodeEditPart text1_value = assertHasFieldValue(editor_text, false);
		assertNotShortcut(text1_value);
		assertGenerated(text1_value);
		// the other fieldValue _should_ most definitely be displayed here, because
		// it's used as the parameter for an incoming operation
		ShapeNodeEditPart text2_value = assertHasFieldValue(editor_text, true);
		assertShortcut(text2_value);
		assertGenerated(text2_value);
		
		// editor should contain both "edit" and "update" operations for
		// text1 and text2
		ShapeNodeEditPart text1_edit = assertHasEventTrigger(editor_text, false, ModelPackage.eINSTANCE.getChangeable_OnChange() );
		ShapeNodeEditPart text2_edit = assertHasEventTrigger(editor_text, true,  ModelPackage.eINSTANCE.getChangeable_OnChange());
		assertNotSame(text1_edit, text2_edit);
		assertGenerated(text1_edit);
		assertGenerated(text2_edit);
		
		ShapeNodeEditPart text1_update = assertHasOperation(editor_text, "update", false);
		ShapeNodeEditPart text2_update = assertHasOperation(editor_text, "update", true);
		assertNotSame(text1_update, text2_update);
		assertGenerated(text1_update);
		assertGenerated(text2_update);
		
		// these should be connected
		ConnectionNodeEditPart text1_run = assertHasRunAction(editor_text, text1_edit, text2_update, "run");
		assertGenerated(text1_run);
		ConnectionNodeEditPart text1_param = assertHasParameterEdge(editor_text, text1_value, text1_run);
		assertGenerated(text1_param);
		
		ConnectionNodeEditPart text2_run = assertHasRunAction(editor_text, text2_edit, text1_update, "run");
		assertGenerated(text2_run);
		ConnectionNodeEditPart text2_param = assertHasParameterEdge(editor_text, text2_value, text2_run);
		assertGenerated(text2_param);

	}

	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	@Override
	public void tearDown() throws Exception {

		if (editor_text != null)
			editor_text.close(false);
		
		if (editor_page != null)
			editor_page.close(false);
		
		super.tearDown();
	}

}
