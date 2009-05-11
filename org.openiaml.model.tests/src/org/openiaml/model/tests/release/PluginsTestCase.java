/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.xpath.XPathExpressionException;

import org.openiaml.model.tests.XmlTestCase;
import org.openiaml.model.tests.xpath.IterableNodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Tests .mf and plugin.xml files of the specified plugins
 * 
 * @author jmwright
 *
 */
public class PluginsTestCase extends XmlTestCase {

	public static final String GMF_ROOT = "../org.openiaml.model/model/";
	/**
	 * All .gmfgen files in this project.
	 */
	public static final String[] GMFGENS = new String[] { 
		GMF_ROOT + "condition.gmfgen",
		GMF_ROOT + "domain_object.gmfgen",
		GMF_ROOT + "domain_object_instance.gmfgen",
		GMF_ROOT + "domain_store.gmfgen",
		GMF_ROOT + "element.gmfgen",
		GMF_ROOT + "operation.gmfgen",
		GMF_ROOT + "root.gmfgen",
		GMF_ROOT + "visual.gmfgen",
		GMF_ROOT + "wire.gmfgen",
	};
	private Map<String,Document> loadedGmfgens = new HashMap<String,Document>(); 

	public static final String PLUGIN_ROOT = "../";
	/**
	 * All plugins with manifest information in this project.
	 */
	public static final String[] PLUGINS = new String[] { 
		"org.openiaml.model",
		"org.openiaml.model.codegen.oaw",
		"org.openiaml.model.diagram",
		"org.openiaml.model.diagram.custom",
		"org.openiaml.model.diagram.condition",
		"org.openiaml.model.diagram.domain_object",
		"org.openiaml.model.diagram.domain_object_instance",
		"org.openiaml.model.diagram.domain_store",
		"org.openiaml.model.diagram.element",
		"org.openiaml.model.diagram.operation",
		"org.openiaml.model.diagram.visual",
		"org.openiaml.model.diagram.wire",
		"org.openiaml.model.drools",
		"org.openiaml.model.edit",
	};
	private Map<String,Properties> loadedManifests = new HashMap<String,Properties>();
	private List<String> shortcuts; 
	
	/**
	 * Load up all the .gmfgen's and MANIFEST.MFs from all of our plugins.
	 * 
	 */
	public void setUp() throws Exception {
		super.setUp();
		
		// load all .gmfgen's
		for (String gmfgen : GMFGENS) {
			loadedGmfgens.put( gmfgen, loadDocument(gmfgen) );
		}
		
		// load all manifest.mf's
		for (String plugin : PLUGINS) {
			String manifest = PLUGIN_ROOT + plugin + "/META-INF/MANIFEST.MF";
			loadedManifests.put( manifest, loadProperties(manifest) );
		}
	
		// load up shortcuts
		shortcuts = new ArrayList<String>();
		
		// lets find the first one
		Document doc = firstDocument(loadedGmfgens);
		IterableNodeList nodes = xpath(doc, "//diagram/containsShortcutsTo");
		for (Element node : nodes) {
			String value = node.getFirstChild().getNodeValue();
			if (!shortcuts.contains(value))
				shortcuts.add(value);
		}
	}
	
	public void tearDown() throws Exception {
		loadedGmfgens = null;
		loadedManifests = null;
		shortcuts = null;
		
		super.tearDown();
	}
	
	/**
	 * Test the version tags of each of these files.
	 * 
	 * @throws Exception
	 */
	public void testVersions() throws Exception {
		// get initial version
		String version = xpathFirst(firstDocument(loadedGmfgens), "//plugin").getAttribute("version");

		// now lets test each .gmfgen
		for (String file : loadedGmfgens.keySet()) {
			Document doc = loadedGmfgens.get(file);
			assertEquals( file + ": expected equal version", 
					version,
					xpathFirst(doc, "//plugin").getAttribute("version"));
		}

		// now lets test each manifest.mf
		for (String file : loadedManifests.keySet()) {
			Properties properties = loadedManifests.get(file);
			assertEquals( file + ": expected equal version",
					version,
					properties.get("Bundle-Version"));
		}
	}

