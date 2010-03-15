/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_4;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests the representation of the different data types for
 * InputTextFields; for example, we cannot enter in strings
 * into a number field. The fields are cleared if it fails.
 */
public class InputTextFieldDataType extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(InputTextFieldDataType.class);
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
	 * Test the text field 'fieldName' by entering in the string
	 * 'input'; however, we expect the result will actually
	 * be 'expected', and this will persist across reloading the page
	 * and reloading the session.
	 * 
	 * @param fieldName
	 * @param input
	 * @param expected
	 * @throws Exception
	 */
	protected void doTypeTest(String fieldName, String input, String expected) throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Home");
		{
			String target = getLabelIDForText(fieldName);
			assertLabeledFieldEquals(target, "");	// empty
			
			// we can set it to a string
			setLabeledFormElementField(target, input);
			assertLabeledFieldEquals(target, expected);
		}
		
		// if we reload the page, it will still be there
		reloadPage(sitemap, "Home");
		{
			String target = getLabelIDForText(fieldName);
			assertLabeledFieldEquals(target, expected);
		}
		
		// if we restart the session, it will still be there
		restartSession(sitemap, "Home");
		{
			String target = getLabelIDForText(fieldName);
			assertLabeledFieldEquals(target, expected);
		}
		
	}
	
	/**
	 * Test setting 'default' type to a String.
	 */
	public void testDefaultString() throws Exception {
		doTypeTest("Default", "Hello, world!", "Hello, world!");
	}
	
	public void testDefaultEmail() throws Exception {
		doTypeTest("Default", "hello@openiaml.org", "hello@openiaml.org");
	}
	
	public void testDefaultDate() throws Exception {
		doTypeTest("Default", "25 dec 1998", "25 dec 1998");
	}
	
	public void testDefaultDate2() throws Exception {
		String today = rfc2822(new Date());
		doTypeTest("Default", today, today);
	}

	public void testDefaultInteger() throws Exception {
		doTypeTest("Default", "346", "346");
	}
	
	/**
	 * Test setting 'String' type to a String.
	 */
	public void testStringString() throws Exception {
		doTypeTest("String", "Hello, world!", "Hello, world!");
	}
	
	public void testStringEmail() throws Exception {
		doTypeTest("String", "hello@openiaml.org", "hello@openiaml.org");
	}
	
	public void testStringDate() throws Exception {
		doTypeTest("String", "25 dec 1998", "25 dec 1998");
	}
	
	public void testStringDate2() throws Exception {
		String today = rfc2822(new Date());
		doTypeTest("String", today, today);
	}

	public void testStringInteger() throws Exception {
		doTypeTest("String", "346", "346");
	}
	
	/**
	 * Test setting 'integer' type to an Integer.
	 */
	public void testIntegerInteger() throws Exception {
		doTypeTest("Integer", "123", "123");
	}
	
	/**
	 * Test setting 'integer' type to a String.
	 */
	public void testIntegerString() throws Exception {
		doTypeTest("Integer", "Hello, world!", "");	
	}

	/**
	 * Test setting 'Integer' type to a String that contains Integers.
	 */
	public void testIntegerStringWithNumbers() throws Exception {
		doTypeTest("Integer", "Hello, world! 456", "");
	}
	
	/**
	 * Test setting 'Integer' type to a String that contains Integers.
	 */
	public void testIntegerStringWithNumbers2() throws Exception {
		doTypeTest("Integer", "Hello, world! 789 Goodbye, world", "");
	}

	public void testIntegerDate() throws Exception {
		long date = 12345678;		// sec since epoch
		doTypeTest("Integer", rfc2822( new Date(date * 1000) ), "");
	}
	
	public void testEmailEmail() throws Exception {
		doTypeTest("Email", "hello@openiaml.org", "hello@openiaml.org");
	}

	public void testEmailString() throws Exception {
		doTypeTest("Email", "Hello, world!", "");
	}
	
	public void testEmailInteger() throws Exception {
		doTypeTest("Email", "12345", "");
	}

	public void testDateTimeDateTime() throws Exception {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTimeZone(TimeZone.getTimeZone("UTC"));
		c.set(1998, 11, 25);
		doTypeTest("Date/Time", "25 dec 1998", rfc2822( c.getTime() ));
	}

	public void testDateTimeInvalid() throws Exception {
		doTypeTest("Date/Time", "hello@openiaml.org", "" );
	}

}
