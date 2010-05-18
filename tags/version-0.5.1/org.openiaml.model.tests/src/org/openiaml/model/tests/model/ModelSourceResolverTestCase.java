/**
 * 
 */
package org.openiaml.model.tests.model;

import org.openiaml.model.tests.ModelSourceResolver;
import org.openiaml.model.tests.ModelTestCase;

import junit.framework.TestCase;

/**
 * Tests the {@link ModelSourceResolver} implementation.
 * 
 * @author jmwright
 *
 */
public class ModelSourceResolverTestCase extends TestCase {

	public void test1() {
		
		ModelSourceResolver resolver = ModelSourceResolver.getInstance();
		
		assertEquals(ModelTestCase.ROOT + "inference/model0_1/", 
				resolver.getModelPathForPackage("org.openiaml.model.tests.inference.model0_1"));

		assertEquals(ModelTestCase.ROOT + "codegen/model0_5_1/", 
				resolver.getModelPathForPackage("org.openiaml.model.tests.codegen.model0_5_1"));

		assertEquals(ModelTestCase.ROOT + "inference/", 
				resolver.getModelPathForPackage("org.openiaml.model.tests.inference"));

		assertEquals(ModelTestCase.ROOT + "codegen/", 
				resolver.getModelPathForPackage("org.openiaml.model.tests.codegen"));

		assertEquals(ModelTestCase.ROOT + "codegen/random/", 
				resolver.getModelPathForPackage("org.openiaml.model.tests.codegen.random"));

		try {
			resolver.getModelPathForPackage("org.openiaml.model.tests");
			fail("Should have thrown an exception");
		} catch (IllegalArgumentException e) {
			// expected
		}

	}
	
}
