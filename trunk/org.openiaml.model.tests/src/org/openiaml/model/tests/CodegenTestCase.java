/**
 * 
 */
package org.openiaml.model.tests;

import junit.framework.AssertionFailedError;
import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.junit.WebTestCase;

import org.eclipse.core.resources.IFile;
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
	 * Have we loaded at least one page (so we can find an ajax_monitor if necessary)?
	 */
	private boolean hasLoaded = false;
	
	/**
	 * Wait for all of the Ajax monitors to return.
	 * 
	 * @throws Exception
	 */
	protected void waitForAjax() throws Exception {
		// sleep a little bit first, so ajax calls can continue
		if (hasLoaded) {
			if (getElementById("ajax_monitor") == null) {
				Thread.sleep(2000);	// sleep for way too long, since we don't know when it will finish
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
	 * NOTE that this resets the current WebClient context, which can cause
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
	 */
	public void beginAt(String url) {
		try {
			super.beginAt(url);
		} catch (RuntimeException e) {
			throw new RuntimeException("Cannot connect to '" + url + "' relative to '" + BASE_URL + "'", e);	// for debugging
		}
		hasLoaded = true;		// we have now loaded a page
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
		waitForAjax();

		gotoPage(sitemap.getProjectRelativePath().toString());
		hasLoaded = true;		// we have now loaded a page
		assertTitleMatch("sitemap");
		
		assertLinkPresentWithText(pageText);
		clickLinkWithText(pageText);
		try {
			assertEquals(expectedTitle, getPageTitle());		// could be different
		} catch (AssertionFailedError e) {
			// something went wrong in the page execution, or
			// the output is mangled HTML: output page source for debug purposes
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
	
	
}
