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
 * Tests opening a sub-diagram of a model diagram in Eclipse.
 * 
 * @author jmwright
 *
 */
public class OpenSubDiagramTestCase extends EclipseTestCaseHelper {

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
		ShapeNodeEditPart page1 = assertHasPage(editor, "page1");
		ShapeNodeEditPart page2 = assertHasPage(editor, "page2");
		ShapeNodeEditPart store = assertHasDomainStore(editor, "domainStore");
		ShapeNodeEditPart page4 = assertHasPage(editor, "last signup user");

		// open the domain store
		IEditorPart ep2 = openDiagram(store);

		// if this is actually an ErrorEditPart, then an error has occured 
		// (but it may not be obvious in the log what it is)
		assertEquals("active editor is the domain store plugin", 
				"org.openiaml.model.model.diagram.domainstore.part.IamlDiagramEditor", 
				ep2.getClass().getName());

		// close editors
		((DiagramDocumentEditor) ep2).close(false);
		((DiagramDocumentEditor) editor).close(false);
		
	}

	
}
