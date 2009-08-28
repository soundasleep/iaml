/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.eclipse.core.resources.IFile;

/**
 * Roles-based access control. 'default' is required.
 * 
 * @author jmwright
 *
 */
public class UserRoles extends AbstractUserLoginTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(UserRoles.class);
		initialiseDatabase();
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
		
		gotoSitemapWithProblem(sitemap, "target");
		assertTitleNotSame("target");
		assertProblem();		// who knows where we are?
	}
	
	/**
	 * Log in as 'default role'; passes
	 * 
	 * @throws Exception
	 */
	public void testDefaultRole() throws Exception {
		IFile sitemap = doStandardLoginAs("default@openiaml.org", "test123");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "target");
		assertNoProblem();
	}

}
