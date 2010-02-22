/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_2;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Showing how to simplify the ExitGate requirements using
 * Model Completion.
 * 
 * @author jmwright
 * @example ExitGate 
 * 		If a {@model ExitGate} is connected by a {@model NavigateWire} called "last", 
 * 		then the target {@model Page} must be viewed at least once before proceeding.
 * @implementation ExitGate,NavigateWire
 * 		If a {@model ExitGate} is connected by a {@model NavigateWire} called "last", 
 * 		then the target {@model Page} must be viewed at least once before proceeding.
 */
public class ExitGateAdSimple extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(ExitGateAdSimple.class);
	}
	
	/**
	 * The home page is empty.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
	}
	
	/**
	 * We can access the external page normally.
	 * 
	 * @throws Exception
	 */
	public void testExternal() throws Exception {
		
		beginAtSitemapThenPage("External Page");
		assertNoProblem();
		
	}
	
	/**
	 * If we enter the session, we have to view the ad
	 * page before we can exit it.
	 * 
	 * @throws Exception
	 */
	public void testSessionExit() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Page 1");
		assertNoProblem();
		
		// but now if we try to visit External Page, we get
		// redirected to Advertisement
		gotoSitemapThenPage(sitemap, "External Page", "Advertisement");
		assertNoProblem();
		
		// but now if we try to access External Page, it is OK
		gotoSitemapThenPage(sitemap, "External Page", "External Page");
		assertNoProblem();
		
	}
	
	/**
	 * Once the ad is viewed once, it persists until the session
	 * is over.
	 *  
	 * @throws Exception
	 */
	public void testSessionRemains() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Page 2");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "External Page", "Advertisement");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "External Page", "External Page");
		assertNoProblem();
		
		// go to page 2
		gotoSitemapThenPage(sitemap, "Page 3");
		assertNoProblem();

		gotoSitemapThenPage(sitemap, "Home", "Home");
		assertNoProblem();

	}
	
	/**
	 * If we start at the advertisement directly, we can exit
	 * without a problem.
	 * 
	 * @throws Exception
	 */
	public void testStartOnAd() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Advertisement");
		assertNoProblem();
		
		// but now if we try to access External Page, it is OK
		gotoSitemapThenPage(sitemap, "External Page", "External Page");
		assertNoProblem();
		
	}
	
	/**
	 * If we view the ad directly, we can exit without any problems.
	 *  
	 * @throws Exception
	 */
	public void testViewAdDirectly() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Page 2");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "Advertisement");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "External Page");
		assertNoProblem();
		
		// go to page 2
		gotoSitemapThenPage(sitemap, "Page 3");
		assertNoProblem();

		gotoSitemapThenPage(sitemap, "Home");
		assertNoProblem();

	}
	
	/**
	 * We can resume the navigation once the condition is satisfied.
	 *  
	 * @throws Exception
	 */
	public void testResume() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Page 2");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "External Page", "Advertisement");
		assertNoProblem();
		
		assertButtonPresentWithText("Continue");
		clickButtonWithText("Continue");
		
		assertTitleEquals("External Page");
		assertNoProblem();

	}
	
	/**
	 * If we ask to resume multiple times, we carry on going back
	 * to the original resume target.
	 * 
	 * @throws Exception
	 */
	public void testResumeMultiple() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Page 2");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "External Page", "Advertisement");
		assertNoProblem();
		
		assertButtonPresentWithText("Continue");
		clickButtonWithText("Continue");
		
		assertTitleEquals("External Page");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "Advertisement");
		assertNoProblem();
		
		assertButtonPresentWithText("Continue");
		clickButtonWithText("Continue");
		
		assertTitleEquals("External Page");
		assertNoProblem();
		
	}
	
}
