/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_1;


/**
 * Try the 'DIVIDE' arithmetic.
 * 
 * @author jmwright
 * @implementation Arithmetic
 * 		{@model Arithmetic} can be used inline to divide its
 * 		incoming operands, and act as a source of data.
 */
public class ArithmeticDivide extends AbstractArithmeticTestCase {

	@Override
	public Class<? extends AbstractArithmeticTestCase> getTestcaseClass() {
		return ArithmeticDivide.class;
	}
	
	@Override
	public String get0_3Calculation() {
		return "0";
	}

	@Override
	public double get13_23Calculation() {
		return (1.3 / 2.3);
	}

	@Override
	public String get1_1Calculation() {
		return "1";
	}

	/**
	 * @implementation Arithmetic
	 * 		If {@model Arithmetic} is used to divide by zero, <code>NaN</code> will
	 * 		result.
	 */
	@Override
	public String get3_0Calculation() {
		return "NaN";
	}

	@Override
	public String getEmptyCalculation() {
		return "NaN";
	}

	@Override
	public String getN1_N1Calculation() {
		return "1";
	}

}
