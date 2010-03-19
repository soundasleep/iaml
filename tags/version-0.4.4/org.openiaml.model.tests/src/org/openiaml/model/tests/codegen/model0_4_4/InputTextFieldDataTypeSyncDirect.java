/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_4;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Simple test cases to check sync wires between elements of the
 * <em>same</em> type.
 * 
 * @example InputTextField,CastNode
 * 		Two {@model InputTextField}s of the same type can easily be {@model SyncWire synchronised}
 * 		with each other.
 */
public class InputTextFieldDataTypeSyncDirect extends WarningEnabledCodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(InputTextFieldDataTypeSyncDirect.class);
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
	
	private void doTest(String label1, String label2, String initial, String text) {
		doTest(label1, label2, initial, text, text);
	}
	
	private void doTest(String label1, String label2, String initial, String text, String expected) {
		{
			String target = getLabelIDForText(label1);
			assertLabeledFieldEquals(target, initial);	// empty
			
			// we can set it to a string
			setLabeledFormElementField(target, text);
			assertLabeledFieldEquals(target, expected);
		}
		
		// and the other one
		{
			String target = getLabelIDForText(label2);
			assertLabeledFieldEquals(target, expected);
		}
	}
	
	public void testString() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		doTest("String", "String 2", "", "hello, world!");
		assertNoProblem();
		doTest("String 2", "String", "hello, world!", "goodbye, world!");
		assertNoProblem();
	}
	
	public void testEmail() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		doTest("Email", "Email 2", "", "one@openiaml.org");
		assertNoProblem();
		doTest("Email 2", "Email", "one@openiaml.org", "two@openiaml.org");
		assertNoProblem();
	}

	public void testInteger() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		doTest("Integer", "Integer 2", "", "1234");
		assertNoProblem();
		doTest("Integer 2", "Integer", "1234", "5678");
		assertNoProblem();
	}
	
	public void testDefault() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		doTest("Default", "Default 2", "", "one@openiaml.org");
		assertNoProblem();
		doTest("Default 2", "Default", "one@openiaml.org", "12345");
		assertNoProblem();
	}
	
	public void testDate() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// construct two expected dates
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		c.clear();
		c.set(1998, 11 /* 0-based */, 25);
		String date1 = rfc2822(c.getTime());

		c.clear();
		c.set(2001, 0 /* 0-based */, 12);
		String date2 = rfc2822(c.getTime());

		doTest("Date", "Date 2", "", "12/25/1998 +0000", date1);
		assertNoProblem();
		doTest("Date 2", "Date", date1, "01/12/2001 +0000", date2);
		assertNoProblem();
	}

}
