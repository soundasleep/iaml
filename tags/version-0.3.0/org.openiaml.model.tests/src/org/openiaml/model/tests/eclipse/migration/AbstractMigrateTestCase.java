/**
 * 
 */
package org.openiaml.model.tests.eclipse.migration;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.IEditorPart;
import org.openiaml.model.diagram.custom.actions.MigrateModelAction;
import org.openiaml.model.diagram.custom.migrate.IamlModelMigrator;
import org.openiaml.model.model.diagram.part.IamlDiagramEditor;
import org.openiaml.model.tests.EclipseTestCaseHelper;

/**
 * Testing the migration functionality.
 * 
 * It tests the migration, loading the model into EMF, initialising the
 * GMF diagram, and then investigating the rendered diagram to make sure
 * the elements are all there.
 * 
 * TODO in the future we could add migration tests to ensure that
 * openable elements can still be opened, etc.
 * 
 * Based on ShortcutsTestCase.
 * 
 * @see #getModel()
 * @see #migrateModel()
 * @author jmwright
 *
 */
public abstract class AbstractMigrateTestCase extends EclipseTestCaseHelper {

	public static final String ROOT = "src/org/openiaml/model/tests/eclipse/migration/";
	
	public IamlDiagramEditor editor;

	private IFile sourceModel;
	private IFile targetModel;
	private IFile targetDiagram;
	
	/**
	 * Only migrate the model; do not initialise the diagram.
	 * 
	 * @see #migrateModel()
	 * @throws Exception
	 */
	public List<IamlModelMigrator> migrateModelOnly() throws Exception {
		// copy our local file into the project
		sourceModel = project.getFile(getModel());
		copyFileIntoWorkspace(ROOT + getSourceModel(),
				sourceModel);
		targetModel = project.getFile(getModelMigrated());
		targetDiagram = project.getFile(getDiagram());
		
		// migrate the model
		assertFalse("the target model should not exist yet", targetModel.exists());
		MigrateModelAction a = new MigrateModelAction();
		IStatus status = a.migrateModel(sourceModel, targetModel, new NullProgressMonitor());
		assertStatusOK(status);
		assertTrue("the target model should have been created", targetModel.exists());
		
		// return list of migrators used
		return a.getMigratorsUsed();
	}
	
	/**
	 * Migrate the model file from the model given in {@link #getModel()}.
	 * 
	 * Uses {@link #migrateModelOnly()} to actually migrate the file.
	 * 
	 * This isn't placed in setUp() as {@link #testRunning()} was failing
	 * from files being created from before.
	 * 
	 * @see #migrateModelOnly()
	 * @throws Exception
	 */
	public List<IamlModelMigrator> migrateModel() throws Exception {
		List<IamlModelMigrator> used = migrateModelOnly();
		
		// initialise the diagram
		assertFalse("the target diagram should not exist yet", targetDiagram.exists());
		initializeModelFile(targetModel, targetDiagram);
		assertTrue("the target diagram should have been created", targetDiagram.exists());

		// load up the editor
		IEditorPart ep = loadDiagramFile(targetDiagram);

		// if this is actually an ErrorEditPart, then an error has occured 
		// (but it may not be obvious in the log what it is)
		assertEditorRoot(ep);
		
		// find what elements are displayed
		editor = (IamlDiagramEditor) ep;
		
		// return list of migrators used
		return used;
	}
	
	/**
	 * Usually this will just return {@link #getModel()}, but
	 * if the source model is stored in a different place
	 * in our testing environment, we will have to override this.
	 * 
	 * @see #getModel()
	 * @return The location of the source model in the testing environment
	 */
	public String getSourceModel() {
		return getModel();
	}

	/**
	 * Assert that the IStatus is ok.
	 * 
	 * @param status
	 * @throws Exception if there was a Throwable in the IStatus
	 */
	protected void assertStatusOK(IStatus status) throws Exception {
		if (!status.isOK()) {
			if (status.getException() != null) {
				// rethrow
				throw new RuntimeException(status.getMessage(), status.getException());
			}
			
			if (status.isMultiStatus()) {
				// build up the message to alert the developer
				MultiStatus ms = (MultiStatus) status;
				StringBuffer msg = new StringBuffer();
				msg.append("Status was not OK: [" + status.getPlugin() + "] " + status.getMessage());
				for (IStatus s : ms.getChildren()) {
					msg.append("\n").append(s.getMessage());
				}
				fail(msg.toString());
			}
			
			// default fail
			fail("Status was not OK: [" + status.getPlugin() + "] " + status.getMessage());
		}
	}

	/**
	 * @return getModel() + "_diagram"
	 */
	public String getDiagram() {
		return getModelMigrated() + "_diagram";
	}

	/**
	 * Close loaded editors.
	 * @throws Exception 
	 */
	public void tearDown() throws Exception {
		if (editor != null)
			editor.close(false);

		super.tearDown();
	}
	
	/**
	 * What model do we want to migrate?
	 * @return
	 */
	public abstract String getModel();
	
	/**
	 * What file do we want to migrate the model to?
	 * By default, this takes the value from {@link #getModel()}
	 * and appends "-migrated", i.e. "foo.iaml" becomes
	 * "foo-migrated".
	 * 
	 * @return
	 */
	public String getModelMigrated() {
		String file = getModel().replace(".iaml", "-migrated.iaml");
		assertNotSame(file, getModel());	// sanity check
		return file;
	}

	protected IFile getTargetModel() {
		return targetModel;
	}

	protected IFile getTargetDiagram() {
		return targetDiagram;
	}

}
