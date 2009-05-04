/**
 * 
 */
package org.openiaml.model.tests.codegen.oaw;

import junit.framework.AssertionFailedError;

import org.eclipse.core.runtime.IStatus;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Test that the checks.chk files throw errors.
 * 
 * @author jmwright
 *
 */
public class ChecksTest extends CodegenTestCase {
	
	protected InternetApplication root;

	@Override
	protected void setUp() throws Exception {
		// do nothing: each checks test method will set up the model itself
	}

	/**
	 * Calls {@link #loadAndCodegen(String)}, but it is expected
	 * that this will fail with an AssertionFailedError.
	 *  
	 * @param filename The model file to load.
	 * @see #loadAndCodegen(String)
	 */
	protected void loadAndCodegenWithFail(String filename) throws Exception {
		boolean passed = true;
		try {
			root = loadAndCodegen(filename);
			passed = false;
		} catch (AssertionFailedError e) {
			// expected
		}
		assertTrue("Code-generation should not pass.", passed);
	}

	/**
	 * Checks that the given status has the given error message.
	 * Parses through MultiStatuses.
	 * 
	 * @param s The status to check
	 * @param string A string that should be contained in a status message (not case-sensitive)
	 * @return true if the status, or one of its children, contains the message
	 */
	protected boolean hasStatusMatch(IStatus s, String string) {
		boolean hasStatus = false;
		
		// parse children
		if (s.isMultiStatus()) {
			for (IStatus ss : s.getChildren()) {
				hasStatus = hasStatusMatch(ss, string);
				if (hasStatus) return hasStatus;
			}
		}
		
		// parse this one
		hasStatus = s.getMessage().toLowerCase().contains(string.toLowerCase());
		
		return hasStatus;
	}
	
	/**
	 * Assert that the given status has the given error message.
	 * Parses through MultiStatuses.
	 * 
	 * @param s The status to check
	 * @param string A string that should be contained in a status message (not case-sensitive)
	 */
	protected void assertStatusMatches(IStatus s, String string) {
		assertTrue("Status '" + string + "' not found.", hasStatusMatch(s, string));
	}

	/**
	 * Test a web application with no pages.
	 * 
	 * @throws Exception
	 */
	public void testNoPages() throws Exception {
		loadAndCodegenWithFail(ROOT + "codegen/oaw/NoPages.iaml");
		
		// lets investigate the error
		IStatus s = getTransformStatus();
		assertFalse(s.isOK());
		
		assertStatusMatches(s, "No pages in internet application");
	}

}