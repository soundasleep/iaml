/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openiaml.model.tests.PhpRuntimeExceptionException;
import org.openiaml.model.tests.codegen.model0_5_1.RSS2_0Reader.FeedItem;

/**
 * @example RemoteDomainObject,Frame
 * 		An {@model RemoteDomainObject external feed} can be piped through to
 * 		a local {@model Frame#render RSS} {@model Frame}.
 */
public class RemoteRSSFeedPiped extends FeedCodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(RemoteRSSFeedPiped.class);
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
			beginAtSitemapThenPage("Details");
			fail("Should not have been able to view 'Details'");
		} catch (PhpRuntimeExceptionException e) {
			assertContains("Required get variable 'generated_primary_key' was not found", e.getMessage());
		}
	}
	
	/**
	 * We can access the RSS feed without a problem.
	 * 
	 * @throws Exception
	 */
	public void testAccessRSS() throws Exception {
		beginAtSitemap();
		
		// get the URL of the 'details' page
		String newsUrl = getURLOfLink("Details");
		assertNotNull(newsUrl);
		
		// get the 'view remote feed' link
		String url = getURLOfLink("View Remote Feed");
		assertNotNull(url);
		
		RSS2_0Reader reader = getFeedReader(url, "View Remote Feed");
		
		// there should be two feed items, ordered by title
		assertEquals(2, reader.getFeedItems().size());
		Set<String> guids = new HashSet<String>();

		{
			// first item: New Event 123
			FeedItem item = reader.getFeedItems().get(0);
			
			assertEquals("New Event 123", item.getTitle());
			assertEquals("This event is great, because it was loaded through RSS!", item.getDescription());
			
			// construct expected URL
			String expectedLink = getTestContext().getBaseUrl().toString() + newsUrl + "?generated_primary_key=";
			assertTrue("Link '" + item.getLink() + "' does not begin with '" + expectedLink + "'", item.getLink().startsWith(expectedLink));
			
			// need to construct the date
			assertEquals(createUTCDate(2008, 05, 03, 9, 39, 21), item.getPubDate());
			
			// and the GUID must be unique in this list
			assertNotNull(item.getGuid());
			assertFalse("List of GUIDs already contains '" + item.getGuid() + "': " + guids, guids.contains(item.getGuid()));
			guids.add(item.getGuid());
		}
		
		{
			// first item: New Event 123
			FeedItem item = reader.getFeedItems().get(1);
			
			assertEquals("New Event 124", item.getTitle());
			assertEquals("This event is great, because it was also loaded through RSS!", item.getDescription());
			
			// construct expected URL
			String expectedLink = getTestContext().getBaseUrl().toString() + newsUrl + "?generated_primary_key=";
			assertTrue("Link '" + item.getLink() + "' does not begin with '" + expectedLink + "'", item.getLink().startsWith(expectedLink));
			
			// need to construct the date
			assertEquals(createUTCDate(2008, 05, 03, 9, 39, 21), item.getPubDate());
			
			// and the GUID must be unique in this list
			assertNotNull(item.getGuid());
			assertFalse("List of GUIDs already contains '" + item.getGuid() + "': " + guids, guids.contains(item.getGuid()));
			guids.add(item.getGuid());
		}
		
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
		// does nothing
		return s;
	}
	
}
