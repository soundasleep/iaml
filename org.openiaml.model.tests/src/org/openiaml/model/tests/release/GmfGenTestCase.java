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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.tests.XmlTestCase;
import org.openiaml.model.tests.model.ModelTestCase;
import org.openiaml.model.tests.xpath.IterableNodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Tests all .gmfgens
 * 
 * @author jmwright
 *
 */
public class GmfGenTestCase extends XmlTestCase {

	private static Map<String,Document> loadedGens;
	
	/**
	 * Load up all the .gmfmap's
	 * 
	 */
	public static Map<String,Document> getGmfGens() throws Exception {
		if (loadedGens == null) {
			loadedGens = new HashMap<String,Document>();
			
			// load all .gmftool's
			for (String map : getGenList()) {
				loadedGens.put( map, loadDocument(map) );
			}
		}
		return loadedGens;
	}
		
	/**
	 * Create a list of gmfmaps from {@link PluginsTestCase}.
	 * 
	 * @return
	 */
	private static Set<String> getGenList() {
		Set<String> tools = new HashSet<String>();
		for (String gmfgen : PluginsTestCase.GMFGENS) {
			tools.add(gmfgen);
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
		assertEquals(getGenList().size(), PluginsTestCase.GMFGENS.length);
		assertEquals(getGmfGens().size(), PluginsTestCase.GMFGENS.length);
	}

	/**
	 * Every node should have no more than one OpenDiagram policy.
	 * 
	 * @throws Exception
	 */
	public void testOnlyOneOpenDiagramPolicy() throws Exception {
		for (String filename : getGmfGens().keySet()) {
			Document doc = getGmfGens().get(filename);
			
			IterableNodeList nodes = xpath(doc, "/GenEditorGenerator/diagram/topLevelNodes");
			assertFalse(filename + ": No nodes found", nodes.isEmpty());
			
			for (Element node : nodes) {
				// directly contained OpenDiagramPolicies
				IterableNodeList opens = xpath(node, "behaviour");
				boolean foundOpen = false;
				for (Element open : opens) {
					if (open.getAttribute("xsi:type").equals("gmfgen:OpenDiagramBehaviour")) {
						assertFalse(filename + ": More than one OpenDiagram policy found for node '" + node + "'", foundOpen);
						foundOpen = true;
					}
				}
			}

			IterableNodeList links = xpath(doc, "/GenEditorGenerator/diagram/links");
			assertFalse(filename + ": No links found", links.isEmpty());
			for (Element link : links) {
				// directly contained OpenDiagramPolicies
				IterableNodeList opens = xpath(link, "behaviour");
				boolean foundOpen = false;
				for (Element open : opens) {
					if (open.getAttribute("xsi:type").equals("gmfgen:OpenDiagramBehaviour")) {
						assertFalse(filename + ": More than one OpenDiagram policy found for link '" + link + "'", foundOpen);
						foundOpen = true;
					}
				}
			}
		}
	}

	/**
	 * All read-only labels inside nodes with OpenDiagramPolicies should
	 * also have an OpenDiagramPolicy. 
	 * 
	 * @throws Exception
	 */
	public void testReadOnlyOpenDiagramPolicies() throws Exception {
		for (String filename : getGmfGens().keySet()) {
			Document doc = getGmfGens().get(filename);
			
			// will do both links and nodes
			IterableNodeList labels = xpath(doc, "//labels[@readOnly='true']");
			assertFalse(filename + ": No read-only labels found", labels.isEmpty());
			
			for (Element label : labels) {
				String labelName = label.getAttribute("editPartClassName"); 
				
				// does the parent have an OpenDiagramPolicy?
				Element parent = (Element) label.getParentNode();
				Element pOpen = hasXpathFirst(parent, "behaviour");
				if (pOpen != null && pOpen.getAttribute("xsi:type").equals("gmfgen:OpenDiagramBehaviour")) {
					// yes! this label should also have an open diagram policy 
					Element open = hasXpathFirst(label, "behaviour");
					assertNotNull(filename + ": Read-only label '" + labelName + "' must have an OpenDiagram behaviour, like its parent", open);
					
					// make sure it's a behaviour
					assertEquals(filename + ": Read-only label '" + labelName + "' has an incorrect OpenDiagram behaviour",
							open.getAttribute("xsi:type"), "gmfgen:OpenDiagramBehaviour");
				}
			}

		}
	}
	
	/**
	 * Non read-only labels inside nodes cannot contain OpenDiagramPolicies.
	 * (Otherwise they could not be edited by double clicking.) 
	 * 
	 * @throws Exception
	 */
	public void testNonReadOnlyCannotHaveOpenPolicies() throws Exception {
		for (String filename : getGmfGens().keySet()) {
			Document doc = getGmfGens().get(filename);
			
			// will do both links and nodes
			IterableNodeList labels = xpath(doc, "//labels[not(@readOnly='true')]");
			assertFalse(filename + ": No normal labels found", labels.isEmpty());
			
			for (Element label : labels) {
				String labelName = label.getAttribute("editPartClassName");
				
				// it cannot have a OpenDiagram behaviour
				IterableNodeList bh = xpath(label, "behaviour");
				for (Element b : bh) {
					assertNotEqual(filename + ": Normal editable label '" + labelName + "' cannot have an OpenDiagram behaviour",
						b.getAttribute("xsi:type"), "gmfgen:OpenDiagramBehaviour");
				}
			}

		}
	}
	
	/**
	 * All labels with OpenDigramPolicies must match those of
	 * their parents.
	 * 
	 * @throws Exception
	 */
	public void testLabelOpenDiagramsMatch() throws Exception {
		for (String filename : getGmfGens().keySet()) {
			Document doc = getGmfGens().get(filename);
			
			// will do both links and nodes
			IterableNodeList labels = xpath(doc, "//labels");
			assertFalse(filename + ": No labels found", labels.isEmpty());
			
			for (Element label : labels) {
				String labelName = label.getAttribute("editPartClassName");
				
				// iterate over behaviours
				IterableNodeList beh = xpath(label, "behaviour");
				for (Element b : beh) {
					if (b.getAttribute("xsi:type").equals("gmfgen:OpenDiagramBehaviour")) {
						// the parent must have the same node
						
						String query = "../behaviour[@editPolicyClassName='" + b.getAttribute("editPolicyClassName") + "' and @diagramKind='" + b.getAttribute("diagramKind") + "' and @editorID='" + b.getAttribute("editorID") + "']";
						Element parentOpen = hasXpathFirst(label, query);
						assertNotNull(filename + ": Could not find matching parent OpenDiagram for label '" + labelName + "' matching '" + query + "'", parentOpen);
					}
				}
			}
		}
	}
	
	/**
	 * For an OpenDiagramPolicy, the destination editor must be a
	 * class that can contain the element referred to in the OpenDiagramPolicy. 
	 * 
	 * @throws Exception
	 */
	public void testOpenDiagramTargetElement() throws Exception {
		for (String filename : getGmfGens().keySet()) {
			Document doc = getGmfGens().get(filename);
			
			IterableNodeList nodes = xpath(doc, "/GenEditorGenerator/diagram/topLevelNodes");
			nodes.addAll(xpath(doc, "/GenEditorGenerator/diagram/links"));
			assertFalse(filename + ": No nodes found", nodes.isEmpty());
			
			for (Element node : nodes) {
				// find the meta element for this node
				Element meta = xpathFirst(node, "modelFacet/metaClass");
				// iaml.genmodel#//model/CompositeOperation
				String metaHref = meta.getAttribute("href");
				
				IterableNodeList beh = xpath(node, "behaviour");
				for (Element b : beh) {
					if (b.getAttribute("xsi:type").equals("gmfgen:OpenDiagramBehaviour")) {
						// an OpenDiagram policy
						assertOpenDiagramElementMatches(filename, b.getAttribute("diagramKind"), metaHref);
					}
				}
			}
		}
	}

	/**
	 * Assert that the given model ID can store the element
	 * referred to in metaHref.
	 * 
	 * @param filename
	 * @param modelID "Iaml_Operation"
	 * @param metaHref "iaml.genmodel#//model/CompositeOperation"
	 */
	private void assertOpenDiagramElementMatches(String filename,
			String modelID, String metaHref) throws Exception {

		// first, lets get the EClass of the meta element
		String metaElementName = metaHref.substring(metaHref.lastIndexOf("/") + 1);
		// resolve
		EClass meta = resolveSimpleEClass(metaElementName);
		
		// and now lets find the EClass of the editor
		EClass editor = getEditorEClass(modelID);
		
		assertNotNull(meta);
		assertNotNull(editor);
		
		// sanity check
		assertTrue(ModelPackage.eINSTANCE.getApplicationElement().isSuperTypeOf( ModelPackage.eINSTANCE.getDomainAttributeInstance() ));
		
		// the meta element should be a subclass of the editor
		assertTrue(filename + ": Element '" + metaHref + "' is not a subclass of diagram type '" + modelID + "' [resolved meta = '" + meta + "', editor = '" + editor + "']", 
				editor.isSuperTypeOf(meta));
	}

	private Map<String, EClass> foundClasses = new HashMap<String, EClass>();
	
	/**
	 * Get the root EClass of the given modelID. Iterates over
	 * existing .gmfgens.
	 * 
	 * @param modelID
	 * @return
	 */
	private EClass getEditorEClass(String modelID) throws Exception {
		if (foundClasses.containsKey(modelID))
			return foundClasses.get(modelID);
		
		for (Document doc : getGmfGens().values()) {
			Element root = xpathFirst(doc, "/GenEditorGenerator");
			if (root.getAttribute("modelID").equals(modelID)) { 
				Element domainDiagramElement = xpathFirst(doc, "/GenEditorGenerator/diagram/domainDiagramElement");
				
				String href = domainDiagramElement.getAttribute("href");
				// trim to last /
				href = href.substring(href.lastIndexOf("/") + 1);
				
				EClass result = resolveSimpleEClass(href);
				foundClasses.put(modelID, result);
				return result;
			}			
		}
		
		fail("Could not find Editor EClass for model ID '" + modelID + "'");
		return null;
	}
	
	private Map<String, EClass> resolvedClasses = new HashMap<String, EClass>();

	/**
	 * Resolve a simple meta element. This assumes that the same
	 * element name does not occur more than once in the given
	 * metamodel.
	 * 
	 * Uses the factory map found in {@link org.openiaml.model.tests.model.ModelTestCase}.
	 * 
	 * @param metaElementName "SingleOperation"
	 * @return
	 */
	private EClass resolveSimpleEClass(String metaElementName) {
		if (resolvedClasses.containsKey(metaElementName)) {
			return resolvedClasses.get(metaElementName);
		}
		
		for (EPackage pkg : ModelTestCase.getFactoryMap().keySet()) {
			EClassifier cf = pkg.getEClassifier(metaElementName);
			if (cf != null && cf instanceof EClass) {
				// found it!
				EClass result = (EClass) cf;
				resolvedClasses.put(metaElementName, result);
				return result;
			}
		}
		fail("Could not resolve simple EClass name '" + metaElementName + "'");
		return null;
	}

	/**
	 * For every editor instance, every subclass of the 
	 * root diagram element should be openable in every editor.
	 * 
	 * @throws Exception
	 */
	public void testAllOpenableElementsAreOpenable() throws Exception {
		for (Document doc : getGmfGens().values()) {
			Element root = xpathFirst(doc, "/GenEditorGenerator");
			String modelID = root.getAttribute("modelID");
			Element domainDiagramElement = xpathFirst(doc, "/GenEditorGenerator/diagram/domainDiagramElement");
				
			String href = domainDiagramElement.getAttribute("href");
			// trim to last /
			href = href.substring(href.lastIndexOf("/") + 1);
				
			EClass rootElement = resolveSimpleEClass(href);
			
			assertAllOpenableElements(modelID, rootElement);
		}
	}

	/**
	 * Check through all .gmfgens and make sure all nodes and
	 * links of subclass [rootElement] have an open behaviour
	 * to [modelID].
	 * 
	 * @param modelID
	 * @param rootElement
	 * @throws Exception 
	 */
	private void assertAllOpenableElements(String modelID, EClass rootElement) throws XPathExpressionException, Exception {
		for (String filename : getGmfGens().keySet()) {
			Document doc = getGmfGens().get(filename);
			
			// will do both links and nodes
			IterableNodeList nodes = xpath(doc, "/GenEditorGenerator/diagram/topLevelNodes");
			nodes.addAll(xpath(doc, "/GenEditorGenerator/diagram/links"));
			assertFalse(filename + ": No nodes found", nodes.isEmpty());
			
			for (Element node : nodes) {
				// find the meta element for this node
				Element meta = xpathFirst(node, "modelFacet/metaClass");
				// iaml.genmodel#//model/CompositeOperation
				String metaHref = meta.getAttribute("href");
				// CompositeOperation
				metaHref = metaHref.substring(metaHref.lastIndexOf("/") + 1);
				
				EClass metaElement = resolveSimpleEClass(metaHref);
				
				if (rootElement.isSuperTypeOf(metaElement)) {
					// there must be a behaviour for this meta element!
					boolean found = false;
					
					IterableNodeList beh = xpath(node, "behaviour");
					for (Element b : beh) {
						if (b.getAttribute("xsi:type").equals("gmfgen:OpenDiagramBehaviour")) {
							// found it directly
							if (b.getAttribute("diagramKind").equals(modelID)) {
								found = true;
							}
							
							// sometimes we can have multiple editors for subclasses, e.g.
							// a generic ApplicationElement editor, and a more specific
							// DomainObject editor. in this case, we need to still
							// make sure that we mark this as found when searching for
							// ApplicationElement editors
							if (!found) {
								// e.g. DomainObject
								EClass actual = getEditorEClass(b.getAttribute("diagramKind"));
								if (rootElement.isSuperTypeOf(actual)) {
									// a valid subclass
									found = true;
								}
							}
						}
					}
					
					assertTrue(filename + ": The diagram element '" + metaHref + "' which is a subclass of '" + rootElement + "', did not have an OpenDiagramPolicy of '" + modelID + "'",
							found);
				}
				
			}
		}
	}
	
	/**
	 * In each .gmfgen, each different diagram type should be unique.
	 * 
	 * @throws Exception
	 */
	public void testUniqueOpenDiagrams() throws Exception {
		for (String filename : getGmfGens().keySet()) {
			Document doc = getGmfGens().get(filename);
			
			List<String> diagramKinds = new ArrayList<String>();
			List<String> editorIDs = new ArrayList<String>();
			List<String> policyNames = new ArrayList<String>();
			
			// will do both links and nodes
			IterableNodeList nodes = xpath(doc, "/GenEditorGenerator/diagram/topLevelNodes");
			nodes.addAll(xpath(doc, "/GenEditorGenerator/diagram/links"));
			assertFalse(filename + ": No nodes found", nodes.isEmpty());
			
			for (Element node : nodes) {
				// does this have a behaviour?
				IterableNodeList beh = xpath(node, "behaviour");
				for (Element b : beh) {
					if (b.getAttribute("xsi:type").equals("gmfgen:OpenDiagramBehaviour")) {
						String diagramKind = b.getAttribute("diagramKind");
						String editorID = b.getAttribute("editorID");
						String policyName = b.getAttribute("editPolicyClassName");
						
						if (diagramKinds.contains(diagramKind)) {
							// these should all match
							int pos = diagramKinds.indexOf(diagramKind);
							
							assertEquals(filename + ": non-unique OpenDiagram behaviour: diagramKind '" 
									+ diagramKind + "'", diagramKinds.get(pos), diagramKind);
							assertEquals(filename + ": non-unique OpenDiagram behaviour: editorID '" 
									+ editorID + "'", editorIDs.get(pos), editorID);
							assertEquals(filename + ": non-unique OpenDiagram behaviour: policyName '" 
									+ policyName + "'", policyNames.get(pos), policyName);
						}
					}
				}
			}
		}
	}
	
}