	/**
	 * Test the vendor tags of each of these files.
	 * 
	 * @throws Exception
	 */
	public void testVendors() throws Exception {
		// get initial version
		String version = xpathFirst(firstDocument(loadedGmfgens), "//plugin").getAttribute("provider");

		// now lets test each .gmfgen
		for (String file : loadedGmfgens.keySet()) {
			Document doc = loadedGmfgens.get(file);
			assertEquals( file + ": expected equal vendor", 
					version,
					xpathFirst(doc, "//plugin").getAttribute("provider"));
		}

		// now lets test each manifest.mf
		for (String file : loadedManifests.keySet()) {
			Properties properties = loadedManifests.get(file);
			
			if (properties.get("Bundle-Vendor").equals("%providerName")) {
				// lets rewrite it manually
				/*
				 * this code mucks up the entire properties file, so we
				 * will replace it in the file manually.
				properties.setProperty("Bundle-Vendor", version);
				
				System.out.println("Writing new MANIFEST.MF for file '" + file + "'...");
				properties.store(new FileWriter(new File(file)), "Unknown comments");
				 */
				String pfile = readFile(new File(file));
				pfile = pfile.replace("Bundle-Vendor: %providerName", "Bundle-Vendor: " + version);
				System.out.println("Writing new MANIFEST.MF for file '" + file + "'...");
				writeFile(new File(file), pfile);
				
				// reload
				properties = loadProperties(file);
				loadedManifests.put(file, properties);				
			}
			
			assertEquals( file + ": expected equal vendor",
					version,
					properties.get("Bundle-Vendor"));
		}
	}
	
	/**
	 * Make sure that each .gmfgen contains shortcuts to
	 * all other .gmfgens
	 */
	public void testContainsShortcutsTo() throws Exception {
		// now test them all
		for (String file : loadedGmfgens.keySet()) {
			Document doc = loadedGmfgens.get(file);
			IterableNodeList nodes = xpath(doc, "//diagram/containsShortcutsTo");
			assertEquals(nodes.getLength(), shortcuts.size());
			for (Element node : nodes) {
				String value = node.getFirstChild().getNodeValue();
				assertTrue( file + ": contains " + value, shortcuts.contains(value) );
			}
		}

	}
	
	/**
	 * Each different .gmfgen should have a different diagram
	 * file name, so there should be identical numbers of 
	 * shortcut names.
	 * 
	 * @throws Exception
	 */
	public void testCorrectNumberOfShortcuts() throws Exception {
		assertEquals(shortcuts.size(), GMFGENS.length);
	}
	
	/**
	 * Make sure that each .gmfgen contains shortcuts to
	 * all other .gmfgens
	 */
	public void testContainsProvidedFor() throws Exception {
		// now test them all
		for (String file : loadedGmfgens.keySet()) {
			Document doc = loadedGmfgens.get(file);
			IterableNodeList nodes = xpath(doc, "//diagram/shortcutsProvidedFor");
			assertEquals(nodes.getLength(), shortcuts.size());
			for (Element node : nodes) {
				String value = node.getFirstChild().getNodeValue();
				assertTrue( file + ": contains " + value, shortcuts.contains(value) );
			}
		}

	}
	
	protected class DiagramUniqueness {
		
		private List<String> editPolicyClassNames = new ArrayList<String>();
		private List<String> diagramKinds = new ArrayList<String>();
		private List<String> editorIDs = new ArrayList<String>();
		private List<Node> nodes = new ArrayList<Node>();
		

