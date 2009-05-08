/**
 * 
 */
package org.openiaml.model.drools;

import java.util.Arrays;
import java.util.List;

import org.openiaml.model.inference.ICreateElements;

/**
 * Uses Drools to refresh data stores.
 * 
 * @author jmwright
 *
 */
public class RefreshDataStores extends DroolsInferenceEngine {
	
	ICreateElements handler;

	public RefreshDataStores(ICreateElements handler) {
		super(handler);
		this.handler = handler;
	}

	private List<String> ruleFiles = Arrays.asList(
			"/rules/file-domain-object.drl"
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
