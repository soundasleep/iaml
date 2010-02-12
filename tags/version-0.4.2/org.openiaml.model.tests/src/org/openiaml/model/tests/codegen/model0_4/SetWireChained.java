/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Chaining of SetWires across both client and server.
 * 
 * @author jmwright
 *
 */
public class SetWireChained extends CodegenTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SetWireChained.class);
	}
	
	/**
	 * Check the initial state of the application.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("Home");
		
		String source = getLabelIDForText("source");
		assertLabeledFieldEquals(source, "");
	
		String target = getLabelIDForText("target");
		assertLabeledFieldEquals(target, "");
		
		// go to page 2
		gotoSitemapThenPage(sitemap, "page2");
		
		String source2 = getLabelIDForText("source target");
		assertLabeledFieldEquals(source2, "");
		
		String target2 = getLabelIDForText("on-page target");
		assertLabeledFieldEquals(target2, "");
		
		// go to page 3
		gotoSitemapThenPage(sitemap, "page3");
		
		String target3 = getLabelIDForText("final target");
		assertLabeledFieldEquals(target3, "");
	}
	
	/**
	 * Check the SetWires in successive order.
	 * 
	 * @throws Exception
	 */
	public void testChainedSuccessive() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("Home");
		
		String test1 = "test1 " + new Date();
		String source = getLabelIDForText("source");
		assertLabeledFieldEquals(source, "");
		setLabeledFormElementField(source, test1);
		
		String target = getLabelIDForText("target");
		assertLabeledFieldEquals(target, test1);
		
		// go to page 2
		gotoSitemapThenPage(sitemap, "page2");
		
		String source2 = getLabelIDForText("source target");
		assertLabeledFieldEquals(source2, test1);
		
		String target2 = getLabelIDForText("on-page target");
		assertLabeledFieldEquals(target2, test1);
		
		// go to page 3
		gotoSitemapThenPage(sitemap, "page3");
		
		String target3 = getLabelIDForText("final target");
		assertLabeledFieldEquals(target3, test1);
		
		// go back to page 1
		gotoSitemapThenPage(sitemap, "Home");
		
		String source0 = getLabelIDForText("source");
		assertLabeledFieldEquals(source0, test1);
		
	}
	
	/**
	 * Check the SetWires in a different order.
	 * 
	 * @throws Exception
	 */
	public void testChainedSkip() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("Home");
		
		String test1 = "test1 " + new Date();
		String source = getLabelIDForText("source");
		assertLabeledFieldEquals(source, "");
		setLabeledFormElementField(source, test1);
		
		String target = getLabelIDForText("target");
		assertLabeledFieldEquals(target, test1);
		
		// go to page 3
		gotoSitemapThenPage(sitemap, "page3");
		
		String target3 = getLabelIDForText("final target");
		assertLabeledFieldEquals(target3, test1);		

		// go to page 2
		gotoSitemapThenPage(sitemap, "page2");
		
		String source2 = getLabelIDForText("source target");
		assertLabeledFieldEquals(source2, test1);
		
		String target2 = getLabelIDForText("on-page target");
		assertLabeledFieldEquals(target2, test1);
		
	}
	
	
}
