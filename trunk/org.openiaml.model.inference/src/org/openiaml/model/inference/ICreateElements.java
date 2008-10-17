/**
 * 
 */
package org.openiaml.model.inference;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ChainedOperation;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.DataFlowEdgeDestination;
import org.openiaml.model.model.DataFlowEdgesSource;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.ExecutionEdgeDestination;
import org.openiaml.model.model.ExecutionEdgesSource;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.operations.StopNode;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;

/**
 * An interface to something that creates elements.
 * 
 * Q: why not use one of the existing interfaces, e.g. ModelFactory?
 * @author jmwright
 *
 */
public interface ICreateElements {

	/**
	 * @return
	 */
	public SyncWire createSyncWire(ContainsWires container, WireEdgesSource source,
			WireEdgeDestination target) throws InferenceException;

	/**
	 * @param element
	 * @param reference
	 * @param value
	 */
	void setValue(EObject element, EStructuralFeature reference, Object value)
	 throws InferenceException;

	/**
	 * @param field
	 * @return
	 */
	public ApplicationElementProperty createApplicationElementProperty(
			InputTextField field) throws InferenceException;

	/**
	 * @param from
	 * @return
	 */
	public CompositeOperation createCompositeOperation(InputTextField from);

	/**
	 * @param co
	 * @return
	 */
	public StartNode createStartNode(CompositeOperation co);

	/**
	 * @param co
	 * @return
	 */
	public StopNode createStopNode(CompositeOperation co);

	/**
	 * @param co
	 * @return
	 */
	public ChainedOperation createChainedOperation(CompositeOperation co);

	/**
	 * @param co
	 * @return
	 */
	public Parameter createParameter(CompositeOperation co);

	/**
	 * @param container
	 * @param source
	 * @param target
	 * @return
	 */
	public ExecutionEdge createExecutionEdge(CompositeOperation container,
			ExecutionEdgesSource source, ExecutionEdgeDestination target);

	/**
	 * @param container
	 * @param source
	 * @param target
	 * @return
	 */
	public DataFlowEdge createDataFlowEdge(CompositeOperation container,
			DataFlowEdgesSource source, DataFlowEdgeDestination target);

	/**
	 * @param from
	 * @return
	 */
	public EventTrigger createEventTrigger(InputTextField from);

	/**
	 * @param container
	 * @param rwi
	 * @param parameter
	 * @return
	 */
	public ParameterWire createParameterWire(SyncWire container,
			RunInstanceWire rwi, ApplicationElementProperty parameter);

	/**
	 * @param container
	 * @param event
	 * @param operation
	 * @return
	 */
	public RunInstanceWire createRunInstanceWire(SyncWire container,
			EventTrigger event, CompositeOperation operation);

	
	
}
