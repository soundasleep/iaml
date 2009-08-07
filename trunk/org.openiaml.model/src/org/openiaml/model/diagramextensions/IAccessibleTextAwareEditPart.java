/**
 * 
 */
package org.openiaml.model.diagramextensions;

/**
 * An extension to generated EditParts in GMF which forces the
 * getLabelText() method to be public. This is only used for testing
 * purposes.
 * 
 * @author jmwright
 *
 */
public interface IAccessibleTextAwareEditPart {

	/**
	 * Get the label text. This method was originally protected in GMF,
	 * but made public for testing.
	 * 
	 * @return
	 */
	public String getLabelText();
	
}
