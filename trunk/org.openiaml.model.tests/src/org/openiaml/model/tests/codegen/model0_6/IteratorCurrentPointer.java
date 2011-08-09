/**
 *
 */
package org.openiaml.model.tests.codegen.model0_6;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;


/**
 * Tests {@issue 219}. The underlying DomainIterator has a limit
 * of 7 set.
 *
 * @example DomainIterator,Event
 * 		Using the {@event onIterate} {@model Event} along with
 * 		the {@model DomainIterator#currentPointer} {@model Value}.
 *
 */
public class IteratorCurrentPointer extends DatabaseCodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(getClass());
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
	 * The DomainIterator will select the first element from the
	 * database. Also tests the initial value of {@model DomainIterator#currentPointer}.
	 *
	 * @throws Exception
	 */
	public void testInitialValues() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();

		assertLabelTextExactlyPresent("Title 1");
		assertLabelTextExactlyPresent("Content 1");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");

		// pagination buttons are present
		assertButtonPresentWithText("Next");
		assertButtonPresentWithText("Previous");
		assertButtonPresentWithText("First");
		assertButtonPresentWithText("Last");

		// test current pointer
		assertInputTextFieldEquals("current pointer", "0");

	}

	/**
	 * Tests that navigation updates the current pointer.
	 *
	 * @throws Exception
	 */
	public void testNavigation() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();

		assertLabelTextExactlyPresent("Title 1");
		assertLabelTextExactlyPresent("Content 1");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");
		assertInputTextFieldEquals("current pointer", "0");

		// click Next button
		clickButtonWithText("Next");

		assertLabelTextExactlyPresent("Title 2");
		assertLabelTextExactlyPresent("Content 2");
		assertLabelTextNotPresent("Title 1");
		assertLabelTextNotPresent("Content 1");
		assertLabelTextNotPresent("Title 3");
		assertLabelTextNotPresent("Content 3");
		assertInputTextFieldEquals("current pointer", "1");

		// click Next button
		clickButtonWithText("Next");

		assertLabelTextExactlyPresent("Title 3");
		assertLabelTextExactlyPresent("Content 3");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");
		assertLabelTextNotPresent("Title 4");
		assertLabelTextNotPresent("Content 4");
		assertInputTextFieldEquals("current pointer", "2");

		// click Previous button
		clickButtonWithText("Previous");

		assertLabelTextExactlyPresent("Title 2");
		assertLabelTextExactlyPresent("Content 2");
		assertLabelTextNotPresent("Title 1");
		assertLabelTextNotPresent("Content 1");
		assertLabelTextNotPresent("Title 3");
		assertLabelTextNotPresent("Content 3");
		assertInputTextFieldEquals("current pointer", "1");

	}

	/**
	 * Tests that going previous of the first post does not change the pointer.
	 *
	 * @throws Exception
	 */
	public void testBeforeFirst() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();

		// click First button
		clickButtonWithText("Previous");

		assertLabelTextExactlyPresent("Title 1");
		assertLabelTextExactlyPresent("Content 1");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");
		assertInputTextFieldEquals("current pointer", "0");

		// click Previous button
		clickButtonWithText("Previous");

		// nothing has changed
		assertLabelTextExactlyPresent("Title 1");
		assertLabelTextExactlyPresent("Content 1");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");
		assertInputTextFieldEquals("current pointer", "0");
	}

	/**
	 * Tests that going next of the last post does not change the pointer.
	 *
	 * @throws Exception
	 */
	public void testAfterLast() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();

		clickButtonWithText("Last");

		assertLabelTextExactlyPresent("Title 7");
		assertLabelTextExactlyPresent("Content 7");
		assertLabelTextNotPresent("Title 1");
		assertLabelTextNotPresent("Content 1");
		assertInputTextFieldEquals("current pointer", "6");

		// click Next button
		clickButtonWithText("Next");

		// nothing has changed
		assertLabelTextExactlyPresent("Title 7");
		assertLabelTextExactlyPresent("Content 7");
		assertLabelTextNotPresent("Title 1");
		assertLabelTextNotPresent("Content 1");
		assertInputTextFieldEquals("current pointer", "6");
	}

	/**
	 * Populate the database with twenty blog posts.
	 */
	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE Blog (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(64) NOT NULL, content VARCHAR(64) NOT NULL)");
		for (int i = 1; i <= 20; i++) {
			s.add("INSERT INTO Blog (generated_primary_key, title, content) VALUES (" + i + ", 'Title " + i + "', 'Content " + i + "')");
		}
		return s;
	}

}
