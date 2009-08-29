/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

/**
 * Helper methods for setting up a database, and automatically logging into,
 * an application protected with a LoginHandler.
 * 
 * @author jmwright
 *
 */
public abstract class AbstractUserLoginTestCase extends DatabaseCodegenTestCase {
	
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
	
	@Override
	protected String getDatabaseName() {
		return "output/users_1233afe655c_1c.db";
	}

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE Guest (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(64) NOT NULL, email VARCHAR(64) NOT NULL, password VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO Guest (generated_primary_key, name, email, password) VALUES (12, 'Guest', 'guest@openiaml.org', 'guest')");
		s.add("INSERT INTO Guest (generated_primary_key, name, email, password) VALUES (22, 'Default Role', 'default@openiaml.org', 'test123')");
		s.add("INSERT INTO Guest (generated_primary_key, name, email, password) VALUES (32, 'Registered User', 'registered@openiaml.org', 'test123')");
		return s;
	}
	
}
