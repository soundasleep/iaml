/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_2;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Demonstrates EntryGates and ExitGates across multiple Scopes.
 *  
 * @author jmwright
 * @example EntryGate,ExitGate
 * 		Using {@model EntryGate}s and {@model ExitGate}s across
 * 		multiple {@model Scope}s.
 * @operational EntryGate
 * 		A hierarchy of {@model EntryGate}s are applied in the order from
 * 		the {@model InternetApplication root}.
 * @operational ExitGate
 * 		A hierarchy of {@model ExitGate}s are applied in the reverse order from
 * 		the {@model InternetApplication root}.
 */
public class GateMultipleSessions extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(GateMultipleSessions.class);
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
	 * We can access the external page normally.
	 * 
	 * @throws Exception
	 */
	public void testExternal() throws Exception {
		
		beginAtSitemapThenPage("To Enter the Outer Scope");
		assertNoProblem();
		
	}
	
	/**
	 * To access a page within the Outer Session, we must first
	 * view the required EntryPage.
	 * 
	 * @throws Exception
	 */
	public void testOuter() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Unrelated Outer Page", "To Enter the Outer Scope");
		assertNoProblem();
		
		// but now that we have viewed it, we can continue
		gotoSitemapThenPage(sitemap, "Unrelated Outer Page");
		assertNoProblem();
		
	}
	
	/**
	 * Test the 'Continue' button in the outer scope.
	 * 
	 * @throws Exception
	 */
	public void testOuterContinue() throws Exception {
		
		beginAtSitemapThenPage("Unrelated Outer Page", "To Enter the Outer Scope");
		assertNoProblem();
		
		// click the Continue button
		clickButtonWithText("Continue");
		assertTitleEquals("Unrelated Outer Page");		
		assertNoProblem();
		
	}
	
	/**
	 * If we access a Page in the Outer scope, we have to
	 * exit through the exit page.
	 * 
	 * @throws Exception
	 */
	public void testOuterExit() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Unrelated Outer Page", "To Enter the Outer Scope");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "Unrelated Outer Page");
		assertNoProblem();
		
		// now try and exit; we are redirected
		gotoSitemapThenPage(sitemap, "Home", "To Exit the Outer Scope");
		assertNoProblem();
		
		// but now we have visited, we can continue
		gotoSitemapThenPage(sitemap, "Home");
		assertNoProblem();
		
	}
	
	/**
	 * If we try access the inner page directly, we first have to go
	 * through the Outer gate, then the Inner gate.
	 * 
	 * @throws Exception
	 */
	public void testInner() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Inner Page", "To Enter the Outer Scope");
		assertNoProblem();
		
		// still not ready
		gotoSitemapThenPage(sitemap, "Inner Page", "To Enter the Inner Scope");
		assertNoProblem();
		
		// we can view it now!
		gotoSitemapThenPage(sitemap, "Inner Page");
		assertNoProblem();
		
		// but if we try and leave, we first have to go through the exit
		gotoSitemapThenPage(sitemap, "Unrelated Outer Page", "Out Page");
		assertNoProblem();
		
		// but now we can view it fine
		gotoSitemapThenPage(sitemap, "Unrelated Outer Page");
		assertNoProblem();
		
	}
	
	
	/**
	 * Go from Home -> Inner Page -> Home. We have to go through
	 * two levels of entry/exit.
	 * 
	 * @throws Exception
	 */
	public void testInner2() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Inner Page", "To Enter the Outer Scope");
		assertNoProblem();
		
		// still not ready
		gotoSitemapThenPage(sitemap, "Inner Page", "To Enter the Inner Scope");
		assertNoProblem();
		
		// we can view it now!
		gotoSitemapThenPage(sitemap, "Inner Page");
		assertNoProblem();
		
		// but if we try and leave, we first have to go through the exit
		gotoSitemapThenPage(sitemap, "Home", "Out Page");
		assertNoProblem();
		
		// and then again, for the previous level
		gotoSitemapThenPage(sitemap, "Home", "To Exit the Outer Scope");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "Home");
		assertNoProblem();
		
	}
	
	/**
	 * We go Home -> Inner Page, then go Home, but on the way out of
	 * Home we go back into the Inner Page. Since the 'last' requires it
	 * to only execute once, we do not have to go through the inner exit
	 * page again.
	 * 
	 * @inference ExitGate
	 * 		For {@model ExitGate}s protected by a 'last' {@model NavigateWire}, the completed
	 * 		model only requires the target of the {@model NavigateWire} to be 
	 * 		visited once.
	 * @throws Exception
	 */
	public void testInnerNavigation() throws Exception {

		IFile sitemap = beginAtSitemapThenPage("Inner Page", "To Enter the Outer Scope");
		assertNoProblem();
		
		// still not ready
		gotoSitemapThenPage(sitemap, "Inner Page", "To Enter the Inner Scope");
		assertNoProblem();
		
		// we can view it now!
		gotoSitemapThenPage(sitemap, "Inner Page");
		assertNoProblem();
		
		// but if we try and leave, we first have to go through the exit
		gotoSitemapThenPage(sitemap, "Home", "Out Page");
		assertNoProblem();
		
		// go back in
		gotoSitemapThenPage(sitemap, "Inner Page");
		assertNoProblem();
		
		// then back out, we can continue getting out		
		gotoSitemapThenPage(sitemap, "Home", "To Exit the Outer Scope");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "Home");
		assertNoProblem();
		
		// once we're out, we can go anywhere we want
		gotoSitemapThenPage(sitemap, "Inner Page");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "Home");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "To Enter the Outer Scope");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "Out Page");
		assertNoProblem();
		
		gotoSitemapThenPage(sitemap, "Home");
		assertNoProblem();
		
	}
	
	/**
	 * Tests the 'Continue' button for Inner pages.
	 * 
	 * @throws Exception
	 */
	public void testInnerContinue() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Inner Page", "To Enter the Outer Scope");
		assertNoProblem();
		
		// still not ready
		gotoSitemapThenPage(sitemap, "Inner Page", "To Enter the Inner Scope");
		assertNoProblem();
		
		// we can view it now!
		gotoSitemapThenPage(sitemap, "Inner Page");
		assertNoProblem();
		
		// but if we try and leave, we first have to go through the exit
		gotoSitemapThenPage(sitemap, "Home", "Out Page");
		assertNoProblem();
		
		clickButtonWithText("Continue");
		assertNoProblem();
		assertTitleEquals("To Exit the Outer Scope");

		clickButtonWithText("Continue");
		assertNoProblem();
		assertTitleEquals("Home");
		
	}
	
}
