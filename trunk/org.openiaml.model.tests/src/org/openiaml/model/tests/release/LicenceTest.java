/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;

import junit.framework.TestCase;

/**
 * Check all plugins have the correct licencing information.
 * 
 * @author jmwright
 *
 */
public class LicenceTest extends TestCase {
	
	public void testPluginsListSize() {
		assertNotSame(0, PluginsTestCase.getPlugins().size());
		assertTrue(PluginsTestCase.getPlugins().size() > 5);
	}
	
	/**
	 * Every org.openiaml.* plugin should include
	 * <code>epl-v10.html</code>.
	 */
	public void testHasEPLFile() {
			
		for (String plugin : PluginsTestCase.getPlugins()) {
			File epl = new File(plugin + File.separator + "epl-v10.html");
			assertTrue("EPL file '" + epl + "' did not exist", epl.exists());
			assertTrue(epl.isFile());
		}
		
	}
	
}
