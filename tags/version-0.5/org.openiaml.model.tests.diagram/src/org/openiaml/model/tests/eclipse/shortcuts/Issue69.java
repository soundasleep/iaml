/**
 * 
 */
package org.openiaml.model.tests.eclipse.shortcuts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;

/**
 * Issue 69: Incoming ParameterWires from Shortcuts not being rendered from Shortcuts
 * 
 * @author jmwright
 *
 */
public class Issue69 extends AbstractShortcutsTestCase {

	@Override
	public String getModel() {
		return "Issue69.iaml";
	}
	
	protected DiagramDocumentEditor editor_page;
	
	public void testEclipseShortcuts() throws Exception {
		initializeModelFile();

		assertEditorHasChildren(2, editor);
		
		// check the contents
		ShapeNodeEditPart page = assertHasFrame(editor, "container");

		// open the page
		editor_page = openDiagram(page);
		assertEditorFrame(editor_page);
		
		// there should be three children
		assertEditorHasChildren(3, editor_page);
		
		// the local domain object instance
		ShapeNodeEditPart instance = assertHasDomainObjectInstance(editor_page, "domain object instance", false);
		ShapeNodeEditPart object = assertHasDomainObject(editor_page, "domain object", true);
		ShapeNodeEditPart property = assertHasProperty(editor_page, "property", true);
		
		// these should be connected
		ConnectionNodeEditPart select = assertHasSelectWire(editor_page, object, instance, "select");
		
		// as with the parameter wire
		assertHasParameterEdge(editor_page, property, select);
	}

	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	@Override
	public void tearDown() throws Exception {
		
		if (editor_page != null)
			editor_page.close(false);
		
		super.tearDown();
	}

}
