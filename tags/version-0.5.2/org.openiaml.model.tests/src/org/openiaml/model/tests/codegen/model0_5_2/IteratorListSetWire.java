/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_2;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

/**
 * @example IteratorList,DomainIterator
 * 		Using a {@model IteratorList} to render the contents of a {@model DomainIterator}.
 * 
 */
public class IteratorListSetWire extends DatabaseCodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(getClass());
	}

	/**
	 * The home page can be accessed.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		newsCount = 10;
		initialiseDatabase();
		beginAtSitemapThenPage("Home");
		assertNoProblem();
	}
	
	/**
	 * A ListIterator displays all of the contents of the Iterator, up to
	 * the limit provided by the Iterator.
	 * 
	 * @throws Exception
	 */
	public void testAllLabelsArePopulated() throws Exception {
		newsCount = 10;
		initialiseDatabase();
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
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
	 * If the DomainIterator has a limit of 3, but there is only 2 elements
	 * in the database, we can still see these two elements.
	 * 
	 * @throws Exception
	 */
	public void testLessLabels() throws Exception {
		newsCount = 2;
		initialiseDatabase();
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		assertLabelTextExactlyPresent("Title 1");
		assertLabelTextExactlyPresent("Content 1");
		assertLabelTextExactlyPresent("Title 2");
		assertLabelTextExactlyPresent("Content 2");
		assertLabelTextNotPresent("Title 3");
		assertLabelTextNotPresent("Content 3");
		assertLabelTextNotPresent("Title 4");
		assertLabelTextNotPresent("Content 4");
		assertLabelTextNotPresent("Title 10");
		assertLabelTextNotPresent("Content 10");
		
	}
	
	/**
	 * If the DomainIterator has a limit of 3, but the database is empty,
	 * we can still display a page with no errors.
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
