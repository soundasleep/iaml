/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.eclipse.core.resources.IFile;

/**
 * Try the 'OR' constraint, which lets us have any
 * of the required permissions.
 * 
 * @author jmwright
 * @example AccessControlHandler,ConstraintEdge,RequiresEdge 
 * 		An {@model AccessControlHandler} which {@model RequiresEdge requires} 
 * 		only one of two {@model Permission permissions}, 
 * 		which are connected by a {@model ConstraintEdge}.
 * @implementation AccessControlHandler,ConstraintEdge 
 * 		If an {@model AccessControlHandler}'s {@model RequiresEdge requirements}
 * 		are connected by a {@model ConstraintEdge} of type "OR", only 
 * 		one of the requirements will be necessary to permit access.
 *
 */
public class UserMultiplePermissionsOr extends AbstractDefaultRoleUserLoginTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(UserMultiplePermissionsOr.class);
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
	 * because it does not have the appropriate Role.
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
	 * Log in as 'default role'
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
	 * Log in as 'another role'
	 * 
	 * @throws Exception
	 */
	public void testAnotherRole() throws Exception {
		IFile sitemap = doStandardLoginAs("another@openiaml.org", "test123");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "target");
		assertNoProblem();
	}
	
	/**
	 * Log in as 'both role'
	 * 
	 * @throws Exception
	 */
	public void testBothRoles() throws Exception {
		IFile sitemap = doStandardLoginAs("both@openiaml.org", "test123");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "target");
		assertNoProblem();
	}
	
}
