/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_3;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests sessions: sync wires across session boundaries.
 * 
 * @author jmwright
 *
 */
public class SessionSyncWires extends CodegenTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SessionSyncWires.class);
	}
	
	public void testRequirement() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "outside");
		
		String outside = "outside " + new Date().toString();
		{
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, outside);
		}
		
		// go to session
		gotoSitemapThenPage(sitemap, "inside");
		
		String inside = "inside " + new Date().toString();
		{
			// should have changed
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, outside);
			setLabeledFormElementField(target, inside);
		}
		
		// reload page
		gotoSitemapThenPage(sitemap, "outside");
		{
			// should have changed
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, inside);
		}

		// *restart* entire session
		restartSession(sitemap, "outside");
		{
			// should be the same
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, inside);
		}

		// re-enter session
		gotoSitemapThenPage(sitemap, "inside");
		{
			// should have been lost
			String target = getLabelIDForText("target");
			assertLabeledFieldEquals(target, inside);	// should still be sync'd to outside
		}	
		
	}

}
