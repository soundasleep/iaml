/**
 * 
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.model.ModelPackage;

/**
 * Emulate right click > initialise diagram.
 * 
 * @author jmwright
 *
 */
public class InitializeDiagramTestCase extends EclipseTestCaseHelper {

	/**
	 * Tests loading the model file with the editor.
	 * 
	 * @throws Exception
	 */
	public void testLoadModel() throws Exception {
		DiagramDocumentEditor editor = initializeAndLoad(
				"shortcuts-root.iaml",
				"src/org/openiaml/model/tests/eclipse/",
				"shortcuts-root.iaml_diagram");
		assertEditorRoot(editor);

		// there should be three children
		assertEditorHasChildren(3, editor);

		// a primitive operation, a frame, and a shortcutted EventTrigger
		assertHasPrimitiveOperation(editor, "op", false);
		ShapeNodeEditPart page = assertHasFrame(editor, "page", false);
		assertHasEventTrigger(editor, true, ModelPackage.eINSTANCE.getScope_OnInit());

		// open the domain store
		DiagramDocumentEditor editor_page = openDiagram(page);

		assertEditorFrame(editor_page);

		// close editors
		((DiagramDocumentEditor) editor_page).close(false);
		((DiagramDocumentEditor) editor).close(false);
	
	}
	
}
