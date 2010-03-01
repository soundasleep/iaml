/**
 * 
 */
package org.openiaml.model.tests.eclipse.actions;

import junit.framework.AssertionFailedError;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.diagram.custom.actions.InferContainedElementsAction;

/**
 * Test partial inference.
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class PartialInference extends AbstractActionTestCase<GraphicalEditPart> {

	@Override
	public String getModel() {
		return "PartialInference.iaml";
	}
	
	protected DiagramDocumentEditor editor_page;
	protected DiagramDocumentEditor editor_text;
	
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
		assertHasFrame(editor, "target");
		assertHasFrame(editor, "other");
	
		// open page
		ShapeNodeEditPart page = assertHasFrame(editor, "target");
		editor_page = openDiagram(page);
		assertEditorFrame(editor_page);

		// get text field
		ShapeNodeEditPart text1 = assertHasInputTextField(editor_page, "target text field");
		editor_text = openDiagram(text1);
		assertEditorVisual(editor_text);
		
		// it should be empty
		assertEditorHasChildren(0, editor_text);

	}
	
	/**
	 * Test partial inference - should only create elements within
	 * the selected element.
	 * 
	 * @throws Exception
	 */
	public void testPartialInference() throws Exception {
		initializeModelFile();
		
		{
			// there should only be two children
			assertEditorHasChildren(2, editor);
			
			// open page
			ShapeNodeEditPart page = assertHasFrame(editor, "target");
			editor_page = openDiagram(page);
			assertEditorFrame(editor_page);
			
			// there should be two children (text fields)
			assertEditorHasChildren(2, editor_page);
			ShapeNodeEditPart text1 = assertHasInputTextField(editor_page, "target text field");
			assertNotShortcut(text1);
			assertNotGenerated(text1);
			ShapeNodeEditPart text2 = assertHasInputTextField(editor_page, "in sync");
			assertNotShortcut(text2);
			assertNotGenerated(text2);
			ConnectionNodeEditPart sync = assertHasSyncWire(editor_page, text1, text2, "sync");
			assertNotGenerated(sync);
			
			// select the first text field and infer!
			runAction(new InferContainedElementsAction(), text1);
			
			// save it
			editor_page.doSave(new NullProgressMonitor());
			
			// there should not be any new elements in the local editor
			assertEditorHasChildren(2, editor_page);
			
			// however, if we open up the text field, there should be elements
			// in here generated
			editor_text = openDiagram(text1);
	
			checkPartialInferenceEditor();
			
			// however, if we now close this diagram and infer in place,
			// we should get the rest of the normal editor
			editor_text.close(false);
			editor_text = null;
			editor_page.close(false);
			editor_page = null;
			editor.close(false);
			editor = null;
		}
		
		// delete the target diagram
		IFile model = getProject().getFile(getModel());
		IFile modelNew = getProject().getFile("new-model.iaml");
		IFile diagramNew = getProject().getFile("new-model.iaml_diagram");
		if (modelNew.exists()) {
			getProject().haltDelete(modelNew);
			
		}
		if (diagramNew.exists()) {
			getProject().haltDelete(diagramNew);
		}
		assertFalse(modelNew.exists());
		assertFalse(diagramNew.exists());
		
		// infer
		inferSourceModelFile(model);
		getProject().refreshProject();
		
		// try renaming the model file
		// (otherwise, it seems only the old diagram is stored in memory, and the test case below fails)
		model.move(modelNew.getFullPath(), true, monitor);
		getProject().refreshProject();
		
		assertTrue("New model file " + modelNew + " exists", modelNew.exists());
	
		// re-init diagram and reload
		initialiseAndLoadDiagram(modelNew, diagramNew);
		
		{
			// there should only be two children
			assertEditorHasChildren(2, editor);
			
			// open page
			ShapeNodeEditPart page = assertHasFrame(editor, "target");
			editor_page = openDiagram(page);
			assertEditorFrame(editor_page);
			
			// there should be three children (text fields and access and init)
			assertEditorHasChildren(4, editor_page);
			ShapeNodeEditPart text1 = assertHasInputTextField(editor_page, "target text field");
			assertNotShortcut(text1);
			assertNotGenerated(text1);
			ShapeNodeEditPart text2 = assertHasInputTextField(editor_page, "in sync");
			assertNotShortcut(text2);
			assertNotGenerated(text2);
			ConnectionNodeEditPart sync = assertHasSyncWire(editor_page, text1, text2, "sync");
			assertNotGenerated(sync);
			ShapeNodeEditPart access = assertHasEventTrigger(editor_page, false, "onAccess");
			assertGenerated(access);
			ShapeNodeEditPart init = assertHasEventTrigger(editor_page, false, "onInit");
			assertGenerated(init);
			
			// in the fully inferred file, there should be lots of children
			editor_text = openDiagram(text1);
			
			checkFullInferenceEditor();
		}
		
	}

	/**
	 * Test full inference - elements should be created everywhere.
	 * 
	 * @throws Exception
	 */
	public void testFullInference() throws Exception {
		initializeModelFile(true);
		
		// there should only be two children
		assertEditorHasChildren(2, editor);
		
		// open page
		ShapeNodeEditPart page = assertHasFrame(editor, "target");
		editor_page = openDiagram(page);
		assertEditorFrame(editor_page);
		
		// there should be three children (text fields and access and oninit)
		assertEditorHasChildren(4, editor_page);
		ShapeNodeEditPart text1 = assertHasInputTextField(editor_page, "target text field");
		assertNotShortcut(text1);
		assertNotGenerated(text1);
		ShapeNodeEditPart text2 = assertHasInputTextField(editor_page, "in sync");
		assertNotShortcut(text2);
		assertNotGenerated(text2);
		ConnectionNodeEditPart sync = assertHasSyncWire(editor_page, text1, text2, "sync");
		assertNotGenerated(sync);
		ShapeNodeEditPart access = assertHasEventTrigger(editor_page, false, "onAccess");
		assertGenerated(access);
		ShapeNodeEditPart init = assertHasEventTrigger(editor_page, false, "onInit");
		assertGenerated(init);
		
		// in the fully inferred file, there should be lots of children
		editor_text = openDiagram(text1);
		
		checkFullInferenceEditor();
		
	}

	/**
	 * Check the editor to make sure it's been _partially_ inferred.
	 */
	protected void checkPartialInferenceEditor() {
		assertEditorVisual(editor_text);
		
		assertEditorHasChildren(6, editor_text);
		
		ShapeNodeEditPart access = assertHasEventTrigger(editor_text, false, "onAccess");
		ShapeNodeEditPart edit = assertHasEventTrigger(editor_text, false, "onEdit");
		ShapeNodeEditPart update = assertHasOperation(editor_text, "update", false);
		ShapeNodeEditPart init = assertHasOperation(editor_text, "init", false);
		ShapeNodeEditPart fieldValue = assertHasFieldValue(editor_text, false);
		ShapeNodeEditPart condition = assertHasCompositeCondition(editor_text, "fieldValue is set", false);
		
		// all generated
		assertGenerated(access);
		assertGenerated(edit);
		assertGenerated(update);
		assertGenerated(init);
		assertGenerated(fieldValue);
		assertGenerated(condition);
		
		// connected by run wire
		ConnectionNodeEditPart run = assertHasRunInstanceWire(editor_text, access, init, "run");
		assertGenerated(run);
	}
	
	/**
	 * Check the editor to make sure it's been _fully_ inferred.
	 */
	protected void checkFullInferenceEditor() {
		assertEditorVisual(editor_text);
		
		assertEditorHasChildren(10, editor_text);
		
		ShapeNodeEditPart access = assertHasEventTrigger(editor_text, false, "onAccess");
		ShapeNodeEditPart edit = assertHasEventTrigger(editor_text, false, "onEdit");
		ShapeNodeEditPart update = assertHasOperation(editor_text, "update", false);
		ShapeNodeEditPart init = assertHasOperation(editor_text, "init", false);
		ShapeNodeEditPart fieldValue = assertHasFieldValue(editor_text, false);
		ShapeNodeEditPart condition = assertHasCompositeCondition(editor_text, "fieldValue is set", false);
		ShapeNodeEditPart condition2 = assertHasCompositeCondition(editor_text, "fieldValue is set", true);

		ShapeNodeEditPart edit2 = assertHasEventTrigger(editor_text, true, "onEdit");
		ShapeNodeEditPart update2 = assertHasOperation(editor_text, "update", true);
		ShapeNodeEditPart fieldValue2 = assertHasFieldValue(editor_text, true);

		// all generated
		assertGenerated(access);
		assertGenerated(edit);
		assertGenerated(update);
		assertGenerated(init);
		assertGenerated(fieldValue);
		assertGenerated(condition);
		assertGenerated(edit2);
		assertGenerated(update2);
		assertGenerated(fieldValue2);
		
		// connected by run wire
		ConnectionNodeEditPart run = assertHasRunInstanceWire(editor_text, access, init, "run");
		assertGenerated(run);
		ConnectionNodeEditPart param = assertHasParameterEdge(editor_text, fieldValue2, run);
		assertGenerated(param);
		
		// connected by run wire
		ConnectionNodeEditPart run2 = assertHasRunInstanceWire(editor_text, edit, update2, "run");
		assertGenerated(run2);
		ConnectionNodeEditPart param2 = assertHasParameterEdge(editor_text, fieldValue, run2);
		assertGenerated(param2);
		
		// connected by condition wire
		ConnectionNodeEditPart condWire = assertHasConditionEdge(editor_text, condition2, run);
		assertGenerated(condWire);
	}
	
	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	@Override
	public void tearDown() throws Exception {

		if (editor_text != null)
			editor_text.close(false);
		
		if (editor_page != null)
			editor_page.close(false);
		
		super.tearDown();
	}

	/**
	 * Override the default method to print out which children are
	 * actually present in the given editor.
	 */
	@Override
	public void assertEditorHasChildren(int i, DiagramDocumentEditor sub) {
		try {
			super.assertEditorHasChildren(i, sub);
		} catch (AssertionFailedError e) {
			String children = "" + sub.getDiagramEditPart().getChildren();
			throw new RuntimeException(children, e);
		}
	}
	
}
