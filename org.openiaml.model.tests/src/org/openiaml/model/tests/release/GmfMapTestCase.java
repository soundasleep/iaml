/**
 * 
 */
package org.openiaml.model.tests.release;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openiaml.model.tests.XmlTestCase;
import org.openiaml.model.xpath.IterableElementList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Tests all .gmfmaps
 * 
 * @author jmwright
 *
 */
public class GmfMapTestCase extends XmlTestCase {

	/**
	 * Load up all the .gmfmap's
	 * 
	 */
	public Map<String,Document> getGmfMaps() throws Exception {
		Map<String,Document> loadedMaps = new HashMap<String,Document>();
		
		// load all .gmftool's
		for (String map : getMapList()) {
			loadedMaps.put( map, loadDocument(map) );
		}

		return loadedMaps;
	}
	
	/**
	 * Load up all the .gmftool's from {@link GmfToolTestCase}.
	 * 
	 * @see GmfToolTestCase#getGmfTools
	 */
	public Map<String,Document> getGmfTools() throws Exception {
		return new GmfToolTestCase().getGmfTools();
	}
	
	/**
	 * Create a list of gmfmaps from {@link PluginsTestCase}.
	 * 
	 * @return
	 */
	private static Set<String> getMapList() {
		Set<String> tools = new HashSet<String>();
		for (String gmfgen : PluginsTestCase.GMFGENS) {
			tools.add(gmfgen.replace(".gmfgen", ".gmfmap"));
		}
		return tools;
	}

	/**
	 * There should be the same number of maps specified here
	 * as in {@link PluginsTestCase}.
	 * 
	 * @throws Exception
	 */
	public void testGmfMapMatch() throws Exception {
		assertEquals(getMapList().size(), PluginsTestCase.GMFGENS.length);
		assertEquals(getGmfMaps().size(), PluginsTestCase.GMFGENS.length);
	}

	/**
	 * Iterate over the nodes in the given node list to make sure
	 * the containment features are unique with respect to loadedContainers.
	 * 
	 * @param filename currently loaded gmfmap file
	 * @param nodes list of nodes to iterate over
	 * @param loadedContainers containment features found
	 * @throws Exception
	 */
	private void iterateOverNodes(String filename, IterableElementList nodes, Map<String, List<String>> loadedContainers, String constraintXpath)
			throws Exception {
		for (Element node : nodes) {
			// all nodes must be contained somewher
			try {
				if (hasXpathFirst(node, "containmentFeature") == null) {
					// it does not have a containment feature: a phantom node
					continue;
				}
				Element containNode = (Element) xpathFirst(node, "containmentFeature");
				String containment = containNode.getAttribute("href"); 
					
				Element oclNode = (Element) hasXpathFirst(node, constraintXpath);
				String ocl = null;
				if (oclNode != null) {
					// all OCL must have a body node
					ocl = oclNode.getAttribute("body");
					assertNotNull(filename + ": Null OCL found for element '" + node + "'", ocl);
					
					// get rid of whitespace
					ocl = ocl.replaceAll("\\s", "");
					
					// it should not be empty
					assertFalse("OCL should not be empty", ocl.isEmpty());
				}
				
				if (loadedContainers.containsKey(containment)) {
					// it already exists
					// make sure we have ocl
					assertNotNull(filename + ": Multiple containment found for '" + containment + "', but no OCL found for element '" + node + "'", ocl);
					
					// the ocl should not exist in the map already
					assertFalse(filename + ": OCL '" + ocl + "' already exists for containment '" + containment + "'", loadedContainers.get(containment).contains(ocl));
					
					// no existing OCL must be blank (i.e. an empty OCL from a previous containment mapping)
					assertNotEqual(filename + ": containment '" + containment + "' contains a non-constrained previous containment mapping", loadedContainers.get(containment).size(), 0);
				} else {
					// add it
					List<String> newList = new ArrayList<String>();
					if (ocl != null) {
						// add current OCL as a containment
						newList.add(ocl);
					}
					
					loadedContainers.put(containment, newList);
					
				}
			} catch (Exception e) {
				throw new RuntimeException(node + ": " + e.getMessage(), e);
			}
		}
	}
	
