/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;
import java.io.FilenameFilter;

import junit.framework.TestCase;

/**
 * Check all plugins have the correct licencing information.
 * 
 * @author jmwright
 *
 */
public class LicenceTest extends TestCase {
	
	/**
	 * Every org.openiaml.* plugin should include
	 * <code>epl-v10.html</code>.
	 */
	public void testHasEPLFile() {
		
		File root = new File("../");
		assertTrue(root.isDirectory());
		assertTrue(root.exists());
		
		String[] children = root.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("org.openiaml");
			}
		});
		
		for (String c : children) {
			File plugin = new File(root.getAbsolutePath() + File.separator + c);
			assertTrue("Plugin directory '" + plugin + "' did not exist", plugin.exists());
			assertTrue(plugin.isDirectory());
			
			File epl = new File(plugin.getAbsolutePath() + File.separator + "epl-v10.html");
			assertTrue("EPL file '" + epl + "' did not exist", epl.exists());
			assertTrue(epl.isFile());
		}
		
	}
	
}
