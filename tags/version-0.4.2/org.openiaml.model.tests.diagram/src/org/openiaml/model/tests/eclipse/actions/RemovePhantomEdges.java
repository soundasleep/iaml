/**
 * 
 */
package org.openiaml.model.tests.eclipse.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openiaml.model.custom.actions.RemovePhantomEdgesAction;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.WireEdge;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.tests.eclipse.EclipseTestCaseHelper;

/**
 * Issue 63: Check the tool to remove phantom edges from our model.
 * 
 * @author jmwright
 *
 */
public class RemovePhantomEdges extends EclipseTestCaseHelper {
	
	private IFile targetModel;
	
	public void copyFiles() throws Exception {
		// register errors
		addLogListener();

		// copy our local file into the project
		targetModel = project.getFile("RemovePhantomEdges.iaml");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/actions/RemovePhantomEdges.iaml",
				targetModel);
		
		assertExists(targetModel);
	}
	
	/**
	 * Test the initial model - it should contain phantom edges.
	 * 
	 * @throws Exception
	 */
	public void testInitial() throws Exception {
		copyFiles();
		
		// try and load the file directly
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(targetModel.getFullPath().toString(), false), true);		
		InternetApplication model = (InternetApplication) resource.getContents().get(0);
		
		// get the Frame
		assertEquals(0, model.getChildren().size());
		assertEquals(1, model.getScopes().size());
		Frame page = (Frame) model.getScopes().get(0);
		
		assertEquals(3, page.getWires().size());
		
		for (WireEdge w : model.getWires()) {
			assertNotNull(w.getFrom());
			assertNull(w.getTo());
		}
	}

	/**
	 * Test the actioned model - it should no longer contain phantom edges.
	 * 
	 * @throws Exception
	 */
	public void testRemovePhantomEdges() throws Exception {
		// copy files
		copyFiles();
		
		// do the phantom edges action
		RemovePhantomEdgesAction act = new RemovePhantomEdgesAction();
		act.doExecute(targetModel, new NullProgressMonitor());

		// try and load the file directly
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(targetModel.getFullPath().toString(), false), true);		
		InternetApplication model = (InternetApplication) resource.getContents().get(0);
		
		// get the Frame
		assertEquals(0, model.getChildren().size());
		assertEquals(1, model.getScopes().size());
		Frame page = (Frame) model.getScopes().get(0);

		// no more wires
		assertEquals(0, page.getWires().size());

	}
	
}
