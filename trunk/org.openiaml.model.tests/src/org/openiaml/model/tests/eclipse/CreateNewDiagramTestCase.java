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
import org.openiaml.model.inference.EcoreCreateElementsHelper;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.diagram.part.IamlDiagramEditor;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorUtil;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.EclipseTestCaseHelper;

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
		
		// there should be 0 elements in this editor
		assertEquals("there should be 0 children", 0, editor.getDiagramEditPart().getChildren().size());
		
		root = (InternetApplication) rendering;
		// should be empty
		assertTrue("InternetApplication is empty", root.eContents().isEmpty());
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

		// create a DomainStore
		DomainStore ds = gmf.createDomainStore(root);
		assertNotNull(ds);
		
		// and a Page
		Page page = gmf.createPage(root);
		assertNotNull(page);
		
		// there should be two elements in this editor
		assertEquals("there should be 2 children", 2, editor.getDiagramEditPart().getChildren().size());
		
		// add another page
		Page page2 = gmf.createPage(root);
		assertNotNull(page2);
		
		// create a SyncWire between the two
		SyncWire sync = gmf.createSyncWire(root, page, page2);
		assertNotNull(sync);

		// there should be three elements in this editor
		assertEquals("there should be 3 children", 3, editor.getDiagramEditPart().getChildren().size());
	}
	
	public void testCreatingSubEditor() throws Exception {
		// create a blank editor
		testCreateBlank();
		EcoreCreateElementsHelper gmf = getElementCreator();
		
		// we should be in the root editor
		assertEditorRoot(editor);

		// create a Page
		Page page = gmf.createPage(root);
		assertNotNull(page);

		// set its name
		gmf.setName(page, "page1");
		assertEquals("Page name should have changed", page.getName(), "page1");
		
		// there should be one element in this editor
		assertEquals("there should be 1 children", 1, editor.getDiagramEditPart().getChildren().size());
		
		// find the edit part for the page
		ShapeNodeEditPart pageNode = assertHasPage(editor, "page1");

		// open the page
		DiagramDocumentEditor pageEditor = openDiagram(pageNode);
		assertNotNull(pageEditor);
		assertEditorVisual(pageEditor);
		
		// there shouldn't be anything here
		assertEquals("there should be 0 children", 0, pageEditor.getDiagramEditPart().getChildren().size());
		
		// close this editor
		((org.openiaml.model.model.diagram.visual.part.IamlDiagramEditor) pageEditor).closeBlocking(false);
		
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
		return URI.createPlatformResourceURI(project.getFile(filename).getFullPath().toString(), true);
	}
	
}
