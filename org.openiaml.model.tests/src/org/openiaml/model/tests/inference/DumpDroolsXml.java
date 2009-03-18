/**
 * 
 */
package org.openiaml.model.tests.inference;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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

import org.eclipse.core.resources.IFile;
import org.openiaml.model.drools.DroolsXmlDumper;
import org.openiaml.model.tests.InferenceTestCase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * Dump the XML involved in the rule bases.
 * 
 * @author jmwright
 *
 */
public class DumpDroolsXml extends InferenceTestCase {
	
	public void setUp() throws Exception {
		super.setUp();		// set up project
	}
	
	/**
	 * Parse the given Java code and insert the results into
	 * the given node.
	 * 
	 * Generates:
	 * RunInstanceWire rw = handler.generatedRunInstanceWire(sw, sw, event, operation);
	 * <statement>
	 *   <assignment>
	 *     <set-variable name="rw" type="RunInstanceWire" />
	 *     <statement>
	 *       <variable name="handler">
	 *         <method name="generatedRunInstanceWire">
	 *           <argument-list>...</argument-list>
	 *         </method>
	 *       </variable>
	 *     </statement>
	 *   </assignment>
	 * </statement>
	 * 
	 * rw.setName("run");
	 * <statement>
	 *   <variable name="rw">
	 *   	<method name="insert">
	 *   		<argument-list>
	 *        		<string-argument value="run" />
	 *     		</argument-list>
	 *  	</method>
	 *   </variable>
	 * </statement>
	 * 
	 * insert(rw);
	 * <statement>
	 *   <method name="insert">
	 *   	<argument-list>
	 *        <variable-argument name="rw" />
	 *      </argument-list>
	 *   </method>
	 * </statement>
	 * 
	 * 				Text n = (Text) rhsList.item(i).getFirstChild();
				String rhs = n.getData();
				n.setData("");	// empty the node
				
				Element t = d.createElement("source");
				n.getParentNode().appendChild(t);
				Text t2 = d.createTextNode(rhs);
				t.appendChild(t2);
	 * 
	 * @param parent
	 * @param java
	 */
	public void parseJava(Document document, Node parent, String java) {
		// expect that no strings contain ;s
		String[] lines = java.split(";");
		for (String javaLine : lines) {
			Element line = document.createElement("statement");
			parent.appendChild(line);
			
			parseJavaEquals(document, line, javaLine); 
		}
	}
	
	/**
	 * Parse a statement "a=b"
	 * 
	 * @param document
	 * @param line
	 * @param javaLine
	 */
	private void parseJavaEquals(Document document, Element line,
			String javaLine) {
		String[] equals = javaLine.split("=");
		if (equals.length == 2) {
			// a = b
			
			Element assignment = document.createElement("assignment");
			line.appendChild(assignment);
			
			String[] elements = equals[0].trim().split("[ \r\n\t]+");
			Element setVariable = document.createElement("set-variable");
			if (elements.length == 2) {
				// typed variable
				setVariable.setAttribute("type", elements[0]);
				setVariable.setAttribute("name", elements[1]);
			} else if (elements.length == 1) {
				// variable
				setVariable.setAttribute("name", elements[0]);
			} else { 
				// who knows
				throw new RuntimeException("Found more than one part of the equals left hand side '" + javaLine + "'");
			}
			assignment.appendChild(setVariable);
			
			Element statement = document.createElement("statement");
			assignment.appendChild(statement);
			parseJavaStatement(document, statement, equals[1]);
		} else if (equals.length == 1) {
			// normal statement
			parseJavaStatement(document, line, javaLine);
		} else {
			// who knows
			throw new RuntimeException("Found more than one part of the equals statement '" + javaLine + "'");
		}
		
	}

	/**
	 * Parse a statement "b.foo()" or "foo()"
	 * 
	 * @param document
	 * @param line
	 * @param javaLine
	 */
	private void parseJavaStatement(Document document, Element line,
			String javaLine) {
		String[] bits = javaLine.split("\\.", 2);
		if (bits.length == 2) {
			// b.foo()
			
			Element variable = document.createElement("variable");
			variable.setAttribute("name", bits[0].trim());
			line.appendChild(variable);
			
			// recurse
			parseJavaStatement(document, variable, bits[1]);
		} else if (bits.length == 1) {
			// foo()
			// get out the brackets
			String[] brackets = bits[0].split("\\(", 2);
			if (brackets.length != 2) {
				// there are no brackets
				throw new RuntimeException("Could not find any brackets in method call '" + bits[0] + "'");
			}
		
			Element method = document.createElement("method");
			method.setAttribute("name", brackets[0].trim());
			line.appendChild(method);
			
			Element argumentList = document.createElement("argument-list");
			method.appendChild(argumentList);
			
			// parse out the arguments
			parseJavaArgumentList(document, argumentList, brackets[1].substring(0, brackets[1].length() - 1));
			
		} else {
			// who knows
			throw new RuntimeException("Somehow split(limit 2) gave us more than 2 results.");
		}
	}

