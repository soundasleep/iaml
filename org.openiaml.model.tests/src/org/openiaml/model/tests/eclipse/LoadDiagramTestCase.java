/**
 * 
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.core.resources.IFile;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorPart;
import org.openiaml.model.model.diagram.part.IamlDiagramEditor;
import org.openiaml.model.tests.EclipseTestCaseHelper;

/**
 * Test loading a diagram in Eclipse.
 * 
 * @author jmwright
 *
 */
public class LoadDiagramTestCase extends EclipseTestCaseHelper {

	public void testLoadModel() throws Exception {
		// copy our local file into the project
		IFile targetModel = project.getFile("generation-sync-multiple.iaml");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/generation-sync-multiple.iaml",
				targetModel);
		IFile targetDiagram = project.getFile("generation-sync-multiple.iaml_diagram");
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
		assertEquals("there should only be 4 children", 4, editor.getDiagramEditPart().getChildren().size());
		
		// check the contents
		ShapeNodeEditPart page1 = assertHasRootPage(editor, "page1");
		ShapeNodeEditPart page2 = assertHasRootPage(editor, "page2");
		ShapeNodeEditPart store = assertHasRootDomainStore(editor, "domainStore");
		ShapeNodeEditPart page4 = assertHasRootPage(editor, "last signup user");

		// close editors
		((DiagramDocumentEditor) editor).close(false);

	}

	
}
