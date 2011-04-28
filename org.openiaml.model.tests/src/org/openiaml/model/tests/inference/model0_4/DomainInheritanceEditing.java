/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.Set;

import org.openiaml.model.model.ActionEdge;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.PrimitiveCondition;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.domain.DomainAttributeInstance;
import org.openiaml.model.model.domain.DomainInstance;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ParameterEdge;
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

		DomainSchema student = assertHasDomainSchema(root, "Student");
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

		Frame page = assertHasFrame(root, "get student");
		InputForm form = assertHasInputForm(page, "view student");
		DomainIterator studentInstance = assertHasDomainIterator(page, "current student");
		DomainInstance instance = studentInstance.getCurrentInstance();
		assertGenerated(instance);
		
		SyncWire sync = assertHasSyncWire(root, studentInstance, form, "sync");
		assertNotGenerated(sync);

		InputTextField field = assertHasInputTextField(form, "name");
		assertGenerated(field);

		EventTrigger access = field.getOnAccess();
		assertGenerated(access);

		Operation init = assertHasOperation(field, "init");
		assertGenerated(init);

		ActionEdge run = assertHasRunAction(field, access, init, "run");
		assertGenerated(run);

		// a parameter
		DomainAttributeInstance nameInstance = assertHasDomainAttributeInstance(instance, "name");
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

		Frame page = assertHasFrame(root, "create a new student without autosave");
		InputForm form = assertHasInputForm(page, "new student form");
		DomainIterator studentInstance = assertHasDomainIterator(page, "new student");
		DomainInstance instance = studentInstance.getCurrentInstance();
		assertGenerated(instance);
		
		SyncWire sync = assertHasSyncWire(root, studentInstance, form, "sync");
		assertNotGenerated(sync);

		InputTextField field = assertHasInputTextField(form, "name");
		assertGenerated(field);

		EventTrigger access = field.getOnAccess();
		assertGenerated(access);

		Operation init = assertHasOperation(field, "init");
		assertGenerated(init);

		ActionEdge run = assertHasRunAction(field, access, init, "run");
		assertGenerated(run);

		// a parameter
		DomainAttributeInstance nameInstance = assertHasDomainAttributeInstance(instance, "name");
		Property instanceValue = assertHasFieldValue(nameInstance);
		ParameterEdge param = assertHasParameterEdge(root, instanceValue, run);
		assertGenerated(param);
		
		// the AttributeInstance and the InputTextField should be synchronised too
		assertGenerated(assertHasSyncWire(root, field, nameInstance));

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
		DomainIterator instance = assertHasDomainIterator(page, "new student");

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
