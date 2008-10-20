/**
 * 
 */
package org.openiaml.model.tests.inference;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.tests.JetWebTestCase;

/**
 * @author jmwright
 *
 */
public class SimpleTestCase extends TestCase {

	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		String modelFile = "models/simple.iaml";
		EObject model = loadModelDirectly(modelFile);
		assertTrue("the model file '" + modelFile + "' should be of type InternetApplication", model instanceof InternetApplication);
		assertNotNull(model);
		
		root = (InternetApplication) model;
	}

	protected void tearDown() throws Exception {
		// empty
	}
	
	/**
	 * Load a model file directly.
	 * Assumes that it will only contain one element (and tests this with JUnit).
	 */
	protected EObject loadModelDirectly(String filename) {
		ResourceSet resourceSet = new ResourceSetImpl(); 
		URI uri = URI.createFileURI(filename);
		Resource resource = resourceSet.getResource(uri, true);
		assertNotNull(resource);
		assertEquals("there should only be one contents in the model file", resource.getContents().size(), 1);
		return resource.getContents().get(0);
	}
	
	public void testContents() {
		// it should have pages
		assertEquals(root.getChildren().size(), 3);
		assertTrue(root.getChildren().get(0) instanceof Page);
		assertTrue(root.getChildren().get(1) instanceof Page);
		assertTrue(root.getChildren().get(2) instanceof Page);
	}
	
}
