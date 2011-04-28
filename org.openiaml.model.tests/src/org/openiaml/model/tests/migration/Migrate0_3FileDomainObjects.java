/**
 * 
 */
package org.openiaml.model.tests.migration;

import java.io.File;
import java.util.List;

import org.openiaml.model.migrate.IamlModelMigrator;
import org.openiaml.model.migrate.Migrate0To1;
import org.openiaml.model.migrate.Migrate1To2;
import org.openiaml.model.migrate.Migrate2To4;
import org.openiaml.model.migrate.Migrate4To5;
import org.openiaml.model.model.InternetApplication;

/**
 * Tests migrating a 0.2 model to 0.3: domain objects
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class Migrate0_3FileDomainObjects extends AbstractMigrateTestCaseWithWarnings {

	@Override
	public String getModel() {
		return "FileDomainObjects.iaml";
	}
	
	/**
	 * TODO put this into an abstract method to generate 
	 * migrated names by default
	 */
	@Override
	public String getModelMigrated() {
		return "FileDomainObjects-migrated.iaml";
	}

	/*
	 * We don't expect there to be any warnings, so we
	 * don't override {@link #assertStatusOK(IStatus)}.
	 */
	
	/**
	 * Test to see which migrators were actually used.
	 * 
	 * @throws Exception
	 */
	public void testMigratorsUsed() throws Exception {
		List<IamlModelMigrator> used = migrateModelOnly();
		assertClassNotIn(Migrate0To1.class, used);
		assertClassNotIn(Migrate1To2.class, used);
		assertClassIn(Migrate2To4.class, used);
		assertClassIn(Migrate4To5.class, used);
	}

	@Override
	public File getExpectedWarningsFile() {
		return new File("src/org/openiaml/model/tests/migration/Migrate0_3Warnings.txt");
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.migration.AbstractMigrateTestCase#migratedModelTests(org.openiaml.model.model.InternetApplication)
	 */
	@Override
	public void migratedModelTests(InternetApplication root) throws Exception {
		// TODO currently empty
	}
	
}
