/**
 * 
 */
package org.openiaml.model.codegen;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

/**
 * Defines an interface for a code generator.
 * 
 * @author jmwright
 *
 */
public interface ICodeGenerator {

	/**
	 * <p>
	 * Generate code for a certain model into a certain output directory.
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
	 * @param model the (already loaded) model in memory
	 * @param srcGen project to generate into (and refresh after generation)
	 * @param outputDir
	 * @param runtimeProperties a map of properties.
	 */
	public IStatus generateCode(EObject model,
			IProject srcGen,
			IProgressMonitor monitor,
			Map<String,String> runtimeProperties);
	
}
