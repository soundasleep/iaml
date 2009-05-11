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
public class SelectFieldFromDynamicQuery extends DatabaseCodegenTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SelectFieldFromDynamicQuery.class);
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
		s.add("INSERT INTO User (id, name, email, password) VALUES (12, 'User Default', 'default@jevon.org', 'test1')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (22, 'User Two', 'target@jevon.org', 'test2')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (42, 'User Three', 'test3@jevon.org', 'test3')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (82, 'User Four', 'test4@jevon.org', 'test4')");
		return s;
	}
	
	/**
	 * Test the default dynamic query parameter.
	 *  
	 * @throws Exception
	 */
	public void testSelectDefault() throws Exception {

		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "container");
		
		// there should be a text field 'editname'
		String field = getLabelIDForText("edit name");
		
		// it should have the first (and only) value in the database
		assertLabeledFieldEquals(field, "User Default");
		
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
		{
			ResultSet rs = executeQuery("SELECT * FROM User WHERE id=12");
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
	 * Test changing the value of the parameter.
	 *  
	 * @throws Exception
	 */
	public void testSelectDynamic() throws Exception {

		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "container");
		
		// there should be a text field 'editname'
		String field = getLabelIDForText("edit name");
		String select = getLabelIDForText("select email");
		
		// it should have the first (and only) value in the database
		assertLabeledFieldEquals(field, "User Default");
		// the select field should also be populated with the default value
		assertLabeledFieldEquals(select, "default@jevon.org");
		
		// change the select field
		setLabeledFormElementField(select, "target@jevon.org");
		
		// the name should change
		assertLabeledFieldEquals(select, "User Two");
		
		// if we change it, this value should be stored between pages		
		String newValue = "another name change " + new Date().toString();
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
		{
			ResultSet rs = executeQuery("SELECT * FROM User WHERE id=22");
			assertTrue(rs.next());
			assertEquals("target@jevon.org", rs.getString("email"));
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
