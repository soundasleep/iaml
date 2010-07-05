/**
 * 
 */
package org.openiaml.emf.properties.tests;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.library.ReferencesCycles;

/**
 * @author jmwright
 *
 */
public class TestReferencesCycles extends TestCase implements IEMFElementSelector {

	/**
	 * Load a model file directly.
	 * Assumes that it will only contain one element (and tests this with JUnit).
	 */
	protected EObject loadModelDirectly(String filename) {
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(filename);
		Resource resource = resourceSet.getResource(uri, true);
		assertNotNull(resource);
		assertEquals("there should only be one contents in the model file", 1, resource.getContents().size());
		return resource.getContents().get(0);
	}
	
	/**
	 * This model should have exactly 6 cycles.
	 */
	public void testBigInfiniteLoop2() {
		ReferencesCycles rc = new ReferencesCycles("references cycles", this);
		EObject model = loadModelDirectly("tests/BigInfiniteLoop-2.iaml");
		Object result = rc.evaluate(model);
		assertTrue("Result should be Number, is: " + result.getClass(), Number.class.isInstance(result));
		
		assertEquals(6, (Number) result);
	}

	@Override
	public boolean ignoreAttribute(EAttribute ref) {
		return false;
	}

	@Override
	public boolean ignoreClass(EClass ref) {
		return false;
	}

	@Override
	public boolean ignoreReference(EReference ref) {
		return false;
	}
	
}
