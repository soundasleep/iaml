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

	public String getModel() {
		return "FormDomainObjectMapping.iaml";
	}
	
	protected DiagramDocumentEditor editor_page;
	protected DiagramDocumentEditor editor_form;
	
	/**
	 * Test the initial model.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		initializeModelFile();
		
		// there should only be two children
		assertEditorHasChildren(2, editor);
		
		// get contents
		ShapeNodeEditPart page = assertHasPage(editor, "container");
		assertHasDomainStore(editor, "domain store");
		
		// open page
		editor_page = openDiagram(page);
		assertEditorVisual(editor_page);
		
		// should have two children
		assertEditorHasChildren(2, editor_page);
		ShapeNodeEditPart form = assertHasInputForm(editor_page, "target form", false);
		assertHasDomainObject(editor_page, "domain object", true);
		
		// open input form
		editor_form = openDiagram(form);
		assertEditorVisual(editor_form);
		
		// empty
		assertEditorHasChildren(0, editor_form);

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
		ShapeNodeEditPart page = assertHasPage(editor, "container");
		editor_page = openDiagram(page);
		ShapeNodeEditPart form = assertHasInputForm(editor_page, "target form", false);

		// lets run the action
		runAction(new RefreshFormMappingsWithDrools(), form);

		// the current editor should still be the same
		// should have two children
		assertEditorHasChildren(2, editor_page);
		assertHasInputForm(editor_page, "target form", false);
		assertHasDomainObject(editor_page, "domain object", true);
		
		// open input form
		editor_form = openDiagram(form);
		assertEditorVisual(editor_form);
		
		// no longer empty!
		// double elements, because shortcuts are also rendered
		assertEditorHasChildren(6, editor_form);
		ShapeNodeEditPart a1 = assertHasInputTextField(editor_form, "attribute one", false);
		assertGenerated(a1);
		ShapeNodeEditPart a2 = assertHasInputTextField(editor_form, "attribute two", false);
		assertGenerated(a2);
		ShapeNodeEditPart a3 = assertHasInputTextField(editor_form, "attribute three", false);
		assertGenerated(a3);

		ShapeNodeEditPart d1 = assertHasDomainAttribute(editor_form, "attribute one", true);
		assertNotGenerated(d1);
		ShapeNodeEditPart d2 = assertHasDomainAttribute(editor_form, "attribute two", true);
		assertNotGenerated(d2);
		ShapeNodeEditPart d3 = assertHasDomainAttribute(editor_form, "attribute three", true);
		assertNotGenerated(d3);
		
		// conneced by SyncWires
		ConnectionNodeEditPart s1 = assertHasSyncWire(editor_form, a1, d1, "sync");
		assertGenerated(s1);
		ConnectionNodeEditPart s2 = assertHasSyncWire(editor_form, a2, d2, "sync");
		assertGenerated(s2);
		ConnectionNodeEditPart s3 = assertHasSyncWire(editor_form, a3, d3, "sync");
		assertGenerated(s3);
		

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
