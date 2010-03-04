/**
 * 
 */
package org.openiaml.model.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * @author jmwright
 *
 */
public class EdgeTypes {
	
	public static final List<EClass> getEdgeTypes() {
		List<EClass> edgeTypes = new ArrayList<EClass>();
		
		edgeTypes.add(ModelPackage.eINSTANCE.getWire());
		edgeTypes.add(ModelPackage.eINSTANCE.getDataFlowEdge());
		edgeTypes.add(WiresPackage.eINSTANCE.getConditionEdge());
		edgeTypes.add(WiresPackage.eINSTANCE.getConstraintEdge());
		edgeTypes.add(ModelPackage.eINSTANCE.getConditionalEdge());
		edgeTypes.add(ModelPackage.eINSTANCE.getExecutionEdge());
		edgeTypes.add(WiresPackage.eINSTANCE.getExtendsEdge());
		edgeTypes.add(WiresPackage.eINSTANCE.getParameterEdge());
		edgeTypes.add(WiresPackage.eINSTANCE.getProvidesEdge());
		edgeTypes.add(WiresPackage.eINSTANCE.getRequiresEdge());
		
		return edgeTypes;
	}
	
}
