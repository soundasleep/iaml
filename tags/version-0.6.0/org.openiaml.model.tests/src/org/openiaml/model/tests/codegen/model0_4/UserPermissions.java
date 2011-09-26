/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.eclipse.core.resources.IFile;

/**
 * The access control requires 'a permission'.
 * 
 * @author jmwright
 *
 */
public class UserPermissions extends AbstractDefaultRoleUserLoginTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(UserPermissions.class);
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
	 * We cannot access the protected page as a User
	 * because it does not have the appropriate Permission.
	 * 
	 * @throws Exception
	 */
	public void testUser() throws Exception {
		IFile sitemap = doStandardLoginAs("user@openiaml.org", "user");
		// we actually expect a problem, because user does not have 'default role' permissions 
		assertProblem();
		
		// if we then try to go to 'target', we likewise will also be prevented		
		gotoSitemapWithProblem(sitemap, "target");
		assertTitleNotSame("target");
		assertProblem();		// who knows where we are?
	}
	
	/**
	 * Log in as 'default role'; works, because it has the Permission
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
		// we actually expect a problem, because user does not have 'default role' permissions 
		assertProblem();
		
		// if we then try to go to 'target', we likewise will also be prevented		
		gotoSitemapWithProblem(sitemap, "target");
		assertProblem();
	}
	
}
