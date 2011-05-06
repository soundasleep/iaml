/**
 *
 */
package org.openiaml.model.tests.inference.model0_2;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.jaxen.JaxenException;
import org.openiaml.model.model.ActionEdge;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Tests that inference can keep track of the rule source of generated
 * elements.
 *
 * Based on ConditionWireFalse.
 *
 * @see ConditionWireXpath
 * @author jmwright
 *
 */
public class SavedRuleSources extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// remove any previous cache information
		removeClassFromInferenceCache(ConditionWireFalse.class);
		// the second parameter makes the inference keep track of rule sources (just names for now)
		root = loadAndInfer(ConditionWireFalse.class, true);
	}

	public void testInference() throws JaxenException {
		TreeIterator<EObject> it = root.eAllContents();
		assertTrue("Expected at least one child", it.hasNext());
		while (it.hasNext()) {
			EObject obj = it.next();
			if (obj instanceof GeneratedElement) {
				GeneratedElement ge = (GeneratedElement) obj;
				
				if (ge.isIsGenerated()) {
					/*
					 * There is no point in checking the specific text of
					 * each generated rule; this makes the test cases
					 * unnecessarily fragile. All that is important is that
					 * each generated element has an associated rule.
					 * 
					 * TODO in the future, this test could be extended to
					 * check that the rule exists in the rule database as well. 
					 */
					assertNotNull("Generated rule should be set: " + ge, ge.getGeneratedRule());
					assertNotEqual("Generated rule should be set: " + ge, ge.getGeneratedRule(), "");
				}
			}
		}

	}

}
