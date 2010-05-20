/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_2;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;


/**
 * DomainIterators can be searched, and the results displayed in
 * a IteratorList instantly (through AJAX).
 * 
 */
public class IteratorListSetWireSearch extends DatabaseCodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(getClass());
	}

	/**
	 * The home page can be accessed.
	 * The results are returned in order of ID ascending.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		newsCount = 12;
		initialiseDatabase();
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// first set the query to the empty string ''
		{
			String target = getLabelIDForText("search");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "");
		}
		
		// check the content on the page
		assertLabelTextExactlyPresent("Title 1");
		assertLabelTextExactlyPresent("Content 1");
		assertLabelTextExactlyPresent("Title 2");
		assertLabelTextExactlyPresent("Content 2");
		assertLabelTextExactlyPresent("Title 3");
		assertLabelTextExactlyPresent("Content 3");
		assertLabelTextNotPresent("Title 4");
		assertLabelTextNotPresent("Content 4");
		assertLabelTextNotPresent("Title 10");
		assertLabelTextNotPresent("Content 10");
	}
	
	/**
	 * If we access the home page without setting a query, the
	 * query is currently <code>null</code>, so there will be no results.
	 * 
	 * @implementation DomainIterator
	 * 		Incoming {@model ParameterEdge}s into a {@model DomainIterator} 
	 * 		can be <code>null</code>; this may cause some queries to fail
	 * 		(e.g. <em>matches(a, <code>null</code>)</em> will return no results).
	 * 
	 * @throws Exception 
	 */
	public void testHomeNull() throws Exception {
		newsCount = 12;
		initialiseDatabase();
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// no results are visible
		assertLabelTextNotPresent("Title 1");
		assertLabelTextNotPresent("Content 1");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");
		assertLabelTextNotPresent("Title 3");
		assertLabelTextNotPresent("Content 3");
		assertLabelTextNotPresent("Title 4");
		assertLabelTextNotPresent("Content 4");
		assertLabelTextNotPresent("Title 10");
		assertLabelTextNotPresent("Content 10");
	}
	
	/**
	 * Search for '1'.
	 *  
	 * @throws Exception
	 */
	public void testSearch1() throws Exception {
		// go to the home page
		testHome();
		
		// enter in the query
		{
			String target = getLabelIDForText("search");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "1");
		}
		
		// this will search for '1'
		assertNoProblem();
		
		// check the content on the page
		assertLabelTextExactlyPresent("Title 1");
		assertLabelTextExactlyPresent("Content 1");
		assertLabelTextExactlyPresent("Title 10");
		assertLabelTextExactlyPresent("Content 10");
		assertLabelTextExactlyPresent("Title 11");
		assertLabelTextExactlyPresent("Content 11");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");
		assertLabelTextNotPresent("Title 3");
		assertLabelTextNotPresent("Content 3");
		assertLabelTextNotPresent("Title 12");		// over limit
		assertLabelTextNotPresent("Content 12");
	}
	
	/**
	 * Search for '1', then search for empty string again.
	 *  
	 * @throws Exception
	 */
	public void testSearch1ThenEmpty() throws Exception {
		// search for '1'
		testSearch1();
		
		// reset the query
		{
			String target = getLabelIDForText("search");
			assertLabeledFieldEquals(target, "1");
			setLabeledFormElementField(target, "");
		}
		
		// this will search for ''
		assertNoProblem();
		
		// check the content on the page
		assertLabelTextExactlyPresent("Title 1");
		assertLabelTextExactlyPresent("Content 1");
		assertLabelTextExactlyPresent("Title 2");
		assertLabelTextExactlyPresent("Content 2");
		assertLabelTextExactlyPresent("Title 3");
		assertLabelTextExactlyPresent("Content 3");
		assertLabelTextNotPresent("Title 4");
		assertLabelTextNotPresent("Content 4");
		assertLabelTextNotPresent("Title 10");
		assertLabelTextNotPresent("Content 10");
	}
	
	/**
	 * Search for '10': there is only one result.
	 * 
	 * @throws Exception
	 */
	public void testSearch10() throws Exception {
		// go to the home page
		testHome();
		
		// enter in the query
		{
			String target = getLabelIDForText("search");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "10");
		}
		
		// this will search for '10'
		assertNoProblem();
		
		// check the content on the page
		assertLabelTextExactlyPresent("Title 10");
		assertLabelTextExactlyPresent("Content 10");
		assertLabelTextNotPresent("Title 11");
		assertLabelTextNotPresent("Content 11");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");
		assertLabelTextNotPresent("Title 3");
	}
	
	/**
	 * Search for a query that has no results.
	 * 
	 * @throws Exception
	 */
	public void testSearchEmpty() throws Exception {
		// go to the home page
		testHome();
		
		// enter in the query
		{
			String target = getLabelIDForText("search");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "invalid");
		}
		
		// this will search for 'invalid'
		assertNoProblem();
		
		// check the content on the page
		assertLabelTextExactlyPresent("Title 1");
		assertLabelTextExactlyPresent("Content 1");
		assertLabelTextExactlyPresent("Title 2");
		assertLabelTextExactlyPresent("Content 2");
		assertLabelTextExactlyPresent("Title 3");
		assertLabelTextExactlyPresent("Content 3");
		assertLabelTextNotPresent("Title 4");
		assertLabelTextNotPresent("Content 4");
		assertLabelTextNotPresent("Title 10");
		assertLabelTextNotPresent("Content 10");
		
		// now search for '2'
		{
			String target = getLabelIDForText("search");
			assertLabeledFieldEquals(target, "invalid");
			setLabeledFormElementField(target, "2");
		}
		
		assertLabelTextExactlyPresent("Title 2");
		assertLabelTextExactlyPresent("Content 2");
		assertLabelTextExactlyPresent("Title 12");
		assertLabelTextExactlyPresent("Content 12");
		assertLabelTextNotPresent("Title 1");
		assertLabelTextNotPresent("Content 1");
		assertLabelTextNotPresent("Title 3");
		assertLabelTextNotPresent("Content 3");
	}
		
	/**
	 * If the DomainIterator has a limit of 3, but the database is empty,
	 * we can still display a page with no errors, and search over an empty
	 * database.
	 * 
	 * @throws Exception
	 */
	public void testEmptyDatabase() throws Exception {
		newsCount = 0;
		initialiseDatabase();
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		assertLabelTextNotPresent("Title 1");
		assertLabelTextNotPresent("Content 1");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");
		assertLabelTextNotPresent("Title 3");
		assertLabelTextNotPresent("Content 3");
		assertLabelTextNotPresent("Title 4");
		assertLabelTextNotPresent("Content 4");
		assertLabelTextNotPresent("Title 10");
		assertLabelTextNotPresent("Content 10");
		
		// enter in the query
		{
			String target = getLabelIDForText("search");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "1");
		}
		
		// nothing has changed
		assertNoProblem();
		assertLabelTextNotPresent("Title 1");
		assertLabelTextNotPresent("Content 1");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");
		assertLabelTextNotPresent("Title 3");
		assertLabelTextNotPresent("Content 3");
		assertLabelTextNotPresent("Title 4");
		assertLabelTextNotPresent("Content 4");
		assertLabelTextNotPresent("Title 10");
		assertLabelTextNotPresent("Content 10");
		
	}
	
	/**
	 * If we search, then reload the page, the results will persist.
	 * 
	 * @throws Exception
	 */
	public void testSearchesPersist() throws Exception {
		newsCount = 12;
		initialiseDatabase();
		IFile sitemap = beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// check the content on the page
		assertLabelTextExactlyPresent("Title 1");
		assertLabelTextExactlyPresent("Content 1");
		assertLabelTextExactlyPresent("Title 2");
		assertLabelTextExactlyPresent("Content 2");
		assertLabelTextExactlyPresent("Title 3");
		assertLabelTextExactlyPresent("Content 3");
		assertLabelTextNotPresent("Title 4");
		assertLabelTextNotPresent("Content 4");
		assertLabelTextNotPresent("Title 10");
		assertLabelTextNotPresent("Content 10");
		
		// enter in the query
		{
			String target = getLabelIDForText("search");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "1");
		}
		
		// this will search for '1'
		assertNoProblem();
		
		// check the content on the page
		assertLabelTextExactlyPresent("Title 1");
		assertLabelTextExactlyPresent("Content 1");
		assertLabelTextExactlyPresent("Title 10");
		assertLabelTextExactlyPresent("Content 10");
		assertLabelTextExactlyPresent("Title 11");
		assertLabelTextExactlyPresent("Content 11");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");
		assertLabelTextNotPresent("Title 3");
		assertLabelTextNotPresent("Content 3");
		assertLabelTextNotPresent("Title 12");		// over limit
		assertLabelTextNotPresent("Content 12");
		
		// reload the page
		reloadPage(sitemap, "Home");
		assertNoProblem();
		
		// query still present
		{
			String target = getLabelIDForText("search");
			assertLabeledFieldEquals(target, "1");
		}
		
		// content still present
		assertLabelTextExactlyPresent("Title 1");
		assertLabelTextExactlyPresent("Content 1");
		assertLabelTextExactlyPresent("Title 10");
		assertLabelTextExactlyPresent("Content 10");
		assertLabelTextExactlyPresent("Title 11");
		assertLabelTextExactlyPresent("Content 11");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");
		assertLabelTextNotPresent("Title 3");
		assertLabelTextNotPresent("Content 3");
		assertLabelTextNotPresent("Title 12");		// over limit
		assertLabelTextNotPresent("Content 12");
		
	}
	
	private int newsCount = -1;
	
	/**
	 * Populate the database with twenty news items. The DomainIterator
	 * only selects the first 3.
	 * 
	 * @param size
	 * @return
	 */
	@Override
	protected List<String> getDatabaseInitialisers() {
		if (newsCount < 0)
			throw new IllegalArgumentException("newsCount has to be set");
		
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE News (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(64) NOT NULL, content VARCHAR(64) NOT NULL, updated DATETIME NOT NULL)");
		for (int i = 1; i <= newsCount; i++) {
			// SQLite does not actually support dates, and they are stored as strings!
			// we have to insert the date with a leading zero, to enable sorting correctly.
			String i2 = i < 10 ? "0" + i : Integer.toString(i);  
			s.add("INSERT INTO News (id, title, content, updated) VALUES (" + i + ", 'Title " + i + "', 'Content " + i + "', '2010-01-" + i2 + " 01:00:00 +0000')");
		}
		return s;
	}
	
}
