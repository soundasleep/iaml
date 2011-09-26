/**
 * 
 */
package org.openiaml.model.tests.codegen.model0_5_3;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.openiaml.model.tests.CodegenTestCase;

/**
 * Tests custom header and footer includes.
 * 
 */
public class CustomHeaderFooter extends CodegenTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		root = loadAndCodegen(getClass());
	}

	/**
	 * Test the custom header.
	 * 
	 * @throws Exception 
	 */
	public void testHeader() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// there is no such label "hello, world!"
		assertLabelTextNotPresent("Hello, world!");
		
		// a custom header
		File sourceFile = new File( ROOT + "codegen/model0_5_3/custom_header.php" );
		IFile targetFile = getProject().getFile("templates/header/default.php");
		copyFileIntoWorkspace(sourceFile, targetFile);

		// visit again
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// the label is now present
		assertLabelTextPresent("Hello, world!");
	}
	
	/**
	 * Test the custom footer.
	 * 
	 * @throws Exception 
	 */
	public void testFooter() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// there is no such label "goodbye, world!"
		assertLabelTextNotPresent("Goodbye, world!");
		
		// a custom header
		File sourceFile = new File( ROOT + "codegen/model0_5_3/custom_footer.php" );
		IFile targetFile = getProject().getFile("templates/footer/default.php");
		copyFileIntoWorkspace(sourceFile, targetFile);

		// visit again
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// the label is now present
		assertLabelTextPresent("Goodbye, world!");
	}
	
	/**
	 * Test the custom meta include.
	 * 
	 * @throws Exception 
	 */
	public void testMeta() throws Exception {
		// page is initially titled 'Home'
		beginAtSitemapThenPage("Home", "Home");
		assertNoProblem();
		
		// a custom header
		File sourceFile = new File( ROOT + "codegen/model0_5_3/custom_meta.php" );
		IFile targetFile = getProject().getFile("templates/meta/default.php");
		copyFileIntoWorkspace(sourceFile, targetFile);

		// title is now uppercase
		beginAtSitemapThenPage("Home", "HOME");
		assertNoProblem();
	}	
	
	/**
	 * Test the specific custom header.
	 * 
	 * @throws Exception 
	 */
	public void testHeaderSpecific() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// there is no such label "hello, world!"
		assertLabelTextNotPresent("Hello, world!");
		
		// a custom header
		File sourceFile = new File( ROOT + "codegen/model0_5_3/custom_header.php" );
		IFile targetFile = getProject().getFile("templates/header/index.php");
		copyFileIntoWorkspace(sourceFile, targetFile);

		// visit again
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// the label is now present
		assertLabelTextPresent("Hello, world!");
	}
	
	/**
	 * Test the specific custom footer.
	 * 
	 * @throws Exception 
	 */
	public void testFooterSpecific() throws Exception {
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// there is no such label "goodbye, world!"
		assertLabelTextNotPresent("Goodbye, world!");
		
		// a custom header
		File sourceFile = new File( ROOT + "codegen/model0_5_3/custom_footer.php" );
		IFile targetFile = getProject().getFile("templates/footer/index.php");
		copyFileIntoWorkspace(sourceFile, targetFile);

		// visit again
		beginAtSitemapThenPage("Home");
		assertNoProblem();
		
		// the label is now present
		assertLabelTextPresent("Goodbye, world!");
	}
	
	/**
	 * Test the specific custom meta include.
	 * 
	 * @throws Exception 
	 */
	public void testMetaSpecific() throws Exception {
		// page is initially titled 'Home'
		beginAtSitemapThenPage("Home", "Home");
		assertNoProblem();
		
		// a custom header
		File sourceFile = new File( ROOT + "codegen/model0_5_3/custom_meta.php" );
		IFile targetFile = getProject().getFile("templates/meta/index.php");
		copyFileIntoWorkspace(sourceFile, targetFile);

		// title is now uppercase
		beginAtSitemapThenPage("Home", "HOME");
		assertNoProblem();
	}	
	
}
