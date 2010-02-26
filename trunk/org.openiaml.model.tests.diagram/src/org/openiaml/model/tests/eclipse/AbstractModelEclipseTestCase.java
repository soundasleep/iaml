/**
 * 
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.openiaml.model.custom.actions.IErrorLogger;
import org.openiaml.model.custom.actions.InferEntireModelAction;
import org.openiaml.model.custom.actions.ProgressEnabledAction;
import org.openiaml.model.diagram.part.IamlDiagramEditor;

/**
 * Sets up and tears down shortcut test cases.
 * 
 * @author jmwright
 *
 */
public abstract class AbstractModelEclipseTestCase<T> extends EclipseTestCaseHelper {

	/**
	 * Get the root of the model files.
	 * 
	 * @return
	 */
	public abstract String getRoot();
	
	public static final String ROOT = null;	// should not be used
	
	public IamlDiagramEditor editor;
	
	/**
	 * Initialise the model file from the model given in {@link #getModel()},
	 * initialise the diagram, and open it.
	 * 
	 * This isn't placed in setUp() as {@link #testRunning()} was failing
	 * from files being created from before.
	 *
	 * This method does not infer the diagram, like {@link #initializeModelFile(boolean)} might.
	 * 
	 * @throws Exception
	 */
	protected void initializeModelFile() throws Exception {
		initializeModelFile(false);
	}
	
	/**
	 * Perform complete inference on the target IFile model.
	 * 
	 * @param targetModel
	 * @param action the action to perform
	 * @throws CoreException 
	 */
	protected void inferSourceModelFile(IFile targetModel, ProgressEnabledAction<IFile> action) throws CoreException {
		
		action.setErrorHandler(new IErrorLogger() {

			/**
			 * Since we are in a test case, we want to get
			 * errors immediately.
			 */
			@Override
			public void logError(String message, Throwable e) {
				throw new RuntimeException(message, e);
			}

			@Override
			public void logError(String message) {
				throw new RuntimeException(message);
			}

			@Override
			public void log(IStatus multi) {
				throw new RuntimeException(multi.getMessage(), multi.getException());
			}
			
		});
		
		action.selectionChanged(null, new StructuredSelection(targetModel));
		action.run(null);
	}

	/**
	 * Perform complete inference on the target IFile model.
	 * 
	 * @param targetModel
	 * @param inferContainedElementsAction 
	 * @throws CoreException 
	 */
	protected void inferSourceModelFile(IFile targetModel) throws CoreException {
		inferSourceModelFile(targetModel, new InferEntireModelAction());
	}

	/**
	 * Initialise the model file from the model given in {@link #getModel()},
	 * possibly infer the contained elements, initialise the diagram, and open it.
	 * 
	 * This isn't placed in setUp() as {@link #testRunning()} was failing
	 * from files being created from before.
	 * 
	 * @param inferModel should we infer the complete model as well before initialising the diagram?
	 * @throws Exception
	 */
	protected void initializeModelFile(boolean inferModel) throws Exception {
		// copy our local file into the project
		copyLocalFile();
		IFile targetModel = getProject().getFile(getModel());
		IFile targetDiagram = getProject().getFile(getDiagram());
		
		// should we infer the model?
		if (inferModel) {
			inferSourceModelFile(targetModel);
		}
		
		// initialise and load the diagram file
		initialiseAndLoadDiagram(targetModel, targetDiagram);
	}
	
	/**
	 * Copy the target model ({@link #getModel()} into the current
	 * workspace.
	 * 
	 * @throws Exception 
	 * 
	 */
	protected void copyLocalFile() throws Exception {
		IFile targetModel = getProject().getFile(getModel());
		copyFileIntoWorkspace(getRoot() + getModel(), targetModel);
	}

	/**
	 * Initialise and load the diagram file, from the target model file.
	 * @throws Exception 
	 */
	protected void initialiseAndLoadDiagram(IFile targetModel, IFile targetDiagram) throws Exception {
		// initialise diagram
		assertFalse("the target diagram should not exist yet", targetDiagram.exists());
		initializeModelFile(targetModel, targetDiagram);
		assertTrue("the target diagram should have been created", targetDiagram.exists());

		// load up the editor
		IEditorPart ep = loadDiagramFile(targetDiagram);

		// if this is actually an ErrorEditPart, then an error has occured 
		// (but it may not be obvious in the log what it is)
		assertEditorRoot((DiagramDocumentEditor) ep);
		
		// find what elements are displayed
		editor = (IamlDiagramEditor) ep;
	}
	
	/**
	 * Run a given action against a given element part as the selection.
	 * 
	 * @param action
	 * @param element
	 */
	public void runAction(ProgressEnabledAction<T> action, T element) {
		
		action.setErrorHandler(new IErrorLogger() {

			/**
			 * Since we are in a test case, we want to get
			 * errors immediately.
			 */
			@Override
			public void logError(String message, Throwable e) {
				throw new RuntimeException(message, e);
			}

			@Override
			public void logError(String message) {
				throw new RuntimeException(message);
			}

			@Override
			public void log(IStatus multi) {
				throw new RuntimeException(multi.getMessage(), multi.getException());
			}
			
		});
		
		action.selectionChanged(null, new StructuredSelection(element));
		action.run(null);
	}

	/**
	 * @return getModel() + "_diagram"
	 */
	public String getDiagram() {
		return getModel() + "_diagram";
	}

	/**
	 * Get the model file that we will load in this test case.
	 * @return
	 */
	public abstract String getModel();

	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	@Override
	public void tearDown() throws Exception {
		if (editor != null)
			editor.close(false);

		super.tearDown();
	}

}
