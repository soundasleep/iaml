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
 * Tests instances: Selecting only one field from any property.
 * 
 * @author jmwright
 *
 */
public class SelectField extends DatabaseCodegenTestCase {
	
	private static final String FIRST_NAME = "Test User";
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SelectField.class);
		initialiseDatabase();
	}

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE User (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(64) NOT NULL, email VARCHAR(64) NOT NULL, password VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO User (id, name, email, password) VALUES (12, '" + FIRST_NAME + "', 'test@jevon.org', 'test')");
		return s;
	}

	/**
	 * Make sure the database contains the expected data.
	 */
	public void testDatabase() throws Exception {
		ResultSet rs = executeQuery("SELECT * FROM User");
		assertTrue(rs.next());
		assertEquals(rs.getString("name"), FIRST_NAME);
		assertFalse(rs.next());
	}
	
	/**
	 * Let's not try and reload/restart the web application yet;
	 * let's just access the database straight away.
	 *  
	 * @throws Exception
	 */
	public void testSelectInstant() throws Exception {

		// go to sitemap
		beginAtSitemapThenPage("container");
		
		// there should be a text field 'editname'
		String field = getLabelIDForText("editname");
		
		// it should have the first (and only) value in the database
		assertLabeledFieldEquals(field, FIRST_NAME);
		
		// if we change it, this value should be stored between pages		
		String newValue = "a new value " + new Date().toString();
		setLabeledFormElementField(field, newValue);
		assertLabeledFieldEquals(field, newValue);

		// check the database
		ResultSet rs = executeQuery("SELECT * FROM User");
		assertTrue(rs.next());
		assertEquals(newValue, rs.getString("name"));
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
		String field = getLabelIDForText("editname");
		
		// it should have the first (and only) value in the database
		assertLabeledFieldEquals(field, FIRST_NAME);
		
		// if we change it, this value should be stored between pages		
		String newValue = "a new value " + new Date().toString();
		setLabeledFormElementField(field, newValue);
		assertLabeledFieldEquals(field, newValue);
		
		// reload page, it should be stored
		reloadPage(sitemap, "container");		
		{
			String field2 = getLabelIDForText("editname");
			assertLabeledFieldEquals(field2, newValue);
		}
		
		// *restart* session, it should be stored
		restartSession(sitemap, "container");
		{
			String field2 = getLabelIDForText("editname");
			assertLabeledFieldEquals(field2, newValue);
		}
		
		ResultSet rs = executeQuery("SELECT * FROM User");
		assertTrue(rs.next());
		assertEquals(newValue, rs.getString("name"));
		assertFalse(rs.next());
		
	}

}
