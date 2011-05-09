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
import org.openiaml.model.model.ModelPackage;

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
			ShapeNodeEditPart access = assertHasEvent(editor_page, false, ModelPackage.eINSTANCE.getAccessible_OnAccess());
			assertGenerated(access);
			ShapeNodeEditPart init = assertHasEvent(editor_page, false, ModelPackage.eINSTANCE.getScope_OnInit());
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
		ShapeNodeEditPart access = assertHasEvent(editor_page, false, ModelPackage.eINSTANCE.getAccessible_OnAccess());
		assertGenerated(access);
		ShapeNodeEditPart init = assertHasEvent(editor_page, false,ModelPackage.eINSTANCE.getScope_OnInit());
		assertGenerated(init);
		
		// in the fully inferred file, there should be lots of children
		editor_text = openDiagram(text1);
		
		checkFullInferenceEditor();
		
	}
	
	/**
	 * Check the editor to make sure it's been _partially_ inferred.
	 */
	protected void checkPartialInferenceEditor() {
		checkPartialInferenceEditor(16);		// 16 to begin with
	}

	/**
	 * Check the editor to make sure it's been _partially_ inferred.
	 */
	protected void checkPartialInferenceEditor(int expectedChildren) {
		assertEditorVisual(editor_text);
		
		assertEditorHasChildren(expectedChildren, editor_text);
		
		ShapeNodeEditPart access = assertHasEvent(editor_text, false, ModelPackage.eINSTANCE.getAccessible_OnAccess());
		ShapeNodeEditPart edit = assertHasEvent(editor_text, false, ModelPackage.eINSTANCE.getChangeable_OnChange());
		ShapeNodeEditPart update = assertHasOperation(editor_text, "update", false);
		ShapeNodeEditPart init = assertHasOperation(editor_text, "init", false);
		ShapeNodeEditPart fieldValue = assertHasFieldValue(editor_text, false);
		ShapeNodeEditPart condition = assertHasPrimitiveCondition(editor_text, "fieldValue is set", false);
		
		// model 0.4.4
		ShapeNodeEditPart label = assertHasLabel(editor_text, "Warning", false);
		ShapeNodeEditPart canCast = assertHasPrimitiveCondition(editor_text, "can cast?", false);

		// model 0.5.1
		ShapeNodeEditPart currentInput = assertHasValue(editor_text, "currentInput", false);
		ShapeNodeEditPart condition2 = assertHasCompositeCondition(editor_text, "currentInput is set", false);
		ShapeNodeEditPart notEmpty = assertHasPrimitiveCondition(editor_text, "not empty", false);
		ShapeNodeEditPart empty = assertHasPrimitiveCondition(editor_text, "empty", false);
		ShapeNodeEditPart show = assertHasPrimitiveOperation(editor_text, "show", false);
		ShapeNodeEditPart hide = assertHasPrimitiveOperation(editor_text, "hide", false);
		ShapeNodeEditPart onClick = assertHasEvent(editor_text, null, false, ModelPackage.eINSTANCE.getVisibleThing_OnClick());
		ShapeNodeEditPart onInput = assertHasEvent(editor_text, null, false, ModelPackage.eINSTANCE.getVisibleThing_OnInput());

		// all generated
		assertGenerated(access);
		assertGenerated(edit);
		assertGenerated(update);
		assertGenerated(init);
		assertGenerated(fieldValue);
		assertGenerated(condition);
		
		// model 0.4.4
		assertGenerated(label);
		assertGenerated(canCast);
		
		// model 0.5.1
		assertGenerated(currentInput);
		assertGenerated(condition2);
		assertGenerated(notEmpty);
		assertGenerated(empty);
		assertGenerated(show);
		assertGenerated(condition);
		assertGenerated(hide);
		assertGenerated(onClick);
		assertGenerated(onInput);
		
		// connected by run wire
		ConnectionNodeEditPart run = assertHasECARule(editor_text, access, init, "run");
		assertGenerated(run);
	}
	
	/**
	 * Check the editor to make sure it's been _fully_ inferred.
	 */
	protected void checkFullInferenceEditor() {
		assertEditorVisual(editor_text);
		
		// use checkPartialInferenceEditor() to check the base elements; should not have changed 
		checkPartialInferenceEditor(22);
		
		ShapeNodeEditPart condition2 = assertHasPrimitiveCondition(editor_text, "fieldValue is set", true);

		ShapeNodeEditPart edit2 = assertHasEvent(editor_text, true, ModelPackage.eINSTANCE.getChangeable_OnChange());
		ShapeNodeEditPart update2 = assertHasOperation(editor_text, "update", true);
		ShapeNodeEditPart fieldValue2 = assertHasFieldValue(editor_text, true);

		// model 0.4.4
		ShapeNodeEditPart label2 = assertHasLabel(editor_text, "Warning", true);
		ShapeNodeEditPart canCast2 = assertHasPrimitiveCondition(editor_text, "can cast?", true);

		// all generated
		assertGenerated(edit2);
		assertGenerated(update2);
		assertGenerated(fieldValue2);
		
		// model 0.4.4
		assertGenerated(label2);
		assertGenerated(canCast2);
		
		// additional connections on previously contained elements
		ShapeNodeEditPart access = assertHasEvent(editor_text, false, ModelPackage.eINSTANCE.getAccessible_OnAccess());
		ShapeNodeEditPart edit = assertHasEvent(editor_text, false, ModelPackage.eINSTANCE.getChangeable_OnChange());
		ShapeNodeEditPart init = assertHasOperation(editor_text, "init", false);
		ShapeNodeEditPart fieldValue = assertHasFieldValue(editor_text, false);
		
		// connected by run wire
		ConnectionNodeEditPart run = assertHasECARule(editor_text, access, init, "run");
		assertGenerated(run);
		ConnectionNodeEditPart param = assertHasParameter(editor_text, fieldValue2, run);
		assertGenerated(param);
		
		// connected by run wire
		ConnectionNodeEditPart run2 = assertHasECARule(editor_text, edit, update2, "run");
		assertGenerated(run2);
		ConnectionNodeEditPart param2 = assertHasParameter(editor_text, fieldValue, run2);
		assertGenerated(param2);
		
		// connected by condition wire
		ConnectionNodeEditPart condWire = assertHasSimpleCondition(editor_text, condition2, run);
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
			StringBuffer buf = new StringBuffer();
			for (Object o : sub.getDiagramEditPart().getChildren()) {
				buf.append(o).append('\n');
			}
			throw new RuntimeException(buf.toString(), e);
		}
	}
	
}
