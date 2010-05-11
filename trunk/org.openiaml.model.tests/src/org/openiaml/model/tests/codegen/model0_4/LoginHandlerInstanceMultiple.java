/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

/**
 * Test a Login Handler in a Session, where the login handler
 * is authenticating against a domain object instance.
 * 
 * @example Session,LoginHandler
 * 		Restricting access to a {@model Session} with a
 * 		{@model LoginHandler} (based on selecting a {@model DomainObject} instance)
 * 		that requires two valid {@model ParameterEdge parameters}.
 * 
 * @implementation LoginHandler,DomainObjectInstance
 * 		A LoginHandler (selecting a DomainObject) needs incoming
 * 		{@model DomainAttribute DomainAttributes} as {@model ParameterEdge parameters} in order 
 * 		to select the valid {@model DomainObjectInstance instance}.
 * 
 * @author jmwright
 *
 */
public class LoginHandlerInstanceMultiple extends DatabaseCodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(LoginHandlerInstanceMultiple.class);
		initialiseDatabase();
	}

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE User (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(64) NOT NULL, email VARCHAR(64) NOT NULL, password VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO User (generated_primary_key, name, email, password) VALUES (12, 'User Default', 'default@jevon.org', 'test1')");
		s.add("INSERT INTO User (generated_primary_key, name, email, password) VALUES (22, 'User Two', 'target@jevon.org', 'test2')");
		s.add("INSERT INTO User (generated_primary_key, name, email, password) VALUES (42, 'User Three', 'test3@jevon.org', 'test3')");
		s.add("INSERT INTO User (generated_primary_key, name, email, password) VALUES (82, 'User Four', 'test4@jevon.org', 'test4')");
		return s;
	}
	
	/**
	 * The site should have a login page.
	 */
	public void testHasLoginPage() {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("login");
		assertTitleMatch("login");
		assertNoProblem();

	}

	/**
	 * The site should have a home page.
	 */
	public void testHasHomePage() {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("Home");
		assertTitleMatch("Home");
		assertNoProblem();

	}
	
	/**
	 * The site should have a current user page, that presents a problem (since
	 * we're not yet authenticated).
	 */
	public void testHasCurrentUserPage() {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("current user");
		assertProblem();	// who knows where we are?

	}
	
	/**
	 * The site should have a login successful page [generated], that presents a problem (since
	 * we're not yet authenticated).
	 */
	public void testHasLoginSuccesfulPage() {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("Login Successful");
		assertProblem();	// who knows where we are?

	}

	/**
	 * The site should have a logout page, that presents a problem (since
	 * we're not yet authenticated).
	 * 
	 * Normally the user wouldn't see the "logout" link, but we should
	 * check that if they access it manually without being authenticated,
	 * that an error is thrown.
	 */
	public void testHasLogoutPage() {
		try {
			
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("logout");
		assertProblem();	// who knows where we are?
		
		} catch (Error e) {
			System.out.println(getPageSource());
			throw e;
		}

	}

	/**
	 * We can't access the view key page without first
	 * logging in. It should redirect us to the login page.
	 */
	public void testCantAccessSecurePageWithoutSession() {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
	
		// try and view the key without having a valid session
		// it should go to the login page
		clickLinkWithText("current user");
		// TODO add: assertTitleNotMatch("viewkey");
		assertTitleMatch("login");
		assertProblem();		// we should have been warned

	}
	
	/**
	 * We can login; and then we can logout by going through the
	 * sitemap (since our model doesn't have a link to logout yet.)
	 * @throws Exception 
	 */
	public void testCanLoginLogoutFromSitemap() throws Exception {
		try {
		IFile sitemap = beginAtSitemapThenPage("login");
		assertNoProblem();
		
		String loginId = getLabelIDForText("password");
		setLabeledFormElementField(loginId, "test2");
		
		String emailId = getLabelIDForText("email");
		setLabeledFormElementField(emailId, "target@jevon.org");
		
		submit();		// submit the form
		
		// we should now be on the viewkey page
		assertEquals("Login Successful", getPageTitle());
		assertTitleMatch("Login Successful");
		assertNoProblem();
		
		// now we just quickly go logout, we should now be on the
		// "logout successful" page
		gotoSitemapThenPage(sitemap, "logout", "Logout Successful");
		assertNoProblem();
		
		} catch (Error e) {
			System.out.println( getPageSource() );		// let us debug the page source
			throw e;		// continue throwing
		}

	}
	
	/**
	 * We login;
	 * We check the page to make sure we are the right user;
	 * We navigate to it again to check it's still there;
	 * Then we logout.
	 */
	public void testLoginLogoutCheck2() throws Exception {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("login");
		String loginId = getLabelIDForText("password");
		setLabeledFormElementField(loginId, "test2");

		String emailId = getLabelIDForText("email");
		setLabeledFormElementField(emailId, "target@jevon.org");

		submit();		// submit the form
		
		// we should now be on the Login Successful page
		assertEquals("Login Successful", getPageTitle());
		assertNoProblem();
		
		// lets now go to the "current user" page
		gotoSitemapThenPage(sitemap, "current user");
		{
			String username = getLabelIDForText("current user name");
			assertLabeledFieldEquals(username, "User Two");
		}
		
		// the password should _not_ be present
		assertNoMatch("test2");
		
		// reload the page
		gotoSitemapThenPage(sitemap, "current user");
		{
			String username = getLabelIDForText("current user name");
			assertLabeledFieldEquals(username, "User Two");
		}
		
		// now we logout, we should now be on the logout page
		gotoSitemapThenPage(sitemap, "logout", "Logout Successful");
	}
	
	/**
	 * We login;
	 * We check the page to make sure we are the right user;
	 * We navigate to it again to check it's still there;
	 * Then we logout.
	 */
	public void testLoginLogoutCheck3() throws Exception {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("login");
		String loginId = getLabelIDForText("password");
		setLabeledFormElementField(loginId, "test3");

		String emailId = getLabelIDForText("email");
		setLabeledFormElementField(emailId, "test3@jevon.org");

		submit();		// submit the form
		
		// we should now be on the Login Successful page
		assertEquals("Login Successful", getPageTitle());
		assertNoProblem();
		
		// lets now go to the "current user" page
		gotoSitemapThenPage(sitemap, "current user");
		{
			String username = getLabelIDForText("current user name");
			assertLabeledFieldEquals(username, "User Three");
		}
		
		// the password should _not_ be present
		assertNoMatch("test3");
		
		// reload the page
		gotoSitemapThenPage(sitemap, "current user");
		{
			String username = getLabelIDForText("current user name");
			assertLabeledFieldEquals(username, "User Three");
		}
		
		// now we logout, we should now be on the logout page
		gotoSitemapThenPage(sitemap, "logout", "Logout Successful");
	}
	
	/**
	 * Once we can login, we can change our name.
	 */
	public void testChangeName() throws Exception {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("login");
		String loginId = getLabelIDForText("password");
		setLabeledFormElementField(loginId, "test4");

		String emailId = getLabelIDForText("email");
		setLabeledFormElementField(emailId, "test4@jevon.org");

		submit();		// submit the form
		
		// we should now be on the Login Successful page
		assertEquals("Login Successful", getPageTitle());
		assertNoProblem();
		
		// lets now go to the "current user" page
		gotoSitemapThenPage(sitemap, "current user");
		String newUsername = "my new username " + new Date();
		{
			String username = getLabelIDForText("current user name");
			assertLabeledFieldEquals(username, "User Four");
			
			// lets set it
			setLabeledFormElementField(username, newUsername);
		}
		
		// reload the page, it should remain
		gotoSitemapThenPage(sitemap, "current user");
		{
			String username = getLabelIDForText("current user name");
			assertLabeledFieldEquals(username, newUsername);
		}
		
		// now we logout, we should now be on the logout page
		gotoSitemapThenPage(sitemap, "logout", "Logout Successful");
		
		// re-login to make sure the change was successful
		beginAtSitemapThenPage(sitemap, "login", "login");
		String loginId2 = getLabelIDForText("password");
		setLabeledFormElementField(loginId2, "test4");

		String emailId2 = getLabelIDForText("email");
		setLabeledFormElementField(emailId2, "test4@jevon.org");

		submit();		// submit the form
		
		// we should now be on the Login Successful page
		assertEquals("Login Successful", getPageTitle());
		assertNoProblem();
		
		// lets now go to the "current user" page
		gotoSitemapThenPage(sitemap, "current user");
		{
			String username = getLabelIDForText("current user name");
			assertLabeledFieldEquals(username, newUsername);
		}
	}
	
	/**
	 * Test that we are actually comparing against the database
	 */
	public void testTryInvalidLogin() throws Exception {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("login");
		String loginId = getLabelIDForText("password");
		setLabeledFormElementField(loginId, "test6"); // INVALID password
		
		String emailId = getLabelIDForText("email");
		setLabeledFormElementField(emailId, "target@jevon.org");
		
		submit();		// submit the form
		
		// we should now be on the Login Successful page
		assertNotEquals("Login Successful", getPageTitle());
		assertProblem();
		
	}
	
	
	/**
	 * Test that we are actually comparing against the database
	 */
	public void testTryInvalidLoginNoEmail() throws Exception {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("login");
		String loginId = getLabelIDForText("password");
		setLabeledFormElementField(loginId, "test2");

		submit();		// submit the form
		
		// we should now be on the Login Successful page
		assertNotEquals("Login Successful", getPageTitle());
		assertProblem();
		
	}
	
	/**
	 * Test that we are actually comparing against the database
	 */
	public void testTryInvalidLoginNoPassword() throws Exception {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("login");

		String emailId = getLabelIDForText("email");
		setLabeledFormElementField(emailId, "target@jevon.org");
		
		submit();		// submit the form
		
		// we should now be on the Login Successful page
		assertNotEquals("Login Successful", getPageTitle());
		assertProblem();
		
	}

	/**
	 * We can't get into the site with an invalid combination.
	 */
	public void testTryInvalidCombination() throws Exception {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("login");

		// 'test3' and 'target@jevon.org' are valid values, 
		// but not valid together
		String loginId = getLabelIDForText("password");
		setLabeledFormElementField(loginId, "test3");

		String emailId = getLabelIDForText("email");
		setLabeledFormElementField(emailId, "target@jevon.org");
		
		submit();		// submit the form
		
		// we should not be on the Login Successful page
		assertNotEquals("Login Successful", getPageTitle());
		assertProblem();
		
	}

	/**
	 * If we switch the values around, we shouldn't be able to
	 * get in either.
	 */
	public void testTryInvalidReverse() throws Exception {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("login");

		// 'test3' and 'target@jevon.org' are valid values, 
		// but not valid together
		String loginId = getLabelIDForText("password");
		setLabeledFormElementField(loginId, "target@jevon.org");

		String emailId = getLabelIDForText("email");
		setLabeledFormElementField(emailId, "test2");
		
		submit();		// submit the form
		
		// we should now be on the Login Successful page
		assertNotEquals("Login Successful", getPageTitle());
		assertProblem();
		
	}
	
}
