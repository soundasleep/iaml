/**
 * 
 */
package org.openiaml.model.inference;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.openiaml.model.FileReference;
import org.openiaml.model.model.Accessible;
import org.openiaml.model.model.Action;
import org.openiaml.model.model.ActionEdgeSource;
import org.openiaml.model.model.BuiltinOperation;
import org.openiaml.model.model.BuiltinProperty;
import org.openiaml.model.model.Changeable;
import org.openiaml.model.model.ContainsFunctions;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsProperties;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.ECARule;
import org.openiaml.model.model.Event;
import org.openiaml.model.model.Function;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.ParameterValue;
import org.openiaml.model.model.QueryParameter;
import org.openiaml.model.model.Scope;
import org.openiaml.model.model.SimpleCondition;
import org.openiaml.model.model.Value;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.WireDestination;
import org.openiaml.model.model.WireSource;
import org.openiaml.model.model.components.ComponentsPackage;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.domain.DomainAttribute;
import org.openiaml.model.model.domain.DomainAttributeInstance;
import org.openiaml.model.model.domain.DomainInstance;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainPackage;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.domain.DomainSource;
import org.openiaml.model.model.domain.DomainStoreTypes;
import org.openiaml.model.model.domain.SchemaEdge;
import org.openiaml.model.model.domain.SelectEdge;
import org.openiaml.model.model.messaging.Email;
import org.openiaml.model.model.messaging.MessagingPackage;
import org.openiaml.model.model.operations.ActivityFunction;
import org.openiaml.model.model.operations.ActivityOperation;
import org.openiaml.model.model.operations.ActivityParameter;
import org.openiaml.model.model.operations.Arithmetic;
import org.openiaml.model.model.operations.ArithmeticOperationTypes;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.CastNode;
import org.openiaml.model.model.operations.DataFlowEdge;
import org.openiaml.model.model.operations.DataFlowEdgeDestination;
import org.openiaml.model.model.operations.DataFlowEdgesSource;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.ExecutionEdge;
import org.openiaml.model.model.operations.ExecutionEdgeDestination;
import org.openiaml.model.model.operations.ExecutionEdgesSource;
import org.openiaml.model.model.operations.ExternalValue;
import org.openiaml.model.model.operations.ExternalValueEdge;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.JoinNode;
import org.openiaml.model.model.operations.OperationCallNode;
import org.openiaml.model.model.operations.OperationsPackage;
import org.openiaml.model.model.operations.SetNode;
import org.openiaml.model.model.operations.SplitNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.ScopesPackage;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.RequiresEdgeDestination;
import org.openiaml.model.model.users.RequiresEdgesSource;
import org.openiaml.model.model.users.Role;
import org.openiaml.model.model.users.UsersPackage;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.IteratorList;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.model.visual.MapPoint;
import org.openiaml.model.model.visual.VisualPackage;
import org.openiaml.model.model.wires.ConditionEdgeDestination;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ExtendsEdgeDestination;
import org.openiaml.model.model.wires.ExtendsEdgesSource;
import org.openiaml.model.model.wires.ParameterEdgeDestination;
import org.openiaml.model.model.wires.ParameterEdgesSource;
import org.openiaml.model.model.wires.RequiresEdge;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * Provides some helpful wrapper methods.
 * 
 * <p>TODO it would be nice one day if this could be automatically generated? This could be
 * generated from the existing .simplegmf definitions, or simply from the existing
 * .ecore model.
 * 
 * @author jmwright
 *
 */
public abstract class EcoreCreateElementsHelper implements ICreateElements {

	public Value generatedValue(GeneratesElements by, ContainsProperties container) throws InferenceException {
		return generatedValue(by, container, ModelPackage.eINSTANCE.getContainsProperties_Properties() );
	}
	
	public Value generatedValue(GeneratesElements by, ContainsProperties container, EReference reference) throws InferenceException {
		Value fieldValue = (Value) createElement( container, ModelPackage.eINSTANCE.getValue(), reference );
		setGeneratedBy(fieldValue, by);
		return fieldValue;
	}

	public Value generatedValueFieldValue(GeneratesElements by, Changeable container) throws InferenceException {
		Value fieldValue = (Value) createElement( container, ModelPackage.eINSTANCE.getValue(), ModelPackage.eINSTANCE.getChangeable_FieldValue() );
		setGeneratedBy(fieldValue, by);
		return fieldValue;
	}
	
	public Value generatedValueCurrentInput(GeneratesElements by, VisibleThing container) throws InferenceException {
		Value fieldValue = (Value) createElement( container, ModelPackage.eINSTANCE.getValue(), ModelPackage.eINSTANCE.getVisibleThing_CurrentInput() );
		setGeneratedBy(fieldValue, by);
		return fieldValue;
	}
	
