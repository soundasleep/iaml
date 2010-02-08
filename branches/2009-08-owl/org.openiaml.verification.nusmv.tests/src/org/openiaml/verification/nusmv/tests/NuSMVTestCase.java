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
	
	/*
	 * G ((!(navigation_running = 1 -> !(F navigation_finished = 1))) 
	 * 		U navigation_running = 0)
	 */
	public static final String EMULATED_GATE_CHECK = 
		getLTLSpec(PAGE_FIRST, PAGE_1) +
		getLTLSpec(PAGE_FIRST, PAGE_2) +
		getLTLSpec(PAGE_FIRST, PAGE_3);
	
	private static String getLTLSpec(String first, String target) {
		return "\n LTLSPEC \n"
			+ "  G ((current_page = " + target + " & navigation_running = 0) -> O current_page = " + first + ")\n";
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
