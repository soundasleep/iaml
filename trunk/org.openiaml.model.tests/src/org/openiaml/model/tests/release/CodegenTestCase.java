/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openiaml.model.tests.XmlTestCase;

/**
 * Various test cases for the codegen plugins.
 * 
 * @author jmwright
 *
 */
public class CodegenTestCase extends XmlTestCase {
	
	/**
	 * The list of extensions files to check.
	 * @see #testUniqueExtensions()
	 */
	protected List<String> EXTENSIONS = Arrays.asList(
			"../org.openiaml.model.codegen.php/src/metamodel/Extensions.ext",
			"../org.openiaml.model.codegen.php/src/template/GeneratorExtensions.ext"
	);
	
	/**
	 * Issue 182: check for duplicated extensions.
	 * 
	 * @see #EXTENSIONS
	 * @throws IOException
	 */
	public void testUniqueExtensions() throws IOException {
		
		Map<String, String> extensionsFound = new HashMap<String, String>();
		
		for (String file : EXTENSIONS) {
			String[] input = readFile(new File(file)).split("\n");
			
			for (String line : input) {
				// match all lines of format:
				// List[model::Type] safeName(model::GeneratedElement this) :
				
				if (line.trim().endsWith(":") && line.contains("(")) {
					
					// get the name of the extension
					String name = line.substring(0, line.lastIndexOf("("));
					{
						String bits[] = name.split(" ");
						name = bits[bits.length - 1];
					}
					
					// get the arguments
					String arguments = line.substring(line.lastIndexOf("(") + 1);
					arguments = arguments.substring(0, arguments.lastIndexOf(")")).trim();
					
					// ignore argument names
					{
						String a = "";
						String bits[] = arguments.split(" ");
						for (int i = 0; i < bits.length; i += 2) {
							a += bits[i] + " ";
						}
						arguments = a.trim().replace(" ", ", ");
					}
					
					String key = name + "(" + arguments + ")";
					if (extensionsFound.containsKey(key)) {
						// make sure it isn't in the map already
						fail("Found duplicate extension " + key + " in file " + extensionsFound.get(key));
					} else {
						// otherwise, put it in
						extensionsFound.put(key, file);
					}
				}
			}
		}
		
		// we must have found at least one
		assertFalse(extensionsFound.isEmpty());
		
	}
	
}
