/**
 *
 */
package org.openiaml.model.tests.inference;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.jaxen.JaxenException;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.ApplicationElementContainer;
import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.Condition;
import org.openiaml.model.model.ContainsConditions;
import org.openiaml.model.model.ContainsEventTriggers;
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
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.ExecutionEdge;
import org.openiaml.model.model.ExecutionEdgeDestination;
import org.openiaml.model.model.ExecutionEdgesSource;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.Parameter;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.WireEdgeDestination;
import org.openiaml.model.model.WireEdgesSource;
import org.openiaml.model.model.components.AccessControlHandler;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.operations.CancelNode;
import org.openiaml.model.model.operations.DecisionOperation;
import org.openiaml.model.model.operations.FinishNode;
import org.openiaml.model.model.operations.JoinNode;
import org.openiaml.model.model.operations.OperationCallNode;
import org.openiaml.model.model.operations.SplitNode;
import org.openiaml.model.model.operations.StartNode;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.Role;
import org.openiaml.model.model.users.UserInstance;
import org.openiaml.model.model.users.UserStore;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.model.wires.ConditionWire;
import org.openiaml.model.model.wires.ExtendsWire;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.model.wires.NewInstanceWire;
import org.openiaml.model.model.wires.ParameterWire;
import org.openiaml.model.model.wires.RequiresWire;
import org.openiaml.model.model.wires.RunInstanceWire;
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
	 * Assert that the given element contains the given
	 * ApplicationElementProperty.
	 *
	 * @return The element found
	 */
	public ApplicationElementProperty assertHasApplicationElementProperty(
			ApplicationElement element, String string) throws JaxenException {
		return (ApplicationElementProperty) queryOne(element, "iaml:properties[iaml:name='" + string + "']");
	}
	
	/**
	 * Assert that the given element contains the given
	 * ApplicationElementProperty.
	 *
	 * @return The element found
	 */
	public ApplicationElementProperty assertHasApplicationElementProperty(
			Session element, String string) throws JaxenException {
		return (ApplicationElementProperty) queryOne(element, "iaml:properties[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element does not contains the given
	 * ApplicationElementProperty.
	 *
	 * @return The element found
	 */
	public void assertHasNoApplicationElementProperty(
			ApplicationElement element, String string) throws JaxenException {
		assertHasNone(element, "iaml:properties[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element does not contains the given
	 * ApplicationElementProperty.
	 *
	 * @return The element found
	 */
	public void assertHasNoApplicationElementProperty(
			Session element, String string) throws JaxenException {
		assertHasNone(element, "iaml:properties[iaml:name='" + string + "']");
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
	 * EventTrigger.
	 *
	 * @return The element found
	 */
	public EventTrigger assertHasEventTrigger(ContainsEventTriggers element,
			String string) throws JaxenException {
		return (EventTrigger) queryOne(element, "iaml:eventTriggers[iaml:name='" + string + "']");
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
	public DomainObject assertHasDomainObject(InternetApplication root, String string) throws JaxenException {
		return (DomainObject) queryOne(root, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * DomainObject.
	 *
	 * @return The element found
	 */
	public DomainObject assertHasDomainObject(ApplicationElementContainer root, String string) throws JaxenException {
		return (DomainObject) queryOne(root, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * UserInstance.
	 *
	 * @return The element found
	 */
	public UserInstance assertHasUserInstance(ApplicationElementContainer root, String string) throws JaxenException {
		return (UserInstance) queryOne(root, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * UserInstance.
	 *
	 * @return The element found
	 */
	public UserInstance assertHasUserInstance(Session root, String string) throws JaxenException {
		return (UserInstance) queryOne(root, "iaml:children[iaml:name='" + string + "']");
	}

	/**
	 * Assert that the given element contains the given
	 * DomainObjectInstance.
	 *
	 * @return The element found
	 */
	public DomainObjectInstance assertHasDomainObjectInstance(ApplicationElementContainer root, String string) throws JaxenException {
		return (DomainObjectInstance) queryOne(root, "iaml:children[iaml:name='" + string + "']");
	}
	
	/**
	 * Assert that the given element contains the given
	 * DomainObjectInstance.
	 *
	 * @return The element found
	 */
	public DomainObjectInstance assertHasDomainObjectInstance(Session root, String string) throws JaxenException {
		return (DomainObjectInstance) queryOne(root, "iaml:children[iaml:name='" + string + "']");
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
	public void assertHasNoDomainObjectInstance(ApplicationElementContainer root, String string) throws JaxenException {
		assertHasNone(root, "iaml:children[iaml:name='" + string + "']");
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
	public InputTextField assertHasInputTextField(VisibleThing element, String string) throws JaxenException {
		return (InputTextField) queryOne(element, "iaml:children[iaml:name='" + string + "']");
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
	 * InputForm.
	 *
	 * @return The element found
	 */
	public InputForm assertHasInputForm(VisibleThing element, String string) throws JaxenException {
		return (InputForm) queryOne(element, "iaml:children[iaml:name='" + string + "']");
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
	public AccessControlHandler assertHasAccessControlHandler(Session root, String string) throws JaxenException {
		return (AccessControlHandler) queryOne(root, "iaml:children[iaml:name='" + string + "']");	
	}
	
	/**
	 * Assert that the given element contains the given
	 * Page.
	 *
	 * @return The element found
	 */
	public Page assertHasPage(InternetApplication root, String string) throws JaxenException {
		return (Page) queryOne(root, "iaml:children[iaml:name='" + string + "']");	
	}

	/**
	 * Assert that the given element contains the given
	 * Page.
	 *
	 * @return The element found
	 */
	public Page assertHasPage(Session session, String string) throws JaxenException {
		return (Page) queryOne(session, "iaml:children[iaml:name='" + string + "']");	
	}

	/**
	 * Assert that the given element does not contain the given
	 * Page.
	 */
	public void assertHasNoPage(Session session, String string) throws JaxenException {
		assertHasNone(session, "iaml:children[iaml:name='" + string + "']");
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
	public LoginHandler assertHasLoginHandler(Session session, String string) throws JaxenException {
		return (LoginHandler) queryOne(session, "iaml:children[iaml:name='" + string + "']");	
	}

	/**
	 * Assert that the given element does not contain the given
	 * LoginHandler.
	 *
	 * @return The element found
	 */
	public void assertHasNoLoginHandler(Session session, String string) throws JaxenException {
		assertHasNone(session, "iaml:children[iaml:name='" + string + "']");	
	}

	/**
	 * Assert that the given element contains the given
	 * Session.
	 *
	 * @return The element found
	 */
	public Session assertHasSession(InternetApplication root, String string) throws JaxenException {
		return (Session) queryOne(root, "iaml:sessions[iaml:name='" + string + "']");	
	}
	
	/**
	 * Assert that the given element contains the given
	 * StaticValue.
	 *
	 * @return The element found
	 */
	public StaticValue assertHasStaticValue(Session session, String string) throws JaxenException {
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
	 * Assert there exists only one bidirectional SyncWire between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public SyncWire assertHasSyncWire(EObject container, WireEdgesSource element1, WireEdgeDestination element2, String name) throws JaxenException {
		return (SyncWire) assertHasWiresBidirectional(1, container, element1, element2, SyncWire.class).iterator().next();
	}
	
	/**
	 * Assert there exists only one unidirectional ParameterWire between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public ParameterWire assertHasParameterWire(EObject container, WireEdgesSource from, WireEdgeDestination to) throws JaxenException {
		return (ParameterWire) assertHasWireFromTo(container, from, to, 
				ParameterWire.class);
	}
	
	/**
	 * Assert there exists only one unidirectional ExtendsWire between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public ExtendsWire assertHasExtendsWire(EObject container, WireEdgesSource from, WireEdgeDestination to) throws JaxenException {
		return (ExtendsWire) assertHasWireFromTo(container, from, to, 
				ExtendsWire.class);
	}
	
	/**
	 * Assert there exists only one unidirectional RequiresWire between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public RequiresWire assertHasRequiresWire(EObject container, WireEdgesSource from, WireEdgeDestination to) throws JaxenException {
		return (RequiresWire) assertHasWireFromTo(container, from, to, 
				RequiresWire.class);
	}
	
	/**
	 * Assert there exists only one unidirectional RunInstanceWire between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public RunInstanceWire assertHasRunInstanceWire(EObject container, WireEdgesSource from, WireEdgeDestination to, String name) throws JaxenException {
		return (RunInstanceWire) assertHasWireFromTo(container, from, to, 
				RunInstanceWire.class, name);
	}
	
	/**
	 * Assert there exists only one unidirectional SetWire between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public SetWire assertHasSetWire(EObject container, WireEdgesSource from, WireEdgeDestination to, String name) throws JaxenException {
		return (SetWire) assertHasWireFromTo(container, from, to, 
				SetWire.class, name);
	}
	
	/**
	 * Assert <em>no</em> unidirectional SetWire exists between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public void assertHasNoSetWire(EObject container, WireEdgesSource from, WireEdgeDestination to) throws JaxenException {
		assertHasNoWiresFromTo(container, from, to, SetWire.class);
	}
	
	/**
	 * Assert there exists only one unidirectional SelectWire between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public SelectWire assertHasSelectWire(EObject container, WireEdgesSource from, WireEdgeDestination to, String name) throws JaxenException {
		return (SelectWire) assertHasWireFromTo(container, from, to, 
				SelectWire.class, name);
	}	
	
	/**
	 * Assert there exists only one unidirectional NewInstanceWire between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public NewInstanceWire assertHasNewInstanceWire(EObject container, WireEdgesSource from, WireEdgeDestination to, String name) throws JaxenException {
		return (NewInstanceWire) assertHasWireFromTo(container, from, to, 
				NewInstanceWire.class, name);
	}
	
	/**
	 * Assert there exists only one unidirectional ConditionWire between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public ConditionWire assertHasConditionWire(EObject container, WireEdgesSource from, WireEdgeDestination to, String name) throws JaxenException {
		return (ConditionWire) assertHasWireFromTo(container, from, to, 
				ConditionWire.class, name);
	}
	
	/**
	 * Assert there exists only one unidirectional NavigateWire between
	 * the given elements.
	 *
	 * @return The element found
	 */
	public NavigateWire assertHasNavigateWire(EObject container, WireEdgesSource from, WireEdgeDestination to, String name) throws JaxenException {
		return (NavigateWire) assertHasWireFromTo(container, from, to, 
				NavigateWire.class, name);
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
	 * @see #assertHasWireFromTo(EObject, WireEdgesSource, WireEdgeDestination, String, Class)
	 * @param container
	 * @param from
	 * @param to
	 * @param type
	 * @return the wire edge found
	 * @throws JaxenException 
	 */
	public WireEdge assertHasWireFromTo(EObject container, WireEdgesSource from, WireEdgeDestination to, Class<? extends EObject> type) throws JaxenException {
		Set<WireEdge> wires = getWiresFromTo(container, from, to);
		WireEdge result = null;
		for (WireEdge w : wires) {
			if (type.isInstance(w)) {
				// found it
				if (result != null)
					fail("Found more than 1 wire from '" + from + "' to '" + to + "' class='" + type + "'. First = '" + result + "', second = '" + w + "'");
				result = w;
			}
		}
		assertNotNull("Did not find any wires connecting '" + from + "' to '" + to + " of type '" + type + "'", result);
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
	 * @return
	 * @throws JaxenException 
	 */
	public WireEdge assertHasWireFromTo(EObject container, WireEdgesSource from, WireEdgeDestination to, Class<? extends EObject> type, String name) throws JaxenException {
		Set<WireEdge> wires = getWiresFromTo(container, from, to);
		WireEdge result = null;
		for (WireEdge w : wires) {
			if (type.isInstance(w) && w instanceof NamedElement && name.equals(((NamedElement) w).getName())) {
				// found it
				if (result != null)
					fail("Found more than 1 wire from '" + from + "' to '" + to + "' class='" + type + "' name='" + name + "'. First = '" + result + "', second = '" + w + "'");
				result = w;
			}
		}
		assertNotNull("Did not find any wires connecting '" + from + "' to '" + to + " of type '" + type + "' with name '" + name + "'", result);
		return result;
	}

}
