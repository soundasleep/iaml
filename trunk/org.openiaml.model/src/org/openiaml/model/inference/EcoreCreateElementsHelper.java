/**
 * 
 */
package org.openiaml.model.inference;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.openiaml.model.model.Accessible;
import org.openiaml.model.model.Action;
import org.openiaml.model.model.ActionDestination;
import org.openiaml.model.model.ActionSource;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsProperties;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.DataFlowEdgeDestination;
import org.openiaml.model.model.DataFlowEdgesSource;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.Editable;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.ExecutionEdgeDestination;
import org.openiaml.model.model.ExecutionEdgesSource;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.PrimitiveCondition;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.Scope;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.WireDestination;
import org.openiaml.model.model.WireSource;
import org.openiaml.model.model.components.ComponentsPackage;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.operations.Arithmetic;
import org.openiaml.model.model.operations.ArithmeticOperationTypes;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.CastNode;
import org.openiaml.model.model.operations.DecisionCondition;
import org.openiaml.model.model.operations.DecisionNode;
import org.openiaml.model.model.operations.DecisionOperation;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.JoinNode;
import org.openiaml.model.model.operations.OperationCallNode;
import org.openiaml.model.model.operations.OperationsPackage;
import org.openiaml.model.model.operations.SplitNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.Email;
import org.openiaml.model.model.scopes.ScopesPackage;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.RequiresEdgeDestination;
import org.openiaml.model.model.users.RequiresEdgesSource;
import org.openiaml.model.model.users.Role;
import org.openiaml.model.model.users.UserInstance;
import org.openiaml.model.model.users.UserStore;
import org.openiaml.model.model.users.UsersPackage;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.model.visual.MapPoint;
import org.openiaml.model.model.visual.VisualPackage;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ConditionEdgeDestination;
import org.openiaml.model.model.wires.ConditionEdgesSource;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ExtendsEdgeDestination;
import org.openiaml.model.model.wires.ExtendsEdgesSource;
import org.openiaml.model.model.wires.NavigateAction;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ParameterEdgeDestination;
import org.openiaml.model.model.wires.ParameterEdgesSource;
import org.openiaml.model.model.wires.RequiresEdge;
import org.openiaml.model.model.wires.RunAction;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * Provides some helpful wrapper methods.
 * 
 * TODO it would be nice one day if this could be automatically generated?
 * 
 * @author jmwright
 *
 */
public abstract class EcoreCreateElementsHelper implements ICreateElements {

	public Property generatedProperty(GeneratesElements by, ContainsProperties container) throws InferenceException {
		return generatedProperty(by, container, ModelPackage.eINSTANCE.getContainsProperties_Properties() );
	}
	
