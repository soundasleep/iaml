/**
 * 
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
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
		getProject().createProject();
	}
	
	/**
	 * When we close the test case, we should also close the project.
	 */
	@Override
	public void tearDown() throws Exception {
		getProject().close();
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
		DiagramRegistry.initializeModelFile(modelFile, diagramFile, true);
		
	}
	
	/**
	 * Copy the <code>sourceModel</code> into our workspace;
	 * {@link #initializeModelFile(IFile, IFile) initialize the model diagram};
	 * and open it as an editor.
	 * 
	 * @param sourceModel the source IFile model instance to initialize
	 * @param root the local root of the sourceModel
	 * @param targetDiagram the target diagram IFile to create
	 * @return the loaded editor instance
	 */
	protected DiagramDocumentEditor initializeAndLoad(IFile sourceModel, String root, IFile targetDiagram) throws Exception {
		// copy our local file into the project
		copyFileIntoWorkspace(root + sourceModel.getName(),
				sourceModel);
		
		assertFalse("the target diagram should not exist yet", targetDiagram.exists());

		// initialise the model
		initializeModelFile(sourceModel, targetDiagram);
		
		assertTrue("the target diagram should have been created", targetDiagram.exists());
		
		// load up the editor
		DiagramDocumentEditor editor = (DiagramDocumentEditor) loadDiagramFile(targetDiagram);
		
		return editor;
	}
	
	/**
	 * Simply wraps the parameters in getProject().getFile().
	 * @see #initializeAndLoad(IFile, String, IFile)
	 */
	protected DiagramDocumentEditor initializeAndLoad(String sourceFile, String root, String targetFile) throws Exception {
		return initializeAndLoad(getProject().getFile(sourceFile), root, getProject().getFile(targetFile));
	}
	
	/**
	 * Simply adds <code>"_diagram"</code> as the target diagram filename
	 * from sourceFile.
	 * 
	 * @see #initializeAndLoad(IFile, String, IFile)
	 */
	protected DiagramDocumentEditor initializeAndLoad(String sourceFile, String root) throws Exception {
		return initializeAndLoad(sourceFile, root, sourceFile + "_diagram");
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
		
		// make sure it isn't an error part
		checkNotErrorPart(ep);
		
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
	

	/**
	 * Before jumping into an Eclipse test, this method should be called
	 * so we can catch any exceptions that occur during loading, and
	 * print them out to the log.
	 */
	protected void addLogListener() {
		Platform.addLogListener(new ILogListener() {
			@Override
			public void logging(IStatus status, String plugin) {
				// rethrow if exception is caught
				if (status.getSeverity() == IStatus.ERROR) {
					// JUnit won't actually catch this, because the Platform is
					// in a different thread. however we will still get the
					// stack trace in System.err so this remains somewhat useful.
					//throw new RuntimeException(status.getMessage(), status.getException());
					System.err.println(status);
					status.getException().printStackTrace(System.err);
				} else {
					// otherwise just print out the error
					System.err.println(status);
				}
				setLastError(status);
			}});
	}

	private IStatus lastErrorStatus = null;

	/**
	 * When we add a log listener ({@link #addLogListener()}, this
	 * method keeps track of the last reported error, hopefully
	 * so we can add stack traces and the like.
	 *
	 * @param status the last status found
	 */
	protected void setLastError(IStatus status) {
		lastErrorStatus = status;
	}

	/**
	 * Check that the given part is not an ErrorPart.
	 * If it is, see whatever status was thrown last ({@link #setLastError(IStatus)},
	 * and throw an exception so we can trace it.
	 *
	 * @param part
	 */
	protected void checkNotErrorPart(IEditorPart part) {
		// we cannot directly 'instanceof', because ErrorEditorPart is
		// in an internal package
		if (part.getClass().getSimpleName().equals("ErrorEditorPart")) {
			if (lastErrorStatus == null) {
				throw new RuntimeException("Loaded part is an ErrorEditorPart (no exception): " + part);
			} else {
				throw new RuntimeException(lastErrorStatus.getMessage() + ": " + part, lastErrorStatus.getException());
			}
		}
	}

}
