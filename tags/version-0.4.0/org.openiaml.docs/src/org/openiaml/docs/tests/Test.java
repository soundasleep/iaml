/**
 * 
 */
package org.openiaml.docs.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openiaml.docs.modeldoc.EMFClass;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.model.model.visual.VisualPackage;

/**
 * @author jmwright
 *
 */
public class Test extends TestCase {

	public void testNewModel() throws Exception {
		
		// get the EClass from IAML
		EClass page = VisualPackage.eINSTANCE.getPage();
		
		EMFClass c = ModeldocFactory.eINSTANCE.createEMFClass();
		c.setTargetClass(page);

		{
			ResourceSet resourceSet = new ResourceSetImpl();
	        URI fileURI = URI.createFileURI(new File("test.modeldoc")
	                .getAbsolutePath());
	        Resource resource = resourceSet.createResource(fileURI);
	        resource.getContents().add(c);
	        resource.save(Collections.EMPTY_MAP);
		}
        
        // try loading it!
		EMFClass c2 = null;
		{
			ResourceSet resourceSet = new ResourceSetImpl();
	        URI fileURI = URI.createFileURI(new File("test.modeldoc")
	                .getAbsolutePath());
	        Resource resource = resourceSet.getResource(fileURI, true);
	        c2 = (EMFClass) resource.getContents().get(0);
		}
		
		assertEquals(page, c2.getTargetClass());
		System.out.println(c2.getTargetClass());
		System.out.println(((EClass) c2.getTargetClass()).getEAllContainments());
        
	}
	
	/**
	 * Load a model file directly.
	 * Assumes that it will only contain one element (and tests this with JUnit).
	 */
	protected static EObject loadModelDirectly(String filename) {
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(filename);
		Resource resource = resourceSet.getResource(uri, true);
		assertNotNull(resource);
		assertEquals("there should only be one contents in the model file", 1, resource.getContents().size());
		return resource.getContents().get(0);
	}
	
	/**
	 * Save the changed, inferred model to a file for later reference.
	 *
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @returns the generated model file
	 */
	protected File saveInferredModel(Resource resource) throws FileNotFoundException, IOException {
		// check that the inference folder exists
		File folder = new File("infer-output/");
		if (!(folder.exists() && folder.isDirectory())) {
			// make it
			assertTrue("Could not make output folder '" + folder + "'", folder.mkdir());
		}
		// it should now exist
		assertTrue(folder.exists());
		assertTrue(folder.isDirectory());
		
		File tempJavaFile = new File("infer-output/" + this.getClass().getSimpleName() + ".iaml");
		Map<?,?> options = resource.getResourceSet().getLoadOptions();
		resource.save(new FileOutputStream(tempJavaFile), options);
		System.out.println("inferred model saved to: " + tempJavaFile.getAbsolutePath());
		return tempJavaFile;
	}

	
}
