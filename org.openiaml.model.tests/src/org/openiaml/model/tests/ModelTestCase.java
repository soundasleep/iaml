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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPathExpressionException;

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
import org.openiaml.model.codegen.oaw.OawCodeGeneratorWithRuntime;
import org.openiaml.model.tests.xpath.DefaultXpathTestCase;
import org.openiaml.model.tests.xpath.IterableElementList;
import org.openiaml.model.tests.xpath.XpathTestCase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * The JET/Junit test framework now manages everything for you. It will
 * automatically create empty projects to be compiled to.
 * 
 * @author jmwright
 *
 */
public abstract class ModelTestCase extends WebTestCase implements XpathTestCase {

	public static final String PLUGIN_ID = "org.openiaml.model.tests";
	public static final String ROOT = "src/org/openiaml/model/tests/";
	
	protected IProject project;
	protected IProgressMonitor monitor;
	
	public static final String BASE_URL = "http://localhost:8080/junit-workspace/";
	// from ./junit-workspace/project_name/output/ to target
	public static final String CONFIG_RUNTIME = "../../../workspace-iaml/org.openiaml.model.runtime/src/include/";
	public static final String BASE_URL_RUNTIME = "http://localhost:8080/iaml-runtime/"; 
	public static final String CONFIG_WEB = "/iaml-runtime/";
	
	/** 
	 * TODO a temporary variable to make sure we don't set up
	 * the same project twice.
	 */
	protected boolean isSetup = false;
	
	/**
	 * @throws java.lang.Exception
	 */
	protected void setUp() throws Exception {
		// parent setup
		super.setUp();
		
		monitor = new NullProgressMonitor();
		
		project = createProject();
		isSetup = true;

		// set test context
		getTestContext().setBaseUrl(BASE_URL + project.getName() + "/");
	}

	/**
	 * @throws java.lang.Exception
	 */
	protected void tearDown() throws Exception {
		// delete this project automatically
		// project.delete(false, monitor); -- usually we would want to see the output?
		
		// remove reference to project
		if (project != null) {
			project.close(monitor);
			project = null;
		}
		
		monitor = null;
		transformStatus = null;
		
		super.tearDown();
	}
	
	/**
	 * Create a new project in our testing environment,
	 * allowing our code generator to output there.
	 * 
	 * We can also copy files directly from our testing environment
	 * to this new project by using
	 * {@link #copyFileIntoWorkspace(String, IFile)}.
	 * 
	 * @see #getProjectName()
	 * @see #copyFileIntoWorkspace(String, IFile)
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
	 * A counter of projects created, so we don't run into a case
	 * where the same project is created in the same second.
	 * 
	 * @see #getProjectName()
	 */
	public static int projectCounter = 0;
	
	/**
	 * Get the project name which will be created, 
	 * by default "testing-" + the className
	 * 
	 * @return
	 */
	public String getProjectName() {
		return "test." + this.getClass().getSimpleName() + "." + new SimpleDateFormat("yyMMddHHmmss").format(new Date()) + projectCounter++;
	}

	protected IStatus transformStatus = null;
	
	/**
	 * Store the last IStatus of doTransform().
	 * @see #doTransform(String)
	 */
	public IStatus getTransformStatus() {
		return transformStatus;
	}

