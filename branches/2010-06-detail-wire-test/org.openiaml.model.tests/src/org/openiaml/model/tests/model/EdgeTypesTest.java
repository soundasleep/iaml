/**
 * 
 */
package org.openiaml.model.tests.model;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.openiaml.model.helpers.EdgeTypes;
import org.openiaml.model.tests.XmlTestCase;

/**
 * Make sure that all edge types are represented in EdgeTypes.
 * 
 * @author jmwright
 *
 */
public class EdgeTypesTest extends XmlTestCase {
	
	/**
	 * There needs to be at least one edge type.
	 */
	public void testEdgeTypesNotEmpty() {
		assertNotEqual(0, EdgeTypes.getEdgeTypes().size());
	}
	
	/**
	 * Any model element type that has a 'from' and 'to' reference (locally)
	 * should be an Edge.
	 */
	public void testFindAllClassesWithFromAndTo() {
		
		for (EClass cls : ContainmentTestCase.getAllClasses().keySet()) {
			boolean hasFrom = false;
			boolean hasTo = false;
			
			for (EReference ref : cls.getEReferences()) {
				if ("from".equals(ref.getName()))
					hasFrom = true;
				if ("to".equals(ref.getName()))
					hasTo = true;
			}
			
			if (hasFrom && hasTo) {
				// it should be an edge type
				assertTrue("Derived edge type '" + cls.getName() + "' was not an EdgeType", 
							EdgeTypes.getEdgeTypes().contains(cls));
			}
		}
		
	}
	
}
