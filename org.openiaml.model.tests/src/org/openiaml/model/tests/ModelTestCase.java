/**
 * 
 */
package org.openiaml.model.tests;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.xpath.XPathExpressionException;

import junit.framework.AssertionFailedError;
import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.api.ITestingEngine;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitTestingEngineImpl;
import net.sourceforge.jwebunit.junit.WebTestCase;
import net.sourceforge.jwebunit.junit.WebTester;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.codegen.DefaultRuntimeProperties;
import org.openiaml.model.codegen.ICodeGeneratorInMemory;
import org.openiaml.model.codegen.php.CustomOAWLog;
import org.openiaml.model.codegen.php.IACleanerBeautifier;
import org.openiaml.model.codegen.php.OawCodeGeneratorWithRuntime;
import org.openiaml.model.tests.xpath.DefaultXpathTestCase;
import org.openiaml.model.xpath.IXpath;
import org.openiaml.model.xpath.IterableElementList;
import org.openiaml.modeltesting.EclipseProject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * The JET/Junit test framework now manages everything for you. It will
 * automatically create empty projects to be compiled to.
 * 
 * @see ModelTestCaseWithProperties
 * @author jmwright
 *
 */
public abstract class ModelTestCase extends WebTestCase implements IXpath {

	public ModelTestCase() {
		// call super with a custom tester
		super(getCustomTester());
	}

	/**
	 * @return
	 */
	private static WebTester getCustomTester() {
		return new WebTester() {

			/* (non-Javadoc)
			 * @see net.sourceforge.jwebunit.junit.WebTester#initializeDialog()
			 */
			@Override
			protected ITestingEngine initializeDialog() {
				HtmlUnitTestingEngineImpl engine = new HtmlUnitTestingEngineImpl() {
					/* (non-Javadoc)
					 * @see net.sourceforge.jwebunit.htmlunit.HtmlUnitTestingEngineImpl#createWebClient()
					 */
					@Override
					protected WebClient createWebClient() {
						WebClient wc = super.createWebClient();
						wc.setAjaxController(new NicelyResynchronizingAjaxController());
						return wc;
					}
					
				};
				return engine;
			}
			
		};
	}

	private EclipseProject project = new EclipseProject(getProjectName());
	
	public static final String PLUGIN_ID = "org.openiaml.model.tests";
	public static final String ROOT = "src/org/openiaml/model/tests/";
	
	// TODO issue 149: remove this field
	protected IProgressMonitor monitor;
	
	public static final String BASE_URL = "http://localhost:8080/junit-workspace/";
	// from ./junit-workspace/project_name/output/ to target
	public static final String CONFIG_RUNTIME = "../../../workspace-iaml/org.openiaml.model.runtime/src/include/";
	public static final String BASE_URL_RUNTIME = "http://localhost:8080/iaml-runtime/"; 
	public static final String CONFIG_WEB = "/iaml-runtime/";
	
	/**
	 * The permissible delta in double tests.
	 */
	public static final double TEST_DELTA = 0.0001;
	
