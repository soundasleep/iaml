/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;

import net.sourceforge.jwebunit.api.IElement;

/**
 * 		
 *
 */
public class MapTextFieldInput extends MapsCodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(MapTextFieldInput.class);
	}

	protected boolean doCheckPoints() {
		return false;
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
	 * 
	 * @implementation Map
	 * 		A {@model Map} that is unitialised will normally display anyway, at
	 * 		any location.
	 * 
	 * @throws Exception
	 */
	public void testHasMap() throws Exception {
		beginAtSitemapThenPage("Home");
		
		IElement map = assertHasMap("Target Map");
		
		// but no map points are generated yet
		assertHasNoMapPoint("Target Map");
		assertHasNoMapPoint(map, "Target Map");
	}
	
	/**
	 * 
	 * @example Map,SetWire
	 * 		A {@model Map} may be updated directly by a {@model InputTextField}
	 * 		through a {@model SetWire}.
	 * 
	 * @throws Exception
	 */
	public void testUpdateMap() throws Exception {
		beginAtSitemapThenPage("Home");
		
		// fill in text field
		{
			String target = getLabelIDForText("select address");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Wellington, New Zealand");
		}
		
		assertNoProblem();
		
		// the map point now exists
		IElement map = assertHasMap("Target Map");
		IElement point1 = assertHasMapPoint("Target Map");
		IElement point2 = assertHasMapPoint(map, "Target Map");
		assertEquals(point1, point2);
	}
	
	/**
	 * We can update the address twice.
	 * 
	 * @throws Exception
	 */
	public void testUpdateTwice() throws Exception {
		testUpdateMap();
		
		// fill in text field
		{
			String target = getLabelIDForText("select address");
			assertLabeledFieldEquals(target, "Wellington, New Zealand");
			setLabeledFormElementField(target, "Auckland, New Zealand");
		}
		
		assertNoProblem();
		
		// the map point now exists
		IElement map = assertHasMap("Target Map");
		IElement point1 = assertHasMapPoint("Target Map");
		IElement point2 = assertHasMapPoint(map, "Target Map");
		assertEquals(point1, point2);
	}

}
