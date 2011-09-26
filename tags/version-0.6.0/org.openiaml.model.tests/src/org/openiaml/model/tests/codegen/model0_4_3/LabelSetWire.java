/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_3;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Labels can also be connected with SetWires.
 * 
 * @author jmwright
 */
public class LabelSetWire extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(LabelSetWire.class);
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
	 * Because the set wire is one way, if we change the text field,
	 * the label will not change. 
	 * 
	 * @throws Exception
	 */
	public void testChangeField() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Home");
		
		// the label
		assertLabelTextPresent("hello, world!");
		
		String value = "goodbye, world!";
		// first, this value should not be on the page
		assertNoMatch(value);
		assertLabelTextNotPresent(value);
		
		{
			String target = getLabelIDForText("Target Text Field");
			assertLabeledFieldEquals(target, "hello, world!");
			setLabeledFormElementField(target, value);
		}
		
		// the label has not changed
		assertLabelTextPresent("hello, world!");
		assertLabelTextNotPresent(value);
		
		// even if we reload the page
		reloadPage(sitemap, "Home");
		
		// the label has not changed
		assertLabelTextPresent("hello, world!");
		assertLabelTextNotPresent(value);
		
		{
			// Label --> set --> InputTextField;
			// when the page is reloaded, the Label will refresh the InputTextField
			// with the Label value
			String target = getLabelIDForText("Target Text Field");
			assertLabeledFieldEquals(target, "hello, world!");
		}
		
	}
	
}
