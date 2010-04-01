/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime.server;

import java.io.File;

/**
 * Checks the conversion of HTML to text.
 * 
 * @author jmwright
 *
 */
public class ConvertHtmlToText extends PhpCodegenTestCase {
	
	/**
	 * Copy <code>html2text/<i>test</i>.html</code> to the local workspace;
	 * Use <code>convert_html_to_text()</code> to convert the HTML;
	 * Compare the results to the local <code>html2text/<i>test</i>.txt</code>.
	 * 
	 * @param test the test string to search for
	 */
	private void convert(String test) throws Exception {
		File input = new File(ROOT + "codegen/runtime/server/html2text/" + test + ".html"); 
		File output = new File(ROOT + "codegen/runtime/server/html2text/" + test + ".txt"); 
		
		// copy a file
		copyFileIntoWorkspace(input, getProject().getFile("output/input.html"));
		
		// read the local file
		assertTrue("File '" + output + "' doesn't exist", output.exists());
		String expected = readFile(output);
		
		// execute
		assertPhpResult(expected, "convert_html_to_text(file_get_contents('output/input.html'))");
		
	}
	
	public void testConvert1() throws Exception {
		convert("basic");
	}

}
