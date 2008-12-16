/**
 * 
 */
package org.openiaml.model.tests.codegen;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.InternetApplication;
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
 * @see LoginHandlerKey.png
 * @author jmwright
 *
 */
public class LoginHandlerKey extends CodegenTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(ROOT + "codegen/LoginHandlerKey.iaml");
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
	 * The site should have a viewkey page, that presents a problem(since
	 * we're not yet authenticated).
	 */
	public void testHasViewkeyPage() {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("viewkey");
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
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("logout");
		assertProblem();	// who knows where we are?

	}

	/**
	 * We can't access the view key page without first
	 * logging in. It should redirect us to the login page.
	 */
	public void testCantAccessViewKeyPageWithoutSession() {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

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
	 */
	public void testCanLoginLogoutFromSitemap() {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("login");
		String loginId = getLabelIDForText("login key");
		setLabeledFormElementField(loginId, "42");
		submit();		// submit the form
		
		// we should now be on the viewkey page
		assertTitleMatch("viewkey");
		assertNoProblem();
		
		// now we just quickly go logout		
		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("logout");
		
		// we should now be on the home page
		assertTitleMatch("home");
		assertNoProblem();

	}
	
	/**
	 * We login;
	 * We check the viewkey page to make sure the key is there;
	 * We navigate to it again to check it's still there;
	 * Then we logout.
	 */
	public void testLoginLogoutCheckViewkey() {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("login");
		String loginId = getLabelIDForText("login key");
		setLabeledFormElementField(loginId, "42");
		submit();		// submit the form
		
		// we should now be on the viewkey page
		assertTitleMatch("viewkey");
		assertNoProblem();
		
		// it should be present
		assertTextPresent("42");
		
		// go back to the viewkey page
		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
		clickLinkWithText("viewkey");
		assertTitleMatch("viewkey");
		assertNoProblem();
		assertTextPresent("42");
		
		// now we logout		
		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("logout");
		
		// we should now be on the home page
		assertTitleMatch("home");
		assertNoProblem();

	}
	
	/**
	 * We try to view the viewkey page, but we get asked to login.
	 * We login, check the viewkey page works, and then logout again.
	 */
	public void testTryViewkeyThenLogin() {
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
		setLabeledFormElementField(loginId, "42");
		submit();		// submit the form

		// we should now be on the viewkey page
		assertTitleMatch("viewkey");
		assertNoProblem();
		
		// it should be present
		assertTextPresent("42");
		
		// go back to the viewkey page
		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
		clickLinkWithText("viewkey");
		assertTitleMatch("viewkey");
		assertNoProblem();
		assertTextPresent("42");
		
		// now we logout		
		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("logout");
		
		// we should now be on the home page
		assertTitleMatch("home");
		assertNoProblem();
		
	}

	/**
	 * Test that we are actually comparing the login keys
	 */
	public void testTryInvalidLogin() {
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("login");
		assertTitleMatch("login");
		assertNoProblem();		// we should be fine

		// lets set the fields
		String loginId = getLabelIDForText("login key");
		setLabeledFormElementField(loginId, "44");	// INVALID
		submit();		// submit the form

		// we should now be on the login page again
		assertTitleMatch("login");
		assertProblem();		// with a problem
		assertTextNotPresent("42");		// it should not be on the page

		// lets set the fields again
		setLabeledFormElementField(loginId, "42");	// VALID
		submit();		// submit the form

		// we should now be on the right page
		assertTitleMatch("viewkey");
		assertNoProblem();		// no problems now
		assertTextPresent("42");

		// now we logout		
		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("logout");
		
		// we should now be on the home page
		assertTitleMatch("home");
		assertNoProblem();
		
	}
	
	/**
	 * helper method: Assert that there has been a problem.
	 */
	protected void assertProblem() {
		assertMatch("(Error|error|Exception|exception)");
	}
	
	/**
	 * Helper method: Assert that there has not been a problem.
	 */
	protected void assertNoProblem() {
		assertNoMatch("(Error|error|Exception|exception)");
	}
	
}
