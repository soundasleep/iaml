/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;

import net.sourceforge.jwebunit.api.IElement;

/**
 * 		
 *
 */
public class MapPointUpdateExplicitly extends MapsCodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(MapPointUpdateExplicitly.class);
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
	 * The home page has a map, but it doesn't have a point
	 * on it.
	 * 
	 * @throws Exception
	 */
	public void testHasMap() throws Exception {

		beginAtSitemapThenPage("Home");
		
		IElement map = assertHasMap("containing map");
		
		// point hasn't been set yet
		assertHasNoMapPoint(map, "target point");
		assertNoProblem();

	}

	/**
	 * We change the address, but don't call submit.
	 * 
	 * @throws Exception
	 */
	public void testSettingMapAddress() throws Exception {
		
		beginAtSitemapThenPage("Home");
		
		IElement map = assertHasMap("containing map");
		
		{
			String target = getLabelIDForText("address");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Wellington, New Zealand");
		}
		
		// nothing has changed yet
		assertHasNoMapPoint(map, "target point");
		assertNoProblem();

	}

	/**
	 * We change the address, and call submit. A new map point should
	 * be created in the map.
	 * 
	 * @throws Exception
	 */
	public void testSettingMapAddressSubmit() throws Exception {
		
		beginAtSitemapThenPage("Home");
		
		IElement map = assertHasMap("containing map");
		
		{
			String target = getLabelIDForText("address");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Wellington, New Zealand");
		}
		
		// click button
		assertButtonPresentWithText("update address");
		clickButtonWithText("update address");
		assertNoProblem();

		// if using google maps, we can't go any further
		if (!doCheckPoints()) return;

		// we now have a map point
		IElement point = assertHasMapPoint(map, "target point");
		
		// TODO how could we check that a given point is actually
		// a valid address?
		assertNotNull(point);
		
	}
	
	/**
	 * We change the address and show a map point, but then we reset it
	 * back to empty; the map point should vanish.
	 * 
	 * @throws Exception
	 */
	public void testSetThenUndo() throws Exception {
		
		beginAtSitemapThenPage("Home");
		
		IElement map = assertHasMap("containing map");
		
		{
			String target = getLabelIDForText("address");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Wellington, New Zealand");
		}
		
		// click button
		assertButtonPresentWithText("update address");
		clickButtonWithText("update address");
		assertNoProblem();
		
		// if using google maps, we can't go any further
		if (!doCheckPoints()) return;

		// we now have a map point
		assertHasMapPoint(map, "target point");

		// now set it back to empty
		
		{
			String target = getLabelIDForText("address");
			setLabeledFormElementField(target, "");
		}
		
		// click button
		assertButtonPresentWithText("update address");
		clickButtonWithText("update address");
		
		// we must now NOT have a point
		assertHasNoMapPoint(map, "target point");
		assertNoProblem();
		
	}

	/**
	 * Because the Google Maps Javascript has syntax errors, we can't apply XPath
	 * over the resulting tree, and thus we can't get //labels for points.
	 * As a result, we can no longer use {@link #assertHasMapPoint(IElement, String)}
	 * if we are using Google Maps.
	 * 
	 * <p>This method returns false if we should not try and check for map points
	 * due to this problem; by default, it returns true.
	 * 
	 * @return true by default
	 */
	protected boolean doCheckPoints() {
		return true;
	}
	
}
