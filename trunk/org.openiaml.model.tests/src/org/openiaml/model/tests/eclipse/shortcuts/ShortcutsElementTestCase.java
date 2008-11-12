/**
 * 
 */
package org.openiaml.model.tests.eclipse.shortcuts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;

/**
 * Tests element shortcuts.
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class ShortcutsElementTestCase extends AbstractShortcutsTestCase {

	public String getModel() {
		return "shortcuts-element.iaml";
	}
	
	protected DiagramDocumentEditor editor_page;
	protected DiagramDocumentEditor editor_form;
	
	public void testLoadModel() throws Exception {

		// there should be four children
		assertEquals("there should be 1 children", 1, editor.getDiagramEditPart().getChildren().size());
		
		// check the contents
		ShapeNodeEditPart page = assertHasPage(editor, "page");

		// open the domain store
		editor_page = openDiagram(page);

		assertEquals("active editor is the visual plugin", 
				"org.openiaml.model.model.diagram.visual.part.IamlDiagramEditor", 
				editor_page.getClass().getName());

		// it should have a domain object
		assertEquals("there should be 3 children", 3, editor_page.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart form = assertHasInputForm(editor_page, "target");
		ShapeNodeEditPart field = assertHasInputTextField(editor_page, "target2");
		ShapeNodeEditPart op = assertHasOperation(editor_page, "op");

		// they should be connected
		assertHasRunInstanceWire(editor_page, field, op, "run");
		assertNotShortcut(form);
		assertNotShortcut(field);
		assertShortcut(op);

		// open up the domain object
		editor_form = openDiagram(form);
		
		// TODO refactor this into assertVisualPlugin(form)
		assertEquals("active editor is the visual plugin", 
				"org.openiaml.model.model.diagram.visual.part.IamlDiagramEditor", 
				editor_form.getClass().getName());
		
		// it should have a domain attribute connected to an event trigger
		assertEquals("there should be 3 children", 3, editor_form.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart event = assertHasEventTrigger(editor_form, "event");
		ShapeNodeEditPart op2 = assertHasOperation(editor_form, "op");
		ShapeNodeEditPart field2 = assertHasInputTextField(editor_form, "target2");
		
		// they should be connected
		assertHasRunInstanceWire(editor_form, field2, op2, "run");
		assertHasRunInstanceWire(editor_form, event, op2, "run2");
		assertNotShortcut(event);
		assertNotShortcut(op2);
		assertShortcut(field2);

		assertSameReferencedElement(field, field2);
		assertSameReferencedElement(op, op2);

	}
	
	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	public void tearDown() throws Exception {
		
		if (editor_form != null)
			editor_form.close(false);
		
		if (editor_page != null)
			editor_page.close(false);
		
		super.tearDown();
	}

}
