/**
 * 
 */
package org.openiaml.model.tests.eclipse.migration.model0_3;

import java.io.File;
import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.migrate.IamlModelMigrator;
import org.openiaml.model.migrate.Migrate0To1;
import org.openiaml.model.migrate.Migrate1To2;
import org.openiaml.model.migrate.Migrate2To4;
import org.openiaml.model.migrate.Migrate4To5;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.tests.eclipse.migration.AbstractMigrateTestCaseWithWarnings;

/**
 * Tests migrating a 0.2 model to 0.3: domain objects
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class MigrateDomainObjects extends AbstractMigrateTestCaseWithWarnings {

	protected DiagramDocumentEditor editor_page = null;

	@Override
	public String getModel() {
		return "FileDomainObjects.iaml";
	}
	
	/**
	 * TODO put this into an abstract method to generate 
	 * migrated names by default
	 */
	@Override
	public String getModelMigrated() {
		return "FileDomainObjects-migrated.iaml";
	}

	/**
	 * We override this because we are in a sub-folder of the
	 * main migrate tests.
	 */
	@Override
	public String getSourceModel() {
		return "model0_3/" + getModel();
	}
	
	/*
	 * We don't expect there to be any warnings, so we
	 * don't override {@link #assertStatusOK(IStatus)}.
	 */
	
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

	/**
	 * The model is migrated, initialised and loaded in 
	 * {@link #migrateModel()}.
	 * 
	 * Test the root diagram for its contents.
	 * 
	 * @throws Exception
	 */
	public void testRoot() throws Exception {
		migrateModel();
		
		// there should be five children (a page and a session)
		assertEditorHasChildren(2, editor);
		
		// check the contents
		assertHasFrame(editor, "a page");
		assertHasSession(editor, "session");
	}

	/**
	 * Test the page sub-editor.
	 * 
	 * @throws Exception
	 */
	public void testPage() throws Exception {
		migrateModel();
		
		ShapeNodeEditPart part = assertHasFrame(editor, "a page");
		assertNotNull(part);
		
		Frame page = (Frame) part.resolveSemanticElement();
		assertEquals(page.getName(), "a page");
		
		// open diagram
		DiagramDocumentEditor sub = openDiagram(part);
		assertEditorFrame(sub);
		
		// check contents
		assertEditorHasChildren(2, sub);
		{
			ShapeNodeEditPart p = assertHasDomainAttribute(sub, "da");
			assertNotNull(p);
			
			DomainAttribute obj = (DomainAttribute) p.resolveSemanticElement();
			assertEquals(obj.getName(), "da");
		}
		{
			ShapeNodeEditPart p = assertHasDomainAttribute(sub, "fa");
			assertNotNull(p);
			
			DomainAttribute obj = (DomainAttribute) p.resolveSemanticElement();
			assertEquals(obj.getName(), "fa");
		}
		
		sub.close(false);
	}
	
	@Override
	public File getExpectedWarningsFile() {
		return new File("src/org/openiaml/model/tests/eclipse/migration/model0_3/Migrate0_3Warnings.txt");
	}
	
}
