/**
 * 
 */
package org.openiaml.model.tests.xpath;

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

/**
 * @author jmwright
 *
 */
public class DefaultXpathTestCase extends TestCase implements XpathTestCase {

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
		IterableNodeList nl = xpath(e, query);
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
		IterableNodeList nl = xpath(e, query);
		if (nl.getLength() == 1) {
			return (Element) nl.item(0);
		}
		return null;
	}
	
}
