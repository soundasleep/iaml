/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import junit.framework.AssertionFailedError;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.openiaml.emf.SoftCache;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.users.UsersPackage;
import org.openiaml.model.tests.XmlTestCase;
import org.openiaml.model.tests.model.ModelTestCase;
import org.openiaml.model.xpath.IterableElementList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Tests all .gmfgens
 * 
 * @author jmwright
 *
 */
public class GmfGenTestCase extends XmlTestCase {
	
	/**
	 * A soft in-memory cache of gmfgen filenames to
	 * loaded XML documents.
	 */
	private static SoftCache<String,Document> gmfgenCache = new SoftCache<String,Document>() {

		@Override
		public Document retrieve(String input) {
			try {
				return loadDocument(input);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}

	};
	
	public static SoftCache<String,Document> getCache() {
		return gmfgenCache;
	}
		
	/**
	 * Create a list of gmfmaps from {@link PluginsTestCase}.
	 * 
	 * @return
	 */
	private static Set<String> getGenList() {
		return PluginsTestCase.getAllGmfGens();
	}
	
	public void testGenListIsntEmpty() {
		assertNotSame(0, getGenList().size());
	}

	/**
	 * Every node should have no more than one OpenDiagram policy.
	 * 
	 * @throws Exception
	 */
	public void testOnlyOneOpenDiagramPolicy() throws Exception {
		for (String filename : getGenList()) {
			Document doc = getCache().get(filename);
			
			IterableElementList nodes = xpath(doc, "/GenEditorGenerator/diagram/topLevelNodes");
			assertFalse(filename + ": No nodes found", nodes.isEmpty());
			
			for (Element node : nodes) {
				// directly contained OpenDiagramPolicies
				IterableElementList opens = xpath(node, "behaviour");
				boolean foundOpen = false;
				for (Element open : opens) {
					if (open.getAttribute("xsi:type").equals("gmfgen:OpenDiagramBehaviour")) {
						assertFalse(filename + ": More than one OpenDiagram policy found for node '" + node + "'", foundOpen);
						foundOpen = true;
					}
				}
			}

			IterableElementList links = xpath(doc, "/GenEditorGenerator/diagram/links");
			assertFalse(filename + ": No links found", links.isEmpty());
			for (Element link : links) {
				// directly contained OpenDiagramPolicies
				IterableElementList opens = xpath(link, "behaviour");
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
		for (String filename : getGenList()) {
			Document doc = getCache().get(filename);
			
			// will do both links and nodes
			IterableElementList labels = xpath(doc, "//labels[@readOnly='true']");
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
		for (String filename : getGenList()) {
			Document doc = getCache().get(filename);
			
			// will do both links and nodes
			IterableElementList labels = xpath(doc, "//labels[not(@readOnly='true')]");
			assertFalse(filename + ": No normal labels found", labels.isEmpty());
			
			for (Element label : labels) {
				String labelName = label.getAttribute("editPartClassName");
				
				// it cannot have a OpenDiagram behaviour
				IterableElementList bh = xpath(label, "behaviour");
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
		for (String filename : getGenList()) {
			Document doc = getCache().get(filename);
			
			// will do both links and nodes
			IterableElementList labels = xpath(doc, "//labels");
			assertFalse(filename + ": No labels found", labels.isEmpty());
			
			for (Element label : labels) {
				String labelName = label.getAttribute("editPartClassName");
				
				// iterate over behaviours
				IterableElementList beh = xpath(label, "behaviour");
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
		for (String filename : getGenList()) {
			Document doc = getCache().get(filename);
			
			IterableElementList nodes = xpath(doc, "/GenEditorGenerator/diagram/topLevelNodes");
			nodes.addAll(xpath(doc, "/GenEditorGenerator/diagram/links"));
			assertFalse(filename + ": No nodes found", nodes.isEmpty());
			
			for (Element node : nodes) {
				// find the meta element for this node
				Element meta = xpathFirst(node, "modelFacet/metaClass");
				// iaml.genmodel#//model/CompositeOperation
				String metaHref = meta.getAttribute("href");
				
				IterableElementList beh = xpath(node, "behaviour");
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
		
		for (String filename : getGenList()) {
			Document doc = getCache().get(filename);
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
	
	public interface XmlDocumentIterator {
		
		/**
		 * Execute something over the given XML document, which
		 * has the given filename.
		 * 
		 * @param filename
		 * @param doc
		 */
		public void execute(String filename, Document doc) throws Exception;
		
	}
	
	public abstract class DefaultIterator implements XmlDocumentIterator {

		/**
		 * Log a message; this will include a helpful link to
		 * the current file. 
		 * 
		 * @param message
		 */
		public void log(String message) {
			System.out.println(simplifyFilename(filename) + ": " + message);
		}
		
		private String simplifyFilename(String s) {
			return s.substring(s.lastIndexOf(File.separator) + 1);
		}

		private String filename;
		
		@Override
		public void execute(String filename, Document doc) throws Exception {
			this.filename = filename;
			execute2(filename, doc);
		}
		
		public void saveDocument(Document doc, String filename) throws IOException, TransformerException {
			log("Saving to disk");
			GmfGenTestCase.saveDocument(doc, filename);
		}
		
		/**
		 * Does the actual work.
		 */
		public abstract void execute2(String filename, Document doc) throws Exception;
		
	}
	
	/**
	 * Iterate over all the .gmfgen's with the given iterator.
	 * If an error occurs while iterating, catches the error and
	 * appends the filename.
	 * 
	 * @param it
	 * @throws Exception
	 */
	protected void iterate(XmlDocumentIterator it) throws Exception {
		for (String filename : getGenList()) {
			try {
				Document doc = getCache().get(filename);
				
				it.execute(filename, doc);
			} catch (Throwable e) {
				throw new RuntimeException(filename + ": " + e.getMessage(), e);
			}
		}
	}
	
	public abstract class AttributeIterator extends DefaultIterator {

		/**
		 * The attribute to check on /GenEditorGenerator, e.g. "modelID"
		 * @return
		 */
		public abstract String getAttributeName();
		
		/**
		 * Get the expected value for the given filename.
		 * 
		 * @param filename
		 * @param current the current value
		 */
		public abstract String getExpected(String filename, String current);
		
		public String getXpathSelector() {
			return "/GenEditorGenerator";
		}
		
		@Override
		public void execute2(String filename, Document doc) throws Exception {
			IterableElementList roots = xpath(doc, getXpathSelector());
			for (Element root : roots) {
				String current = root.getAttribute(getAttributeName());
				
				String expected = getExpected(filename, current);
				if (isEmpty(current)) {
					log("Setting " + getAttributeName() + " to " + expected);
					root.setAttribute(getAttributeName(), expected);
					saveDocument(doc, filename);
				} else {
					if (!expected.equals(current)) {
						if (forceSet()) {
							log("Force setting " + getAttributeName() + " to " + expected);
							root.setAttribute(getAttributeName(), expected);
							saveDocument(doc, filename);
						} else {
							assertEquals(expected, current);
						}
					}
				}
			}
		}
		
		/**
		 * Should we forceably set the attribute if it is incorrect?
		 * @return
		 */
		public boolean forceSet() {
			return false;
		}
		
	}
	
	/**
	 * Every .gmfgen should have a ModelID.
	 * Sets the ModelID to the filename if not.
	 */
	public void testModelID() throws Exception {
		iterate(new AttributeIterator() {

			@Override
			public String getAttributeName() {
				return "modelID";
			}

			@Override
			public String getExpected(String filename, String current) {
				filename = getName(filename);
				if (filename.equals("root")) {
					return "Iaml";
				}
				return "Iaml_" + camelCase(filename);
			}
			
		});
		
	}

	public void testPackageNamePrefix() throws Exception {
		iterate(new AttributeIterator() {
			
			@Override
			public String getAttributeName() {
				return "packageNamePrefix";
			}

			@Override
			public String getExpected(String filename, String current) {
				return getPluginName(filename);
			}
			
		});
		
	}
	
	public void testDiagramFileExtension() throws Exception {
		iterate(new AttributeIterator() {

			@Override
			public String getAttributeName() {
				return "diagramFileExtension";
			}

			@Override
			public String getExpected(String filename, String current) {
				if (getName(filename).equals("root")) {
					return "iaml_diagram";
				}
				return "iaml_" + getName(filename) + "_diagram";
			}
			
		});
		
	}
	
	public void testDomainFileExtension() throws Exception {
		iterate(new AttributeIterator() {

			@Override
			public String getAttributeName() {
				return "domainFileExtension";
			}

			@Override
			public String getExpected(String filename, String current) {
				if (getName(filename).equals("root")) {
					return "iaml";
				}
				return "iaml_" + getName(filename);
			}
			
		});
		
	}
	
	public void testDynamicTemplates() throws Exception {
		iterate(new AttributeIterator() {

			@Override
			public String getAttributeName() {
				return "dynamicTemplates";
			}

			@Override
			public String getExpected(String filename, String current) {
				return "true";
			}
			
		});
		
	}
	
	public void testTemplateDirectory() throws Exception {
		iterate(new AttributeIterator() {

			@Override
			public String getAttributeName() {
				return "templateDirectory";
			}

			@Override
			public String getExpected(String filename, String current) {
				return "/org.openiaml.model/templates/";
			}
			
		});
		
	}
	
	public void testValidationEnabled() throws Exception {
		iterate(new AttributeIterator() {

			@Override
			public String getAttributeName() {
				return "validationEnabled";
			}

			@Override
			public String getExpected(String filename, String current) {
				return "true";
			}

			@Override
			public String getXpathSelector() {
				return "/GenEditorGenerator/diagram";
			}
			
		});
		
	}
	
	public void testValidationDecorators() throws Exception {
		iterate(new AttributeIterator() {

			@Override
			public String getAttributeName() {
				return "validationDecorators";
			}

			@Override
			public String getExpected(String filename, String current) {
				return "true";
			}

			@Override
			public String getXpathSelector() {
				return "/GenEditorGenerator/diagram";
			}
			
		});
		
	}
	
	public List<String> ALL_PLUGIN_NAMES = getAllPluginNames();
	
	public List<String> getAllPluginNames() {
		try {
			List<String> result = new ArrayList<String>();
			for (String filename : getGenList()) {
				result.add(getName(filename));
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Converts "root", "foo" into "iaml", "iaml_foo".
	 * 
	 * @return
	 */
	public List<String> getAllShortcuts() {
		List<String> result = new ArrayList<String>();
		for (String name : ALL_PLUGIN_NAMES) {
			if (name.equals("root")) {
				result.add("iaml");
			} else {
				result.add("iaml_" + name);
			}
		}
		return result;
	}
	
	public void testContainsShortcutsTo() throws Exception {
		iterate(new DefaultIterator() {

			@Override
			public void execute2(String filename, Document doc)
					throws Exception {
				
				Element diagram = xpathFirst(doc, "/GenEditorGenerator/diagram");
				boolean changed = false;
				for (String shortcut : getAllShortcuts()) {
					if (hasXpathFirst(diagram, "containsShortcutsTo[text()='" + shortcut + "']") == null) {
						// add it
						log("Adding containsShortcutsTo: " + shortcut);
						
						Element e = doc.createElement("containsShortcutsTo");
						e.setTextContent(shortcut);
						diagram.appendChild(e);
						changed = true;
					}
				}
				
				if (changed) {
					saveDocument(doc, filename);
				}
				
			}
			
		});
	}
	
	public void testShortcutsProvidedFor() throws Exception {
		iterate(new DefaultIterator() {

			@Override
			public void execute2(String filename, Document doc)
					throws Exception {
				
				Element diagram = xpathFirst(doc, "/GenEditorGenerator/diagram");
				boolean changed = false;
				for (String shortcut : getAllShortcuts()) {
					if (hasXpathFirst(diagram, "shortcutsProvidedFor[text()='" + shortcut + "']") == null) {
						// add it
						log("Adding shortcutsProvidedFor: " + shortcut);
						
						Element e = doc.createElement("shortcutsProvidedFor");
						e.setTextContent(shortcut);
						diagram.appendChild(e);
						changed = true;
					}
				}
				
				if (changed) {
					saveDocument(doc, filename);
				}
				
			}
			
		});
	}
	
	public void testPluginId() throws Exception {
		iterate(new AttributeIterator() {

			@Override
			public String getAttributeName() {
				return "iD";
			}

			@Override
			public String getExpected(String filename, String current) {
				filename = getName(filename);
				if (filename.equals("root")) {
					return "org.openiaml.model.diagram";
				} else {
					return "org.openiaml.model.diagram." + filename;
				}
			}

			@Override
			public String getXpathSelector() {
				return "/GenEditorGenerator/plugin";
			}
			
		});
		
	}
	
	public void testPluginName() throws Exception {
		iterate(new AttributeIterator() {

			@Override
			public String getAttributeName() {
				return "name";
			}

			@Override
			public String getExpected(String filename, String current) {
				filename = getName(filename);
				if (filename.equals("root")) {
					return "Iaml Diagram Plugin";
				} else {
					return "Iaml Diagram Plugin (" + spaceCase(filename) + ")";
				}
			}

			@Override
			public String getXpathSelector() {
				return "/GenEditorGenerator/plugin";
			}
			
		});
		
	}
	
	public void testPluginProvider() throws Exception {
		iterate(new AttributeIterator() {

			@Override
			public String getAttributeName() {
				return "provider";
			}

			@Override
			public String getExpected(String filename, String current) {
				try {
					return PluginsTestCase.getVendor();
				} catch (Throwable e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			}

			@Override
			public String getXpathSelector() {
				return "/GenEditorGenerator/plugin";
			}
			
		});
		
	}

	public void testPluginVersion() throws Exception {
		iterate(new AttributeIterator() {

			@Override
			public String getAttributeName() {
				return "version";
			}

			@Override
			public String getExpected(String filename, String current) {
				try {
					return PluginsTestCase.getVersion();
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			}

			@Override
			public String getXpathSelector() {
				return "/GenEditorGenerator/plugin";
			}

			@Override
			public boolean forceSet() {
				return true;
			}
			
		});
		
	}
	
	/**
	 * all preferencePages should have an ID
	 * of 'org.openiaml.model.diagram.xxx'.
	 * 
	 * @throws Exception
	 */
	public void testPreferencePages() throws Exception {
		iterate(new AttributeIterator() {

			@Override
			public String getAttributeName() {
				return "iD";
			}

			@Override
			public String getExpected(String filename, String current) {
				return current.replace("org.openiaml.diagram", "org.openiaml.model.diagram");
			}

			@Override
			public String getXpathSelector() {
				return "/GenEditorGenerator/diagram/preferencePages";
			}

			@Override
			public boolean forceSet() {
				return true;
			}
			
		});
		
	}
	
	
	/**
	 * all preferencePages children should have an ID
	 * of 'org.openiaml.model.diagram.xxx'.
	 * 
	 * @throws Exception
	 */
	public void testPreferencePagesChildren() throws Exception {
		iterate(new AttributeIterator() {

			@Override
			public String getAttributeName() {
				return "iD";
			}

			@Override
			public String getExpected(String filename, String current) {
				return current.replace("org.openiaml.diagram", "org.openiaml.model.diagram");
			}

			@Override
			public String getXpathSelector() {
				return "/GenEditorGenerator/diagram/preferencePages/children";
			}

			@Override
			public boolean forceSet() {
				return true;
			}
			
		});
		
	}
	
	public String getName(String filename) {
		assertTrue(filename, filename.endsWith(".gmfgen"));
		filename = filename.substring(filename.lastIndexOf("/") + 1);
		filename = filename.substring(filename.lastIndexOf("\\") + 1);
		filename = filename.substring(0, filename.indexOf(".gmfgen"));
		return filename;
	}
	
	/**
	 * Tests {@link #getName(filename)}.
	 */
	public void testGetName() {
		assertEquals("root", getName("../root.gmfgen"));
		assertEquals("root", getName("root.gmfgen"));
		assertEquals("another", getName("../another/directory//another.gmfgen"));
		assertEquals("root", getName("..\\another\\directory\\root.gmfgen"));
	}

	private String getPluginName(String filename) {
		filename = getName(filename);
		if (filename.equals("root")) {
			return "org.openiaml.model.diagram";
		} else {
			return "org.openiaml.model.diagram." + filename;
		}
	}
	
	/**
	 * Tests {@link #getPluginName(filename)}.
	 */
	public void testGetPluginName() {
		assertEquals("org.openiaml.model.diagram", getPluginName("../root.gmfgen"));
		assertEquals("org.openiaml.model.diagram", getPluginName("root.gmfgen"));
		assertEquals("org.openiaml.model.diagram.another", getPluginName("../another/directory//another.gmfgen"));
		assertEquals("org.openiaml.model.diagram.test_test_test", getPluginName("..\\another\\directory\\test_test_test.gmfgen"));
	}

	
	/**
	 * Is the given string null or empty?
	 * 
	 * @param modelID
	 * @return
	 */
	private boolean isEmpty(String modelID) {
		return modelID == null || "".equals(modelID);
	}

	/**
	 * Converts "foo_bar" to "FooBar".
	 * 
	 * @param underCase
	 * @return
	 */
	private String camelCase(String underCase) {
		String[] s = underCase.split("_");
		String r = "";
		for (String s2 : s) {
			r += Character.toUpperCase(s2.charAt(0)) + s2.substring(1).toLowerCase();
		}
		return r;
	}
	
	/**
	 * Tests {@link #camelCase(String)}.
	 */
	public void testCamelCase() {
		assertEquals("FooBar", camelCase("foo_bar"));
		assertEquals("Foo", camelCase("foo"));
	}

	/**
	 * Converts "foo_bar" to "Foo Bar".
	 * 
	 * @param underCase
	 * @return
	 */
	private String spaceCase(String underCase) {
		String[] s = underCase.split("_");
		String r = "";
		for (String s2 : s) {
			if (!r.equals(""))
				r += " ";
			r += Character.toUpperCase(s2.charAt(0)) + s2.substring(1).toLowerCase();
		}
		return r;
	}
	
	/**
	 * Tests {@link #spaceCase(String)}.
	 */
	public void testSpaceCase() {
		assertEquals("Foo Bar", spaceCase("foo_bar"));
		assertEquals("Foo", spaceCase("foo"));
	}

	/**
	 * Represents a triple of (diagram canvas element, diagram ID, diagram gmfgen filename).
	 * 
	 * @author jmwright
	 *
	 */
	public class RootElementPair {
		private EClass element;
		private String modelID;
		private String filename;
		
		/**
		 * @param element
		 * @param modelID
		 */
		public RootElementPair(EClass element, String modelID, String filename) {
			super();
			this.element = element;
			this.modelID = modelID;
			this.filename = filename;
		}
		
		public EClass getElement() {
			return element;
		}
		public String getModelID() {
			return modelID;
		}
		public String getFilename() {
			return filename;
		}
	
	}
	
	/**
	 * Fix any missing OpenBehaviours, and check existing
	 * behaviours.
	 * 
	 * @throws Exception
	 */
	public void testFixOpenBehaviours() throws Exception {
		// initialise all root elements
		final List<RootElementPair> rootElements = new ArrayList<RootElementPair>();
		
		// from all gmfgens
		iterate(new DefaultIterator() {
			
			@Override
			public void execute2(String filename, Document doc) throws Exception {
				// find the 
				Element root = xpathFirst(doc, "/GenEditorGenerator");
				String modelID = root.getAttribute("modelID");
				if (getName(filename).equals("root"))
					modelID = "Iaml";
				assertFalse("Document modelID has not been set yet", isEmpty(modelID));
				Element domainDiagramElement = xpathFirst(doc, "/GenEditorGenerator/diagram/domainDiagramElement");
				
				String href = domainDiagramElement.getAttribute("href");
				// trim to last /
				href = href.substring(href.lastIndexOf("/") + 1);
					
				EClass rootElement = resolveSimpleEClass(href);

				rootElements.add(new RootElementPair(rootElement, modelID, filename));
			}
		});
		
		// and then check all gmfgens that these root elements are coorect
		iterate(new DefaultIterator() {
			
			@Override
			public void execute2(String filename, Document doc) throws Exception {
				// will do both links and nodes
				IterableElementList nodes = xpath(doc, "/GenEditorGenerator/diagram/topLevelNodes");
				nodes.addAll(xpath(doc, "/GenEditorGenerator/diagram/links"));
				assertFalse(filename + ": No nodes found", nodes.isEmpty());
				
				boolean changed = false;
				for (Element node : nodes) {
					// find the meta element for this node
					Element meta = xpathFirst(node, "modelFacet/metaClass");
					// iaml.genmodel#//model/CompositeOperation
					String metaHref = meta.getAttribute("href");
					// CompositeOperation
					metaHref = metaHref.substring(metaHref.lastIndexOf("/") + 1);
					
					EClass metaElement = resolveSimpleEClass(metaHref);
					
					try {
						RootElementPair pair = getBestPair(rootElements, metaElement);
						
						if (pair == null)
							continue;		// can't open this element
						
						// there must be a behaviour for this meta element!
						changed |= checkOrAddOpenBehaviour(doc, node, pair);
						
						// check readonly labels contained within
						IterableElementList labels = xpath(node, "labels[@readOnly='true']");
						
						for (Element label : labels) {
							
							try {
								changed |= checkOrAddOpenBehaviour(doc, label, pair);
							} catch (Throwable t) {
								String labelName = label.getAttribute("editPartClassName"); 
								throw new RuntimeException("Label " + labelName + ": " + t.getMessage(), t);
							}
							
						}


					} catch (Throwable t) {
						throw new RuntimeException(node.getAttribute("editPartClassName") + ": " + t.getMessage(), t);
					}
				}	
				
				if (changed) {
					saveDocument(doc, filename);
				}
			}
			
			/**
			 * Checks the given node (can either be a /node, /link or /label)
			 * that it contains the given OpenDiagramBehaviour. Adds it if it
			 * doesn't exist, otherwise check that it matches what it should be. 
			 * 
			 * @param doc The root document
			 * @param node The node to check for behaviour containment
			 * @param pair The RootElementPair pointing to the editor it should open
			 * @return true if the document has been modified, false otherwise
			 * @throws XPathExpressionException
			 */
			private boolean checkOrAddOpenBehaviour(Document doc, Element node, RootElementPair pair) throws XPathExpressionException {
				IterableElementList beh = xpath(node, "behaviour");
				assertFalse("Too many behaviours for " + node, beh.size() > 1);
				
				String policyName = camelCase(getName(pair.getFilename())) + "OpenDiagramEditPolicy";
				String diagramKind = pair.getModelID();
				String editorId = getPluginName(pair.getFilename()) + ".part.IamlDiagramEditorID";

				if (beh.size() == 1) {
					// check it is correct
					Element b = beh.item(0);
					assertEquals("gmfgen:OpenDiagramBehaviour", b.getAttribute("xsi:type"));
					assertEquals(policyName, b.getAttribute("editPolicyClassName"));
					assertEquals(diagramKind, b.getAttribute("diagramKind"));
					assertEquals(editorId, b.getAttribute("editorID"));
					
				} else if (beh.size() == 0) {
					// there is none; insert one
					log("Adding new behaviour " + policyName);
					
					Element behaviour = doc.createElement("behaviour");
					behaviour.setAttribute("xsi:type", "gmfgen:OpenDiagramBehaviour");
					behaviour.setAttribute("editPolicyClassName", policyName);
					behaviour.setAttribute("diagramKind", diagramKind);
					behaviour.setAttribute("editorID", editorId);
					
					node.appendChild(behaviour);

					return true;
				}
				return false;

			}
			
		});
		
	}

	/**
	 * What should be the element that will open the given element?
	 * 
	 * @param rootElements
	 * @param metaElement
	 * @return the pair or null if it shouldn't be opened
	 */
	private RootElementPair getBestPair(
			List<RootElementPair> rootElements, EClass metaElement) {
		
		RootElementPair best = null;
		for (RootElementPair pair : rootElements) {
			if (pair.getElement().isSuperTypeOf(metaElement)) {
				if (best == null || best.getElement().isSuperTypeOf(pair.getElement())) {
					best = pair;
				}
			}
		}
		
		return best;
	}

	/**
	 * Tests {@link #getBestPair(List, EClass)}.
	 */
	public void testGetBestPair() {
		EClass DomainStore = ModelPackage.eINSTANCE.getDomainStore(); 
		EClass UserStore = UsersPackage.eINSTANCE.getUserStore(); 
		EClass ApplicationElement = ModelPackage.eINSTANCE.getApplicationElement(); 
		
		List<RootElementPair> elements = new ArrayList<RootElementPair>();
		
		// only DomainStore
		elements.add(new RootElementPair(DomainStore, null, null));		
		assertEqualsPair(DomainStore, getBestPair(elements, DomainStore));
		assertEqualsPair(DomainStore, getBestPair(elements, UserStore));
		assertEqualsPair(null, getBestPair(elements, ApplicationElement));
		
		// add UserStore
		elements.add(new RootElementPair(UserStore, null, null));		
		assertEqualsPair(UserStore, getBestPair(elements, UserStore));
		assertEqualsPair(DomainStore, getBestPair(elements, DomainStore));
		assertEqualsPair(null, getBestPair(elements, ApplicationElement));

		// add UserStore
		elements.add(new RootElementPair(ApplicationElement, null, null));		
		assertEqualsPair(UserStore, getBestPair(elements, UserStore));
		assertEqualsPair(DomainStore, getBestPair(elements, DomainStore));
		assertEqualsPair(ApplicationElement, getBestPair(elements, ApplicationElement));
		
		elements.clear();
		elements.add(new RootElementPair(ApplicationElement, null, null));		
		assertEqualsPair(null, getBestPair(elements, UserStore));
		assertEqualsPair(null, getBestPair(elements, DomainStore));
		assertEqualsPair(ApplicationElement, getBestPair(elements, ApplicationElement));

	}
	
	private void assertEqualsPair(EClass a,
			RootElementPair pair) {
		if (a == null)
			assertNull(pair);
		else
			assertEquals(a, pair.getElement());
	}

	/**
	 * For every editor instance, every subclass of the 
	 * root diagram element should be openable in every editor.
	 * 
	 * @throws Exception
	 */
	public void testAllOpenableElementsAreOpenable() throws Exception {
		for (String filename : getGenList()) {
			Document doc = getCache().get(filename);
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
		for (String filename : getGenList()) {
			try {
			Document doc = getCache().get(filename);
			
			// will do both links and nodes
			IterableElementList nodes = xpath(doc, "/GenEditorGenerator/diagram/topLevelNodes");
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
					
					IterableElementList beh = xpath(node, "behaviour");
					String foundType = null;
					for (Element b : beh) {
						if (b.getAttribute("xsi:type").equals("gmfgen:OpenDiagramBehaviour")) {
							// found it directly
							foundType = b.getAttribute("diagramKind");
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
					
					assertTrue(filename + ": The diagram element '" + metaHref + "' which is a subclass of '" + rootElement + "', did not have an OpenDiagramPolicy of '" + modelID + "', found: " + foundType,
							found);
				}
				
			}
			} catch (AssertionFailedError e) {
				throw new RuntimeException(filename + ": " + e.getMessage(), e);
			}
		}
	}
	
	/**
	 * In each .gmfgen, each different diagram type should be unique.
	 * 
	 * @throws Exception
	 */
	public void testUniqueOpenDiagrams() throws Exception {
		for (String filename : getGenList()) {
			Document doc = getCache().get(filename);
			
			List<String> diagramKinds = new ArrayList<String>();
			List<String> editorIDs = new ArrayList<String>();
			List<String> policyNames = new ArrayList<String>();
			
			// will do both links and nodes
			IterableElementList nodes = xpath(doc, "/GenEditorGenerator/diagram/topLevelNodes");
			nodes.addAll(xpath(doc, "/GenEditorGenerator/diagram/links"));
			assertFalse(filename + ": No nodes found", nodes.isEmpty());
			
			for (Element node : nodes) {
				// does this have a behaviour?
				IterableElementList beh = xpath(node, "behaviour");
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
	
	/**
	 * Make sure there is only one 'contextMenu' in the root of each
	 * .gmfgen. Otherwise, there may be a situation where multiple identical
	 * commands with the same ID would be generated, throwing a warning
	 * "Conflict for xxx.LoadResourceAction".
	 * 
	 */
	public void testContextMenus() throws Exception {
		for (String filename : getGenList()) {
			Document doc = getCache().get(filename);
			
			IterableElementList nodes = xpath(doc, "/GenEditorGenerator/contextMenus");
			
			assertEquals(filename + ": there should be exactly one /contextMenus", nodes.size(), 1);
		}
	}
	
}
