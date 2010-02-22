package org.openiaml.model.custom.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * Iterate over all .iaml files in the current project, migrate them,
 * and then make sure they can be loaded using EMF.
 * 
 * @author jmwright
 *
 */
public class MigrateAllModelsAction extends ProgressEnabledAction<IContainer> {

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.ProgressEnabledAction#execute(java.lang.Object, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus execute(IContainer individual, IProgressMonitor monitor) {
		MigrateAllModelsLogic job = new MigrateAllModelsLogic(individual);
		try {
			return job.run(monitor);
		} catch (CoreException e) {
			return errorStatus("Core exception: " + e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.ProgressEnabledAction#getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(IContainer individual, String message) {
		return "Could not migrate models in '" + individual + "': " + message;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.ProgressEnabledAction#getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Migrating all contained models";
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.ProgressEnabledAction#getSelection(java.lang.Object[])
	 */
	@Override
	public List<IContainer> getSelection(Object[] selection) {
		final List<IContainer> ifiles = new ArrayList<IContainer>();
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IContainer) {
					ifiles.add((IContainer) o);
				}
			}
		}
		
		return ifiles;
	}

}
