/**
 * 
 */
package org.openiaml.model.diagramextensions;

import java.text.ChoiceFormat;
import java.text.FieldPosition;

/**
 * A custom formatter that formats boolean values.
 * If the value is true, it returns "/"; otherwise, an empty string.
 * 
 * @author jmwright
 *
 */
public class IsGeneratedFormat extends ChoiceFormat {
	
	private static final long serialVersionUID = 1L;

	public IsGeneratedFormat() {
		super(null);	// empty
	}

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo,
			FieldPosition pos) {
		if (obj instanceof Boolean) {
			if ((Boolean) obj)
				return toAppendTo.append("/");		// "derived"
			else
				return toAppendTo;		// empty
		} else {
			throw new IllegalArgumentException("Object '" + obj + "' is not a boolean.");
		}
	}

	@Override
	public void applyPattern(String newPattern) {
		// just do nothing with the new pattern (it's most likely null, thanks to the constructor)
	}
	
}
