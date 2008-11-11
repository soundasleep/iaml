package org.openiaml.model.diagram.custom.actions;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorUtil;
import org.w3c.dom.Document;

/**
 * Action to migrate an old .iaml file to a new .iaml file
 * 
 * @see org.openiaml.model.codegen.oaw
 * @author jmwright
 *
 */
public class MigrateModelAction implements IViewActionDelegate {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IFile) {
					IFile source = (IFile) o;
					// we need to get a new file
					IPath containerPath = source.getLocation().removeLastSegments(1);
					String fileName = source.getName();
					String fileExtension = source.getFileExtension();
					// generate unique name
					String uniqueName = IamlDiagramEditorUtil.getUniqueFileName( containerPath, fileName, fileExtension);

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
						return;
					}
					
					// get the file
					IFile target = source.getParent().getFile(
							source.getFullPath().removeLastSegments(1).append(destination)
						);
					
					if (target.exists()) {
						IamlDiagramEditorPlugin.getInstance().logError(
								"The target model file already exists.", null); //$NON-NLS-1$
						return;
					}
					
					// migrate it
					IStatus status = migrateModel(source, target, new NullProgressMonitor());
					if (!status.isOK()) {
						// TODO remove this reference to the plugin and remove the reference in plugin.xml
						IamlDiagramEditorPlugin.getInstance().logError(
								"Could not migrate '" + source + "' to '" + target + "': " + status.getMessage(), status.getException());
					}
				}
			}
		}

	}
	
	/**
	 * Migrate a file to another file. 
	 * 
	 * We have a couple of options for loading the files for migration:
	 * <ol>
	 *   <li>Keep multiple instances of the model files in multiple plugins, and let
	 *       EMF decide which model implementation to load. Once the model is loaded,
	 *       we can use EMF functionality to traverse over the model and create a new
	 *       version.</li>
	 *   <li>Use ATL. But ATL also requires a metamodel for both the source and target
	 *       models.</li>
	 *   <li>Load the model file with XML and create a new XMI model manually. This is
	 *       more error prone.</li> 
	 * </ol>
	 * 
	 * We also have some options for doing multiple migrations in a row (say v1, v2, v3, v4):
	 * <ol>
	 *   <li>v1 -> v2, v2 -> v3, v3 -> v4</li>
	 *   <li>v1 -> v4, v2 -> v4, v3 -> v4</li>
	 * </ol>
	 * 
	 * The first option requires less development work but more processing time
	 * since they chain.
	 * 
	 * @param input
	 * @param output
	 * @param monitor
	 * @return
	 */
	protected IStatus migrateModel(IFile input, IFile output, IProgressMonitor monitor) {
		// sanity
		assert(input.exists());
		assert(!output.exists());
		
		try {
			// get all the migrators
			ArrayList<IamlModelMigrator> migrators = new ArrayList<IamlModelMigrator>();
			migrators.add(new Migrate0To1());
			
			// try each of them
			InputStream source = input.getContents();
			for (IamlModelMigrator m : migrators) {
				if (m.isVersion(source)) {
					// we want to migrate it
					InputStream target = m.migrate(source, monitor);
					
					// write this to the file
					output.setContents(target, true, false, monitor);
					
					// then reload the file for the next round
					
					// we now want to pipe the target and source together
					
				}
			}
		} catch (Exception e) {
			return new Status(Status.ERROR, PLUGIN_ID, "Could not load migrated model: " + e.getMessage(), e);
		}
		
		return Status.OK_STATUS;
	}
	
	/**
	 * Migrate model version 0.0 to version 0.1.
	 * 
	 * Differences:
	 * - 0.1 now has ID values
	 * 
	 * @author jmwright
	 *
	 */
	protected class Migrate0To1 implements IamlModelMigrator {

		/**
		 * TODO write up what defines a 0.0 model 
		 * 
		 * @see org.openiaml.model.diagram.custom.actions.MigrateModelAction.IamlModelMigrator#isVersion(org.eclipse.core.resources.IFile)
		 */
		@Override
		public boolean isVersion(InputStream source) throws MigrationException {
			try {
				// load the model version
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(source);
				
				// get parameters
				String nsPackage = doc.getDocumentElement().getAttribute("xmlns:iaml");
				String rootId = doc.getDocumentElement().getAttribute("id");

				if (nsPackage.equals("http://openiaml.org/model") && 
						rootId.isEmpty()) {
					// this is us!
					return true;
				}
			} catch (Exception e) {
				throw new MigrationException(e);
			}
				
			// TODO Auto-generated method stub
			return false;
		}

		/* (non-Javadoc)
		 * @see org.openiaml.model.diagram.custom.actions.MigrateModelAction.IamlModelMigrator#migrate(org.eclipse.core.resources.IFile, org.eclipse.core.resources.IFile, org.eclipse.core.runtime.IProgressMonitor)
		 */
		@Override
		public InputStream migrate(InputStream input, IProgressMonitor monitor)
				throws MigrationException {
			try {
				// load the model version
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(input);
				
				// TODO continue implementation
				return null;
	
			} catch (Exception e) {
				throw new MigrationException(e);
			}
		}
		
	}
	
	public class MigrationException extends Exception {

		private static final long serialVersionUID = 1L;

		public MigrationException(Exception e) {
			super(e);
		}
		
	}
	
	public interface IamlModelMigrator {
		/**
		 * Is the given file a model of this version?
		 * 
		 * @param source
		 * @return
		 */
		public boolean isVersion(InputStream source) throws MigrationException;
		
		/**
		 * Migrate a file to the next version.
		 * 
		 * @param input
		 * @param output
		 * @param monitor
		 * @throws MigrationException
		 */
		public InputStream migrate(InputStream input, IProgressMonitor monitor)
			throws MigrationException;
	}
	
	public static final String PLUGIN_ID = "org.openiaml.model.diagram.custom";

	Object[] selection;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = null;
		if (selection instanceof IStructuredSelection) {
			this.selection = ((IStructuredSelection) selection).toArray();
		}
	}

}
