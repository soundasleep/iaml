/**
 * 
 */
package org.openiaml.model.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import junit.framework.TestCase;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Helper methods for test cases that use XML-based and properties-based
 * tests.
 * 
 * @author jmwright
 *
 */
public class XmlTestCase extends TestCase {

	/**
	 * Wraps {@link org.w3c.dom.NodeList} with the Iterable interface.
	 * 
	 * @author jmwright
	 *
	 */
	public class IterableNodeList implements NodeList, Iterable<Element> {
		private NodeList nl;
		
		public IterableNodeList(NodeList elements) {
			this.nl = elements;
		}

		/* (non-Javadoc)
		 * @see java.lang.Iterable#iterator()
		 */
		@Override
		public Iterator<Element> iterator() {
			List<Element> e = new ArrayList<Element>();
			for (int i = 0; i < nl.getLength(); i++) {
				e.add((Element) nl.item(i));
			}
			return e.iterator();
		}

		/* (non-Javadoc)
		 * @see org.w3c.dom.NodeList#getLength()
		 */
		@Override
		public int getLength() {
			return nl.getLength();
		}

		/* (non-Javadoc)
		 * @see org.w3c.dom.NodeList#item(int)
		 */
		@Override
		public Node item(int index) {
			return nl.item(index);
		}
	}
	
	/**
	 * Apply an XPath query to an XML document.
	 */
	public IterableNodeList xpath(Node doc, String query) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr = xpath.compile(query);
		NodeList result = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		return new IterableNodeList(result);
	}
	
	/**
	 * Get the first node result from an XPath query.
	 */
	public Element xpathFirst(Document doc, String query) throws XPathExpressionException {
		assertNotNull("Cannot find the xpath for a null document", doc);
		Element e = (Element) xpath(doc, query).item(0);
		assertNotNull("Could not find result for query '" + query + "'", e);
		return e;
	}
	
	/**
	 * Get the first node result from an XPath query.
	 */
	public Element xpathFirst(Element e, String query) throws XPathExpressionException {
		assertNotNull("Cannot find the xpath for a null element", e);
		Element e2 = (Element) xpath(e, query).item(0);
		assertNotNull("Could not find result for query '" + query + "'", e2);
		return e2;
	}
	
	/**
	 * Get the first node result from an XPath query.
	 * 
	 * @returns the found node, or null if none is found (or more than one is found)
	 */
	public Element hasXpathFirst(Element e, String query) throws XPathExpressionException {
		assertNotNull("Cannot find the xpath for a null element", e);
		NodeList nl = xpath(e, query);
		if (nl.getLength() == 1) {
			return (Element) nl.item(0);
		}
		return null;
	}
	
	/**
	 * Get the first node result from an XPath query.
	 * 
	 * @returns the found node, or null if none is found (or more than one is found)
	 */
	public Element hasXpathFirst(Document e, String query) throws XPathExpressionException {
		assertNotNull("Cannot find the xpath for a null element", e);
		NodeList nl = xpath(e, query);
		if (nl.getLength() == 1) {
			return (Element) nl.item(0);
		}
		return null;
	}
	
	/**
	 * Resolve an EMF query like '//@figures.0/@descriptors.29/@accessors.0'
	 * to an Element. Fail if the element cannot be found.
	 * 
	 * @param doc
	 * @param query
	 * @return
	 * @throws XPathExpressionException 
	 */
	public Element resolveEmfElement(Document doc, String query) throws XPathExpressionException {
		// we cheat by splitting up the /, replacing the numbers with
		// indexes, and resolving it through xpath
		assertTrue("'" + query + "' must start with '//'", query.startsWith("//"));
		String q2 = query.substring(2);
		
		String out = "/";
		String[] bits = q2.split("/");
		for (String bit : bits) {
			if (bit.contains(".")) {
				// @figures.0
				String index = bit.substring(bit.indexOf(".") + 1);
				out += "/"			// to create the tree 
					+ bit.substring(1, bit.indexOf("."))	// chop off '@' 
					+ "[" 
					+ (new Integer(index).intValue() + 1)
					+ "]";
			} else {
				// @figures
				out += "/"			// to create the tree
					+ bit.substring(1);		// chop off '@'
			}
		}
		
		try {
			return xpathFirst(doc, out);
		} catch (XPathExpressionException e) {
			throw new RuntimeException("XPath query '" + out + "' was not valid: " + e.getMessage(), e);
		}
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
	public static Document loadDocument(String filename) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		return loadDocument( new FileInputStream(filename) );
	}

	/**
	 * Load an XML document.
	 */
	public static Document loadDocument(InputStream source) throws ParserConfigurationException, SAXException, IOException {
		// load the model version
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(source);
		
		// done
		source.close();
		
		return doc;
	}

	/**
	 * Try saving an XML document.
	 */
	public void saveDocument(Document doc, File target) throws IOException, TransformerException {
        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = transfac.newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");	// omit '<?xml version="1.0"?>'
        trans.setOutputProperty(OutputKeys.INDENT, "yes");

        // TODO clean this up into a piped input/output stream setup?
		FileWriter sw = new FileWriter(target);
        StreamResult result = new StreamResult(sw);
        DOMSource source = new DOMSource(doc);
        trans.transform(source, result);
        sw.close();
	}
	
	/**
	 * Assert that the given file exists.
	 * 
	 * @param source
	 */
	public void assertFileExists(File source) {
		assertFileExists("", source);
	}
	
	/**
	 * Assert that the given file exists.
	 * 
	 * @param source
	 */
	public void assertFileExists(String prefix, File source) {
		assertTrue(prefix + "File '" + source.getAbsolutePath() + "' doesn't exist.", source.exists());
	}
	
	/**
	 * Read in a file into a string.
	 * 
	 * @throws IOException if an IO exception occurs
	 */
	public static String readFile(File sourceFile) throws IOException {
		if (!sourceFile.exists()) {
			throw new IOException("File " + sourceFile.getAbsolutePath() + " does not exist.");
		}
		
		int bufSize = 128;
		StringBuffer sb = new StringBuffer(bufSize);
		BufferedReader reader = new BufferedReader(new FileReader(sourceFile), bufSize);
				
		char[] chars = new char[bufSize];
		int numRead = 0;
		while ((numRead = reader.read(chars)) > -1) {
			sb.append(String.valueOf(chars).substring(0, numRead));	
		}
		
		reader.close();
		return sb.toString();
	}

	/**
	 * Helper method: assert A >= B.
	 * 
	 * @param expected expected value (B)
	 * @param actual actual value (A)
	 */
	protected void assertGreaterEq(int expected, int actual) {
		assertTrue("expected >= than " + expected + ", but actually had " + actual, actual >= expected);
	}
	
	/**
	 * Assert that the two elements are not the same.
	 * 
	 * @param string
	 */
	public void assertNotEqual(String message, Object a, Object b) {
		if (a == null) {
			assertNotNull(message + ", expected not equal: '" + a + "' and: '" + b + "'", b);
		} else {
			assertFalse(message + ", expected not equal: '" + a + "' and: '" + b + "'", a.equals(b));
		}
	}

	
}
