/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;


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

	@Override
	protected boolean doCheckPoints() {
		return false;
	}

	@Override
	protected boolean useMapsMockup() {
		return false;
	}
	
}
