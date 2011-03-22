/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Drools Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.DroolsPackage#getPlugin <em>Plugin</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.DroolsPackage#getPackage <em>Package</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.DroolsPackage#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.DroolsPackage#getRules <em>Rules</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.DroolsPackage#getJavadocs <em>Javadocs</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.DroolsPackage#getUniqueRules <em>Unique Rules</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getDroolsPackage()
 * @model
 * @generated
 */
public interface DroolsPackage extends Reference {
	/**
	 * Returns the value of the '<em><b>Plugin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Plugin</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plugin</em>' attribute.
	 * @see #setPlugin(String)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getDroolsPackage_Plugin()
	 * @model
	 * @generated
	 */
	String getPlugin();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.DroolsPackage#getPlugin <em>Plugin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plugin</em>' attribute.
	 * @see #getPlugin()
	 * @generated
	 */
	void setPlugin(String value);

	/**
	 * Returns the value of the '<em><b>Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package</em>' attribute.
	 * @see #setPackage(String)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getDroolsPackage_Package()
	 * @model
	 * @generated
	 */
	String getPackage();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.DroolsPackage#getPackage <em>Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package</em>' attribute.
	 * @see #getPackage()
	 * @generated
	 */
	void setPackage(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getDroolsPackage_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.DroolsPackage#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.DroolsRule}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.DroolsRule#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rules</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rules</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getDroolsPackage_Rules()
	 * @see org.openiaml.docs.modeldoc.DroolsRule#getPackage
	 * @model opposite="package" containment="true"
	 * @generated
	 */
	EList<DroolsRule> getRules();

	/**
	 * Returns the value of the '<em><b>Javadocs</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.JavadocFragment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Javadocs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Javadocs</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getDroolsPackage_Javadocs()
	 * @model containment="true"
	 * @generated
	 */
	EList<JavadocFragment> getJavadocs();

	/**
	 * Returns the value of the '<em><b>Unique Rules</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unique Rules</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unique Rules</em>' attribute.
	 * @see #setUniqueRules(int)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getDroolsPackage_UniqueRules()
	 * @model
	 * @generated
	 */
	int getUniqueRules();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.DroolsPackage#getUniqueRules <em>Unique Rules</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unique Rules</em>' attribute.
	 * @see #getUniqueRules()
	 * @generated
	 */
	void setUniqueRules(int value);

} // DroolsPackage
