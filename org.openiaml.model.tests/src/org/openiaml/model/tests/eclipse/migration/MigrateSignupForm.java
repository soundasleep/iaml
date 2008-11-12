/**
 * 
 */
package org.openiaml.model.tests.eclipse.migration;

import org.eclipse.core.resources.IFile;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorPart;
import org.openiaml.model.diagram.custom.actions.MigrateModelAction;
import org.openiaml.model.model.diagram.part.IamlDiagramEditor;
import org.openiaml.model.tests.EclipseTestCaseHelper;

/**
 * Tests migrating a very old model version. 
 * It tests the migration, loading the model into EMF, initialising the
 * GMF diagram, and then investigating the rendered diagram to make sure
 * the elements are all there.
 * 
 * TODO in the future we could add migration tests to ensure that
 * openable elements can still be opened, etc.
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class MigrateSignupForm extends EclipseTestCaseHelper {

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
	
	public String getModel() {
		return "signup_form.iaml";
	}
	
	public String getModelMigrated() {
		return "signup_form2.iaml";
	}
	
	public void testLoadModel() throws Exception {

		// there should be four children
		assertEquals("there should be 2 children", 2, editor.getDiagramEditPart().getChildren().size());
		
		// check the contents
		ShapeNodeEditPart page = assertHasPage(editor, "SignupForm");
		ShapeNodeEditPart store = assertHasDomainStore(editor, "domain store");

		// here we could open the page/stores and see what they contain
		assertNotNull(page);
		assertNotNull(store);
		
	}

}
