/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4_2;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.model.components.Gate;
import org.openiaml.model.model.wires.NavigateWire;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Showing how to simplify the EntryGate requirements using
 * Model Completion.
 * 
 * @author jmwright
 * @example EntryGate 
 * 		If a {@model EntryGate} is connected by a {@model NavigateWire} called "first", 
 * 		then the target {@model Page} must be viewed at least once before proceeding.
 * @implementation EntryGate,NavigateWire
 * 		If a {@model EntryGate} is connected by a {@model NavigateWire} called "first", 
 * 		then the target {@model Page} must be viewed at least once before proceeding.
 */
public class GateRequiredPage extends CodegenTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(GateRequiredPage.class);
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
	 * If we try to visit 'Page 1', we are forced to visit
	 * the disclaimer.
	 * 
	 * @throws Exception
	 */
	public void testRequired() throws Exception {
		
		beginAtSitemapThenPage("Page 1", "Required Page");
		assertNoProblem();
		
	}
	
	/**
	 * If we try to visit 'Page 1', we have to visit the
	 * disclaimer; we can then visit 'Page 1' after this.
	 * 
	 * @throws Exception
	 */
	public void testRequiredVisit() throws Exception {
		
		IFile sitemap = beginAtSitemapThenPage("Page 1", "Required Page");
		assertTitleEquals("Required Page");
		
		// try going to Page 1 now
		gotoSitemapThenPage(sitemap, "Page 1");
		assertTitleEquals("Page 1");
		
	}
	
	/**
	 * If we get interrupted, we can resume from where the gate threw us off.
	 * 
	 * @inference Gate 
	 * 		If a {@model Gate} is protected by a "first" {@model NavigateWire},
	 * 		then the destination {@model Frame} will have a new {@model Button} created
	 * 		called "Continue"
	 * @throws Exception
	 */
	public void testRequiredResume() throws Exception {
		
		beginAtSitemapThenPage("Page 2", "Required Page");
		assertTitleEquals("Required Page");
		
		// click on the 'resume' button (generated)
		clickButtonWithText("Continue");
		assertTitleEquals("Page 2");
		
	}
	
	/**
	 * Visiting the disclaimer is limited to only the session.
	 * 
	 * @throws Exception
	 */
	public void testRequiredLimitedToSession() throws Exception {

		// first session
		testRequiredVisit();
		
		// second session
		testRequiredVisit();
		
	}
	
}
