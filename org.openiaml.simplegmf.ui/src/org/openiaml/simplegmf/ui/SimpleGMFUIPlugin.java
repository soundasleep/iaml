package org.openiaml.simplegmf.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class SimpleGMFUIPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.openiaml.simplegmf.ui";

	// The shared instance
	private static SimpleGMFUIPlugin plugin;
	
	/**
	 * The constructor
	 */
	public SimpleGMFUIPlugin() {
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
	public static SimpleGMFUIPlugin getInstance() {
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
				new Status(IStatus.ERROR, SimpleGMFUIPlugin.PLUGIN_ID,
						IStatus.OK, error, throwable));
	}

	public void logInfo(String message) {
		logInfo(message, null);
	}

	public void logInfo(String message, Throwable throwable) {
		if (message == null && throwable != null) {
			message = throwable.getMessage();
		}
		getLog().log(
				new Status(IStatus.INFO, SimpleGMFUIPlugin.PLUGIN_ID,
						IStatus.OK, message, throwable));
	}

	public void logError(Throwable e) {
		logError(e.getMessage(), e);
	}

	public void log(IStatus multi) {
		getLog().log(multi);
	}
	
}
