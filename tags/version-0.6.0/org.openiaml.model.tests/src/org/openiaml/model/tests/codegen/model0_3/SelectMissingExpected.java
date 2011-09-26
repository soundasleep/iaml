/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

/**
 * Tests instances: Selecting an initial value that doesn't exist
 * in the database throws an error, but we expect this error
 * will occur, and catch it accordingly.
 * 
 * @author jmwright
 *
 */
public class SelectMissingExpected extends DatabaseCodegenTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SelectMissingExpected.class);
		initialiseDatabase();
	}

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE User (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(64) NOT NULL, email VARCHAR(64) NOT NULL, password VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO User (id, name, email, password) VALUES (14, 'User Default', 'default@jevon.org', 'test1')");
		return s;
	}
	
	public void testSelectMissing() throws Exception {
		// go to sitemap
		IFile sitemap = getSitemap();
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "container", "an expected failure");

		// a problem should instantly occur, but we will be
		// redirected
		assertTitleEquals("an expected failure");
		assertTextPresent("No results found for query");
		assertProblem();
	}

}
