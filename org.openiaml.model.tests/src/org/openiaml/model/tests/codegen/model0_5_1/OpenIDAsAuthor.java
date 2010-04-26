/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_1;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;
import org.openiaml.model.tests.PhpRuntimeExceptionException;


/**
 * @example InputTextField
 * 		A {@model InputTextField} of type <code>iamlOpenIDURL</code>
 * 		will have functionality to authenticate the URL with an
 * 		<a href="http://openid.net/">OpenID</a> server.
 */
public class OpenIDAsAuthor extends CodegenTestCase {

	/**
	 * This page will provide a openid.server to the same host.
	 */
	public static final String OPENID_PAGE_SERVER = "http://openiaml.org/openid/";
	
	/**
	 * This page will provide an openid.server and openid.delegate.
	 * The delegate actually just points to the {@link #OPENID_PAGE_SERVER} above.
	 */
	public static final String OPENID_PAGE_DELEGATE = "http://openiaml.org/openid/delegate/";
	
	/**
	 * This page will provide a openid.server to the same host, but
	 * it requires login as 'test'.
	 */
	public static final String OPENID_PAGE_SERVER_PASS = "http://openiaml.org/openid/password/";
	
	/**
	 * This page will provide an openid.server and openid.delegate, but
	 * it requires login as 'test'.
	 * The delegate actually just points to the {@link #OPENID_PAGE_SERVER} above.
	 */
	public static final String OPENID_PAGE_DELEGATE_PASS = "http://openiaml.org/openid/delegate-password/";
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(OpenIDAsAuthor.class);
	}

	/**
	 * The home page can be accessed.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
	}
	
	/**
	 * If we go to the View Posts page, we will get an exception:
	 * there are no posts currently.
	 * 
	 * @throws Exception
	 */
	public void testViewPosts() throws Exception {
		try {
			beginAtSitemapThenPage("View Posts");			
			fail("Should have failed");
		} catch (PhpRuntimeExceptionException e) {
			assertContains("No results found for query ", e.getMessage());
		}
	}
	
	/**
	 * Create a new post. Because the OpenID field is
	 * required, we cannot create a new post without authenticating it.
	 * 
	 * @throws Exception
	 */
	public void testCreateNewPost() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("Create New Post");
		assertNoProblem();
		
		// edit the title
		{
			String target = getLabelIDForText("title");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Created Title");
		}
		
		// we don't set the URL
		
		// we try clicking on the button
		try {
			clickButtonWithText("Save");
			fail("Should have failed");
		} catch (RuntimeException e) {
			// expected
			// TODO however, this RuntimeException is not a PhpRuntimeException, or anything expected.
			// it's actually probably an implementation bug, because 'Save' is trying to
			// be called when not all attributes have been set.
		}
		
		// and we can't view the news page
		viewNewsPageUnsuccessfully(sitemap);
		
	}
	
	/**
	 * We try create a new post now, but provide a valid OpenID
	 * url; but we don't try and authenticate. It should still fail.
	 * 
	 * @throws Exception
	 */
	public void testCreateNewPostWithOpenID() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("Create New Post");
		assertNoProblem();

		// it is not present
		assertLabelTextNotPresent("Error: Could not save post.");

		// edit the title
		{
			String target = getLabelIDForText("title");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Created Title");
		}
		
		// set the URL
		{
			String target = getLabelIDForText("author");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, OPENID_PAGE_SERVER);
		}
		
		// we try clicking on the button
		clickButtonWithText("Save");
		
		// there should be a problem
		assertProblem();
		
		// the warning has now shown
		assertLabelTextExactlyPresent("Error: Could not save post.");
		
		// and we can't view the news page
		viewNewsPageUnsuccessfully(sitemap);
		
		// TODO: add a test case to check that the Warning label disappears
		// when we authenticate successfully
	}

	/**
	 * Check that the page we are looking for actually contains the <link> we are looking for.
	 * 
	 * @throws Exception
	 */
	public void testPageServerHasLink() throws Exception {
		beginAt(OPENID_PAGE_SERVER);
		String source = getPageSource();
		assertContains("link rel=\"openid.server\" href=\"" + OPENID_PAGE_SERVER + "\"", source);
	}

	/**
	 * Check that the page we are looking for actually contains the <link> we are looking for.
	 * 
	 * @throws Exception
	 */
	public void testPageDelegateHasLink() throws Exception {
		beginAt(OPENID_PAGE_DELEGATE);
		String source = getPageSource();
		assertContains("link rel=\"openid.server\" href=\"" + OPENID_PAGE_SERVER + "\"", source);
		assertContains("link rel=\"openid.delegate\" href=\"" + OPENID_PAGE_DELEGATE + "\"", source);
	}

	/**
	 * Check that the page we are looking for actually contains the <link> we are looking for.
	 * 
	 * @throws Exception
	 */
	public void testPageServerPassHasLink() throws Exception {
		beginAt(OPENID_PAGE_SERVER_PASS);
		String source = getPageSource();
		assertContains("link rel=\"openid.server\" href=\"" + OPENID_PAGE_SERVER_PASS + "\"", source);
	}

	/**
	 * Check that the page we are looking for actually contains the <link> we are looking for.
	 * 
	 * @throws Exception
	 */
	public void testPageDelegatePassHasLink() throws Exception {
		beginAt(OPENID_PAGE_DELEGATE_PASS);
		String source = getPageSource();
		assertContains("link rel=\"openid.server\" href=\"" + OPENID_PAGE_SERVER_PASS + "\"", source);
		assertContains("link rel=\"openid.delegate\" href=\"" + OPENID_PAGE_DELEGATE_PASS + "\"", source);
	}

	/**
	 * Provide the correct OpenID URL, and actually go through authentication.
	 * Should allow instant login, and then we can actually save the
	 * post and view it.
	 * 
	 * @throws Exception
	 */
	public void testWithServer() throws Exception {
		doSuccessfulOpenID("testWithServer", OPENID_PAGE_SERVER, false);
	}
	
	/**
	 * This will use a delegate, but we still don't need to provide
	 * anything as the user-agent.
	 * 
	 * @throws Exception
	 */
	public void testWithDelegate() throws Exception {
		doSuccessfulOpenID("testWithDelegate", OPENID_PAGE_DELEGATE, false);
	}
	
	/**
	 * We need to provide a password.
	 * 
	 * @throws Exception
	 */
	public void testWithServerPassword() throws Exception {
		doSuccessfulOpenID("testWithServerPassword", OPENID_PAGE_SERVER_PASS, true);
	}
	
	/**
	 * We need to provide a password.
	 * 
	 * @throws Exception
	 */
	public void testWithDelegatePassword() throws Exception {
		doSuccessfulOpenID("testWithDelegatePassword", OPENID_PAGE_DELEGATE_PASS, true);
	}
	
	public void testWithIncorrectPassword() throws Exception {
		fail("TODO test cases with an incorrect password");
	}
	
	/**
	 * Try and use an invalid URL.
	 * 
	 */
	public void testUseInvalidURL() throws Exception {

		IFile sitemap = beginAtSitemapThenPage("Create New Post");
		assertNoProblem();
		
		// edit the title
		{
			String target = getLabelIDForText("title");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Created Title");
		}
		
		// set the URL (invalid)
		String invalid = "invalid";
		{
			String target = getLabelIDForText("author");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, invalid);
		}
		
		// there should be a button "Authenticate"
		clickButtonWithText("Authenticate");
		
		// but we should get an error
		assertProblem();
		
		// and we can't view the news page
		viewNewsPageUnsuccessfully(sitemap);
		
	}
	
	/**
	 * Actually does the authentication, etc.
	 * 
	 * @param title the news post title to use
	 * @param openid the OpenID URL to use
	 * @param needPass if true, we need to provide a password to the
	 * 		openID server (assumed above)
	 * @throws Exception 
	 */
	protected void doSuccessfulOpenID(String title, String openid, boolean needPass) throws Exception {

		IFile sitemap = beginAtSitemapThenPage("Create New Post");
		assertNoProblem();
		
		// edit the title
		{
			String target = getLabelIDForText("title");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Created Title");
		}
		
		// set the URL
		{
			String target = getLabelIDForText("author");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, openid);
		}
		
		// there should be a button "Authenticate"
		clickButtonWithText("Authenticate");
		
		if (needPass) {
			// we are now on another page, and need to provide a password
			System.out.println(getPageSource());
			setTextField("password", "test");
			submit();
		}
		
		// we should be directed back to our own page
		{
			String target = getLabelIDForText("title");
			assertLabeledFieldEquals(target, "Created Title");
		}
		{
			String target = getLabelIDForText("author");
			assertLabeledFieldEquals(target, openid);
		}
		
		// we try clicking on the button
		clickButtonWithText("Save");
		
		// there should NOT be a problem
		assertNoProblem();
		
		// now if we try and view the news page, we will succeed
		viewNewsPageSuccessfully(sitemap, "Created Title", openid);
		
	}
	
	/**
	 * Try and view the news page successfully, with the given
	 * expected title, and openID server URL.
	 * 
	 * @param sitemap
	 * @param title
	 * @param openidPageServer
	 * @throws Exception 
	 */
	protected void viewNewsPageSuccessfully(IFile sitemap, String title,
			String openidPageServer) throws Exception {
		
		gotoSitemapThenPage(sitemap, "View Posts");
		assertNoProblem();
		
		// check the content
		assertLabelTextExactlyPresent(title);
		
		// and the URL is also included
		assertLabelTextExactlyPresent(openidPageServer);
		
	}

	/**
	 * Try and view the news page unsuccessfully.
	 * 
	 * @param sitemap
	 * @throws Exception 
	 */
	protected void viewNewsPageUnsuccessfully(IFile sitemap) throws Exception {
	
		try {
			gotoSitemapThenPage(sitemap, "View Posts");
			fail("Should have failed");
		} catch (PhpRuntimeExceptionException e) {
			assertContains("No results found for query ", e.getMessage());
		}
		
	}

}