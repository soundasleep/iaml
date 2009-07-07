/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;
import java.io.FileWriter;

import org.openiaml.model.tests.XmlTestCase;

/**
 * Simple tests to ensure that all copies of .ecores across the
 * plugin are identical.
 * 
 * @author jmwright
 *
 */
public class EcoreTestCase extends XmlTestCase {

	/**
	 * Check the ecore stored in oaw.
	 * @throws Exception 
	 */
	public void testOawEcore() throws Exception {
		File source = new File("../org.openiaml.model/model/iaml.ecore");
		File target = new File("../org.openiaml.model.codegen.oaw/src/metamodel/iaml-current.ecore");
		
		assertFileExists(source);
		assertFileExists(target);
		
		String sourceString = readFile(source);
		String targetString = readFile(target);
		
		if (!sourceString.equals(targetString)) {
			// copy over the file manually
			if (source.lastModified() < target.lastModified()) {
				fail("Cannot copy over the OAW ecore files: the target file '" + target + "' has been changed since the source file '" + source + "'");
			}
			
			assertTrue("Could not delete '" + target + "'", target.delete());
			
			FileWriter fw = new FileWriter(target);
			fw.write(sourceString);
			fw.close();
			
			System.out.println("Copied '" + source + "' to '" + target + "'");
		}
		
	}
	
}
