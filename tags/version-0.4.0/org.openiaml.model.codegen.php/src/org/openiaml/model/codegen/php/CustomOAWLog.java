/**
 * 
 */
package org.openiaml.model.codegen.php;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;

/**
 * <p>A special logger that controls OAW logs, and saves a copy of all Errors
 * into a list that can be retrieved later.</p>
 * 
 * <p>Allows OAW logging to go through a custom Eclipse class defined in the
 * workspace, rather than using a properties file (not accessible from the
 * current class loader).</p>
 * 
 * <p>The OAW logs all use commons.logging directly, so we have to 
 * override the log that commons.logging uses.</p>
 * 
 * <p>From http://oaw-forum.itemis.de/forum/viewtopic.php?forum=1&showtopic=1486 (german)</p>
 * 
 * @author jmwright
 *
 */
public class CustomOAWLog extends SimpleLog implements Log, Serializable {
	private static final long serialVersionUID = 9181448189753075665L;
	
	/** The last log factory System property. */
	private static String lastLogFactory = null;

	/** A list of all errors parsed by this logger. */
	private static List<IStatus> errors = null;

	/** A list of all warnings parsed by this logger. */
	private static List<IStatus> warnings = null;

	/** For keeping track and monitoring progress of file creation */
	private static IProgressMonitor monitor;
	private static SubProgressMonitor lastSubMonitor;

	public static final String PLUGIN_ID = org.openiaml.model.codegen.php.OawCodeGenerator.PLUGIN_ID;
	
	/**
	 * org.apache.commons.logging.impl.LogFactoryImpl requires a
	 * constructor with a String parameters, or a NoSuchMethodException
	 * will be thrown.
	 * 
	 * @param name Name of the log
	 */
	public CustomOAWLog(String name) { 
		super(name);
		
		setLevel(LOG_LEVEL_ALL);
		errors = new ArrayList<IStatus>();
		warnings = new ArrayList<IStatus>();
	}
	
	/**
	 * Register this log to commons.logging.
	 */
	public static void registerToLogFactory()
	{
		lastLogFactory = System.getProperty("org.apache.commons.logging.Log");
		System.setProperty("org.apache.commons.logging.Log", CustomOAWLog.class.getName());
		errors = new ArrayList<IStatus>();	// reset errors
		warnings = new ArrayList<IStatus>();	// reset errors
	}

	/**
	 * Reset the log from commons.logging, so that other plugins
	 * continue to work as normal.
	 */
	public static void unregisterFromLogFactory()
	{
		if (lastLogFactory == null) {
			// we have to clear it this way, we can't set it to null, or we will get a NullPointerException
			System.clearProperty("org.apache.commons.logging.Log");
		} else {
			System.setProperty("org.apache.commons.logging.Log", lastLogFactory);
		}

		if (lastSubMonitor != null) {
			lastSubMonitor.done();		// finish last file
			lastSubMonitor = null;
		}
		if (monitor != null) {
			monitor.done();
			monitor = null;
		}
	}
	
	/**
	 * Override any log methods here.
	 */
	@Override
	protected void log(int type, Object message, Throwable t) {
		super.log(type, message, t);
		
		/** should we retrieve the original Throwable from OAW?
		 * OAW mangles up thrown exceptions within code, so we have to
		 * use a workaround.
		 * 
		 * @see {@link org.openiaml.model.codegen.php.OawCodeGenerator#throwException(String message)}
		 */
		if (type >= LOG_LEVEL_ERROR && t == null && message instanceof String) {			
			Throwable original = OawCodeGenerator.getExceptionForMessage(message.toString());
			if (original != null) {
				t = original;	// replace
			}
		}

		// catch any Error (or higher) messages
		if (type >= LOG_LEVEL_ERROR) {
			errors.add(new Status(Status.ERROR, PLUGIN_ID, message.toString(), t));
		}
		
		// catch any Warning (or higher) messages
		if (type >= LOG_LEVEL_WARN) {
			errors.add(new Status(Status.WARNING, PLUGIN_ID, message.toString(), t));
		}
		
		// log file references
		String str = message.toString();
		if (monitor != null && str.startsWith("Opening file : ")) {
			String file = "unknown";
			if (str.contains("\\")) {
				// windows
				file = str.substring(str.lastIndexOf("\\") + 1);
			} else {
				// other
				file = str.substring(str.lastIndexOf("/") + 1);
			}
			
			// last file was created
			if (monitor != null) {
				monitor.worked(1);
			}
			
			// a new file is being created
			monitor.subTask("Creating file " + file + "...");			
		}
	}

	/**
	 * Get the errors parsed out by the log method, in an IStatus
	 * (likely a MultiStatus).
	 * 
	 * @see #log(int, Object, Throwable)
	 * @return OK only if there are no errors (ignores warnings)
	 */
	public static IStatus getErrors() {
		// no error, or only one?
		if (errors.size() == 0)
			return Status.OK_STATUS;
		if (errors.size() == 1)
			return errors.get(0);
		
		// otherwise create a multi status
		IStatus multi = new MultiStatus(PLUGIN_ID, 
				Status.ERROR, 
				errors.toArray(new IStatus[]{}), 
				getMultiErrorMessage(), 
				null);
		return multi;
	}
	
	/**
	 * Get the warnings parsed out by the log method, in an IStatus
	 * (likely a MultiStatus).
	 * 
	 * @see #log(int, Object, Throwable)
	 * @return OK only if there are no warnings (will include errors)
	 */
	public static IStatus getWarnings() {
		// no warning, or only one?
		if (warnings.size() == 0)
			return Status.OK_STATUS;
		if (warnings.size() == 1)
			return errors.get(0);
		
		// otherwise create a multi status
		IStatus multi = new MultiStatus(PLUGIN_ID, 
				Status.WARNING, 
				warnings.toArray(new IStatus[]{}), 
				getMultiWarningMessage(), 
				null);
		return multi;
	}
	
	/**
	 * Get an informative message describing the errors.
	 * 
	 * @return
	 */
	private static String getMultiErrorMessage() {
		return "Multiple errors occured.";
	}
	
	/**
	 * Get an informative message describing the errors.
	 * 
	 * @return
	 */
	private static String getMultiWarningMessage() {
		return "Multiple warnings occured.";
	}
	
	/**
	 * Have we found any errors?
	 * @return
	 */
	public static boolean hasErrors() {
		return errors.size() != 0;
	}
	
	/**
	 * Have we found any warnings?
	 * @return
	 */
	public static boolean hasWarnings() {
		return warnings.size() != 0;
	}

	/**
	 * Set a monitor that we can use to notify of progress through
	 * creating files.
	 * 
	 * @param monitor
	 */
	public static void setMonitor(IProgressMonitor monitor) {
		CustomOAWLog.monitor = monitor;
		lastSubMonitor = null;
	}

}
