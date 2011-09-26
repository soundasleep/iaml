/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;

import net.sourceforge.jwebunit.api.IElement;

/** 		
 * @example Map,MapPoint
 * 		A {@model Map} containing many preset {@model MapPoint}s.
 */
public class MapContainingMapPoints extends MapsCodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(MapContainingMapPoints.class);
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
	 * Test the map exists, and the target map points are accessible.
	 * 
	 * @throws Exception
	 */
	public void testMapPoints() throws Exception {
		beginAtSitemapThenPage("Home");
		
		IElement map = assertHasMap("Target Map");
		
		if (!doCheckPoints()) return;
		
		// TODO how to check that the map points refer to the correct locations, are visible etc?
		{
			IElement point = assertHasMapPoint(map, "point 1");
			// and this point is not present anywhere else on the page
			assertEquals(point, assertHasMapPoint("point 1"));
		}
		{
			IElement point = assertHasMapPoint(map, "point 2");
			assertEquals(point, assertHasMapPoint("point 2"));
		}
		{
			IElement point = assertHasMapPoint(map, "point 3");
			assertEquals(point, assertHasMapPoint("point 3"));
		}
		{
			IElement point = assertHasMapPoint(map, "point 4");
			assertEquals(point, assertHasMapPoint("point 4"));
		}
		{
			IElement point = assertHasMapPoint(map, "point 5");
			assertEquals(point, assertHasMapPoint("point 5"));
		}
	}
		
}
