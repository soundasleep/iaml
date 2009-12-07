/**
 * 
 */
package org.openiaml.docs.generation;

import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;

/**
 * @author jmwright
 *
 */
public interface ILoader {

	/**
	 * Create documentation elements from the given factory into the
	 * given ModelDocumentation root element.
	 * 
	 * @throws DocumentationGenerationException if an exception occurs while generating documentation 
	 */
	public void load(ModeldocFactory factory, ModelDocumentation root) 
		throws DocumentationGenerationException;
	
}
