/**
 * 
 */
package org.openiaml.model.runtime;

import java.io.File;

/**
 * When a file is copied from the runtime library to the runtime environment,
 * this listener will be called. This is necessary to allow for runtime
 * caching; otherwise, runtime files copied over will not be cached for
 * future executions.
 * 
 * @author jmwright
 *
 */
public interface IFileCopyListener {

	/**
	 * A file was copied from the given source to the given target.
	 * 
	 * @param source
	 * @param target
	 */
	public void fileCopied(File source, File target);
	
}
