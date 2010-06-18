/**
 *
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.diagram.custom.commands.GmfInferenceHandler;
import org.openiaml.model.diagram.part.IamlDiagramEditor;
import org.openiaml.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.diagram.part.IamlDiagramEditorUtil;
import org.openiaml.model.inference.EcoreCreateElementsHelper;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.wires.SyncWire;

/**
 * Try creating a new diagram and new domain file.
 *
 * @author jmwright
 *
 */
public class CreateNewDiagramTestCase extends EclipseTestCaseHelper {

	private IamlDiagramEditor editor;
	private InternetApplication root;

	/**
	 * Try simply creating a brand new editor with no files in it.
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
		assertEquals(0, root.getElements().size());
		assertEquals(1, root.getScopes().size());
		Frame page = (Frame) root.getScopes().get(0);
		assertEquals("Home", page.getName());

		// should not be empty
		assertFalse("InternetApplication is not empty", root.eContents().isEmpty());
	}

	/**
	 * Get the currently open editor.
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
	public void testCreatingElements() throws Exception {
		// create a blank editor
		testCreateBlank();
		EcoreCreateElementsHelper gmf = getElementCreator();

		// initially just the default page
		assertEditorHasChildren(1, editor);

		// create a DomainStore
		DomainSchema ds = gmf.createDomainSchema(root);
		assertNotNull(ds);

		// and a Frame
		Frame page = gmf.createFrame(root);
		assertNotNull(page);

		// there should be three elements in this editor
		assertEditorHasChildren(3, editor);

		// add another frame
		Frame page2 = gmf.createFrame(root);
		assertNotNull(page2);

		// create a SyncWire between the two
		SyncWire sync = gmf.createSyncWire(root, page, page2);
		assertNotNull(sync);

		// there should be four elements in this editor
		assertEditorHasChildren(4, editor);
	}

	public void testCreatingSubEditor() throws Exception {
		// create a blank editor
		testCreateBlank();
		EcoreCreateElementsHelper gmf = getElementCreator();

		// we should be in the root editor
		assertEditorRoot(editor);

		// initially just the initial page
		assertEditorHasChildren(1, editor);

		// create a Page
		Frame page = gmf.createFrame(root);
		assertNotNull(page);

		// set its name
		gmf.setName(page, "page1");
		assertEquals("Page name should have changed", page.getName(), "page1");

		// there should be one element in this editor
		assertEditorHasChildren(2, editor);

		// find the edit part for the page
		ShapeNodeEditPart pageNode = assertHasFrame(editor, "page1");

		// open the page
		DiagramDocumentEditor pageEditor = openDiagram(pageNode);
		assertNotNull(pageEditor);
		assertEditorFrame(pageEditor);

		// there shouldn't be anything here
		assertEditorHasChildren(0, pageEditor);

		// close this editor
		((org.openiaml.model.diagram.frame.part.IamlDiagramEditor) pageEditor).closeBlocking(false);

		// we're back in the root editor
		assertEquals(editor.getTitle(), getActiveEditor().getTitle());
		assertEquals("We're back in the root editor after closing the visual editor", getActiveEditor(), editor);
		assertEditorRoot(getActiveEditor());
	}

	/**
	 * Returns an object that we will use to create elements in the editor.
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
	 *
	 * @param filename
	 * @returns an EMF URI of the filename
	 */
	protected URI createProjectURI(String filename) {
		return URI.createPlatformResourceURI(getProject().getFile(filename).getFullPath().toString(), true);
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
