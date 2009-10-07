/**
 * 
 */
package org.openiaml.verification.drools;

import java.util.Arrays;
import java.util.List;

/**
 * Uses Drools to infer new elements.
 * 
 * @author jmwright
 *
 */
public class VerifyWithDrools extends VerificationEngine {

	public VerifyWithDrools(VerifyHandler verify, boolean trackInsertions) {
		super(verify, trackInsertions);
	}

	private List<String> ruleFiles = Arrays.asList(
			"/rules/base.drl"
			);
	
	/**
	 * Get the list of rule files used.
	 * 
	 * @see #addRuleFile(String)
	 * @return
	 */
	public List<String> getRuleFiles() {
		return ruleFiles;
	}

}
