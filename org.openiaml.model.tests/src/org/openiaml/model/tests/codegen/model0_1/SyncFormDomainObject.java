/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_1;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Random;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Testing an entire Form connected with a SyncWire to an entire DomainObject
 * 
 * @author jmwright
 *
 */
public class SyncFormDomainObject extends CodegenTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(SyncFormDomainObject.class);
	}
	
	public void testCase() throws Exception {
		// get the DomainStore in this app
		DomainStore ds = (DomainStore) queryOne(root, "iaml:domainStores[iaml:name='domainStore1']");
		String dbName = "output/" + safeName(ds) + ".db";  

		// there should first be no database
		{
			IFile db = getProject().getFile(dbName);
			assertFalse("db '" + dbName + "' shouldn't exist", db.exists());
		}

		// we can now go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page1
		beginAtSitemapThenPage(sitemap, "page1");
		
		// the values should all be empty first
		String field1id = getLabelIDForText("field1");
		assertLabeledFieldEquals(field1id, "");
		String field2id = getLabelIDForText("field2");
		assertLabeledFieldEquals(field2id, "");
		
		// set the values of field1 and field2
		String testingText1 = new Date().toString();
		String testingText2 = "kittens " + new Random().nextInt(32768);		// TODO add input checking tests
		setLabeledFormElementField(field1id, testingText1);
		setLabeledFormElementField(field2id, testingText2);
		
		// check the values have been saved
		assertLabeledFieldEquals(field1id, testingText1);
		assertLabeledFieldEquals(field2id, testingText2);
		
		// reload the page
		gotoSitemapThenPage(sitemap, "page1");

		// the values should still be the same
		assertLabeledFieldEquals(field1id, testingText1);
		assertLabeledFieldEquals(field2id, testingText2);
				
		// check the database
		{
			ResultSet rs = loadDatabaseQuery(dbName, "SELECT * FROM form1;");
			assertTrue(rs.next());	// there should be one value
			
			String field1 = rs.getString("field1");
			String field2 = rs.getString("field2");
			String field3 = rs.getString("field3");

			// it should be our testing text
			assertEquals(testingText1, field1);
			assertEquals(testingText2, field2);
			assertNotSame(testingText1, field3);	// it shouldn't be either of these
			assertNotSame(testingText2, field3);	// it shouldn't be either of these

			// there should not be any more values in the database
			assertFalse(rs.next());

		}
		
	}

}
