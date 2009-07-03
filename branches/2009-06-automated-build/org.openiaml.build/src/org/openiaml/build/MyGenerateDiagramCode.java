/*******************************************************************************
 * Copyright (c) 2009 Ecliptical Software Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Ecliptical Software Inc. - initial API and implementation
 *******************************************************************************/
package org.openiaml.build;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin.EclipsePlugin;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.jdt.ui.PreferenceConstants;

public class MyGenerateDiagramCode extends Task {
	
	private String gmfgenPath;
	
	private boolean ignoreLoadErrors;
	
	private boolean ignoreValidationErrors;
	
	public void setGmfgenPath(String gmfgenPath) {
		this.gmfgenPath = gmfgenPath;
	}

	public void setIgnoreLoadErrors(boolean ignoreLoadErrors) {
		this.ignoreLoadErrors = ignoreLoadErrors;
	}
	
	public void setIgnoreValidationErrors(boolean ignoreValidationErrors) {
		this.ignoreValidationErrors = ignoreValidationErrors;
	}

	/**
	 * From: http://dev.eclipse.org/newslists/news.eclipse.platform/msg54558.html
	 * 
	 * @param enable
	 * @return
	 * @throws CoreException
	 */
   public static boolean enableAutoBuild(boolean enable) throws 
   CoreException {
           IWorkspace workspace= ResourcesPlugin.getWorkspace();
           IWorkspaceDescription desc= workspace.getDescription();
           boolean isAutoBuilding= desc.isAutoBuilding();
           if (isAutoBuilding != enable) {
               desc.setAutoBuilding(enable);
               workspace.setDescription(desc);
           }
           return isAutoBuilding;
       }


	@Override
	public void execute() throws BuildException {
		// hack to load it
		// PreferenceConstants.initializeDefaultValues(PreferenceConstants.getPreferenceStore());
		// System.out.println("xxx = " + PreferenceConstants.getPreferenceStore());
		
		// AntRunner r;
		MyAntRunner ant = new MyAntRunner();
		try {
			ant.run((Object) null);
		} catch (Exception e1) {
			throw new BuildException(e1.getMessage(), e1);
		}
		
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
		
		try {
			// turn off auto building
			// stops 'java.lang.NoClassDefFoundError: org/eclipse/jdt/ui/PreferenceConstants'
			// ref: http://www.eclipse.org/newsportal/article.php?id=81377&group=eclipse.platform
			enableAutoBuild(false);
			
			op.run(monitor);
		} catch (CoreException e) {
			throw new BuildException(e);
		}		
	}
}
