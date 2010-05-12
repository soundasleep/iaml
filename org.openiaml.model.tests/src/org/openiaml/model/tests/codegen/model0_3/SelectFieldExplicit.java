/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

/**
 * Tests instances: Selecting an explicit query (no arguments)
 * 
 * @author jmwright
 *
 */
public class SelectFieldExplicit extends DatabaseCodegenTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SelectFieldExplicit.class);
		initialiseDatabase();
	}

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE User (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(64) NOT NULL, email VARCHAR(64) NOT NULL, password VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO User (id, name, email, password) VALUES (14, 'User Default', 'default@jevon.org', 'test1')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (24, 'User Two', 'target@jevon.org', 'test2')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (44, 'User Three', 'explicit@jevon.org', 'test3')");
		s.add("INSERT INTO User (id, name, email, password) VALUES (84, 'User Four', 'test4@jevon.org', 'test4')");
		return s;
	}
	
	public void testSelectExplicit() throws Exception {

		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("container");
		
		// there should NOT be a field called 'edit name'
		assertLabelNotPresent("edit name");
		
		// there should be a text field 'editname'
		String edit = getLabelIDForText("edit email");
		
		// it should have the initial explicit value
		assertLabeledFieldEquals(edit, "explicit@jevon.org");
		
		// change the field
		String newValue = "a new value@jevon.org";
		setLabeledFormElementField(edit, newValue);
		
		// the value should remain
		setLabeledFormElementField(edit, newValue);
		
		// reload page, it should be stored
		reloadPage(sitemap, "container");		
		{
			String field2 = getLabelIDForText("edit email");
			assertLabeledFieldEquals(field2, newValue);
		}
		
		// *restart* session, it should be stored
		restartSession(sitemap, "container");
		{
			String field2 = getLabelIDForText("edit email");
			assertLabeledFieldEquals(field2, newValue);
		}
		
		// check the database
		// autosave is on, so it should have been pushed to the database
		{
			ResultSet rs = executeQuery("SELECT * FROM User WHERE id=44");
			assertTrue(rs.next());
			assertEquals(newValue, rs.getString("email"));
			assertEquals("User Three", rs.getString("name"));
			assertFalse(rs.next());
		}
		
		// make sure it hasn't changed other values
		{
			ResultSet rs = executeQuery("SELECT * FROM User WHERE id=84");
			assertTrue(rs.next());
			assertEquals("User Four", rs.getString("name"));
			assertFalse(rs.next());
		}
		
	}

}
