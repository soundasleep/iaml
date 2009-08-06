/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests sessions: sync wires across session boundaries, with multiple
 * pages involved.
 * 
 * page1 <--> page2 <-- [ --> page3 <--> page4 <--> page5 ]
 * 
 * @author jmwright
 *
 */
public class SessionSyncWiresMultiple extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SessionSyncWiresMultiple.class);
	}
	
	public void testRequirement() throws Exception {
		// go to sitemap
		IFile sitemap = beginAtSitemapThenPage("outside1");
		
		String outside = "outside " + new Date().toString();
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, outside);
		}
		
		// outside sync should have changed
		gotoSitemapThenPage(sitemap, "outside2");		
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, outside);
		}
		
		// go to session
		gotoSitemapThenPage(sitemap, "inside1");
		{
			// should have changed
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, outside);
		}

		gotoSitemapThenPage(sitemap, "inside3");
		{
			// should have changed
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, outside);
		}

		// change a value in the session
		gotoSitemapThenPage(sitemap, "inside2");
		String inside = "an inside change " + new Date().toString();
		{
			// should have changed
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, outside);
			setLabeledFormElementField(target, inside);
		}
		
		// should have changed outside
		gotoSitemapThenPage(sitemap, "outside1");
		{
			// should have changed
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, inside);
		}

		// should have changed inside
		gotoSitemapThenPage(sitemap, "inside3");
		{
			// should have changed
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, inside);
		}

		// *restart* entire session
		restartSession(sitemap, "outside1");
		{
			// should not have been lost
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, inside);
		}

		// *restart* entire session, but go to session page
		restartSession(sitemap, "inside2");
		{
			// should be resurrected
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, inside);
		}
		
		// change a value outside
		gotoSitemapThenPage(sitemap, "outside1");
		String outside2 = "a final outside value change " + new Date().toString();
		{
			// should have changed
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, inside);
			setLabeledFormElementField(target, outside2);
		}
		
		// should have changed inside
		gotoSitemapThenPage(sitemap, "inside3");
		{
			// should have changed
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, outside2);
		}
		
	}

}
