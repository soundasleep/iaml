/**
 *
 */
package org.openiaml.model.tests.inference;

import java.util.List;

import org.jaxen.JaxenException;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.CastNode;
import org.openiaml.model.model.operations.DecisionOperation;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.RunAction;
import org.openiaml.model.model.wires.SyncWire;

/**
 * Tests inference of sync wires between a .properties
 * FileDomainStore and an InputForm.
 *
 * @author jmwright
 *
 */
public class SyncWiresProperties extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(SyncWiresProperties.class);
	}

	public void testInference() throws JaxenException {
		Frame page = assertHasFrame(root, "container");
		InputForm form = assertHasInputForm(page, "target form");
		DomainStore store = assertHasDomainStore(root, "properties file");
		DomainObject properties = assertHasDomainObject(store, "properties");

		// there should be exactly three fields here (generated)
		// if there are four fields here, the generated key may have been added as a text field
		List<?> nodes = query(form, "iaml:children");
		assertEquals(3, nodes.size());
		InputTextField f1 = (InputTextField) getNodeWithName(nodes, "fruit");
		InputTextField f2 = (InputTextField) getNodeWithName(nodes, "animal");
		InputTextField f3 = (InputTextField) getNodeWithName(nodes, "country");
		assertEquals(f1.getName(), "fruit");
		assertTrue(f1.isIsGenerated());
		assertEquals(f2.getName(), "animal");
		assertTrue(f2.isIsGenerated());
		assertEquals(f3.getName(), "country");
		assertTrue(f3.isIsGenerated());

		// find the sync wire
		SyncWire wire = assertHasSyncWire(page, form, properties, "sync properties");
		assertNotGenerated(wire);

		// there should be 4 attributes; one of these will be a primary key
		List<?> nodes2 = query(properties, "iaml:attributes");
		assertEquals(4, nodes2.size());
		DomainAttribute a1 = (DomainAttribute) getNodeWithName(nodes2, "fruit");
		DomainAttribute a2 = (DomainAttribute) getNodeWithName(nodes2, "animal");
		DomainAttribute a3 = (DomainAttribute) getNodeWithName(nodes2, "country");
		DomainAttribute a4 = (DomainAttribute) getNodeWithName(nodes2, "generated primary key");
		assertEquals(a1.getName(), "fruit");
		assertFalse(a1.isPrimaryKey());
		assertEquals(a2.getName(), "animal");
		assertFalse(a2.isPrimaryKey());
		assertEquals(a3.getName(), "country");
		assertFalse(a3.isPrimaryKey());
		assertEquals(a4.getName(), "generated primary key");
		assertTrue(a4.isPrimaryKey());

		// there should be sync wires between each of these elements
		// (and the directionality does not matter)
		SyncWire sw1 = assertHasSyncWire(form, f1, a1);
		SyncWire sw2 = assertHasSyncWire(form, f2, a2);
		SyncWire sw3 = assertHasSyncWire(form, f3, a3);

		assertTrue(sw1.isIsGenerated());
		assertTrue(sw2.isIsGenerated());
		assertTrue(sw3.isIsGenerated());

		// there should be RunActions on each of these attributes
		// (following from SyncFormDomainObject)

		// fields should have fieldValues
		Property value1 = assertHasFieldValue(f1);
		Property value2 = assertHasFieldValue(f2);
		Property value3 = assertHasFieldValue(f3);
		Property valuea1 = assertHasFieldValue(a1);
		Property valuea2 = assertHasFieldValue(a2);
		Property valuea3 = assertHasFieldValue(a3);

		// these field values should be parameters to run instance wires
		{
			ParameterEdge pw1 = value1.getOutParameterEdges().get(0);
			assertTrue(pw1.toString(), pw1.getTo() instanceof RunAction);
			ParameterEdge pw2 = value2.getOutParameterEdges().get(0);
			assertTrue(pw2.toString(), pw2.getTo() instanceof RunAction);
			ParameterEdge pw3 = value3.getOutParameterEdges().get(0);
			assertTrue(pw3.toString(), pw3.getTo() instanceof RunAction);
			ParameterEdge pwa1 = valuea1.getOutParameterEdges().get(0);
			assertTrue(pwa1.toString(), pwa1.getTo() instanceof RunAction);
			ParameterEdge pwa2 = valuea2.getOutParameterEdges().get(0);
			assertTrue(pwa2.toString(), pwa2.getTo() instanceof RunAction);
			ParameterEdge pwa3 = valuea3.getOutParameterEdges().get(0);
			assertTrue(pwa3.toString(), pwa3.getTo() instanceof RunAction);
		}

		// test for 'access' events and 'initialize' operations (new)
		EventTrigger access1 = f1.getOnAccess();
		EventTrigger access2 = f2.getOnAccess();
		EventTrigger access3 = f3.getOnAccess();
		Operation op1 = assertHasOperation(f1, "init");
		Operation op2 = assertHasOperation(f2, "init");
		Operation op3 = assertHasOperation(f3, "init");

		// these field values should be parameters to run instance wires
		{
			RunAction rw1 = assertHasRunAction(root, access1, op1);
			RunAction rw2 = assertHasRunAction(root, access2, op2);
			RunAction rw3 = assertHasRunAction(root, access3, op3);

			getParameterEdgeFromTo(root, valuea1, rw1);
			getParameterEdgeFromTo(root, valuea2, rw2);
			getParameterEdgeFromTo(root, valuea3, rw3);
		}

	}

	/**
	 * Test the contents of each init operation, used to
	 * initialise the field at access.
	 *
	 * Same as {@link SyncWireTestCase#testAllUpdates()}.
	 *
	 * @throws JaxenException
	 */
	public void testInitContents() throws JaxenException {
		// get all 'initialise' operations
		List<?> inits = query(root, "//iaml:operations[iaml:name='init']");

		// there are 3 input texts => there should be at least 3 initialise operations
		assertGreaterEq(3, inits.size());

		int i = 0;
		for (Object obj : inits) {
			i++;
			String prelude = "'init' operation #" + i;
			CompositeOperation update = (CompositeOperation) obj;	// should be a composite operation
			assertEquals(prelude, update.getName(), "init");

			// -- traverse from start node --
			StartNode start = assertHasStartNode(update);
			FinishNode finish = assertHasFinishNode(update);
			CancelNode cancel = assertHasCancelNode(update);
			
			DecisionOperation check = assertHasDecisionOperation(update, "can cast?");
			CastNode cast = assertHasCastNode(update);
			
			PrimitiveOperation set = assertHasPrimitiveOperation(update, "setPropertyToValue");
			
			assertHasExecutionEdge(update, start, check);
			assertHasExecutionEdge(update, check, cancel, "no");
			assertHasExecutionEdge(update, check, set, "yes");
			assertHasExecutionEdge(update, set, finish);
			
			// data flow edges
			Parameter param = assertHasParameter(update, "setValueTo");
			assertHasDataFlowEdge(update, param, cast);
			assertHasDataFlowEdge(update, cast, check);
			assertHasDataFlowEdge(update, cast, set);

			assertEquals(1, set.getOutFlows().size());
			Property f2 = (Property) set.getOutFlows().get(0).getTo();
			assertEquals("fieldValue", f2.getName());
		}
	}

	/**
	 * Get a NamedElement with a given name, or fail.
	 *
	 * @param nodes
	 * @param name
	 * @return
	 */
	protected NamedElement getNodeWithName(List<?> nodes, String name) {
		for (Object n : nodes) {
			if (n instanceof NamedElement && name.equals(((NamedElement) n).getName()))
				return (NamedElement) n;
		}
		fail("No node found with name '" + name + "'");
		return null;
	}

}
