/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.codegen.DefaultRuntimeProperties;
import org.openiaml.model.tests.codegen.model0_1.SyncFieldApplicationElementProperty;

/**
 * Issue 81: Test that if we ask to include the runtime libraries
 * explicitly, that they are included as normal. 
 * 
 * <p>Otherwise tests everything in {@link SyncFieldApplicationElementProperty}.
 * 
 * @author jmwright
 *
 */
public class TestIncludeLibraries extends SyncFieldApplicationElementProperty {

	@Override
	protected Class<?> getRuntimeClass() {
		return TestIncludeLibraries.class;
	}

	@Override
	protected Map<String, String> getRuntimeProperties() {
		// get default properties (NOT from super.getRuntimeProperties())
		Map<String, String> result = new DefaultRuntimeProperties().getDefaultProperties();
		
		// we don't let the codegen test case use the settings from the super method
		result.put("include_runtime", "true");
		return result;
	}
	
	/**
	 * Check that the runtime files were actually copied over.
	 * 
	 */
	public void testRuntimeWasCopied() {
		IFile file = getProject().getFile("output/runtime/web/index.html");
		assertTrue("The runtime file '" + file + "' should have been copied over.", file.exists());
	}
	
}
