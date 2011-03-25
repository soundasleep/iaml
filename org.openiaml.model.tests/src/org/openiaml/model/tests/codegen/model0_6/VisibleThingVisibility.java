/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_6;

import java.util.List;

import net.sourceforge.jwebunit.api.IElement;

import org.openiaml.model.tests.codegen.model0_5.MapsCodegenTestCase;

/**
 * Issue 223: Add 'visibility' boolean property to {@model VisibleThing}s
 * 
 */
public class VisibleThingVisibility extends MapsCodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(getClass());
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
	 * Check the visibility of {@model InputForm}s.
	 * 
	 * @throws Exception
	 */
	public void testFormVisible() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// one form is visible
		hasInputFormForText("Form visible");
		
		// another field is not
		List<IElement> list = hasInputFormForText("Form hidden");
		assertEquals(1, list.size());
		IElement form = list.get(0);
		assertFalse("Form is hidden", isDisplayed(form));
		
	}
	
	/**
	 * Check the visibility of {@model InputTextField}s.
	 * 
	 * @throws Exception
	 */
	public void testTextVisibility() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// one field is visible
		{
			IElement text = getFieldForLabel("Text visible");
			assertTrue("Text is hidden", isDisplayed(text));
		}

		// another field is not
		{
			IElement text = getFieldForLabel("Text hidden");
			assertFalse("Text is displayed", isDisplayed(text));
		}
	}
	
	/**
	 * Check the visibility of {@model Button}s.
	 * 
	 * @throws Exception
	 */
	public void testButtonVisibility() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// one field is visible
		{
			IElement button = getButtonWithText("Button visible");
			assertTrue("Button is hidden", isDisplayed(button));
		}
		
		// another field is not
		{
			IElement button = getButtonWithText("Button hidden");
			assertFalse("Button is displayed", isDisplayed(button));
		}
	}
	
	/**
	 * Check the visibility of {@model Label}s.
	 * 
	 * @throws Exception
	 */
	public void testLabelVisibility() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// one label is visible
		assertLabelTextExactlyPresent("Label visible");
		
		// another label is not
		assertLabelTextNotPresent("Label hidden");
	}
	
	/**
	 * Check the visibility of {@model Map}s.
	 * 
	 * @throws Exception
	 */
	public void testMapVisibility() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		{
			IElement map = assertHasMap("Map visible");
			assertTrue("Map is hidden", isDisplayed(map));
		}
		
		{
			IElement map = assertHasMap("Map hidden");
			assertFalse("Map is displayed", isDisplayed(map));
		}
	}
	
}
