/**
 * 
 */
package org.openiaml.emf.properties;

import java.util.ArrayList;
import java.util.List;

import org.openiaml.emf.properties.library.AttributesCount;
import org.openiaml.emf.properties.library.AttributesCountIgnoreDefault;
import org.openiaml.emf.properties.library.ContainmentsCount;
import org.openiaml.emf.properties.library.ContainmentsCountIgnoreEmpty;
import org.openiaml.emf.properties.library.ContainmentsDiameter;
import org.openiaml.emf.properties.library.ContainmentsRadius;
import org.openiaml.emf.properties.library.ContainmentsSum;
import org.openiaml.emf.properties.library.CyclomaticComplexity;
import org.openiaml.emf.properties.library.DistinctAttributeValueStrings;
import org.openiaml.emf.properties.library.DistinctAttributeValues;
import org.openiaml.emf.properties.library.DistinctContainments;
import org.openiaml.emf.properties.library.DistinctReferences;
import org.openiaml.emf.properties.library.DistinctSupertypes;
import org.openiaml.emf.properties.library.DistinctTypes;
import org.openiaml.emf.properties.library.ElementsCount;
import org.openiaml.emf.properties.library.MaxDegreeAttributes;
import org.openiaml.emf.properties.library.MaxDegreeContainments;
import org.openiaml.emf.properties.library.MaxDegreeReferences;
import org.openiaml.emf.properties.library.MaxDegreeReferencesWithoutContainments;
import org.openiaml.emf.properties.library.MaxInheritanceHeight;
import org.openiaml.emf.properties.library.MinDegreeAttributes;
import org.openiaml.emf.properties.library.MinDegreeContainments;
import org.openiaml.emf.properties.library.MinDegreeReferences;
import org.openiaml.emf.properties.library.MinDegreeReferencesWithoutContainments;
import org.openiaml.emf.properties.library.ReferencesCount;
import org.openiaml.emf.properties.library.ReferencesCountIgnoreEmpty;
import org.openiaml.emf.properties.library.ReferencesCycles;
import org.openiaml.emf.properties.library.ReferencesDiameter;
import org.openiaml.emf.properties.library.ReferencesRadius;
import org.openiaml.emf.properties.library.ReferencesSum;
import org.openiaml.emf.properties.library.ReferencesWithoutContainmentsCount;
import org.openiaml.emf.properties.library.ReferencesWithoutContainmentsCountIgnoreEmpty;
import org.openiaml.emf.properties.library.ReferencesWithoutContainmentsCycles;
import org.openiaml.emf.properties.library.ReferencesWithoutContainmentsDiameter;
import org.openiaml.emf.properties.library.ReferencesWithoutContainmentsRadius;
import org.openiaml.emf.properties.library.ReferencesWithoutContainmentsSum;
import org.openiaml.emf.properties.library.RootContainmentsHeight;
import org.openiaml.emf.properties.library.SupertypesCount;

/**
 * Provides a single static method to get a list of <em>all</em>
 * EMF properties in this library.
 * 
 * @author jmwright
 *
 */
public class EMFPropertiesLibrary {

	/**
	 * Return a list of <em>all</em> EMF properties within this
	 * library of property investigators. Not all of these properties
	 * will be useful.
	 * 
	 * @param selector allows for selective properties investigation
	 * @return A list of EMF property investigators
	 */
	public static List<IPropertyInvestigator> getAllEMFProperties(IEMFElementSelector selector) {
		List<IPropertyInvestigator> investigators = new ArrayList<IPropertyInvestigator>();

		investigators.add(new AttributesCount(selector));
		investigators.add(new AttributesCountIgnoreDefault(selector));
		investigators.add(new ContainmentsCount(selector));
		investigators.add(new ContainmentsCountIgnoreEmpty(selector));
		investigators.add(new ContainmentsDiameter(selector));
		investigators.add(new ContainmentsRadius(selector));
		investigators.add(new ContainmentsSum(selector));
		//investigators.add(new CountTypes(selector)); - needs a parameter
		investigators.add(new CyclomaticComplexity(selector));
		investigators.add(new DistinctAttributeValues(selector));
		investigators.add(new DistinctAttributeValueStrings(selector));
		investigators.add(new DistinctContainments(selector));
		investigators.add(new DistinctReferences(selector));
		investigators.add(new DistinctSupertypes(selector));
		investigators.add(new DistinctTypes(selector));
		investigators.add(new ElementsCount(selector));
		investigators.add(new MaxDegreeAttributes(selector));
		investigators.add(new MaxDegreeContainments(selector));
		investigators.add(new MaxDegreeReferences(selector));
		investigators.add(new MaxDegreeReferencesWithoutContainments(selector));
		investigators.add(new MaxInheritanceHeight(selector));
		investigators.add(new MinDegreeAttributes(selector));
		investigators.add(new MinDegreeContainments(selector));
		investigators.add(new MinDegreeReferences(selector));
		investigators.add(new MinDegreeReferencesWithoutContainments(selector));
		investigators.add(new ReferencesCount(selector));
		investigators.add(new ReferencesCountIgnoreEmpty(selector));
		investigators.add(new ReferencesCycles(selector));
		investigators.add(new ReferencesDiameter(selector));
		investigators.add(new ReferencesRadius(selector));
		investigators.add(new ReferencesSum(selector));
		investigators.add(new ReferencesWithoutContainmentsCount(selector));
		investigators.add(new ReferencesWithoutContainmentsCountIgnoreEmpty(selector));
		investigators.add(new ReferencesWithoutContainmentsCycles(selector));
		investigators.add(new ReferencesWithoutContainmentsDiameter(selector));
		investigators.add(new ReferencesWithoutContainmentsRadius(selector));
		investigators.add(new ReferencesWithoutContainmentsSum(selector));
		investigators.add(new RootContainmentsHeight(selector));
		investigators.add(new SupertypesCount(selector));
		
		return investigators;
	}
	
}
