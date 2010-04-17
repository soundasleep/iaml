/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.PhpRuntimeExceptionException;
import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

/**
 * 
 * @example DomainObjectInstance
 * 		Navigating around a {@model DomainObjectInstance} using the provided
 * 		{@model PrimitiveOperation}s. 
 * @author jmwright
 *
 */
public class IteratedSyncWires extends DatabaseCodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(IteratedSyncWires.class);
		initialiseDatabase();
	}

	/**
	 * The home page can be accessed.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
	}
	
	/**
	 * All of the necessary fields are accessible on the page.
	 * 
	 * @throws Exception
	 */
	public void testFormExists() throws Exception {
		beginAtSitemapThenPage("Home");
		
		getLabelIDForText("title");
		getLabelIDForText("content");
		getLabelIDForText("posted");
	}
	
	/**
	 * When loading the page for the first time, we get the first result.
	 * 
	 * @throws Exception
	 */
	public void testStartsWithFirst() throws Exception {
		beginAtSitemapThenPage("Home");
		assertContent1();
	}
	
	/**
	 * Because we are only selecting one result, we should not
	 * have any navigation results.
	 * 
	 * @see SelectWireManyPaginate
	 * @throws Exception
	 */
	public void testNoPaginationButtons() throws Exception {
		beginAtSitemapThenPage("Home");
		
		assertButtonNotPresentWithText("Next");
		assertButtonNotPresentWithText("Previous");
		assertButtonNotPresentWithText("First");
		assertButtonNotPresentWithText("Last");
	}

	/**
	 * Check the given content on the page.
	 */
	private void assertContent(String title, String content, String posted) {
		{
			String id = getLabelIDForText("title");
			assertLabeledFieldEquals(id, title);
		}

		{
			String id = getLabelIDForText("content");
			assertLabeledFieldEquals(id, content);
		}

		{
			String id = getLabelIDForText("posted");
			assertLabeledFieldEquals(id, posted);
		}
	}
	
	private void assertContent1() {
		assertContent("Title 1", "Content 1", "2010-01-01 11:11:00");
	}
	private void assertContent2() {
		assertContent("Title 2", "Content 2", "2010-02-01 12:11:00");
	}
	private void assertContent3() {
		assertContent("Title 3", "Content 3", "2010-01-03 11:13:00");
	}
	private void assertNewContent1() {
		assertContent("New Title 1", "Content 1", "2010-01-01 11:11:00");
	}
	private void assertNewContent3() {
		assertContent("Title 3", "New Content 3", "2010-01-03 11:13:00");
	}

	/**
	 * Click 'next' to get to the next element.
	 * 
	 * @throws Exception
	 */
	public void testNavigateNext() throws Exception {
		beginAtSitemapThenPage("Home");
		
		clickButtonWithText("next");
		assertNoProblem();

		assertContent2();

		// and again
		clickButtonWithText("next");
		assertNoProblem();

		assertContent3();
		
		// but if we click it again, we get an error
		try {
			clickButtonWithText("next");
			fail("Should have thrown an exception");
		} catch (PhpRuntimeExceptionException e) {
			// expected
			assertContains("No results found for query", e.getMessage());
		}
		assertProblem();

	}
	

	/**
	 * Click 'possibly next' to get to the next element.
	 * 
	 * @throws Exception
	 */
	public void testNavigatePossiblyNext() throws Exception {
		beginAtSitemapThenPage("Home");
		
		clickButtonWithText("possibly next");
		assertNoProblem();
		assertContent2();
		
		// and again
		clickButtonWithText("possibly next");
		assertNoProblem();
		assertContent3();
		
		// but if we click it again, we won't proceed anywhere
		clickButtonWithText("possibly next");
		assertNoProblem();
		assertContent3();
		
	}
	
	/**
	 * Click 'next' then 'previous'
	 * 
	 * @throws Exception
	 */
	public void testNavigateNextThenPrevious() throws Exception {
		beginAtSitemapThenPage("Home");
		
		clickButtonWithText("next");
		assertNoProblem();
		assertContent2();
		
		clickButtonWithText("previous");
		assertNoProblem();
		assertContent1();
		
		clickButtonWithText("next");
		assertNoProblem();
		assertContent2();

	}
	
	/**
	 * Click 'previous' immediately.
	 * 
	 * @throws Exception
	 */
	public void testPreviousFails() throws Exception {
		beginAtSitemapThenPage("Home");
		
		// but if we click it again, we get an error
		try {
			clickButtonWithText("previous");
			fail("Should have thrown an exception");
		} catch (PhpRuntimeExceptionException e) {
			// expected
			assertContains("Offset cannot be negative", e.getMessage());
		}
		assertProblem();

	}
	
	/**
	 * Click 'possibly previous' immediately.
	 * 
	 * @throws Exception
	 */
	public void testPossiblyPreviousPasses() throws Exception {
		beginAtSitemapThenPage("Home");
		
		clickButtonWithText("possibly previous");
		assertNoProblem();
		assertContent1();

	}

	/**
	 * Click 'reset' immediately.
	 * 
	 * @throws Exception
	 */
	public void testReset() throws Exception {
		beginAtSitemapThenPage("Home");
		
		clickButtonWithText("reset");
		assertNoProblem();
		assertContent1();

	}

	/**
	 * Click 'reset' twice.
	 * 
	 * @throws Exception
	 */
	public void testResetTwice() throws Exception {
		beginAtSitemapThenPage("Home");
		
		clickButtonWithText("reset");
		assertNoProblem();
		assertContent1();

		clickButtonWithText("reset");
		assertNoProblem();
		assertContent1();

	}
	
	/**
	 * 'Next', 'next', 'reset', 'next'
	 * 
	 * @throws Exception
	 */
	public void testNextNextResetNext() throws Exception {
		beginAtSitemapThenPage("Home");
		
		clickButtonWithText("next");
		assertNoProblem();
		assertContent2();

		// and again
		clickButtonWithText("next");
		assertNoProblem();
		assertContent3();
		
		// reset
		clickButtonWithText("reset");
		assertNoProblem();
		assertContent1();
		
		// and again
		clickButtonWithText("next");
		assertNoProblem();
		assertContent2();

	}
	
	/**
	 * 'Next', 'previous', 'reset', 'next'
	 * 
	 * @throws Exception
	 */
	public void testNextPreviousResetNext() throws Exception {
		beginAtSitemapThenPage("Home");
		
		clickButtonWithText("possibly next");
		assertNoProblem();
		assertContent2();

		// and again
		clickButtonWithText("previous");
		assertNoProblem();
		assertContent1();
		
		// reset
		clickButtonWithText("reset");
		assertNoProblem();
		assertContent1();
		
		// and again
		clickButtonWithText("possibly next");
		assertNoProblem();
		assertContent2();

	}
	
	/**
	 * Update the content of the first entry.
	 * 
	 * @throws Exception
	 */
	public void testEditingFirst() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("Home");
		assertContent1();
		
		{
			String id = getLabelIDForText("title");
			setLabeledFormElementField(id, "New Title 1");
			assertLabeledFieldEquals(id, "New Title 1");
		}
		
		clickButtonWithText("save current");
		assertNoProblem();
		
		assertNewContent1();
		
		// next
		clickButtonWithText("possibly next");
		assertNoProblem();
		assertContent2();
		
		// previous
		clickButtonWithText("possibly previous");
		assertNoProblem();
		assertNewContent1();
		
		// reload the page
		reloadPage(sitemap, "Home");
		assertNoProblem();
		assertNewContent1();
	}
	
	/**
	 * Update the content of the third entry entry.
	 * 
	 * @throws Exception
	 */
	public void testEditingThird() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("Home");
		
		clickButtonWithText("possibly next");	// to #2
		clickButtonWithText("possibly next");	// to #3
		
		{
			String id = getLabelIDForText("content");
			setLabeledFormElementField(id, "New Content 3");
		}
		
		clickButtonWithText("save current");
		assertNoProblem();
		
		assertNewContent3();
		
		// next
		clickButtonWithText("possibly next");
		assertNoProblem();
		assertNewContent3();
		
		// previous
		clickButtonWithText("possibly previous");
		assertNoProblem();
		assertContent2();
		
		// reload the page
		reloadPage(sitemap, "Home");
		assertNoProblem();
		assertContent2();
		
		// next
		clickButtonWithText("possibly next");
		assertNoProblem();
		assertNewContent3();
	}
	
	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE News (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(64) NOT NULL, content VARCHAR(64) NOT NULL, posted VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO News (generated_primary_key, title, content, posted) VALUES (1, 'Title 1', 'Content 1', '2010-01-01 11:11:00')");
		s.add("INSERT INTO News (generated_primary_key, title, content, posted) VALUES (2, 'Title 2', 'Content 2', '2010-02-01 12:11:00')");
		s.add("INSERT INTO News (generated_primary_key, title, content, posted) VALUES (3, 'Title 3', 'Content 3', '2010-01-03 11:13:00')");
		return s;
	}

	@Override
	protected String getDatabaseName() {
		return "output/model_127e56efe3e_4.db";
	}
	
}
