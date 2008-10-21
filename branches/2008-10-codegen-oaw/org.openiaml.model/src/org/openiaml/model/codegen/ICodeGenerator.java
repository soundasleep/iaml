/**
 * 
 */
package org.openiaml.model.codegen;

import org.eclipse.core.runtime.IStatus;

/**
 * Defines an interface for a code generator.
 * 
 * @author jmwright
 *
 */
public interface ICodeGenerator {

	/**
	 * Generate code for a certain model file into a certain output directory.
	 * 
	 * @param modelFile
	 * @param outputDir
	 */
	public IStatus generateCode(String modelFile, String outputDir);
	
}
