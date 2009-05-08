/**
 * 
 */
package org.openiaml.model.tests.eclipse.migration.model0_3;

import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.diagram.custom.migrate.IamlModelMigrator;
import org.openiaml.model.diagram.custom.migrate.Migrate0To1;
import org.openiaml.model.diagram.custom.migrate.Migrate1To2;
import org.openiaml.model.diagram.custom.migrate.Migrate2To3;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.domain.DomainStoreTypes;
import org.openiaml.model.tests.eclipse.migration.AbstractMigrateTestCase;

/**
 * Tests migrating a 0.2 model to 0.3: domain objects
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class MigrateDomainObjects extends AbstractMigrateTestCase {

	protected DiagramDocumentEditor editor_page = null;
	
	/**
	 * Test to see which migrators were actually used.
	 * 
	 * @throws Exception
	 */
	public void testMigratorsUsed() throws Exception {
		List<IamlModelMigrator> used = migrateModelOnly();
		assertClassNotIn(Migrate0To1.class, used);
		assertClassNotIn(Migrate1To2.class, used);
		assertClassIn(Migrate2To3.class, used);
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
		
		// there should be five children (five pages)
		assertChildren(5, editor);
		
		// check the contents
		assertHasDomainStore(editor, "normal domain store");
		assertHasDomainStore(editor, "file domain store");
		assertHasDomainObject(editor, "normal domain object");
		assertHasPage(editor, "a page");
		assertHasSession(editor, "session");
	}

	/**
	 * Test the domain store sub-editor.
	 * 
	 * @throws Exception
	 */
	public void testNormalDomainStore() throws Exception {
		migrateModel();
		
		ShapeNodeEditPart part = assertHasDomainStore(editor, "normal domain store");
		assertNotNull(part);
		
		DomainStore store = (DomainStore) part.resolveSemanticElement();
		assertEquals(store.getName(), "normal domain store");
		assertEquals(store.getType(), DomainStoreTypes.RELATIONAL_DB);
		
		// open diagram
		DiagramDocumentEditor sub = openDiagram(part);
		assertEditorDomainStore(sub);
		
		// check contents
		assertChildren(2, sub);
		{
			ShapeNodeEditPart p = assertHasDomainObject(sub, "normal domain object");
			assertNotNull(p);
			
			DomainObject obj = (DomainObject) p.resolveSemanticElement();
			assertEquals(obj.getName(), "normal domain object");
		}
		{
			ShapeNodeEditPart p = assertHasDomainAttribute(sub, "normal domain attribute");
			assertNotNull(p);
			
			DomainAttribute obj = (DomainAttribute) p.resolveSemanticElement();
			assertEquals(obj.getName(), "normal domain attribute");
		}
		
		sub.close(false);
	}
	
	public String getModel() {
		return "FileDomainObjects.iaml";
	}
	
	/**
	 * TODO put this into an abstract method to generate 
	 * migrated names by default
	 */
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
	
}
