/**
 * 
 */
package org.openiaml.model.tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.ModelLoader.ModelLoadException;
import org.openiaml.model.drools.CreateMissingElementsWithDrools;
import org.openiaml.model.drools.ICreateElementsFactory;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.model.tests.CachedModelLoader.IModelReloader;

/**
 * <p>Extends {@link ModelTestCase} to also check for EMF properties on the
 * intitial and final (completed) models.</p>
 * 
 * <p>When executing model inference, the inference process will be repeated 
 * 12 times. Ten of these iterations will be timed, one will be to remove any 
 * initial cost in completing the model, and one will be used to identify the
 * final properties of the model, as in {@link ModelPropertiesInvestigator}.</p>
 * 
 * <p>Essentially, the supertype of any class that extends {@link ModelTestCase}
 * can replace it with this class, and obtain the properties investigation for free.</p>
 * 
 * @see ModelPropertiesInvestigator
 * @author jmwright
 *
 */
public abstract class ModelTestCaseWithProperties extends CodegenTestCase {

	/**
	 * Should the EMF properties investigation be 
	 * executed for these test cases?
	 * 
	 * @return 
	 */
	protected boolean doPropertiesInvestigation() {
		return true;
	}
	
	/**
	 * Should complex checks be included?
	 * 
	 * @return 
	 */
	protected boolean includeComplexChecks() {
		return true;
	}
	
	/**
	 * <p>Create a new instance of the inference engine.</p>
	 * 
	 * <p>In this particular implementation, we extend the
	 * <code>create</code> method so we can keep a log of 
	 * model elements generated in every step.</p>
	 * 
	 * @return
	 */
	@Override
	public CreateMissingElementsWithDrools getInferenceEngine(ICreateElementsFactory handler, boolean trackInsertions, final IModelReloader reloader) {
		if (!doPropertiesInvestigation())
			return super.getInferenceEngine(handler, trackInsertions, reloader);
		
		final Class<?> caller = getClass();
		
		// return engine which checks EMF properties
		return new CreateMissingElementsWithDrools(handler, trackInsertions) {

			@Override
			public void create(EObject model, boolean logRuleSource,
					IProgressMonitor monitor) throws InferenceException {

				// investigate initial model properties
				List<Object> initialProperties = getModelPropertiesInvestigator(false).investigate(model);
				List<Object> initialPropertiesNoGen = getModelPropertiesInvestigator(true).investigate(model);
				List<Object> initialDiff = getIncreaseAbsolute(initialPropertiesNoGen, initialProperties);
				List<Object> initialDiffPct = getIncreasePercent(initialPropertiesNoGen, initialProperties);
				
				// how many elements are in the initial model?
				int initial = 0;
				{
					Iterator<EObject> it = model.eAllContents();
					while (it.hasNext()) {
						it.next();
						initial++;
					}
				}
				
				// do inference once, to remove any initial creation time
				// (and also log to inference queue log)
				try {
					super.create(model, logRuleSource, monitor, new InferenceQueueLog());
					// reload
					model = reloader.reload();
				} catch (NumberFormatException e1) {
					throw new InferenceException(e1);
				} catch (IOException e1) {
					throw new InferenceException(e1);
				} catch (ModelLoadException e1) {
					throw new InferenceException(e1);
				}
				
				int MAX_TIMES = 10;
				List<Long> timedList = new ArrayList<Long>();
				// now execute it 5 times, to get the times
				for (int iteration = 0; iteration < MAX_TIMES; iteration++) {
					System.out.println("iteration " + (iteration + 1) + "...");
					long startTime = System.currentTimeMillis();
					super.create(model, logRuleSource, monitor);
					long diff = System.currentTimeMillis() - startTime;
					timedList.add(diff);
					// reload
					try {
						model = reloader.reload();
					} catch (ModelLoadException e) {
						throw new InferenceException(e);
					}
				}
				long diff = timedList.get(0);
				
				// this execution is to get the actual final result
				super.create(model, logRuleSource, monitor);

				// investigate final model properties
				List<Object> finalProperties = getModelPropertiesInvestigator(false).investigate(model);
				List<Object> finalPropertiesNoGen = getModelPropertiesInvestigator(true).investigate(model);
				List<Object> finalDiff = getIncreaseAbsolute(finalPropertiesNoGen, finalProperties);
				List<Object> finalDiffPct = getIncreasePercent(finalPropertiesNoGen, finalProperties);

				// how many are in the final model?
				int finalCount = 0;
				{
					Iterator<EObject> it = model.eAllContents();
					while (it.hasNext()) {
						it.next();
						finalCount++;
					}
				}
				
				// write this out to a log file
				try {
					File f = new File("inference-properties.csv");
					if (!f.exists()) {
						// write down a list of all the property names
						write(f, "mode", getModelPropertiesInvestigator(false).getModelProperties());
					}
					write(f, "initial", initialProperties);
					write(f, "initial-no-gen", initialPropertiesNoGen);
					write(f, "initial-diff", initialDiff);
					write(f, "initial-diff-%", initialDiffPct);
					write(f, "final", finalProperties);
					write(f, "final-no-gen", finalPropertiesNoGen);
					write(f, "final-diff", finalDiff);
					write(f, "final-diff-%", finalDiffPct);
					write(f, "time", timedList);
					System.out.println(initial + " -> " + finalCount + "(" + diff + " ms)");
					
				} catch (IOException e) {
					throw new InferenceException(e);
				}
				
			}

			/**
			 * Return the absolute increase between the given list of long values.
			 * 
			 * @param source
			 * @param target
			 * @return
			 */
			protected List<Object> getIncreaseAbsolute(
					List<Object> source,
					List<Object> target) {
				List<Object> result = new ArrayList<Object>();
				for (int i = 0; i < source.size(); i++) {
					result.add(
						((Number) target.get(i)).longValue() -
						((Number) source.get(i)).longValue()
					);
				}
				return result;
			}
			
			/**
			 * Return the relative increase in % between the given list of long values.
			 * 
			 * @param source
			 * @param target
			 * @return
			 */
			protected List<Object> getIncreasePercent(
					List<Object> source,
					List<Object> target) {
				List<Object> result = new ArrayList<Object>();
				for (int i = 0; i < source.size(); i++) {
					result.add(
						(((Number) target.get(i)).doubleValue() -
						((Number) source.get(i)).doubleValue())
						/ ((Number) source.get(i)).doubleValue()
					);
				}
				return result;
			}

			/**
			 * Write out all the properties, with the given mode string,
			 * to the given log file.
			 * 
			 * @param f
			 * @param string
			 * @param modelProperties
			 * @throws IOException 
			 */
			private void write(File f, String string,
					List<?> modelProperties) throws IOException {
				
				FileWriter fw = new FileWriter(f, true);
				BufferedWriter writer = new BufferedWriter(fw);
				writer.write(caller.getName() + "," + string);
				for (Object prop : modelProperties) {
					writer.write(",");
					writer.write(prop.toString());
				}
				writer.write("\n");
				writer.close();
				fw.close();

			}
			
		};
	}
	
	/**
	 * Construct the model properties investigator that we will use
	 * to investigate properties, including selecting which properties
	 * to search for.
	 * 
	 * @return
	 */
	public ModelPropertiesInvestigator getModelPropertiesInvestigator(boolean ignoreGenerated) {
		return new ModelPropertiesInvestigator(ignoreGenerated, includeComplexChecks());
	}

}
