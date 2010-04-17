/**
 * 
 */
package org.openiaml.model.diagram.custom.helpers;

import java.io.IOException;
import java.util.HashMap;
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
import org.eclipse.ui.PartInitException;

/**
 * Contains a map of all IAML diagrams. We can use this to open
 * up arbitrary IAML diagram images (e.g. <code>.iaml_diagram</code>
 * or <code>.iaml_operation_diagram</code>) without having to
 * refer to the diagram code directly.
 * 
 * <p>TODO unless this is used by end-user code, this should be moved
 * into a development-only plugin; currently it is only used by modeldoc
 * and diagram tests.
 * 
 * @author jmwright
 *
 */
public class DiagramRegistry {
	
	/**
	 * Contains all of the necessary information for a given 
	 * diagram editor.
	 * 
	 */
	private abstract static class IamlDiagramRegistryOptions {

		public String modelId;
		public int visualId;
		public PreferencesHint prefHint;
		public Map<?,?> saveOptions;
		public String initMessage;
		public String errorMessage;

		public IamlDiagramRegistryOptions(String modelId, int visualId,
				PreferencesHint prefHint, Map<?,?> saveOptions,
				String initMessage,
				String errorMessage) {
			this.modelId = modelId;
			this.visualId = visualId;
			this.prefHint = prefHint;
			this.saveOptions = saveOptions;
			this.initMessage = initMessage;
			this.errorMessage = errorMessage;
		}

		public abstract int getDiagramVisualID(IFile modelFile, EObject modelRoot);

		/**
		 * Open the given diagram resource.
		 * @throws PartInitException 
		 */
		public abstract void openDiagram(Resource diagramResource) throws PartInitException;
		
	}
	
