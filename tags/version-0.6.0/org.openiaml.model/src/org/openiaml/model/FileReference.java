/**
 * 
 */
package org.openiaml.model;

import java.io.File;

import org.eclipse.emf.common.util.URI;


/**
 * Represents a file reference. The reference
 * may be relative so we need a path to resolve it against.
 * 
 * @author jmwright
 *
 */
public interface FileReference {

	/**
	 * Get the file referred to by this reference. The reference
	 * may be relative so we need an absolute path to resolve it against.
	 * 
	 * @param absolutePath the absolute URI to compare against 
	 * @return
	 */
	File toFile(URI absolutePath);


}
