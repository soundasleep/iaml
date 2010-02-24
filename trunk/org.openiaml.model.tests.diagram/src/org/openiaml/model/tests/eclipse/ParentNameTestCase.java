/**
 *
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.diagram.custom.commands.GmfInferenceHandler;
import org.openiaml.model.diagram.edit.parts.CompositeOperationEditPart.ExtendedCompositeOperationFigure;
import org.openiaml.model.diagram.edit.parts.EventTriggerEditPart.ExtendedEventTriggerFigure;
import org.openiaml.model.diagram.part.IamlDiagramEditor;
import org.openiaml.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.diagram.part.IamlDiagramEditorUtil;
import org.openiaml.model.inference.EcoreCreateElementsHelper;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.visual.Frame;

/**
 * Tests that editors that have elements with "parents" rendering
 * enabled actually render them.
 *
 * TODO currently this only tests the Root editor; in the future this should
 * test all other editors.
 *
 * @author jmwright
 *
 */
public class ParentNameTestCase extends EclipseTestCaseHelper {

	private IamlDiagramEditor editor;
	private InternetApplication root;

	/**
	 * Try simply creating a brand new editor with no files in it.
	 * TODO move into superclass
	 *
	 * @throws Exception
	 */
	public void testCreateBlank() throws Exception {
		Resource r = IamlDiagramEditorUtil.createDiagram(
				createProjectURI("blank.iaml_diagram"),
				createProjectURI("blank.iaml"),
				new NullProgressMonitor());

		assertNotNull(r);

		boolean opened = IamlDiagramEditorUtil.openDiagram(r);
		assertTrue("Editor opened", opened);

		IEditorPart ep = getActiveEditor();
		assertTrue("Active editor an IamlDiagramEditor", ep instanceof IamlDiagramEditor);

		editor = (IamlDiagramEditor) ep;
		EObject rendering = editor.getDiagramEditPart().resolveSemanticElement();
		assertNotNull("Rendering a non-null element", rendering);
		assertTrue("Rendering an InternetApplication", rendering instanceof InternetApplication);

		// there should be 1 elements in this editor: a generated page (issue 89)
		assertEditorHasChildren(1, editor);

		root = (InternetApplication) rendering;
		assertEquals(0, root.getChildren().size());
		assertEquals(1, root.getScopes().size());
		Frame page = (Frame) root.getScopes().get(0);
		assertEquals("Home", page.getName());

		// should not be empty
		assertFalse("InternetApplication is not empty", root.eContents().isEmpty());
	}

	/**
	 * Get the currently open editor.
	 * TODO move into superclass
	 *
	 * @return The currently open editor part.
	 */
	protected IEditorPart getActiveEditor() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		assertNotNull("Workbench page not null", page);

		IEditorPart ep = page.getActiveEditor();
		assertNotNull("Active editor not null", ep);

