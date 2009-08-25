/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import java.sql.ResultSet;
import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Test database connections that are wrapped with inputforms (or not)
 * 
 * @author jmwright
 *
 */
public class DatabaseWithInputForm extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(DatabaseWithInputForm.class);
	}
	
	public void testWithoutForm() throws Exception {
		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("without form");
		
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
		sitemap = beginAtSitemapThenPage("without form");
		{
			// should be the same
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, text);
		}
		
		// let's check the database
		ResultSet rs = loadDatabaseQuery("output/model_120b1f587bf_3cf.db", 
				"SELECT value1 FROM single_values;");
		assertTrue(rs.next());	// there should be one value
		
		String value = rs.getString("value1");
		assertEquals(value, text);
		
		// there should not be any more values in the database
		assertFalse(rs.next());
		
	}
	
	public void testWithForm() throws Exception {
		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("with form");
		
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
		sitemap = beginAtSitemapThenPage("with form");
		{
			// should be the same
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, text);
		}		

		// let's check the database
		ResultSet rs = loadDatabaseQuery("output/model_120b1f587bf_3cf.db", 
				"SELECT value2 FROM single_values;");
		assertTrue(rs.next());	// there should be one value
		
		String value = rs.getString("value2");
		assertEquals(value, text);
		
		// there should not be any more values in the database
		assertFalse(rs.next());

	}

}
