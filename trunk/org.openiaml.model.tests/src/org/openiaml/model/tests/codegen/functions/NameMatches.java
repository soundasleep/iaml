/**
 * 
 */
package org.openiaml.model.tests.codegen.functions;

import org.openiaml.model.model.ApplicationElementProperty;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.model.visual.InputTextField;

/**
 * 
 * @author jmwright
 *
 */
public class NameMatches extends DroolsHelperFunctionsTestCase {

	@Override
	protected Class<? extends DroolsHelperFunctionsTestCase> getTestClass() {
		return NameMatches.class;
	}

	public void testNameMatches() throws Exception {
		
		Frame p1 = assertHasFrame(root, "p1");
		
		InputTextField text1_1 = (InputTextField) queryOne(p1, "iaml.visual:children[iaml:id='visual.126f8149daa.ba']");
		InputTextField text1_2 = (InputTextField) queryOne(p1, "iaml.visual:children[iaml:id='visual.126f8149daa.bc']");
		InputTextField text2 = assertHasInputTextField(p1, "text2");
		ApplicationElementProperty prop = assertHasApplicationElementProperty(p1, "text2");
		
		assertNotSame(text1_1, text1_2);
		assertNotSame(text2, prop);
		
		// same elements all match
		assertTrue(getHelper().nameMatches(text1_1, text1_1));
		assertTrue(getHelper().nameMatches(text1_2, text1_2));
		assertTrue(getHelper().nameMatches(text2, text2));
		assertTrue(getHelper().nameMatches(prop, prop));
		
		// same name between different elements
		assertTrue(getHelper().nameMatches(text1_1, text1_2));
		assertTrue(getHelper().nameMatches(text1_2, text1_1));
		assertTrue(getHelper().nameMatches(text2, prop));
		assertTrue(getHelper().nameMatches(prop, text2));
		
		// different names
		assertFalse(getHelper().nameMatches(text1_1, prop));
		assertFalse(getHelper().nameMatches(text1_2, text2));
		assertFalse(getHelper().nameMatches(text2, text1_1));
		assertFalse(getHelper().nameMatches(prop, text1_2));
		
	}
	
}
