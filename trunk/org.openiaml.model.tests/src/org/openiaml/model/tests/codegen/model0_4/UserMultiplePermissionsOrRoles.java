/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.eclipse.core.resources.IFile;

/**
 * Try the 'AND' constraint, which requires both 'a
 * permission' and 'default role'.
 * 
 * @author jmwright
 *
 */
public class UserMultiplePermissionsOrRoles extends AbstractUserLoginTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(UserMultiplePermissionsOrRoles.class);
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
	 * We cannot access the protected page as a User
	 * because it does not have the appropriate Role.
	 * 
	 * @throws Exception
	 */
	public void testUser() throws Exception {
		IFile sitemap = doStandardLoginAs("user@openiaml.org", "user");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "target");
		assertTitleNotSame("target");
		assertProblem();		// who knows where we are?
	}
	
	/**
	 * Log in as 'default role'; works
	 * 
	 * @throws Exception
	 */
	public void testDefaultRole() throws Exception {
		IFile sitemap = doStandardLoginAs("default@openiaml.org", "test123");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "target");
		assertNoProblem();
	}
	
	/**
	 * Log in as 'another role'; doesn't work
	 * 
	 * @throws Exception
	 */
	public void testAnotherRole() throws Exception {
		IFile sitemap = doStandardLoginAs("another@openiaml.org", "test123");
		assertNoProblem();
		
		gotoSitemapWithProblem(sitemap, "target");
		assertProblem();
	}
	
	/**
	 * Log in as 'both role'; doesn't work
	 * 
	 * @throws Exception
	 */
	public void testBothRoles() throws Exception {
		IFile sitemap = doStandardLoginAs("both@openiaml.org", "test123");
		assertNoProblem();
		
		gotoSitemapWithProblem(sitemap, "target");
		assertProblem();
	}
	
}