		/**
		 * Check a given set of attributes for the appropriate uniqueness.
		 * 
		 * @param prefix a prefix to put at the beginning of any assertions
		 * @param node Behaviour node
		 * @param editPolicyClassName
		 * @param diagramKind
		 * @param editorID
		 */
		public void check(String prefix, Node node, String editPolicyClassName, String diagramKind,
				String editorID) {
			// make sure that either ALL of these elements exist
			// at the same place, or that NONE of them exist anywhere
			
			int a = editPolicyClassNames.indexOf(editPolicyClassName);
			int b = diagramKinds.indexOf(diagramKind);
			int c = editorIDs.indexOf(editorID);
			
			if (a == b && b == c) {
				// the same: ok!
				if (a == -1) {
					// it didn't exist already
					editPolicyClassNames.add(editPolicyClassName);
					diagramKinds.add(diagramKind);
					editorIDs.add(editorID);
					nodes.add(node);
				}
			} else {
				// something was inconsistent
				if (a != -1) {
					// editPolicyClassName already existed
					fail(prefix + "editPolicyClassName for " + node + " already existed in node " + nodes.get(a));
				}
				if (b != -1) {
					// diagramKind already existed
					fail(prefix + "diagramKind for " + node + " already existed in node " + nodes.get(b));
				}
				if (c != -1) {
					// editorID already existed
					fail(prefix + "editorID for " + node + " already existed in node " + nodes.get(c));
				}
			}
		}
		
	}
	
	/**
	 * Test each OpenDiagramPolicy to make sure it is sufficiently
	 * unique within the .gmfgen.
	 * 
	 * That is, all different editors in a .gmfgen have the exact same
	 * editorClass, editorID and diagramKind.
	 * 
	 * @throws Exception
	 */
	public void testOpenDiagramUniqueness() throws Exception {
		int checks = 0;
		
		// get all gmfgens
		for (String file : loadedGmfgens.keySet()) {
			Document doc = loadedGmfgens.get(file);
			DiagramUniqueness du = new DiagramUniqueness();
			
			// get all top level nodes
			IterableNodeList nodes = xpath(doc, "//diagram/topLevelNodes");
			
			for (Element node : nodes) {
				IterableNodeList behaviours = xpath(node, "behaviour");
				for (Element b : behaviours) {
					// assume each Behaviour is an OpenDiagramPolicy
					// (since we can't get xsi:type from xpath)
					String editPolicyClassName = b.getAttributes().getNamedItem("editPolicyClassName").getNodeValue();
					String diagramKind = b.getAttributes().getNamedItem("diagramKind").getNodeValue();
					String editorID = b.getAttributes().getNamedItem("editorID").getNodeValue();
					
					assertTrue("editPolicyClassName is empty for " + b, !editPolicyClassName.isEmpty());
					assertTrue("diagramKind is empty for " + b, !diagramKind.isEmpty());
					assertTrue("editorID is empty for " + b, !editorID.isEmpty());
					
					du.check("[" + file + "] ", b, editPolicyClassName, diagramKind, editorID);
					checks++;
				}
				
			}
		}
		
		// we should have checked at least one
		assertTrue("We didn't check any OpenDiagramPolicies", checks != 0);
	}
	
	/**
	 * Make sure that plugin IDs are unique across all diagram editors.
	 * 
	 * @throws XPathExpressionException
	 */
	public void testGmfgenPluginIds() throws XPathExpressionException {
		List<String> foundIDs = new ArrayList<String>();
		
		// now lets test each .gmfgen
		for (String file : loadedGmfgens.keySet()) {
			Document doc = loadedGmfgens.get(file);
			String id = xpathFirst(doc, "//plugin").getAttribute("iD");
			assertFalse(file + ": found a duplicate Plugin ID '" + id + "'", foundIDs.contains(id));
			foundIDs.add(id);
		}		
	}
	
	/**
	 * Make sure that plugin names are unique across all diagram editors.
	 * 
	 * @throws XPathExpressionException
	 */
	public void testGmfgenPluginNames() throws XPathExpressionException {
		List<String> foundNames = new ArrayList<String>();
		
		// now lets test each .gmfgen
		for (String file : loadedGmfgens.keySet()) {
			Document doc = loadedGmfgens.get(file);
			String name = xpathFirst(doc, "//plugin").getAttribute("name");
			assertFalse(file + ": found a duplicate Plugin name '" + name + "'", foundNames.contains(name));
			foundNames.add(name);
		}		
	}
	
}
