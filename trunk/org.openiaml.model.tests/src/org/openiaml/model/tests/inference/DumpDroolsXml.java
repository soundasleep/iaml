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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import junit.framework.AssertionFailedError;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.drools.DroolsXmlDumper;
import org.openiaml.model.tests.DijkstraAlgorithm;
import org.openiaml.model.tests.InferenceTestCase;
import org.openiaml.model.tests.TestComposition;
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
	}
	
	/**
	 * Parse the given Java code and insert the results into
	 * the given node.
	 * 
	 * Generates:
	 * <pre>
	 * RunInstanceWire rw = handler.generatedRunInstanceWire(sw, sw, event, operation);
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
		
		NodeList statements = xpath(root, "statement");
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
		
		for (String f : results.keySet()) {
			
			String name = f.substring(f.lastIndexOf("/"));
			IFile out = project.getFile(name + ".xml");

			// who knows what format XmlDump is supplied in?
			// we will assume UTF-8 as the dumped XML is UTF-8
			InputStream source = new ByteArrayInputStream(results.get(f).getBytes("UTF-8")); 

			System.out.println("Writing " + out + "...");
			
			// load the created XML and replace the <rhs> with 
			// more XML (specific to our use in IAML)
			
			Document document = loadDocument(source);
			NodeList rhsList = xpath(document, "//rhs");
			
			for (int i = 0; i < rhsList.getLength(); i++) {
				Text originalNode = (Text) rhsList.item(i).getFirstChild();
				String sourceCode = originalNode.getData();
				originalNode.setData("");	// empty the node
				
				Element t = document.createElement("source");
				originalNode.getParentNode().appendChild(t);
				Text t2 = document.createTextNode(sourceCode);
				t.appendChild(t2);
				
				// lets create the statements here
				parseJava(document, originalNode.getParentNode(), sourceCode);
			}
			
			// work out the logic formula defined by each rule
			List<LogicRule> rules = investigateLogic(document, name);
			allRules.addAll(rules);
			
			// out.create(source, true, monitor);
			saveDocument(document, out.getLocation().toFile());

		}
		
		// output out the rules in a prolog format
		outputPrologStratification(allRules);
		
		// check each rule for composition
		checkRulesCompositionGraph(allRules);
		
		// find rule cycles
		findStratCycleRules(allRules);
		
		refreshProject();
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
		
		Set<String> sources = new HashSet<String>();
		
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
						addIntoMap(graphGt, t.term.name, b.name);
						addIntoMap(graphGt, t.term.name, b.name + "!target");
						addIntoMap(graphGt, t.term.name + "!target", b.name); // although we are not starting from source!target, we still need this as an edge in the graph
						//graphGtTargets.add(b.name + "!target");
						sources.add(t.term.name);
						sources.add(b.name);
					} else {
						LogicTerm t = (LogicTerm) h;
						addIntoMap(graphGtEq, t.name, b.name);
						addIntoMap(graphGtEq, t.name, b.name + "!target");
						addIntoMap(graphGt, t.name + "!target", b.name);
						//graphGtEqTargets.add(b.name + "!target");
						sources.add(t.name);
						sources.add(b.name);
					}
				}
			}
		}
		
		// now for every possible class, see if there is a cycle
		for (String from : sources) {
			StratificationCycleChecker scc = new StratificationCycleChecker(graphGt, graphGtEq);
			int d = scc.dijkstra(from, from + "!target");
			String path = scc.getLastPath();
			System.out.println(from + " -> " + from + "!target strat: " + d + ": " + path);
		}
		
	}

	/**
	 * @param doubleMap
	 * @param key
	 * @param value
	 */
	private void addIntoMap(Map<String, List<String>> doubleMap, String key,
			String value) {
		List<String> r = doubleMap.get(key);
		if (r == null) {
			doubleMap.put(key, new ArrayList<String>());
		}
		doubleMap.get(key).add(value);
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
		Set<String> allUnique = new HashSet<String>();
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
		Set<String> s = new HashSet<String>();
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
		
		public LogicTerm(String name) {
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
		
		public LogicNotTerm(LogicTerm term) {
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
		NodeList rules = xpath(document, "//rule");
		for (int i = 0; i < rules.getLength(); i++) {
			LogicRule logic = new LogicRule();
			Element rule = (Element) rules.item(i);
			
			Element lhs = xpathFirst(rule, "lhs");
			Element rhs = xpathFirst(rule, "rhs");
			
			for (int j = 0; j < lhs.getChildNodes().getLength(); j++) {
				Node p2 = lhs.getChildNodes().item(j);
				if (p2 instanceof Element) {
					Element p = (Element) p2;
					if (p.getNodeName().equals("pattern")) {
						LogicTerm t = new LogicTerm(p.getAttribute("object-type"));
						logic.head.add(t);
					} else if (p.getNodeName().equals("not")) {
						Element actualP = xpathFirst(p, "pattern"); 
						LogicTerm t = new LogicTerm(actualP.getAttribute("object-type"));
						logic.head.add(new LogicNotTerm(t));
					}
				}
			}
			
			// find the handler rules
			// we assume that rule bodies only generate elements using
			// handler.generatedXXX(...)
			NodeList generatedElements = xpath(rhs, "statement/assignment/statement/variable[@name='handler']/method");
			for (int j = 0; j < generatedElements.getLength(); j++) {
				Element g = (Element) generatedElements.item(j);
				assertEquals("method", g.getNodeName());
				String methodName = g.getAttribute("name");;
				if (methodName.startsWith("generated")) {
					methodName = methodName.substring("generated".length());
				}
				LogicTerm t = new LogicTerm(methodName);
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

	/*
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

		
		
	}
	
}
