/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;

import org.openiaml.model.tests.XmlTestCase;
import org.openiaml.model.tests.xpath.IterableElementList;
import org.w3c.dom.Document;

/**
 * Checks all plugins to make sure that only the root diagram
 * editor has a "new wizard" editor: issue 53
 * 
 * @author jmwright
 *
 */
public class NewWizardsTest extends XmlTestCase {
	
	public static final String DIAGRAM_PREFIX = "org.openiaml.model.diagram.";
	
	public void testNewWizards() throws Exception {
		
		// iterate over all plugins
		int checked = 0;
		for (String plugin : PluginsTestCase.PLUGINS) {
			if (plugin.startsWith(DIAGRAM_PREFIX)) {
				checkNewWizard(plugin);
				checked++;
			}
		}
		assertNotEqual(0, checked);
		
	}

	private void checkNewWizard(String plugin) throws Exception {
		String pluginXml = PluginsTestCase.PLUGIN_ROOT + plugin + "/plugin.xml";
		File f = new File(pluginXml);
		assertTrue("plugin " + plugin + ": " + f + " exists", f.exists());
		
		Document doc = loadDocument(f);

		// it should have another extension point (sanity check)
		xpathFirst(doc, "/plugin/extension");
		
		// it should _not_ have a NewWizard
		IterableElementList result = xpath(doc, "/plugin/extension[@point='org.eclipse.ui.newWizards']");
		assertTrue("plugin " + plugin + ": should not have a NewWizard", result.isEmpty());
	
	}
	
	
}
