/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_2;

import java.io.IOException;
import java.util.List;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;

import org.openiaml.model.tests.CodegenTestCase;

import com.gargoylesoftware.htmlunit.html.HtmlElement;

/**
 * Tests SyncWires with executeOnInput = true. Also tests 
 * InputTextField.onInput.
 */
public class SyncWireInstant extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SyncWireInstant.class);
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
	 * We can update the 'normal' text fields as normal.
	 * 
	 * @throws Exception
	 */
	public void testInputNormal() throws Exception {
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForText("normal 1");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "new value");
		}
		
		// updates as normal
		{
			String target = getLabelIDForText("normal 2");
			assertLabeledFieldEquals(target, "new value");
			setLabeledFormElementField(target, "another value");
		}

		// updates as normal
		{
			String target = getLabelIDForText("normal 1");
			assertLabeledFieldEquals(target, "another value");
		}

		// doesn't update other labels
		{
			String target = getLabelIDForText("instant 2");
			assertLabeledFieldEquals(target, "");
		}
		{
			String target = getLabelIDForText("mixed 2");
			assertLabeledFieldEquals(target, "");
		}

		assertNoProblem();
	}
	
	/**
	 * We can update the 'instant' text fields as normal.
	 * 
	 * @throws Exception
	 */
	public void testInputInstant() throws Exception {
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForText("instant 1");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "new value");
		}
		
		// updates as normal
		{
			String target = getLabelIDForText("instant 2");
			assertLabeledFieldEquals(target, "new value");
			setLabeledFormElementField(target, "another value");
		}

		// updates as normal
		{
			String target = getLabelIDForText("instant 1");
			assertLabeledFieldEquals(target, "another value");
		}

		// doesn't update other labels
		{
			String target = getLabelIDForText("normal 2");
			assertLabeledFieldEquals(target, "");
		}
		{
			String target = getLabelIDForText("mixed 2");
			assertLabeledFieldEquals(target, "");
		}

		assertNoProblem();
	}
	
	/**
	 * As we type in characters into instant1, we will see
	 * the results in instant1.
	 * 
	 * @throws Exception
	 */
	public void testInstantInput() throws Exception {
		beginAtSitemapThenPage("Home");
		
		// currently empty
		{
			String target = getLabelIDForText("instant 1");
			assertLabeledFieldEquals(target, "");
			
			// type in a string
			typeLabeledFormElement(target, "new value");
		}
		
		// instant2 is updated already
		{
			String target = getLabelIDForText("instant 2");
			assertLabeledFieldEquals(target, "new value");
		}
		
		// type in some more into instant1
		{
			String target = getLabelIDForText("instant 1");
			assertLabeledFieldEquals(target, "new value");
			
			// type in a string
			typeLabeledFormElement(target, " and more");
		}
		
		// instant2 is updated already
		{
			String target = getLabelIDForText("instant 2");
			assertLabeledFieldEquals(target, "new value and more");
		}

		assertNoProblem();
	}
	
	/**
	 * If we type characters into normal1, they are not automatically
	 * updated into normal2, until we lose focus.
	 * 
	 * @throws Exception
	 */
	public void testInstantInputOnNormal() throws Exception {
		beginAtSitemapThenPage("Home");
		
		// currently empty
		{
			String target = getLabelIDForText("normal 1");
			assertLabeledFieldEquals(target, "");
			
			// type in a string
			typeLabeledFormElement(target, "new value");
			
			// is set
			assertLabeledFieldEquals(target, "new value");
		}
		
		// normal2 is not updated
		{
			String target = getLabelIDForText("normal 2");
			assertLabeledFieldEquals(target, "");
		}
		
		// until we lose focus on normal1
		{
			String target = getLabelIDForText("normal 1");
			
			// still set
			assertLabeledFieldEquals(target, "new value");
			
			// remove focus
			loseFocus(target);
		}
		
		// normal2 is now updated
		{
			String target = getLabelIDForText("normal 2");
			assertLabeledFieldEquals(target, "new value");
		}

		assertNoProblem();
	}

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
			System.out.println(element);
			
			// type it into this label
			assertTrue("Expected HtmlUnitElementImpl, but was " + element.getClass().getName(),
					element instanceof HtmlUnitElementImpl);

			// get the actual HtmlUnit element
			HtmlElement actual = ((HtmlUnitElementImpl) element).getHtmlElement();
			assertNotNull(actual);
			
			// type in the text
			actual.type(text);
			
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
