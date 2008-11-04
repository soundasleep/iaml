/**
 * 
 */
package org.openiaml.model.inference;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ChainedOperation;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.ContainsEventTriggers;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.GeneratesElements;
import org.openiaml.model.model.ModelPackage;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.operations.OperationsPackage;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.operations.StopNode;
import org.openiaml.model.model.visual.InputTextField;

/**
 * Provides some helpful wrapper methods.
 * 
 * TODO it would be nice one day if this could be automatically generated?
 * 
 * @author jmwright
 *
 */
public abstract class EcoreCreateElementsHelper implements ICreateElements {

	public ApplicationElementProperty generatedApplicationElementProperty(GeneratesElements by, InputTextField container) throws InferenceException {
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

	public StartNode generatedStartNode(GeneratesElements by, CompositeOperation container) throws InferenceException {
		StartNode node = (StartNode) createElement( container, OperationsPackage.eINSTANCE.getStartNode(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes() );
		node.setIsGenerated(true);
		node.setGeneratedBy(by);
		return node;
	}

	public StopNode generatedStopNode(GeneratesElements by, CompositeOperation container) throws InferenceException {
		StopNode node = (StopNode) createElement( container, OperationsPackage.eINSTANCE.getStopNode(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes() );
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

}
