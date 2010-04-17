/**
 * 
 */
package org.openiaml.docs.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.openiaml.docs.tools.InitialiseDiagram.InitializeDiagramException;
import org.openiaml.model.diagram.custom.actions.ExportToClickableHtml;
import org.openiaml.model.diagram.custom.actions.ExportImagePartsAction.ExportImageException;

/**
 * Actually contains all of the logic of exporting example IAML images.
 * 
 * @author jmwright
 *
 */
public class ExportIAMLExamplesJob extends AbstractIAMLDocJob {
	
	// the project to search
	private IProject project;
	
	// the destination project
	private IProject examples;
	
	public ExportIAMLExamplesJob(IProject project) {
		super("Export IAML example images");
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
		
		// find the "examples" project
		examples = project.getWorkspace().getRoot().getProject("examples");
		if (!examples.exists()) {
			// examples project does not exist
			return errorStatus("Examples project (\"examples\") does not exist in workspace root.");
		}
		
		// find the "examplesList.txt" text file
		IFile list = project.getFile("examplesList.txt");
		if (!list.exists()) {
			// examples list does not exist
			return errorStatus("Examples list (\"examplesList.txt\") does not exist in project root.");
		}
		
		// open it
		monitor.subTask("Loading list of examples to export");
		Set<String> listModels;
		try {
			// 10%
			listModels = getModelsList(list, new SubProgressMonitor(monitor, 10));
		} catch (IOException e) {
			return errorStatus(e);
		}	
		
		if (listModels.isEmpty()) {
			// no examples to load
			return errorStatus("Examples list was empty.");
		}
		
		// export each model
		IStatus status = exportAllModels(listModels, new SubProgressMonitor(monitor, 90));	// 90%
		monitor.done();
		
		return status;
	}
	
	/**
	 * For each example model in the given list of parameters, 
	 * initialise the diagram, export it to the examples project,
	 * and then delete the initialised diagram file.
	 * 
	 * @param models the list of models to load in the current project
	 * @param monitor the monitor to notify
	 * @return
	 * @throws CoreException 
	 */
	protected IStatus exportAllModels(Set<String> models,
			SubProgressMonitor monitor) throws CoreException {
		
		monitor.beginTask("Exporting " + models.size() + " models", models.size() * 3);
		
		for (String model : models) {
			if (monitor.isCanceled())
				return Status.CANCEL_STATUS;
			
			monitor.subTask("Exporting " + model.substring(model.lastIndexOf("/")));
			
			// find the file, make sure that it exists
			IFile file = project.getFile(model);
			if (!file.exists()) {
				return errorStatus("File '" + file + "' does not exist");
			}
			
			// first, initialise the diagram (but don't open it)
			IFile diagram = null;
			InitialiseDiagram init = new InitialiseDiagram();
			try {
				diagram = init.initialize(project, file, false, new SubProgressMonitor(monitor, 1));
			} catch (InitializeDiagramException e) {
				return errorStatus(e);
			}
			
			// now export it
			ExportToClickableHtml export = new ExportToClickableHtml();
			try {
				export.doExport(diagram, examples /* copy to examples project */, new SubProgressMonitor(monitor, 1));
			} catch (RuntimeException e) {
				throw new RuntimeException("Could not export model '" + model + "': " + e.getMessage(), e);
			} catch (ExportImageException e) {
				return errorStatus(e);
			}
			
			// finally, delete the diagram
			diagram.delete(true, new SubProgressMonitor(monitor, 1));
		
		}
		
		// completed successfully
		return Status.OK_STATUS;
	}

	/**
	 * Load the input IFile for a list of models to find:
	 * 
	 * <p><code>
	 * org/openiaml/model/tests/codegen/model0_4/DomainInheritance.iaml<br>
	 * org/openiaml/model/tests/codegen/model0_4/DomainInheritance.iaml<br>
	 * org/openiaml/model/tests/codegen/model0_4_1/SetValueStatic.iaml<br>
	 * </code>
	 * 
	 * <p>It must return a list of unique model names.
	 * 
	 * @param list the file to load
	 * @param monitor the monitor to notify
	 * @return a list of unique model names
	 * @throws CoreException 
	 * @throws IOException 
	 */
	protected Set<String> getModelsList(IFile list, SubProgressMonitor monitor) throws CoreException, IOException {
		
		monitor.beginTask("Loading examples models list", 2);
		
		InputStream is = list.getContents();
		StringBuffer result = new StringBuffer();
		int buf = 1024;
		byte[] buffer = new byte[buf];
		while (true) {
			int read = is.read(buffer);
			if (read == -1)
				break;			
			result.append(new String(buffer, 0, read));
		}
		is.close();
		monitor.worked(1);
		
		// now split it up
		monitor.subTask("Splitting up list of models");
		String[] asString = result.toString().split("\n");
		Set<String> models = new HashSet<String>();
		for (String s : asString) {
			// ignore empty lines
			if (!s.trim().isEmpty()) {
				// ignore whitespace
				models.add(s.trim());
			}
		}
		
		monitor.done();
		
		return models;
	}
	
}