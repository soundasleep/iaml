/**
 * 
 */
package org.openiaml.model.diagram.custom.helpers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.diagram.edit.parts.InternetApplicationEditPart;
import org.openiaml.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.diagram.part.IamlDiagramEditorUtil;
import org.openiaml.model.diagram.part.IamlVisualIDRegistry;
import org.openiaml.model.diagram.part.Messages;

/**
 * Contains a map of all IAML diagrams. We can use this to open
 * up arbitrary IAML diagram images (e.g. <code>.iaml_diagram</code>
 * or <code>.iaml_operation_diagram</code>) without having to
 * refer to the diagram code directly.
 * 
 * @author jmwright
 *
 */
public class DiagramRegistry {

	/**
	 * Initialise a model file from a source file.
	 * The {@link IFile#getFileExtension() model file extension} is used to select the
	 * appropriate editor.
	 *  
	 * TODO Copied from <code>org.openiaml.model.tests.eclipse.InitializeDiagramTestCase</code> - it should be placed into a separate project!!
	 *
	 * @see IFile#getFileExtension()
	 * @param modelFile must exist
	 * @param diagramFile must not exist yet
	 * @throws ExecutionException 
	 * @throws IOException 
	 * @throws PartInitException 
	 */
	public static void initializeModelFile(IFile modelFile, IFile diagramFile) throws DiagramRegistryException, ExecutionException, IOException, PartInitException {

		// pass the appropriate arguments
		Resource diagramResource = initializeModelFile(
				modelFile,
				diagramFile,
				InternetApplicationEditPart.MODEL_ID,
				InternetApplicationEditPart.VISUAL_ID,
				IamlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT,
				IamlDiagramEditorUtil.getSaveOptions(),
				Messages.IamlNewDiagramFileWizard_InitDiagramCommand,
				Messages.IamlNewDiagramFileWizard_IncorrectRootError
		);
		
		IamlDiagramEditorUtil.openDiagram(diagramResource);
		
	}
	
	protected static int getDiagramVisualID(IFile modelFile, EObject modelRoot) {
		return IamlVisualIDRegistry.getDiagramVisualID(modelRoot);
	}
	
	/**
	 * Initialise the diagram file, and return the loaded resource (before it is opened)
	 * @param iamlNewDiagramFileWizardIncorrectRootError 
	 * @param  
	 */
	protected static Resource initializeModelFile(final IFile modelFile, final IFile diagramFile,
			final String model_id, final int visual_id, final PreferencesHint prefHint, Map saveOptions, String initMessage, final String errorMessage) throws DiagramRegistryException, ExecutionException, IOException, PartInitException {
		
		if (!modelFile.exists())
			throw new IllegalArgumentException("Model file " + modelFile + " does not exist");
		if (diagramFile.exists()) 
			throw new IllegalArgumentException("Diagram file " + diagramFile + " should not exist yet");

		// initialize model file
		// based on generated IamlNewDiagramFileWizard#performFinish
		// based on generated IamlInitDiagramFileAction#run
		ResourceSet resourceSet = new ResourceSetImpl();
		URI sourceModelURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), false);
		URI diagramModelURI = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), false);
		final Resource modelResource = resourceSet.getResource(sourceModelURI, true);
		final Resource diagramResource = resourceSet.createResource(diagramModelURI);
		final EObject modelRoot = (EObject) modelResource.getContents().get(0);
		
		List<Object> affectedFiles = new LinkedList<Object>();
		affectedFiles.add(diagramFile);
		TransactionalEditingDomain myEditingDomain = GMFEditingDomainFactory.INSTANCE
			.createEditingDomain();
		
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(
				myEditingDomain,
				initMessage,
				affectedFiles) {

			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				int diagramVID = getDiagramVisualID(modelFile, modelRoot);
				if (diagramVID != visual_id) {
					return CommandResult
							.newErrorCommandResult(errorMessage);
				}
				Diagram diagram = ViewService.createDiagram(
						modelRoot,
						model_id,
						prefHint);
				diagramResource.getContents().add(diagram);
				return CommandResult.newOKCommandResult();
			}
		};
		OperationHistoryFactory.getOperationHistory().execute(command,
				new NullProgressMonitor(), null);
		diagramResource.save(saveOptions);
		
		return diagramResource;
		
	}
		
	public static class DiagramRegistryException extends Exception {

		public DiagramRegistryException(String string) {
			super(string);
		}

		private static final long serialVersionUID = 1L;

	}
	
}
