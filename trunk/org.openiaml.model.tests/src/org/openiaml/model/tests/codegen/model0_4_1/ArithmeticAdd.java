/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_1;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * Try the 'ADD' arithmetic.
 * 
 * @author jmwright
 * @example Arithmetic,PrimitiveOperation Using {@model Arithmetic} to add
 * 		two {@model InputTextField input values} together.
 * @operational Arithmetic
 * 		{@model Arithmetic} can be used inline to add together its
 * 		incoming operands, and act as a source of data.
 */
public class ArithmeticAdd extends CodegenTestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(ArithmeticAdd.class);
	}
	
	/**
	 * Initially, all values are empty 
	 * @throws Exception 
	 */
	public void testInitial() throws Exception {
		
		beginAtSitemapThenPage("Home");
		{
			String op1 = getLabelIDForText("op1");
			assertLabeledFieldEquals(op1, "");
		}
		{
			String op2 = getLabelIDForText("op2");
			assertLabeledFieldEquals(op2, "");
		}
		{
			String result = getLabelIDForText("result");
			assertLabeledFieldEquals(result, "");
		}
		assertButtonPresentWithText("calculate");
		
	}
	
	/**
	 * Calling calculate with empty operands will result in '0'.
	 * 
	 * @throws Exception
	 */
	public void testEmpty() throws Exception {
		
		testInitial();
		clickButtonWithText("calculate");
		{
			String result = getLabelIDForText("result");
			assertLabeledFieldEquals(result, "0");
		}
		
	}
	
	/**
	 * Calling calculate with '1' and '1' will result in '2'.
	 * 
	 * @throws Exception
	 */
	public void test1Plus1() throws Exception {
		
		testInitial();
		{
			String op1 = getLabelIDForText("op1");
			setLabeledFormElementField(op1, "1");
		}
		{
			String op2 = getLabelIDForText("op2");
			setLabeledFormElementField(op2, "1");
		}
		clickButtonWithText("calculate");
		{
			String result = getLabelIDForText("result");
			assertLabeledFieldEquals(result, "2");
		}
		
	}
	
	/**
	 * Calling calculate with '-1' and '-1' will result in '-2'.
	 * 
	 * @throws Exception
	 */
	public void testM1PlusM1() throws Exception {
		
		testInitial();
		{
			String op1 = getLabelIDForText("op1");
			setLabeledFormElementField(op1, "-1");
		}
		{
			String op2 = getLabelIDForText("op2");
			setLabeledFormElementField(op2, "-1");
		}
		clickButtonWithText("calculate");
		{
			String result = getLabelIDForText("result");
			assertLabeledFieldEquals(result, "-2");
		}
		
	}
	
	/**
	 * Calling calculate with '1.3' and '2.3' will result in '3.6'.
	 * 
	 * @throws Exception
	 */
	public void test13Plus23() throws Exception {
		
		testInitial();
		{
			String op1 = getLabelIDForText("op1");
			setLabeledFormElementField(op1, "1.3");
		}
		{
			String op2 = getLabelIDForText("op2");
			setLabeledFormElementField(op2, "2.3");
		}
		clickButtonWithText("calculate");
		{
			String result = getLabelIDForText("result");
			assertLabeledFieldEquals(result, "3.6");
		}
		
	}
	
}