	/**
	 * Do the transformation and make sure it succeeded.
	 * The result of the transformation is stored in {@link #getTransformStatus()}.
	 * 
	 * @param inputFile
	 * @throws Throwable if it did not succeed
	 */
	protected void doTransform(String inputFile) throws Exception {
		transformStatus = doTransform(inputFile, getProjectName(), monitor);
		if (!transformStatus.isOK()) {
			String firstMessage = null;
			for (IStatus s : transformStatus.getChildren()) {
				System.err.println(s);		// print out the status errors
				if (s.getException() != null)
					throw new TransformationException("Transformation failed: " + s.getException().getMessage(), s.getException());	// we will only crash on the first one
				if (s.getMessage() != null && !s.getMessage().trim().isEmpty()) {
					firstMessage = s.getMessage();
				}
			}
			
			if (transformStatus.getException() != null) {
				throw new TransformationException("Transformation failed: " + transformStatus.getException().getMessage(), transformStatus.getException());	// force an exception
			}
			// force a fail
			throw new TransformationException("Transformation did not pass successfully: " + transformStatus.getMessage() + (firstMessage == null ? "" : ", first message: " + firstMessage));
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
	 * <p>
	 * Transform a filename using OpenArchitectureWare. It also forces
	 * a filesystem refresh of the particular project directory,
	 * and does not stop until the refresh is complete.
	 * </p>
	 * 
	 * <p>
	 * Sadly, we cannot refactor out the initialisation of the WorkflowRunner
	 * in OAW, because the WorkflowRunners are not designed to be run more than 
	 * once per invoke() method:
	 * <pre>
	 * org.openarchitectureware.workflow.ConfigurationException: A default outlet is allready registered!
	 * at org.openarchitectureware.xpand2.output.OutputImpl.addOutlet(OutputImpl.java:49)
	 * </pre>
	 * </p>
	 * 
	 * TODO refactor into CodegenTestCase
	 */
	protected IStatus doTransformOAWWorkflow(IFile filename,
			IProgressMonitor monitor) throws CoreException {
		
		// create some properties
		Map<String,String> runtimeProperties = getRuntimeProperties();
		
		ICodeGenerator runner = new OawCodeGeneratorWithRuntime();
		IStatus status = runner.generateCode(filename, monitor, runtimeProperties);
		
		if (!status.isOK()) {
			// bail early
			return status;
		}
		
		// once generated, we need to refresh the workspace before
		// we can carry on testing (normally, we would just let Eclipse automatically
		// refresh the resources)
		
		// we need to *force* refresh the workspace
		return refreshProject();
	}

	/**
	 * Generate the properties required for ICodeGenerator.
	 * 
	 * @see org.openiaml.model.codegen.ICodeGenerator#generateCode(IFile, IProgressMonitor, Map)
	 * @return
	 */
	protected Map<String, String> getRuntimeProperties() {
		Map<String,String> properties = new HashMap<String,String>();

		properties.put("config_runtime", CONFIG_RUNTIME);
		properties.put("config_web", CONFIG_WEB);

		return properties;
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
	
	
	/**
	 * Assert an instance of a class is in a given list.
	 * 
	 * @param class1 class to check
	 * @param used list of class instances
	 */
	protected void assertClassIn(Class<?> class1,
			List<?> used) {
		for (Object m : used) {
			if (m.getClass().equals(class1))
				return;
		}
		fail("Migrator '" + class1.getSimpleName() + "' should be found in list of used migrators: " + used);
	}

	/**
	 * Assert an instance of a class is not in a given list. 
	 * 
	 * @param class1 class to check
	 * @param used list of class instances
	 */
	protected void assertClassNotIn(Class<?> class1,
			List<?> used) {
		for (Object m : used) {
			if (m.getClass().equals(class1))
				fail("Migrator '" + class1.getSimpleName() + "' should not be found in list of used migrators: " + used);
		}
	}
	
	/**
	 * An exception has occured while transforming the model.
	 * 
	 * @author jmwright
	 *
	 */
	public class TransformationException extends Exception {

		private static final long serialVersionUID = 1L;
		
		public TransformationException(String message, Throwable e) {
			super(message, e);
		}
		public TransformationException(Throwable e) {
			super(e.getMessage(), e);
		}
		public TransformationException(String message) {
			super(message);
		}
		
	}
	

	/**
	 * XPath helper methods.
	 * @see org.openiaml.model.tests.xpath.XpathTestCase
	 */
	private static XpathTestCase xpath = new DefaultXpathTestCase();

	/**
	 * Get the first node result from an XPath query. Returns the 
	 * node found, or null if none or more than one is found.
	 * 
	 * @returns the found node, or null if none is found (or more than one is found)
	 */
	@Override
	public Element hasXpathFirst(Element e, String query)
			throws XPathExpressionException {
		return xpath.hasXpathFirst(e, query);
	}

	/**
	 * Get the first node result from an XPath query. Returns the 
	 * node found, or null if none or more than one is found.
	 * 
	 * @returns the found node, or null if none is found (or more than one is found)
	 */
	@Override
	public Element hasXpathFirst(Document e, String query)
			throws XPathExpressionException {
		return xpath.hasXpathFirst(e, query);
	}


	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.xpath.XpathTestCase#xpath(org.w3c.dom.Node, java.lang.String)
	 */
	@Override
	public IterableElementList xpath(Node doc, String query)
			throws XPathExpressionException {
		return xpath.xpath(doc, query);
	}


	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.xpath.XpathTestCase#xpathFirst(org.w3c.dom.Document, java.lang.String)
	 */
	@Override
	public Element xpathFirst(Document doc, String query)
			throws XPathExpressionException {
		return xpath.xpathFirst(doc, query);
	}


	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.xpath.XpathTestCase#xpathFirst(org.w3c.dom.Element, java.lang.String)
	 */
	@Override
	public Element xpathFirst(Element e, String query)
			throws XPathExpressionException {
		return xpath.xpathFirst(e, query);
	}

	
}
