/**
 * 
 */
package org.openiaml.verification.nusmv.tests;


/**
 * Tests all NuSMV-based verification rules.
 * 
 * @author jmwright
 *
 */
public class NuSMVTestCase extends AbstractNuSMVTestCase {
	
	public void testNormalOperationLoop() throws Exception {		
		assertValid("models/NormalOperationLoop.iaml", 0);
	}

	public void testInfiniteOperationLoop() throws Exception {
		assertValid("models/InfiniteOperationLoop.iaml", 1);

		// TODO: check that the violation is correct
	}

	public void testDecisionOperationLoop() throws Exception {
		assertValid("models/DecisionOperationLoop.iaml", 0);

		// TODO: check that the violation is correct
	}
	
	public void testDecisionOperationLoopInfinite() throws Exception {
		assertValid("models/DecisionOperationLoopInfinite.iaml", 1);

		// TODO: check that the violation is correct
	}

	public void testSplitOperation() throws Exception {
		assertValid("models/SplitOperation.iaml", 0);

		// TODO: check that the violation is correct
	}
	
	public void testSplitOperationInfinite() throws Exception {
		assertValid("models/SplitOperationInfinite.iaml", 1);

		// TODO: check that the violation is correct
	}

	public void testSplitOperationDecision() throws Exception {
		assertValid("models/SplitOperationDecision.iaml", 0);

		// TODO: check that the violation is correct
	}
	
	public void testSplitOperationDecisionInfinite() throws Exception {
		assertValid("models/SplitOperationDecisionInfinite.iaml", 1);

		// TODO: check that the violation is correct
	}

	public void testAccessOperationLoop() throws Exception {
		assertValid("models/AccessOperationLoop.iaml", 0);

		// TODO: check that the violation is correct
	}
	
	public void testAccessOperationLoopInfinite() throws Exception {
		assertValid("models/AccessOperationLoopInfinite.iaml", 1);

		// TODO: check that the violation is correct
	}

	public void testOperationCallOperationLoop() throws Exception {
		assertValid("models/OperationCallOperationLoop.iaml", 0);

		// TODO: check that the violation is correct
	}
	
	public void testOperationCallOperationLoopInfinite() throws Exception {
		assertValid("models/OperationCallOperationLoopInfinite.iaml", 1);

		// TODO: check that the violation is correct
	}

	public void testNavigationNormal() throws Exception {
		assertValid("models/navigation/Normal.iaml", 0);
		
		// TODO: check that the violation is correct
	}

	public void testNavigationSimpleLoop() throws Exception {
		assertValid("models/navigation/SimpleLoop.iaml", 1);
		
		// TODO: check that the violation is correct
	}
		
}
