/**
 * 
 */
package org.openiaml.model.tests.release;

import java.io.File;

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
		
		assertEquals("The source and target ecore files should be identical", sourceString, targetString);
	}
	
}
