/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_1;


/**
 * Try the 'MULTIPLY' arithmetic.
 * 
 * @author jmwright
 * @operational Arithmetic
 * 		{@model Arithmetic} can be used inline to multiply its
 * 		incoming operands, and act as a source of data.
 */
public class ArithmeticMultiply extends AbstractArithmeticTestCase {

	@Override
	public Class<? extends AbstractArithmeticTestCase> getTestcaseClass() {
		return ArithmeticMultiply.class;
	}
	
	@Override
	public String get0_3Calculation() {
		return "0";
	}

	@Override
	public double get13_23Calculation() {
		return (1.3 * 2.3);
	}

	@Override
	public String get1_1Calculation() {
		return "1";
	}

	@Override
	public String get3_0Calculation() {
		return "0";
	}

	@Override
	public String getEmptyCalculation() {
		return "0";
	}

	@Override
	public String getN1_N1Calculation() {
		return "1";
	}

}
