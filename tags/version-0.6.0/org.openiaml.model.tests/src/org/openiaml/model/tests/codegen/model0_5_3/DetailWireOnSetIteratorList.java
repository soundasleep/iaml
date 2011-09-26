/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_3;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jwebunit.api.IElement;

import org.openiaml.model.tests.codegen.DatabaseCodegenTestCase;

/**
 * 
 * @example DetailWire
 * 		Using a {@model DetailWire} to provide more detail to a
 * 		{@model IteratorList} selected through a {@model DomainIterator}.
 * 
 */
public class DetailWireOnSetIteratorList extends DatabaseCodegenTestCase {

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
		
		// there are three labels visible
		assertLabelTextExactlyPresent("Content One");
		assertLabelTextExactlyPresent("Content Two");
		assertLabelTextExactlyPresent("Content Three");
		assertLabelTextNotPresent("Content Four");
		assertLabelTextNotPresent("Content Five");		
	}

	/**
	 * Click the first button.
	 * 
	 * @throws Exception
	 */
	public void testClickButton1() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// three buttons
		List<IElement> buttons = checkButtons();
		
		// click the first button
		clickElement(buttons.get(0));
		assertNoProblem();
		
		// we should now be on the target frame
		assertTitleEquals("Target Frame");
		
		// with only the label and PK present
		assertLabelTextExactlyPresent("Content One");
		assertLabelTextNotPresent("Content Two");
		assertLabelTextNotPresent("Content Three");
	}

	/**
	 * Click the second button.
	 * 
	 * @throws Exception
	 */
	public void testClickButton2() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// three buttons
		List<IElement> buttons = checkButtons();
		
		// click the first button
		clickElement(buttons.get(1));
		assertNoProblem();
		
		// we should now be on the target frame
		assertTitleEquals("Target Frame");
		
		// with only the label and PK present
		assertLabelTextExactlyPresent("Content Two");
		assertLabelTextNotPresent("Content One");
		assertLabelTextNotPresent("Content Three");
	}
	
	/**
	 * Click the third button.
	 * 
	 * @throws Exception
	 */
	public void testClickButton3() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// three buttons
		List<IElement> buttons = checkButtons();
		
		// click the first button
		clickElement(buttons.get(2));
		assertNoProblem();
		
		// we should now be on the target frame
		assertTitleEquals("Target Frame");
		
		// with only the label and PK present
		assertLabelTextExactlyPresent("Content Three");
		assertLabelTextNotPresent("Content One");
		assertLabelTextNotPresent("Content Two");
	}
	
	/**
	 * Check the order of the buttons and the labels, in the table (IteratorList).
	 * @return 
	 * 
	 * @throws Exception
	 */
	private List<IElement> checkButtons() throws Exception {

		// three buttons
		List<IElement> buttons = getElementsByXPath("//input[@type='submit']");
		assertEquals(3, buttons.size());

		// check the order of the labels, too
		List<IElement> labels = getElementsByXPath("//label[" + getContainsTextXPath("Content ") + "]");
		assertEquals(3, labels.size());
		
		assertEquals("Content One", labels.get(0).getTextContent());
		assertEquals("Content Two", labels.get(1).getTextContent());
		assertEquals("Content Three", labels.get(2).getTextContent());
		
		return buttons;
		
	}

	@Override
	protected List<String> getDatabaseInitialisers() {
		List<String> s = new ArrayList<String>();
		s.add("CREATE TABLE schema (generated_primary_key INTEGER PRIMARY KEY AUTOINCREMENT, attribute VARCHAR(64) NOT NULL)");
		s.add("INSERT INTO schema (generated_primary_key, attribute) VALUES (1, 'Content One')");
		s.add("INSERT INTO schema (generated_primary_key, attribute) VALUES (2, 'Content Two')");
		s.add("INSERT INTO schema (generated_primary_key, attribute) VALUES (3, 'Content Three')");
		s.add("INSERT INTO schema (generated_primary_key, attribute) VALUES (4, 'Content Four')");
		s.add("INSERT INTO schema (generated_primary_key, attribute) VALUES (5, 'Content Five')");
		return s;
	}
	
}
