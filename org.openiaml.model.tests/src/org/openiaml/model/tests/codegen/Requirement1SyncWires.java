/**
 * 
 */
package org.openiaml.model.tests.codegen;

import java.util.Date;

import net.sourceforge.jwebunit.api.IElement;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests requirement 1: sync wires.
 * 
 * @model ../examples/requirements/1-sync_wires.iaml 
 * @author jmwright
 *
 */
public class Requirement1SyncWires extends CodegenTestCase {
	
	protected InternetApplication root;
	
	protected void setUp() throws Exception {
		root = loadAndCodegen(ROOT + "../examples/requirements/1-sync_wires.iaml");
	}
	
	public void testRequirement() throws Exception {
		// go to sitemap
		IFile sitemap = getProject().getFile("output/sitemap.html");
		assertTrue("sitemap " + sitemap + " exists", sitemap.exists());
		
		// go to page
		beginAtSitemapThenPage(sitemap, "container");
		
		// fill in name and email
		String testName = "name " + new Date().toString();
		String testPassword = "password " + new Date().toString();
		{
			String nameId = getLabelIDForText("firstName");
			assertNotNull(nameId);
			assertLabeledFieldNotEquals(nameId, testName);
			setLabeledFormElementField(nameId, testName);
			String passId = getLabelIDForText("password");
			assertNotNull(passId);
			assertLabeledFieldNotEquals(passId, testPassword);
			setLabeledFormElementField(passId, testPassword);
		}
		
		// reload page
		gotoSitemapThenPage(sitemap, "container");
		waitForAjax();
		
		// should have changed
		{
			String nameId = getLabelIDForText("firstName");
			assertLabeledFieldEquals(nameId, testName);
			String passId = getLabelIDForText("password");
			assertLabeledFieldEquals(passId, testPassword);
		}
		
	}

	/**
	 * Assert that a given labelled field does NOT equal the current value.
	 * 
	 * TODO move into JWebUnit.
	 * 
	 * @param id
	 * @param value
	 */
	protected void assertLabeledFieldNotEquals(String id, String value) {
		IElement e = getElementById(id);
		assertNotNull(e);
		assertNotEquals(e.getAttribute("value"), value);
	}

	/**
	 * Assert that the given elements are NOT equal, i.e. !a.equals(b).
	 * 
	 * TODO move into a superclass.
	 * 
	 * @param a
	 * @param b
	 */
	private void assertNotEquals(String a, String b) {
		if (a == null) {
			assertTrue("'" + a + "' should not equal '" + b + "'", b != null);
		} else {
			assertTrue("'" + a + "' should not equal '" + b + "'", a.equals(b));
		}
	}

}
