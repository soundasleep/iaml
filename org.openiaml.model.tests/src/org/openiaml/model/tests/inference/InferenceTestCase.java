/**
 *
 */
package org.openiaml.model.tests.inference;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.jaxen.JaxenException;
import org.openiaml.model.model.Action;
import org.openiaml.model.model.ActionDestination;
import org.openiaml.model.model.ActionSource;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsOperations;
import org.openiaml.model.model.DataFlowEdge;
import org.openiaml.model.model.DataFlowEdgeDestination;
import org.openiaml.model.model.DataFlowEdgesSource;
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.ExecutionEdgeDestination;
import org.openiaml.model.model.ExecutionEdgesSource;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.PrimitiveOperation;
import org.openiaml.model.model.Property;
import org.openiaml.model.model.QueryParameter;
import org.openiaml.model.model.Scope;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.WireDestination;
import org.openiaml.model.model.WireSource;
import org.openiaml.model.model.components.AccessControlHandler;
import org.openiaml.model.model.components.EntryGate;
import org.openiaml.model.model.components.ExitGate;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionCondition;
import org.openiaml.model.model.operations.DecisionOperation;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.JoinNode;
import org.openiaml.model.model.operations.OperationCallNode;
import org.openiaml.model.model.operations.SplitNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.RequiresEdgeDestination;
import org.openiaml.model.model.users.RequiresEdgesSource;
import org.openiaml.model.model.users.Role;
import org.openiaml.model.model.users.UserInstance;
import org.openiaml.model.model.users.UserStore;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.model.wires.ConditionEdge;
import org.openiaml.model.model.wires.ConditionEdgeDestination;
import org.openiaml.model.model.wires.ConditionEdgesSource;
import org.openiaml.model.model.wires.ExtendsEdge;
import org.openiaml.model.model.wires.ExtendsEdgeDestination;
import org.openiaml.model.model.wires.ExtendsEdgesSource;
import org.openiaml.model.model.wires.NavigateAction;
import org.openiaml.model.model.wires.NewInstanceWire;
import org.openiaml.model.model.wires.ParameterEdge;
import org.openiaml.model.model.wires.ParameterEdgeDestination;
import org.openiaml.model.model.wires.ParameterEdgesSource;
import org.openiaml.model.model.wires.RequiresEdge;
import org.openiaml.model.model.wires.RunAction;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.model.wires.SyncWire;
import org.openiaml.model.tests.ModelInferenceTestCase;

/**
 * Test case methods for asserting the results of model inference.
 *
 * @author jmwright
 */
public abstract class InferenceTestCase extends ModelInferenceTestCase {

	/**
	 * A way to selectively filter objects.
	 * 
	 * @author jmwright
	 * @param <T> the inner type to filter
	 */
	public static interface Filter<T extends Object> {
		public boolean accept(T o);
	}
	
	/**
	 * A filter that accepts everything.
	 */
	public static final Filter<Wire> ALL = new Filter<Wire>() {
		@Override
		public boolean accept(Wire o) {
			return true;
		}
	};
	
	/**
	 * A filter that accepts everything.
	 */
	public static final Filter<Action> ALL_ACTIONS = new Filter<Action>() {
		@Override
		public boolean accept(Action o) {
			return true;
		}
	};
	
