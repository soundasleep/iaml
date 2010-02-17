/**
 * 
 */
package org.openiaml.model.tests.inference;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.AssertionFailedError;

import org.jaxen.JaxenException;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.wires.ConditionWire;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ParameterEdgesSource;
import org.openiaml.model.model.wires.RunInstanceWire;
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
	 * Check that there are no ConditionWires from from to to, with
	 * the given page as a Parameter
	 * 
	 * @throws JaxenException 
	 */
	protected void assertNoParamtersToConditionWires(InternetApplication container,
			WireEdgesSource from, WireEdgeDestination to, ParameterEdgesSource page) throws JaxenException {

		Set<WireEdge> conditions = getWiresFromTo(container, from, to);
		for (WireEdge wire : conditions) {
			ConditionWire condition = (ConditionWire) wire;

			Set<ParameterEdge> params = getParameterEdgesFromTo(container, page, condition);
			assertEquals("Unexpectedly found ParameterEdge: " + params, 0, params.size());
		}
		
	}

	/**
	 * Checks to see that a given wire has exactly two ConditionWires;
	 * one is from page1, the other is from page2.
	 * 
	 * @throws JaxenException 
	 */
	protected void getConditionWireFromToWithParameters(InternetApplication root,
			CompositeCondition cond, RunInstanceWire rw,
			DynamicApplicationElementSet dae, Frame page1, Frame page2) throws JaxenException {
		
		Set<WireEdge> conditions = getWiresFromTo(root, cond, rw);
		// should only be two conditions: page1 matches xpath, page2 matches xpath
		assertEquals(2, conditions.size());
		
		boolean checksPage1 = false;
		boolean checksPage2 = false;
		
		for (WireEdge wire : conditions) {
			ConditionWire cw = (ConditionWire) wire;
			
			// all wires should have a parameter from the set (xpath)
			getParameterEdgeFromTo(root, dae, cw);
			
			// but one wire should have page1, the other should have page2
			if (hasWireFromTo(root, page1, cw)) {
				checksPage1 = true;
			}
			if (hasWireFromTo(root, page2, cw)) {
				checksPage2 = true;
			}
		}
		
		assertTrue("Page1 should be connected", checksPage1);
		assertTrue("Page2 should be connected", checksPage2);
		
	}


	/**
	 * Checks to see that a given wire has exactly two ConditionWires;
	 * one is from page1, the other is from page2.
	 * 
	 * @throws JaxenException 
	 */
	protected void getConditionWireFromToWithParameters(InternetApplication root,
			CompositeCondition cond, SyncWire rw,
			DynamicApplicationElementSet dae, Frame page1, Frame page2) throws JaxenException {
		
		Set<WireEdge> conditions = getWiresFromTo(root, cond, rw);
		
		boolean checksPage1 = false;
		boolean checksPage2 = false;
		
		List<WireEdge> froms = new ArrayList<WireEdge>();
		
		for (WireEdge wire : conditions) {
			ConditionWire cw = (ConditionWire) wire;
			
			// all wires should have a parameter from the set (xpath)
			getParameterEdgeFromTo(root, dae, cw);

			// but one wire should have page1, the other should have page2
			if (hasWireFromTo(root, page1, cw)) {
				checksPage1 = true;
			}
			if (hasWireFromTo(root, page2, cw)) {
				checksPage2 = true;
			}
			
			froms.addAll( getWiresTo(root, cw) );
		}
		
		try {
			assertTrue("Page1 '" + page1 + "' should be connected to: " + cond, checksPage1);
			assertTrue("Page2 '" + page2 + "' should be connected to: " + cond, checksPage2);

			// should only be two conditions: page1 matches xpath, page2 matches xpath
			assertEquals(2, conditions.size());
		} catch (AssertionFailedError e) {
			// print out those that ARE connected
			for (WireEdge w : froms) {
				System.err.println(w.getFrom());
			}
			throw e;		// rethrow
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
		
		Set<WireEdge> conditions = getWiresFromTo(root, cond, rw);
		
		boolean checksPage1 = false;

		for (WireEdge wire : conditions) {
			ConditionWire cw = (ConditionWire) wire;
			
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
	
	/**
	 * TODO move into higher-level test case
	 * 
	 * @throws JaxenException 
	 */
	protected boolean hasWireFromTo(InternetApplication root2, Frame page1,
			ConditionWire cw) throws JaxenException {
		try {
			getWireFromTo(root2, page1, cw);
			return true;
		} catch (AssertionFailedError e) {
			return false;
		}
	}

}
