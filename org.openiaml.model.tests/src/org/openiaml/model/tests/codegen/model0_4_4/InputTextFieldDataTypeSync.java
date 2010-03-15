/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_4;

import org.eclipse.core.resources.IFile;
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
	 * If we just load the page, and then just reload it, no warnings
	 * should show.
	 * 
	 * @throws Exception
	 */
	public void testReloadingPageDoesntShowErrors() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// reload
		reloadPage(sitemap, "Home");
		assertNoProblem();
		
		// restart session
		restartSession(sitemap, "Home");
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
	 * String 2 to Email, with an invalid input, should have a problem.
	 */
	public void testStringToEmailInvalid() throws Exception {
		doInputWithWarnings("String 2", "Email", "invalid e-mail address");
	}
	
	/**
	 * Email 2 to Integer, with an invalid input, should have a problem.
	 */
	public void testEmailToIntegerInvalid() throws Exception {
		doInputWithWarnings("Email 2", "Integer", "test@openiaml.org");
	}
	
	/**
	 * Email 2 to Integer, with an invalid input, should revert back
	 * before it can even be passed along.
	 */
	public void testEmailToIntegerInvalid2() throws Exception {
		beginAtSitemapThenPage("Home");
		{
			String target = getLabelIDForText("Email 2");
			assertLabeledFieldEquals(target, "");	// empty
			
			// we can set it to a string
			setLabeledFormElementField(target, "123");
			
			// it will revert back
			assertLabeledFieldEquals(target, "");
		}
		
		// but no warning will be displayed
		assertNoProblem();
		
		// and the target value should be as expected
		{
			String target = getLabelIDForText("Integer");
			assertLabeledFieldEquals(target, "");	// should not change
		}
	}
	
	/**
	 * If we insert an invalid input, a warning will display.
	 * If we however then set it to a valid input, the warning will disappear.
	 */
	public void testStringToEmailInvalidThenValid() throws Exception {
		IFile sitemap = doInputWithWarnings("String 2", "Email", "invalid e-mail address");
		
		// now insert in a valid e-mail address
		{
			String target = getLabelIDForText("String 2");
			assertLabeledFieldEquals(target, "invalid e-mail address");	// from previous call
			
			// we can set it to a string
			setLabeledFormElementField(target, "test@openiaml.org");
			assertLabeledFieldEquals(target, "test@openiaml.org");
		}
		
		// there should NOT be any warnings
		assertNoProblem();
		
		// and the target value should be as expected
		{
			String target = getLabelIDForText("Email");
			assertLabeledFieldEquals(target, "test@openiaml.org");
		}
		
		// even if we reload the page
		reloadPage(sitemap, "Home");
		assertNoProblem();
		{
			String target = getLabelIDForText("String 2");
			assertLabeledFieldEquals(target, "test@openiaml.org");
		}
		{
			String target = getLabelIDForText("Email");
			assertLabeledFieldEquals(target, "test@openiaml.org");
		}
		
	}
	
	/**
	 * If we enter in an invalid input, and refresh the page, the invalid
	 * input should not automatically be loaded into a field that cannot
	 * support it.
	 * 
	 * @throws Exception
	 */
	public void testInvalidInputDoesNotShowOnInit() throws Exception {
		
		// set the Integer to a field
		// 'Email 2' will remain blank
		IFile sitemap = doInputWithWarnings("Integer", "Email 2", "12345");
		
		// reload the page
		reloadPage(sitemap, "Home");
		
		{
			String target = getLabelIDForText("Integer");
			assertLabeledFieldEquals(target, "12345");
		}
		
		// e-mail should still be blank
		{
			String target = getLabelIDForText("Email 2");
			assertLabeledFieldEquals(target, "");
		}
		
		// and there should be a problem
		assertProblem();
		assertMatch("Invalid input.");
		
	}

	/**
	 * Override to check also for warnings.
	 */
	@Override
	protected void assertProblem() {
		resetDebug();
		assertMatch("(Error|error|Exception|exception|Warning|warning)");
	}

	/**
	 * Override to check also for warnings.
	 */
	@Override
	protected void assertNoProblem() {
		resetDebug();
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
		assertNoProblem();
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
	 * target 'targetName' should NOT be changed (i.e. remain empty).
	 * 
	 * @param fieldName
	 * @param targetName
	 * @param input
	 * @throws Exception
	 * @returns the sitemap
	 */
	protected IFile doInputWithWarnings(String fieldName, String targetName, String input) throws Exception {
		return doInputWithWarnings(fieldName, targetName, input, input);
	}
	
	/**
	 * Enter in 'input' into 'fieldName'. A warning should occur, and the
	 * target 'targetName' should NOT be changed (i.e. remain empty).
	 * Expect that 'fieldName' will revert back to 'expected'.
	 * 
	 * @param fieldName
	 * @param targetName
	 * @param input
	 * @throws Exception
	 * @returns the sitemap
	 */
	protected IFile doInputWithWarnings(String fieldName, String targetName, String input, String expectedInput) throws Exception {
		IFile sitemap = beginAtSitemapThenPage("Home");
		assertNoProblem();
		{
			String target = getLabelIDForText(fieldName);
			assertLabeledFieldEquals(target, "");	// empty
			
			// we can set it to a string
			setLabeledFormElementField(target, input);
			
			// it may be reverted
			assertLabeledFieldEquals(target, expectedInput);
		}
		
		// there should be a warning
		assertProblem();
		assertMatch("Invalid input.");
		
		// and the target value should be as expected
		{
			String target = getLabelIDForText(targetName);
			assertLabeledFieldEquals(target, "");	// should not change
		}
		
		return sitemap;
		
	}
	

}
