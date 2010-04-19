/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_1;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * 
 * @example ParameterEdge,NavigateAction
 *		A {@model ParameterEdge} can connect to a {@model NavigateWire} in order to
 *		provide named {@model QueryParameter}s to the destination {@model Frame}. 
 */
public class NavigateWithParameter extends CodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(NavigateWithParameter.class);
	}

	/**
	 * The home page can be accessed.
	 * 
	 * @throws Exception 
	 */
	public void testHome() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
	}
	
	/**
	 * We can't access the 'target' page with no query parameter. 
	 * @throws Exception
	 */
	public void testTargetFailure() throws Exception {
		beginAtSitemapThenPage("Target");
		assertProblem();
	}
	
	/**
	 * However, if we access and provide a 'parameter' argument manually,
	 * we can access.
	 * @throws Exception
	 */
	public void testTargetManually() throws Exception {
		IFile sitemap = getSitemap();
		beginAtSitemapThenPage(sitemap, "Target", "Target", "select=three");
		
		{
			String target = getLabelIDForText("parameter:");
			assertLabeledFieldEquals(target, "three");
		}
	}
	
	/**
	 * Implicit 'onClick'.
	 * @throws Exception
	 */
	public void testButtonOne() throws Exception {
		beginAtSitemapThenPage("Home");
		
		clickButtonWithText("one");
		assertTitleEquals("Target");
		
		{
			String target = getLabelIDForText("parameter:");
			assertLabeledFieldEquals(target, "one");
		}
	}
	
	/**
	 * Explicit onClick->Navigate
	 * @throws Exception
	 */
	public void testButtonTwo() throws Exception {
		beginAtSitemapThenPage("Home");
		
		clickButtonWithText("two");
		assertTitleEquals("Target");
		
		{
			String target = getLabelIDForText("parameter:");
			assertLabeledFieldEquals(target, "two");
		}
	}
	
	/**
	 * A wire with no parameter throws an exception
	 * @throws Exception
	 */
	public void testButtonNone() throws Exception {
		beginAtSitemapThenPage("Home");
		
		clickButtonWithText("none");
		
		assertProblem();
	}
	
	/**
	 * A wire with the wrong parameter name throws an exception
	 * @throws Exception
	 */
	public void testButtonWrong() throws Exception {
		beginAtSitemapThenPage("Home");
		
		clickButtonWithText("wrong param name");
		
		assertProblem();
	}
	
	
}
