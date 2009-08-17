/**
 * 
 */
package org.openiaml.model.drools.export;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.inference.InferenceException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Extends {@link ExportDroolsXml} to also generate the RHS
 * Java code into XML.
 * 
 * @author jmwright
 *
 */
public class ExportDroolsJavaXml extends ExportDroolsXml {

	/**
	 * Dump all the rule files into XML using XmlDumper, into a map
	 * of source files to generated XML strings.
	 * 
	 * @see org.drools.xml.XmlDumper
	 * @see CreateMissingElementsWithDrools#getRuleFiles()
	 * @return a map of filename to XML strings
	 * @throws InferenceException 
	 */
	public Map<String,String> getRuleXmls() throws InferenceException {
		Map<String,String> result = super.getRuleXmls();
		
		return result;
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

	
}
