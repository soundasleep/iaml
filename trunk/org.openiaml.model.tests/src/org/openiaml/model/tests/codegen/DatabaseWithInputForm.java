/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.sql.ResultSet;
import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Test database connections that are wrapped with inputforms (or not)
 * 
 * @author jmwright
 *
 */
public class DatabaseWithInputForm extends CodegenTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(ROOT + "codegen/DatabaseWithInputForm.iaml");
	}
	
	public void testWithoutForm() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "without form");
		
		String text = "a changed value " + new Date().toString();
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, text);
		}
		
		// reload page
		gotoSitemapThenPage(sitemap, "without form");
		{
			// should have changed
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, text);
		}
		
		// *restart* entire session
		beginAtSitemapThenPage(sitemap, "without form");
		{
			// should be the same
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, text);
		}
		
		// let's check the database
		ResultSet rs = loadDatabaseQuery("output/model_120b1f587bf_3cf.db", 
				"SELECT * FROM single_values WHERE name='value1';");
		assertTrue(rs.next());	// there should be one value
		
		String value = rs.getString("value");
		assertEquals(value, text);
		
		// there should not be any more values in the database
		assertFalse(rs.next());
		
	}
	
	public void testWithForm() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "with form");
		
		String text = "a changed value " + new Date().toString();
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, text);
		}
		
		// reload page
		gotoSitemapThenPage(sitemap, "with form");
		{
			// should have changed
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, text);
		}
		
		// *restart* entire session
		beginAtSitemapThenPage(sitemap, "with form");
		{
			// should be the same
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, text);
		}		

		// let's check the database
		ResultSet rs = loadDatabaseQuery("output/model_120b1f587bf_3cf.db", 
				"SELECT * FROM single_values WHERE name='value2';");
		assertTrue(rs.next());	// there should be one value
		
		String value = rs.getString("value");
		assertEquals(value, text);
		
		// there should not be any more values in the database
		assertFalse(rs.next());

	}

}
