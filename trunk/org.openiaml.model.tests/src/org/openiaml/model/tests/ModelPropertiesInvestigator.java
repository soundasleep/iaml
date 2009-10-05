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
import org.openiaml.emf.properties.library.ContainmentsSum;
import org.openiaml.emf.properties.library.DistinctAttributeValues;
import org.openiaml.emf.properties.library.DistinctSupertypes;
import org.openiaml.emf.properties.library.DistinctTypes;
import org.openiaml.emf.properties.library.ElementsCount;
import org.openiaml.emf.properties.library.Increase;
import org.openiaml.emf.properties.library.IncreaseAbsolute;
import org.openiaml.emf.properties.library.MaxDegreeAttributes;
import org.openiaml.emf.properties.library.MaxDegreeContainments;
import org.openiaml.emf.properties.library.MaxDegreeReferences;
import org.openiaml.emf.properties.library.MaxInheritanceHeight;
import org.openiaml.emf.properties.library.MinDegreeAttributes;
import org.openiaml.emf.properties.library.MinDegreeContainments;
import org.openiaml.emf.properties.library.MinDegreeReferences;
import org.openiaml.emf.properties.library.ReferencesCycles;
import org.openiaml.emf.properties.library.ReferencesDiameter;
import org.openiaml.emf.properties.library.ReferencesRadius;
import org.openiaml.emf.properties.library.ReferencesSum;
import org.openiaml.emf.properties.library.ReferencesWithoutContainmentsSum;
import org.openiaml.emf.properties.library.RootContainmentsHeight;
import org.openiaml.model.model.ModelPackage;

/**
 * <p>For an upcoming paper, we need to investigate the properties
 * of the test models.</p>
 * 
 * <p>Given an input model, this will return a list of 
 * properties loaded from the model.</p>
 * 
 * <p>With each property, it also returns the time taken to execute the
 * property check.</p>
 * 
 * @author jmwright
 *
 */
public class ModelPropertiesInvestigator implements IEMFElementSelector {

	private boolean includeComplexChecks;

	public ModelPropertiesInvestigator(boolean ignoreGenerated, boolean includeComplexChecks) {
		this.ignoreGenerated = ignoreGenerated;
		this.includeComplexChecks = includeComplexChecks;
	}
	
	private List<IPropertyInvestigator> investigators = null;
	private boolean ignoreGenerated;
	
	/**
	 * Should we include complicated complex checks, 
	 * e.g. graph radius/diameter?
	 * 	
	 * @return
	 */
	public boolean isIncludeComplexChecks() {
		return includeComplexChecks;
	}

