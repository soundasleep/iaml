/**
 * 
 */
package it.itc.irst.nusmv.cli.tests;

import it.itc.irst.nusmv.cli.ExecuteNuSMV;
import it.itc.irst.nusmv.cli.ExecuteNuSMV.NuSMVException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

/**
 * @author jmwright
 *
 */
public class BasicNuSMVTest extends TestCase {

	/**
	 * Helper method to check the result of NuSMV execution.
	 * 
	 * @param result the expected result (pass or fail)
	 * @param file the input file (relative to the plugin)
	 * @throws IOException 
	 * @throws NuSMVException 
	 */
	protected void assertNusmvResult(boolean result, String file) throws IOException, NuSMVException {
		NuSMVCliTestsPlugin instance = NuSMVCliTestsPlugin.getInstance();
		assertNotNull(instance);
		
		URL smv;
		try {
			smv = instance.getResolvedFile(file);
			assertNotNull(smv);
		} catch (NullPointerException e) {
			throw new RuntimeException("A file might be missing from the bundle: " + e.getMessage(), e);
		}

		// get the crocopat output
		ExecuteNuSMV exec = new ExecuteNuSMV();
		List<String> r = exec.execute(smv.openStream());
		
		assertNotNull("Result should not be null", r);
		
		// should have passed with some log info
		assertFalse("Expected some results", r.size() == 0);
		boolean hasExpected = false;
		for (String s : r) {
			if (result && s.contains(" is false")) {
				fail("Expected a pass result, got a fail: " + s);
			}
			if (!result && s.contains(" is true")) {
				fail("Expected a fail result, got a pass: " + s);
			}
			if (result && s.contains(" is true")) {
				hasExpected = true;
			}
			if (!result && s.contains(" is false")) {
				hasExpected = true;
			}
		}
		
		assertTrue("Result did not return " + result + ": " + r, hasExpected);
	}
	
	/**
	 * A basic test for NuSMV integration.
	 * 
	 */
	public void testPass() throws Exception {
		assertNusmvResult(true, "tests/pass.smv");
	}
	
	/**
	 * Test failure.
	 */
	public void testFail() throws Exception {
		assertNusmvResult(false, "tests/fail.smv");
	}
	
}
