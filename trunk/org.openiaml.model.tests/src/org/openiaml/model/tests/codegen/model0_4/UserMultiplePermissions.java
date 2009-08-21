/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.eclipse.core.resources.IFile;

/**
 * A User which has a role containing both permissions
 * can access a page that requires
 * both permissions.
 * 
 * @author jmwright
 *
 */
public class UserMultiplePermissions extends AbstractUserLoginTestCase {
	
	private final String EMAIL = "both_roles@openiaml.org";
	private final String PASSWORD = "test123";
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(UserMultiplePermissions.class);
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
	 * We cannot access the protected page as a Guest
	 * because it does not have the appropriate Role.
	 * 
	 * @throws Exception
	 */
	public void testGuest() throws Exception {
		IFile sitemap = doStandardLoginAs("guest@openiaml.org", "guest");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "target");
		assertTitleNotSame("target");
		assertProblem();		// who knows where we are?
	}
	
	/**
	 * If we do log in properly, we can access the target page.
	 * 
	 * @throws Exception
	 */
	public void testAccessTarget() throws Exception {
		IFile sitemap = doStandardLoginAs(EMAIL, PASSWORD);
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "target");
		assertNoProblem();
	}
	
}
