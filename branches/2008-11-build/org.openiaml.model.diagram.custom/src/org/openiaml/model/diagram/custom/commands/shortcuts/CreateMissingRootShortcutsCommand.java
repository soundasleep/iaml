package org.openiaml.model.diagram.custom.commands.shortcuts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgesSource;

/**
 * Implementation of the missing shortcuts for InternetApplications
 * 
 * @author jmwright
 *
 */
public class CreateMissingRootShortcutsCommand extends
		AbstractCreateMissingShortcutsCommand {

	private String modelId;
	
	public CreateMissingRootShortcutsCommand(GraphicalEditPart root, PreferencesHint prefHint, String modelId) {
		super(root, prefHint);
		this.modelId = modelId;
	}
	
	@Override
	protected List<WireEdge> getEdgesIn(EObject object) {
		InternetApplication rootObject = (InternetApplication) object;
		
		List<WireEdge> connectionsIn = new ArrayList<WireEdge>();

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
		
		// DomainStore doesn't have in edges
		
		return connectionsIn;
	}

	@Override
	protected List<WireEdge> getEdgesOut(EObject object) {
		InternetApplication rootObject = (InternetApplication) object;
		
		List<WireEdge> connectionsOut = new ArrayList<WireEdge>();
		
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

		// DomainStore doesn't have out edges

		return connectionsOut;
	}

	@Override
	protected String getEditPartModelId() {
		return modelId;
	}

}
