/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.eclipse.core.resources.IFile;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

/**
 * We can create new users and assign them roles, and
 * assign and remove roles and permissions dynamically 
 * at runtime.
 * 
 * @author jmwright
 *
 */
public class UserModifyRoles extends AbstractDefaultRoleUserLoginTestCase {
	
	private final String NEW_EMAIL = "new@openiaml.org";
	private final String NEW_PASSWORD = "test123";
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(UserModifyRoles.class, true);
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
	 * The user that we will create does not exist in
	 * the database already.
	 * 
	 * @throws Exception
	 */
	public void testNewUserDoesNotExist() throws Exception {
		doStandardLoginAs(NEW_EMAIL, NEW_PASSWORD);
		assertProblem();
	}
	
	public void testCreateRole() throws Exception {
		createRole();
	}
	
	/**
	 * We can create a new user, and use it to login.
	 * Because the new UserInstance is an instance of 'Default Role',
	 * it should automatically have the 'Default Role' permissions.
	 * 
	 * @throws Exception
	 */
	protected IFile createRole() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("create a new user");
		assertNoProblem();
		
		// fill in the user details
		{
			String email = getLabelIDForText("email");
			assertLabeledFieldEquals(email, "");
			setLabeledFormElementField(email, NEW_EMAIL);
		
			String password = getLabelIDForText("password");
			assertLabeledFieldEquals(password, "");
			setLabeledFormElementField(password, NEW_PASSWORD);
		}
		
		// click the button
		clickButtonWithText("create new user");
		waitForAjax();
		
		// lets try and login now!
		doStandardLoginAs(sitemap, NEW_EMAIL, NEW_PASSWORD);
		assertNoProblem();
		
		// and go to the target page
		gotoSitemapThenPage(sitemap, "target");
		assertTitleEquals("target");
		assertNoProblem();	// successful
		
