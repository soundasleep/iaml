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

	public CreateMissingElementsWithDrools(ICreateElements handler, boolean trackInsertions) {
		super(handler, trackInsertions);
	}

	private List<String> ruleFiles = Arrays.asList(
			"/rules/base.drl",
			"/rules/sync-wires.drl",
			"/rules/set-wires.drl",
			"/rules/events.drl",
			"/rules/sessions.drl",
			"/rules/login-handler.drl",
			"/rules/gate.drl",
			"/rules/operations.drl",
			"/rules/dynamic-sources.drl",
			"/rules/conditions.drl",
			"/rules/users.drl",
			"/rules/runtime/file-domain-object.drl",
			"/rules/runtime/domain.drl",
			"/rules/runtime/new-instance.drl"
			);
	
	/**
	 * Get the list of rule files used.
	 * 
	 * @see #addRuleFile(String)
	 * @return
	 */
	@Override
	public List<String> getRuleFiles() {
		return ruleFiles;
	}

}
