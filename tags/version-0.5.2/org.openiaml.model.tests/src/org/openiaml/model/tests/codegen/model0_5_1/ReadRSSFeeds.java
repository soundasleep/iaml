/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openiaml.model.tests.ModelTestCase;
import org.openiaml.model.tests.codegen.model0_5_1.RSS2_0Reader.RSSReaderException;

/**
 * A test case to check that we can read RSS feeds fine using our 
 * test framework.
 * 
 * <p>This loads an external URL and checks that it can be read. The sample
 * RSS feed is also provided in this same folder.
 */
public class ReadRSSFeeds extends ModelTestCase {
	
	public void testDownloadRSSFeed() throws RSSReaderException, ParseException {
		
		RSS2_0Reader reader = new RSS2_0Reader("http://openiaml.org/publications/rss_sample.xml");
		reader.assertTitle("sample new events RSS feed");
		reader.assertDescription("A sample event feed");
		
		// parse the date string into the expected date
		Date date = new SimpleDateFormat(RSS2_0Reader.RSS_DATE_FORMAT).parse("Tue, 10 Jun 2008 09:41:01 GMT");
		
		reader.assertLastBuildDate(date);
		reader.assertGenerator("textpad");
		
	}

}
