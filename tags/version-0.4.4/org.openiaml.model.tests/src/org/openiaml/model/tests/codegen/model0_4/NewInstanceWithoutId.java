/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_4;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * If a domain object instance is created through a New Instance wire
 * without the DomainObject having an 'id' element created, then
 * an 'id' element should automatically be created and the
 * instance can be edited.
 * 
 * Since this DomainObjectInstance is stored in a page, there
 * is only ever one instance of the object created in the application.
 * 
 * Tests issue 65.
 * 
 * @author jmwright
 *
 */
public class NewInstanceWithoutId extends CodegenTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(NewInstanceWithoutId.class);
	}
	
	/**
	 * We should be able to access the page normally.
	 */
	public void testCanAccessContainerPage() throws Exception {
		IFile sitemap = beginAtSitemapThenPage("container");
		assertNoProblem();
		
		// there should be a 'name' attribute generated
		String name = getLabelIDForText("name");
		assertLabeledFieldEquals(name, "");
		
		// lets change it
		String testValue = "test " + new Date();
		setLabeledFormElementField(name, testValue);
		assertLabeledFieldEquals(name, testValue);
		
		// reload page
		reloadPage(sitemap, "container");
		
		// it should have remained
		name = getLabelIDForText("name");
		assertLabeledFieldEquals(name, testValue);
	}

}
