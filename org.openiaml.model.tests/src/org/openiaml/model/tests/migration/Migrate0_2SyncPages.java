/**
 * 
 */
package org.openiaml.model.tests.migration;

import java.util.List;

import org.openiaml.model.migrate.IamlModelMigrator;
import org.openiaml.model.migrate.Migrate0To1;
import org.openiaml.model.migrate.Migrate1To2;
import org.openiaml.model.migrate.Migrate4To5;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;

/**
 * Tests migrating a very old model version, from 0.1 to 0.2.
 * 
 * @see #getModel()
 * @author jmwright
 *
 */
public class Migrate0_2SyncPages extends AbstractMigrateTestCase {

	/**
	 * Test to see which migrators were actually used.
	 * 
	 * @throws Exception
	 */
	public void testMigratorsUsed() throws Exception {
		List<IamlModelMigrator> used = migrateModelOnly();
		assertClassNotIn(Migrate0To1.class, used);
		assertClassIn(Migrate1To2.class, used);
		assertClassIn(Migrate4To5.class, used);
	}
	
	@Override
	public String getModel() {
		return "codegen-sync-pages-0_2.iaml";
	}
	
	/*
	 * We don't expect there to be any warnings, so we
	 * don't override {@link #assertStatusOK(IStatus)}.
	 */

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.migration.AbstractMigrateTestCase#migratedModelTests(org.openiaml.model.model.InternetApplication)
	 */
	@Override
	public void migratedModelTests(InternetApplication root) throws Exception {
		// check the contents
		Frame page1 = assertHasFrame(root, "page1");
		Frame page2 = assertHasFrame(root, "page2");
		assertHasFrame(root, "page3");
		assertHasFrame(root, "page4");
		assertHasFrame(root, "page5");

		// 2 text fields
		assertEquals(typeSelect(page1.getChildren(), InputTextField.class).size(), 2);
		assertHasInputTextField(page1, "text1");
		assertHasInputTextField(page1, "text2");

		// 2 text fields
		assertEquals(typeSelect(page2.getChildren(), InputTextField.class).size(), 2);
		assertHasInputTextField(page2, "text1");
		assertHasInputTextField(page2, "text3");
	}
	
}
