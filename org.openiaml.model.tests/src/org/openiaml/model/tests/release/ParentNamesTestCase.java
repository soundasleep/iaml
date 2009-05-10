/**
 * 
 */
package org.openiaml.model.tests.release;

import org.openiaml.model.tests.XmlTestCase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Tests model files for the ParentNames
 * 
 * @author jmwright
 *
 */
public class ParentNamesTestCase extends XmlTestCase {

	// TODO refactor into subclass
	public static final String GMF_ROOT = "../org.openiaml.model/model/";

	/**
	 * Ideally this would be from some design document. This
	 * is the list of all elements that we expect will contain 
	 * parent name elements.
	 */
	public static final String[] PARENT_NAME_ELEMENTS = new String[] {
		"DomainObject",
		"DomainAttribute",
		"EventTrigger",
		"SingleOperation",
		"ChainedOperation",
		"CompositeOperation",
		"ApplicationElementProperty",
		"InputForm",
		"InputTextField",
		"CompositeCondition",
		"Button",
	};
	
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
	 * Test to make sure that {@link #PARENT_NAME_ELEMENTS} is
	 * complete, based on the .gmfgraph.
	 * 
	 * @throws Exception
	 */
	public void testAllParents() throws Exception {
		Document gmfgraph = getGmfgraph();
		NodeList nl = xpath(gmfgraph, "//actualFigure/children[contains(@name, 'ParentNameFigure')]");
		
		for (int i = 0; i < nl.getLength(); i++) {
			Element child = (Element) nl.item(i);
			String childName = child.getAttribute("name");
			
			// go up:
			// descriptors > actualFigure > children
			Element descriptor = (Element) ((Element) child.getParentNode()).getParentNode();
			assertEquals(descriptor.getNodeName(), "descriptors");
			assertEquals(childName.replace("ParentNameFigure", "Figure"), descriptor.getAttribute("name"));
			
			String figureName = childName.replace("ParentNameFigure", "");
			
			// this figure name should be in PARENT_NAME_ELEMENTS
			boolean found = false;
			for (String p : PARENT_NAME_ELEMENTS) {
				if (p.equals(figureName)) {
					found = true;
					break;
				}
			}
			assertTrue("The figure name '" + figureName + "' was not found in PARENT_NAME_ELEMENTS", found);			
		}
	}
	
	/**
	 * Test to make sure that every element in {@link #PARENT_NAME_ELEMENTS}
	 * has a ParentName label in the .gmfgraph.
	 * 
	 * @throws Exception
	 */
	public void testAllParentsInGraph() throws Exception {
		Document gmfgraph = getGmfgraph();
		for (String parent : PARENT_NAME_ELEMENTS) {
			// make sure the element is there
			Element descriptor = xpathFirst(gmfgraph, "//descriptors[@name='" + parent + "Figure']");
			
			// it should have a label called XXXParentNameFigure
			Element parentLabel = xpathFirst(descriptor, "actualFigure/children[@name='" + parent + "ParentNameFigure']");
			assertEquals(parentLabel.getNodeName(), "children");
			assertEquals(parentLabel.getAttribute("xsi:type"), "gmfgraph:Label");
			
			// it should have default text [none]
			assertEquals(parentLabel.getAttribute("text"), "[none]");
			
			// it should have foreground color of grey
			Element fg = xpathFirst(parentLabel, "foregroundColor");
			assertEquals(fg.getAttribute("xsi:type"), "gmfgraph:ConstantColor");
			assertEquals(fg.getAttribute("value"), "gray");	
		}
		
	}
	
	/**
	 * Nodes that have ParentName should have a vertical 
	 * FlowLayout.
	 * @throws Exception
	 */
	public void testParentVerticalLayout() throws Exception {
		Document gmfgraph = getGmfgraph();
		for (String parent : PARENT_NAME_ELEMENTS) {
			// make sure the element is there
			Element descriptor = xpathFirst(gmfgraph, "//descriptors[@name='" + parent + "Figure']");
			
			// it should have a layout
			Element layout = xpathFirst(descriptor, "actualFigure/layout");
			assertEquals(layout.getAttribute("xsi:type"), "gmfgraph:FlowLayout");
			assertEquals(layout.getAttribute("vertical"), "true");
		}
		
	}
	
	/**
	 * Root DiagramLabel nodes for ParentNames should have
	 * Element Icon set to false.
	 * 
	 * @throws Exception
	 */
	public void testParentDiagramLabelIcon() throws Exception {
		Document gmfgraph = getGmfgraph();
		for (String parent : PARENT_NAME_ELEMENTS) {
			// make sure the element is there
			Element label = xpathFirst(gmfgraph, "Canvas/labels[@name='" + parent + "ParentName']");
			
			// element icon should be false
			assertEquals(label.getAttribute("elementIcon"), "false");
		}
	}
	
}
