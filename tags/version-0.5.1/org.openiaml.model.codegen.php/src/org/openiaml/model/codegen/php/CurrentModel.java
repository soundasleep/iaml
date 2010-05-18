/**
 * 
 */
package org.openiaml.model.codegen.php;

import org.eclipse.emf.ecore.EObject;
import org.openarchitectureware.workflow.WorkflowContext;
import org.openarchitectureware.workflow.issues.Issues;
import org.openarchitectureware.workflow.lib.WorkflowComponentWithModelSlot;
import org.openarchitectureware.workflow.monitor.ProgressMonitor;

/**
 * A workflow component to save a reference to the in-memory model, which can
 * be passed along to OAW (so that we don't have to write out the
 * inferred model, and OAW doesn't have to reload it).
 * 
 * <p>Based on http://www.openarchitectureware.org/article.php/How_to_use_oAW_with_a_JavaBeans_based_MM
 * 
 * @author jmwright
 *
 */
public class CurrentModel extends WorkflowComponentWithModelSlot {

	/* (non-Javadoc)
	 * @see org.openarchitectureware.workflow.WorkflowComponent#invoke(org.openarchitectureware.workflow.WorkflowContext, org.openarchitectureware.workflow.monitor.ProgressMonitor, org.openarchitectureware.workflow.issues.Issues)
	 */
	@Override
	public void invoke(WorkflowContext ctx, ProgressMonitor monitor,
			Issues issues) {
	
		// apply the model to the model slot
		ctx.set(getModelSlot(), getCurrentModel());
	}

	/**
	 * Get the loaded model.
	 * 
	 * @return
	 */
	protected EObject getCurrentModel() {
		if (currentModel == null) {
			// throw an exception
			throw new NullPointerException("No current model has been loaded.");
		}
		
		return currentModel;
	}

	private static EObject currentModel = null;
	
	public static void setCurrentModel(EObject model) {
		currentModel = model;
	}
	
}
