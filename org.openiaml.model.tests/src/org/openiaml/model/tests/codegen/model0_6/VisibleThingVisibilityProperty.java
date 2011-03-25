/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_6;

import net.sourceforge.jwebunit.api.IElement;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.codegen.model0_5.MapsCodegenTestCase;

/**
 * Issue 223: Add 'visibility' boolean property to {@model VisibleThing}s.
 * 
 * These tests check that the property is accessible as a normal property.
 * 
 */
public class VisibleThingVisibilityProperty extends MapsCodegenTestCase {

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
		
		// there should be a button that is visible
		assertTargetVisible(true);
		
		// a button to set visibility
		assertButtonPresentWithText("set visibility");
		
		// and a text field to set the value
		{
			String id = getLabelIDForText("visibility value");
			assertLabeledFieldEquals(id, "");
		}
		
	}

	private void assertTargetVisible(boolean b) {
		IElement button = getButtonWithText("target");
		assertEquals("Button should be " + (b ? "visible" : "hidden"), b, isDisplayed(button));
	}

	/**
	 * Set the visibility to true.
	 * 
	 * @throws Exception 
	 */
	public void testSetVisibleTrue() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		assertTargetVisible(true);
		assertButtonPresentWithText("set visibility");
		
		// and a text field to set the value
		{
			String id = getLabelIDForText("visibility value");
			assertLabeledFieldEquals(id, "");
			setLabeledFormElementField(id, "true");
		}
		
		// click the button
		clickButtonWithText("set visibility");
		
		// the target is still visible
		assertTargetVisible(true);
		
	}

	/**
	 * Set the visibility to false.
	 * 
	 * @throws Exception 
	 */
	public void testSetVisibleFalse() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		assertTargetVisible(true);
		assertButtonPresentWithText("set visibility");
		
		// and a text field to set the value
		{
			String id = getLabelIDForText("visibility value");
			assertLabeledFieldEquals(id, "");
			setLabeledFormElementField(id, "false");
		}
		
		clickButtonWithText("set visibility");
		assertTargetVisible(false);
		
	}
	
	/**
	 * Set the visibility to false, then true.
	 * 
	 * @throws Exception 
	 */
	public void testSetVisibleFalseThenTrue() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		assertTargetVisible(true);
		assertButtonPresentWithText("set visibility");
		
		// and a text field to set the value
		{
			String id = getLabelIDForText("visibility value");
			assertLabeledFieldEquals(id, "");
			setLabeledFormElementField(id, "false");
		}

		clickButtonWithText("set visibility");
		assertTargetVisible(false);
		
		{
			String id = getLabelIDForText("visibility value");
			assertLabeledFieldEquals(id, "false");
			setLabeledFormElementField(id, "true");
		}

		clickButtonWithText("set visibility");
		assertTargetVisible(true);

	}
	
	/**
	 * Set the visibility to true, then false.
	 * 
	 * @throws Exception 
	 */
	public void testSetVisibleTrueThenFalse() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		assertTargetVisible(true);
		assertButtonPresentWithText("set visibility");
		
		// and a text field to set the value
		{
			String id = getLabelIDForText("visibility value");
			assertLabeledFieldEquals(id, "");
			setLabeledFormElementField(id, "true");
		}

		clickButtonWithText("set visibility");
		assertTargetVisible(true);
		
		{
			String id = getLabelIDForText("visibility value");
			assertLabeledFieldEquals(id, "true");
			setLabeledFormElementField(id, "false");
		}

		clickButtonWithText("set visibility");
		assertTargetVisible(false);

	}
	
	/**
	 * Check that visibility persists across reloads (since we aren't
	 * in a session).
	 * 
	 * @throws Exception 
	 */
	public void testSetVisibleFalsePersists() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		assertTargetVisible(true);
		assertButtonPresentWithText("set visibility");
		
		// and a text field to set the value
		{
			String id = getLabelIDForText("visibility value");
			assertLabeledFieldEquals(id, "");
			setLabeledFormElementField(id, "false");
		}
		
		clickButtonWithText("set visibility");
		assertTargetVisible(false);
		
		// reload the page
		reloadPage(sitemap, "Home");
		
		// still hidden
		assertTargetVisible(false);
		
	}
	
}
