/**
 * 
 */
package org.openiaml.model;

import java.io.File;


/**
 * Represents a file reference.
 * 
 * @author jmwright
 *
 */
public interface FileReference {

	/**
	 * Get the file referred to by this reference.
	 * 
	 * @return
	 */
	File toFile();


}
