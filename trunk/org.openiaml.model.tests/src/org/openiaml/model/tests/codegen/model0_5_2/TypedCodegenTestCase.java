/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_2;

import java.io.IOException;
import java.util.List;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitTestingEngineImpl;

import org.openiaml.model.tests.CodegenTestCase;

import com.gargoylesoftware.htmlunit.html.HtmlElement;

/**
 * Extends {@link CodegenTestCase} to provide additional methods
 * for typing in, and waiting for, typed input elements.
 */
public abstract class TypedCodegenTestCase extends CodegenTestCase {

	/**
	 * Type in the given text to the Input element labelled by the given element ID.
	 * 
	 * @throws IOException 
	 */
	public void typeLabeledFormElement(String id, String text) throws IOException {
		// get the elements referenced by this label
		IElement label = getElementById(id);
		assertNotNull(label);
		
		List<IElement> elements = getFieldsForLabel(label);
		assertFalse("Could not find any elements for label " + id, elements.isEmpty());
		
		for (IElement element : elements) {
			// type it into this label
			assertTrue("Expected HtmlUnitElementImpl, but was " + element.getClass().getName(),
					element instanceof HtmlUnitElementImpl);

			// get the actual HtmlUnit element
			HtmlElement actual = ((HtmlUnitElementImpl) element).getHtmlElement();
			assertNotNull(actual);
			
			// type in the text
			actual.type(text);
			
			// wait until all background tasks (setTimeout, ...) are finished
			while (((HtmlUnitTestingEngineImpl) getTestingEngine()).getWebClient().waitForBackgroundJavaScript(500) != 0);
			
			// and return
			return;
		}	
	}
	
	/**
	 * blur() on the Input element labelled by the given element ID.
	 * 
	 * @param id
	 */
	public void loseFocus(String id) {
		// get the elements referenced by this label
		IElement label = getElementById(id);
		assertNotNull(label);
		
		List<IElement> elements = getFieldsForLabel(label);
		assertFalse("Could not find any elements for label " + id, elements.isEmpty());
		
		for (IElement element : elements) {
			// type it into this label
			assertTrue("Expected HtmlUnitElementImpl, but was " + element.getClass().getName(),
					element instanceof HtmlUnitElementImpl);

			// get the actual HtmlUnit element
			HtmlElement actual = ((HtmlUnitElementImpl) element).getHtmlElement();
			assertNotNull(actual);
			
			// lose focus
			actual.blur();
			
			// and return
			return;
		}	
		
	}
	
}
