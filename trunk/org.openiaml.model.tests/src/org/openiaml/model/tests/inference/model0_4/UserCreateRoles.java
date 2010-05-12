/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import org.openiaml.model.model.components.AccessControlHandler;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSource;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.Role;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * Since we are using a LoginHandler with a User type, it should create 
 * a DomainIterator in the session called 'current instance'. This
 * DomainIterator should be connected to the AccessControlHandler; this is
 * the instance that is actually evaluated by the ACH.
 * 
 * <p>This iterator also needs to have a reference back to the original
 * Role, so we know what the Iterator actually looks like.
 *
 * @author jmwright
 *
 */
public class UserCreateRoles extends ValidInferenceTestCase {

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return UserCreateRoles.class;
	}

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(UserCreateRoles.class);
		
		Session session = assertHasSession(root, "target session");
		assertNotGenerated(session);
		AccessControlHandler ach = assertHasAccessControlHandler(session, "role-based access");
		assertNotGenerated(ach);
		
		Role role = assertHasRole(root, "default role");
		assertNotGenerated(role);
		
		// the Role is required
		assertNotGenerated(assertHasRequiresEdge(root, ach, role));
		
	}
	
	public void testCurrentInstanceCreated() throws Exception {
		root = loadAndInfer(UserCreateRoles.class);

		Session session = assertHasSession(root, "target session");
		DomainIterator cur = assertHasDomainIterator(session, "current instance");
		assertGenerated(cur);

		AccessControlHandler ach = assertHasAccessControlHandler(session, "role-based access");
		assertNotGenerated(ach);
		
		// there must be a parameter from the iterator to the ACH
		// (for the ACH to know what to check)
		assertGenerated(assertHasParameterEdge(root, cur, ach));

	}
	
	/**
	 * The created 'current instance' must have a link back to the
	 * Role that it is based on.
	 * 
	 * @throws Exception
	 */
	public void testCurrentInstanceHasReferenceToRole() throws Exception {
		root = loadAndInfer(UserCreateRoles.class);
		
		Session session = assertHasSession(root, "target session");
		DomainIterator cur = assertHasDomainIterator(session, "current instance");
		
		Role role = assertHasRole(root, "default role");
		
		// a source has been connected
		DomainSource source = cur.getOutSelects().get(0).getTo();
		// (but it's already been defined)
		assertNotGenerated(source);
		
		// there must be a link from 'cur' to the target Role
		// (so the iterator knows what type it is)
		assertGenerated(assertHasSelectEdge(cur, source));
		
		// but the edge from the source to the schema has not been generated
		assertNotGenerated(assertHasSchemaEdge(source, role));
		
	}

}
