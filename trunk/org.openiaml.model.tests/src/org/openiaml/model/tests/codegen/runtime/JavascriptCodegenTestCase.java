/**
 * 
 */
package org.openiaml.model.tests.codegen.runtime;

import java.io.StringBufferInputStream;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

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
		root = loadAndCodegen(JavascriptCodegenTestCase.class);
		hasStarted = false;
	}
	
	/**
	 * Evaluate the given script and assert that it returns the given
	 * result. The result must be a string.
	 * 
	 * @throws Exception 
	 */
	public void assertJavascriptResult(String expected, String script) throws Exception {
		setExpectedJavaScriptAlert(expected);
		executeJavascript("alert(" + script + ");");
		
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
		// create a new page with our target javascript
		IFile runtime = getProject().getFile("output/runtime-js-test.html");
		if (runtime.exists()) {
			runtime.delete(true, monitor);
		}
		StringBufferInputStream content = new StringBufferInputStream("<html><script src=\"" + CONFIG_WEB + "js/default.js\"></script><script>" + string + "</script></html>");
		
		runtime.create(content, true, monitor);
		if (hasStarted) {
			gotoPage("output/runtime-js-test.html");	
		} else {
			beginAt("output/runtime-js-test.html");
			hasStarted = true;
		}
		
	}
	
}
