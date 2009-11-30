/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_1;


/**
 * Try the 'DIVIDE' arithmetic.
 * 
 * @author jmwright
 * @operational Arithmetic
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
	public String get13_23Calculation() {
		return "0.56521739130434782608695652173913";
	}

	@Override
	public String get1_1Calculation() {
		return "1";
	}

	/**
	 * @operational Arithmetic
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
