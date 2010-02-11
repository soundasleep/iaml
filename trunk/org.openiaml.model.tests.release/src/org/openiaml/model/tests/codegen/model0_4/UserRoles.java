/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.util.List;

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
	 * We cannot access the protected page as a User
	 * because it does not have the appropriate Role.
	 * 
	 * @throws Exception
	 */
	public void testUser() throws Exception {
		IFile sitemap = doStandardLoginAs("user@openiaml.org", "user");
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

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = super.getDatabaseInitialisers();
		s.add("CREATE TABLE default_role (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, User_generated_primary_key INTEGER NOT NULL)");
		s.add("INSERT INTO default_role (generated_primary_key, User_generated_primary_key) VALUES (44, 22)");
		return s;
	}
	
}
