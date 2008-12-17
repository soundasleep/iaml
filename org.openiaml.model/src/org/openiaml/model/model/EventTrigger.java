/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.openiaml.model.model.ModelPackage#getEventTrigger()
 * @model
 * @generated
 */
public interface EventTrigger extends NamedElement, WireEdgesSource {
	
	/**
	 * Follow this event trigger and find out which operation can be
	 * considered "last" in the chain.
	 * 
	 * @generated NOT
	 * @return the last ChainedOperation in this chain, or null if there are none
	 */
	public ChainedOperation getLastChainedOperation();
	
} // EventTrigger
