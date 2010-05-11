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
 * Tests creating new instances in the root.
 * 
 * @author jmwright
 *
 */
public class NewInstanceObject extends DatabaseCodegenTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(NewInstanceObject.class);
		initialiseDatabase();
	}

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE User (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(64), email VARCHAR(64), password VARCHAR(64))");
		s.add("INSERT INTO User (id, name, email, password) VALUES (12, 'User Default', 'default@jevon.org', 'test1')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (22, 'User Two', 'target@jevon.org', 'test2')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (42, 'User Three', 'test3@jevon.org', 'test3')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (82, 'User Four', 'test4@jevon.org', 'test4')");
		return s;
	}
	
	/**
	 * Test creating a new instance.
	 *  
	 * @throws Exception
	 */
	public void testNewInstance() throws Exception {

		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("container");
		
		// there should be a text field 'editname'
		String field = getLabelIDForText("edit new instance name");
		
		// it should be empty
		assertLabeledFieldEquals(field, "");
		
		// if we change it, this value should be stored between pages		
		String newValue = "a new value " + new Date().toString();
		setLabeledFormElementField(field, newValue);
		assertLabeledFieldEquals(field, newValue);
		
		// reload page, it should be stored
		reloadPage(sitemap, "container");		
		{
			String field2 = getLabelIDForText("edit new instance name");
			assertLabeledFieldEquals(field2, newValue);
		}
		
		// *restart* session, it should still be stored
		restartSession(sitemap, "container");
		{
			String field2 = getLabelIDForText("edit new instance name");
			assertLabeledFieldEquals(field2, newValue);
		}
		
		// check the database
		{
			ResultSet rs = executeQuery("SELECT * FROM User WHERE name='" + newValue + "'");
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


	/**
	 * Since this is contained within the page, there should only
	 * be one instance created.
	 *  
	 * @throws Exception
	 */
	public void testOnlyOneNewInstance() throws Exception {

		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("container");
		
		// there should be a text field 'editname'
		String field = getLabelIDForText("edit new instance name");
		
		// it should be empty
		assertLabeledFieldEquals(field, "");
		
		// if we change it, this value should be stored between pages		
		String newValue = "a new value " + new Date().toString();
		setLabeledFormElementField(field, newValue);
		assertLabeledFieldEquals(field, newValue);
		
		// reload page, it should be stored
		reloadPage(sitemap, "container");		
		{
			String field2 = getLabelIDForText("edit new instance name");
			assertLabeledFieldEquals(field2, newValue);
		}
		
		// *restart* session, it should still be stored
		restartSession(sitemap, "container");
		{
			String field2 = getLabelIDForText("edit new instance name");
			assertLabeledFieldEquals(field2, newValue);
		}
		
		// we change the value; this should *overwrite* the old value
		String newValue2 = "a second value " + new Date().toString();
		setLabeledFormElementField(field, newValue2);
		assertLabeledFieldEquals(field, newValue2);
		
		// check the database: the first value should not exist (since we changed it)
		{
			ResultSet rs = executeQuery("SELECT * FROM User WHERE name='" + newValue + "'");
			assertFalse(rs.next());
		}

		// check the database: the second value should exist
		{
			ResultSet rs = executeQuery("SELECT * FROM User WHERE name='" + newValue2 + "'");
			assertTrue(rs.next());
			assertEquals(newValue2, rs.getString("name"));
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
