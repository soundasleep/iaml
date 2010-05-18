/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * SetWires operating on a remote object.
 * 
 * @implementation SetWire 
 * 		If a {@model InputTextField} is connected to another with 
 * 		a {@model SetWire} on a different page, changes in the source
 * 		InputTextField will update the target InputTextField.
 *  
 * @author jmwright
 *
 */
public class SetWireRemote extends CodegenTestCase {
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(SetWireRemote.class);
	}
	
	/**
	 * Check the initial state of the application.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("Home");
		String source = getLabelIDForText("source");
		assertLabeledFieldEquals(source, "");
		
		gotoSitemapThenPage(sitemap, "page2");
		String target = getLabelIDForText("target");
		assertLabeledFieldEquals(target, "");
	}
	
	/**
	 * Test that setting 'source' will set 'target'.
	 */
	public void testSetSourceTarget() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("Home");
		
		String test1 = "test1 " + new Date();
		String source = getLabelIDForText("source");
		assertLabeledFieldEquals(source, "");
		setLabeledFormElementField(source, test1);
		
		gotoSitemapThenPage(sitemap, "page2");
		String target = getLabelIDForText("target");
		assertLabeledFieldEquals(target, test1);
	}
	
	/**
	 * Test that setting 'target' will not set 'source'.
	 */
	public void testSetTargetNotSource() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("page2");
		
		String test1 = "test1 " + new Date();
		String target = getLabelIDForText("target");
		assertLabeledFieldEquals(target, "");
		setLabeledFormElementField(target, test1);
		
		gotoSitemapThenPage(sitemap, "Home");
		String source = getLabelIDForText("source");
		assertLabeledFieldEquals(source, "");
	}
		
}
