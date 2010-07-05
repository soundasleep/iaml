/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.docs.modeldoc;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File Line Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openiaml.docs.modeldoc.FileLineReference#getFile <em>File</em>}</li>
 *   <li>{@link org.openiaml.docs.modeldoc.FileLineReference#getLine <em>Line</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getFileLineReference()
 * @model
 * @generated
 */
public interface FileLineReference extends Reference {
	/**
	 * Returns the value of the '<em><b>File</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.openiaml.docs.modeldoc.FileReference#getLines <em>Lines</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File</em>' container reference.
	 * @see #setFile(FileReference)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getFileLineReference_File()
	 * @see org.openiaml.docs.modeldoc.FileReference#getLines
	 * @model opposite="lines" transient="false"
	 * @generated
	 */
	FileReference getFile();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.FileLineReference#getFile <em>File</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File</em>' container reference.
	 * @see #getFile()
	 * @generated
	 */
	void setFile(FileReference value);

	/**
	 * Returns the value of the '<em><b>Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line</em>' attribute.
	 * @see #setLine(int)
	 * @see org.openiaml.docs.modeldoc.ModeldocPackage#getFileLineReference_Line()
	 * @model
	 * @generated
	 */
	int getLine();

	/**
	 * Sets the value of the '{@link org.openiaml.docs.modeldoc.FileLineReference#getLine <em>Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line</em>' attribute.
	 * @see #getLine()
	 * @generated
	 */
	void setLine(int value);

} // FileLineReference
