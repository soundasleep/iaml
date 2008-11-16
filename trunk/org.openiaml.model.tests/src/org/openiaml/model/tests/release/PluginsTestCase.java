/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import junit.framework.TestCase;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Tests .mf and plugin.xml files of the specified plugins
 * 
 * @author jmwright
 *
 */
public class PluginsTestCase extends TestCase {

	public static final String GMF_ROOT = "../org.openiaml.model/model/";
	/**
	 * All .gmfgen files in this project.
	 */
	public static final String[] GMFGENS = new String[] { 
		GMF_ROOT + "domain_object.gmfgen",
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
		"org.openiaml.model.diagram.domain_object",
		"org.openiaml.model.diagram.domain_store",
		"org.openiaml.model.diagram.element",
		"org.openiaml.model.diagram.operation",
		"org.openiaml.model.diagram.visual",
		"org.openiaml.model.diagram.wire",
		"org.openiaml.model.drools",
		"org.openiaml.model.edit",
	};
	private Map<String,Properties> loadedManifests = new HashMap<String,Properties>(); 
	
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
			assertEquals( file + ": expected equal vendor",
					version,
					properties.get("Bundle-Vendor"));
		}
	}
	
	/**
	 * Apply an XPath query to an XML document.
	 */
	public NodeList xpath(Document doc, String query) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr = xpath.compile(query);
		NodeList result = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		return result;
	}
	
	/**
	 * Get the first node result from an XPath query.
	 */
	public Element xpathFirst(Document doc, String query) throws XPathExpressionException {
		return (Element) xpath(doc, query).item(0);
	}
	
	public Document firstDocument(Map<?,Document> map) {
		return map.values().iterator().next();
	}
	
	/**
	 * Load a properties file.
	 */
	public Properties loadProperties(String manifest) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileInputStream(manifest));
		return p;
	}
	
	/**
	 * Load an XML document.
	 */
	public Document loadDocument(String filename) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		return loadDocument( new FileInputStream(filename) );
	}

	/**
	 * Load an XML document.
	 */
	public Document loadDocument(InputStream source) throws ParserConfigurationException, SAXException, IOException {
		// load the model version
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(source);
		
		// done
		source.close();
		
		return doc;
	}
	
}
