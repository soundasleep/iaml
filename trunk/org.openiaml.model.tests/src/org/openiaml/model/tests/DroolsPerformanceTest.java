/**
 * 
 */
package org.openiaml.model.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.openiaml.model.ModelLoader.ModelLoadException;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.VisualPackage;
import org.openiaml.model.tests.CachedModelLoader.IModelReloader;

/**
 * <p>In order for this test to execute and keep track of model properties
 * (e.g. number of source/target elements), you <em>must</em> make 
 * {@link ModelInferenceTestCase} extend {@model ModelTestCaseWithProperties}.</p>
 * 
 * @author jmwright
 *
 */
public class DroolsPerformanceTest extends ModelInferenceTestCase implements IModelReloader {

	/**
	 * Override this method to prevent NullPointerExceptions in saving models
	 * with no resource.
	 */
	@Override
	public File saveInferredModel(Resource resource) throws FileNotFoundException,
			IOException {
		// do nothing; prevents NPE
		return null;
	}

	/**
	 * Gradually create bigger and bigger models for inference
	 * to work against.
	 * @throws InferenceException 
	 * 
	 */
	public void test() throws Exception {
		
		for (int i = 0; i < 90000; i += 300) {
			final int i_copy = i;

			IModelReloader reloader = new IModelReloader() {
				@Override
				public EObject reload() throws InferenceException {
					try {
						InternetApplication root = (InternetApplication) DroolsPerformanceTest.this.reload();
						EcoreInferenceHandler handler = CachedModelInferer.getInstance().createHandler(root.eResource());
						
						// add the pages etc
						for (int j = 0; j < i_copy; j++) {
							Frame page = handler.createFrame(root);
							page.setName("test page " + j);
							
							/*
							Button button = (Button) handler.createElement(page, VisualPackage.eINSTANCE.getButton(), ModelPackage.eINSTANCE.getInternetApplication_Children());
							button.setName("test button " + j);
							*/
							
							InputTextField text = (InputTextField) handler.createElement(page, VisualPackage.eINSTANCE.getInputTextField(), ModelPackage.eINSTANCE.getApplicationElementContainer_Children());
							text.setName("test field " + j);
							
						}
						
						return root;
					} catch (ModelLoadException e) {
						throw new InferenceException(e);
					}
				}
			};
			
			InternetApplication root = (InternetApplication) reloader.reload();
			
			infer(root, false, reloader);
		}
		
	}
	
	protected boolean doPropertiesInvestigation() {
		return false;	// do not investiate
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.ModelInferenceTestCase.IModelReloader#reload()
	 */
	@Override
	public EObject reload() throws InferenceException, ModelLoadException {
		return CachedModelLoader.getInstance().loadModelDirectly("src/org/openiaml/model/tests/blank.iaml");
	}
	
}
