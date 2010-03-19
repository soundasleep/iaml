/**
 * 
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.core.resources.IFile;
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
		// copy our local file into the project
		IFile targetModel = getProject().getFile("shortcuts-root.iaml");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/shortcuts-root.iaml",
				targetModel);
		
		IFile targetDiagram = getProject().getFile("shortcuts-root.iaml_diagram");
		assertFalse("the target diagram should not exist yet", targetDiagram.exists());

		// initialise the model
		initializeModelFile(targetModel, targetDiagram);
		
		assertTrue("the target diagram should have been created", targetDiagram.exists());
		
		// load up the editor
		DiagramDocumentEditor editor = (DiagramDocumentEditor) loadDiagramFile(targetDiagram);
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
