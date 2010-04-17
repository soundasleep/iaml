/**
 * 
 */
package org.openiaml.model.tests.codegen.functions;

import org.openiaml.model.model.scopes.Session;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputForm;
import org.openiaml.model.model.visual.InputTextField;

/**
 * 
 * @author jmwright
 *
 */
public class ContainingSession extends DroolsHelperFunctionsTestCase {

	@Override
	protected Class<? extends DroolsHelperFunctionsTestCase> getTestClass() {
		return ContainingSession.class;
	}

	public void testContainingSession() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		Session s1 = assertHasSession(root, "s1");
		Session s2 = assertHasSession(root, "s2");
		
		InputForm form1 = assertHasInputForm(home, "form1");
		
		Frame f1 = assertHasFrame(s1, "f1");
		
		Frame f2 = assertHasFrame(s2, "f2");
		Frame f3 = assertHasFrame(f2, "f3");
		InputTextField t1 = assertHasInputTextField(f3, "t1");
		
		assertEquals(null, getHelper().containingSession(home));
		assertEquals(null, getHelper().containingSession(form1));
		assertEquals(s1, getHelper().containingSession(f1));
		assertEquals(s2, getHelper().containingSession(f2));
		assertEquals(s2, getHelper().containingSession(f3));
		assertEquals(s2, getHelper().containingSession(t1));
		
		// sessions: they do not contain themselves
		assertEquals(null, getHelper().containingSession(s1));
		assertEquals(null, getHelper().containingSession(s2));
		
		
	}
	
	public void testContainingSessionEquals() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		Session s1 = assertHasSession(root, "s1");
		Session s2 = assertHasSession(root, "s2");
		
		InputForm form1 = assertHasInputForm(home, "form1");
		
		Frame f1 = assertHasFrame(s1, "f1");
		
		Frame f2 = assertHasFrame(s2, "f2");
		Frame f3 = assertHasFrame(f2, "f3");
		InputTextField t1 = assertHasInputTextField(f3, "t1");
		
		// test containingSessionEquals
		assertTrue(getHelper().containingSessionEquals(f1, s1));
		assertTrue(getHelper().containingSessionEquals(f2, s2));
		assertTrue(getHelper().containingSessionEquals(f3, s2));
		assertTrue(getHelper().containingSessionEquals(t1, s2));

		assertFalse(getHelper().containingSessionEquals(f1, s2));
		assertFalse(getHelper().containingSessionEquals(f2, s1));
		assertFalse(getHelper().containingSessionEquals(f3, s1));
		assertFalse(getHelper().containingSessionEquals(t1, s1));
		
		// null
		assertFalse(getHelper().containingSessionEquals(form1, s1));
		assertFalse(getHelper().containingSessionEquals(form1, s2));		
		
	}
	
	public void sessionContains() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		Session s1 = assertHasSession(root, "s1");
		Session s2 = assertHasSession(root, "s2");
		
		InputForm form1 = assertHasInputForm(home, "form1");
		
		Frame f1 = assertHasFrame(s1, "f1");
		
		Frame f2 = assertHasFrame(s2, "f2");
		Frame f3 = assertHasFrame(f2, "f3");
		InputTextField t1 = assertHasInputTextField(f3, "t1");
		
		// test containingSessionEquals
		assertTrue(getHelper().sessionContains(s1, f1));
		assertTrue(getHelper().sessionContains(s2, f2));
		assertTrue(getHelper().sessionContains(s2, f3));
		assertTrue(getHelper().sessionContains(s2, t1));

		assertFalse(getHelper().sessionContains(s2, f1));
		assertFalse(getHelper().sessionContains(s1, f2));
		assertFalse(getHelper().sessionContains(s1, f3));
		assertFalse(getHelper().sessionContains(s1, t1));
		
		// null
		assertFalse(getHelper().sessionContains(s1, form1));
		assertFalse(getHelper().sessionContains(s2, form1));		
		
	}
	

}
