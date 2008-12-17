/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Test just a single SyncWire with a InputTextField to a DomainAttribute
 * 
 * @author jmwright
 *
 */
public class SyncFieldDomainAttribute extends CodegenTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(ROOT + "codegen/SyncFieldDomainAttribute.iaml");
	}
	
	/**
	 * Make sure that the database is initially empty (and can be loaded 
	 * through SQLite). It then goes into the only page in this 
	 * InternetApplication and changes its value. It then checks to make
	 * sure this value persists over multiple page refreshes, and
	 * exists in the database.
	 */
	public void testDatabaseIsEmpty() throws Exception {
		// get the DomainStore in this app
		DomainStore ds = (DomainStore) queryOne(root, "iaml:domainStores[iaml:name='store']");
		String dbName = "output/" + safeName(ds) + ".db";  

		// there should first be no database
		{
			IFile db = getProject().getFile(dbName);
			assertFalse("db '" + dbName + "' shouldn't exist", db.exists());
		}

		// we can now go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		// go to "page" to initialise the db/application
		beginAtSitemapThenPage(sitemap, "page");

		// refresh the workspace
		assertTrue(refreshProject().isOK());
		
		// the database should now exist 
		IFile db = getProject().getFile(dbName);
		assertTrue("db '" + dbName + "' should exist now that we have initialised the application", db.exists());

		// make sure the database is empty
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + db.getLocation());
			Statement stat = conn.createStatement();
	
			ResultSet rs = stat.executeQuery("SELECT attribute FROM domain;");
			assertFalse(rs.next());	// it should be empty
		}
		
		// we should now be able to fill in a field
		// and the result will stay the same when we reload the page
		beginAtSitemapThenPage(sitemap, "page");
		String fieldID = getLabelIDForText("single-text-field");
		String testingText = new Date().toString();
		
		// it should start out as empty
		assertLabeledFieldEquals(fieldID, "");
		
		// set it
		setLabeledFormElementField(fieldID, testingText);
		
		// reload the page and make sure it's the same
		// TODO add reloadPage() to JWebUnit
		beginAtSitemapThenPage(sitemap, "page");
		assertLabeledFieldEquals(fieldID, testingText);
		
		// and again
		beginAtSitemapThenPage(sitemap, "page");
		assertLabeledFieldEquals(fieldID, testingText);

		// check the database
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + db.getLocation());
			Statement stat = conn.createStatement();
	
			ResultSet rs = stat.executeQuery("SELECT attribute FROM domain;");
			assertTrue(rs.next());	// there should be one value
			
			// it should be our testing text
			assertEquals(rs.getString("attribute"), testingText);
			
			// there should not be any more values in the database
			assertFalse(rs.next());
		}
		
	}

}
