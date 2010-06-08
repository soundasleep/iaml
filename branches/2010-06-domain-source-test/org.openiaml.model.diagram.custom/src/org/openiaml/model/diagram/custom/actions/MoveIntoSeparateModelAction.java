package org.openiaml.model.diagram.custom.actions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.openiaml.model.custom.actions.ProgressEnabledAction;
import org.openiaml.model.diagram.part.IamlCreationWizardPage;
import org.openiaml.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.diagram.part.IamlDiagramEditorUtil;
import org.openiaml.model.diagram.part.Messages;
import org.openiaml.model.model.ApplicationElement;
import org.openiaml.model.model.CompositeCondition;
import org.openiaml.model.model.CompositeOperation;
import org.openiaml.model.model.NamedElement;
import org.openiaml.model.model.VisibleThing;
import org.openiaml.model.model.domain.DomainIterator;
import org.openiaml.model.model.domain.DomainSchema;

/**
 * An action which allows the user to move the selected model element
 * into a separate model file.
 *
 * EMF handles all the logic for us, it seems.
 *
 * @author jmwright
 */
public class MoveIntoSeparateModelAction extends ProgressEnabledAction<GraphicalEditPart> {

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(GraphicalEditPart individual, String message) {
		return "Could not move model element '" + individual + "': " + message;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Moving model element";
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getSelection(java.lang.Object[])
	 */
	@Override
	public List<GraphicalEditPart> getSelection(Object[] selection) {
		final List<GraphicalEditPart> ifiles = new ArrayList<GraphicalEditPart>();

		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof GraphicalEditPart) {
					ifiles.add((GraphicalEditPart) o);
				}
			}
		}

		return ifiles;
	}

	/**
	 *
	 * <p>Based off generated {@link org.openiaml.model.diagram.part.IamlCreationWizard}.</p>
	 *
	 * @author jmwright
	 *
	 */
	public class NewDomainFileWizard extends Wizard {

		protected IStructuredSelection selection;

		protected String suggestedName;

		protected TransactionalEditingDomain domain;

		/**
		 * The object to move.
		 */
		protected EObject object;

		protected String fileExtension;

		/**
		 * @param selection
		 * @param suggestedName
		 * @param domain
		 * @param object
		 */
		public NewDomainFileWizard(IStructuredSelection selection,
				String suggestedName, String fileExtension,
				TransactionalEditingDomain domain,
				EObject object) {
			super();
			this.selection = selection;
			this.suggestedName = suggestedName;
			this.fileExtension = fileExtension;
			this.domain = domain;
			this.object = object;

			setWindowTitle(Messages.IamlCreationWizardTitle);
			setDefaultPageImageDescriptor(IamlDiagramEditorPlugin
					.getBundledImageDescriptor("icons/wizban/NewModelWizard.gif")); //$NON-NLS-1$
			setNeedsProgressMonitor(true);
		}

		public IStructuredSelection getSelection() {
			return selection;
		}

		protected IamlCreationWizardPage modelPage;

		@Override
		public void addPages() {
			modelPage = new IamlCreationWizardPage(
					"DomainModelFile", getSelection(), fileExtension) {

				public void setVisible(boolean visible) {
					if (visible) {
						String fileName = suggestedName;
						setFileName(IamlDiagramEditorUtil.getUniqueFileName(
								getContainerFullPath(), fileName, fileExtension));
					}
					super.setVisible(visible);
				}
			};
			modelPage
					.setTitle(Messages.IamlCreationWizard_DomainModelFilePageTitle);
			modelPage
					.setDescription(Messages.IamlCreationWizard_DomainModelFilePageDescription);
			addPage(modelPage);
		}

		@Override
		public boolean performFinish() {

			IRunnableWithProgress op = new WorkspaceModifyOperation(null) {

				protected void execute(IProgressMonitor monitor)
						throws CoreException, InterruptedException {

					AbstractTransactionalCommand command = new AbstractTransactionalCommand(
							domain,
							"Move into separate model file",
							Collections.EMPTY_LIST /* TODO make this select the correct affected files */) {

						@Override
						protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
								IAdaptable info) throws ExecutionException {

							Resource modelResource = domain.getResourceSet().createResource(modelPage.getURI());
							modelResource.getContents().add(object);
							try {
								modelResource.save(org.openiaml.model.diagram.part.IamlDiagramEditorUtil
										.getSaveOptions());
							} catch (IOException e) {
								return CommandResult.newErrorCommandResult(e);
							}

							return CommandResult.newOKCommandResult();
						}
					};

					try {
						IStatus result = command.execute(new NullProgressMonitor(), null);
						if (!result.isOK()) {
							logStatus(result);
						}
					} catch (ExecutionException e) {
						throw new RuntimeException(e);
					}

				}
			};

			try {
				getContainer().run(false, true, op);
			} catch (InterruptedException e) {
				return false;
			} catch (InvocationTargetException e) {
				logStatus(errorStatus(e));
				return false;
			}
			return true;
		}
	}

	/**
	 * Wrap the action with monitor updates.
	 *
	 * @param part The edit part to work with
	 * @param monitor
	 * @return
	 */
	public IStatus execute(final GraphicalEditPart part, IProgressMonitor monitor) {

		EObject target = part.resolveSemanticElement();
		String suggestedName = suggestName(target);

		String fileExtension = getFileExtension(target);
		if (fileExtension == null) {
			return errorStatus("Cannot move the given object '" + target.eClass().getName() + "' into a separate file." );
		}

		String location = target.eResource().getURI().toPlatformString(true);
		IStructuredSelection selection = new StructuredSelection(ResourcesPlugin.getWorkspace().getRoot().findMember( location ));

		// get a wizard to select the new model element file
		final NewDomainFileWizard wizard = new NewDomainFileWizard(selection,
				suggestedName,
				fileExtension,
				part.getEditingDomain(),
				target);

		// we need to exec the Wizard in the UI thread
		final Display display = PlatformUI.getWorkbench().getDisplay();
		display.syncExec(new Runnable() {

			@Override
			public void run() {
				WizardDialog dialog = new WizardDialog(display.getActiveShell(), wizard);
				dialog.create();
				dialog.open();

			}

		});

		// done
		return Status.OK_STATUS;
	}

	/**
	 * Suggest a filename for the given EObject.
	 *
	 * @param target
	 * @return
	 */
	protected String suggestName(EObject target) {
		if (target instanceof NamedElement) {
			return ((NamedElement) target).getName().replaceAll("[^A-Za-z0-9]+", "_");
		}
		return "moved";
	}

	/**
	 * Get the file extension for the given EObject.
	 * If the given file extension does not accurately describe the EMF
	 * object, this method must return null, so that the object is not moved
	 * into an EMF model which cannot support it.
	 * 
	 * <p>TODO this should be generated automatically.
	 *
	 * @param target
	 * @return The domain model file extension, or null if none can be found
	 */
	protected String getFileExtension(EObject target) {
		if (target instanceof CompositeCondition) {
			return "iaml_condition";
		}
		if (target instanceof DomainIterator) {
			return "iaml_iterator";
		}
		if (target instanceof DomainSchema) {
			return "iaml_schema";
		}
		if (target instanceof ApplicationElement) {
			return "iaml_element";
		}
		if (target instanceof CompositeOperation) {
			return "iaml_operation";
		}
		if (target instanceof VisibleThing) {
			return "iaml_visual";
		}

		// must return null if none is found
		return null;
	}

}
