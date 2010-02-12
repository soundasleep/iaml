/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_3;

import junit.framework.AssertionFailedError;
import net.sourceforge.jwebunit.api.IElement;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Demonstrates a static label which does not change.
 * 
 * @author jmwright
 */
public class LabelSyncWire extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(LabelSyncWire.class);
	}
	
	/**
	 * The home page can be accessed.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// the text "initial value" should be on the page (three times)
		assertMatch("initial");
		
	}
	
	/**
	 * If we change the text field, the label is changed too.
	 * 
	 * @throws Exception
	 */
	public void testChangeField() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Home");
		
		String value = "hello, world!";
		// first, this value should not be on the page
		assertNoMatch(value);
		
		{
			String target = getLabelIDForText("Text Field");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, value);
		}
		
		// it should reflect on the page
		assertMatch(value);
		
		// in fact, it should be contained within a label
		{
			IElement match = getElementByXPath("//label[" + getContainsTextXPath(value) + "]");
			assertNotNull(match);
			assertEquals(value, match.getTextContent());
		}
		
		// if we reload the page, the value is still there
		reloadPage(sitemap, "Home");
		
		// it should reflect on the page
		try {
			assertMatch(value);
			
			// in fact, it should be contained within a label
			{
				IElement match = getElementByXPath("//label[" + getContainsTextXPath(value) + "]");
				assertNotNull(match);
				assertEquals(value, match.getTextContent());
			}
		} catch (AssertionFailedError e) {
			System.err.println(getPageSource());
			throw e;
		}
		
	}
	
}
