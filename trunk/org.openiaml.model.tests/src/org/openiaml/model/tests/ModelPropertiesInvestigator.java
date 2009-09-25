/**
 * 
 */
package org.openiaml.model.tests;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.emf.properties.IEMFElementSelector;
import org.openiaml.emf.properties.IPropertyInvestigator;
import org.openiaml.emf.properties.library.AttributesCount;
import org.openiaml.emf.properties.library.AttributesCountIgnoreDefault;
import org.openiaml.emf.properties.library.ContainmentsCount;
import org.openiaml.emf.properties.library.ContainmentsCountIgnoreEmpty;
import org.openiaml.emf.properties.library.ContainmentsSum;
import org.openiaml.emf.properties.library.CountTypes;
import org.openiaml.emf.properties.library.Diameter;
import org.openiaml.emf.properties.library.DistinctAttributeValues;
import org.openiaml.emf.properties.library.DistinctContainments;
import org.openiaml.emf.properties.library.DistinctReferences;
import org.openiaml.emf.properties.library.DistinctSupertypes;
import org.openiaml.emf.properties.library.DistinctTypes;
import org.openiaml.emf.properties.library.ElementsCount;
import org.openiaml.emf.properties.library.MaxDegreeAttributes;
import org.openiaml.emf.properties.library.MaxDegreeContainments;
import org.openiaml.emf.properties.library.MaxDegreeReferences;
import org.openiaml.emf.properties.library.MaxInheritanceHeight;
import org.openiaml.emf.properties.library.Radius;
import org.openiaml.emf.properties.library.ReferencesCount;
import org.openiaml.emf.properties.library.ReferencesCountIgnoreEmpty;
import org.openiaml.emf.properties.library.ReferencesSum;
import org.openiaml.emf.properties.library.SupertypesCount;
import org.openiaml.model.model.ActivityNode;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.visual.Page;

/**
 * For an upcoming paper, we need to investigate the properties
 * of the test models.
 * 
 * Given an input model, this will return a list of 
 * properties loaded from the model.
 * 
 * @author jmwright
 *
 */
public class ModelPropertiesInvestigator implements IEMFElementSelector {

	public ModelPropertiesInvestigator(boolean ignoreGenerated) {
		this.ignoreGenerated = ignoreGenerated;
	}
	
	private List<IPropertyInvestigator> investigators = null;
	private boolean ignoreGenerated;
	
	public List<IPropertyInvestigator> getInvestigators() {
		if (investigators == null) {
			investigators = new ArrayList<IPropertyInvestigator>();
			// initialise
			
			investigators.add(new ElementsCount("elements-count", this));
			investigators.add(new AttributesCount("attributes-count", this));
			investigators.add(new AttributesCountIgnoreDefault("attributes-count-no-default", this));
			investigators.add(new ReferencesCount("references-count", this));
			investigators.add(new ReferencesCountIgnoreEmpty("references-count-no-empty", this));
			investigators.add(new ReferencesSum("references-sum", this));
			investigators.add(new ContainmentsCount("containments-count", this));
			investigators.add(new ContainmentsCountIgnoreEmpty("containments-count-no-empty", this));
			investigators.add(new ContainmentsSum("containments-sum", this));
			investigators.add(new DistinctTypes("distinct-types", this));
			investigators.add(new SupertypesCount("supertype-count", this));
			investigators.add(new DistinctSupertypes("distinct-supertypes", this));
			investigators.add(new DistinctAttributeValues("distinct-attribute-values", this));
			investigators.add(new DistinctReferences("distinct-references", this));
			investigators.add(new DistinctContainments("distinct-containments", this));
			investigators.add(new MaxDegreeAttributes("max-degree-attributes", this));
			investigators.add(new MaxDegreeReferences("max-degree-references", this));
			investigators.add(new MaxDegreeContainments("max-degree-containments", this));
			investigators.add(new MaxInheritanceHeight("max-inheritance-height", this));
			investigators.add(new Radius("radius", this));
			investigators.add(new Diameter("diameter", this));
			investigators.add(new CountTypes("wires", this, WireEdge.class));
			investigators.add(new CountTypes("visible-things", this, VisibleThing.class));
			investigators.add(new CountTypes("events", this, EventTrigger.class));
			investigators.add(new CountTypes("operations", this, Operation.class));
			investigators.add(new CountTypes("conditions", this, Condition.class));
			investigators.add(new CountTypes("execution-edges", this, ExecutionEdge.class));
			investigators.add(new CountTypes("data-flow-edges", this, DataFlowEdge.class));
			investigators.add(new CountTypes("properties", this, ApplicationElementProperty.class));
			investigators.add(new CountTypes("nodes", this, ActivityNode.class));
			investigators.add(new CountTypes("pages", this, Page.class));
		}
		return investigators;
	}
	
	/**
	 * Is the given reference a 'generated' reference?
	 * 
	 * @see #investigate(EObject, boolean)
	 * @see #ignoreReference(EReference)
	 * @param ref
	 * @return
	 */
	protected boolean isGeneratedReference(EReference ref) {
		return ref.equals(ModelPackage.eINSTANCE.getGeneratedElement_GeneratedBy()) ||
			ref.equals(ModelPackage.eINSTANCE.getGeneratesElements_GeneratedElements());
	}

	/**
	 * Return a list of all the property names that we will investigate.
	 * 
	 * @return
	 */
	public List<String> getModelProperties() {
		List<String> result = new ArrayList<String>();
		for (IPropertyInvestigator p : getInvestigators()) {
			result.add(p.getName());
		}
		return result;
	}
	
	/**
	 * Should we ignore the current reference?
	 * Set in {@link #investigate(EObject, boolean)}.
	 * 
	 * @param ref
	 * @return
	 */
	public boolean ignoreReference(EReference ref) {
		return ignoreGenerated && isGeneratedReference(ref); 
	}
	
	/**
	 * Investigate the given EObject for all of the model
	 * properties. 
	 * 
	 * @see #isGeneratedReference(EReference)
	 * @param root
	 * @param ignoreGenerated should we ignore generated references?
	 * @return
	 */
	public List<Object> investigate(EObject root) {
		List<Object> result = new ArrayList<Object>();
		for (IPropertyInvestigator p : getInvestigators()) {
			result.add(p.evaluate(root));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.emf.properties.IEMFElementSelector#ignoreAttribute(org.eclipse.emf.ecore.EAttribute)
	 */
	@Override
	public boolean ignoreAttribute(EAttribute ref) {
		return ignoreGenerated && (ref.equals(ModelPackage.eINSTANCE.getGeneratedElement_GeneratedRule())
				|| ref.equals(ModelPackage.eINSTANCE.getGeneratedElement_IsGenerated()));
	}

	/* (non-Javadoc)
	 * @see org.openiaml.emf.properties.IEMFElementSelector#ignoreClass(org.eclipse.emf.ecore.EClass)
	 */
	@Override
	public boolean ignoreClass(EClass ref) {
		return ignoreGenerated && ref.equals(ModelPackage.eINSTANCE.getGeneratedElement());
	}
	
}
