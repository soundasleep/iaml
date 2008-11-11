/**
 * 
 */
package org.openiaml.model.tests.eclipse.shortcuts;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorPart;
import org.openiaml.model.model.diagram.part.IamlDiagramEditor;
import org.openiaml.model.tests.EclipseTestCaseHelper;

/**
 * Sets up and tears down shortcut test cases.
 * 
 * @author jmwright
 *
 */
public abstract class ShortcutsTestCase extends EclipseTestCaseHelper {

	public static final String ROOT = "src/org/openiaml/model/tests/eclipse/shortcuts/";
	
	public IamlDiagramEditor editor;
	
	public void setUp() throws Exception {
		// set up project
		super.setUp();
		
		// copy our local file into the project
		IFile targetModel = project.getFile(getModel());
		copyFileIntoWorkspace(ROOT + getModel(),
				targetModel);
		IFile targetDiagram = project.getFile(getDiagram());
		
		// initialise diagram
		assertFalse("the target diagram should not exist yet", targetDiagram.exists());
		initializeModelFile(targetModel, targetDiagram);
		assertTrue("the target diagram should have been created", targetDiagram.exists());

		// load up the editor
		IEditorPart ep = loadDiagramFile(targetDiagram);

		// if this is actually an ErrorEditPart, then an error has occured 
		// (but it may not be obvious in the log what it is)
		assertTrue("active editor is our plugin, but is " + ep, ep instanceof IamlDiagramEditor);
		
		// find what elements are displayed
		editor = (IamlDiagramEditor) ep;
	}
	
	/**
	 * @return getModel() + "_diagram"
	 */
	public String getDiagram() {
		return getModel() + "_diagram";
	}

	/**
	 * Get the model file that we will load in this test case.
	 * @return
	 */
	public abstract String getModel();

	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	public void tearDown() throws Exception {
		if (editor != null)
			editor.close(false);

		super.tearDown();
	}

}
