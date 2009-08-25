/**
 *
 */
package org.openiaml.model.tests.drools;

import java.util.HashMap;
import java.util.Map;

import org.openiaml.model.drools.export.ExportDroolsJavaXml;
import org.openiaml.model.tests.inference.InferenceTestCase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Issue 100: Check that all rules have unique names.
 *
 * @author jmwright
 *
 */
public class CheckUniqueRuleNames extends InferenceTestCase {

	public void testUniqueNames() throws Exception {
		ExportDroolsJavaXml export = new ExportDroolsJavaXml();
		
		Map<String,Document> results = export.getRuleXmlDocuments();
		Map<String,String> ruleNames = new HashMap<String,String>();
		for (String filename : results.keySet()) {
			
			Document doc = results.get(filename);
			for (Element rule : xpath(doc, "//rule[@name]")) {
				String name = rule.getAttribute("name");
				
				if (ruleNames.containsKey(name)) {
					fail(filename + ": Rule '" + name + "' is not unique, previously defined in " + ruleNames.get(name));
				}
				ruleNames.put(name, filename);
			}

		}
		
	}
	
}
