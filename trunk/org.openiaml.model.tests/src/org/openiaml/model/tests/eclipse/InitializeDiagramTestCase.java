/**
 * 
 */
package org.openiaml.model.tests.eclipse;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.model.DomainStore;
import org.openiaml.model.model.diagram.edit.parts.InternetApplicationEditPart;
import org.openiaml.model.model.diagram.part.IamlDiagramEditor;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorUtil;
import org.openiaml.model.model.diagram.part.IamlInitDiagramFileAction;
import org.openiaml.model.model.diagram.part.IamlNewDiagramFileWizard;
import org.openiaml.model.model.diagram.part.IamlVisualIDRegistry;
import org.openiaml.model.model.diagram.part.Messages;
import org.openiaml.model.model.visual.Page;
import org.openiaml.model.tests.ModelTestCase;

/**
 * Emulate right click > initialise diagram.
 * 
 * @author jmwright
 *
 */
public class InitializeDiagramTestCase extends ModelTestCase {

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
	 * Tests loading the model file with the editor.
	 * 
	 * @throws Exception
	 */
	public void testLoadModel() throws Exception {
		// copy our local file into the project
		IFile targetModel = project.getFile("shortcuts-root.iaml");
		copyFileIntoWorkspace("src/org/openiaml/model/tests/eclipse/shortcuts-root.iaml",
				targetModel);
		
		IFile targetDiagram = project.getFile("shortcuts-root.iaml_diagram");
		assertFalse("the target diagram should not exist yet", targetDiagram.exists());

		// initialise the model
		initializeModelFile(targetModel, targetDiagram);
		
		assertTrue("the target diagram should have been created", targetDiagram.exists());
		
		IEditorPart ep = loadDiagramFile(targetDiagram);
		
		// if this is actually an ErrorEditPart, then an error has occured 
		// (but it may not be obvious in the log what it is)
		assertTrue("active editor is our plugin, but is " + ep, ep instanceof IamlDiagramEditor);
		
		// find what elements are displayed
		IamlDiagramEditor editor = (IamlDiagramEditor) ep;

		// there should be four children
		assertEquals("there should only be 4 children", 4, editor.getDiagramEditPart().getChildren().size());
		
		ShapeNodeEditPart page1 = assertHasRootPage(editor, "page1");
		ShapeNodeEditPart page2 = assertHasRootPage(editor, "page2");
		ShapeNodeEditPart store = assertHasRootDomainStore(editor, "store");
		ShapeNodeEditPart page4 = assertHasRootPage(editor, "page4");
		
		IEditorPart ep2 = openDiagram(store);

		// if this is actually an ErrorEditPart, then an error has occured 
		// (but it may not be obvious in the log what it is)
		assertTrue("active editor is our plugin, but is " + ep2, ep2 instanceof IamlDiagramEditor);

	}
	
	/**
	 * Try opening a sub-diagram.
	 * based on org.eclipse.gef.tools.SelectEditPartTracker#performOpen()
	 * 
	 * @see org.eclipse.gef.tools.SelectEditPartTracker#performOpen()
	 * @param sourcePart
	 * @return
	 */
	protected IEditorPart openDiagram(ShapeNodeEditPart sourcePart) throws Exception {

		// based on org.eclipse.gef.tools.SelectEditPartTracker#performOpen()
		SelectionRequest request = new SelectionRequest();
		request.setLocation(sourcePart.getLocation());
		request.setModifiers(0 /*getCurrentInput().getModifiers()*/);
		request.setType(RequestConstants.REQ_OPEN);
		sourcePart.performRequest(request);

		// we should have loaded up a new editor
		IWorkbenchPage page9 = PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage();
		IEditorPart ep2 = page9.getActiveEditor();
		assertNotNull("has active editor part", ep2);
		
		return ep2;
		
	}
	
