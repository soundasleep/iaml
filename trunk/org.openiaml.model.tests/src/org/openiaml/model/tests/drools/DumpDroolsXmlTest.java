/**
 *
 */
package org.openiaml.model.tests.drools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.drools.export.ExportDroolsJavaXml;
import org.openiaml.model.tests.inference.InferenceTestCase;
import org.openiaml.model.xpath.IterableElementList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * Dump the XML involved in the rule bases.
 *
 * @author jmwright
 *
 */
public class DumpDroolsXmlTest extends InferenceTestCase {

	protected Document loadTestXML() throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		return loadDocument("src/org/openiaml/model/tests/drools/parser-test.xml");
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
		new ExportDroolsJavaXml().parseJava(d, root, "insert(rw);");

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
		new ExportDroolsJavaXml().parseJava(d, root, "// a comment\nRunInstanceWire rw = handler.generatedRunInstanceWire(sw, sw, event, operation);		rw.setName(\"run\");		insert(rw); insert(\"a complicated string. with full stops. and line breaks;\");");

		IterableElementList statements = xpath(root, "statement");
		assertEquals(4, statements.getLength());

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

		// fourth statement
		// insert("a complicated string. with full stops. and line breaks;");
		{
			Element statement = (Element) statements.item(3);

			Element method = xpathFirst(statement, "method");
			assertEquals(1, method.getAttributes().getLength());
			assertEquals("insert", method.getAttribute("name"));

			Element argumentList = xpathFirst(method, "argument-list");
			assertEquals(0, argumentList.getAttributes().getLength());
			assertEquals(1, argumentList.getChildNodes().getLength());

			Element argument = (Element) argumentList.getChildNodes().item(0);
			assertEquals("string-argument", argument.getNodeName());
			assertEquals("a complicated string. with full stops. and line breaks;", argument.getAttribute("value"));
		}

	}

	public void testDumpXml() throws Exception {
		ExportDroolsJavaXml dump = new ExportDroolsJavaXml();
		Map<String,Document> results = dump.getRuleXmlDocuments();

		for (String f : results.keySet()) {

			String name = f.substring(f.lastIndexOf("/"));
			IFile out = project.getFile(name + ".xml");

			// load the created XML and replace the <rhs> with
			// more XML (specific to our use in IAML)
			
			Document document = results.get(f);

			// out.create(source, true, monitor);
			saveDocument(document, out.getLocation().toFile());
		}
		
		refreshProject();
	}

	/**
	 * Get the text within the given element.
	 *
	 * @param expression
	 * @return
	 */
	protected String getTextInNode(Element expression) {
		String out = "";
		NodeList nl = expression.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			if (nl.item(i) instanceof Text) {
				out += ((Text) nl.item(i)).getData();
			}
		}
		return out;
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


}
