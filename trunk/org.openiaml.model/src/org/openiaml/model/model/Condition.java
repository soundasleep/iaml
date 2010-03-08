/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.openiaml.model.model.wires.ConditionEdgesSource;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Condition allows for conditional execution of {@model Wire wires} when {@model ConditionEdge connected}.
 * <!-- end-model-doc -->
 *
 *
 * @see org.openiaml.model.model.ModelPackage#getCondition()
 * @model abstract="true"
 *        annotation="http://openiaml.org/comment comment='added in 0.2'"
 * @generated
 */
public interface Condition extends WireSource, DataFlowEdgesSource, NamedElement, ConditionEdgesSource {
} // Condition
