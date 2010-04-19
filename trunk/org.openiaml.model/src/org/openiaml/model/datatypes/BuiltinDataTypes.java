/**
 * 
 */
package org.openiaml.model.datatypes;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;
import org.openiaml.model.ModelLoader;
import org.openiaml.model.ModelLoader.ModelLoadException;

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
public class BuiltinDataTypes {
	
	private BuiltinDataTypes() {
		// not meant to be instantiated
	}
	
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

	/**
	 * Address data type.
	 */
	public static final String TYPE_ADDRESS = "http://openiaml.org/model/datatypes#iamlAddress";

	private static XSDSimpleTypeDefinition xsdString = null;
	private static XSDSimpleTypeDefinition xsdEmail = null;
	private static XSDSimpleTypeDefinition xsdDateTime = null;
	private static XSDSimpleTypeDefinition xsdInteger = null;
	private static XSDSimpleTypeDefinition xsdAddress = null;
	
	/**
	 * Get the {@link XSDSimpleTypeDefinition} for {@link #TYPE_INTEGER}.
	 * May call {@link #updateTypeDefinitions()} if the types have not been
	 * loaded from the plugin yet.
	 *  
	 * @return the XSDSimpleTypeDefinition for TYPE_INTEGER
	 */
	public static final XSDSimpleTypeDefinition getTypeInteger() {
		if (xsdInteger == null) {
			updateTypeDefinitions();
		}
		return xsdInteger;
	}
	
	/**
	 * Get the {@link XSDSimpleTypeDefinition} for {@link #TYPE_DATETIME}.
	 * May call {@link #updateTypeDefinitions()} if the types have not been
	 * loaded from the plugin yet.
	 *  
	 * @return the XSDSimpleTypeDefinition for TYPE_DATETIME
	 */
	public static final XSDSimpleTypeDefinition getTypeDateTime() {
		if (xsdDateTime == null) {
			updateTypeDefinitions();
		}
		return xsdDateTime;
	}
	
	/**
	 * Get the {@link XSDSimpleTypeDefinition} for {@link #TYPE_EMAIL}.
	 * May call {@link #updateTypeDefinitions()} if the types have not been
	 * loaded from the plugin yet.
	 *  
	 * @return the XSDSimpleTypeDefinition for TYPE_EMAIL
	 */
	public static final XSDSimpleTypeDefinition getTypeEmail() {
		if (xsdEmail == null) {
			updateTypeDefinitions();
		}
		return xsdEmail;
	}
	
	/**
	 * Get the {@link XSDSimpleTypeDefinition} for {@link #TYPE_STRING}.
	 * May call {@link #updateTypeDefinitions()} if the types have not been
	 * loaded from the plugin yet.
	 *  
	 * @return the XSDSimpleTypeDefinition for TYPE_STRING
	 */
	public static final XSDSimpleTypeDefinition getTypeString() {
		if (xsdString == null) {
			updateTypeDefinitions();
		}
		return xsdString;
	}
	
	/**
	 * Get the {@link XSDSimpleTypeDefinition} for {@link #TYPE_ADDRESS}.
	 * May call {@link #updateTypeDefinitions()} if the types have not been
	 * loaded from the plugin yet.
	 *  
	 * @return the XSDSimpleTypeDefinition for TYPE_ADDRESS
	 */
	public static final XSDSimpleTypeDefinition getTypeAddress() {
		if (xsdAddress == null) {
			updateTypeDefinitions();
		}
		return xsdAddress;
	}
	
	/**
	 * The source of the builtin data types.
	 */
	public static final String TYPE_DEFINITIONS = "platform:/plugin/org.openiaml.model/model/datatypes.xsd"; 
	
	/**
	 * Load the XSD file at {@link #TYPE_DEFINITIONS} and populate the
	 * related XSD definitions, such as {@link #getTypeInteger()}.
	 * 
	 * @throws BuiltinTypeInitializationException if an exception occurs
	 */
	protected static void updateTypeDefinitions() {
		URI uri = URI.createURI(TYPE_DEFINITIONS);
		if (uri == null) {
			throw new BuiltinTypeInitializationException("URI for '" + TYPE_DEFINITIONS + "' was null");
		}
		EObject root;
		try {
			root = ModelLoader.load(uri);
		} catch (ModelLoadException e) {
			throw new BuiltinTypeInitializationException("Could not load URI " + uri, e);
		}
		
		XSDSchema schema = (XSDSchema) root;
		for (XSDTypeDefinition type : schema.getTypeDefinitions()) {
			if (type instanceof XSDSimpleTypeDefinition) {
				XSDSimpleTypeDefinition stype = (XSDSimpleTypeDefinition) type;
				String u = stype.getURI();
				
				// for every type
				if (TYPE_INTEGER.equals(u)) {
					xsdInteger = stype;
				} else if (TYPE_STRING.equals(u)) {
					xsdString = stype;
				} else if (TYPE_EMAIL.equals(u)) {
					xsdEmail = stype;
				} else if (TYPE_DATETIME.equals(u)) {
					xsdDateTime = stype;
				} else if (TYPE_ADDRESS.equals(u)) {
					xsdAddress = stype;
				}
			}
		}
	
	}
	
	/**
	 * A runtime exception indicating something bad happened while
	 * trying to initialise the built-in XSD types.
	 * 
	 * @author jmwright
	 */
	public static class BuiltinTypeInitializationException extends RuntimeException {

		private static final long serialVersionUID = 1L;
		
		public BuiltinTypeInitializationException(String message) {
			super(message);
		}
		public BuiltinTypeInitializationException(Throwable cause) {
			super(cause.getMessage(), cause);
		}
		public BuiltinTypeInitializationException(String message, Throwable cause) {
			super(message, cause);
		}
		
	}
	
}
