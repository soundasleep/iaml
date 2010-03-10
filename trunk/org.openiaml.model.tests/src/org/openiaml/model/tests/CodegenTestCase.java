/**
 * 
 */
package org.openiaml.model.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import junit.framework.AssertionFailedError;
import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.junit.WebTestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.InternetApplication;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

/**
 * Code generation-specific test cases.
 * 
 * @author jmwright
 *
 */
public abstract class CodegenTestCase extends ModelInferenceTestCase {

	protected InternetApplication root;

	/**
	 * Prevent setUp() being called outside of code generation.
	 * 
	 * Subclasses should not call setUp(), because the parent
	 * setUp() creates a project etc. We instead want
	 * the code in {@link #loadAndCodegen(Class)} to create
	 * the project.
	 */
	//protected abstract void setUp() throws Exception;
	
	/**
	 * Load a model and perform code generation.
	 * 
	 * @see #loadAndInfer(String)
	 * @param modelFile
	 * @return The loaded InternetApplication
	 * @throws Exception
	 */
	protected InternetApplication loadAndCodegen(Class<?> modelFile) throws Exception {
		return loadAndCodegen(modelFile, false);
	}
	
	/**
	 * Load a model and perform code generation.
	 * 
	 * @see #loadAndInfer(String)
	 * @param modelFile
	 * @param logRuleSource should the source of rules be logged?
	 * @return The loaded InternetApplication
	 * @throws Exception
	 */
	protected InternetApplication loadAndCodegen(Class<?> modelFile, boolean logRuleSource) throws Exception {
		InternetApplication root = loadAndInfer(modelFile, logRuleSource);
		doCodegen(modelFile, root);
		return root;
	}
	
	/**
	 * Load a model and perform code generation.
	 * 
	 * @see #loadAndInfer(String)
	 * @param modelFile
	 * @param logRuleSource should the source of rules be logged?
	 * @return The loaded InternetApplication
	 * @throws Exception
	 */
	protected InternetApplication loadAndCodegen(Class<?> modelFile, IProgressMonitor monitor) throws Exception {
		InternetApplication root = loadAndInfer(modelFile, false, monitor);
		doCodegen(modelFile, root);
		return root;
	}

	/**
	 * Load a model and perform code generation.
	 * 
	 * @see #loadAndInfer(String)
	 * @param modelFile
	 * @return The loaded InternetApplication
	 * @throws Exception
	 */
	protected InternetApplication loadAndCodegen(Class<?> testClass, String modelFile) throws Exception {
		return loadAndCodegen(testClass, modelFile, false);
	}
	
	/**
	 * Load a model and perform code generation.
	 * 
	 * @see #loadAndInfer(String)
	 * @param modelFile
	 * @param logRuleSource should the source of rules be logged?
	 * @return The loaded InternetApplication
	 * @throws Exception
	 */
	protected InternetApplication loadAndCodegen(Class<?> testClass, String modelFile, boolean logRuleSource) throws Exception {
		InternetApplication root = loadAndInfer(modelFile, logRuleSource);
		doCodegen(testClass, root);
		return root;
	}
	
	/**
	 * Get the inferred model from {@link #getInferredModel()} and
	 * do code generation with {@link #doTransform(String)}.
	 * @throws Exception 
	 */
	protected void doCodegen(Class<?> testClass, EObject model) throws Exception {

		if (!isSetup) {
			// TODO remove this in the future
			// we don't want this method to create the project; it should be created already
			throw new RuntimeException("This test case should have called setUp() already [this creates the project]");
			// super.setUp();		// create project
		}
		doTransform(testClass, model);	// output to project
	}
	
	/**
	 * When we close the test case, we should also close the project.
	 */
	@Override
	protected void tearDown() throws Exception {
		logTimed("web: navigation complete");
		
		// remove reference to InternetApplication
		if (root != null) {
			root = null;
		}
		
		super.tearDown();
	}

	/** 
	 * Have we loaded at least one page?
	 * We can't goto the sitemap if we haven't begun the session yet.
	 */
	private boolean hasBegun;

	/**
	 * Wait for all of the Ajax monitors to return.
	 * 
	 * @throws Exception
	 */
	protected void waitForAjax() throws Exception {
		// does nothing
	}
	
