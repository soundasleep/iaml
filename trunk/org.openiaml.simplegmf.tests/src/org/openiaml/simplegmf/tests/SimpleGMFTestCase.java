/**
 * 
 */
package org.openiaml.simplegmf.tests;

import java.io.File;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.openiaml.modeltesting.EclipseProject;
import org.openiaml.simplegmf.codegen.SimpleGMFCodeGenerator;

/**
 * Tests the code generation functionality of SimpleGMF.
 * 
 * @author jmwright
 *
 */
public class SimpleGMFTestCase extends TestCase {

	/**
	 * 
	 * @throws Exception
	 */
	public void testDump() throws Exception {

		// create new project
		EclipseProject project = new EclipseProject("simplegmf");
		project.createProject();
		
		// copy local test instance
		IFile modelFile = EclipseProject.copyFileIntoWorkspace(new File("test.simplegmf"), project.getFile("test.simplegmf"), new NullProgressMonitor());
				
		// generate code
		SimpleGMFCodeGenerator codegen = new SimpleGMFCodeGenerator();
		IStatus status = codegen.generateCode(modelFile.getLocation().toFile());
		
		// check everything is OK
		assertTrue(status.toString(), status.isOK());
		
		// files should be created
		IFile check = project.getFile("root.gmftool");
		assertTrue(check + " should exist", check.exists());
		
	}
	
}
