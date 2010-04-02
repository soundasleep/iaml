/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;

import java.io.File;

import org.eclipse.core.resources.IFile;

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
	 * Should this test case check for intercepted emails?
	 * 
	 * @return true by default
	 */
	protected boolean doEmailChecks() {
		return true;
	}
	
	/**
	 * We can edit the form, and then send the e-mail, which has
	 * all of the details required. Returns the intercepted email, which can
	 * then be tested.
	 * @return 
	 * 
	 * @return the intercepted email
	 * @throws Exception
	 */
	private InterceptedEmail fillOutForm() throws Exception {
		
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
		if (doEmailChecks()) { 
			assertEmailNotEmpty();
			assertEquals(1, getEmailSize());
			InterceptedEmail email = getEmailFirst();
			
			return email;
		} else {
			return null;
		}
		
	}
	
	/**
	 * We can edit the form, and then send the e-mail, which has
	 * all of the details required.
	 * 
	 * @throws Exception
	 */
	public void testEditForm() throws Exception {
	
		InterceptedEmail email = fillOutForm();
		if (!doEmailChecks()) return;

		assertEquals("source@openiaml.org", email.getFrom());
		assertEquals("target@openiaml.org", email.getTo());
		
		// no custom template is provided, so we get the default content
		assertEquals("from: source@openiaml.org\nsubject: [automated] EmailSetWire test case\nto: target@openiaml.org\nfield 1: value 1\nfield 2: value 2\nfield 3: value 3\n", email.getContent());
	
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

		if (!doEmailChecks()) return;
		assertEmailNotEmpty();
		assertEquals(1, getEmailSize());
		InterceptedEmail email = getEmailFirst();
		
		assertEquals("source@openiaml.org", email.getFrom());
		assertEquals("target@openiaml.org", email.getTo());
		
		// no custom template is provided, so we get the default content
		assertEquals("from: source@openiaml.org\nsubject: [automated] EmailSetWire test case\nto: target@openiaml.org\nfield 1: \nfield 2: \nfield 3: \n", email.getContent());
	
	}
	
	/**
	 * Tests adding a custom template for the {@model Email}.
	 * 
	 * @example Email
	 * 		Using a custom PHP template for rendering {@model Email}s.
	 * 
	 * @implementation Email
	 * 		If a PHP file called <code><strong>CUSTOM_ROOT</strong>/templates/{@model Email#id}.php</code> is
	 * 		readable at runtime, this template file will be used to render the {@model Email} instead
	 * 		of the default template.
	 * 
	 * @throws Exception
	 */
	public void testCustomTemplate() throws Exception {
		
		// copy over a custom template first
		File sourceFile = new File( ROOT + "codegen/model0_5/email.php" );
		IFile targetFile = getProject().getFile("templates/email.php");
		copyFileIntoWorkspace(sourceFile, targetFile);
		
		// proceed as normal
		InterceptedEmail email = fillOutForm();
		if (!doEmailChecks()) return;

		assertEquals("source@openiaml.org", email.getFrom());
		assertEquals("target@openiaml.org", email.getTo());
		
		// our custom template should have been used
		assertEquals("This e-mail, from source@openiaml.org to target@openiaml.org, has these field values: value 1, value 2, value 3.", email.getContent());
		
	}
	
}
