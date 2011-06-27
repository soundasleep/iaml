/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_3;

import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.domain.DomainAttribute;
import org.openiaml.model.model.domain.DomainAttributeInstance;
import org.openiaml.model.model.domain.DomainInstance;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainType;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.IteratorList;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 *
 *
 * @author jmwright
 */
public class DetailWireOnSetIteratorList extends ValidInferenceTestCase {

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

		DomainType schema = assertHasDomainType(root, "schema");
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

		DomainType schema = assertHasDomainType(root, "schema");

		// a generated primary key
		assertHasDomainAttribute(schema, "generated primary key");

		// List has 'attribute' as a Label
		Label lattr = assertHasLabel(list, "attribute");
		assertTrue(lattr.isVisible());

		// generated primary key is stored as a hidden
		Label hpk = assertHasLabel(list, "generated primary key");
		assertFalse(hpk.isVisible());

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
		Label hpk = assertHasLabel(list, "generated primary key");

		Button link = assertHasButton(list, "link");
		assertGenerated(link);

		Event onClick = link.getOnClick();
		assertGenerated(onClick);

		ECARule edge = assertHasECARule(root, onClick, target);
		assertGenerated(link);

		// with the parameter
		Value pkvalue = assertHasFieldValue(hpk);
		assertGenerated(pkvalue);

		assertGenerated(assertHasParameter(root, pkvalue, edge));

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
		DomainInstance instance = iterator.getCurrentInstance();
		assertGenerated(instance);
		IteratorList list = assertHasIteratorList(home, "List");
		Label hpk = assertHasLabel(list, "generated primary key");

		DomainAttributeInstance dai = assertHasDomainAttributeInstance(instance, "generated primary key");
		assertGenerated(dai);
		assertGenerated(assertHasSetWire(root, dai, hpk));

	}

	/**
	 * IteratorLists must <em>NOT</em> have update operations; what does it mean
	 * to update the value of a list?
	 *
	 * @throws Exception
	 */
	public void testIteratorListMustNotHaveUpdateOperation() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		IteratorList list = assertHasIteratorList(home, "List");

		assertHasNoOperation(list, "update");
	}

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return getClass();
	}

}
