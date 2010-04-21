/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;
import org.openiaml.model.tests.codegen.model0_5_1.RSS2_0Reader.RSSReaderException;
import org.openiaml.model.tests.release.PluginsTestCase;

/**
 * Various helper methods for reading feeds.
 * 
 * @author jmwright
 *
 */
public abstract class FeedCodegenTestCase extends DatabaseCodegenTestCase {
	
	/**
	 * Assert that the given date is either today, or yesterday, but
	 * not after the current time.
	 * 
	 * @param date
	 */
	protected void assertRecent(Date date) {
		Date now = new Date();
		long max = now.getTime();
		long min = max - (86400l * 1000l);	// one day before
		assertTrue("Date '" + date + "' was not recently after '" + now + "'", date.getTime() > min);
		assertTrue("Date '" + date + "' was not recently before '" + now + "'", date.getTime() <= max);
	}
	
	/**
	 * Assert that an RSS feed exists at the given relative URL, and has
	 * the given name. Also assert that the feed has been created
	 * recently. Returns the found reader.
	 * @throws RSSReaderException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	public RSS2_0Reader getFeedReader(String url, String name) throws RSSReaderException, FileNotFoundException, IOException {
		RSS2_0Reader reader = new RSS2_0Reader(getTestContext().getBaseUrl().toString() + url);
		reader.assertTitle(name);
		assertRecent(reader.getLastBuildDate());
		reader.assertGenerator("Internet Application Modelling Language " + PluginsTestCase.getVersion());
		
		reader.assertLink(BASE_URL + getProject().getProjectName() + "/output");
		reader.assertDocs("http://blogs.law.harvard.edu/tech/rss");
		
		return reader;
	}

	/**
	 * Construct a Date of the given format (times assumed to be in UTC). 
	 * 
	 * @param month 0-based month, e.g. 1 = February.
	 * @return the Date representing the given date
	 */
	protected Date createUTCDate(int year, int month, int day, int hour, int minute, int second) {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		c.clear();
		c.set(year, month, day, hour, minute, second);		
		return c.getTime();		
	}

}
