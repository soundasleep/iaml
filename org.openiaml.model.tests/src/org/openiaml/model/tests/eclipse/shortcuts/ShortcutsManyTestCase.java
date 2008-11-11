/**
 * 
 */
package org.openiaml.model.tests.eclipse.shortcuts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;

/**
 * Tests many shortcuts.
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class ShortcutsManyTestCase extends ShortcutsTestCase {

	public String getModel() {
		return "shortcuts-many.iaml";
	}
	
	protected DiagramDocumentEditor editor_page;
	protected DiagramDocumentEditor editor_form1;
	protected DiagramDocumentEditor editor_form2;
	
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

		// it should have two forms
		assertEquals("there should be 2 children", 2, editor_page.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart form1 = assertHasInputForm(editor_page, "form1");
		ShapeNodeEditPart form2 = assertHasInputForm(editor_page, "form2");
		
		// open up the first form
		editor_form1 = openDiagram(form1);

		assertEquals("active editor is the visual plugin", 
				"org.openiaml.model.model.diagram.visual.part.IamlDiagramEditor", 
				editor_form1.getClass().getName());
		
		// it should have a domain attribute connected to an event trigger
		assertEquals("there should be 8 children", 8, editor_form1.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart f1f1 = assertHasInputTextField(editor_form1, "f1");
		ShapeNodeEditPart f1f2 = assertHasInputTextField(editor_form1, "f2");
		ShapeNodeEditPart f1f3 = assertHasInputTextField(editor_form1, "f3");
		ShapeNodeEditPart f1f4 = assertHasInputTextField(editor_form1, "f4");
		ShapeNodeEditPart f1t1 = assertHasInputTextField(editor_form1, "t1");
		ShapeNodeEditPart f1t2 = assertHasInputTextField(editor_form1, "t2");
		ShapeNodeEditPart f1t3 = assertHasInputTextField(editor_form1, "t3");
		ShapeNodeEditPart f1t4 = assertHasInputTextField(editor_form1, "t4");
		
		// they should be connected
		assertHasSyncWire(editor_form1, f1f1, f1t1, "s1");
		assertHasSyncWire(editor_form1, f1f2, f1t2, "s2");
		assertHasSyncWire(editor_form1, f1f3, f1t3, "s3");
		assertHasSyncWire(editor_form1, f1f4, f1t4, "s4");
		assertNotShortcut(f1f1);
		assertNotShortcut(f1f2);
		assertNotShortcut(f1f3);
		assertNotShortcut(f1f4);
		assertShortcut(f1t1);
		assertShortcut(f1t2);
		assertShortcut(f1t3);
		assertShortcut(f1t4);

		// open up the next form
		editor_form2 = openDiagram(form2);

		assertEquals("active editor is the visual plugin", 
				"org.openiaml.model.model.diagram.visual.part.IamlDiagramEditor", 
				editor_form2.getClass().getName());
		
		// it should have a domain attribute connected to an event trigger
		assertEquals("there should be 8 children", 8, editor_form2.getDiagramEditPart().getChildren().size());
		ShapeNodeEditPart f2f1 = assertHasInputTextField(editor_form2, "f1");
		ShapeNodeEditPart f2f2 = assertHasInputTextField(editor_form2, "f2");
		ShapeNodeEditPart f2f3 = assertHasInputTextField(editor_form2, "f3");
		ShapeNodeEditPart f2f4 = assertHasInputTextField(editor_form2, "f4");
		ShapeNodeEditPart f2t1 = assertHasInputTextField(editor_form2, "t1");
		ShapeNodeEditPart f2t2 = assertHasInputTextField(editor_form2, "t2");
		ShapeNodeEditPart f2t3 = assertHasInputTextField(editor_form2, "t3");
		ShapeNodeEditPart f2t4 = assertHasInputTextField(editor_form2, "t4");
		
		// they should be connected
		assertHasSyncWire(editor_form2, f2f1, f2t1, "s1");
		assertHasSyncWire(editor_form2, f2f2, f2t2, "s2");
		assertHasSyncWire(editor_form2, f2f3, f2t3, "s3");
		assertHasSyncWire(editor_form2, f2f4, f2t4, "s4");
		assertShortcut(f2f1);
		assertShortcut(f2f2);
		assertShortcut(f2f3);
		assertShortcut(f2f4);
		assertNotShortcut(f2t1);
		assertNotShortcut(f2t2);
		assertNotShortcut(f2t3);
		assertNotShortcut(f2t4);

		assertSameReferencedElement(f1f1, f2f1);
		assertSameReferencedElement(f1f2, f2f2);
		assertSameReferencedElement(f1f3, f2f3);
		assertSameReferencedElement(f1f4, f2f4);
		assertSameReferencedElement(f1t1, f2t1);
		assertSameReferencedElement(f1t2, f2t2);
		assertSameReferencedElement(f1t3, f2t3);
		assertSameReferencedElement(f1t4, f2t4);

	}
	
	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	public void tearDown() throws Exception {
		if (editor_form1 != null)
			editor_form1.close(false);
		
		if (editor_form2 != null)
			editor_form2.close(false);
		
		if (editor_page != null)
			editor_page.close(false);
		
		super.tearDown();
	}

}
