/**
 * 
 */
package org.openiaml.model.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.openiaml.emf.SoftCache;
import org.openiaml.model.ModelLoader.ModelLoadException;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.ICreateElements;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CachedModelLoader.IModelReloader;

/**
 * @author jmwright
 *
 */
public class CachedModelInferer {

	private static CachedModelLoader loader = CachedModelLoader.getInstance();
	
	private CachedModelInferer() {
		// private
	}
	
	/**
	 * When inference is done, the last inferred model is saved to this file.
	 */
	protected File inferredModel;
	
	/**
	 * Perform inference on a loaded model.
	 *
	 * @see CreateMissingElementsWithDrools#create(EObject, boolean)
	 * @param logRuleSource Log the rule source of inserted elements.
	 * @return
	 * @throws InferenceException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public InternetApplication infer(IProvidesInferenceEngine provider, InternetApplication root, boolean logRuleSource, IModelReloader reloader) throws InferenceException, FileNotFoundException, IOException {
		
		// we now try to do inference
		Resource resource = root.eResource();
		if (resource == null) {
			throw new IllegalArgumentException("EObject '" + root + "' has a null resource.");
		}
		
		ICreateElements handler = createHandler(resource);
		CreateMissingElementsWithDrools ce = provider.getInferenceEngine(handler, false, reloader);
		ce.create(root, logRuleSource, createMonitor());

		// write out this inferred model for reference
		inferredModel = provider.saveInferredModel(resource);

		return root;
	}
	
	private static Map<Class<?>, File> inferCache = new HashMap<Class<?>, File>();
	
	/**
	 * Load a model file and perform inference on it.
	 * This method also sees if we have got a cached model; if
	 * not, it loads and infers it, then saves the inferred model
	 * location to the cache.
	 *
	 * @see CreateMissingElementsWithDrools#create(EObject, boolean)
	 * @param logRuleSource Log the rule source of inserted elements.
	 * @return
	 * @throws Exception
	 */
	public InternetApplication loadAndInfer(IProvidesInferenceEngine provider, final Class<?> loadClass, final boolean logRuleSource) throws Exception {
		if (!inferCache.containsKey(loadClass)) {
			logTimed("infer: loading model");
			
			// reload
			InternetApplication root = loader.loadDirectly(loadClass, logRuleSource);
			
			// we now try to do inference
			Resource resource = root.eResource();
			if (resource == null) {
				throw new IllegalArgumentException("EObject '" + root + "' has a null resource.");
			}
			
			ICreateElements handler = createHandler(resource);
			CreateMissingElementsWithDrools ce = provider.getInferenceEngine(handler, false, new IModelReloader() {

				@Override
				public EObject reload() throws InferenceException {
					try {
						return loader.loadDirectly(loadClass, logRuleSource);
					} catch (Exception e) {
						throw new InferenceException(e);
					}
				}
				
			});

			logTimed("infer: performing inference");
			ce.create(root, logRuleSource, createMonitor());
	
			// write out this inferred model for reference
			logTimed("infer: writing out inferred model");
			inferredModel = provider.saveInferredModel(resource);
			
			// put this model down in the cache
			logTimed("infer: saving to cache");
			inferCache.put(loadClass, inferredModel);
			
			// save a copy in the model cache
			modelCache.put(loadClass, root);
			
			logTimed("infer: inference complete");
			return root;
		} else {
			// load it from the given file - it will be inferred already
			System.out.println("Loaded model for '" + loadClass + "' directly from cache '" + inferCache.get(loadClass) + "'");
			
			inferredModel = inferCache.get(loadClass); 
			
			return modelCache.get(loadClass);
		}
	}
	
	/**
	 * Load the model file (.iaml) for this given
	 * test class, and do inference.
	 *
	 * @see #loadAndCodegen(String)
	 * @param class1 The test class to load a model for.
	 * @param logRuleSource Log the rule source of inserted elements.
	 * @return the loaded and inferred InternetApplication
	 * @throws ModelLoadException 
	 * @throws IOException 
	 * @throws InferenceException 
	 * @throws FileNotFoundException 
	 */
	public InternetApplication loadAndInfer(IProvidesInferenceEngine provider, 
			final String filename, boolean logRuleSource) throws ModelLoadException, FileNotFoundException, InferenceException, IOException {
		return infer(provider, (InternetApplication) loader.loadModelDirectly(filename), logRuleSource,
				new IModelReloader() {

					@Override
					public EObject reload() throws InferenceException {
						try {
							return loader.loadModelDirectly(filename);
						} catch (ModelLoadException e) {
							throw new InferenceException(e);
						}
					}
			
		});
	}

	/**
	 * Load a model file and perform inference on it.
	 * @throws ModelLoadException 
	 * @throws IOException 
	 * @throws InferenceException 
	 * @throws FileNotFoundException 
	 */
	public InternetApplication loadAndInfer(IProvidesInferenceEngine provider, final String modelFile) throws ModelLoadException, FileNotFoundException, InferenceException, IOException {
		InternetApplication root = (InternetApplication) loader.loadModelDirectly(modelFile);
		return infer(provider, root, false, new IModelReloader() {

			@Override
			public EObject reload() throws InferenceException {
				try {
					return loader.loadModelDirectly(modelFile);
				} catch (ModelLoadException e) {
					throw new InferenceException(e);
				}
			}
			
		});
	}
	
	private IProgressMonitor createMonitor() {
		return new NullProgressMonitor();
	}

	/**
	 * Currently an empty method.
	 * @param string
	 */
	private void logTimed(String string) {
		// empty
	}

	private static final ModelCache modelCache = new ModelCache();
	
	/**
	 * We use a soft reference model cache to store model results. This way,
	 * if we run out of memory, model files can be discarded.
	 * 
	 * @author jmwright
	 *
	 */
	private static class ModelCache extends SoftCache<Class<?>, InternetApplication> {

		/**
		 * If the model cache reference does not exist, we load it through
		 * {@link ModelInferenceTestCase#loadModelDirectly(String)}.
		 * 
		 * @see org.openiaml.model.tests.SoftCache#retrieve(java.lang.Object)
		 */
		@Override
		public InternetApplication retrieve(Class<?> input) {
			try {
				return (InternetApplication) loader.loadModelDirectly(inferCache.get(input).getAbsolutePath());
			} catch (ModelLoadException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
	}

	/**
	 * Create an {@link ICreateElements} handler that can be used
	 * to modify the model.
	 * 
	 * @return
	 */
	public EcoreInferenceHandler createHandler(Resource resource) {
		EcoreInferenceHandler handler = new EcoreInferenceHandler(resource);
		return handler;
	}

	private static CachedModelInferer instance;
	
	public static CachedModelInferer getInstance() {
		if (instance == null) {
			instance = new CachedModelInferer();
		}
		return instance;
	}

	/**
	 * Get the file representing the saved post-inference model.
	 *
	 * @return
	 */
	public File getInferredModel() {
		return inferredModel;
	}
	
}
