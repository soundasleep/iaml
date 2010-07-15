/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.tests.inference.ValidInferenceTestCase;

/**
 * 
 * 
 * @author jmwright
 */
public class MultipleAccessControlHandlers extends ValidInferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(MultipleAccessControlHandlers.class);
	}

	/**
	 * There should only be six sessions (i.e. three created).
	 * 
	 * @throws Exception
	 */
	public void testOnlyThreeSessionsCreated() throws Exception {
		assertEquals(6, typeSelect(root.getScopes(), Session.class).size());
		
		// all the sessions must have unique names
		List<?> children = typeSelect(root.getScopes(), Session.class);
		Set<String> foundNames = new HashSet<String>();
		for (Object obj : children) {
			Session sess = (Session) obj;
			assertFalse(foundNames + " should not contain " + sess.getName(), foundNames.contains(sess.getName()));
			foundNames.add(sess.getName());
		}
		
		assertFalse(foundNames.isEmpty());
	}
	
	/**
	 * Otherwise, most of this test case is focused on the fact that
	 * the model is valid through the checks.
	 */
	@Override
	public Class<? extends ValidInferenceTestCase> getInferenceClass() {
		return getClass();
	}
	
}
