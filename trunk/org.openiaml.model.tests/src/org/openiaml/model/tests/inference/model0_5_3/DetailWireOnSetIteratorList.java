/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_3;

import org.openiaml.model.model.ActionEdge;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.Hidden;
import org.openiaml.model.model.visual.IteratorList;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * 
 * 
 * @author jmwright
 */
public class DetailWireOnSetIteratorList extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(DetailWireOnSetIteratorList.class);
	}

	/**
	 * Test the initial model.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		
		DomainSchema schema = assertHasDomainSchema(root, "schema");
		DomainAttribute attr = assertHasDomainAttribute(schema, "attribute");
		
		Frame target = assertHasFrame(root, "Target Frame");
		
		DomainIterator iterator = assertHasDomainIterator(home, "Iterator");
		assertEquals(3, iterator.getLimit());
		
		IteratorList list = assertHasIteratorList(home, "List");
		assertHasSetWire(root, iterator, list);
		
		assertHasDetailWire(root, list, target);
				
		assertNotGenerated(home, schema, attr, target, iterator, list);

	}
	
	/**
	 * Test the generated contents of the IteratorList.
	 * 
	 * @throws Exception
	 */
	public void testContentsOfList() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		IteratorList list = assertHasIteratorList(home, "List");
		
		DomainSchema schema = assertHasDomainSchema(root, "schema");
		
		// a generated primary key
		assertHasDomainAttribute(schema, "generated primary key");
		
		// List has 'attribute' as a Label
		Label lattr = assertHasLabel(list, "attribute");
		
		// generated primary key is stored as a hidden
		Hidden hpk = assertHasHidden(list, "generated primary key");
		
		// no Label for generated primary key
		assertHasNoLabel(list, "generated primary key");
		
		// no Hidden for attribute
		assertHasNoHidden(list, "attribute");
		
		assertGenerated(lattr, hpk);
		
	}

	/**
	 * There is a link button created in the list, which is connected to the
	 * target page with the hidden attribute (PK) as a parameter.
	 * 
	 * @throws Exception
	 */
	public void testLinkButtonOnList() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		Frame target = assertHasFrame(root, "Target Frame");
		IteratorList list = assertHasIteratorList(home, "List");
		Hidden hpk = assertHasHidden(list, "generated primary key");
		
		Button link = assertHasButton(list, "link");
		assertGenerated(link);
		
		EventTrigger onClick = link.getOnClick();
		assertGenerated(onClick);
		
		ActionEdge edge = assertHasActionEdge(root, onClick, target);
		assertGenerated(link);
		
		// with the parameter
		Property pkvalue = assertHasFieldValue(hpk);
		assertGenerated(pkvalue);
		
		assertGenerated(assertHasParameterEdge(root, pkvalue, edge));
		
		// there is only one parameter
		assertEquals(1, edge.getInParameterEdges().size());
		
	}
	
	/**
	 * The DomainAttributeInstance from the DomainIterator is connected to
	 * the Hidden PK with a SetWire.
	 * 
	 * @throws Exception
	 */
	public void testDomainAttributeInstanceSetsHiddenPK() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		DomainIterator iterator = assertHasDomainIterator(home, "Iterator");
		IteratorList list = assertHasIteratorList(home, "List");
		Hidden hpk = assertHasHidden(list, "generated primary key");
		
		DomainAttributeInstance dai = assertHasDomainAttributeInstance(iterator, "generated primary key");
		assertGenerated(dai);
		assertGenerated(assertHasSetWire(root, dai, hpk));

	}

}
