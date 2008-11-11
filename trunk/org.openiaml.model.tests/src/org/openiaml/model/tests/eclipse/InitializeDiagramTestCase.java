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
		IFile targetModel = project.getFile("shortcuts-root.iaml");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/shortcuts-root.iaml",
				targetModel);
		
		IFile targetDiagram = project.getFile("shortcuts-root.iaml_diagram");
		assertFalse("the target diagram should not exist yet", targetDiagram.exists());

		// initialise the model
		initializeModelFile(targetModel, targetDiagram);
		
		assertTrue("the target diagram should have been created", targetDiagram.exists());
		
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
		ShapeNodeEditPart page = assertHasRootPage(editor, "page");
		assertHasRootEventTrigger(editor, "et");
		assertHasRootOperation(editor, "op");

		// open the domain store
		IEditorPart ep2 = openDiagram(page);

		// if this is actually an ErrorEditPart, then an error has occured 
		// (but it may not be obvious in the log what it is)
		assertEquals("active editor is the visual plugin", 
				"org.openiaml.model.model.diagram.visual.part.IamlDiagramEditor", 
				ep2.getClass().getName());

		// close editors
		((DiagramDocumentEditor) ep2).close(false);
		((DiagramDocumentEditor) editor).close(false);
	
	}
	
}
