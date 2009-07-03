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

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.gmf.codegen.gmfgen.GenDiagram;
import org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator;
import org.eclipse.gmf.codegen.util.Generator;
import org.eclipse.gmf.internal.common.migrate.ModelLoadHelper;

@SuppressWarnings("restriction")
public class MyGenerateOperation {

	private URI genModelURI;
	
	private boolean ignoreLoadErrors;
	
	private boolean ignoreValidationErrors;

	private GenEditorGenerator genModel;

	public void setGenModelURI(URI genModelURI) {
		this.genModelURI = genModelURI;
	}
	
	public void setIgnoreLoadErrors(boolean ignoreLoadErrors) {
		this.ignoreLoadErrors = ignoreLoadErrors;
	}
	
	public void setIgnoreValidationErrors(boolean ignoreValidationErrors) {
		this.ignoreValidationErrors = ignoreValidationErrors;
	}

	public void run(IProgressMonitor monitor) throws CoreException {
		if (genModelURI == null)
			throw new CoreException(new Status(IStatus.ERROR, MyActivator.PLUGIN_ID, 0, "Missing GenModel URI.", null));

		try {
			Diagnostic loadStatus = loadGenModel();
			if (!canProcessGMFGenModel(loadStatus))
				throw new CoreException(BasicDiagnostic.toIStatus(loadStatus));

			Assert.isNotNull(getGenModel());
			Diagnostic validStatus = validateGenModel();
			if (validStatus.getSeverity() != Diagnostic.OK) {
				if (!ignoreValidationErrors)
					throw new CoreException(BasicDiagnostic.toIStatus(validStatus));
			}

			IStatus runStatus = doRun(monitor);
			if (!runStatus.isOK())
				throw new CoreException(runStatus);
		} finally {
			unloadGenModel();
		}
	}

	private boolean canProcessGMFGenModel(Diagnostic loadStatus) {
		if (loadStatus.getSeverity() != Diagnostic.OK) {
			if (genModel == null || !ignoreLoadErrors)
				return false;
		}
		
		return true;
	}

	private IStatus doRun(IProgressMonitor monitor) {
		Generator g = createGenerator();
		IStatus runStatus;
		try {
			g.run(monitor);
			runStatus = g.getRunStatus();
		} catch (InterruptedException e) {
			runStatus = Status.CANCEL_STATUS;
		}
		
		return runStatus;
	}
	
	private Generator createGenerator() {
		return new Generator(getGenModel(), MyActivator.getDefault().getEmitters(getGenModel()));
	}

	private GenEditorGenerator getGenModel() {
		return genModel;
	}

	private Diagnostic loadGenModel() {
		ResourceSet srcResSet = new ResourceSetImpl();
		srcResSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());		
		MyLoadOperation loadHelper = new MyLoadOperation(srcResSet, genModelURI);
		Object root = loadHelper.getContentsRoot();
		if (root instanceof GenDiagram)
			genModel = ((GenDiagram) root).getEditorGen();
		else if (root instanceof GenEditorGenerator)
			genModel = (GenEditorGenerator) root;

		if (genModel != null && genModel.getDomainGenModel() != null)
			genModel.getDomainGenModel().reconcile();

		return loadHelper.getDiagnostics();
	}

	private void unloadGenModel() {
		if (genModel != null && genModel.eResource() != null)
			genModel.eResource().unload();

		genModel = null;
	}

	private Diagnostic validateGenModel() {
		return Diagnostician.INSTANCE.validate(getGenModel());
	}
}
