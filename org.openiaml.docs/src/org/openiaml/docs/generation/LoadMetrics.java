/**
 * 
 */
package org.openiaml.docs.generation;

import org.eclipse.emf.ecore.EPackage;
import org.openiaml.docs.modeldoc.Metric;
import org.openiaml.docs.modeldoc.ModelDocumentation;
import org.openiaml.docs.modeldoc.ModeldocFactory;
import org.openiaml.emf.properties.IPropertyInvestigator;
import org.openiaml.emf.properties.IEMFElementSelector.DefaultElementSelector;
import org.openiaml.emf.properties.library.metamodel.AllMetamodelPropertiesLibrary;

/**
 * Calculates metamodel metrics.
 * 
 * @author jmwright
 *
 */
public class LoadMetrics extends DocumentationHelper implements ILoader {

	private EPackage rootPackage;

	public LoadMetrics(EPackage root) {
		super();
		
		this.rootPackage = root;
	}

	/**
	 * Load all of the runtime icons as GraphicalRepresentations.
	 * 
	 * @param factory
	 * @param root
	 */
	public void load(ModeldocFactory factory, ModelDocumentation root) {
		
		// get all the metrics in the library
		for (IPropertyInvestigator m : AllMetamodelPropertiesLibrary.getAllMetamodelProperties(new DefaultElementSelector())) {
			Object result = m.evaluate(rootPackage);
			
			// insert a new metric
			Metric metric = factory.createMetric();
			metric.setName(m.getName());
			metric.setValue(result.toString());
			root.getMetrics().add(metric);
			
		}
		
	}
	
}
