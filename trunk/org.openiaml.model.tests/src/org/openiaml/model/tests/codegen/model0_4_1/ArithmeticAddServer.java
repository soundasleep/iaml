/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_1;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Abstract classes for arithmetic tests.
 * 
 * @author jmwright
 *
 */
public class ArithmeticAddServer extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(ArithmeticAddServer.class);
	}
	
	/**
	 * Initially, the home page is set to '' + '42' = 42.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		
		beginAtSitemapThenPage("Home");
		{
			String result = getLabelIDForText("result");
			assertLabeledFieldEquals(result, "42");
		}
		assertNoProblem();
		
	}
	
	/**
	 * We change Page2.external to '43', so then the home
	 * page will change to '43' + '42' = 85.
	 * 
	 * @throws Exception
	 */
	public void testChangeParam() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Page2");
		{
			String external = getLabelIDForText("external");
			assertLabeledFieldEquals(external, "");
			setLabeledFormElementField(external, "43");
		}
		assertNoProblem();
		
		// go back to home page
		gotoSitemapThenPage(sitemap, "Home");
		{
			String result = getLabelIDForText("result");
			assertLabeledFieldEquals(result, "85");
		}
		assertNoProblem();
	
	}
	
	/**
	 * Clicking 'make negative' on Page2 will set Home.negative result to
	 * a negative value.
	 * 
	 * @throws Exception
	 */
	public void testExternalOperation() throws Exception {
		
		// initialise result to '42', since it's set as 'access'
		IFile sitemap = beginAtSitemapThenPage("Home");
		{
			String result = getLabelIDForText("result");
			assertLabeledFieldEquals(result, "42");
			
			// currently empty
			String nresult = getLabelIDForText("negative result");
			assertLabeledFieldEquals(nresult, "");
		}
		assertNoProblem();
		
		// now go to Page2
		gotoSitemapThenPage(sitemap, "Page2");
		
		// click the button
		assertButtonPresentWithText("make negative");
		clickButtonWithText("make negative");
		assertNoProblem();
		
		// go back to Home
		gotoSitemapThenPage(sitemap, "Home");
		{
			// still the same value
			String result = getLabelIDForText("result");
			assertLabeledFieldEquals(result, "42");
			// has been changed
			String nresult = getLabelIDForText("negative result");
			assertLabeledFieldEquals(nresult, "-42");
		}
		assertNoProblem();
		
	}
	
	/**
	 * Even if we change Home.result, the value is lost when we
	 * reload the page, since we have an 'access' event changing it.
	 * 
	 * @throws Exception
	 */
	public void testOnAccessRefresh() throws Exception {
		
		// initialise result to '42', since it's set as 'access'
		IFile sitemap = beginAtSitemapThenPage("Home");
		{
			String result = getLabelIDForText("result");
			assertLabeledFieldEquals(result, "42");
			
			// set it
			setLabeledFormElementField(result, "43");
		}
		assertNoProblem();
		
		// reload
		reloadPage(sitemap, "Home");
		
		// hasn't changed
		{
			String result = getLabelIDForText("result");
			assertLabeledFieldEquals(result, "42");
		}
		assertNoProblem();
		
	}
		
}
