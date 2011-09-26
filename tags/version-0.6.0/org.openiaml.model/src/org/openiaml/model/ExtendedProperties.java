/**
 * 
 */
package org.openiaml.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Extends properties to add a getPropertyNames() collection
 * 
 * @author jmwright
 *
 */
public class ExtendedProperties extends java.util.Properties {

	private static final long serialVersionUID = 1L;

	public List<String> getPropertyNames() {
		return new ArrayList<String>(stringPropertyNames());
	}

	public ExtendedProperties() {
		super();
	}

	/**
	 * @param defaults
	 */
	public ExtendedProperties(Properties defaults) {
		super(defaults);
	}
	
}