	/**
	 * Assert that the given element contains the given
	 * Property.
	 *
	 * @return The element found
	 */
	public Property assertHasProperty(
			Scope element, String string) throws JaxenException {
		return (Property) queryOne(element, "iaml:properties[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * Property.
	 *
	 * @return The element found
	 */
	public Property assertHasProperty(
			VisibleThing element, String string) throws JaxenException {
		return (Property) queryOne(element, "iaml:properties[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * Property.
	 *
	 * @return The element found
	 */
	public Property assertHasProperty(
			ApplicationElement element, String string) throws JaxenException {
		return (Property) queryOne(element, "iaml:properties[iaml:name='" + string + "']");
	}
	
	/**
	 * Assert that the given element does not contains the given
	 * Property.
	 *
	 * @return The element found
	 */
	public void assertHasNoProperty(
			ApplicationElement element, String string) throws JaxenException {
		assertHasNone(element, "iaml:properties[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element does not contains the given
	 * Property.
	 *
	 * @return The element found
	 */
	public void assertHasNoProperty(
			Scope element, String string) throws JaxenException {
		assertHasNone(element, "iaml.scopes:properties[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element does not contains the given
	 * Property.
	 *
	 * @return The element found
	 */
	public void assertHasNoProperty(
			VisibleThing element, String string) throws JaxenException {
		assertHasNone(element, "iaml:properties[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element does not contains the given
	 * Property called 'fieldValue'.
	 *
	 * @return The element found
	 */
	public void assertHasNoFieldValue(
			ApplicationElement element) throws JaxenException {
		assertHasNoProperty(element, "fieldValue");
	}
	
	/**
	 * Assert that the given element does not contains the given
	 * Property called 'fieldValue'.
	 *
	 * @return The element found
	 */
	public void assertHasNoFieldValue(
			Scope element) throws JaxenException {
		assertHasNoProperty(element, "fieldValue");
	}
	
	/**
	 * Assert that the given element does not contains the given
	 * Property called 'fieldValue'.
	 *
	 * @return The element found
	 */
	public void assertHasNoFieldValue(
			VisibleThing element) throws JaxenException {
		assertHasNoProperty(element, "fieldValue");
	}
	
	/**
	 * Assert that the given element contains the given
	 * Property called 'fieldValue'.
	 *
	 * @return The element found
	 */
	public Property assertHasFieldValue(
			VisibleThing element) throws JaxenException {
		return assertHasProperty(element, "fieldValue");
	}
	
	/**
	 * Assert that the given element contains the given
	 * Operation.
	 *
	 * @return The element found
	 */
	public Operation assertHasOperation(ContainsOperations element, String string) throws JaxenException {
		return (Operation) queryOne(element, "iaml:operations[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * CompositeOperation.
	 *
	 * @return The element found
	 */
	public CompositeOperation assertHasCompositeOperation(ContainsOperations element, String string) throws JaxenException {
		return (CompositeOperation) assertHasOperation(element, string);
	}

	/**
	 * Assert that the given element contains the given
	 * PrimitiveOperation.
	 *
	 * @return The element found
	 */
	public PrimitiveOperation assertHasPrimitiveOperation(ContainsOperations element, String string) throws JaxenException {
		return (PrimitiveOperation) assertHasOperation(element, string);
	}

	/**
	 * Assert that the given element contains the given
	 * Condition.
	 *
	 * @return The element found
	 */
	public Condition assertHasCondition(ContainsConditions element, String string) throws JaxenException {
		return (Condition) queryOne(element, "iaml:conditions[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * CompositeCondition.
	 *
	 * @return The element found
	 */
	public CompositeCondition assertHasCompositeCondition(ContainsConditions element, String string) throws JaxenException {
		return (CompositeCondition) assertHasCondition(element, string);
	}

	/**
	 * Assert that the given element contains the given
	 * CompositeCondition.
	 *
	 * @return The element found
	 */
	public DecisionCondition assertHasDecisionCondition(ContainsConditions element, String string) throws JaxenException {
		return (DecisionCondition) assertHasCondition(element, string);
	}

	/**
	 * Assert that the given element contains the given
	 * DomainAttribute.
	 *
	 * @return The element found
	 */
	public DomainAttribute assertHasDomainAttribute(DomainObject obj,
			String string) throws JaxenException {
		return (DomainAttribute) queryOne(obj, "iaml:attributes[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * DomainAttribute.
	 *
	 * @return The element found
	 */
	public DomainAttribute assertHasDomainAttribute(DomainStore obj,
			String string) throws JaxenException {
		return (DomainAttribute) queryOne(obj, "iaml:attributes[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * DomainAttributeInstance.
	 *
	 * @return The element found
	 */
	public DomainAttributeInstance assertHasDomainAttributeInstance(ApplicationElement obj,
			String string) throws JaxenException {
		return (DomainAttributeInstance) queryOne(obj, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * DomainAttributeInstance.
	 *
	 * @return The element found
	 */
	public DomainAttributeInstance assertHasDomainAttributeInstance(Scope obj,
			String string) throws JaxenException {
		return (DomainAttributeInstance) queryOne(obj, "iaml:elements[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * DomainAttributeInstance.
	 *
	 * @return The element found
	 */
	public DomainAttributeInstance assertHasDomainAttributeInstance(InternetApplication obj,
			String string) throws JaxenException {
		return (DomainAttributeInstance) queryOne(obj, "iaml:children[iaml:name='" + string + "']");
	}
	
	/**
	 * Assert that the given element contains the given
	 * DomainAttributeInstance.
	 *
	 * @return The element found
	 */
	public DomainAttributeInstance assertHasDomainAttributeInstance(DomainObjectInstance obj,
			String string) throws JaxenException {
		return (DomainAttributeInstance) queryOne(obj, "iaml:attributes[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element does not contain the given
	 * DomainAttributeInstance.
	 *
	 * @return The element found
	 */
	public void assertHasNoDomainAttributeInstance(ApplicationElement obj,
			String string) throws JaxenException {
		assertHasNone(obj, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element does not contain the given
	 * DomainAttributeInstance.
	 *
	 * @return The element found
	 */
	public void assertHasNoDomainAttributeInstance(InternetApplication obj,
			String string) throws JaxenException {
		assertHasNone(obj, "iaml:children[iaml:name='" + string + "']");
	}
	
	/**
	 * Assert that the given element does not contain the given
	 * DomainAttributeInstance.
	 *
	 * @return The element found
	 */
	public void assertHasNoDomainAttributeInstance(DomainObjectInstance obj,
			String string) throws JaxenException {
		assertHasNone(obj, "iaml:attributes[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * DomainObject.
	 *
	 * @return The element found
	 */
	public DomainObject assertHasDomainObject(DomainStore store, String string) throws JaxenException {
		return (DomainObject) queryOne(store, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * DomainObject.
	 *
	 * @return The element found
	 */
	public DomainObject assertHasDomainObject(Scope store, String string) throws JaxenException {
		return (DomainObject) queryOne(store, "iaml:elements[iaml:name='" + string + "']");
	}
	
	/**
	 * Assert that the given element contains the given
	 * DomainObject.
	 *
	 * @return The element found
	 */
	public DomainObject assertHasDomainObject(InternetApplication root, String string) throws JaxenException {
		return (DomainObject) queryOne(root, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * UserInstance.
	 *
	 * @return The element found
	 */
	public UserInstance assertHasUserInstance(Scope root, String string) throws JaxenException {
		return (UserInstance) queryOne(root, "iaml:elements[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * DomainObjectInstance.
	 *
	 * @return The element found
	 */
	public DomainObjectInstance assertHasDomainObjectInstance(Scope root, String string) throws JaxenException {
		return (DomainObjectInstance) queryOne(root, "iaml:elements[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * DomainObjectInstance.
	 *
	 * @return The element found
	 */
	public DomainObjectInstance assertHasDomainObjectInstance(InternetApplication root, String string) throws JaxenException {
		return (DomainObjectInstance) queryOne(root, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element does not contain the given
	 * DomainObjectInstance.
	 *
	 * @return The element found
	 */
	public void assertHasNoDomainObjectInstance(InternetApplication root, String string) throws JaxenException {
		assertHasNone(root, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * DomainStore.
	 *
	 * @return The element found
	 */
	public DomainStore assertHasDomainStore(InternetApplication root,
			String string) throws JaxenException {
		return (DomainStore) queryOne(root, "iaml:domainStores[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * InputTextField.
	 *
	 * @return The element found
	 */
	public InputTextField assertHasInputTextField(Frame element, String string) throws JaxenException {
		return (InputTextField) queryOne(element, "iaml.visual:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * InputTextField.
	 *
	 * @return The element found
	 */
	public InputTextField assertHasInputTextField(VisibleThing element, String string) throws JaxenException {
		return (InputTextField) queryOne(element, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element does not contains the given
	 * InputTextField.
	 *
	 * @return The element found
	 */
	public void assertHasNoInputTextField(Frame element, String string) throws JaxenException {
		assertHasNone(element, "iaml.visual:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element does not contains the given
	 * InputTextField.
	 *
	 * @return The element found
	 */
	public void assertHasNoInputTextField(VisibleThing element, String string) throws JaxenException {
		assertHasNone(element, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * Label.
	 *
	 * @return The element found
	 */
	public Label assertHasLabel(Frame element, String string) throws JaxenException {
		return (Label) queryOne(element, "iaml.visual:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * Label.
	 *
	 * @return The element found
	 */
	public Label assertHasLabel(VisibleThing element, String string) throws JaxenException {
		return (Label) queryOne(element, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element does not contains the given
	 * Label.
	 *
	 * @return The element found
	 */
	public void assertHasNoLabel(Frame element, String string) throws JaxenException {
		assertHasNone(element, "iaml.visual:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element does not contains the given
	 * Label.
	 *
	 * @return The element found
	 */
	public void assertHasNoLabel(VisibleThing element, String string) throws JaxenException {
		assertHasNone(element, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * InputForm.
	 *
	 * @return The element found
	 */
	public InputForm assertHasInputForm(VisibleThing element, String string) throws JaxenException {
		return (InputForm) queryOne(element, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * InputForm.
	 *
	 * @return The element found
	 */
	public InputForm assertHasInputForm(Frame element, String string) throws JaxenException {
		return (InputForm) queryOne(element, "iaml.visual:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * Button.
	 *
	 * @return The element found
	 */
	public Button assertHasButton(VisibleThing element, String string) throws JaxenException {
		return (Button) queryOne(element, "iaml:children[iaml:name='" + string + "']");
	}
	
	/**
	 * Assert that the given element contains the given
	 * Button.
	 *
	 * @return The element found
	 */
	public Button assertHasButton(Frame element, String string) throws JaxenException {
		return (Button) queryOne(element, "iaml.visual:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * UserStore.
	 *
	 * @return The element found
	 */
	public UserStore assertHasUserStore(InternetApplication root, String string) throws JaxenException {
		return (UserStore) queryOne(root, "iaml:domainStores[iaml:name='" + string + "']");	
	}

	/**
	 * Assert that the given element contains the given
	 * Role.
	 *
	 * @return The element found
	 */
	public Role assertHasRole(UserStore root, String string) throws JaxenException {
		return (Role) queryOne(root, "iaml:children[iaml:name='" + string + "']");	
	}
	
	/**
	 * Assert that the given element contains the given
	 * AccessControlHandler.
	 *
	 * @return The element found
	 */
	public AccessControlHandler assertHasAccessControlHandler(Scope root, String string) throws JaxenException {
		return (AccessControlHandler) queryOne(root, "iaml:elements[iaml:name='" + string + "']");	
	}
	
	/**
	 * Assert that the given element contains the given
	 * EntryGate.
	 *
	 * @return The element found
	 */
	public EntryGate assertHasEntryGate(Scope root, String string) throws JaxenException {
		return (EntryGate) queryOne(root, "iaml:entryGate[iaml:name='" + string + "']");	
	}
	
	/**
	 * Assert that the given element contains the given
	 * ExitGate.
	 *
	 * @return The element found
	 */
	public ExitGate assertHasExitGate(Scope root, String string) throws JaxenException {
		return (ExitGate) queryOne(root, "iaml:exitGate[iaml:name='" + string + "']");	
	}

	/**
	 * Assert that the given element contains the given
	 * Frame.
	 */
	public Frame assertHasFrame(Scope scope, String string) throws JaxenException {
		return (Frame) queryOne(scope, "iaml:scopes[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element does not contain the given
	 * Frame.
	 */
	public void assertHasNoFrame(Scope scope, String string) throws JaxenException {
		assertHasNone(scope, "iaml:scopes[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * Frame.
	 */
	public Frame assertHasFrame(InternetApplication scope, String string) throws JaxenException {
		return (Frame) queryOne(scope, "iaml:scopes[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element does not contain the given
	 * Frame.
	 */
	public void assertHasNoFrame(InternetApplication scope, String string) throws JaxenException {
		assertHasNone(scope, "iaml:scopes[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element does not contain the given
	 * Page.
	 */
	public void assertHasNoPage(InternetApplication root, String string) throws JaxenException {
		assertHasNone(root, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * LoginHandler.
	 *
	 * @return The element found
	 */
	public LoginHandler assertHasLoginHandler(Scope session, String string) throws JaxenException {
		return (LoginHandler) queryOne(session, "iaml:elements[iaml:name='" + string + "']");	
	}

	/**
	 * Assert that the given element does not contain the given
	 * LoginHandler.
	 *
	 * @return The element found
	 */
	public void assertHasNoLoginHandler(Scope session, String string) throws JaxenException {
		assertHasNone(session, "iaml.scopes:elements[iaml:name='" + string + "']");	
	}

	/**
	 * Assert that the given element contains the given
	 * Session.
	 *
	 * @return The element found
	 */
	public Session assertHasSession(InternetApplication root, String string) throws JaxenException {
		return (Session) queryOne(root, "iaml:scopes[iaml:name='" + string + "']");	
	}
	
	/**
	 * Assert that the given element contains the given
	 * StaticValue.
	 *
	 * @return The element found
	 */
	public StaticValue assertHasStaticValue(Scope session, String string) throws JaxenException {
		return (StaticValue) queryOne(session, "iaml:values[iaml:name='" + string + "']");	
	}
	
	/**
	 * Assert that the given element contains the given
	 * StaticValue.
	 *
	 * @return The element found
	 */
	public StaticValue assertHasStaticValue(CompositeOperation element, String string) throws JaxenException {
		return (StaticValue) queryOne(element, "iaml:values[iaml:name='" + string + "']");	
	}
	
	/**
	 * Assert that the given element contains the given
	 * DynamicApplicationElementSet.
	 *
	 * @return The element found
	 */
	public DynamicApplicationElementSet assertHasDynamicApplicationElementSet(InternetApplication element, String string) throws JaxenException {
		return (DynamicApplicationElementSet) queryOne(element, "iaml:children[iaml:name='" + string + "']");	
	}
	
	/**
	 * Assert that the given element contains the given
	 * DecisionOperation.
	 *
	 * @return The element found
	 */
	public DecisionOperation assertHasDecisionOperation(ContainsOperations element, String string) throws JaxenException {
		return (DecisionOperation) queryOne(element, "iaml:operations[iaml:name='" + string + "']");	
	}
	
	/**
	 * Assert that the given element contains the given
	 * SplitNode.
	 *
	 * @return The element found
	 */
	public SplitNode assertHasSplitNode(CompositeOperation element) throws JaxenException {
		return (SplitNode) assertHasOne(element, "iaml:nodes", SplitNode.class);
	}
	
	/**
	 * Assert that the given element contains the given
	 * JoinNode.
	 *
	 * @return The element found
	 */
	public JoinNode assertHasJoinNode(CompositeOperation element) throws JaxenException {
		return (JoinNode) assertHasOne(element, "iaml:nodes", JoinNode.class);
	}
	
	/**
	 * Assert that the given element contains the given
	 * StartNode.
	 *
	 * @return The element found
	 */
	public StartNode assertHasStartNode(CompositeOperation element) throws JaxenException {
		return (StartNode) assertHasOne(element, "iaml:nodes", StartNode.class);
	}
	
	/**
	 * Assert that the given element contains the given
	 * StartNode.
	 *
	 * @return The element found
	 */
	public StartNode assertHasStartNode(CompositeCondition element) throws JaxenException {
		return (StartNode) assertHasOne(element, "iaml:nodes", StartNode.class);
	}
	
	/**
	 * Assert that the given element contains the given
	 * FinishNode.
	 *
	 * @return The element found
	 */
	public FinishNode assertHasFinishNode(CompositeOperation element) throws JaxenException {
		return (FinishNode) assertHasOne(element, "iaml:nodes", FinishNode.class);
	}
	
	/**
	 * Assert that the given element contains the given
	 * FinishNode.
	 *
	 * @return The element found
	 */
	public FinishNode assertHasFinishNode(CompositeCondition element) throws JaxenException {
		return (FinishNode) assertHasOne(element, "iaml:nodes", FinishNode.class);
	}
	
	/**
	 * Assert that the given element <em>does not</em> contains the given
	 * FinishNode.
	 *
	 * @return The element found
	 */
	public void assertHasNoFinishNode(CompositeOperation element) throws JaxenException {
		assertHasNone(element, "iaml:nodes", FinishNode.class);
	}
	
	/**
	 * Assert that the given element contains the given
	 * CancelNode.
	 *
	 * @return The element found
	 */
	public CancelNode assertHasCancelNode(CompositeOperation element) throws JaxenException {
		return (CancelNode) assertHasOne(element, "iaml:nodes", CancelNode.class);
	}
	
	/**
	 * Assert that the given element contains the given
	 * CancelNode.
	 *
	 * @return The element found
	 */
	public CancelNode assertHasCancelNode(CompositeCondition element) throws JaxenException {
		return (CancelNode) assertHasOne(element, "iaml:nodes", CancelNode.class);
	}
	
	/**
	 * Assert that the given element <em>does not</em> contains the given
	 * CancelNode.
	 *
	 * @return The element found
	 */
	public void assertHasNoCancelNode(CompositeOperation element) throws JaxenException {
		assertHasNone(element, "iaml:nodes", CancelNode.class);
	}
	
	/**
	 * Assert that the given element contains the given
	 * OperationCallNode.
	 *
	 * @return The element found
	 */
	public OperationCallNode assertHasOperationCallNode(CompositeOperation element, String string) throws JaxenException {
		return (OperationCallNode) queryOne(element, "iaml:nodes[iaml:name='" + string + "']");	
	}
	
	/**
	 * Assert that the given element contains the given
	 * OperationCallNode.
	 *
	 * @return The element found
	 */
	public OperationCallNode assertHasOperationCallNode(CompositeCondition element, String string) throws JaxenException {
		return (OperationCallNode) queryOne(element, "iaml:nodes[iaml:name='" + string + "']");	
	}
	
	/**
	 * Assert that the given element contains the given
	 * Parameter.
	 *
	 * @return The element found
	 */
	public Parameter assertHasParameter(CompositeOperation element, String string) throws JaxenException {
		return (Parameter) queryOne(element, "iaml:parameters[iaml:name='" + string + "']");	
	}
	
	/**
	 * Assert that the given element contains the given
	 * QueryParameter.
	 *
	 * @return The element found
	 */
	public QueryParameter assertHasQueryParameter(Frame element, String string) throws JaxenException {
		return (QueryParameter) queryOne(element, "iaml:parameters[iaml:name='" + string + "']");	
	}
	
	/**
	 * Assert there exists only one bidirectional SyncWire between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public SyncWire assertHasSyncWire(EObject container, WireSource element1, WireDestination element2, String name) throws JaxenException {
		Set<Wire> x = assertHasWiresBidirectional(1, container, element1, element2, SyncWire.class);
		assertEquals(1, x.size());
		SyncWire sw = (SyncWire) x.iterator().next();
		assertEquals(name, sw.getName());
		return sw;
	}
	
	/**
	 * Assert there exists only one bidirectional SyncWire between
	 * the given elements, with any name.
	 *
	 * @return The element found
	 */
	public SyncWire assertHasSyncWire(EObject container, WireSource element1, WireDestination element2) throws JaxenException {
		Set<Wire> x = assertHasWiresBidirectional(1, container, element1, element2, SyncWire.class);
		assertEquals(1, x.size());
		SyncWire sw = (SyncWire) x.iterator().next();
		return sw;
	}
	
	/**
	 * Assert there exists only one unidirectional SetWire between
	 * the given elements, with any name.
	 *
	 * @return The element found
	 */
	public SetWire assertHasSetWire(EObject container, WireSource from, WireDestination to) throws JaxenException {
		return (SetWire) assertHasWireFromTo(container, from, to, SetWire.class, ALL);
	}
	
	/**
	 * Assert there exists only one unidirectional ParameterEdge between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public ParameterEdge assertHasParameterEdge(EObject container, ParameterEdgesSource from, ParameterEdgeDestination to) throws JaxenException {
		Set<ParameterEdge> params = getParameterEdgesFromTo(container, from, to);
		assertEquals("Should be exactly one parameter edge: " + params, 1, params.size());
		return params.iterator().next();
	}
	
	/**
	 * Assert there exists only one unidirectional ExtendsEdge between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public ExtendsEdge assertHasExtendsEdge(EObject container, ExtendsEdgesSource from, ExtendsEdgeDestination to) throws JaxenException {
		Set<ExtendsEdge> params = getExtendsEdgesFromTo(container, from, to);
		assertEquals("Should be exactly one extends edge: " + params, 1, params.size());
		return params.iterator().next();
	}
	
	/**
	 * Assert there exists only one unidirectional RequiresEdge between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public RequiresEdge assertHasRequiresEdge(EObject container, RequiresEdgesSource from, RequiresEdgeDestination to) throws JaxenException {
		Set<RequiresEdge> params = getRequiresEdgesFromTo(container, from, to);
		assertEquals("Should be exactly one requires edge: " + params, 1, params.size());
		return params.iterator().next();
	}
	
	/**
	 * Assert there exists only one unidirectional RunAction between
	 * the given elements, with the given name.
	 *
	 * @return The element found
	 */
	public RunAction assertHasRunAction(EObject container, ActionSource from, ActionDestination to, String name) throws JaxenException {
		return (RunAction) assertHasActionFromTo(container, from, to, 
				RunAction.class, getNameFilter(name));
	}
	
	/**
	 * Construct a new filter for only selecting {@link Action}s with a given name.
	 * 
	 * @param name the name to search for
	 * @return a new name filter for {@link Action}s.
	 */
	private Filter<Action> getNameFilter(final String name) {
		return new Filter<Action>() {
			@Override
			public boolean accept(Action o) {
				if (o instanceof NamedElement) {
					return name.equals(((NamedElement) o).getName());
				}						
				return false;
			}
		};		
	}
	
	/**
	 * Assert there exists only one unidirectional RunAction between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public RunAction assertHasRunAction(EObject container, ActionSource from, ActionDestination to) throws JaxenException {
		return (RunAction) assertHasActionFromTo(container, from, to, 
				RunAction.class, ALL_ACTIONS);
	}
	
	/**
	 * Assert there exists only one unidirectional RunAction between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public RunAction assertHasRunAction(EObject container, ActionSource from, ActionDestination to, Filter<Action> filter) throws JaxenException {
		return (RunAction) assertHasActionFromTo(container, from, to, 
				RunAction.class, filter);
	}
	
	/**
	 * Assert there exists only one unidirectional SetWire between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public SetWire assertHasSetWire(EObject container, WireSource from, WireDestination to, String name) throws JaxenException {
		return (SetWire) assertHasWireFromTo(container, from, to, 
				SetWire.class, name, ALL);
	}
	
	/**
	 * Assert <em>no</em> unidirectional SetWire exists between
	 * the given elements.
	 */
	public void assertHasNoSetWire(EObject container, WireSource from, WireDestination to) throws JaxenException {
		assertHasNoWiresFromTo(container, from, to, SetWire.class);
	}
	
	
	/**
	 * Assert <em>no</em> bidirectional SyncWire exists between
	 * the given elements.
	 */
	public void assertHasNoSyncWire(EObject container, WireSource from, WireDestination to) throws JaxenException {
		Set<Wire> wires = getWiresBidirectional(container, from, to, SyncWire.class);
		assertEquals("Unexpected SyncWires found: " + wires, 0, wires.size());
	}
	
	/**
	 * Assert there exists only one unidirectional SelectWire between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public SelectWire assertHasSelectWire(EObject container, WireSource from, WireDestination to, String name) throws JaxenException {
		return (SelectWire) assertHasWireFromTo(container, from, to, 
				SelectWire.class, name, ALL);
	}	
	
	/**
	 * Assert there exists only one unidirectional SelectWire between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public SelectWire assertHasSelectWire(EObject container, WireSource from, WireDestination to) throws JaxenException {
		return (SelectWire) assertHasWireFromTo(container, from, to, 
				SelectWire.class, ALL);
	}
	
	/**
	 * Assert there exists only one unidirectional NewInstanceWire between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public NewInstanceWire assertHasNewInstanceWire(EObject container, WireSource from, WireDestination to, String name) throws JaxenException {
		return (NewInstanceWire) assertHasWireFromTo(container, from, to, 
				NewInstanceWire.class, name, ALL);
	}
	
	/**
	 * Assert there exists only one unidirectional ConditionEdge between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public ConditionEdge assertHasConditionEdge(EObject container, ConditionEdgesSource from, ConditionEdgeDestination to, String name) throws JaxenException {
		Set<ConditionEdge> params = getConditionEdgesFromTo(container, from, to, name);
		assertEquals("Should be exactly one condition edge with the name '" + name + "': " + params, 1, params.size());
		return params.iterator().next();
	}
	
	/**
	 * Assert there exists only one unidirectional ConditionEdge between
	 * the given elements. Ignores the name.
	 *
	 * @return The element found
	 */
	public ConditionEdge assertHasConditionEdge(EObject container, ConditionEdgesSource from, ConditionEdgeDestination to) throws JaxenException {
		Set<ConditionEdge> params = getConditionEdgesFromTo(container, from, to);
		assertEquals("Should be exactly one condition edge: " + params, 1, params.size());
		return params.iterator().next();
	}
	
	/**
	 * Assert there exists only one unidirectional NavigateAction between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public NavigateAction assertHasNavigateAction(EObject container, ActionSource from, ActionDestination to, String name) throws JaxenException {
		return (NavigateAction) assertHasActionFromTo(container, from, to, 
				NavigateAction.class, getNameFilter(name) );
	}
	
	/**
	 * Assert there exists only one unidirectional NavigateAction between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public NavigateAction assertHasNavigateAction(EObject container, ActionSource from, ActionDestination to) throws JaxenException {
		return (NavigateAction) assertHasActionFromTo(container, from, to, 
				NavigateAction.class, ALL_ACTIONS);
	}
	
	/**
	 * Assert there exists only one unidirectional ExecutionEdge between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public ExecutionEdge assertHasExecutionEdge(EObject container, ExecutionEdgesSource from, ExecutionEdgeDestination to) throws JaxenException {
		ExecutionEdge result = null;
		List<?> wires = query(container, "//iaml:executionEdges");
		for (Object o : wires) {
			if (o instanceof ExecutionEdge) {
				ExecutionEdge e = (ExecutionEdge) o;
				if (from.equals(e.getFrom()) && to.equals(e.getTo())) {
					if (result != null) {
						fail("Found more than one execution edge from '" + from + "' to '" + to + "'. First = '" + result + ", second = '" + e + "'");
					}
					result = e;
				}
			}
		}
		assertNotNull("Did not find an ExecutionEdge from '" + from + "' to '" + to + "'", result);
		return result;
	}
	
	/**
	 * Assert there exists only one unidirectional ExecutionEdge between
	 * the given elements, and only with the given name.
	 *
	 * @return The element found
	 */
	public ExecutionEdge assertHasExecutionEdge(EObject container, ExecutionEdgesSource from, ExecutionEdgeDestination to, String name) throws JaxenException {
		ExecutionEdge result = null;
		List<?> wires = query(container, "//iaml:executionEdges");
		for (Object o : wires) {
			if (o instanceof ExecutionEdge) {
				ExecutionEdge e = (ExecutionEdge) o;
				if (from.equals(e.getFrom()) && to.equals(e.getTo()) && name.equals(e.getName())) {
					if (result != null) {
						fail("Found more than one execution edge from '" + from + "' to '" + to + "' with name '" + name + "'. First = '" + result + ", second = '" + e + "'");
					}
					result = e;
				}
			}
		}
		assertNotNull("Did not find an ExecutionEdge from '" + from + "' to '" + to + "'", result);
		return result;
	}
	
	/**
	 * Assert there exists only one unidirectional DataFlowEdge between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public DataFlowEdge assertHasDataFlowEdge(EObject container, DataFlowEdgesSource from, DataFlowEdgeDestination to) throws JaxenException {
		DataFlowEdge result = null;
		List<?> edges = query(container, "//iaml:dataEdges");
		for (Object o : edges) {
			if (o instanceof DataFlowEdge) {
				DataFlowEdge e = (DataFlowEdge) o;
				if (from.equals(e.getFrom()) && to.equals(e.getTo())) {
					if (result != null) {
						fail("Found more than one data flow edge from '" + from + "' to '" + to + "'. First = '" + result + ", second = '" + e + "'");
					}
					result = e;
				}
			}
		}
		assertNotNull("Did not find an DataFlowEdge from '" + from + "' to '" + to + "'", result);
		return result;
	}
	
	/**
	 * Does <em>exactly one</em> DataFlowEdge exist between the given elements?
	 *
	 * @return true if exactly one edge exists, or false otherwise
	 */
	public boolean hasDataFlowEdge(EObject container, DataFlowEdgesSource from, DataFlowEdgeDestination to) throws JaxenException {
		int count = 0;
		List<?> wires = query(container, "//iaml:dataEdges");
		for (Object o : wires) {
			if (o instanceof DataFlowEdge) {
				DataFlowEdge e = (DataFlowEdge) o;
				if (from.equals(e.getFrom()) && to.equals(e.getTo())) {
					assertEquals("Found more than one data flow edge from '" + from + "' to '" + to + "'. Second = '" + e + "'", 0, count);
					count++;
				}
			}
		}
		return count == 1;
	}
	
	/**
	 * For unnamed objects that are only differentiated by xsi:type, we need
	 * a special method to find that only one of these types exist.
	 * 
	 * @param container
	 * @param query
	 * @param type
	 * @return
	 * @throws JaxenException 
	 */
	public EObject assertHasOne(EObject container, String query, Class<? extends EObject> type) throws JaxenException {
		EObject result = null;
		List<?> results = query(container, query);
		for (Object r : results) {
			if (type.isInstance(r)) {
				if (result != null) {
					fail("Found more than one '" + type + "' in container '" + container + "' with query '" + query + ". First = '" + result + ", second = '" + r + "'");
				}
				result = (EObject) r;
			}
		}
		assertNotNull("Did not find any results of '" + query + "' of type '" + type + "' in container '" + container + "'", result);
		return result;
	}
	
	/**
	 * Assert that there exists <em>only one</em> wire of the given type from the 'from' element
	 * to the 'to' element.
	 * 
	 * @see #assertHasWireFromTo(EObject, WireSource, WireDestination, String, Class)
	 * @param container
	 * @param from
	 * @param to
	 * @param type
	 * @return the wire edge found
	 * @throws JaxenException 
	 */
	public Wire assertHasWireFromTo(EObject container, WireSource from, WireDestination to, Class<? extends Wire> type, Filter<Wire> filter) throws JaxenException {
		Set<Wire> wires = getWiresFromTo(container, from, to);
		Wire result = null;
		for (Wire w : wires) {
			if (type.isInstance(w)) {
				if (filter == null || filter.accept(w)) {
					// found it
					if (result != null)
						fail("Found more than 1 wire from '" + from + "' to '" + to + "' class='" + type + "'. First = '" + result + "', second = '" + w + "'");
					result = w;
				}
			}
		}
		assertNotNull("Did not find any wires connecting '" + from + "' to '" + to + " of type '" + type + "'", result);
		return result;
	}
	
	/**
	 * Assert that there exists <em>only one</em> wire of the given type from the 'from' element
	 * to the 'to' element.
	 * 
	 * @see #assertHasWireFromTo(EObject, WireSource, WireDestination, String, Class)
	 * @param container
	 * @param from
	 * @param to
	 * @param type
	 * @return the wire edge found
	 * @throws JaxenException 
	 */
	public Action assertHasActionFromTo(EObject container, ActionSource from, ActionDestination to, Class<? extends Action> type, Filter<Action> filter) throws JaxenException {
		Set<Action> wires = getActionsFromTo(container, from, to);
		Action result = null;
		for (Action w : wires) {
			if (type.isInstance(w)) {
				if (filter == null || filter.accept(w)) {
					// found it
					if (result != null)
						fail("Found more than 1 Action from '" + from + "' to '" + to + "' class='" + type + "'. First = '" + result + "', second = '" + w + "'");
					result = w;
				}
			}
		}
		assertNotNull("Did not find any Actions connecting '" + from + "' to '" + to + " of type '" + type + "'", result);
		return result;
	}
	
	/**
	 * Assert that there exists <em>only one</em> wire of the given type from the 'from' element
	 * to the 'to' element, with the given name; the wire must implement NamedElement.
	 * 
	 * @param container
	 * @param from
	 * @param to
	 * @param type
	 * @param name the name of the NamedElement wire
	 * @param filter an optional filter; may be null
	 * @return
	 * @throws JaxenException 
	 */
	public Wire assertHasWireFromTo(EObject container, WireSource from, WireDestination to, Class<? extends Wire> type, String name, Filter<Wire> filter) throws JaxenException {
		Set<Wire> wires = getWiresFromTo(container, from, to);
		Wire result = null;
		for (Wire w : wires) {
			if (type.isInstance(w) && w instanceof NamedElement && name.equals(((NamedElement) w).getName())) {
				if (filter == null || filter.accept(w)) {
					// found it
					if (result != null)
						fail("Found more than 1 wire from '" + from + "' to '" + to + "' class='" + type + "' name='" + name + "'. First = '" + result + "', second = '" + w + "'");
					result = w;
				}
			}
		}
		assertNotNull("Did not find any wires connecting '" + from + "' to '" + to + " of type '" + type + "' with name '" + name + "'", result);
		return result;
	}

}
