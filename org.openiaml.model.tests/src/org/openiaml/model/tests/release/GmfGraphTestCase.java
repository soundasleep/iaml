/**
 * 
 */
package org.openiaml.model.tests.release;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.openiaml.model.tests.XmlTestCase;
import org.openiaml.model.xpath.IterableElementList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Tests the .gmfgraph.
 * 
 * @author jmwright
 *
 */
public class GmfGraphTestCase extends XmlTestCase {

	// TODO refactor into subclass
	public static final String GMF_ROOT = "../org.openiaml.model/model/";

	/**
	 * Load the .gmfgraph.
	 * 
	 * @return
	 * @throws Exception
	 */
	public Document getGmfgraph() throws Exception {
		return loadDocument(GMF_ROOT + "iaml.gmfgraph");
	}
	
	/**
	 * All labels that have a name of XXXFigure 
	 * within 'figures' should have an
	 * accessor in the root. 
	 * 
	 * We cannot test that all Labels have a child accessor, because
	 * we might have some design-time labels that will never change. 
	 * 
	 * @throws Exception
	 */
	public void testAllLabelsAreInRoot() throws Exception {
		Document gmfgraph = getGmfgraph();
		IterableElementList nl = xpath(gmfgraph, "//descriptors/actualFigure/children");
		
		assertNotSame("We should have at least one label node", nl.getLength(), 0);
		for (Element child : nl) {
			if (child.getAttribute("xsi:type").equals("gmfgraph:Label")) {
				String childName = child.getAttribute("name");
				// only consider labels which are called XXXFigure
				if (childName.endsWith("Figure")) {
					assertTrue("'" + childName + "' should end in 'Figure'", childName.endsWith("Figure"));
					
					String labelName = childName.replace("Figure", "");
					
					// this label should be in the root
					Element root = xpathFirst(gmfgraph, "Canvas/labels[@name='" + labelName + "']");
					assertNotNull(root);
				}
			}
		}
	}
	
	/**
	 * All elements in 'figures' should either have a node
	 * or link in the root.
	 * 
	 * @throws Exception
	 */
	public void testAllNodesAndLinks() throws Exception {
		Document gmfgraph = getGmfgraph();
		IterableElementList nl = xpath(gmfgraph, "/Canvas/figures/descriptors");
		
		assertNotSame("We should have at least one node", nl.getLength(), 0);
		for (Element child : nl) {
			String name = child.getAttribute("name");	// ButtonFigure
			
			// it should have a node
			String nodeXpath = "/Canvas/nodes[@figure='" + name + "']";
			String linkXpath = "/Canvas/connections[@figure='" + name + "']";
			Element hasNode = hasXpathFirst(gmfgraph, nodeXpath);
			Element hasLink = hasXpathFirst(gmfgraph, linkXpath);
			assertFalse("Node " + name + " cannot be both a node and a link", hasNode != null && hasLink != null);
			
			if (hasNode != null) {
				assertEquals("Root node '" + name + "' has a valid name.", hasNode.getAttribute("name"), hasNode.getAttribute("figure").replace("Figure", ""));
			} else {
				assertEquals("Root connection '" + name + "' has a valid name.", hasLink.getAttribute("name"), hasLink.getAttribute("figure").replace("Figure", ""));
			}
		}
	}
	
	/**
	 * All labels in the root should have an accessor that
	 * points to the right label in the 'figures'.
	 * 
	 * @throws Exception
	 */
	public void testRootLabelAccessors() throws Exception {
		Document gmfgraph = getGmfgraph();
		IterableElementList nl = xpath(gmfgraph, "//labels[@accessor]");
		
		assertNotSame("We should have at least one label node", nl.getLength(), 0);
		for (Element child : nl) {
			String childName = child.getAttribute("name");
			
			// it should resolve to an <accessors>
			Element accessor = resolveEmfElement(gmfgraph, child.getAttribute("accessor"));
			assertEquals(accessor.getNodeName(), "accessors");
			assertTrue("Accessor '" + child.getAttribute("name") + "' should have a 'figure' attribute", accessor.hasAttribute("figure"));
			
			// resolve this figure too, it should resolve to a <children>
			Element figure = resolveEmfElement(gmfgraph, accessor.getAttribute("figure"));
			assertEquals(figure.getNodeName(), "children");
			
			// it should have resolved to XXXFigure
			assertEquals(figure.getAttribute("name"), childName + "Figure");
			
			// and it should be of a type label
			assertEquals(figure.getAttribute("xsi:type"), "gmfgraph:Label");
		}
	}
	
