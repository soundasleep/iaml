/**
 * 
 */
package org.openiaml.model.owl.tests;

import java.io.File;

import l3i.sido.emf4sw.ui.ecore2owl.Ecore2OWLFileAction;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.StructuredSelection;
import org.openiaml.model.tests.ModelTestCase;

/**
 * @author jmwright
 *
 */
public class TransformEcoreToOwl extends ModelTestCase {

	public void testTransform() throws Exception {

		// copy over ecore file
		// File source = new File("../org.openiaml.model/model/iaml.ecore");
		File source = new File("model/simple.ecore");
		System.out.println(source.getAbsolutePath());
		assertTrue("Source file exists: " + source, source.exists());
		IFile target = getProject().getFile("simple.ecore");
		assertFalse("Target file should not exist: " + target, target.exists());
		copyFileIntoWorkspace(source, target);
		assertTrue("Target file should exist: " + target, target.exists());
		IFile transformed = getProject().getFile("simple.owl");
		assertFalse("Final file should not exist: " + transformed, transformed.exists());
		
		// try the transformation action
		Ecore2OWLFileAction action = new Ecore2OWLFileAction();
		action.selectionChanged(null, new StructuredSelection(target));
		action.run(null);
		
		// once run, the "target.owl" file should exist
		assertTrue("Final file should exist: " + transformed, transformed.exists());
	}
	
}
