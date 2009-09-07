/**
 * 
 */
package org.openiaml.model.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;
import org.openiaml.model.inference.EcoreInferenceHandler;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.visual.VisualPackage;

/**
 * @author jmwright
 *
 */
public class DroolsPerformanceTest extends ModelInferenceTestCase {

	@Override
	protected File saveInferredModel(Resource resource) throws FileNotFoundException,
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
			for (int k = 0; k < 10; k++) {
			InternetApplication root = (InternetApplication) loadModelDirectly("src/org/openiaml/model/tests/blank.iaml");
			EcoreInferenceHandler handler = createHandler(root.eResource());
			for (int j = 0; j < i; j++) {
				Page page = handler.createPage(root);
				page.setName("test page " + j);
				
				/*
				Button button = (Button) handler.createElement(page, VisualPackage.eINSTANCE.getButton(), ModelPackage.eINSTANCE.getInternetApplication_Children());
				button.setName("test button " + j);
				*/
				
				InputTextField text = (InputTextField) handler.createElement(page, VisualPackage.eINSTANCE.getInputTextField(), ModelPackage.eINSTANCE.getInternetApplication_Children());
				text.setName("test field " + j);
				
			}
			loadAndInfer(root, false);
			}
		}
		
	}
	
}