	/**
	 * Make sure all 'figures/descriptors' are unique.
	 * @throws Exception
	 */
	public void testUniqueDescriptors() throws Exception {
		Document gmfgraph = getGmfgraph();
		IterableElementList nl = xpath(gmfgraph, "/Canvas/figures/descriptors");
		
		assertNotSame("We should have at least one descriptor node", nl.getLength(), 0);
		Set<String> found = new HashSet<String>();
		for (Element child : nl) {
			String childName = child.getAttribute("name");
			
			assertFalse("More than one descriptor found for '" + childName + "'", found.contains(childName));
			found.add(childName);
		}
	}

	
	/**
	 * Make sure all 'nodes' are unique.
	 * @throws Exception
	 */
	public void testUniqueNodes() throws Exception {
		Document gmfgraph = getGmfgraph();
		IterableElementList nl = xpath(gmfgraph, "/Canvas/nodes");
		
		assertNotSame("We should have at least one node node", nl.getLength(), 0);
		Set<String> found = new HashSet<String>();
		for (Element child : nl) {
			String childName = child.getAttribute("name");
			
			assertFalse("More than one node found for '" + childName + "'", found.contains(childName));
			found.add(childName);
		}
	}

	
	/**
	 * Make sure all 'connections' are unique.
	 * @throws Exception
	 */
	public void testUniqueConnections() throws Exception {
		Document gmfgraph = getGmfgraph();
		IterableElementList nl = xpath(gmfgraph, "/Canvas/connections");
		
		assertNotSame("We should have at least one connection node", nl.getLength(), 0);
		Set<String> found = new HashSet<String>();
		for (Element child : nl) {
			String childName = child.getAttribute("name");

			assertFalse("More than one connection found for '" + childName + "'", found.contains(childName));
			found.add(childName);
		}
	}
	
	/**
	 * Make sure all 'labels' are unique.
	 * @throws Exception
	 */
	public void testUniqueLabels() throws Exception {
		Document gmfgraph = getGmfgraph();
		IterableElementList nl = xpath(gmfgraph, "/Canvas/labels");
		assertNotSame("We should have at least one label node", nl.getLength(), 0);
		
		Set<String> found = new HashSet<String>();
		for (Element child : nl) {
			String childName = child.getAttribute("name");
			
			assertFalse("More than one label found for '" + childName + "'", found.contains(childName));
			found.add(childName);
		}
	}
	
	/**
	 * Issue 56: A node element should only have one label with
	 * an element icon.
	 * 
	 */
	public void testDuplicateIcons() throws Exception {
		Document gmfgraph = getGmfgraph();
		IterableElementList nl = xpath(gmfgraph, "/Canvas/labels");
		assertNotSame("We should have at least one label node", nl.getLength(), 0);
		
		// store a map of [/accessor]->[element icon count]
		Map<String,Integer> elementIconMap = new HashMap<String,Integer>();
		// a map back to the original label element [for debug]
		Map<String,String> labelMap = new HashMap<String,String>();
		
		for (Element e : nl) {
			if (!e.getAttribute("elementIcon").equals("false")) {
				// get the accessor
				String accessorKey = getAccessorKey(e);
				if (!elementIconMap.containsKey(accessorKey)) {
					elementIconMap.put(accessorKey, 0);
					labelMap.put(accessorKey, e.getAttribute("name"));
				}
				elementIconMap.put(accessorKey, elementIconMap.get(accessorKey) + 1);
			}
		}
		
		// check all the contents
		for (String acc : elementIconMap.keySet()) {
			if (elementIconMap.get(acc) > 1) {
				fail("Element '" + labelMap.get(acc) + "' has " + elementIconMap.get(acc) + " labels with icons");
			}
		}
		
	}

	/**
	 * Translate
	 * <e accessor="//@figures.0/@descriptors.25/@accessors.0"/>
	 * into
	 * "//@figures.0/@descriptors.25"
	 * 
	 * @param e
	 * @return
	 */
	private String getAccessorKey(Element e) {
		String ac = e.getAttribute("accessor");
		return ac.substring(0, ac.lastIndexOf('/'));
	}

}
