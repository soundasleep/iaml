/**
 * 
 */
package org.openiaml.model.tests.inference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.jaxen.JaxenException;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.tests.codegen.CodegenWebTestCase;

import ca.ecliptical.emf.xpath.EMFXPath;

/**
 * @author jmwright
 *
 */
public abstract class InferenceTestCase extends CodegenWebTestCase {

	protected InternetApplication root;
	protected Resource resource;
	
	/**
	 * Load a model file directly.
	 * Assumes that it will only contain one element (and tests this with JUnit).
	 */
	protected EObject loadModelDirectly(String filename) {
		ResourceSet resourceSet = new ResourceSetImpl(); 
		URI uri = URI.createFileURI(filename);
		resource = resourceSet.getResource(uri, true);
		assertNotNull(resource);
		assertEquals("there should only be one contents in the model file", resource.getContents().size(), 1);
		return resource.getContents().get(0);
	}

	/**
	 * Perform an XPath-like query on an EMF object
	 * 
	 * @param root
	 * @param query
	 * @return
	 * @throws JaxenException
	 */
	public static List<Object> query(final EObject root, String query) throws JaxenException {
		EMFXPath xpath = new EMFXPath(query);
		xpath.addNamespace("iaml", ModelPackage.eNS_URI);
		xpath.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		return xpath.selectNodes(root);
	}
	
	/**
	 * Helper method: print out a list of objects
	 * @param obj
	 */
	protected void dump(List<Object> obj) {
		for (Object o : obj) 
			EMFXPath.dump(o, System.out);
		System.out.println("-");
	}

	/**
	 * Helper method: print out an objects
	 * @param obj
	 */
	protected void dump(Object o) {
		EMFXPath.dump(o, System.out);
	}
	
	/**
	 * Helper method: perform a query, but assert that there is only
	 * one result returned, and it is of type EObject
	 * 
	 * @param root
	 * @param query
	 * @return
	 * @throws JaxenException 
	 */
	protected EObject queryOne(EObject root, String query) throws JaxenException {
		List<Object> q = query(root, query);
		assertEquals("queryOne for '" + query + "' had no results", 1, q.size());
		return (EObject) q.get(0);
	}

	/**
	 * Save the changed, inferred model to a file for later reference.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @returns the generated model file
	 */
	protected File saveInferredModel() throws FileNotFoundException, IOException {
		File tempJavaFile = new File("infer-output/" + this.getClass().getSimpleName() + ".iaml");
		Map<?,?> options = resource.getResourceSet().getLoadOptions();
		resource.save(new FileOutputStream(tempJavaFile), options);
		return tempJavaFile;
	}
}
