/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.xml.xpath.XPathExpressionException;

import org.openiaml.model.tests.SoftCache;
import org.openiaml.model.tests.XmlTestCase;
import org.openiaml.model.xpath.IterableElementList;
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

	public static final String PLUGIN_ROOT = "../";
	
	private static Set<String> plugins = null;
	
	public static final String[] NO_MANIFEST_PLUGINS = new String[] {
		"org.openiaml.model.editor",
		"org.openiaml.update",
	};
	
	/**
	 * Get all of the plugins in the system; that is,
	 * those that start with 'org.openiaml'.
	 * 
	 * @return
	 */
	public static Set<String> getPlugins() {
		if (plugins == null) {
			plugins = new HashSet<String>();
			File root = new File(PLUGIN_ROOT);
			assertTrue(root.isDirectory());
			assertTrue(root.exists());
			
			String[] children = root.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.startsWith("org.openiaml") &&
						!name.startsWith("org.openiaml.iacleaner") &&
						!name.startsWith("org.openiaml.gmf");
				}
			});
			
			for (String c : children) {
				String plugin = root.getAbsolutePath() + File.separator + c;
				assertTrue(new File(plugin).exists());
				assertTrue(new File(plugin).isDirectory());
				plugins.add(plugin);
			}
		}
		return plugins;
	}
	
	private List<String> shortcuts; 
	
	private static SoftCache<String,Properties> manifestCache = new SoftCache<String,Properties>() {

		@Override
		public Properties retrieve(String input) {
			try {
				return loadProperties(input);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
	};
	
	public static SoftCache<String,Properties> getManifestCache() {
		return manifestCache;
	}
	
	private static Set<String> loadedGmfGens = null;
	
	/**
	 * Get all of the GMF gens loaded from disk.
	 * 
	 * @return
	 */
	public static Set<String> getAllGmfGens() {
		if (loadedGmfGens == null) {
			File root = new File(GMF_ROOT);
			loadedGmfGens = new HashSet<String>();
			assertTrue(root.isDirectory());
			
			String[] contents = root.list(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".gmfgen");
				}
				
			});
			for (String filename : contents) {
				loadedGmfGens.add(GMF_ROOT + filename);
			}
		}
		return loadedGmfGens;
	}
	
	private static Set<String> loadedManifests = null;
	
	/**
	 * Get all of the manifests loaded from disk.
	 * 
	 * @return
	 */
	public static Set<String> getAllManifests() {
		if (loadedManifests == null) {
			loadedManifests = new HashSet<String>();

			for (String plugin : getPlugins()) {
				boolean excluded = false;
				for (String exclude : NO_MANIFEST_PLUGINS) {
					if (plugin.endsWith(exclude))
						excluded = true;
				}
				
				if (!excluded) {
					String manifest = plugin + "/META-INF/MANIFEST.MF";
					assertTrue("Manifest '" + manifest + "' does not exist", new File(manifest).exists());
					loadedManifests.add(manifest);
				}
			}
			
		}
		return loadedManifests;
	}
	
	/**
	 * Load up all the .gmfgen's and MANIFEST.MFs from all of our plugins.
	 * 
	 */
	public void setUp() throws Exception {
		super.setUp();
	
		// load up shortcuts
		shortcuts = new ArrayList<String>();
		
		// lets find the first one
		Document doc = firstDocument(GmfGenTestCase.getCache());
		IterableElementList nodes = xpath(doc, "//diagram/containsShortcutsTo");
		for (Element node : nodes) {
			String value = node.getFirstChild().getNodeValue();
			if (!shortcuts.contains(value))
				shortcuts.add(value);
		}
	}
	
	/**
	 * Get the first document in the given cache.
	 * 
	 * @param cache
	 * @return
	 */
	private Document firstDocument(SoftCache<String, Document> cache) {
		return cache.get( getAllGmfGens().iterator().next() );
	}

	public void tearDown() throws Exception {
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
		String version = getVersion();

		// now lets test each .gmfgen
		for (String file : getAllGmfGens()) {
			Document doc = GmfGenTestCase.getCache().get(file);
			assertEquals( file + ": expected equal version", 
					version,
					xpathFirst(doc, "//plugin").getAttribute("version"));
		}

		// now lets test each manifest.mf
		for (String file : getAllManifests()) {
			Properties properties = getManifestCache().get(file);
			assertEquals( file + ": expected equal version",
					version,
					properties.get("Bundle-Version"));
		}
	}
	
	/**
	 * Get the version that all plugins should be.
	 * In particular, this is the version provided in the root
	 * model manifest.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getVersion() throws FileNotFoundException, IOException {
		String manifest = PLUGIN_ROOT + "org.openiaml.model/META-INF/MANIFEST.MF";
		Properties properties = loadProperties(manifest);
		return properties.getProperty("Bundle-Version");
	}
	
	/**
	 * Get the vendor that all plugins should be.
	 * In particular, this is the version provided in the root
	 * model manifest.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getVendor() throws FileNotFoundException, IOException {
		String manifest = PLUGIN_ROOT + "org.openiaml.model/META-INF/MANIFEST.MF";
		Properties properties = loadProperties(manifest);
		return properties.getProperty("Bundle-Vendor");
	}

	/**
	 * Test the vendor tags of each of these files.
	 * 
	 * @throws Exception
	 */
	public void testVendors() throws Exception {
		// get initial version
		String version = getVendor();

		// now lets test each .gmfgen
		for (String file : getAllGmfGens()) {
			Document doc = GmfGenTestCase.getCache().get(file);
			assertEquals( file + ": expected equal vendor", 
					version,
					xpathFirst(doc, "//plugin").getAttribute("provider"));
		}

		// now lets test each manifest.mf
		for (String file : getAllManifests()) {
			Properties properties = getManifestCache().get(file);
			
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
				getManifestCache().put(file, properties);
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
		for (String file : getAllGmfGens()) {
			Document doc = GmfGenTestCase.getCache().get(file);
			IterableElementList nodes = xpath(doc, "//diagram/containsShortcutsTo");
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
		assertEquals(shortcuts.size(), getAllGmfGens().size());
	}
	
	/**
	 * Make sure that each .gmfgen contains shortcuts to
	 * all other .gmfgens
	 */
	public void testContainsProvidedFor() throws Exception {
		// now test them all
		for (String file : getAllGmfGens()) {
			Document doc = GmfGenTestCase.getCache().get(file);
			IterableElementList nodes = xpath(doc, "//diagram/shortcutsProvidedFor");
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
		for (String file : getAllGmfGens()) {
			Document doc = GmfGenTestCase.getCache().get(file);
			DiagramUniqueness du = new DiagramUniqueness();
			
			// get all top level nodes
			IterableElementList nodes = xpath(doc, "//diagram/topLevelNodes");
			
			for (Element node : nodes) {
				IterableElementList behaviours = xpath(node, "behaviour");
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
		for (String file : getAllGmfGens()) {
			Document doc = GmfGenTestCase.getCache().get(file);
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
		for (String file : getAllGmfGens()) {
			Document doc = GmfGenTestCase.getCache().get(file);
			String name = xpathFirst(doc, "//plugin").getAttribute("name");
			assertFalse(file + ": found a duplicate Plugin name '" + name + "'", foundNames.contains(name));
			foundNames.add(name);
		}		
	}
	
}
