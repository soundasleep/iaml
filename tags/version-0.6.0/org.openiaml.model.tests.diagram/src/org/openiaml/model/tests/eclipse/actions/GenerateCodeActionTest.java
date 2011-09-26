/**
 * 
 */
package org.openiaml.model.tests.eclipse.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.ui.browser.IWebBrowser;
import org.openiaml.model.custom.actions.GenerateCodeAction;
import org.openiaml.model.custom.actions.GenerateCodeActionAndView;

/**
 * Issue 83: Test the GenerateCodeAction action, both the normal
 * action and the "view in browser" action.
 * 
 * @author jmwright
 *
 */
public class GenerateCodeActionTest extends AbstractActionTestCase<IFile> {
	
	private IFile targetModel;

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.eclipse.AbstractModelEclipseTestCase#getModel()
	 */
	@Override
	public String getModel() {
		return "GenerateCodeActionTest.iaml";
	}
	
	public void copyFiles() throws Exception {
		// register errors
		addLogListener();

		// copy our local file into the project
		targetModel = getProject().getFile("GenerateCodeActionTest.iaml");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/actions/GenerateCodeActionTest.iaml",
				targetModel);
		
		assertExists(targetModel);
	}
	
	/**
	 * Test the code generation action.
	 * 
	 * @throws Exception
	 */
	public void testAction() throws Exception {
		// copy model
		copyFiles();
		
		IFolder outputFolder = getProject().getFolder("output");
		assertNotExists(outputFolder);
		
		// do the action
		runAction(new GenerateCodeAction(), targetModel);
		
		// the output folder should now be created
		assertExists(outputFolder);
		
		// a sitemap should have been created
		IFile sitemap = outputFolder.getFile("sitemap.html");
		assertExists(sitemap);
		
	}
	
	/**
	 * Test the code generation and view in browser action.
	 * 
	 * @throws Exception
	 */
	public void testActionAndView() throws Exception {
		// copy model
		copyFiles();
		
		IFolder outputFolder = getProject().getFolder("output");
		assertNotExists(outputFolder);
		
		// do the action		
		GenerateCodeActionAndView action = new GenerateCodeActionAndView();
		runAction(action, targetModel);
		
		// the output folder should now be created
		assertExists(outputFolder);
		
		// a sitemap should have been created
		IFile sitemap = outputFolder.getFile("sitemap.html");
		assertExists(sitemap);
		
		// and the browser window should have been created
		IWebBrowser browser = action.getCreatedBrowser();
		assertNotNull("Browser was not created", browser);
		
	}

}
