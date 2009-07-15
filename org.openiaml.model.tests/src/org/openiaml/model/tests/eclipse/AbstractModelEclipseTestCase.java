/**
 * 
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.core.resources.IFile;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewActionDelegate;
import org.openiaml.model.diagram.custom.actions.InferEntireModelAction;
import org.openiaml.model.model.diagram.part.IamlDiagramEditor;
import org.openiaml.model.tests.EclipseTestCaseHelper;

/**
 * Sets up and tears down shortcut test cases.
 * 
 * @author jmwright
 *
 */
public abstract class AbstractModelEclipseTestCase extends EclipseTestCaseHelper {

	/**
	 * Get the root of the model files.
	 * 
	 * @return
	 */
	public abstract String getRoot();
	
	public static final String ROOT = null;	// should not be used
	
	public IamlDiagramEditor editor;
	
	/**
	 * Initialise the model file from the model given in {@link #getModel()}.
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
	 */
	protected void inferSourceModelFile(IFile targetModel, IViewActionDelegate action) {
		action.selectionChanged(null, new StructuredSelection(targetModel));
		action.run(null);
	}

	/**
	 * Perform complete inference on the target IFile model.
	 * 
	 * @param targetModel
	 * @param inferContainedElementsAction 
	 */
	protected void inferSourceModelFile(IFile targetModel) {
		inferSourceModelFile(targetModel, new InferEntireModelAction());
	}

	/**
	 * Initialise the model file from the model given in {@link #getModel()}.
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
		IFile targetModel = project.getFile(getModel());
		IFile targetDiagram = project.getFile(getDiagram());
		
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
		IFile targetModel = project.getFile(getModel());
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
	public void tearDown() throws Exception {
		if (editor != null)
			editor.close(false);

		super.tearDown();
	}

}
