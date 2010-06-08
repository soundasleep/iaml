/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;



/**
 * @example Email
 * 		Changing the various {@model Property properties} of an {@model Email} 
 * 		at runtime.
 */
public class EmailChangeProperties extends EmailCodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(EmailChangeProperties.class);
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
	 * Default settings.
	 * 
	 * @throws Exception
	 */
	public void testDefault() throws Exception {

		checkBefore();
		checkAfter();
		
		InterceptedEmail email = getEmailFirst();
		
		assertEquals("from@openiaml.org", email.getFrom());
		assertEquals("From name", email.getFromName());
		assertEquals("to@openiaml.org", email.getTo());
		assertEquals("To name", email.getToName());
		assertEquals("Subject", email.getSubject());
		assertEquals("Content", email.getContent());
		
	}
	
	public void testChangeTo() throws Exception {

		checkBefore();
		{
			String target = getLabelIDForText("to", "to name");	// not 'to name'!
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "new@openiaml.org");
		}
		{
			String target = getLabelIDForText("to name");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "new2@openiaml.org");
		}
		checkAfter();
		
		InterceptedEmail email = getEmailFirst();
		
		assertEquals("from@openiaml.org", email.getFrom());
		assertEquals("From name", email.getFromName());
		assertEquals("new@openiaml.org", email.getTo());
		assertEquals("new2@openiaml.org", email.getToName());
		assertEquals("Subject", email.getSubject());
		assertEquals("Content", email.getContent());
		
	}
	
	public void testChangeFrom() throws Exception {

		checkBefore();
		{
			String target = getLabelIDForText("from", "from name");	// not 'from name'!
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "new@openiaml.org");
		}
		{
			String target = getLabelIDForText("from name");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "new2@openiaml.org");
		}
		checkAfter();
		
		InterceptedEmail email = getEmailFirst();
		
		assertEquals("new@openiaml.org", email.getFrom());
		assertEquals("new2@openiaml.org", email.getFromName());
		assertEquals("to@openiaml.org", email.getTo());
		assertEquals("To name", email.getToName());
		assertEquals("Subject", email.getSubject());
		assertEquals("Content", email.getContent());
		
	}
	
	public void testChangeSubjectAndContent() throws Exception {

		checkBefore();
		{
			String target = getLabelIDForText("subject");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "new@openiaml.org");
		}
		{
			String target = getLabelIDForText("content");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "new2@openiaml.org");
		}
		checkAfter();
		
		InterceptedEmail email = getEmailFirst();
		
		assertEquals("from@openiaml.org", email.getFrom());
		assertEquals("From name", email.getFromName());
		assertEquals("to@openiaml.org", email.getTo());
		assertEquals("To name", email.getToName());
		assertEquals("new@openiaml.org", email.getSubject());
		assertEquals("new2@openiaml.org", email.getContent());
		
	}
	
	/**
	 * Check that the e-mails are empty; navigate to home page.
	 * 
	 * @throws Exception
	 */
	private void checkBefore() throws Exception {
		
		// email log is empty
		waitForEmails();
		assertEmailEmpty();
		
		beginAtSitemapThenPage("Home");
		
	}
	
	/**
	 * Click the button to send the e-mail; wait for the e-mail to send;
	 * download the latest e-mail.
	 * 
	 * @throws Exception
	 */
	private void checkAfter() throws Exception {

		assertButtonPresentWithText("send email");
		clickButtonWithText("send email");
		
		assertNoProblem();
		
		// we should now have received an email
		waitForEmails();
		assertEmailNotEmpty();
		assertEquals(1, getEmailSize());
		
	}


}
