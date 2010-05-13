/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime.server.direct;

import net.sourceforge.jwebunit.junit.WebTestCase;

/**
 * Tests the domain object functionality directly through PHP.
 * This is through the <code>domain/tests/run.php</code> script (written in PHP).
 * 
 * @author jmwright
 *
 */
public class DomainTestCases extends WebTestCase {
	
	/**
	 * Converts <code>org.openiaml.test</code> (e.g.) into <code>src/org/openiaml/test</code>.
	 */
	public String getRootPath() {
		return "src/" + this.getClass().getPackage().getName().replace('.', '/');
	}

	public void testDomainTestCases() throws Exception {
		
		// go to the target page
		beginAt(AllDirectRuntimeTests.INCLUDE_URL + "domain/tests/run.php");
		
		// get the page text
		String actual = getPageSource();
		
		// each line should be '[test N] PASS'
		String[] lines = actual.split("\n");
		StringBuffer expected = new StringBuffer();
		for (int i = 0; i < lines.length; i++) {
			if (!lines[i].trim().isEmpty()) {
				expected.append("[test ").append(i + 1).append("] PASS\n");
			}
		}
		
		// make sure they're identical
		assertEquals(unifyNewlines(expected.toString()), unifyNewlines(actual));
		
	}

	/**
	 * Unify all newline types together; the resulting string will only
	 * have <code>\n</code> newlines.
	 * 
	 * @param s a string containing any number of <code>\n</code>, <code>\r</code>, and <code>\r\n</code>
	 * @return a string that only contains <code>\n</code>
	 */
	public String unifyNewlines(String s) {
		return s.replaceAll("\r\n", "\n").replaceAll("\r", "\n");
	}
	
}
