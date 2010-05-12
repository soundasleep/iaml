/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

/**
 * Tests instances: Selecting a field using a query.
 * 
 * @author jmwright
 *
 */
public class SelectFieldQuery extends DatabaseCodegenTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SelectFieldQuery.class);
		initialiseDatabase();
	}

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE User (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(64) NOT NULL, email VARCHAR(64) NOT NULL, password VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO User (id, name, email, password) VALUES (12, 'User One', 'test1@jevon.org', 'test1')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (22, 'User Two', 'test2@jevon.org', 'test2')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (42, 'User Three', 'test3@jevon.org', 'test3')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (82, 'User Four', 'test4@jevon.org', 'test4')");
		return s;
	}

	/**
	 * Make sure the database contains the expected data.
	 */
	public void testDatabase() throws Exception {
		ResultSet rs = executeQuery("SELECT * FROM User WHERE id=42");
		assertTrue(rs.next());
		assertEquals(rs.getString("name"), "User Three");
		assertFalse(rs.next());
	}
	
	/**
	 * Test attribute instances, even over page reloads and
	 * session restarts.
	 * 
	 * @throws Exception
	 */
	public void testSelect() throws Exception {

		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("container");
		
		// there should be a text field 'editname'
		String field = getLabelIDForText("textfield");
		
		// it should have the first (and only) value in the database
		assertLabeledFieldEquals(field, "User Three");
		
		// if we change it, this value should be stored between pages		
		String newValue = "a new value " + new Date().toString();
		setLabeledFormElementField(field, newValue);
		assertLabeledFieldEquals(field, newValue);
		
		// reload page, it should be stored
		reloadPage(sitemap, "container");		
		{
			String field2 = getLabelIDForText("textfield");
			assertLabeledFieldEquals(field2, newValue);
		}
		
		// *restart* session, it should be stored
		restartSession(sitemap, "container");
		{
			String field2 = getLabelIDForText("textfield");
			assertLabeledFieldEquals(field2, newValue);
		}
		
		// check the database
		// autosave is on, so it should have been pushed to the database
		{
			ResultSet rs = executeQuery("SELECT * FROM User WHERE id=42");
			assertTrue(rs.next());
			assertEquals(newValue, rs.getString("name"));
			assertFalse(rs.next());
		}
		
		// make sure it hasn't changed other values
		{
			ResultSet rs = executeQuery("SELECT * FROM User WHERE id=82");
			assertTrue(rs.next());
			assertEquals("User Four", rs.getString("name"));
			assertFalse(rs.next());
		}
		
	}

}
