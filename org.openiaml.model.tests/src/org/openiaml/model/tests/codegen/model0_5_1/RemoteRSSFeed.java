/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_1;

import org.openiaml.model.tests.CodegenTestCase;


/**
 * @example RemoteDomainObject
 * 		Using a {@model RemoteDomainObject} to download information from
 * 		a remote RSS feed.
 */
public class RemoteRSSFeed extends CodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(RemoteRSSFeed.class);
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
	 * We can go to the page to view the feed without a problem.
	 * 
	 * @throws Exception
	 */
	public void testViewFeed() throws Exception {
		beginAtSitemapThenPage("View Remote Feed");
		assertNoProblem();
	}
	
	/**
	 * We can view the feed, and navigate through it.
	 * 
	 * @throws Exception
	 */
	public void testNavigateThroughFeed() throws Exception {
		beginAtSitemapThenPage("View Remote Feed");
		
		// first result
		assertLabelTextExactlyPresent("New Event 123");
		assertLabelTextExactlyPresent("This event is great, because it was loaded through RSS!");
		
		// the <link> wasn't defined in the model
		assertLabelTextNotPresent("http://example.com/event/123");
		assertLabelTextNotPresent("http://example.com/feed#123");
		
		// the date is stored as a date, so it will be rendered as a date
		assertLabelTextExactlyPresent("Tue, 03 Jun 2008 09:39:21 GMT");
		
		// we can click the 'Next' button
		clickButtonWithText("Next");
		assertNoProblem();
		
		// the values have changed
		assertLabelTextExactlyPresent("New Event 124");
		assertLabelTextExactlyPresent("This event is great, because it was also loaded through RSS!");
		
		// the <link> wasn't defined in the model
		assertLabelTextNotPresent("http://example.com/event/124");
		assertLabelTextNotPresent("http://example.com/feed#124");
		
		// the date is stored as a date, so it will be rendered as a date
		assertLabelTextExactlyPresent("Tue, 03 Jun 2008 09:39:21 GMT");
		
		// click the 'Next' button again
		clickButtonWithText("Next");
		assertNoProblem();
		
		// the values haven't changed
		assertLabelTextExactlyPresent("New Event 124");
		assertLabelTextExactlyPresent("This event is great, because it was also loaded through RSS!");
		assertLabelTextExactlyPresent("Tue, 03 Jun 2008 09:39:21 GMT");
		
		// click 'Reset'
		clickButtonWithText("First");
		assertNoProblem();
		
		assertLabelTextExactlyPresent("New Event 123");
		assertLabelTextExactlyPresent("This event is great, because it was loaded through RSS!");
		assertLabelTextExactlyPresent("Tue, 03 Jun 2008 09:39:21 GMT");

	}
	
}