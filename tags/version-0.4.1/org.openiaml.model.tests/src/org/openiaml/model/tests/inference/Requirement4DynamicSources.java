/**
 *
 */
package org.openiaml.model.tests.inference;

import java.util.List;
import java.util.Set;

import junit.framework.AssertionFailedError;

import org.eclipse.emf.ecore.EObject;
import org.jaxen.JaxenException;
import org.openiaml.model.codegen.php.OawCodeGenerator;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ConditionWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
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
		root = loadAndInfer(ROOT + "../examples/requirements/4-dynamic_sources.iaml", true);
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
	
	/**
	 * The CompositeCondition 'xpath' (generated) should only have two parameters.
	 * 
	 * @throws Exception
	 */
	public void testOnly2Parameters() throws Exception {
	    DynamicApplicationElementSet dae = assertHasDynamicApplicationElementSet(root, "all pages");
	    assertNotGenerated(dae);
	    
	    // generated
	    CompositeCondition cond = assertHasCompositeCondition(dae, "xpath");
	    assertGenerated(cond);
	    
	    // parameters
	    List<?> parameters = query(cond, "iaml:parameters");
	    assertEquals(2, parameters.size());
	    
	}
	
	/**
	 * Since we are using Xpath which adds a condition to all sync wires,
	 * the condition that restricts the init/access wire should also copy
	 * over the appropriate parameters (exactly two - element, xpath).
	 * 
	 * @throws Exception
	 */
	public void testTextFieldInitConditionParameters() throws Exception {
		// initial
		Page page1 = assertHasPage(root, "page a");
		Page page2 = assertHasPage(root, "page b");
		Page page3 = assertHasPage(root, "page c");
		Page fieldList = assertHasPage(root, "FieldList");
		DynamicApplicationElementSet dae = assertHasDynamicApplicationElementSet(root, "all pages");
		
		// 'xpath' condition
		CompositeCondition c = assertHasCompositeCondition(dae, "xpath");
		assertGenerated(c);
		
		SyncWire sw = assertHasSyncWire(root, fieldList, page1, "dynamic sync");
		assertGenerated(sw);
		
		// condition on sync wire
		ConditionWire cond = assertHasConditionWire(page1, c, sw);
		assertGenerated(cond);
		
		// page1.target
		InputTextField page1text1 = assertHasInputTextField(page1, "target");
		assertNotGenerated(page1text1);
		// page2 has no target
		assertHasNoInputTextField(page2, "target");
		// page3.target
		InputTextField page3text1 = assertHasInputTextField(page1, "target");
		assertNotGenerated(page1text1);
		// fieldlist.target
		InputTextField fltext1 = assertHasInputTextField(fieldList, "target");
		assertNotGenerated(fltext1);
		
		// sync wire cascaded to text field
		SyncWire swtext = assertHasSyncWire(root, page1text1, fltext1);
		assertGenerated(swtext);

		// sync wires go page1 <--> fieldlist <--> page3, not
		// sync wires going page1 <--> page3
		assertNoWireBidirectional(root, page1text1, page3text1);
		
		// condition cascaded to text field
		ConditionWire syncCond = assertHasConditionWire(root, c, swtext);	
		assertGenerated(syncCond);
		
		EventTrigger access = assertHasEventTrigger(page1text1, "access");
		assertGenerated(access);
		Operation init = assertHasOperation(page1text1, "init");
		assertGenerated(init);
		
		// run instance wire from 'access' to 'init'
		RunInstanceWire run = assertHasRunInstanceWire(root, access, init);
		assertGenerated(run);
		
		// condition cascaded to run wire
		ConditionWire initCondition = assertHasConditionWire(root, c, run);  
		assertGenerated(initCondition);
		
		// parameter wires
		Set<?> params = getWiresTo(syncCond, initCondition);
		assertEquals(2, params.size());
		
		assertInstanceOf(ParameterWire.class, params);		
		
	}

}
