/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_1;

import org.openiaml.model.tests.CodegenTestCase;

/**
 * Abstract classes for arithmetic tests.
 * 
 * @author jmwright
 *
 */
public abstract class AbstractArithmeticTestCase extends CodegenTestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(getTestcaseClass());
	}
	
	/**
	 * Get the current class - this is used to get the model to
	 * test. 
	 * 
	 * @return
	 */
	public abstract Class<? extends AbstractArithmeticTestCase> getTestcaseClass();

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
	 * Calling calculate with empty operands.
	 * 
	 * @see #getEmptyCalculation()
	 */
	public void testEmpty() throws Exception {
		
		testInitial();
		clickButtonWithText("calculate");
		{
			String result = getLabelIDForText("result");
			assertLabeledFieldEquals(result, getEmptyCalculation());
		}
		
	}
	
	/**
	 * Op1 = ''; Op2 = ''; what should the result be?
	 * 
	 * @return the result as a string
	 * @see #testEmpty()
	 */
	public abstract String getEmptyCalculation();
	
	/**
	 * Calling calculate with '1' and '1'.
	 * 
	 * @see #get1_1Calculation()
	 */
	public void test1Op1() throws Exception {
		
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
			assertLabeledFieldEquals(result, get1_1Calculation());
		}
		
	}
	
	/**
	 * Op1 = '1'; Op2 = '1'; what should the result be?
	 * 
	 * @return the result as a string
	 * @see #test1Op1()
	 */
	public abstract String get1_1Calculation();

	/**
	 * Calling calculate with '3' and '0'.
	 * 
	 * @see #get3_0Calculation()
	 */
	public void test3Op0() throws Exception {
		
		testInitial();
		{
			String op1 = getLabelIDForText("op1");
			setLabeledFormElementField(op1, "3");
		}
		{
			String op2 = getLabelIDForText("op2");
			setLabeledFormElementField(op2, "0");
		}
		clickButtonWithText("calculate");
		{
			String result = getLabelIDForText("result");
			assertLabeledFieldEquals(result, get3_0Calculation());
		}
		
	}
	
	/**
	 * Op1 = '3'; Op2 = '0'; what should the result be?
	 * 
	 * @return the result as a string
	 * @see #test3Op0()
	 */
	public abstract String get3_0Calculation();
	

	/**
	 * Calling calculate with '0' and '3'.
	 * 
	 * @see #get0_3Calculation()
	 */
	public void test0Op3() throws Exception {
		
		testInitial();
		{
			String op1 = getLabelIDForText("op1");
			setLabeledFormElementField(op1, "0");
		}
		{
			String op2 = getLabelIDForText("op2");
			setLabeledFormElementField(op2, "3");
		}
		clickButtonWithText("calculate");
		{
			String result = getLabelIDForText("result");
			assertLabeledFieldEquals(result, get0_3Calculation());
		}
		
	}
	
	/**
	 * Op1 = '0'; Op2 = '3'; what should the result be?
	 * 
	 * @return the result as a string
	 * @see #test3Op0()
	 */
	public abstract String get0_3Calculation();
	
	/**
	 * Calling calculate with '-1' and '-1'.
	 * 
	 * @see #getN1_N1Calculation()
	 */
	public void testN1OpN1() throws Exception {
		
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
			assertLabeledFieldEquals(result, getN1_N1Calculation());
		}
		
	}
	
	/**
	 * Op1 = '-1'; Op2 = '-1'; what should the result be?
	 * 
	 * @return the result as a string
	 * @see #testN1OpN1()
	 */
	public abstract String getN1_N1Calculation();
	
	/**
	 * Calling calculate with '1.3' and '2.3' will result in '3.6'.
	 * 
	 * @throws Exception
	 * @see #get13_23Calculation()
	 */
	public void test13Op23() throws Exception {
		
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
			assertLabeledFieldEquals(result, get13_23Calculation());
		}
		
	}

	/**
	 * Op1 = '1.3'; Op2 = '2.3'; what should the result be?
	 * 
	 * @return the result as a string
	 * @see #test13Op23()
	 */
	public abstract String get13_23Calculation();
	
	
}
