/**
 * 
 */
package org.openiaml.model.tests.eclipse.shortcuts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.model.ModelPackage;

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
	
	/**
	 * Test the shortcuts in the 'Home' frame.
	 * 
	 * @throws Exception
	 */
	public void testRootShortcuts() throws Exception {
		initializeModelFile();

		assertEditorHasChildren(2, editor);
		
		ShapeNodeEditPart home = assertHasFrame(editor, "Home");
		
		// open the page
		editor_page = openDiagram(home);
		try {
			assertEditorFrame(editor_page);
			
			// directly, there is a button and a static value
			ShapeNodeEditPart button = assertHasButton(editor_page, "direct");
			ShapeNodeEditPart staticValue = assertHasStaticValue(editor_page, "parameter");
			
			// the button runs an external operation, which is a shortcut
			ShapeNodeEditPart op = assertHasCompositeOperation(editor_page, "some operation", true);
			
			// connected by a RunAction
			ConnectionNodeEditPart run = assertHasRunAction(editor_page, button, op, "onClick");
			
			// this RunAction has an incoming parameter
			assertHasParameterEdge(editor_page, staticValue, run);
			
			// there should only be three children (as above)
			assertEditorHasChildren(3, editor_page);
		} finally {
			editor_page.close(false);
		}
	}
	
	/**
	 * Test the shortcuts in the 'direct' button.
	 * 
	 * @throws Exception
	 */
	public void testButtonShortcuts() throws Exception {
		initializeModelFile();

		assertEditorHasChildren(2, editor);
		
		ShapeNodeEditPart home = assertHasFrame(editor, "Home");
		
		// open the page
		editor_page = openDiagram(home);
		try {
			assertEditorFrame(editor_page);
			
			// directly, there is a button and a static value
			ShapeNodeEditPart button = assertHasButton(editor_page, "direct");
			
			// open the button
			DiagramDocumentEditor editor_button = openDiagram(button);
			try {
				
				// it has an event trigger onChange (not a shortcut)
				ShapeNodeEditPart event = assertHasEventTrigger(editor_button, false, ModelPackage.eINSTANCE.getChangeable_OnChange());
				
				// there is a shortcutted operation
				ShapeNodeEditPart op = assertHasCompositeOperation(editor_button, "some operation", true);
				
				// the event is connected to the op by a RunAction
				ConnectionNodeEditPart run = assertHasRunAction(editor_button, event, op, "run");
				
				// there is also a shortcutted StaticValue, which is the parameter
				ShapeNodeEditPart staticValue = assertHasStaticValue(editor_button, "parameter", true);
				
				// which is connected to the RunAction
				assertHasParameterEdge(editor_button, staticValue, run);

				// there should only be three children (as above)
				assertEditorHasChildren(3, editor_button);

			} finally {
				editor_button.close(false);
			}
		} finally {
			editor_page.close(false);
		}
	}

	/**
	 * Test the shortcuts in the 'Target' frame.
	 * 
	 * @throws Exception
	 */
	public void testTargetShortcuts() throws Exception {
		initializeModelFile();

		assertEditorHasChildren(2, editor);
		
		ShapeNodeEditPart target = assertHasFrame(editor, "Target");
		
		// open the page
		editor_page = openDiagram(target);
		try {
			assertEditorFrame(editor_page);
			
			// we contain the operation directly
			ShapeNodeEditPart op = assertHasCompositeOperation(editor_page, "some operation", false);
			
			// there is a shortcutted button 'direct'
			ShapeNodeEditPart button = assertHasButton(editor_page, "direct", true);
			
			// connected by a RunAction to the operation
			ConnectionNodeEditPart run = assertHasRunAction(editor_page, button, op, "onClick");
			
			// there is a shortcutted event 'onChange'
			ShapeNodeEditPart event = assertHasEventTrigger(editor_page, true, ModelPackage.eINSTANCE.getChangeable_OnChange());
			
			// also connected by a RunAction
			ConnectionNodeEditPart run2 = assertHasRunAction(editor_page, event, op, "run");
			
			// finally, there is a static value that has a shortcut
			ShapeNodeEditPart staticValue = assertHasStaticValue(editor_page, "parameter", true);
			
			// connected to both run actions
			assertHasParameterEdge(editor_page, staticValue, run);
			assertHasParameterEdge(editor_page, staticValue, run2);
			
			// there should only be four children (as above)
			assertEditorHasChildren(4, editor_page);
			
		} finally {
			editor_page.close(false);
		}
	}
	
	
}
