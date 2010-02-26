/**
 * 
 */
package org.openiaml.model.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.tests.CachedModelLoader.IModelReloader;

/**
 * @author jmwright
 *
 */
public interface IProvidesInferenceEngine {

	/**
	 * <p>Create a new instance of the inference engine.</p>
	 * 
	 * @return
	 */
	public CreateMissingElementsWithDrools getInferenceEngine(ICreateElements handler, boolean trackInsertions, final IModelReloader reloader);

	/**
	 * Save the changed, inferred model to a file for later reference.
	 *
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @returns the generated model file
	 */
	public File saveInferredModel(Resource resource) throws FileNotFoundException, IOException;
	
}
