/**
 * 
 */
package org.openiaml.model.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sourceforge.jwebunit.junit.WebTestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.openiaml.model.codegen.ICodeGenerator;
import org.openiaml.model.codegen.oaw.OawCodeGenerator;

/**
 * The JET/Junit test framework now manages everything for you. It will
 * automatically create empty projects to be compiled to.
 * 
 * @author jmwright
 *
 */
public abstract class ModelTestCase extends WebTestCase {

	public final String PLUGIN_ID = "org.openiaml.model.tests";
	public final String ROOT = "src/org/openiaml/model/tests/";
	
	protected IProject project;
	protected IProgressMonitor monitor = new NullProgressMonitor();
	
	public String BASE_URL = "http://localhost:8080/junit-workspace/";
	
	/**
	 * @throws java.lang.Exception
	 */
	protected void setUp() throws Exception {
		project = createProject();

		// set test context
		getTestContext().setBaseUrl(BASE_URL + project.getName() + "/");
	}

	/**
	 * @throws java.lang.Exception
	 */
	protected void tearDown() throws Exception {
		// delete this project automatically
		// project.delete(false, monitor); -- usually we would want to see the output?
	}
	
	/**
	 * Create a new project in our testing environment,
	 * allowing our code generator to output there.
	 * 
	 * TODO it might be helpful to *copy* the desired
	 * model file directly into this new project -- this way,
	 * we can get an IFile directly.
	 * 
	 * @see #PROJECT_NAME
	 * @return the created project
	 * @throws CoreException
	 */
	protected IProject createProject() throws CoreException {
		// create a new project automatically
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		root.refreshLocal(5, monitor);
		IProject project = root.getProject(getProjectName());
		// delete any existing ones
		if (project.exists()) {
			project.delete(true, monitor);
		}
		
		// create it
		project.create(monitor);
		assertTrue(project.exists());
		project.open(monitor);
		
		return project;
	}

	
	/**
	 * Get the project name which will be created, 
	 * by default "testing-" + the className
	 * 
	 * @return
	 */
	public String getProjectName() {
		return "test." + this.getClass().getSimpleName() + "." + new SimpleDateFormat("yyMMddHHmmss").format(new Date());
	}

	/**
	 * Do the transformation and make sure it succeeded.
	 * 
	 * @param inputFile
	 * @throws Throwable if it did not succeed
	 */
	protected void doTransform(String inputFile) throws Exception {
		IStatus test = doTransform(inputFile, getProjectName(), monitor);
		if (!test.isOK()) {
			for (IStatus s : test.getChildren()) {
				System.err.println(s);		// print out the status errors
				if (s.getException() != null)
					throw new RuntimeException("transformation failed", s.getException());	// we will only crash on the first one
			}
			
			if (test.getException() != null) {
				throw new RuntimeException("transformation failed", test.getException());	// force an exception
			}
			assertTrue(test.getMessage(), false);	// force a fail
		}
	}
	
	/**
	 * Transform a model file into some code generated output. This currently contains the logic on
	 * which code generation solution to use.
	 */
	protected IStatus doTransform(String filename, String outputDir, IProgressMonitor monitor) throws IOException, URISyntaxException, CoreException {
		// which transform to use?
		// return doTransformJET(filename, outputDir, monitor);
		return doTransformOAW(filename, outputDir, monitor);
	}
	
	/**
	 * Set up transformation for openArchitectureWare. In particular, copies the model file from the
	 * development platform to the runtime platform, because we cannot refer to models
	 * external to the runtime platform (all model inputs are prefixed with 'platform:/resource/').
	 */
	protected IStatus doTransformOAW(String filename, String outputDir, IProgressMonitor monitor) throws FileNotFoundException, CoreException {
		// we can't use platform:/ resource, because we are trying to refer to a resource
		// that doesn't exist in this platform.
		// so we need to copy it to our local platform and execute it from there
		String tempName = "temp.iaml";
		IFile file = project.getFile(tempName);		// create file in the project
		
		FileInputStream fis = new FileInputStream(filename);
		file.create(fis, true, monitor);		// copy file
		
		assertTrue(file.exists());		// file should now exist
		
		// now we can do the transform
		return doTransformOAWWorkflow(file, monitor);
	}

	/**
	 * Force a complete refresh of the entire project, and
	 * halt execution until it's completed.
	 * 
	 * @return
	 * @throws CoreException
	 */
	protected IStatus refreshProject() throws CoreException {
		class HaltProgressMonitor extends NullProgressMonitor {
			@Override
			public void setCanceled(boolean cancelled) {
				isDone = true;	// bail early
				super.setCanceled(cancelled);
			}

			private boolean isDone = false;
			public synchronized boolean isDone() {
				return isDone;
			}

			@Override
			public void done() {
				isDone = true;
				super.done();
			}
		}
		
		HaltProgressMonitor m = new HaltProgressMonitor();
		project.refreshLocal(IResource.DEPTH_INFINITE, m);
		try {
			while (!m.isDone()) {
				Thread.sleep(300);
			}
		} catch (InterruptedException e) {
			return new Status(Status.ERROR, PLUGIN_ID, "refresh thread was interrupted", e);
		}

		return Status.OK_STATUS;
				
	}
	
	/**
	 * Transform a filename using OpenArchitectureWare. It also forces
	 * a filesystem refresh of the particular project directory,
	 * and does not stop until the refresh is complete.
	 * 
	 * TODO refactor into CodegenTestCase
	 */
	protected IStatus doTransformOAWWorkflow(IFile filename,
			IProgressMonitor monitor) throws CoreException {
		
		ICodeGenerator runner = new OawCodeGenerator();		
		runner.generateCode(filename, monitor);
		
		// once generated, we need to refresh the workspace before
		// we can carry on testing (normally, we would just let Eclipse automatically
		// refresh the resources)
		
		// we need to *force* refresh the workspace
		return refreshProject();
	}

	/**
	 * @return the project
	 */
	public IProject getProject() {
		return project;
	}
	
	/**
	 * Copy a local file into the Eclipse workspace. Makes sure it doesn't
	 * already exist, and that it does exist once this method is completed.
	 * 
	 * @param sourceName
	 * @param target
	 * @return the target file
	 * @throws Exception
	 */
	protected IFile copyFileIntoWorkspace(String sourceName, IFile target) throws Exception {
		// first, copy local file into project workspace
		File sourceFile = new File(sourceName);
		assertTrue("source file '" + sourceFile + "' exists", sourceFile.exists());

		assertFalse("target file '" + target + "' should not exist yet", target.exists());
		
		// copy
		target.create(new FileInputStream(sourceFile), true, monitor);
		
		// check
		assertTrue("target file '" + target + "' should now exist", target.exists());
		
		return target;
	}

	/**
	 * Helper method: assert A >= B.
	 * 
	 * @param expected expected value (B)
	 * @param actual actual value (A)
	 */
	protected void assertGreaterEq(int expected, int actual) {
		assertTrue("expected >= than " + expected + ", but actually had " + actual, actual >= expected);
	}
	
}
