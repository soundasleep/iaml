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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;

import ca.ecliptical.gmf.ant.GenerateDiagramCodeOperation;

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

	@Override
	public void execute() throws BuildException {
		System.out.println("generating..");
		MyGenerateOperation op = new MyGenerateOperation();
		System.out.println("created op: " + op);
		URI uri = URI.createPlatformResourceURI(gmfgenPath, true);
		uri = URI.createFileURI(gmfgenPath);
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
			op.run(monitor);
		} catch (CoreException e) {
			throw new BuildException(e);
		}		
	}
}
