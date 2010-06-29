/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_2;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jwebunit.api.IElement;

import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

/**
 * @example AutocompleteWire
 * 		Using an {@model AutocompleteWire} to allow the search for
 * 		e-mail addresses using a separate {@model DomainAttribute} for searching.
 * 
 */
public class AutocompleteWireSimple extends DatabaseCodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(getClass());
		initialiseDatabase();
	}

	/**
	 * The home page can be accessed.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
	}
	
	private void assertNoLabelsVisible() {
		assertLabelTextNotPresent("one@openiaml.org");
		assertLabelTextNotPresent("two@openiaml.org");
		assertLabelTextNotPresent("three@openiaml.org");
		assertLabelTextNotPresent("four@openiaml.org");
		assertLabelTextNotPresent("five@openiaml.org");
		assertLabelTextNotPresent("six@openiaml.org");
		assertLabelTextNotPresent("seven@openiaml.org");
		assertLabelTextNotPresent("eight@openiaml.org");
		assertLabelTextNotPresent("nine@openiaml.org");
		assertLabelTextNotPresent("ten@openiaml.org");

		assertLabelTextNotPresent("One");
		assertLabelTextNotPresent("Two");
		assertLabelTextNotPresent("Three");
		assertLabelTextNotPresent("Four");
		assertLabelTextNotPresent("Five");
		assertLabelTextNotPresent("Six");
		assertLabelTextNotPresent("Seven");
		assertLabelTextNotPresent("Eight");
		assertLabelTextNotPresent("Nine");
		assertLabelTextNotPresent("Ten");
	}
	
	/**
	 * Setting a value directly.
	 * TODO should there be an InputTextField.onFocus/onLostFocus?
	 * 
	 * @throws Exception
	 */
	public void testNormalSearch() throws Exception {
		
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// no results are visible
		assertNoLabelsVisible();
		
		{
			String target = getLabelIDForText("Search by name");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "i");
		}
		assertNoProblem();
		
		// we now have 3 results displayed
		// not in any particular order though, so go by ID order
		assertLabelTextNotPresent("one@openiaml.org");
		assertLabelTextNotPresent("two@openiaml.org");
		assertLabelTextNotPresent("three@openiaml.org");
		assertLabelTextNotPresent("four@openiaml.org");
		assertLabelTextExactlyPresent("five@openiaml.org");
		assertLabelTextExactlyPresent("six@openiaml.org");
		assertLabelTextNotPresent("seven@openiaml.org");
		assertLabelTextExactlyPresent("eight@openiaml.org");
		assertLabelTextNotPresent("nine@openiaml.org");
		assertLabelTextNotPresent("ten@openiaml.org");

		assertLabelTextNotPresent("One");
		assertLabelTextNotPresent("Two");
		assertLabelTextNotPresent("Three");
		assertLabelTextNotPresent("Four");
		assertLabelTextExactlyPresent("Five");
		IElement label = assertLabelTextExactlyPresent("Six");
		assertLabelTextNotPresent("Seven");
		assertLabelTextExactlyPresent("Eight");
		assertLabelTextNotPresent("Nine");
		assertLabelTextNotPresent("Ten");
		
		// click on the name
		clickElement(label);
		assertNoProblem();
		
		// the labels disappear
		assertLabelTextNotPresent("one@openiaml.org");
		assertLabelTextNotPresent("two@openiaml.org");
		assertLabelTextNotPresent("three@openiaml.org");
		assertLabelTextNotPresent("four@openiaml.org");
		assertLabelTextNotPresent("five@openiaml.org");
		assertLabelTextNotPresent("seven@openiaml.org");
		assertLabelTextNotPresent("eight@openiaml.org");
		assertLabelTextNotPresent("nine@openiaml.org");
		assertLabelTextNotPresent("ten@openiaml.org");

		assertLabelTextNotPresent("One");
		assertLabelTextNotPresent("Two");
		assertLabelTextNotPresent("Three");
		assertLabelTextNotPresent("Four");
		assertLabelTextNotPresent("Five");
		assertLabelTextNotPresent("Six");
		assertLabelTextNotPresent("Seven");
		assertLabelTextNotPresent("Eight");
		assertLabelTextNotPresent("Nine");
		assertLabelTextNotPresent("Ten");
		
		// except the desired email
		assertLabelTextExactlyPresent("six@openiaml.org");
		
		// and the field value should be reset
		{
			String target = getLabelIDForText("Search by name");
			assertLabeledFieldEquals(target, "");
		}
		
	}
	
	/**
	 * Searching for an invalid value shouldn't throw an exception.
	 * 
	 * @throws Exception
	 */
	public void testInvalidSearch() throws Exception {
		
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// no results are visible
		assertNoLabelsVisible();
		
		{
			String target = getLabelIDForText("Search by name");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, "invalid");
		}
		assertNoProblem();
		
	}
	
	/**
	 * Populate the database with e-mail contacts.
	 */
	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE Contacts (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(64) NOT NULL, email VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO Contacts (generated_primary_key, name, email) VALUES (1, 'One', 'one@openiaml.org')");
		s.add("INSERT INTO Contacts (generated_primary_key, name, email) VALUES (2, 'Two', 'two@openiaml.org')");
		s.add("INSERT INTO Contacts (generated_primary_key, name, email) VALUES (3, 'Three', 'three@openiaml.org')");
		s.add("INSERT INTO Contacts (generated_primary_key, name, email) VALUES (4, 'Four', 'four@openiaml.org')");
		s.add("INSERT INTO Contacts (generated_primary_key, name, email) VALUES (5, 'Five', 'five@openiaml.org')");
		s.add("INSERT INTO Contacts (generated_primary_key, name, email) VALUES (6, 'Six', 'six@openiaml.org')");
		s.add("INSERT INTO Contacts (generated_primary_key, name, email) VALUES (7, 'Seven', 'seven@openiaml.org')");
		s.add("INSERT INTO Contacts (generated_primary_key, name, email) VALUES (8, 'Eight', 'eight@openiaml.org')");
		s.add("INSERT INTO Contacts (generated_primary_key, name, email) VALUES (9, 'Nine', 'nine@openiaml.org')");
		s.add("INSERT INTO Contacts (generated_primary_key, name, email) VALUES (10, 'Ten', 'ten@openiaml.org')");
		return s;
	}
	
}
