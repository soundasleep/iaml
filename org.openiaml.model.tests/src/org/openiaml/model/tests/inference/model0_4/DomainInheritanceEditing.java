/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ConditionWire;
import org.openiaml.model.model.wires.NewInstanceWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Inference of access control handlers.
 *
 * @author jmwright
 *
 */
public class DomainInheritanceEditing extends InferenceTestCase {

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(DomainInheritanceEditing.class);
		
		DomainStore store = assertHasDomainStore(root, "domain store");
		assertNotGenerated(store);
		DomainObject student = assertHasDomainObject(store, "Student");
		assertNotGenerated(student);

	}
	
	/**
	 * If a text field is being initialised with an attribute
	 * stored in an object from a SelectWire, it should <em>not</em> include
	 * an 'exists?' condition check.
	 * 
	 * @throws Exception
	 */
	public void testSelectWireNoExistsCheck() throws Exception {
		root = loadAndInfer(DomainInheritanceEditing.class);
		
		DomainStore store = assertHasDomainStore(root, "domain store");
		DomainObject student = assertHasDomainObject(store, "Student");
		
		Page page = assertHasPage(root, "get student");
		InputForm form = assertHasInputForm(page, "view student");
		DomainObjectInstance studentInstance = assertHasDomainObjectInstance(page, "current student");
		
		// connected by a SelectWire
		SelectWire select = assertHasSelectWire(root, student, studentInstance, "select");
		assertNotGenerated(select);
		
		// no NewInstanceWires
		assertHasNoWiresFromTo(root, student, studentInstance, NewInstanceWire.class);
		
		SyncWire sync = assertHasSyncWire(root, studentInstance, form, "sync");
		assertNotGenerated(sync);
		
		InputTextField field = assertHasInputTextField(form, "name");
		assertGenerated(field);
		
		EventTrigger access = assertHasEventTrigger(field, "access");
		assertGenerated(access);
		
		Operation init = assertHasOperation(field, "init");
		assertGenerated(init);
		
		RunInstanceWire run = assertHasRunInstanceWire(field, access, init, "run");
		assertGenerated(run);
		
		// a parameter
		DomainAttributeInstance nameInstance = assertHasDomainAttributeInstance(studentInstance, "name");
		ApplicationElementProperty instanceValue = assertHasApplicationElementProperty(nameInstance, "fieldValue");
		ParameterWire param = assertHasParameterWire(root, instanceValue, run);
		assertGenerated(param);
		
		// the condition on the object instance
		Condition exists = assertHasCondition(studentInstance, "exists?");
		assertGenerated(exists);
		
		// NOT connected to the run instance wire
		assertHasNoWiresFromTo(root, exists, run, ConditionWire.class);
	}
	
	/**
	 * If a text field is being initialised with an attribute
	 * stored in an object from a NewInstanceWire, it should include
	 * an 'exists?' condition check.
	 * 
	 * @throws Exception
	 */
	public void testNewInstanceWireExistsCheck() throws Exception {
		root = loadAndInfer(DomainInheritanceEditing.class);
		
		DomainStore store = assertHasDomainStore(root, "domain store");
		DomainObject student = assertHasDomainObject(store, "Student");
		
		Page page = assertHasPage(root, "create a new student without autosave");
		InputForm form = assertHasInputForm(page, "new student form");
		DomainObjectInstance studentInstance = assertHasDomainObjectInstance(page, "new student");
		
		// connected by a NewInstanceWire
		NewInstanceWire newWire = assertHasNewInstanceWire(root, student, studentInstance, "new");
		assertNotGenerated(newWire);
		
		// no SelectWires
		assertHasNoWiresFromTo(root, student, studentInstance, SelectWire.class);
		
		SyncWire sync = assertHasSyncWire(root, studentInstance, form, "sync");
		assertNotGenerated(sync);
		
		InputTextField field = assertHasInputTextField(form, "name");
		assertGenerated(field);
		
		EventTrigger access = assertHasEventTrigger(field, "access");
		assertGenerated(access);
		
		Operation init = assertHasOperation(field, "init");
		assertGenerated(init);
		
		RunInstanceWire run = assertHasRunInstanceWire(field, access, init, "run");
		assertGenerated(run);
		
		// a parameter
		DomainAttributeInstance nameInstance = assertHasDomainAttributeInstance(studentInstance, "name");
		ApplicationElementProperty instanceValue = assertHasApplicationElementProperty(nameInstance, "fieldValue");
		ParameterWire param = assertHasParameterWire(root, instanceValue, run);
		assertGenerated(param);
		
		// the condition on the object instance
		Condition exists = assertHasCondition(studentInstance, "exists?");
		assertGenerated(exists);
		
		// connected to the run instance wire
		ConditionWire conditionWire = assertHasConditionWire(root, exists, run, "check new instance exists");
		assertGenerated(conditionWire);
	}
		
	/**
	 * The inferred model should be valid.
	 * 
	 * @throws Exception
	 */
	public void testInferredModelIsValid() throws Exception {
		checkModelIsValid(loadAndInfer(DomainInheritanceEditing.class));
	}
	
}
