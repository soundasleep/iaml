/**
 * 
 */
package org.openiaml.model.codegen.oaw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;

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
	
	public static final String PLUGIN_ID = org.openiaml.model.codegen.oaw.OawCodeGenerator.PLUGIN_ID;
	
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
	}
	
	/**
	 * Register this log to commons.logging.
	 */
	public static void registerToLogFactory()
	{
		lastLogFactory = System.getProperty("org.apache.commons.logging.Log");
		System.setProperty("org.apache.commons.logging.Log", CustomOAWLog.class.getName());
		errors = new ArrayList<IStatus>();	// reset errors
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
	}
	
	/**
	 * Override any log methods here.
	 */
	@Override
	protected void log(int type, Object message, Throwable t) {
		super.log(type, message, t);

		// catch any Error (or higher) messages
		if (type >= LOG_LEVEL_ERROR) {
			System.out.println("adding error " + message);
			errors.add(new Status(Status.ERROR, PLUGIN_ID, message.toString(), t));
		}
	}

	/**
	 * Get the errors parsed out by the log method, in an IStatus
	 * (likely a MultiStatus).
	 * 
	 * @see #log(int, Object, Throwable)
	 * @return
	 */
	public static IStatus getErrors() {
		// no error, or only one?
		if (errors.size() == 0)
			return Status.OK_STATUS;
		if (errors.size() == 1)
			return errors.get(0);
		
		// otherwise create a multi status
		IStatus multi = new MultiStatus(PLUGIN_ID, Status.ERROR, errors.toArray(new IStatus[]{}), "Multiple errors occured", null);
		return multi;
	}
	
	/**
	 * Have we found any errors?
	 * @return
	 */
	public static boolean hasErrors() {
		return errors.size() != 0;
	}
	
}
