/**
 * 
 */
package org.openiaml.model.tests.eclipse.migration;

import java.io.File;
import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.migrate.IamlModelMigrator;
import org.openiaml.model.migrate.Migrate0To1;
import org.openiaml.model.migrate.Migrate2To4;
import org.openiaml.model.migrate.Migrate4To5;

/**
 * Tests migrating a very old model version, from 0.0 to 0.1.
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class Migrate0_1SignupForm extends AbstractMigrateTestCaseWithWarnings {

	protected DiagramDocumentEditor editor_page = null;
	protected DiagramDocumentEditor editor_store = null;
	
	/**
	 * Test to see which migrators were actually used.
	 * 
	 * @throws Exception
	 */
	public void testMigratorsUsed() throws Exception {
		List<IamlModelMigrator> used = migrateModelOnly();
		assertClassIn(Migrate0To1.class, used);
		assertClassIn(Migrate2To4.class, used);
		assertClassIn(Migrate4To5.class, used);
	}
	
	/**
	 * The model is migrated, initialised and loaded in 
	 * {@link #setUp()}.
	 * 
	 * @throws Exception
	 */
	public void testLoadModel() throws Exception {
		migrateModel();
		
		// there should be two children
		assertEditorHasChildren(1, editor);
		
		// check the contents
		ShapeNodeEditPart page = assertHasFrame(editor, "SignupForm");
		
		// here we could open the page/stores and see what they contain
		assertNotNull(page);
		
	}
	
	/**
	 * We should be able to open the sub diagrams without problems.
	 * 
	 * @throws Exception
	 */
	public void testOpenSubdiagrams() throws Exception {
		migrateModel();

		// there should be two children
		assertEditorHasChildren(1, editor);
		
		// check the contents
		ShapeNodeEditPart page = assertHasFrame(editor, "SignupForm");
		
		// here we could open the page/stores and see what they contain
		assertNotNull(page);

		editor_page = openDiagram(page);
		assertEditorFrame(editor_page);
		editor_page.close(false);
		editor_page = null;
		
	}

	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	@Override
	public void tearDown() throws Exception {
		if (editor_store != null)
			editor_store.close(false);
		
		if (editor_page != null)
			editor_page.close(false);
		
		super.tearDown();
	}
	
	@Override
	public String getModel() {
		return "signup-form-0_1.iaml";
	}

	@Override
	public File getExpectedWarningsFile() {
		return new File("src/org/openiaml/model/tests/eclipse/migration/Migrate0_1Warnings.txt");
	}
	
}
