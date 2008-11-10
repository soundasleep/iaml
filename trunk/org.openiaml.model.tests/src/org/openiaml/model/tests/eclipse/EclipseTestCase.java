/**
 * 
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.diagram.part.IamlDiagramEditor;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorUtil;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.tests.ModelTestCase;

/**
 * Test the Eclipse editor.
 * 
 * @author jmwright
 *
 */
public class EclipseTestCase extends ModelTestCase {

	/**
	 * Create a project.
	 * 
	 * @throws CoreException
	 * @see {@link #createProject()} 
	 */
	public void setUp() throws CoreException {
		// create the project
		project = createProject();
	}
	
	/**
	 * Make sure the Eclipse UI exists and is running.
	 */
	public void testRunning() {
		assertTrue(PlatformUI.isWorkbenchRunning());
	}

	/**
	 * Tests loading the model file with the editor.
	 * 
	 * @throws Exception
	 */
	public void testLoadModel() throws Exception {
		// copy our local file into the project
		IFile targetModel = project.getFile("generation-sync-multiple.iaml");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/generation-sync-multiple.iaml",
				targetModel);
		IFile targetDiagram = project.getFile("generation-sync-multiple.iaml_diagram");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/generation-sync-multiple.iaml_diagram",
				targetDiagram);
		
		// try loading it up with Eclipse
		ResourceSet resSet = new ResourceSetImpl();          
		Resource res = resSet.getResource(URI.createPlatformResourceURI(targetDiagram.getFullPath().toString(), false), true);
		assertTrue("can load new editor", IamlDiagramEditorUtil.openDiagram( res ));
		
		// get the active workbench editor part
		// based on IamlDiagramEditorUtil#openDiagram()
		IWorkbenchPage page = PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage();
		IEditorPart ep = page.getActiveEditor();
		assertNotNull("has active editor part", ep);
		// if this is actually an ErrorEditPart, then an error has occured 
		// (but it may not be obvious in the log what it is)
		assertTrue("active editor is our plugin, but is " + ep, ep instanceof IamlDiagramEditor);
		
		// find what elements are displayed
		IamlDiagramEditor editor = (IamlDiagramEditor) ep;

		// there should be four children
		assertEquals("there should only be 4 children", 4, editor.getDiagramEditPart().getChildren().size());
		
		// see what they all are
		ShapeNodeEditPart page1 = null;
		ShapeNodeEditPart page2 = null;
		ShapeNodeEditPart store = null;
		ShapeNodeEditPart page4 = null;

		for (Object o : editor.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof Page) {
					Page p = (Page) obj;
					if (p.getName().equals("page1"))
						page1 = s;
					if (p.getName().equals("page2"))
						page2 = s;
					if (p.getName().equals("last signup user"))
						page4 = s;
				}
				if (obj instanceof DomainStore) {
					if (((DomainStore) obj).getName().equals("domainStore")) {
						store = s;
					}
				}
			} else {
				fail("unknown child part type: " + o);
			}
		}
		
		// make sure they all exist
		assertNotNull("page1 exists", page1);
		assertNotNull("page2 exists", page2);
		assertNotNull("domain store exists", store);
		assertNotNull("last signup user exists", page4);

		// try opening up the domain store
		// based on org.eclipse.gef.tools.SelectEditPartTracker#performOpen()
		SelectionRequest request = new SelectionRequest();
		request.setLocation(store.getLocation());
		request.setModifiers(0 /*getCurrentInput().getModifiers()*/);
		request.setType(RequestConstants.REQ_OPEN);
		store.performRequest(request);

		// we should have loaded up a new editor
		IWorkbenchPage page9 = PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage();
		IEditorPart ep2 = page9.getActiveEditor();
		assertNotNull("has active editor part", ep2);
		// if this is actually an ErrorEditPart, then an error has occured 
		// (but it may not be obvious in the log what it is)
		assertTrue("active editor is our plugin, but is " + ep2, ep2 instanceof IamlDiagramEditor);

		
	}

	
}
