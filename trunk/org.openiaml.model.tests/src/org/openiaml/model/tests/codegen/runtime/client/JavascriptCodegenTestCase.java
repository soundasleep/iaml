/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime.client;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.openiaml.model.tests.CodegenTestCase;
import org.openiaml.model.tests.ModelSourceResolver;

/**
 * Allows for the execution of client-side Javascript from the
 * server. (Mostly a big hack.)
 * 
 * @author jmwright
 *
 */
public abstract class JavascriptCodegenTestCase extends CodegenTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(JavascriptCodegenTestCase.class, ModelSourceResolver.getInstance().getAbsolutePathRoot() + ROOT + "codegen/runtime/client/JavascriptCodegenTestCase.iaml");
		hasStarted = false;
	}
	
	/**
	 * Evaluate the given script and assert that it returns the given
	 * result. The result must be a string.
	 * 
	 * @param expected the expected string result
	 * @param script the Javascript code to execute
	 * @throws Exception 
	 */
	public void assertJavascriptResult(String expected, String script) throws Exception {
		assertJavascriptResult(expected, "", script);
		
	}
	
	/**
	 * 
	 * @param init pre-init code
	 * @throws Exception 
	 */
	public void assertJavascriptResult(String expected, String init, String script) throws Exception {
		setExpectedJavaScriptAlert(expected);
		executeJavascript(init + "alert(" + script + ");");		
	}
	
	/**
	 * Evaluate the given script and assert that it returns the given
	 * boolean value. This is converted into a string.
	 * 
	 * @param expected the expected boolean result (translated into string)
	 * @param script the Javascript code to execute
	 * @see #assertJavascriptResult(String, String)
	 * @throws Exception 
	 */
	public void assertJavascriptResult(Boolean expected, String script) throws Exception {
		assertJavascriptResult(expected.toString(), "", script);
	}

	/**
	 * @throws Exception 
	 */
	public void assertJavascriptResult(Boolean expected, String init, String script) throws Exception {
		assertJavascriptResult(expected.toString(), init, script);
	}

	private boolean hasStarted = false;
	
	/**
	 * Execute the given Javascript. Each call to this method
	 * executes in a separate instance; so, if passing variables, 
	 * an entire script must be provided in the parameter.
	 * 
	 * @param string The Javascript code to execute
	 */
	public void executeJavascript(String string) throws Exception {
		try {
			// create a new page with our target javascript
			long random = new Random().nextLong();
			String filename = "output/runtime-js-test" + random + ".html";
			IFile runtime = getProject().getFile(filename);
			if (runtime.exists()) {
				runtime.delete(true, new NullProgressMonitor());
			}
			InputStream content = new ByteArrayInputStream(("<html><script src=\"" + CONFIG_WEB + "js/default.js\"></script><script>" + string + "</script></html>").getBytes());
			
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
