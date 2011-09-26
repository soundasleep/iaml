/**
 * 
 */
package org.openiaml.model.custom.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.ModelLoader.ModelLoadException;
import org.openiaml.model.drools.DroolsInferenceEngine;
import org.openiaml.model.drools.EcoreInferenceHandlerFactory;
import org.openiaml.model.inference.InferenceException;

/**
 * Just like {@link ExportDotGraphAction}, except this also does
 * inference so we can see the difference.
 * 
 * @author jmwright
 *
 */
public class ExportInferredDotGraphAction extends ExportDotGraphAction {

	/**
	 * The target model becomes the inferred model. This way, we can
	 * see the difference between a source model and the generated
	 * completed model.
	 * 
	 * @throws InferenceException 
	 * @throws ModelLoadException 
	 */
	@Override
	public EObject getTargetModel(IFile source, IProgressMonitor monitor) throws InferenceException, ModelLoadException {
		monitor.beginTask("Completing model using inference", 50);
		
		monitor.subTask("Loading model");
		EObject target = ModelLoader.load(source);
		monitor.worked(10);
		
		// use the inference action
		monitor.subTask("Initialising inference engine");
		InferEntireModelAction action = new InferEntireModelAction();
		DroolsInferenceEngine ce = action.getEngine(new EcoreInferenceHandlerFactory());
		monitor.worked(10);

		// do inference
		monitor.subTask("Executing model completion");
		ce.create(target, new SubProgressMonitor(monitor, 80));
		
		// return 
		return target;
	}
	
}
