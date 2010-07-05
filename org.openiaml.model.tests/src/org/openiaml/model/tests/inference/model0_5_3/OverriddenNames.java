/**
 *
 */
package org.openiaml.model.tests.inference.model0_5_3;

import java.util.ArrayList;
import java.util.List;

import junit.framework.AssertionFailedError;

import org.openiaml.model.model.visual.Button;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;
import org.openiaml.model.model.visual.Label;
import org.openiaml.model.tests.inference.InferenceTestCase;

/**
 * Tests the "overridden names" helper property for model generation.
 * 
 * @author jmwright
 */
public class OverriddenNames extends InferenceTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndInfer(OverriddenNames.class);
	}

	/**
	 * Test the initial model.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		
		InputForm source = assertHasInputForm(home, "source");
		
		// test contents
		InputTextField a = assertHasInputTextField(source, "a");
		InputTextField b = assertHasInputTextField(source, "b");
		Button c = assertHasButton(source, "c");
		Label d = assertHasLabel(source, "d");
		InputTextField e = assertHasInputTextField(source, "e");
		assertNotGenerated(a, b, c, d, e);
		
		InputForm sync1 = assertHasInputForm(home, "sync target");
		assertHasSyncWire(root, source, sync1);
		InputForm sync2 = assertHasInputForm(home, "sync target 2");
		assertHasSyncWire(root, sync1, sync2);
		assertHasNoSyncWire(root, source, sync2);
		assertHasNoSetWire(root, source, sync1);
		
		InputForm set1 = assertHasInputForm(home, "set target");
		assertHasSetWire(root, source, set1);
		InputForm set2 = assertHasInputForm(home, "set target 2");
		assertHasSetWire(root, set1, set2);
		assertHasNoSetWire(root, source, set2);
		assertHasNoSyncWire(root, source, set1);
		
		// test the things that must be prevented
		assertCollectionEquals(sync1.getOverriddenNames(), "a", "b");
		assertCollectionEquals(sync2.getOverriddenNames(), "c", "a");
		assertCollectionEquals(set1.getOverriddenNames(), "a", "b");
		assertCollectionEquals(set2.getOverriddenNames(), "c", "a");
		
	}

	/**
	 * Tests Sync1 form.
	 * 
	 * @throws Exception
	 */
	public void testSync1() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm sync1 = assertHasInputForm(home, "sync target");
		
		// test contents
		//InputTextField a = assertHasInputTextField(sync1, "a");
		//InputTextField b = assertHasInputTextField(sync1, "b");
		//Button c = assertHasButton(sync1, "c"); - buttons aren't copied
		Label d = assertHasLabel(sync1, "d");
		InputTextField e = assertHasInputTextField(sync1, "e");
		assertGenerated(d, e);
		
		assertHasNoInputTextField(sync1, "a");
		assertHasNoInputTextField(sync1, "b");
		
		// no text fields or labels; they must be their original type
		assertHasNoInputTextField(sync1, "c");
		assertHasNoInputTextField(sync1, "d");
		assertHasNoLabel(sync1, "c");
	}
	
	/**
	 * Tests Sync2 form.
	 * 
	 * @throws Exception
	 */
	public void testSync2() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm sync2 = assertHasInputForm(home, "sync target 2");
		
		// test contents
		//InputTextField a = assertHasInputTextField(sync1, "a");
		//InputTextField b = assertHasInputTextField(sync1, "b");
		//InputTextField c = assertHasInputTextField(sync1, "c");
		Label d = assertHasLabel(sync2, "d");
		InputTextField e = assertHasInputTextField(sync2, "e");
		assertGenerated(d, e);
		
		assertHasNoInputTextField(sync2, "a");
		assertHasNoInputTextField(sync2, "b");	
		assertHasNoInputTextField(sync2, "c");	
	}
	
	/**
	 * Tests Set1 form.
	 * 
	 * @throws Exception
	 */
	public void testSet1() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm set1 = assertHasInputForm(home, "set target");
		
		// test contents
		//InputTextField a = assertHasInputTextField(sync1, "a");
		//InputTextField b = assertHasInputTextField(sync1, "b");
		//Button c = assertHasButton(set1, "c"); - buttons aren't copied
		Label d = assertHasLabel(set1, "d");
		Label e = assertHasLabel(set1, "e");
		assertGenerated(d, e);
		
		assertHasNoInputTextField(set1, "a");
		assertHasNoInputTextField(set1, "b");
		assertHasNoLabel(set1, "a");
		assertHasNoLabel(set1, "b");	
		assertHasNoLabel(set1, "c");	
	}
	
	/**
	 * Tests Set2 form.
	 * 
	 * @throws Exception
	 */
	public void testSet2() throws Exception {
		Frame home = assertHasFrame(root, "Home");
		InputForm set2 = assertHasInputForm(home, "set target 2");
		
		// test contents
		//InputTextField a = assertHasInputTextField(sync1, "a");
		//InputTextField b = assertHasInputTextField(sync1, "b");
		//InputTextField c = assertHasInputTextField(sync1, "c");
		Label d = assertHasLabel(set2, "d");
		Label e = assertHasLabel(set2, "e"); // InputTextField -> Label through a SetWire
		assertGenerated(d, e);
		
		assertHasNoInputTextField(set2, "a");
		assertHasNoInputTextField(set2, "b");	
		assertHasNoInputTextField(set2, "c");	
		assertHasNoLabel(set2, "a");
		assertHasNoLabel(set2, "b");	
		assertHasNoLabel(set2, "c");	
	}	

	/**
	 * Tests {@link #assertCollectionEquals(List, String...)}.
	 * TODO move into a separate testing framework testing class.
	 */
	public void testAssertCollectionEquals() {
		List<String> s = new ArrayList<String>();
		s.add("1");
		s.add("2");
		
		assertCollectionEquals(s, "1", "2");
		assertCollectionEquals(s, "2", "1");
		
		// the following should fail
		try {
			assertCollectionEquals(s, "1");
			throw new RuntimeException("Unexpectedly passed.");
		} catch (RuntimeException e) {
			// expected
			assertNotNull(e.getCause());
			assertTrue(e.getCause() instanceof AssertionFailedError);
		}
		
		// the following should fail
		try {
			assertCollectionEquals(s, "1", "1");
			throw new RuntimeException("Unexpectedly passed.");
		} catch (AssertionFailedError e) {
			// expected
		}

		// the following should fail
		try {
			assertCollectionEquals(s, "1", "2", "1");
			throw new RuntimeException("Unexpectedly passed.");
		} catch (RuntimeException e) {
			// expected
			assertNotNull(e.getCause());
			assertTrue(e.getCause() instanceof AssertionFailedError);
		}
		

		// the following should fail
		try {
			assertCollectionEquals(s, "0");
			throw new RuntimeException("Unexpectedly passed.");
		} catch (RuntimeException e) {
			// expected
			assertNotNull(e.getCause());
			assertTrue(e.getCause() instanceof AssertionFailedError);
		}
		

	}
	
}
