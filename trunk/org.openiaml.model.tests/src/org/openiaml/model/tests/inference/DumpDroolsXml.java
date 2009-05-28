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
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;

import junit.framework.AssertionFailedError;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.drools.DroolsXmlDumper;
import org.openiaml.model.tests.DijkstraAlgorithm;
import org.openiaml.model.tests.InferenceTestCase;
import org.openiaml.model.tests.TestComposition;
import org.openiaml.model.tests.xpath.IterableElementList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
		
		identifierCount = -1;		// reset identifier count
		existsIdentifierCount = 0;
	}
	
	/**
	 * Parse the given Java code and insert the results into
	 * the given node.
	 * 
	 * Generates:
	 * <pre>
	 * RunInstanceWire rw = handler.generatedRunInstanceWire(sw, sw, event, operation);
	 * 
     * &lt;statement&gt;
     *   &lt;assignment&gt;
     *     &lt;set-variable name="rw" type="RunInstanceWire" /&gt;
     *     &lt;statement&gt;
     *       &lt;variable name="handler"&gt;
     *         &lt;method name="generatedRunInstanceWire"&gt;
     *           &lt;argument-list&gt;...&lt;/argument-list&gt;
     *         &lt;/method&gt;
     *       &lt;/variable&gt;
     *     &lt;/statement&gt;
     *   &lt;/assignment&gt;
     * &lt;/statement&gt;
     * 
     * rw.setName("run");
     * 
     * &lt;statement&gt;
     *   &lt;variable name="rw"&gt;
     *    &lt;method name="insert"&gt;
     *      &lt;argument-list&gt;
     *            &lt;string-argument value="run" /&gt;
     *        &lt;/argument-list&gt;
     *    &lt;/method&gt;
     *   &lt;/variable&gt;
     * &lt;/statement&gt;
     * 
     * insert(rw);
     * 
     * &lt;statement&gt;
     *   &lt;method name="insert"&gt;
     *    &lt;argument-list&gt;
     *        &lt;variable-argument name="rw" /&gt;
     *      &lt;/argument-list&gt;
     *   &lt;/method&gt;
     * &lt;/statement&gt;
	 * </pre>
	 * 
	 * <p><b>NOTE</b> All comments will be stripped out first, so
	 * any string that contains //, /* and # will be destroyed.
	 * There is also no support for nested method calls, or
	 * casting.</p>
	 * 
	 * @param parent
	 * @param java
	 */
	public void parseJava(Document document, Node parent, String java) {
		try {
			// first, lets just destroy all comments
			java = java.replaceAll("//[^\n]*\n", "");
			java = java.replaceAll("/\\*.+\\*/", "");
			java = java.replaceAll("#[^\n]*\n", "");
			
			// parse out all strings
			java = parseOutStrings(java);
			
			// expect that no strings contain ;s or .s
			String[] lines = java.split(";");
			for (String javaLine : lines) {
				javaLine = javaLine.trim();
				if (!javaLine.isEmpty()) {
					Element line = document.createElement("statement");
					parent.appendChild(line);
					
					parseJavaEquals(document, line, javaLine);
				}
			}
		} catch (RuntimeException e) {
			// print out the source code for debugging
			System.err.println(java);
			throw e;		// rethrow
		}
	}
	
	private Map<String,String> parsedOutStrings;
	
	/**
	 * Parse out all strings in the java code.
	 * We assume that this code has no comments in it, and all strings are
	 * ""s, and there are no \"s.
	 * 
	 * @param java
	 * @return
	 */
	private String parseOutStrings(String java) {
		parsedOutStrings = new HashMap<String,String>();

		int i = 0;
		while (java.indexOf("\"") != -1) {
			// find boundaries
			int start = java.indexOf("\"");
			int end = java.indexOf("\"", start + 1);
			if (end == -1)
				throw new RuntimeException("Cannot find the ending string after " + java.substring(start));
			
			// take it out
			String name = "_string" + i++;
			String value = java.substring(start + 1, end);
			parsedOutStrings.put(name, value);
			java = java.replace("\"" + value + "\"", name);
			
		}
		
		return java;
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
			
			String[] elements = equals[0].trim().split("\\s+");
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
				throw new RuntimeException("Found more than one part of the equals left hand side '" + javaLine + "': " + equals[0] + " (found " + elements.length + " elements: " + Arrays.toString(elements) + ")");
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
			if (argument.matches("-?[0-9]+(\\.?[0-9]+)")) {
				// a number
				Element a = document.createElement("number-argument");
				a.setAttribute("value", argument);
				argumentList.appendChild(a);
			} else {
				// a variable or a string
				if (parsedOutStrings.containsKey(argument)) {
					// yes, it was a string
					Element a = document.createElement("string-argument");
					a.setAttribute("value", parsedOutStrings.get(argument));
					argumentList.appendChild(a);
				} else {
					// no, it's actually a variable
					Element a = document.createElement("variable-argument");
					a.setAttribute("name", argument);
					argumentList.appendChild(a);
				}
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
		parseJava(d, root, "// a comment\nRunInstanceWire rw = handler.generatedRunInstanceWire(sw, sw, event, operation);		rw.setName(\"run\");		insert(rw); insert(\"a complicated string. with full stops. and line breaks;\");");
		
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
		DroolsXmlDumper dump = new DroolsXmlDumper();
		Map<String,String> results = dump.getRuleXmls();
	
		Set<LogicRule> allRules = new HashSet<LogicRule>();
		
		boolean foundTestRule = false;
		for (String f : results.keySet()) {
			
			String name = f.substring(f.lastIndexOf("/"));
			IFile out = project.getFile(name + ".xml");
			
			IFile outParsed = project.getFile(name + "-parsed.txt");
			String parsed = "";

			IFile outLatex = project.getFile(name + "-parsed.tex");
			String latex = "";

			if (f.toLowerCase().contains("dynamic-sources")) {
				// TODO we need to allow for if (a...) { b... } syntax
				// OR we need to modify the rule source to not use this 
				// syntax directly
				System.out.println("Skipping drools rule file: " + f);
				continue;
			}
			
			// who knows what format XmlDump is supplied in?
			// we will assume UTF-8 as the dumped XML is UTF-8
			InputStream source = new ByteArrayInputStream(results.get(f).getBytes("UTF-8")); 

			System.out.println("Writing " + out + "...");
			
			// load the created XML and replace the <rhs> with 
			// more XML (specific to our use in IAML)
			
			Document document = loadDocument(source);
			IterableElementList rhsList = xpath(document, "//rhs");
			
			for (Element rhs : rhsList) {
				Text originalNode = (Text) rhs.getFirstChild();
				String sourceCode = originalNode.getData();
				originalNode.setData("");	// empty the node
				
				Element t = document.createElement("source");
				originalNode.getParentNode().appendChild(t);
				Text t2 = document.createTextNode(sourceCode);
				t.appendChild(t2);
				
				// lets create the statements here
				parseJava(document, originalNode.getParentNode(), sourceCode);
				
				// find the rule node
				Element ruleNode = (Element) rhs.getParentNode();
				assertEquals(ruleNode.getNodeName(), "rule");
				
				/**
				 * Our new paper wants to generate the factory graph/k-graph
				 * of the rule. Here we make sure that it parses ok.
				 */
				if (ruleNode.getAttribute("name").equals("Create empty domain store")) {
					myTestSampleRule(ruleNode);
					foundTestRule = true;
				}
				
				// parse all the rules
				try {
					List<InferredTerm> parsedResults = parseSampleRule(ruleNode);
					for (InferredTerm s : parsedResults) {
						parsed += s.toString() + "\n";
						latex += "  \\item $" + s.toLatex() + "$\n";
					}
				} catch (TermParseException e) {
					// write document anyway
					// out.create(source, true, monitor);
					saveDocument(document, out.getLocation().toFile());
					outParsed.create(new StringBufferInputStream(parsed), 
							true, monitor);
					outLatex.create(new StringBufferInputStream(latex), 
							true, monitor);
					
					throw new TermParseException("While trying to parse '" + f + "'...: " + e.getMessage(), e);
				}
			}
			
			// work out the logic formula defined by each rule
			List<LogicRule> rules = investigateLogic(document, name);
			allRules.addAll(rules);
			
			// out.create(source, true, monitor);
			saveDocument(document, out.getLocation().toFile());

			// write out parsed rules
			System.out.println("Writing to '" + outParsed + "'...");
			outParsed.create(new StringBufferInputStream(parsed), 
					true, monitor);
			// write out parsed rules
			System.out.println("Writing to '" + outLatex + "'...");
			outLatex.create(new StringBufferInputStream(latex), 
					true, monitor);
			
		}

		assertTrue("Never found and parsed the test rule", foundTestRule);

		// output out the rules in a prolog format
		outputPrologStratification(allRules);
		
		// check each rule for composition
		checkRulesCompositionGraph(allRules);
		
		// find rule cycles
		findStratCycleRules(allRules);
		
		refreshProject();
	}
	
	/**
	 * Test the parsing for factory graph/k-graph of the given rule
	 * "Create empty domain store".
	 * 
	 * @param ruleNode
	 * @throws TermParseException 
	 */
	private void myTestSampleRule(Element ruleNode) throws TermParseException {
		// sanity checks
		assertEquals(ruleNode.getNodeName(), "rule");
		assertEquals(ruleNode.getAttribute("name"), "Create empty domain store");
		
		String[] result = convertToStrings(parseSampleRule(ruleNode));
		try {
			assertEquals(2, result.length);
			// we need to use regexps, because the variable names may have been changed since
			// (or we could set these ourselves)
			String r2 = "L[0-9]+\\(a\\) <- DomainStore\\(x[0-9]*\\), name\\(x[0-9]*, \"test domain store\"\\), containedIn\\(x[0-9]*, a\\)";
			assertStringMatches(r2, result[0]);
			String r1 = "DomainStore\\(f\\(a, 0\\)\\), generatedBy\\(f\\(a, 0\\), a\\), containedIn\\(f\\(a, 0\\), a\\), name\\(f\\(a, 0\\), \"test domain store\"\\) <- InternetApplication\\(a\\), name\\(a, \"test\"\\), not\\(L[0-9]+\\(a\\)\\)";
			assertStringMatches(r1, result[1]);
		} catch (AssertionFailedError e) {
			// print out the contents of the list
			for (String r : result) {
				System.err.println("> " + r);
			}
			throw e;
		}
	}

	/**
	 * Convert a list of rule elements to an array of strings.
	 * 
	 * @param t
	 * @return
	 */
	private String[] convertToStrings(List<InferredTerm> r) {
		String[] s = new String[r.size()];
		for (int i = 0; i < r.size(); i++) {
			s[i] = r.get(i).toString();
		}
		return s;
	}

	/**
	 * The given string should match the given 'expected' regexp
	 * 
	 * @param expected expected regular expression to match
	 * @param string the string to consider
	 */
	public void assertStringMatches(String expected, String string) {
		assertTrue("Did not match regular expression '" + expected + "': '" + string + "'", string.matches(expected));
	}

	/**
	 * Parse the rule to get a set of logic-style outputs.
	 * 
	 * TODO actually implement
	 * 
	 * @param ruleNode
	 * @return
	 * @throws TermParseException 
	 */
	private List<InferredTerm> parseSampleRule(Element ruleNode) throws TermParseException {
		List<InferredTerm> terms = parseInferredTerms(ruleNode);
		
		// now, remove <not exists> from the list of terms
		List<InferredTerm> resolved = removeNotExists(terms);
		
		return resolved;
	}
	
	public class TermParseException extends Exception {

		private static final long serialVersionUID = 1L;
		
		public TermParseException(String message) {
			super(message);
		}
		public TermParseException(String message, Throwable e) {
			super(message, e);
		}
		public TermParseException(Throwable e) {
			super(e.getMessage(), e);
		}
		
	}
	
	/**
	 * From an element, create InferredTerms.
	 * 
	 * @param ruleNode
	 * @return
	 * @throws TermParseException 
	 */
	private List<InferredTerm> parseInferredTerms(Element ruleNode) throws TermParseException {
		List<InferredTerm> terms = new ArrayList<InferredTerm>();
		
		if (!ruleNode.getNodeName().equals("rule")) {
			throw new TermParseException("Root rule node name must be 'rule', found '" + ruleNode.getNodeName() + "'");
		}
		
		InferredTerm t = new InferredTerm();
		// if there are multiple <rhs><lhs> (unexpected), they will be merged together
		for (Element e : new IterableElementList(ruleNode.getChildNodes())) {
			if (e.getNodeName().equals("rhs")) {
				// head of the rule
				parseNodeSide(e, t.getHead());
			} else if (e.getNodeName().equals("lhs")) {
				// body of the rule
				parseNodeSide(e, t.getBody());
			} else {
				throw new TermParseException("Unexpected child of <rule> node: " + e);
			}
		}
		// ignore empty terms
		if (!t.isEmpty()) {
			terms.add(t);
		}
		
		return terms;
	}
	
	private class FactoryIndexTracker {
		private int current = 0;
		
		public int newIndex() {
			return current++;
		}
	}
	
	private void parseNodeSide(Element e, Set<Function> head) throws TermParseException {
		try {
			parseNodeSide(e, head, null, new HashMap<String, FactoryFunction>(), new FactoryIndexTracker());
		} catch (XPathExpressionException e1) {
			throw new TermParseException(e1);
		}
	}
	
	/**
	 * Parse the given element <rhs> or <lhs> into the given
	 * function list.
	 * 
	 * @param e
	 * @param head
	 * @param tracker keeps track of indexes for f(x, N)
	 * @throws TermParseException 
	 * @throws XPathExpressionException 
	 */
	private void parseNodeSide(Element e, Set<Function> head, Variable currentVariable,
			Map<String, FactoryFunction> factoryFunctionMap, FactoryIndexTracker tracker) throws TermParseException, XPathExpressionException {
		// this element
		if (e.getNodeName().equals("pattern")) {
			String identifier = e.getAttribute("identifier");
			String type = e.getAttribute("object-type");
			NormalVariable v = new NormalVariable(identifier);
			Function f = new SingleFunction(type, v);
			head.add(f);
			currentVariable = v;		// save the current variable
		}
		
		if (e.getNodeName().equals("from")) {
			Element expression = xpathFirst(e, "expression");
			String expr = getTextInNode(expression);
			if (expr == null)
				throw new TermParseException("Did not expect null expression for <from>: " + expression);
			if (expr.isEmpty())
				throw new TermParseException("Did not expect empty expression for <from>: " + expression);
			
			// split up on '.'
			String[] bits = expr.trim().split("\\.");
			if (bits.length != 2) {
				throw new TermParseException("Expected exactly 'a.b' format from <from> expression, found: '" + expr + "'");
			}
			// the first part will be a variable
			NormalVariable fromVar = new NormalVariable(bits[0]);
			DoubleFunction f = new DoubleFunction("containedIn", currentVariable, fromVar);
			head.add(f);
		}
		
		if (e.getNodeName().equals("or-constraint-connective")) {
			// wrap in an <or>
			// first, get the contents
			Set<Function> foundTerms = new LinkedHashSet<Function>();
			for (Element e2 : new IterableElementList(e.getChildNodes())) {
				parseNodeSide(e2, foundTerms, currentVariable, factoryFunctionMap, tracker);
			}
			if (foundTerms.isEmpty()) {
				throw new TermParseException("Parsed an <or> but did not expect no elements contained within");
			}
			// now add a new <or>
			Or or = new Or();
			or.addAll(foundTerms);
			head.add(or);
			// don't re-parse contents
			return;
		}
		
		if (e.getNodeName().equals("field-constraint")) {
			String fieldName = e.getAttribute("field-name");
			// we want to find out what the comparison is
			IterableElementList literalRestrictions = new IterableElementList(e.getElementsByTagName("literal-restriction"));
			IterableElementList variableRestrictions = new IterableElementList(e.getElementsByTagName("variable-restriction"));
			IterableElementList quantRestrictions = new IterableElementList(e.getElementsByTagName("qualified-identifier-restriction"));
			if (!literalRestrictions.isEmpty()) {
				if (literalRestrictions.size() != 1) {
					throw new TermParseException("Did not expect more than 1 literal restriction, found: " + literalRestrictions);
				}
				Element lit = (Element) literalRestrictions.item(0);
				String evaluator = lit.getAttribute("evaluator");
				String value = lit.getAttribute("value");
				StringLiteral literalValue = new StringLiteral(value);
				if (evaluator.equals("==") || evaluator.equals("!=")) {
					Function f = new DoubleFunction(fieldName, currentVariable, literalValue);
					if (evaluator.equals("!=")) {
						// wrap it around a not
						Not nf = new Not();
						nf.add(f);
						f = nf;
					}
					head.add(f);
				} else {
					throw new TermParseException("Expected evaluator to be '==' or '!=' but was: " + evaluator);
				}
			} else if (!variableRestrictions.isEmpty()) {
				if (variableRestrictions.size() != 1) {
					throw new TermParseException("Did not expect more than 1 variable restriction, found: " + variableRestrictions);
				}
				Element var = (Element) variableRestrictions.item(0);
				String evaluator = var.getAttribute("evaluator");
				String identifier = var.getAttribute("identifier");
				if (identifier.isEmpty()) {
					throw new TermParseException("Unexpected empty identifier for " + var);
				}
				Variable comparedTo = new NormalVariable(identifier);
				if (evaluator.equals("==") || evaluator.equals("!=")) {
					Function f;
					if (fieldName.equals("eContainer")) {
						f = new DoubleFunction("containedIn", currentVariable, comparedTo);
					} else {
						f = new DoubleFunction(fieldName, currentVariable, comparedTo);
					}
					if (evaluator.equals("!=")) {
						// wrap it around a not
						Not nf = new Not();
						nf.add(f);
						f = nf;
					}
					head.add(f);
				} else {
					throw new TermParseException("Expected evaluator to be '==' or '!=' but was: " + evaluator);
				}
			} else if (!quantRestrictions.isEmpty()) {
				if (quantRestrictions.size() != 1) {
					throw new TermParseException("Did not expect more than 1 quantified identifier restriction, found: " + quantRestrictions);
				}
				Element quant = (Element) quantRestrictions.item(0);
				String evaluator = quant.getAttribute("evaluator");
				String identifier = getTextInNode(quant);
				if (identifier.isEmpty()) {
					throw new TermParseException("Unexpected empty string while parsing qualified identifier restriction " + quant);
				}
				
				if (!evaluator.equals("==")) {
					throw new TermParseException("Expected evaluator to be '==' but was: " + evaluator);
				}
				
				// identifier = 'o.eContainer'
				// fieldName = 'eContainer'
				
				// we want this to first create a rule "containedIn(o, z)" (z is new)
				// and then connect this to fieldName "containedIn(this, z)"
				String[] bits = identifier.trim().split("\\.");
				if (bits.length != 2) {
					throw new TermParseException("Expected two components ('a.b') in quantified expression, found: " + bits);
				}
				if (bits[0].isEmpty()) {
					throw new TermParseException("Unexpected empty identifier for " + bits[0]);
				}
				if (bits[1].isEmpty()) {
					throw new TermParseException("Unexpected empty field for " + bits[1]);
				}
				
				Variable newVariable = new NormalVariable(newIdentifier());
				Variable from = new NormalVariable(bits[0]);	// o
				Function f;	 // new function to create
				if (bits[1].equals("eContainer")) {
					f = new DoubleFunction("containedIn", from, newVariable);
				} else {
					f = new DoubleFunction(bits[1], from, newVariable);
				}
				head.add(f);		// add rule
				
				// now connect the rest
				Function f2;
				if (fieldName.equals("eContainer")) {
					f2 = new DoubleFunction("containedIn", currentVariable, newVariable);
				} else {
					f2 = new DoubleFunction(bits[1], currentVariable, newVariable);
				}
				head.add(f2);
			} else {
				throw new TermParseException("Did not find any restrictions for field-constant '" + fieldName + "': " + e);
			}
		}
		
		if (e.getNodeName().equals("not")) {
			// find the first <pattern>
			IterableElementList patterns = new IterableElementList(e.getElementsByTagName("pattern"));
			if (!patterns.isEmpty()) {
				if (patterns.size() != 1) {
					throw new TermParseException("Did not expect more than 1 pattern in <not>, found: " + patterns);
				}
				Element pattern = patterns.item(0);
				String identifier = pattern.getAttribute("identifier");
				String type = pattern.getAttribute("object-type");
				if (identifier.isEmpty())
					identifier = newIdentifier();	// x, x0, x1, ... 

				NormalVariable v = new NormalVariable(identifier);
				Function f = new SingleFunction(type, v);
				
				NotExists ne = new NotExists(v);
				ne.add(f);
				head.add(ne); 
				// add additional constraints
				for (Element ee : new IterableElementList(pattern.getChildNodes())) {
					parseNodeSide(ee, ne.getContents(), v, factoryFunctionMap, tracker);
				}
			} else {
				throw new TermParseException("Expected a <not> term to contain at least one <pattern>: " + e);
			}
			// don't parse children
			return;
		}
		
		if (e.getNodeName().equals("statement")) {
			// is this a handler.generatedXXX?
			IterableElementList generated = xpath(e, "assignment[set-variable]/statement/variable[@name='handler']/method[contains(@name, 'generated')]");
			System.out.println("generated = " + generated);
			if (!generated.isEmpty()) {
				if (generated.size() != 1) {
					throw new TermParseException("Did not expect more than one generated method, found: " + generated);
				}
				Element generateMethod = generated.item(0);
				// we found it!
				// find the type of the assignment
				Element setVariable = xpathFirst(e, "assignment/set-variable");
				String type = setVariable.getAttribute("type");
				String variableName = setVariable.getAttribute("name");
				
				// how many arguments do we have?
				IterableElementList args = xpath(generateMethod, "argument-list/variable-argument");
				
				FactoryFunction gfunction;
				if (args.size() == 2 || args.size() == 4) {
					// first 2 arguments are generated by/contained in
					String generatedBy = args.item(0).getAttribute("name");
					String containedIn = args.item(1).getAttribute("name");
					
					// DomainStore(f(a))
					NormalVariable vGeneratedBy = new NormalVariable(generatedBy);
					gfunction = new FactoryFunction(vGeneratedBy, tracker.newIndex());
					SingleFunction typeCreation = new SingleFunction(type, gfunction);
					head.add(typeCreation);
					
					// generatedBy(f(a))
					DoubleFunction gbyf = new DoubleFunction("generatedBy", gfunction, vGeneratedBy);
					head.add(gbyf);
					
					// containedIn(f(a), a)
					NormalVariable containedInV = new NormalVariable(containedIn);
					DoubleFunction cinf = new DoubleFunction("containedIn", gfunction, containedInV);
					head.add(cinf);
					
					// add this function to the map
					factoryFunctionMap.put(variableName, gfunction);
				} else {
					throw new TermParseException("Expected 2 or 4 arguments for 'generated' method, found " + args.size() + ": " + args);
				}
				
				// linked arguments?
				if (args.size() == 4) {
					// next 2 arguments are linked from/to
					String linkFrom = args.item(2).getAttribute("name");
					String linkTo = args.item(3).getAttribute("name");
					
					// linkedFrom(f(a), x)
					NormalVariable varFrom = new NormalVariable(linkFrom);
					DoubleFunction f1 = new DoubleFunction("linkedFrom", gfunction, varFrom);
					head.add(f1);
					
					// linkedTo(f(a), y)
					NormalVariable varTo = new NormalVariable(linkTo);
					DoubleFunction f2 = new DoubleFunction("linkedTo", gfunction, varTo);
					head.add(f2);					
				}
				
				// don't parse children
				return;
			}
		}
		
		if (e.getNodeName().equals("statement")) {
			// does this have a variable?
			IterableElementList variables = xpath(e, "variable");
			if (variables.size() == 1) {
				Element variable = variables.item(0);
				String varName = variable.getAttribute("name");
				
				// is this in the mapping of generator functions?
				Variable resolved;
				if (factoryFunctionMap.containsKey(varName)) {
					resolved = factoryFunctionMap.get(varName);
				} else {
					// its a different variable
					resolved = new NormalVariable(varName);
				}
				
				currentVariable = resolved;

			} else if (variables.size() > 1) {
				throw new TermParseException("Did not expect multiple variables for <statement>; found: " + variables);
			}
		}

		if (e.getNodeName().equals("method")) {
			// we should be operating on a current variable
			String methodName = e.getAttribute("name");
			if (methodName.equals("setName")) {
				// there should be one argument
				Element arg = xpathFirst(e, "argument-list/string-argument");
				Variable resArg;
				if (arg.getNodeName().equals("string-argument")) {
					resArg = new StringLiteral(arg.getAttribute("value"));
				} else {
					throw new TermParseException("Not sure what to do with an argument of type " + arg);
				}
				// name(f(a), 'foo')
				DoubleFunction f = new DoubleFunction("name", currentVariable, resArg);
				head.add(f);
			} else if (methodName.equals("insert")) {
				// ignore insert and do not process children
				return;
			}
		}
		
		// then children
		for (Element inside : new IterableElementList(e.getChildNodes())) {
			parseNodeSide(inside, head, currentVariable, factoryFunctionMap, tracker);
		}
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
	 * Test the inferred terms parsing directly.
	 * 
	 * @return
	 */
	public void testParseInferredTerm() throws Exception {
		Document d = loadInlineDocument("<?xml version=\"1.0\"?><rule><lhs><pattern identifier=\"a\" object-type=\"InternetApplication\"><field-constraint field-name=\"name\"><literal-restriction evaluator=\"==\" value=\"test\" /></field-constraint></pattern></lhs></rule>");
		Element root = (Element) d.getElementsByTagName("rule").item(0);
		assertEquals(root.getNodeName(), "rule");
		
		List<InferredTerm> list = parseInferredTerms(root);
		assertEquals(1, list.size());
		assertEquals("<- InternetApplication(a), name(a, \"test\")", list.get(0).toString());
	}

	/**
	 * Test the inferred terms parsing directly.
	 * 
	 * @return
	 */
	public void testParseInferredTermFrom() throws Exception {
		Document d = loadInlineDocument("<?xml version=\"1.0\"?><rule><lhs><pattern identifier=\"a\" object-type=\"InternetApplication\"><field-constraint field-name=\"name\"><literal-restriction evaluator=\"==\" value=\"test\" /></field-constraint><from><expression>b.domainStores</expression></from></pattern></lhs></rule>");
		Element root = (Element) d.getElementsByTagName("rule").item(0);
		assertEquals(root.getNodeName(), "rule");
		
		List<InferredTerm> list = parseInferredTerms(root);
		assertEquals(1, list.size());
		assertEquals("<- InternetApplication(a), name(a, \"test\"), containedIn(a, b)", list.get(0).toString());
	}

	/**
	 * Test the inferred terms parsing directly.
	 * 
	 * This rule might look like this:
	 * 
	 * <pre>rule "foo"
	 *   when
	 *     b : DomainStore ( )
	 *     a : InternetApplication ( name == "test", eContainer = o.eContainer )</pre>
	 * 
	 * @return
	 */
	public void testParseInferredTermQuantifiedIdentifierRestriction() throws Exception {
		Document d = loadInlineDocument("<?xml version=\"1.0\"?><rule><lhs><pattern identifier=\"b\" object-type=\"DomainStore\"></pattern><pattern identifier=\"a\" object-type=\"InternetApplication\"><field-constraint field-name=\"name\"><literal-restriction evaluator=\"==\" value=\"test\" /></field-constraint><field-constraint field-name=\"eContainer\"><qualified-identifier-restriction evaluator=\"==\">o.eContainer</qualified-identifier-restriction></field-constraint></pattern></lhs></rule>");
		Element root = (Element) d.getElementsByTagName("rule").item(0);
		assertEquals(root.getNodeName(), "rule");
		
		List<InferredTerm> list = parseInferredTerms(root);
		assertEquals(1, list.size());
		assertEquals("<- DomainStore(b), InternetApplication(a), name(a, \"test\"), containedIn(o, x), containedIn(a, x)", list.get(0).toString());
	}

	/**
	 * Test the inferred terms parsing directly.
	 * 
	 * @return
	 */
	public void testParseInferredTermNotExists() throws Exception {
		Document d = loadInlineDocument("<?xml version=\"1.0\"?><rule><lhs><not><pattern object-type=\"InternetApplication\"><field-constraint field-name=\"name\"><literal-restriction evaluator=\"==\" value=\"test\" /></field-constraint></pattern></not></lhs></rule>");
		Element root = (Element) d.getElementsByTagName("rule").item(0);
		assertEquals(root.getNodeName(), "rule");
		
		List<InferredTerm> list = parseInferredTerms(root);
		assertEquals(1, list.size());
		assertEquals("<- notExists(x : InternetApplication(x), name(x, \"test\"))", list.get(0).toString());
	}
	
	/**
	 * Test the inferred terms parsing directly.
	 * 
	 * @return
	 */
	public void testParseInferredTermRhs() throws Exception {
		Document d = loadInlineDocument("<?xml version=\"1.0\"?><rule><rhs><source>kittens</source><statement><assignment><set-variable name=\"ds\" type=\"DomainStore\" /><statement><variable name=\"handler\"><method name=\"generatedDomainStore\"><argument-list><variable-argument name=\"a\" /><variable-argument name=\"a\" /></argument-list></method></variable></statement></assignment></statement></rhs></rule>");
		Element root = (Element) d.getElementsByTagName("rule").item(0);
		assertEquals(root.getNodeName(), "rule");
		
		List<InferredTerm> list = parseInferredTerms(root);
		assertEquals(1, list.size());
		assertEquals("DomainStore(f(a, 0)), generatedBy(f(a, 0), a), containedIn(f(a, 0), a) <-", list.get(0).toString());
	}
	
	/**
	 * Test the inferred terms parsing directly.
	 * 
	 * @return
	 */
	public void testParseInferredTermRhs2() throws Exception {
		Document d = loadInlineDocument("<?xml version=\"1.0\"?><rule><rhs><source>kittens</source><statement><assignment><set-variable name=\"ds\" type=\"DomainStore\" /><statement><variable name=\"handler\"><method name=\"generatedDomainStore\"><argument-list><variable-argument name=\"a\" /><variable-argument name=\"a\" /></argument-list></method></variable></statement></assignment></statement><statement><variable name=\"ds\"><method name=\"setName\"><argument-list><string-argument value=\"test domain store\" /></argument-list></method></variable></statement></rhs></rule>");
		Element root = (Element) d.getElementsByTagName("rule").item(0);
		assertEquals(root.getNodeName(), "rule");
		
		List<InferredTerm> list = parseInferredTerms(root);
		assertEquals(1, list.size());
		assertEquals("DomainStore(f(a, 0)), generatedBy(f(a, 0), a), containedIn(f(a, 0), a), name(f(a, 0), \"test domain store\") <-", list.get(0).toString());
	}

	/**
	 * Test the inferred terms parsing directly.
	 * 
	 * @return
	 */
	public void testParseInferredTermRhsIgnoresInsert() throws Exception {
		Document d = loadInlineDocument("<?xml version=\"1.0\"?><rule><rhs><source>kittens</source><statement><assignment><set-variable name=\"ds\" type=\"DomainStore\" /><statement><variable name=\"handler\"><method name=\"generatedDomainStore\"><argument-list><variable-argument name=\"a\" /><variable-argument name=\"a\" /></argument-list></method></variable></statement></assignment></statement><statement><variable name=\"ds\"><method name=\"setName\"><argument-list><string-argument value=\"test domain store\" /></argument-list></method></variable></statement><statement><method name=\"insert\"><argument-list><variable-argument name=\"ds\" /></argument-list></method></statement></rhs></rule>");
		Element root = (Element) d.getElementsByTagName("rule").item(0);
		assertEquals(root.getNodeName(), "rule");
		
		List<InferredTerm> list = parseInferredTerms(root);
		assertEquals(1, list.size());
		assertEquals("DomainStore(f(a, 0)), generatedBy(f(a, 0), a), containedIn(f(a, 0), a), name(f(a, 0), \"test domain store\") <-", list.get(0).toString());
	}

	public void testRemovingNotExists() throws Exception {
		Document d = loadInlineDocument("<?xml version=\"1.0\"?><rule><lhs><not><pattern object-type=\"InternetApplication\"><field-constraint field-name=\"name\"><literal-restriction evaluator=\"==\" value=\"test\" /></field-constraint></pattern></not></lhs></rule>");
		Element root = (Element) d.getElementsByTagName("rule").item(0);
		assertEquals(root.getNodeName(), "rule");
		
		List<InferredTerm> list = parseInferredTerms(root);
		assertEquals(1, list.size());
		assertEquals("<- notExists(x : InternetApplication(x), name(x, \"test\"))", list.get(0).toString());
		
		// when we remove not exists, we should get two results
		List<InferredTerm> resolved = removeNotExists(list);
		assertEquals(2, resolved.size());
		assertEquals("L0 <- InternetApplication(x), name(x, \"test\")", resolved.get(0).toString());
		assertEquals("<- not(L0)", resolved.get(1).toString());

	}

	public void testRemovingNotExistsWithBindings() throws Exception {
		Document d = loadInlineDocument("<?xml version=\"1.0\"?><rule><lhs><not><pattern object-type=\"InternetApplication\"><field-constraint field-name=\"name\"><variable-restriction evaluator=\"==\" identifier=\"a\" /></field-constraint></pattern></not></lhs></rule>");
		Element root = (Element) d.getElementsByTagName("rule").item(0);
		assertEquals(root.getNodeName(), "rule");
		
		List<InferredTerm> list = parseInferredTerms(root);
		assertEquals(1, list.size());
		assertEquals("<- notExists(x : InternetApplication(x), name(x, a))", list.get(0).toString());
		
		// when we remove not exists, we should get two results
		List<InferredTerm> resolved = removeNotExists(list);
		assertEquals(2, resolved.size());
		assertEquals("L0(a) <- InternetApplication(x), name(x, a)", resolved.get(0).toString());
		assertEquals("<- not(L0(a))", resolved.get(1).toString());

	}

	public void testRemovingNotExistsWithTypedBindings() throws Exception {
		Document d = loadInlineDocument("<?xml version=\"1.0\"?><rule><lhs><pattern identifier=\"a\" object-type=\"InternetApplication\"><field-constraint field-name=\"name\"><literal-restriction evaluator=\"==\" value=\"test\" /></field-constraint></pattern><not><pattern object-type=\"InternetApplication\"><field-constraint field-name=\"name\"><variable-restriction evaluator=\"==\" identifier=\"a\" /></field-constraint></pattern></not></lhs></rule>");
		Element root = (Element) d.getElementsByTagName("rule").item(0);
		assertEquals(root.getNodeName(), "rule");
		
		List<InferredTerm> list = parseInferredTerms(root);
		assertEquals(1, list.size());
		assertEquals("<- InternetApplication(a), name(a, \"test\"), notExists(x : InternetApplication(x), name(x, a))", list.get(0).toString());
		
		// when we remove not exists, we should get two results
		List<InferredTerm> resolved = removeNotExists(list);
		assertEquals(2, resolved.size());
		assertEquals("L0(a) <- InternetApplication(x), name(x, a)", resolved.get(0).toString());
		assertEquals("<- InternetApplication(a), name(a, \"test\"), not(L0(a))", resolved.get(1).toString());

	}

	/**
	 * Test the inferred terms parsing directly.
	 * 
	 * @return
	 */
	public void testParseDumpDrools1Xml() throws Exception {
		Document d = loadDocument( ROOT + "inference/DumpDrools1.xml" );
		Element root = (Element) d.getElementsByTagName("rule").item(0);
		assertEquals(root.getNodeName(), "rule");
		
		List<InferredTerm> list = parseInferredTerms(root);
		assertEquals(1, list.size());
		assertEquals("<- InputTextField(f), overridden(f, \"false\"), notExists(x : Operation(x), containedIn(x, f), name(x, \"update\"))", list.get(0).toString());
	}

	/**
	 * Test the inferred terms parsing directly.
	 * This one has 'or()' in it.
	 * 
	 * @return
	 */
	public void testParseDumpDrools2Xml() throws Exception {
		Document d = loadDocument( ROOT + "inference/DumpDrools2.xml" );
		Element root = (Element) d.getElementsByTagName("rule").item(0);
		assertEquals(root.getNodeName(), "rule");
		
		List<InferredTerm> list = parseInferredTerms(root);
		assertEquals(1, list.size());
		assertEquals("<- CompositeOperation(o), overridden(o, \"false\"), or(name(o, \"update\"), name(o, \"refresh\"), name(o, \"init\")), ApplicationElementProperty(field), containedIn(o, x), containedIn(field, x), name(field, \"fieldValue\")", list.get(0).toString());
	}

	/**
	 * Test the inferred terms parsing directly.
	 * This one has multiple element creations from the same elements.
	 * 
	 * @return
	 */
	public void testParseDumpDrools3Xml() throws Exception {
		Document d = loadDocument( ROOT + "inference/DumpDrools3.xml" );
		Element root = (Element) d.getElementsByTagName("rule").item(0);
		assertEquals(root.getNodeName(), "rule");
		
		List<InferredTerm> list = parseInferredTerms(root);
		assertEquals(1, list.size());
		assertEquals("Parameter(f(o, 0)), generatedBy(f(o, 0), o), containedIn(f(o, 0), o), name(f(o, 0), \"setValueTo\"), " + 
				"Parameter(f(o, 1)), generatedBy(f(o, 1), o), containedIn(f(o, 1), o), name(f(o, 1), \"setValueTo\"), " +
				"Parameter(f(o, 2)), generatedBy(f(o, 2), o), containedIn(f(o, 2), o), name(f(o, 2), \"setValueTo\"), " +
				"Parameter(f(o, 3)), generatedBy(f(o, 3), o), containedIn(f(o, 3), o), name(f(o, 3), \"setValueTo\") " +
				"<- CompositeOperation(o), overridden(o, \"false\"), or(name(o, \"update\"), name(o, \"refresh\"), name(o, \"init\")), ApplicationElementProperty(field), containedIn(o, x), containedIn(field, x), name(field, \"fieldValue\")", list.get(0).toString());
	}

	/**
	 * Add any variables used in function 'f' that are not 'compare'
	 * into the set 'otherVariables'
	 *
	 * @param compare variable to compare with
	 * @param f function to iterate through
	 * @param otherVariables any variables in 'f' that are not 'compare' are added to here
	 * @throws TermParseException 
	 */
	protected void parseOutOtherVariables(FunctionTerm compare, Function f, Set<FunctionTerm> otherVariables) throws TermParseException {
		if (f instanceof SingleFunction) {
			FunctionTerm c = ((SingleFunction) f).getFunctionTerm();							
			if (c instanceof NormalVariable && !compare.equals(c)) {
				// a different variable
				otherVariables.add(c);
			}
		} else if (f instanceof DoubleFunction) {
			FunctionTerm c1 = ((DoubleFunction) f).getFunctionTerm1();
			FunctionTerm c2 = ((DoubleFunction) f).getFunctionTerm2();
			if (c1 instanceof NormalVariable && !compare.equals(c1)) {
				// a different variable
				otherVariables.add(c1);
			}
			if (c2 instanceof NormalVariable && !compare.equals(c2)) {
				// a different variable
				otherVariables.add(c2);
			}
		} else if (f instanceof Not) {
			// iterate over contents
			for (Function e : ((Not) f).getContents()) {
				parseOutOtherVariables(compare, e, otherVariables);
			}
		} else {
			throw new TermParseException("Unexpected function type in parseOutOtherVariables: " + f);
		}
	}
	
	/**
	 * Resolve any terms of 'notExists(x : foo(x))' in the body of rules
	 * by adding another term 'L1(...) <- foo(x)', and replacing the 
	 * original term with 'not(L1)'
	 * 
	 * @param list
	 * @return
	 * @throws TermParseException 
	 */
	protected List<InferredTerm> removeNotExists(List<InferredTerm> list) throws TermParseException {
		List<InferredTerm> result = new ArrayList<InferredTerm>();
		
		for (InferredTerm term : list) {
			// copy head over without change
			InferredTerm nt = new InferredTerm();
			for (Function a : term.getHead()) {
				nt.getHead().add(a);
			}
			// copy over body except for NotExists
			for (Function a : term.getBody()) {
				// is this a NotExists?
				if (a instanceof NotExists) {
					NotExists ne = (NotExists) a;
					// how many other variables are used in this NotExists?
					Set<FunctionTerm> otherVariables = new LinkedHashSet<FunctionTerm>();
					for (Function b : ne.getContents()) {
						parseOutOtherVariables(ne.getFunctionTerm(), b, otherVariables);
					}
					// create a new function L1 for these other variables
					String functionName = newExistsIdentifier();
					VariableFunction lf = new VariableFunction(functionName, otherVariables);
					// wrap it with a not
					Not not = new Not();
					not.add(lf);
					// use L1 instead of notExists(...)
					nt.getBody().add(not);
					
					// add this L1 definition to the result
					InferredTerm ldef = new InferredTerm();
					ldef.getHead().add(lf);
					for (Function b : ne.getContents()) {
						ldef.getBody().add(b);
					}
					result.add(ldef);
				} else {
					// add as normal
					nt.getBody().add(a);
				}
			}
			result.add(nt);
		}
		
		return result;
	}

	public interface Latexable {
		/**
		 * Compile the term to a Latex format
		 */
		public String toLatex();
	}
	public interface FunctionTerm extends Latexable {

	}
	public interface Function extends Latexable {
		
	}
	public interface Variable extends FunctionTerm, Latexable {

	}
	
	public abstract class LazyEquals {
		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			return toString().equals(obj.toString());
		}
		@Override
		public int hashCode() {
			return toString().hashCode();
		}
	}

	public class InferredTerm extends LazyEquals implements Latexable {

		private Set<Function> head = new LinkedHashSet<Function>();
		private Set<Function> body = new LinkedHashSet<Function>();
		
		public Set<Function> getHead() {
			return head;
		}
		public boolean isEmpty() {
			return head.isEmpty() && body.isEmpty();
		}
		public Set<Function> getBody() {
			return body;
		}
		
		public String toString() {
			if (head.isEmpty())
				if (body.isEmpty())
					return "";
				else
					return "<- " + DumpDroolsXml.toString(body);
			else
				if (body.isEmpty())
					return DumpDroolsXml.toString(head) + " <-";
				else
					return DumpDroolsXml.toString(head) + " <- " + DumpDroolsXml.toString(body);
		}
		
		/**
		 * Compile the term to a Latex format
		 */
		public String toLatex() {
			return DumpDroolsXml.toLatexAnd(head) + " \\leftarrow " + DumpDroolsXml.toLatexAnd(body);
		}

	}

	/**
	 * Create the format string.
	 * @param head2
	 * @return
	 */
	public static String toString(Set<?> variables) {
		boolean isFirst = true;
		String result = "";
		for (Object f : variables) {
			if (!isFirst)
				result += ", ";
			result += f.toString();
			isFirst = false;
		}
		return result;
	}

	/**
	 * Create the format string, in Latex.
	 * Separate terms with \wedge.
	 * 
	 * @param head2
	 * @return
	 */
	public static String toLatexAnd(Set<? extends Latexable> variables) {
		boolean isFirst = true;
		String result = "";
		for (Latexable f : variables) {
			if (!isFirst)
				result += " \\wedge ";
			result += f.toLatex();
			isFirst = false;
		}
		return result;
	}
	
	/**
	 * Create the format string, in Latex.
	 * Separate terms with \vee.
	 * 
	 * @param head2
	 * @return
	 */
	public static String toLatexOr(Set<? extends Latexable> variables) {
		boolean isFirst = true;
		String result = "";
		for (Latexable f : variables) {
			if (!isFirst)
				result += " \\vee ";
			result += f.toLatex();
			isFirst = false;
		}
		return result;
	}
	
	/**
	 * Create the format string, in Latex.
	 * Separate terms with ','.
	 * 
	 * @param head2
	 * @return
	 */
	public static String toLatexComma(Set<? extends Latexable> variables) {
		boolean isFirst = true;
		String result = "";
		for (Latexable f : variables) {
			if (!isFirst)
				result += ", ";
			result += f.toLatex();
			isFirst = false;
		}
		return result;
	}
	
	/**
	 * 'name(variable)'
	 */
	public class SingleFunction extends LazyEquals implements Function {

		private String name;
		private FunctionTerm variable;
		public SingleFunction(String string, FunctionTerm variable) {
			this.name = string;
			this.variable = variable;
		}
		
		public FunctionTerm getFunctionTerm() {
			return variable;
		}

		public String toString() {
			return name + "(" + variable + ")";
		}

		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.inference.DumpDroolsXml.Latexable#toLatex()
		 */
		@Override
		public String toLatex() {
			return escapeLatex(name) + "(" + variable.toLatex() + ")";
		}

		public String getName() {
			return name;
		}

		public FunctionTerm getVariable() {
			return variable;
		}
	}
	
	/**
	 * 'not(...)' 
	 */
	public class Not extends LazyEquals implements Function {
		private Set<Function> contents = new LinkedHashSet<Function>();
		public Not() {
		}

		/**
		 * Create a new 'not' with the given function already in it.
		 * 
		 * @param init
		 */
		public Not(Function f) {
			contents.add(f);
		}

		public Set<Function> getContents() {
			return contents;
		}

		public void add(Function t) {
			contents.add(t);
		}

		public String toString() {
			return "not(" + DumpDroolsXml.toString(contents) + ")";
		}

		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.inference.DumpDroolsXml.Latexable#toLatex()
		 */
		@Override
		public String toLatex() {
			return "\\neg (" + DumpDroolsXml.toLatexAnd(contents) + ")";
		}
	}
	
	/**
	 * 'or(...)' 
	 */
	public class Or extends LazyEquals implements Function {
		private Set<Function> contents = new LinkedHashSet<Function>();
		public Or() {
		}

		public void addAll(Set<Function> foundTerms) {
			for (Function f : foundTerms) {
				contents.add(f);
			}
		}

		public Set<Function> getContents() {
			return contents;
		}

		public void add(Function t) {
			contents.add(t);
		}

		public String toString() {
			return "or(" + DumpDroolsXml.toString(contents) + ")";
		}

		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.inference.DumpDroolsXml.Latexable#toLatex()
		 */
		@Override
		public String toLatex() {
			return "(" + DumpDroolsXml.toLatexOr(contents) + ")";
		}
	}
	
	/**
	 * 'name(v1, v2, ...)' or 'name'
	 */
	public class VariableFunction extends LazyEquals implements Function {
		private String name;
		private Set<FunctionTerm> variables;
		public VariableFunction(String string, Set<FunctionTerm> variable) {
			this.name = string;
			this.variables = variable;
		}

		public String toString() {
			if (variables.size() == 0)
				return name;
			else		
				return name + "(" + DumpDroolsXml.toString(variables) + ")";
		}

		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.inference.DumpDroolsXml.Latexable#toLatex()
		 */
		@Override
		public String toLatex() {
			return escapeLatex(name) + "(" + DumpDroolsXml.toLatexComma(variables) + ")";
		}	
	}
	
	/**
	 * 'a'
	 */
	public class NormalVariable extends LazyEquals implements FunctionTerm, Variable {

		private String name;
		public NormalVariable(String string) {
			this.name = string;
		}

		public String toString() {
			return name;
		}

		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.inference.DumpDroolsXml.Latexable#toLatex()
		 */
		@Override
		public String toLatex() {
			return escapeLatex(name);
		}
		
	}
	
	private static int identifierCount = -1;
	/**
	 * Create a new identifier. Returns elements in
	 * [x, x0, x1, x2, ...]
	 * 
	 * @return
	 */
	public static String newIdentifier() {
		String result;
		if (identifierCount == -1) {
			result = "x";				
		} else {
			result = "x" + identifierCount;
		}
		identifierCount++;
		return result;
	}
	
	/**
	 * Escape any special latex contents.
	 * 
	 * @param name
	 * @return
	 */
	public String escapeLatex(String name) {
		return name.replace("-", "\\-").replace("_", "\\_");
	}

	private static int existsIdentifierCount = 0;
	/**
	 * Create a new identifier. Returns elements in
	 * [L0, L1, L2, ...]
	 * 
	 * @return
	 */
	public static String newExistsIdentifier() {
		String result;
		result = "L" + existsIdentifierCount;
		existsIdentifierCount++;
		return result;
	}
	
	/**
	 * '"test"' 
	 */
	public class StringLiteral extends LazyEquals implements FunctionTerm, Variable {

		private String value;
		public StringLiteral(String value) {
			this.value = value;
		}
		public String toString() {
			return "\"" + value + "\"";
		}
		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.inference.DumpDroolsXml.Latexable#toLatex()
		 */
		@Override
		public String toLatex() {
			return "\\textrm{\"" + escapeLatex(value) + "\"}";
		}
		
	}
	
	/**
	 * 'f(a)' 
	 */
	public class FactoryFunction extends LazyEquals implements FunctionTerm, Variable {
		private NormalVariable variable;
		private int index;
		public FactoryFunction(NormalVariable string, int index) {
			this.variable = string;
			this.index = index;
		}
		public String toString() {
			return "f(" + variable + ", " + index + ")";
		}
		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.inference.DumpDroolsXml.Latexable#toLatex()
		 */
		@Override
		public String toLatex() {
			return "f_{" + index + "}(" + variable.toLatex() + ")";
		}

		public NormalVariable getVariable() {
			return variable;
		}
		
	}
	
	/**
	 * 'notExists(x : ...)'
	 */
	public class NotExists extends LazyEquals implements Function {

		private NormalVariable variable;
		public NotExists(NormalVariable v) {
			this.variable = v;
		}
		
		/**
		 * @return
		 */
		public FunctionTerm getFunctionTerm() {
			return variable;
		}

		public Set<Function> getContents() {
			return body;
		}

		private Set<Function> body = new LinkedHashSet<Function>();
		public void add(Function linked) {
			body.add(linked);
		}
		
		public String toString() {
			return "notExists(" + variable + " : " + DumpDroolsXml.toString(body) + ")";
		}

		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.inference.DumpDroolsXml.Latexable#toLatex()
		 */
		@Override
		public String toLatex() {
			return "(\\neg \\exists " + variable.toLatex() + " : " + DumpDroolsXml.toLatexAnd(body) + ")"; 
		}
		
	}
	
	/**
	 * 'name(a, b)' 
	 */
	public class DoubleFunction extends LazyEquals implements Function {
		private String name;
		private FunctionTerm variable;
		private FunctionTerm variable2;
		public DoubleFunction(String string, FunctionTerm currentVariable, FunctionTerm v2) {
			this.name = string;
			this.variable = currentVariable;
			this.variable2 = v2;
		}
		public FunctionTerm getFunctionTerm2() {
			return variable2;
		}
		public FunctionTerm getFunctionTerm1() {
			return variable;
		}
		public String toString() {
			return name + "(" + variable + ", " + variable2 + ")";
		}
		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.inference.DumpDroolsXml.Latexable#toLatex()
		 */
		@Override
		public String toLatex() {
			return escapeLatex(name) + "(" + variable.toLatex() + ", " + variable2.toLatex() + ")";
		}
	}
	
	/**
	 * Test that .toString() on inferred terms generates the correct 
	 * output.
	 */
	public void testTermToStringFactoryFunction() {
		InferredTerm t = new InferredTerm();
		NormalVariable a = new NormalVariable("a");
		FactoryFunction fa = new FactoryFunction(a, 0);
		SingleFunction ds = new SingleFunction("DomainStore", fa);
		t.getHead().add(ds);
		assertEquals("DomainStore(f(a, 0)) <-", t.toString());
		
		t.getBody().add(ds);
		assertEquals("DomainStore(f(a, 0)) <- DomainStore(f(a, 0))", t.toString());

		// its a HashSet so additional identical terms do nothing
		t.getHead().add(ds);
		assertEquals("DomainStore(f(a, 0)) <- DomainStore(f(a, 0))", t.toString());
	}
	
	/**
	 * Test that .toString() on inferred terms generates the correct 
	 * output.
	 */
	public void testTermToStringNotExists() {
		InferredTerm t = new InferredTerm();
		NormalVariable a = new NormalVariable("a");
		NormalVariable x = new NormalVariable("x");
		NotExists nb = new NotExists(x);
		SingleFunction ia = new SingleFunction("InternetApplication", x);
		nb.add(ia);
		DoubleFunction linked = new DoubleFunction("linked", x, a);
		nb.add(linked);
		t.getBody().add(nb);
		assertEquals("<- notExists(x : InternetApplication(x), linked(x, a))", t.toString());
		
		SingleFunction sf = new SingleFunction("DomainStore", a);
		t.getHead().add(sf);
		assertEquals("DomainStore(a) <- notExists(x : InternetApplication(x), linked(x, a))", t.toString());
	}
	
	/**
	 * Test the creation of a factory graph.
	 * 
	 * From the example in version4:
	 * – a(x) -> b(f(x))
	 * – b(x) -> c(f(x))
	 * – a(x), b(x) -> d(f(x))
	 * – b(x), ¬e(x) -> f(x)
	 * – f(x) -> g(f(x))
	 * – d(x) -> e(f(x))
	 * – g(x) -> a(f(x)) **
	 * @throws Exception 
	 * 
	 */
	public void testCreatingFactoryGraph() throws Exception {
		NormalVariable x = new NormalVariable("x");
		NormalVariable y = new NormalVariable("y");	// to test that variable names can change
		FactoryFunction fx = new FactoryFunction(x, 0);
		FactoryFunction fy = new FactoryFunction(y, 0);
		List<InferredTerm> program = new ArrayList<InferredTerm>();
		{
			InferredTerm t = new InferredTerm();
			t.getBody().add(new SingleFunction("a", x));
			t.getHead().add(new SingleFunction("b", fx));
			assertEquals("b(f(x, 0)) <- a(x)", t.toString());
			program.add(t);
			
			// try the node calculations
			FactoryLoopNodeList n = calculate(t);
			assertEquals(2, n.size());
			assertTrue(n.containsNode("a")); 
			assertTrue(n.containsNode("b")); 
			assertEquals("a", n.getNode("a").getName());
			assertEquals(1, n.getNode("a").getEdges().size());
			assertNotNull(n.getNode("a").getEdges().get(0));
			assertEquals("b", n.getNode("a").getEdges().get(0).getName());
			
			// the program so far should also return the same
			FactoryLoopNodeList nodes = calculate(program);
			assertEquals(2, nodes.size());
			assertTrue(nodes.containsNode("a")); 
			assertTrue(nodes.containsNode("b")); 
			assertEquals("a", nodes.getNode("a").getName());
			assertEquals(1, nodes.getNode("a").getEdges().size());
			assertNotNull(nodes.getNode("a").getEdges().get(0));
			assertEquals("b", nodes.getNode("a").getEdges().get(0).getName());
		}
		{
			InferredTerm t = new InferredTerm();
			t.getBody().add(new SingleFunction("b", x));
			t.getHead().add(new SingleFunction("c", fx));
			assertEquals("c(f(x, 0)) <- b(x)", t.toString());
			program.add(t);
		}
		{
			InferredTerm t = new InferredTerm();
			t.getBody().add(new SingleFunction("a", x));
			t.getBody().add(new SingleFunction("b", x));
			t.getHead().add(new SingleFunction("d", fx));
			assertEquals("d(f(x, 0)) <- a(x), b(x)", t.toString());
			program.add(t);

			// try the node calculations
			FactoryLoopNodeList n = calculate(t);
			// there should be three; a -> d, and b -> d, and d -> .
			assertEquals(3, n.size());
			FactoryLoopNode n1 = n.getNode("a");
			assertEquals("a", n1.getName());
			assertEquals(1, n1.getEdges().size());
			assertEquals("d", n1.getEdges().get(0).getName());
			FactoryLoopNode n2 = n.getNode("b");
			assertEquals("b", n2.getName());
			assertEquals(1, n2.getEdges().size());
			assertEquals("d", n2.getEdges().get(0).getName());
			FactoryLoopNode n3 = n.getNode("d");
			assertEquals("d", n3.getName());
			assertEquals(0, n3.getEdges().size());
		}
		{
			InferredTerm t = new InferredTerm();
			t.getBody().add(new SingleFunction("b", x));
			t.getBody().add(new Not(new SingleFunction("e", x)));
			t.getHead().add(new SingleFunction("f", x));
			assertEquals("f(x) <- b(x), not(e(x))", t.toString());
			program.add(t);
			
			// try the node calculations
			FactoryLoopNodeList n = calculate(t);
			// this term does nothing by itself
			assertEquals(1, n.size());
			assertTrue(n.containsNode("b"));	// just the single node
		}
		{
			InferredTerm t = new InferredTerm();
			t.getBody().add(new SingleFunction("f", y));
			t.getHead().add(new SingleFunction("g", fy));
			assertEquals("g(f(y, 0)) <- f(y)", t.toString());
			program.add(t);
		}
		{
			InferredTerm t = new InferredTerm();
			t.getBody().add(new SingleFunction("d", y));
			t.getHead().add(new SingleFunction("e", fy));
			assertEquals("e(f(y, 0)) <- d(y)", t.toString());
			program.add(t);
		}
		
		// lets test all the generated loop edges
		{
			FactoryLoopNodeList n = calculate(program);
			assertEquals(7, n.size());
			
			FactoryLoopNode a = n.getNode("a");
			assertEquals(2, a.getEdges().size());
			assertTrue(a.hasEdgeTo("d"));
			assertTrue(a.hasEdgeTo("b"));
			FactoryLoopNode b = n.getNode("b");
			
			assertEquals(3, b.getEdges().size());
			assertTrue(b.hasEdgeTo("c"));
			assertTrue(b.hasEdgeTo("d"));
			assertTrue(b.hasEdgeTo("g"));
			
			// 'c' will exist, but it will have 0 edges
			FactoryLoopNode c = n.getNode("c");
			assertNotNull(c);
			assertTrue(n.containsNode("c"));
			assertEquals(0, c.getEdges().size());
			
			FactoryLoopNode d = n.getNode("d");
			assertEquals(1, d.getEdges().size());
			assertTrue(d.hasEdgeTo("e"));
			
			FactoryLoopNode e = n.getNode("e");
			assertEquals(0, e.getEdges().size());
			
			FactoryLoopNode f = n.getNode("f");
			assertEquals(1, f.getEdges().size());
			assertTrue(f.hasEdgeTo("g"));
		
			FactoryLoopNode g = n.getNode("g");
			assertEquals(0, g.getEdges().size());
			assertFalse(a.hasEdgeTo("a"));	// not yet
		}

		{
			// there shouldn't be any loops yet
			List<GraphLoop<Function>> loops = calculateFactoryLoops(program);
			
			//assertEquals(0, loops.size());
		}

		{
			InferredTerm t = new InferredTerm();
			t.getBody().add(new SingleFunction("g", y));
			t.getHead().add(new SingleFunction("a", fy));
			assertEquals("a(f(y, 0)) <- g(y)", t.toString());
			program.add(t);
		}
		
		// lets test all the generated loop edges
		{
			FactoryLoopNodeList n = calculate(program);
			assertEquals(7, n.size());
			
			FactoryLoopNode g = n.getNode("g");
			assertEquals(1, g.getEdges().size());
			assertTrue(g.hasEdgeTo("a"));	// it has it now
		}

		{
			// there should now be one loop
			List<GraphLoop<Function>> loops = calculateFactoryLoops(program);
			
			assertEquals(1, loops.size());
			assertEquals("a -> b -> g -> a", loops.get(0).toString("a"));
		}
	}
	
	/**
	 * Calculate the loops within a given graph.
	 * 
	 * @param program
	 * @return
	 * @throws TermParseException 
	 */
	private List<GraphLoop<Function>> calculateFactoryLoops(
			List<InferredTerm> program) throws TermParseException {

		FactoryLoopNodeList parsed = calculate(program);
		
		// now lets find loops
		//List<GraphLoop<FactoryLoopNode>> result = new ArrayList<GraphLoop<FactoryLoopNode>>();
		
		System.out.println("---");
		for (FactoryLoopNode f : parsed) {
			
			// create the checker
			FactoryLoopChecker fc = new FactoryLoopChecker(parsed);
			
			// does a path exist?
			int path = fc.dijkstra(f, f);
			System.out.println("path from '" + f + "' to '" + f + "': " + path);
			
		}
		
		// TODO complete method
		return null;
		
	}
	
	public class DijkstraException extends Exception {

		private static final long serialVersionUID = 1L;

		public DijkstraException(String message) {
			super(message);
		}

	}
	
	/**
	 * Extends Dijkstra's algorithm to not consider the 'source' of the 
	 * current node when calculating distance.
	 * 
	 * TODO rename appropriately
	 * 
	 * @author jmwright
	 * @param <T>
	 */
	public abstract class DijkstraAlgorithmWithoutRoot<T> extends DijkstraAlgorithm<T> {

		private T copy;
		private T source;
		
		/**
		 * Add the duplicate copy. We have to make a new list
		 * and place it in there, otherwise we will get a
		 * ConcurrentModificationException.
		 */
		@Override
		protected Collection<T> getInternalEdges() {
			Collection<T> r = getEdges();
			Collection<T> newR = new ArrayList<T>();
			newR.addAll(r);
			newR.add(copy);
			return newR;
		}

		/**
		 * If we are the duplicate copy, add all of the edges
		 * of the original element
		 */
		@Override
		public List<T> getInternalNeighbours(T u) {
			if (this.copy.equals(u)) {
				// get the edges of the source
				return getNeighbours(this.source);
			}
			return getNeighbours(u);
		}

		/**
		 * We change something like:
		 * 
		 *   a -> b, c, d
		 *   e, c -> a
		 *   
		 * To:
		 * 
		 *   a -> b, c, d
		 *   a' -> b, c, d
		 *   e, c -> a
		 *   
		 * And call dijkstra(a', a), rather than (a, a).
		 * @throws DijkstraException 
		 * 
		 */
		@Override
		public int dijkstra(T source, T target) {
			
			// we need to make a new copy of the source - this will
			// be our new target
			this.source = target;
			this.copy = makeCopy(target);
			
			try {
				if (this.copy.equals(target)) {
					throw new DijkstraException("While trying to make a copy of the source element, making a copy did not create a new element which was not equal: '" + source + "', '" + this.source + "'");
				}
				if (getEdges().contains(this.copy)) {
					throw new DijkstraException("While trying to make a copy of the source element, the copy already existed in the edge list: '" + this.source + "'");
				}
			} catch (DijkstraException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
			
			// continue as normal
			return super.dijkstra(source, this.copy);
		}

		/**
		 * We need to make a new element 'e' that 
		 * definitely does not equal 'e'. It also cannot currently
		 * be stored within the edges.
		 * 
		 * @param source2
		 * @return
		 */
		public abstract T makeCopy(T e);

		/**
		 * We need to override it so we can link up to the 
		 * copied source.
		 * 
		 * TODO you may need to change this method to internalXXX
		 */
		@Override
		public int distanceBetween(T u, T n) {
			if (u.equals(this.copy)) {
				if (n.equals(this.copy)) {
					return super.distanceBetween(this.source, this.source);
				} else { 
					return super.distanceBetween(this.source, n);
				}
			} else {
				if (n.equals(this.copy)) {
					return super.distanceBetween(u, this.source);
				} else {
					return super.distanceBetween(u, n);
				}
			}
		}
		
		
		
	}
	
	public class FactoryLoopChecker extends DijkstraAlgorithmWithoutRoot<FactoryLoopNode> {

		FactoryLoopNodeList contents;
		
		public FactoryLoopChecker(FactoryLoopNodeList contents) {
			this.contents = contents;
		}
		
		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.DijkstraAlgorithm#getEdges()
		 */
		@Override
		protected Collection<FactoryLoopNode> getEdges() {
			return contents;
		}

		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.DijkstraAlgorithm#getNeighbours(java.lang.Object)
		 */
		@Override
		public List<FactoryLoopNode> getNeighbours(FactoryLoopNode u) {
			return u.getEdges();
		}

		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.inference.DumpDroolsXml.DijkstraAlgorithmWithoutRoot#makeCopy(java.lang.Object)
		 */
		@Override
		public FactoryLoopNode makeCopy(FactoryLoopNode e) {
			// make a new node based on 'e' that does not exist in contents
			for (int i = 0; i < 1024; i++) {
				FactoryLoopNode r = new FactoryLoopNode("[copy]" + e.getName() + i);
				if (!contents.contains(r)) {
					return r;
				}
			}
			throw new RuntimeException("Could not generate a unique node for '" + e + "'");
		}
		
	}

	/**
	 * Represents a loop in a graph of elements in T.
	 * 
	 * @author jmwright
	 *
	 */
	public class GraphLoop<T> {

		/**
		 * Print out the contents of the graph loop, starting at 
		 * an element which is represented by the given string.
		 * 
		 * @param string
		 * @return
		 */
		public String toString(String string) {
			// TODO
			return null;
		}

	}

	
	/**
	 * Calculate all the nodes from the given term.
	 * 
	 * @param t
	 * @return
	 * @throws TermParseException 
	 */
	public FactoryLoopNodeList calculate(InferredTerm t) throws TermParseException {
		List<InferredTerm> list = new ArrayList<InferredTerm>();
		list.add(t);
		return calculate(list);
	}

	/**
	 * Calculate all the nodes from the given list of terms.
	 * 
	 * @param program
	 * @return
	 * @throws TermParseException 
	 */
	public FactoryLoopNodeList calculate(List<InferredTerm> program) throws TermParseException {
		FactoryLoopNodeList fl = new FactoryLoopNodeList();
		
		for (InferredTerm term : program) {
			// for every positive term in the body,
			for (Function body : term.getBody()) {
				// first just select all types functionName(x).
				if (body instanceof SingleFunction) {
					SingleFunction f = (SingleFunction) body;

					// do we have this node in the list already?
					FactoryLoopNode bodyNode = fl.getNodeForFunction(f);
					if (bodyNode == null) {
						// otherwise, create a new one
						bodyNode = new FactoryLoopNode(f);
						fl.add(bodyNode);
					}

					// iterate over all the elements in the head
					// to find out what factory elements it could create
					Set<Function> canCreate = 
						investigateFunctionForPotentialFactoryFunctions(f, term, program);
					
					// turn these into FactoryNodes
					Set<FactoryLoopNode> nodes =
						fl.convertFunctionsToNodes(canCreate);
					
					// all of these elements will be neighbours of this node.
					bodyNode.addEdges(nodes);
				}
				
			}
			
		}
		
		return fl;
	}
	
	/**
	 * In the given program in the given term, take the given function and find out all
	 * the factory functions f(x) that it may 
	 * 
	 * @param f
	 * @param program
	 * @return
	 * @throws TermParseException 
	 */
	private Set<Function> investigateFunctionForPotentialFactoryFunctions(
			SingleFunction f, InferredTerm current, List<InferredTerm> program) throws TermParseException {
		
		Set<Function> results = new HashSet<Function>();
		
		if (!current.getBody().contains(f)) {
			throw new TermParseException("Expected function '" + f + "' to be in the current term body: '" + current + "'");
		}
		
		for (Function head : current.getHead()) {
			if (head instanceof SingleFunction) {
				SingleFunction sf = (SingleFunction) head;
				if (sf.getVariable() instanceof FactoryFunction) {
					FactoryFunction ff = (FactoryFunction) sf.getVariable();
					if (ff.getVariable().equals( f.getVariable() )) {
						// sf(f(x)) <- current(x)
						results.add(sf);
					}
				} else if (sf.getVariable().equals(f.getVariable())) {
					// its a function that uses the current variable
					
					// look through the entire program for other things that match 
					// this current term
					Set<Function> subResults = 
						investigateFunctionForPotentialFactoryFunctions(sf, program);
					
					// add these to the results
					results.addAll(subResults);
				}
			}
		}
		
		return results;
	}

	/**
	 * Go through the entire program and look for terms that have the given
	 * function in the body. Add all of the factory functions that this
	 * term creates, and return these.
	 * 
	 * @param sf
	 * @param program
	 * @return
	 * @throws TermParseException 
	 */
	private Set<Function> investigateFunctionForPotentialFactoryFunctions(
			SingleFunction sf, List<InferredTerm> program) throws TermParseException {

		Set<Function> results = new HashSet<Function>();
		
		for (InferredTerm term : program) {
			// we can't look directly at these functions, we
			// have to look at the function names
			for (Function f : term.getBody()) {
				if (f instanceof SingleFunction && ((SingleFunction) f).getName().equals(sf.getName())) {
					// a match
					Set<Function> sub = 
						investigateFunctionForPotentialFactoryFunctions((SingleFunction) f, term, program);
					results.addAll(sub);
				}
			}
		}

		
		return results;
	}

	/**
	 * We take InferredTerms and connect all of the related paths
	 * into a single node.
	 * 
	 * e.g. "a(x) -> b(f(x))" becomes a node 'a', with a link to 'b'
	 * 
	 * "a(x) -> b(x), b(x) -> c(f(x))" becomes a node 'a', with
	 * a link to 'c'.
	 * 
	 * If we combine the two above, we get a node 'a' with links to
	 * both 'b' and 'c'. 
	 * 
	 * @author jmwright
	 *
	 */
	protected class FactoryLoopNode {

		private String name;
		private Set<FactoryLoopNode> edges = new LinkedHashSet<FactoryLoopNode>();
		public FactoryLoopNode(String name) {
			this.name = name;
		}

		/**
		 * Add all of the given nodes as edges.
		 * 
		 * @param nodes
		 */
		public void addEdges(Set<FactoryLoopNode> nodes) {
			for (FactoryLoopNode n : nodes) {
				if (n == null)
					throw new NullPointerException("Node '" + n + "' cannot be null.");
				edges.add(n);
			}
		}

		public FactoryLoopNode(SingleFunction f) {
			this(f.getName());
		}

		/**
		 * Does this node have an edge to the given node name?
		 * 
		 * @param string
		 * @return
		 */
		public boolean hasEdgeTo(String string) {
			for (FactoryLoopNode e : getEdges()) {
				if (e.getName().equals(string))
					return true;
			}
			return false;
		}

		/**
		 * Get all neighbours to this node.
		 * 
		 * @return
		 */
		public List<FactoryLoopNode> getEdges() {
			List<FactoryLoopNode> r = new ArrayList<FactoryLoopNode>();
			r.addAll(edges);
			return r;
		}

		public String getName() {
			return name;
		}
		
		public String toString() {
			// prevent infinite loops
			String e = "";
			for (FactoryLoopNode edge : edges) {
				if (!e.isEmpty())
					e += ", ";
				e += edge.getName();
			}
				
			return "[Node '" + name + "', edges: " + e + "]";
		}
		
	}
	
	/**
	 * Helper functions around a list of FactoryLoopNodes.
	 * 
	 * @author jmwright
	 *
	 */
	public class FactoryLoopNodeList extends ArrayList<FactoryLoopNode> {

		private static final long serialVersionUID = 1L;

		/**
		 * Get the FactoryLoopNode with the given name, or
		 * null if it doesn't exist.
		 * 
		 * @param string
		 * @return
		 */
		public FactoryLoopNode getNode(String string) {
			for (FactoryLoopNode f : this) {
				if (f.getName().equals(string))
					return f;
			}
			return null;
		}

		/**
		 * Take all functions in the given list and add them
		 * as nodes to the currnet list, returning the nodes
		 * created.
		 * 
		 * Modifies the current list.
		 * 
		 * @param list
		 * @param program
		 * @return
		 */
		public Set<FactoryLoopNode> convertFunctionsToNodes(
				Set<Function> list) {
			
			Set<FactoryLoopNode> result = new LinkedHashSet<FactoryLoopNode>();
			
			for (Function f : list) {
				if (f instanceof SingleFunction) {
					FactoryLoopNode found = getNodeForFunction((SingleFunction) f);
					if (found == null) {
						found = new FactoryLoopNode((SingleFunction) f);
						add(found);
					}
					result.add(found);
				}
			}
			
			return result;
		}

		/**
		 * Get the node for the given function, or null
		 * if none exists.
		 * 
		 * @param body
		 * @return
		 */
		public FactoryLoopNode getNodeForFunction(SingleFunction body) {
			return getNode(((SingleFunction) body).getName());
		}

		/**
		 * Does this list contain the given node name?
		 * 
		 * @param string
		 * @return
		 */
		public boolean containsNode(String string) {
			return getNode(string) != null;
		}
		
	}
	
	/**
	 * Create a graph of the stratification rules, and check for
	 * any cycles.
	 * 
	 * @param allRules
	 */
	protected void findStratCycleRules(Set<LogicRule> allRules) {
		
		Map<String, List<String>> graphGt = new HashMap<String, List<String>>();
		Map<String, List<String>> graphGtEq = new HashMap<String, List<String>>();
		
		//List<StratElement> graph = new ArrayList<StratElement>();
		List<StratReason> reasonGt = new ArrayList<StratReason>();
		List<StratReason> reasonGtEq = new ArrayList<StratReason>();
		
		Set<String> sources = new LinkedHashSet<String>();
		
		// first, create the graph
		for (LogicRule r : allRules) {
			// r: a, b, not(c), not(d) -> d, e
			// into: a->d, b->d, not(c)->d, a->e, b->e, not(c)->e
			
			List<LogicElement> headWithoutNots = removeInsertedElements(r.head, r.body);
			for (LogicElement h : headWithoutNots) {
				for (LogicElement b2 : r.body) {
					LogicTerm b = (LogicTerm) b2;
					if (h instanceof LogicNotTerm) {
						LogicNotTerm t = (LogicNotTerm) h;
						addIntoMap(graphGt, t.term.name, b.name, reasonGt, b.reason);
						addIntoMap(graphGt, t.term.name, b.name + "!target", reasonGt, b.reason);
						addIntoMap(graphGt, t.term.name + "!target", b.name, reasonGt, b.reason); // although we are not starting from source!target, we still need this as an edge in the graph
						//graphGtTargets.add(b.name + "!target");
						sources.add(t.term.name);
						sources.add(b.name);
					} else {
						LogicTerm t = (LogicTerm) h;
						addIntoMap(graphGtEq, t.name, b.name, reasonGtEq, b.reason);
						addIntoMap(graphGtEq, t.name, b.name + "!target", reasonGtEq, b.reason);
						addIntoMap(graphGtEq, t.name + "!target", b.name, reasonGtEq, b.reason);
						//graphGtEqTargets.add(b.name + "!target");
						sources.add(t.name);
						sources.add(b.name);
					}
				}
			}
		}
		
		// now for every possible class, see if there is a cycle
		for (String from : sources) {
			StratificationCycleChecker scc = new StratificationCycleCheckerWithExplanation(graphGt, graphGtEq, reasonGt, reasonGtEq);
			int d = scc.dijkstra(from, from + "!target");
			if (d == -1) {
				System.out.println(from + " -> " + from + "!target strat: no path found");
			} else {
				String path = scc.getLastPath();
				System.out.println(from + " -> " + from + "!target strat: " + d + ": " + path);				
			}
		}
		
	}

	/**
	 * @param doubleMap
	 * @param key
	 * @param value
	 */
	private void addIntoMap(Map<String, List<String>> doubleMap, String key,
			String value, List<StratReason> reasonList, String reason) {
		List<String> r = doubleMap.get(key);
		if (r == null) {
			doubleMap.put(key, new ArrayList<String>());
		}
		doubleMap.get(key).add(value);
		
		/*
		 * Also add it to the reason list.
		 */
		reasonList.add(new StratReason( key, value, reason ));
	}

	/**
	 * Remove not(e) elements from the head that are asserted in the body.
	 * 
	 * @param head
	 * @param body
	 * @return
	 */
	private List<LogicElement> removeInsertedElements(List<LogicElement> head,
			List<LogicElement> body) {
		List<LogicElement> elements = new ArrayList<LogicElement>();
		
		for (LogicElement h : head) {
			if (h instanceof LogicNotTerm) {
				LogicNotTerm t = (LogicNotTerm) h;
				boolean found = false;
				for (LogicElement b : body) {
					if (((LogicTerm) b).name.equals(t.term.name)) {
						found = true;
						break;
					}
				}
				if (!found) {
					elements.add(h);
				}
			} else {
				elements.add(h);
			}
		}
		return elements;
		
	}

	/**
	 * Check each rule to make sure that there is a path from head to
	 * body, but no path from body to head.
	 * 
	 * Ignores negated() rules in body.
	 * 
	 * @param allRules
	 */
	protected void checkRulesCompositionGraph(Set<LogicRule> allRules) {
		TestComposition comp = new TestComposition();
		
		for (LogicRule r : allRules) {
			// get the element with the highest distance from InternetApplication
			int max_d = -1;
			LogicTerm maxTerm = null;
			for (LogicElement e : r.head) {
				if (!(e instanceof LogicNotTerm)) {
					LogicTerm t = (LogicTerm) e;
					int d = comp.dijkstra("InternetApplication", t.name);
					if (d > max_d) {
						maxTerm = t;
						max_d = d;
					}
				}
			}
			
			// now check each element in the rule body
			for (LogicElement e : r.body) {
				if (e instanceof LogicTerm) {
					// even if it fails, we want to continue evaluating the model
					try { 
						comp.checkDijkstra(maxTerm.name, ((LogicTerm) e).name);
					} catch (AssertionFailedError ex) {
						System.out.println(ex.getMessage());		// temporary TODO remove
					}
				} else {
					throw new RuntimeException("Element " + e + " should have been a LogicTerm.");
				}
			}
		}

	}

	/**
	 * Create all the prolog rules that are necessary to work out
	 * stratification.
	 * 
	 * @param allRules
	 */
	public void outputPrologStratification(Set<LogicRule> allRules) throws Exception {
		StringBuffer pl = new StringBuffer();
		
		/*
		 * First, lets define the rules.
		 * 
		 *   a, b -> c
		 * changes to:
		 *   strat_1(OutA, OutB, OutC) :- strat(a, OutA), strat(b, OutB), strat(c, outC), C >= A, C >= B.
		 */
		int i = 0;
		for (LogicRule r : allRules) {
			i++;
			
			// strat_1(OutA, ...) :-
			pl.append("strat_").append(i).append("(");
			Set<String> unique = uniqueElementsInRule(r);
			
			int z = 0;
			for (String element_name : unique) {
				if (z != 0) pl.append(", ");
				pl.append("Out" + element_name);
				z++;
			}
			pl.append(") :- ");
			
			// strat(a, OutA), 
			for (LogicElement e : r.head) {
				if (e instanceof LogicTerm) {
					String t = ((LogicTerm) e).name;
					pl.append("strat(" + undercase(t) + ", Out" + t + "), ");
				} else if (e instanceof LogicNotTerm) {
					String t = ((LogicNotTerm) e).term.name;
					pl.append("strat(" + undercase(t) + ", Out" + t + "), ");
				}
			}
			for (LogicElement e : r.body) {
				if (e instanceof LogicTerm) {
					String t = ((LogicTerm) e).name;
					pl.append("strat(" + undercase(t) + ", Out" + t + "), ");
				} else {
					throw new RuntimeException("Body should only contain LogicTerms");
				}
			}
			
			// X >= Y, X > Y, ...
			for (LogicElement f : r.body) {
				String t2 = ((LogicTerm) f).name;
				for (LogicElement e : r.head) {
					if (e instanceof LogicTerm) {
						// X >= Y
						String t = ((LogicTerm) e).name;
						pl.append("Out" + t2 + " >= Out" + t + ", ");
					} else if (e instanceof LogicNotTerm) {
						String t = ((LogicNotTerm) e).term.name;
						// X > Y
						pl.append("Out" + t2 + " > Out" + t + ", ");
					}
				}
			}			
			
			pl.append("true.\n");		// end the rule
		}
		pl.append("\n");
		
		// now we create a rule which will specify all the strat values
		Set<String> allUnique = new LinkedHashSet<String>();
		for (LogicRule r : allRules) {
			allUnique.addAll(uniqueElementsInRule(r));
		}
		
		// head: valid(OutA, OutB, OutC, ...) :-
		pl.append("valid(");
		{
			int z = 0;
			for (String term : allUnique) {
				if (z != 0) pl.append(", ");
				pl.append("Out" + term);
				z++;
			}
		}
		pl.append(") :- ");
		
		// body: each rule
		i = 0;
		for (LogicRule r : allRules) {
			if (i != 0) pl.append(", ");
			i++;
			pl.append("strat_").append(i).append("(");
			Set<String> unique = uniqueElementsInRule(r);

			int z = 0;
			for (String element_name : unique) {
				if (z != 0) pl.append(", ");
				pl.append("Out" + element_name);
				z++;
			}
			pl.append(")");
		}
		
		pl.append(".\n\n");
		
		// finally, we list out all possible values of every strat
		for (String term : allUnique) {
			for (int j = 0; j < 10; j++) {
				pl.append("strat(").append(undercase(term)).append(", ").append(j).append(").\n");
			}
		}
		
		// write to file
		IFile outFile = project.getFile("strat.pl");
		InputStream stream = new ByteArrayInputStream(pl.toString().getBytes("UTF-8"));
		System.out.println("Writing " + outFile + "...");
		outFile.create(stream, true, monitor);
	}
	
	/**
	 * Get a list of all the unique elements mentioned in both the
	 * head and body of this rule. 
	 */
	private Set<String> uniqueElementsInRule(LogicRule r) {
		Set<String> s = new LinkedHashSet<String>();
		for (LogicElement e : r.head) {
			if (e instanceof LogicTerm) {
				s.add(((LogicTerm) e).name);
			} else if (e instanceof LogicNotTerm) {
				s.add(((LogicNotTerm) e).term.name);
			} else {
				throw new RuntimeException("Unknown logic element type " + e.getClass());
			}
		}
		for (LogicElement e : r.body) {
			if (e instanceof LogicTerm) {
				s.add(((LogicTerm) e).name);
			} else if (e instanceof LogicNotTerm) {
				s.add(((LogicNotTerm) e).term.name);
			} else {
				throw new RuntimeException("Unknown logic element type " + e.getClass());
			}
		}
		return s;
	}
	
	/**
	 * Change MyFoo to my_foo
	 * @param a
	 */
	private String undercase(String a) {
		String out = "";
		for (int i = 0; i < a.length(); i++) {
			char c = a.charAt(i);
			if (i != 0 && (c >= 'A' && c <= 'Z')) {
				// uppercase char 
				out += "_" + Character.toLowerCase(c);
			} else {
				out += Character.toLowerCase(c);
			}
		}
		return out;
	}

	protected class LogicTerm extends LogicElement {
		public String name;
		
		public LogicTerm(String name, String reason) {
			super(reason);
			this.name = name;
		}

		public String toHtml() {
			return name;
		}
		
		public String toString() { return name; }
		
		@Override
		public boolean equals(Object obj) {
			return obj instanceof LogicTerm && 
			((LogicTerm) obj).name.equals(this.name);
		}

		@Override
		public int hashCode() {
			return ("LogicTerm" + name.hashCode()).hashCode();
		}
	}
	
	protected class LogicNotTerm extends LogicElement {
		public LogicTerm term;
		
		public LogicNotTerm(LogicTerm term, String reason) {
			super(reason);
			this.term = term;
		}

		public String toHtml() {
			return "not(" + term.toHtml() + ")";
		}

		public String toString() { return "not(" + term + ")"; }		

		@Override
		public boolean equals(Object obj) {
			return obj instanceof LogicNotTerm && 
			((LogicNotTerm) obj).term.equals(this.term);
		}

		@Override
		public int hashCode() {
			return ("LogicNotTerm" + term.hashCode()).hashCode();
		}
	}
	
	protected abstract class LogicElement {

		public abstract String toHtml();
		
		public String reason;
		
		public LogicElement(String reason) {
			this.reason = reason;
		}
		
	}
	
	protected class LogicRule {
		public List<LogicElement> head = new ArrayList<LogicElement>();
		public List<LogicElement> body = new ArrayList<LogicElement>();
			
		@Override
		public boolean equals(Object obj) {
			return obj instanceof LogicRule && 
			((LogicRule) obj).head.equals(this.head) && 
			((LogicRule) obj).body.equals(this.body); 
		}

		@Override
		public int hashCode() {
			return ("LogicRule" + head.hashCode() + body.hashCode()).hashCode();
		}

		/**
		 * Cycle through generated elements in the body
		 * and remove those that are not(..) in the head.
		 * 
		 * i.e. "A, not(B) -> B" should be simplified to
		 * "A -> B"
		 * 
		 * Currently only concerns itself with element names
		 */
		public void removeGeneratedElements() {
			List<LogicElement> elementsToRemove = new ArrayList<LogicElement>();
			
			for (LogicElement e : body) {
				String name = ((LogicTerm) e).name;
				// is it in the head?
				for (LogicElement f : head) {
					if (f instanceof LogicNotTerm && ((LogicNotTerm) f).term.name.equals(name)) {
						// found it; remove it and skip
						elementsToRemove.add(f);
						continue;
					}
				}
			}
			
			for (LogicElement n : elementsToRemove) {
				head.remove(n);
			}
		}
		
		public String toHtml() {
			String head = "";
			for (LogicElement e : this.head) {
				head += (head.isEmpty() ? "" : ", ") + e.toHtml();
			}
			String body = "";
			for (LogicElement e : this.body) {
				body += (body.isEmpty() ? "" : ", ") + e.toHtml();
			}
			return head + " -> " + body;
		}
	}
	
	/**
	 * Investigate XML rules to calculate their logic formulas.
	 * TODO move all of this into separate classes (if necessary)
	 * 
	 * @param document
	 * @param name
	 */
	private List<LogicRule> investigateLogic(Document document, String name) throws Exception {
		List<LogicRule> logicRules = new ArrayList<LogicRule>();
		
		// parse over each rule
		IterableElementList rules = xpath(document, "//rule");
		for (Element rule : rules) {
			LogicRule logic = new LogicRule();
			String reason = rule.getAttribute("name");
			
			Element lhs = xpathFirst(rule, "lhs");
			Element rhs = xpathFirst(rule, "rhs");
			
			for (int j = 0; j < lhs.getChildNodes().getLength(); j++) {
				Node p2 = lhs.getChildNodes().item(j);
				if (p2 instanceof Element) {
					Element p = (Element) p2;
					if (p.getNodeName().equals("pattern")) {
						LogicTerm t = new LogicTerm(p.getAttribute("object-type"), reason);
						logic.head.add(t);
					} else if (p.getNodeName().equals("not")) {
						Element actualP = xpathFirst(p, "pattern"); 
						LogicTerm t = new LogicTerm(actualP.getAttribute("object-type"), reason);
						logic.head.add(new LogicNotTerm(t, reason));
					}
				}
			}
			
			// find the handler rules
			// we assume that rule bodies only generate elements using
			// handler.generatedXXX(...)
			IterableElementList generatedElements = xpath(rhs, "statement/assignment/statement/variable[@name='handler']/method");
			for (Element g : generatedElements) {
				assertEquals("method", g.getNodeName());
				String methodName = g.getAttribute("name");;
				if (methodName.startsWith("generated")) {
					methodName = methodName.substring("generated".length());
				}
				LogicTerm t = new LogicTerm(methodName, reason);
				logic.body.add(t);
			}
			
			logic.removeGeneratedElements();
			logicRules.add(logic);
		}
		
		// concatenate down
		StringBuffer out = new StringBuffer();
		out.append("<html><h1>" + name + " rules</h1><p>\n\n<ol>\n");
		for (LogicRule r : logicRules) {
			out.append("<li>" + r.toHtml() + "</li>\n");
		}
		out.append("\n</ol></html>\n");
		
		// output to name-logic.html
		IFile outFile = project.getFile(name + ".logic.html");
		InputStream source = new ByteArrayInputStream(out.toString().getBytes("UTF-8"));
		outFile.create(source, true, monitor);
		
		return logicRules;
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
	 * Load an XML document from an XML snippet.
	 * 
	 * @param xml
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Document loadInlineDocument(String xml) throws ParserConfigurationException, SAXException, IOException {
		return loadDocument(new StringBufferInputStream(xml));
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
	
	protected class StratificationCycleChecker extends DijkstraAlgorithm<String> {

		public Map<String, List<String>> graphGt;
		public Map<String, List<String>> graphGtEquals;
		
		public StratificationCycleChecker(Map<String, List<String>> graphGt, Map<String, List<String>> graphGtEquals) {
			this.graphGt = graphGt;
			this.graphGtEquals = graphGtEquals;
		}
		
		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.DijkstraAlgorithm#getEdges()
		 */
		@Override
		protected Collection<String> getEdges() {
			if (edgeCache == null) {
				edgeCache = new HashSet<String>();
				edgeCache.addAll(graphGt.keySet());
				edgeCache.addAll(graphGtEquals.keySet());
				for (String k : graphGt.keySet()) {
					edgeCache.addAll(graphGt.get(k));
				}
				for (String k : graphGtEquals.keySet()) {
					edgeCache.addAll(graphGtEquals.get(k));
				}
			}
			return edgeCache;
		}
		
		private Set<String> edgeCache = null;

		/* (non-Javadoc)
		 * @see org.openiaml.model.tests.DijkstraAlgorithm#getNeighbours(java.lang.Object)
		 */
		@Override
		public List<String> getNeighbours(String u) {
			List<String> a = graphGt.get(u);
			List<String> b = graphGtEquals.get(u);
			
			if (a == null) {
				if (b == null) {
					return new ArrayList<String>();
				} else {
					List<String> bc = cloneList(b);
					bc.remove(u);
					return bc;
				}
			} else {
				if (b == null) {
					List<String> ac = cloneList(a);
					ac.remove(u);
					return ac;
				} else {
					// make a copy
					List<String> ac = cloneList(a);
					ac.addAll(b);
					ac.remove(u);
					return ac;
				}
			}
		}

		/**
		 * @param a
		 * @return
		 */
		private List<String> cloneList(List<String> a) {
			List<String> r = new ArrayList<String>();
			r.addAll(a);
			return r;
		}

		/**
		 * We modify this to return a huge value for >=, and a small
		 * value for >, so hopefully we will prefer to find paths of
		 * > (which we are searching for)
		 */
		@Override
		public int distanceBetween(String from, String to) {
			List<String> gt = graphGt.get(from);
			if (gt != null && gt.contains(to)) {
				return 1;		// >
			}
			// it must be in the other graph
			List<String> eq = graphGtEquals.get(from);
			if (eq != null && eq.contains(to)) {
				return 1000;	// >=
			}
			// we should never get here
			throw new RuntimeException("There is no distance between " + from + " and " + to + ", as they are not connected.");
		}
		
		/**
		 * Compile the last path, but as a list of elements, rather than
		 * a string.
		 */
		protected List<String> compilePathList(String source, String target,
				Map<String, String> previous) {

			List<String> path = new ArrayList<String>();
			int i = 0;
			String cur = target;
			while (cur != source && cur != null && i < 50) {
				path.add(cur);
				cur = previous.get(cur);
				i++;
			}
			if (path.isEmpty()) {
				return null;
			}
			if (cur != null) {
				path.add(source);
			}
			
			return path;
			
		}

		@Override
		public String compilePath(String source, String target,
				Map<String, String> previous) {
			List<String> path = compilePathList(source, target, previous);
			if (path == null)
				return "[no path]";
			
			// now, reverse-compile this into a string
			String buf = "";
			for (int j = path.size() - 1; j >= 0; j--) {
				String e = path.get(j);
				buf += e;
				if (j != 0) {
					String next = path.get(j-1);	// it's next, since the list is backwards
					// is the link from prev to e a > or >= ?
					// > is first, since > is given the lowest value and
					// thus found first
					if (graphGt.get(e) != null && graphGt.get(e).contains(next)) {
						buf += " > ";
					} else if (graphGtEquals.get(e) != null && graphGtEquals.get(e).contains(next)) {
						buf += " >= ";
					} else {
						buf += " ??? ";
					}
				}
			}
			
			return buf;
		}
		
	}
	
	/**
	 * Represents a stratification statement, i.e. from (>|>=) to, because: reason (usually a rule name)
	 * @author jmwright
	 *
	 */
	protected class StratReason {

		public String from;
		public String to;
		public String reason;
		
		/**
		 * @param from
		 * @param to
		 * @param reason
		 */
		public StratReason(String from, String to, String reason) {
			this.from = from;
			this.to = to;
			this.reason = reason;
		}
		
	}
	
	protected class StratificationCycleCheckerWithExplanation extends StratificationCycleChecker {

		private List<StratReason> reasonGt;
		private List<StratReason> reasonGtEquals;
		
		/**
		 * Default constructor. Add the explanations for each graph map.
		 * 
		 * @param graphGt
		 * @param graphGtEquals
		 * @param reasonGt 
		 * @param reasonGtEquals 
		 */
		public StratificationCycleCheckerWithExplanation(
				Map<String, List<String>> graphGt,
				Map<String, List<String>> graphGtEquals,
				List<StratReason> reasonGt, 
				List<StratReason> reasonGtEquals) {
			super(graphGt, graphGtEquals);
			this.reasonGt = reasonGt;
			this.reasonGtEquals = reasonGtEquals;
			
		}

		@Override
		public String compilePath(String source, String target,
				Map<String, String> previous) {
			String buf = super.compilePath(source, target, previous);
			
			List<String> path = compilePathList(source, target, previous);
			if (path == null)
				return "[no path]";
			
			// now, reverse-compile this into a string
			String reasoning = "";
			for (int j = path.size() - 1; j >= 0; j--) {
				String e = path.get(j);
				if (j != 0) {
					String next = path.get(j-1);	// it's next, since the list is backwards
					// is the link from prev to e a > or >= ?
					// > is first, since > is given the lowest value and
					// thus found first
					if (graphGt.get(e) != null && graphGt.get(e).contains(next)) {
						// e > next
						reasoning += "- " + e + " > " + next + ": " + getReason(reasonGt, e, next) + "\n";
					} else if (graphGtEquals.get(e) != null && graphGtEquals.get(e).contains(next)) {
						reasoning += "- " + e + " >= " + next + ": " + getReason(reasonGtEquals, e, next) + "\n";
					} else {
						reasoning += "- " + e + " >= " + next + ": ???\n";
					}
				}
			}
			
			return buf + "\n" + reasoning;
			
		}

		/**
		 * Search the given list for an element from 'from' to 'to',
		 * and provide the reason provided.
		 * 
		 * @param list
		 * @param from
		 * @param to
		 * @return null if no reason found.
		 */
		private String getReason(List<StratReason> list, String from,
				String to) {
			for (StratReason r : list) {
				if (r.from.equals(from) && r.to.equals(to)) {
					return r.reason;
				}
			}
			return null;
		}
		
		
		
	}
	
}
