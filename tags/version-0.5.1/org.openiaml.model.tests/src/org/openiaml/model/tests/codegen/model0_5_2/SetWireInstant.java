/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_2;


/**
 * Tests SetWires with executeOnInput = true.
 */
public class SetWireInstant extends TypedCodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SetWireInstant.class);
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
	 * Normal SetWire execution: sets after focus is lost.
	 * 
	 * @throws Exception
	 */
	public void testNormal() throws Exception {
		beginAtSitemapThenPage("Home");
		
		// label not present
		assertLabelTextNotPresent("new value");
		
		{
			String target = getLabelIDForText("input");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "new value");
		}
		
		// label is now present
		assertLabelTextExactlyPresent("new value");
		assertNoProblem();
	}
	
	/**
	 * Instant SetWire execution: sets immediately
	 * 
	 * @throws Exception
	 */
	public void testInstant() throws Exception {
		beginAtSitemapThenPage("Home");
		
		// label not present
		assertLabelTextNotPresent("new value");
		
		{
			String target = getLabelIDForText("input");
			assertLabeledFieldEquals(target, "");
			
			typeLabeledFormElement(target, "new value");			
		}
		
		// label is now present
		assertLabelTextExactlyPresent("new value");
		assertNoProblem();
	}
	

}
