/**
 *
 */
package org.openiaml.model.tests.codegen.model0_4_2;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Check that NavigateActions can be controlled by incoming ConditionWires,
 * making sure it works on the server too.
 *
 * @author jmwright
 */
public class NavigateConditionWireServer extends CodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(NavigateConditionWireServer.class);
	}

	/**
	 * The home page is empty.
	 *
	 * @throws Exception
	 */
	public void testHome() throws Exception {

		beginAtSitemapThenPage("Home");
		assertNoProblem();

		{
			String target = getLabelIDForText("redirect to page two?");
			assertLabeledFieldEquals(target, "");	// empty
		}

	}

	/**
	 * Try go to 'Page 1'; the condition is false, so we do not
	 * redirect to 'Page 2'.
	 *
	 * @throws Exception
	 */
	public void testInitial() throws Exception {

		beginAtSitemapThenPage("Page 1", "Page 1");
		assertNoProblem();

	}

	/**
	 *
	 * @throws Exception
	 */
	public void testCase() throws Exception {

		IFile sitemap = beginAtSitemapThenPage("Home");
		assertNoProblem();

		{
			String target = getLabelIDForText("redirect to page two?");
			assertLabeledFieldEquals(target, "");	// empty
			setLabeledFormElementField(target, "true");
		}

		gotoSitemapThenPage(sitemap, "Page 1", "Page 2");
		assertNoProblem();

	}

}
