/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.eclipse.core.resources.IFile;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

/**
 * If we place one access control in a session, and another
 * access control in the contained page, then <em>both</em>
 * of these access controls must be satisfied in order to
 * provide access
 * 
 * @author jmwright
 *
 */
public class UserPermissionsRecursive extends AbstractDefaultRoleUserLoginTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(UserPermissionsRecursive.class, true);
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
	 * <p>In fact, we can't even log in, because the given user permissions
	 * (user) don't even have the required permission to login.
	 * As a result, an exception is not thrown; rather, we are taken
	 * back to the login page.
	 * 
	 * @throws Exception
	 */
	public void testUser() throws Exception {
		IFile sitemap = doStandardLoginAs("user@openiaml.org", "user");
		// we actually expect a problem, because user does not have 'default role' permissions 
		assertProblem();
		
		// if we then try to go to 'target', we likewise will also be prevented		
		// this should NOT throw an exception
		gotoSitemapWithProblem(sitemap, "target");
		
		// rather, we should be back on the login page
		assertTitleEquals("login");
		
		// with a problem
		assertProblem();
	}
	
	/**
	 * Log in as 'default role'; fails
	 * 
	 * @throws Exception
	 */
	public void testDefaultRole() throws Exception {
		IFile sitemap = doStandardLoginAs("default@openiaml.org", "test123");
		assertNoProblem();
		
		try {
			gotoSitemapWithProblem(sitemap, "target");
			fail("Did not expect to get into 'target' page");
		} catch (FailingHttpStatusCodeException e) {
			// expected
			checkExceptionContains(e, "User of type 'current instance' did not have permission 'a different permission'");
		}

	}

	/**
	 * Log in as 'default role' allows us to access 'unrelated'
	 * 
	 * @throws Exception
	 */
	public void testDefaultRoleUnrelated() throws Exception {
		IFile sitemap = doStandardLoginAs("default@openiaml.org", "test123");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "unrelated");
		assertNoProblem();
	}
	
	/**
	 * Log in as 'another role'; fails
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
	
	/**
	 * Log in as 'both roles'; works
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
