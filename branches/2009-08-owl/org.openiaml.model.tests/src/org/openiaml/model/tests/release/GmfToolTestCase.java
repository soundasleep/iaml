/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.xpath.XPathExpressionException;

import org.openiaml.model.tests.XmlTestCase;
import org.openiaml.model.xpath.IterableElementList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Tests .mf and plugin.xml files of the specified plugins
 * 
 * @author jmwright
 *
 */
public class GmfToolTestCase extends XmlTestCase {

	/**
	 * Load up all the .gmftool's
	 * 
	 */
	public Map<String,Document> getGmfTools() throws Exception {
		Map<String,Document> loadedTools = new HashMap<String,Document>();
			
		// load all .gmftool's
		for (String map : getToolList()) {
			loadedTools.put( map, loadDocument(map) );
		}

		return loadedTools;
	}
	
	/**
	 * Create a list of gmftools from {@link PluginsTestCase}.
	 * 
	 * @return
	 */
	private static Set<String> getToolList() {
		Set<String> tools = new HashSet<String>();
		for (String gmfgen : PluginsTestCase.GMFGENS) {
			tools.add(gmfgen.replace(".gmfgen", ".gmftool"));
		}
		return tools;
	}

	/**
	 * There should be the same number of tools specified here
	 * as in {@link PluginsTestCase}.
	 * 
	 * @throws Exception
	 */
	public void testGmfToolMatch() throws Exception {
		assertEquals(getGmfTools().size(), PluginsTestCase.GMFGENS.length);
	}
	
	/**
	 * From a given root element (//palette/tools) [there may be
	 * many 'tools' in //palette), get ALL 
	 * gmfTool elements within. Excluding tool groups, but recursing 
	 * through the gool groups.
	 * 
	 * @param root
	 * @throws XPathExpressionException 
	 */
	private IterableElementList getAllGmfTools(IterableElementList roots, String prefix) throws XPathExpressionException {
		assertNotEqual(prefix + ": There should be exactly one //tools entry", roots.getLength(), 0);
		
		List<Element> result = new ArrayList<Element>();

		for (Element root : roots) {		
			result.addAll(getAllGmfToolsRecursive(root, prefix));
		}
		
		return new IterableElementList(result);
	}
	

	/**
	 * From a given root element (//palette/tools) [there may be
	 * many 'tools' in //palette), get ALL 
	 * gmfTool elements within. Excluding tool groups, but recursing 
	 * through the gool groups.
	 * 
	 * @param root
	 * @throws XPathExpressionException 
	 */
	private List<Element> getAllGmfToolsRecursive(Element root, String prefix) throws XPathExpressionException {
		List<Element> result = new ArrayList<Element>();
		IterableElementList tools = xpath(root, "tools");
		
		for (Element e : tools) {
			if (e.getAttribute("xsi:type").equals("gmftool:ToolGroup")) {
				// found a group
				result.addAll(getAllGmfToolsRecursive(e, prefix));
			} else if (e.getAttribute("xsi:type").equals("gmftool:CreationTool")) {
				// found a tool
				result.add(e);
			} else {
				// unknown tool type
				fail("Unexpected GMF tool xsi:type: " + e.getAttribute("xsi:type"));
			}
		}

		return result;
	}

