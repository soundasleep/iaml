/**
 * 
 */
package org.openiaml.model.tests.drools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.tests.XmlTestCase;

/**
 * Issue 122: Check that each drools rule has a separate package declaration.
 * 
 * @author jmwright
 *
 */
public class DroolsPackageTest extends XmlTestCase {
	
	public static final String DROOLS_PLUGIN_ROOT = "../org.openiaml.model.drools/";
	
	public void testPackages() throws Exception {
		
		DroolsInferenceEngine engine = 
			new CreateMissingElementsWithDrools(null, false);
		
		Map<String,String> foundPackages = new HashMap<String,String>();
		
		for (String file : engine.getRuleFiles()) {
			// check file
			String input = readFile(new File(DROOLS_PLUGIN_ROOT + file));
			String[] split = input.split("\n");
			
			for (String line : split) {
				line = line.trim();			
				if (line.startsWith("package ")) {
					// remove any leading comments
					if (line.contains("#")) {
						line = line.substring(0, line.indexOf("#")).trim();
					}
					if (line.contains("//")) {
						line = line.substring(0, line.indexOf("//")).trim();
					}
					
					if (foundPackages.containsKey(line)) {
						fail(file + ": Package declaration '" + line + "' was also found in " + foundPackages.get(line));
					} else {
						foundPackages.put(line, file);
					}
				}
			}
			
		}
		
		assertNotSame(0, foundPackages.keySet().size());
		
	}
	
}
