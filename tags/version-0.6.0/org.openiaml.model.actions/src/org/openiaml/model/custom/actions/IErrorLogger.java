/**
 * 
 */
package org.openiaml.model.custom.actions;

import org.eclipse.core.runtime.IStatus;

/**
 * Allows the logging of errors.
 * 
 * @author jmwright
 *
 */
public interface IErrorLogger {

	/**
	 * Log the given error.
	 * 
	 * @param message A message to log
	 * @param e The root cause of the error
	 */
	public void logError(String message, Throwable e);

	/**
	 * Log the given error.
	 * 
	 * @param message A message to log
	 */
	public void logError(String message);

	/**
	 * Log the given multi status.
	 * 
	 * @param multi
	 */
	public void log(IStatus multi);

}
