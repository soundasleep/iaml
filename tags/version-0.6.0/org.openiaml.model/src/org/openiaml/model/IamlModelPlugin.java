package org.openiaml.model;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class IamlModelPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.openiaml.model";

	// The shared instance
	private static IamlModelPlugin plugin;
	
	/**
	 * The constructor
	 */
	public IamlModelPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static IamlModelPlugin getInstance() {
		return plugin;
	}

	public void logError(String error) {
		logError(error, null);
	}

	public void logError(String error, Throwable throwable) {
		if (error == null && throwable != null) {
			error = throwable.getMessage();
		}
		getLog().log(
				new Status(IStatus.ERROR, IamlModelPlugin.PLUGIN_ID,
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
				new Status(IStatus.INFO, IamlModelPlugin.PLUGIN_ID,
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

	public void log(IStatus multi) {
		Platform.getLog(this.getBundle()).log(multi);
	}
	
}
