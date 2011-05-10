/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_2;

import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.EXSDDataType;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.Function;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.domain.DomainAttribute;
import org.openiaml.model.model.domain.DomainAttributeInstance;
import org.openiaml.model.model.domain.DomainInstance;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.IteratorList;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.model.wires.AutocompleteWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 *
 * @author jmwright
 */
public class AutocompleteWireSimple extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(AutocompleteWireSimple.class);
	}

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		assertNotGenerated(home);

		DomainSchema schema = assertHasDomainSchema(root, "Contacts");
		assertNotGenerated(schema);

		DomainAttribute aemail = assertHasDomainAttribute(schema, "email");
		assertNotGenerated(aemail);
		DomainAttribute aname = assertHasDomainAttribute(schema, "name");
		assertNotGenerated(aname);

		DomainIterator iterator = assertHasDomainIterator(home, "Select Contact");
		assertNotGenerated(iterator);
		assertEquals(3, iterator.getLimit());

		Label target = assertHasLabel(home, "email");
		assertNotGenerated(target);
		assertEquals(BuiltinDataTypes.getTypeEmail().getURI(),
				((EXSDDataType) target.getType()).getDefinition().getURI());

		AutocompleteWire ac = assertHasAutocompleteWire(root, iterator, target);
		assertNotGenerated(ac);
		assertEquals(ac.getMatch(), aname);		// matches name

	}

	/**
	 * Tests the input for the DomainIterator.
	 * Normal input, that is - onChange, etc - still updates the Iterator.
	 *
	 * @throws Exception
	 */
	public void testIteratorInputNormal() throws Exception {
		Frame home = assertHasFrame(root, "Home");

		// a containing form for the search field, and the iterator list
		// same name as the iterator
		InputForm containerForm = assertHasInputForm(home, "Select Contact");
		assertGenerated(containerForm);

		// an input is created to enter in a name
		InputTextField input = assertHasInputTextField(containerForm, "Search by name");
		assertGenerated(input);

		// the query is changed to 'matches(name, :name)'
		DomainIterator iterator = assertHasDomainIterator(home, "Select Contact");
		assertEquals("matches(name, :name)", iterator.getQuery());

		// it has an incoming parameter
		Value fieldValue = assertHasFieldValue(input);
		Parameter param = assertHasParameter(root, fieldValue, iterator);
		assertEquals("name", param.getName());

	}

	/**
	 * Input.onInput calls Input.update(Input.currentInput).
	 *
	 * @throws Exception
	 */
	public void testIteratorInputInstant() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm containerForm = assertHasInputForm(home, "Select Contact");

		// an input is created to enter in a name
		InputTextField input = assertHasInputTextField(containerForm, "Search by name");
		assertGenerated(input);

		Event onInput = input.getOnInput();
		assertGenerated(onInput);

		Operation update = assertHasOperation(input, "update");
		assertGenerated(update);

		ECARule run = assertHasRunAction(root, onInput, update);
		assertGenerated(run);

		// currentInput as parameter
		Value currentInput = assertHasCurrentInput(input);
		assertGenerated(currentInput);

		assertGenerated(assertHasParameter(root, currentInput, run));

	}

	/**
	 * An IteratorList is created and populated by the DomainIterator.
	 *
	 * @throws Exception
	 */
	public void testIteratorListCreated() throws Exception {

		DomainSchema schema = assertHasDomainSchema(root, "Contacts");
		DomainAttribute aemail = assertHasDomainAttribute(schema, "email");
		DomainAttribute aname = assertHasDomainAttribute(schema, "name");

		Frame home = assertHasFrame(root, "Home");
		DomainIterator iterator = assertHasDomainIterator(home, "Select Contact");
		DomainInstance instance = iterator.getCurrentInstance();
		assertGenerated(instance);
		InputForm containerForm = assertHasInputForm(home, "Select Contact");

		// same name as the iterator
		IteratorList list = assertHasIteratorList(containerForm, "Select Contact");
		assertGenerated(list);

		// connected by SetWire
		assertGenerated(assertHasSetWire(root, iterator, list));

		// check contents of Iterator
		DomainAttributeInstance iname = assertHasDomainAttributeInstance(instance, "name");
		DomainAttributeInstance iemail = assertHasDomainAttributeInstance(instance, "email");

		// test contents of list
		Label lname = assertHasLabel(list, "name");
		Label lemail = assertHasLabel(list, "email");

		// identical types
		assertEqualType(aemail, iemail);
		assertEqualType(iemail, lemail);
		assertEqualType(aname, iname);
		assertEqualType(iname, lname);

		assertNotEqualType(aemail, aname);
		assertNotEqualType(aemail, iname);

	}

	/**
	 * When we click a Label in the IteratorList, we populate the target
	 * with the desired attribute (email).
	 * Checks the 'name' label.
	 *
	 * @throws Exception
	 */
	public void testCreatedLabelOnclickPopulatesTarget_Name() throws Exception {

		DomainSchema schema = assertHasDomainSchema(root, "Contacts");
		DomainAttribute aemail = assertHasDomainAttribute(schema, "email");
		Frame home = assertHasFrame(root, "Home");
		InputForm containerForm = assertHasInputForm(home, "Select Contact");
		IteratorList list = assertHasIteratorList(containerForm, "Select Contact");
		Label lname = assertHasLabel(list, "name");
		Label lemail = assertHasLabel(list, "email");

		// for 'name' label
		Label targetLabel = lname;

		Value email = assertHasFieldValue(lemail);
		assertGenerated(email);
		assertEqualType(email, aemail);

		Event onClick = targetLabel.getOnClick();
		assertGenerated(onClick);

		Label target = assertHasLabel(home, "email");
		Operation update = assertHasOperation(target, "update");
		assertGenerated(update);

		ECARule run = assertHasRunAction(root, onClick, update);
		assertGenerated(run);

		// with the email as the parameter
		assertGenerated(assertHasParameter(root, email, run));

	}

	/**
	 * When we click a Label in the IteratorList, we populate the target
	 * with the desired attribute (email).
	 * Checks the 'email' label.
	 *
	 * @throws Exception
	 */
	public void testCreatedLabelOnclickPopulatesTarget_Email() throws Exception {

		DomainSchema schema = assertHasDomainSchema(root, "Contacts");
		DomainAttribute aemail = assertHasDomainAttribute(schema, "email");
		Frame home = assertHasFrame(root, "Home");
		InputForm containerForm = assertHasInputForm(home, "Select Contact");
		IteratorList list = assertHasIteratorList(containerForm, "Select Contact");
		Label lemail = assertHasLabel(list, "email");

		// for 'name' label
		Label targetLabel = lemail;

		Value email = assertHasFieldValue(lemail);
		assertGenerated(email);
		assertEqualType(email, aemail);

		Event onClick = targetLabel.getOnClick();
		assertGenerated(onClick);

		Label target = assertHasLabel(home, "email");
		Operation update = assertHasOperation(target, "update");
		assertGenerated(update);

		ECARule run = assertHasRunAction(root, onClick, update);
		assertGenerated(run);

		// with the email as the parameter
		assertGenerated(assertHasParameter(root, email, run));

	}

	/**
	 * On access, the IteratorList is hidden, unless the input is not empty.
	 *
	 * @throws Exception
	 */
	public void testFrameOnAccessHidesIteratorList() throws Exception {

		Frame home = assertHasFrame(root, "Home");
		InputForm containerForm = assertHasInputForm(home, "Select Contact");
		IteratorList list = assertHasIteratorList(containerForm, "Select Contact");
		InputTextField input = assertHasInputTextField(containerForm, "Search by name");

		Event onAccess = home.getOnAccess();
		assertGenerated(onAccess);

		{
			Operation hide = assertHasBuiltinOperation(list, "hide");
			assertGenerated(hide);

			ECARule run = assertHasRunAction(root, onAccess, hide);
			assertGenerated(run);

			// only if the input is empty
			Function empty = input.getEmpty();
			assertGenerated(empty);

			assertGenerated(assertHasSimpleCondition(root, empty, run));
		}

		{
			Operation hide = assertHasBuiltinOperation(list, "show");
			assertGenerated(hide);

			ECARule run = assertHasRunAction(root, onAccess, hide);
			assertGenerated(run);

			// only if the input is not empty
			Function empty = input.getNotEmpty();
			assertGenerated(empty);

			assertGenerated(assertHasSimpleCondition(root, empty, run));
		}

	}

	/**
	 * When input is entered, the list is shown if not empty. (check onChange)
	 *
	 * @throws Exception
	 */
	public void testOnChangeShowsList() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm containerForm = assertHasInputForm(home, "Select Contact");
		IteratorList list = assertHasIteratorList(containerForm, "Select Contact");
		InputTextField input = assertHasInputTextField(containerForm, "Search by name");

		Event onChange = input.getOnChange();

		// only if the input is empty
		{
			Operation hide = assertHasBuiltinOperation(list, "hide");
			ECARule run = assertHasRunAction(root, onChange, hide);
			Function empty = input.getEmpty();
			assertGenerated(assertHasSimpleCondition(root, empty, run));
		}

		// only if the input is not empty
		{
			Operation show = assertHasBuiltinOperation(list, "show");
			ECARule run = assertHasRunAction(root, onChange, show);
			Function notEmpty = input.getNotEmpty();
			assertGenerated(assertHasSimpleCondition(root, notEmpty, run));
		}

	}

	/**
	 * When we click a result, we also reset the search text.
	 *
	 * @throws Exception
	 */
	public void testClickResetsFieldValue() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm containerForm = assertHasInputForm(home, "Select Contact");
		IteratorList list = assertHasIteratorList(containerForm, "Select Contact");
		Label targetLabel = assertHasLabel(list, "email");
		Event onClick = targetLabel.getOnClick();

		InputTextField input = assertHasInputTextField(containerForm, "Search by name");

		Operation update = assertHasOperation(input, "update");

		ECARule run = assertHasRunAction(root, onClick, update);
		assertGenerated(run);

		Value blank = assertHasValue(root, "blank");
		assertTrue(blank.isReadOnly());
		assertEqualType(blank.getType(), BuiltinDataTypes.getTypeString());
		assertEquals(blank.getDefaultValue(), "");

		// with the email as the parameter
		assertGenerated(assertHasParameter(root, blank, run));

	}

}
