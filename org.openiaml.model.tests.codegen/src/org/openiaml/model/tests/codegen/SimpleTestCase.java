/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jet.JET2Platform;

/**
 * How to set up the JET/JUnit tests:
 * 
 * <ol>
 *  <li>Follow the instructions at http://www.jevon.org/wiki/Setting_up_an_EMF/JET_testing_project_with_JUnit_and_Eclipse
 *  <li>Make sure that your "JUnit plugin test" run settings have a valid
 *    different workspace directory!
 *  <li>Make sure that this workspace has, already existing, a project named
 *    RUNTIME_WORKSPACE
 *    (or you may get 'Resource Not Found' Jet Exceptions)
 *  <li>Output will then go into the RUNTIME_PROJECT/output directory (or as defined
 *    in the .jet files)
 * 
 * @author jmwright
 *
 */
public class SimpleTestCase extends TestCase {
	
	private final String RUNTIME_PROJECT = "testing/"; 
	
	/**
	 * @throws java.lang.Exception
	 */
	protected void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	protected void tearDown() throws Exception {
	}
	
	/**
	 * unfortunately I don't know how to get access to an IFile (I think 
	 * IFiles are for workspace files only anyway), so we have to
	 * load the file manually and then config JET to emulate a workspace 
	 * being there.
	 * 
	 * @throws Throwable 
	 */
	public void test1() throws Throwable {
		// it would be nice to get resources via the bundle, but I don't know enough
		// about bundle loading to get an IFile, or a File, from a URL, which is what
		// getBundle().getResource() returns (but in "bundleresource://.." form)
		// doTransform(Platform.getBundle("org.openiaml.model.tests.codegen").getResource("tests/simple.iaml"), new File("/output"));
		
		doTransform("models/simple.iaml", ".");
		
		// workspace location = Platform.getInstanceLocation().getURL()
	}

	
	/**
	 * Do the transformation and make sure it succeeded.
	 * 
	 * @param inputFile
	 * @throws Throwable if it did not succeed
	 */
	private void doTransform(String inputFile, String outputDir) throws Throwable {
		IStatus test = doTransform(inputFile, outputDir, new NullProgressMonitor());
		if (!test.isOK()) {
			for (IStatus s : test.getChildren()) {
				System.err.println(s);		// print out the status errors
				if (s.getException() != null)
					throw s.getException();
			}
			
			if (test.getException() != null) {
				throw test.getException();	// force an exception
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
		variables.put("org.eclipse.jet.resource.project.name", RUNTIME_PROJECT);
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
