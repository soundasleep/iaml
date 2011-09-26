/**
 * 
 */
package org.openiaml.emf.properties.library.metamodel;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IPropertyInvestigator;


/**
 * Provides a single static method to get a list of <em>all</em>
 * metamodel properties in this library.
 * 
 * @author jmwright
 *
 */
public class AllMetamodelPropertiesLibrary {

	/**
	 * Return a list of <em>all</em> metamodel properties within this
	 * library of property investigators. Not all of these properties
	 * will be useful.
	 * 
	 * @param selector allows for selective properties investigation
	 * @return A list of metamodel property investigators
	 */
	public static List<IPropertyInvestigator> getAllMetamodelProperties(IEMFElementSelector selector) {
		List<IPropertyInvestigator> investigators = new ArrayList<IPropertyInvestigator>();

		investigators.add(new MMAbstractClassesCount(selector));
		investigators.add(new MMAttributesCount(selector));
		investigators.add(new MMClassesCount(selector));
		investigators.add(new MMContainment(selector));
		investigators.add(new MMDataQuantity(selector));
		investigators.add(new MMEnumCount(selector));
		investigators.add(new MMNavigability(selector));
		investigators.add(new MMPackagesCount(selector));
		investigators.add(new MMPrimitiveDatatypesCount(selector));
		investigators.add(new MMReferencesCount(selector));
		
		return investigators;
	}
	
}
