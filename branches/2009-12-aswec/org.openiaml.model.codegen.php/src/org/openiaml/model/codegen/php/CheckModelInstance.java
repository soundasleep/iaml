/**
 * 
 */
package org.openiaml.model.codegen.php;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.openarchitectureware.workflow.WorkflowRunner;
import org.openarchitectureware.workflow.util.ProgressMonitorAdapter;

/**
 * <p>Allows programatically validating an instance of an InternetApplication
 * with OAW checks. We need to use a workflow file in order to
 * set everything up properly.</p>
 * 
 * <p>If the checks process returns either errors or warnings, these
 * are returned in {@link #checkModel(IFile, IProgressMonitor)}.</p>
 * 
 * @author jmwright
 *
 */
public class CheckModelInstance {
	
	public static final String PLUGIN_ID = "org.openiaml.model.codegen.php"; 
	
	/**
	 * Check the instance in a given model file.
	 * 
	 * Returns an OK status, or a MultiStatus containing any 
	 * errors or warnings found.
	 * 
	 */
	public IStatus checkModel(IFile file, IProgressMonitor monitor) {

		monitor.beginTask("Checking model instance '" + file.getName() + "'", 1);
		
		/*
		 * We have to do some magic to enable OAW logging to go through
		 * our own class. We have to provide this information to 
		 * commons.logging directly.
		 * 
		 * Based on http://oaw-forum.itemis.de/forum/viewtopic.php?forum=1&showtopic=1486 (german)
		 */
		ClassLoader oldcl = Thread.currentThread().getContextClassLoader();
		
		try {
			// to enable custom logging
			Thread.currentThread().setContextClassLoader(CheckModelInstance.class.getClassLoader());
			CustomOAWLog.registerToLogFactory();
			
			// notify the logger of our monitor, so we can keep track
			// of created files
			CustomOAWLog.setMonitor(monitor);
			
			String wfFile = "src/workflow/checks.oaw";
			Map<String,String> properties = new HashMap<String,String>();
			properties.put("model", file.getFullPath().toString());
			
			Map<String,Object> slotContents = new HashMap<String,Object>();
			
			executeWorkflow(wfFile, monitor, properties, slotContents);
			
			// refresh output folder
			try {
				file.getProject().refreshLocal(IResource.DEPTH_INFINITE, new SubProgressMonitor(monitor, 1));
			} catch (CoreException e) {
				return new Status(Status.WARNING, PLUGIN_ID, "Could not refresh local project", e);
			}
			
			if (CustomOAWLog.hasErrors()) {
				return CustomOAWLog.getErrors();
			}
			return Status.OK_STATUS;
		} finally {
			// reset the classloader/log
			Thread.currentThread().setContextClassLoader(oldcl);
			CustomOAWLog.unregisterFromLogFactory();
			
			// the monitor is done
			monitor.done();
		}
			
	}
	
	/**
	 * <p>Execute the workflow in the given workflow file.
	 * By default, this creates a new {@link WorkflowRunner} and then
	 * calls {@link WorkflowRunner#run(String, org.openarchitectureware.workflow.monitor.ProgressMonitor, Map, Map)}.
	 * </p>
	 * 
	 * <p>This method is extracted so it can be overridden if necessary.</p>
	 * 
	 * @param wfFile
	 * @param monitor
	 * @param properties
	 * @param slotContents
	 */
	protected void executeWorkflow(String wfFile, IProgressMonitor monitor, Map<String, String> properties, Map<String, ?> slotContents) {
		new WorkflowRunner().run(wfFile,
				new ProgressMonitorAdapter(monitor), properties, slotContents);
	}
	
}
