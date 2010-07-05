/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_2;

import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.model.ActionEdge;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.domain.DomainSource;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.IteratorList;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Basic inference of an IteratorList connected to a DomainIterator by
 * a SetWire.
 * 
 * @author jmwright
 */
public class IteratorListSetWire extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(IteratorListSetWire.class);
	}
	
	/**
	 * Test the initial model.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		
		DomainSchema news = assertHasDomainSchema(root, "News");
		DomainSource db = assertHasDomainSource(root, "Database");
		assertHasSchemaEdge(db, news);
		
		IteratorList list = assertHasIteratorList(home, "List");
		DomainIterator iterator = assertHasDomainIterator(home, "select three news");
		assertEquals(3, iterator.getLimit());
		
		assertHasSetWire(root, iterator, list);
		
		// iterator has a source
		assertHasSelectEdge(iterator, db);
		
		// attributes in the schema
		DomainAttribute id = assertHasDomainAttribute(news, "id");
		DomainAttribute title = assertHasDomainAttribute(news, "title");
		DomainAttribute content = assertHasDomainAttribute(news, "content");

		assertEquals(id.getType().getURI(), BuiltinDataTypes.getTypeInteger().getURI());
		assertEquals(title.getType().getURI(), BuiltinDataTypes.getTypeString().getURI());
		assertEquals(content.getType().getURI(), BuiltinDataTypes.getTypeString().getURI());
		
	}
	
	/**
	 * The DomainIterator will have DomainAttributeInstances created.
	 * 
	 * @throws Exception
	 */
	public void testDomainIteratorHasDomainAttributes() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		DomainSchema news = assertHasDomainSchema(root, "News");
		DomainIterator iterator = assertHasDomainIterator(home, "select three news");
		
		DomainAttributeInstance iid = assertHasDomainAttributeInstance(iterator, "id");
		DomainAttributeInstance ititle = assertHasDomainAttributeInstance(iterator, "title");
		DomainAttributeInstance icontent = assertHasDomainAttributeInstance(iterator, "content");
		
		assertGenerated(iid);
		assertGenerated(ititle);
		assertGenerated(icontent);

		// correct data types
		DomainAttribute id = assertHasDomainAttribute(news, "id");
		DomainAttribute title = assertHasDomainAttribute(news, "title");
		DomainAttribute content = assertHasDomainAttribute(news, "content");

		assertEquals(id.getType().getURI(), iid.getType().getURI());
		assertEquals(title.getType().getURI(), ititle.getType().getURI());
		assertEquals(content.getType().getURI(), icontent.getType().getURI());
		
	}
	
	/**
	 * The IteratorList will have Labels created within it.
	 * 
	 * @throws Exception
	 */
	public void testLabelsCreatedInList() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		DomainSchema news = assertHasDomainSchema(root, "News");
		IteratorList list = assertHasIteratorList(home, "List");
		
		Label lid = assertHasLabel(list, "id");
		Label ltitle = assertHasLabel(list, "title");
		Label lcontent = assertHasLabel(list, "content");
		
		assertGenerated(lid);
		assertGenerated(ltitle);
		assertGenerated(lcontent);
		
		// same data types
		// attributes in the schema
		DomainAttribute id = assertHasDomainAttribute(news, "id");
		DomainAttribute title = assertHasDomainAttribute(news, "title");
		DomainAttribute content = assertHasDomainAttribute(news, "content");

		assertEquals(id.getType().getURI(), lid.getType().getURI());
		assertEquals(title.getType().getURI(), ltitle.getType().getURI());
		assertEquals(content.getType().getURI(), lcontent.getType().getURI());
		
	}
	
	/**
	 * Each Label within the IteratorList will be connected by a SetWire.
	 * 
	 * @throws Exception
	 */
	public void testLabelsConnectedBySetWire() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		IteratorList list = assertHasIteratorList(home, "List");
		DomainIterator iterator = assertHasDomainIterator(home, "select three news");
		
		Label lid = assertHasLabel(list, "id");
		Label ltitle = assertHasLabel(list, "title");
		Label lcontent = assertHasLabel(list, "content");
		
		DomainAttributeInstance iid = assertHasDomainAttributeInstance(iterator, "id");
		DomainAttributeInstance ititle = assertHasDomainAttributeInstance(iterator, "title");
		DomainAttributeInstance icontent = assertHasDomainAttributeInstance(iterator, "content");
		
		assertGenerated(assertHasSetWire(root, iid, lid));
		assertGenerated(assertHasSetWire(root, ititle, ltitle));
		assertGenerated(assertHasSetWire(root, icontent, lcontent));
	}
	
	/**
	 * DomainAttribute.onChange calls Label.update only if DomainAttribute exists
	 * 
	 * @throws Exception
	 */
	public void testLabelUpdatedOnlyIfAttributeExists() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		IteratorList list = assertHasIteratorList(home, "List");
		DomainIterator iterator = assertHasDomainIterator(home, "select three news");
		
		Label ltitle = assertHasLabel(list, "title");
		DomainAttributeInstance ititle = assertHasDomainAttributeInstance(iterator, "title");
		assertGenerated(assertHasSetWire(root, ititle, ltitle));
		
		EventTrigger onChange = ititle.getOnChange();
		assertGenerated(onChange);
		
		Operation op = assertHasOperation(ltitle, "update");
		assertGenerated(op);
		
		ActionEdge run = assertHasRunAction(root, onChange, op);
		assertGenerated(run);
		
		// with parameter from attr instance
		Property value = assertHasFieldValue(ititle);
		assertGenerated(value);
		assertGenerated(assertHasParameterEdge(root, value, run));
		
		Condition cond = assertHasCondition(iterator, "not empty");
		
		assertGenerated(assertHasConditionEdge(root, cond, run));
	}
	
}
