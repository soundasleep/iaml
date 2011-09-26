/**
 * 
 */
package org.openiaml.model.actions;

/**
 * A simple class for passing back values from a thread.
 * 
 * @author jmwright
 */
public class QuestionDialogResult {
	private boolean result = false;

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
}