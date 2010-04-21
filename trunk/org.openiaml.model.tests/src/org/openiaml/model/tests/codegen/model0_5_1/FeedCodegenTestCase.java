/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_1;

import java.util.Date;

import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

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

}
