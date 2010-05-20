/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_2;

import java.io.IOException;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;


/**
 * Tests that we can show and hide IteratorLists.
 * 
 */
public class IteratorListSetClickResults extends IteratorListSetWire {

	/**
	 * If we click a result's title, we can obtain the title in the text field.
	 * 
	 * @throws Exception
	 */
	public void testClickResults() throws Exception {
		// access the home page, so we have 1, 2 and 3
		testHome();
		
		// the label starts out empty
		{
			String target = getLabelIDForText("clicked title");
			assertLabeledFieldEquals(target, "");
		}
		
		// click 'Title 2'
		{
			IElement label = assertLabelTextExactlyPresent("Title 2");
			clickElement(label);
		}
		assertNoProblem();
		
		// the text field has been refreshed
		{
			String target = getLabelIDForText("clicked title");
			assertLabeledFieldEquals(target, "Title 2");
		}
		
		// click 'Title 3'
		{
			IElement label = assertLabelTextExactlyPresent("Title 3");
			clickElement(label);
		}
		assertNoProblem();
		
		// the text field has been refreshed
		{
			String target = getLabelIDForText("clicked title");
			assertLabeledFieldEquals(target, "Title 3");
		}
		
		// click 'Title 1'
		{
			IElement label = assertLabelTextExactlyPresent("Title 1");
			clickElement(label);
		}
		assertNoProblem();
		
		// the text field has been refreshed
		{
			String target = getLabelIDForText("clicked title");
			assertLabeledFieldEquals(target, "Title 1");
		}
		
		// click 'Title 1'
		{
			IElement label = assertLabelTextExactlyPresent("Title 1");
			clickElement(label);
		}
		assertNoProblem();
		
		// the text field has been refreshed
		{
			String target = getLabelIDForText("clicked title");
			assertLabeledFieldEquals(target, "Title 1");
		}
	}
	
	/**
	 * Click the given Element. Assumed to be an HtmlUnitElementImpl.
	 * 
	 * @param label
	 * @throws IOException 
	 */
	private void clickElement(IElement label) throws IOException {
		((HtmlUnitElementImpl) label).getHtmlElement().click(); 
	}

}
