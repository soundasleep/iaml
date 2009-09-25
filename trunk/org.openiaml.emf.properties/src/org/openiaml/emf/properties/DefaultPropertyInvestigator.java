/**
 * 
 */
package org.openiaml.emf.properties;


/**
 * Default abstract implementation.
 * 
 * @author jmwright
 *
 */
public abstract class DefaultPropertyInvestigator implements IPropertyInvestigator {
	private String name;
	
	public DefaultPropertyInvestigator(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
