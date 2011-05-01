/**
 * 
 */
package org.openiaml.simplegmf.tests;

import org.eclipse.core.resources.IFile;
import org.openiaml.simplegmf.codegen.SimpleGMFCodeGenerator;

import junit.framework.TestCase;

/**
 * Tests the code generation functionality of SimpleGMF.
 * 
 * @author jmwright
 *
 */
public class SimpleGMFTestCase extends TestCase {

	/**
	 * Tries to create a new instance of ModelDocumentation and
	 * save it to a file. It also executes the actual documentation
	 * generation through OAW.
	 * 
	 * @throws Exception
	 */
	public void testDump() throws Exception {
		
		// TODO move EclipseProject into a generic testing framework
		IFile modelFile = null;
		
		// generate code
		SimpleGMFCodeGenerator codegen = new SimpleGMFCodeGenerator();
		codegen.generateCode(modelFile);
		
	}
	
}
