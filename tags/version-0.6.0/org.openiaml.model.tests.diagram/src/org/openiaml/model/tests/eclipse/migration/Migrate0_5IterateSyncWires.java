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
import org.openiaml.model.migrate.Migrate1To2;
import org.openiaml.model.migrate.Migrate2To4;
import org.openiaml.model.migrate.Migrate4To5;
import org.openiaml.model.migrate.Migrate5To6;

/**
 * Tests migrating a very old model version, from 0.5 to 0.6.
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class Migrate0_5IterateSyncWires extends AbstractMigrateTestCaseWithWarnings {

	protected DiagramDocumentEditor editor_page = null;
	protected DiagramDocumentEditor editor_operation = null;
	
	/**
	 * Test to see which migrators were actually used.
	 * 
	 * @throws Exception
	 */
	public void testMigratorsUsed() throws Exception {
		List<IamlModelMigrator> used = migrateModelOnly();
		assertClassNotIn(Migrate0To1.class, used);
		assertClassNotIn(Migrate1To2.class, used);
		assertClassNotIn(Migrate2To4.class, used);
		assertClassNotIn(Migrate4To5.class, used);
		assertClassIn(Migrate5To6.class, used);
	}
	
	public void testLoadModel() throws Exception {
		migrateModel();
		
		ShapeNodeEditPart page = assertHasFrame(editor, "Home");
		assertNotNull(page);
		
		assertEditorHasChildren(1, editor);
		
		// open page
		editor_page = openDiagram(page);
		try {
			assertEditorFrame(editor_page);
			
			// lots of buttons
			{
				ShapeNodeEditPart button = assertHasButton(editor_page, "reset");
				assertNotGenerated(button);
			}

			{
				ShapeNodeEditPart button = assertHasButton(editor_page, "next");
				assertNotGenerated(button);
			}

			{
				ShapeNodeEditPart button = assertHasButton(editor_page, "possibly next");
				assertNotGenerated(button);
			}

			{
				ShapeNodeEditPart button = assertHasButton(editor_page, "previous");
				assertNotGenerated(button);
			}

			{
				ShapeNodeEditPart button = assertHasButton(editor_page, "possibly previous");
				assertNotGenerated(button);
			}

		} finally {
			editor_page.close(false);
		}
	}

	
	@Override
	public String getModel() {
		return "IteratedSyncWires-0_5.iaml";
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.eclipse.migration.AbstractMigrateTestCaseWithWarnings#getExpectedWarningsFile()
	 */
	@Override
	public File getExpectedWarningsFile() {
		return new File("src/org/openiaml/model/tests/eclipse/migration/Migrate0_5Warnings.txt");
	}
	
}