	/** 
	 * TODO a temporary variable to make sure we don't set up
	 * the same project twice.
	 */
	protected boolean isSetup = false;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Override
	protected void setUp() throws Exception {
		// parent setup
		super.setUp();
		
		logTimed("model", "setUp", "", "");
		
		monitor = new NullProgressMonitor();
		
		project.createProject();
		isSetup = true;

		// set test context
		getTestContext().setBaseUrl(BASE_URL + project.getProjectName() + "/");
		
		// do we need to set a proxy?
		if (System.getenv("http.proxyHost") != null) {
			getTestContext().setProxyAuthorization(null, null, System.getenv("http.proxyHost"), Integer.parseInt(System.getenv("http.proxyPort")));
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Override
	protected void tearDown() throws Exception {
		project.close();
		
		monitor = null;
		transformStatus = null;
		
		super.tearDown();
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
	 * Reset the codegen cache.
	 */
	public void resetCodegenCache() {
		codegenCache.clear();
	}
	
	/**
	 * Remove the given class from the codegen cache. Returns the
	 * Strings that were in the cache (if it was present) or <code>null</code>.
	 * 
	 * @param class1 The class to remove from the codegen cache.
	 */
	public List<String> removeCodegenCache(Class<?> class1) {
		return codegenCache.remove(class1);
	}
	
	/**
	 * This cache attempts to store the results of previous code generations, allowing us
	 * to perform multiple tests faster.
	 * 
	 * It stores a map of (input class, list of relative paths) of generated files.
	 */
	private static Map<Class<?>, List<String>> codegenCache = new HashMap<Class<?>, List<String>>();

	/**
	 * A map of project classes to generated project paths.
	 */
	private static Map<Class<?>, String> projectCache = new HashMap<Class<?>, String>();

	/**
	 * Do the transformation and make sure it succeeded.
	 * The result of the transformation is stored in {@link #getTransformStatus()}.
	 * 
	 * @param inputFile
	 * @throws Throwable if it did not succeed
	 */
	protected void doTransform(Class<?> cacheClass, EObject model) throws Exception {
		if (codegenCache.containsKey(cacheClass) && projectCache.containsKey(cacheClass)) {
			logTimed("codegen", "loading from cache", "", "");
			
			// load from cache
			loadFromCache(cacheClass);
			logTimed("codegen", "load from cache complete", "", "");
		} else {
			// codegen manually
			logTimed("codegen", "doing actual codegen", "", "");
			doTransformActual(model);
			
			// update cache
			logTimed("codegen", "updating cache", "", "");
			updateCache(cacheClass);
			
			logTimed("codegen", "saving to cache complete", "", "");
		}
	}
	
	/**
	 * The code generation has been cached previously; copy the 
	 * cached code-generated files from this old project into our new project.
	 * 
	 * @param inputFile
	 * @throws Exception 
	 */
	private void loadFromCache(Class<?> inputFile) throws Exception {
		String root = projectCache.get(inputFile);
		for (String file : codegenCache.get(inputFile)) {
			// copy this file to the local project
			File source = new File(root + file);
			IFile target = getProject().getProject().getFile(file);
			
			// check that we can copy OK
			System.out.println("Copying cache file '" + file + "'...");
			assertTrue("The source file '" + source + "' should exist from our codegen cache.", source.exists());
			assertFalse("The target file '" + target + "' should not exist yet.", target.exists());

			copyFileIntoWorkspace(source, target);
		}
	}

	/**
	 * Take the code generation results and load them into a cache
	 * for future reference.
	 * 
	 * @param inputFile
	 */
	protected void updateCache(Class<?> inputFile) {
		// do nothing if generation was unsuccessful
		if (lastTracingCache == null)
			return;
		
		List<String> result = new ArrayList<String>();
		for (File f : lastTracingCache) {
			// get the relative path
			String root = project.getProject().getLocation().toOSString();
			result.add( f.getAbsolutePath().replace(root, "") );
		}
		
		codegenCache.put(inputFile, result);
		projectCache.put(inputFile, project.getProject().getLocation().toOSString());
	}

	/**
	 * Do the transformation and make sure it succeeded.
	 * The result of the transformation is stored in {@link #getTransformStatus()}.
	 * TODO refactor this out into plugins
	 * 
	 * @param inputFile
	 * @throws Throwable if it did not succeed
	 */
	protected void doTransformActual(EObject model) throws Exception {
		transformStatus = doTransform(model, monitor);
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
			throw new TransformationException("Transformation did not pass successfully: " + transformStatus.getMessage() + (firstMessage == null ? "" : ", first message: " + firstMessage), transformStatus.getChildren());
		}
	}
	
	/**
	 * Transform a model file into some code generated output. This currently contains the logic on
	 * which code generation solution to use.
	 */
	protected IStatus doTransform(EObject model, IProgressMonitor monitor) throws IOException, URISyntaxException, CoreException {
		// which transform to use?
		// return doTransformJET(filename, outputDir, monitor);
		return doTransformOAWWorkflow(model, monitor);
	}

	/**
	 * The results of getTracingCache() for the last code generation run.
	 */
	private List<File> lastTracingCache;
	
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
	protected IStatus doTransformOAWWorkflow(EObject model, 
			IProgressMonitor monitor) throws CoreException {
		
		// enable tracing cache
		IACleanerBeautifier.setTracingEnabled(true);
		
		// set last cache to null
		lastTracingCache = null;
		
		try {
			// create some properties
			Map<String,String> runtimeProperties = getRuntimeProperties();
			
			ICodeGeneratorInMemory runner = new OawCodeGeneratorWithRuntime();
			IStatus status = runner.generateCode(model, project.getProject(), monitor, runtimeProperties);
			
			if (!status.isOK()) {
				// bail early
				return status;
			}

			lastTracingCache = IACleanerBeautifier.getTracingCache();

			// once generated, we need to refresh the workspace before
			// we can carry on testing (normally, we would just let Eclipse automatically
			// refresh the resources)
			
			// we need to *force* refresh the workspace
			return project.refreshProject();
		} finally {
			// disable tracing cache
			IACleanerBeautifier.setTracingEnabled(false);
		}
	}

	/**
	 * Generate the properties required for ICodeGenerator.
	 * 
	 * <p>This method will look for a local file <code>proxy.properties</code>;
	 * if this file exists, this Properties file will be loaded and the
	 * {@link DefaultRuntimeProperties#getDefaultProperties() default properties}
	 * will be overridden with these.
	 * 
	 * @see org.openiaml.model.codegen.ICodeGenerator#generateCode(IFile, IProgressMonitor, Map)
	 * @return
	 */
	protected Map<String, String> getRuntimeProperties() {
		// first, get the default properties
		Map<String,String> properties = new DefaultRuntimeProperties().getDefaultProperties();
		
		// then overwrite
		properties.put("include_runtime", "false");
		properties.put("config_runtime", CONFIG_RUNTIME);
		properties.put("config_web", CONFIG_WEB);
		properties.put("debug", "true");
		
		// we might want to get instance-specific information
		File f = new File("proxy.properties");
		if (f.exists()) {
			Properties p = new Properties();
			try {
				p.load(new FileReader(f));
			} catch (IOException e) {
				throw new RuntimeException("Problem loading file '" + f + "': " + e.getMessage(), e);
			}
			for (Object k : p.keySet()) {
				if (k instanceof String) {
					String ks = (String) k;
					properties.put(ks, p.getProperty(ks));
				}
			}
		}

		return properties;
	}

	/**
	 * @return the project
	 */
	public EclipseProject getProject() {
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
		return copyFileIntoWorkspace(new File(sourceName), target);
	}

	/**
	 * Copy a local file into the Eclipse workspace. Makes sure it doesn't
	 * already exist, and that it does exist once this method is completed.
	 * 
	 * This method also creates any necessary sub-folders recursively. 
	 * 
	 * @param source
	 * @param target
	 * @return the target file
	 * @throws CoreException 
	 * @throws FileNotFoundException 
	 */
	protected IFile copyFileIntoWorkspace(File sourceFile, IFile target) throws CoreException, FileNotFoundException {
		return project.copyFileIntoWorkspace(sourceFile, target, monitor);	
	}

	/**
	 * Helper method: assert A >= B.
	 * 
	 * @param expected expected value (B)
	 * @param actual actual value (A)
	 */
	public void assertGreaterEq(int expected, int actual) {
		assertTrue("expected >= than " + expected + ", but actually had " + actual, actual >= expected);
	}
	
	/**
	 * Helper method: assert A > B.
	 * 
	 * @param expected expected value (B)
	 * @param actual actual value (A)
	 */
	public void assertGreater(int expected, int actual) {
		assertTrue("expected > than " + expected + ", but actually had " + actual, actual > expected);
	}
	
	/**
	 * Helper method: assert A != B
	 * 
	 * @param expected expected value (B)
	 * @param actual actual value (A)
	 */
	public void assertNotEqual(String message, int expected, int actual) {
		assertFalse(message, expected == actual);
	}
	
	/**
	 * Helper method: assert A != B
	 * 
	 * @param expected expected value (B)
	 * @param actual actual value (A)
	 */
	public void assertNotEqual(String a, String b) {
		if (a == null)
			assertNotNull("null should not equal null", b);
		else
			assertFalse("'" + a + "' should not equal '" + b + "'", a.equals(b));
	}
	
	/**
	 * Assert that the given string is equal to one of the
	 * expected strings.
	 * 
	 * @param expected
	 * @param actual
	 */
	public void assertEqualsOneOf(String[] expected, String actual) {
		for (String a : expected) {
			if (actual.equals(a))
				return;
		}
		fail("String '" + actual + "' was not equal to any of the strings in '" + Arrays.toString(expected) + "'");
	}
	
	/**
	 * Helper method: assert A != B
	 * 
	 * @param expected expected value
	 * @param actual actual value
	 */
	public void assertNotEqual(int expected, int actual) {
		assertFalse("Expected '" + expected + "', actually '" + actual + "'", expected == actual);
	}
	
	/**
	 * Assert that the given exception contains the given message.
	 * 
	 * @param e
	 * @param string
	 */
	public void checkExceptionContains(Exception e,
			String string) {
		if (!e.getMessage().contains(string)) {
			// throw another exception, so we can still get the trace
			throw new RuntimeException("Exception did not contain '" + string + "'", e);
		}
	}
	
	/**
	 * Override the method to provide more information.
	 * TODO move this implementation into JWebUnit
	 */
	@Override
	public void assertTitleMatch(String s) {
		try {
			super.assertTitleMatch(s);
		} catch (AssertionFailedError e) {
			throw new RuntimeException("Could not match '" + s + "' in title '" + getPageTitle() + "'");
		}
	}

	/**
	 * TODO move this implementation into JWebUnit
	 * 
	 * @return the page title
	 */
	public String getPageTitle() {
		IElement title = getElementByXPath("//title");
		if (title == null)
			return "null";
		return title.getTextContent();
	}

	/**
	 * Assert an instance of a class is in a given list.
	 * 
	 * @param class1 class to check
	 * @param used list of class instances
	 */
	public void assertClassIn(Class<?> class1,
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
	public void assertClassNotIn(Class<?> class1,
			List<?> used) {
		for (Object m : used) {
			if (m.getClass().equals(class1))
				fail("Migrator '" + class1.getSimpleName() + "' should not be found in list of used migrators: " + used);
		}
	}
	
	/**
	 * Assert that the given object is of the given class or higher.
	 *
	 * @param class1 the expected class
	 * @param object the object to check
	 */
	public void assertInstanceOf(Class<?> class1, Object object) {
		if (class1.isInstance(object)) {
			// ok
		} else {
			fail("Expected object instance '" + class1 + "', got '" + object.getClass() + "': " + object);
		}
	}
	
	/**
	 * Assert that the given list of objects are of the given class or higher.
	 *
	 * @param class1 the expected class
	 * @param objects the list of objects to check
	 */
	public void assertInstanceOf(Class<?> class1, Collection<?> objects) {
		for (Object object : objects) {
			if (class1.isInstance(object)) {
				// ok
			} else {
				fail("Expected object instance '" + class1 + "', got '" + object.getClass() + "': " + object);
			}
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
		
		private IStatus[] children; 
		
		public TransformationException(String message, IStatus[] children) {
			this(message);
			this.children = children;
		}
		
		public IStatus[] getChildren() {
			return children;
		}
		
	}
	
	/**
	 * Does the given element ID exist?
	 * 
	 * TODO move into JWebUnit.
	 */
	public boolean hasElementById(String id) {
		try {
			getElementById(id);
			return true;
		} catch (AssertionFailedError e) {
			return false;
		}
	}

	/**
	 * Select all of the elements of the given list that are
	 * instances of the given class.
	 * 
	 * @param children
	 * @param class1
	 * @return
	 */
	public List<?> typeSelect(List<?> children,
			Class<?> class1) {
		List<Object> result = new ArrayList<Object>();
		for (Object o : children) {
			if (class1.isInstance(o))
				result.add(o);
		}
		return result;
	}

	/**
	 * Assert that the given haystack String contains the given needle String.
	 * 
	 * @param needle
	 * @param haystack
	 */
	public void assertContains(String needle, String haystack) {
		assertTrue("String '" + haystack + "' does not contain needle '" + needle + "'", haystack.contains(needle));
	}
	
	/**
	 * XPath helper methods.
	 * @see org.openiaml.model.tests.xpath.IXpath
	 */
	private static IXpath xpath = new DefaultXpathTestCase();

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

	/**
	 * Should we log the results to TIMED_LOG_FILE?
	 */
	public static final boolean ENABLE_TIMED_LOG = false;
	private static final String TIMED_LOG_FILE = "timed.log";
	
	/**
	 * Extends the CustomOAWLog to allow us to also register the time it
	 * takes for various operations. 
	 */
	static {
		if (ENABLE_TIMED_LOG)
			initaliseLogExtender();
	}
	
	private static TimedOAWLogExtender extender;
	public static void initaliseLogExtender() {
		
		extender = new TimedOAWLogExtender(TIMED_LOG_FILE);
		
		// register to CustomOAWLog
		CustomOAWLog.setLogExtender(extender);
		
	}
	
	protected static void logTimed(String key1, String key2, String key3, String key4) {
		if (ENABLE_TIMED_LOG) {
			extender.logDirect(key1, key2, key3, key4);
		}
	}
	
	/**
	 * @see XmlTestCase#readFile(File)
	 */
	public static String readFile(File sourceFile) throws IOException {
		return XmlTestCase.readFile(sourceFile);
	}
	
	/**
	 * @see XmlTestCase#copyFile(File, File)
	 */
	public static void copyFile(File source, File target) throws IOException {
		XmlTestCase.copyFile(source, target);
	}
	
	/**
	 * @see XmlTestCase#writeFile(File, String)
	 */
	public static void writeFile(File sourceFile, String data) throws IOException {
		XmlTestCase.writeFile(sourceFile, data);
	}
	
	/**
	 * Write the given string to the target IFile.
	 * Assumed that the given string is in UTF-8 encoding.
	 * 
	 * @param target
	 * @param data
	 * @throws UnsupportedEncodingException 
	 * @throws CoreException 
	 */
	public static void writeFile(IFile target, String data) throws UnsupportedEncodingException, CoreException {
		InputStream source = new ByteArrayInputStream(data.getBytes("UTF-8")); 
		target.create(source, true, new NullProgressMonitor());
	}
	
}