	/**
	 * If a .gmfmap has multiple containers, each of the different
	 * node elements should have a separate OCL expression. 
	 * 
	 * @throws Exception
	 */
	public void testMultipleContainersHaveOcl() throws Exception {
		for (String filename : getGmfMaps().keySet()) {
			// reset stored containers
			Map<String, List<String>> loadedContainers = new HashMap<String, List<String>>();
			
			Document doc = getGmfMaps().get(filename);
			
			try {
				IterableElementList nodes = xpath(doc, "/Mapping/nodes");
				iterateOverNodes(filename, nodes, loadedContainers, "ownedChild/domainSpecialization");
	
				IterableElementList links = xpath(doc, "/Mapping/links");
				iterateOverNodes(filename, links, loadedContainers, "domainSpecialization");
			} catch (Exception e) {
				throw new RuntimeException(filename + ": " + e.getMessage(), e);
			}
			
		}
	}
	
	/**
	 * All label mappings should match their contained node.
	 * 
	 * @see #assertLabelMappingMatches(String, String, String)
	 */
	public void testMappingsMatch() throws Exception {
		for (String filename : getGmfMaps().keySet()) {
			Document doc = getGmfMaps().get(filename);
			
			IterableElementList nodes = xpath(doc, "/Mapping/nodes");
			for (Element node : nodes) {
				// all nodes must be contained somewhere
				Element domainMetaElement = (Element) xpathFirst(node, "ownedChild/domainMetaElement");
				
				// iaml.ecore#//EventTrigger
				String elementName = domainMetaElement.getAttribute("href");
				
				IterableElementList labels = xpath(node, "ownedChild/labelMappings");
				for (Element label : labels) {
					Element diagramLabel = (Element) xpathFirst(label, "diagramLabel");
					
					// iaml.gmfgraph#EventTriggerName
					String labelName = diagramLabel.getAttribute("href");
					
					assertLabelMappingMatches(filename, elementName, labelName);
				}
				
				// make sure the diagramNode matches too
				Element diagramNode = (Element) xpathFirst(node, "ownedChild/diagramNode");
				
				// iaml.gmfgraph#EventTrigger
				String dnode = diagramNode.getAttribute("href");
				
				assertNodeMappingMatches(filename, elementName, dnode);
				
				// finally, check the tool matches
				// IF it has a toolnode
				Element toolNode = (Element) hasXpathFirst(node, "ownedChild/tool");
				
				if (toolNode != null) {
					// visual.gmftool#//@palette/@tools.0/@tools.22
					String tool = toolNode.getAttribute("href");
					
					assertToolMappingMatches(filename, elementName, tool);
				}
			}
			
			IterableElementList links = xpath(doc, "/Mapping/links");
			for (Element link : links) {
				// all nodes must be contained somewhere
				Element domainMetaElement = (Element) xpathFirst(link, "domainMetaElement");
				
				// iaml.ecore#//EventTrigger
				String elementName = domainMetaElement.getAttribute("href");
				
				IterableElementList labels = xpath(link, "labelMappings");
				for (Element label : labels) {
					Element diagramLabel = (Element) xpathFirst(label, "diagramLabel");
					
					// iaml.gmfgraph#EventTriggerName
					String labelName = diagramLabel.getAttribute("href");
					
					assertLabelMappingMatches(filename, elementName, labelName);
				}
				
				// make sure the diagramNode matches too
				Element diagramNode = (Element) xpathFirst(link, "diagramLink");
				
				// iaml.gmfgraph#EventTrigger
				String dnode = diagramNode.getAttribute("href");
				
				assertNodeMappingMatches(filename, elementName, dnode);
				
				// finally, check the tool matches
				// IF it has a toolnode
				Element toolNode = (Element) hasXpathFirst(link, "tool");
				
				if (toolNode != null) {
					// visual.gmftool#//@palette/@tools.0/@tools.22
					String tool = toolNode.getAttribute("href");
					
					assertToolMappingMatches(filename, elementName, tool);
				}
			}
		}
	}

