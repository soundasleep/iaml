/**
 * 
 */
package org.openiaml.model.tests.eclipse.shortcuts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;

/**
 * We can separate model instances into multiple files, which all
 * refer to each other. However we can't yet this this programatically,
 * as a NPE is thrown. Perhaps an Eclipse application is loading the
 * separate model in a different way?
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
@Deprecated
public class SeparatedInstances extends AbstractShortcutsTestCase {

	@Override
	public String getModel() {
		return "SeparatedInstances.iaml";
	}
	
	protected DiagramDocumentEditor editor_page;
	
	public void testEclipseShortcuts() throws Exception {
		initializeModelFile();
		assertEditorRoot(editor);

		assertEditorHasChildren(2, editor);
		
		ShapeNodeEditPart page = assertHasFrame(editor, "Home", false);
		assertHasOperation(editor, "root operation", false);
		
		// open the page
		editor_page = openDiagram(page);
		assertEditorVisual(editor_page);

		ShapeNodeEditPart event = assertHasEventTrigger(editor_page, "access", false);
		// shortcut
		ShapeNodeEditPart op = assertHasOperation(editor_page, "target operation", true);
		assertHasRunInstanceWire(editor_page, event, op, "run");
		
		assertEditorHasChildren(2, editor_page);

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
