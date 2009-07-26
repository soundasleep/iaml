/**
 * 
 */
package org.openiaml.model.codegen;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * Defines an interface for a code generator.
 * 
 * @author jmwright
 *
 */
public interface ICodeGenerator {

	/**
	 * <p>
	 * Generate code for a certain model file into a certain output directory.
	 * </p>
	 * 
	 * <p>
	 * Expected properties:
	 * 
	 * <ul>
	 * 	<li>config_runtime: the server-side path to the include libraries dir (php)</li>
	 * 	<li>config_web: the client-side path to the include libraries dir (js)</li>
	 * 	<li>include_runtime: should the generated code also include the runtime libraries? (issue 81)</li>
	 * </ul>
	 * </p> 
	 * 
	 * @param modelFile
	 * @param outputDir
	 * @param runtimeProperties a map of properties.
	 */
	public IStatus generateCode(IFile modelFile, 
			IProgressMonitor monitor,
			Map<String,String> runtimeProperties);
	
}
