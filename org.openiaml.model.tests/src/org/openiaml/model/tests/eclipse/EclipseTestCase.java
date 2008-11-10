/**
 * 
 */
package org.openiaml.model.tests.eclipse;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorUtil;
import org.openiaml.model.tests.ModelTestCase;

/**
 * Test the Eclipse editor.
 * 
 * @author jmwright
 *
 */
public class EclipseTestCase extends ModelTestCase {

	/**
	 * Create a project.
	 * 
	 * @throws CoreException
	 * @see {@link #createProject()} 
	 */
	public void setUp() throws CoreException {
		// create the project
		project = createProject();
	}
	
	/**
	 * Make sure the Eclipse UI exists and is running.
	 */
	public void testRunning() {
		assertTrue(PlatformUI.isWorkbenchRunning());
	}

	/**
	 * Test copying the model file into the workspace.
	 * 
	 * @throws Exception
	 */
	public void testLoadModel() throws Exception {
		IFile target = project.getFile("EclipseTestCase.iaml");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/EclipseTestCase.iaml",
				target);
		
		// try loading it up with Eclipse
		ResourceSet resSet = new ResourceSetImpl();          
		Resource res = resSet.getResource(URI.createPlatformResourceURI(target.getFullPath().toString(), false), true);
		IamlDiagramEditorUtil.openDiagram( res );

	}

	
}
