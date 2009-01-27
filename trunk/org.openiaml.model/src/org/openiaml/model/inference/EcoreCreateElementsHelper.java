/**
 * 
 */
package org.openiaml.model.inference;

import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ChainedOperation;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionOperation;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.OperationsPackage;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.visual.VisualPackage;
import org.openiaml.model.model.wires.FindWire;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
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

	public ApplicationElementProperty generatedApplicationElementProperty(GeneratesElements by, ApplicationElement container) throws InferenceException {
		ApplicationElementProperty fieldValue = (ApplicationElementProperty) createElement( container, ModelPackage.eINSTANCE.getApplicationElementProperty(), ModelPackage.eINSTANCE.getApplicationElement_Properties() );
		fieldValue.setIsGenerated(true);
		fieldValue.setGeneratedBy(by);
		return fieldValue;
	}

	public EventTrigger generatedEventTrigger(GeneratesElements by, ContainsEventTriggers container) throws InferenceException {
		EventTrigger event = (EventTrigger) createElement( container, ModelPackage.eINSTANCE.getEventTrigger(), ModelPackage.eINSTANCE.getContainsEventTriggers_EventTriggers() );
		event.setIsGenerated(true);
		event.setGeneratedBy(by);
		return event;
	}
	
	public CompositeOperation generatedCompositeOperation(GeneratesElements by, ContainsOperations container) throws InferenceException {
		CompositeOperation operation = (CompositeOperation) createElement( container, ModelPackage.eINSTANCE.getCompositeOperation(), ModelPackage.eINSTANCE.getContainsOperations_Operations() );
		operation.setIsGenerated(true);
		operation.setGeneratedBy(by);
		return operation;
	}
	
	public Parameter generatedParameter(GeneratesElements by, Operation container) throws InferenceException {
		Parameter parameter = (Parameter) createElement( container, ModelPackage.eINSTANCE.getParameter(), ModelPackage.eINSTANCE.getOperation_Parameters() );
		parameter.setIsGenerated(true);
		parameter.setGeneratedBy(by);
		return parameter;
	}

	public ChainedOperation generatedChainedOperation(GeneratesElements by, ContainsOperations container) throws InferenceException {
		ChainedOperation operation = (ChainedOperation) createElement( container, ModelPackage.eINSTANCE.getChainedOperation(), ModelPackage.eINSTANCE.getContainsOperations_Operations() );
		operation.setIsGenerated(true);
		operation.setGeneratedBy(by);
		return operation;
	}

	public DecisionOperation generatedDecisionOperation(GeneratesElements by, ContainsOperations container) throws InferenceException {
		DecisionOperation operation = (DecisionOperation) createElement( container, OperationsPackage.eINSTANCE.getDecisionOperation(), ModelPackage.eINSTANCE.getContainsOperations_Operations() );
		operation.setIsGenerated(true);
		operation.setGeneratedBy(by);
		return operation;
	}
	
	public StaticValue generatedStaticValue(GeneratesElements by, CompositeOperation container) throws InferenceException {
		StaticValue value = (StaticValue) createElement( container, ModelPackage.eINSTANCE.getStaticValue(), ModelPackage.eINSTANCE.getCompositeOperation_Values() );
		value.setIsGenerated(true);
		value.setGeneratedBy(by);
		return value;
	}
	
	public StartNode generatedStartNode(GeneratesElements by, CompositeOperation container) throws InferenceException {
		StartNode node = (StartNode) createElement( container, OperationsPackage.eINSTANCE.getStartNode(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes() );
		node.setIsGenerated(true);
		node.setGeneratedBy(by);
		return node;
	}

	public CancelNode generatedCancelNode(GeneratesElements by, CompositeOperation container) throws InferenceException {
		CancelNode node = (CancelNode) createElement( container, OperationsPackage.eINSTANCE.getCancelNode(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes() );
		node.setIsGenerated(true);
		node.setGeneratedBy(by);
		return node;
	}

	public FinishNode generatedFinishNode(GeneratesElements by, CompositeOperation container) throws InferenceException {
		FinishNode node = (FinishNode) createElement( container, OperationsPackage.eINSTANCE.getFinishNode(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes() );
		node.setIsGenerated(true);
		node.setGeneratedBy(by);
		return node;
	}

	public DataFlowEdge generatedDataFlowEdge(GeneratesElements by, CompositeOperation container) throws InferenceException {
		DataFlowEdge edge = (DataFlowEdge) createElement( container, ModelPackage.eINSTANCE.getDataFlowEdge(), ModelPackage.eINSTANCE.getCompositeOperation_DataEdges() );
		edge.setIsGenerated(true);
		edge.setGeneratedBy(by);
		return edge;
	}

	public ExecutionEdge generatedExecutionEdge(GeneratesElements by, CompositeOperation container) throws InferenceException {
		ExecutionEdge edge = (ExecutionEdge) createElement( container, ModelPackage.eINSTANCE.getExecutionEdge(), ModelPackage.eINSTANCE.getCompositeOperation_ExecutionEdges() );
		edge.setIsGenerated(true);
		edge.setGeneratedBy(by);
		return edge;
	}
	
	public RunInstanceWire generatedRunInstanceWire(GeneratesElements by, ContainsWires container, WireEdgesSource source, WireEdgeDestination target) throws InferenceException {
		RunInstanceWire wire = (RunInstanceWire) createRelationship(container, WiresPackage.eINSTANCE.getRunInstanceWire(), source, target, ModelPackage.eINSTANCE.getContainsWires_Wires(), ModelPackage.eINSTANCE.getWireEdge_From(), ModelPackage.eINSTANCE.getWireEdge_To());
		wire.setIsGenerated(true);
		wire.setGeneratedBy(by);
		return wire;
	}

	public ParameterWire generatedParameterWire(GeneratesElements by, ContainsWires container, WireEdgesSource source, WireEdgeDestination target) throws InferenceException {
		ParameterWire wire = (ParameterWire) createRelationship(container, WiresPackage.eINSTANCE.getParameterWire(), source, target, ModelPackage.eINSTANCE.getContainsWires_Wires(), ModelPackage.eINSTANCE.getWireEdge_From(), ModelPackage.eINSTANCE.getWireEdge_To());
		wire.setIsGenerated(true);
		wire.setGeneratedBy(by);
		return wire;
	}
	
	public SyncWire generatedSyncWire(GeneratesElements by, ContainsWires container, WireEdgesSource source, WireEdgeDestination target) throws InferenceException {
		SyncWire wire = (SyncWire) createRelationship(container, WiresPackage.eINSTANCE.getSyncWire(), source, target, ModelPackage.eINSTANCE.getContainsWires_Wires(), ModelPackage.eINSTANCE.getWireEdge_From(), ModelPackage.eINSTANCE.getWireEdge_To());
		wire.setIsGenerated(true);
		wire.setGeneratedBy(by);
		return wire;
	}

	public FindWire generatedFindWire(GeneratesElements by, ContainsWires container, WireEdgesSource source, WireEdgeDestination target) throws InferenceException {
		FindWire wire = (FindWire) createRelationship(container, WiresPackage.eINSTANCE.getFindWire(), source, target, ModelPackage.eINSTANCE.getContainsWires_Wires(), ModelPackage.eINSTANCE.getWireEdge_From(), ModelPackage.eINSTANCE.getWireEdge_To());
		wire.setIsGenerated(true);
		wire.setGeneratedBy(by);
		return wire;
	}
	
	public NavigateWire generatedNavigateWire(GeneratesElements by, ContainsWires container, WireEdgesSource source, WireEdgeDestination target) throws InferenceException {
		NavigateWire wire = (NavigateWire) createRelationship(container, WiresPackage.eINSTANCE.getNavigateWire(), source, target, ModelPackage.eINSTANCE.getContainsWires_Wires(), ModelPackage.eINSTANCE.getWireEdge_From(), ModelPackage.eINSTANCE.getWireEdge_To());
		wire.setIsGenerated(true);
		wire.setGeneratedBy(by);
		return wire;
	}

	public Page generatedPage(GeneratesElements by, ApplicationElementContainer container) throws InferenceException {
		Page page = (Page) createElement( container, VisualPackage.eINSTANCE.getPage(), ModelPackage.eINSTANCE.getApplicationElementContainer_Children() );
		page.setIsGenerated(true);
		page.setGeneratedBy(by);
		return page;
	}

	public Page generatedPage(GeneratesElements by, InternetApplication container) throws InferenceException {
		Page page = (Page) createElement( container, VisualPackage.eINSTANCE.getPage(), ModelPackage.eINSTANCE.getInternetApplication_Children() );
		page.setIsGenerated(true);
		page.setGeneratedBy(by);
		return page;
	}

	public InputForm generatedInputForm(GeneratesElements by, ApplicationElementContainer container) throws InferenceException {
		InputForm form = (InputForm) createElement( container, VisualPackage.eINSTANCE.getInputForm(), ModelPackage.eINSTANCE.getApplicationElementContainer_Children() );
		form.setIsGenerated(true);
		form.setGeneratedBy(by);
		return form;
	}

	public InputTextField generatedInputTextField(GeneratesElements by, ApplicationElementContainer container) throws InferenceException {
		InputTextField form = (InputTextField) createElement( container, VisualPackage.eINSTANCE.getInputTextField(), ModelPackage.eINSTANCE.getApplicationElementContainer_Children() );
		form.setIsGenerated(true);
		form.setGeneratedBy(by);
		return form;
	}
	
	public Button generatedButton(GeneratesElements by, ApplicationElementContainer container) throws InferenceException {
		Button form = (Button) createElement( container, VisualPackage.eINSTANCE.getButton(), ModelPackage.eINSTANCE.getApplicationElementContainer_Children() );
		form.setIsGenerated(true);
		form.setGeneratedBy(by);
		return form;
	}
	
}
