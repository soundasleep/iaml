/**
 * 
 */
package org.openiaml.model.tests.release;

import java.util.Set;

import org.openiaml.emf.SoftCache;
import org.openiaml.model.tests.XmlTestCase;
import org.openiaml.model.xpath.IterableElementList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Tests that all model elements have Stereotype/instance class name labels.
 * 
 * @author jmwright
 *
 */
public class StereotypesTestCase extends XmlTestCase {

	/**
	 * Test that the .gmfgraph has labels for all nodes and edges.
	 * @throws Exception 
	 * 
	 */
	public void testGmfGraphHasLabels() throws Exception {

		Document gmfgraph = getGmfgraph();
		IterableElementList nl = xpath(gmfgraph, "/Canvas/figures/descriptors");
		
		assertNotSame("We should have at least one figure node", nl.getLength(), 0);
		
		boolean changed = false;
		int descriptorNumber = -1;
		for (Element child : nl) {
			descriptorNumber++;
			
			// get the element name
			String name = child.getAttribute("name");
			assertTrue(name + " doesn't end with Figure", name.endsWith("Figure"));
			
			// name = InputTextField
			name = name.substring(0, name.length() - "Figure".length());
			
			String xpath = "actualFigure/children[@name='" + name + "StereotypeFigure']";
			
			// try adding one
			if (hasXpathFirst(child, xpath) == null) {
				System.out.println("Adding label " + name + "StereotypeFigure");

				// create the new element
				Element n = gmfgraph.createElement("children");
				n.setAttribute("xsi:type", "gmfgraph:Label");
				n.setAttribute("name", name + "StereotypeFigure");
				n.setAttribute("text", ": " + name);		// ": EventTrigger"
				
				// find the first child
				IterableElementList children = xpath(child, "actualFigure/children");
				Element actualFigure = xpathFirst(child, "actualFigure");
				int newAccessorIndex = -1;
				if (children.size() < 2) {
					// add directly to actualFigure
					actualFigure.appendChild(n);
					newAccessorIndex = children.size();
				} else {
					// add to the second child
					actualFigure.insertBefore(n, children.item(1));
					newAccessorIndex = 1;
				}
				
				// we also need to add a new ChildAccess
				String newHref = "//@figures.0/@descriptors." + descriptorNumber + "/@actualFigure/@children." + children.size();
				Element newAccessor = gmfgraph.createElement("accessors");
				newAccessor.setAttribute("figure", newHref);
				child.appendChild(newAccessor);
				
				// rewrite all other root accessors
				{
					IterableElementList accessors2 = xpath(gmfgraph, "/Canvas/labels");
					for (Element a : accessors2) {
						String href = a.getAttribute("accessor");
						if (href.startsWith("//@figures.0/@descriptors." + descriptorNumber + "/")) {
							if (href.endsWith(".0")) {
								// ignore first
							} else {
								// rewrite
								int newIndex = Integer.parseInt(href.substring(href.lastIndexOf(".") + 1)) + 1;
								System.out.println(href + " -> " + newIndex);
								String newHref2 = "//@figures.0/@descriptors." + descriptorNumber + "/@accessors." + newIndex;
								a.setAttribute("accessor", newHref2);
							}
						}
					}
				}
				
				// finally, add a diagram label to the root
				Element newLabel = gmfgraph.createElement("labels");
				newLabel.setAttribute("name", name + "Stereotype");
				newLabel.setAttribute("figure", name + "Figure");
				newLabel.setAttribute("elementIcon", "false");
				newLabel.setAttribute("accessor", "//@figures.0/@descriptors." + descriptorNumber + "/@accessors." + newAccessorIndex);
				xpathFirst(gmfgraph, "/Canvas").appendChild(newLabel);				
				changed = true;
			}
			
			// there should be a Stereotype label
			// will fail if not
			Element label = xpathFirst(child, xpath);
			
			// check its properties
			assertEquals("gmfgraph:Label", label.getAttribute("xsi:type"));
			assertEquals(": " + name, label.getAttribute("text"));
			
			// make sure that it's second
			IterableElementList children = xpath(child, "actualFigure/children");
			assertNotSame("We should have at least one children node", children.getLength(), 0);
			
			// if it has 2 or more nodes, it must be second
			if (children.size() >= 2) {
				assertEquals(name, label, children.item(1));
			}

		}
		
		if (changed) {
			System.out.println("Writing " + GmfGraphTestCase.GMF_FILENAME + "...");
			saveDocument(gmfgraph, GmfGraphTestCase.GMF_ROOT + GmfGraphTestCase.GMF_FILENAME);
		}

	}
	
