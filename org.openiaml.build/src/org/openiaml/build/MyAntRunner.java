/**
 * 
 */
package org.openiaml.build;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * @author jmwright
 *
 */
public class MyAntRunner implements IApplication {

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	@Override
	public Object start(IApplicationContext context) throws Exception {
		System.out.println("starting application two, context = " + context);
		
		// lets generate some diagram code already!
		
		
		return EXIT_OK;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	@Override
	public void stop() {
		System.out.println("stoping application");
		
	}
	
}
