/**
 * 
 */
package org.openiaml.model.tests;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.ModelLoader.ModelLoadException;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.InternetApplication;

/**
 * @author jmwright
 *
 */
public class CachedModelLoader {
	
	private static ModelSourceResolver resolver = ModelSourceResolver.getInstance();

	private CachedModelLoader() {
		// cannot be called by clients
	}

	/**
	 * Load the model file (.iaml) for this given
	 * test class, but don't do inference.
	 *
	 * @see #getModelFileForClass(Class)
	 * @see #loadModelDirectly(Class)
	 * @param class1 The test class to load a model for.
	 * @param logRuleSource Log the rule source of inserted elements.
	 * @return the loaded and inferred InternetApplication
	 */
	public InternetApplication loadDirectly(
			Class<?> class1, boolean logRuleSource) throws Exception {
		
		return (InternetApplication) loadModelDirectly(resolver.getModelFileForClass(class1));

	}

	/**
	 * Load load the model file (.iaml) for this given
	 * test class, but don't do inference.
	 *
	 * @see #loadAndInfer(Class)
	 * @param class1 The test class to load a model for.
	 * @return the loaded and inferred InternetApplication
	 */
	public InternetApplication loadDirectly(
			Class<?> class1) throws Exception {
		return loadDirectly(class1, false);
	}
	
	/**
	 * Load a model file directly.
	 * Assumes that it will only contain one element (and tests this with JUnit).
	 * @throws ModelLoadException 
	 */
	public EObject loadModelDirectly(String filename) throws ModelLoadException {
		EObject model = ModelLoader.load(filename);
		return model;
	}
	
	/**
	 * If we are executing the model inference process multiple times, we need
	 * to be able to reload it from the disk.
	 * 
	 * @author jmwright
	 */
	public interface IModelReloader {
		
		/**
		 * Reload the model.
		 * 
		 * @return
		 * @throws ModelLoadException 
		 */
		public EObject reload() throws InferenceException, ModelLoadException;
		
	}

	private static CachedModelLoader instance;

	/**
	 * @return
	 */
	public static CachedModelLoader getInstance() {
		if (instance == null) {
			instance = new CachedModelLoader();
		}
		return instance;
	}
	
}