	/**
	 * Check .gmftools for icons. If the .gmftool does not
	 * specify an icon, one is specified automatically, and the
	 * .gmftool is rewritten.
	 * 
	 * (TODO The other alternative is to build this into a build.xml.)
	 * 
	 * @throws Exception
	 */
	public void testGmfTools() throws Exception {
		Map<String,Document> loaded = getGmfTools();
		
		for (String file : loaded.keySet()) {
			boolean changed = false;
			Document doc = loaded.get(file);
			IterableElementList nl = xpath(doc, "//palette/tools");
			
			IterableElementList tools = getAllGmfTools(nl, file + ": ");
			assertNotSame(file + ": There should be at least one subtool entry", tools.getLength(), 0);
			
			for (int i = 0; i < tools.getLength(); i++) {
				String prefix = "[" + file + "#" + i + "] ";
				Element tool = (Element) tools.item(i);
				assertNotNull(prefix, tool);
				Element smallIcon = (Element) xpath(tool, "smallIcon").item(0);
				assertNotNull(prefix, smallIcon);
				Element largeIcon = (Element) xpath(tool, "largeIcon").item(0);
				assertNotNull(prefix, largeIcon);
				String defaultIcon = tool.getAttribute("title") + ".gif";
				
				// has it got an icon defined?
				String smallPath = smallIcon.getAttribute("path");
				if (smallPath == null || smallPath.isEmpty()) {
					// set it
					smallIcon.setAttribute("path", "icons/full/obj16/" + defaultIcon);
					smallIcon.setAttribute("bundle", "org.openiaml.model.edit");
					smallIcon.setAttribute("xsi:type", "gmftool:BundleImage");
					changed = true;
				}
				
				// the icon should exist
				assertFileExists(prefix, new File( "../" + smallIcon.getAttribute("bundle") + "/" + smallIcon.getAttribute("path") ));				

				// has it got an icon defined?
				String largePath = largeIcon.getAttribute("path");
				if (largePath == null || largePath.isEmpty()) {
					// set it
					largeIcon.setAttribute("path", "icons/full/obj32/" + defaultIcon);
					largeIcon.setAttribute("bundle", "org.openiaml.model.edit");
					largeIcon.setAttribute("xsi:type", "gmftool:BundleImage");
					changed = true;
				}
				
				// the icon should exist
				assertFileExists(prefix, new File( "../" + largeIcon.getAttribute("bundle") + "/" + largeIcon.getAttribute("path") ));				

			}
			
			if (changed) {
				System.out.println("Writing backup " + file + "...");
				saveDocument(doc, new File(file + ".backup"));
				
				System.out.println("Writing new " + file + "...");
				saveDocument(doc, new File(file));
			}
		}
	}
	
	/**
	 * In each .gmftool, there should be exactly one mapping to
	 * a node in the .gmfmap. 
	 * 
	 * @throws Exception
	 */
	public void testCheckAllToolsMapped() throws Exception {
		Map<String,Document> loaded = getGmfTools();
		
		for (String filename : loaded.keySet()) {
			Document gmftool = loaded.get(filename);
			Document gmfmap = loadGmfmap(filename);
			
			IterableElementList roots = xpath(gmftool, "/ToolRegistry/palette/tools");
			IterableElementList tools = getAllGmfTools(roots, filename);
			
			for (Element tool : tools) {
				// EventTrigger
				String toolName = tool.getAttribute("title");
				
				// should match either a node..
				IterableElementList matches = xpath(gmfmap, "/Mapping/nodes/ownedChild/domainMetaElement[contains(@href, '" + toolName + "')]");
				boolean matched = false;
				for (Element match : matches) {
					String href = match.getAttribute("href");
					if (href.endsWith(toolName)) {
						assertFalse(filename + ": More than one mapping for tool '" + toolName + "'", matched);
						matched = true;
					}
				}

				// or a link
				IterableElementList matches2 = xpath(gmfmap, "/Mapping/links/domainMetaElement[contains(@href, '" + toolName + "')]");
				for (Element match : matches2) {
					String href = match.getAttribute("href");
					if (href.endsWith(toolName)) {
						assertFalse(filename + ": More than one mapping for tool '" + toolName + "'", matched);
						matched = true;
					}
				}
				
				assertTrue(filename + ": Tool '" + toolName + "' did not match any mappings.", matched);
				
			}
		}
	}

	/**
	 * Load a corresponding .gmfmap for a .gmftool.
	 * Assumed to be the same filename as the .gmftool, except
	 * replacing the file extension.
	 * 
	 * @param filename
	 * @return
	 */
	private Document loadGmfmap(String filename) throws Exception {
		String f = filename.replace(".gmftool", ".gmfmap");
		assertNotEqual("'" + filename + "' does not have gmftool extension", f, filename);
		return loadDocument(f);
	}
	
	/**
	 * For each .gmftool creation node, the icons set to the
	 * creation tool should match the name of the element
	 * created.
	 * 
	 * @throws Exception
	 */
	public void testIconsMatch() throws Exception {
		Map<String,Document> loaded = getGmfTools();
		
		for (String filename : loaded.keySet()) {
			Document gmftool = loaded.get(filename);
			
			IterableElementList roots = xpath(gmftool, "/ToolRegistry/palette/tools");
			IterableElementList tools = getAllGmfTools(roots, filename);

			for (Element tool : tools) {
				// EventTrigger
				String toolName = tool.getAttribute("title");
				String message = "[" + filename + "] " + toolName + ": ";
				
				// should have a smallIcon
				Element smallIcon = xpathFirst(tool, "smallIcon");
				assertEndsWith(message, "/" + toolName + ".gif", smallIcon.getAttribute("path"));

				// should have a largeIcon
				Element largeIcon = xpathFirst(tool, "smallIcon");
				assertEndsWith(message, "/" + toolName + ".gif", largeIcon.getAttribute("path"));
				
			}
		}
	}

}
