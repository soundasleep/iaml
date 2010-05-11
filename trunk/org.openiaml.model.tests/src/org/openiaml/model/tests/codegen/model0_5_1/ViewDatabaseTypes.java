/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_1;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

/**
 * Make sure that we can view database types properly, across all their
 * ranges.
 */
public class ViewDatabaseTypes extends DatabaseCodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(ViewDatabaseTypes.class);
		initialiseDatabase();
	}

	/**
	 * The home page can be accessed.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
	}
	
	public void testView() throws Exception {
		beginAtSitemapThenPage("View");
		assertNoProblem();
		
		// all the fields are present
		
		// integer
		assertLabelTextPresent("5");
		assertLabelTextExactlyPresent("5");
		
		// string
		assertLabelTextPresent("String");
		assertLabelTextExactlyPresent("String");
		
		// date
		// will be rendered in RFC 2822 format
		assertLabelTextPresent("Mon, 01 Jan 2001 01:01:01 +0000");
		assertLabelTextExactlyPresent("Mon, 01 Jan 2001 01:01:01 +0000");
		
		// address
		assertLabelTextPresent("Address");
		assertLabelTextExactlyPresent("Address");
		
		// email
		assertLabelTextPresent("test@openiaml.org");
		assertLabelTextExactlyPresent("test@openiaml.org");
		
	}
	
	/**
	 * Populate the database with twenty news items. The SelectWire
	 * only selects the first 10.
	 * 
	 * @param size
	 * @return
	 */
	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE Container (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, integer INTEGER NOT NULL, string VARCHAR(64) NOT NULL, date DATETIME NOT NULL, address VARCHAR(64) NOT NULL, email VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO Container (integer, string, date, address, email) " + 
				"VALUES (00005, 'String', '2001-1-1 1:01:01 +0000', 'Address', 'test@openiaml.org')");
		return s;
	}
	
}
