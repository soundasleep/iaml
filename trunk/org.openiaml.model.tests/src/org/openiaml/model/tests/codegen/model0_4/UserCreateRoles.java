/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.eclipse.core.resources.IFile;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

/**
 * We can create new users and assign them roles.
 * 
 * @example Role Creating a new user by creating a new {@model Role} instance.
 * @author jmwright
 *
 */
public class UserCreateRoles extends AbstractUserLoginTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(UserCreateRoles.class);
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
	 * Because the Session does not have a LoginHandler,
	 * one is created automatically, so a login page exists.
	 */
	public void testLoginExists() throws Exception {
		beginAtSitemapThenPage("login");
		assertNoProblem();
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
	 * The user that we will create does not exist in
	 * the database already.
	 * 
	 * @throws Exception
	 */
	public void testNewUserDoesNotExist() throws Exception {
		doStandardLoginAs("new@openiaml.org", "test456");
		assertProblem();
	}
	
	/**
	 * We create a new user, then use it to login.
	 * 
	 * @throws Exception
	 */
	public void testCreateRole() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("create a new user");
		assertNoProblem();
		
		// fill in the user details
		String sourceEmail = "new@openiaml.org";
		String sourcePassword = "test456";
		{
			String email = getLabelIDForText("email");
			assertLabeledFieldEquals(email, "");
			setLabeledFormElementField(email, sourceEmail);
		
			String password = getLabelIDForText("password");
			assertLabeledFieldEquals(password, "");
			setLabeledFormElementField(password, sourcePassword);
		}
		
		// click the button
		clickButtonWithText("create new user");
		assertNoProblem();
	
		// lets try and login now!
		doStandardLoginAs(sitemap, sourceEmail, sourcePassword);
		assertNoProblem();
		
		// and go to the target page
		gotoSitemapThenPage(sitemap, "target");
		assertTitleEquals("target");
		assertNoProblem();	// successful
	}
	
}
