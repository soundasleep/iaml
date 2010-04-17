/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_3;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * SyncWires on labels which have conditions.
 * 
 * @author jmwright
 */
public class LabelSyncCondition extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(LabelSyncCondition.class);
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
	 * By default, we should be able to sync.
	 * 
	 * @throws Exception
	 */
	public void testSyncDefault() throws Exception {
		
		String value = "Hello, world!";
		
		beginAtSitemapThenPage("Home");
		assertLabelTextNotPresent(value);
		
		{
			String target = getLabelIDForText("Field");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, value);
		}
		
		// by default, the condition is enabled (fieldValue.default = true)
		assertLabelTextPresent(value);
		
	}
	
	/**
	 * If we change 'Allow Sync' to false, we prevent the label from syncing.
	 * 
	 * @throws Exception
	 */
	public void testSyncDisabled() throws Exception {
		
		String value = "Hello, world!";
		
		beginAtSitemapThenPage("Home");
		assertLabelTextNotPresent(value);
		
		// disable sync
		{
			String target = getLabelIDForText("Allow Sync?");
			assertLabeledFieldEquals(target, "true");
			setLabeledFormElementField(target, "false");
		}
		
		{
			String target = getLabelIDForText("Field");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, value);
		}
		
		// the label has not changed
		assertLabelTextNotPresent(value);
		
	}
	
	
}
