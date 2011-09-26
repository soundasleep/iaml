/**
 * 
 */
package org.openiaml.model.tests.codegen;

import org.openiaml.model.tests.ModelTestCase;

/** 
 * Make sure that the client-side library (accessible by
 * the URL {@link #BASE_URL_RUNTIME}.
 * 
 * If this test fails you may need to set up an alias to the runtime
 * web directory in your httpd.conf. See installation instructions.
 * 
 * @author jmwright
 *
 */
public class TestClientsideRuntimeLibrary extends ModelTestCase {

	/**
	 * Try accessing the index.html page 
	 */
	public void testClientsideLibraryExists() throws Exception {
		
		getTestContext().setBaseUrl(BASE_URL_RUNTIME);
		beginAt("index.html");
		
		// "IAML runtime library" should exist on the page
		assertTextPresent("IAML Runtime Library");
		
	}


}
