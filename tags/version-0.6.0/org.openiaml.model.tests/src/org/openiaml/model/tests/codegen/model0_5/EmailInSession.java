/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;

import org.eclipse.core.resources.IFile;


/**
 * 
 */
public class EmailInSession extends EmailCodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(EmailInSession.class);
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
	 * Can access the session page without a problem.
	 * @throws Exception
	 */
	public void testSessionPage() throws Exception {
		beginAtSitemapThenPage("Send a Session Email");
		assertNoProblem();
	}

	/**
	 * Initially, since the Email has not been modified, sending the e-mail
	 * will fail.
	 * 
	 * @throws Exception
	 */
	public void testSessionSendInitial() throws Exception {
		
		// email log is empty
		waitForEmails();
		assertEmailEmpty();
		
		beginAtSitemapThenPage("Send a Session Email");
		assertNoProblem();
		
		clickButtonWithText("send email");
		
		// nothing should have happened
		waitForEmails();
		assertEmailEmpty();
		
	}
	
	/**
	 * If we set 'to' in the session, we can then send the e-mail.
	 *  
	 * @throws Exception
	 */
	public void testSessionSendModified() throws Exception {
		
		// email log is empty
		waitForEmails();
		assertEmailEmpty();

		beginAtSitemapThenPage("Send a Session Email");
		assertNoProblem();

		// set the 'to' address
		{
			String target = getLabelIDForText("set to");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "target@openiaml.org");
		}
		
		clickButtonWithText("send email");
		
		// we should now have received an email
		waitForEmails();
		assertEmailNotEmpty();
		assertEquals(1, getEmailSize());
		InterceptedEmail email = getEmailFirst();
		
		assertEquals("target@openiaml.org", email.getTo());
		
	}
	
	/**
	 * We set 'to' in the session, but then reset it; thus, the value
	 * is lost (and reset)
	 *  
	 * @throws Exception
	 */
	public void testSessionModifyThenResetSession() throws Exception {
		
		// email log is empty
		waitForEmails();
		assertEmailEmpty();

		IFile sitemap = beginAtSitemapThenPage("Send a Session Email");
		assertNoProblem();
		
		// set the 'to' address
		{
			String target = getLabelIDForText("set to");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "target@openiaml.org");
		}
		
		// reset session
		restartSession(sitemap, "Send a Session Email");
		assertNoProblem();
		
		// and now click the button
		clickButtonWithText("send email");
		
		// but the value has reset
		waitForEmails();
		assertEmailEmpty();
		
	}
	
	/**
	 * Set it, lose the session, then set it again.
	 *  
	 * @throws Exception
	 */
	public void testSessionResetThenSetAgain() throws Exception {
		
		// email log is empty
		waitForEmails();
		assertEmailEmpty();

		IFile sitemap = beginAtSitemapThenPage("Send a Session Email");
		assertNoProblem();
		
		// set the 'to' address
		{
			String target = getLabelIDForText("set to");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "target@openiaml.org");
		}
		
		// reset session
		restartSession(sitemap, "Send a Session Email");
		assertNoProblem();
		
		// set the 'to' address again
		{
			String target = getLabelIDForText("set to");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "new@openiaml.org");
		}
		
		// and now click the button
		clickButtonWithText("send email");
		
		// we should now have received an email
		waitForEmails();
		assertEmailNotEmpty();
		assertEquals(1, getEmailSize());
		InterceptedEmail email = getEmailFirst();
		
		assertEquals("new@openiaml.org", email.getTo());
		
	}
	
}