	/**
	 * Assert that the given tool mapping matches the given element.
	 * 
	 * Maps the tool mapping to a loaded .gmftool document through {@link #findGmftoolFor(String)}. 
	 * 
	 * @see #findGmftoolFor(String)
	 * @param filename
	 * @param elementName "iaml.ecore#//EventTrigger"
	 * @param tool "visual.gmftool#//@palette/@tools.0/@tools.22"
	 * @returns the resolved tool mapping node from the gmftool
	 */
	private Element assertToolMappingMatches(String filename, String elementName,
			String tool) throws Exception {

		// get the last name (ignore sub-packages)
		String element = elementName.substring(elementName.lastIndexOf("/") + 1);
		// element = EventTrigger
		
		// find the appropriate gmftool
		String toolFilename = tool.substring(0, tool.indexOf("#"));
		String href = tool.substring(tool.indexOf("#") + 1);
		String toolResolved = findGmftoolFor(toolFilename);
		
		Element toolNode;
		try {
			toolNode = resolveEmfElement( getGmfTools().get(toolResolved), href);
		} catch (RuntimeException e) {
			throw new RuntimeException(filename + ": Could not load element href '" + href + "' from '" + toolFilename + "', resolved to '" + toolResolved + "': " + e.getMessage(), e);
		}
		
		// EventTrigger
		String toolName = toolNode.getAttribute("title");
		
		// these should be the same
		assertEquals(filename + ": tool mapping did not match", element, toolName);
		
		return toolNode;
	}

	/**
	 * Look into the map of loaded gmftools, and find which one matches
	 * the given filename.
	 * 
	 * e.g. gmftools = ['foo/bar/baz/visual.gmftool', ...]
	 * toolFilename = 'visual.gmftool'
	 * 
	 * Returns 'foo/bar/baz/visual.gmftool', the key in the gmftool map.
	 * 
	 * @see #getGmfTools()
	 * @param toolFilename
	 * @return
	 */
	private String findGmftoolFor(String toolFilename) throws Exception {
		for (String filename : getGmfTools().keySet()) {
			if (filename.endsWith(toolFilename)) {
				return filename;
			}
		}
		fail("Could not find gmftool mapping for filename '" + toolFilename + "'");
		return null;
	}

	/**
	 * Assert that the given node mapping matches the given element.
	 * 
	 * @param filename
	 * @param elementName "iaml.ecore#//EventTrigger"
	 * @param nodeName "iaml.gmfgraph#EventTrigger"
	 */
	private void assertNodeMappingMatches(String filename, String elementName,
			String nodeName) {
		// get the last name (ignore sub-packages)
		String element = elementName.substring(elementName.lastIndexOf("/") + 1);
		// element = EventTrigger

		String node = nodeName.substring(nodeName.indexOf("#") + 1);
		// node = EventTrigger
		
		// they should be the same
		assertEquals(filename + ": Element '" + elementName + "' and node '" + nodeName + "' do not match: '" + element + "', '" + node + "'",
				node, element);

	}

	/**
	 * Assert that the given label matches the given element.
	 * 
	 * @param elementName "iaml.ecore#//EventTrigger"
	 * @param labelName "iaml.gmfgraph#EventTriggerName"
	 */
	private void assertLabelMappingMatches(String filename, String elementName, String labelName) {
		// get the last name (ignore sub-packages)
		String element = elementName.substring(elementName.lastIndexOf("/") + 1);
		// element = EventTrigger
		
		String label = labelName.substring(labelName.indexOf("#") + 1);
		// label = EventTriggerName

		// they should have the same prefix
		assertTrue(filename + ": Element '" + elementName + "' and label '" + labelName + "' do not match: '" + element + "', '" + label + "'",
			label.startsWith(element));
		
	}
	
}