	/**
	 * <p>
	 * Begin at the sitemap page, and then click on a particular page title.
	 * This method also checks that the destination page is the same as the
	 * target page; if not, use {@link #beginAtSitemapThenPage(IFile, String, String)}.
	 * </p>
	 * 
	 * <p>
	 * <b>NOTE</b> that this resets the current WebClient context, which can cause
	 * the client to lose sessions/cookies. If this is undesirable,
	 * use {@link #gotoSitemapThenPage(IFile, String)}.
	 * </p>
	 * 
	 * <p>TODO this should return a new type, ISitemap or likewise</p>
	 * 
	 * @see #beginAtSitemapThenPage(IFile, String, String)
	 * @param pageTitle the page title to select from the sitemap
	 * @return the location of the sitemap file
	 * @throws Exception 
	 */
	public IFile beginAtSitemapThenPage(String pageTitle) throws Exception {
		// go to sitemap
		IFile sitemap = getSitemap();
		assertTrue("Sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, pageTitle, pageTitle);
		
		// check to make sure we didn't run out of execution time
		if (getPageSource().matches("Maximum execution time of [0-9]+ seconds exceeded in")) {
			throw new PhpExecutionTimeException("Maximum execution time exceeded in PHP script: '" + pageTitle + "'");
		}
		
		return sitemap;
	}
	
	/**
	 * Identical to {@link #beginAtSitemapThenPage(String)}, except adds
	 * the given query to the URL through {@link #visitLinkWithQueryParameter(String, String)}.
	 * 
	 * @see #visitLinkWithQueryParameter(String, String)
	 * @param pageTitle
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public IFile beginAtSitemapThenPageQuery(String pageTitle, String query) throws Exception {
		// go to sitemap
		IFile sitemap = getSitemap();
		assertTrue("Sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, pageTitle, pageTitle, query);
		
		// check to make sure we didn't run out of execution time
		if (getPageSource().matches("Maximum execution time of [0-9]+ seconds exceeded in")) {
			throw new PhpExecutionTimeException("Maximum execution time exceeded in PHP script: '" + pageTitle + "'");
		}
		
		return sitemap;
	}
	
	/**
	 * Emulate clicking the given link, but add the given query string
	 * to the end of the request. This won't actually call any
	 * Javascript etc that is present on the given link.
	 * 
	 * @param linkName
	 * @param query Query to append, e.g. <code>key=value&amp;key2=value2</code>
	 */
	public void visitLinkWithQueryParameter(String linkName, String query) {
		IElement link = getElementByXPath("//a[contains(text(), '" + linkName + "')]");
		assertNotNull(link);
		
		// find the href
		String href = link.getAttribute("href");
		assertNotNull(href);
		assertNotEquals(href, "");
		
		// TODO get the current page URL from JWebUnit in order to work out
		// the current relative path to the sitemap
		href = "output/" + href;	// hack until we can get the actual relative URL
		
		// construct the query
		if (href.indexOf('?') != -1) {
			href += "&" + query;
		} else {
			href += "?" + query;
		}
		
		// go to the link
		gotoPage(href);
	}
	
	/**
	 * <p>
	 * Like {@link #beginAtSitemapThenPage(String)}, except we expect
	 * to not end up at <code>pageTitle</code>.
	 * </p>
	 * 
	 * <p>
	 * <b>NOTE</b> that this resets the current WebClient context, which can cause
	 * the client to lose sessions/cookies. If this is undesirable,
	 * use {@link #gotoSitemapThenPage(IFile, String)}.
	 * </p>
	 * 
	 * @see #beginAtSitemapThenPage(String)
	 * @param pageTitle the page title to select from the sitemap
	 * @param expectedTitle the expected destination page title, usually the same as pageTitle
	 * @return the location of the sitemap file
	 * @throws Exception 
	 */
	public IFile beginAtSitemapThenPage(String pageTitle, String expectedTitle) throws Exception {
		// go to sitemap
		IFile sitemap = getSitemap();
		assertTrue("Sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, pageTitle, expectedTitle);
		
		return sitemap;
	}
	
	/** 
	 * Get the sitemap file, i.e. <code>output/sitemap.html</code>
	 */
	public IFile getSitemap() {
		return getProject().getFile("output/sitemap.html");
	}
	
	/**
	 * Get the root folder of the generated output code. This is the
	 * base href for the entire navigation.
	 * 
	 * @see #gotoSitemapThenPage(IFile, String, String, String)
	 * @return
	 */
	public IFile getOutputRoot() {
		return getProject().getFile("output/");
	}
	
	/**
	 * Begin at the sitemap page, and then click on a particular page title.
	 * This method checks to see the destination page title is the same as
	 * expectedPageTitle.
	 * 
	 * <b>NOTE</b> that this resets the current WebClient context, which can cause
	 * the client to lose sessions/cookies. If this is undesirable,
	 * use {@link #gotoSitemapThenPage(IFile, String)}.
	 * 
	 * @see #beginAtSitemapThenPage(IFile, String, String)
	 * @param sitemap the location of the sitemap file
	 * @param pageTitle the page title to select from the sitemap
	 * @param expectedPageTitle the expected destination page title, usually the same as pageTitle
	 */ 
	public void beginAtSitemapThenPage(IFile sitemap, String pageTitle, String expectedPageTitle) throws Exception {
		beginAtSitemapThenPage(sitemap, pageTitle, expectedPageTitle, null);
	}

	/**
	 * Begin at the sitemap page, and then click on a particular page title.
	 * This method checks to see the destination page title is the same as
	 * expectedPageTitle.
	 * 
	 * <b>NOTE</b> that this resets the current WebClient context, which can cause
	 * the client to lose sessions/cookies. If this is undesirable,
	 * use {@link #gotoSitemapThenPage(IFile, String)}.
	 * 
	 * @see #beginAtSitemapThenPage(IFile, String, String)
	 * @param sitemap the location of the sitemap file
	 * @param pageTitle the page title to select from the sitemap
	 * @param expectedPageTitle the expected destination page title, usually the same as pageTitle
	 * @param query query string to append to the end of the destination, or null if none
	 */ 
	public void beginAtSitemapThenPage(IFile sitemap, String pageTitle, String expectedPageTitle, String query) throws Exception {
		logTimed("web: beginAtSitemapThenPage");
		waitForAjax();

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
		
		assertLinkPresentWithText(pageTitle);
		if (query == null) {	
			clickLinkWithText(pageTitle);
		} else {
			visitLinkWithQueryParameter(pageTitle, query);
		}
		
		try {
			assertTitleMatch(expectedPageTitle);
		} catch (AssertionFailedError e) {
			// something went wrong in the page execution, or
			// the output is mangled HTML: output page source for debug purposes
			System.out.println(this.getPageSource());
			throw e;	// carry on throwing
		}
		
		// check to make sure we didn't run out of execution time
		if (getPageSource().matches("Maximum execution time of [0-9]+ seconds exceeded in")) {
			throw new PhpExecutionTimeException("Maximum execution time exceeded in PHP script: '" + pageTitle + "'");
		}
		
		logTimed("web: beginAtSitemapThenPage complete");
	}

	/**
	 * We extend {@link WebTestCase#beginAt(String)} to also set
	 * {@link #hasLoaded} to true (to help Ajax navigation).
	 * 
	 * <b>NOTE</b> that this resets the current WebClient context, which can cause
	 * the client to lose sessions/cookies. If this is undesirable,
	 * use {@link #gotoSitemapThenPage(IFile, String)}.
	 */
	@Override
	public void beginAt(String url) {
		try {
			super.beginAt(url);
		} catch (RuntimeException e) {
			throw new RuntimeException("Cannot connect to '" + url + "' relative to '" + BASE_URL + "'", e);	// for debugging
		}
		hasBegun = true;		// we have begun somewhere
	}
	
	/**
	 * Go to the sitemap page, and then click on a particular page title.
	 * 
	 * <p>If you want the client to be reset (e.g. delete cookies, sessions),
	 * use {@link #beginAtSitemapThenPage(IFile, String)}.
	 * 
	 * @param sitemap the sitemap url to start from
	 * @param pageText the page text link to click
	 * @param expected the expected page title on the new page, if different from the page text link
	 */ 
	protected void gotoSitemapThenPage(IFile sitemap, String pageText, String expectedTitle) throws Exception {
		logTimed("web: gotoSitemapThenPage");
		
		// we can't goto the sitemap if we haven't begun the session yet
		// (sanity check)
		if (!hasBegun)
			throw new RuntimeException("You cannot gotoSitemap() for a session that hasn't started yet. Use beginAt or beginAtSitemapThenPage instead.");
	
		waitForAjax();

		gotoPage(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
		
		assertLinkPresentWithText(pageText);
		clickLinkWithText(pageText);
		try {
			assertTitleEquals(expectedTitle);
			// assertEquals(expectedTitle, getPageTitle());		// could be different
		} catch (Exception e) {
			// something went wrong in the page execution, or
			// the output is mangled HTML: output page source for debug purposes
			System.out.println(this.getPageSource());
			throw e;	// carry on throwing
		} catch (AssertionFailedError e) {
			System.out.println(this.getPageSource());
			throw e;	// carry on throwing
		}
		
		// check to make sure we didn't run out of execution time
		if (getPageSource().matches("Maximum execution time of [0-9]+ seconds exceeded in")) {
			throw new PhpExecutionTimeException("Maximum execution time exceeded in PHP script: '" + pageText + "'");
		}

		logTimed("web: gotoSitemapThenPage complete");
	}

	/**
	 * Go to the sitemap page, and then click on a particular page title.
	 * 
	 * <p>If you want the client to be reset (e.g. delete cookies, sessions),
	 * use {@link #beginAtSitemapThenPage(IFile, String)}.
	 * 
	 * @param sitemap the sitemap url to start from
	 * @param pageText the page text link to click
	 * @param expected the expected page title on the new page, if different from the page text link
	 * @param query additional query parameters to append to the URL, e.g. "id=123". may be null. 
	 */ 
	protected void gotoSitemapThenPage(IFile sitemap, String pageText, String expectedTitle, String query) throws Exception {
		logTimed("web: gotoSitemapThenPage query");
	
		// prepare the query
		if (query == null || query.isEmpty()) {
			query = "";
		} else if (query.startsWith("?")) {
			// do nothing; already starts with '?'
		} else {
			query = "?" + query;
		}
		
		// we can't goto the sitemap if we haven't begun the session yet
		// (sanity check)
		if (!hasBegun)
			throw new RuntimeException("You cannot gotoSitemap() for a session that hasn't started yet. Use beginAt or beginAtSitemapThenPage instead.");

		waitForAjax();
	
		gotoPage(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
		
		// make sure the link exists, first
		assertLinkPresentWithText(pageText);
		
		// now find the link, and get where it goes to
		IElement element = getElementByXPath("//a[normalize-space(text()) = normalize-space('" + pageText + "')]");		
		String href = element.getAttribute("href");
		assertNotNull(href);
		
		// construct the url
		String url = getOutputRoot().getProjectRelativePath().toString()
		 	+ "/"
			+ href
			+ query;
		
		// now go there
		gotoPage(url);

		// check to make sure we didn't run out of execution time
		if (getPageSource().matches("Maximum execution time of [0-9]+ seconds exceeded in")) {
			throw new PhpExecutionTimeException("Maximum execution time exceeded in PHP script: '" + pageText + "'");
		}

		logTimed("web: gotoSitemapThenPage query complete");
	}

	/**
	 * Go to the given page, but assert that we do not arrive
	 * on the given page. Does not actually check {@link #assertProblem()}.
	 * 
	 * @param sitemap
	 * @param string
	 */
	public void gotoSitemapWithProblem(IFile sitemap, String pageText) throws Exception {
		logTimed("web: gotoSitemapWithProblem");
		
		// we can't goto the sitemap if we haven't begun the session yet
		// (sanity check)
		if (!hasBegun)
			throw new RuntimeException("You cannot gotoSitemap() for a session that hasn't started yet. Use beginAt or beginAtSitemapThenPage instead.");
	
		waitForAjax();

		gotoPage(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
		
		assertLinkPresentWithText(pageText);
		clickLinkWithText(pageText);
		try {
			assertTitleNotSame(pageText);
			// assertEquals(expectedTitle, getPageTitle());		// could be different
		} catch (Exception e) {
			// something went wrong in the page execution, or
			// the output is mangled HTML: output page source for debug purposes
			System.out.println(this.getPageSource());
			throw e;	// carry on throwing
		} catch (AssertionFailedError e) {
			System.out.println(this.getPageSource());
			throw e;	// carry on throwing
		}
		
		// check to make sure we didn't run out of execution time
		if (getPageSource().matches("Maximum execution time of [0-9]+ seconds exceeded in")) {
			throw new PhpExecutionTimeException("Maximum execution time exceeded in PHP script: '" + pageText + "'");
		}

		logTimed("web: gotoSitemapWithProblem complete");
	}
	
	/**
	 * <p>Go to the sitemap page, and then click on a particular page title.
	 * Assert that the target page has the same given title.</p>
	 * 
	 * <p>If you want the client to be reset (e.g. delete cookies, sessions),
	 * use {@link #beginAtSitemapThenPage(IFile, String)}.</p>
	 * 
	 * @param sitemap the sitemap url to start from
	 * @param pageText the page text link to click
	 */ 
	protected void gotoSitemapThenPage(IFile sitemap, String pageText) throws Exception {
		gotoSitemapThenPage(sitemap, pageText, pageText);
	}
	
	/**
	 * <p>
	 * We need some way of working out the label ID that contains 
	 * a particular string.
	 * </p>
	 * 
	 * <p>
	 * Previously, this would occasionally fail even though the
	 * label was directly in the page. It turns out this is because
	 * these two labels have different text() values:
	 * 
	 * <ol>
	 * 	<li><code>&lt;a&gt;b c&lt;/a&gt;</code></li>
	 *  <li><code>&lt;a&gt;b<br>c&lt;/a&gt;</code></li>
	 * </ol>
	 * </p>
	 * 
	 * <p>
	 * As a result, we wrap both <code>text()</code> and the testing
	 * string with the <code>normalize-string()</code> XPath function.
	 * </p>
	 * 
	 * <p>TODO return a custom object, not just a String, which can be easily confused.</p>
	 * 
	 * @see #getContainsTextXPath(String)
	 * @param text
	 * @return
	 */
	protected String getLabelIDForText(String text) {
		logTimed("internal: get label ID for text");
		IElement element = getElementByXPath("//label[" + getContainsTextXPath(text) + "]");
		String id = element.getAttribute("id");
		assertNotNull("Label ID for text '" + text + "' was null", id);
		logTimed("internal: get label ID for text complete");
		return id;
	}

	/**
	 * Extension of {@link #getLabelIDForText(String)}; add an extra
	 * XPath condition for text that it should not contain, 
	 * allowing us to select elements that might otherwise
	 * be erroneously matched.
	 * 
	 * @param text
	 * @param notText
	 * @return
	 */
	protected String getLabelIDForText(String text, String notText) {
		logTimed("internal: get label ID for text (2)");
		IElement element = getElementByXPath("//label[" + getContainsTextXPath(text) + " and not(" + getContainsTextXPath(notText) + ")]");
		logTimed("internal: get label ID for text (2) complete");
		return element.getAttribute("id");
	}
	
	/**
	 * Construct the necessary XPath expression to find a node that 
	 * contains the specific text.
	 * 
	 * <p>Currently returns <code>contains(normalize-space(text()), normalize-space('<u>text</u>'))</code>.
	 * 
	 * @param text
	 * @return
	 */
	protected String getContainsTextXPath(String text) {
		return "contains(normalize-space(text()), normalize-space('" + text + "'))";
	}
	
	/**
	 * Assert that there is no label on the page containing the given text.
	 * 
	 * @param text
	 */
	protected void assertHasNotLabelIDForText(String text) {
		try {
			// expect this to fail
			getLabelIDForText(text);
		} catch (AssertionFailedError e) {
			// it wasn't found; ok
			return;
		}
		fail("Unexpectedly found label '" + text + "': " + getLabelIDForText(text));
	}
	
	/**
	 * Helper method: Get the current page title.
	 */
	protected String getPageTitle() {
		return getElementByXPath("//title").getTextContent(); 
	}
	
	/**
	 * Assert that the value in a given element is null/empty/zero.
	 * 
	 * @param e the element
	 */
	protected void assertEmpty(IElement e) {
		assertTrue( "Element " + e + " expected to be empty: was " + e.getTextContent(),
				e.getTextContent() == null || 
				e.getTextContent().isEmpty() ||
				(Integer.parseInt(e.getTextContent())) == 0 );
	}
	
	/**
	 * Assert that no remote calls have occured when loading the currente
	 * page.
	 * @throws Exception 
	 */
	protected void assertNoRemoteCalls() throws Exception {
		waitForAjax();
		assertEmpty(getElementById("counter_store_db"));
		assertEmpty(getElementById("counter_store_event"));
		assertEmpty(getElementById("counter_set_session"));
		assertEmpty(getElementById("counter_set_application_value"));		
	}
	

	/**
	 * Assert that a given labelled field does NOT equal the current value.
	 * 
	 * @param id
	 * @param value
	 */
	protected void assertLabeledFieldNotEquals(String id, String value) {
		boolean failed = true;
		try {
			assertLabeledFieldEquals(id, value);
			failed = false;
		} catch (AssertionFailedError e) {
			// expected
		}
		assertTrue("value of field for label [" + id + "] shouldn't be [" + value + "]", failed);
	}
	
	/**
	 * Assert that a label with the given text is NOT present.
	 * 
	 * @see #getLabelIDForText(String)
	 * @param text label text to search for
	 */
	protected void assertLabelNotPresent(String text) {
		IElement element;
		try {
			element = getElementByXPath("//label[contains(text(),'" + text + "')]");
		} catch (AssertionFailedError e) {
			// expected
			element = null;
		}
		assertNull("Label with text '" + text + "' should not be present: " + element, element);
	}

	/**
	 * Assert that the given elements are NOT equal, i.e. !a.equals(b).
	 * 
	 * @param a
	 * @param b
	 */
	protected void assertNotEquals(String a, String b) {
		if (a == null) {
			assertFalse("'" + a + "' should not equal '" + b + "'", b == null);
		} else {
			assertFalse("'" + a + "' should not equal '" + b + "'", a.equals(b));
		}
	}
	
	/**
	 * Load a database in the given database file, and execute
	 * a SQL query against it.
	 * 
	 * @param dbName database file to load, not resolved against the project
	 * @param query SQL query to execute
	 * @return the SQL result set
	 * @throws Exception
	 */
	protected ResultSet loadDatabaseQuery(String dbName, String query) throws Exception {
		// wait for ajax calls if necessary
		waitForAjax();
		
		// refresh the workspace
		assertTrue(getProject().refreshProject().isOK());
		
		IFile db = getProject().getFile(dbName);
		assertTrue("db '" + dbName + "' should exist at: " + db, db.exists());

		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:" + db.getLocation());
		Statement stat = conn.createStatement();

		ResultSet rs = stat.executeQuery(query);

		return rs;
	}
	
	/**
	 * Load a properties file in the testing workspace.
	 * 
	 * @param filename properties file to load
	 * @return the loaded properties
	 * @throws Exception
	 */
	protected Properties loadProperties(String filename) throws Exception {
		// wait for ajax calls if necessary
		waitForAjax();
		
		// refresh the workspace
		assertTrue(getProject().refreshProject().isOK());

		IFile target = getProject().getFile(filename);
		assertTrue("File '" + target + "' exists", target.exists());
		
		Properties p = new Properties();
		target.refreshLocal(IResource.DEPTH_INFINITE, monitor);	// refresh
		p.load(target.getContents());

		return p;
	}
	
	/**
	 * Reload a page (but do not lose the session information).
	 * 
	 * @see #restartSession(IFile, String)
	 * @param sitemap the sitemap to reload at
	 * @param pageText the destination page to load
	 * @throws Exception
	 */
	protected void reloadPage(IFile sitemap, String pageText) throws Exception {
		waitForAjax();	// TODO remove this check; we shouldn't have to wait for ajax
		gotoSitemapThenPage(sitemap, pageText);
	}
	
	/**
	 * Restart an entire session. Existing session information will
	 * be lost.
	 * 
	 * @see #reloadPage(IFile, String)
	 * @param sitemap the sitemap to reload at
	 * @param pageText the destination page to load
	 * @throws Exception
	 */
	protected void restartSession(IFile sitemap, String pageText) throws Exception {
		waitForAjax();	// TODO remove this check; we shouldn't have to wait for ajax
		beginAtSitemapThenPage(sitemap, pageText, pageText);
	}
	
	/**
	 * Helper method: Assert that there has been a problem.
	 */
	protected void assertProblem() {
		assertMatch("(Error|error|Exception|exception)");
	}
	
	/**
	 * Helper method: Assert that there has not been a problem.
	 */
	protected void assertNoProblem() {
		assertNoMatch("(Error|error|Exception|exception)");
	}

	/**
	 * We wrap the method to try and catch runtime exceptions
	 * from the PHP server-side code.
	 */
	@Override
	public void clickLinkWithText(String linkText) {
		logTimed("web: clicking link with text");
		try {
			super.clickLinkWithText(linkText);
		} catch (FailingHttpStatusCodeException f) {
			// if we failed at exception.php, we can try and read out the error
			if (PhpRuntimeExceptionException.canHandle(f)) {
				throw new PhpRuntimeExceptionException(f);
			}
			throw f;
		} catch (RuntimeException e) {
			// perhaps this exception was caused by PHP running
			// out of execution time?
			if (getPageSource().matches("Maximum execution time of [0-9]+ seconds exceeded in")) {
				throw new PhpExecutionTimeException("Maximum execution time exceeded in PHP script: '" + linkText + "'", e);
			}
			throw e; 
		}
		logTimed("web: clicking link with text complete");
	}

	/**
	 * We wrap the method to try and catch runtime exceptions
	 * from the PHP server-side code.
	 */
	@Override
	public void gotoPage(String url) {
		try {
			super.gotoPage(url);
		} catch (FailingHttpStatusCodeException f) {
			// if we failed at exception.php, we can try and read out the error
			if (PhpRuntimeExceptionException.canHandle(f)) {
				throw new PhpRuntimeExceptionException(f);
			}
			throw f;
		} catch (RuntimeException e) {
			// perhaps this exception was caused by PHP running
			// out of execution time?
			if (getPageSource().matches("Maximum execution time of [0-9]+ seconds exceeded in")) {
				throw new PhpExecutionTimeException("Maximum execution time exceeded in PHP script: '" + url + "'", e);
			}
			throw e; 
		}
	}
	
	/**
	 * Throw a RuntimeException with the various information from the
	 * debug box (<code>&lt;div id="debug"&gt;...&lt;/div&gt;</code>)
	 * for debugging purposes.
	 * 
	 * Also prints out the current source code.
	 */
	public void throwDebugInformation(Throwable e) {
		logTimed("internal: throwing debug information");
		// print out the source code
		System.out.println(getTester().getPageSource());
		System.out.println(getElementById("debug").getTextContent());
		// throw out any response text too
		logTimed("internal: throwing debug information complete");
		throw new RuntimeException("Response = '" + getElementById("response").getTextContent() + "' Debug='" + getElementById("debug").getTextContent() + "'", e);
		
	}
	
	@Override
	public void setLabeledFormElementField(String id, String value) {
		logTimed("web: set labeled form");
		super.setLabeledFormElementField(id, value);
		logTimed("web: set labeled form complete");
	}
	
	/**
	 * Assert that a label exists with the given text.
	 * 
	 * @param text
	 */
	public void assertLabelTextPresent(String text) {
		assertFalse("Cannot assert the presence of an empty label", text.isEmpty());
		
		IElement match = getElementByXPath("//label[" + getContainsTextXPath(text) + "]");
		assertNotNull(match);
		String textContent = match.getTextContent();
		// normalise
		textContent = textContent.replaceAll("[\\s]+", " ").trim();
		assertEquals(text, textContent);
	}
	
	/**
	 * Assert that a label <em>does not</em> exist with the given text.
	 * 
	 * @param text
	 */
	public void assertLabelTextNotPresent(String text) {
		boolean failed = false;
		try {
			getElementByXPath("//label[" + getContainsTextXPath(text) + "]");
			failed = true;
		} catch (AssertionFailedError e) {
			// expected
		}
		assertFalse("Unexpectedly found a label with text '" + text + "'", failed);
	}
	
	/**
	 * Assert that a label exists with the given text, but does
	 * not contain another text.
	 * 
	 * @param text the text to contain
	 * @param notText the text to <em>not</em> contain
	 */
	public void assertLabelTextPresent(String text, String notText) {
		assertFalse(text.equals(notText));	// sanity check
		assertFalse("Cannot assert the presence of an empty label", text.isEmpty());
		
		IElement match = getElementByXPath("//label[" + getContainsTextXPath(text) + " and not(" + getContainsTextXPath(notText) + ")]");
		assertNotNull(match);
		String textContent = match.getTextContent();
		// normalise
		textContent = textContent.replaceAll("[\\s]+", " ").trim();
		assertEquals(text, textContent);
	}
	
}
