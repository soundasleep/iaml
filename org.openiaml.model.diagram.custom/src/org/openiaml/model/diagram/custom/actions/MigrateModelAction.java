package org.openiaml.model.diagram.custom.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.diagram.custom.migrate.ExpectedMigrationException;
import org.openiaml.model.diagram.custom.migrate.IamlModelMigrator;
import org.openiaml.model.diagram.custom.migrate.Migrate0To1;
import org.openiaml.model.diagram.custom.migrate.MigrationException;
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

	/**
	 * Get a new filename for the migrated model (cannot be
	 * the same filename) and do the actual migration.
	 * 
	 * @see #migrateModel(IFile, IFile, IProgressMonitor)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IFile) {
					IFile source = (IFile) o;
					// we need to get a new file
					IPath containerPath = source.getFullPath().removeLastSegments(1);
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
					IFile target = source.getProject().getFile(
							source.getProjectRelativePath().removeLastSegments(1).append(destination)
						);
					
					if (target.exists()) {
						IamlDiagramEditorPlugin.getInstance().logError(
								"The target model file already exists.", null); //$NON-NLS-1$
						return;
					}
					
					// must not be the same file
					if (source.getLocation().toString().equals(target.getLocation().toString())) {
						IamlDiagramEditorPlugin.getInstance().logError(
								"Cannot write to the same file.", null); //$NON-NLS-1$
						return;
					}
					
					// migrate it
					IStatus status = migrateModel(source, target, new NullProgressMonitor());
					if (!status.isOK()) {
						// TODO remove this reference to the plugin and remove the reference in plugin.xml
						IamlDiagramEditorPlugin.getInstance().getLog().log( status );
						
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
	 * The third option is more accessible but more work in the
	 * long term, but this is how we are doing it for now.
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
	public IStatus migrateModel(IFile input, IFile output, IProgressMonitor monitor) {
		// sanity
		assert(input.exists());
		assert(!output.exists());
		
		try {
			// get all the migrators
			ArrayList<IamlModelMigrator> migrators = new ArrayList<IamlModelMigrator>();
			migrators.add(new Migrate0To1());
			
			// load the initial document
			Document doc = loadDocument(input.getContents());
			
			// initialise an error log
			List<ExpectedMigrationException> errors = new ArrayList<ExpectedMigrationException>();
			
			// try each of them
			for (IamlModelMigrator m : migrators) {
				if (m.isVersion(doc)) {
					// we want to migrate it with this migrator
					doc = m.migrate(doc, monitor, errors);
					
				}
			}
			
			// once we are done, write the final document to the new model
			// IFiles cannot handle OutputStreams so we can either create a threaded
			// piped input/output stream, or we can just write it temporarily
			// to a file and reload it (or even a string -- but this could run out of memory)
            TransformerFactory transfac = TransformerFactory.newInstance();
            Transformer trans = transfac.newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");	// omit '<?xml version="1.0"?>'
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            // TODO clean this up into a piped input/output stream setup?
			File f = File.createTempFile("test", "iaml");
			FileWriter sw = new FileWriter(f);
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);
            trans.transform(source, result);
            sw.close();
            
            // now pipe this new file into the desired target file
            FileInputStream fout = new FileInputStream(f);
            output.create(fout, true, monitor);
            
            // delete original file
            f.delete();
            
            // do we have any warnings/errors?
            if (errors.isEmpty()) {
            	return Status.OK_STATUS;
            }
            
            // compile a multi status
            MultiStatus s = new MultiStatus(PLUGIN_ID, Status.WARNING, errors.size() + " problems occured when migrating model", null);
            for (ExpectedMigrationException e : errors) {
            	s.add( new Status(Status.WARNING, PLUGIN_ID, e.getMessage(), e) );
            }

            return s;

		} catch (Exception e) {
			return new Status(Status.ERROR, PLUGIN_ID, "Could not migrate model: " + e.getMessage(), e);
		}		
	}
	
	/**
	 * Eclipse can't handle the RewindableInputStream that the XML reader
	 * uses, and throws an IOException(read error). This happens because
	 * we are trying to read the same XML file multiple times.
	 * 
	 * In this method we load the model up once and only once per file. The
	 * loaded document is instead passed to the migrators.
	 * 
	 * @param source
	 * @return
	 * @throws MigrationException
	 */
	public Document loadDocument(InputStream source) throws MigrationException {
		try {
			// load the model version
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(source);
			
			// done
			source.close();
			
			return doc;
		} catch (Exception e) {
			throw new MigrationException(e);
		}
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
