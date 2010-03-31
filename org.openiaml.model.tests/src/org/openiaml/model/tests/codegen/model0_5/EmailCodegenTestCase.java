/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Allows e-mails sent and received to be intercepted by the testing framework.
 * 
 * @author jmwright
 *
 */
public abstract class EmailCodegenTestCase extends CodegenTestCase {

	/**
	 * If you get a null pointer when accessing this, you need to
	 * first call {@link #readEmails()}.
	 */
	private List<InterceptedEmail> emails;
	
	public InterceptedEmail getEmailFirst() {
		return emails.get(0);
	}

	public void assertEmailNotEmpty() {
		if (emails.size() != 0) {
			fail("Expected an e-mail, but got none");
		}
	}

	public void assertEmailEmpty() {
		assertEquals("Expected 0 emails, got: " + emails.size(), 0, emails.size());
	}

	public int getEmailSize() {
		return emails.size();
	}
	
	/**
	 * Get all of the intercepted emails. You may need to call
	 * {@link #readEmails()} before calling this method.
	 * 
	 * @return the list of intercepted emails
	 * @see #readEmails()
	 */
	public List<InterceptedEmail> getEmails() {
		return emails;
	}
	
	public static final String PHPMAILER_INCLUDE = "../../../workspace-iaml/org.openiaml.model.runtime.phpmailer/phpmailer/";
	
	/**
	 * Add a hook to send e-mails to a file, rather than actually sending e-mails.
	 */
	@Override
	protected Map<String, String> getRuntimeProperties() {
		Map<String,String> properties = super.getRuntimeProperties();

		// add these properties
		properties.put("email_handler", "file");
		properties.put("email_handler_file_destination", "emails.txt");
		properties.put("email_handler_phpmailer_include", PHPMAILER_INCLUDE);

		return properties;
	}

	/**
	 * Update the list of e-mails found.
	 * 
	 * @throws IOException
	 * @throws CoreException
	 */
	public void readEmails() throws IOException, CoreException {
		// reset
		emails = new ArrayList<InterceptedEmail>(); 
		
		IFile file = getProject().getFile("emails.txt");
		if (!file.exists()) {
			// empty
			return;
		}
		
		// read into properties
		Properties prop = new Properties();
		prop.load(file.getContents());
		
		// parse into e-mails
		// a "size" property will define how many emails there are
		int size = (int) (Integer) prop.get("size");
		for (int i = 0; i < size; i++) {
			InterceptedEmail ie = new InterceptedEmail();
			
			// any of these may be null, if it is not set when sent
			ie.setFrom( (String) prop.get("email." + i + ".from") );
			ie.setFromName( (String) prop.get("email." + i + ".fromName") );
			ie.setTo( (String) prop.get("email." + i + ".to") );
			ie.setToName( (String) prop.get("email." + i + ".toName") );
			ie.setSubject( (String) prop.get("email." + i + ".subject") );
			ie.setContent( (String) prop.get("email." + i + ".content") );
			
			// add it
			emails.add(ie);
		}
		
		// done
	}
	
	/**
	 * Wait for any unprocessed e-mails to send.
	 * Once done, {@link #readEmails() reads the e-mails from disk}.
	 * 
	 * @throws CoreException 
	 * @throws IOException 
	 */
	public void waitForEmails() throws IOException, CoreException {
		// does nothing
		readEmails();
	}
	
}
