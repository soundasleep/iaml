/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.Set;

import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionOperation;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.OperationCallNode;
import org.openiaml.model.model.operations.StartNode;
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
	 * an 'exists?' condition check.
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
		Condition exists = assertHasCondition(studentInstance, "exists?");
		assertGenerated(exists);

		// NOT connected to the run instance wire
		Set<ConditionEdge> conds = getConditionEdgesFromTo(root, exists, run);
		assertEquals(conds.toString(), 0, conds.size());
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
		Condition exists = assertHasCondition(studentInstance, "exists?");
		assertGenerated(exists);

		// connected to the run instance wire
		ConditionEdge conditionEdge = assertHasConditionEdge(root, exists, run, "check new instance exists");
		assertGenerated(conditionEdge);
	}

	/**
	 * Test the composition of the 'exists?' composite condition.
	 *
	 * @throws Exception
	 */
	public void testExistsConditionCompositiion() throws Exception {
		root = loadAndInfer(DomainInheritanceEditing.class);

		Frame page = assertHasFrame(root, "create a new student without autosave");
		DomainObjectInstance instance = assertHasDomainObjectInstance(page, "new student");

		// the 'exists?' operation
		DecisionOperation decision = assertHasDecisionOperation(instance, "exists?");
		assertGenerated(decision);

		// the condition on the object instance
		CompositeCondition exists = assertHasCompositeCondition(instance, "exists?");
		assertGenerated(exists);

		StartNode start = assertHasStartNode(exists);
		FinishNode finish = assertHasFinishNode(exists);
		CancelNode cancel = assertHasCancelNode(exists);

		// virtual operation call
		OperationCallNode callExists = assertHasOperationCallNode(exists, "exists?");
		assertGenerated(callExists);

		// calls the decision operation
		RunAction virtual = assertHasRunAction(root, callExists, decision, "run");
		assertGenerated(virtual);

		// the rest of the structure
		assertHasExecutionEdge(exists, start, callExists);
		assertHasExecutionEdge(exists, callExists, finish);
		assertHasExecutionEdge(exists, callExists, cancel);

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
