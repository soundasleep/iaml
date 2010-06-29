/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.PhpRuntimeExceptionException;
import org.openiaml.model.tests.codegen.model0_5_1.RSS2_0Reader.FeedItem;

/**
 * @example Frame
 *		A complete example of using an {@model Frame#render RSS} {@model Frame} to provide an RSS
 *		feed from a {@model DomainObjectInstance}. 
 */
public class FeedProducerComplete extends FeedCodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(FeedProducerComplete.class);
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
	 * Trying to view the 'view news' page without a query parameter fails.
	 * 
	 * @throws Exception
	 */
	public void testViewWithoutID() throws Exception {
		try {
			beginAtSitemapThenPage("View News");
			fail("Should not have been able to view 'View News'");
		} catch (PhpRuntimeExceptionException e) {
			assertContains("Required get variable 'id' was not found", e.getMessage());
		}
	}
	
	/**
	 * Trying to view the 'view news' page manually passes.
	 * 
	 * @throws Exception
	 */
	public void testViewWithID() throws Exception {
		IFile sitemap = getSitemap();
		beginAtSitemapThenPage(sitemap, "View News", "View News", "id=5");
		assertNoProblem();
		
		// date is displayed in RFC 2822 format
		assertContent("Title 5", "Description 5", "Tue, 05 Jan 2010 01:00:00 +0000");
		
		// there is no navigation buttons, since we are only selecting one
		assertButtonNotPresentWithText("Next");
		assertButtonNotPresentWithText("Previous");
		assertButtonNotPresentWithText("First");
		assertButtonNotPresentWithText("Last");
	}
	
	/**
	 * We can access the RSS feed without a problem.
	 * 
	 * @throws Exception
	 */
	public void testAccessRSS() throws Exception {
		beginAtSitemap();
		
		// get the URL of the 'view news' page
		String newsUrl = getURLOfLink("View News");
		assertNotNull(newsUrl);
		
		// get the 'target feed' link
		String url = getURLOfLink("Target Feed");
		assertNotNull(url);
		
		RSS2_0Reader reader = getFeedReader(url, "Target Feed");
		
		// there should be ten feed items
		assertEquals(10, reader.getFeedItems().size());
		
		// we start from 20 down to 11
		Set<String> guids = new HashSet<String>();
		int i = 20;
		for (FeedItem item : reader.getFeedItems()) {
			assertEquals("Title " + i, item.getTitle());
			assertEquals("Description " + i, item.getDescription());
			
			// construct expected URL
			String expectedLink = getTestContext().getBaseUrl().toString() + newsUrl + "?id=" + i;			
			assertEquals(expectedLink, item.getLink());
			
			// need to construct the date
			assertEquals(createUTCDate(2010, 0, i, 1, 0, 0), item.getPubDate());
			
			// and the GUID must be unique in this list
			assertNotNull(item.getGuid());
			assertFalse("List of GUIDs already contains '" + item.getGuid() + "': " + guids, guids.contains(item.getGuid()));
			guids.add(item.getGuid());
			
			// increment down, since we are in DESC			
			i--;
		}

	}

	/**
	 * Check the given content on the page.
	 * 
	 * <p>Because we are using a SetWire, we cannot select by TextField;
	 * we can only search for labels containing the given text.
	 */
	private void assertContent(String title, String description, String updated) {
		assertLabelTextPresent(title);
		assertLabelTextPresent(description);
		assertLabelTextPresent(updated);
	}
	
	/**
	 * Populate the database with twenty news items. The SelectWire
	 * only selects the first 10.
	 * 
	 * @param size
	 * @return
	 */
	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE News (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(64) NOT NULL, description VARCHAR(64) NOT NULL, updated DATETIME NOT NULL)");
		for (int i = 1; i <= 20; i++) {
			// SQLite does not actually support dates, and they are stored as strings!
			// we have to insert the date with a leading zero, to enable sorting correctly.
			String i2 = i < 10 ? "0" + i : Integer.toString(i);  
			s.add("INSERT INTO News (id, title, description, updated) VALUES (" + i + ", 'Title " + i + "', 'Description " + i + "', '2010-01-" + i2 + " 01:00:00 +0000')");
		}
		return s;
	}
	
}
