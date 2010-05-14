/**
 *
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.core.resources.IFile;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorPart;
import org.openiaml.model.diagram.part.IamlDiagramEditor;

/**
 * Tests opening a sub-diagram of a model diagram in Eclipse.
 *
 * @author jmwright
 *
 */
public class OpenSubDiagramTestCase extends EclipseTestCaseHelper {

	public void testLoadModel() throws Exception {
		// register errors
		addLogListener();

		// copy our local file into the project
		IFile targetModel = getProject().getFile("generation-sync-multiple.iaml");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/generation-sync-multiple.iaml",
				targetModel);
		IFile targetDiagram = getProject().getFile("generation-sync-multiple.iaml_diagram");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/generation-sync-multiple.iaml_diagram",
				targetDiagram);

		// load up the editor
		IEditorPart ep = loadDiagramFile(targetDiagram);

		// if this is actually an ErrorEditPart, then an error has occured
		// (but it may not be obvious in the log what it is)
		assertTrue("active editor is our plugin, but is " + ep, ep instanceof IamlDiagramEditor);

		// find what elements are displayed
		IamlDiagramEditor editor = (IamlDiagramEditor) ep;

		// there should be four children
		assertEditorHasChildren(4, editor);

		// check the contents
		ShapeNodeEditPart page1 = assertHasFrame(editor, "page1");
		ShapeNodeEditPart page2 = assertHasFrame(editor, "page2");
		ShapeNodeEditPart store = assertHasDomainSchema(editor, "domainStore");
		ShapeNodeEditPart page4 = assertHasFrame(editor, "last signup user");

		// stop warnings
		assertNotNull(page1);
		assertNotNull(page2);
		assertNotNull(store);
		assertNotNull(page4);

		// open the domain store
		IEditorPart ep2 = openDiagram(store);

		// if this is actually an ErrorEditPart, then an error has occured
		// (but it may not be obvious in the log what it is)
		assertEditorDomainSchema(ep2);

		// close editors
		((DiagramDocumentEditor) ep2).close(false);
		((DiagramDocumentEditor) editor).close(false);

	}


}
