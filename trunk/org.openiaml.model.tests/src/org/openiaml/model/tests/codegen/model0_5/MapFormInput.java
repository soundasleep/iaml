/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;

import net.sourceforge.jwebunit.api.IElement;

/**
 * 		
 * @example Map,MapPoint
 * 		Automatically populating a {@model Map} with new {@model MapPoint}s
 * 		from the fields on an {@model InputForm}.
 */
public class MapFormInput extends MapsCodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(MapFormInput.class);
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
	 * Initially, we have a map, but no contained map points, since
	 * they are all empty.
	 * 
	 * @throws Exception
	 */
	public void testInitialState() throws Exception {
		beginAtSitemapThenPage("Home");
		
		IElement map = assertHasMap("target map");
		
		// but no map points are generated yet
		assertHasNoMapPoint("address 1");
		assertHasNoMapPoint(map, "address 1");

		assertHasNoMapPoint("address 2");
		assertHasNoMapPoint(map, "address 2");

		assertHasNoMapPoint("address 3");
		assertHasNoMapPoint(map, "address 3");

	}
	
	/**
	 * Just set one address; the rest should stay hidden.
	 * @throws Exception
	 */
	public void testSetOne() throws Exception {
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForInputText("address 2");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Wellington, New Zealand");
		}
		
		assertNoProblem();
		IElement map = assertHasMap("target map");
		
		// but no map points are generated yet
		assertHasNoMapPoint("address 1");
		assertHasNoMapPoint(map, "address 1");

		assertHasNoMapPoint("address 3");
		assertHasNoMapPoint(map, "address 3");
		
		// but we do have an address 2
		if (!doCheckPoints()) return;
		IElement point1 = assertHasMapPoint("address 2");
		IElement point2 = assertHasMapPoint(map, "address 2");
		assertEquals(point1, point2);

	}
	
	public void testSetAll() throws Exception {
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForInputText("address 1");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Wellington, New Zealand");
		}
		{
			String target = getLabelIDForInputText("address 2");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Auckland, New Zealand");
		}
		{
			String target = getLabelIDForInputText("address 3");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Christchurch, New Zealand");
		}
		
		assertNoProblem();
		IElement map = assertHasMap("target map");
		
		if (!doCheckPoints()) return;
		{
			IElement point1 = assertHasMapPoint("address 1");
			IElement point2 = assertHasMapPoint(map, "address 1");
			assertEquals(point1, point2);
		}
		{
			IElement point1 = assertHasMapPoint("address 2");
			IElement point2 = assertHasMapPoint(map, "address 2");
			assertEquals(point1, point2);
		}
		{
			IElement point1 = assertHasMapPoint("address 3");
			IElement point2 = assertHasMapPoint(map, "address 3");
			assertEquals(point1, point2);
		}
	}
	
	/**
	 * We can set values repeatedly.
	 */
	public void testSetRepeatedly() throws Exception {
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForInputText("address 1");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Wellington, New Zealand");
		}
		{
			String target = getLabelIDForInputText("address 2");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Auckland, New Zealand");
		}
		{
			String target = getLabelIDForInputText("address 3");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Christchurch, New Zealand");
		}

		{
			String target = getLabelIDForInputText("address 2");
			setLabeledFormElementField(target, "Wellington, New Zealand");
		}
		{
			String target = getLabelIDForInputText("address 3");
			setLabeledFormElementField(target, "Auckland, New Zealand");
		}
		{
			String target = getLabelIDForInputText("address 1");
			setLabeledFormElementField(target, "Christchurch, New Zealand");
		}

		assertNoProblem();
		IElement map = assertHasMap("target map");
		
		if (!doCheckPoints()) return;
		{
			IElement point1 = assertHasMapPoint("address 1");
			IElement point2 = assertHasMapPoint(map, "address 1");
			assertEquals(point1, point2);
		}
		{
			IElement point1 = assertHasMapPoint("address 2");
			IElement point2 = assertHasMapPoint(map, "address 2");
			assertEquals(point1, point2);
		}
		{
			IElement point1 = assertHasMapPoint("address 3");
			IElement point2 = assertHasMapPoint(map, "address 3");
			assertEquals(point1, point2);
		}
	}

	/**
	 * Make sure that we don't accidentally select a Map or MapPoint.
	 * 
	 * @see #getLabelIDForText(String, String, String)
	 */
	private String getLabelIDForInputText(String string) {
		return getLabelIDForText(string, "IGNORE_STRING", "not(contains(@class, 'map')) and not(contains(@class, 'point'))");
	}
	
}
