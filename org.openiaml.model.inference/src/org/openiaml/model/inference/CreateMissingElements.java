/**
 * 
 */
package org.openiaml.model.inference;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.ChainedOperation;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.ContainsWires;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.DataFlowEdgeDestination;
import org.openiaml.model.model.DataFlowEdgesSource;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainStore;
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
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.operations.OperationsPackage;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.operations.StopNode;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RunInstanceWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.model.wires.WiresPackage;

/**
 * This is meant to be an engine that supports creating elements
 * in the model automatically.
 * 
 * Currently it's all Java-based, so there is likely many logic
 * holes in here! In the future, we would like to move this to a
 * real inference engine. 
 * 
 * @author jmwright
 *
 */
public class CreateMissingElements {
	
	private ICreateElements parent;

	public CreateMissingElements(ICreateElements parent) {
		this.parent = parent;
	}
	
	/**
	 * Use inference (or whatever) to create missing elements in the model.
	 * The ICreateElements parent will actually create any elements needed.
	 * 
	 * @param parent
	 * @param rootObject
	 * @throws InferenceException 
	 */
	public void create(EObject rootObject) throws InferenceException {

		if (rootObject instanceof InternetApplication) {
			InternetApplication vt = (InternetApplication) rootObject;

			for (ApplicationElement e : vt.getChildren()) {
				handleChild(e);
				
				// recursive
				create(e);
			}
		}
		if (rootObject instanceof ApplicationElementContainer) {
			ApplicationElementContainer vt = (ApplicationElementContainer) rootObject;

			for (ApplicationElement e : vt.getChildren()) {
				handleChild(e);
				
				// recursive
				create(e);
			}
		}
		if (rootObject instanceof DomainStore) {
			DomainStore vt = (DomainStore) rootObject;

			for (ApplicationElement e : vt.getChildren()) {
				handleChild(e);
				
				// recursive
				create(e);
			}
		}
		if (rootObject instanceof DomainObject) {
			DomainObject vt = (DomainObject) rootObject;

			for (ApplicationElement e : vt.getAttributes()) {
				handleChild(e);
				
				// recursive
				create(e);
			}
		}
		if (rootObject instanceof InputTextField) {
			InputTextField vt = (InputTextField) rootObject;
			
			handleFieldValueProperty(vt); 
		}
		
		// now handle the wires
		if (rootObject instanceof WireEdgesSource) {
			WireEdgesSource vt = (WireEdgesSource) rootObject;
			
			handleWires(vt);
		}
				
	}
	
	/**
	 * @param vt
	 * @throws InferenceException 
	 */
	private void handleWires(WireEdgesSource root) throws InferenceException {
		for (WireEdge wire : root.getWires()) {
			if (wire instanceof SyncWire) {
				SyncWire vt = (SyncWire) wire;
				
				handleSyncWire(vt);
			}
		}
	}

	/**
	 * @param root
	 * @throws InferenceException 
	 */
	private void handleSyncWire(SyncWire root) throws InferenceException {
		// don't generate elements if it has been overridden
		if (!root.isOverridden()) {
			
			// syncing two input text fields?
			// todo future: abstract away to (anything that contains fieldValue property)
			if (root.getFrom() instanceof InputTextField && 
					root.getTo() instanceof InputTextField) {
				
				InputTextField from = (InputTextField) root.getFrom();
				InputTextField to = (InputTextField) root.getTo();
				
				// TODO replace all these string constants with
				// class constants

				// get field value
				ApplicationElementProperty fromValue = getApplicationElementProperty(root, from, "fieldValue");
				// get field value
				ApplicationElementProperty toValue = getApplicationElementProperty(root, to, "fieldValue");

				// add 'update' operation
				CompositeOperation fromUpdate = getCompositeOperation(from, "update", fromValue);
				// add 'refresh' operation
				CompositeOperation fromRefresh = getCompositeOperation(from, "refresh", toValue);
				// add 'edit' event trigger
				EventTrigger fromEdit = getEventTrigger(from, "edit");
				// add 'change' event trigger
				EventTrigger fromChange = getEventTrigger(from, "change");

				// add 'update' operation
				CompositeOperation toUpdate = getCompositeOperation(to, "update", toValue);
				// add 'refresh' operation
				CompositeOperation toRefresh = getCompositeOperation(to, "refresh", fromValue);
				// add 'edit' event trigger
				EventTrigger toEdit = getEventTrigger(to, "edit");
				// add 'change' event trigger
				EventTrigger toChange = getEventTrigger(to, "change");
				
				// connect them where necessary
				connectEventToOperationWithParameter(root, fromEdit, toUpdate, fromValue);
				connectEventToOperationWithParameter(root, toChange, fromRefresh, fromValue);
				connectEventToOperationWithParameter(root, fromChange, toRefresh, toValue);
				connectEventToOperationWithParameter(root, toEdit, fromUpdate, toValue);

			}
			
		}
	}

