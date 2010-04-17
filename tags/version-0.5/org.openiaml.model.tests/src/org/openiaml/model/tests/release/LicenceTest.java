/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;
import java.util.Arrays;
import java.util.List;

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
	
	protected List<String> LGPL_PLUGINS = Arrays.asList(
		"org.openiaml.model.runtime.phpmailer"
	);
	
	/**
	 * Every org.openiaml.* plugin should include
	 * <code>epl-v10.html</code>.
	 */
	public void testHasEPLFile() {
			
		for (String plugin : PluginsTestCase.getPlugins()) {
			// skip LGPL plugins
			if (shouldSkip(plugin, LGPL_PLUGINS))
				continue;
			
			File epl = new File(plugin + File.separator + "epl-v10.html");
			assertTrue("EPL file '" + epl + "' did not exist", epl.exists());
			assertTrue(epl.isFile());
		}
		
	}
	
	private boolean shouldSkip(String plugin, Iterable<String> toSkip) {
		for (String p : toSkip) {
			if (plugin.contains(p))
				return true;
		}
		return false;
	}
	
	private boolean containedIn(Iterable<String> plugins, String p) {
		for (String p2 : plugins) {
			if (p2.contains(p))
				return true;
		}
		return false;
	}
	
	/**
	 * Some plugins don't have EPL; some have LGPL.
	 */
	public void testHasLicenseFile() {
		
		for (String plugin : LGPL_PLUGINS) {
			File epl = new File(PluginsTestCase.PLUGIN_ROOT + plugin + File.separator + "LICENSE");
			assertTrue("License file '" + epl + "' did not exist", epl.exists());
			assertTrue(epl.isFile());
		}
		
	}
	
	/**
	 * All the plugins in {@link #LGPL_PLUGINS} should also be in 
	 * {@link PluginsTestCase#getPlugins()}.
	 */
	public void testLgplPluginsInPluginList() {
		
		for (String p : LGPL_PLUGINS) {			
			assertTrue("Plugin '" + p + "' did not exist in plugin list", containedIn(PluginsTestCase.getPlugins(), p));
		}
		
	}
	
}
	