	/**
	 * Initialise all of the editors.
	 * 
	 * @return
	 */
	protected static Map<String,IamlDiagramRegistryOptions> getEditors() {
		Map<String,IamlDiagramRegistryOptions> editors = new HashMap<String,IamlDiagramRegistryOptions>();
		
		editors.put("iaml", new IamlDiagramRegistryOptions(
				org.openiaml.model.diagram.edit.parts.InternetApplicationChangePart.MODEL_ID,
				org.openiaml.model.diagram.edit.parts.InternetApplicationChangePart.VISUAL_ID,
				org.openiaml.model.diagram.part.IamlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT,
				org.openiaml.model.diagram.part.IamlDiagramEditorUtil.getSaveOptions(),
				org.openiaml.model.diagram.part.Messages.IamlNewDiagramFileWizard_InitDiagramCommand,
				org.openiaml.model.diagram.part.Messages.IamlNewDiagramFileWizard_IncorrectRootError) {

					@Override
					public void openDiagram(Resource diagramResource) throws PartInitException {						
						org.openiaml.model.diagram.part.IamlDiagramEditorUtil.openDiagram(diagramResource);
					}
					
					@Override
					public int getDiagramVisualID(IFile modelFile, EObject modelRoot) {
						return org.openiaml.model.diagram.part.IamlVisualIDRegistry.getDiagramVisualID(modelRoot);
					}
			
		});

		editors.put("iaml_condition", new IamlDiagramRegistryOptions(
				org.openiaml.model.diagram.condition.edit.parts.CompositeConditionChangePart.MODEL_ID,
				org.openiaml.model.diagram.condition.edit.parts.CompositeConditionChangePart.VISUAL_ID,
				org.openiaml.model.diagram.condition.part.IamlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT,
				org.openiaml.model.diagram.condition.part.IamlDiagramEditorUtil.getSaveOptions(),
				org.openiaml.model.diagram.condition.part.Messages.IamlNewDiagramFileWizard_InitDiagramCommand,
				org.openiaml.model.diagram.condition.part.Messages.IamlNewDiagramFileWizard_IncorrectRootError) {

					@Override
					public void openDiagram(Resource diagramResource) throws PartInitException {						
						org.openiaml.model.diagram.condition.part.IamlDiagramEditorUtil.openDiagram(diagramResource);
					}
					
					@Override
					public int getDiagramVisualID(IFile modelFile, EObject modelRoot) {
						return org.openiaml.model.diagram.condition.part.IamlVisualIDRegistry.getDiagramVisualID(modelRoot);
					}
			
		});

		editors.put("iaml_domain_object", new IamlDiagramRegistryOptions(
				org.openiaml.model.diagram.domain_object.edit.parts.DomainObjectEditPart.MODEL_ID,
				org.openiaml.model.diagram.domain_object.edit.parts.DomainObjectEditPart.VISUAL_ID,
				org.openiaml.model.diagram.domain_object.part.IamlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT,
				org.openiaml.model.diagram.domain_object.part.IamlDiagramEditorUtil.getSaveOptions(),
				org.openiaml.model.diagram.domain_object.part.Messages.IamlNewDiagramFileWizard_InitDiagramCommand,
				org.openiaml.model.diagram.domain_object.part.Messages.IamlNewDiagramFileWizard_IncorrectRootError) {

					@Override
					public void openDiagram(Resource diagramResource) throws PartInitException {						
						org.openiaml.model.diagram.domain_object.part.IamlDiagramEditorUtil.openDiagram(diagramResource);
					}
					
					@Override
					public int getDiagramVisualID(IFile modelFile, EObject modelRoot) {
						return org.openiaml.model.diagram.domain_object.part.IamlVisualIDRegistry.getDiagramVisualID(modelRoot);
					}
			
		});
		
		editors.put("iaml_domain_object_instance", new IamlDiagramRegistryOptions(
				org.openiaml.model.diagram.domain_object_instance.edit.parts.DomainObjectInstanceEditPart.MODEL_ID,
				org.openiaml.model.diagram.domain_object_instance.edit.parts.DomainObjectInstanceEditPart.VISUAL_ID,
				org.openiaml.model.diagram.domain_object_instance.part.IamlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT,
				org.openiaml.model.diagram.domain_object_instance.part.IamlDiagramEditorUtil.getSaveOptions(),
				org.openiaml.model.diagram.domain_object_instance.part.Messages.IamlNewDiagramFileWizard_InitDiagramCommand,
				org.openiaml.model.diagram.domain_object_instance.part.Messages.IamlNewDiagramFileWizard_IncorrectRootError) {

					@Override
					public void openDiagram(Resource diagramResource) throws PartInitException {						
						org.openiaml.model.diagram.domain_object_instance.part.IamlDiagramEditorUtil.openDiagram(diagramResource);
					}
					
					@Override
					public int getDiagramVisualID(IFile modelFile, EObject modelRoot) {
						return org.openiaml.model.diagram.domain_object_instance.part.IamlVisualIDRegistry.getDiagramVisualID(modelRoot);
					}
			
		});
		
		editors.put("iaml_domain_store", new IamlDiagramRegistryOptions(
				org.openiaml.model.diagram.domain_store.edit.parts.DomainStoreEditPart.MODEL_ID,
				org.openiaml.model.diagram.domain_store.edit.parts.DomainStoreEditPart.VISUAL_ID,
				org.openiaml.model.diagram.domain_store.part.IamlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT,
				org.openiaml.model.diagram.domain_store.part.IamlDiagramEditorUtil.getSaveOptions(),
				org.openiaml.model.diagram.domain_store.part.Messages.IamlNewDiagramFileWizard_InitDiagramCommand,
				org.openiaml.model.diagram.domain_store.part.Messages.IamlNewDiagramFileWizard_IncorrectRootError) {

					@Override
					public void openDiagram(Resource diagramResource) throws PartInitException {						
						org.openiaml.model.diagram.domain_store.part.IamlDiagramEditorUtil.openDiagram(diagramResource);
					}
					
					@Override
					public int getDiagramVisualID(IFile modelFile, EObject modelRoot) {
						return org.openiaml.model.diagram.domain_store.part.IamlVisualIDRegistry.getDiagramVisualID(modelRoot);
					}
			
		});
		
		editors.put("iaml_frame", new IamlDiagramRegistryOptions(
				org.openiaml.model.diagram.frame.edit.parts.FrameEditPart.MODEL_ID,
				org.openiaml.model.diagram.frame.edit.parts.FrameEditPart.VISUAL_ID,
				org.openiaml.model.diagram.frame.part.IamlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT,
				org.openiaml.model.diagram.frame.part.IamlDiagramEditorUtil.getSaveOptions(),
				org.openiaml.model.diagram.frame.part.Messages.IamlNewDiagramFileWizard_InitDiagramCommand,
				org.openiaml.model.diagram.frame.part.Messages.IamlNewDiagramFileWizard_IncorrectRootError) {

					@Override
					public void openDiagram(Resource diagramResource) throws PartInitException {						
						org.openiaml.model.diagram.frame.part.IamlDiagramEditorUtil.openDiagram(diagramResource);
					}
					
					@Override
					public int getDiagramVisualID(IFile modelFile, EObject modelRoot) {
						return org.openiaml.model.diagram.frame.part.IamlVisualIDRegistry.getDiagramVisualID(modelRoot);
					}
			
		});
		
		editors.put("iaml_operation", new IamlDiagramRegistryOptions(
				org.openiaml.model.diagram.operation.edit.parts.CompositeOperationEditPart.MODEL_ID,
				org.openiaml.model.diagram.operation.edit.parts.CompositeOperationEditPart.VISUAL_ID,
				org.openiaml.model.diagram.operation.part.IamlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT,
				org.openiaml.model.diagram.operation.part.IamlDiagramEditorUtil.getSaveOptions(),
				org.openiaml.model.diagram.operation.part.Messages.IamlNewDiagramFileWizard_InitDiagramCommand,
				org.openiaml.model.diagram.operation.part.Messages.IamlNewDiagramFileWizard_IncorrectRootError) {

					@Override
					public void openDiagram(Resource diagramResource) throws PartInitException {						
						org.openiaml.model.diagram.operation.part.IamlDiagramEditorUtil.openDiagram(diagramResource);
					}
					
					@Override
					public int getDiagramVisualID(IFile modelFile, EObject modelRoot) {
						return org.openiaml.model.diagram.operation.part.IamlVisualIDRegistry.getDiagramVisualID(modelRoot);
					}
			
		});
		
		
		editors.put("iaml_session", new IamlDiagramRegistryOptions(
				org.openiaml.model.diagram.session.edit.parts.SessionEditPart.MODEL_ID,
				org.openiaml.model.diagram.session.edit.parts.SessionEditPart.VISUAL_ID,
				org.openiaml.model.diagram.session.part.IamlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT,
				org.openiaml.model.diagram.session.part.IamlDiagramEditorUtil.getSaveOptions(),
				org.openiaml.model.diagram.session.part.Messages.IamlNewDiagramFileWizard_InitDiagramCommand,
				org.openiaml.model.diagram.session.part.Messages.IamlNewDiagramFileWizard_IncorrectRootError) {

					@Override
					public void openDiagram(Resource diagramResource) throws PartInitException {						
						org.openiaml.model.diagram.session.part.IamlDiagramEditorUtil.openDiagram(diagramResource);
					}
					
					@Override
					public int getDiagramVisualID(IFile modelFile, EObject modelRoot) {
						return org.openiaml.model.diagram.session.part.IamlVisualIDRegistry.getDiagramVisualID(modelRoot);
					}
			
		});
		
		editors.put("iaml_user_store", new IamlDiagramRegistryOptions(
				org.openiaml.model.diagram.user_store.edit.parts.UserStoreEditPart.MODEL_ID,
				org.openiaml.model.diagram.user_store.edit.parts.UserStoreEditPart.VISUAL_ID,
				org.openiaml.model.diagram.user_store.part.IamlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT,
				org.openiaml.model.diagram.user_store.part.IamlDiagramEditorUtil.getSaveOptions(),
				org.openiaml.model.diagram.user_store.part.Messages.IamlNewDiagramFileWizard_InitDiagramCommand,
				org.openiaml.model.diagram.user_store.part.Messages.IamlNewDiagramFileWizard_IncorrectRootError) {

					@Override
					public void openDiagram(Resource diagramResource) throws PartInitException {						
						org.openiaml.model.diagram.user_store.part.IamlDiagramEditorUtil.openDiagram(diagramResource);
					}
					
					@Override
					public int getDiagramVisualID(IFile modelFile, EObject modelRoot) {
						return org.openiaml.model.diagram.user_store.part.IamlVisualIDRegistry.getDiagramVisualID(modelRoot);
					}
			
		});
		
		editors.put("iaml_visual", new IamlDiagramRegistryOptions(
				org.openiaml.model.diagram.visual.edit.parts.VisibleThingEditPart.MODEL_ID,
				org.openiaml.model.diagram.visual.edit.parts.VisibleThingEditPart.VISUAL_ID,
				org.openiaml.model.diagram.visual.part.IamlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT,
				org.openiaml.model.diagram.visual.part.IamlDiagramEditorUtil.getSaveOptions(),
				org.openiaml.model.diagram.visual.part.Messages.IamlNewDiagramFileWizard_InitDiagramCommand,
				org.openiaml.model.diagram.visual.part.Messages.IamlNewDiagramFileWizard_IncorrectRootError) {

					@Override
					public void openDiagram(Resource diagramResource) throws PartInitException {						
						org.openiaml.model.diagram.visual.part.IamlDiagramEditorUtil.openDiagram(diagramResource);
					}
					
					@Override
					public int getDiagramVisualID(IFile modelFile, EObject modelRoot) {
						return org.openiaml.model.diagram.visual.part.IamlVisualIDRegistry.getDiagramVisualID(modelRoot);
					}
			
		});

		return editors;		
	}
	
