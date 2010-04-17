/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import java.util.Date;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.EmfToDot;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Test properties files that are wrapped with inputforms (or not)
 * 
 * @author jmwright
 *
 */
public class PropertiesFileWithInputForm extends CodegenTestCase {
	
	/**
	 * TODO Exports the initial model, and the inferred model, into DOT format. 
	 * 
	 * @throws Exception
	 */
	public void testExportDot() throws Exception {
		
		EmfToDot dot = new EmfToDot(); 
		root = loadDirectly(PropertiesFileWithInputForm.class);

		EObject root2 = loadAndInfer(PropertiesFileWithInputForm.class);
		System.out.println(dot.toDot(root, root2));
		
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(PropertiesFileWithInputForm.class);
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
		
		// open up the properties file
		Properties p = loadProperties("output/result.properties");
		assertTrue(p.containsKey("value1"));
		assertEquals(text, p.getProperty("value1"));		
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

		// open up the properties file
		Properties p = loadProperties("output/result.properties");
		assertTrue(p.containsKey("value2"));
		assertEquals(text, p.getProperty("value2"));		

	}

}
