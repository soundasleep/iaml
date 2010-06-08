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
 * A representation of the model object '<em><b>Template File</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.TemplateFile#getPlugin <em>Plugin</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.TemplateFile#getPackage <em>Package</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.TemplateFile#getName <em>Name</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.TemplateFile#getTemplates <em>Templates</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getTemplateFile()
 * @model
 * @generated
 */
public interface TemplateFile extends Reference {
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
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getTemplateFile_Plugin()
	 * @model
	 * @generated
	 */
	String getPlugin();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.TemplateFile#getPlugin <em>Plugin</em>}' attribute.
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
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getTemplateFile_Package()
	 * @model
	 * @generated
	 */
	String getPackage();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.TemplateFile#getPackage <em>Package</em>}' attribute.
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
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getTemplateFile_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.TemplateFile#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Templates</b></em>' containment reference list.
	 * The list contents are of type {@link org.openiaml.docs.modeldoc.Template}.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.Template#getTemplateFile <em>Template File</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Templates</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Templates</em>' containment reference list.
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getTemplateFile_Templates()
	 * @see org.openiaml.docs.modeldoc.Template#getTemplateFile
	 * @model opposite="templateFile" containment="true"
	 * @generated
	 */
	EList<Template> getTemplates();

} // TemplateFile
