/**
 *
 */
package org.openiaml.model.tests.inference.model0_4;

import java.util.Set;

import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.components.LoginHandler;
import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.users.UserInstance;
import org.openiaml.model.model.wires.SelectWire;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Inference of access control handlers.
 *
 * @author jmwright
 *
 */
public class UserRolesLoginHandler extends InferenceTestCase {

	/**
	 * Test the initial model.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		root = loadDirectly(UserRolesLoginHandler.class);

		Session session = assertHasSession(root, "my session");
		assertNotGenerated(session);
		LoginHandler handler = assertHasLoginHandler(session, "user login handler");
		assertNotGenerated(handler);
		
		// no user instances
		assertHasNone(session, "iaml:children[iaml:name='current instance']");
		
	}
	
	/**
	 * There should only be one select wire created.
	 * 
	 * @throws Exception
	 */
	public void testSelectWires() throws Exception {
		root = loadAndInfer(UserRolesLoginHandler.class, true);

		Session session = assertHasSession(root, "my session");
		
		// generated
		UserInstance ui = assertHasUserInstance(session, "current instance");
		assertGenerated(ui);
		
		// there should only be one incoming select wire
		Set<WireEdge> wires = getWiresTo(session, ui, SelectWire.class);
		assertEquals(wires.toString(), 1, wires.size());		

	}
	
	/**
	 * The inferred model should be valid.
	 * 
	 * @throws Exception
	 */
	public void testInferredModelIsValid() throws Exception {
		checkModelIsValid(loadAndInfer(UserRolesLoginHandler.class));
	}
	
}
