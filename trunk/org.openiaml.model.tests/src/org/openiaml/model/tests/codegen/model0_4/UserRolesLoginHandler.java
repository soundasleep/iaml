/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.util.List;

import org.eclipse.core.resources.IFile;

/**
 * We explicitly define the login handler and the roles 
 * required.
 * 
 * @example LoginHandler,AccessControlHandler,Session Restricting access to {@model Frame frames} contained
 * 		within a {@model Session} by enforcing {@model LoginHandler user login}
 * 		and checking the {@model UserInstance current user} has a given {@model Role}.
 * @implementation Session If a {@model Session} contains an {@model AccessControlHandler},
 * 		it will check that the {@model UserInstance current user} matches its
 * 		{@model RequiresEdge access requirements}.
 * @implementation Session If a {@model Session} contains a {@model LoginHandler} of type
 * 		"user", access to pages contained within it will need to login to the current session. 
 * @author jmwright
 *
 */
public class UserRolesLoginHandler extends AbstractUserLoginTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(UserRolesLoginHandler.class);
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
		
		gotoSitemapWithProblem(sitemap, "target page");
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

	@Override
	protected String getDatabaseName() {
		return "output/users_1233b454017_bb.db";
	}

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = super.getDatabaseInitialisers();
		s.add("CREATE TABLE Registered_User (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, User_generated_primary_key INTEGER NOT NULL)");
		s.add("INSERT INTO Registered_User (generated_primary_key, User_generated_primary_key) VALUES (44, 32)");
		return s;
	}
	
}
