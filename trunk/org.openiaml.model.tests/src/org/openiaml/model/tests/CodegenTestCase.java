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
import org.openiaml.model.model.InternetApplication;

/**
 * Code generation-specific test cases.
 * 
 * @author jmwright
 *
 */
public abstract class CodegenTestCase extends InferenceTestCase {
	
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
	protected InternetApplication loadAndCodegen(String modelFile) throws Exception {
		InternetApplication root = loadAndInfer(modelFile);
		
		// write out this inferred model for reference
		String outModel = getInferredModel().getAbsolutePath();

		if (!isSetup) {
			// TODO remove this in the future
			// we don't want this method to create the project; it should be created already
			throw new RuntimeException("This test case should have called setUp() already [this creates the project]");
			// super.setUp();		// create project
		}
		doTransform(outModel);	// output to project
		
		return root;
	}
	
	/**
	 * Automagically load the model file (.iaml) for this given
	 * test class.
	 * 
	 * @see #loadAndCodegen(String)
	 * @param class1 The test class to load a model for.
	 * @return the loaded and compiled InternetApplication
	 */
	protected InternetApplication loadAndCodegen(
			Class<?> class1) throws Exception {
		if (class1.getPackage().getName().contains("model0_1")) {
			return loadAndCodegen(getAbsolutePathRoot() + ROOT + "codegen/model0_1/" + class1.getSimpleName() + ".iaml");
		}
		if (class1.getPackage().getName().contains("model0_2")) {
			return loadAndCodegen(getAbsolutePathRoot() + ROOT + "codegen/model0_2/" + class1.getSimpleName() + ".iaml");
		}
		if (class1.getPackage().getName().contains("model0_3")) {
			return loadAndCodegen(getAbsolutePathRoot() + ROOT + "codegen/model0_3/" + class1.getSimpleName() + ".iaml");
		}
		if (class1.getPackage().getName().contains("runtime")) {
			return loadAndCodegen(getAbsolutePathRoot() + ROOT + "codegen/runtime/" + class1.getSimpleName() + ".iaml");
		}
		fail("Could not work out an IAML model for class: " + class1);
		return null;
	}
	
	/**
	 * When we close the test case, we should also close the project.
	 */
	@Override
	protected void tearDown() throws Exception {
		// remove reference to InternetApplication
		if (root != null) {
			root = null;
		}
		
		super.tearDown();
	}

	/** 
	 * Have we loaded at least one page (so we can find an ajax_monitor if necessary)?
	 */
	private boolean hasLoaded = false;
	private boolean hasBegun;
	
	/**
	 * Wait for all of the Ajax monitors to return.
	 * 
	 * @throws Exception
	 */
	protected void waitForAjax() throws Exception {
		// sleep a little bit first, so ajax calls can continue
		if (hasLoaded) {
			if (getElementById("ajax_monitor") == null) {
				throw new RuntimeException("Ajax monitor did not exist, even though we expected it to.");
				// Thread.sleep(2000);	// sleep for way too long, since we don't know when it will finish
			} else {
				int cycles = 0;
				while (cycles < 500) {		// max 15 seconds
					try {
						IElement monitor = getElementById("ajax_monitor");
						String text = monitor.getTextContent();
						if (text != null && (text.isEmpty() || new Integer(text) == 0))		// all ajax calls have finished
							break;		// completed; we can carry on the test case
						
						if (text.length() > 6 && text.substring(0, 6).equals("failed"))
							throw new Exception("Ajax loading failed: " + monitor.getTextContent());
					
						// carry on sleeping
						Thread.sleep(30);
					} catch (AssertionFailedError e) {
						// the monitor was not found
						Thread.sleep(30);
					}
					cycles++;
				}
				
			}
		}
		
	}

	/**
	 * Begin at the sitemap page, and then click on a particular page title.
	 * This method also checks that the destination page is the same as the
	 * target page; if not, use {@link #beginAtSitemapThenPage(IFile, String, String)}.
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
	protected void beginAtSitemapThenPage(IFile sitemap, String pageTitle) throws Exception {
		beginAtSitemapThenPage(sitemap, pageTitle, pageTitle);
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
	protected void beginAtSitemapThenPage(IFile sitemap, String pageTitle, String expectedPageTitle) throws Exception {
		waitForAjax();

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
		
		assertLinkPresentWithText(pageTitle);
		clickLinkWithText(pageTitle);
		try {
			assertTitleMatch(expectedPageTitle);
		} catch (AssertionFailedError e) {
			// something went wrong in the page execution, or
			// the output is mangled HTML: output page source for debug purposes
			System.out.println(this.getPageSource());
			throw e;	// carry on throwing
		}
		
	}
	
	/**
	 * We extend {@link WebTestCase#beginAt(String)} to also set
	 * {@link #hasLoaded} to true (to help Ajax navigation).
	 * 
	 * <b>NOTE</b> that this resets the current WebClient context, which can cause
	 * the client to lose sessions/cookies. If this is undesirable,
	 * use {@link #gotoSitemapThenPage(IFile, String)}.
	 */
	public void beginAt(String url) {
		try {
			super.beginAt(url);
		} catch (RuntimeException e) {
			throw new RuntimeException("Cannot connect to '" + url + "' relative to '" + BASE_URL + "'", e);	// for debugging
		}
		hasLoaded = true;		// we have now loaded a page
		hasBegun = true;		// we have begun somewhere
	}
	
	/**
	 * Go to the sitemap page, and then click on a particular page title.
	 * 
	 * If you want the client to be reset (e.g. delete cookies, sessions),
	 * use {@link #beginAtSitemapThenPage(IFile, String)}.
	 * 
	 * @param sitemap the sitemap url to start from
	 * @param pageText the page text link to click
	 * @param expected the expected page title on the new page, if different from the page text link
	 */ 
	protected void gotoSitemapThenPage(IFile sitemap, String pageText, String expectedTitle) throws Exception {
		// we can't goto the sitemap if we haven't begun the session yet
		// (sanity check)
		if (!hasBegun)
			throw new RuntimeException("You cannot gotoSitemap() for a session that hasn't started yet. Use beginAt or beginAtSitemapThenPage instead.");
	
		waitForAjax();

		gotoPage(sitemap.getProjectRelativePath().toString());
		hasLoaded = true;		// we have now loaded a page
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
		
	}
	
	/**
	 * Go to the sitemap page, and then click on a particular page title.
	 * 
	 * If you want the client to be reset (e.g. delete cookies, sessions),
	 * use {@link #beginAtSitemapThenPage(IFile, String)}.
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
	 * @param text
	 * @return
	 */
	protected String getLabelIDForText(String text) {
		IElement element = getElementByXPath("//label[contains(normalize-space(text()), normalize-space('" + text + "'))]");
		return element.getAttribute("id");
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
	 * TODO move into JWebUnit.
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
			assertTrue("'" + a + "' should not equal '" + b + "'", b != null);
		} else {
			assertTrue("'" + a + "' should not equal '" + b + "'", a.equals(b));
		}
	}

	
	/**
	 * Load a database in the given database file, and execute
	 * a SQL query against it.
	 * 
	 * @param dbName database file to load
	 * @param query SQL query to execute
	 * @return the SQL result set
	 * @throws Exception
	 */
	protected ResultSet loadDatabaseQuery(String dbName, String query) throws Exception {
		// wait for ajax calls if necessary
		waitForAjax();
		
		// refresh the workspace
		assertTrue(refreshProject().isOK());
		
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
		assertTrue(refreshProject().isOK());

		IFile target = project.getFile(filename);
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
		beginAtSitemapThenPage(sitemap, pageText);
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

}
