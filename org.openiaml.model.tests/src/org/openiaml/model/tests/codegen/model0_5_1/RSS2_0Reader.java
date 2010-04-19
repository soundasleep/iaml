/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import junit.framework.TestCase;

import org.openiaml.model.tests.XmlTestCase;
import org.openiaml.model.tests.xpath.DefaultXpathTestCase;
import org.openiaml.model.xpath.IXpath;
import org.openiaml.model.xpath.IterableElementList;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * A helper class for reading and testing RSS 2.0 feeds. This test
 * class is derived from the RSS 2.0 specifications at
 * <a href="http://cyber.law.harvard.edu/rss/rss.html">http://cyber.law.harvard.edu/rss/rss.html</a>.
 * 
 * @author jmwright
 */
public class RSS2_0Reader extends TestCase {
	
	/**
	 * The format of RSS 2.0 dates, namely <code>Tue, 10 Jun 2008 09:41:01 GMT</code>.
	 * @see SimpleDateFormat#SimpleDateFormat(String)
	 */
	public static final String RSS_DATE_FORMAT =
		"EEE, d MMM yyyy HH:mm:ss zzz";

	private URL url;
	private Document xml;

	/**
	 * XPath helper methods.
	 * @see org.openiaml.model.tests.xpath.IXpath
	 */
	private IXpath xpath = new DefaultXpathTestCase();

	public RSS2_0Reader(String url) throws RSSReaderException {
		this(convertStringToURL(url));
	}
	
	public RSS2_0Reader(URL url) throws RSSReaderException {
		this.url = url;
		this.xml = loadXML();
	}
	
	/**
	 * Assert that the <code>/rss/channel/title</code> of the loaded
	 * feed is equal to the given parameter.
	 */
	public void assertTitle(String arg) throws RSSReaderException {
		assertEquals(arg, getTextContent("/rss/channel/title"));
	}

	/**
	 * Assert that the <code>/rss/channel/description</code> of the loaded
	 * feed is equal to the given parameter.
	 */
	public void assertDescription(String arg) throws RSSReaderException {
		assertEquals(arg, getTextContent("/rss/channel/description"));
	}

	/**
	 * Assert that the <code>/rss/channel/generator</code> of the loaded
	 * feed is equal to the given parameter.
	 */
	public void assertGenerator(String arg) throws RSSReaderException {
		assertEquals(arg, getTextContent("/rss/channel/generator"));
	}

	/**
	 * Assert that the <code>/rss/channel/lastBuildDate</code> of the loaded
	 * feed is equal to the given parameter.
	 */
	public void assertLastBuildDate(Date date) throws RSSReaderException {
		String actualDate = getTextContent("/rss/channel/lastBuildDate");
		Date actual;
		try {
			actual = new SimpleDateFormat(RSS_DATE_FORMAT).parse(actualDate);
		} catch (ParseException e) {
			throw new RSSReaderException("Could not parse '" + actualDate + "': " + e.getMessage(), e);
		}		
		assertEquals("date '" + actual + "' was not expected date '" + date + "'", date.getTime(), actual.getTime());
	}

	/**
	 * Execute the given XPath string and return the text content.
	 * Asserts that there is only one result for the xpath query.
	 * 
	 * @param query the XPath query to evaluate
	 * @return
	 * @throws RSSReaderException if the query throws a {@link XPathExpressionException}
	 */
	protected String getTextContent(String query) throws RSSReaderException {
		IterableElementList list;
		try {
			list = this.xpath.xpath(xml, query);
		} catch (XPathExpressionException e) {
			throw new RSSReaderException("Invalid XPath: " + query, e);
		}
		assertEquals(1, list.size());
		return list.item(0).getTextContent();
	}

	protected Document loadXML() throws RSSReaderException {
		try {
			return XmlTestCase.loadDocument(url.openStream());
		} catch (ParserConfigurationException e) {
			throw new RSSReaderException(e);
		} catch (SAXException e) {
			throw new RSSReaderException(e);
		} catch (IOException e) {
			throw new RSSReaderException(e);
		}
	}
	
	/**
	 * Convert the given string URL to a Java URL. Throw a
	 * {@link RSSReaderException} if the conversion fails.
	 * 
	 * @param url the string url
	 * @return a URL for the given string url
	 * @throws RSSReaderException if a {@link MalformedURLException} occurs
	 */
	protected static URL convertStringToURL(String url) throws RSSReaderException {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			throw new RSSReaderException(e);
		}
	}
	
	/**
	 * An exception that occurred when trying to load an RSS feed,
	 * or parse the XPath query.
	 */
	public static class RSSReaderException extends Exception {

		private static final long serialVersionUID = 1L;
		
		public RSSReaderException(Throwable e) {
			super(e.getMessage(), e);
		}

		public RSSReaderException(String message, Throwable e) {
			super(message, e);
		}

	}
	
}
