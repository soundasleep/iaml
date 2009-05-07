/**
 * 
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.diagram.part.IamlDiagramEditor;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorUtil;
import org.openiaml.model.tests.EclipseTestCaseHelper;

/**
 * Try creating a new diagram and new domain file.
 * 
 * @author jmwright
 *
 */
public class CreateNewDiagramTestCase extends EclipseTestCaseHelper {

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
		
		IWorkbenchPage page = PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage();
		assertNotNull("Workbench page not null", page);
		
		IEditorPart ep = page.getActiveEditor();
		assertNotNull("Active editor not null", ep);
		assertTrue("Active editor an IamlDiagramEditor", ep instanceof IamlDiagramEditor);
		
		IamlDiagramEditor editor = (IamlDiagramEditor) ep;
		EObject rendering = editor.getDiagramEditPart().resolveSemanticElement();
		assertNotNull("Rendering a non-null element", rendering);
		assertTrue("Rendering an InternetApplication", rendering instanceof InternetApplication);
		
		InternetApplication ia = (InternetApplication) rendering;
		// should be empty
		assertTrue("InternetApplication is empty", ia.eContents().isEmpty());
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
	
	public void setUp() throws Exception {
		super.setUp();
		
		// register errors
		// TODO should this go into a global setUp()?
		addLogListener();
	}
	
	// TODO put this into superclass
	public void tearDown() throws Exception {
		// close all editors
		PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
		
		super.tearDown();
	}
	
}
