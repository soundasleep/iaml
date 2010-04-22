package org.openiaml.model.diagram.custom.commands.shortcuts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.DataFlowEdgesSource;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.ExecutionEdgesSource;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgesSource;

/**
 * Implementation of the missing shortcuts for DomainStore.
 * 
 * @author jmwright
 *
 */
public class CreateMissingOperationShortcutsCommand extends
		AbstractCreateMissingShortcutsCommand {

	private String modelId;
	
	public CreateMissingOperationShortcutsCommand(GraphicalEditPart root, PreferencesHint prefHint, String modelId) {
		super(root, prefHint);
		this.modelId = modelId;
	}
	
	@Override
	protected List<WireEdge> getEdgesIn(EObject object) {
		CompositeOperation rootObject = (CompositeOperation) object;
		
		List<WireEdge> connectionsIn = new ArrayList<WireEdge>();

		// TemporaryVariable has no in edges

		// OperationParameter has no in edges
		
		// Operation (incl ChainedOperation)
		for (Operation child : rootObject.getOperations()) {
			connectionsIn.addAll( child.getInEdges() );
		}
		
		return connectionsIn;
	}

	@Override
	protected List<WireEdge> getEdgesOut(EObject object) {
		CompositeOperation rootObject = (CompositeOperation) object;
		
		List<WireEdge> connectionsOut = new ArrayList<WireEdge>();

		// TemporaryVariable has no out edges

		// OperationParameter has no out edges
				
		// Operation (incl ChainedOperation)
		for (Operation child : rootObject.getOperations()) {
			// not all Operations have outwards edges
			if (child instanceof WireEdgesSource) {
				connectionsOut.addAll( ((WireEdgesSource) child).getOutEdges() );
			}
		}

		return connectionsOut;

	}

	
	@Override
	protected List<ExecutionEdge> getExecutionEdgesIn(EObject object) {
		CompositeOperation rootObject = (CompositeOperation) object;
		
		List<ExecutionEdge> connectionsIn = new ArrayList<ExecutionEdge>();

		// TemporaryVariable has no in edges

		// OperationParameter has no in edges
		
		// Operation (incl ChainedOperation)
		for (Operation child : rootObject.getOperations()) {
			connectionsIn.addAll( child.getInExecutions() );
		}
		
		return connectionsIn;
	}

	@Override
	protected List<ExecutionEdge> getExecutionEdgesOut(EObject object) {
		CompositeOperation rootObject = (CompositeOperation) object;
		
		List<ExecutionEdge> connectionsOut = new ArrayList<ExecutionEdge>();

		// TemporaryVariable has no out edges

		// OperationParameter has no out edges
				
		// Operation (incl ChainedOperation)
		for (Operation child : rootObject.getOperations()) {
			// not all Operations have outwards edges
			if (child instanceof ExecutionEdgesSource) {
				connectionsOut.addAll( ((ExecutionEdgesSource) child).getOutExecutions() );
			}
		}

		return connectionsOut;

	}
	
	
	@Override
	protected List<DataFlowEdge> getFlowEdgesIn(EObject object) {
		CompositeOperation rootObject = (CompositeOperation) object;
		
		List<DataFlowEdge> connectionsIn = new ArrayList<DataFlowEdge>();

		// TemporaryVariable has no in edges

		// OperationParameter has no in edges
		
		// Operation (incl ChainedOperation)
		for (Operation child : rootObject.getOperations()) {
			connectionsIn.addAll( child.getInFlows() );
		}
		
		return connectionsIn;
	}

	@Override
	protected List<DataFlowEdge> getFlowEdgesOut(EObject object) {
		CompositeOperation rootObject = (CompositeOperation) object;
		
		List<DataFlowEdge> connectionsOut = new ArrayList<DataFlowEdge>();

		// TemporaryVariable has no out edges

		// OperationParameter has no out edges
				
		// Operation (incl ChainedOperation)
		for (Operation child : rootObject.getOperations()) {
			// not all Operations have outwards edges
			if (child instanceof DataFlowEdgesSource) {
				connectionsOut.addAll( ((DataFlowEdgesSource) child).getOutFlows() );
			}
		}

		return connectionsOut;

	}
	
	@Override
	protected String getEditPartModelId() {
		return modelId;
	}

}