	/**
	 * Look at the editor's children to see if a Page is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	protected ShapeNodeEditPart assertHasRootPage(IamlDiagramEditor root, String pageName) {
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof Page) {
					Page p = (Page) obj;
					if (p.getName().equals(pageName))
						return s;
				}
			}
		}
		// failed
		fail("assertHasRootPage: no page '" + pageName + "' found.");
		return null;
	}

	/**
	 * Look at the editor's children to see if a Domain Store is being displayed.
	 * 
	 * @param root
	 * @param pageName
	 * @return
	 */
	protected ShapeNodeEditPart assertHasRootDomainStore(IamlDiagramEditor root, String storeName) {
		for (Object o : root.getDiagramEditPart().getChildren()) {
			if (o instanceof ShapeNodeEditPart) {
				ShapeNodeEditPart s = (ShapeNodeEditPart) o;
				EObject obj = s.resolveSemanticElement();
				if (obj instanceof DomainStore) {
					DomainStore p = (DomainStore) obj;
					if (p.getName().equals(storeName))
						return s;
				}
			}
		}
		// failed
		fail("assertHasRootDomainStore: no domain store '" + storeName + "' found.");
		return null;
	}
	
	
	/**
	 * Initialise a model file from a source file.
	 * 
	 * @see IamlNewDiagramFileWizard#performFinish
	 * @see IamlInitDiagramFileAction#run
	 * @param modelFile must exist
	 * @param diagramFile must not exist yet
	 */
	protected void initializeModelFile(IFile modelFile, IFile diagramFile) throws Exception {
		assertTrue("model file exists", modelFile.exists());
		assertFalse("diagram file doesn't exist", diagramFile.exists());

		// initialize model file
		// based on generated IamlNewDiagramFileWizard#performFinish
		// based on generated IamlInitDiagramFileAction#run
		ResourceSet resourceSet = new ResourceSetImpl();
		URI sourceModelURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), false);
		URI diagramModelURI = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), false);
		final Resource modelResource = resourceSet.getResource(sourceModelURI, true);
		final Resource diagramResource = resourceSet.createResource(diagramModelURI);
		final EObject modelRoot = (EObject) modelResource.getContents().get(0);
		
		List affectedFiles = new LinkedList();
		affectedFiles.add(diagramFile);
		TransactionalEditingDomain myEditingDomain = GMFEditingDomainFactory.INSTANCE
			.createEditingDomain();
		
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(
				myEditingDomain,
				Messages.IamlNewDiagramFileWizard_InitDiagramCommand,
				affectedFiles) {

			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				int diagramVID = IamlVisualIDRegistry
						.getDiagramVisualID(modelRoot);
				if (diagramVID != InternetApplicationEditPart.VISUAL_ID) {
					return CommandResult
							.newErrorCommandResult(Messages.IamlNewDiagramFileWizard_IncorrectRootError);
				}
				Diagram diagram = ViewService.createDiagram(
						modelRoot,
						InternetApplicationEditPart.MODEL_ID,
						IamlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				diagramResource.getContents().add(diagram);
				return CommandResult.newOKCommandResult();
			}
		};
		OperationHistoryFactory.getOperationHistory().execute(command,
				new NullProgressMonitor(), null);
		diagramResource.save(IamlDiagramEditorUtil.getSaveOptions());
		IamlDiagramEditorUtil.openDiagram(diagramResource);
		// end
	}

	/**
	 * Load a diagram file in Eclipse. Makes sure that an active editor part is loaded.
	 * 
	 * @param diagramFile
	 * @returns the active editor part
	 * @throws Exception
	 */
	protected IEditorPart loadDiagramFile(IFile diagramFile) throws Exception {
		// try loading it up with Eclipse
		ResourceSet resSet = new ResourceSetImpl();          
		Resource res = resSet.getResource(URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), false), true);
		assertTrue("can load new editor", IamlDiagramEditorUtil.openDiagram( res ));
		
		// get the active workbench editor part
		// based on IamlDiagramEditorUtil#openDiagram()
		IWorkbenchPage page = PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage();
		IEditorPart ep = page.getActiveEditor();
		assertNotNull("has active editor part", ep);
		
		return ep;

	}
	
}
