/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * SetWires connecting to SyncWires operating over multiple pages,
 * both client and server.
 * 
 * @example SetWire,SyncWire
 * 		Chaining {@model SetWire SetWires} and {@model SyncWire SyncWires}
 * 		together across multiple {@model Frame Frames}.
 * 
 * @author jmwright
 *
 */
public class SetWireSyncChained extends CodegenTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SetWireSyncChained.class);
	}
	
	/**
	 * Check the initial state of the application.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("page3");

		// page 3
		String changed = getLabelIDForText("changed"); // sync
		assertLabeledFieldEquals(changed, "");

		// page 2
		gotoSitemapThenPage(sitemap, "page2");
		String target = getLabelIDForText("target"); //set
		assertLabeledFieldEquals(target, "");
		
		// Home
		gotoSitemapThenPage(sitemap, "Home");
		String source = getLabelIDForText("source");
		assertLabeledFieldEquals(source, "");
	}
	
	/**
	 * Set a value and check each page in succession.
	 * 
	 * @throws Exception
	 */
	public void testSuccessive() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("Home");

		String test = "test1 " + new Date();
		String source = getLabelIDForText("source");
		assertLabeledFieldEquals(source, "");
		setLabeledFormElementField(source, test);
		
		// page 2
		gotoSitemapThenPage(sitemap, "page2");
		String target = getLabelIDForText("target"); //set
		assertLabeledFieldEquals(target, test);

		// page 3
		gotoSitemapThenPage(sitemap, "page3");
		String changed = getLabelIDForText("changed"); // sync
		assertLabeledFieldEquals(changed, test);
	}
			
	/**
	 * Set a value and check the pages randomly.
	 * 
	 * @throws Exception
	 */
	public void testRandomly() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("Home");

		String test = "test1 " + new Date();
		String source = getLabelIDForText("source");
		assertLabeledFieldEquals(source, "");
		setLabeledFormElementField(source, test);

		// page 3
		gotoSitemapThenPage(sitemap, "page3");
		String changed = getLabelIDForText("changed"); // sync
		assertLabeledFieldEquals(changed, test);

		// page 2
		gotoSitemapThenPage(sitemap, "page2");
		String target = getLabelIDForText("target"); //set
		assertLabeledFieldEquals(target, test);
	}
	
	/**
	 * Setting only the SyncWire does not change the SetWire.
	 * 
	 * @throws Exception
	 */
	public void testSyncNotSet() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("page3");

		String test = "test1 " + new Date();
		String changed = getLabelIDForText("changed"); // sync
		assertLabeledFieldEquals(changed, "");
		setLabeledFormElementField(changed, test);

		// page 2
		gotoSitemapThenPage(sitemap, "page2");
		String target = getLabelIDForText("target"); //set
		assertLabeledFieldEquals(target, test);
		
		// Home
		gotoSitemapThenPage(sitemap, "Home");
		String source = getLabelIDForText("source");
		assertLabeledFieldEquals(source, "");

	}
	
}