	public Property generatedProperty(GeneratesElements by, ContainsProperties container, EReference reference) throws InferenceException {
		Property fieldValue = (Property) createElement( container, ModelPackage.eINSTANCE.getProperty(), reference );
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

	public DomainStore generatedDomainStore(GeneratesElements by, InternetApplication container) throws InferenceException {
		DomainStore ds = createDomainStore(container);
		setGeneratedBy(ds, by);
		return ds;
	}

	public DomainStore createDomainStore(InternetApplication container) throws InferenceException {
		DomainStore ds = (DomainStore) createElement( container, ModelPackage.eINSTANCE.getDomainStore(), ModelPackage.eINSTANCE.getInternetApplication_DomainStores() );
		return ds;
	}
		
	public EventTrigger generatedEventTriggerOnAccess(GeneratesElements by, Accessible container) throws InferenceException {
		EventTrigger event = (EventTrigger) createElement( container, ModelPackage.eINSTANCE.getEventTrigger(), ModelPackage.eINSTANCE.getAccessible_OnAccess() );
		setGeneratedBy(event, by);
		return event;
	}

	public EventTrigger generatedEventTriggerOnClick(GeneratesElements by, VisibleThing container) throws InferenceException {
		EventTrigger event = (EventTrigger) createElement( container, ModelPackage.eINSTANCE.getEventTrigger(), ModelPackage.eINSTANCE.getVisibleThing_OnClick() );
		setGeneratedBy(event, by);
		return event;
	}

	public EventTrigger generatedEventTriggerOnEdit(GeneratesElements by, Editable container) throws InferenceException {
		EventTrigger event = (EventTrigger) createElement( container, ModelPackage.eINSTANCE.getEventTrigger(), ModelPackage.eINSTANCE.getEditable_OnEdit() );
		setGeneratedBy(event, by);
		return event;
	}

	public EventTrigger generatedEventTriggerOnInit(GeneratesElements by, Scope container) throws InferenceException {
		EventTrigger event = (EventTrigger) createElement( container, ModelPackage.eINSTANCE.getEventTrigger(), ModelPackage.eINSTANCE.getScope_OnInit() );
		setGeneratedBy(event, by);
		return event;
	}

	public EventTrigger generatedEventTrigger(GeneratesElements by, EObject container, EReference reference) throws InferenceException {
		EventTrigger event = (EventTrigger) createElement( container, ModelPackage.eINSTANCE.getEventTrigger(), reference);
		setGeneratedBy(event, by);
		return event;
	}

	public CompositeOperation generatedCompositeOperation(GeneratesElements by, ContainsOperations container) throws InferenceException {
		CompositeOperation operation = createCompositeOperation(container);
		setGeneratedBy(operation, by);
		return operation;
	}
	
	public Parameter generatedParameter(GeneratesElements by, Operation container) throws InferenceException {
		Parameter parameter = (Parameter) createElement( container, ModelPackage.eINSTANCE.getParameter(), ModelPackage.eINSTANCE.getOperation_Parameters() );
		setGeneratedBy(parameter, by);
		return parameter;
	}	
	
	public Parameter generatedParameter(GeneratesElements by, CompositeCondition container) throws InferenceException {
		Parameter parameter = (Parameter) createElement( container, ModelPackage.eINSTANCE.getParameter(), ModelPackage.eINSTANCE.getCompositeCondition_Parameters() );
		setGeneratedBy(parameter, by);
		return parameter;
	}

	public PrimitiveOperation generatedPrimitiveOperation(GeneratesElements by, ContainsOperations container) throws InferenceException {
		return generatedPrimitiveOperation(by, container, ModelPackage.eINSTANCE.getContainsOperations_Operations() );
	}
	
	public PrimitiveOperation generatedPrimitiveOperation(GeneratesElements by, ContainsOperations container, EReference reference) throws InferenceException {
		PrimitiveOperation operation = (PrimitiveOperation) createElement( container, ModelPackage.eINSTANCE.getPrimitiveOperation(), reference );
		setGeneratedBy(operation, by);
		return operation;
	}

	public PrimitiveCondition generatedPrimitiveCondition(GeneratesElements by, ContainsConditions container) throws InferenceException {
		return generatedPrimitiveCondition(by, container, ModelPackage.eINSTANCE.getContainsConditions_Conditions() );
	}
	
	public PrimitiveCondition generatedPrimitiveCondition(GeneratesElements by, ContainsConditions container, EReference reference) throws InferenceException {
		PrimitiveCondition operation = (PrimitiveCondition) createElement( container, ModelPackage.eINSTANCE.getPrimitiveCondition(), reference );
		setGeneratedBy(operation, by);
		return operation;
	}

	public DecisionOperation generatedDecisionOperation(GeneratesElements by, ContainsOperations container) throws InferenceException {
		DecisionOperation operation = (DecisionOperation) createElement( container, OperationsPackage.eINSTANCE.getDecisionOperation(), ModelPackage.eINSTANCE.getContainsOperations_Operations() );
		setGeneratedBy(operation, by);
		return operation;
	}
	
	public DecisionNode generatedDecisionNode(GeneratesElements by, CompositeOperation container) throws InferenceException {
		DecisionNode operation = (DecisionNode) createElement( container, OperationsPackage.eINSTANCE.getDecisionNode(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes() );
		setGeneratedBy(operation, by);
		return operation;
	}

	public DecisionNode generatedDecisionNode(GeneratesElements by, CompositeCondition container) throws InferenceException {
		DecisionNode operation = (DecisionNode) createElement( container, OperationsPackage.eINSTANCE.getDecisionNode(), ModelPackage.eINSTANCE.getCompositeCondition_Nodes() );
		setGeneratedBy(operation, by);
		return operation;
	}

	public StaticValue generatedStaticValue(GeneratesElements by, CompositeOperation container) throws InferenceException {
		StaticValue value = (StaticValue) createElement( container, ModelPackage.eINSTANCE.getStaticValue(), ModelPackage.eINSTANCE.getCompositeOperation_Values() );
		setGeneratedBy(value, by);
		return value;
	}
	
	public StartNode generatedStartNode(GeneratesElements by, CompositeOperation container) throws InferenceException {
		StartNode node = (StartNode) createElement( container, OperationsPackage.eINSTANCE.getStartNode(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public CancelNode generatedCancelNode(GeneratesElements by, CompositeOperation container) throws InferenceException {
		CancelNode node = (CancelNode) createElement( container, OperationsPackage.eINSTANCE.getCancelNode(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public FinishNode generatedFinishNode(GeneratesElements by, CompositeOperation container) throws InferenceException {
		FinishNode node = (FinishNode) createElement( container, OperationsPackage.eINSTANCE.getFinishNode(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public SplitNode generatedSplitNode(GeneratesElements by, CompositeOperation container) throws InferenceException {
		SplitNode node = (SplitNode) createElement( container, OperationsPackage.eINSTANCE.getSplitNode(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public JoinNode generatedJoinNode(GeneratesElements by, CompositeOperation container) throws InferenceException {
		JoinNode node = (JoinNode) createElement( container, OperationsPackage.eINSTANCE.getJoinNode(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public CastNode generatedCastNode(GeneratesElements by, CompositeOperation container) throws InferenceException {
		CastNode node = (CastNode) createElement( container, OperationsPackage.eINSTANCE.getCastNode(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}
	
	public CastNode generatedCastNode(GeneratesElements by, CompositeCondition container) throws InferenceException {
		CastNode node = (CastNode) createElement( container, OperationsPackage.eINSTANCE.getCastNode(), ModelPackage.eINSTANCE.getCompositeCondition_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public OperationCallNode generatedOperationCallNode(GeneratesElements by, CompositeOperation container) throws InferenceException {
		OperationCallNode node = (OperationCallNode) createElement( container, OperationsPackage.eINSTANCE.getOperationCallNode(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public OperationCallNode generatedOperationCallNode(GeneratesElements by, CompositeCondition container) throws InferenceException {
		OperationCallNode node = (OperationCallNode) createElement( container, OperationsPackage.eINSTANCE.getOperationCallNode(), ModelPackage.eINSTANCE.getCompositeCondition_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}
	
	public StartNode generatedStartNode(GeneratesElements by, CompositeCondition container) throws InferenceException {
		StartNode node = (StartNode) createElement( container, OperationsPackage.eINSTANCE.getStartNode(), ModelPackage.eINSTANCE.getCompositeCondition_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public CancelNode generatedCancelNode(GeneratesElements by, CompositeCondition container) throws InferenceException {
		CancelNode node = (CancelNode) createElement( container, OperationsPackage.eINSTANCE.getCancelNode(), ModelPackage.eINSTANCE.getCompositeCondition_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public FinishNode generatedFinishNode(GeneratesElements by, CompositeCondition container) throws InferenceException {
		FinishNode node = (FinishNode) createElement( container, OperationsPackage.eINSTANCE.getFinishNode(), ModelPackage.eINSTANCE.getCompositeCondition_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public SplitNode generatedSplitNode(GeneratesElements by, CompositeCondition container) throws InferenceException {
		SplitNode node = (SplitNode) createElement( container, OperationsPackage.eINSTANCE.getSplitNode(), ModelPackage.eINSTANCE.getCompositeCondition_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public JoinNode generatedJoinNode(GeneratesElements by, CompositeCondition container) throws InferenceException {
		JoinNode node = (JoinNode) createElement( container, OperationsPackage.eINSTANCE.getJoinNode(), ModelPackage.eINSTANCE.getCompositeCondition_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}

	public Arithmetic generatedArithmetic(GeneratesElements by, CompositeOperation container) throws InferenceException {
		Arithmetic node = (Arithmetic) createElement( container, OperationsPackage.eINSTANCE.getArithmetic(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}
	
	public Arithmetic generatedArithmetic(GeneratesElements by, CompositeCondition container) throws InferenceException {
		Arithmetic node = (Arithmetic) createElement( container, OperationsPackage.eINSTANCE.getArithmetic(), ModelPackage.eINSTANCE.getCompositeCondition_Nodes() );
		setGeneratedBy(node, by);
		return node;
	}
	
	public DataFlowEdge generatedDataFlowEdge(GeneratesElements by, CompositeOperation container) throws InferenceException {
		DataFlowEdge edge = (DataFlowEdge) createElement( container, ModelPackage.eINSTANCE.getDataFlowEdge(), ModelPackage.eINSTANCE.getCompositeOperation_DataEdges() );
		setGeneratedBy(edge, by);
		return edge;
	}

	public DataFlowEdge generatedDataFlowEdge(GeneratesElements by, CompositeOperation container, DataFlowEdgesSource from, DataFlowEdgeDestination to) throws InferenceException {
		DataFlowEdge edge = generatedDataFlowEdge(by, container);
		setFrom(edge, from);
		setTo(edge, to);
		return edge;
	}
	
	public ExecutionEdge generatedExecutionEdge(GeneratesElements by, CompositeOperation container) throws InferenceException {
		ExecutionEdge edge = (ExecutionEdge) createElement( container, ModelPackage.eINSTANCE.getExecutionEdge(), ModelPackage.eINSTANCE.getCompositeOperation_ExecutionEdges() );
		setGeneratedBy(edge, by);
		return edge;
	}

	public ExecutionEdge generatedExecutionEdge(GeneratesElements by, CompositeOperation container, ExecutionEdgesSource from, ExecutionEdgeDestination to) throws InferenceException {
		ExecutionEdge edge = generatedExecutionEdge(by, container);
		setFrom(edge, from);
		setTo(edge, to);
		return edge;
	}
	
	public DataFlowEdge generatedDataFlowEdge(GeneratesElements by, CompositeCondition container) throws InferenceException {
		DataFlowEdge edge = (DataFlowEdge) createElement( container, ModelPackage.eINSTANCE.getDataFlowEdge(), ModelPackage.eINSTANCE.getCompositeCondition_DataEdges() );
		setGeneratedBy(edge, by);
		return edge;
	}

	public DataFlowEdge generatedDataFlowEdge(GeneratesElements by, CompositeCondition container, DataFlowEdgesSource from, DataFlowEdgeDestination to) throws InferenceException {
		DataFlowEdge edge = generatedDataFlowEdge(by, container);
		setFrom(edge, from);
		setTo(edge, to);
		return edge;
	}

	public ExecutionEdge generatedExecutionEdge(GeneratesElements by, CompositeCondition container) throws InferenceException {
		ExecutionEdge edge = (ExecutionEdge) createElement( container, ModelPackage.eINSTANCE.getExecutionEdge(), ModelPackage.eINSTANCE.getCompositeCondition_ExecutionEdges() );
		setGeneratedBy(edge, by);
		return edge;
	}

	public ExecutionEdge generatedExecutionEdge(GeneratesElements by, CompositeCondition container, ExecutionEdgesSource from, ExecutionEdgeDestination to) throws InferenceException {
		ExecutionEdge edge = generatedExecutionEdge(by, container);
		setFrom(edge, from);
		setTo(edge, to);
		return edge;
	}
	
	public RunAction generatedRunAction(GeneratesElements by, ContainsWires container, ActionSource source, ActionDestination target) throws InferenceException {
		RunAction wire = (RunAction) createRelationship(container, WiresPackage.eINSTANCE.getRunAction(), source, target, ModelPackage.eINSTANCE.getContainsWires_Actions(), ModelPackage.eINSTANCE.getAction_From(), ModelPackage.eINSTANCE.getAction_To());
		setGeneratedBy(wire, by);
		return wire;
	}

	public ParameterEdge generatedParameterEdge(GeneratesElements by, ContainsWires container, ParameterEdgesSource source, ParameterEdgeDestination target) throws InferenceException {
		ParameterEdge wire = (ParameterEdge) createRelationship(container, WiresPackage.eINSTANCE.getParameterEdge(), source, target, ModelPackage.eINSTANCE.getContainsWires_ParameterEdges(), WiresPackage.eINSTANCE.getParameterEdge_From(), WiresPackage.eINSTANCE.getParameterEdge_To());
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

	public SelectWire generatedSelectWire(GeneratesElements by, ContainsWires container, WireSource source, WireDestination target) throws InferenceException {
		SelectWire wire = (SelectWire) createRelationship(container, WiresPackage.eINSTANCE.getSelectWire(), source, target, ModelPackage.eINSTANCE.getContainsWires_Wires(), ModelPackage.eINSTANCE.getWire_From(), ModelPackage.eINSTANCE.getWire_To());
		setGeneratedBy(wire, by);
		return wire;
	}
	
	public NavigateAction generatedNavigateAction(GeneratesElements by, ContainsWires container, ActionSource source, ActionDestination target) throws InferenceException {
		NavigateAction wire = (NavigateAction) createRelationship(container, WiresPackage.eINSTANCE.getNavigateAction(), source, target, ModelPackage.eINSTANCE.getContainsWires_Actions(), ModelPackage.eINSTANCE.getAction_From(), ModelPackage.eINSTANCE.getAction_To());
		setGeneratedBy(wire, by);
		return wire;
	}

	public Frame generatedFrame(GeneratesElements by, InternetApplication container) throws InferenceException {
		Frame page = createFrame(container);
		setGeneratedBy(page, by);
		return page;
	}

	public Frame generatedFrame(GeneratesElements by, Session container) throws InferenceException {
		Frame page = createFrame(container);
		setGeneratedBy(page, by);
		return page;
	}

	public Session generatedSession(GeneratesElements by, InternetApplication container) throws InferenceException {
		Session session = (Session) createElement( container, ScopesPackage.eINSTANCE.getSession(), ModelPackage.eINSTANCE.getInternetApplication_Scopes() );
		setGeneratedBy(session, by);
		return session;
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
		Label field = (Label) createElement( container, VisualPackage.eINSTANCE.getLabel(), ScopesPackage.eINSTANCE.getEmail_Labels() );
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
	
	public MapPoint generatedMapPoint(GeneratesElements by, VisibleThing container) throws InferenceException {
		MapPoint field = (MapPoint) createElement( container, VisualPackage.eINSTANCE.getMapPoint(), ModelPackage.eINSTANCE.getVisibleThing_Children() );
		setGeneratedBy(field, by);
		return field;
	}
	
	public CompositeCondition generatedCompositeCondition(GeneratesElements by, ContainsConditions container) throws InferenceException {
		CompositeCondition object = (CompositeCondition) createElement( container, ModelPackage.eINSTANCE.getCompositeCondition(), ModelPackage.eINSTANCE.getContainsConditions_Conditions() );
		setGeneratedBy(object, by);
		return object;
	}

	public DecisionCondition generatedDecisionCondition(GeneratesElements by, ContainsConditions container) throws InferenceException {
		DecisionCondition object = (DecisionCondition) createElement( container, OperationsPackage.eINSTANCE.getDecisionCondition(), ModelPackage.eINSTANCE.getContainsConditions_Conditions() );
		setGeneratedBy(object, by);
		return object;
	}

	public ConditionEdge generatedConditionEdge(GeneratesElements by, ContainsWires container, ConditionEdgesSource source, ConditionEdgeDestination target) throws InferenceException {
		ConditionEdge wire = (ConditionEdge) createRelationship(container, WiresPackage.eINSTANCE.getConditionEdge(), source, target, ModelPackage.eINSTANCE.getContainsWires_ConditionEdges(), WiresPackage.eINSTANCE.getConditionEdge_From(), WiresPackage.eINSTANCE.getConditionEdge_To());
		setGeneratedBy(wire, by);
		return wire;
	}

	public Frame createFrame(InternetApplication container) throws InferenceException {
		Frame page = (Frame) createElement( container, VisualPackage.eINSTANCE.getFrame(), ModelPackage.eINSTANCE.getInternetApplication_Scopes() );
		return page;
	}

	public Frame createFrame(Scope container) throws InferenceException {
		Frame page = (Frame) createElement( container, VisualPackage.eINSTANCE.getFrame(), ModelPackage.eINSTANCE.getScope_Scopes() );
		return page;
	}
	
	public SyncWire createSyncWire(ContainsWires container, WireSource source,
			WireDestination target) throws InferenceException {
		SyncWire wire = (SyncWire) createRelationship(container, WiresPackage.eINSTANCE.getSyncWire(), source, target, ModelPackage.eINSTANCE.getContainsWires_Wires(), ModelPackage.eINSTANCE.getWire_From(), ModelPackage.eINSTANCE.getWire_To());
		return wire;
	}

	public CompositeOperation createCompositeOperation(ContainsOperations container) throws InferenceException {
		CompositeOperation operation = (CompositeOperation) createElement( container, ModelPackage.eINSTANCE.getCompositeOperation(), ModelPackage.eINSTANCE.getContainsOperations_Operations() );
		return operation;
	}

	public DomainObject generatedDomainObject(GeneratesElements by, DomainStore container) throws InferenceException {
		DomainObject object = (DomainObject) createElement( container, ModelPackage.eINSTANCE.getDomainObject(), ModelPackage.eINSTANCE.getDomainStore_Children() );
		setGeneratedBy(object, by);
		return object;
	}

	public DomainAttribute generatedDomainAttribute(GeneratesElements by, DomainObject container) throws InferenceException {
		DomainAttribute object = (DomainAttribute) createElement( container, ModelPackage.eINSTANCE.getDomainAttribute(), ModelPackage.eINSTANCE.getDomainObject_Attributes() );
		setGeneratedBy(object, by);
		return object;
	}

	public DomainAttributeInstance generatedDomainAttributeInstance(GeneratesElements by, DomainObjectInstance container) throws InferenceException {
		DomainAttributeInstance object = (DomainAttributeInstance) createElement( container, ModelPackage.eINSTANCE.getDomainAttributeInstance(), ModelPackage.eINSTANCE.getDomainObjectInstance_Attributes() );
		setGeneratedBy(object, by);
		return object;
	}

	public DomainObjectInstance generatedDomainObjectInstance(GeneratesElements by, Session container) throws InferenceException {
		DomainObjectInstance object = (DomainObjectInstance) createElement( container, ModelPackage.eINSTANCE.getDomainObjectInstance(), ModelPackage.eINSTANCE.getScope_Elements() );
		setGeneratedBy(object, by);
		return object;
	}
	
	public UserInstance generatedUserInstance(GeneratesElements by, Session container) throws InferenceException {
		UserInstance object = (UserInstance) createElement( container, UsersPackage.eINSTANCE.getUserInstance(), ModelPackage.eINSTANCE.getScope_Elements() );
		setGeneratedBy(object, by);
		return object;
	}
	
	public LoginHandler generatedLoginHandler(GeneratesElements by, Session container) throws InferenceException {
		LoginHandler object = (LoginHandler) createElement( container, ComponentsPackage.eINSTANCE.getLoginHandler(), ModelPackage.eINSTANCE.getScope_Elements() );
		setGeneratedBy(object, by);
		return object;
	}

	public Role generatedRole(GeneratesElements by, UserStore container) throws InferenceException {
		Role object = (Role) createElement( container, UsersPackage.eINSTANCE.getRole(), ModelPackage.eINSTANCE.getDomainStore_Children() );
		setGeneratedBy(object, by);
		return object;
	}

	// property setting helpers
	public void setName(NamedElement element, String value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getNamedElement_Name(), value);
	}

	public void setValue(StaticValue element, String value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getStaticValue_Value(), value);
	}
	
	public void setFrom(DataFlowEdge element, EObject value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getDataFlowEdge_From(), value);
	}

	public void setTo(DataFlowEdge element, EObject value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getDataFlowEdge_To(), value);
	}

	public void setFrom(ExecutionEdge element, EObject value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getExecutionEdge_From(), value);
	}

	public void setTo(ExecutionEdge element, EObject value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getExecutionEdge_To(), value);
	}
	
	public void setQuery(SelectWire element, String value) throws InferenceException {
		setValue(element, WiresPackage.eINSTANCE.getSelectWire_Query(), value);
	}
	
	public void setAutosave(DomainAttributeInstance element, boolean value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getDomainAttributeInstance_Autosave(), value);
	}
	
	public void setType(InputTextField element, XSDSimpleTypeDefinition value) throws InferenceException {
		setValue(element, VisualPackage.eINSTANCE.getInputTextField_Type(), value);
	}
	
	public void setType(Property element, XSDSimpleTypeDefinition value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getProperty_Type(), value);
	}

	public void setType(StaticValue element, XSDSimpleTypeDefinition value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getStaticValue_Type(), value);
	}

	public void setType(Arithmetic element, ArithmeticOperationTypes value) throws InferenceException {
		setValue(element, OperationsPackage.eINSTANCE.getArithmetic_OperationType(), value);
	}

	public void setPriority(Action element, int value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getAction_Priority(), value);
	}
	
	public void setExceptionText(CancelNode element, String value) throws InferenceException {
		setValue(element, OperationsPackage.eINSTANCE.getCancelNode_ExceptionText(), value);
	}
	
	public void setDefault(Property element, String value) throws InferenceException {
		setValue(element, ModelPackage.eINSTANCE.getProperty_DefaultValue(), value);
	}
	
}
