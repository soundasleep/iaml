/**
 *
 */
package org.openiaml.model.tests.inference;

import java.util.Set;

import junit.framework.AssertionFailedError;

import org.eclipse.emf.ecore.EObject;
import org.jaxen.JaxenException;
import org.openiaml.model.codegen.php.OawCodeGenerator;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.SyncWire;

/**
 * A simple test, testing OawCodeGenerator#resolveDynamicSet, and the
 * query given in the requirements model. This test makes sure the
 * XPath resolver is working correctly with the given model.
 *
 * @author jmwright
 *
 */
public class Requirement4DynamicSources extends InferenceTestCaseWithConditionWires {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(ROOT + "../examples/requirements/4-dynamic_sources.iaml");
	}

	public void testXPathQuery() throws JaxenException {
		// there should be a dynamic set
		DynamicApplicationElementSet set = assertHasDynamicApplicationElementSet(root, "all pages");

		// see what it resolves to
		Set<EObject> results = OawCodeGenerator.resolveDynamicSet(root, set);

		// there should be three results
		try {
			assertEquals(3, results.size());
		} catch (AssertionFailedError e) {
			// lets print out the contents
			for (EObject o : results) {
				System.err.println(o);
			}
			throw e;
		}
	}

	/**
	 * Make sure the right parameters are being given to the SyncWires
	 * connected between page1 and the 'unrelated' page.
	 * @throws JaxenException
	 *
	 */
	public void testUnrelatedSyncWires() throws JaxenException {
		Page page1 = assertHasPage(root, "page a");
		Page page2 = assertHasPage(root, "page b");
		Page fieldList = assertHasPage(root, "FieldList");
		Page unrelated = assertHasPage(root, "unrelated");
	    DynamicApplicationElementSet dae = assertHasDynamicApplicationElementSet(root, "all pages");
		SyncWire sync = (SyncWire) getWireBidirectional(root, fieldList, dae);

		// generated
		SyncWire sync2 = (SyncWire) getWireBidirectional(root, fieldList, page1);
		SyncWire sync3 = (SyncWire) getWireBidirectional(root, fieldList, unrelated);

	    // generated
	    CompositeCondition cond = assertHasCompositeCondition(dae, "xpath");

		// there should be one condition wire between fieldList and unrelated
	    // i.e. only sync [fieldList] and [unrelated] if
	    // [unrelated] matches [xpath]
	    getConditionWireFromToWithParameters(root, cond, sync3, dae, unrelated);

	}

}