/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime.server;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Allows for the execution of client-side Javascript from the
 * server. (Mostly a big hack.)
 * 
 * @author jmwright
 *
 */
public abstract class PhpCodegenTestCase extends CodegenTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(PhpCodegenTestCase.class, getAbsolutePathRoot() + ROOT + "codegen/runtime/server/PhpCodegenTestCase.iaml");
		hasStarted = false;
	}
	
	/**
	 * Evaluate the given script and assert that it returns the given
	 * result. The result must be a string.
	 * 
	 * @throws Exception 
	 */
	public void assertPhpResult(String expected, String script) throws Exception {
		assertPhpResult(expected, "", script);
		
	}
	
	/**
	 * 
	 * @param init pre-init code
	 * @throws Exception 
	 */
	public void assertPhpResult(String expected, String init, String script) throws Exception {
		executePhp(init + "echo " + script + ";");
		assertEquals(expected, getPageSource());
	}
	
	/**
	 * @throws Exception 
	 */
	public void assertPhpResult(Boolean expected, String script) throws Exception {
		assertPhpResult(expected, "", script);
	}

	/**
	 * @throws Exception 
	 */
	public void assertPhpResult(Boolean expected, String init, String script) throws Exception {
		executePhp(init + "echo (" + script + ") ? 'true' : 'false';");
		assertEquals(expected.toString(), getPageSource());
	}

	private boolean hasStarted = false;
	
	/**
	 * Execute the given Javascript. Each call to this method
	 * executes in a separate instance; so, if passing variables, 
	 * an entire script must be provided in the parameter.
	 * 
	 * @param string The Javascript code to execute
	 */
	public void executePhp(String string) throws Exception {
		try {
			// create a new page with our target javascript
			long random = new Random().nextLong();
			String filename = "output/runtime-js-test" + random + ".php";
			IFile runtime = getProject().getFile(filename);
			if (runtime.exists()) {
				runtime.delete(true, new NullProgressMonitor());
			}
			InputStream content = new ByteArrayInputStream(("<?php require(\"" + CONFIG_RUNTIME + "/global.php\"); " + string + "").getBytes());
			
			runtime.create(content, true, new NullProgressMonitor());
			if (hasStarted) {
				gotoPage(filename);	
			} else {
				beginAt(filename);
				hasStarted = true;
			}
			
		} catch (RuntimeException e) {
			throw new RuntimeException("Could not execute '" + string + ": " + e.getMessage(), e);
		}
		
	}
	
}
