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
	
	public static final String PAGE_FIRST = "visual_126aa99339d_211";
	public static final String PAGE_1 = "visual_126aa99339d_20e";
	public static final String PAGE_2 = "visual_126aa99339d_20f";
	public static final String PAGE_3 = "visual_126aa99339d_210";

	/**
	 * Constructed at run-time using helper methods.
	 */
	public static final String EMULATED_GATE_CHECK = 
		getLTLVisitRequires(PAGE_FIRST, PAGE_1) +
		getLTLVisitRequires(PAGE_FIRST, PAGE_2) +
		getLTLVisitRequires(PAGE_FIRST, PAGE_3) +
		getLTLCanVisit(PAGE_1) +
		getLTLCanVisit(PAGE_2) +
		getLTLCanVisit(PAGE_3) +
		getLTLCanVisit(PAGE_FIRST);
	
	private static String getLTLVisitRequires(String first, String target) {
		return "\n LTLSPEC \n"
			+ "  G (! (current_page = " + target + " & ! O (current_page = " + first + ")))\n";
			// + "  G (F ((current_page = " + target + " -> F navigation_finished = 1) -> O current_page = " + first + "))\n";
	}
	
	private static String getLTLCanVisit(String target) {
		return "\n LTLSPEC \n"
			+ "  G (F (current_page = " + target + " & navigation_finished = 1))\n";
			// + "  F (current_page = " + target + " & navigation_finished = 1 )\n";
		// U (current_page = " + target + " & navigation_finished = 1)
	}
	
	public void testEmulatedGate() throws Exception {
		assertValid("models/navigation/EmulatedGate.iaml", 0, EMULATED_GATE_CHECK);
		
		// TODO: check that the violation is correct
	}
	
	public void testEmulatedGateBroken() throws Exception {
		assertValid("models/navigation/EmulatedGateBroken.iaml", 1, EMULATED_GATE_CHECK);
		
		// TODO: check that the violation is correct
	}
	
}
