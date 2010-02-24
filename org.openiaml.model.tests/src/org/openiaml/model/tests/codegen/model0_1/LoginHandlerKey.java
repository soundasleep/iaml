/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_1;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Test a Login Handler in a Session, where the login handler
 * is authenticating against a key.
 * 
 * InternetApplication
 *   Page "Home"
 *   Session "My Session"
 *     LoginHandler type=key [in=42, set=myKey, navigate"login"=viewkey, navigate"logout"=home]
 *     StaticValue key=42
 *     FieldValue "my login key"
 *   Page "View Page"
 *     TextField "current login key"
 *       sync'd with "my login key"
 * 
 * @author jmwright
 *
 */
public class LoginHandlerKey extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(LoginHandlerKey.class);
	}
	
	/**
	 * The site should have a login page.
	 */
	public void testHasLoginPage() throws Exception {
		beginAtSitemapThenPage("login");
		assertTitleMatch("login");
		assertNoProblem();

	}

	/**
	 * The site should have a home page.
	 */
	public void testHasHomePage() throws Exception {
		beginAtSitemapThenPage("Home");
		assertTitleMatch("Home");
		assertNoProblem();

	}
	
	/**
	 * The site should have a viewkey page, that presents a problem (since
	 * we're not yet authenticated).
	 */
	public void testHasViewkeyPage() {
		IFile sitemap = getSitemap();
		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("viewkey");
		assertProblem();	// who knows where we are?
		
		/*
		 * even though we failed, we should have been redirected to another page,
		 * and an exception should NOT be thrown. 
		 */

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
			IFile sitemap = getSitemap();
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
	public void testCantAccessViewKeyPageWithoutSession() {
		IFile sitemap = getSitemap();
		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
	
		// try and view the key without having a valid session
		// it should go to the login page
		clickLinkWithText("viewkey");
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
			assertEquals("viewkey", getPageTitle());
			assertTitleMatch("viewkey");
			assertNoProblem();
			
			// now we just quickly go logout
			gotoSitemapThenPage(sitemap, "logout", "Home");
			assertNoProblem();
			
			// we should now be on the home page
			assertEquals("Home", getPageTitle());
			assertTitleMatch("Home");
			assertNoProblem();
		
		} catch (Error e) {
			throwDebugInformation(e);
		}

	}
	
	/**
	 * We login;
	 * We check the viewkey page to make sure the key is there;
	 * We navigate to it again to check it's still there;
	 * Then we logout.
	 */
	public void testLoginLogoutCheckViewkey() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("login");

		String loginId = getLabelIDForText("login key");
		setLabeledFormElementField(loginId, "key42");
		submit();		// submit the form
		waitForAjax();	// wait for ajax forms
		
		// we should now be on the viewkey page
		try {
			assertEquals("viewkey", getPageTitle());
		} catch (Error e) {
			throwDebugInformation(e);
		}
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
	 * We try to view the viewkey page, but we get asked to login.
	 * We login, check the viewkey page works, and then logout again.
	 */
	public void testTryViewkeyThenLogin() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("viewkey", "login");
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
		IFile sitemap = beginAtSitemapThenPage("login");
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
	
	
	/**
	 * If we login, and then log out, we can not access
	 * the target page again.
	 */
	public void testLoginLogoutComplete() throws Exception {
		testLoginLogoutCheckViewkey(); //login then logout
		
		IFile sitemap = getSitemap();
		
		gotoSitemapWithProblem(sitemap, "viewkey");
		// we should have hit a problem
		assertNotEquals("Login Successful", getPageTitle());
		assertNotEquals("viewkey", getPageTitle());
		assertProblem();
		
		// but we can continue logging in again
		String loginId = getLabelIDForText("login key");
		setLabeledFormElementField(loginId, "key42");
		submit();		// submit the form
		
		// we should now be on the Login Successful page
		assertEquals("viewkey", getPageTitle());
		assertNoProblem();
		
	}
	
	
}
