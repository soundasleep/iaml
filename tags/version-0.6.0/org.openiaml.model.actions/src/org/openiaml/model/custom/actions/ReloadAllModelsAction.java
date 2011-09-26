package org.openiaml.model.custom.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.ModelLoader.ModelLoadException;

/**
 * Iterate over all .iaml files in the current project;
 * makes sure they can be loaded using EMF; and then
 * saves the model instance back to file.
 * 
 * @author jmwright
 *
 */
public class ReloadAllModelsAction extends ProgressEnabledAction<IContainer> {

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.ProgressEnabledAction#execute(java.lang.Object, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus execute(IContainer individual, IProgressMonitor monitor) {
		Set<IFile> models = null;
		try {
			models = MigrateAllModelsLogic.findTargetModels(individual);
		} catch (CoreException e1) {
			return AbstractIAMLJob.errorStatus(e1);
		}
		
		monitor.beginTask(getProgressMessage(), models.size());
		
		MultiStatus status = AbstractIAMLJob.multiStatus("Could not load all models successfully", null);
		
		for (IFile f : models) {
			if (monitor.isCanceled())
				return Status.CANCEL_STATUS;
			
			monitor.subTask("Loading model " + f.getName());
			try {
				EObject obj = ModelLoader.load(f);
				try {
					// ask EMF to re-save it
					obj.eResource().save( ModelLoader.getSaveOptions() );
				} catch (IOException e) {
					// IO exception during save
					status.add(AbstractIAMLJob.errorStatus("Could not save loaded model " + f + ": " + e.getMessage(), e));
				}
			} catch (ModelLoadException e) {
				// loading failed; add error status
				status.add(AbstractIAMLJob.errorStatus("Could not load migrated model " + f + ": " + e.getMessage(), e));
			} catch (WrappedException e) {			
				// loading failed; add error status
				status.add(AbstractIAMLJob.errorStatus("Could not load migrated model " + f + ": " + e.getMessage(), e));
			}
		}
		
		monitor.done();
		
		// any errors?
		if (status.getChildren().length != 0) {
			return status;
		}
		
		return Status.OK_STATUS;
		
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.ProgressEnabledAction#getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(IContainer individual, String message) {
		return "Could not load models in '" + individual + "': " + message;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.custom.actions.ProgressEnabledAction#getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Loading all contained models";
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
