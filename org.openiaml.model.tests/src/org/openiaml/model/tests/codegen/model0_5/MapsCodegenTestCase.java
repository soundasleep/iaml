/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import junit.framework.AssertionFailedError;
import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitTestingEngineImpl;

import org.openiaml.model.codegen.DefaultRuntimeProperties;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Provides methods for verifying maps in generated code.	
 *
 */
public abstract class MapsCodegenTestCase extends CodegenTestCase {

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
	 * Assert a map with the given name does <em>not</em> exist.
	 */
	public void assertHasNoMap(String label) {
		try {
			IElement map = assertHasMap(label);
			throw new RuntimeException("Unexpectedly found map with label '" + label + "': " + map);
		} catch (AssertionFailedError e) {
			// expected
		}
	}

	/**
	 * Assert that the given map point exists in the given map, and
	 * returns the found point.
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
	 * Assert that the given map point exists anywhere.
	 */
	public IElement assertHasMapPoint(String label) {
		// find a point with the given string
		String id = getLabelIDForText(label);
		IElement point = getElementById(id);
		
		return point;
	}
	
	/**
	 * Assert that the given map point does <em>not</em> exist anywhere.
	 */
	public void assertHasNoMapPoint(String label) {
		try {
			IElement point = assertHasMapPoint(label);
			throw new RuntimeException("Unexpectedly found map point '" + label + "': " + point);
		} catch (AssertionFailedError e) {
			// expected
		}
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
	 * Specify the map handler, and the Google API key if necessary.
	 */
	@Override
	protected Map<String, String> getRuntimeProperties() {
		Map<String,String> properties = super.getRuntimeProperties();
	
		if (useMapsMockup()) {		
			// add these properties
			properties.put("map_handler", "mock");	
		} else {
			// change back to default
			File key = new File(ROOT + "codegen/model0_5/google-maps-api-key.txt");
			assertTrue("You need to specify a Google Maps API key in '" + key + "'", key.exists());
			
			String apiKey;
			try {
				apiKey = readFile(key).trim();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			assertTrue(apiKey.length() > 5); // at least 5 chars long
			
			properties.put("google_maps_api_key", apiKey);
		}
		
		return properties;
	}
	
	/**
	 * Should we use the <code>mock</code> map renderer, instead of the
	 * Google Maps renderer?
	 * 
	 * @return true by default
	 */
	protected boolean useMapsMockup() {
		return true;
	}
	
	private static boolean hasResetCodegenCache = false;
	
	@Override
	protected void setUp() throws Exception {
		// since we are extending, we don't want the cache to persist
		if (!hasResetCodegenCache)
			resetCodegenCache();
		hasResetCodegenCache = true;
		
		/*
		 * The Google Maps API does not return correct JS (it has
		 * syntax errors) so we need to turn the exception throwing off.
		 */
		HtmlUnitTestingEngineImpl engine = (HtmlUnitTestingEngineImpl) getTestingEngine();
		engine.setThrowExceptionOnScriptError(false);

		super.setUp();
	}

	/**
	 * Use the default <code>map_handler</code>.
	 */
	protected Map<String, String> getGoogleMapsRuntimeProperties() {
		Map<String, String> props = super.getRuntimeProperties();
		
		// change back to default
		Map<String, String> defaults = new DefaultRuntimeProperties().getDefaultProperties();
		
		File key = new File(ROOT + "codegen/model0_5/google-maps-api-key.txt");
		assertTrue("You need to specify a Google Maps API key in '" + key + "'", key.exists());
		
		String apiKey;
		try {
			apiKey = readFile(key).trim();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		assertTrue(apiKey.length() > 5); // at least 5 chars long
		
		props.put("map_handler", defaults.get("map_handler"));
		props.put("google_maps_api_key", apiKey);
		
		return props;
	}
	
}
