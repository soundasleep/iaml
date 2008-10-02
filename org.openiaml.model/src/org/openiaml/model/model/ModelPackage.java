/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.openiaml.model.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://openiaml.org/model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "iaml";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = org.openiaml.model.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.NamedElement <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.NamedElement
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.WireEdgeImpl <em>Wire Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.WireEdgeImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getWireEdge()
	 * @generated
	 */
	int WIRE_EDGE = 1;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_EDGE__FROM = 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_EDGE__TO = 1;

	/**
	 * The number of structural features of the '<em>Wire Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_EDGE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.WireEdgeDestination <em>Wire Edge Destination</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.WireEdgeDestination
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getWireEdgeDestination()
	 * @generated
	 */
	int WIRE_EDGE_DESTINATION = 2;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_EDGE_DESTINATION__IN_EDGES = 0;

	/**
	 * The number of structural features of the '<em>Wire Edge Destination</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_EDGE_DESTINATION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.WireEdgesSource <em>Wire Edges Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.WireEdgesSource
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getWireEdgesSource()
	 * @generated
	 */
	int WIRE_EDGES_SOURCE = 3;

	/**
	 * The feature id for the '<em><b>Out Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_EDGES_SOURCE__OUT_EDGES = 0;

	/**
	 * The number of structural features of the '<em>Wire Edges Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIRE_EDGES_SOURCE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.EventTriggerImpl <em>Event Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.EventTriggerImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getEventTrigger()
	 * @generated
	 */
	int EVENT_TRIGGER = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TRIGGER__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Out Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TRIGGER__OUT_EDGES = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Event Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TRIGGER_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.ContainsEventTriggers <em>Contains Event Triggers</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.ContainsEventTriggers
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getContainsEventTriggers()
	 * @generated
	 */
	int CONTAINS_EVENT_TRIGGERS = 5;

	/**
	 * The feature id for the '<em><b>Event Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINS_EVENT_TRIGGERS__EVENT_TRIGGERS = 0;

	/**
	 * The number of structural features of the '<em>Contains Event Triggers</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINS_EVENT_TRIGGERS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.ContainsOperations <em>Contains Operations</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.ContainsOperations
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getContainsOperations()
	 * @generated
	 */
	int CONTAINS_OPERATIONS = 14;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINS_OPERATIONS__OPERATIONS = 0;

	/**
	 * The number of structural features of the '<em>Contains Operations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINS_OPERATIONS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.ApplicationElementImpl <em>Application Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.ApplicationElementImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getApplicationElement()
	 * @generated
	 */
	int APPLICATION_ELEMENT = 15;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT__OPERATIONS = CONTAINS_OPERATIONS__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT__NAME = CONTAINS_OPERATIONS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Event Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT__EVENT_TRIGGERS = CONTAINS_OPERATIONS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Out Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT__OUT_EDGES = CONTAINS_OPERATIONS_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT__IN_EDGES = CONTAINS_OPERATIONS_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT__PROPERTIES = CONTAINS_OPERATIONS_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Application Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_FEATURE_COUNT = CONTAINS_OPERATIONS_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.DomainObjectImpl <em>Domain Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.DomainObjectImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getDomainObject()
	 * @generated
	 */
	int DOMAIN_OBJECT = 6;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__OPERATIONS = APPLICATION_ELEMENT__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__NAME = APPLICATION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Event Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__EVENT_TRIGGERS = APPLICATION_ELEMENT__EVENT_TRIGGERS;

	/**
	 * The feature id for the '<em><b>Out Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__OUT_EDGES = APPLICATION_ELEMENT__OUT_EDGES;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__IN_EDGES = APPLICATION_ELEMENT__IN_EDGES;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__PROPERTIES = APPLICATION_ELEMENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__ATTRIBUTES = APPLICATION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Domain Object Wires</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__DOMAIN_OBJECT_WIRES = APPLICATION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Domain Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT_FEATURE_COUNT = APPLICATION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.DomainAttributeImpl <em>Domain Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.DomainAttributeImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getDomainAttribute()
	 * @generated
	 */
	int DOMAIN_ATTRIBUTE = 7;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_ATTRIBUTE__OPERATIONS = APPLICATION_ELEMENT__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_ATTRIBUTE__NAME = APPLICATION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Event Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_ATTRIBUTE__EVENT_TRIGGERS = APPLICATION_ELEMENT__EVENT_TRIGGERS;

	/**
	 * The feature id for the '<em><b>Out Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_ATTRIBUTE__OUT_EDGES = APPLICATION_ELEMENT__OUT_EDGES;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_ATTRIBUTE__IN_EDGES = APPLICATION_ELEMENT__IN_EDGES;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_ATTRIBUTE__PROPERTIES = APPLICATION_ELEMENT__PROPERTIES;

	/**
	 * The number of structural features of the '<em>Domain Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_ATTRIBUTE_FEATURE_COUNT = APPLICATION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.ActivityNodeImpl <em>Activity Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.ActivityNodeImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getActivityNode()
	 * @generated
	 */
	int ACTIVITY_NODE = 8;

	/**
	 * The number of structural features of the '<em>Activity Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_NODE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.OperationImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 9;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__IN_EDGES = WIRE_EDGE_DESTINATION__IN_EDGES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__NAME = WIRE_EDGE_DESTINATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>In Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__IN_FLOWS = WIRE_EDGE_DESTINATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>In Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__IN_EXECUTIONS = WIRE_EDGE_DESTINATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Out Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__OUT_FLOWS = WIRE_EDGE_DESTINATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__PARAMETERS = WIRE_EDGE_DESTINATION_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = WIRE_EDGE_DESTINATION_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.ParameterImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Out Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__OUT_FLOWS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>In Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__IN_FLOWS = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.SingleOperationImpl <em>Single Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.SingleOperationImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getSingleOperation()
	 * @generated
	 */
	int SINGLE_OPERATION = 11;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_OPERATION__IN_EDGES = OPERATION__IN_EDGES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_OPERATION__NAME = OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>In Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_OPERATION__IN_FLOWS = OPERATION__IN_FLOWS;

	/**
	 * The feature id for the '<em><b>In Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_OPERATION__IN_EXECUTIONS = OPERATION__IN_EXECUTIONS;

	/**
	 * The feature id for the '<em><b>Out Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_OPERATION__OUT_FLOWS = OPERATION__OUT_FLOWS;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_OPERATION__PARAMETERS = OPERATION__PARAMETERS;

	/**
	 * The number of structural features of the '<em>Single Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_OPERATION_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.ChainedOperationImpl <em>Chained Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.ChainedOperationImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getChainedOperation()
	 * @generated
	 */
	int CHAINED_OPERATION = 12;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAINED_OPERATION__IN_EDGES = OPERATION__IN_EDGES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAINED_OPERATION__NAME = OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>In Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAINED_OPERATION__IN_FLOWS = OPERATION__IN_FLOWS;

	/**
	 * The feature id for the '<em><b>In Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAINED_OPERATION__IN_EXECUTIONS = OPERATION__IN_EXECUTIONS;

	/**
	 * The feature id for the '<em><b>Out Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAINED_OPERATION__OUT_FLOWS = OPERATION__OUT_FLOWS;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAINED_OPERATION__PARAMETERS = OPERATION__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Out Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAINED_OPERATION__OUT_EXECUTIONS = OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Chained Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAINED_OPERATION_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.CompositeOperationImpl <em>Composite Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.CompositeOperationImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getCompositeOperation()
	 * @generated
	 */
	int COMPOSITE_OPERATION = 13;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_OPERATION__IN_EDGES = CHAINED_OPERATION__IN_EDGES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_OPERATION__NAME = CHAINED_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>In Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_OPERATION__IN_FLOWS = CHAINED_OPERATION__IN_FLOWS;

	/**
	 * The feature id for the '<em><b>In Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_OPERATION__IN_EXECUTIONS = CHAINED_OPERATION__IN_EXECUTIONS;

	/**
	 * The feature id for the '<em><b>Out Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_OPERATION__OUT_FLOWS = CHAINED_OPERATION__OUT_FLOWS;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_OPERATION__PARAMETERS = CHAINED_OPERATION__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Out Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_OPERATION__OUT_EXECUTIONS = CHAINED_OPERATION__OUT_EXECUTIONS;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_OPERATION__NODES = CHAINED_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Composite Operation Wires</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_OPERATION__COMPOSITE_OPERATION_WIRES = CHAINED_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Data Edges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_OPERATION__DATA_EDGES = CHAINED_OPERATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Execution Edges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_OPERATION__EXECUTION_EDGES = CHAINED_OPERATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Composite Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_OPERATION_FEATURE_COUNT = CHAINED_OPERATION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.ApplicationElementContainerImpl <em>Application Element Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.ApplicationElementContainerImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getApplicationElementContainer()
	 * @generated
	 */
	int APPLICATION_ELEMENT_CONTAINER = 16;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_CONTAINER__OPERATIONS = APPLICATION_ELEMENT__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_CONTAINER__NAME = APPLICATION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Event Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_CONTAINER__EVENT_TRIGGERS = APPLICATION_ELEMENT__EVENT_TRIGGERS;

	/**
	 * The feature id for the '<em><b>Out Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_CONTAINER__OUT_EDGES = APPLICATION_ELEMENT__OUT_EDGES;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_CONTAINER__IN_EDGES = APPLICATION_ELEMENT__IN_EDGES;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_CONTAINER__PROPERTIES = APPLICATION_ELEMENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_CONTAINER__CHILDREN = APPLICATION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Application Element Wires</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_CONTAINER__APPLICATION_ELEMENT_WIRES = APPLICATION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Application Element Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_CONTAINER_FEATURE_COUNT = APPLICATION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.ApplicationElementPropertyImpl <em>Application Element Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.ApplicationElementPropertyImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getApplicationElementProperty()
	 * @generated
	 */
	int APPLICATION_ELEMENT_PROPERTY = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_PROPERTY__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Out Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_PROPERTY__OUT_EDGES = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_PROPERTY__IN_EDGES = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Out Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_PROPERTY__OUT_FLOWS = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>In Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_PROPERTY__IN_FLOWS = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Application Element Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_ELEMENT_PROPERTY_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.StaticValueImpl <em>Static Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.StaticValueImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getStaticValue()
	 * @generated
	 */
	int STATIC_VALUE = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_VALUE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Out Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_VALUE__OUT_EDGES = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>In Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_VALUE__IN_FLOWS = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_VALUE__VALUE = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Static Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_VALUE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.VisibleThingImpl <em>Visible Thing</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.VisibleThingImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getVisibleThing()
	 * @generated
	 */
	int VISIBLE_THING = 19;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_THING__OPERATIONS = APPLICATION_ELEMENT_CONTAINER__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_THING__NAME = APPLICATION_ELEMENT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Event Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_THING__EVENT_TRIGGERS = APPLICATION_ELEMENT_CONTAINER__EVENT_TRIGGERS;

	/**
	 * The feature id for the '<em><b>Out Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_THING__OUT_EDGES = APPLICATION_ELEMENT_CONTAINER__OUT_EDGES;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_THING__IN_EDGES = APPLICATION_ELEMENT_CONTAINER__IN_EDGES;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_THING__PROPERTIES = APPLICATION_ELEMENT_CONTAINER__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_THING__CHILDREN = APPLICATION_ELEMENT_CONTAINER__CHILDREN;

	/**
	 * The feature id for the '<em><b>Application Element Wires</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_THING__APPLICATION_ELEMENT_WIRES = APPLICATION_ELEMENT_CONTAINER__APPLICATION_ELEMENT_WIRES;

	/**
	 * The number of structural features of the '<em>Visible Thing</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VISIBLE_THING_FEATURE_COUNT = APPLICATION_ELEMENT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.InternetApplicationImpl <em>Internet Application</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.InternetApplicationImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getInternetApplication()
	 * @generated
	 */
	int INTERNET_APPLICATION = 20;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNET_APPLICATION__OPERATIONS = CONTAINS_OPERATIONS__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Event Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNET_APPLICATION__EVENT_TRIGGERS = CONTAINS_OPERATIONS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNET_APPLICATION__NAME = CONTAINS_OPERATIONS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNET_APPLICATION__PROPERTIES = CONTAINS_OPERATIONS_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNET_APPLICATION__CHILDREN = CONTAINS_OPERATIONS_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Domain Stores</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNET_APPLICATION__DOMAIN_STORES = CONTAINS_OPERATIONS_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Ia Wires</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNET_APPLICATION__IA_WIRES = CONTAINS_OPERATIONS_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Internet Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNET_APPLICATION_FEATURE_COUNT = CONTAINS_OPERATIONS_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.DomainStoreImpl <em>Domain Store</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.DomainStoreImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getDomainStore()
	 * @generated
	 */
	int DOMAIN_STORE = 21;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STORE__OPERATIONS = CONTAINS_OPERATIONS__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Event Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STORE__EVENT_TRIGGERS = CONTAINS_OPERATIONS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STORE__NAME = CONTAINS_OPERATIONS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STORE__CHILDREN = CONTAINS_OPERATIONS_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STORE__PROPERTIES = CONTAINS_OPERATIONS_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Domain Store Wires</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STORE__DOMAIN_STORE_WIRES = CONTAINS_OPERATIONS_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Domain Store</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STORE_FEATURE_COUNT = CONTAINS_OPERATIONS_FEATURE_COUNT + 5;


	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.DataFlowEdgeImpl <em>Data Flow Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.DataFlowEdgeImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getDataFlowEdge()
	 * @generated
	 */
	int DATA_FLOW_EDGE = 22;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FLOW_EDGE__FROM = 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FLOW_EDGE__TO = 1;

	/**
	 * The number of structural features of the '<em>Data Flow Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FLOW_EDGE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.DataFlowEdgeDestination <em>Data Flow Edge Destination</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.DataFlowEdgeDestination
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getDataFlowEdgeDestination()
	 * @generated
	 */
	int DATA_FLOW_EDGE_DESTINATION = 23;

	/**
	 * The feature id for the '<em><b>In Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FLOW_EDGE_DESTINATION__IN_FLOWS = 0;

	/**
	 * The number of structural features of the '<em>Data Flow Edge Destination</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FLOW_EDGE_DESTINATION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.DataFlowEdgesSource <em>Data Flow Edges Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.DataFlowEdgesSource
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getDataFlowEdgesSource()
	 * @generated
	 */
	int DATA_FLOW_EDGES_SOURCE = 24;

	/**
	 * The feature id for the '<em><b>Out Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FLOW_EDGES_SOURCE__OUT_FLOWS = 0;

	/**
	 * The number of structural features of the '<em>Data Flow Edges Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_FLOW_EDGES_SOURCE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.TemporaryVariableImpl <em>Temporary Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.TemporaryVariableImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getTemporaryVariable()
	 * @generated
	 */
	int TEMPORARY_VARIABLE = 25;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_VARIABLE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Out Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_VARIABLE__OUT_FLOWS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>In Flows</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_VARIABLE__IN_FLOWS = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Temporary Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_VARIABLE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.ExecutionEdgeImpl <em>Execution Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.ExecutionEdgeImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getExecutionEdge()
	 * @generated
	 */
	int EXECUTION_EDGE = 26;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_EDGE__FROM = 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_EDGE__TO = 1;

	/**
	 * The number of structural features of the '<em>Execution Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_EDGE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.ExecutionEdgeDestination <em>Execution Edge Destination</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.ExecutionEdgeDestination
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getExecutionEdgeDestination()
	 * @generated
	 */
	int EXECUTION_EDGE_DESTINATION = 27;

	/**
	 * The feature id for the '<em><b>In Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_EDGE_DESTINATION__IN_EXECUTIONS = 0;

	/**
	 * The number of structural features of the '<em>Execution Edge Destination</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_EDGE_DESTINATION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.ExecutionEdgesSource <em>Execution Edges Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.ExecutionEdgesSource
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getExecutionEdgesSource()
	 * @generated
	 */
	int EXECUTION_EDGES_SOURCE = 28;

	/**
	 * The feature id for the '<em><b>Out Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_EDGES_SOURCE__OUT_EXECUTIONS = 0;

	/**
	 * The number of structural features of the '<em>Execution Edges Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_EDGES_SOURCE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.openiaml.model.model.impl.DynamicApplicationElementSetImpl <em>Dynamic Application Element Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.openiaml.model.model.impl.DynamicApplicationElementSetImpl
	 * @see org.openiaml.model.model.impl.ModelPackageImpl#getDynamicApplicationElementSet()
	 * @generated
	 */
	int DYNAMIC_APPLICATION_ELEMENT_SET = 29;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_APPLICATION_ELEMENT_SET__OPERATIONS = CONTAINS_OPERATIONS__OPERATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_APPLICATION_ELEMENT_SET__NAME = CONTAINS_OPERATIONS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Event Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_APPLICATION_ELEMENT_SET__EVENT_TRIGGERS = CONTAINS_OPERATIONS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Out Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_APPLICATION_ELEMENT_SET__OUT_EDGES = CONTAINS_OPERATIONS_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>In Edges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_APPLICATION_ELEMENT_SET__IN_EDGES = CONTAINS_OPERATIONS_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Query</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_APPLICATION_ELEMENT_SET__QUERY = CONTAINS_OPERATIONS_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Dynamic Application Element Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_APPLICATION_ELEMENT_SET_FEATURE_COUNT = CONTAINS_OPERATIONS_FEATURE_COUNT + 5;


	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see org.openiaml.model.model.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.model.model.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.openiaml.model.model.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.WireEdge <em>Wire Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wire Edge</em>'.
	 * @see org.openiaml.model.model.WireEdge
	 * @generated
	 */
	EClass getWireEdge();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.model.model.WireEdge#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see org.openiaml.model.model.WireEdge#getFrom()
	 * @see #getWireEdge()
	 * @generated
	 */
	EReference getWireEdge_From();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.model.model.WireEdge#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see org.openiaml.model.model.WireEdge#getTo()
	 * @see #getWireEdge()
	 * @generated
	 */
	EReference getWireEdge_To();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.WireEdgeDestination <em>Wire Edge Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wire Edge Destination</em>'.
	 * @see org.openiaml.model.model.WireEdgeDestination
	 * @generated
	 */
	EClass getWireEdgeDestination();

	/**
	 * Returns the meta object for the reference list '{@link org.openiaml.model.model.WireEdgeDestination#getInEdges <em>In Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>In Edges</em>'.
	 * @see org.openiaml.model.model.WireEdgeDestination#getInEdges()
	 * @see #getWireEdgeDestination()
	 * @generated
	 */
	EReference getWireEdgeDestination_InEdges();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.WireEdgesSource <em>Wire Edges Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wire Edges Source</em>'.
	 * @see org.openiaml.model.model.WireEdgesSource
	 * @generated
	 */
	EClass getWireEdgesSource();

	/**
	 * Returns the meta object for the reference list '{@link org.openiaml.model.model.WireEdgesSource#getOutEdges <em>Out Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Out Edges</em>'.
	 * @see org.openiaml.model.model.WireEdgesSource#getOutEdges()
	 * @see #getWireEdgesSource()
	 * @generated
	 */
	EReference getWireEdgesSource_OutEdges();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.EventTrigger <em>Event Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Trigger</em>'.
	 * @see org.openiaml.model.model.EventTrigger
	 * @generated
	 */
	EClass getEventTrigger();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.ContainsEventTriggers <em>Contains Event Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contains Event Triggers</em>'.
	 * @see org.openiaml.model.model.ContainsEventTriggers
	 * @generated
	 */
	EClass getContainsEventTriggers();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.ContainsEventTriggers#getEventTriggers <em>Event Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event Triggers</em>'.
	 * @see org.openiaml.model.model.ContainsEventTriggers#getEventTriggers()
	 * @see #getContainsEventTriggers()
	 * @generated
	 */
	EReference getContainsEventTriggers_EventTriggers();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.DomainObject <em>Domain Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Domain Object</em>'.
	 * @see org.openiaml.model.model.DomainObject
	 * @generated
	 */
	EClass getDomainObject();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.DomainObject#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.openiaml.model.model.DomainObject#getAttributes()
	 * @see #getDomainObject()
	 * @generated
	 */
	EReference getDomainObject_Attributes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.DomainObject#getDomainObjectWires <em>Domain Object Wires</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Domain Object Wires</em>'.
	 * @see org.openiaml.model.model.DomainObject#getDomainObjectWires()
	 * @see #getDomainObject()
	 * @generated
	 */
	EReference getDomainObject_DomainObjectWires();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.DomainAttribute <em>Domain Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Domain Attribute</em>'.
	 * @see org.openiaml.model.model.DomainAttribute
	 * @generated
	 */
	EClass getDomainAttribute();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.ActivityNode <em>Activity Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Node</em>'.
	 * @see org.openiaml.model.model.ActivityNode
	 * @generated
	 */
	EClass getActivityNode();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see org.openiaml.model.model.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.Operation#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see org.openiaml.model.model.Operation#getParameters()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Parameters();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see org.openiaml.model.model.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.SingleOperation <em>Single Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Operation</em>'.
	 * @see org.openiaml.model.model.SingleOperation
	 * @generated
	 */
	EClass getSingleOperation();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.ChainedOperation <em>Chained Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Chained Operation</em>'.
	 * @see org.openiaml.model.model.ChainedOperation
	 * @generated
	 */
	EClass getChainedOperation();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.CompositeOperation <em>Composite Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Operation</em>'.
	 * @see org.openiaml.model.model.CompositeOperation
	 * @generated
	 */
	EClass getCompositeOperation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.CompositeOperation#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see org.openiaml.model.model.CompositeOperation#getNodes()
	 * @see #getCompositeOperation()
	 * @generated
	 */
	EReference getCompositeOperation_Nodes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.CompositeOperation#getCompositeOperationWires <em>Composite Operation Wires</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Composite Operation Wires</em>'.
	 * @see org.openiaml.model.model.CompositeOperation#getCompositeOperationWires()
	 * @see #getCompositeOperation()
	 * @generated
	 */
	EReference getCompositeOperation_CompositeOperationWires();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.CompositeOperation#getDataEdges <em>Data Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Edges</em>'.
	 * @see org.openiaml.model.model.CompositeOperation#getDataEdges()
	 * @see #getCompositeOperation()
	 * @generated
	 */
	EReference getCompositeOperation_DataEdges();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.CompositeOperation#getExecutionEdges <em>Execution Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Execution Edges</em>'.
	 * @see org.openiaml.model.model.CompositeOperation#getExecutionEdges()
	 * @see #getCompositeOperation()
	 * @generated
	 */
	EReference getCompositeOperation_ExecutionEdges();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.ContainsOperations <em>Contains Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contains Operations</em>'.
	 * @see org.openiaml.model.model.ContainsOperations
	 * @generated
	 */
	EClass getContainsOperations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.ContainsOperations#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see org.openiaml.model.model.ContainsOperations#getOperations()
	 * @see #getContainsOperations()
	 * @generated
	 */
	EReference getContainsOperations_Operations();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.ApplicationElement <em>Application Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application Element</em>'.
	 * @see org.openiaml.model.model.ApplicationElement
	 * @generated
	 */
	EClass getApplicationElement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.ApplicationElement#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see org.openiaml.model.model.ApplicationElement#getProperties()
	 * @see #getApplicationElement()
	 * @generated
	 */
	EReference getApplicationElement_Properties();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.ApplicationElementContainer <em>Application Element Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application Element Container</em>'.
	 * @see org.openiaml.model.model.ApplicationElementContainer
	 * @generated
	 */
	EClass getApplicationElementContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.ApplicationElementContainer#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.openiaml.model.model.ApplicationElementContainer#getChildren()
	 * @see #getApplicationElementContainer()
	 * @generated
	 */
	EReference getApplicationElementContainer_Children();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.ApplicationElementContainer#getApplicationElementWires <em>Application Element Wires</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Application Element Wires</em>'.
	 * @see org.openiaml.model.model.ApplicationElementContainer#getApplicationElementWires()
	 * @see #getApplicationElementContainer()
	 * @generated
	 */
	EReference getApplicationElementContainer_ApplicationElementWires();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.ApplicationElementProperty <em>Application Element Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application Element Property</em>'.
	 * @see org.openiaml.model.model.ApplicationElementProperty
	 * @generated
	 */
	EClass getApplicationElementProperty();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.StaticValue <em>Static Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Static Value</em>'.
	 * @see org.openiaml.model.model.StaticValue
	 * @generated
	 */
	EClass getStaticValue();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.model.model.StaticValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.openiaml.model.model.StaticValue#getValue()
	 * @see #getStaticValue()
	 * @generated
	 */
	EAttribute getStaticValue_Value();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.VisibleThing <em>Visible Thing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Visible Thing</em>'.
	 * @see org.openiaml.model.model.VisibleThing
	 * @generated
	 */
	EClass getVisibleThing();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.InternetApplication <em>Internet Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Internet Application</em>'.
	 * @see org.openiaml.model.model.InternetApplication
	 * @generated
	 */
	EClass getInternetApplication();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.InternetApplication#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see org.openiaml.model.model.InternetApplication#getProperties()
	 * @see #getInternetApplication()
	 * @generated
	 */
	EReference getInternetApplication_Properties();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.InternetApplication#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.openiaml.model.model.InternetApplication#getChildren()
	 * @see #getInternetApplication()
	 * @generated
	 */
	EReference getInternetApplication_Children();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.InternetApplication#getDomainStores <em>Domain Stores</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Domain Stores</em>'.
	 * @see org.openiaml.model.model.InternetApplication#getDomainStores()
	 * @see #getInternetApplication()
	 * @generated
	 */
	EReference getInternetApplication_DomainStores();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.InternetApplication#getIaWires <em>Ia Wires</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ia Wires</em>'.
	 * @see org.openiaml.model.model.InternetApplication#getIaWires()
	 * @see #getInternetApplication()
	 * @generated
	 */
	EReference getInternetApplication_IaWires();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.DomainStore <em>Domain Store</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Domain Store</em>'.
	 * @see org.openiaml.model.model.DomainStore
	 * @generated
	 */
	EClass getDomainStore();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.DomainStore#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.openiaml.model.model.DomainStore#getChildren()
	 * @see #getDomainStore()
	 * @generated
	 */
	EReference getDomainStore_Children();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.DomainStore#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see org.openiaml.model.model.DomainStore#getProperties()
	 * @see #getDomainStore()
	 * @generated
	 */
	EReference getDomainStore_Properties();

	/**
	 * Returns the meta object for the containment reference list '{@link org.openiaml.model.model.DomainStore#getDomainStoreWires <em>Domain Store Wires</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Domain Store Wires</em>'.
	 * @see org.openiaml.model.model.DomainStore#getDomainStoreWires()
	 * @see #getDomainStore()
	 * @generated
	 */
	EReference getDomainStore_DomainStoreWires();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.DataFlowEdge <em>Data Flow Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Flow Edge</em>'.
	 * @see org.openiaml.model.model.DataFlowEdge
	 * @generated
	 */
	EClass getDataFlowEdge();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.model.model.DataFlowEdge#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see org.openiaml.model.model.DataFlowEdge#getFrom()
	 * @see #getDataFlowEdge()
	 * @generated
	 */
	EReference getDataFlowEdge_From();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.model.model.DataFlowEdge#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see org.openiaml.model.model.DataFlowEdge#getTo()
	 * @see #getDataFlowEdge()
	 * @generated
	 */
	EReference getDataFlowEdge_To();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.DataFlowEdgeDestination <em>Data Flow Edge Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Flow Edge Destination</em>'.
	 * @see org.openiaml.model.model.DataFlowEdgeDestination
	 * @generated
	 */
	EClass getDataFlowEdgeDestination();

	/**
	 * Returns the meta object for the reference list '{@link org.openiaml.model.model.DataFlowEdgeDestination#getInFlows <em>In Flows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>In Flows</em>'.
	 * @see org.openiaml.model.model.DataFlowEdgeDestination#getInFlows()
	 * @see #getDataFlowEdgeDestination()
	 * @generated
	 */
	EReference getDataFlowEdgeDestination_InFlows();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.DataFlowEdgesSource <em>Data Flow Edges Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Flow Edges Source</em>'.
	 * @see org.openiaml.model.model.DataFlowEdgesSource
	 * @generated
	 */
	EClass getDataFlowEdgesSource();

	/**
	 * Returns the meta object for the reference list '{@link org.openiaml.model.model.DataFlowEdgesSource#getOutFlows <em>Out Flows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Out Flows</em>'.
	 * @see org.openiaml.model.model.DataFlowEdgesSource#getOutFlows()
	 * @see #getDataFlowEdgesSource()
	 * @generated
	 */
	EReference getDataFlowEdgesSource_OutFlows();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.TemporaryVariable <em>Temporary Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Temporary Variable</em>'.
	 * @see org.openiaml.model.model.TemporaryVariable
	 * @generated
	 */
	EClass getTemporaryVariable();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.ExecutionEdge <em>Execution Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Edge</em>'.
	 * @see org.openiaml.model.model.ExecutionEdge
	 * @generated
	 */
	EClass getExecutionEdge();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.model.model.ExecutionEdge#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see org.openiaml.model.model.ExecutionEdge#getFrom()
	 * @see #getExecutionEdge()
	 * @generated
	 */
	EReference getExecutionEdge_From();

	/**
	 * Returns the meta object for the reference '{@link org.openiaml.model.model.ExecutionEdge#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see org.openiaml.model.model.ExecutionEdge#getTo()
	 * @see #getExecutionEdge()
	 * @generated
	 */
	EReference getExecutionEdge_To();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.ExecutionEdgeDestination <em>Execution Edge Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Edge Destination</em>'.
	 * @see org.openiaml.model.model.ExecutionEdgeDestination
	 * @generated
	 */
	EClass getExecutionEdgeDestination();

	/**
	 * Returns the meta object for the reference list '{@link org.openiaml.model.model.ExecutionEdgeDestination#getInExecutions <em>In Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>In Executions</em>'.
	 * @see org.openiaml.model.model.ExecutionEdgeDestination#getInExecutions()
	 * @see #getExecutionEdgeDestination()
	 * @generated
	 */
	EReference getExecutionEdgeDestination_InExecutions();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.ExecutionEdgesSource <em>Execution Edges Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Edges Source</em>'.
	 * @see org.openiaml.model.model.ExecutionEdgesSource
	 * @generated
	 */
	EClass getExecutionEdgesSource();

	/**
	 * Returns the meta object for the reference list '{@link org.openiaml.model.model.ExecutionEdgesSource#getOutExecutions <em>Out Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Out Executions</em>'.
	 * @see org.openiaml.model.model.ExecutionEdgesSource#getOutExecutions()
	 * @see #getExecutionEdgesSource()
	 * @generated
	 */
	EReference getExecutionEdgesSource_OutExecutions();

	/**
	 * Returns the meta object for class '{@link org.openiaml.model.model.DynamicApplicationElementSet <em>Dynamic Application Element Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Application Element Set</em>'.
	 * @see org.openiaml.model.model.DynamicApplicationElementSet
	 * @generated
	 */
	EClass getDynamicApplicationElementSet();

	/**
	 * Returns the meta object for the attribute '{@link org.openiaml.model.model.DynamicApplicationElementSet#getQuery <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Query</em>'.
	 * @see org.openiaml.model.model.DynamicApplicationElementSet#getQuery()
	 * @see #getDynamicApplicationElementSet()
	 * @generated
	 */
	EAttribute getDynamicApplicationElementSet_Query();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.NamedElement <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.NamedElement
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.WireEdgeImpl <em>Wire Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.WireEdgeImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getWireEdge()
		 * @generated
		 */
		EClass WIRE_EDGE = eINSTANCE.getWireEdge();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WIRE_EDGE__FROM = eINSTANCE.getWireEdge_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WIRE_EDGE__TO = eINSTANCE.getWireEdge_To();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.WireEdgeDestination <em>Wire Edge Destination</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.WireEdgeDestination
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getWireEdgeDestination()
		 * @generated
		 */
		EClass WIRE_EDGE_DESTINATION = eINSTANCE.getWireEdgeDestination();

		/**
		 * The meta object literal for the '<em><b>In Edges</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WIRE_EDGE_DESTINATION__IN_EDGES = eINSTANCE.getWireEdgeDestination_InEdges();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.WireEdgesSource <em>Wire Edges Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.WireEdgesSource
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getWireEdgesSource()
		 * @generated
		 */
		EClass WIRE_EDGES_SOURCE = eINSTANCE.getWireEdgesSource();

		/**
		 * The meta object literal for the '<em><b>Out Edges</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WIRE_EDGES_SOURCE__OUT_EDGES = eINSTANCE.getWireEdgesSource_OutEdges();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.EventTriggerImpl <em>Event Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.EventTriggerImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getEventTrigger()
		 * @generated
		 */
		EClass EVENT_TRIGGER = eINSTANCE.getEventTrigger();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.ContainsEventTriggers <em>Contains Event Triggers</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.ContainsEventTriggers
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getContainsEventTriggers()
		 * @generated
		 */
		EClass CONTAINS_EVENT_TRIGGERS = eINSTANCE.getContainsEventTriggers();

		/**
		 * The meta object literal for the '<em><b>Event Triggers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINS_EVENT_TRIGGERS__EVENT_TRIGGERS = eINSTANCE.getContainsEventTriggers_EventTriggers();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.DomainObjectImpl <em>Domain Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.DomainObjectImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getDomainObject()
		 * @generated
		 */
		EClass DOMAIN_OBJECT = eINSTANCE.getDomainObject();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOMAIN_OBJECT__ATTRIBUTES = eINSTANCE.getDomainObject_Attributes();

		/**
		 * The meta object literal for the '<em><b>Domain Object Wires</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOMAIN_OBJECT__DOMAIN_OBJECT_WIRES = eINSTANCE.getDomainObject_DomainObjectWires();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.DomainAttributeImpl <em>Domain Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.DomainAttributeImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getDomainAttribute()
		 * @generated
		 */
		EClass DOMAIN_ATTRIBUTE = eINSTANCE.getDomainAttribute();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.ActivityNodeImpl <em>Activity Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.ActivityNodeImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getActivityNode()
		 * @generated
		 */
		EClass ACTIVITY_NODE = eINSTANCE.getActivityNode();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.OperationImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__PARAMETERS = eINSTANCE.getOperation_Parameters();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.ParameterImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.SingleOperationImpl <em>Single Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.SingleOperationImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getSingleOperation()
		 * @generated
		 */
		EClass SINGLE_OPERATION = eINSTANCE.getSingleOperation();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.ChainedOperationImpl <em>Chained Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.ChainedOperationImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getChainedOperation()
		 * @generated
		 */
		EClass CHAINED_OPERATION = eINSTANCE.getChainedOperation();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.CompositeOperationImpl <em>Composite Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.CompositeOperationImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getCompositeOperation()
		 * @generated
		 */
		EClass COMPOSITE_OPERATION = eINSTANCE.getCompositeOperation();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_OPERATION__NODES = eINSTANCE.getCompositeOperation_Nodes();

		/**
		 * The meta object literal for the '<em><b>Composite Operation Wires</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_OPERATION__COMPOSITE_OPERATION_WIRES = eINSTANCE.getCompositeOperation_CompositeOperationWires();

		/**
		 * The meta object literal for the '<em><b>Data Edges</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_OPERATION__DATA_EDGES = eINSTANCE.getCompositeOperation_DataEdges();

		/**
		 * The meta object literal for the '<em><b>Execution Edges</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_OPERATION__EXECUTION_EDGES = eINSTANCE.getCompositeOperation_ExecutionEdges();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.ContainsOperations <em>Contains Operations</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.ContainsOperations
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getContainsOperations()
		 * @generated
		 */
		EClass CONTAINS_OPERATIONS = eINSTANCE.getContainsOperations();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINS_OPERATIONS__OPERATIONS = eINSTANCE.getContainsOperations_Operations();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.ApplicationElementImpl <em>Application Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.ApplicationElementImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getApplicationElement()
		 * @generated
		 */
		EClass APPLICATION_ELEMENT = eINSTANCE.getApplicationElement();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_ELEMENT__PROPERTIES = eINSTANCE.getApplicationElement_Properties();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.ApplicationElementContainerImpl <em>Application Element Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.ApplicationElementContainerImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getApplicationElementContainer()
		 * @generated
		 */
		EClass APPLICATION_ELEMENT_CONTAINER = eINSTANCE.getApplicationElementContainer();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_ELEMENT_CONTAINER__CHILDREN = eINSTANCE.getApplicationElementContainer_Children();

		/**
		 * The meta object literal for the '<em><b>Application Element Wires</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_ELEMENT_CONTAINER__APPLICATION_ELEMENT_WIRES = eINSTANCE.getApplicationElementContainer_ApplicationElementWires();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.ApplicationElementPropertyImpl <em>Application Element Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.ApplicationElementPropertyImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getApplicationElementProperty()
		 * @generated
		 */
		EClass APPLICATION_ELEMENT_PROPERTY = eINSTANCE.getApplicationElementProperty();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.StaticValueImpl <em>Static Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.StaticValueImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getStaticValue()
		 * @generated
		 */
		EClass STATIC_VALUE = eINSTANCE.getStaticValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATIC_VALUE__VALUE = eINSTANCE.getStaticValue_Value();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.VisibleThingImpl <em>Visible Thing</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.VisibleThingImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getVisibleThing()
		 * @generated
		 */
		EClass VISIBLE_THING = eINSTANCE.getVisibleThing();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.InternetApplicationImpl <em>Internet Application</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.InternetApplicationImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getInternetApplication()
		 * @generated
		 */
		EClass INTERNET_APPLICATION = eINSTANCE.getInternetApplication();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERNET_APPLICATION__PROPERTIES = eINSTANCE.getInternetApplication_Properties();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERNET_APPLICATION__CHILDREN = eINSTANCE.getInternetApplication_Children();

		/**
		 * The meta object literal for the '<em><b>Domain Stores</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERNET_APPLICATION__DOMAIN_STORES = eINSTANCE.getInternetApplication_DomainStores();

		/**
		 * The meta object literal for the '<em><b>Ia Wires</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERNET_APPLICATION__IA_WIRES = eINSTANCE.getInternetApplication_IaWires();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.DomainStoreImpl <em>Domain Store</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.DomainStoreImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getDomainStore()
		 * @generated
		 */
		EClass DOMAIN_STORE = eINSTANCE.getDomainStore();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOMAIN_STORE__CHILDREN = eINSTANCE.getDomainStore_Children();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOMAIN_STORE__PROPERTIES = eINSTANCE.getDomainStore_Properties();

		/**
		 * The meta object literal for the '<em><b>Domain Store Wires</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOMAIN_STORE__DOMAIN_STORE_WIRES = eINSTANCE.getDomainStore_DomainStoreWires();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.DataFlowEdgeImpl <em>Data Flow Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.DataFlowEdgeImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getDataFlowEdge()
		 * @generated
		 */
		EClass DATA_FLOW_EDGE = eINSTANCE.getDataFlowEdge();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_FLOW_EDGE__FROM = eINSTANCE.getDataFlowEdge_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_FLOW_EDGE__TO = eINSTANCE.getDataFlowEdge_To();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.DataFlowEdgeDestination <em>Data Flow Edge Destination</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.DataFlowEdgeDestination
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getDataFlowEdgeDestination()
		 * @generated
		 */
		EClass DATA_FLOW_EDGE_DESTINATION = eINSTANCE.getDataFlowEdgeDestination();

		/**
		 * The meta object literal for the '<em><b>In Flows</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_FLOW_EDGE_DESTINATION__IN_FLOWS = eINSTANCE.getDataFlowEdgeDestination_InFlows();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.DataFlowEdgesSource <em>Data Flow Edges Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.DataFlowEdgesSource
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getDataFlowEdgesSource()
		 * @generated
		 */
		EClass DATA_FLOW_EDGES_SOURCE = eINSTANCE.getDataFlowEdgesSource();

		/**
		 * The meta object literal for the '<em><b>Out Flows</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_FLOW_EDGES_SOURCE__OUT_FLOWS = eINSTANCE.getDataFlowEdgesSource_OutFlows();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.TemporaryVariableImpl <em>Temporary Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.TemporaryVariableImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getTemporaryVariable()
		 * @generated
		 */
		EClass TEMPORARY_VARIABLE = eINSTANCE.getTemporaryVariable();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.ExecutionEdgeImpl <em>Execution Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.ExecutionEdgeImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getExecutionEdge()
		 * @generated
		 */
		EClass EXECUTION_EDGE = eINSTANCE.getExecutionEdge();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_EDGE__FROM = eINSTANCE.getExecutionEdge_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_EDGE__TO = eINSTANCE.getExecutionEdge_To();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.ExecutionEdgeDestination <em>Execution Edge Destination</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.ExecutionEdgeDestination
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getExecutionEdgeDestination()
		 * @generated
		 */
		EClass EXECUTION_EDGE_DESTINATION = eINSTANCE.getExecutionEdgeDestination();

		/**
		 * The meta object literal for the '<em><b>In Executions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_EDGE_DESTINATION__IN_EXECUTIONS = eINSTANCE.getExecutionEdgeDestination_InExecutions();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.ExecutionEdgesSource <em>Execution Edges Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.ExecutionEdgesSource
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getExecutionEdgesSource()
		 * @generated
		 */
		EClass EXECUTION_EDGES_SOURCE = eINSTANCE.getExecutionEdgesSource();

		/**
		 * The meta object literal for the '<em><b>Out Executions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_EDGES_SOURCE__OUT_EXECUTIONS = eINSTANCE.getExecutionEdgesSource_OutExecutions();

		/**
		 * The meta object literal for the '{@link org.openiaml.model.model.impl.DynamicApplicationElementSetImpl <em>Dynamic Application Element Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.openiaml.model.model.impl.DynamicApplicationElementSetImpl
		 * @see org.openiaml.model.model.impl.ModelPackageImpl#getDynamicApplicationElementSet()
		 * @generated
		 */
		EClass DYNAMIC_APPLICATION_ELEMENT_SET = eINSTANCE.getDynamicApplicationElementSet();

		/**
		 * The meta object literal for the '<em><b>Query</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_APPLICATION_ELEMENT_SET__QUERY = eINSTANCE.getDynamicApplicationElementSet_Query();

	}

} //ModelPackage