	/**
	 * Initialise a model file from a source file.
	 * The {@link IFile#getFileExtension() model file extension} is used to select the
	 * appropriate editor.
	 * 
	 * @see #getEditors()
	 * @see IFile#getFileExtension()
	 * @param modelFile must exist
	 * @param diagramFile must not exist yet
	 * @param open should the new diagram file be opened?
	 * @throws DiagramRegistryException if no editor could be found for the given file
	 * @throws IOException 
	 * @throws ExecutionException 
	 * @throws PartInitException 
	 */
	public static void initializeModelFile(IFile modelFile, IFile diagramFile, boolean open) throws DiagramRegistryException, PartInitException, ExecutionException, IOException {
		
		// find the appropriate editor
		IamlDiagramRegistryOptions opt = getEditors().get(modelFile.getFileExtension());
		if (opt == null)
			throw new DiagramRegistryException("Could not find editor for file extension: " + modelFile.getFileExtension());

		// pass the appropriate arguments
		Resource diagramResource = initializeModelFile(
				modelFile,
				diagramFile,
				opt
		);
		
		if (open) {
			opt.openDiagram(diagramResource);
		}
		
	}
	
	/**
	 * Initialise the diagram file, and return the loaded resource (before it is opened).
	 * Does not open the diagram file.
	 */
	protected static Resource initializeModelFile(final IFile modelFile, final IFile diagramFile,
			final IamlDiagramRegistryOptions options) throws DiagramRegistryException, ExecutionException, IOException, PartInitException {
		
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
				options.initMessage,
				affectedFiles) {

			@Override
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				int diagramVID = options.getDiagramVisualID(modelFile, modelRoot);
				if (diagramVID != options.visualId) {
					return CommandResult
							.newErrorCommandResult(options.errorMessage);
				}
				Diagram diagram = ViewService.createDiagram(
						modelRoot,
						options.modelId,
						options.prefHint);
				diagramResource.getContents().add(diagram);
				return CommandResult.newOKCommandResult();
			}
		};
		OperationHistoryFactory.getOperationHistory().execute(command,
				new NullProgressMonitor(), null);
		diagramResource.save(options.saveOptions);
		
		return diagramResource;
		
	}
		
	public static class DiagramRegistryException extends Exception {

		public DiagramRegistryException(String string) {
			super(string);
		}

		private static final long serialVersionUID = 1L;

	}
	
}
