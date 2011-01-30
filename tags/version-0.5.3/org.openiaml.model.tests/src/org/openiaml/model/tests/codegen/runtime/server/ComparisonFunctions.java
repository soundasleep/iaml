/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime.server;

/**
 * Checks comparison functions.
 * 
 * @author jmwright
 *
 */
public class ComparisonFunctions extends PhpCodegenTestCase {

	/**
	 * Tests <code>xpathMatch()</code>.
	 */
	public void testXpathMatch() throws Exception {
		String init = "$a = new Visual_Frame('a', 'a'); $b = new Visual_Frame('b', 'b'); $c = new Visual_Frame('c', 'c');";
		
		assertPhpResult(true, init, "xpathMatch(array('a'), $a)");
		assertPhpResult(true, init, "xpathMatch(array('b'), $b)");
		assertPhpResult(true, init, "xpathMatch($b, array('b'))");
		assertPhpResult(false, init, "xpathMatch(array('a'), $b)");
		assertPhpResult(true, init, "xpathMatch(array('a', 'b'), $b)");
		assertPhpResult(true, init, "xpathMatch($a, array('a', 'b'))");
		assertPhpResult(false, init, "xpathMatch($c, array('a', 'b'))");
	}
	
	/**
	 * Tests <code>is_absolute_url()</code>.
	 */
	public void testIsAbsoluteUrl() throws Exception {
		assertPhpResult(true, "is_absolute_url('http://foo.com')");
		assertPhpResult(true, "is_absolute_url('http://www.foo.com')");
		assertPhpResult(true, "is_absolute_url('ftp://foo.com')");
		assertPhpResult(true, "is_absolute_url('file://foo.com')");
		assertPhpResult(false, "is_absolute_url('/foo.com')");
		assertPhpResult(false, "is_absolute_url('../foo.com')");
		assertPhpResult(false, "is_absolute_url('foo.com')");
		assertPhpResult(false, "is_absolute_url('www.foo.com')");
	}

}