		return ep;
	}

	/**
	 * Try creating a new diagram, and then add elements to it.
	 *
	 * @throws Exception
	 */
	public void testRootParentNames() throws Exception {
		// create a blank editor
		testCreateBlank();
		EcoreCreateElementsHelper gmf = getElementCreator();

		// set the root name
		gmf.setName(root, "root element");
		assertEquals(root.getName(), "root element");

		// create a CompositeOperation
		CompositeOperation cop = gmf.createCompositeOperation(root);
		assertNotNull(cop);

		// set name
		gmf.setName(cop, "my operation");
		assertEquals(cop.getName(), "my operation");

		// find the edit part for this element
		ShapeNodeEditPart part = assertHasOperation(editor, "my operation");
		assertNotNull(part);

		// what is the content pane?
		IFigure fig = part.getContentPane();
		assertTrue("CompositeOperation should be extended: " + fig.getClass(),
				fig instanceof org.openiaml.model.diagram.edit.parts.CompositeOperationEditPart.ExtendedCompositeOperationFigure);

		ExtendedCompositeOperationFigure ext = (ExtendedCompositeOperationFigure) fig;
		// check to see it has the correct initial parent value
		assertEquals(ext.getFigureCompositeOperationParentNameFigure().getText(), "root element");

		// change the root name
		gmf.setName(root, "a new parent name");
		assertEquals(root.getName(), "a new parent name");
		// the parent label should have changed
		assertEquals(ext.getFigureCompositeOperationParentNameFigure().getText(), "a new parent name");
	}

	/**
	 * Try multiple elements.
	 * TODO refactor!!
	 *
	 * @throws Exception
	 */
	public void testRootParentNamesMultiple() throws Exception {
		// create a blank editor
		testCreateBlank();
		EcoreCreateElementsHelper gmf = getElementCreator();

		// set the root name
		gmf.setName(root, "root element");
		assertEquals(root.getName(), "root element");

		// create a CompositeOperation
		CompositeOperation cop = gmf.createCompositeOperation(root);
		assertNotNull(cop);

		// set name
		gmf.setName(cop, "my operation");
		assertEquals(cop.getName(), "my operation");

		// create some EventTriggers
		EventTrigger event1 = gmf.createEventTrigger(root);
		assertNotNull(event1);
		gmf.setName(event1, "event");
		assertEquals(event1.getName(), "event");

		EventTrigger event2 = gmf.createEventTrigger(root);
		assertNotNull(event2);
		gmf.setName(event2, "event 2");
		assertEquals(event2.getName(), "event 2");

		assertNotSame(event1, event2);

		// find the edit part for this element
		ShapeNodeEditPart partOp = assertHasOperation(editor, "my operation");
		ShapeNodeEditPart event1Op = assertHasEventTrigger(editor, "event");
		ShapeNodeEditPart event2Op = assertHasEventTrigger(editor, "event 2");
		assertNotNull(partOp);
		assertNotNull(event1Op);
		assertNotNull(event2Op);

		// what is the content pane?
		IFigure fig_o = partOp.getContentPane();
		assertTrue("CompositeOperation should be extended: " + fig_o.getClass(),
				fig_o instanceof org.openiaml.model.diagram.edit.parts.CompositeOperationEditPart.ExtendedCompositeOperationFigure);

		ExtendedCompositeOperationFigure ext_o = (ExtendedCompositeOperationFigure) fig_o;
		// check to see it has the correct initial parent value
		assertEquals(ext_o.getFigureCompositeOperationParentNameFigure().getText(), "root element");

		IFigure fig_e1 = event1Op.getContentPane();
		assertTrue("EventTrigger 1 should be extended: " + fig_e1.getClass(),
				fig_e1 instanceof org.openiaml.model.diagram.edit.parts.EventTriggerEditPart.ExtendedEventTriggerFigure);

		ExtendedEventTriggerFigure ext_e1 = (ExtendedEventTriggerFigure) fig_e1;
		// check to see it has the correct initial parent value
		assertEquals(ext_e1.getFigureEventTriggerParentNameFigure().getText(), "root element");

		IFigure fig_e2 = event2Op.getContentPane();
		assertTrue("EventTrigger 2 should be extended: " + fig_e2.getClass(),
				fig_e2 instanceof org.openiaml.model.diagram.edit.parts.EventTriggerEditPart.ExtendedEventTriggerFigure);

		ExtendedEventTriggerFigure ext_e2 = (ExtendedEventTriggerFigure) fig_e2;
		// check to see it has the correct initial parent value
		assertEquals(ext_e2.getFigureEventTriggerParentNameFigure().getText(), "root element");

		// change the root name
		gmf.setName(root, "a new parent name");
		assertEquals(root.getName(), "a new parent name");

		// the parent labels should have changed
		assertEquals(ext_o.getFigureCompositeOperationParentNameFigure().getText(), "a new parent name");
		assertEquals(ext_e1.getFigureEventTriggerParentNameFigure().getText(), "a new parent name");
		assertEquals(ext_e2.getFigureEventTriggerParentNameFigure().getText(), "a new parent name");
	}

	/**
	 * Check that elements that shouldn't have parent names (e.g. Pages)
	 * do NOT have them (or are extended).
	 *
	 * TODO refactor!!
	 *
	 * @throws Exception
	 */
	public void testRootParentNamesNot() throws Exception {
		// create a blank editor
		testCreateBlank();
		EcoreCreateElementsHelper gmf = getElementCreator();

		// set the root name
		gmf.setName(root, "root element");
		assertEquals(root.getName(), "root element");

		// create a Frame
		Frame page = gmf.createFrame(root);
		assertNotNull(page);

		// set name
		gmf.setName(page, "my page");
		assertEquals(page.getName(), "my page");

		// find the edit part for this element
		ShapeNodeEditPart part = assertHasFrame(editor, "my page");
		assertNotNull(part);

		// what is the content pane?
		IFigure fig = part.getContentPane();
		assertEquals("Frame should NOT be extended.",
				fig.getClass().getSimpleName(),
				"FrameFigure");
	}

	/**
	 * Returns an object that we will use to create elements in the editor.
	 * TODO put into superclass
	 *
	 * @see GmfInferenceHandler
	 * @return an object creation helper
	 */
	protected EcoreCreateElementsHelper getElementCreator() {
		return new GmfInferenceHandler(
			new NullProgressMonitor(), 	// monitor
			null, // IAdaptable info
			IamlDiagramEditorPlugin.ID, // editorId
			editor.getEditingDomain()); // editingDomain
	}

	/**
	 * Construct an EMF URI from a given filename in the current
	 * project.
	 * TODO put into superclass
	 *
	 * @param filename
	 * @returns an EMF URI of the filename
	 */
	protected URI createProjectURI(String filename) {
		return URI.createPlatformResourceURI(project.getFile(filename).getFullPath().toString(), true);
	}

	@Override
	public void tearDown() throws Exception {
		if (editor != null) {
			editor.close(false);
			editor = null;
		}

		root = null;

		super.tearDown();
	}

}
