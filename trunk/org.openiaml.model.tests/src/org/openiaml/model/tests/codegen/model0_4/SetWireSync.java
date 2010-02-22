/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.util.Date;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * SetWires connecting to SyncWires.
 * 
 * @implementation SetWire 
 * 		If a {@model SetWire} is chained with a {@model SyncWire},
 * 		changes from the SetWire source will update the target of the 
 * 		SyncWire.
 * 
 * @author jmwright
 *
 */
public class SetWireSync extends CodegenTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SetWireSync.class);
	}
	
	/**
	 * Check the initial state of the application.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		beginAtSitemapThenPage("Home");
		
		String source = getLabelIDForText("source");
		assertLabeledFieldEquals(source, "");
	
		String sync1 = getLabelIDForText("sync1");
		assertLabeledFieldEquals(sync1, "");

		String sync2 = getLabelIDForText("sync2");
		assertLabeledFieldEquals(sync2, "");
	}
	
	/**
	 * Set 'source' to a value should set the other fields.
	 * 
	 * @throws Exception
	 */
	public void testSetSync() throws Exception {
		beginAtSitemapThenPage("Home");

		String test = "test1 " + new Date();
		String source = getLabelIDForText("source");
		assertLabeledFieldEquals(source, "");
		setLabeledFormElementField(source, test);
	
		String sync1 = getLabelIDForText("sync1");
		assertLabeledFieldEquals(sync1, test);

		String sync2 = getLabelIDForText("sync2");
		assertLabeledFieldEquals(sync2, test);
	}
	
	/**
	 * Set 'sync1' to a value should not set the 'source'
	 * field, but set 'sync2' (since they're connected with
	 * SyncWires)
	 * 
	 * @throws Exception
	 */
	public void testSyncNoSet() throws Exception {
		beginAtSitemapThenPage("Home");

		String sync1 = getLabelIDForText("sync1");
		assertLabeledFieldEquals(sync1, "");
		String test = "test1 " + new Date();
		setLabeledFormElementField(sync1, test);
	
		// should have changed (SyncWire)
		String sync2 = getLabelIDForText("sync2");
		assertLabeledFieldEquals(sync2, test);

		// should not have changed
		String source = getLabelIDForText("source");
		assertLabeledFieldEquals(source, "");

	}
		
	
}