	/**
	 * Convenience method that sets isGenerated and generatedBy to the
	 * appropriate values.
	 * 
	 * @param fieldValue
	 * @param by
	 */
	public void setGeneratedBy(GeneratedElement element,
			GeneratesElements by) throws InferenceException {
		addReference(element, ModelPackage.eINSTANCE.getGeneratedElement_GeneratedBy(), by);
		setValue(element, ModelPackage.eINSTANCE.getGeneratedElement_IsGenerated(), true);
	}
	
	/**
	 * Sets the "generatedRule" property of a GeneratedElement.
	 * 
	 * @param element
	 * @param ruleName
	 * @throws InferenceException
	 */
	public void setGeneratedRule(GeneratedElement element, String ruleName) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getGeneratedElement_GeneratedRule(), ruleName);
	}

	public Event generatedEventOnAccess(GeneratesElements by, Accessible container) throws InferenceException {
		Event event = (Event) createElement( container, ModelPackage.eINSTANCE.getEvent(), ModelPackage.eINSTANCE.getAccessible_OnAccess() );
		setGeneratedBy(event, by);
		return event;
	}

	public Event generatedEventOnClick(GeneratesElements by, VisibleThing container) throws InferenceException {
		Event event = (Event) createElement( container, ModelPackage.eINSTANCE.getEvent(), ModelPackage.eINSTANCE.getVisibleThing_OnClick() );
		setGeneratedBy(event, by);
		return event;
	}

	public Event generatedEventOnChange(GeneratesElements by, Changeable container) throws InferenceException {
		Event event = (Event) createElement( container, ModelPackage.eINSTANCE.getEvent(), ModelPackage.eINSTANCE.getChangeable_OnChange() );
		setGeneratedBy(event, by);
		return event;
	}

	public Event generatedEventOnInit(GeneratesElements by, Scope container) throws InferenceException {
		Event event = (Event) createElement( container, ModelPackage.eINSTANCE.getEvent(), ModelPackage.eINSTANCE.getScope_OnInit() );
		setGeneratedBy(event, by);
		return event;
	}

	public Event generatedEventOnInput(GeneratesElements by, VisibleThing container) throws InferenceException {
		Event event = (Event) createElement( container, ModelPackage.eINSTANCE.getEvent(), ModelPackage.eINSTANCE.getVisibleThing_OnInput() );
		setGeneratedBy(event, by);
		return event;
	}

	public Event generatedEvent(GeneratesElements by, EObject container, EReference reference) throws InferenceException {
		Event event = (Event) createElement( container, ModelPackage.eINSTANCE.getEvent(), reference);
		setGeneratedBy(event, by);
		return event;
	}

	public DomainSchema generatedDomainSchema(GeneratesElements by, InternetApplication container) throws InferenceException {
		DomainSchema obj = createDomainSchema(container);
		setGeneratedBy(obj, by);
		return obj;
	}

	// used by tests.diagram
	public DomainSchema createDomainSchema(InternetApplication container) throws InferenceException {
		DomainSchema obj = (DomainSchema) createElement( container, DomainPackage.eINSTANCE.getDomainSchema(), ModelPackage.eINSTANCE.getInternetApplication_Schemas() );
		return obj;
	}

	public Role generatedRole(GeneratesElements by, InternetApplication container) throws InferenceException {
		Role obj = (Role) createElement( container, UsersPackage.eINSTANCE.getRole(), ModelPackage.eINSTANCE.getInternetApplication_Schemas() );
		setGeneratedBy(obj, by);
		return obj;
	}

	public DomainSource generatedDomainSource(GeneratesElements by, InternetApplication container) throws InferenceException {
		DomainSource obj = (DomainSource) createElement( container, DomainPackage.eINSTANCE.getDomainSource(), ModelPackage.eINSTANCE.getInternetApplication_Sources() );
		setGeneratedBy(obj, by);
		return obj;
	}

	public DomainIterator generatedDomainIterator(GeneratesElements by, Scope container) throws InferenceException {
		DomainIterator obj = (DomainIterator) createElement( container, DomainPackage.eINSTANCE.getDomainIterator(), ModelPackage.eINSTANCE.getScope_Iterators() );
		setGeneratedBy(obj, by);
		return obj;
	}
	
	public DomainInstance generatedDomainInstance(GeneratesElements by, DomainIterator container) throws InferenceException {
		DomainInstance obj = (DomainInstance) createElement( container, DomainPackage.eINSTANCE.getDomainInstance(), DomainPackage.eINSTANCE.getDomainIterator_CurrentInstance() );
		setGeneratedBy(obj, by);
		return obj;
	}

	public DomainAttribute generatedDomainAttribute(GeneratesElements by, DomainSchema container) throws InferenceException {
		DomainAttribute obj = (DomainAttribute) createElement( container, DomainPackage.eINSTANCE.getDomainAttribute(), EcorePackage.eINSTANCE.getEClass_EStructuralFeatures() );
		setGeneratedBy(obj, by);
		return obj;
	}

	public DomainAttributeInstance generatedDomainAttributeInstance(GeneratesElements by, DomainInstance container) throws InferenceException {
		DomainAttributeInstance obj = (DomainAttributeInstance) createElement( container, DomainPackage.eINSTANCE.getDomainAttributeInstance(), DomainPackage.eINSTANCE.getDomainInstance_FeatureInstances() );
		setGeneratedBy(obj, by);
		return obj;
	}

	public ActivityOperation generatedActivityOperation(GeneratesElements by, ContainsOperations container) throws InferenceException {
		ActivityOperation operation = createActivityOperation(container);
		setGeneratedBy(operation, by);
		return operation;
	}
	
	public ActivityParameter generatedActivityParameter(GeneratesElements by, ActivityOperation container) throws InferenceException {
		ActivityParameter parameter = (ActivityParameter) createElement( container, OperationsPackage.eINSTANCE.getActivityParameter(), OperationsPackage.eINSTANCE.getActivityOperation_Parameters() );
		setGeneratedBy(parameter, by);
		return parameter;
	}	
	
	public ActivityParameter generatedActivityParameter(GeneratesElements by, ActivityFunction container) throws InferenceException {
		ActivityParameter parameter = (ActivityParameter) createElement( container, OperationsPackage.eINSTANCE.getActivityParameter(), OperationsPackage.eINSTANCE.getActivityFunction_Parameters() );
		setGeneratedBy(parameter, by);
		return parameter;
	}

	public BuiltinOperation generatedBuiltinOperation(GeneratesElements by, ContainsOperations container) throws InferenceException {
		return generatedBuiltinOperation(by, container, ModelPackage.eINSTANCE.getContainsOperations_Operations() );
	}
	
	public BuiltinOperation generatedBuiltinOperation(GeneratesElements by, ContainsOperations container, EReference reference) throws InferenceException {
		BuiltinOperation operation = (BuiltinOperation) createElement( container, ModelPackage.eINSTANCE.getBuiltinOperation(), reference );
		setGeneratedBy(operation, by);
		return operation;
	}

	public BuiltinProperty generatedBuiltinProperty(GeneratesElements by, ContainsFunctions container) throws InferenceException {
		return generatedBuiltinProperty(by, container, ModelPackage.eINSTANCE.getContainsFunctions_Functions() );
	}
	
	public BuiltinProperty generatedBuiltinProperty(GeneratesElements by, ContainsFunctions container, EReference reference) throws InferenceException {
		BuiltinProperty operation = (BuiltinProperty) createElement( container, ModelPackage.eINSTANCE.getBuiltinProperty(), reference );
		setGeneratedBy(operation, by);
		return operation;
	}

	public DecisionNode generatedDecisionNode(GeneratesElements by, ActivityOperation container) throws InferenceException {
		DecisionNode operation = (DecisionNode) createElement( container, OperationsPackage.eINSTANCE.getDecisionNode(), OperationsPackage.eINSTANCE.getActivityOperation_Nodes() );
		setGeneratedBy(operation, by);
		return operation;
	}

	public DecisionNode generatedDecisionNode(GeneratesElements by, ActivityFunction container) throws InferenceException {
		DecisionNode operation = (DecisionNode) createElement( container, OperationsPackage.eINSTANCE.getDecisionNode(), OperationsPackage.eINSTANCE.getActivityFunction_Nodes() );
		setGeneratedBy(operation, by);
		return operation;
	}

	public StartNode generatedStartNode(GeneratesElements by, ActivityOperation container) throws InferenceException {
		StartNode node = (StartNode) createElement( container, OperationsPackage.eINSTANCE.getStartNode(), OperationsPackage.eINSTANCE.getActivityOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public CancelNode generatedCancelNode(GeneratesElements by, ActivityOperation container) throws InferenceException {
		CancelNode node = (CancelNode) createElement( container, OperationsPackage.eINSTANCE.getCancelNode(), OperationsPackage.eINSTANCE.getActivityOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public FinishNode generatedFinishNode(GeneratesElements by, ActivityOperation container) throws InferenceException {
		FinishNode node = (FinishNode) createElement( container, OperationsPackage.eINSTANCE.getFinishNode(), OperationsPackage.eINSTANCE.getActivityOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public SplitNode generatedSplitNode(GeneratesElements by, ActivityOperation container) throws InferenceException {
		SplitNode node = (SplitNode) createElement( container, OperationsPackage.eINSTANCE.getSplitNode(), OperationsPackage.eINSTANCE.getActivityOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public JoinNode generatedJoinNode(GeneratesElements by, ActivityOperation container) throws InferenceException {
		JoinNode node = (JoinNode) createElement( container, OperationsPackage.eINSTANCE.getJoinNode(), OperationsPackage.eINSTANCE.getActivityOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public CastNode generatedCastNode(GeneratesElements by, ActivityOperation container) throws InferenceException {
		CastNode node = (CastNode) createElement( container, OperationsPackage.eINSTANCE.getCastNode(), OperationsPackage.eINSTANCE.getActivityOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}
	
	public CastNode generatedCastNode(GeneratesElements by, ActivityFunction container) throws InferenceException {
		CastNode node = (CastNode) createElement( container, OperationsPackage.eINSTANCE.getCastNode(), OperationsPackage.eINSTANCE.getActivityFunction_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public OperationCallNode generatedOperationCallNode(GeneratesElements by, ActivityOperation container) throws InferenceException {
		OperationCallNode node = (OperationCallNode) createElement( container, OperationsPackage.eINSTANCE.getOperationCallNode(), OperationsPackage.eINSTANCE.getActivityOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public OperationCallNode generatedOperationCallNode(GeneratesElements by, ActivityFunction container) throws InferenceException {
		OperationCallNode node = (OperationCallNode) createElement( container, OperationsPackage.eINSTANCE.getOperationCallNode(), OperationsPackage.eINSTANCE.getActivityFunction_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}
	
	public StartNode generatedStartNode(GeneratesElements by, ActivityFunction container) throws InferenceException {
		StartNode node = (StartNode) createElement( container, OperationsPackage.eINSTANCE.getStartNode(), OperationsPackage.eINSTANCE.getActivityFunction_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public CancelNode generatedCancelNode(GeneratesElements by, ActivityFunction container) throws InferenceException {
		CancelNode node = (CancelNode) createElement( container, OperationsPackage.eINSTANCE.getCancelNode(), OperationsPackage.eINSTANCE.getActivityFunction_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public FinishNode generatedFinishNode(GeneratesElements by, ActivityFunction container) throws InferenceException {
		FinishNode node = (FinishNode) createElement( container, OperationsPackage.eINSTANCE.getFinishNode(), OperationsPackage.eINSTANCE.getActivityFunction_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public SplitNode generatedSplitNode(GeneratesElements by, ActivityFunction container) throws InferenceException {
		SplitNode node = (SplitNode) createElement( container, OperationsPackage.eINSTANCE.getSplitNode(), OperationsPackage.eINSTANCE.getActivityFunction_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public JoinNode generatedJoinNode(GeneratesElements by, ActivityFunction container) throws InferenceException {
		JoinNode node = (JoinNode) createElement( container, OperationsPackage.eINSTANCE.getJoinNode(), OperationsPackage.eINSTANCE.getActivityFunction_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public Arithmetic generatedArithmetic(GeneratesElements by, ActivityOperation container) throws InferenceException {
		Arithmetic node = (Arithmetic) createElement( container, OperationsPackage.eINSTANCE.getArithmetic(), OperationsPackage.eINSTANCE.getActivityOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}
	
	public Arithmetic generatedArithmetic(GeneratesElements by, ActivityFunction container) throws InferenceException {
		Arithmetic node = (Arithmetic) createElement( container, OperationsPackage.eINSTANCE.getArithmetic(), OperationsPackage.eINSTANCE.getActivityFunction_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}
	
	public SetNode generatedSetNode(GeneratesElements by, ActivityOperation container) throws InferenceException {
		SetNode node = (SetNode) createElement( container, OperationsPackage.eINSTANCE.getSetNode(), OperationsPackage.eINSTANCE.getActivityOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public ExternalValue generatedExternalValue(GeneratesElements by, ActivityOperation container) throws InferenceException {
		ExternalValue node = (ExternalValue) createElement( container, OperationsPackage.eINSTANCE.getExternalValue(), OperationsPackage.eINSTANCE.getActivityOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}
	
	public ExternalValue generatedExternalValue(GeneratesElements by, ActivityFunction container) throws InferenceException {
		ExternalValue node = (ExternalValue) createElement( container, OperationsPackage.eINSTANCE.getExternalValue(), OperationsPackage.eINSTANCE.getActivityFunction_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}
	
	public ExternalValueEdge generatedExternalValueEdge(GeneratesElements by, ExternalValue container, ExternalValue from, Value to) throws InferenceException {
		ExternalValueEdge edge = (ExternalValueEdge) createElement( container, OperationsPackage.eINSTANCE.getExternalValueEdge(), OperationsPackage.eINSTANCE.getExternalValue_ExternalValueEdges() );
		setGeneratedBy(edge, by);
		setFrom(edge, from);
		setTo(edge, to);
		return edge;
	}

	public DataFlowEdge generatedDataFlowEdge(GeneratesElements by, ActivityOperation container) throws InferenceException {
		DataFlowEdge edge = (DataFlowEdge) createElement( container, OperationsPackage.eINSTANCE.getDataFlowEdge(), OperationsPackage.eINSTANCE.getActivityOperation_DataEdges() );
		setGeneratedBy(edge, by);
		return edge;
	}

	public DataFlowEdge generatedDataFlowEdge(GeneratesElements by, ActivityOperation container, DataFlowEdgesSource from, DataFlowEdgeDestination to) throws InferenceException {
		DataFlowEdge edge = generatedDataFlowEdge(by, container);
		setFrom(edge, from);
		setTo(edge, to);
		return edge;
	}
	
	public ExecutionEdge generatedExecutionEdge(GeneratesElements by, ActivityOperation container) throws InferenceException {
		ExecutionEdge edge = (ExecutionEdge) createElement( container, OperationsPackage.eINSTANCE.getExecutionEdge(), OperationsPackage.eINSTANCE.getActivityOperation_ExecutionEdges() );
		setGeneratedBy(edge, by);
		return edge;
	}

	public ExecutionEdge generatedExecutionEdge(GeneratesElements by, ActivityOperation container, ExecutionEdgesSource from, ExecutionEdgeDestination to) throws InferenceException {
		ExecutionEdge edge = generatedExecutionEdge(by, container);
		setFrom(edge, from);
		setTo(edge, to);
		return edge;
	}
	
	public DataFlowEdge generatedDataFlowEdge(GeneratesElements by, ActivityFunction container) throws InferenceException {
		DataFlowEdge edge = (DataFlowEdge) createElement( container, OperationsPackage.eINSTANCE.getDataFlowEdge(), OperationsPackage.eINSTANCE.getActivityFunction_DataEdges() );
		setGeneratedBy(edge, by);
		return edge;
	}

	public DataFlowEdge generatedDataFlowEdge(GeneratesElements by, ActivityFunction container, DataFlowEdgesSource from, DataFlowEdgeDestination to) throws InferenceException {
		DataFlowEdge edge = generatedDataFlowEdge(by, container);
		setFrom(edge, from);
		setTo(edge, to);
		return edge;
	}

	public ExecutionEdge generatedExecutionEdge(GeneratesElements by, ActivityFunction container) throws InferenceException {
		ExecutionEdge edge = (ExecutionEdge) createElement( container, OperationsPackage.eINSTANCE.getExecutionEdge(), OperationsPackage.eINSTANCE.getActivityFunction_ExecutionEdges() );
		setGeneratedBy(edge, by);
		return edge;
	}

	public ExecutionEdge generatedExecutionEdge(GeneratesElements by, ActivityFunction container, ExecutionEdgesSource from, ExecutionEdgeDestination to) throws InferenceException {
		ExecutionEdge edge = generatedExecutionEdge(by, container);
		setFrom(edge, from);
		setTo(edge, to);
		return edge;
	}

	public SchemaEdge generatedSchemaEdge(GeneratesElements by, ContainsWires container, DomainSource from, DomainSchema to) throws InferenceException {
		SchemaEdge edge = (SchemaEdge) createElement( container, DomainPackage.eINSTANCE.getSchemaEdge(), ModelPackage.eINSTANCE.getContainsWires_SchemaEdges() );
		setGeneratedBy(edge, by);
		setValue(edge, DomainPackage.eINSTANCE.getSchemaEdge_From(), from);
		setValue(edge, DomainPackage.eINSTANCE.getSchemaEdge_To(), to);
		return edge;
	}

	public SelectEdge generatedSelectEdge(GeneratesElements by, ContainsWires container, DomainIterator from, DomainSource to) throws InferenceException {
		SelectEdge edge = (SelectEdge) createElement( container, DomainPackage.eINSTANCE.getSelectEdge(), ModelPackage.eINSTANCE.getContainsWires_SelectEdges() );
		setGeneratedBy(edge, by);
		setValue(edge, DomainPackage.eINSTANCE.getSelectEdge_From(), from);
		setValue(edge, DomainPackage.eINSTANCE.getSelectEdge_To(), to);
		return edge;
	}
	
	public ECARule generatedECARule(GeneratesElements by, ContainsWires container, ActionEdgeSource source, Action target) throws InferenceException {
		ECARule wire = (ECARule) createRelationship(container, ModelPackage.eINSTANCE.getECARule(), source, target, ModelPackage.eINSTANCE.getContainsWires_EcaRules(), ModelPackage.eINSTANCE.getECARule_Trigger(), ModelPackage.eINSTANCE.getECARule_Target());
		setGeneratedBy(wire, by);
		return wire;
	}

	public Parameter generatedParameter(GeneratesElements by, ContainsWires container, ParameterEdgesSource source, ParameterEdgeDestination target) throws InferenceException {
		Parameter wire = (Parameter) createRelationship(container, ModelPackage.eINSTANCE.getParameter(), source, target, ModelPackage.eINSTANCE.getContainsWires_ParameterEdges(), ModelPackage.eINSTANCE.getParameter_ParameterValue(), ModelPackage.eINSTANCE.getParameter_ParameterTerm() );
		setGeneratedBy(wire, by);
		return wire;
	}

	public RequiresEdge generatedRequiresEdge(GeneratesElements by, ContainsWires container, RequiresEdgesSource source, RequiresEdgeDestination target) throws InferenceException {
		RequiresEdge wire = (RequiresEdge) createRelationship(container, WiresPackage.eINSTANCE.getRequiresEdge(), source, target, ModelPackage.eINSTANCE.getContainsWires_RequiresEdges(), WiresPackage.eINSTANCE.getRequiresEdge_From(), WiresPackage.eINSTANCE.getRequiresEdge_To() );
		setGeneratedBy(wire, by);
		return wire;
	}

	public ExtendsEdge generatedExtendsEdge(GeneratesElements by, ContainsWires container, ExtendsEdgesSource source, ExtendsEdgeDestination target) throws InferenceException {
		ExtendsEdge wire = (ExtendsEdge) createRelationship(container, WiresPackage.eINSTANCE.getExtendsEdge(), source, target, ModelPackage.eINSTANCE.getContainsWires_ExtendsEdges(), WiresPackage.eINSTANCE.getExtendsEdge_From(), WiresPackage.eINSTANCE.getExtendsEdge_To());
		setGeneratedBy(wire, by);
		return wire;
	}
	
	public SyncWire generatedSyncWire(GeneratesElements by, ContainsWires container, WireSource source, WireDestination target) throws InferenceException {
		SyncWire wire = createSyncWire(container, source, target);
		setGeneratedBy(wire, by);
		return wire;
	}

	public SetWire generatedSetWire(GeneratesElements by, ContainsWires container, WireSource source, WireDestination target) throws InferenceException {
		SetWire wire = (SetWire) createRelationship(container, WiresPackage.eINSTANCE.getSetWire(), source, target, ModelPackage.eINSTANCE.getContainsWires_Wires(), ModelPackage.eINSTANCE.getWire_From(), ModelPackage.eINSTANCE.getWire_To());
		setGeneratedBy(wire, by);
		return wire;
	}

	public Frame generatedFrame(GeneratesElements by, InternetApplication container) throws InferenceException {
		Frame page = createFrame(container);
		setGeneratedBy(page, by);
		return page;
	}

	public Frame generatedFrame(GeneratesElements by, Scope container) throws InferenceException {
		Frame page = createFrame(container);
		setGeneratedBy(page, by);
		return page;
	}

	public Session generatedSession(GeneratesElements by, Scope container) throws InferenceException {
		Session session = (Session) createElement( container, ScopesPackage.eINSTANCE.getSession(), ModelPackage.eINSTANCE.getScope_Scopes() );
		setGeneratedBy(session, by);
		return session;
	}

	public InputForm generatedInputForm(GeneratesElements by, VisibleThing container) throws InferenceException {
		InputForm form = (InputForm) createElement( container, VisualPackage.eINSTANCE.getInputForm(), ModelPackage.eINSTANCE.getVisibleThing_Children() );
		setGeneratedBy(form, by);
		return form;
	}

	public InputForm generatedInputForm(GeneratesElements by, Frame container) throws InferenceException {
		InputForm form = (InputForm) createElement( container, VisualPackage.eINSTANCE.getInputForm(), VisualPackage.eINSTANCE.getFrame_Children() );
		setGeneratedBy(form, by);
		return form;
	}

	public InputTextField generatedInputTextField(GeneratesElements by, VisibleThing container) throws InferenceException {
		InputTextField field = (InputTextField) createElement( container, VisualPackage.eINSTANCE.getInputTextField(), ModelPackage.eINSTANCE.getVisibleThing_Children() );
		setGeneratedBy(field, by);
		return field;
	}

	public InputTextField generatedInputTextField(GeneratesElements by, Frame container) throws InferenceException {
		InputTextField field = (InputTextField) createElement( container, VisualPackage.eINSTANCE.getInputTextField(), VisualPackage.eINSTANCE.getFrame_Children() );
		setGeneratedBy(field, by);
		return field;
	}
	
	public Label generatedLabel(GeneratesElements by, VisibleThing container) throws InferenceException {
		Label field = (Label) createElement( container, VisualPackage.eINSTANCE.getLabel(), ModelPackage.eINSTANCE.getVisibleThing_Children() );
		setGeneratedBy(field, by);
		return field;
	}

	public Label generatedLabel(GeneratesElements by, Frame container) throws InferenceException {
		Label field = (Label) createElement( container, VisualPackage.eINSTANCE.getLabel(), VisualPackage.eINSTANCE.getFrame_Children() );
		setGeneratedBy(field, by);
		return field;
	}

	public Label generatedLabel(GeneratesElements by, Email container) throws InferenceException {
		Label field = (Label) createElement( container, VisualPackage.eINSTANCE.getLabel(), MessagingPackage.eINSTANCE.getEmail_Labels() );
		setGeneratedBy(field, by);
		return field;
	}

	public Button generatedButton(GeneratesElements by, VisibleThing container) throws InferenceException {
		Button button = (Button) createElement( container, VisualPackage.eINSTANCE.getButton(), ModelPackage.eINSTANCE.getVisibleThing_Children() );
		setGeneratedBy(button, by);
		return button;
	}
	
	public Button generatedButton(GeneratesElements by, Frame container) throws InferenceException {
		Button button = (Button) createElement( container, VisualPackage.eINSTANCE.getButton(), VisualPackage.eINSTANCE.getFrame_Children() );
		setGeneratedBy(button, by);
		return button;
	}
	
	public IteratorList generatedIteratorList(GeneratesElements by, Frame container) throws InferenceException {
		IteratorList list = (IteratorList) createElement( container, VisualPackage.eINSTANCE.getIteratorList(), VisualPackage.eINSTANCE.getFrame_Children() );
		setGeneratedBy(list, by);
		return list;
	}
	
	public IteratorList generatedIteratorList(GeneratesElements by, VisibleThing container) throws InferenceException {
		IteratorList list = (IteratorList) createElement( container, VisualPackage.eINSTANCE.getIteratorList(), ModelPackage.eINSTANCE.getVisibleThing_Children() );
		setGeneratedBy(list, by);
		return list;
	}
	
	public MapPoint generatedMapPoint(GeneratesElements by, VisibleThing container) throws InferenceException {
		MapPoint field = (MapPoint) createElement( container, VisualPackage.eINSTANCE.getMapPoint(), ModelPackage.eINSTANCE.getVisibleThing_Children() );
		setGeneratedBy(field, by);
		return field;
	}
	
	public ActivityFunction generatedActivityFunction(GeneratesElements by, ContainsFunctions container) throws InferenceException {
		ActivityFunction object = (ActivityFunction) createElement( container, OperationsPackage.eINSTANCE.getActivityFunction(), ModelPackage.eINSTANCE.getContainsFunctions_Functions() );
		setGeneratedBy(object, by);
		return object;
	}

	public SimpleCondition generatedSimpleCondition(GeneratesElements by, ContainsWires container, Function source, ConditionEdgeDestination target) throws InferenceException {
		SimpleCondition wire = (SimpleCondition) createRelationship(container, ModelPackage.eINSTANCE.getSimpleCondition(), source, target, ModelPackage.eINSTANCE.getContainsWires_ConditionEdges(), ModelPackage.eINSTANCE.getComplexTerm_Function(), ModelPackage.eINSTANCE.getComplexTerm_Conditioned());
		setGeneratedBy(wire, by);
		return wire;
	}

	public Frame createFrame(Scope container) throws InferenceException {
		Frame page = (Frame) createElement( container, VisualPackage.eINSTANCE.getFrame(), ModelPackage.eINSTANCE.getScope_Scopes() );
		return page;
	}
	
	public Email createEmail(Scope container) throws InferenceException {
		Email page = (Email) createElement( container, MessagingPackage.eINSTANCE.getEmail(), ModelPackage.eINSTANCE.getScope_Messages() );
		return page;
	}
	
	public SyncWire createSyncWire(ContainsWires container, WireSource source,
			WireDestination target) throws InferenceException {
		SyncWire wire = (SyncWire) createRelationship(container, WiresPackage.eINSTANCE.getSyncWire(), source, target, ModelPackage.eINSTANCE.getContainsWires_Wires(), ModelPackage.eINSTANCE.getWire_From(), ModelPackage.eINSTANCE.getWire_To());
		return wire;
	}

	public ActivityOperation createActivityOperation(ContainsOperations container) throws InferenceException {
		ActivityOperation operation = (ActivityOperation) createElement( container, OperationsPackage.eINSTANCE.getActivityOperation(), ModelPackage.eINSTANCE.getContainsOperations_Operations() );
		return operation;
	}

	public LoginHandler generatedLoginHandler(GeneratesElements by, Session container) throws InferenceException {
		LoginHandler object = (LoginHandler) createElement( container, ComponentsPackage.eINSTANCE.getLoginHandler(), ModelPackage.eINSTANCE.getScope_LoginHandlers() );
		setGeneratedBy(object, by);
		return object;
	}

	public QueryParameter generatedQueryParameter(GeneratesElements by, Frame container) throws InferenceException {
		QueryParameter object = (QueryParameter) createElement( container, ModelPackage.eINSTANCE.getQueryParameter(), ModelPackage.eINSTANCE.getScope_Parameters() );
		setGeneratedBy(object, by);
		return object;
	}

	// property setting helpers
	public void setName(NamedElement element, String value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getNamedElement_Name(), value);
	}

	public void setName(ENamedElement element, String value) throws InferenceException {
		setValue(element, EcorePackage.eINSTANCE.getENamedElement_Name(), value);
	}

	public void setFrom(DataFlowEdge element, DataFlowEdgesSource value) throws InferenceException {
		setValue(element, OperationsPackage.eINSTANCE.getDataFlowEdge_From(), value);
	}
	
	public void setFrom(ExternalValueEdge element, ExternalValue value) throws InferenceException {
		setValue(element, OperationsPackage.eINSTANCE.getExternalValueEdge_From(), value);
	}

	public void setTo(DataFlowEdge element, DataFlowEdgeDestination value) throws InferenceException {
		setValue(element, OperationsPackage.eINSTANCE.getDataFlowEdge_To(), value);
	}
	
	public void setTo(ExternalValueEdge element, Value value) throws InferenceException {
		setValue(element, OperationsPackage.eINSTANCE.getExternalValueEdge_Value(), value);
	}

	public void setFrom(ExecutionEdge element, ExecutionEdgesSource value) throws InferenceException {
		setValue(element, OperationsPackage.eINSTANCE.getExecutionEdge_From(), value);
	}

	public void setTo(ExecutionEdge element, ExecutionEdgeDestination value) throws InferenceException {
		setValue(element, OperationsPackage.eINSTANCE.getExecutionEdge_To(), value);
	}
	
	public void setType(ParameterValue element, EDataType value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getParameterValue_Type(), value);
	}

	public void setType(Arithmetic element, ArithmeticOperationTypes value) throws InferenceException {
		setValue(element, OperationsPackage.eINSTANCE.getArithmetic_OperationType(), value);
	}
	
	public void setType(Changeable element, EDataType value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getChangeable_Type(), value);
	}

	public void setEType(ETypedElement element, EClassifier value) throws InferenceException {
		setValue(element, EcorePackage.eINSTANCE.getETypedElement_EType(), value);
	}

	public void setQuery(DomainIterator element, String value) throws InferenceException {
		setValue(element, DomainPackage.eINSTANCE.getDomainIterator_Query(), value);
	}

	public void setPriority(ECARule element, int value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getECARule_Priority(), value);
	}
	
	public void setRenderOrder(VisibleThing element, int value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getVisibleThing_RenderOrder(), value);
	}

	public void setLimit(DomainIterator element, int value) throws InferenceException {
		setValue(element, DomainPackage.eINSTANCE.getDomainIterator_Limit(), value);
	}
	
	public void setExceptionText(CancelNode element, String value) throws InferenceException {
		setValue(element, OperationsPackage.eINSTANCE.getCancelNode_ExceptionText(), value);
	}
	
	public void setDefaultValue(Value element, String value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getValue_DefaultValue(), value);
	}
	
	public void setFile(DomainSource element, FileReference value) throws InferenceException {
		setValue(element, DomainPackage.eINSTANCE.getDomainSource_File(), value);
	}
	
	public void setType(DomainSource element, DomainStoreTypes value) throws InferenceException {
		setValue(element, DomainPackage.eINSTANCE.getDomainSource_Type(), value);
	}
	
	public void setUrl(DomainSource element, String value) throws InferenceException {
		setValue(element, DomainPackage.eINSTANCE.getDomainSource_Url(), value);
	}
	
	public void setCache(DomainSource element, int value) throws InferenceException {
		setValue(element, DomainPackage.eINSTANCE.getDomainSource_Cache(), value);
	}
	
	public void setExecuteOnInput(SyncWire element, boolean value) throws InferenceException {
		setValue(element, WiresPackage.eINSTANCE.getSyncWire_ExecuteOnInput(), value);
	}
	
	public void setExecuteOnInput(SetWire element, boolean value) throws InferenceException {
		setValue(element, WiresPackage.eINSTANCE.getSetWire_ExecuteOnInput(), value);
	}
	
	public void setVisible(VisibleThing element, boolean value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getVisibleThing_Visible(), value);
	}
	
	public void setReadOnly(Value element, boolean value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getValue_ReadOnly(), value);
	}

}
