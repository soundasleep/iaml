/**
 * 
 */
package org.openiaml.model.tests.eclipse.actions;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.emf.ecore.EObject;
import org.openiaml.model.custom.actions.RewriteElementIDs;
import org.openiaml.model.model.GeneratedElement;
import org.openiaml.model.model.InternetApplication;

/**
 * Issue 86: a command to refresh all element IDs to new IDs that
 * are more meaningful.
 * 
 * @author jmwright
 *
 */
public class RewriteGeneratedIDsTest extends AbstractActionTestCase<IFile> {
	
	private IFile targetModel;

	/* (non-Javadoc)
	 * @see org.openiaml.model.tests.eclipse.AbstractModelEclipseTestCase#getModel()
	 */
	@Override
	public String getModel() {
		return "PartialInference.iaml";
	}
	
	public void copyFiles() throws Exception {
		// register errors
		addLogListener();

		// copy our local file into the project
		targetModel = getProject().getFile("PartialInference.iaml");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/actions/PartialInference.iaml",
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
		RewriteElementIDs action = new RewriteElementIDs();
		runAction(action, targetModel);
		
		// get the rewritten model
		InternetApplication model = (InternetApplication) action.getModel();
		
		// none of the contained elements should have an ID containing '.' or 'model'
		Iterator<EObject> it = model.eAllContents();
		while (it.hasNext()) {
			EObject obj = it.next();
			if (obj instanceof GeneratedElement) {
				assertFalse("Object " + obj + " contains an invalid ID", ((GeneratedElement) obj).getId().contains("."));
				assertFalse("Object " + obj + " contains an invalid ID", ((GeneratedElement) obj).getId().contains("model"));
				assertFalse("Object " + obj + " contains an invalid ID", ((GeneratedElement) obj).getId().contains("visual"));
			}
		}
		
		// also check the root element
		assertFalse("Object " + model + " contains an invalid ID", model.getId().contains("."));
		assertFalse("Object " + model + " contains an invalid ID", model.getId().contains("model"));
		assertFalse("Object " + model + " contains an invalid ID", model.getId().contains("visual"));
		
	}

}
