/**
 * 
 */
package org.openiaml.docs.tools;

import java.io.IOException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;
import org.openiaml.model.diagram.custom.helpers.DiagramRegistry;
import org.openiaml.model.diagram.custom.helpers.DiagramRegistry.DiagramRegistryException;

/**
 * Simply try and initialise a .iaml file into a .iaml_diagram file.
 * 
 * @author jmwright
 *
 */
public class InitialiseDiagram {

	public static class InitializeDiagramException extends Exception {

		private static final long serialVersionUID = 1L;
		
		public InitializeDiagramException(Throwable cause) {
			super(cause.getMessage(), cause);
		}
		
	}
	
	/**
	 * 
	 * @param project the containing project
	 * @param model the model file to initialize
	 * @param open should the new diagram file be opened?
	 * @param monitor monitor to notify
	 * 
	 * @return the new diagram file created
	 * 
	 * @throws PartInitException 
	 * @throws InitializeDiagramException 
	 */
	public IFile initialize(IProject project, IFile model, boolean open, IProgressMonitor monitor) throws PartInitException, InitializeDiagramException {
		
		monitor.beginTask("Finding diagram name", 1);
		
		// try initialising the diagram
		IFile diagram = null;
		for (int i = 0; i < 100; i++) {
			diagram = project.getFile(model.getName() + "_diagram" + (i == 0 ? "" : i));
			if (!diagram.exists())
				break;
		}
		
		monitor.subTask("Initialising diagram " + diagram.getName());
		
		try {
			// get the diagram registry to open the diagram
			DiagramRegistry.initializeModelFile(model, diagram, open);
		} catch (RuntimeException e) {
			throw new InitializeDiagramException(e);
		} catch (ExecutionException e) {
			throw new InitializeDiagramException(e);
		} catch (DiagramRegistryException e) {
			throw new InitializeDiagramException(e);
		} catch (IOException e) {
			throw new InitializeDiagramException(e);
		}
		monitor.done();
		
		return diagram;
		
	}
	
}
