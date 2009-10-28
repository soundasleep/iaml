/**
 * 
 */
package org.openiaml.verification.crocopat;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * @author jmwright
 *
 */
public class VerificationCrocopatPlugin extends Plugin {
	
	// The plug-in ID
	public static final String PLUGIN_ID = "org.openiaml.verification.crocopat";

	private static VerificationCrocopatPlugin instance;
	
	public static VerificationCrocopatPlugin getInstance() {
		return instance;
	}
	
	/**
	 * This method should only be called by the Eclipse framework.
	 * 
	 * @deprecated Only the Eclipse framework should use this; see {@link #getInstance()}. 
	 */
	public VerificationCrocopatPlugin() {
		super();
		instance = this;
	}
		
	/**
	 * Get the given file in our current bundle. 
	 * 
	 * @param filename
	 * @return
	 * @throws IOException 
	 */
	public URL getResolvedFile(String filename) throws IOException {
		URL file = getBundle().getEntry(filename);
		
		return FileLocator.resolve(file);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		instance = null;
		super.stop(context);
	}
	
}
