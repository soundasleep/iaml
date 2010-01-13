/**
 * 
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.diagram.custom.helpers.DiagramRegistry;
import org.openiaml.model.diagram.part.IamlDiagramEditorUtil;
import org.openiaml.model.diagram.part.IamlInitDiagramFileAction;
import org.openiaml.model.diagram.part.IamlNewDiagramFileWizard;
import org.openiaml.model.tests.ModelInferenceTestCase;
import org.openiaml.model.tests.ModelTestCase;

/**
 * Eclipse testing functionality.
 * 
 * @author jmwright
 *
 */
public abstract class EclipseTestCase extends ModelTestCase {

	/**
	 * Create a project.
	 * 
	 * @throws CoreException
	 * @throws Exception 
	 * @see {@link #createProject()} 
	 */
	@Override
	public void setUp() throws Exception {
		// create the project
		project = createProject();
	}
	
	/**
	 * When we close the test case, we should also close the project.
	 */
	@Override
	public void tearDown() throws Exception {
		project.close(monitor);
	}
	
	/**
	 * Make sure the Eclipse UI exists and is running.
	 */
	public void testRunning() {
		assertTrue(PlatformUI.isWorkbenchRunning());
	}

	/**
	 * Try opening a sub-diagram.
	 * based on org.eclipse.gef.tools.SelectEditPartTracker#performOpen()
	 * 
	 * @see org.eclipse.gef.tools.SelectEditPartTracker#performOpen()
	 * @param sourcePart
	 * @return
	 */
	protected DiagramDocumentEditor openDiagram(EditPart sourcePart) throws Exception {
		assertNotNull(sourcePart);
		
		// based on org.eclipse.gef.tools.SelectEditPartTracker#performOpen()
		SelectionRequest request = new SelectionRequest();
		request.setLocation(null);		// the location isn't actually required
		request.setModifiers(0 /*getCurrentInput().getModifiers()*/);
		request.setType(RequestConstants.REQ_OPEN);
		sourcePart.performRequest(request);

		// we should have loaded up a new editor
		IWorkbenchPage activePage = PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage();
		IEditorPart editor = activePage.getActiveEditor();
		assertNotNull("has active editor part", editor);
		
		assertTrue("active editor is not an DiagramDocumentEditor, but is a " + editor, editor instanceof DiagramDocumentEditor);
		
		return (DiagramDocumentEditor) editor;
		
	}
	
	/**
	 * Initialise a model file from a source file.
	 * 
	 * @see IamlNewDiagramFileWizard#performFinish
	 * @see IamlInitDiagramFileAction#run
	 * @param modelFile must exist
	 * @param diagramFile must not exist yet
	 */
	protected void initializeModelFile(IFile modelFile, IFile diagramFile) throws Exception {
		
		// we now ask the DiagramRegistry to initialise the diagram for us (and open it)
		DiagramRegistry.initializeModelFile(modelFile, diagramFile);
		
	}

	/**
	 * Load a diagram file in Eclipse. Makes sure that an active editor part is loaded.
	 * 
	 * @param diagramFile
	 * @returns the active editor part
	 * @throws Exception
	 */
	protected IEditorPart loadDiagramFile(IFile diagramFile) throws Exception {
		// try loading it up with Eclipse
		ResourceSet resSet = new ResourceSetImpl();          
		Resource res = resSet.getResource(URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), false), true);
		assertTrue("can load new editor", IamlDiagramEditorUtil.openDiagram( res ));
		
		// get the active workbench editor part
		// based on IamlDiagramEditorUtil#openDiagram()
		IWorkbenchPage page = PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage();
		IEditorPart ep = page.getActiveEditor();
		assertNotNull("has active editor part", ep);
		
		return ep;

	}
	
	/**
	 * Assert that the IStatus is ok.
	 * 
	 * @param status
	 * @throws Exception if there was a Throwable in the IStatus
	 */
	public void assertStatusOK(IStatus status) throws Exception {
		ModelInferenceTestCase.assertStatusIsOK(status);
	}
	
}
