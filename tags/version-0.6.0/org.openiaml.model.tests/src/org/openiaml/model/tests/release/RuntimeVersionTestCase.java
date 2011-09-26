/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;

import org.openiaml.model.tests.XmlTestCase;

/**
 * Checks just the /index.html for the current version.
 * 
 * @author jmwright
 *
 */
public class RuntimeVersionTestCase extends XmlTestCase {
	
	public void testIndexHtmlVersion() throws Exception {

		String html = readFile(new File("../org.openiaml.model.runtime/src/web/index.html"));
		String version = PluginsTestCase.getVersion();
		
		assertTrue("HTML did not contain version '" + version + "'", html.contains(version));
	
	}
	
}
