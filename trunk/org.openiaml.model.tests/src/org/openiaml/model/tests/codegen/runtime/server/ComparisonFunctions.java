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
	 * tests emailAddressMatch()
	 */
	public void testEmailAddressMatch() throws Exception {
		assertPhpResult(true, "emailAddressMatch('test@jevon.org')");
		assertPhpResult(true, "emailAddressMatch('long.address@test.example.co.nz')");
		assertPhpResult(false, "emailAddressMatch('failure')");
	}

	/**
	 * tests xpathMatch()
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

}
