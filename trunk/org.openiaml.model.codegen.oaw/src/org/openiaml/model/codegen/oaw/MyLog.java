/**
 * 
 */
package org.openiaml.model.codegen.oaw;

import java.io.Serializable;

import org.apache.commons.logging.impl.SimpleLog;

/**
 * Allows OAW logging to go through a custom Eclipse class defined in the
 * workspace, rather than using a properties file (not accessible from the
 * current class loader).
 * 
 * The OAW logs all use commons.logging directly, so we have to 
 * override the log that commons.logging uses.
 * 
 * From http://oaw-forum.itemis.de/forum/viewtopic.php?forum=1&showtopic=1486 (german)
 * 
 * @author jmwright
 *
 */
public class MyLog extends SimpleLog implements org.apache.commons.logging.Log, Serializable {
	private static final long serialVersionUID = 9181448189753075665L;
	
	private static String lastLogFactory = null;
	
	/**
	 * Register this log to commons.logging.
	 */
	public static void registerToLogFactory()
	{
		lastLogFactory = System.getProperty("org.apache.commons.logging.Log");
		System.setProperty("org.apache.commons.logging.Log", MyLog.class.getName());
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
	 * org.apache.commons.logging.impl.LogFactoryImpl requires a
	 * constructor with a String parameters, or a NoSuchMethodException
	 * will be thrown.
	 * 
	 * @param name Name of the log
	 */
	public MyLog(String name) { 
		super(name);
		
		setLevel(LOG_LEVEL_ALL);
	}

	/**
	 * Override any log methods here.
	 */
	@Override
	protected void log(int type, Object message, Throwable t) {
		// TODO Auto-generated method stub
		super.log(type, "[my log] " + message, t);
	}

}
