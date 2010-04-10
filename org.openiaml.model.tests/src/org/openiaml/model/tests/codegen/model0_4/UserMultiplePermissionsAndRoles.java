/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.eclipse.core.resources.IFile;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

/**
 * Try the 'AND' constraint, which requires both 'a
 * permission' and 'default role'.
 * 
 * @author jmwright
 *
 */
public class UserMultiplePermissionsAndRoles extends AbstractDefaultRoleUserLoginTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(UserMultiplePermissionsAndRoles.class, true);
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
		try {
			gotoSitemapWithProblem(sitemap, "target");
			fail("Expected to not be able to go to 'target' page");
		} catch (FailingHttpStatusCodeException e) {
			// expected
			checkExceptionContains(e, "Source role 'User' could not be translated into 'default role'");
		}
		assertProblem();
	}
	
	/**
	 * Log in as 'default role'; works ('default role' provides 'a permission')
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
		try {
			gotoSitemapWithProblem(sitemap, "target");
			fail("Expected to not be able to go to 'target' page");
		} catch (FailingHttpStatusCodeException e) {
			// expected
			checkExceptionContains(e, "Source role 'User' could not be translated into 'default role'");
		}
		assertProblem();
	}
	
	/**
	 * Log in as 'both role'; doesn't work ('both role' does
	 * not extend 'default role')
	 * 
	 * @throws Exception
	 */
	public void testBothRoles() throws Exception {
		IFile sitemap = doStandardLoginAs("both@openiaml.org", "test123");
		// we actually expect a problem, because user does not have 'default role' permissions 
		assertProblem();
		
		// if we then try to go to 'target', we likewise will also be prevented		
		try {
			gotoSitemapWithProblem(sitemap, "target");
			fail("Expected to not be able to go to 'target' page");
		} catch (FailingHttpStatusCodeException e) {
			// expected
			checkExceptionContains(e, "Source role 'User' could not be translated into 'default role'");
		}
		assertProblem();		// who knows where we are?
	}
	
}
