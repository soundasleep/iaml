package org.openiaml.verification.nusmv.cleaner.tests;

import java.io.File;
import java.util.Arrays;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.openiaml.verification.nusmv.oaw.NuSMVBeautifier;

/**
 * <p>
 * For testing the expected results of different input files, we extend this abstract class.
 * </p>
 * 
 * <p>
 * If a class is called <code>Foo_Html</code>, it will run the
 * following tests:
 * <ol>
 * 	<li>format('foo.html') == 'foo.expected.html': {@link #testExpected()}</li>
 * 	<li>format('foo.expected.html') == 'foo.expected.html': {@link #testStable()}</li>
 * 	<li>format('foo.compact.html') == 'foo.expected.html': {@link #testWhitespace()}</li>
 * </ol>
 * </p>
 * 
 * <p>TODO move into a separate project
 * 
 * @author Jevon
 *
 */
public abstract class ExpectedTestCase extends TestCase {

	/**
	 * Location of expected resources.
	 */
	public static final String ROOT = "src/org/openiaml/verification/nusmv/cleaner/tests/resources/";
	
	/**
	 * Get the current class.
	 */
	public abstract Class<?> getTestCaseClass();
	
	protected String getInputFilename() {
		assertTrue("Test case '" + getTestCaseClass() + "' needs to have an underscore in its name.", getTestCaseClass().getSimpleName().contains("_"));
		return getTestCaseClass().getSimpleName().replace("_", ".").toLowerCase();
	}
	
	/**
	 * Input file.
	 * @return
	 */
	protected File getInputFile() {
		String inputFile = getInputFilename();
		
		File f = new File(ROOT + inputFile);
		assertTrue("File '" + inputFile + "' doesn't exist", f.exists());
		
		return f;
	}
	
	/**
	 * Expected output.
	 * @return
	 */
	protected File getExpectedFile() {
		String inputFile = getInputFilename();
		String resultFile = inputFile.substring(0, inputFile.lastIndexOf(".")) + ".expected." + inputFile.substring(inputFile.lastIndexOf(".") + 1);
		
		File f = new File(ROOT + resultFile);
		assertTrue("File '" + resultFile + "' doesn't exist", f.exists());
		
		return f;
	}
	
	/**
	 * Another input, with most whitespace removed.
	 * 
	 * @return
	 */
	protected File getWhitespaceInputFile() {
		String inputFile = getInputFilename();
		String resultFile = inputFile.substring(0, inputFile.lastIndexOf(".")) + ".compact." + inputFile.substring(inputFile.lastIndexOf(".") + 1);
		
		File f = new File(ROOT + resultFile);
		assertTrue("File '" + resultFile + "' doesn't exist", f.exists());
		
		return f;
	}
	
	/**
	 * Test that [testcase_ext], after cleaning, equals [testcase_expected_ext] exactly.
	 * 
	 * @throws Exception
	 */
	public void testExpected() throws Exception {
	
		String inputText = NuSMVBeautifier.readFile(getInputFile());
		assertNotNull(inputText);
		String outputText = NuSMVBeautifier.readFile(getExpectedFile());
		assertNotNull(outputText);
		// replace \r\n with \n
		outputText = outputText.replace("\r\n", "\n");

		NuSMVBeautifier c = new NuSMVBeautifier();
		String result = c.cleanNuSMV(getInputFile());
		assertStringEquals(outputText, result);
		
	}
	
	/**
	 * If we load the expected file, it does not change between executions.
	 * 
	 * @throws Exception
	 */
	public void testStable() throws Exception {
		
		String inputText = NuSMVBeautifier.readFile(getExpectedFile());
		assertNotNull(inputText);
		// replace \r\n with \n
		inputText = inputText.replace("\r\n", "\n");

		NuSMVBeautifier c = new NuSMVBeautifier();
		String result = c.cleanNuSMV(getExpectedFile());
		assertStringEquals(inputText, result);

	}
	
	/**
	 * This is the same file, except all whitespace has been removed. 
	 * iacleaner should properly indent the code as well.
	 * 
	 * @throws Exception
	 */
	public void testWhitespace() throws Exception {
		
		String inputText = NuSMVBeautifier.readFile(getWhitespaceInputFile());
		assertNotNull(inputText);
		String outputText = NuSMVBeautifier.readFile(getExpectedFile());
		assertNotNull(outputText);
		// replace \r\n with \n
		outputText = outputText.replace("\r\n", "\n");

		NuSMVBeautifier c = new NuSMVBeautifier();
		String result = c.cleanNuSMV(getWhitespaceInputFile());
		assertStringEquals(outputText, result);

	}
	
	/**
	 * This is like {@link #assertEquals(String, String)}, except we ignore any
	 * differences in line endings (\r, \n)
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertStringEquals(String expected, String actual) {
		assertEquals(expected.replace("\r", ""), actual.replace("\r", ""));
	}
	
	/**
	 * Expands on the standard {@link #assertEquals(String, String)} method by also
	 * printing out the individual characters.
	 * 
	 * @param expected
	 * @param actual
	 */
	public static void assertEquals(String expected, String actual) {
		try {
			TestCase.assertEquals(expected, actual);
		} catch (AssertionFailedError e) {
			System.out.println("result: " + Arrays.toString(actual.toCharArray()));
			System.out.println("wanted: " + Arrays.toString(expected.toCharArray()));
			throw e;
		}
	}
	
}
