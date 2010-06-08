/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_2;

import org.openiaml.model.datatypes.BuiltinDataTypes;
import org.openiaml.model.model.Changeable;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.IteratorList;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.model.wires.AutocompleteWire;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.RunAction;
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
		assertEquals(BuiltinDataTypes.getTypeEmail().getURI(), target.getType().getURI());
		
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
		Property fieldValue = assertHasFieldValue(input);
		ParameterEdge param = assertHasParameterEdge(root, fieldValue, iterator);
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
		
		EventTrigger onInput = input.getOnInput();
		assertGenerated(onInput);
		
		Operation update = assertHasOperation(input, "update");
		assertGenerated(update);
		
		RunAction run = assertHasRunAction(root, onInput, update);
		assertGenerated(run);
		
		// currentInput as parameter
		Property currentInput = assertHasCurrentInput(input);
		assertGenerated(currentInput);
		
		assertGenerated(assertHasParameterEdge(root, currentInput, run));

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
		InputForm containerForm = assertHasInputForm(home, "Select Contact");
		
		// same name as the iterator
		IteratorList list = assertHasIteratorList(containerForm, "Select Contact");
		assertGenerated(list);
		
		// connected by SetWire
		assertGenerated(assertHasSetWire(root, iterator, list));

		// check contents of Iterator
		DomainAttributeInstance iname = assertHasDomainAttributeInstance(iterator, "name");
		DomainAttributeInstance iemail = assertHasDomainAttributeInstance(iterator, "email");

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
		
		Property email = assertHasFieldValue(lemail);
		assertGenerated(email);
		assertEqualType(email, aemail);
		
		EventTrigger onClick = targetLabel.getOnClick();
		assertGenerated(onClick);
		
		Label target = assertHasLabel(home, "email");
		Operation update = assertHasOperation(target, "update");
		assertGenerated(update);
		
		RunAction run = assertHasRunAction(root, onClick, update);
		assertGenerated(run);
		
		// with the email as the parameter
		assertGenerated(assertHasParameterEdge(root, email, run));
		
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
		
		Property email = assertHasFieldValue(lemail);
		assertGenerated(email);
		assertEqualType(email, aemail);
		
		EventTrigger onClick = targetLabel.getOnClick();
		assertGenerated(onClick);
		
		Label target = assertHasLabel(home, "email");
		Operation update = assertHasOperation(target, "update");
		assertGenerated(update);
		
		RunAction run = assertHasRunAction(root, onClick, update);
		assertGenerated(run);
		
		// with the email as the parameter
		assertGenerated(assertHasParameterEdge(root, email, run));
	
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
		
		EventTrigger onAccess = home.getOnAccess();
		assertGenerated(onAccess);
		
		{
			Operation hide = assertHasPrimitiveOperation(list, "hide");
			assertGenerated(hide);
			
			RunAction run = assertHasRunAction(root, onAccess, hide);
			assertGenerated(run);
			
			// only if the input is empty
			Condition empty = assertHasPrimitiveCondition(input, "empty");
			assertGenerated(empty);
			
			assertGenerated(assertHasConditionEdge(root, empty, run));
		}

		{
			Operation hide = assertHasPrimitiveOperation(list, "show");
			assertGenerated(hide);
			
			RunAction run = assertHasRunAction(root, onAccess, hide);
			assertGenerated(run);
			
			// only if the input is not empty
			Condition empty = assertHasPrimitiveCondition(input, "not empty");
			assertGenerated(empty);
			
			assertGenerated(assertHasConditionEdge(root, empty, run));
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
		
		EventTrigger onChange = input.getOnChange();

		// only if the input is empty
		{
			Operation hide = assertHasPrimitiveOperation(list, "hide");
			RunAction run = assertHasRunAction(root, onChange, hide);
			Condition empty = assertHasPrimitiveCondition(input, "empty");
			assertGenerated(assertHasConditionEdge(root, empty, run));
		}
		
		// only if the input is not empty
		{
			Operation show = assertHasPrimitiveOperation(list, "show");
			RunAction run = assertHasRunAction(root, onChange, show);
			Condition notEmpty = assertHasPrimitiveCondition(input, "not empty");
			assertGenerated(assertHasConditionEdge(root, notEmpty, run));
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
		EventTrigger onClick = targetLabel.getOnClick();
		
		InputTextField input = assertHasInputTextField(containerForm, "Search by name");

		Operation update = assertHasOperation(input, "update");
		
		RunAction run = assertHasRunAction(root, onClick, update);
		assertGenerated(run);

		StaticValue blank = assertHasStaticValue(root, "blank");
		assertEquals(blank.getType(), BuiltinDataTypes.getTypeString());
		assertEquals(blank.getValue(), "");
		
		// with the email as the parameter
		assertGenerated(assertHasParameterEdge(root, blank, run));
	
	}

	/**
	 * Are the two elements of the same type? That is, do their type URIs
	 * match?
	 */
	protected void assertEqualType(Changeable a,
			Changeable b) {
		assertEquals(a.getType().getURI(), b.getType().getURI());
	}

	/**
	 * Are the two elements of the same type? That is, do their type URIs
	 * match?
	 */
	protected void assertEqualType(Property a,
			Changeable b) {
		assertEquals(a.getType().getURI(), b.getType().getURI());
	}
	
	/**
	 * Are the two elements of the same type? That is, do their type URIs
	 * match?
	 */
	protected void assertEqualType(Changeable a,
			Property b) {
		assertEquals(a.getType().getURI(), b.getType().getURI());
	}
	
	/**
	 * Are the two elements of the same type? That is, do their type URIs
	 * match?
	 */
	protected void assertEqualType(Property a,
			Property b) {
		assertEquals(a.getType().getURI(), b.getType().getURI());
	}
	
	/**
	 * Are the two elements <em>not</em> of the same type? That is, do their type URIs
	 * not match?
	 */
	protected void assertNotEqualType(Changeable a,
			Changeable b) {
		assertNotEqual(a.getType().getURI(), b.getType().getURI());
	}
	
}