	/**
	 * @param to
	 * @param string
	 * @return
	 * @throws InferenceException 
	 */
	private ApplicationElementProperty getApplicationElementProperty(
			SyncWire root, InputTextField to, String string) throws InferenceException {

		for (ApplicationElementProperty property : to.getProperties()) {
			if (property.getName().equals(string))
				return property;
		}
		
		// nothing found: create a new one
		ApplicationElementProperty property = (ApplicationElementProperty) parent.createElement(to, ModelPackage.eINSTANCE.getApplicationElementProperty(), ModelPackage.eINSTANCE.getApplicationElementContainer_Children());
		markAsGenerated(root, property);
		setElementName(property, string);
		
		return property;
		
	}


	/**
	 * @param field
	 * @param field2
	 * @param string
	 * @throws InferenceException 
	 */
	private ApplicationElementProperty getApplicationElementProperty(InputTextField root,
			InputTextField to, String string) throws InferenceException {

		for (ApplicationElementProperty property : to.getProperties()) {
			if (property.getName().equals(string))
				return property;
		}
		
		// nothing found: create a new one
		ApplicationElementProperty property = (ApplicationElementProperty) parent.createElement(to, ModelPackage.eINSTANCE.getApplicationElementProperty(), ModelPackage.eINSTANCE.getApplicationElementContainer_Children());
		markAsGenerated(root, property);
		setElementName(property, string);
		
		return property;
		
	}	
	
	/**
	 * @param fromEdit
	 * @param toUpdate
	 * @param fromValue
	 * @throws InferenceException 
	 */
	private void connectEventToOperationWithParameter(SyncWire container,
			EventTrigger event,
			CompositeOperation operation, ApplicationElementProperty parameter) throws InferenceException {

		// does a runinstance wire exist?
		RunInstanceWire rwi = getRunInstanceWire(container, event, operation, "run");
		
		// does a parameter wire exist?
		ParameterWire pw = getParameterWire(container, rwi, parameter);
		
	}

	/**
	 * @param container
	 * @param rwi
	 * @param parameter
	 * @return
	 * @throws InferenceException 
	 */
	private ParameterWire getParameterWire(SyncWire container,
			RunInstanceWire rwi, ApplicationElementProperty parameter) throws InferenceException {

		for (WireEdge w : container.getWires()) {
			if (w instanceof ParameterWire) {
				ParameterWire pw = (ParameterWire) w;
				if (pw.getFrom().equals(parameter) && pw.getTo().equals(rwi)) {
					return pw;
				}
			}
		}
		
		// nothing found: create a new one
		ParameterWire pw = (ParameterWire) parent.createRelationship(container, WiresPackage.eINSTANCE.getParameterWire(), rwi, parameter, ModelPackage.eINSTANCE.getContainsWires_Wires(), ModelPackage.eINSTANCE.getWireEdge_From(), ModelPackage.eINSTANCE.getWireEdge_To());
		markAsGenerated(container, pw);
		
		return pw;

	}

	/**
	 * @param container
	 * @param event
	 * @param operation
	 * @return
	 * @throws InferenceException 
	 */
	private RunInstanceWire getRunInstanceWire(SyncWire container,
			EventTrigger event, CompositeOperation operation, String name) throws InferenceException {

		for (WireEdge w : container.getWires()) {
			if (w instanceof RunInstanceWire) {
				RunInstanceWire rwi = (RunInstanceWire) w;
				if (rwi.getFrom().equals(event) && rwi.getTo().equals(operation) && 
						rwi.getName().equals(name)) {
					return rwi;
				}
			}
		}
		
		// nothing found: create a new one
		RunInstanceWire rwi = (RunInstanceWire) (ParameterWire) parent.createRelationship(container, WiresPackage.eINSTANCE.getRunInstanceWire(), event, operation, ModelPackage.eINSTANCE.getContainsWires_Wires(), ModelPackage.eINSTANCE.getWireEdge_From(), ModelPackage.eINSTANCE.getWireEdge_To());
		markAsGenerated(container, rwi);
		setElementName(rwi, name);
		
		return rwi;

	}

