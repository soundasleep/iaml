/**
 * 
 */
package org.openiaml.model.tests.codegen.functions;

import org.openiaml.model.model.DynamicApplicationElementSet;
import org.openiaml.model.model.ModelFactory;
import org.openiaml.model.model.visual.Frame;

/**
 * 
 * @author jmwright
 *
 */
public class XPathMatch extends DroolsHelperFunctionsTestCase {

	@Override
	protected Class<? extends DroolsHelperFunctionsTestCase> getTestClass() {
		return XPathMatch.class;
	}
	
	public void testIsXPath() throws Exception {
		// valid xpath
		assertTrue(getHelper().isXPath("xpath:query"));
		
		// not xpath
		assertFalse(getHelper().isXPath("sql:select * from 1"));
		
		// invalid xpath is still possibly xpath
		assertTrue(getHelper().isXPath("xpath:query[1%;4[3;2p]1]"));
		
		// xpath: is in later
		assertFalse(getHelper().isXPath("sql:select xpath:* from 1"));
	}

	public void testXPathMatch() throws Exception {
		
		Frame home = assertHasFrame(root, "Home");
		Frame page1 = assertHasFrame(root, "Page 1");
		Frame page2 = assertHasFrame(root, "Page 2");
		
		// create a DynamicApplicationElementSet
		{
			DynamicApplicationElementSet ds = ModelFactory.eINSTANCE.createDynamicApplicationElementSet();
			ds.setQuery("xpath:iaml:scopes");
			
			assertTrue(getHelper().potentialXPathMatch(root, ds, home));
			assertTrue(getHelper().potentialXPathMatch(root, ds, page1));
			assertTrue(getHelper().potentialXPathMatch(root, ds, page2));
		}
		
		{
			DynamicApplicationElementSet ds = ModelFactory.eINSTANCE.createDynamicApplicationElementSet();
			ds.setQuery("xpath://iaml:scopes");
			
			assertTrue(getHelper().potentialXPathMatch(root, ds, home));
			assertTrue(getHelper().potentialXPathMatch(root, ds, page1));
			assertTrue(getHelper().potentialXPathMatch(root, ds, page2));
		}
		
		{
			DynamicApplicationElementSet ds = ModelFactory.eINSTANCE.createDynamicApplicationElementSet();
			ds.setQuery("xpath:iaml:nothing");
			
			assertFalse(getHelper().potentialXPathMatch(root, ds, home));
			assertFalse(getHelper().potentialXPathMatch(root, ds, page1));
			assertFalse(getHelper().potentialXPathMatch(root, ds, page2));
		}
		
		{
			DynamicApplicationElementSet ds = ModelFactory.eINSTANCE.createDynamicApplicationElementSet();
			ds.setQuery("xpath://iaml:scopes[iaml:name='Home']");
			
			assertTrue(getHelper().potentialXPathMatch(root, ds, home));
			// the following are still considered potential; we let the runtime decide
			assertTrue(getHelper().potentialXPathMatch(root, ds, page1));
			assertTrue(getHelper().potentialXPathMatch(root, ds, page2));
		}
		
	}
	
}
