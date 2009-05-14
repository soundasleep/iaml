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
 * Tests creating new instances in sessions, but autosave is
 * turned off.
 * 
 * @author jmwright
 *
 */
public class NewInstanceAttributeSessionAutosave extends DatabaseCodegenTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(NewInstanceAttributeSessionAutosave.class);
		initialiseDatabase();
	}
	
	@Override
	protected String getDatabaseName() {
		return "output/model_1213cee9b4f_18.db";
	}

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE User (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(64) NOT NULL, email VARCHAR(64) NOT NULL, password VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO User (id, name, email, password) VALUES (12, 'User Default', 'default@jevon.org', 'test1')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (22, 'User Two', 'target@jevon.org', 'test2')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (42, 'User Three', 'test3@jevon.org', 'test3')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (82, 'User Four', 'test4@jevon.org', 'test4')");
		return s;
	}
	
	/**
	 * Test creating a new instance that is not automatically saved.
	 *  
	 * @throws Exception
	 */
	public void testNewInstanceNoSave() throws Exception {

		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "session page");
		
		// there should be a text field 'editname'
		String field = getLabelIDForText("edit new name");
		
		// it should be empty
		assertLabeledFieldEquals(field, "");
		
		// if we change it, this value should be stored between pages		
		String newValue = "a new value " + new Date().toString();
		setLabeledFormElementField(field, newValue);
		assertLabeledFieldEquals(field, newValue);
		
		// reload page, it should NOT be stored
		reloadPage(sitemap, "session page");
		{
			String field2 = getLabelIDForText("edit new name");
			assertLabeledFieldEquals(field2, "");
		}
		
		// *restart* session, a new instance should be created
		restartSession(sitemap, "session page");
		String field2 = getLabelIDForText("edit new name");
		assertLabeledFieldEquals(field2, "");
			
		// lets set it
		String newValue2 = "a second session value " + new Date().toString();
		setLabeledFormElementField(field, newValue2);
		assertLabeledFieldEquals(field, newValue2);

		// reload page, it should NOT be stored
		reloadPage(sitemap, "session page");		
		{
			String field3 = getLabelIDForText("edit new name");
			assertLabeledFieldEquals(field3, "");
		}

		// check the database for the first session value
		{
			ResultSet rs = executeQuery("SELECT * FROM User WHERE name='" + newValue + "'");
			assertFalse(rs.next());
		}

		// check the database for the second session value
		{
			ResultSet rs = executeQuery("SELECT * FROM User WHERE name='" + newValue2 + "'");
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
	 * Test creating a new instance that is saved when
	 * we click the button.
	 *  
	 * @throws Exception
	 */
	public void testNewInstanceSave() throws Exception {

		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "session page");
		
		// there should be a text field 'editname'
		String field = getLabelIDForText("edit new name");
		
		// it should be empty
		assertLabeledFieldEquals(field, "");
		
		// if we change it, this value should be stored between pages		
		String newValue = "a new value " + new Date().toString();
		setLabeledFormElementField(field, newValue);
		assertLabeledFieldEquals(field, newValue);
		
		// but now we click the button
		assertButtonPresentWithText("manually save");
		clickButtonWithText("manually save");
		
		// the value is still there
		assertLabeledFieldEquals(field, newValue);
		
		// reload page, it should be stored in the session
		reloadPage(sitemap, "session page");		
		{
			String field2 = getLabelIDForText("edit new name");
			assertLabeledFieldEquals(field2, newValue);
		}
		
		// *restart* session, a new instance should be created
		restartSession(sitemap, "session page");
		String field2 = getLabelIDForText("edit new name");
		assertLabeledFieldEquals(field2, "");
			
		// lets set it
		String newValue2 = "a second session value " + new Date().toString();
		setLabeledFormElementField(field, newValue2);
		assertLabeledFieldEquals(field, newValue2);

		// but now we click the button
		assertButtonPresentWithText("manually save");
		clickButtonWithText("manually save");
		
		// the value is still there
		assertLabeledFieldEquals(field, newValue2);
		
		// reload page, it still be there
		reloadPage(sitemap, "session page");		
		{
			String field3 = getLabelIDForText("edit new name");
			assertLabeledFieldEquals(field3, newValue2);
		}

		// check the database for the first session value
		{
			ResultSet rs = executeQuery("SELECT * FROM User WHERE name='" + newValue + "'");
			assertTrue(rs.next());
			assertEquals(newValue, rs.getString("name"));
			assertFalse(rs.next());
		}

		// check the database for the second session value
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
