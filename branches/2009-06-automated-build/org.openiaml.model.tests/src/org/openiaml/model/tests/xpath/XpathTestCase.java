/**
 * 
 */
package org.openiaml.model.tests.xpath;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Methods that need to be included in XPath test cases.
 * 
 * @author jmwright
 *
 */
public interface XpathTestCase {

	/**
	 * Apply an XPath query to an XML document.
	 */
	public IterableElementList xpath(Node doc, String query) throws XPathExpressionException;
	
	/**
	 * Get the first node result from an XPath query.
	 */
	public Element xpathFirst(Document doc, String query) throws XPathExpressionException;
	
	/**
	 * Get the first node result from an XPath query.
	 */
	public Element xpathFirst(Element e, String query) throws XPathExpressionException;
	
	/**
	 * Get the first node result from an XPath query.
	 * 
	 * @returns the found node, or null if none is found (or more than one is found)
	 */
	public Element hasXpathFirst(Element e, String query) throws XPathExpressionException;
	
	/**
	 * Get the first node result from an XPath query.
	 * 
	 * @returns the found node, or null if none is found (or more than one is found)
	 */
	public Element hasXpathFirst(Document e, String query) throws XPathExpressionException;
	
}
