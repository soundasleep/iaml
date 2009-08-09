/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Test a Login Handler in a Session, where the login handler
 * is authenticating against a domain object instance.
 * 
 * @author jmwright
 *
 */
public class LoginHandlerInstance extends CodegenTestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(LoginHandlerInstance.class);
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
		
		String loginId = getLabelIDForText("login key");
		setLabeledFormElementField(loginId, "key42");
		submit();		// submit the form
		waitForAjax();	// wait for ajax forms
		
		// we should now be on the viewkey page
		assertEquals("Login Successful", getPageTitle());
		assertTitleMatch("Login Successful");
		assertNoProblem();
		
		// now we just quickly go logout
		gotoSitemapThenPage(sitemap, "logout", "Home");
		assertNoProblem();
		
		// we should now be on the home page
		assertEquals("Home", getPageTitle());
		assertTitleMatch("Home");
		assertNoProblem();
		
		} catch (Error e) {
			System.out.println( getPageSource() );		// let us debug the page source
			throw e;		// continue throwing
		}

	}
	
	/**
	 * We login;
	 * We check the viewkey page to make sure the key is there;
	 * We navigate to it again to check it's still there;
	 * Then we logout.
	 */
	public void testLoginLogoutCheckViewkey() throws Exception {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("login");
		String loginId = getLabelIDForText("login key");
		setLabeledFormElementField(loginId, "key42");
		submit();		// submit the form
		waitForAjax();	// wait for ajax forms
		
		// we should now be on the Login Successful page
		assertEquals("Login Successful", getPageTitle());
		assertNoProblem();
		
		// the password we entered should not be present
		fail("continue this test");
		
		fail("need to sitemap then go to 'current user' page");
		
		// it should be present
		assertTextPresent("key42");
		
		// go back to the viewkey page
		gotoSitemapThenPage(sitemap, "viewkey");
		assertNoProblem();
		assertTextPresent("key42");
		
		// now we logout		
		gotoSitemapThenPage(sitemap, "logout", "Home");
		
		// we should now be on the home page
		assertTitleMatch("Home");
		assertNoProblem();

	}
	
	/**
	 * We try to view the viewkey page, but we get asked to login.
	 * We login, check the viewkey page works, and then logout again.
	 */
	public void testTryViewkeyThenLogin() throws Exception {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("viewkey");
		// TODO add: assertTitleNotMatch("viewkey");
		assertTitleMatch("login");
		assertProblem();		// we should have been warned

		// lets set the fields
		String loginId = getLabelIDForText("login key");
		setLabeledFormElementField(loginId, "key42");
		submit();		// submit the form
		waitForAjax();	// wait for ajax forms

		// we should now be on the viewkey page
		assertEquals("viewkey", getPageTitle());
		assertNoProblem();
		
		// it should be present
		assertTextPresent("key42");
		
		// go back to the viewkey page
		gotoSitemapThenPage(sitemap, "viewkey");
		assertNoProblem();
		assertTextPresent("key42");
		
		// now we logout		
		gotoSitemapThenPage(sitemap, "logout", "Home");
		
		// we should now be on the home page
		assertTitleMatch("Home");
		assertNoProblem();
		
	}

	/**
	 * Test that we are actually comparing the login keys
	 */
	public void testTryInvalidLogin() throws Exception {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("login");
		assertTitleMatch("login");
		assertNoProblem();		// we should be fine

		// lets set the fields
		String loginId = getLabelIDForText("login key");
		setLabeledFormElementField(loginId, "key44");	// INVALID
		submit();		// submit the form
		waitForAjax();	// wait for ajax forms

		// we should now be on the login page again
		assertTitleMatch("login");
		assertProblem();		// with a problem
		assertTextNotPresent("key42");		// it should not be on the page

		// lets set the fields again
		setLabeledFormElementField(loginId, "key42");	// VALID
		submit();		// submit the form
		waitForAjax();	// wait for ajax forms

		// we should now be on the right page
		assertTitleMatch("viewkey");
		assertNoProblem();		// no problems now
		assertTextPresent("key42");

		// now we logout		
		gotoSitemapThenPage(sitemap, "logout", "Home");
		
		// we should now be on the home page
		assertTitleMatch("Home");
		assertNoProblem();
		
	}

}
