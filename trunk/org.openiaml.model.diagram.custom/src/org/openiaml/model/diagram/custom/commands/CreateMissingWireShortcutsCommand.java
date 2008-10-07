package org.openiaml.model.diagram.custom.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.wires.CompositeWire;

/**
 * Implementation of the missing shortcuts for CompositeWire.
 * 
 * @note Yes, this is a copy of CreateMissingVisualShortcutsCommand, 
 * but this is because the Ecore model considers them as two different 
 * entities. (Indeed, we have different editors for each of them.)
 * 
 * @author jmwright
 *
 */
public class CreateMissingWireShortcutsCommand extends
		AbstractCreateMissingShortcutsCommand {

	private String modelId;
	
	public CreateMissingWireShortcutsCommand(GraphicalEditPart root, PreferencesHint prefHint, String modelId) {
		super(root, prefHint);
		this.modelId = modelId;
	}
	
	@Override
	protected List<WireEdge> getEdgesIn(EObject object) {
		CompositeWire rootObject = (CompositeWire) object;
		
		List<WireEdge> connectionsIn = new ArrayList<WireEdge>();
		
		// StaticValue doesn't have in edges
		
		// ApplicationElement (incl VisualThing and Page)
		for (ApplicationElement child : rootObject.getChildren()) {
			connectionsIn.addAll( child.getInEdges() );
		}

		// EventTrigger doesn't have in edges
		
		// Operation (incl ChainedOperation)
		for (Operation child : rootObject.getOperations()) {
			connectionsIn.addAll( child.getInEdges() );
		}
		
		// ApplicationElementProperty
		for (ApplicationElementProperty child : rootObject.getProperties()) {
			connectionsIn.addAll( child.getInEdges() );
		}
		
		return connectionsIn;
	}

	@Override
	protected List<WireEdge> getEdgesOut(EObject object) {
		CompositeWire rootObject = (CompositeWire) object;
		
		List<WireEdge> connectionsOut = new ArrayList<WireEdge>();

		// StaticValue
		for (StaticValue child : rootObject.getValues()) {
			connectionsOut.addAll( child.getOutEdges() );
		}

		// ApplicationElement (incl VisualThing and Page)
		for (ApplicationElement child : rootObject.getChildren()) {
			connectionsOut.addAll( child.getOutEdges() );
		}
		
		// EventTrigger
		for (EventTrigger child : rootObject.getEventTriggers()) {
			connectionsOut.addAll( child.getOutEdges() );
		}
		
		// Operation (incl ChainedOperation)
		for (Operation child : rootObject.getOperations()) {
			// not all Operations have outwards edges
			if (child instanceof WireEdgesSource) {
				connectionsOut.addAll( ((WireEdgesSource) child).getOutEdges() );
			}
		}
		
		// ApplicationElementProperty
		for (ApplicationElementProperty child : rootObject.getProperties()) {
			connectionsOut.addAll( child.getOutEdges() );
		}

		return connectionsOut;

	}

	@Override
	protected String getEditPartModelId() {
		return modelId;
	}

}
