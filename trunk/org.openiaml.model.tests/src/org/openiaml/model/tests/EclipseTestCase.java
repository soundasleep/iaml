/**
 * 
 */
package org.openiaml.model.tests;

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
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.model.diagram.edit.parts.InternetApplicationEditPart;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorUtil;
import org.openiaml.model.model.diagram.part.IamlInitDiagramFileAction;
import org.openiaml.model.model.diagram.part.IamlNewDiagramFileWizard;
import org.openiaml.model.model.diagram.part.IamlVisualIDRegistry;
import org.openiaml.model.model.diagram.part.Messages;

/**
 * Eclipse testing functionality.
 * 
 * @author jmwright
 *
 */
public abstract class EclipseTestCase extends ModelTestCase {

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
	 * Try opening a sub-diagram.
	 * based on org.eclipse.gef.tools.SelectEditPartTracker#performOpen()
	 * 
	 * @see org.eclipse.gef.tools.SelectEditPartTracker#performOpen()
	 * @param sourcePart
	 * @return
	 */
	protected DiagramDocumentEditor openDiagram(ShapeNodeEditPart sourcePart) throws Exception {

		// based on org.eclipse.gef.tools.SelectEditPartTracker#performOpen()
		SelectionRequest request = new SelectionRequest();
		request.setLocation(sourcePart.getLocation());
		request.setModifiers(0 /*getCurrentInput().getModifiers()*/);
		request.setType(RequestConstants.REQ_OPEN);
		sourcePart.performRequest(request);

		// we should have loaded up a new editor
		IWorkbenchPage activePage = PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage();
		IEditorPart editor = activePage.getActiveEditor();
		assertNotNull("has active editor part", editor);
		
		assertTrue("active editor is not an DiagramDocumentEditor, but is a " + editor, editor instanceof DiagramDocumentEditor);
		
		return (DiagramDocumentEditor) editor;
		
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
