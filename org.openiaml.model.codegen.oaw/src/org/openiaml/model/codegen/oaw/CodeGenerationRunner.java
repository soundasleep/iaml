/**
 * 
 */
package org.openiaml.model.codegen.oaw;

import java.util.HashMap;
import java.util.Map;

import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.monitor.NullProgressMonitor;

/**
 * @author jmwright
 *
 */
public class CodeGenerationRunner {
	
	/**
	 * Generate code for a given model file into a given output directory
	 * 
	 * @param modelFile
	 * @param outputDir
	 */
	public void generateCode(String modelFile, String outputDir) {

		String wfFile = "src/workflow/generator.oaw";
		Map<String,String> properties = new HashMap<String,String>();
		properties.put("model", modelFile);
		properties.put("src-gen", outputDir);
		Map<String,Object> slotContents = new HashMap<String,Object>();
		new WorkflowRunner().run(wfFile,
			new NullProgressMonitor(), properties, slotContents);
			
	}
}
