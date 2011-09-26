/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_6;

import org.openiaml.model.tests.codegen.model0_5.MapsCodegenTestCase;

/**
 * Issue 246: implement XQueryPredicates
 * 
 */
public class XQueryPredicates extends MapsCodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(getClass());
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
	
	public void testStringPredicatesAccess() throws Exception {
		beginAtSitemapThenPage("String Predicates");
		assertNoProblem();
		
		// check all of the buttons and fields
		assertInputTextFieldEquals("arg1", "");
		assertInputTextFieldEquals("arg2", "");

		assertButtonPresentWithText("execute");
		assertButtonPresentWithText("contains");
		assertButtonPresentWithText("codepoint equal");
		assertButtonPresentWithText("not arg1");

	}
	
	/**
	 * Try clicking "execute" while the text fields are all still empty.
	 * @throws Exception
	 */
	public void testStringPredicatesEmpty() throws Exception {
		testStringPredicatesAccess();
		
		clickButtonWithText("execute");
		assertNoProblem();
		
		// "".contains("")
		assertButtonPresentWithText("contains");
		
		// "" == ""
		assertButtonPresentWithText("codepoint equal");
		
		// !""
		assertButtonPresentWithText("not arg1");
	}
	
	/**
	 * Set only arg1, and see the results.
	 * 
	 * @throws Exception
	 */
	public void testStringPredicatesSetArg1() throws Exception {
		testStringPredicatesAccess();
		
		setInputTextField("arg1", "hello");
		clickButtonWithText("execute");
		assertNoProblem();
		
		// "hello".contains("")
		assertButtonPresentWithText("contains");
		
		// "hello" == ""
		assertButtonNotPresentWithText("codepoint equal");
		
		// not("hello")
		assertButtonNotPresentWithText("not arg1");
	}
	
	/**
	 * Set arg1 and arg2, and see the results.
	 * 
	 * @throws Exception
	 */
	public void testStringPredicatesSetArg1And2() throws Exception {
		testStringPredicatesAccess();
		
		setInputTextField("arg1", "hello");
		setInputTextField("arg2", "hello");
		clickButtonWithText("execute");
		assertNoProblem();
		
		// "hello".contains("hello")
		assertButtonPresentWithText("contains");
		
		// "hello" == "hello"
		assertButtonPresentWithText("codepoint equal");
		
		// not("hello")
		assertButtonNotPresentWithText("not arg1");
	}
	
	/**
	 * Set arg1 and arg2 to different values, and see the results.
	 * 
	 * @throws Exception
	 */
	public void testStringPredicatesSetArg1And2Differently() throws Exception {
		testStringPredicatesAccess();
		
		setInputTextField("arg1", "hello");
		setInputTextField("arg2", "world");
		clickButtonWithText("execute");
		assertNoProblem();
		
		// "hello".contains("world")
		assertButtonNotPresentWithText("contains");
		
		// "hello" == "world"
		assertButtonNotPresentWithText("codepoint equal");
		
		// not("world")
		assertButtonNotPresentWithText("not arg1");
	}
	
	/**
	 * Checks that "execute" can be selected multiple times without errors. 
	 * 
	 * @throws Exception
	 */
	public void testStringPredicatesMultipleExecution() throws Exception {
		testStringPredicatesSetArg1And2();
		
		clickButtonWithText("execute");
		assertNoProblem();
	
		assertButtonPresentWithText("contains");
		assertButtonPresentWithText("codepoint equal");
		assertButtonNotPresentWithText("not arg1");
	}
	
	public void testStringPredicatesWithNumbers() throws Exception {
		testStringPredicatesAccess();
		
		setInputTextField("arg1", "12");
		setInputTextField("arg2", "2");
		clickButtonWithText("execute");
		assertNoProblem();
		
		// "12".contains("2")
		assertButtonPresentWithText("contains");
		
		// "12" == "2"
		assertButtonNotPresentWithText("codepoint equal");
		
		// not("12")
		assertButtonNotPresentWithText("not arg1");
	}
	
	public void testNumericPredicatesAccess() throws Exception {
		beginAtSitemapThenPage("Numeric Predicates");
		assertNoProblem();
		
		// check all of the buttons and fields
		assertInputTextFieldEquals("arg1", "0");
		assertInputTextFieldEquals("arg2", "0");

		assertButtonPresentWithText("execute");
		assertButtonPresentWithText("numeric-equal");
		assertButtonPresentWithText("numeric-less-than");
		assertButtonPresentWithText("numeric-greater-than");
		assertButtonPresentWithText("not arg1");
		
	}
	
	public void testNumericPredicatesEmpty() throws Exception {
		testNumericPredicatesAccess();
		
		clickButtonWithText("execute");
		assertNoProblem();
		
		// 0 == 0
		assertButtonPresentWithText("numeric-equal");
		
		// 0 < 0
		assertButtonNotPresentWithText("numeric-less-than");
		
		// 0 > 0
		assertButtonNotPresentWithText("numeric-greater-than");
		
		// !0
		assertButtonPresentWithText("not arg1");
	}
	
	/**
	 * Set only arg1, and see the results.
	 * 
	 * @throws Exception
	 */
	public void testNumericPredicatesSetArg1() throws Exception {
		testNumericPredicatesAccess();
		
		setInputTextField("arg1", "123");
		clickButtonWithText("execute");
		assertNoProblem();
		
		// 123 == 0
		assertButtonNotPresentWithText("numeric-equal");
		
		// 123 < 0
		assertButtonNotPresentWithText("numeric-less-than");
		
		// 123 > 0
		assertButtonPresentWithText("numeric-greater-than");
		
		// !123
		assertButtonNotPresentWithText("not arg1");
	}
	
	/**
	 * Set arg1 and arg2, and see the results.
	 * 
	 * @throws Exception
	 */
	public void testNumericPredicatesSetArg1And2() throws Exception {
		testNumericPredicatesAccess();
		
		setInputTextField("arg1", "123");
		setInputTextField("arg2", "456");
		clickButtonWithText("execute");
		assertNoProblem();
		
		// 123 == 456
		assertButtonNotPresentWithText("numeric-equal");
		
		// 123 < 456
		assertButtonPresentWithText("numeric-less-than");
		
		// 123 > 456
		assertButtonNotPresentWithText("numeric-greater-than");
		
		// !123
		assertButtonNotPresentWithText("not arg1");
	}
	
	/**
	 * Set arg2, and see the results.
	 * 
	 * @throws Exception
	 */
	public void testNumericPredicatesSetArg2() throws Exception {
		testNumericPredicatesAccess();
		
		setInputTextField("arg2", "456");
		clickButtonWithText("execute");
		assertNoProblem();
		
		// 0 == 456
		assertButtonNotPresentWithText("numeric-equal");
		
		// 0 < 456
		assertButtonPresentWithText("numeric-less-than");
		
		// 0 > 456
		assertButtonNotPresentWithText("numeric-greater-than");
		
		// !0
		assertButtonPresentWithText("not arg1");
	}
	
	/**
	 * Set arg1 and arg2 to the same values, and see the results.
	 * 
	 * @throws Exception
	 */
	public void testNumericPredicatesSetArg1And2Equal() throws Exception {
		testNumericPredicatesAccess();
		
		setInputTextField("arg1", "345");
		setInputTextField("arg2", "345");
		clickButtonWithText("execute");
		assertNoProblem();
		
		// 345 == 345
		assertButtonPresentWithText("numeric-equal");
		
		// 345 < 345
		assertButtonNotPresentWithText("numeric-less-than");
		
		// 345 > 345
		assertButtonNotPresentWithText("numeric-greater-than");
		
		// !345
		assertButtonNotPresentWithText("not arg1");
	}
	
	/**
	 * Checks that "execute" can be selected multiple times without errors. 
	 * 
	 * @throws Exception
	 */
	public void testNumericPredicatesMultipleExecution() throws Exception {
		testNumericPredicatesSetArg1And2();
		
		clickButtonWithText("execute");
		assertNoProblem();
	
		assertButtonNotPresentWithText("numeric-equal");
		assertButtonPresentWithText("numeric-less-than");
		assertButtonNotPresentWithText("numeric-greater-than");
		assertButtonNotPresentWithText("not arg1");
	}
	
	/**
	 * Test calling number predicates with invalid typed input, i.e. strings.
	 * 
	 * @throws Exception
	 */
	public void testNumericPredicatesWithStrings() throws Exception {
		testNumericPredicatesAccess();
		
		setInputTextField("arg1", "hello");
		setInputTextField("arg2", "world");
		clickButtonWithText("execute");
		assertNoProblem();
		
		// the results will be the same as if the text had never
		// been entered, i.e. 0 / 0
		
		// 0 == 0
		assertButtonPresentWithText("numeric-equal");
		
		// 0 < 0
		assertButtonNotPresentWithText("numeric-less-than");
		
		// 0 > 0
		assertButtonNotPresentWithText("numeric-greater-than");
		
		// !0
		assertButtonPresentWithText("not arg1");
	}
	
}
