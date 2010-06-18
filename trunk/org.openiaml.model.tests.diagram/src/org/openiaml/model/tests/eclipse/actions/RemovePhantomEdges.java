/**
 * 
 */
package org.openiaml.model.tests.eclipse.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openiaml.model.custom.actions.RemovePhantomEdgesAction;
import org.openiaml.model.helpers.EdgeTypes;
import org.openiaml.model.model.InternetApplication;
import org.openiaml.model.model.Wire;
import org.openiaml.model.model.visual.Frame;
import org.openiaml.model.tests.eclipse.EclipseTestCaseHelper;
import org.openiaml.model.tests.model.ContainmentTestCase;

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
		targetModel = getProject().getFile("RemovePhantomEdges.iaml");
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
		assertEquals(0, model.getElements().size());
		assertEquals(1, model.getScopes().size());
		Frame page = (Frame) model.getScopes().get(0);
		
		assertEquals(3, page.getWires().size());
		
		for (Wire w : model.getWires()) {
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
		assertEquals(0, model.getElements().size());
		assertEquals(1, model.getScopes().size());
		Frame page = (Frame) model.getScopes().get(0);

		// no more wires
		assertEquals(0, page.getWires().size());

	}
	
	/**
	 * Get the first concete class for the given class. Returns <code>null</code>
	 * if none can be found.
	 * 
	 * @param cls any class (abstract or non-abstract)
	 * @return A concrete class of the given type, or <code>null</code> if none can be found
	 */
	private EClass findConcreteTypeFor(EClass cls) {
		if (!cls.isAbstract())
			return cls;
		
		for (EClass c : ContainmentTestCase.getAllClasses().keySet()) {
			if (cls.isSuperTypeOf(c)) {
				EClass r = findConcreteTypeFor(c);
				if (r != null)
					return r;
			}
		}
		
		// none found
		return null;
	}
	
	/**
	 * Test case for issue 156: make sure that all edges are covered.
	 * 
	 * @throws Exception
	 */
	public void testAllEdgeTypes() throws Exception {
		
		for (EClass typ2 : EdgeTypes.getEdgeTypes()) {
			// make concrete
			EClass typ = findConcreteTypeFor(typ2);
			
			// find the factory for it
			EFactory factory = ContainmentTestCase.getAllClasses().get(typ);
			assertNotNull("Could not find factory for '" + typ.getName() + "'", factory);
			
			// instantiate the object
			EObject obj = factory.create(typ);
			
			// the 'from' and to' should be null, so we need to remove this phantom edge
			assertTrue("We should remove type " + typ.getName() + ": " + obj, 
					RemovePhantomEdgesAction.shouldRemove(obj));
		}
		
	}
	
	/**
	 * If we try and remove the phantom edges of a non-edge, it should not be removed.
	 * 
	 * @throws Exception
	 */
	public void testNonEdgeType() throws Exception {
		
		// for every element type that _isn't_ in the EdgeTypes
		for (EClass typ2 : ContainmentTestCase.getAllClasses().keySet()) {
			// make concrete
			EClass typ = findConcreteTypeFor(typ2);
			
			// make sure it's not a subclass of a known edge
			boolean isEdge = false;
			for (EClass edge : EdgeTypes.getEdgeTypes()) {
				if (edge.isSuperTypeOf(typ))
					isEdge = true;
			}
			if (isEdge) continue;
			
			// find the factory for it
			EFactory factory = ContainmentTestCase.getAllClasses().get(typ);
			assertNotNull("Could not find factory for '" + typ.getName() + "'", factory);
			
			// instantiate the object
			EObject obj = factory.create(typ);
			
			// we should not remove this non-edge object
			assertFalse("Asking to remove non-edge object '" + typ.getName() + "' was unexpectedly successful", RemovePhantomEdgesAction.shouldRemove(obj));
		}
		
	}
	
}
