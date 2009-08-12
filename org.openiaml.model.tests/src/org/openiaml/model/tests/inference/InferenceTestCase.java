/**
 *
 */
package org.openiaml.model.tests.inference;

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
import org.openiaml.model.model.DomainAttribute;
import org.openiaml.model.model.DomainAttributeInstance;
import org.openiaml.model.model.DomainObject;
import org.openiaml.model.model.DomainObjectInstance;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.EventTrigger;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.Operation;
import org.openiaml.model.model.StaticValue;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Page;
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
	public DomainAttributeInstance assertHasDomainAttributeInstance(DomainObjectInstance obj,
			String string) throws JaxenException {
		return (DomainAttributeInstance) queryOne(obj, "iaml:attributes[iaml:name='" + string + "']");
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
	 * DomainObjectInstance.
	 *
	 * @return The element found
	 */
	public DomainObjectInstance assertHasDomainObjectInstance(ApplicationElementContainer root, String string) throws JaxenException {
		return (DomainObjectInstance) queryOne(root, "iaml:children[iaml:name='" + string + "']");
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
	 * Assert that the given element contains the given
	 * LoginHandler.
	 *
	 * @return The element found
	 */
	public LoginHandler assertHasLoginHandler(Session session, String string) throws JaxenException {
		return (LoginHandler) queryOne(session, "iaml:children[iaml:name='" + string + "']");	
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
	 * DynamicApplicationElementSet.
	 *
	 * @return The element found
	 */
	public DynamicApplicationElementSet assertHasDynamicApplicationElementSet(InternetApplication element, String string) throws JaxenException {
		return (DynamicApplicationElementSet) queryOne(element, "iaml:children[iaml:name='" + string + "']");	
	}

}
