/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.eclipse.core.resources.IFile;

/**
 * We explicitly define the login handler and the roles 
 * required.
 * 
 * @author jmwright
 *
 */
public class UserRolesLoginHandler extends AbstractUserLoginTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(UserRolesLoginHandler.class);
	}
	
	/**
	 * Check the initial state of the application.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		beginAtSitemapThenPage("Home");
	}

	/**
	 * We cannot access the protected page as a Guest
	 * because it does not have the appropriate Role.
	 * 
	 * @throws Exception
	 */
	public void testGuest() throws Exception {
		IFile sitemap = doStandardLoginAs("guest@openiaml.org", "guest");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "target page");
		assertTitleNotSame("target page");
		assertProblem();		// who knows where we are?
	}
	
	/**
	 * Log in as 'Registered User'; passes
	 * 
	 * @throws Exception
	 */
	public void testDefaultRole() throws Exception {
		IFile sitemap = doStandardLoginAs("registered@openiaml.org", "test123");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "target page");
		assertNoProblem();
	}

}
