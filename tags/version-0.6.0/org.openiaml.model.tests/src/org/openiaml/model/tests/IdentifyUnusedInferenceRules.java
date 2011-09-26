/**
 * 
 */
package org.openiaml.model.tests;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.ModelLoader.ModelLoadException;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CachedModelLoader.IModelReloader;

/**
 * Issue 261: Identify and remove unused inference rules.
 * This test case iterates over all model instances in the tests,
 * executes inference on them, and identifies rule sources.
 * 
 * @author jmwright
 *
 */
public class IdentifyUnusedInferenceRules extends ModelInferenceTestCase  {
	
	private class RuleCount extends HashMap<String, Integer> {

		private static final long serialVersionUID = 1L;

		public void generatedRule(String r) {
			if (!containsKey(r)) {
				put(r, 0);
			}
			put(r, get(r) + 1);
		}
		
	}

	public void testIdentifyUnusedInferenceRules() throws IOException {

		RuleCount rc = new RuleCount();
		File f = new File("src/");
		recurseSearch(f, rc);
		
		// and now print out the contents
		FileWriter fw = new FileWriter("unused/used-rules.txt");
		for (String rule : rc.keySet()) {
			fw.write(rule);
			fw.write("\t");
			fw.write(Integer.toString(rc.get(rule)));
			fw.write("\n");
		}
		fw.close();
	
	}

	/**
	 * @param f
	 * @param rc
	 */
	private void recurseSearch(File f, RuleCount rc) {
		
		System.out.println("Searching " + f + "...");
		
		// find all *.iaml files
		for (final File modelFile : f.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".iaml");
			}
			
		})) {
			
			// try loading the file
			try {
				System.out.println("Loading " + modelFile + "...");
				IModelReloader reloader = new IModelReloader() {

					@Override
					public EObject reload() throws InferenceException,
							ModelLoadException {
						return ModelLoader.load(modelFile);
					}
					
				};
				
				EObject model = ModelLoader.load(modelFile);
				InternetApplication root = (InternetApplication) model;
				
				// try inferring it
				resetInferenceCache();
				infer(root, true, reloader);
				
				// now iterate over its contents				
				handleEObject(root, rc);
				TreeIterator<EObject> it = root.eAllContents();
				while (it.hasNext()) {
					handleEObject(it.next(), rc);
				}
			} catch (ModelLoadException e) {
				// ignore
				System.err.println("Could not load " + modelFile + ": " + e.getMessage());
			} catch (Exception e) {
				System.err.println("Could not infer " + modelFile + ": " + e.getMessage());
			}
			
		}

		// and then go into each sub-directory
		for (File dir : f.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory() && !pathname.isHidden();
			}
			
		})) {
			recurseSearch(dir, rc);
		}
	}

	/**
	 * @param next
	 * @param rc 
	 */
	private void handleEObject(EObject next, RuleCount rc) {
		if (next instanceof GeneratedElement) {
			GeneratedElement ge = (GeneratedElement) next;
			if (ge.getGeneratedRule() != null) {
				rc.generatedRule(ge.getGeneratedRule());
			}
		}
	}

}
