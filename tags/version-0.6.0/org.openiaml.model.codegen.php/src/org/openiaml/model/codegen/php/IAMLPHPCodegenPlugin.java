package org.openiaml.model.codegen.php;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class IAMLPHPCodegenPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.openiaml.model.codegen.php";

	// The shared instance
	private static IAMLPHPCodegenPlugin plugin;
	
	/**
	 * The constructor
	 */
	public IAMLPHPCodegenPlugin() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

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
	public static IAMLPHPCodegenPlugin getDefault() {
		return plugin;
	}

}
