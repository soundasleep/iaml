/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;


/**
 * A simple test case for sending an e-mail, without wanting to change any
 * properties.
 * 
 * @example Email
 * 		Sending an {@model Email} after clicking a {@model Button}.
 */
public class EmailExplicit extends EmailCodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(EmailExplicit.class);
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
	 * There is a button called 'send an e-mail'. If we click it,
	 * an e-mail is sent.
	 * 
	 * @throws Exception
	 */
	public void testSendEmail() throws Exception {
	
		// email log is empty
		waitForEmails();
		assertEmailEmpty();
		
		beginAtSitemapThenPage("Home");
		assertButtonPresentWithText("send an email");
		clickButtonWithText("send an email");
		
		assertNoProblem();
		
		// we should now have received an email
		waitForEmails();
		assertEmailNotEmpty();
		assertEquals(1, getEmailSize());
		
		InterceptedEmail email = getEmailFirst();
		
		assertEquals("source@openiaml.org", email.getFrom());
		assertEquals("Source", email.getFromName());
		assertEquals("test@openiaml.org", email.getTo());
		assertEquals("Test User", email.getToName());
		assertEquals("A test e-mail", email.getSubject());
		
		assertEquals("hello, world!", email.getContent());
		
	}
	
	/**
	 * If we click the button twice, the email is sent twice.
	 * 
	 * @throws Exception
	 */
	public void testSendEmailTwice() throws Exception {
	
		// email log is empty
		waitForEmails();
		assertEmailEmpty();
		
		beginAtSitemapThenPage("Home");
		assertButtonPresentWithText("send an email");
		clickButtonWithText("send an email");
		assertNoProblem();
		
		// click it again
		clickButtonWithText("send an email");
		assertNoProblem();
		
		// we should now have received two emails
		waitForEmails();
		assertEmailNotEmpty();
		assertEquals(2, getEmailSize());
		
		for (InterceptedEmail email : getEmails()) {
			assertEquals("source@openiaml.org", email.getFrom());
			assertEquals("Source", email.getFromName());
			assertEquals("test@openiaml.org", email.getTo());
			assertEquals("Test User", email.getToName());
			assertEquals("A test e-mail", email.getSubject());
			
			assertEquals("hello, world!", email.getContent());
		}
		
	}


}
