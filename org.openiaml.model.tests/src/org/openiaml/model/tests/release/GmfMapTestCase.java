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

import javax.xml.xpath.XPathExpressionException;

import org.openiaml.emf.SoftCache;
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

	private static SoftCache<String,Document> mapCache = new SoftCache<String,Document>() {

		@Override
		public Document retrieve(String input) {
			try {
				return loadDocument(input);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
	};
	
	public static SoftCache<String,Document> getMapCache() {
		return mapCache;
	}

	private static Set<String> loadedMaps = null;
	
	/**
	 * Create a list of gmfmaps from {@link PluginsTestCase}.
	 * 
	 * @return
	 */
	public static Set<String> getMapList() {
		if (loadedMaps == null) {
			loadedMaps = new HashSet<String>();
			for (String gmfgen : PluginsTestCase.getAllGmfGens()) {
				loadedMaps.add(gmfgen.replace(".gmfgen", ".gmfmap"));
			}
		}
		return loadedMaps;
	}

	/**
	 * There should be the same number of maps specified here
	 * as in {@link PluginsTestCase}.
	 * 
	 * @throws Exception
	 */
	public void testGmfMapMatch() throws Exception {
		assertEquals(getMapList().size(), PluginsTestCase.getAllGmfGens().size());
		assertEquals(getMapList().size(), PluginsTestCase.getAllGmfGens().size());
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
		for (String filename : getMapList()) {
			// reset stored containers
			Map<String, List<String>> loadedContainers = new HashMap<String, List<String>>();
			
			Document doc = getMapCache().get(filename);
			
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
		for (String filename : getMapList()) {
			boolean changed = false;
			
			Document doc = getMapCache().get(filename);
			
			IterableElementList nodes = xpath(doc, "/Mapping/nodes");
			for (Element node : nodes) {
				// all nodes must be contained somewhere
				Element domainMetaElement = (Element) xpathFirst(node, "ownedChild/domainMetaElement");
				
				// can it be contained?
				String containmentName = getContainmentName(filename, node);
				
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
					
					// find out which tool it should be
					String expected = getDesiredToolMappingMatch(filename, elementName, containmentName);
					
					// set it if different
					if (!tool.equals(expected)) {
						// set it
						toolNode.setAttribute("href", expected);
						changed = true;
					}
					
					assertToolMappingMatches(filename, elementName, expected, containmentName);
				}
			}
			
			IterableElementList links = xpath(doc, "/Mapping/links");
			for (Element link : links) {
				// all nodes must be contained somewhere
				Element domainMetaElement = (Element) xpathFirst(link, "domainMetaElement");
				
				String containmentName = getContainmentName(filename, link);

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
					
					// find out which tool it should be
					String expected = getDesiredToolMappingMatch(filename, elementName, containmentName);
					
					// set it if different
					if (!tool.equals(expected)) {
						// set it
						toolNode.setAttribute("href", expected);
						changed = true;
					}
					
					assertToolMappingMatches(filename, elementName, expected, containmentName);
				}
			}
			
			if (changed) {
				System.out.println("Writing " + filename + "...");
				saveDocument(doc, filename);
			}
		}
	}

	/**
	 * Does the given node or link have a containment feature?
	 * 
	 * @param node the element to search from, either node or link
	 * @return the name of the containment feature, or <code>null</code>
	 * @throws XPathExpressionException 
	 */
	private String getContainmentName(String filename, Element node) throws XPathExpressionException {
		Element containmentFeature = hasXpathFirst(node, "containmentFeature");
		String containmentName = null;	// default = empty
		if (containmentFeature != null) {
			// "iaml.ecore#//VisibleThing/onClick"
			containmentName = containmentFeature.getAttribute("href");
			assertNotNull(filename + ": containment feature for '" + node + "' should not have been null: " + containmentFeature, containmentName);
		
			// "onClick"
			assertTrue(containmentName.contains("/"));
			containmentName = containmentName.substring(containmentName.lastIndexOf("/") + 1);
		}
		
		return containmentName;
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
	 * @param containmentFeature containment feature of the element, e.g. "onEdit"
	 * @returns the resolved tool mapping node from the gmftool
	 */
	private Element assertToolMappingMatches(String filename, String elementName,
			String tool, String containmentFeature) throws Exception {
		
		// get the last name (ignore sub-packages)
		String element = elementName.substring(elementName.lastIndexOf("/") + 1);
		// element = EventTrigger
		
		// find the appropriate gmftool
		String toolFilename = tool.substring(0, tool.indexOf("#"));
		String href = tool.substring(tool.indexOf("#") + 1);
		String toolResolved = findGmftoolFor(toolFilename);
		
		Element toolNode;
		try {
			toolNode = resolveEmfElement( GmfToolTestCase.getToolCache().get(toolResolved), href);
		} catch (RuntimeException e) {
			throw new RuntimeException(filename + ": Could not load element href '" + href + "' from '" + toolFilename + "', resolved to '" + toolResolved + "': " + e.getMessage(), e);
		}
		
		// EventTrigger
		String toolName = toolNode.getAttribute("title");
		
		// these should be the same
		assertTrue(filename + ": tool mapping did not match: '" + element + "', '" + toolName + "'", toolMatches(toolName, element, containmentFeature) );
		
		return toolNode;
	}
	
	/**
	 * Get the actual desired tool mapping for the given tool name,
	 * within the given .gmftool Document 
	 * 
	 * @param the current gmfmap filename, "visual.gmfmap"
	 * @param elementName "iaml.ecore#//EventTrigger"
	 * @param containmentFeature the containment feature, e.g. "onEdit"
	 * @return "visual.gmftool#//@palette/@tools.0/@tools.22"
	 * @throws XPathExpressionException 
	 */
	private String getDesiredToolMappingMatch(String filename, 
			String elementName, String containmentFeature) throws XPathExpressionException {
		
		// get the last name (ignore sub-packages)
		String element = elementName.substring(elementName.lastIndexOf("/") + 1);
		// element = EventTrigger

		assertTrue("filename does not contain .gmfmap: " + filename, filename.contains(".gmfmap"));
		
		filename = filename.replace(".gmfmap", ".gmftool");
		Document doc = GmfToolTestCase.getToolCache().get(filename);
		
		for (Element tool : xpath(doc, "//tools")) {
			
			if (tool.hasAttribute("title") && toolMatches(tool.getAttribute("title"), element, containmentFeature)) {
				// we found the target tool
				String ref = compileEmfReference( filename.substring(filename.lastIndexOf("/") + 1), tool );
				
				// replace '@palette.0' with '@palette'
				ref = ref.replace("/@palette.0/", "/@palette/");
				
				return ref;
			}
			
		}
		
		// couldn't find anything
		fail("Could not find tool mapping for '" + elementName + "' in filename '" + filename + "' for containment '" + containmentFeature + "'");
		return null;
	}
	
	/**
	 * Does the given tool title match the given element name title?
	 * 
	 * @param title 'EventTrigger' or 'EventTrigger [onClick]'
	 * @param elementName 'EventTrigger' 
	 */
	private boolean toolMatches(String title, String elementName, String containmentFeature) {
		if (title.equals(elementName))
			return true;
		
		if (title.contains("[")) {
			// remove [containmentFeature] so we only match this containment feature
			title = title.replace(" [" + containmentFeature + "]", "");
			
			if (title.equals(elementName))
				return true;
		}
		
		return false;
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
		for (String filename : GmfToolTestCase.getToolList()) {
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