	/**
	 * @param from
	 * @param string
	 * @return
	 * @throws InferenceException 
	 */
	private EventTrigger getEventTrigger(InputTextField from, String string) throws InferenceException {
		for (EventTrigger o : from.getEventTriggers()) {
			if (o.getName().equals(string))
				return o;
		}

		// it's not there, so we better create it
		EventTrigger event = (EventTrigger) parent.createElement(from, ModelPackage.eINSTANCE.getEventTrigger(), ModelPackage.eINSTANCE.getApplicationElementContainer_Children());
		markAsGenerated(from, event);
		setElementName(event, string);
		
		return event;
		
	}

	/**
	 * get an operation with a certain name
	 * 
	 * @param from
	 * @param string
	 * @return
	 * @throws InferenceException 
	 */
	private CompositeOperation getCompositeOperation(InputTextField from,
			String string, ApplicationElementProperty fieldValue) throws InferenceException {
		for (Operation o : from.getOperations()) {
			if (o instanceof CompositeOperation && o.getName().equals(string)) {
				// found it
				return (CompositeOperation) o; 
			}
		}
		
		// it's not there, so we better create it
		CompositeOperation operation = (CompositeOperation) parent.createElement(from, ModelPackage.eINSTANCE.getCompositeOperation(), ModelPackage.eINSTANCE.getApplicationElementContainer_Children());
		markAsGenerated(from, operation);
		setElementName(operation, string);
		
		// what should this operation contain?
		// TODO should update and refresh really be the same thing?
		if (string.equals("update") || string.equals("refresh")) {
			StartNode startNode = (StartNode) parent.createElement(operation, OperationsPackage.eINSTANCE.getStartNode(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes());
			markAsGenerated(from, startNode);
			
			StopNode stopNode = (StopNode) parent.createElement(operation, OperationsPackage.eINSTANCE.getStopNode(), ModelPackage.eINSTANCE.getCompositeOperation_Nodes());
			markAsGenerated(from, stopNode);
			
			ChainedOperation setPropertyOp = (ChainedOperation) parent.createElement(operation, ModelPackage.eINSTANCE.getChainedOperation(), ModelPackage.eINSTANCE.getContainsOperations_Operations());
			markAsGenerated(from, setPropertyOp);
			setElementName(setPropertyOp, "setPropertyToValue");
			
			Parameter param = (Parameter) parent.createElement(operation, ModelPackage.eINSTANCE.getParameter(), ModelPackage.eINSTANCE.getCompositeOperation__shouldnt_parameters());
			markAsGenerated(from, param);
			setElementName(param, "setValueTo");
			
			// connect them together
			ExecutionEdge edge = connectExecutionEdge(operation, startNode, setPropertyOp);
			markAsGenerated(from, edge);
			ExecutionEdge edge2 = connectExecutionEdge(operation, setPropertyOp, stopNode);
			markAsGenerated(from, edge2);
			DataFlowEdge flow = connectDataEdge(operation, param, setPropertyOp);
			markAsGenerated(from, flow);
			DataFlowEdge flow2 = connectDataEdge(operation, setPropertyOp, fieldValue);
			markAsGenerated(from, flow2);
		}
		
		return null;
	}

	private ExecutionEdge connectExecutionEdge(CompositeOperation container,
			ExecutionEdgesSource source, ExecutionEdgeDestination target) throws InferenceException {
		
		ExecutionEdge edge = (ExecutionEdge) parent.createRelationship(container, ModelPackage.eINSTANCE.getExecutionEdge(), source, target, ModelPackage.eINSTANCE.getCompositeOperation_ExecutionEdges(), ModelPackage.eINSTANCE.getExecutionEdge_From(), ModelPackage.eINSTANCE.getExecutionEdge_To());
		return edge;
		
	}

	private DataFlowEdge connectDataEdge(CompositeOperation container,
			DataFlowEdgesSource source, DataFlowEdgeDestination target) throws InferenceException {
		
		DataFlowEdge edge = (DataFlowEdge) parent.createRelationship(container, ModelPackage.eINSTANCE.getDataFlowEdge(), source, target, ModelPackage.eINSTANCE.getCompositeOperation_DataEdges(), ModelPackage.eINSTANCE.getDataFlowEdge_From(), ModelPackage.eINSTANCE.getDataFlowEdge_To());
		return edge;
		
	}
	/**
	 * @param co
	 * @param string
	 * @throws InferenceException 
	 */
	private void setElementName(NamedElement element, String string) throws InferenceException {
		parent.setValue(element, ModelPackage.eINSTANCE.getNamedElement_Name(), string);		
	}

	/**
	 * TODO rename to markAsGeneratedBy
	 * @param co
	 * @throws InferenceException 
	 */
	private void markAsGenerated(GeneratesElements by, GeneratedElement element) throws InferenceException {
		parent.setValue(element, ModelPackage.eINSTANCE.getGeneratedElement_IsGenerated(), true);
		parent.setValue(element, ModelPackage.eINSTANCE.getGeneratedElement_GeneratedBy(), by);
	}

	/**
	 * Should this element have a fieldValue property?
	 * 
	 * @param field
	 * @throws InferenceException
	 */
	private void handleFieldValueProperty(InputTextField field) throws InferenceException {
		// don't generate elements if it has been overridden
		if (!field.isOverridden()) {
			
			getApplicationElementProperty(field, field, "fieldValue");
			
		}
	}

	private void handleChild(GeneratesElements e) throws InferenceException {
		// don't generate elements if it has been overridden
		if (!e.isOverridden()) {
			
			// get all the input forms
			if (e instanceof ApplicationElementContainer) {
				ApplicationElementContainer f = (ApplicationElementContainer) e;
				
				// get all the wires
				for (WireEdge w : f.getOutEdges()) {
					// get all the sync wires
					
					if (w instanceof SyncWire && ((SyncWire) w).getTo() instanceof ApplicationElementContainer) {
						// sync up these elements
						doSyncWires(f, (ApplicationElementContainer) w.getFrom(), (ApplicationElementContainer) w.getTo(), (SyncWire) w);
						// and back again
						doSyncWires(f, (ApplicationElementContainer) w.getTo(), (ApplicationElementContainer) w.getFrom(), (SyncWire) w);
					}
				}
				
			}
		}
	}
		

	/**
	 * A SyncWire between a source and a target: all of the components in the
	 * source should be linked up with the target. needs to be called twice to
	 * sync up bidirectionally.
	 * 
	 * @param source
	 * @param target
	 * @throws InferenceException 
	 */
	protected void doSyncWires(ContainsWires container, ApplicationElementContainer source, ApplicationElementContainer target, SyncWire generatedBy) throws InferenceException {
		// map each of the children in the source
		for (ApplicationElement child : source.getChildren()) {
			ApplicationElement mapTarget = getChildMatch(child, target);
			if (mapTarget != null) {
				// we have an element to map to
				// is it already mapped?
				if (!elementsAreAlreadySyncWire(child, mapTarget)) {
					// map them together
					
					SyncWire wire = (SyncWire) parent.createRelationship(container, WiresPackage.eINSTANCE.getSyncWire(), child, mapTarget, ModelPackage.eINSTANCE.getContainsWires_Wires(), ModelPackage.eINSTANCE.getWireEdge_From(), ModelPackage.eINSTANCE.getWireEdge_To());
					Assert.isTrue(wire != null);
					
					// set sync wire parameters
					parent.setValue(wire, ModelPackage.eINSTANCE.getNamedElement_Name(), "sync[generated]");
					// set isGenerated parameter
					parent.setValue(wire, ModelPackage.eINSTANCE.getGeneratedElement_IsGenerated(), true);
					// set generatedBy parameter
					parent.setValue(wire, ModelPackage.eINSTANCE.getGeneratedElement_GeneratedBy(), generatedBy);

				}
			}
		}
	}
	
	
	/**
	 * are two elements already sync wired together?
	 * 
	 * @param c
	 * @param mapTarget
	 * @return
	 */
	private boolean elementsAreAlreadySyncWire(ApplicationElement c,
			ApplicationElement mapTarget) {
		
		for (WireEdge w : c.getOutEdges()) {
			if (w.getTo().equals(mapTarget) && w instanceof SyncWire)
				return true;
		}
		for (WireEdge w : c.getInEdges()) {
			if (w.getFrom().equals(mapTarget) && w instanceof SyncWire)
				return true;
		}
		
		return false;
		
	}

	/**
	 * Get a child in the targetParent that matches the source element
	 * 
	 * @see #childrenMapUp(ApplicationElement, ApplicationElement)
	 * @param source
	 * @param targetParent
	 * @return an ApplicationElement, or null if none match
	 */
	private ApplicationElement getChildMatch(ApplicationElement source, ApplicationElementContainer targetParent) {
		for (ApplicationElement c : targetParent.getChildren()) {
			if (childrenMapUp(source, c))
				return c;
		}
		return null;
	}
	
	/**
	 * Do two elements match up, for sync purposes? In our case, we say they
	 * do if they have the same name (case insensitive)
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	private boolean childrenMapUp(ApplicationElement source, ApplicationElement target) {
		return source.getName().toLowerCase().equals(target.getName().toLowerCase());
	}
	
	
}
