/**
 * 
 */
package org.openiaml.model.diagramextensions;

import java.text.ChoiceFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * A custom parser that looks for the first character
 * to begin with "/"; if so, returns true; otherwise
 * return false;
 * 
 * @author jmwright
 *
 */
public class DefaultFalseFormat extends Format {

	private static final long serialVersionUID = 1L;

	/**
	 * Todo integrate IsGeneratedFormat into here (perhaps extend ChoiceFormat but actually call this)
	 */
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
	public Object parseObject(String source, ParsePosition pos) {
		if (source.charAt(pos.getIndex()) == '/') {
			pos.setIndex(pos.getIndex() + 1);
			return true;
		}
		return false;
	}
	
}
