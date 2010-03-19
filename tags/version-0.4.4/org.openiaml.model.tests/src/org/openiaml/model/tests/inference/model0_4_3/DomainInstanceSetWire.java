/**
 *
 */
package org.openiaml.model.tests.inference.model0_4_3;

import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.QueryParameter;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Inference of the EntryGate model completion rules.
 *
 * @author jmwright
 *
 */
public class DomainInstanceSetWire extends ValidInferenceTestCase {

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return DomainInstanceSetWire.class;
	}

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(DomainInstanceSetWire.class);
		
		Frame page = assertHasFrame(root, "Home");
		QueryParameter param = assertHasQueryParameter(page, "id");
		
		DomainObjectInstance viewing = assertHasDomainObjectInstance(page, "viewing instance");
		InputForm view = assertHasInputForm(page, "View");
		
		Frame add = assertHasFrame(root, "Add New Product");
		
		DomainObjectInstance editing = assertHasDomainObjectInstance(add, "New Product");
		InputForm edit = assertHasInputForm(add, "Edit Form");
		Button save = assertHasButton(add, "save");
		
		DomainStore store = assertHasDomainStore(root, "Store");
		DomainObject product = assertHasDomainObject(store, "Product");
		DomainAttribute id = assertHasDomainAttribute(product, "id");
		DomainAttribute name = assertHasDomainAttribute(product, "name");
		DomainAttribute price = assertHasDomainAttribute(product, "price");
		
		// none of these are generated
		assertNotGenerated(page);
		assertNotGenerated(param);
		assertNotGenerated(viewing);
		assertNotGenerated(view);
		assertNotGenerated(add);
		assertNotGenerated(editing);
		assertNotGenerated(edit);
		assertNotGenerated(save);
		
		assertNotGenerated(store);
		assertNotGenerated(product);
		assertNotGenerated(id);
		assertNotGenerated(name);
		assertNotGenerated(price);
		
	}
	
	/**
	 * The New Object form will have three text fields relating to
	 * the attributes of the new object. These will be connected by SyncWires.
	 * 
	 * <p>There will be no Labels generated.
	 * 
	 * @throws Exception
	 */
	public void testEditForm() throws Exception {
		root = loadAndInfer(DomainInstanceSetWire.class);

		Frame add = assertHasFrame(root, "Add New Product");
		
		DomainObjectInstance editing = assertHasDomainObjectInstance(add, "New Product");
		InputForm edit = assertHasInputForm(add, "Edit Form");
		
		// text fields
		InputTextField tid = assertHasInputTextField(edit, "id");
		InputTextField tname = assertHasInputTextField(edit, "name");
		InputTextField tprice = assertHasInputTextField(edit, "price");
		assertGenerated(tid);
		assertGenerated(tname);
		assertGenerated(tprice);
		
		// no labels
		assertHasNone(edit, "iaml:children", Label.class);
		
		// domain object instance, generated attributes
		DomainAttributeInstance aid = assertHasDomainAttributeInstance(editing, "id");
		DomainAttributeInstance aname = assertHasDomainAttributeInstance(editing, "name");
		DomainAttributeInstance aprice = assertHasDomainAttributeInstance(editing, "price");
		assertGenerated(aid);
		assertGenerated(aname);
		assertGenerated(aprice);
		
		// connected up by sync wires
		assertGenerated(assertHasSyncWire(add, aid, tid));
		assertGenerated(assertHasSyncWire(add, aname, tname));
		assertGenerated(assertHasSyncWire(add, aprice, tprice));
		
		// no set wires
		assertHasNoSetWire(add, aid, tid);
		assertHasNoSetWire(add, tid, aid);

	}
	
	/**
	 * The View form will have three <em>labels</em> relating to
	 * the attributes of the existing object. These will be connected by SetWires.
	 * 
	 * <p>There will be no text fields generated.
	 * 
	 * @throws Exception
	 */
	public void testViewForm() throws Exception {
		root = loadAndInfer(DomainInstanceSetWire.class);

		Frame page = assertHasFrame(root, "Home");
		
		DomainObjectInstance viewing = assertHasDomainObjectInstance(page, "viewing instance");
		InputForm view = assertHasInputForm(page, "View");
		
		// labels
		Label tid = assertHasLabel(view, "id");
		Label tname = assertHasLabel(view, "name");
		Label tprice = assertHasLabel(view, "price");
		assertGenerated(tid);
		assertGenerated(tname);
		assertGenerated(tprice);
		
		// no text fields
		assertHasNone(view, "iaml:children", InputTextField.class);
		
		// domain object instance, generated attributes
		DomainAttributeInstance aid = assertHasDomainAttributeInstance(viewing, "id");
		DomainAttributeInstance aname = assertHasDomainAttributeInstance(viewing, "name");
		DomainAttributeInstance aprice = assertHasDomainAttributeInstance(viewing, "price");
		assertGenerated(aid);
		assertGenerated(aname);
		assertGenerated(aprice);
		
		// connected up by set wires
		assertGenerated(assertHasSetWire(page, aid, tid));
		assertGenerated(assertHasSetWire(page, aname, tname));
		assertGenerated(assertHasSetWire(page, aprice, tprice));
		
		// no sync wires
		assertHasNoSyncWire(page, aid, tid);

	}
	
	
}
