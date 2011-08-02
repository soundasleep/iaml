package org.openiaml.docs.tests.expected;

import java.io.File;
import java.util.Arrays;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.openiaml.docs.generation.TranslateHTMLToLatex;

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
 * @author Jevon
 *
 */
public abstract class ExpectedTestCase extends TestCase {

	/**
	 * Location of expected resources.
	 */
	public static final String ROOT = "tests/resources/html2tex/";
	
	/**
	 * Get the current class.
	 */
	public abstract Class<?> getTestCaseClass();
	
	protected String getInputFilename() {
		return getTestCaseClass().getSimpleName().toLowerCase() + ".html";
	}
	
	protected String getOutputFilename() {
		return getTestCaseClass().getSimpleName().toLowerCase() + ".tex";
	}
	
	/**
	 * Input file.
	 * @return
	 */
	protected File getInputFile() {
		String inputFile = getInputFilename();
		
		File f = new File(ROOT + inputFile);
		assertTrue("File '" + f + "' doesn't exist", f.exists());
		
		return f;
	}
	
	/**
	 * Expected output.
	 * @return
	 */
	protected File getExpectedFile() {
		String resultFile = getOutputFilename();
		
		File f = new File(ROOT + resultFile);
		assertTrue("File '" + f + "' doesn't exist", f.exists());
		
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
	
		TranslateHTMLToLatex c = AllExpectedTests.getConverter();
		
		String inputText = TranslateHTMLToLatex.readFile(getInputFile());
		assertNotNull(inputText);
		String outputText = TranslateHTMLToLatex.readFile(getExpectedFile());
		assertNotNull(outputText);
		// replace \r\n with \n
		outputText = outputText.replace("\r\n", "\n");

		String result = c.convertToTex(getInputFile());
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
