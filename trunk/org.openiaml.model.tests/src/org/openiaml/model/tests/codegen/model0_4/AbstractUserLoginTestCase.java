/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Helper methods for setting up a database, and automatically logging into,
 * an application protected with a LoginHandler.
 * 
 * @author jmwright
 *
 */
public abstract class AbstractUserLoginTestCase extends CodegenTestCase {
	
	/**
	 * Begin the application using {@link #beginAtSitemapThenPage(IFile, String, String)},
	 * and then go to the "login" page and attempt to login with the
	 * specified e-mail and password.
	 * 
	 * This method does not check whether the login was
	 * successful or not.
	 * 
	 * @param email
	 * @param password
	 * @return the sitemap file
	 */
	public IFile doStandardLoginAs(String email, String password) throws Exception {
		IFile sitemap = beginAtSitemapThenPage("login");
		
		String emailField = getLabelIDForText("email");
		assertLabeledFieldEquals(emailField, "");
		setLabeledFormElementField(emailField, email);
	
		String passwordField = getLabelIDForText("password");
		assertLabeledFieldEquals(passwordField, "");
		setLabeledFormElementField(passwordField, password);
		
		// submit form
		submit();
		waitForAjax();
		
		return sitemap;
	}
	
	/**
	 * <em>Continue</em> the application using {@link #gotoSitemapThenPage(IFile, String)},
	 * and then go to the "login" page and attempt to login with the
	 * specified e-mail and password.
	 * 
	 * This method does not check whether the login was
	 * successful or not.
	 * 
	 * @param email
	 * @param password
	 * @return the sitemap file
	 */
	public IFile doStandardLoginAs(IFile sitemap, String email, String password) throws Exception {
		gotoSitemapThenPage(sitemap, "login");
		
		String emailField = getLabelIDForText("email");
		assertLabeledFieldEquals(emailField, "");
		setLabeledFormElementField(emailField, email);
	
		String passwordField = getLabelIDForText("password");
		assertLabeledFieldEquals(passwordField, "");
		assertLabeledFieldEquals(passwordField, password);
		
		// submit form
		submit();
		waitForAjax();
		
		return sitemap;
	}
	
	public void testDatabaseSetup() throws Exception {
		fail("TODO need to implement LoginHandler database initialisation.");
	}
	
}
