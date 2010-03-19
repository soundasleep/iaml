/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Store Types</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.openiaml.model.model.domain.DomainPackage#getDomainStoreTypes()
 * @model annotation="http://openiaml.org/comment added='0.3'"
 * @generated
 */
public enum DomainStoreTypes implements Enumerator {
	/**
	 * The '<em><b>RELATIONAL DB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RELATIONAL_DB_VALUE
	 * @generated
	 * @ordered
	 */
	RELATIONAL_DB(0, "RELATIONAL_DB", "RELATIONAL_DB"),

	/**
	 * The '<em><b>PROPERTIES FILE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROPERTIES_FILE_VALUE
	 * @generated
	 * @ordered
	 */
	PROPERTIES_FILE(1, "PROPERTIES_FILE", "PROPERTIES_FILE");

	/**
	 * The '<em><b>RELATIONAL DB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RELATIONAL DB</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RELATIONAL_DB
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RELATIONAL_DB_VALUE = 0;

	/**
	 * The '<em><b>PROPERTIES FILE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PROPERTIES FILE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PROPERTIES_FILE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTIES_FILE_VALUE = 1;

	/**
	 * An array of all the '<em><b>Store Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DomainStoreTypes[] VALUES_ARRAY =
		new DomainStoreTypes[] {
			RELATIONAL_DB,
			PROPERTIES_FILE,
		};

	/**
	 * A public read-only list of all the '<em><b>Store Types</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<DomainStoreTypes> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Store Types</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DomainStoreTypes get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DomainStoreTypes result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Store Types</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DomainStoreTypes getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DomainStoreTypes result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Store Types</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DomainStoreTypes get(int value) {
		switch (value) {
			case RELATIONAL_DB_VALUE: return RELATIONAL_DB;
			case PROPERTIES_FILE_VALUE: return PROPERTIES_FILE;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DomainStoreTypes(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //DomainStoreTypes
