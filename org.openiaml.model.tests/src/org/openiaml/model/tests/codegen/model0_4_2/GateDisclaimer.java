/**
 *
 */
package org.openiaml.model.tests.codegen.model0_4_2;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Demonstrating the use of EntryGate to prevent access to a
 * Scope without first viewing a disclaimer page.
 *
 * @author jmwright
 * @example Gate
 * 		Using an entry {@model Gate} to prevent access to a {@model Scope} without
 * 		first viewing a disclaimer {@model Frame}.
 * @implementation Gate
 * 		If a {@model Frame} is contained within a {@model Session} that contains
 * 		an entry {@model Gate}, any incoming {@model Condition}s on that Gate
 * 		must first be satisfied in order to proceed.
 * @implementation Gate,ECARule
 *		An {@model ECARule} may be used to resume {@model Frame} access
 *		previously limited by an entry {@model Gate}.
 */
public class GateDisclaimer extends CodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(GateDisclaimer.class);
	}

	/**
	 * The home page is empty.
	 *
	 * @throws Exception
	 */
	public void testHome() throws Exception {

		beginAtSitemapThenPage("Home");
		assertNoProblem();

	}

	/**
	 * If we try to visit 'Page 1', we are forced to visit
	 * the disclaimer.
	 *
	 * @throws Exception
	 */
	public void testDisclaimer() throws Exception {

		beginAtSitemapThenPage("Page 1", "Disclaimer Page");
		assertNoProblem();

	}

	/**
	 * If we try to visit 'Page 1', we have to visit the
	 * disclaimer; we can then visit 'Page 1' after this.
	 *
	 * @throws Exception
	 */
	public void testDisclaimerVisit() throws Exception {

		IFile sitemap = beginAtSitemapThenPage("Page 1", "Disclaimer Page");
		assertTitleEquals("Disclaimer Page");

		// try going to Page 1 now
		gotoSitemapThenPage(sitemap, "Page 1");
		assertTitleEquals("Page 1");

	}

	/**
	 * If we get interrupted, we can resume from where the gate threw us off.
	 *
	 * @throws Exception
	 */
	public void testDisclaimerResume() throws Exception {

		beginAtSitemapThenPage("Page 2", "Disclaimer Page");
		assertTitleEquals("Disclaimer Page");

		// click on the 'resume' button
		clickButtonWithText("continue");
		assertTitleEquals("Page 2");

	}

	/**
	 * Visiting the disclaimer is limited to only the session.
	 *
	 * @throws Exception
	 */
	public void testDisclaimerLimitedToSession() throws Exception {

		// first session
		testDisclaimerVisit();

		// second session
		testDisclaimerVisit();

	}

	/**
	 * If we view the disclaimer once, it has been viewed
	 * for all pages.
	 *
	 * @throws Exception
	 */
	public void testDisclaimerOnAllPages() throws Exception {

		IFile sitemap = beginAtSitemapThenPage("Page 2", "Disclaimer Page");
		assertTitleEquals("Disclaimer Page");

		// try going to Page 3 now
		gotoSitemapThenPage(sitemap, "Page 3");

		// try going to Page 2 now
		gotoSitemapThenPage(sitemap, "Page 2");

		// try going to Page 1 now
		gotoSitemapThenPage(sitemap, "Page 1");

	}

	/**
	 * We still have to view the disclaimer even if we click
	 * onto the page from another button.
	 *
	 * @throws Exception
	 */
	public void testNavigationToDisclaimer() throws Exception {

		IFile sitemap = beginAtSitemapThenPage("List of Pages");

		// there should be a button here
		assertButtonPresentWithText("page 3");
		clickButtonWithText("page 3");

		// we are now on the disclaimer
		assertTitleEquals("Disclaimer Page");

		// but if we go back, we can carry on
		gotoSitemapThenPage(sitemap, "List of Pages");
		clickButtonWithText("page 3");

		assertTitleEquals("Page 3");

	}

}
