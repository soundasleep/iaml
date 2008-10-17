/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import net.sourceforge.jwebunit.junit.WebTestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jet.JET2Platform;

/**
 * The JET/Junit test framework now manages everything for you. It will
 * automatically create empty projects to be compiled to.
 * 
 * @author jmwright
 *
 */
public class SimpleTestCase extends WebTestCase {
	
	private final String PROJECT_NAME = "testing-" + this.getClass().getSimpleName();
	private IProject project;
	private IProgressMonitor monitor = new NullProgressMonitor();
	
	/**
	 * @throws java.lang.Exception
	 */
	protected void setUp() throws Exception {
		project = createProject();
		doTransform("models/simple.iaml");

		// set test context
		getTestContext().setBaseUrl("file:///" + project.getLocation());
	}

	/**
	 * @throws java.lang.Exception
	 */
	protected void tearDown() throws Exception {
		// delete this project automatically
		project.delete(false, monitor);
	}
	
	/**
	 * Create a new project in our testing environment,
	 * allowing JET to output there.
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
		IProject project = root.getProject(PROJECT_NAME);
		// delete any existing ones
		if (project.exists()) {
			project.delete(false, monitor);
		}
		
		// create it
		project.create(monitor);
		assertTrue(project.exists());
		project.open(monitor);
		
		return project;
	}
	
	public void testSitemap() {
		
		IFile sitemap = project.getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());

		System.out.println(sitemap.getProjectRelativePath().toString());
		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("untitled");
		assertTitleMatch("sitemap");

	}
	
	/**
	 * Do the transformation and make sure it succeeded.
	 * 
	 * @param inputFile
	 * @throws Throwable if it did not succeed
	 */
	private void doTransform(String inputFile) throws Exception {
		IStatus test = doTransform(inputFile, PROJECT_NAME, monitor);
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
	 * Transform a filename using JET. The generation options are defined
	 * by the JET plugin.
	 * 
	 * @param filename The file to transform
	 * @param monitor A monitor
	 * @return The status of the transform
	 * @throws IOException 
	 * @throws URISyntaxException 
	 */
	protected IStatus doTransform(String filename, String outputDir, IProgressMonitor monitor) throws IOException, URISyntaxException {
		File f = new File(filename);
		assertTrue("file " + filename + " exists", f.exists());
		assertTrue("file " + filename + " can be read", f.canRead());

		if (!(f.exists() && f.canRead()))
			throw new IOException("Cannot read file " + filename);
		String fileContents = openFile(f);
		
		Map<String,Object> variables = new HashMap<String,Object>();
		
		// we need to set this parameter ourselves
		variables.put("org.eclipse.jet.resource.project.name", outputDir);
		// set any other parameters here
		
		// run the transformation on a certain JET plugin, loading the data as XML
		// (although the JET plugin should define the real loader)
		return JET2Platform.runTransformOnString("org.openiaml.model.codegen.jet", 
				fileContents, "xml", variables, monitor);
	}	

	/**
	 * Open a file and return its entire contents as a string
	 * 
	 * @param f The file to read
	 * @return The contents of the file
	 * @throws IOException If the file could not be opened
	 */
	private String openFile(File f) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f));
		StringBuffer sb = new StringBuffer();
		String s = null;
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		return sb.toString();
	}
	
}
