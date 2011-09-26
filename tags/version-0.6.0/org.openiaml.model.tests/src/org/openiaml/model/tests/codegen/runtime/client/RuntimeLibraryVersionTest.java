/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.openiaml.model.runtime.IamlRuntimeLibrariesPlugin;
import org.openiaml.model.tests.XmlTestCase;

/**
 * Checks that the published information in the runtime plugin
 * matches the meta-information across our other plugins.
 * 
 * @author jmwright
 *
 */
public class RuntimeLibraryVersionTest extends XmlTestCase {
	
	/**
	 * The <code>web/index.html</code> file in the runtime plugin
	 * should include the plugin version.
	 */
	public void testIndexHtmlVersion() throws Exception {
		String pluginVersion = getPluginVersion();
		
		IamlRuntimeLibrariesPlugin plugin = IamlRuntimeLibrariesPlugin.getInstance();
		URL indexHtml = plugin.getResolvedFile("src/web/index.html");
		
		assertContains(indexHtml.openStream(), pluginVersion);
	}
	
	/**
	 * Get the plugin version of the local tests plugin, loaded from 
	 * <code>MANIFEST.MF</code>.
	 * 
	 * @return The plugin version of the local tests plugin.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String getPluginVersion() throws FileNotFoundException, IOException {
		Properties properties = loadProperties("META-INF/MANIFEST.MF");
		return properties.getProperty("Bundle-Version");
	}

	/**
	 * Assert that the given stream contains the given String.
	 * 
	 * @param stream
	 * @param content
	 * @throws IOException
	 */
	public void assertContains(InputStream stream, String content) throws IOException {
		assertNotNull(stream);
		assertNotNull(content);
		assertFalse("".equals(content));
	
		String buffer = "";
		int c;
		while ((c = stream.read()) != -1) {
			if (buffer.length() == content.length()) {
				buffer = buffer.substring(1) + (char) c;
			} else {
				buffer += (char) c;
			}
			
			if (buffer.equals(content)) {
				// found it
				stream.close();
				return;
			}
		}
		
		stream.close();
		fail("Could not find string '" + content + "' in stream '" + stream + "'");
		
	}
	
}
