/**
 * 
 */
package org.openiaml.model.tests.release;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.components.ComponentsPackage;
import org.openiaml.model.model.domain.DomainPackage;
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
	 * A list of all abstract types of elements that should display parent names.
	 * This is essentially the design document for parent name types.
	 * 
	 * <p>The test method {@link #testParentNameElements()} checks that
	 * all of the concrete classes in the model that are subtypes of
	 * one of these types are specified in {@link #PARENT_NAME_ELEMENTS}.
	 */
	private static final EClass[] PARENT_NAME_TYPES = new EClass[] {
		ModelPackage.eINSTANCE.getDomainAttribute(),
		ModelPackage.eINSTANCE.getDomainAttributeInstance(),
		ModelPackage.eINSTANCE.getEventTrigger(),
		ModelPackage.eINSTANCE.getOperation(),
		ModelPackage.eINSTANCE.getCondition(),
		ModelPackage.eINSTANCE.getProperty(),
		VisualPackage.eINSTANCE.getFrame(),
		ComponentsPackage.eINSTANCE.getGate(),
		ModelPackage.eINSTANCE.getVisibleThing(),
	};
	
	/**
	 * A list of all elements that should display parent names. This
	 * list is derived from {@link #PARENT_NAME_TYPES}.
	 */
	private static final EClass[] PARENT_NAME_ELEMENTS = new EClass[] {
		// DomainAttribute
		ModelPackage.eINSTANCE.getDomainAttribute(),
		// DomainAttributeInstance
		ModelPackage.eINSTANCE.getDomainAttributeInstance(),
		// EventTrigger
		ModelPackage.eINSTANCE.getEventTrigger(),
		// Operation
		ModelPackage.eINSTANCE.getPrimitiveOperation(),
		ModelPackage.eINSTANCE.getCompositeOperation(),
		// Condition
		ModelPackage.eINSTANCE.getPrimitiveCondition(),
		ModelPackage.eINSTANCE.getCompositeCondition(),
		// Property
		ModelPackage.eINSTANCE.getProperty(),
		// Frame
		VisualPackage.eINSTANCE.getFrame(),
		// Gate
		ComponentsPackage.eINSTANCE.getEntryGate(),
		ComponentsPackage.eINSTANCE.getExitGate(),
		// VisibleThing
		VisualPackage.eINSTANCE.getInputForm(),
		VisualPackage.eINSTANCE.getInputTextField(),
		VisualPackage.eINSTANCE.getLabel(),
		VisualPackage.eINSTANCE.getHidden(),
		VisualPackage.eINSTANCE.getButton(),
		VisualPackage.eINSTANCE.getMap(),
		VisualPackage.eINSTANCE.getMapPoint(),
		VisualPackage.eINSTANCE.getIteratorList(),
	};
	
	private static List<String> inst_getParentNameElements = null;
	
	/**
	 * Test that all subtypes of {@link #PARENT_NAME_TYPES} are
	 * in {@link #PARENT_NAME_ELEMENTS}.
	 */
	public void testParentNameElements() {
		testParentNameElementsPackage(ModelPackage.eINSTANCE);
	}
	
	// test all of the components in the given package
	private void testParentNameElementsPackage(EPackage pkg) {
		// test subpackages
		for (EPackage sub : pkg.getESubpackages()) {
			testParentNameElementsPackage(sub);
		}
		
		for (EClassifier cls : pkg.getEClassifiers()) {
			if (cls instanceof EClass) {
				EClass c = (EClass) cls;
				// ignore abstract classes
				if (!c.isAbstract() && !c.isInterface()) {
					// check each PARENT_NAME_TYPES
					for (EClass typ : PARENT_NAME_TYPES) {
						if (typ.isSuperTypeOf(c)) {
							// it should be in PARENT_NAME_ELEMENTS
							assertParentNameElementsContains(c);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Assert that {@link #PARENT_NAME_ELEMENTS} contains the given class.
	 * 
	 * @param c
	 */
	private void assertParentNameElementsContains(EClass c) {
		for (EClass cls : PARENT_NAME_ELEMENTS) {
			if (cls.equals(c)) {
				// ok
				return;
			}
		}
		fail("Could not find class '" + c.getName() + "' in PARENT_NAME_ELEMENTS");
	}
	
	/**
	 * Test that all classes in {@link #PARENT_NAME_ELEMENTS} are
	 * an instance of at least one specified {@link #PARENT_NAME_TYPES}.
	 */
	public void testParentNameElementsAreAllSpecified() {
		for (EClass target : PARENT_NAME_ELEMENTS) {
			boolean found = false;
			for (EClass source : PARENT_NAME_TYPES) {
				if (source.isSuperTypeOf(target)) {
					// ok
					found = true;
				}
			}
			
			assertTrue("Specified parent name class '" + target.getName() + "' was not an instance of any type in PARENT_NAME_TYPES", found);
		}
	}

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
		ModelPackage.eINSTANCE.getProperty(),
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
		VisualPackage.eINSTANCE.getLabel(),
		VisualPackage.eINSTANCE.getHidden(),
		
		// not data typed, but typed in a different way
		DomainPackage.eINSTANCE.getDomainSource(),
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
