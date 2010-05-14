/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_1;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * @inference EntryGate,Label,RequiresEdge
 *		A {@model EntryGate} can {@model RequiresEdge require} a given {@model Label} to be
 *		set, creating a {@model Frame} that must be filled in before the containing {@model Scope}
 *		can be accessed.
 *
 * @example EntryGate
 * 		Using an {@model EntryGate} to {@model RequiresEdge require} that a given
 * 		contained {@model Label} is set.
 */
public class EntryGateRequiresOpenID extends CodegenTestCase {
	
	// an OpenID that doesn't require passwords
	final String openid = OpenIDAsAuthor.OPENID_PAGE_SERVER;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(EntryGateRequiresOpenID.class);
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
	 * Trying to access the session without the ID does not permit access to the session.
	 * 
	 * @throws Exception
	 */
	public void testAccessWithoutID1() throws Exception {
		
		// we try and access 'Secure Page', but we're redirected to 'Provide Current OpenID'
		beginAtSitemapThenPage("Secure Page", "Provide Current OpenID");
		
		// there are no problems though
		assertNoProblem();
		
	}
	
	/**
	 * We try to access a protected page, but then we enter in a valid OpenID;
	 * once we return we can resume the gate and continue as normal.
	 * 
	 * @throws Exception
	 */
	public void testAccessWithoutIDThenProvideIt() throws Exception {
		doTestAccessWithoutIDThenProvideIt();
	}
	
	protected IFile doTestAccessWithoutIDThenProvideIt() throws Exception {

		// we try and access 'Secure Page', but we're redirected to 'Provide Current OpenID'
		IFile sitemap = beginAtSitemapThenPage("See Your OpenID", "Provide Current OpenID");
		assertNoProblem();
		
		// we will have a text field to enter in our OpenID
		// set the URL
		{
			String target = getLabelIDForText("Current OpenID");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, openid);
		}
		
		// click the 'Authenticate' button
		clickButtonWithText("Authenticate");
		assertNoProblem();
		
		// we should return back to the Provide page
		assertTitleEquals("Provide Current OpenID");
		
		// the URL is still there
		{
			String target = getLabelIDForText("Current OpenID");
			assertLabeledFieldEquals(target, openid);
		}
		
		// we can click the 'Continue' button
		clickButtonWithText("Continue");
		assertNoProblem();
		
		// we are now on the view ID page
		assertTitleEquals("See Your OpenID");
		
		// our OpenID is provided
		assertLabelTextExactlyPresent(openid);
		
		return sitemap;
		
	}
	
	/**
	 * We try to access a protected page, but then we enter in a valid OpenID; but, we
	 * don't validate, so we can't resume the page.
	 * 
	 * @throws Exception
	 */
	public void testDontAuthenticate() throws Exception {
		
		// we try and access 'Secure Page', but we're redirected to 'Provide Current OpenID'
		beginAtSitemapThenPage("See Your OpenID", "Provide Current OpenID");
		
		// we will have a text field to enter in our OpenID
		// set the URL
		{
			String target = getLabelIDForText("Current OpenID");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, openid);
		}
		
		// DON'T click the Authenticate button
		// we click the 'Continue' button
		clickButtonWithText("Continue");
		
		// but go back to the same page
		assertTitleEquals("Provide Current OpenID");
		
		// our OpenID is not a label anywhere
		assertLabelTextNotPresent(openid);
		
	}
	
	/**
	 * We authenticate, then leave the Scope; we should be able to go back into
	 * the scope, as the Session stores the authentication.
	 * 
	 * @throws Exception
	 */
	public void testAuthenticateThenLeaveScope() throws Exception {
		
		// authenticate as normal
		IFile sitemap = doTestAccessWithoutIDThenProvideIt();
		
		// go back home
		gotoSitemapThenPage(sitemap, "Home");
		assertNoProblem();
		
		// try go back into the session
		gotoSitemapThenPage(sitemap, "Secure Page");
		assertNoProblem();
		
	}
	
	/**
	 * We authenticate, but then reset the Session; we have to reauthenticate
	 * before we can get back into the Session.
	 * 
	 * @throws Exception
	 */
	public void testAuthenticateThenRestartSession() throws Exception {

		// authenticate as normal
		IFile sitemap = doTestAccessWithoutIDThenProvideIt();
		
		// restart session
		restartSession(sitemap, "Home");
		
		// try and view the same page again; we have to reauthenticate
		gotoSitemapThenPage(sitemap, "See Your OpenID", "Provide Current OpenID");
		assertNoProblem();
		
		// our OpenID is not a label anywhere
		assertLabelTextNotPresent(openid);
		
	}
	
	/**
	 * We can authenticate first, so we are never redirected.
	 * 
	 * @throws Exception
	 */
	public void testAuthenticateFirst() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Provide Current OpenID");
		assertNoProblem();
		
		// set the URL
		{
			String target = getLabelIDForText("Current OpenID");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, openid);
		}
		
		// click the 'Authenticate' button
		clickButtonWithText("Authenticate");
		assertNoProblem();
		
		// we should return back to the Provide page
		assertTitleEquals("Provide Current OpenID");
		
		// we can now go directly to See Your OpenID
		gotoSitemapThenPage(sitemap, "See Your OpenID");
		
		// our OpenID is provided
		assertLabelTextExactlyPresent(openid);

	}

}
