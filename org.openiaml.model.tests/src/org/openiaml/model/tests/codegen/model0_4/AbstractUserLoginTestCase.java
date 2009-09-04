/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
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
	 * @see #actualDoStandardLoginAs(String, String)
	 * @param email
	 * @param password
	 * @return the sitemap file
	 */
	public IFile doStandardLoginAs(String email, String password) throws Exception {
		IFile sitemap = beginAtSitemapThenPage("login");
		
		actualDoStandardLoginAs(email, password);
		
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
	 * @see #actualDoStandardLoginAs(String, String)
	 * @param email
	 * @param password
	 * @return the sitemap file
	 */
	public IFile doStandardLoginAs(IFile sitemap, String email, String password) throws Exception {
		gotoSitemapThenPage(sitemap, "login");
		
		actualDoStandardLoginAs(email, password);
		
		return sitemap;
	}
	
	/**
	 * Actually do the login.
	 * 
	 * @see #doStandardLoginAs(String, String)
	 * @see #doStandardLoginAs(IFile, String, String)
	 */
	protected void actualDoStandardLoginAs(String email, String password) throws Exception {
		String emailField = getLabelIDForText("email");
		assertLabeledFieldEquals(emailField, "");
		setLabeledFormElementField(emailField, email);
	
		String passwordField = getLabelIDForText("password");
		assertLabeledFieldEquals(passwordField, "");
		setLabeledFormElementField(passwordField, password);
		
		// submit form
		submit();
		waitForAjax();
	}
	
	@Override
	protected String getDatabaseName() {
		return "output/users_1233afe655c_1c.db";
	}

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE User (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(64) NOT NULL, email VARCHAR(64) NOT NULL, password VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO User (generated_primary_key, name, email, password) VALUES (12, 'User', 'user@openiaml.org', 'user')");
		s.add("INSERT INTO User (generated_primary_key, name, email, password) VALUES (22, 'Default Role', 'default@openiaml.org', 'test123')");
		s.add("INSERT INTO User (generated_primary_key, name, email, password) VALUES (32, 'Registered User', 'registered@openiaml.org', 'test123')");
		return s;
	}
	
	/**
	 * Check that we have created the correct database in
	 * {@link #getDatabaseName()}; i.e. check that no more
	 * than one database exists in the output folder
	 * after visiting the home page.
	 * 
	 */
	public void testCorrectDatabase() throws Exception {
		beginAtSitemapThenPage("Home");
		
		// the output database name should start with 'output/'
		assertTrue("Database name '" + getDatabaseName() + "' should start with 'output/'", getDatabaseName().startsWith("output/"));
		String databaseName = getDatabaseName().substring(getDatabaseName().lastIndexOf("/") + 1);
		
		refreshProject();
		IResource[] resources = getProject().getFolder("output").members(false);
		for (IResource res : resources) {
			if (res.getName().endsWith(".db")) {
				if (!res.getName().equals("stored_events.db") && !res.getName().endsWith(databaseName)) {
					// found an extra database
					fail("Found an extra database: " + res.getName() + " (" + res + ")");
				}
			}
		}
		
		// check that the database has been initialised normally
		assertTrue("Database was not initialised by default", hasDatabaseBeenInitialised());
	}
	
}
