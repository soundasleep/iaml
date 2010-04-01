/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;



/**
 * 
 */
public class EmailSetWire extends EmailCodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(EmailSetWire.class);
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
	 * We can edit the form, and then send the e-mail, which has
	 * all of the details required.
	 * 
	 * @throws Exception
	 */
	public void testEditForm() throws Exception {
	
		// email log is empty
		waitForEmails();
		assertEmailEmpty();
		
		beginAtSitemapThenPage("Home");
		
		// email log is empty
		waitForEmails();
		assertEmailEmpty();
		
		{
			String target = getLabelIDForText("field 1");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "value 1");
		}
		{
			String target = getLabelIDForText("field 2");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "value 2");
		}
		{
			String target = getLabelIDForText("field 3");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "value 3");
		}
		
		assertNoProblem();
		
		// click the button
		clickButtonWithText("send email");
		assertNoProblem();
		
		// we should now have received an email
		waitForEmails();
		assertEmailNotEmpty();
		assertEquals(1, getEmailSize());
		InterceptedEmail email = getEmailFirst();
		
		assertEquals("source@openiaml.org", email.getFrom());
		assertEquals("target@openiaml.org", email.getTo());
		
		// no custom template is provided, so we get the default content
		assertEquals("TODO", email.getContent());
	
	}
	
	/**
	 * If we don't edit the form, we can still send an e-mail; the values
	 * are just empty.
	 * 
	 * @throws Exception
	 */
	public void testEmptyForm() throws Exception {
	
		// email log is empty
		waitForEmails();
		assertEmailEmpty();
		
		beginAtSitemapThenPage("Home");
	
		// click the button
		clickButtonWithText("send email");
		assertNoProblem();

		// we should now have received an email
		waitForEmails();
		assertEmailNotEmpty();
		assertEquals(1, getEmailSize());
		InterceptedEmail email = getEmailFirst();
		
		assertEquals("source@openiaml.org", email.getFrom());
		assertEquals("target@openiaml.org", email.getTo());
		
		// no custom template is provided, so we get the default content
		assertEquals("TODO", email.getContent());
	
	}
	
}
