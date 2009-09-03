/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.eclipse.core.resources.IFile;

/**
 * Test Login Handler [type=user].
 * 
 * @author jmwright
 *
 */
public class LoginHandlerUser extends AbstractUserLoginTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(LoginHandlerUser.class);
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
	
	@Override
	protected String getDatabaseName() {
		return "output/users_1233b454017_f.db";
	}

	/**
	 * We can't access the login handler protected page
	 * without logging in.
	 * 
	 * @throws Exception
	 */
	public void testInitialAccess() throws Exception {
		IFile sitemap = getSitemap();
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");

		clickLinkWithText("target");
		assertProblem();	// who knows where we are?
	}
	
	/**
	 * Try logging in, and then accessing the 
	 * protected page.
	 * 
	 * @throws Exception
	 */
	public void testLogin() throws Exception {
		IFile sitemap = doStandardLoginAs("user@openiaml.org", "user");
		assertNoProblem();
		
		// now go to the secured page
		gotoSitemapThenPage(sitemap, "target");
		
		// there should now be an 'email' field
		{
			String email = getLabelIDForText("email");
			assertLabeledFieldEquals(email, "user@openiaml.org");
		
			// TODO a password field will exist until issue 113 is resolved
			String password = getLabelIDForText("password");
			assertLabeledFieldEquals(password, "user");
		}
	}
		
}
