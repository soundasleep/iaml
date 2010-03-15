/**
 * 
 */
package org.openiaml.model.datatypes;

/**
 * A convenience list of constants representing the different 
 * built-in data types. 
 * 
 * <p>TODO this should be synchronised or otherwise loaded from the
 * actual XSD file.
 * 
 * @author jmwright
 *
 */
public interface BuiltinDataTypes {
	
	/**
	 * String data type.
	 */
	public static final String TYPE_STRING = "http://openiaml.org/model/datatypes#iamlString";
	
	/**
	 * Email data type.
	 */
	public static final String TYPE_EMAIL = "http://openiaml.org/model/datatypes#iamlEmail";
	
	/**
	 * Date/time data type.
	 */
	public static final String TYPE_DATETIME = "http://openiaml.org/model/datatypes#iamlDateTime";
	
	/**
	 * Integer data type.
	 */
	public static final String TYPE_INTEGER = "http://openiaml.org/model/datatypes#iamlInteger";
	
}