	/**
	 * Parse a statement like "a,b,c,.."
	 * Non-recursive (i.e. we can't have "a,b,foo(a,b),c")
	 * 
	 * @param document
	 * @param method
	 * @param string
	 */
	private void parseJavaArgumentList(Document document, Element argumentList,
			String string) {
		String[] arguments = string.split(",");
		for (String argument : arguments) {
			argument = argument.trim();
			if (argument.indexOf("\"") != -1) {
				// a string
				Element a = document.createElement("string-argument");
				a.setAttribute("value", argument.substring(1, argument.length() - 1));
				argumentList.appendChild(a);
			} else if (argument.matches("-?[0-9]+(\\.?[0-9]+)")) {
				// a number
				Element a = document.createElement("number-argument");
				a.setAttribute("value", argument);
				argumentList.appendChild(a);
			} else {
				// a variable
				Element a = document.createElement("variable-argument");
				a.setAttribute("name", argument);
				argumentList.appendChild(a);
			}
		}
		
	}

	protected Document loadTestXML() throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		return loadDocument("src/org/openiaml/model/tests/inference/parser-test.xml");
	}
	
	/**
	 * Test the parser to make sure that it works as expected.
	 * Only on one line: insert(rw)
	 */
	public void testBasicParsing() throws Exception {
		Document d = loadTestXML();
		Element root = xpathFirst(d, "//test");
		
		assertNotNull(root);
		assertEquals( "123", root.getAttribute("attr") );
		
		// test parsing some simple text
		parseJava(d, root, "insert(rw);");
		
		Element statement = xpathFirst(root, "statement");
		assertEquals(0, statement.getAttributes().getLength());
		
		Element method = xpathFirst(statement, "method");
		assertEquals(1, method.getAttributes().getLength());
		assertEquals("insert", method.getAttribute("name"));
		
		Element argumentList = xpathFirst(method, "argument-list");
		assertEquals(0, argumentList.getAttributes().getLength());
		assertEquals(1, argumentList.getChildNodes().getLength());
		
		Element argument = (Element) argumentList.getChildNodes().item(0);
		assertEquals("variable-argument", argument.getNodeName());
		assertEquals("rw", argument.getAttribute("name"));
		
	}
	
	/**
	 * Test the parser to make sure that it works as expected.
	 * Only on one line: insert(rw)
	 */
	public void testComplexParsing() throws Exception {
		Document d = loadTestXML();
		Element root = xpathFirst(d, "//test");
		
		assertNotNull(root);
		assertEquals( "123", root.getAttribute("attr") );
		
		// test parsing some simple code
		parseJava(d, root, "RunInstanceWire rw = handler.generatedRunInstanceWire(sw, sw, event, operation);		rw.setName(\"run\");		insert(rw);");
		
		NodeList statements = xpath(root, "statement");
		assertEquals(3, statements.getLength());
		
		// first statement
		// RunInstanceWire rw = handler.generatedRunInstanceWire(sw, sw, event, operation);
		{
			Element statement = (Element) statements.item(0);

			Element assignment = xpathFirst(statement, "assignment");
			assertNotNull(assignment);
			assertEquals(0, assignment.getAttributes().getLength());
			assertEquals(2, assignment.getChildNodes().getLength());
			
			Element setVariable = (Element) assignment.getChildNodes().item(0);
			assertEquals("set-variable", setVariable.getNodeName());
			assertEquals(2, setVariable.getAttributes().getLength());
			assertEquals("rw", setVariable.getAttribute("name"));
			assertEquals("RunInstanceWire", setVariable.getAttribute("type"));
			
			Element statement2 = (Element) assignment.getChildNodes().item(1);
			assertEquals("statement", statement2.getNodeName());
			assertEquals(0, statement2.getAttributes().getLength());
			
			Element variable = xpathFirst(statement2, "variable");
			assertEquals(1, variable.getAttributes().getLength());
			assertEquals("handler", variable.getAttribute("name"));
			
			Element method = xpathFirst(variable, "method");
			assertEquals(1, method.getAttributes().getLength());
			assertEquals("generatedRunInstanceWire", method.getAttribute("name"));
			
			Element argumentList = xpathFirst(method, "argument-list");
			assertEquals(0, argumentList.getAttributes().getLength());
			assertEquals(4, argumentList.getChildNodes().getLength());
			
			Element argument1 = (Element) argumentList.getChildNodes().item(0);
			assertEquals("variable-argument", argument1.getNodeName());
			assertEquals("sw", argument1.getAttribute("name"));

			Element argument2 = (Element) argumentList.getChildNodes().item(1);
			assertEquals("variable-argument", argument2.getNodeName());
			assertEquals("sw", argument2.getAttribute("name"));

			Element argument3 = (Element) argumentList.getChildNodes().item(2);
			assertEquals("variable-argument", argument3.getNodeName());
			assertEquals("event", argument3.getAttribute("name"));

			Element argument4 = (Element) argumentList.getChildNodes().item(3);
			assertEquals("variable-argument", argument4.getNodeName());
			assertEquals("operation", argument4.getAttribute("name"));
		}
		
		// second statement
		// rw.setName("run");
		{
			Element statement = (Element) statements.item(1);

			Element variable = xpathFirst(statement, "variable");
			assertEquals(1, variable.getAttributes().getLength());
			assertEquals("rw", variable.getAttribute("name"));
			
			Element method = xpathFirst(variable, "method");
			assertEquals(1, method.getAttributes().getLength());
			assertEquals("setName", method.getAttribute("name"));
			
			Element argumentList = xpathFirst(method, "argument-list");
			assertEquals(0, argumentList.getAttributes().getLength());
			assertEquals(1, argumentList.getChildNodes().getLength());
			
			Element argument = (Element) argumentList.getChildNodes().item(0);
			assertEquals("string-argument", argument.getNodeName());
			assertEquals("run", argument.getAttribute("value"));
		}
		
		// third statement
		// insert(rw);
		{
			Element statement = (Element) statements.item(2);
			
			Element method = xpathFirst(statement, "method");
			assertEquals(1, method.getAttributes().getLength());
			assertEquals("insert", method.getAttribute("name"));
			
			Element argumentList = xpathFirst(method, "argument-list");
			assertEquals(0, argumentList.getAttributes().getLength());
			assertEquals(1, argumentList.getChildNodes().getLength());
			
			Element argument = (Element) argumentList.getChildNodes().item(0);
			assertEquals("variable-argument", argument.getNodeName());
			assertEquals("rw", argument.getAttribute("name"));
		}
		
	}

	public void testDumpXml() throws Exception {
		DroolsXmlDumper dump = new DroolsXmlDumper();
		Map<String,String> results = dump.getRuleXmls();
	
		for (String f : results.keySet()) {
			
			String name = f.substring(f.lastIndexOf("/"));
			IFile out = project.getFile(name + ".xml");

			// who knows what format XmlDump is supplied in?
			// we will assume UTF-8 as the dumped XML is UTF-8
			InputStream source = new ByteArrayInputStream(results.get(f).getBytes("UTF-8")); 

			System.out.println("Writing " + out + "...");
			
			// load the created XML and replace the <rhs> with 
			// more XML (specific to our use in IAML)
			
			Document d = loadDocument(source);
			NodeList rhsList = xpath(d, "//rhs");
			
			for (int i = 0; i < rhsList.getLength(); i++) {
				Text n = (Text) rhsList.item(i).getFirstChild();
				String rhs = n.getData();
				n.setData("");	// empty the node
				
				Element t = d.createElement("source");
				n.getParentNode().appendChild(t);
				Text t2 = d.createTextNode(rhs);
				t.appendChild(t2);
			}
			
			// out.create(source, true, monitor);
			saveDocument(d, out.getLocation().toFile());

		}
		
		refreshProject();
	}
	
	/**
	 * Helper methods, copied directly from XmlTestCase. TODO move these out into a different class.
	 */

	/**
	 * Apply an XPath query to an XML document.
	 */
	public NodeList xpath(Node doc, String query) throws XPathExpressionException {
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
	
	/*
	 * Get the first node result from an XPath query.
	 */
	public Element xpathFirst(Element doc, String query) throws XPathExpressionException {
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
	
}
