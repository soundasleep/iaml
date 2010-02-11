/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_2;

import java.util.Date;
import java.util.Random;

import junit.framework.AssertionFailedError;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests requirement 4: dynamic sources of elements
 * 
 * @model ../examples/requirements/4-dynamic_sources.iaml
 * @author jmwright
 *
 */
public class Requirement4DynamicSources extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(Requirement4DynamicSources.class, ROOT + "../examples/requirements/4-dynamic_sources.iaml", true);
	}
	
	public void testRequirement() throws Exception {
		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("page a");
		
		String test1 = "set target value " + new Date().toString();
		String test2 = "should not be set " + new Random().nextDouble();
		{
			// there should be an element called value
			String target = getLabelIDForText("target");
			assertNotNull(target);
			assertLabeledFieldEquals(target, "");
			
			// set the field
			setLabeledFormElementField(target, test1);
			
			// set another field
			String unrelated = getLabelIDForText("unrelated1");
			assertNotNull(unrelated);
			assertLabeledFieldEquals(unrelated, "");
			setLabeledFormElementField(unrelated, test2);
			
			// they should remain set
			assertLabeledFieldEquals(target, test1);
			assertLabeledFieldEquals(unrelated, test2);
		}
		
		// now if we go to FieldList, the target on this page
		// should also have changed
		waitForAjax();
		gotoSitemapThenPage(sitemap, "FieldList");
		
		{
			// there should be an element called value
			String target = getLabelIDForText("target");
			assertNotNull(target);
			assertLabeledFieldEquals(target, test1);
		}
		
		// 'page c' should also have been set
		waitForAjax();
		gotoSitemapThenPage(sitemap, "page c");
		
		{
			// there should be an element called value
			String target = getLabelIDForText("target");
			assertNotNull(target);
			assertLabeledFieldEquals(target, test1);
		}
		
		// go back to FieldList
		waitForAjax();
		gotoSitemapThenPage(sitemap, "FieldList");
		
		String test3 = "a third field change " + new Random().nextDouble();
		{
			// set target to another string
			String target = getLabelIDForText("target");
			assertNotNull(target);
			setLabeledFormElementField(target, test3);
			assertLabeledFieldEquals(target, test3);
		}

		// 'page c' should have been set
		waitForAjax();
		gotoSitemapThenPage(sitemap, "page c");
		
		{
			// there should be an element called value
			String target = getLabelIDForText("target");
			assertNotNull(target);
			assertLabeledFieldEquals(target, test3);
		}
		
		// going to the 'unrelated' page
		// it shouldn't be sync'd since it doesn't match the xpath
		waitForAjax();
		gotoSitemapThenPage(sitemap, "unrelated");
		
		String test4 = "an unrelated change " + new Random().nextDouble();
		{
			// set target to another string
			String target = getLabelIDForText("target");
			assertNotNull(target);
			assertLabeledFieldNotEquals(target, test1);
			assertLabeledFieldNotEquals(target, test2);
			assertLabeledFieldNotEquals(target, test3);
			setLabeledFormElementField(target, test4);
		}

		// 'page c' should not have changed
		waitForAjax();
		gotoSitemapThenPage(sitemap, "page c");

		try
		{
			// there should be an element called value
			String target = getLabelIDForText("target");
			assertNotNull(target);
			assertLabeledFieldNotEquals(target, test4);
			assertLabeledFieldEquals(target, test3);
		}
		catch (AssertionFailedError e) {
			System.out.println( getPageSource() );
			throw e;
		}
		
	}

}
