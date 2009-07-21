/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import java.util.Date;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Test properties files that are wrapped with inputforms (or not)
 * 
 * @author jmwright
 *
 */
public class PropertiesFileWithInputForm extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(PropertiesFileWithInputForm.class);
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
		
		// open up the properties file
		Properties p = loadProperties("output/result.properties");
		assertTrue(p.containsKey("value1"));
		assertEquals(text, p.getProperty("value1"));		
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

		// open up the properties file
		Properties p = loadProperties("output/result.properties");
		assertTrue(p.containsKey("value2"));
		assertEquals(text, p.getProperty("value2"));		

	}

}
