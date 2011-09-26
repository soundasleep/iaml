/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;

import net.sourceforge.jwebunit.api.IElement;

/**
 * 		
 *
 */
public class MapPointTextFieldInput extends MapsCodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(MapPointTextFieldInput.class);
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
	 * The home page doesn't have a "map", and also doesn't have a
	 * map point yet.
	 * 
	 * @throws Exception
	 */
	public void testDoesntHaveMap() throws Exception {
		beginAtSitemapThenPage("Home");
		
		// no explicit map
		assertHasNoMap("target map point");
		
		// point hasn't been set yet
		assertHasNoMapPoint("target map point");
		assertNoProblem();
	}
	
	/**
	 * If we create an address, it will automatically
	 * create the map point.
	 * 
	 * @throws Exception
	 */
	public void testSetPoint() throws Exception {
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForText("select address");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Wellington, New Zealand");
		}
		
		assertNoProblem();
		
		// the map point has been created
		if (!doCheckPoints()) return;
		IElement point = assertHasMapPoint("target map point");
		
		// TODO how to check that this point is at the right address?
		assertNotNull(point);
	}
	
	/**
	 * If we create an address <em>twice</em>, it will automatically
	 * create the map point, but only once.
	 * 
	 * @throws Exception
	 */
	public void testSetPointTwice() throws Exception {
		beginAtSitemapThenPage("Home");
		
		{
			String target = getLabelIDForText("select address");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "Wellington, New Zealand");
		}
		
		assertNoProblem();
		
		// the map point has been created
		if (!doCheckPoints()) return;
		IElement point = assertHasMapPoint("target map point");
		
		{
			String target = getLabelIDForText("select address");
			assertLabeledFieldEquals(target, "Wellington, New Zealand");
			setLabeledFormElementField(target, "Auckland, New Zealand");
		}
		
		assertNoProblem();
		IElement point2 = assertHasMapPoint("target map point");
		
		// TODO do they refer to the same element?
		assertEquals(point, point2);
			
	}
	
}
