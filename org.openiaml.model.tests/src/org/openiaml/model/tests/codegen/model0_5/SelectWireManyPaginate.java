/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

/**
 * If we select multiple results and connect the resulting 
 * {@model DomainObjectInstance} to a {@model InputForm} with a
 * {@model SetWire}, the InputForm will be populated with buttons
 * and conditions necessary for navigating over the result set.
 * 
 * @example SelectWire
 * 		Using a {@model SelectWire} that selects {@model SelectWire#limit many results}
 * 		to transform an {@model InputForm} into a paginated browser over a {@model DomainObjectInstance}.
 */
public class SelectWireManyPaginate extends DatabaseCodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SelectWireManyPaginate.class);
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
	 * Test the initial contents of the form.
	 * 
	 * @throws Exception
	 */
	public void testContentsOfForm() throws Exception {
		
		beginAtSitemapThenPage("Home");
		
		assertContent("Title 1", "Content 1");
		
		assertButtonPresentWithText("Next");
		assertButtonPresentWithText("Previous");
		assertButtonPresentWithText("First");
		assertButtonPresentWithText("Last");
		
		assertNoResults("10");	// we do not get the full 10 results
		assertResults("5");
		
	}
	
	/**
	 * We can navigate through it.
	 * 
	 * @throws Exception
	 */
	public void testNavigateThrough() throws Exception {
		
		beginAtSitemapThenPage("Home");

		// initial
		assertContent("Title 1", "Content 1");
		
		// navigate
		for (int i = 2; i <= 5; i++) {
			clickButtonWithText("Next");
			assertContent("Title " + i, "Content " + i);
		}
		
		// if we click 'next', we won't fail
		assertNoProblem();
		clickButtonWithText("Next");
		assertNoProblem();
		assertContent("Title 5", "Content 5");

		// we can cycle backwards
		for (int i = 4; i >= 1; i--) {
			clickButtonWithText("Previous");
			assertContent("Title " + i, "Content " + i);
		}
		
		// if we click 'previous', we won't fail
		assertNoProblem();
		clickButtonWithText("Previous");
		assertNoProblem();
		assertContent("Title 1", "Content 1");
		
	}
	
	/**
	 * We can navigate through it, then press reset.
	 * 
	 * @throws Exception
	 */
	public void testNavigateThroughThenReset() throws Exception {
		
		beginAtSitemapThenPage("Home");

		// initial
		assertContent("Title 1", "Content 1");
		
		// navigate
		for (int i = 2; i <= 5; i++) {
			clickButtonWithText("Next");
			assertContent("Title " + i, "Content " + i);
		}
		
		// click reset
		assertNoProblem();
		clickButtonWithText("First");
		assertNoProblem();
		assertContent("Title 1", "Content 1");

		// if we click 'previous', we won't fail
		clickButtonWithText("Previous");
		assertNoProblem();
		assertContent("Title 1", "Content 1");
		
	}
	
	/**
	 * We can navigate through it, then reload the page; the
	 * results will remain.
	 * 
	 * @throws Exception
	 */
	public void testNavigateThroughThenReload() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Home");

		// initial
		assertContent("Title 1", "Content 1");
		
		// navigate
		for (int i = 2; i <= 5; i++) {
			clickButtonWithText("Next");
			assertContent("Title " + i, "Content " + i);
		}
	
		// reload the page
		reloadPage(sitemap, "Home");
		
		// still on the same page
		assertContent("Title 5", "Content 5");
	
		// restart the session
		restartSession(sitemap, "Home");

		// still on the same page
		assertContent("Title 5", "Content 5");

	}
	
	/**
	 * Test 'Last' then 'First'
	 * 
	 * @throws Exception
	 */
	public void testLastFirst() throws Exception {
		
		beginAtSitemapThenPage("Home");

		// initial
		assertContent("Title 1", "Content 1");
		
		// last
		clickButtonWithText("Last");
		assertNoProblem();
		assertContent("Title 5", "Content 5");

		// first
		clickButtonWithText("First");
		assertNoProblem();
		assertContent("Title 1", "Content 1");

		// first
		clickButtonWithText("First");
		assertNoProblem();
		assertContent("Title 1", "Content 1");

	}
	
	/**
	 * Check the given content on the page.
	 * 
	 * <p>Because we are using a SetWire, we cannot select by TextField;
	 * we can only search for labels containing the given text.
	 */
	private void assertContent(String title, String content) {
		assertLabelTextPresent(title);
		assertLabelTextPresent(content);
	}
	
	private void assertResults(String results) {
		assertLabelTextPresent(results);
	}
	private void assertNoResults(String results) {
		assertLabelTextNotPresent(results);
	}
	
	/**
	 * Populate the database with ten news items. The SelectWire
	 * only selects the first 5.
	 * 
	 * @param size
	 * @return
	 */
	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE News_Item (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(64) NOT NULL, content VARCHAR(64) NOT NULL)");
		for (int i = 1; i <= 10; i++) {
			s.add("INSERT INTO News_Item (generated_primary_key, title, content) VALUES (" + i + ", 'Title " + i + "', 'Content " + i + "')");
		}
		return s;
	}
	
}
