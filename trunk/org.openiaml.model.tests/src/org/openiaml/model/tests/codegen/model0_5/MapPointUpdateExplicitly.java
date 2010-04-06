/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;

import java.util.List;
import java.util.Map;

import junit.framework.AssertionFailedError;
import net.sourceforge.jwebunit.api.IElement;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * 		
 *
 */
public class MapPointUpdateExplicitly extends CodegenTestCase {
	
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
	 * Assert a map with the given name exists, and return the
	 * found map.
	 */
	public IElement assertHasMap(String label) {
		// find a label with the given string
		String id = getLabelIDForText(label);
		
		// this should contain a 'map' <div>
		IElement wrapper = getElementById(id);
		assertNotNull(wrapper);
		
		// through label.for
		assertNotNull(wrapper.getAttribute("for"));
		IElement map = getElementById(wrapper.getAttribute("for"));
		assertNotNull(map);

		return map;
	}

	
	/**
	 * Assert that the given map point exists in the given map, and
	 * returns the foind point.
	 */
	public IElement assertHasMapPoint(IElement map, String label) {
		// find a point with the given string
		String id = getLabelIDForText(label);
		IElement point = getElementById(id);
		
		// this should be contained within the given map
		List<IElement> elements = map.getElements("//*");
		assertTrue(elements + " did not contain " + point,
				elements.contains(point));
		
		return point;
	}

	/**
	 * @param map
	 * @param string
	 */
	public void assertHasNoMapPoint(IElement map, String string) {
		try {
			IElement point = assertHasMapPoint(map, string);

			throw new RuntimeException("Map point '" + string + "' was unexpectedly found in map '" + map + "': " + point);
		} catch (AssertionFailedError e) {
			// expected
		}
	}

	/**
	 * Add a hook to send e-mails to a file, rather than actually sending e-mails.
	 */
	@Override
	protected Map<String, String> getRuntimeProperties() {
		Map<String,String> properties = super.getRuntimeProperties();

		// add these properties
		properties.put("map_handler", "mock");

		return properties;
	}

	
}
