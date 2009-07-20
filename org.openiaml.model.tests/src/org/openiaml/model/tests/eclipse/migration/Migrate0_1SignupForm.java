/**
 * 
 */
package org.openiaml.model.tests.eclipse.migration;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.diagram.custom.migrate.IamlModelMigrator;
import org.openiaml.model.diagram.custom.migrate.Migrate0To1;

/**
 * Tests migrating a very old model version. 
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class Migrate0_1SignupForm extends AbstractMigrateTestCase {

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
		assertEditorHasChildren(2, editor);
		
		// check the contents
		ShapeNodeEditPart page = assertHasPage(editor, "SignupForm");
		ShapeNodeEditPart store = assertHasDomainStore(editor, "domain store");
		
		// here we could open the page/stores and see what they contain
		assertNotNull(page);
		assertNotNull(store);
		
	}
	
	/**
	 * We should be able to open the sub diagrams without problems.
	 * 
	 * @throws Exception
	 */
	public void testOpenSubdiagrams() throws Exception {
		migrateModel();

		// there should be two children
		assertEditorHasChildren(2, editor);
		
		// check the contents
		ShapeNodeEditPart page = assertHasPage(editor, "SignupForm");
		ShapeNodeEditPart store = assertHasDomainStore(editor, "domain store");
		
		// here we could open the page/stores and see what they contain
		assertNotNull(page);
		assertNotNull(store);
		
		editor_store = openDiagram(store);
		assertEditorDomainStore(editor_store);
		editor_store.close(false);
		editor_store = null;

		editor_page = openDiagram(page);
		assertEditorVisual(editor_page);
		editor_page.close(false);
		editor_page = null;
		
	}

	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	public void tearDown() throws Exception {
		if (editor_store != null)
			editor_store.close(false);
		
		if (editor_page != null)
			editor_page.close(false);
		
		super.tearDown();
	}
	
	public String getModel() {
		return "signup-form-0_1.iaml";
	}

	/**
	 * We actually expect there to be some warnings.
	 */
	@Override
	protected void assertStatusOK(IStatus status) throws Exception {
		if (status.getSeverity() == IStatus.WARNING && status instanceof MultiStatus) {
			return;
		}
		// if not a multi-warning status, continue
		super.assertStatusOK(status);
	}
	
	
	
}
