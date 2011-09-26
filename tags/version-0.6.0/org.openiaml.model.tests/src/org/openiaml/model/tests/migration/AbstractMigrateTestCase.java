/**
 *
 */
package org.openiaml.model.tests.migration;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.migrate.IamlModelMigrator;
import org.openiaml.model.migrate.MigrationController;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Testing the migration functionality.
 *
 * <p>It tests the migration, loading the model into EMF, initialising the
 * GMF diagram, and then investigating the rendered diagram to make sure
 * the elements are all there.
 *
 * <p>TODO in the future we could add migration tests to ensure that
 * openable elements can still be opened, etc.
 *
 * <p>Based on ShortcutsTestCase.
 *
 * @see #getModel()
 * @see #migrateModel()
 * @author jmwright
 *
 */
public abstract class AbstractMigrateTestCase extends InferenceTestCase {

	public static final String ROOT = "src/org/openiaml/model/tests/migration/";

	private IFile sourceModel;
	private IFile targetModel;
	
	/**
	 * Only migrate the model; do not initialise the diagram.
	 *
	 * @see #migrateModel()
	 * @throws Exception
	 */
	public List<IamlModelMigrator> migrateModelOnly() throws Exception {
		// copy our local file into the project
		sourceModel = getProject().getFile(getModel());
		copyFileIntoWorkspace(ROOT + getSourceModel(),
				sourceModel);
		targetModel = getProject().getFile(getModelMigrated());

		// migrate the model
		assertFalse("the target model should not exist yet", targetModel.exists());
		MigrationController a = new MigrationController();
		IStatus status = a.migrateModel(sourceModel, targetModel, new NullProgressMonitor());
		assertStatusOK(status);
		assertTrue("the target model should have been created", targetModel.exists());

		// return list of migrators used
		return a.getMigratorsUsed();
	}

	/**
	 * Translate the static method into an instance method.
	 * @param status
	 * @throws Exception
	 */
	public void assertStatusOK(IStatus status) throws Exception {
		// call the static method
		assertStatusIsOK(status);
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

	/**
	 * Tries to migrate the particular model. Will fail
	 * if the migration fails unexpectedly.
	 * 
	 * @throws Exception
	 */
	public void testModelMigration() throws Exception {
		migrateModelOnly();
		
		// now try and load the migrated model
		EObject model = loadModelDirectly(getTargetModel());
		
		// it should be an InternetApplication
		assertInstanceOf(InternetApplication.class, model);
		
		InternetApplication root = (InternetApplication) model;
		migratedModelTests(root);
	}

	/**
	 * Should any additional tests be performed on the migrated
	 * model?
	 * 
	 * @param root loaded InternetApplication after model migration
	 * @throws Exception 
	 */
	public abstract void migratedModelTests(InternetApplication root) throws Exception;
	
}
