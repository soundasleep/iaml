package org.openiaml.model.custom.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.openiaml.iacleaner.inline.InlineStringReader;
import org.openiaml.model.migrate.ExpectedMigrationException;
import org.openiaml.model.migrate.IamlModelMigrator;
import org.openiaml.model.migrate.MigrationException;
import org.openiaml.model.migrate.MigratorRegistry;
import org.w3c.dom.Document;

/**
 * Action to migrate an old .iaml file to a new .iaml file
 * 
 * @see org.openiaml.model.codegen.php
 * @author jmwright
 *
 */
public class MigrateModelAction extends ProgressEnabledUIAction<IFile> {

	private List<IamlModelMigrator> migratorsUsed;
	
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
	 * Get a new filename for the migrated model (cannot be
	 * the same filename) and do the actual migration.
	 * 
	 * @see #migrateModel(IFile, IFile, IProgressMonitor)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public IStatus execute(IFile source, IProgressMonitor monitor) {
		// we need to get a new file
		IPath containerPath = source.getFullPath().removeLastSegments(1);
		String fileName = source.getName();
		String fileExtension = source.getFileExtension();
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
			return Status.CANCEL_STATUS;
		}
		
		// get the file
		IFile target = source.getProject().getFile(
				source.getProjectRelativePath().removeLastSegments(1).append(destination)
			);
		
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
			// reset list of used migrators
			migratorsUsed = new ArrayList<IamlModelMigrator>();
			
			// get all the migrators
			List<IamlModelMigrator> migrators = getMigrators();
			
			// load the initial document
			Document doc = loadDocument(input.getContents());
			
			// initialise an error log
			List<ExpectedMigrationException> errors = new ArrayList<ExpectedMigrationException>();
			
			int scale = 20;
			monitor.beginTask("Migrating", (migrators.size() + 1) * scale);
			
			// try each of them
			for (IamlModelMigrator m : migrators) {
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;

				if (m.isVersion(doc)) {
					// add to list of migrators used
					migratorsUsed.add(m);

					// we want to migrate it with this migrator
					monitor.subTask(m.getName());
					doc = m.migrate(doc, new SubProgressMonitor(monitor, scale), errors);
				} else {
					monitor.worked(scale);
				}
			}
			
			monitor.subTask("Writing out final model");
			
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
            IndentedStream indent = new IndentedStream(fout);
            output.create(indent, true, monitor);
            
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
            
            monitor.done();

            return s;

		} catch (Exception e) {
			return new Status(Status.ERROR, PLUGIN_ID, "Could not migrate model: " + e.getMessage(), e);
		}		
	}
	
	/**
	 * Wraps an InputStream which provides XML to indent the
	 * output. It assumes the input stream is generated by DOM, which generates
	 * XML identical to the following; that is, there is no whitespace at the start
	 * of each line:
	 * 
	 * <p><code>&lt;?xml...?&gt;<br>
	 * &lt;tag&gt;<br>
	 * &lt;tag&gt;<br>
	 * &lt;/tag&gt;<br>
	 * &lt;/tag&gt;</code></p>
	 * 
	 * @author jmwright
	 *
	 */
	public static class IndentedStream extends InputStream {

		private InlineStringReader internal = null;
		
		public IndentedStream(InputStream i) {
			this.internal = new InlineStringReader(new InputStreamReader(i)) {

				@Override
				protected void throwWarning(String message, String buffer) {
					throw new RuntimeException(message);
				}
				
			};
		}
		
		private String buf = "";
		private int indent = 0;
		private String indentString = "  ";
		private int lastChar = -1;
		private boolean closingTag = false;
		
		@Override
		public int read() throws IOException {
			// a character in the buffer?
			if (!buf.isEmpty()) {
				int c = buf.charAt(0);
				buf = buf.substring(1);
				return c;
			}
			
			// otherwise, read the stream
			int c = internal.read();
			
			if (lastChar == '/' && c == '>') {
				// indent doesn't change in a one-line tag
			} else if (c == '>') {
				// indent changes
				if (closingTag) {
					// indent--; - has already been decremented, below
				} else {
					indent++;
				}
			} else if (c == '<') {
				if (internal.readAhead() == '/' || internal.readAhead() == '?') {
					// closing tag or processing instruction
					closingTag = true;
				} else {
					// normal tag
					closingTag = false;
				}
			} else if (c == '\n') {
				// we need to set the indent buffer
				if (internal.readAhead(2) != null && internal.readAhead(2).equals("</")) {
					// if we're closing, indent-- early
					indent--;
				}
				buf = repeatString(indentString, indent);
			}
			
			lastChar = c;
			return c;
		}
		
		public String repeatString(String s, int n) {
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < n; i++) {
				buf.append(s);
			}
			return buf.toString();
		}
		
	}
	
	/**
	 * Get all of the migrators registered in the system.
	 * They should be returned in order of migration, e.g. a
	 * version 3-4 migrator should appear before a 5-6 migrator,
	 * and after a 2-3 migrator.
	 * 
	 * In the future this might be implemented as an extension point.
	 * 
	 * @return A list of available model migrators.
	 */
	public List<IamlModelMigrator> getMigrators() {
		return MigratorRegistry.getMigrators();
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

	/**
	 * Get a list of all the model migrators used in this action.
	 * 
	 * @return A list of used model migrators
	 */
	public List<IamlModelMigrator> getMigratorsUsed() {
		return migratorsUsed;
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
