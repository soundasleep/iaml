/**
 * 
 */
package org.openiaml.model.drools;

import java.util.Arrays;
import java.util.List;

import org.openiaml.model.inference.ICreateElements;

/**
 * Uses Drools to infer new elements.
 * 
 * @author jmwright
 *
 */
public class CreateMissingElementsWithDrools extends DroolsInferenceEngine {

	public CreateMissingElementsWithDrools(ICreateElements handler) {
		super(handler);
	}

	private List<String> ruleFiles = Arrays.asList(
			"/rules/base.drl",
			"/rules/sync-wires.drl",
			"/rules/events.drl",
			"/rules/sessions.drl",
			"/rules/operations.drl",
			"/rules/dynamic-sources.drl",
			"/rules/conditions.drl"
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
