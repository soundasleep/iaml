/**
 * 
 */
package org.openiaml.build;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;

/**
 * @author jmwright
 *
 */
public class MyAntRunner implements IApplication {

	private String gmfgenPath = "org.openiaml.model/model/condition.gmfgen";
	private boolean ignoreLoadErrors = false;
	private boolean ignoreValidationErrors = false;

	public class MyWorkbenchAdvisor extends WorkbenchAdvisor {

		/* (non-Javadoc)
		 * @see org.eclipse.ui.application.WorkbenchAdvisor#getInitialWindowPerspectiveId()
		 */
		@Override
		public String getInitialWindowPerspectiveId() {
			return null;		// doesn't matter
		}
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	@Override
	public Object start(IApplicationContext context) throws Exception {
		System.out.println("starting application two, context = " + context);

		System.out.println("activator = " + MyActivator.getDefault());
		
		// lets generate some diagram code already!

		// from WorkbenchAdvisor
		/*
		new Runnable() {
			public void run() {
				// wait until we get one..
				try {
					while (PlatformUI.isWorkbenchRunning() == false) {
						// sleep
						System.out.println("sleeping...");
						Thread.sleep(30);
					}
				} catch (Exception e) {
					e.fillInStackTrace();
				}
				System.out.println("closing...");
				// now that we got one, lets close it
				PlatformUI.getWorkbench().close();
				System.out.println("closed");
			}
		}.run();
		*/
		
		 new Thread(new Runnable() {
			 public void run() {
				WorkbenchAdvisor workbenchAdvisor = new MyWorkbenchAdvisor();
				Display display = PlatformUI.createDisplay();
				 int returnCode = PlatformUI.createAndRunWorkbench(display, workbenchAdvisor);
				 if (returnCode == PlatformUI.RETURN_RESTART) {
					 throw new RuntimeException("restarted");
				 }
			 }
		 }).start();

		System.out.println("generating..");
		MyGenerateOperation op = new MyGenerateOperation(); 
		System.out.println("created op: " + op);
		URI uri = URI.createPlatformResourceURI(gmfgenPath, true);
		//uri = URI.createFileURI(gmfgenPath);
		op.setGenModelURI(uri);
		System.out.println("Set model URI to: " + uri);
		op.setIgnoreLoadErrors(ignoreLoadErrors);
		op.setIgnoreValidationErrors(ignoreValidationErrors);
//		IProgressMonitor monitor = (IProgressMonitor) getProject().getReferences().get(AntCorePlugin.ECLIPSE_PROGRESS_MONITOR);
//		if (monitor == null)
		System.out.println("Setting monitor...");
		IProgressMonitor monitor = BasicMonitor.toIProgressMonitor(new BasicMonitor.Printing(System.out));
		System.out.println("executing");
		
		// turn off auto building
		// stops 'java.lang.NoClassDefFoundError: org/eclipse/jdt/ui/PreferenceConstants'
		// ref: http://www.eclipse.org/newsportal/article.php?id=81377&group=eclipse.platform
		// enableAutoBuild(false);
		
		op.run(monitor);
		
		// we can now close the eclipse instance
		PlatformUI.getWorkbench().close();
		
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