		return sitemap;
	}
	
	/**
	 * Without adding 'Role 1' to the user, we cannot access
	 * the secure page.
	 * 
	 * @throws Exception
	 */
	public void testWithoutRole1() throws Exception {
		// create a new user
		IFile sitemap = createRole();
		
		// we cannot access the 'requires role 1' page, since we
		// only have 'default role'
		try {
			gotoSitemapWithProblem(sitemap, "requires role 1");
			fail("Should not be able to access page");
		} catch (FailingHttpStatusCodeException e) {
			// expected
		}
	}
	
	public void testCreateAddRole1() throws Exception {
		createAddRole1();
	}

	/**
	 * Once created, we can add 'role 1' which can then
	 * be used to access the page.
	 * 
	 * @throws Exception
	 */
	protected IFile createAddRole1() throws Exception {
		// create a new user
		IFile sitemap = createRole();
		
		gotoSitemapThenPage(sitemap, "add new roles");		
		clickButtonWithText("add role 1");
		assertNoProblem();
		
		// we can now access the 'requires role 1' page
		gotoSitemapThenPage(sitemap, "requires role 1");		
		assertNoProblem();
		
		return sitemap;
	}
	
	/**
	 * Once we have 'role 1', we can log out, log
	 * back in, and the role persists.
	 * 
	 * @throws Exception
	 */
	public void testCreateAddRole1Logout() throws Exception {
		// create a new user
		IFile sitemap = createAddRole1();
		
		gotoSitemapThenPage(sitemap, "logout", "Logout Successful");
		assertNoProblem();
		
		// we can't get back to role 1 page
		gotoSitemapWithProblem(sitemap, "requires role 1");
		assertProblem();
		
		// so we login
		doStandardLoginAs(sitemap, NEW_EMAIL, NEW_PASSWORD);
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "requires role 1");
		assertNoProblem();
	}
	
	/**
	 * If we add 'role 2', we still can't access a page
	 * that requires 'role 1'
	 * 
	 * @throws Exception
	 */
	public void testCreateAddRole2() throws Exception {
		// create a new user
		IFile sitemap = createRole();
		
		gotoSitemapThenPage(sitemap, "add new roles");		
		clickButtonWithText("add role 2");
		assertNoProblem();
		
		// we can't access the 'requires role 1' page
		try {
			gotoSitemapWithProblem(sitemap, "requires role 1");
			fail("Should not have been able to get to this page");
		} catch (FailingHttpStatusCodeException e) {
			// expected
		}
		
		// we can't access the 'requires permission 1' page
		gotoSitemapWithProblem(sitemap, "requires permission 1");		
		assertProblem();
		
		// or the inherited permission page
		gotoSitemapWithProblem(sitemap, "requires inherited permission");		
		assertProblem();
		
	}	
	
	public void testCreateAddPermission() throws Exception {
		createAddPermission();
	}
	
	/**
	 * Once created, we can add 'permission 1' which can then
	 * be used to access the page.
	 * 
	 * @throws Exception
	 */
	protected IFile createAddPermission() throws Exception {
		// create a new user
		IFile sitemap = createRole();
		
		gotoSitemapThenPage(sitemap, "add new roles");		
		clickButtonWithText("add permission 1");
		assertNoProblem();
		
		// we can now access the 'requires permission 1' page
		gotoSitemapThenPage(sitemap, "requires permission 1");		
		assertNoProblem();
		
		// but we can't access the inherited permission
		gotoSitemapWithProblem(sitemap, "requires inherited permission");
		assertProblem();
		
		return sitemap;
	}

	/**
	 * If we add 'role 2', we automatically gain inherited
	 * permissions.
	 * 
	 * @throws Exception
	 */
	public void testCreateInheritedPermission() throws Exception {
		// create a new user
		IFile sitemap = createRole();
		
		gotoSitemapThenPage(sitemap, "add new roles");		
		clickButtonWithText("add role 2");
		assertNoProblem();
		
		// we can now access the 'requires inherited permission' page
		gotoSitemapThenPage(sitemap, "requires inherited permission");		
		assertNoProblem();
	}
	
	public void testCreateManyPermissions() throws Exception {
		createManyPermissions();
	}
	
	/**
	 * If we create a user with all these permissions, we can
	 * access all of the pages.
	 * 
	 * @throws Exception
	 */
	protected IFile createManyPermissions() throws Exception {
		// create a new user
		IFile sitemap = createRole();
		
		gotoSitemapThenPage(sitemap, "add new roles");		
		clickButtonWithText("add role 1");
		clickButtonWithText("add role 2");
		clickButtonWithText("add permission 1");
		assertNoProblem();
		
		// we can now access the 'requires inherited permission' page
		gotoSitemapThenPage(sitemap, "requires inherited permission");		
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "requires role 1");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "requires permission 1");		
		assertNoProblem();
		
		return sitemap;
	}
	
	/**
	 * If we create a user with all these permissions, we can
	 * access all of the pages, even after logging out.
	 * 
	 * @throws Exception
	 */
	public void testCreateManyPermissionsLogout() throws Exception {
		// create a new user
		IFile sitemap = createManyPermissions();
		
		gotoSitemapThenPage(sitemap, "logout", "Logout Successful");
		assertNoProblem();
		
		// relogin
		doStandardLoginAs(sitemap, NEW_EMAIL, NEW_PASSWORD);
		assertNoProblem();
		
		// we can now access the 'requires inherited permission' page
		gotoSitemapThenPage(sitemap, "requires inherited permission");		
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "requires role 1");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "requires permission 1");		
		assertNoProblem();
		
	}
	
	/**
	 * If we create a user with all these permissions, and
	 * then remove them all, we can no longer access all pages.
	 * 
	 * @throws Exception
	 */
	public void testCreateManyPermissionsRemove() throws Exception {
		// create a new user
		IFile sitemap = createManyPermissions();
		
		gotoSitemapThenPage(sitemap, "remove roles");		
		clickButtonWithText("remove all roles");
		clickButtonWithText("remove all permissions");
		assertNoProblem();
		
		// can't access a page
		gotoSitemapThenPage(sitemap, "requires role 1");
		assertProblem();
		
		// logout
		gotoSitemapThenPage(sitemap, "logout", "Logout Successful");
		assertNoProblem();
		
		// relogin
		doStandardLoginAs(sitemap, NEW_EMAIL, NEW_PASSWORD);
		assertNoProblem();
		
		// we can now access the 'requires inherited permission' page
		gotoSitemapThenPage(sitemap, "requires inherited permission");		
		assertProblem();
		
		gotoSitemapThenPage(sitemap, "requires role 1");
		assertProblem();
		
		gotoSitemapThenPage(sitemap, "requires permission 1");		
		assertProblem();

	}
	
}
