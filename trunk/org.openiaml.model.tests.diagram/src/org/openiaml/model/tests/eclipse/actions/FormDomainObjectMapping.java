/**
 * 
 */
package org.openiaml.model.tests.eclipse.actions;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.diagram.custom.actions.RefreshFormMappingsWithDrools;

/**
 * Test InputForm mappings to DomainObjects. (Issue 45)
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class FormDomainObjectMapping extends AbstractActionTestCase<GraphicalEditPart> {

	@Override
	public String getModel() {
		return "FormDomainObjectMapping.iaml";
	}
	
	/**
	 * Test the initial model.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		initializeModelFile();
		
		// there should only be two children
		assertEditorHasChildren(3, editor);
		
		// get contents
		ShapeNodeEditPart page = assertHasFrame(editor, "container");
		assertHasDomainSchema(editor, "domain schema");
		assertHasDomainSource(editor, "domain source");
		
		// open page
		DiagramDocumentEditor editor_page = openDiagram(page);
		try {
			assertEditorFrame(editor_page);
			
			// should have three children
			assertEditorHasChildren(3, editor_page);
			ShapeNodeEditPart form = assertHasInputForm(editor_page, "target form", false);
			ShapeNodeEditPart source = assertHasDomainSource(editor_page, "domain source", true);
			
			ShapeNodeEditPart iterator = assertHasDomainIterator(editor_page, "iterator");
			
			// connected by a SyncWire
			assertHasSyncWire(editor_page, iterator, form, "sync");
			
			// the iterator has a select edge
			assertHasSelectEdge(editor_page, iterator, source);
			
			// open input form
			DiagramDocumentEditor editor_form = openDiagram(form);
			try {
				assertEditorVisual(editor_form);
				
				// empty
				assertEditorHasChildren(0, editor_form);
			} finally {
				editor_form.close(false);
			}
		} finally {
			editor_page.close(false);
		}

	}
	
	/**
	 * Test the action.
	 * 
	 * @throws Exception
	 */
	public void testAction() throws Exception {
		initializeModelFile();
		
		// there should only be two children
		
		// open page
		ShapeNodeEditPart page = assertHasFrame(editor, "container");
		DiagramDocumentEditor editor_page = openDiagram(page);
		try {
			ShapeNodeEditPart form = assertHasInputForm(editor_page, "target form", false);
	
			// lets run the action
			runAction(new RefreshFormMappingsWithDrools(), form);
	
			// the current editor should still be the same
			// should have three children
			assertEditorHasChildren(3, editor_page);
			assertHasInputForm(editor_page, "target form", false);
			
			// open input form
			DiagramDocumentEditor editor_form = openDiagram(form);
			try {
				assertEditorVisual(editor_form);
				
				// no longer empty!
				// text fields in the form
				assertEditorHasChildren(6, editor_form);
				ShapeNodeEditPart a1 = assertHasInputTextField(editor_form, "attribute one", false);
				assertGenerated(a1);
				ShapeNodeEditPart a2 = assertHasInputTextField(editor_form, "attribute two", false);
				assertGenerated(a2);
				ShapeNodeEditPart a3 = assertHasInputTextField(editor_form, "attribute three", false);
				assertGenerated(a3);
		
				// attribute instances in the iterator
				ShapeNodeEditPart d1 = assertHasDomainAttributeInstance(editor_form, "attribute one", true);
				assertGenerated(d1);
				ShapeNodeEditPart d2 = assertHasDomainAttributeInstance(editor_form, "attribute two", true);
				assertGenerated(d2);
				ShapeNodeEditPart d3 = assertHasDomainAttributeInstance(editor_form, "attribute three", true);
				assertGenerated(d3);
				
				// conneced by SyncWires
				ConnectionNodeEditPart s1 = assertHasSyncWire(editor_form, a1, d1, "sync");
				assertGenerated(s1);
				ConnectionNodeEditPart s2 = assertHasSyncWire(editor_form, a2, d2, "sync");
				assertGenerated(s2);
				ConnectionNodeEditPart s3 = assertHasSyncWire(editor_form, a3, d3, "sync");
				assertGenerated(s3);
			} finally {
				editor_form.close(false);
			}
			
		} finally {
			editor_page.close(false);
		}

	}

}
