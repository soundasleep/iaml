/**
 * 
 */
package org.openiaml.model;

/**
 * @author jmwright
 *
 */
public class EXSDDataTypeTransactionHandler {
	
	private boolean isLoadingDiagram = false;
	private static EXSDDataTypeTransactionHandler instance;

	private EXSDDataTypeTransactionHandler() {
		// disable instantiation
	}
	
	public static EXSDDataTypeTransactionHandler getInstance() {
		if (instance == null) {
			instance = new EXSDDataTypeTransactionHandler();
		}
		return instance;
	}

	/**
	 * @return the isLoadingDiagram
	 */
	public boolean isLoadingDiagram() {
		return isLoadingDiagram;
	}

	/**
	 * @param isLoadingDiagram the isLoadingDiagram to set
	 */
	public void setLoadingDiagram(boolean isLoadingDiagram) {
		this.isLoadingDiagram = isLoadingDiagram;
	}
	
}
