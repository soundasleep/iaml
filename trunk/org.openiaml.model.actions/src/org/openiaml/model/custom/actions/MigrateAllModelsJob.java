/**
 * 
 */
package org.openiaml.model.custom.actions;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.ModelLoader.ModelLoadException;

/**
 * Actually contains all of the logic of migrating contained models.
 * 
 * @author jmwright
 *
 */
public class MigrateAllModelsJob extends AbstractIAMLJob {
	
	// the container to search
	private IContainer project;

	public MigrateAllModelsJob(IContainer project) {
		super("Migrate all contained models");
		this.project = project;
		setUser(true);		// we are a user job, so we should get some feedback
	}

	/**
	 * Do the actual work.
	 */
	@Override
	protected IStatus runActual(IProgressMonitor monitor) throws CoreException {
		// new task
		monitor.beginTask(this.getName(), 100);
		
		// first, find all models in the project
		Set<IFile> models = findTargetModels(project);
		monitor.worked(30);
		
		if (monitor.isCanceled())
			return Status.CANCEL_STATUS;
		
		// now, for each of them, migrate and load them
		MultiStatus status = multiStatus("Could not migrate all models successfully", null);
		IProgressMonitor m2 = new SubProgressMonitor(monitor, 70);
		m2.beginTask("Migrating models", models.size());
		for (IFile model : models) {
			if (monitor.isCanceled())
				return Status.CANCEL_STATUS;

			try {
				migrateAndLoad(status, model, new SubProgressMonitor(m2, 1));
			} catch (CoreException e) {
				// save to status
				status.add(errorStatus("When migrating " + model + ": " + e.getMessage(), e));
				// and continue
			}
		}
		
		m2.done();
		
		// any errors?
		if (status.getChildren().length != 0) {
			return status;
		}
		
		monitor.done();
		return Status.OK_STATUS;
	}

	/**
	 * @param status
	 * @param model
	 * @throws CoreException 
	 */
	private void migrateAndLoad(MultiStatus status, IFile model, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask("Migrating " + model, 7);
		
		// backup original file
		IFile backup = model.getProject().getFile("backup" + new Random().nextLong() );
		monitor.subTask("Backing up to " + backup);
		model.move(backup.getFullPath(), true, new SubProgressMonitor(monitor, 1));
		
		if (!backup.exists()) {
			status.add(errorStatus("Backup file '" + backup + "' should have been created"));
			return;
		}
		
		// delete original file
		boolean migrateSuccessful = false;
		try {
			
			// first, migrate it
			MigrateModelAction migrator = new MigrateModelAction();
			monitor.subTask("Migrating " + model);
			IStatus s = migrator.migrateModel(backup, model, new SubProgressMonitor(monitor, 1));
			
			// was it OK?
			if (!s.isOK()) {
				MultiStatus s2 = multiStatus("Could not migrate model " + model + ": " + s.getMessage(), null);
				s2.add(s);
				status.add(s2);
			}
			
			if (model.exists()) {
				// then try and load it
				monitor.subTask("Loading " + model + " with EMF");
				try {
					EObject obj = ModelLoader.load(model);
					migrateSuccessful = true;
					
					try {
						// ask EMF to re-save it
						obj.eResource().save( ModelLoader.getSaveOptions() );
					} catch (IOException e) {
						// IO exception during save
						status.add(errorStatus("Could not save loaded model " + model + ": " + e.getMessage(), e));
					}
				} catch (ModelLoadException e) {
					// loading failed; add error status
					status.add(errorStatus("Could not load migrated model " + model + ": " + e.getMessage(), e));
				} catch (WrappedException e) {			
					// loading failed; add error status
					status.add(errorStatus("Could not load migrated model " + model + ": " + e.getMessage(), e));
				}
			}
			
		} finally {
			// replace backup file if it was not successful
			if (!migrateSuccessful) {
				if (model.exists()) {
					monitor.subTask("Deleting broken file");
					model.delete(true, new SubProgressMonitor(monitor, 1));
				}
				
				monitor.subTask("Replacing with backup");
				backup.move(model.getFullPath(), true, new SubProgressMonitor(monitor, 1));
			} else {
				// delete the backup if it exists
				if (backup.exists())
					backup.delete(true, new SubProgressMonitor(monitor, 1));
			}

			monitor.done();
		}
	}

	/**
	 * Find all models in the given resource, and sub-folders.
	 * 
	 * @param project2
	 * @return
	 * @throws CoreException 
	 */
	private Set<IFile> findTargetModels(IResource res) throws CoreException {
		HashSet<IFile> result = new HashSet<IFile>();
		
		if (res instanceof IContainer) {
			IContainer c = (IContainer) res;
			
			for (IResource r : c.members()) {
				result.addAll(findTargetModels(r));
			}
		}
		
		if (res instanceof IFile) {
			IFile f = (IFile) res;
			if (f.getFileExtension() != null && f.getFileExtension().equals("iaml")) {
				// found one
				result.add(f);
			}
		}
		
		return result;
	}
	
}