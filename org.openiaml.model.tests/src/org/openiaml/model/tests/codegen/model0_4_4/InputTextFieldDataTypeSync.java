/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_4;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * @example SyncWire,InputTextField
 * 		{@model SyncWire}s connected between {@model InputTextField}s of
 * 		different types.
 */
public class InputTextFieldDataTypeSync extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(InputTextFieldDataTypeSync.class);
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
	 * Default to String shouldn't have any problems.
	 */
	public void testDefaultToString() throws Exception {
		doInputWithNoWarnings("Default", "String", "hello, world!", "hello, world!");
	}
	
	/**
	 * String to Default shouldn't have any problems.
	 */
	public void testStringToDefault() throws Exception {
		doInputWithNoWarnings("String", "Default", "hello, world!", "hello, world!");
	}
	
	/**
	 * String 2 to Email, with a valid input, shouldn't have any problems.
	 */
	public void testStringToEmailValid() throws Exception {
		doInputWithNoWarnings("String 2", "Email", "test@openiaml.org", "test@openiaml.org");
	}
	
	/**
	 * String 2 to Email, with an invalid input, should have any problems.
	 */
	public void testStringToEmailInalid() throws Exception {
		doInputWithWarnings("String 2", "Email", "invalid e-mail address", "test@openiaml.org");
	}
	
	/**
	 * Override to check also for warnings.
	 */
	@Override
	protected void assertProblem() {
		assertMatch("(Error|error|Exception|exception|Warning|warning)");
	}

	/**
	 * Override to check also for warnings.
	 */
	@Override
	protected void assertNoProblem() {
		assertNoMatch("(Error|error|Exception|exception|Warning|warning)");
	}
	
	/**
	 * Enter in 'input' into 'fieldName'. No warnings should 
	 * occur, and the target 'targetName' should be updated to the
	 * 'expected' value.
	 * 
	 * @param fieldName
	 * @param targetName
	 * @param input
	 * @param expected
	 * @throws Exception
	 */
	protected void doInputWithNoWarnings(String fieldName, String targetName, String input, String expected) throws Exception {
		
		beginAtSitemapThenPage("Home");
		{
			String target = getLabelIDForText(fieldName);
			assertLabeledFieldEquals(target, "");	// empty
			
			// we can set it to a string
			setLabeledFormElementField(target, input);
			assertLabeledFieldEquals(target, input);
		}
		
		// there should NOT be any warnings
		assertNoProblem();
		
		// and the target value should be as expected
		{
			String target = getLabelIDForText(targetName);
			assertLabeledFieldEquals(target, expected);
		}
		
	}
	
	/**
	 * Enter in 'input' into 'fieldName'. A warning should occur, and the
	 * target 'targetName' should NOT be changed.
	 * 
	 * @param fieldName
	 * @param targetName
	 * @param input
	 * @param expected
	 * @throws Exception
	 */
	protected void doInputWithWarnings(String fieldName, String targetName, String input, String expected) throws Exception {
		
		beginAtSitemapThenPage("Home");
		{
			String target = getLabelIDForText(fieldName);
			assertLabeledFieldEquals(target, "");	// empty
			
			// we can set it to a string
			setLabeledFormElementField(target, input);
			assertLabeledFieldEquals(target, input);
		}
		
		// there should be a warning
		assertProblem();
		assertMatch("Invalid input.");
		
		// and the target value should be as expected
		{
			String target = getLabelIDForText(targetName);
			assertLabeledFieldEquals(target, "");	// should not change
		}
		
	}
	

}
