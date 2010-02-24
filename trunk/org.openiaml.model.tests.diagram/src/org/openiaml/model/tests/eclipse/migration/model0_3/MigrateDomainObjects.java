/**
 * 
 */
package org.openiaml.model.tests.eclipse.migration.model0_3;

import java.io.File;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.impl.FileReferenceImpl;
import org.openiaml.model.migrate.IamlModelMigrator;
import org.openiaml.model.migrate.Migrate0To1;
import org.openiaml.model.migrate.Migrate1To2;
import org.openiaml.model.migrate.Migrate2To4;
import org.openiaml.model.migrate.Migrate4To5;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.domain.DomainStoreTypes;
import org.openiaml.model.model.visual.Frame;
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
		
		// there should be five children (five pages)
		assertEditorHasChildren(5, editor);
		
		// check the contents
		assertHasDomainStore(editor, "normal domain store");
		assertHasDomainStore(editor, "file domain store");
		assertHasDomainObject(editor, "normal domain object");
		assertHasFrame(editor, "a page");
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
		assertEditorHasChildren(2, sub);
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

	/**
	 * Test the file domain store sub-editor.
	 * 
	 * @throws Exception
	 */
	public void testFileDomainStore() throws Exception {
		migrateModel();
		
		ShapeNodeEditPart part = assertHasDomainStore(editor, "file domain store");
		assertNotNull(part);
		
		DomainStore store = (DomainStore) part.resolveSemanticElement();
		assertEquals(store.getName(), "file domain store");
		assertEquals(store.getType(), DomainStoreTypes.PROPERTIES_FILE);
		
		// we need to get a URI
		URI relative = store.eResource().getURI();
		File desired = FileReferenceImpl.resolveFilePath(relative, "test1.properties");
		File actual = store.getFile().toFile(relative);
		assertEquals("The desired and actual FileReferences do not resolve to the same location: actual = '" + actual + "', desired = '" + desired + "'", actual, desired);
		
		// open diagram
		DiagramDocumentEditor sub = openDiagram(part);
		assertEditorDomainStore(sub);
		
		// check contents
		assertEditorHasChildren(2, sub);
		{
			ShapeNodeEditPart p = assertHasDomainObject(sub, "file domain object");
			assertNotNull(p);
			
			DomainObject obj = (DomainObject) p.resolveSemanticElement();
			assertEquals(obj.getName(), "file domain object");
		}
		{
			ShapeNodeEditPart p = assertHasDomainAttribute(sub, "file domain attribute");
			assertNotNull(p);
			
			DomainAttribute obj = (DomainAttribute) p.resolveSemanticElement();
			assertEquals(obj.getName(), "file domain attribute");
		}
		
		sub.close(false);
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
		assertEditorHasChildren(4, sub);
		{
			ShapeNodeEditPart p = assertHasDomainObject(sub, "do");
			assertNotNull(p);
			
			DomainObject obj = (DomainObject) p.resolveSemanticElement();
			assertEquals(obj.getName(), "do");
		}
		{
			ShapeNodeEditPart p = assertHasDomainAttribute(sub, "da");
			assertNotNull(p);
			
			DomainAttribute obj = (DomainAttribute) p.resolveSemanticElement();
			assertEquals(obj.getName(), "da");
		}
		{
			ShapeNodeEditPart p = assertHasDomainObject(sub, "fo");
			assertNotNull(p);
			
			DomainObject obj = (DomainObject) p.resolveSemanticElement();
			assertEquals(obj.getName(), "fo");
		}
		{
			ShapeNodeEditPart p = assertHasDomainAttribute(sub, "fa");
			assertNotNull(p);
			
			DomainAttribute obj = (DomainAttribute) p.resolveSemanticElement();
			assertEquals(obj.getName(), "fa");
		}
		
		sub.close(false);
	}
	
	/**
	 * Test the domain store sub-editor, and then the domain
	 * object sub-editor.
	 * 
	 * @throws Exception
	 */
	public void testNormalDomainStoreObject() throws Exception {
		migrateModel();
		
		ShapeNodeEditPart part = assertHasDomainStore(editor, "normal domain store");
		assertNotNull(part);
		
		// open diagram
		DiagramDocumentEditor sub = openDiagram(part);
		assertEditorDomainStore(sub);
		
		// check contents
		ShapeNodeEditPart p2 = assertHasDomainObject(sub, "normal domain object");
		assertNotNull(p2);
		
		// open diagram
		DiagramDocumentEditor sub2 = openDiagram(p2);
		assertEditorDomainObject(sub2);
		
		// check contents
		assertEditorHasChildren(1, sub2);
		{
			ShapeNodeEditPart p = assertHasDomainAttribute(sub2, "another normal attribute");
			assertNotNull(p);
			
			DomainAttribute obj = (DomainAttribute) p.resolveSemanticElement();
			assertEquals(obj.getName(), "another normal attribute");
		}
		
		sub2.close(false);
		sub.close(false);
	}
	
	/**
	 * Test the file store sub-editor, and then the file
	 * object sub-editor.
	 * 
	 * @throws Exception
	 */
	public void testFileDomainStoreObject() throws Exception {
		migrateModel();
		
		ShapeNodeEditPart part = assertHasDomainStore(editor, "file domain store");
		assertNotNull(part);
		
		// open diagram
		DiagramDocumentEditor sub = openDiagram(part);
		assertEditorDomainStore(sub);
		
		// check contents
		ShapeNodeEditPart p2 = assertHasDomainObject(sub, "file domain object");
		assertNotNull(p2);
		
		// open diagram
		DiagramDocumentEditor sub2 = openDiagram(p2);
		assertEditorDomainObject(sub2);
		
		// check contents
		assertEditorHasChildren(1, sub2);
		{
			ShapeNodeEditPart p = assertHasDomainAttribute(sub2, "another fda");
			assertNotNull(p);
			
			DomainAttribute obj = (DomainAttribute) p.resolveSemanticElement();
			assertEquals(obj.getName(), "another fda");
		}
		
		sub2.close(false);
		sub.close(false);
	}
	
}
