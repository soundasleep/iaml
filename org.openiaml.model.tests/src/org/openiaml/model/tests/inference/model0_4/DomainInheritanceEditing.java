/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.Set;

import org.openiaml.model.model.BuiltinProperty;
import org.openiaml.model.model.ComplexTerm;
import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.Function;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.SimpleCondition;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.domain.DomainAttributeInstance;
import org.openiaml.model.model.domain.DomainInstance;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
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
	 * an 'empty' Function check.
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

		Event access = field.getOnAccess();
		assertGenerated(access);

		Operation init = assertHasOperation(field, "init");
		assertGenerated(init);

		ECARule run = assertHasRunAction(field, access, init, "run");
		assertGenerated(run);

		// a parameter
		DomainAttributeInstance nameInstance = assertHasDomainAttributeInstance(instance, "name");
		Value instanceValue = assertHasFieldValue(nameInstance);
		Parameter param = assertHasParameter(root, instanceValue, run);
		assertGenerated(param);

		// the Function on the object instance
		Function exists = studentInstance.getEmpty();
		assertGenerated(exists);

		// NOT connected to the run instance wire
		Set<ComplexTerm> conds = getComplexTermsFromTo(root, exists, run);
		assertEquals(conds.toString(), 0, conds.size());
	}

	/**
	 * If a text field is being initialised with an attribute
	 * stored in an object from a NewInstanceWire, it should include
	 * an 'empty' Function check.
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

		Event access = field.getOnAccess();
		assertGenerated(access);

		Operation init = assertHasOperation(field, "init");
		assertGenerated(init);

		ECARule run = assertHasRunAction(field, access, init, "run");
		assertGenerated(run);

		// a parameter
		DomainAttributeInstance nameInstance = assertHasDomainAttributeInstance(instance, "name");
		Value instanceValue = assertHasFieldValue(nameInstance);
		Parameter param = assertHasParameter(root, instanceValue, run);
		assertGenerated(param);

		// the AttributeInstance and the InputTextField should be synchronised too
		assertGenerated(assertHasSyncWire(root, field, nameInstance));

		// the Function on the object instance
		Function notEmpty = assertHasFunction(studentInstance, "not empty");
		assertGenerated(notEmpty);

		// connected to the run instance wire
		SimpleCondition conditionEdge = assertHasSimpleCondition(root, notEmpty, run, "check new instance exists");
		assertGenerated(conditionEdge);

		// NOT the 'exists?' condition, which is the reverse
		Function exists = studentInstance.getEmpty();
		assertGenerated(exists);

		assertHasNoSimpleConditions(root, exists, run, "check new instance exists");

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
		assertHasNoFunction(instance, "exists?");

		assertNotNull(instance.getEmpty());
		BuiltinProperty pc = (BuiltinProperty) instance.getEmpty();
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
