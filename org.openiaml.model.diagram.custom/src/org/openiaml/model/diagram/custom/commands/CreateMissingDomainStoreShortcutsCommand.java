package org.openiaml.model.diagram.custom.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgesSource;

/**
 * Implementation of the missing shortcuts for DomainStore.
 * 
 * @author jmwright
 *
 */
public class CreateMissingDomainStoreShortcutsCommand extends
		AbstractCreateMissingShortcutsCommand {

	private String modelId;
	
	public CreateMissingDomainStoreShortcutsCommand(GraphicalEditPart root, PreferencesHint prefHint, String modelId) {
		super(root, prefHint);
		this.modelId = modelId;
	}
	
	@Override
	protected List<WireEdge> getEdgesIn(EObject object) {
		DomainStore rootObject = (DomainStore) object;
		
		List<WireEdge> connectionsIn = new ArrayList<WireEdge>();

		// DomainObject <- ApplicationElement
		for (DomainObject child : rootObject.getChildren()) {
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
		DomainStore rootObject = (DomainStore) object;
		
		List<WireEdge> connectionsOut = new ArrayList<WireEdge>();

		// DomainObject <- ApplicationElement
		for (DomainObject child : rootObject.getChildren()) {
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
