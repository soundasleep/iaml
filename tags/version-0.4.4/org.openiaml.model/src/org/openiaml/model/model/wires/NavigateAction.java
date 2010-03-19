/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.wires;

import org.openiaml.model.model.Action;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.WireDestination;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Navigate Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * When the {@model EventTrigger source event} executes, the user will be navigated to the target {@model Frame}.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.wires.WiresPackage#getNavigateAction()
 * @model annotation="http://openiaml.org/comment added='0.2'"
 * @generated
 */
public interface NavigateAction extends WireDestination, NamedElement, GeneratesElements, ConditionEdgeDestination, Action {
} // NavigateAction
