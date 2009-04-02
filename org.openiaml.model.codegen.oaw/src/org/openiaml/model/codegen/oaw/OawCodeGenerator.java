/**
 * 
 */
package org.openiaml.model.codegen.oaw;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openiaml.model.codegen.ICodeGenerator;
import org.openarchitectureware.workflow.util.ProgressMonitorAdapter;

/**
 * @author jmwright
 *
 */
public class OawCodeGenerator implements ICodeGenerator {
	
	/**
	 * Generate code for a given model file into a given output directory.
	 * Does NOT deal with inference.
	 * 
	 */
	public IStatus generateCode(IFile file, IProgressMonitor monitor) {
		
		// we can't set the property to get the correct logger, because Eclipse
		// instantiates the logger before we even have the chance to.
		// and we can't have a .properties file, because this is the .propertiues
		// from the context of the classloader (Eclipse)
		
		// System.setProperty("org.apache.commons.logging.LogFactory", "org.openiaml.model.codegen.oaw.OawCodeGenerator.MyLogFactory");
		
		String wfFile = "src/workflow/generator.oaw";
		Map<String,String> properties = new HashMap<String,String>();
		properties.put("model", file.getFullPath().toString());
		properties.put("src-gen", file.getProject().getLocation().toString());	// have to get absolute filename for output dir
		Map<String,Object> slotContents = new HashMap<String,Object>();
		new WorkflowRunner().run(wfFile,
			new ProgressMonitorAdapter(monitor), properties, slotContents);
		
		// refresh output folder
		try {
			file.getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
		} catch (CoreException e) {
			return new Status(Status.WARNING, "org.openiaml.model.codegen.oaw", "Could not refresh local project", e);
		}
		
		return Status.OK_STATUS;
			
	}
	
	/**
	 * Construct a RuntimeException with the given message, and throw it.
	 * Useful in templates, as we can get a stack trace to problems, rather
	 * than using OAW's ERROR code, which only prints out text.
	 * 
	 * @param message
	 */
	public static void throwException(String message) {
		throw new RuntimeException(message);
	}
	
}
