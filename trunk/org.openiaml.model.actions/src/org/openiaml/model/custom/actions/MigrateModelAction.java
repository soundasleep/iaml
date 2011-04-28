package org.openiaml.model.custom.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.migrate.MigrationController;

/**
 * Action to migrate an old .iaml file to a new .iaml file
 * 
 * @see org.openiaml.model.codegen.php
 * @author jmwright
 *
 */
public class MigrateModelAction extends ProgressEnabledUIAction<IFile> {
	
	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(IFile individual, String message) {
		return "Could not migrate model '" + individual.getName() + "': " + message;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Migrating model";
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getSelection(java.lang.Object[])
	 */
	@Override
	public List<IFile> getSelection(Object[] selection) {
		final List<IFile> ifiles = new ArrayList<IFile>();
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IFile) {
					ifiles.add((IFile) o);
				}
			}
		}
		
		return ifiles;
	}
	
	/**
	 * Ask the user for a target IFile to save to, based on a unique name of
	 * the source IFile, and a given file extension. Returns <code>null</code>
	 * if cancel was selected.
	 * 
	 * @return the provided IFile, or <code>null</code> if the dialog was cancelled. 
	 */
	public static IFile askForFilename(IFile source, String fileExtension) {
		// we need to get a new file
		IPath containerPath = source.getFullPath().removeLastSegments(1);
		String fileName = source.getName();
		// generate unique name
		String uniqueName = getUniqueFileName( containerPath, fileName, fileExtension);

		// TODO migrate this to a wizard
		InputDialog dialog = new InputDialog(
				PlatformUI.getWorkbench().getDisplay().getActiveShell(),
				"Enter in destination model file",
				"Please enter in the destination model file",
				uniqueName,
				null	// InputValidator
			);
		dialog.setBlockOnOpen(true);
		dialog.open();
		
		String destination = dialog.getValue();
		if (destination == null) {
			// cancelled
			return null;
		}
		
		// get the file
		IFile target = source.getProject().getFile(
				source.getProjectRelativePath().removeLastSegments(1).append(destination)
			);
		
		return target;
	}
	
	/**
	 * Ask the user for a target IFile to save to, based on the filename
	 * and file extension of the given source IFile.
	 * 
	 * @return the provided IFile, or <code>null</code> if the dialog was cancelled. 
	 */
	public static IFile askForFilename(IFile source) {
		return askForFilename(source, source.getFileExtension());
	}

	/**
	 * Get a new filename for the migrated model (cannot be
	 * the same filename) and do the actual migration.
	 * 
	 * @see #migrateModel(IFile, IFile, IProgressMonitor)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public IStatus execute(IFile source, IProgressMonitor monitor) {
		IFile target = askForFilename(source);	// uses the same file extension
		
		if (target == null) {
			// migration cancelled
			return cancelledStatus();
		}
		
		if (target.exists()) {
			return errorStatus("The target model file already exists.");
		}
		
		// must not be the same file
		if (source.getLocation().toString().equals(target.getLocation().toString())) {
			return errorStatus("Cannot write to the same file.");
		}
		
		// migrate it
		IStatus status = migrateModel(source, target, monitor);
		if (!status.isOK()) {
			// warn or error
			if (status.getSeverity() == IStatus.WARNING) {
				// msg
				MessageDialog.openWarning( PlatformUI.getWorkbench().getDisplay().getActiveShell() ,
						"Model migration warning", status.getChildren().length + " errors occured during model migration. Please check the error log to review them.");
			} else {
				MessageDialog.openError( PlatformUI.getWorkbench().getDisplay().getActiveShell() ,
						"Model migration failed", "Could not migrate model. Please check the error log.");
			}
		}
		
		return status;
	}

	/**
	 * Migrate the given model into the target file. This calls
	 * the {@link MigrationController}, which performs all of the
	 * hard work.
	 * 
	 * @param source
	 * @param target
	 * @param monitor
	 * @return
	 */
	protected IStatus migrateModel(IFile source, IFile target,
			IProgressMonitor monitor) {
		MigrationController mc = new MigrationController();
		return mc.migrateModel(source, target, monitor);
	}

	/**
	 * Copied directly from generated GMF diagram code.
	 */
	public static String getUniqueFileName(IPath containerFullPath,
			String fileName, String extension) {
		if (containerFullPath == null) {
			containerFullPath = new Path(""); //$NON-NLS-1$
		}
		if (fileName == null || fileName.trim().length() == 0) {
			fileName = "default"; //$NON-NLS-1$
		}
		IPath filePath = containerFullPath.append(fileName);
		if (extension != null && !extension.equals(filePath.getFileExtension())) {
			filePath = filePath.addFileExtension(extension);
		}
		extension = filePath.getFileExtension();
		fileName = filePath.removeFileExtension().lastSegment();
		int i = 1;
		while (ResourcesPlugin.getWorkspace().getRoot().exists(filePath)) {
			i++;
			filePath = containerFullPath.append(fileName + i);
			if (extension != null) {
				filePath = filePath.addFileExtension(extension);
			}
		}
		return filePath.lastSegment();
	}
	
}
