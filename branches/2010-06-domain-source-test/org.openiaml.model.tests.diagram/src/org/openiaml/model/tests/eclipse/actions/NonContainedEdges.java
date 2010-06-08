/**
 *
 */
package org.openiaml.model.tests.eclipse.actions;

import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;

/**
 * A test case to check regression of issue 34: if we try to
 * create an edge from a source element which cannot directly
 * contain the element, then it should also check the containing
 * editor.
 *
 * @see #getModel()
 * @author jmwright
 *
 */
public class NonContainedEdges extends AbstractActionTestCase<GraphicalEditPart> {

	@Override
	public String getModel() {
		return "NonContainedEdges.iaml";
	}

	protected DiagramDocumentEditor editor_sub;

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		initializeModelFile();

		assertHasProperty(editor, "target property", false);
		assertHasCondition(editor, "target condition", false);
		assertHasOperation(editor, "target operation", false);

	}

	/**
	 * Check that the extra methods exist in a condition editor.
	 *
	 * @throws Exception
	 */
	public void testConditionEditor() throws Exception {

		initializeModelFile();

		ShapeNodeEditPart property = assertHasProperty(editor, "target property", false);
		ShapeNodeEditPart condition = assertHasCondition(editor, "target condition", false);

		editor_sub = openDiagram(condition);
		assertEditorCondition(editor_sub);

		// there should be two elements, one shortcut
		ShapeNodeEditPart property2 = assertHasProperty(editor_sub, "target property", true);
		ShapeNodeEditPart var = assertHasTemporaryVariable(editor_sub, "target", false);

		assertHasDataFlowEdge(editor_sub, property2, var);
		// identical elements being rendered
		assertSameReferencedElement(property, property2);
		
		// removed testing code for elements not normally exported; is it actually necessary to test?

	}

	/**
	 * Check that the extra methods exist in a operation editor.
	 *
	 * @throws Exception
	 */
	public void testOperationEditor() throws Exception {

		initializeModelFile();

		ShapeNodeEditPart property = assertHasProperty(editor, "target property", false);
		ShapeNodeEditPart operation = assertHasOperation(editor, "target operation", false);

		editor_sub = openDiagram(operation);
		assertEditorOperation(editor_sub);

		// there should be two elements, one shortcut
		ShapeNodeEditPart property2 = assertHasProperty(editor_sub, "target property", true);
		ShapeNodeEditPart var = assertHasTemporaryVariable(editor_sub, "target", false);

		assertHasDataFlowEdge(editor_sub, property2, var);
		// identical elements being rendered
		assertSameReferencedElement(property, property2);

		// removed testing code for elements not normally exported; is it actually necessary to test?

	}

	/**
	 * Close loaded editors.
	 * @throws Exception
	 */
	@Override
	public void tearDown() throws Exception {

		if (editor_sub != null)
			editor_sub.close(false);

		super.tearDown();
	}

}