	/**
	 * Test that all nodes in each .gmfmap have the mapping
	 * to a stereotype label.
	 * 
	 * <p>The label mapping <em>must not</em> be a design label mapping, or 
	 * else the expected icons will not be rendered:
	 * see http://www.eclipse.org/forums/index.php?t=msg&th=169402
	 * 
	 * @throws Exception
	 */
	public void testGmfMapMappingsToStereotypesNodes() throws Exception {
		
		for (String filename : getMapList()) {
			boolean changed = false;
			
			Document doc = getMapCache().get(filename);
			
			IterableElementList nodes = xpath(doc, "/Mapping/nodes");
			for (Element node : nodes) {
				Element ownedChild = xpathFirst(node, "ownedChild");
				
				// all nodes must be contained somewhere
				Element domainMetaElement = (Element) xpathFirst(ownedChild, "domainMetaElement");
				
				// iaml.ecore#//EventTrigger
				String elementName = domainMetaElement.getAttribute("href");
				// get the last name (ignore sub-packages) -> EventTrigger
				elementName = elementName.substring(elementName.lastIndexOf("/") + 1);
				
				// is it currently a design label mapping? if so, delete it
				{
					IterableElementList mapping = xpath(ownedChild, "labelMappings");
					assertNotSame(filename + ": No label mappings found for " + elementName, 0, mapping.size());
					boolean found = false;
					int count = -1;
					for (Element map : mapping) {
						count++;
						Element labelHref = xpathFirst(map, "diagramLabel");
						// iaml.gmfgraph#InputFormStereotype
						String href = labelHref.getAttribute("href");
						if (href.endsWith("#" + elementName + "Stereotype")) {
							assertFalse(filename + ": Found two mappings for " + elementName, found);
							
							if ("gmfmap:DesignLabelMapping".equals(map.getAttribute("xsi:type"))) {
								// delete this element
								ownedChild.removeChild(map);
								changed = true;
							}
						}
					}
				}
				
				// there must be at least one label mapping to a stereotype label
				{
					IterableElementList mapping = xpath(ownedChild, "labelMappings");
					boolean found = false;
					int count = -1;
					for (Element map : mapping) {
						count++;
						Element labelHref = xpathFirst(map, "diagramLabel");
						// iaml.gmfgraph#InputFormStereotype
						String href = labelHref.getAttribute("href");
						if (href.endsWith("#" + elementName + "Stereotype")) {
							assertFalse(filename + ": Found two mappings for " + elementName, found);
							found = true;
							
							// if this is first, it should be the only child
							if (count == 0 && mapping.size() != 1) {
								fail(filename + ": Stereotype mapping should have been first in an element with only one label: " + href);
							} else if (count > 1) {
								fail(filename + ": Stereotype mapping must be second: " + href);
							}
						}
					}

					if (!found) {
						// try adding it manually
						Element newMapping = doc.createElement("labelMappings");
						// must not be design label mapping
						// newMapping.setAttribute("xsi:type", "gmfmap:DesignLabelMapping");
						newMapping.setAttribute("readOnly", "true");
						
						// insert as second child
						if (mapping.size() < 2) {
							ownedChild.appendChild(newMapping);
						} else {
							ownedChild.insertBefore(newMapping, mapping.item(1));
						}
						
						// add href
						Element newHref = doc.createElement("diagramLabel");
						newHref.setAttribute("href", GmfGraphTestCase.GMF_FILENAME + "#" + elementName + "Stereotype");
						newMapping.appendChild(newHref);
						
						changed = true;
					}
				}
				
				// then search again
				{
					IterableElementList mapping = xpath(ownedChild, "labelMappings");
					assertNotSame(filename + ": No label mappings found for " + elementName, 0, mapping.size());
					boolean found = false;
					int count = -1;
					for (Element map : mapping) {
						count++;
						Element labelHref = xpathFirst(map, "diagramLabel");
						// iaml.gmfgraph#InputFormStereotype
						String href = labelHref.getAttribute("href");
						if (href.endsWith("#" + elementName + "Stereotype")) {
							assertFalse(filename + ": Found two mappings for " + elementName, found);
							found = true;
							
							// check attributes
							assertEquals("true", map.getAttribute("readOnly"));
							
							// must not be design label mapping
							assertNotEqual(filename + ": " + href, "gmfmap:DesignLabelMapping", map.getAttribute("xsi:type"));
							
							// if this is first, it should be the only child
							if (count == 0 && mapping.size() != 1) {
								fail(filename + ": Stereotype mapping should have been first in an element with only one label");
							} else if (count > 1) {
								fail(filename + ": Stereotype mapping must be second");
							}
						}
					}

					// it must be OK now
					assertTrue(filename + ": Found no Stereotype mappings for " + elementName, found);
				}				
			
			}
			
			if (changed) {
				System.out.println("Writing " + filename + "...");
				saveDocument(doc, filename);
			}
		}
		
	}
	

