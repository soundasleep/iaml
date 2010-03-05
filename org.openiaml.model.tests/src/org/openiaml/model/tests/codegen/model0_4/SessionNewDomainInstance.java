/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.AssertionFailedError;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

/**
 * DomainObjectInstances have a "new" operation, which when executed
 * will discard the current instance and make a new instance as
 * specified by the NewInstanceWire. 
 * 
 * @author jmwright
 *
 */
public class SessionNewDomainInstance extends DatabaseCodegenTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SessionNewDomainInstance.class);
	}
	
	/**
	 * Make sure that the form for editing exists and is
	 * accessible.
	 * 
	 * @throws Exception
	 */
	public void testFormExists() throws Exception {
		beginAtSitemapThenPage("container");
		assertNoProblem();
		
		// we should have an 'email' text field that is empty
		String email = getLabelIDForText("email");
		assertLabeledFieldEquals(email, "");
		
		// and a button called 'make a new user'
		assertButtonPresentWithText("make a new user");
	}
	
	/**
	 * If we make a change to the e-mail, it stays within the session.
	 * 
	 * @throws Exception
	 */
	public void testEditWithinSession() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("container");
		assertNoProblem();
		
		// we should have an 'email' text field that is empty
		String test = new Date() + "@openiaml.org";
		{
			String email = getLabelIDForText("email");
			assertLabeledFieldEquals(email, "");
			
			// set email
			setLabeledFormElementField(email, test);
			
			// it should stay
			assertLabeledFieldEquals(email, test);
		}
		
		// reload this page
		gotoSitemapThenPage(sitemap, "container");
		
		// it should remain
		{
			String email = getLabelIDForText("email");
			assertLabeledFieldEquals(email, test);
		}
		
		// restart the session
		sitemap = beginAtSitemapThenPage("container");
		{
			// the email field should have been reset
			String email = getLabelIDForText("email");
			assertLabeledFieldEquals(email, "");
		}
	}
	
	/**
	 * We can press the new button which will clear the
	 * email field.
	 * 
	 * @throws Exception
	 */
	public void testNewButtonClearsField() throws Exception {
		try {
			beginAtSitemapThenPage("container");
			assertNoProblem();
			
			// we should have an 'email' text field that is empty
			String email = getLabelIDForText("email");
			assertLabeledFieldEquals(email, "");
			
			// set email
			String test = "test@openiaml.org";
			setLabeledFormElementField(email, test);
			
			// it should stay
			assertLabeledFieldEquals(email, test);
			
			// press the button
			clickButtonWithText("make a new user");
			waitForAjax();
			
			// no problems
			assertNoProblem();
			
			// text field should have been reset
			assertLabeledFieldEquals(email, "");
		} catch (AssertionFailedError e) {
			throwDebugInformation(e);
		}
	}
	
	/**
	 * We can press the new button even when we haven't 
	 * entered in any text.
	 * 
	 * @throws Exception
	 */
	public void testInitialNewButton() throws Exception {
		beginAtSitemapThenPage("container");
		assertNoProblem();
		
		// press the button many times
		clickButtonWithText("make a new user");
		clickButtonWithText("make a new user");
		clickButtonWithText("make a new user");
		clickButtonWithText("make a new user");
		
		// we should have an 'email' text field that is empty
		String email = getLabelIDForText("email");
		assertLabeledFieldEquals(email, "");
		
		// set email
		String test = "test@openiaml.org";
		setLabeledFormElementField(email, test);
		
		// it should stay
		assertLabeledFieldEquals(email, test);
	}
	
	
	/**
	 * We can create many instances.
	 * 
	 * @throws Exception
	 */
	public void testManyNewInstances() throws Exception {
		beginAtSitemapThenPage("container");
		assertNoProblem();
		
		String[] emails = new String[] { 
			"test1@openiaml.org", 
			"test2@openiaml.org", 
			"test3@openiaml.org",
			"test4@openiaml.org",
			"test5@openiaml.org" };
		
		// none of these emails should exist in the db
		assertNoEmailsExistInDatabase(emails);
		
		for (String test : emails) {
			String email = getLabelIDForText("email");
			assertLabeledFieldEquals(email, "");
			
			// set email
			setLabeledFormElementField(email, test);
			
			// it should stay
			assertLabeledFieldEquals(email, test);
			
			// press the button
			clickButtonWithText("make a new user");
			waitForAjax();
		}

		// they should have all been inserted into the database
		assertEmailsExistInDatabase(emails);
		
	}

	/**
	 * Check that the database does not contain the given emails.
	 * 
	 * @param emails
	 * @throws Exception 
	 */
	private void assertNoEmailsExistInDatabase(String[] emails) throws Exception {
		for (String email : emails) {
			// check the database
			{
				ResultSet rs = executeQuery("SELECT * FROM User WHERE email='" + email + "'");
				assertFalse(rs.next());	// none
			}
		}
	}

	/**
	 * Check that the database only contains one instance of each of these emails.
	 * 
	 * @param emails
	 * @throws Exception 
	 */
	private void assertEmailsExistInDatabase(String[] emails) throws Exception {
		for (String email : emails) {
			// check the database
			{
				ResultSet rs = executeQuery("SELECT * FROM User WHERE email='" + email + "'");
				assertTrue(rs.next());
				assertEquals(email, rs.getString("email"));
				assertFalse(rs.next());	// only one
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.codegen.DatabaseCodegenTestCase#getDatabaseInitialisers()
	 */
	@Override
	protected List<String> getDatabaseInitialisers() {
		return new ArrayList<String>();	// empty
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.codegen.DatabaseCodegenTestCase#getDatabaseName()
	 */
	@Override
	protected String getDatabaseName() {
		return "output/model_1230bdbae76_25a.db";
	}
		
}
