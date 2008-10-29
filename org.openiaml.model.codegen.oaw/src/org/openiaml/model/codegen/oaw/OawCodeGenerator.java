/**
 * 
 */
package org.openiaml.model.codegen.oaw;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.monitor.NullProgressMonitor;
import org.openiaml.model.codegen.ICodeGenerator;

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
		
		String wfFile = "src/workflow/generator.oaw";
		Map<String,String> properties = new HashMap<String,String>();
		properties.put("model", file.getFullPath().toString());
		properties.put("src-gen", file.getProject().getLocation().toString());	// have to get absolute filename for output dir
		Map<String,Object> slotContents = new HashMap<String,Object>();
		new WorkflowRunner().run(wfFile,
			new NullProgressMonitor(), properties, slotContents);
		
		// refresh output folder
		try {
			file.getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
		} catch (CoreException e) {
			return new Status(Status.WARNING, "org.openiaml.model.codegen.oaw", "Could not refresh local project", e);
		}
		
		return Status.OK_STATUS;
			
	}
}
