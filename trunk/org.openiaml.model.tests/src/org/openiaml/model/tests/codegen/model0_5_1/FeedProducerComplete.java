/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.PhpRuntimeExceptionException;
import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;
import org.openiaml.model.tests.release.PluginsTestCase;

/**
 * @example FeedProducer
 *		A complete example of using {@model FeedProducer} to provide an RSS
 *		feed from a {@model DomainObjectInstance}. 
 */
public class FeedProducerComplete extends DatabaseCodegenTestCase {

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
		
		// get the 'target feed' link
		String url = getURLOfLink("Target Feed");
		assertNotNull(url);
		
		RSS2_0Reader reader = new RSS2_0Reader(getTestContext().getBaseUrl().toString() + url);
		reader.assertTitle("Target Feed");
		assertRecent(reader.getLastBuildDate());
		reader.assertGenerator("Internet Application Modelling Language " + PluginsTestCase.getVersion());
		
		fail("Method is incomplete");
	}
	
	/**
	 * Assert that the given date is either today, or yesterday, but
	 * not after the current time.
	 * 
	 * @param date
	 */
	private void assertRecent(Date date) {
		Date now = new Date();
		long max = now.getTime();
		long min = max - (86400l * 1000l);	// one day before
		assertTrue("Date '" + date + "' was not recently after '" + now + "'", date.getTime() > min);
		assertTrue("Date '" + date + "' was not recently before '" + now + "'", date.getTime() <= max);
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
			s.add("INSERT INTO News (id, title, description, updated) VALUES (" + i + ", 'Title " + i + "', 'Description " + i + "', '2010-01-" + i + " 01:00:00 +0000')");
		}
		return s;
	}

	@Override
	protected String getDatabaseName() {
		return "output/model_1280b46d146_77.db";
	}
	
}