	public List<IPropertyInvestigator> getInvestigators() {
		if (investigators == null) {
			investigators = new ArrayList<IPropertyInvestigator>();
			// initialise
			
			investigators.add(new ElementsCount("elements-count", this));
			investigators.add(new AttributesCount("attributes-count", this));
			investigators.add(new AttributesCountIgnoreDefault("attributes-count-no-default", this));
			// investigators.add(new ReferencesCount("references-count", this));
			// investigators.add(new ReferencesCountIgnoreEmpty("references-count-no-empty", this));
			investigators.add(new ReferencesSum("references-sum", this));
			// investigators.add(new ContainmentsCount("containments-count", this));
			// investigators.add(new ContainmentsCountIgnoreEmpty("containments-count-no-empty", this));
			investigators.add(new ContainmentsSum("containments-sum", this));
			investigators.add(new ReferencesWithoutContainmentsSum("references-without-containments-sum", this));
			investigators.add(new DistinctTypes("distinct-types", this));
			// investigators.add(new SupertypesCount("supertype-count", this));
			investigators.add(new DistinctSupertypes("distinct-supertypes", this));
			investigators.add(new DistinctAttributeValues("distinct-attribute-values", this));
			// investigators.add(new DistinctReferences("distinct-references", this));
			// investigators.add(new DistinctContainments("distinct-containments", this));
			investigators.add(new MaxDegreeAttributes("max-degree-attributes", this));
			investigators.add(new MaxDegreeReferences("max-degree-references", this));
			investigators.add(new MaxDegreeContainments("max-degree-containments", this));
			investigators.add(new MinDegreeAttributes("min-degree-attributes", this));
			investigators.add(new MinDegreeReferences("min-degree-references", this));
			investigators.add(new MinDegreeContainments("min-degree-containments", this));
			investigators.add(new MaxInheritanceHeight("max-inheritance-height", this));
			if (isIncludeComplexChecks()) {
				investigators.add(new ReferencesRadius("references-radius", this));
				investigators.add(new ReferencesDiameter("references-diameter", this));
				// investigators.add(new ContainmentsRadius("containments-radius", this));
				// investigators.add(new ContainmentsDiameter("containments-diameter", this));
				/*
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
				*/
				investigators.add(new ReferencesCycles("cycles", this));
				investigators.add(new RootContainmentsHeight("children-height", this));
			}
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
	public static boolean isGeneratedReference(EReference ref) {
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
			result.add(p.getName() + "-time");	// add time as well
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
			long startTime = System.currentTimeMillis();
			result.add(p.evaluate(root));
			long endTime = System.currentTimeMillis();
			result.add(endTime - startTime);	// add time as well
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
	
	/**
	 * Wraps {@link #getModelPropertiesInvestigator(boolean)} with the
	 * {@link Increase} operator, so we can investigate the difference
	 * between ignoreGenerated=false and ignoreGenerated=true.
	 * 
	 * @author jmwright
	 *
	 */
	public static class ModelPropertiesInvestigatorIncreasePercent extends ModelPropertiesInvestigator {

		private ModelPropertiesInvestigator source;
		private ModelPropertiesInvestigator target;
		
		/**
		 * @param ignoreGenerated
		 */
		public ModelPropertiesInvestigatorIncreasePercent(ModelPropertiesInvestigator source, ModelPropertiesInvestigator target) {
			super(false, true);
			this.source = source;
			this.target = target;
		}

		private List<IPropertyInvestigator> investigators = null;
		
		/**
		 * Join up all investigators from both source and target
		 * with differences.
		 * 
		 * Assumes all the investigators match up in order and length.
		 */
		@Override
		public List<IPropertyInvestigator> getInvestigators() {
			if (investigators == null) {
				investigators = new ArrayList<IPropertyInvestigator>();
				// initialise from source and target
				for (int i = 0; i < source.getInvestigators().size(); i++) {
					IPropertyInvestigator s = source.getInvestigators().get(i); 
					IPropertyInvestigator t = target.getInvestigators().get(i); 
					investigators.add(new Increase(s.getName() + "-increase", 
							new DefaultElementSelector(),
							s, t));
				}
				
			}
			
			return investigators;
		}
		
	}
	
	/**
	 * Wraps {@link #getModelPropertiesInvestigator(boolean)} with the
	 * {@link Increase} operator, so we can investigate the difference
	 * between ignoreGenerated=false and ignoreGenerated=true.
	 * 
	 * @author jmwright
	 *
	 */
	public static class ModelPropertiesInvestigatorIncreaseAbsolute extends ModelPropertiesInvestigator {

		private ModelPropertiesInvestigator source;
		private ModelPropertiesInvestigator target;
		
		/**
		 * @param ignoreGenerated
		 */
		public ModelPropertiesInvestigatorIncreaseAbsolute(ModelPropertiesInvestigator source, ModelPropertiesInvestigator target) {
			super(false, true);
			this.source = source;
			this.target = target;
		}

		private List<IPropertyInvestigator> investigators = null;
		
		/**
		 * Join up all investigators from both source and target
		 * with differences.
		 * 
		 * Assumes all the investigators match up in order and length.
		 */
		@Override
		public List<IPropertyInvestigator> getInvestigators() {
			if (investigators == null) {
				investigators = new ArrayList<IPropertyInvestigator>();
				// initialise from source and target
				for (int i = 0; i < source.getInvestigators().size(); i++) {
					IPropertyInvestigator s = source.getInvestigators().get(i); 
					IPropertyInvestigator t = target.getInvestigators().get(i); 
					investigators.add(new IncreaseAbsolute(s.getName() + "-increase", 
							new DefaultElementSelector(),
							s, t));
				}
				
			}
			
			return investigators;
		}
		
	}
	
}
