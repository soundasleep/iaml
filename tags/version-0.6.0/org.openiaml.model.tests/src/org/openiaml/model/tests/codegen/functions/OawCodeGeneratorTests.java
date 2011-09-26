/**
 * 
 */
package org.openiaml.model.tests.codegen.functions;

import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.TestCase;

import org.openiaml.model.codegen.php.OawCodeGenerator;
import org.openiaml.model.tests.release.PluginsTestCase;

/**
 * Test the static methods in {@link OawCodeGenerator}.
 * 
 * @author jmwright
 */
public class OawCodeGeneratorTests extends TestCase {

	public void testThrowException() {
		OawCodeGenerator.resetKeyToExceptionMap();
		try {
			OawCodeGenerator.throwException("Test message");
			fail("Should have thrown an exception");
		} catch (RuntimeException e) {
			// expected
			// the '@key' annotation is necessary to track it later
			assertTrue(e.getMessage().contains("Test message"));

			// we will have a native RuntimeException in the cause
			assertNotNull(e.getCause());
			assertEquals("Test message", e.getCause().getMessage());
			assertFalse(e instanceof NullPointerException);	// shouldn't be a NPE
		}
	}
	
	public void testIamlVersion() throws FileNotFoundException, IOException {
		assertEquals(PluginsTestCase.getVersion(), OawCodeGenerator.getIamlVersion());
	}

}
