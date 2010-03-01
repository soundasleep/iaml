/**
 *
 */
package org.openiaml.model.tests.eclipse.actions;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.openiaml.model.diagram.helpers.GeneratedElementDeleter;
import org.openiaml.model.diagram.helpers.GeneratedElementHandler;
import org.openiaml.model.model.GeneratedElement;

/**
 *
 *
 * @author jmwright
 *
 */
public class DeleteGeneratedElements extends AbstractActionTestCase<IFile> {

	@Override
	public String getModel() {
		return "DeleteGeneratedElements.iaml";
	}

	protected DiagramDocumentEditor editor_page;
	protected DiagramDocumentEditor editor_target;

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		initializeModelFile();

		// open page
		ShapeNodeEditPart page = assertHasFrame(editor, "container");
		editor_page = openDiagram(page);
		assertEditorFrame(editor_page);

		// there should be six children
		assertEditorHasChildren(6, editor_page);
		ShapeNodeEditPart target = assertHasInputTextField(editor_page, "target", false);
		assertGenerated(target);
		ShapeNodeEditPart unrelated = assertHasInputTextField(editor_page, "unrelated", false);
		assertGenerated(unrelated);

		ShapeNodeEditPart edit = assertHasEventTrigger(editor_page, true, "onEdit");
		assertGenerated(edit);
		ShapeNodeEditPart incoming = assertHasOperation(editor_page, "incoming", false);
		assertNotGenerated(incoming);
		assertHasRunInstanceWire(editor_page, edit, incoming, "run out");

		ShapeNodeEditPart outgoing = assertHasEventTrigger(editor_page, false, "onAccess");
		assertNotGenerated(outgoing);
		ShapeNodeEditPart update = assertHasOperation(editor_page, "update", true);
		assertGenerated(update);
		assertHasRunInstanceWire(editor_page, outgoing, update, "run in");

		// open the target
		//editor_target = openDiagram(target);

		// there should be 7 children (why??)
		/*
		assertEditorHasChildren(7, editor_target);
		editor_target.close(false);
		*/

		editor_target = openDiagram(unrelated);
		assertEditorHasChildren(5, editor_target);

	}

	/**
	 * Deleting an element which is being used by others should
	 * show a message box.
	 * @throws Exception
	 */
	public void testDeleteConfirm() throws Exception {
		initializeModelFile();

		// open page
		ShapeNodeEditPart page = assertHasFrame(editor, "container");
		editor_page = openDiagram(page);
		assertEditorFrame(editor_page);

		// there should be six children
		assertEditorHasChildren(6, editor_page);
		ShapeNodeEditPart target = assertHasInputTextField(editor_page, "target", false);
		assertGenerated(target);

		// sadly, I don't know how to emulate selecting the element
		// and deleting it.
		/*
		 the following code throws:

			java.lang.NullPointerException
			at org.eclipse.emf.workspace.AbstractEMFOperation.createTransaction(AbstractEMFOperation.java:447)
			at org.eclipse.emf.workspace.AbstractEMFOperation.execute(AbstractEMFOperation.java:134)
			at org.eclipse.gmf.runtime.diagram.ui.internal.actions.PromptingDeleteAction.run(PromptingDeleteAction.java:159)
			at org.openiaml.model.diagram.visual.part.IamlDiagramEditor$MyDeleteAction.run(IamlDiagramEditor.java:180)
		*/
		/*
			MyDeleteAction action = ((IamlDiagramEditor) editor_page).new MyDeleteAction(editor_page);
			action.selectionChanged(editor_page, new StructuredSelection(target));
			action.run();
		 */

		// so we are limited to unit testing the helper objects
	}

	/**
	 * Unit test the element handler.
	 *
	 * @throws Exception
	 */
	public void testHandler() throws Exception {
		initializeModelFile();

		// open page
		ShapeNodeEditPart page = assertHasFrame(editor, "container");
		editor_page = openDiagram(page);
		assertEditorFrame(editor_page);

		// there should be six children
		assertEditorHasChildren(6, editor_page);
		ShapeNodeEditPart target = assertHasInputTextField(editor_page, "target", false);
		assertGenerated(target);

		GeneratedElementHandler handler = new GeneratedElementHandler(target);
		assertTrue("we should have incoming edges", handler.needsConfirmation());
		assertEquals("There should only be one element to confirm", 1, handler.getConfirmationElements().size());
		GeneratedElement toConfirm = (GeneratedElement) handler.getConfirmationElements().get(0);

		assertEqualsOneOf(new String[] {
				"The generated element 'InputTextField 'target'' contains elements which are connected to non-generated elements:\n\n" +
				"EventTrigger onEdit\n" +
				"CompositeOperation 'update'\n\n" +
				"Deleting 'InputTextField 'target'' will also delete these generated elements, currently in use:\n\n" +
				"CompositeOperation 'update'\n" +
				"EventTrigger onEdit",

				// swap Event/Operation around
				"The generated element 'InputTextField 'target'' contains elements which are connected to non-generated elements:\n\n" +
				"CompositeOperation 'update'\n" +
				"EventTrigger onEdit\n\n" +
				"Deleting 'InputTextField 'target'' will also delete these generated elements, currently in use:\n\n" +
				"CompositeOperation 'update'\n" +
				"EventTrigger onEdit"		
		}, handler.getConfirmationMessage(toConfirm));
	}

	public void testDeletedElements() throws Exception {
		initializeModelFile();

		// open page
		ShapeNodeEditPart page = assertHasFrame(editor, "container");
		editor_page = openDiagram(page);
		assertEditorFrame(editor_page);

		// there should be six children
		assertEditorHasChildren(6, editor_page);
		ShapeNodeEditPart target = assertHasInputTextField(editor_page, "target", false);
		assertGenerated(target);

		GeneratedElementHandler handler = new GeneratedElementHandler(target);
		List<EObject> toDelete = handler.getOtherElementsToDelete();

		// there should be two other elements that are important to delete
		assertEquals(2, toDelete.size());

		ShapeNodeEditPart edit = assertHasEventTrigger(editor_page, true, "onEdit");
		assertGenerated(edit);
		ShapeNodeEditPart update = assertHasOperation(editor_page, "update", true);
		assertGenerated(update);
		assertContains(edit.resolveSemanticElement(), toDelete);
		assertContains(update.resolveSemanticElement(), toDelete);

		// get ALL related elements to also
		GeneratedElementDeleter deleter = new GeneratedElementDeleter((GeneratedElement) target.resolveSemanticElement());

		assertGreaterEq(6, deleter.getElementsToDelete().size());
	}

	/**
	 * Close loaded editors.
	 * @throws Exception
	 */
	@Override
	public void tearDown() throws Exception {

		if (editor_target != null)
			editor_target.close(false);

		if (editor_page != null)
			editor_page.close(false);

		super.tearDown();
	}

}
