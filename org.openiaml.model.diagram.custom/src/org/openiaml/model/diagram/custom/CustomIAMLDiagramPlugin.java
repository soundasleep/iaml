/**
 * 
 */
package org.openiaml.model.diagram.custom;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * @author jmwright
 *
 */
public class CustomIAMLDiagramPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.openiaml.model.diagram.custom";

	// The shared instance
	private static CustomIAMLDiagramPlugin plugin;

	public CustomIAMLDiagramPlugin() {
		// empty
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static CustomIAMLDiagramPlugin getInstance() {
		return plugin;
	}
	
	// following methods copied from generated IamlDiagramEditorPlugin
	
	public void logError(String error) {
		logError(error, null);
	}

	public void logError(String error, Throwable throwable) {
		if (error == null && throwable != null) {
			error = throwable.getMessage();
		}
		getLog().log(
				new Status(IStatus.ERROR, CustomIAMLDiagramPlugin.PLUGIN_ID,
						IStatus.OK, error, throwable));
		debug(error, throwable);
	}

	public void logInfo(String message) {
		logInfo(message, null);
	}

	public void logInfo(String message, Throwable throwable) {
		if (message == null && throwable != null) {
			message = throwable.getMessage();
		}
		getLog().log(
				new Status(IStatus.INFO, CustomIAMLDiagramPlugin.PLUGIN_ID,
						IStatus.OK, message, throwable));
		debug(message, throwable);
	}

	private void debug(String message, Throwable throwable) {
		if (!isDebugging()) {
			return;
		}
		if (message != null) {
			System.err.println(message);
		}
		if (throwable != null) {
			throwable.printStackTrace();
		}
	}
	
}
