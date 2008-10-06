/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.openiaml.model.model.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.*;
import org.openiaml.model.model.ActivityNode;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ChainedOperation;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.DataFlowEdgeDestination;
import org.openiaml.model.model.DataFlowEdgesSource;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.ExecutionEdgeDestination;
import org.openiaml.model.model.ExecutionEdgesSource;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.SingleOperation;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.TemporaryVariable;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.openiaml.model.model.ModelPackage
 * @generated
 */
public class ModelSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelSwitch() {
		if (modelPackage == null) {
			modelPackage = ModelPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ModelPackage.NAMED_ELEMENT: {
				NamedElement namedElement = (NamedElement)theEObject;
				T result = caseNamedElement(namedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.WIRE_EDGE: {
				WireEdge wireEdge = (WireEdge)theEObject;
				T result = caseWireEdge(wireEdge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.WIRE_EDGE_DESTINATION: {
				WireEdgeDestination wireEdgeDestination = (WireEdgeDestination)theEObject;
				T result = caseWireEdgeDestination(wireEdgeDestination);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.WIRE_EDGES_SOURCE: {
				WireEdgesSource wireEdgesSource = (WireEdgesSource)theEObject;
				T result = caseWireEdgesSource(wireEdgesSource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.EVENT_TRIGGER: {
				EventTrigger eventTrigger = (EventTrigger)theEObject;
				T result = caseEventTrigger(eventTrigger);
				if (result == null) result = caseNamedElement(eventTrigger);
				if (result == null) result = caseWireEdgesSource(eventTrigger);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.CONTAINS_EVENT_TRIGGERS: {
				ContainsEventTriggers containsEventTriggers = (ContainsEventTriggers)theEObject;
				T result = caseContainsEventTriggers(containsEventTriggers);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOMAIN_OBJECT: {
				DomainObject domainObject = (DomainObject)theEObject;
				T result = caseDomainObject(domainObject);
				if (result == null) result = caseApplicationElement(domainObject);
				if (result == null) result = caseContainsWires(domainObject);
				if (result == null) result = caseContainsOperations(domainObject);
				if (result == null) result = caseNamedElement(domainObject);
				if (result == null) result = caseContainsEventTriggers(domainObject);
				if (result == null) result = caseWireEdgesSource(domainObject);
				if (result == null) result = caseWireEdgeDestination(domainObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOMAIN_ATTRIBUTE: {
				DomainAttribute domainAttribute = (DomainAttribute)theEObject;
				T result = caseDomainAttribute(domainAttribute);
				if (result == null) result = caseApplicationElement(domainAttribute);
				if (result == null) result = caseContainsOperations(domainAttribute);
				if (result == null) result = caseNamedElement(domainAttribute);
				if (result == null) result = caseContainsEventTriggers(domainAttribute);
				if (result == null) result = caseWireEdgesSource(domainAttribute);
				if (result == null) result = caseWireEdgeDestination(domainAttribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ACTIVITY_NODE: {
				ActivityNode activityNode = (ActivityNode)theEObject;
				T result = caseActivityNode(activityNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.OPERATION: {
				Operation operation = (Operation)theEObject;
				T result = caseOperation(operation);
				if (result == null) result = caseWireEdgeDestination(operation);
				if (result == null) result = caseNamedElement(operation);
				if (result == null) result = caseDataFlowEdgeDestination(operation);
				if (result == null) result = caseExecutionEdgeDestination(operation);
				if (result == null) result = caseActivityNode(operation);
				if (result == null) result = caseDataFlowEdgesSource(operation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.PARAMETER: {
				Parameter parameter = (Parameter)theEObject;
				T result = caseParameter(parameter);
				if (result == null) result = caseNamedElement(parameter);
				if (result == null) result = caseDataFlowEdgesSource(parameter);
				if (result == null) result = caseDataFlowEdgeDestination(parameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SINGLE_OPERATION: {
				SingleOperation singleOperation = (SingleOperation)theEObject;
				T result = caseSingleOperation(singleOperation);
				if (result == null) result = caseOperation(singleOperation);
				if (result == null) result = caseWireEdgeDestination(singleOperation);
				if (result == null) result = caseNamedElement(singleOperation);
				if (result == null) result = caseDataFlowEdgeDestination(singleOperation);
				if (result == null) result = caseExecutionEdgeDestination(singleOperation);
				if (result == null) result = caseActivityNode(singleOperation);
				if (result == null) result = caseDataFlowEdgesSource(singleOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.CHAINED_OPERATION: {
				ChainedOperation chainedOperation = (ChainedOperation)theEObject;
				T result = caseChainedOperation(chainedOperation);
				if (result == null) result = caseOperation(chainedOperation);
				if (result == null) result = caseExecutionEdgesSource(chainedOperation);
				if (result == null) result = caseWireEdgeDestination(chainedOperation);
				if (result == null) result = caseNamedElement(chainedOperation);
				if (result == null) result = caseDataFlowEdgeDestination(chainedOperation);
				if (result == null) result = caseExecutionEdgeDestination(chainedOperation);
				if (result == null) result = caseActivityNode(chainedOperation);
				if (result == null) result = caseDataFlowEdgesSource(chainedOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.COMPOSITE_OPERATION: {
				CompositeOperation compositeOperation = (CompositeOperation)theEObject;
				T result = caseCompositeOperation(compositeOperation);
				if (result == null) result = caseChainedOperation(compositeOperation);
				if (result == null) result = caseContainsOperations(compositeOperation);
				if (result == null) result = caseContainsWires(compositeOperation);
				if (result == null) result = caseOperation(compositeOperation);
				if (result == null) result = caseExecutionEdgesSource(compositeOperation);
				if (result == null) result = caseWireEdgeDestination(compositeOperation);
				if (result == null) result = caseNamedElement(compositeOperation);
				if (result == null) result = caseDataFlowEdgeDestination(compositeOperation);
				if (result == null) result = caseExecutionEdgeDestination(compositeOperation);
				if (result == null) result = caseActivityNode(compositeOperation);
				if (result == null) result = caseDataFlowEdgesSource(compositeOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.CONTAINS_OPERATIONS: {
				ContainsOperations containsOperations = (ContainsOperations)theEObject;
				T result = caseContainsOperations(containsOperations);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.APPLICATION_ELEMENT: {
				ApplicationElement applicationElement = (ApplicationElement)theEObject;
				T result = caseApplicationElement(applicationElement);
				if (result == null) result = caseContainsOperations(applicationElement);
				if (result == null) result = caseNamedElement(applicationElement);
				if (result == null) result = caseContainsEventTriggers(applicationElement);
				if (result == null) result = caseWireEdgesSource(applicationElement);
				if (result == null) result = caseWireEdgeDestination(applicationElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.APPLICATION_ELEMENT_CONTAINER: {
				ApplicationElementContainer applicationElementContainer = (ApplicationElementContainer)theEObject;
				T result = caseApplicationElementContainer(applicationElementContainer);
				if (result == null) result = caseApplicationElement(applicationElementContainer);
				if (result == null) result = caseContainsWires(applicationElementContainer);
				if (result == null) result = caseContainsOperations(applicationElementContainer);
				if (result == null) result = caseNamedElement(applicationElementContainer);
				if (result == null) result = caseContainsEventTriggers(applicationElementContainer);
				if (result == null) result = caseWireEdgesSource(applicationElementContainer);
				if (result == null) result = caseWireEdgeDestination(applicationElementContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.APPLICATION_ELEMENT_PROPERTY: {
				ApplicationElementProperty applicationElementProperty = (ApplicationElementProperty)theEObject;
				T result = caseApplicationElementProperty(applicationElementProperty);
				if (result == null) result = caseNamedElement(applicationElementProperty);
				if (result == null) result = caseWireEdgesSource(applicationElementProperty);
				if (result == null) result = caseWireEdgeDestination(applicationElementProperty);
				if (result == null) result = caseDataFlowEdgesSource(applicationElementProperty);
				if (result == null) result = caseDataFlowEdgeDestination(applicationElementProperty);
				if (result == null) result = caseContainsWires(applicationElementProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.STATIC_VALUE: {
				StaticValue staticValue = (StaticValue)theEObject;
				T result = caseStaticValue(staticValue);
				if (result == null) result = caseNamedElement(staticValue);
				if (result == null) result = caseWireEdgesSource(staticValue);
				if (result == null) result = caseDataFlowEdgeDestination(staticValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.VISIBLE_THING: {
				VisibleThing visibleThing = (VisibleThing)theEObject;
				T result = caseVisibleThing(visibleThing);
				if (result == null) result = caseApplicationElementContainer(visibleThing);
				if (result == null) result = caseApplicationElement(visibleThing);
				if (result == null) result = caseContainsWires(visibleThing);
				if (result == null) result = caseContainsOperations(visibleThing);
				if (result == null) result = caseNamedElement(visibleThing);
				if (result == null) result = caseContainsEventTriggers(visibleThing);
				if (result == null) result = caseWireEdgesSource(visibleThing);
				if (result == null) result = caseWireEdgeDestination(visibleThing);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.INTERNET_APPLICATION: {
				InternetApplication internetApplication = (InternetApplication)theEObject;
				T result = caseInternetApplication(internetApplication);
				if (result == null) result = caseContainsOperations(internetApplication);
				if (result == null) result = caseContainsEventTriggers(internetApplication);
				if (result == null) result = caseNamedElement(internetApplication);
				if (result == null) result = caseContainsWires(internetApplication);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOMAIN_STORE: {
				DomainStore domainStore = (DomainStore)theEObject;
				T result = caseDomainStore(domainStore);
				if (result == null) result = caseContainsOperations(domainStore);
				if (result == null) result = caseContainsEventTriggers(domainStore);
				if (result == null) result = caseNamedElement(domainStore);
				if (result == null) result = caseContainsWires(domainStore);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DATA_FLOW_EDGE: {
				DataFlowEdge dataFlowEdge = (DataFlowEdge)theEObject;
				T result = caseDataFlowEdge(dataFlowEdge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DATA_FLOW_EDGE_DESTINATION: {
				DataFlowEdgeDestination dataFlowEdgeDestination = (DataFlowEdgeDestination)theEObject;
				T result = caseDataFlowEdgeDestination(dataFlowEdgeDestination);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DATA_FLOW_EDGES_SOURCE: {
				DataFlowEdgesSource dataFlowEdgesSource = (DataFlowEdgesSource)theEObject;
				T result = caseDataFlowEdgesSource(dataFlowEdgesSource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TEMPORARY_VARIABLE: {
				TemporaryVariable temporaryVariable = (TemporaryVariable)theEObject;
				T result = caseTemporaryVariable(temporaryVariable);
				if (result == null) result = caseNamedElement(temporaryVariable);
				if (result == null) result = caseDataFlowEdgesSource(temporaryVariable);
				if (result == null) result = caseDataFlowEdgeDestination(temporaryVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.EXECUTION_EDGE: {
				ExecutionEdge executionEdge = (ExecutionEdge)theEObject;
				T result = caseExecutionEdge(executionEdge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.EXECUTION_EDGE_DESTINATION: {
				ExecutionEdgeDestination executionEdgeDestination = (ExecutionEdgeDestination)theEObject;
				T result = caseExecutionEdgeDestination(executionEdgeDestination);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.EXECUTION_EDGES_SOURCE: {
				ExecutionEdgesSource executionEdgesSource = (ExecutionEdgesSource)theEObject;
				T result = caseExecutionEdgesSource(executionEdgesSource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DYNAMIC_APPLICATION_ELEMENT_SET: {
				DynamicApplicationElementSet dynamicApplicationElementSet = (DynamicApplicationElementSet)theEObject;
				T result = caseDynamicApplicationElementSet(dynamicApplicationElementSet);
				if (result == null) result = caseContainsOperations(dynamicApplicationElementSet);
				if (result == null) result = caseNamedElement(dynamicApplicationElementSet);
				if (result == null) result = caseContainsEventTriggers(dynamicApplicationElementSet);
				if (result == null) result = caseWireEdgesSource(dynamicApplicationElementSet);
				if (result == null) result = caseWireEdgeDestination(dynamicApplicationElementSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.CONTAINS_WIRES: {
				ContainsWires containsWires = (ContainsWires)theEObject;
				T result = caseContainsWires(containsWires);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wire Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wire Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWireEdge(WireEdge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wire Edge Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wire Edge Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWireEdgeDestination(WireEdgeDestination object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wire Edges Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wire Edges Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWireEdgesSource(WireEdgesSource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEventTrigger(EventTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contains Event Triggers</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contains Event Triggers</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainsEventTriggers(ContainsEventTriggers object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Domain Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Domain Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDomainObject(DomainObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Domain Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Domain Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDomainAttribute(DomainAttribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivityNode(ActivityNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperation(Operation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameter(Parameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Single Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Single Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSingleOperation(SingleOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Chained Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Chained Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChainedOperation(ChainedOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composite Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composite Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompositeOperation(CompositeOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contains Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contains Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainsOperations(ContainsOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Application Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Application Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseApplicationElement(ApplicationElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Application Element Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Application Element Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseApplicationElementContainer(ApplicationElementContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Application Element Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Application Element Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseApplicationElementProperty(ApplicationElementProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Static Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Static Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStaticValue(StaticValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Visible Thing</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Visible Thing</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVisibleThing(VisibleThing object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Internet Application</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Internet Application</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInternetApplication(InternetApplication object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Domain Store</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Domain Store</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDomainStore(DomainStore object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Flow Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Flow Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataFlowEdge(DataFlowEdge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Flow Edge Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Flow Edge Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataFlowEdgeDestination(DataFlowEdgeDestination object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Flow Edges Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Flow Edges Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataFlowEdgesSource(DataFlowEdgesSource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Temporary Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Temporary Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemporaryVariable(TemporaryVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Execution Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Execution Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutionEdge(ExecutionEdge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Execution Edge Destination</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Execution Edge Destination</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutionEdgeDestination(ExecutionEdgeDestination object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Execution Edges Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Execution Edges Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutionEdgesSource(ExecutionEdgesSource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dynamic Application Element Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dynamic Application Element Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDynamicApplicationElementSet(DynamicApplicationElementSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contains Wires</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contains Wires</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainsWires(ContainsWires object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ModelSwitch
