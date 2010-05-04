/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.Set;

import org.openiaml.model.model.Condition;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.PrimitiveCondition;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.NewInstanceWire;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.RunAction;
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
	 * an 'empty' condition check.
	 *
	 * @throws Exception
	 */
	public void testSelectWireNoExistsCheck() throws Exception {
		root = loadAndInfer(DomainInheritanceEditing.class);

		DomainStore store = assertHasDomainStore(root, "domain store");
		DomainObject student = assertHasDomainObject(store, "Student");

		Frame page = assertHasFrame(root, "get student");
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

		EventTrigger access = field.getOnAccess();
		assertGenerated(access);

		Operation init = assertHasOperation(field, "init");
		assertGenerated(init);

		RunAction run = assertHasRunAction(field, access, init, "run");
		assertGenerated(run);

		// a parameter
		DomainAttributeInstance nameInstance = assertHasDomainAttributeInstance(studentInstance, "name");
		Property instanceValue = assertHasFieldValue(nameInstance);
		ParameterEdge param = assertHasParameterEdge(root, instanceValue, run);
		assertGenerated(param);

		// the condition on the object instance
		Condition exists = studentInstance.getEmpty();
		assertGenerated(exists);

		// NOT connected to the run instance wire
		Set<ConditionEdge> conds = getConditionEdgesFromTo(root, exists, run);
		assertEquals(conds.toString(), 0, conds.size());
	}

	/**
	 * If a text field is being initialised with an attribute
	 * stored in an object from a NewInstanceWire, it should include
	 * an 'empty' condition check.
	 *
	 * @throws Exception
	 */
	public void testNewInstanceWireExistsCheck() throws Exception {
		root = loadAndInfer(DomainInheritanceEditing.class);

		DomainStore store = assertHasDomainStore(root, "domain store");
		DomainObject student = assertHasDomainObject(store, "Student");

		Frame page = assertHasFrame(root, "create a new student without autosave");
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

		EventTrigger access = field.getOnAccess();
		assertGenerated(access);

		Operation init = assertHasOperation(field, "init");
		assertGenerated(init);

		RunAction run = assertHasRunAction(field, access, init, "run");
		assertGenerated(run);

		// a parameter
		DomainAttributeInstance nameInstance = assertHasDomainAttributeInstance(studentInstance, "name");
		Property instanceValue = assertHasFieldValue(nameInstance);
		ParameterEdge param = assertHasParameterEdge(root, instanceValue, run);
		assertGenerated(param);

		// the condition on the object instance
		Condition notEmpty = assertHasCondition(studentInstance, "not empty");
		assertGenerated(notEmpty);
		
		// connected to the run instance wire
		ConditionEdge conditionEdge = assertHasConditionEdge(root, notEmpty, run, "check new instance exists");
		assertGenerated(conditionEdge);
		
		// NOT the 'exists?' condition, which is the reverse
		Condition exists = studentInstance.getEmpty();
		assertGenerated(exists);
		
		assertHasNoConditionEdge(root, exists, run, "check new instance exists");

	}

	/**
	 * There is no 'exists?' composite condition, or 'exists?' primitive
	 * condition; in issue 180, this has been replaced by the direct 
	 * 'empty' condition.
	 *
	 * @throws Exception
	 */
	public void testExistsConditions() throws Exception {
		root = loadAndInfer(DomainInheritanceEditing.class);

		Frame page = assertHasFrame(root, "create a new student without autosave");
		DomainObjectInstance instance = assertHasDomainObjectInstance(page, "new student");

		// the 'exists?' PrimitiveCondition
		// it has no PrimitiveCondition or CompositeCondition whatsoever
		assertHasNoCondition(instance, "exists?");
		
		assertNotNull(instance.getEmpty());
		PrimitiveCondition pc = (PrimitiveCondition) instance.getEmpty();
		assertGenerated(pc);

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
