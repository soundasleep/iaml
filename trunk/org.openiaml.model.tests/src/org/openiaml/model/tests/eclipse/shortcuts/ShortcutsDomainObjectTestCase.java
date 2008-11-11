/**
 * 
 */
package org.openiaml.model.tests.eclipse.shortcuts;

import org.eclipse.core.resources.IFile;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorPart;
import org.openiaml.model.model.diagram.part.IamlDiagramEditor;
import org.openiaml.model.tests.EclipseTestCaseHelper;

/**
 * Tests domain object shortcuts.
 * 
 * @model shortcuts-domain_object.iaml
 * @author jmwright
 *
 */
public class ShortcutsDomainObjectTestCase extends EclipseTestCaseHelper {

	public static final String ROOT = "src/org/openiaml/model/tests/eclipse/shortcuts/";
	public static final String MODEL = "shortcuts-domain_object.iaml";
	public static final String DIAGRAM = MODEL + "_diagram";
	
	public void testLoadModel() throws Exception {
		// copy our local file into the project
		IFile targetModel = project.getFile(MODEL);
		copyFileIntoWorkspace(ROOT + MODEL,
				targetModel);
		IFile targetDiagram = project.getFile(DIAGRAM);
		
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
		IamlDiagramEditor editor = (IamlDiagramEditor) ep;

		// there should be four children
		assertEquals("there should be 2 children", 2, editor.getDiagramEditPart().getChildren().size());
		
		// check the contents
		ShapeNodeEditPart page = assertHasPage(editor, "page");
		ShapeNodeEditPart store = assertHasDomainStore(editor, "domain store");

		// open the domain store
		DiagramDocumentEditor editor_store = openDiagram(store);

		assertEquals("active editor is the domain store plugin", 
				"org.openiaml.model.model.diagram.domainstore.part.IamlDiagramEditor", 
				editor_store.getClass().getName());

		// it should have a domain object
		assertEquals("there should be 1 children", 1, editor_store.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart object = assertHasDomainObject(editor_store, "domain object");
		
		// open up the domain object
		DiagramDocumentEditor editor_object = openDiagram(object);

		assertEquals("active editor is the domain store plugin", 
				"org.openiaml.model.model.diagram.domain_object.part.IamlDiagramEditor", 
				editor_object.getClass().getName());
		
		// it should have a domain attribute connected to an event trigger
		assertEquals("there should be 2 children", 2, editor_object.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart attribute = assertHasDomainAttribute(editor_object, "domain attribute");
		ShapeNodeEditPart event = assertHasEventTrigger(editor_object, "event trigger");
		
		// they should be connected
		assertHasRunInstanceWire(editor_object, event, attribute, "runWire");
		assertShortcut(event);
		assertNotShortcut(attribute);
		
		// close editors 
		// TODO move into tearDown()
		editor_object.close(false);
		editor_store.close(false);
		
		// open 'page' editor
		DiagramDocumentEditor editor_page = openDiagram(page);
		
		assertEquals("active editor is the domain store plugin", 
				"org.openiaml.model.model.diagram.visual.part.IamlDiagramEditor", 
				editor_page.getClass().getName());
		
		// it should have a domain attribute connected to an event trigger
		assertEquals("there should be 2 children", 2, editor_page.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart attribute2 = assertHasDomainAttribute(editor_page, "domain attribute");
		ShapeNodeEditPart event2 = assertHasEventTrigger(editor_page, "event trigger");

		// they should be connected
		assertHasRunInstanceWire(editor_page, event2, attribute2, "runWire");
		assertNotShortcut(event2);
		assertShortcut(attribute2);

		// close final editors
		editor_page.close(false);
		editor.close(false);
		
	}

}
