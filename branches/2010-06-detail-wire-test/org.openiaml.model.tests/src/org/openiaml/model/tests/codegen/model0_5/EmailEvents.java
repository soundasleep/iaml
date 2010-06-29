/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;



/**
 * Tests the onFailure and onSent events.
 * 
 * @implementation Email
 * 		If an {@model Email} fails to send, the {@model Email#onFailure}
 * 		{@model EventTrigger event} is executed.
 * @implementation Email
 * 		If an {@model Email} sends successfully, the {@model Email#onSent}
 * 		{@model EventTrigger event} is executed.
 */
public class EmailEvents extends EmailCodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(EmailEvents.class);
	}

	/**
	 * The home page can be accessed.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// the status is empty
		{
			String target = getLabelIDForText("status");
			assertLabeledFieldEquals(target, "");
		}
	}
	
	/**
	 * There is a button called 'send passing email'. If we click it,
	 * an e-mail is sent successfully, and the onSent status is
	 * triggered.
	 * 
	 * @throws Exception
	 */
	public void testOnSent() throws Exception {
	
		beginAtSitemapThenPage("Home");
		assertButtonPresentWithText("send passing email");
		clickButtonWithText("send passing email");
		
		assertNoProblem();
		
		// wait for emails to send
		waitForEmails();
		
		// we should have now received an email
		assertEmailNotEmpty();
		assertEquals(1, getEmailSize());
		
		// and the status should have been changed, now that we have received it
		{
			String target = getLabelIDForText("status");
			assertLabeledFieldEquals(target, "passing email sent");
		}
		
	}
	
	/**
	 * There is a button called 'send failing email'. If we click it,
	 * an e-mail is sent successfully, and the onSent status is
	 * triggered.
	 * 
	 * @throws Exception
	 */
	public void testOnFailure() throws Exception {
	
		beginAtSitemapThenPage("Home");
		assertButtonPresentWithText("send failing email");
		clickButtonWithText("send failing email");
		
		assertNoProblem();
		
		// wait for emails to send
		waitForEmails();
		
		// we should not have received an email
		assertEmailEmpty();
		
		// and the status should have been changed, now that we have received it
		{
			String target = getLabelIDForText("status");
			assertLabeledFieldEquals(target, "failing email failed");
		}
		
	}
	

}
