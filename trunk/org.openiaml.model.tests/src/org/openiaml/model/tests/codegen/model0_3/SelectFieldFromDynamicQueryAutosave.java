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
 * Tests instances: Selecting only one field from any property, but
 * the property has "autosave" set to false, so it does not save
 * unless we call "save" operation on it.
 * 
 * @author jmwright
 *
 */
public class SelectFieldFromDynamicQueryAutosave extends DatabaseCodegenTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SelectFieldFromDynamicQueryAutosave.class);
		initialiseDatabase();
	}
	
	/**
	 * Override this method so we can try and do instrumentation/code
	 * coverage.
	 * 
	 * Unfortunately this doesn't work yet, because it appears the runtime
	 * test environment uses pre-compiled templates. We need some way to
	 * reload the templates manually, but this is too much work.
	 * So, instead we will use {@link org.openiaml.model.codegen.php.coverage.RunInstrumentation} to
	 * manually instrument the code.
	 * 
	 * @see org.openiaml.model.codegen.php.coverage.InstrumentOawCode
	 */
	/*
	protected IStatus doTransformOAW_old(String filename, String outputDir,
			IProgressMonitor monitor) throws FileNotFoundException,
			CoreException {
		try {
			File dir = new File("../org.openiaml.model.codegen.php/src/template");
			InstrumentOawCode inst = new InstrumentOawCode();
			
			// we need to do some refreshing
			refreshProject();
			
			try {
				// instrument
				inst.preInstrumentTemplates(dir, ROOT + "instrument/");
				
				return super.doTransformOAW(filename, outputDir, monitor);
			} finally {
				// revert
				inst.postInstrumentTemplates(dir);
			}
		} catch (InstrumentationException e) {
			// wrap as a runtime exception and throw, because we
			// can't directly throw it from this method
			throw new RuntimeException(e);
		}
	}
	*/

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
	 * Selecting the default value.
	 * Check that it doesn't automatically save when we change it.
	 *  
	 * @throws Exception
	 */
	public void testSelectDefaultNoSave() throws Exception {

		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("container");
		
		// there should be a text field 'editname'
		String field = getLabelIDForText("edit name");
		
		// it should have the first (and only) value in the database
		assertLabeledFieldEquals(field, "User Default");
		
		// if we change it, this value should NOT be stored between pages		
		String newValue = "a new value " + new Date().toString();
		setLabeledFormElementField(field, newValue);
		assertLabeledFieldEquals(field, newValue);
		
		// if we reload the page, it is still there (since it persists in the Frame)
		reloadPage(sitemap, "container");		
		{
			String field2 = getLabelIDForText("edit name");
			assertLabeledFieldEquals(field2, newValue);
		}
		
		// if we restart the entire session, we have reverted back to the initial value
		// (since it is only persisting within the given Session)
		restartSession(sitemap, "container");		
		{
			String field2 = getLabelIDForText("edit name");
			assertLabeledFieldEquals(field2, "User Default");
		}
		
		// check the database
		{
			ResultSet rs = executeQuery("SELECT * FROM User WHERE id=12");
			assertTrue(rs.next());
			assertEquals("User Default", rs.getString("name"));
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
	 * Selecting the default value.
	 * Check that it saves when we click the "save" button.
	 *  
	 * @throws Exception
	 */
	public void testSelectDefaultSave() throws Exception {

		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("container");
		
		// there should be a text field 'editname'
		String field = getLabelIDForText("edit name");
		
		// it should have the first (and only) value in the database
		assertLabeledFieldEquals(field, "User Default");
		
		// if we change it, this value should NOT be stored between pages		
		String newValue = "a new value " + new Date().toString();
		setLabeledFormElementField(field, newValue);
		assertLabeledFieldEquals(field, newValue);
		
		// but now we click the button
		assertButtonPresentWithText("manually save");
		clickButtonWithText("manually save");
		
		// the value is still there
		assertLabeledFieldEquals(field, newValue);
		
		// if we reload the page, it has not changed
		reloadPage(sitemap, "container");		
		{
			String field2 = getLabelIDForText("edit name");
			assertLabeledFieldEquals(field2, newValue);
		}
		
		// *restart* session,  it should not have changed
		restartSession(sitemap, "container");
		{
			String field2 = getLabelIDForText("edit name");
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
	 * Change the value of the parameter.
	 * Check that it doesn't automatically save when we change it.
	 *  
	 * @throws Exception
	 */
	public void testSelectDynamicNoSave() throws Exception {

		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("container");
		
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
		assertLabeledFieldEquals(field, "User Two");
		
		// if we change it, this value should NOT stored between pages		
		String newValue = "another name change " + new Date().toString();
		setLabeledFormElementField(field, newValue);
		assertLabeledFieldEquals(field, newValue);
		
		// if we reload the page, it is still there (since it persists in the Frame)
		reloadPage(sitemap, "container");		
		{
			String field2 = getLabelIDForText("edit name");
			assertLabeledFieldEquals(field2, newValue);
		}
		
		// if we restart the entire session, we have reverted back to the initial value
		// (since it is only persisting within the given Session)
		// (and the query parameter has also defaulted back)  
		restartSession(sitemap, "container");
		{
			String field2 = getLabelIDForText("edit name");
			assertLabeledFieldEquals(field2, "User Default");
		}
		
		// but if we set the parameter back to 'target@jevon.org', we will get the original name
		setLabeledFormElementField(select, "target@jevon.org");
		assertLabeledFieldEquals(field, "User Two");
		
		// check the database
		{
			ResultSet rs = executeQuery("SELECT * FROM User WHERE id=22");
			assertTrue(rs.next());
			assertEquals("target@jevon.org", rs.getString("email"));
			assertEquals("User Two", rs.getString("name"));
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
	 * Change the value of the parameter.
	 * Check that it saves when we click the "save" button.
	 *  
	 * @throws Exception
	 */
	public void testSelectDynamicSave() throws Exception {

		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("container");
		
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
		assertLabeledFieldEquals(field, "User Two");
		
		// if we change it, this value should NOT stored between pages		
		String newValue = "another name change " + new Date().toString();
		setLabeledFormElementField(field, newValue);
		assertLabeledFieldEquals(field, newValue);
		
		// but now we click the button
		assertButtonPresentWithText("manually save");
		clickButtonWithText("manually save");
		
		// the value is still there
		assertLabeledFieldEquals(field, newValue);

		// reload page, it should be stored
		reloadPage(sitemap, "container");		
		{
			String field2 = getLabelIDForText("edit name");
			assertLabeledFieldEquals(field2, newValue);
		}
		
		// *restart* session, it should be stored
		// (but the query parameter has also defaulted back)  
		restartSession(sitemap, "container");
		{
			String field2 = getLabelIDForText("edit name");
			assertLabeledFieldEquals(field2, "User Default");
		}
		
		// if we set the parameter back to 'target@jevon.org', we will get the new name
		setLabeledFormElementField(select, "target@jevon.org");
		assertLabeledFieldEquals(field, newValue);
		
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
