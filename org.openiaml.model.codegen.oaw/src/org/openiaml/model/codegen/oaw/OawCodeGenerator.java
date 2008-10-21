/**
 * 
 */
package org.openiaml.model.codegen.oaw;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
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
	 * Generate code for a given model file into a given output directory
	 * 
	 */
	public IStatus generateCode(IFile file) {
		
		String wfFile = "src/workflow/generator.oaw";
		Map<String,String> properties = new HashMap<String,String>();
		properties.put("model", file.getFullPath().toString());
		properties.put("src-gen", file.getProject().getLocation().toString());	// have to get absolute filename for output dir
		Map<String,Object> slotContents = new HashMap<String,Object>();
		new WorkflowRunner().run(wfFile,
			new NullProgressMonitor(), properties, slotContents);
		
		return Status.OK_STATUS;
			
	}
}
