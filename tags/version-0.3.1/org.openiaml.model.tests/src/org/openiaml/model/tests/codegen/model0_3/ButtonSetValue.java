/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests buttons can set values.
 * 
 * @author jmwright
 *
 */
public class ButtonSetValue extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(ButtonSetValue.class);
	}
	
	/**
	 * Explicitly call the update operation.
	 * 
	 * @throws Exception
	 */
	public void testExplicitCopy() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "container");
		
		// there should be two buttons here
		assertButtonPresentWithText("copy value over to target");
		assertButtonPresentWithText("reset target");
		
		// and two text fields
		String source = getLabelIDForText("source one");
		String target = getLabelIDForText("target one");
		
		// set the source value
		String text = "buttonSetValue explicit " + new Date().toString();
		setLabeledFormElementField(source, text);
		
		// the target should not have changed yet
		assertLabeledFieldNotEquals(target, text);
		
		// but now we click the button
		clickButtonWithText("copy value over to target");
		
		// and now it should have changed
		assertLabeledFieldEquals(target, text);
		assertLabeledFieldEquals(source, text);
		
		// if we click the reset button
		clickButtonWithText("reset target");
		
		// the field should change to 'empty'
		assertLabeledFieldEquals(target, "empty");
		assertLabeledFieldEquals(source, text);
		
		// if we reload the page, the values should still be there
		reloadPage(sitemap, "container");
		{
			String source2 = getLabelIDForText("source one");
			String target2 = getLabelIDForText("target one");
			assertLabeledFieldEquals(source2, text);
			assertLabeledFieldEquals(target2, "empty");
		}

		// if we restart the session, the values should still be there
		restartSession(sitemap, "container");
		{
			String source2 = getLabelIDForText("source one");
			String target2 = getLabelIDForText("target one");
			assertLabeledFieldEquals(source2, text);
			assertLabeledFieldEquals(target2, "empty");
		}

	}
	
	/**
	 * Implicitly call the update operation.
	 * 
	 * @throws Exception
	 */
	public void testImplicitCopy() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "implicit");
		
		// there should be one buttons here
		assertButtonPresentWithText("copy over implicitly");
		assertButtonNotPresentWithText("reset target");
		
		// and two text fields
		String source = getLabelIDForText("source two");
		String target = getLabelIDForText("target two");
		
		// set the source value
		String text = "buttonSetValue implicit " + new Date().toString();
		setLabeledFormElementField(source, text);
		
		// the target should not have changed yet
		assertLabeledFieldNotEquals(target, text);
		
		// but now we click the button
		clickButtonWithText("copy over implicitly");
		
		// and now it should have changed
		assertLabeledFieldEquals(target, text);
		assertLabeledFieldEquals(source, text);
		
		// if we reload the page, the values should still be there
		reloadPage(sitemap, "implicit");
		{			
			assertTitleEquals("implicit");
			String source2 = getLabelIDForText("source two");
			String target2 = getLabelIDForText("target two");
			assertLabeledFieldEquals(source2, text);
			assertLabeledFieldEquals(target2, text);
		}

		// if we restart the session, the values should still be there
		restartSession(sitemap, "implicit");
		{
			assertTitleEquals("implicit");
			String source2 = getLabelIDForText("source two");
			String target2 = getLabelIDForText("target two");
			assertLabeledFieldEquals(source2, text);
			assertLabeledFieldEquals(target2, text);
		}
	}
	
}
