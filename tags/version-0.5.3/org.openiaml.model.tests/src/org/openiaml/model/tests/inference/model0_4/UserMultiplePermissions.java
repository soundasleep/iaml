/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import org.openiaml.model.model.components.AccessControlHandler;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;
import org.openiaml.model.model.domain.DomainSource;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.Permission;
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
public class UserMultiplePermissions extends ValidInferenceTestCase {

	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return UserMultiplePermissions.class;
	}

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(UserMultiplePermissions.class);
		
		Session session = assertHasSession(root, "target session");
		
		Role user = assertHasRole(root, "default role");
		assertNotGenerated(user);
		
		Permission permission1 = assertHasPermission(user, "a permission");
		assertNotGenerated(permission1);
		Permission permission2 = assertHasPermission(user, "a different permission");
		assertNotGenerated(permission2);
		
		AccessControlHandler ach = assertHasAccessControlHandler(session, "role-based access");
		assertNotGenerated(ach);
		
		assertNotGenerated(assertHasRequiresEdge(root, ach, permission1));
		assertNotGenerated(assertHasRequiresEdge(root, ach, permission2));
		
		// we specify the Permissions, not the Role
		assertHasNoRequiresEdge(root, ach, user);
		
	}
	
	/**
	 * A LoginHandler is created.
	 * 
	 * @throws Exception
	 */
	public void testLoginHandlerCreated() throws Exception {
		root = loadAndInfer(UserMultiplePermissions.class);

		Session session = assertHasSession(root, "target session");
		
		Role user = assertHasRole(root, "default role");
		Permission permission1 = assertHasPermission(user, "a permission");
		Permission permission2 = assertHasPermission(user, "a different permission");
		
		AccessControlHandler ach = assertHasAccessControlHandler(session, "role-based access");

		// we specify the Permissions, not the Role
		assertHasNoRequiresEdge(root, ach, user);

		// generated default user
		Role default_user = assertHasRole(root, "User");
		assertGenerated(default_user);
		
		// is extended
		assertGenerated(assertHasExtendsEdge(root, user, default_user));

		LoginHandler handler = assertHasLoginHandler(session, "role-based login handler for target session");
		assertGenerated(handler);
		
		// the LoginHandler has an incoming User (not permissions)
		// from the default user
		assertGenerated(assertHasParameterEdge(root, default_user, handler));
		assertHasNoParameterEdge(root, permission1, handler);
		assertHasNoParameterEdge(root, permission2, handler);
		
	}
	
	/**
	 * A DomainIterator is connected, which is connected to the
	 * correct new DomainSource.
	 * 
	 * @throws Exception
	 */
	public void testDomainIteratorCreated() throws Exception {
		root = loadAndInfer(UserMultiplePermissions.class, true);

		Session session = assertHasSession(root, "target session");

		// generated default user
		Role default_user = assertHasRole(root, "User");
				
		// there is a generated instance
		DomainIterator iterator = assertHasDomainIterator(session, "current instance");
		assertGenerated(iterator);
		
		// connected by a SetWire from the LoginHandler
		LoginHandler handler = assertHasLoginHandler(session, "role-based login handler for target session");
		assertGenerated(assertHasSetWire(root, handler, iterator));
		
		// TODO which Schema should it connect to?
		
		// it must have a SelectEdge out to the
		DomainSource source = iterator.getOutSelects().get(0).getTo();
		assertGenerated(source);
		
		DomainSchema targetSchema = default_user;
		
		assertGenerated(assertHasSchemaEdge(source, targetSchema));
		assertGenerated(assertHasSelectEdge(iterator, source));
		
		// but there must only be one select
		assertEquals(1, iterator.getOutSelects().size());
		
	}

}
