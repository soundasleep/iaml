/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.util.List;

import org.eclipse.core.resources.IFile;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

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

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = super.getDatabaseInitialisers();
		s.add("CREATE TABLE default_role (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, User_generated_primary_key INTEGER NOT NULL)");
		s.add("INSERT INTO default_role (generated_primary_key, User_generated_primary_key) VALUES (44, 22)");
		return s;
	}
	
}