	/**
	 * Test that all links in each .gmfmap have the mapping
	 * to a stereotype label.
	 * 
	 * <p>The label mapping <em>must not</em> be a design label mapping, or 
	 * else the expected icons will not be rendered:
	 * see http://www.eclipse.org/forums/index.php?t=msg&th=169402
	 * 
	 * @throws Exception
	 */
	public void testGmfMapMappingsToStereotypesLinks() throws Exception {
		
		for (String filename : getMapList()) {
			boolean changed = false;
			
			Document doc = getMapCache().get(filename);
			
			IterableElementList nodes = xpath(doc, "/Mapping/links");
			for (Element node : nodes) {

				// all nodes must be contained somewhere
				Element domainMetaElement = (Element) xpathFirst(node, "domainMetaElement");
				
				// iaml.ecore#//EventTrigger
				String elementName = domainMetaElement.getAttribute("href");
				// get the last name (ignore sub-packages) -> EventTrigger
				elementName = elementName.substring(elementName.lastIndexOf("/") + 1);
				
				// is it currently a design label mapping? if so, delete it
				{
					IterableElementList mapping = xpath(node, "labelMappings");
					assertNotSame(filename + ": No label mappings found for " + elementName, 0, mapping.size());
					boolean found = false;
					int count = -1;
					for (Element map : mapping) {
						count++;
						Element labelHref = xpathFirst(map, "diagramLabel");
						// iaml.gmfgraph#InputFormStereotype
						String href = labelHref.getAttribute("href");
						if (href.endsWith("#" + elementName + "Stereotype")) {
							assertFalse(filename + ": Found two mappings for " + elementName, found);
							
							if ("gmfmap:DesignLabelMapping".equals(map.getAttribute("xsi:type"))) {
								// delete this element
								node.removeChild(map);
								changed = true;
							}
						}
					}
				}
				
				// there must be at least one label mapping to a stereotype label
				{
					IterableElementList mapping = xpath(node, "labelMappings");
					// its OK to have a link with no node mappings
					boolean found = false;
					int count = -1;
					for (Element map : mapping) {
						count++;
						Element labelHref = xpathFirst(map, "diagramLabel");
						// iaml.gmfgraph#InputFormStereotype
						String href = labelHref.getAttribute("href");
						if (href.endsWith("#" + elementName + "Stereotype")) {
							assertFalse(filename + ": Found two mappings for " + elementName, found);
							found = true;
							
							// if this is first, it should be the only child
							if (count == 0 && mapping.size() != 1) {
								fail(filename + ": Stereotype mapping should have been first in an element with only one label");
							} else if (count > 1) {
								fail(filename + ": Stereotype mapping must be second");
							}
						}
					}

					if (!found) {
						// try adding it manually
						Element newMapping = doc.createElement("labelMappings");
						// must not be design label mapping
						// newMapping.setAttribute("xsi:type", "gmfmap:DesignLabelMapping");
						newMapping.setAttribute("readOnly", "true");
						
						// insert as second child
						if (mapping.size() < 2) {
							node.appendChild(newMapping);
						} else {
							node.insertBefore(newMapping, mapping.item(1));
						}
						
						// add href
						Element newHref = doc.createElement("diagramLabel");
						newHref.setAttribute("href", GmfGraphTestCase.GMF_FILENAME + "#" + elementName + "Stereotype");
						newMapping.appendChild(newHref);
						
						changed = true;
					}
				}
				
				// then search again
				{
					IterableElementList mapping = xpath(node, "labelMappings");
					// its OK to have a link with no node mappings
					boolean found = false;
					int count = -1;
					for (Element map : mapping) {
						count++;
						Element labelHref = xpathFirst(map, "diagramLabel");
						// iaml.gmfgraph#InputFormStereotype
						String href = labelHref.getAttribute("href");
						if (href.endsWith("#" + elementName + "Stereotype")) {
							assertFalse(filename + ": Found two mappings for " + elementName, found);
							found = true;
							
							// check attributes
							assertEquals("true", map.getAttribute("readOnly"));
							
							// must not be design label mapping
							assertNotEqual(filename + ": " + href, "gmfmap:DesignLabelMapping", map.getAttribute("xsi:type"));
							
							// if this is first, it should be the only child
							if (count == 0 && mapping.size() != 1) {
								fail(filename + ": Stereotype mapping should have been first in an element with only one label");
							} else if (count > 1) {
								fail(filename + ": Stereotype mapping must be second");
							}
						}
					}

					// it must be OK now
					assertTrue(filename + ": Found no Stereotype mappings for " + elementName, found);
				}				
			
			}
			
			if (changed) {
				System.out.println("Writing " + filename + "...");
				saveDocument(doc, filename);
			}
		}
		
	}

	private SoftCache<String, Document> getMapCache() {
		return GmfMapTestCase.getMapCache();
	}

	private Set<String> getMapList() {
		return GmfMapTestCase.getMapList();
	}

	private Document getGmfgraph() throws Exception {
		return GmfGraphTestCase.getGmfgraph();
	}
	
}
