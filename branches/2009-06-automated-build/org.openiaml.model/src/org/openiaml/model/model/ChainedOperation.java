/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Chained Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.openiaml.model.model.ModelPackage#getChainedOperation()
 * @model
 * @generated
 */
public interface ChainedOperation extends Operation, ExecutionEdgesSource, WireEdgesSource {

	/**
	 * Follow this operation and find out which operation can be
	 * considered "last" in the chain.
	 * 
	 * @generated NOT
	 * @return the last ChainedOperation in this chain, or this if this the last operation
	 */
	public ChainedOperation getLastChainedOperation();
	
} // ChainedOperation
