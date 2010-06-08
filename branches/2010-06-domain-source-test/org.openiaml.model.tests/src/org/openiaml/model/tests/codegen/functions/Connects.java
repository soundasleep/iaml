/**
 * 
 */
package org.openiaml.model.tests.codegen.functions;

import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.wires.SetWire;
import org.openiaml.model.model.wires.SyncWire;

/**
 * 
 * @author jmwright
 *
 */
public class Connects extends DroolsHelperFunctionsTestCase {

	@Override
	protected Class<? extends DroolsHelperFunctionsTestCase> getTestClass() {
		return Connects.class;
	}

	public void testConnects() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		Frame p2 = assertHasFrame(root, "p2");
		Frame p3 = assertHasFrame(root, "p3");
		
		SyncWire sync1 = assertHasSyncWire(root, home, p2, "sync1");
		// bidirectional
		assertTrue(getHelper().connects(sync1, home, p2));
		assertTrue(getHelper().connects(sync1, p2, home));

		// does not connect unrelated
		assertFalse(getHelper().connects(sync1, home, p3));
		assertFalse(getHelper().connects(sync1, p3, home));
		assertFalse(getHelper().connects(sync1, home, home));	// or self
		
		// bidirectional
		SyncWire sync2 = assertHasSyncWire(root, p3, p2, "sync");
		assertTrue(getHelper().connects(sync2, p3, p2));
		assertTrue(getHelper().connects(sync2, p2, p3));
		
	}
	
	public void testConnectsSet() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		InputTextField t1 = assertHasInputTextField(home, "t1");
		InputTextField t2 = assertHasInputTextField(home, "t2");
		InputTextField t3 = assertHasInputTextField(home, "t3");
		
		SetWire set1 = assertHasSetWire(home, t1, t2, "set1");
		// unidirectional
		assertTrue(getHelper().connectsSet(set1, t1, t2));
		assertFalse(getHelper().connectsSet(set1, t2, t1));

		// unrelated
		assertFalse(getHelper().connectsSet(set1, t2, t3));
		assertFalse(getHelper().connectsSet(set1, t3, t2));
		assertFalse(getHelper().connectsSet(set1, t1, t3));
		assertFalse(getHelper().connectsSet(set1, t3, t1));
		assertFalse(getHelper().connectsSet(set1, t1, t1)); // or self
		assertFalse(getHelper().connectsSet(set1, t3, t3)); // or self
		
		SetWire set2 = assertHasSetWire(home, t2, t3, "set2");
		// unidirectional
		assertTrue(getHelper().connectsSet(set2, t2, t3));
		assertFalse(getHelper().connectsSet(set2, t3, t2));
		
	}

}
