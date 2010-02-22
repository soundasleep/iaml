/**
 * 
 */
package org.openiaml.model.tests.inference;

import java.util.Set;

import org.jaxen.JaxenException;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ConditionEdgeDestination;
import org.openiaml.model.model.wires.ConditionEdgesSource;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ParameterEdgesSource;
import org.openiaml.model.model.wires.SyncWire;

/**
 * Inference-specific test cases that deal with ConditionWires and
 * ParameterWires.
 * 
 * TODO merge into {@link InferenceTestCase}.
 * 
 * @author jmwright
 *
 */
public abstract class InferenceTestCaseWithConditionWires extends InferenceTestCase {

	/**
	 * Check that there are no ConditionEdges from from to to, with
	 * the given page as a Parameter
	 * 
	 * @throws JaxenException 
	 */
	protected void assertNoParamtersToConditionEdges(InternetApplication container,
			ConditionEdgesSource from, ConditionEdgeDestination to, ParameterEdgesSource page) throws JaxenException {

		Set<ConditionEdge> conditions = getConditionEdgesFromTo(container, from, to);
		for (ConditionEdge condition : conditions) {

			Set<ParameterEdge> params = getParameterEdgesFromTo(container, page, condition);
			assertEquals("Unexpectedly found ParameterEdge: " + params, 0, params.size());
		}
		
	}
	
	/**
	 * Checks to see that a given wire has exactly one ConditionWire;
	 * one is from page1.
	 * 
	 * @throws JaxenException 
	 */
	protected void getConditionWireFromToWithParameters(InternetApplication root,
			CompositeCondition cond, SyncWire rw,
			DynamicApplicationElementSet dae, Frame page1) throws JaxenException {
		
		Set<ConditionEdge> conditions = getConditionEdgesFromTo(root, cond, rw);
		
		boolean checksPage1 = false;

		for (ConditionEdge cw : conditions) {			
			// all wires should have a parameter from the set (xpath)
			getParameterEdgeFromTo(root, dae, cw);

			// but one wire should have page1, the other should have page2
			if (getParameterEdgesFromTo(root, page1, cw).size() == 1) {
				checksPage1 = true;
			}

		}
		
		assertTrue("Page1 '" + page1 + "' should be connected to: " + cond, checksPage1);

		// should only be two conditions: page1 matches xpath, page2 matches xpath
		assertEquals(1, conditions.size());
	}

}
