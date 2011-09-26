/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_1;


/**
 * Try the 'SUBTRACT' arithmetic.
 * 
 * @author jmwright
 * @implementation Arithmetic
 * 		{@model Arithmetic} can be used inline to subtract its
 * 		incoming operands, and act as a source of data.
 */
public class ArithmeticSubtract extends AbstractArithmeticTestCase {

	@Override
	public Class<? extends AbstractArithmeticTestCase> getTestcaseClass() {
		return ArithmeticSubtract.class;
	}
	
	@Override
	public String get0_3Calculation() {
		return "-3";
	}

	@Override
	public double get13_23Calculation() {
		return (1.3 - 2.3);
	}

	@Override
	public String get1_1Calculation() {
		return "0";
	}

	@Override
	public String get3_0Calculation() {
		return "3";
	}

	@Override
	public String getEmptyCalculation() {
		return "0";
	}

	@Override
	public String getN1_N1Calculation() {
		return "0";
	}

}
