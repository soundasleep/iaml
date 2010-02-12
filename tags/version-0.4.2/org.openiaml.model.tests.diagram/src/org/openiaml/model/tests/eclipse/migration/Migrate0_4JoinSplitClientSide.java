/**
 * 
 */
package org.openiaml.model.tests.eclipse.migration;

import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.migrate.IamlModelMigrator;
import org.openiaml.model.migrate.Migrate0To1;
import org.openiaml.model.migrate.Migrate1To2;
import org.openiaml.model.migrate.Migrate2To4;
import org.openiaml.model.migrate.Migrate4To5;

/**
 * Tests migrating a very old model version, from 0.3 to 0.4.
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class Migrate0_4JoinSplitClientSide extends AbstractMigrateTestCase {

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
		assertClassIn(Migrate2To4.class, used);
		assertClassIn(Migrate4To5.class, used);
	}
	
	public void testLoadModel() throws Exception {
		migrateModel();
		
		ShapeNodeEditPart page = assertHasFrame(editor, "Home");
		assertNotNull(page);
		
		// open page
		editor_page = openDiagram(page);
		assertEditorFrame(editor_page);
		
		ShapeNodeEditPart operation = assertHasOperation(editor_page, "target operation");
		assertNotNull(operation);

		// open operation
		editor_operation = openDiagram(operation);
		assertEditorOperation(editor_operation);
	
		assertHasPrimitiveOperation(editor_operation, "setPropertyToValue", false, false);
	}

	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	public void tearDown() throws Exception {
		if (editor_page != null)
			editor_page.close(false);
		if (editor_operation != null)
			editor_operation.close(false);
		
		super.tearDown();
	}
	
	public String getModel() {
		return "JoinSplitClientSide-0_4.iaml";
	}
	
	/*
	 * We don't expect there to be any warnings, so we
	 * don't override {@link #assertStatusOK(IStatus)}.
	 */
	
}
