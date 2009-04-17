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
public class CodegenTestCase extends InferenceTestCase {
	
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

		super.setUp();		// create project
		doTransform(outModel);	// output to project
		
		return root;
	}
	
	/**
	 * When we close the test case, we should also close the project.
	 */
	@Override
	protected void tearDown() throws Exception {
		if (getProject() != null) {
			getProject().close(monitor);
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
						Thread.sleep(300);
					}
					cycles++;
				}
				
			}
		}
		
	}

	/**
	 * Begin at the sitemap page, and then click on a particular page title.
	 * 
	 * <b>NOTE</b> that this resets the current WebClient context, which can cause
	 * the client to lose sessions/cookies. If this is undesirable,
	 * use {@link #gotoSitemapThenPage(IFile, String)}.
	 */ 
	protected void beginAtSitemapThenPage(IFile sitemap, String pageText) throws Exception {
		waitForAjax();

		beginAt(sitemap.getProjectRelativePath().toString());
		assertTitleMatch("sitemap");
		
		assertLinkPresentWithText(pageText);
		clickLinkWithText(pageText);
		try {
			assertTitleMatch(pageText);
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
	 * We need some way of working out the label ID that contains 
	 * a particular string.
	 * 
	 * @param text
	 * @return
	 */
	protected String getLabelIDForText(String text) {
		IElement element = getElementByXPath("//label[contains(text(),'" + text + "')]");
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
		waitForAjax();
		IFile target = project.getFile(filename);
		assertTrue("File '" + target + "' exists", target.exists());
		
		Properties p = new Properties();
		target.refreshLocal(IResource.DEPTH_INFINITE, monitor);	// refresh
		p.load(target.getContents());

		return p;
	}
	
}
