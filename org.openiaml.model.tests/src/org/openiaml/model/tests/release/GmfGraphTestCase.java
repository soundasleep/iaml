/**
 * 
 */
package org.openiaml.model.tests.release;

import org.openiaml.model.tests.XmlTestCase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
		NodeList nl = xpath(gmfgraph, "//descriptors/actualFigure/children");
		
		assertNotSame("We should have at least one label node", nl.getLength(), 0);
		for (int i = 0; i < nl.getLength(); i++) {
			Element child = (Element) nl.item(i);
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

	
}
