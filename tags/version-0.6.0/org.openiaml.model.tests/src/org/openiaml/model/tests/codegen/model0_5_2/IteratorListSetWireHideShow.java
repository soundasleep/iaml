/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_2;


/**
 * Tests that we can show and hide IteratorLists.
 * 
 */
public class IteratorListSetWireHideShow extends IteratorListSetWire {

	/**
	 * A ListIterator displays all of the contents of the Iterator, up to
	 * the limit provided by the Iterator.
	 * 
	 * @throws Exception
	 */
	@Override
	public void testAllLabelsArePopulated() throws Exception {
		// call super
		super.testAllLabelsArePopulated();
		
		// hide the list
		clickButtonWithText("hide list");
		assertNoProblem();
		
		// the labels are no longer there
		assertLabelTextNotPresent("Title 1");
		assertLabelTextNotPresent("Content 1");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");
		assertLabelTextNotPresent("Title 3");
		assertLabelTextNotPresent("Content 3");
		assertLabelTextNotPresent("Title 4");
		assertLabelTextNotPresent("Content 4");
		assertLabelTextNotPresent("Title 10");
		assertLabelTextNotPresent("Content 10");
		
		// show the list again
		clickButtonWithText("show list");
		assertNoProblem();
		
		// the labels are now present
		assertLabelTextExactlyPresent("Title 1");
		assertLabelTextExactlyPresent("Content 1");
		assertLabelTextExactlyPresent("Title 2");
		assertLabelTextExactlyPresent("Content 2");
		assertLabelTextExactlyPresent("Title 3");
		assertLabelTextExactlyPresent("Content 3");
		assertLabelTextNotPresent("Title 4");
		assertLabelTextNotPresent("Content 4");
		assertLabelTextNotPresent("Title 10");
		assertLabelTextNotPresent("Content 10");
		
	}
	
	/**
	 * If the DomainIterator has a limit of 3, but there is only 2 elements
	 * in the database, we can still see these two elements.
	 * 
	 * @throws Exception
	 */
	@Override
	public void testLessLabels() throws Exception {
		// call super
		super.testLessLabels();
		
		// hide the list
		clickButtonWithText("hide list");
		assertNoProblem();
		
		// the labels are no longer there
		assertLabelTextNotPresent("Title 1");
		assertLabelTextNotPresent("Content 1");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");
		assertLabelTextNotPresent("Title 3");
		assertLabelTextNotPresent("Content 3");
		assertLabelTextNotPresent("Title 4");
		assertLabelTextNotPresent("Content 4");
		assertLabelTextNotPresent("Title 10");
		assertLabelTextNotPresent("Content 10");
		
		// show the list again
		clickButtonWithText("show list");
		assertNoProblem();
		
		// the labels are now present
		assertLabelTextExactlyPresent("Title 1");
		assertLabelTextExactlyPresent("Content 1");
		assertLabelTextExactlyPresent("Title 2");
		assertLabelTextExactlyPresent("Content 2");
		assertLabelTextNotPresent("Title 3");
		assertLabelTextNotPresent("Content 3");
		assertLabelTextNotPresent("Title 4");
		assertLabelTextNotPresent("Content 4");
		assertLabelTextNotPresent("Title 10");
		assertLabelTextNotPresent("Content 10");
		
	}
	
	/**
	 * If the DomainIterator has a limit of 3, but the database is empty,
	 * we can still display a page with no errors.
	 * 
	 * @throws Exception
	 */
	@Override
	public void testEmptyDatabase() throws Exception {
		// call super
		super.testEmptyDatabase();
		
		// hide the list
		clickButtonWithText("hide list");
		assertNoProblem();
		
		// the labels are no longer there
		assertLabelTextNotPresent("Title 1");
		assertLabelTextNotPresent("Content 1");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");
		assertLabelTextNotPresent("Title 3");
		assertLabelTextNotPresent("Content 3");
		assertLabelTextNotPresent("Title 4");
		assertLabelTextNotPresent("Content 4");
		assertLabelTextNotPresent("Title 10");
		assertLabelTextNotPresent("Content 10");
		
		// show the list again
		clickButtonWithText("show list");
		assertNoProblem();
		
		// the labels are still not present, since there are no results
		assertLabelTextNotPresent("Title 1");
		assertLabelTextNotPresent("Content 1");
		assertLabelTextNotPresent("Title 2");
		assertLabelTextNotPresent("Content 2");
		assertLabelTextNotPresent("Title 3");
		assertLabelTextNotPresent("Content 3");
		assertLabelTextNotPresent("Title 4");
		assertLabelTextNotPresent("Content 4");
		assertLabelTextNotPresent("Title 10");
		assertLabelTextNotPresent("Content 10");
	}

}
