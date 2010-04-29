/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_3;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;
import org.openiaml.model.tests.PhpRuntimeExceptionException;

/**
 * When a DomainObjectInstance is connected to a Form by a
 * SetWire, Labels are created, allowing the object to be <em>viewed</em>
 * but not saved.
 * 
 * @author jmwright
 * @example DomainObjectInstance
 * 		Using model completion of {@model Label}s to let a {@model DomainObjectInstance} 
 * 		be viewed, but not edited.
 */
public class DomainInstanceSetWire extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(DomainInstanceSetWire.class);
	}
	
	/**
	 * The home page <em>cannot</em> be accessed (we need
	 * a query parameter).
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		
		try {
			beginAtSitemapThenPage("Home");
			fail("Should not have been able to access Home page");
		} catch (PhpRuntimeExceptionException e) {
			// expected
		}
		
	}
	
	/**
	 * We can create a new product.
	 * 
	 * @throws Exception
	 */
	public void testCreateProduct() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Add New Product");
		assertNoProblem();
		
		String id = "123";
		String name = "Product Name";
		String price = "123.45";
		
		{
			String target = getLabelIDForText("id");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, id);
		}
		{
			String target = getLabelIDForText("name");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, name);
		}
		{
			String target = getLabelIDForText("price");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, price);
		}
		
		// the object is not saved yet; we have to click the button
		assertButtonPresentWithText("save");
		clickButtonWithText("save");
		
		// we can now go to the home page with the query parameter
		gotoSitemapThenPage(sitemap, "Home", "Home", "id=123");
		assertNoProblem();
		
		// there will be three labels
		assertLabelTextPresent(id, price);
		assertLabelTextPresent(name);
		assertLabelTextPresent(price);
		
	}
	
}
