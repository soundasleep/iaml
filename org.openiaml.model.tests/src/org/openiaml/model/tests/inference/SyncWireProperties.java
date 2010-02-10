/**
 *
 */
package org.openiaml.model.tests.inference;

import java.util.List;

import org.jaxen.JaxenException;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;

/**
 * Tests inference of sync wires between a .properties
 * FileDomainStore and an InputForm.
 *
 * @model SyncWireProperties.iaml
 * @author jmwright
 *
 */
public class SyncWireProperties extends InferenceTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(SyncWireProperties.class);
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
		SyncWire wire = (SyncWire) queryOne(page, "//iaml:wires[iaml:name='sync properties']");
		assertNotNull(wire);
		assertEquals(wire.getFrom(), form);
		assertEquals(wire.getTo(), properties);

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
		SyncWire sw1 = (SyncWire) getWireBidirectional(form, f1, a1);
		SyncWire sw2 = (SyncWire) getWireBidirectional(form, f2, a2);
		SyncWire sw3 = (SyncWire) getWireBidirectional(form, f3, a3);

		assertTrue(sw1.isIsGenerated());
		assertTrue(sw2.isIsGenerated());
		assertTrue(sw3.isIsGenerated());

		// there should be RunInstanceWires on each of these attributes
		// (following from SyncFormDomainObject)

		// fields should have fieldValues
		ApplicationElementProperty value1 = assertHasApplicationElementProperty(f1, "fieldValue");
		ApplicationElementProperty value2 = assertHasApplicationElementProperty(f2, "fieldValue");
		ApplicationElementProperty value3 = assertHasApplicationElementProperty(f3, "fieldValue");
		ApplicationElementProperty valuea1 = assertHasApplicationElementProperty(a1, "fieldValue");
		ApplicationElementProperty valuea2 = assertHasApplicationElementProperty(a2, "fieldValue");
		ApplicationElementProperty valuea3 = assertHasApplicationElementProperty(a3, "fieldValue");

		// these field values should be parameters to run instance wires
		{
			ParameterWire pw1 = (ParameterWire) getWireFrom(root, value1);
			assertTrue(pw1.toString(), pw1.getTo() instanceof RunInstanceWire);
			ParameterWire pw2 = (ParameterWire) getWireFrom(root, value2);
			assertTrue(pw2.toString(), pw2.getTo() instanceof RunInstanceWire);
			ParameterWire pw3 = (ParameterWire) getWireFrom(root, value3);
			assertTrue(pw3.toString(), pw3.getTo() instanceof RunInstanceWire);
			ParameterWire pwa1 = (ParameterWire) getWireFrom(root, valuea1);
			assertTrue(pwa1.toString(), pwa1.getTo() instanceof RunInstanceWire);
			ParameterWire pwa2 = (ParameterWire) getWireFrom(root, valuea2);
			assertTrue(pwa2.toString(), pwa2.getTo() instanceof RunInstanceWire);
			ParameterWire pwa3 = (ParameterWire) getWireFrom(root, valuea3);
			assertTrue(pwa3.toString(), pwa3.getTo() instanceof RunInstanceWire);
		}

		// test for 'access' events and 'initialize' operations (new)
		EventTrigger access1 = assertHasEventTrigger(f1, "access");
		EventTrigger access2 = assertHasEventTrigger(f2, "access");
		EventTrigger access3 = assertHasEventTrigger(f3, "access");
		Operation op1 = assertHasOperation(f1, "init");
		Operation op2 = assertHasOperation(f2, "init");
		Operation op3 = assertHasOperation(f3, "init");

		// these field values should be parameters to run instance wires
		{
			RunInstanceWire rw1 = (RunInstanceWire) getWireFromTo(root, access1, op1);
			RunInstanceWire rw2 = (RunInstanceWire) getWireFromTo(root, access2, op2);
			RunInstanceWire rw3 = (RunInstanceWire) getWireFromTo(root, access3, op3);

			ParameterWire pw1 = (ParameterWire) getWireFromTo(root, valuea1, rw1);
			ParameterWire pw2 = (ParameterWire) getWireFromTo(root, valuea2, rw2);
			ParameterWire pw3 = (ParameterWire) getWireFromTo(root, valuea3, rw3);

			assertNotNull(pw1);
			assertNotNull(pw2);
			assertNotNull(pw3);
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
	public void testAllInitialises() throws JaxenException {
		// get all 'initialise' operations
		List<?> inits = query(root, "//iaml:operations[iaml:name='init']");

		// there are 3 input texts => there should be at least 3 initialise operations
		assertGreaterEq(3, inits.size());

		int i = 0;
		for (Object obj : inits) {
			i++;
			String prelude = "'initialise' operation #" + i;
			CompositeOperation init = (CompositeOperation) obj;	// should be a composite operation
			assertEquals(prelude, init.getName(), "init");

			// has a start node
			List<?> nodes = query(init, "iaml:nodes");
			assertEquals(prelude, 2, nodes.size());
			assertTrue(prelude, nodes.get(0) instanceof StartNode);
			assertTrue(prelude, nodes.get(1) instanceof FinishNode);

			// -- traverse from start node --
			StartNode start = (StartNode) nodes.get(0);
			FinishNode stop = (FinishNode) nodes.get(1);

			// start node should go to 'setPropertyToValue'
			assertEquals(prelude, start.getOutExecutions().size(), 1);
			PrimitiveOperation setProp = (PrimitiveOperation) start.getOutExecutions().get(0).getTo();
			assertEquals(prelude, setProp.getName(), "setPropertyToValue");

			// setProperty should have one node: a parameter
			assertEquals(prelude, setProp.getInFlows().size(), 1);
			assertTrue(prelude, setProp.getInFlows().get(0).getFrom() instanceof Parameter);

			// setProperty should flow out to ApplicationElementProperty
			assertEquals(prelude, setProp.getOutFlows().size(), 1);
			ApplicationElementProperty outProp = (ApplicationElementProperty) setProp.getOutFlows().get(0).getTo();
			assertEquals(prelude, outProp.getName(), "fieldValue");

			// this property should belong to an application element with a different name
			assertNotSame(prelude, ((NamedElement) outProp.eContainer()).getName(), "name" + i);

			// finally, the op should go to the stop node above
			assertEquals(prelude, setProp.getOutExecutions().size(), 1);
			FinishNode finalNode = (FinishNode) setProp.getOutExecutions().get(0).getTo();

			assertEquals(prelude, stop, finalNode);
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
