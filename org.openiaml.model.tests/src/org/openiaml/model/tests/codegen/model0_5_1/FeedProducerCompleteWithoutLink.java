/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_1;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.model.tests.codegen.model0_5_1.RSS2_0Reader.FeedItem;

/**
 * &lt;link&gt; and &lt;pubDate&gt; are optional in RSS, so we don't have
 * to include them.
 */
public class FeedProducerCompleteWithoutLink extends FeedCodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(FeedProducerCompleteWithoutLink.class);
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
	 * We can access the RSS feed without a problem.
	 * 
	 * @throws Exception
	 */
	public void testAccessRSS() throws Exception {
		beginAtSitemap();
		
		// get the 'target feed' link
		String url = getURLOfLink("Target Feed");
		assertNotNull(url);
		
		RSS2_0Reader reader = getFeedReader(url, "Target Feed");

		// there should be ten feed items
		assertEquals(10, reader.getFeedItems().size());
		
		// title is stored as a string, so we are ordered in terms of string
		String[] expected = new String[] { "9", "8", "7", "6", "5", "4", "3", "20", "2", "19" };
		
		// we start from 20 down to 11
		int i = 0;
		for (FeedItem item : reader.getFeedItems()) {
			String si = expected[i];
			assertEquals("Title " + si, item.getTitle());
			assertEquals("Description " + si, item.getDescription());
			
			// there is no <link>
			assertNull(item.getLink());
			
			// there is no <guid>
			assertNull(item.getGuid());
			
			// there is no <pubDate>
			assertNull(item.getPubDate());
			
			i++;
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
		s.add("CREATE TABLE News (id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(64) NOT NULL, description VARCHAR(64) NOT NULL)");
		for (int i = 1; i <= 20; i++) {
			s.add("INSERT INTO News (id, title, description) VALUES (" + i + ", 'Title " + i + "', 'Description " + i + "')");
		}
		return s;
	}

	@Override
	protected String getDatabaseName() {
		return "output/model_1280b46d146_77.db";
	}
	
}
