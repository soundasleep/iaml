/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

/**
 * Tests instances: Selecting an initial value that doesn't exist
 * in the database throws an error.
 * 
 * @author jmwright
 *
 */
public class SelectMissing extends DatabaseCodegenTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SelectMissing.class);
		initialiseDatabase();
	}
	
	@Override
	protected String getDatabaseName() {
		return "output/model_12109331eea_1083.db";
	}

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE User (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(64) NOT NULL, email VARCHAR(64) NOT NULL, password VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO User (id, name, email, password) VALUES (14, 'User Default', 'default@jevon.org', 'test1')");
		return s;
	}
	
	public void testSelectMissing() throws Exception {

		try {
			beginAtSitemapThenPage("container");
			fail("We should not be able to load page 'container'");
		} catch (FailingHttpStatusCodeException e) {
			// we should instantly have an exception occur
			// expected
			checkExceptionContains(e, "Could not find any value instance for attribute");
			
			assertTextPresent("Could not find any value instance for attribute");
			assertProblem();
		}
		
	}

}
