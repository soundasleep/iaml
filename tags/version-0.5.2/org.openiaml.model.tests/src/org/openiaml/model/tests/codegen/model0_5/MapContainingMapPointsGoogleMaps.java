/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5;


/**
 * Actually generate Google Maps, rather than mock code.
 * 
 * <p>NOTE that in the actual test case, this essentially will not fail as long
 * as the code can be generated - it doesn't actually interact with the
 * web application in any meaningful way.
 *
 */
public class MapContainingMapPointsGoogleMaps extends MapContainingMapPoints {
	
	@Override
	protected boolean doCheckPoints() {
		return false;
	}

	@Override
	protected boolean useMapsMockup() {
		return false;
	}
	
}