/**
 * 
 */
package org.openiaml.model.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

import junit.framework.AssertionFailedError;
import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.exception.TestingEngineResponseException;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitTestingEngineImpl;
import net.sourceforge.jwebunit.junit.WebTestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.InternetApplication;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

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
	 * Load a model and perform code generation. The file is loaded
	 * through {@link ModelSourceResolver#getModelFileForClass(Class) the resolver}.
	 * 
	 * @see ModelSourceResolver#getModelFileForClass(Class)
	 * @see #loadAndInfer(String)
	 * @see #DEFAULT_LOG_RULE_SOURCES
	 * @param modelFile
	 * @return The loaded InternetApplication
	 * @throws Exception
	 */
	protected InternetApplication loadAndCodegen(Class<?> modelFile) throws Exception {
		return loadAndCodegen(modelFile, DEFAULT_LOG_RULE_SOURCES);
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
	 * @see #DEFAULT_LOG_RULE_SOURCES
	 * @param modelFile
	 * @param logRuleSource should the source of rules be logged?
	 * @return The loaded InternetApplication
	 * @throws Exception
	 */
	protected InternetApplication loadAndCodegen(Class<?> modelFile, IProgressMonitor monitor) throws Exception {
		InternetApplication root = loadAndInfer(modelFile, DEFAULT_LOG_RULE_SOURCES, monitor);
		doCodegen(modelFile, root);
		return root;
	}

	/**
	 * Load a model and perform code generation.
	 * 
	 * @see #loadAndInfer(String)
	 * @see #DEFAULT_LOG_RULE_SOURCES
	 * @param modelFile
	 * @return The loaded InternetApplication
	 * @throws Exception
	 */
	protected InternetApplication loadAndCodegen(Class<?> testClass, String modelFile) throws Exception {
		return loadAndCodegen(testClass, modelFile, DEFAULT_LOG_RULE_SOURCES);
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
		logTimed("web", "navigation complete", "");
		
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
		String href = getURLOfLink(linkName);
		
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
	 * Get the given link name on the page, and return the resulting URL (as a string)
	 * if we clicked on the link.
	 * 
	 * @param linkName
	 * @return 
	 */
	public String getURLOfLink(String linkName) {
		IElement link = getElementByXPath("//a[contains(text(), '" + linkName + "')]");
		assertNotNull(link);
		
		// find the href
		String href = link.getAttribute("href");
		assertNotNull(href);
		assertNotEquals(href, "");
		
		// TODO get the current page URL from JWebUnit in order to work out
		// the current relative path to the sitemap
		href = "output/" + href;	// hack until we can get the actual relative URL
		
		return href;
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
	 * Just begin at the sitemap, and return the loaded sitemap file.
	 * 
	 * @return the sitemap file
	 */
	public IFile beginAtSitemap() {
		IFile sitemap = getSitemap();
		beginAtSitemap(sitemap);
		return sitemap;
	}
	
	/**
	 * Just begin at the given sitemap.
	 * 
	 * @return the sitemap file
	 */
	public void beginAtSitemap(IFile sitemap) {
		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
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
	 * @param pageText the <em>exact</em> page text link to click
	 * @param expectedPageTitle the expected destination page title, usually the same as pageTitle
	 * @param query query string to append to the end of the destination, or null if none; e.g. <code>one=1&two=2</param>
	 */ 
	public void beginAtSitemapThenPage(IFile sitemap, String pageTitle, String expectedPageTitle, String query) throws Exception {
		logTimed("web", "beginAtSitemapThenPage", "");

		beginAtSitemap(sitemap);
		
		assertLinkPresentWithExactText(pageTitle);
		if (query == null) {	
			clickLinkWithExactText(pageTitle);
		} else {
			visitLinkWithQueryParameter(pageTitle, query);
		}
		
		try {
			assertTitleMatch(expectedPageTitle);
		} catch (AssertionFailedError e) {
			// something went wrong in the page execution, or
			// the output is mangled HTML: output page source for debug purposes
			throw new RuntimeException("Did not match title '" + expectedPageTitle + "' in title '" + getTestingEngine().getPageTitle() + "'", e);
		}
		
		// check to make sure we didn't run out of execution time
		if (getPageSource().matches("Maximum execution time of [0-9]+ seconds exceeded in")) {
			throw new PhpExecutionTimeException("Maximum execution time exceeded in PHP script: '" + pageTitle + "'");
		}
		
		logTimed("web", "beginAtSitemapThenPage complete", "");
	}

	/**
	 * We extend {@link WebTestCase#beginAt(String)} to also set
	 * {@link #hasLoaded} to true (to help Ajax navigation).
	 * 
	 * <b>NOTE</b> that this resets the current WebClient context, which can cause
	 * the client to lose sessions/cookies. If this is undesirable,
	 * use {@link #gotoSitemapThenPage(IFile, String)}.
	 * 
	 * <p>{@inheritDoc}
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
	 * @param pageText the <em>exact</em> page text link to click
	 * @param expected the expected page title on the new page, if different from the page text link
	 */ 
	protected void gotoSitemapThenPage(IFile sitemap, String pageText, String expectedTitle) throws Exception {
		logTimed("web", "gotoSitemapThenPage", "");
		
		// we can't goto the sitemap if we haven't begun the session yet
		// (sanity check)
		if (!hasBegun)
			throw new RuntimeException("You cannot gotoSitemap() for a session that hasn't started yet. Use beginAt or beginAtSitemapThenPage instead.");

		gotoPage(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
		
		assertLinkPresentWithExactText(pageText);
		clickLinkWithExactText(pageText);
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

		logTimed("web", "gotoSitemapThenPage complete", "");
	}

	/**
	 * Write the given log message to the debug log, '<code>php.log</code>'.
	 * 
	 * @param string log message
	 * @throws CoreException 
	 * @throws IOException 
	 */
	public void writeDebug(String string) throws CoreException, IOException {
		IFile f = getProject().getFile("output/php.log");

		// append
		File fp = f.getLocation().toFile();
		assertTrue("Log file '" + fp + "' does not exist from '" + f + "'", fp.exists());
		FileWriter fw = new FileWriter(fp, true);
		fw.write("[test framework] " + string + "\n");
		fw.close();
	}

	/**
	 * Go to the sitemap page, and then click on a particular page title.
	 * 
	 * <p>If you want the client to be reset (e.g. delete cookies, sessions),
	 * use {@link #beginAtSitemapThenPage(IFile, String)}.
	 * 
	 * @param sitemap the sitemap url to start from
	 * @param pageText the <em>exact</em> page text link to click
	 * @param expected the expected page title on the new page, if different from the page text link
	 * @param query additional query parameters to append to the URL, e.g. "id=123". may be null. 
	 */ 
	protected void gotoSitemapThenPage(IFile sitemap, String pageText, String expectedTitle, String query) throws Exception {
		logTimed("web", "gotoSitemapThenPage query", "");
	
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
	
		gotoPage(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
		
		// make sure the link exists, first
		assertLinkPresentWithExactText(pageText);
		
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

		logTimed("web", "gotoSitemapThenPage query complete", "");
	}

	/**
	 * Go to the given page, but assert that we do not arrive
	 * on the given page. Does not actually check {@link #assertProblem()}.
	 * 
	 * @param sitemap
	 * @param pageText the <em>exact</em> page text link to click
	 */
	public void gotoSitemapWithProblem(IFile sitemap, String pageText) throws Exception {
		logTimed("web", "gotoSitemapWithProblem", "");
		
		// we can't goto the sitemap if we haven't begun the session yet
		// (sanity check)
		if (!hasBegun)
			throw new RuntimeException("You cannot gotoSitemap() for a session that hasn't started yet. Use beginAt or beginAtSitemapThenPage instead.");

		gotoPage(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
		
		assertLinkPresentWithExactText(pageText);
		clickLinkWithExactText(pageText);
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

		logTimed("web", "gotoSitemapWithProblem complete", "");
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
	 * Get the first IElement that a given label (with the given label text)
	 * is for. i.e.:
	 * 
	 * <pre>
	 * &lt;label for="target"&gt;label text&lt;/label&gt;
	 * &lt;input id="target" /&gt;
	 * </pre>
	 * 
	 * @param labelText
	 * @return
	 */
	protected IElement getFieldForLabel(String labelText) {
		List<IElement> list = hasLabelForText(labelText);
		assertEquals(1, list.size());
		IElement label = list.get(0);
		
		List<IElement> forFields = getFieldsForLabel(label);
		assertEquals(1, forFields.size());
		return forFields.get(0);		
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
	 * @see #hasLabelForText(String)
	 * @param text
	 * @return
	 */
	protected String getLabelIDForText(String text) {
		List<IElement> elements = hasLabelForText(text);
		assertEquals("Too many labels found for text '" + text + "'", 1, elements.size());
		IElement element = elements.get(0);
		String id = element.getAttribute("id");
		assertNotNull("Label ID for text '" + text + "' was null", id);
		return id;
	}
	
	/**
	 * Assert that a label with the given text exists. 
	 * Returns a list of labels that match the given text.
	 * 
	 * @see #getLabelIDForText(String)
	 * @see #hasNoLabelForText(String)
	 * @param text
	 * @return
	 */
	protected List<IElement> hasLabelForText(String text) {
		logTimed("web", "internal", "get label ID for text");
		List<IElement> elements = getElementsByXPath("//label[" + getContainsTextXPath(text) + "]");
		assertFalse("No labels found for text '" + text + "'", elements.isEmpty());
		logTimed("web", "internal", "get label ID for text complete");
		return elements;
	}

	/**
	 * Assert that a label with the given text <em>does not</em> exist. 
	 * 
	 * @see #hasLabelForText(String)
	 * @param text
	 */
	protected void hasNoLabelForText(String text) {
		List<IElement> elements = getElementsByXPath("//label[" + getContainsTextXPath(text) + "]");
		assertTrue("Labels found for text '" + text + "'", elements.isEmpty());
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
		logTimed("web", "internal", "get label ID for text (2)");
		IElement element = getElementByXPath("//label[" + getContainsTextXPath(text) + " and not(" + getContainsTextXPath(notText) + ")]");
		logTimed("web", "internal", "get label ID for text (2) complete");
		return element.getAttribute("id");
	}
	
	/**
	 * Extension of {@link #getLabelIDForText(String)}; add an extra
	 * XPath condition for text that it should not contain, and another
	 * extra XPath condition directly, 
	 * allowing us to select elements that might otherwise
	 * be erroneously matched.
	 * 
	 * @param text
	 * @param notText
	 * @return
	 */
	protected String getLabelIDForText(String text, String notText, String alsoCondition) {
		logTimed("web", "internal", "get label ID for text (3)");
		IElement element = getElementByXPath("//label[" + getContainsTextXPath(text) + " and not(" + getContainsTextXPath(notText) + ") and (" + alsoCondition + ")]");
		logTimed("web", "internal", "get label ID for text (3) complete");
		return element.getAttribute("id");
	}
	
	/**
	 * Assert that a form with the given heading exists. 
	 * Returns a list of forms that match the given text.
	 * 
	 * @see #hasNoInputFormForText(String)
	 * @param text
	 * @return
	 */
	protected List<IElement> hasInputFormForText(String text) {
		logTimed("web", "internal", "get form ID for text");
		List<IElement> elements = getElementsByXPath("//form[h2[" + getContainsTextXPath(text) + "]]");
		assertFalse("No forms found for text '" + text + "'", elements.isEmpty());
		logTimed("web", "internal", "get form ID for text complete");
		return elements;
	}

	/**
	 * Assert that a form with the given heading <em>does not</em> exist. 
	 * 
	 * @see #hasInputFormForText(String)
	 * @param text
	 */
	protected void hasNoInputFormForText(String text) {
		List<IElement> elements = getElementsByXPath("//form[h2[" + getContainsTextXPath(text) + "]]");
		assertTrue("Forms found for text '" + text + "'", elements.isEmpty());
	}	
	
	/**
	 * Assert that the {@model InputTextField} with the given name
	 * (<code>label</code>) has the expected text.
	 * 
	 * @param label the label of the {@model InputTextField}
	 * @param expected the expected value
	 */
	public void assertInputTextFieldEquals(String label, String expected) {
		String id = getLabelIDForText(label);
		assertLabeledFieldEquals(id, expected);
	}
	
	/**
	 * Set the given {@model InputTextField} with the given text.
	 */
	public void setInputTextField(String label, String value) {
		String id = getLabelIDForText(label);
		setLabeledFormElementField(id, value);
	}
	
	/**
	 * Set the given {@model InputTextField} with the given text,
	 * after checking that it is currently set to the expected text.
	 * 
	 */
	public void setInputTextField(String label, String expected, String value) {
		String id = getLabelIDForText(label);
		assertLabeledFieldEquals(id, expected);
		setLabeledFormElementField(id, value);
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
	 * Construct the necessary XPath expression to find a node that 
	 * is exactly the specific text.
	 * 
	 * <p>Currently returns <code>normalize-space(text()) = normalize-space('<u>text</u>')</code>.
	 * 
	 * @param text
	 * @return
	 */
	protected String getExactTextXPath(String text) {
		return "normalize-space(text()) = normalize-space('" + text + "')";
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
	public ResultSet loadDatabaseQuery(String dbName, String query) throws Exception {
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
		beginAtSitemapThenPage(sitemap, pageText, pageText);
	}
	
	/**
	 * Helper method: Assert that there has been a problem.
	 */
	protected void assertProblem() {
		try {
			resetDebug();
			assertMatch("(Error|error|Exception|exception)");
		} catch (AssertionFailedError e) {
			throw new RuntimeException("Problem not encountered: " + getTestingEngine().getPageText(), e); 
		}
	}
	
	/**
	 * Helper method: Assert that there has not been a problem.
	 */
	protected void assertNoProblem() {
		resetDebug();
		assertNoMatch("(Error|error|Exception|exception)");
	}
	
	/**
	 * Extend the base method to also throw the current page text when
	 * displaying the error.
	 */
	@Override
	public void assertNoMatch(String regexp) {
		try {
			resetDebug();
			super.assertNoMatch(regexp);
		} catch (AssertionFailedError e) {
			throw new RuntimeException("Problem encountered: " + getTestingEngine().getPageText(), e); 
		}
	}


	/**
	 * Reset the debug dialog text, improving the accuracy of methods
	 * like {@link #assertNoProblem()}.
	 */
	public void resetDebug() {
		if (hasElementById("debug")) {
			IElement debug = getElementById("debug");
			debug.setTextContent("");
		}
		if (hasElementById("response")) {
			IElement debug = getElementById("response");
			debug.setTextContent("");
		}
	}
	
	/**
	 * Get the current debug dialog text, or <code>null</code>
	 * if it can't be found.
	 * 
	 * @return the debug dialog text, or <code>null</code> if no debug dialog exists
	 */
	public String getDebugText() {
		if (hasElementById("debug")) {
			IElement debug = getElementById("debug");
			return debug.getTextContent();
		}
		return null;
	}
	
	/**
	 * We wrap the method to try and catch runtime exceptions
	 * from the PHP server-side code.
	 */
	@Override
	public void clickLinkWithText(String linkText) {
		logTimed("web", "clicking link with text", "");
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
		logTimed("web", "clicking link with text complete", "");
	}
	
	/**
	 * We wrap the method to try and catch runtime exceptions
	 * from the PHP server-side code.
	 */
	@Override
	public void clickLinkWithExactText(String linkText) {
		logTimed("web", "clicking link with exact text", "");
		try {
			super.clickLinkWithExactText(linkText);
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
		logTimed("web", "clicking link with text complete", "");
	}

	/**
	 * We wrap the method to try and catch runtime exceptions
	 * from the PHP server-side code.
	 * 
	 * <p>{@inheritDoc}
	 * @throws PhpRuntimeExceptionException
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
	 * We wrap the method to try and catch runtime exceptions
	 * from the PHP server-side code.
	 * 
	 * <p>{@inheritDoc}
	 * @throws PhpRuntimeExceptionException
	 */
	@Override
	public void clickButtonWithText(String buttonValueText) {
		try {
			super.clickButtonWithText(buttonValueText);
		} catch (RuntimeException f) {
			if (f.getCause() instanceof FailingHttpStatusCodeException) {
				FailingHttpStatusCodeException f2 = (FailingHttpStatusCodeException) f.getCause();
				// if we failed at exception.php, we can try and read out the error
				if (PhpRuntimeExceptionException.canHandle(f2)) {
					throw new PhpRuntimeExceptionException(f2);
				}
			}
			throw f;
		}
	}
	
	/**
	 * We wrap the method to try and catch runtime exceptions
	 * from the PHP server-side code.
	 * 
	 * <p>{@inheritDoc}
	 * @throws PhpRuntimeExceptionException
	 */
	@Override
	public void submit() {
		try {
			super.submit();
		} catch (TestingEngineResponseException f) {
			if (f.getCause() instanceof FailingHttpStatusCodeException) {
				FailingHttpStatusCodeException f2 = (FailingHttpStatusCodeException) f.getCause();
				// if we failed at exception.php, we can try and read out the error
				if (PhpRuntimeExceptionException.canHandle(f2)) {
					throw new PhpRuntimeExceptionException(f2);
				}
			}
			throw f;
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
		logTimed("web", "internal", "throwing debug information");
		// print out the source code
		System.out.println(getTester().getPageSource());
		System.out.println(getElementById("debug").getTextContent());
		// throw out any response text too
		logTimed("web", "internal", "throwing debug information complete");
		throw new RuntimeException("Response = '" + getElementById("response").getTextContent() + "' Debug='" + getElementById("debug").getTextContent() + "'", e);
		
	}
	
	@Override
	public void setLabeledFormElementField(String id, String value) {
		logTimed("web", "set labeled form", "");
		super.setLabeledFormElementField(id, value);
		logTimed("web", "set labeled form complete", "");
	}
	
	/**
	 * Assert that a label exists with the given text.
	 * 
	 * @param text
	 * @return the Label found with the given text.
	 */
	public IElement assertLabelTextPresent(String text) {
		assertFalse("Cannot assert the presence of an empty label", text.isEmpty());
		
		IElement match = getElementByXPath("//label[" + getContainsTextXPath(text) + "]");
		assertNotNull(match);
		String textContent = match.getTextContent();
		// normalise
		textContent = normalizeSpace(textContent);
		assertEquals(text, textContent);
		return match;
	}
	
	/**
	 * Assert that a label exists with the <em>exact</em> given text.
	 * 
	 * @param text
	 * @return the Label found with the given text.
	 */
	public IElement assertLabelTextExactlyPresent(String text) {
		IElement match = getElementByXPath("//label[" + getExactTextXPath(text) + "]");
		assertNotNull(match);
		String textContent = match.getTextContent();
		// normalise
		textContent = normalizeSpace(textContent);
		assertEquals(text, textContent);
		
		// make sure it's visible
		if (!isDisplayed(match)) {
			fail("The label with text '" + text + "' was not displayed: " + match);
		}
		
		return match;
	}
	
	/**
	 * Implementation of XPath's <code>normalize-space()</code>.
	 * 
	 * @param s
	 * @return
	 */
	protected String normalizeSpace(String s) {
		return s.replaceAll("[\\s]+", " ").trim();
	}
	
	/**
	 * Assert that a label <em>does not</em> exist with the given text.
	 * A label is also defined to not exist if it does exist, but it has
	 * a <code>style</code> property containing "<code>display: none;</code>".
	 * 
	 * @param text
	 */
	public void assertLabelTextNotPresent(String text) {
		List<IElement> results = getElementsByXPath("//label[" + getContainsTextXPath(text) + "]");
		for (IElement e : results) {
			// ask HtmlUnit if the element is visible
			if (isDisplayed(e)) {
				fail("Unexpectedly found a label with text '" + text + "' that was displayed: " + e);
			}			
		}
	}
	
	/**
	 * Assert that a label exists with the given text, but does
	 * not contain another text.
	 * 
	 * @param text the text to contain
	 * @param notText the text to <em>not</em> contain
	 * @return the Label found with the given text.
	 */
	public IElement assertLabelTextPresent(String text, String notText) {
		assertFalse(text.equals(notText));	// sanity check
		assertFalse("Cannot assert the presence of an empty label", text.isEmpty());
		
		IElement match = getElementByXPath("//label[" + getContainsTextXPath(text) + " and not(" + getContainsTextXPath(notText) + ")]");
		assertNotNull(match);
		String textContent = match.getTextContent();
		// normalise
		textContent = textContent.replaceAll("[\\s]+", " ").trim();
		assertEquals(text, textContent);
		
		return match;
	}
	
    /**
     * This method is extended to also check that any found buttons are
     * <em>not</em> invisible.
     * 
     * <p>{@inheritDoc}
     */
    @Override
	public void assertButtonPresentWithText(String text) {
    	IElement button = getButtonWithText(text);
    	// we found a button; check that it is visible
        assertTrue("Found button with text '" + text + "', but it was hidden", isDisplayed(button));
    }
	
    /**
     * This method is extended to also check that any found buttons are
     * also invisible.
     * 
     * <p>{@inheritDoc}
     */
    @Override
	public void assertButtonNotPresentWithText(String text) {
    	IElement button;
    	try {
    		button = getButtonWithText(text);
    	} catch (AssertionFailedError e) {
    		// expected
    		return;
    	}
    	
    	// we found a button, but it should be invisible
        assertFalse("Found button with text '" + text + "', but it was displayed", isDisplayed(button));
    }
	
	/**
	 * Example: <code>Fri, 03 Dec 1901 20:45:52 +0000</code>
	 */
	private static final String RFC_2822 = "EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z";
	
	/**
	 * Convert the given date to RFC 2822 format, represented in UTC.
	 */
	public String rfc2822(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat(RFC_2822, Locale.US);
		fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
		return fmt.format(date);
	}
	
	/**
	 * Get a page identifier for log methods.
	 * @return
	 */
	private String getPageIdentifier() {
		if (ENABLE_TIMED_LOG) {
			try {
				return ((HtmlUnitTestingEngineImpl) getTestingEngine()).getPageURL().toString();
			} catch (RuntimeException e) {
				// ignore
			}
		}
		return null;
	}

	private void logTimed(String string, String string2, String string3) {
		logTimed(string, string2, string3, getPageIdentifier());
	}
	

	/**
	 * Type in the given text to the Input element labelled by the given element ID.
	 * 
	 * @throws IOException 
	 */
	public void typeLabeledFormElement(String id, String text) throws IOException {
		// get the elements referenced by this label
		IElement label = getElementById(id);
		assertNotNull(label);
		
		List<IElement> elements = getFieldsForLabel(label);
		assertFalse("Could not find any elements for label " + id, elements.isEmpty());
		
		for (IElement element : elements) {
			// type it into this label
			assertTrue("Expected HtmlUnitElementImpl, but was " + element.getClass().getName(),
					element instanceof HtmlUnitElementImpl);

			// get the actual HtmlUnit element
			HtmlElement actual = ((HtmlUnitElementImpl) element).getHtmlElement();
			assertNotNull(actual);
			
			// type in the text
			actual.type(text);
			
			// wait until all background tasks (setTimeout, ...) are finished
			while (((HtmlUnitTestingEngineImpl) getTestingEngine()).getWebClient().waitForBackgroundJavaScript(500) != 0);
			
			// and return
			return;
		}	
	}
	
	/**
	 * blur() on the Input element labelled by the given element ID.
	 * 
	 * @param id
	 */
	public void loseFocus(String id) {
		// get the elements referenced by this label
		IElement label = getElementById(id);
		assertNotNull(label);
		
		List<IElement> elements = getFieldsForLabel(label);
		assertFalse("Could not find any elements for label " + id, elements.isEmpty());
		
		for (IElement element : elements) {
			// type it into this label
			assertTrue("Expected HtmlUnitElementImpl, but was " + element.getClass().getName(),
					element instanceof HtmlUnitElementImpl);

			// get the actual HtmlUnit element
			HtmlElement actual = ((HtmlUnitElementImpl) element).getHtmlElement();
			assertNotNull(actual);
			
			// lose focus
			actual.blur();
			
			// and return
			return;
		}	
		
	}
	
	/**
	 * Click the given Element. Assumed to be an HtmlUnitElementImpl.
	 * 
	 * @param label
	 * @throws IOException 
	 */
	public void clickElement(IElement label) throws IOException {
		((HtmlUnitElementImpl) label).getHtmlElement().click(); 
	}
	
	/**
	 * Get a button with the given text.
	 * 
	 * @throws AssertionFailedError if no button could be found
	 * @param text
	 * @return 
	 * @return the button found
	 */
	public IElement getButtonWithText(String text) {
		try {
        	// try <button>
    		return getElementByXPath("//button[" + getExactTextXPath(text) + "]" );
    	} catch (AssertionFailedError e) {
        	// try <input type="button">
    		return getElementByXPath("//input[(@type='button' or @type='submit' or @type='reset' or @type='image') and (@value='" + text + "')]" );
    	}
	}
	
    /**
     * Returns <code>true</code> if the given IElement is visible.
     * 
	 * @param element
	 */
	protected boolean isDisplayed(IElement element) {
		return ((HtmlUnitElementImpl) element).getHtmlElement().isDisplayed();
	}

	/*
	 * Extended to check that web browsing has first begun through {@link #beginAt()}. 
	 */
	@Override
	public String getElementAttributeByXPath(String xpath, String attribute) {
		assertTrue("Web browsing has not yet begun", hasBegun);
		return super.getElementAttributeByXPath(xpath, attribute);
	}

	/*
	 * Extended to check that web browsing has first begun through {@link #beginAt()}. 
	 */
	@Override
	public IElement getElementById(String id) {
		assertTrue("Web browsing has not yet begun", hasBegun);
		return super.getElementById(id);
	}

	/*
	 * Extended to check that web browsing has first begun through {@link #beginAt()}. 
	 */
	@Override
	public IElement getElementByXPath(String xpath) {
		assertTrue("Web browsing has not yet begun", hasBegun);
		return super.getElementByXPath(xpath);
	}

	/*
	 * Extended to check that web browsing has first begun through {@link #beginAt()}. 
	 */
	@Override
	public List<IElement> getElementsByXPath(String xpath) {
		assertTrue("Web browsing has not yet begun", hasBegun);
		return super.getElementsByXPath(xpath);
	}

	/*
	 * Extended to check that web browsing has first begun through {@link #beginAt()}. 
	 */
	@Override
	public String getElementTextByXPath(String xpath) {
		assertTrue("Web browsing has not yet begun", hasBegun);
		return super.getElementTextByXPath(xpath);
	}
	
}
