/*******************************************************************************
 * Copyright (c) 2009 L3i ( http://l3i.univ-larochelle.fr ).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.openiaml.model.owl.tests;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;

import l3i.sido.emf4sw.rdf.RDFPackage;
import l3i.sido.emf4sw.ui.rdf.RDFModel2RDFXMLAction;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IExtractor;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFReferenceModel;
import org.eclipse.m2m.atl.core.launch.ILauncher;
import org.eclipse.m2m.atl.core.service.CoreService;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.osgi.framework.Bundle;

/**
 * Copied from eclipseuml2owl project.
 * 
 */
public class Simple2RDFModelAction implements IObjectActionDelegate {

	private static IInjector injector;

	private static IExtractor extractor;

	private static IReferenceModel rdfMetamodel;

	private static IReferenceModel simple;

	private static URL asmURL;

	private ISelection currentSelection;

	static {
		Bundle bundle = Platform.getBundle("org.openiaml.model.owl");
		asmURL = bundle.getEntry("atl/simple2rdf.asm");
		if (asmURL == null) {
			asmURL = Simple2RDFModelAction.class.getResource("atl/simple2rdf.asm");
		}
		try {
			injector = CoreService.getInjector("EMF");
			extractor = CoreService.getExtractor("EMF");
		} catch (ATLCoreException e) {
			e.printStackTrace();
		}					
	}

	/**
	 * Constructor for Action1.
	 */
	public Simple2RDFModelAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
	 *      org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		IStructuredSelection iss = (IStructuredSelection)currentSelection;
		for (Iterator<?> iterator = iss.iterator(); iterator.hasNext();) {
			try {
				transform((IFile)iterator.next());				
			} catch (CoreException e) {
				throw new RuntimeException(e.getMessage(), e);
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			} catch (ATLCoreException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}

	private void transform(IFile file) throws CoreException, IOException, ATLCoreException {
		ModelFactory factory = CoreService.createModelFactory("EMF");

		rdfMetamodel = factory.newReferenceModel();
		((EMFInjector)injector).inject((EMFReferenceModel)rdfMetamodel, RDFPackage.eINSTANCE.eResource());

		simple = factory.newReferenceModel();
		//injector.inject(simple, "http://www.eclipse.org/emf/2002/Ecore");
		injector.inject(simple, "http://openiaml.org/simple");

		ILauncher launcher = CoreService.getLauncher("EMF-specific VM");
		launcher.initialize(Collections.<String, Object> emptyMap());

		IModel model = factory.newModel(simple);
		IModel owlModel = factory.newModel(rdfMetamodel);

		injector.inject(model, file.getFullPath().toString());

		launcher.addInModel(model, "IN", "simple");
		launcher.addOutModel(owlModel, "OUT", "rdf");

		launcher.launch(ILauncher.RUN_MODE, new NullProgressMonitor(), 
				Collections.<String, Object> emptyMap(), 
				new Object[] {asmURL.openStream()}
		);

		String name = file.getName();
		name = name.substring(0, name.length() - (file.getFileExtension().length()+1)) + ".rdf-ecore";

		extractor.extract(owlModel, file.getFullPath().removeLastSegments(1).append(name).toString());
		
		// once saved as RDFecore, load this up and save it as RDF/XML
		RDFModel2RDFXMLAction action = new RDFModel2RDFXMLAction();
		IFile targetRdf = null;
		if (file.getParent() instanceof IFolder) {
			targetRdf = ((IFolder) file.getParent()).getFile(name);
		} else if (file.getParent() instanceof IProject) {
			targetRdf = ((IProject) file.getParent()).getFile(name);
		} else {
			throw new RuntimeException("Could not get parent from '" + file.getParent() + "': class " + file.getParent().getClass());
		}
		action.selectionChanged(null, new StructuredSelection( targetRdf ));
		action.run(null);

		file.getParent().refreshLocal(IProject.DEPTH_INFINITE, null);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.currentSelection = selection;
	}

}
