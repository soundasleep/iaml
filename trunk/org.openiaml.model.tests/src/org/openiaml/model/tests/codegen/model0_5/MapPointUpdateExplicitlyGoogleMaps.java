/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import net.sourceforge.jwebunit.htmlunit.HtmlUnitTestingEngineImpl;

import org.openiaml.model.codegen.DefaultRuntimeProperties;

/**
 * Override {@link MapPointUpdateExplicitly} to actually generate
 * Google Maps.
 * 
 * <p>NOTE You may need to set the Java proxy system manually if you are
 * running these test cases behind a proxy. For example:
 * 
 * <pre> -Dhttp.proxyPort=8080
 * -Dhttp.proxyHost=tur-cache1.massey.ac.nz</pre>
 *
 */
public class MapPointUpdateExplicitlyGoogleMaps extends MapPointUpdateExplicitly {
	
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
	@Override
	protected Map<String, String> getRuntimeProperties() {
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
