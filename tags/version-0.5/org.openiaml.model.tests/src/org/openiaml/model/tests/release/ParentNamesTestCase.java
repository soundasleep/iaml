/**
 * 
 */
package org.openiaml.model.tests.release;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.components.ComponentsPackage;
import org.openiaml.model.model.visual.VisualPackage;
import org.openiaml.model.tests.XmlTestCase;
import org.openiaml.model.xpath.IterableElementList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
	 * TODO Ideally this would be from some design document. This
	 * is the list of all elements that we expect will contain 
	 * parent name elements.
	 */
	private static final EClass[] PARENT_NAME_ELEMENTS = new EClass[] {
		ModelPackage.eINSTANCE.getDomainObject(),
		ModelPackage.eINSTANCE.getDomainObject(),
		ModelPackage.eINSTANCE.getDomainAttribute(),
		ModelPackage.eINSTANCE.getEventTrigger(),
		ModelPackage.eINSTANCE.getPrimitiveOperation(),
		ModelPackage.eINSTANCE.getPrimitiveCondition(),
		ModelPackage.eINSTANCE.getCompositeOperation(),
		ModelPackage.eINSTANCE.getProperty(),
		VisualPackage.eINSTANCE.getInputForm(),
		VisualPackage.eINSTANCE.getInputTextField(),
		VisualPackage.eINSTANCE.getLabel(),
		ModelPackage.eINSTANCE.getCompositeCondition(),
		VisualPackage.eINSTANCE.getButton(),
		ComponentsPackage.eINSTANCE.getEntryGate(),
		ComponentsPackage.eINSTANCE.getExitGate(),
		ModelPackage.eINSTANCE.getScope(),
		VisualPackage.eINSTANCE.getMap(),
		VisualPackage.eINSTANCE.getMapPoint(),
	};
	
	private static List<String> inst_getParentNameElements = null;
	
	/**
	 * Get a list of all elements that should have a parent name label.
	 */
	public static List<String> getParentNameElements() {
		if (inst_getParentNameElements == null) {
			inst_getParentNameElements = new ArrayList<String>();
			for (EClass cls : PARENT_NAME_ELEMENTS) {
				inst_getParentNameElements.add(cls.getName());
			}
		}
		return inst_getParentNameElements;
	}
	
	/**
	 * TODO Ideally this would be from some design document. This
	 * is the list of all elements that we expect will contain 
	 * parent name elements.
	 */
	private static final EClass[] CONTAINMENT_FEATURE_ELEMENTS = new EClass[] {
		ModelPackage.eINSTANCE.getEventTrigger(),
	};
	
	private static List<String> inst_getContainmentFeatureElements = null;
	
	/**
	 * Get a list of all elements that should have a containment feature label.
	 */
	public static List<String> getContainmentFeatureElements() {
		if (inst_getContainmentFeatureElements == null) {
			inst_getContainmentFeatureElements = new ArrayList<String>();
			for (EClass cls : CONTAINMENT_FEATURE_ELEMENTS) {
				inst_getContainmentFeatureElements.add(cls.getName());
			}
		}
		return inst_getContainmentFeatureElements;
	}
	
	/**
	 * TODO Ideally this would be from some design document. This
	 * is the list of all elements that we expect will contain 
	 * parent name elements.
	 */
	private static final EClass[] TYPED_ELEMENTS = new EClass[] {
		ModelPackage.eINSTANCE.getTemporaryVariable(),
		ModelPackage.eINSTANCE.getProperty(),
		ModelPackage.eINSTANCE.getDomainAttribute(),
		ModelPackage.eINSTANCE.getDomainAttributeInstance(),
		ModelPackage.eINSTANCE.getStaticValue(),
		VisualPackage.eINSTANCE.getInputTextField(),
	};
	
	private static List<String> inst_getTypedElements = null;
	
	/**
	 * Get a list of all elements that should have a type label.
	 * @see #getTypedElementClasses()
	 */
	public static List<String> getTypedElements() {
		if (inst_getTypedElements == null) {
			inst_getTypedElements = new ArrayList<String>();
			for (EClass cls : TYPED_ELEMENTS) {
				inst_getTypedElements.add(cls.getName());
			}
		}
		return inst_getTypedElements;
	}
	
	/**
	 * Get a list of all elements that should have a type label.
	 * @see #getTypedElements()
	 */
	public static EClass[] getTypedElementClasses() {
		return TYPED_ELEMENTS;
	}
	
	/**
	 * Load the .gmfgraph.
	 * 
	 * @return
	 * @throws Exception
	 */
	public Document getGmfgraph() throws Exception {
		return loadDocument(GMF_ROOT + "iaml.gmfgraph");
	}

	private void doTestAll(String figureName, List<String> classes, String name) throws Exception {
		doTestAll(figureName, classes, name, new ArrayList<String>());
	}

	/**
	 * Test to make sure that the list of figures supplied by <code>name</code> is
	 * complete, based on the .gmfgraph.
	 * 
	 * @throws Exception
	 */
	private void doTestAll(String figureName, List<String> classes, String name,
			List<String> ignore) throws Exception {
		Document gmfgraph = getGmfgraph();
		IterableElementList nl = xpath(gmfgraph, "//actualFigure/children[contains(@name, '" + figureName + "')]");
		
		for (Element child : nl) {
			String childName = child.getAttribute("name");
			
			// go up:
			// descriptors > actualFigure > children
			Element descriptor = (Element) ((Element) child.getParentNode()).getParentNode();
			assertEquals(descriptor.getNodeName(), "descriptors");
			assertEquals(childName.replace(figureName, "Figure"), descriptor.getAttribute("name"));
			
			String fn = childName.replace(figureName, "");
			if (ignore.contains(fn))
				continue;
			
			// this figure name should be in PARENT_NAME_ELEMENTS
			boolean found = false;
			for (String p : classes) {
				if (p.equals(fn)) {
					found = true;
					break;
				}
			}
			assertTrue("The figure name '" + fn + "' was not found in " + name, found);			
		}
	}

	/**
	 * Test to make sure that {@link #getParentNameElements()} is
	 * complete, based on the .gmfgraph.
	 * 
	 * @throws Exception
	 */
	public void testAllParents() throws Exception {
		doTestAll("ParentNameFigure", getParentNameElements(), "getParentNameElements()");
	}
	
	/**
	 * Test to make sure that {@link #getContainmentFeatureElements()} is
	 * complete, based on the .gmfgraph.
	 * 
	 * @throws Exception
	 */
	public void testAllContainments() throws Exception {
		doTestAll("ContainmentFeatureFigure", getContainmentFeatureElements(), "getContainmentFeatureElements()");
	}

	/**
	 * Test to make sure that {@link #getTypedElements()} is
	 * complete, based on the .gmfgraph.
	 * 
	 * @throws Exception
	 */
	public void testAllTypes() throws Exception {
		List<String> ignore = new ArrayList<String>();
		ignore.add("LoginHandler");
		ignore.add("Arithmetic");
		
		doTestAll("TypeFigure", getTypedElements(), "getTypedElements()", 
				ignore);
	}

	/**
	 * Test to make sure that every element in {@link #PARENT_NAME_ELEMENTS}
	 * has a ParentName label in the .gmfgraph. Also make sure that they all have
	 * a foreground color of gray, an an initial text of [none].
	 * 
	 * @throws Exception
	 */
	public void testAllParentsInGraph() throws Exception {
		Document gmfgraph = getGmfgraph();
		for (String parent : getParentNameElements()) {
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
		for (String parent : getParentNameElements()) {
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
		for (String parent : getParentNameElements()) {
			// make sure the element is there
			Element label = xpathFirst(gmfgraph, "Canvas/labels[@name='" + parent + "ParentName']");
			
			// element icon should be false
			assertEquals(label.getAttribute("elementIcon"), "false");
		}
	}
	
	/**
	 * All ParentName mappings in .gmfmaps are read-only.
	 * 
	 * @throws Exception
	 */
	public void testMappingReadOnly() throws Exception {
		for (String filename : GmfMapTestCase.getMapList()) {
			Document doc = GmfMapTestCase.getMapCache().get(filename);

			IterableElementList nodes = xpath(doc, "/Mapping/nodes/ownedChild/labelMappings/diagramLabel[contains(@href, 'ParentName')]");
			for (Element node : nodes) {
				Element parent = (Element) node.getParentNode();
				assertEquals(filename + ": ParentName '" + node + "' must be read-only.", parent.getAttribute("readOnly"), "true");
			}
		}

	}

}
