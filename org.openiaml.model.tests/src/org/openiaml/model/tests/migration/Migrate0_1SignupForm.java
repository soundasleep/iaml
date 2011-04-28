/**
 * 
 */
package org.openiaml.model.tests.migration;

import java.io.File;
import java.util.List;

import org.openiaml.model.migrate.IamlModelMigrator;
import org.openiaml.model.migrate.Migrate0To1;
import org.openiaml.model.migrate.Migrate2To4;
import org.openiaml.model.migrate.Migrate4To5;
import org.openiaml.model.model.InternetApplication;

/**
 * Tests migrating a very old model version, from 0.0 to 0.1.
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class Migrate0_1SignupForm extends AbstractMigrateTestCaseWithWarnings {
	
	/**
	 * Test to see which migrators were actually used.
	 * 
	 * @throws Exception
	 */
	public void testMigratorsUsed() throws Exception {
		List<IamlModelMigrator> used = migrateModelOnly();
		assertClassIn(Migrate0To1.class, used);
		assertClassIn(Migrate2To4.class, used);
		assertClassIn(Migrate4To5.class, used);
	}
	
	@Override
	public String getModel() {
		return "signup-form-0_1.iaml";
	}

	@Override
	public File getExpectedWarningsFile() {
		return new File("src/org/openiaml/model/tests/migration/Migrate0_1Warnings.txt");
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.migration.AbstractMigrateTestCase#migratedModelTests(org.openiaml.model.model.InternetApplication)
	 */
	@Override
	public void migratedModelTests(InternetApplication root) throws Exception {
		assertHasFrame(root, "SignupForm");
		
	}
	
}
