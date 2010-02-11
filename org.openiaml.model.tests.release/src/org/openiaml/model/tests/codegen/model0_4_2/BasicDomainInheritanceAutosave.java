/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_2;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Basic tests for domain inheritance when used with autosave.
 */
public class BasicDomainInheritanceAutosave extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(BasicDomainInheritanceAutosave.class);
	}
	
	/**
	 * The home page is empty.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
	}
	
	/**
	 * If we go to the new user page, and create a user, but then reload the page,
	 * the previous information persists.
	 * 
	 * @throws Exception
	 */
	public void testNoAutosavePersists() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("New User");
		assertNoProblem();
		
		String id = "new id";
		{
			String target = getLabelIDForText("id");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, id);
		}
		
		// reload
		gotoSitemapThenPage(sitemap, "New User");
		{
			String target = getLabelIDForText("id");
			assertLabeledFieldEquals(target, id);
		}
		
	}
	
	/**
	 * Check both fields.
	 */
	public void testNoAutosavePersistsBoth() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("New User");
		assertNoProblem();
		
		String id = "new id";
		{
			String target = getLabelIDForText("id");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, id);
		}
		
		String name = "new name";
		{
			String target = getLabelIDForText("name");
			assertLabeledFieldEquals(target, "");
			setLabeledFormElementField(target, name);
		}
		
		// reload
		gotoSitemapThenPage(sitemap, "New User");
		{
			String target = getLabelIDForText("id");
			assertLabeledFieldEquals(target, id);
		}
		{
			String target = getLabelIDForText("name");
			assertLabeledFieldEquals(target, name);
		}
		
	}
	
}
