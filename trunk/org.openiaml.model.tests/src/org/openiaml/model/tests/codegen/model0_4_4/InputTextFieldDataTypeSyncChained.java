/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_4;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Chained SyncWires.
 */
public class InputTextFieldDataTypeSyncChained extends WarningEnabledCodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(InputTextFieldDataTypeSyncChained.class);
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
	
	public void testSetString() throws Exception {
		
		beginAtSitemapThenPage("Home");
		assertNoProblem();	// no problem to begin with
		
		String text = "hello, world!";
		{
			String target = getLabelIDForText("String");
			assertLabeledFieldEquals(target, "");	// empty
			
			// we can set it to a string
			setLabeledFormElementField(target, text);
			assertLabeledFieldEquals(target, text);
		}
		
		// 'Default' is changed
		assertLabel("Default", text);
		
		// Integer is not
		assertLabel("Integer", "");
		
		// Email is not
		assertLabel("Email", "");
		
		// Date and Date 2 are not
		assertLabel("Date", "");
		assertLabel("Date 2", "");
		
		// in fact, there is a warning because of the lack of conversion
		assertProblem();
		
		// an unrelated field, String 2, is unchanged
		assertLabel("String 2", "");
		
	}
	
	/**
	 * If we set 'String' to a valid date, it will pass along
	 * 
	 * @throws Exception
	 */
	public void testSetStringDate() throws Exception {
		
		beginAtSitemapThenPage("Home");
		assertNoProblem();	// no problem to begin with
		
		String text = "25 dec 1998 +0000";
		
		// conversion
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		c.clear();
		c.set(1998, 11, 25);
		String expected = rfc2822(c.getTime());
		
		{
			String target = getLabelIDForText("String");
			assertLabeledFieldEquals(target, "");	// empty
			
			// we can set it to a string
			setLabeledFormElementField(target, text);
			assertLabeledFieldEquals(target, expected);	// changed
		}
		
		// 'Default' is changed
		assertLabel("Default", expected);
		
		// Date and Date 2 are
		assertLabel("Date", expected);
		assertLabel("Date 2", expected);
		
		// Integer is not
		assertLabel("Integer", "");
		
		// Email is not
		assertLabel("Email", "");

		// in fact, there is a warning because of the lack of conversion
		assertProblem();
		
		// an unrelated field, String 2, is unchanged
		assertLabel("String 2", "");
		
	}
	
	/**
	 * If we set 'Integer' to a valid integer, it will pass along
	 * 
	 * @throws Exception
	 */
	public void testSetInteger() throws Exception {
		
		beginAtSitemapThenPage("Home");
		assertNoProblem();	// no problem to begin with
		
		String text = "1642015";
		
		{
			String target = getLabelIDForText("Integer");
			assertLabeledFieldEquals(target, "");	// empty
			
			// we can set it to a string
			setLabeledFormElementField(target, text);
			assertLabeledFieldEquals(target, text);
		}
		
		// 'Default' is changed
		assertLabel("Default", text);

		// 'String' is changed
		assertLabel("String", text);

		// Date and Date 2 are not
		assertLabel("Date", "");
		assertLabel("Date 2", "");
		
		// Email is not
		assertLabel("Email", "");

		// in fact, there is a warning because of the lack of conversion
		assertProblem();
		
		// an unrelated field, String 2, is unchanged
		assertLabel("String 2", "");
		
	}
	
	/**
	 * If we set 'Integer' to an invalid integer, nothing will happen.
	 * 
	 * @throws Exception
	 */
	public void testInvalidInteger() throws Exception {
		
		beginAtSitemapThenPage("Home");
		assertNoProblem();	// no problem to begin with
		
		String text = "hello, world!";
		
		{
			String target = getLabelIDForText("Integer");
			assertLabeledFieldEquals(target, "");	// empty
			
			setLabeledFormElementField(target, text);
			
			// it does not change
			assertLabeledFieldEquals(target, "");
		}
		
		// none of the chained elements change
		assertLabel("Default", "");
		assertLabel("String", "");
		assertLabel("Date", "");
		assertLabel("Date 2", "");
		assertLabel("Email", "");
		
		// there is no problem, because the non-integer wasn't even accepted
		assertNoProblem();
		
		// an unrelated field, String 2, is unchanged
		assertLabel("String 2", "");
		
	}
	
	private void assertLabel(String labelName, String text) {
		String target = getLabelIDForText(labelName);
		assertLabeledFieldEquals(target, text);
	}

}
