/**
 * 
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.core.resources.IFile;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.tests.EclipseTestCaseHelper;

/**
 * Test loading a diagram in Eclipse.
 * 
 * @author jmwright
 *
 */
public class LoadDiagramTestCase extends EclipseTestCaseHelper {

	public void testLoadModel() throws Exception {
		// register errors
		addLogListener();

		// copy our local file into the project
		IFile targetModel = project.getFile("generation-sync-multiple.iaml");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/generation-sync-multiple.iaml",
				targetModel);
		IFile targetDiagram = project.getFile("generation-sync-multiple.iaml_diagram");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/generation-sync-multiple.iaml_diagram",
				targetDiagram);
		
		DiagramDocumentEditor editor = (DiagramDocumentEditor) loadDiagramFile(targetDiagram);
		assertEditorRoot(editor);

		// there should be four children
		assertEditorHasChildren(4, editor);
		
		// check the contents
		ShapeNodeEditPart page1 = assertHasPage(editor, "page1");
		ShapeNodeEditPart page2 = assertHasPage(editor, "page2");
		ShapeNodeEditPart store = assertHasDomainStore(editor, "domainStore");
		ShapeNodeEditPart page4 = assertHasPage(editor, "last signup user");
		
		// stop warnings
		assertNotNull(page1);
		assertNotNull(page2);
		assertNotNull(store);
		assertNotNull(page4);

		// close editors
		((DiagramDocumentEditor) editor).close(false);

	}

	
}
