/**
 * 
 */
package org.openiaml.model.tests.inference.model0_4;

import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.helpers.IamlBreadcrumb;
import org.openiaml.model.model.domain.DomainAttribute;
import org.openiaml.model.model.domain.DomainAttributeInstance;
import org.openiaml.model.model.domain.DomainInstance;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainType;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * @author jmwright
 *
 */
public class LoginHandlerInstanceBreadcrumb extends InferenceTestCase {
	
	private static final Class<?> TESTED_MODEL = LoginHandlerInstance.class;

	private void assertBreadcrumb(String string, EObject object) {
		assertEquals(string, IamlBreadcrumb.breadcrumb(object, 3));
	}
	
	public void testRoot() throws Exception {
		root = loadDirectly(TESTED_MODEL);
		
		assertBreadcrumb("InternetApplication", root);
	}
	
	public void testSession() throws Exception {
		root = loadDirectly(TESTED_MODEL);
		
		Session session = assertHasSession(root, "my session");
		assertBreadcrumb("InternetApplication > Session: 'my session'", session);
		
		DomainIterator iterator = assertHasDomainIterator(session, "logged in user");
		assertBreadcrumb("InternetApplication > Session: 'my session' > DomainIterator: 'logged in user'", iterator);
		
		DomainInstance instance = iterator.getCurrentInstance();
		assertBreadcrumb("... > Session: 'my session' > DomainIterator: 'logged in user' > DomainInstance: 'Current instance'", instance);
		
		DomainAttributeInstance aname = assertHasDomainAttributeInstance(instance, "name");
		assertBreadcrumb("... > DomainIterator: 'logged in user' > DomainInstance: 'Current instance' > DomainAttributeInstance: 'name'", aname);
	}

	/**
	 * Tests that domain types have names displayed.
	 * Tests issue 280.
	 * 
	 * @throws Exception
	 */
	public void testDomainTypes() throws Exception {
		root = loadDirectly(TESTED_MODEL);

		DomainType obj = assertHasDomainType(root, "User");
		assertBreadcrumb("InternetApplication > DomainType: 'User'", obj);

		DomainAttribute password = assertHasDomainAttribute(obj, "password");
		assertBreadcrumb("InternetApplication > DomainType: 'User' > DomainAttribute: 'password'", password);

	}

	
}
