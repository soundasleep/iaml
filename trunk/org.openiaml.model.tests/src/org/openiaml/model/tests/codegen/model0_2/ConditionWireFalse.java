/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_2;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * A simple ConditionWire test case, which is a condition that is always false. 
 * 
 * @author jmwright
 *
 */
public class ConditionWireFalse extends CodegenTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(ROOT + "codegen/model0_2/ConditionWireFalse.iaml");
	}
	
	public void testFalseCondition() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "container");
		
		String testString = "value " + new Date().toString();
		{
			String sourceId = getLabelIDForText("source");
			assertNotNull(sourceId);
			assertLabeledFieldNotEquals(sourceId, testString);
			setLabeledFormElementField(sourceId, testString);
			
			// when we set the field, the SyncWire on the page
			// should stop the Sync'ing from happening.
			
			String targetId = getLabelIDForText("target");
			assertNotNull(targetId);
			assertLabeledFieldNotEquals(targetId, testString);
		}
		
		// and also backwards
		String testString2 = "a second value " + new Date().toString();
		{
			String targetId = getLabelIDForText("target");
			assertNotNull(targetId);
			assertLabeledFieldNotEquals(targetId, testString2);
			setLabeledFormElementField(targetId, testString2);
			
			// when we set the field, the SyncWire on the page
			// should stop the Sync'ing from happening.
			
			String sourceId = getLabelIDForText("source");
			assertNotNull(sourceId);
			assertLabeledFieldNotEquals(sourceId, testString2);
			// it should still be the first text
			assertLabeledFieldEquals(sourceId, testString);
		}
		
	}

}
