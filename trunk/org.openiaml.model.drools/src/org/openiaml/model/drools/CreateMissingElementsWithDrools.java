/**
 * 
 */
package org.openiaml.model.drools;

import java.util.Arrays;
import java.util.List;

/**
 * Uses Drools to infer new elements.
 * 
 * @author jmwright
 *
 */
public class CreateMissingElementsWithDrools extends DroolsInferenceEngine {

	public CreateMissingElementsWithDrools(ICreateElementsFactory factory, boolean trackInsertions) {
		super(factory, trackInsertions);
	}

	private List<String> ruleFiles = Arrays.asList(			
			"/rules/base.drl",
			"/rules/casting.drl",
			"/rules/sync-wires.drl",
			"/rules/set-wires.drl",
			"/rules/detail-wires.drl", 
			"/rules/emails.drl",
			"/rules/events.drl",
			"/rules/sessions.drl",
			"/rules/login-handler.drl",
			"/rules/gate.drl",
			"/rules/instance.drl",
			"/rules/labels.drl",
			"/rules/textfields.drl",
			"/rules/operations.drl",
			"/rules/paginate.drl",
			"/rules/dynamic-sources.drl",
			"/rules/conditions.drl",
			"/rules/users.drl",
			"/rules/validate.drl",
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
