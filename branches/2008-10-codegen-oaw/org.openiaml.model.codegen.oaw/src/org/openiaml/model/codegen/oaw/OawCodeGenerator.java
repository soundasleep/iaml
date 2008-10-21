/**
 * 
 */
package org.openiaml.model.codegen.oaw;

import java.util.HashMap;
import java.util.Map;

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
	 * @param modelFile
	 * @param outputDir
	 */
	public IStatus generateCode(String modelFile, String outputDir) {

		String wfFile = "src/workflow/generator.oaw";
		Map<String,String> properties = new HashMap<String,String>();
		properties.put("model", modelFile);
		properties.put("src-gen", outputDir);
		Map<String,Object> slotContents = new HashMap<String,Object>();
		new WorkflowRunner().run(wfFile,
			new NullProgressMonitor(), properties, slotContents);
		
		return Status.OK_STATUS;
			
	}
}
