/**
 * 
 */
package org.openiaml.model.tests.eclipse.migration;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorPart;
import org.openiaml.model.diagram.custom.actions.MigrateModelAction;
import org.openiaml.model.model.diagram.part.IamlDiagramEditor;
import org.openiaml.model.tests.EclipseTestCaseHelper;

/**
 * Testing the migration functionality.
 * 
 * It tests the migration, loading the model into EMF, initialising the
 * GMF diagram, and then investigating the rendered diagram to make sure
 * the elements are all there.
 * 
 * TODO in the future we could add migration tests to ensure that
 * openable elements can still be opened, etc.
 * 
 * Based on ShortcutsTestCase.
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public abstract class AbstractMigrateTestCase extends EclipseTestCaseHelper {

	public static final String ROOT = "src/org/openiaml/model/tests/eclipse/migration/";
	
	public IamlDiagramEditor editor;
	
	public void setUp() throws Exception {
		// set up project
		super.setUp();
		
		// copy our local file into the project
		IFile sourceModel = project.getFile(getModel());
		copyFileIntoWorkspace(ROOT + getModel(),
				sourceModel);
		IFile targetModel = project.getFile(getModelMigrated());
		IFile targetDiagram = project.getFile(getDiagram());
		
		// migrate the model
		assertFalse("the target model should not exist yet", targetModel.exists());
		MigrateModelAction a = new MigrateModelAction();
		a.migrateModel(sourceModel, targetModel, monitor);
		assertTrue("the target diagram should have been created", targetModel.exists());

		// initialise the diagram
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
		return getModelMigrated() + "_diagram";
	}

	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	public void tearDown() throws Exception {
		if (editor != null)
			editor.close(false);

		super.tearDown();
	}
	
	/**
	 * What model do we want to migrate?
	 * @return
	 */
	public abstract String getModel();
	
	/**
	 * What file do we want to migrate the model to?
	 * @return
	 */
	public abstract String getModelMigrated();

}
