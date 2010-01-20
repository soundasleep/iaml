package org.openiaml.model.custom.actions;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.inference.InferenceException;
import org.openiaml.verification.nusmv.NuSMVViolation;
import org.openiaml.verification.nusmv.VerificationEngine;
import org.openiaml.verification.nusmv.VerificationException;

/**
 * Verify the .iaml file with Crocopat.
 * 
 * @see org.openiaml.verification.crocopat
 * @author jmwright
 * 
 */
public class VerificationWithNuSMVAction extends IamlFileAction {

	private EObject model;

	/**
	 * From http://wiki.eclipse.org/FAQ_How_do_I_open_an_editor_on_something_that_is_not_a_file%3F 
	 */
	public class StringStorage implements IStorage {
		private String string;
		private String name;
		
		StringStorage(String name, String input) {
			this.string = input;
			this.name = name;
		}

		public InputStream getContents() throws CoreException {
			return new ByteArrayInputStream(string.getBytes());
		}

		public IPath getFullPath() {
			return null;
		}

		@SuppressWarnings("unchecked")
		public Object getAdapter(Class adapter) {
			return null;
		}

		public String getName() {
			return name;
		}

		public boolean isReadOnly() {
			return true;
		}
	}

	/**
	 * From http://wiki.eclipse.org/FAQ_How_do_I_open_an_editor_on_something_that_is_not_a_file%3F 
	 */
	public class StringInput implements IStorageEditorInput {
		private IStorage storage;

		StringInput(IStorage storage) {
			this.storage = storage;
		}

		public boolean exists() {
			return true;
		}

		public ImageDescriptor getImageDescriptor() {
			return null;
		}

		public String getName() {
			return storage.getName();
		}

		public IPersistableElement getPersistable() {
			return null;
		}

		public IStorage getStorage() {
			return storage;
		}

		public String getToolTipText() {
			return storage.getName();
		}

		@SuppressWarnings("unchecked")
		public Object getAdapter(Class adapter) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#execute
	 * (java.lang.Object, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus doExecute(IFile o, IProgressMonitor monitor)
			throws InferenceException, IOException, CoreException {
		
		monitor.beginTask("Verifying model '" + o.getName() + "'", 10);

		monitor.subTask("Loading model");
		// try and load the file directly
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(URI.createFileURI(o
				.getLocation().toString()), true);

		// we can only do one model
		if (resource.getContents().size() != 1) {
			return new Status(
					IStatus.ERROR,
					PLUGIN_ID,
					"Could not transform model: unexpected number of model elements in file (expected: 1, found: "
							+ resource.getContents().size() + ")");
		}

		// do inference on the model
		model = resource.getContents().get(0);
		monitor.worked(1);

		// do verification
		VerificationEngine verify = new VerificationEngine();
		IStatus status;
		try {
			status = verify.verify(model, new SubProgressMonitor(monitor, 9));
		} catch (VerificationException e) {
			return errorStatus(e);
		}

		// check for violation exceptions
		if (!verify.getViolations().isEmpty()) {
			final StringBuffer buf = new StringBuffer();

			for (int i = 0; i < 5 && i < verify.getViolations().size(); i++) {
				NuSMVViolation violation = verify.getViolations().get(i);
				if (i != 0)
					buf.append("\n");

				buf.append(violation.getMessage());

			}

			if (verify.getViolations().size() > 5) {
				// there are more
				buf.append("\n(...").append(verify.getViolations().size() - 5)
						.append(" more)");
			}
			
			Display.getDefault().asyncExec(new Runnable() {

				@Override
				public void run() {
					IWorkbench wb = PlatformUI.getWorkbench();
					if (wb != null) {
						IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
						IStorage storage = new StringStorage("NuSMV Results", buf.toString());
						IStorageEditorInput input = new StringInput(storage);
						IWorkbenchPage page = window.getActivePage();
						if (page != null) {
							try {
								page.openEditor(input, "org.eclipse.ui.DefaultTextEditor");
							} catch (PartInitException e) {
								throw new RuntimeException(e);
							}
						}
					}
				}
				
			});
			
			// shorten the message for the status
			// if there are too many newlines, split it up
			String bufString = buf.toString();
			String[] bits = bufString.split("\n");
			if (bits.length > MAX_ERROR_LINES) {
				StringBuffer buf2 = new StringBuffer();
				for (int i = 0; i < MAX_ERROR_LINES; i++) {
					buf2.append(bits[i]).append('\n');
				}
				buf2.append("(... " + (bits.length - MAX_ERROR_LINES) + " more lines)");
				bufString = buf2.toString();
			}
			
			// return the error
			return errorStatus("Verification failed:\n\n" + bufString);
		}

		// finished
		monitor.done();
		
		// display OK message
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), 
					 "Verification successful",
					 "No verification problems were found with NuSMV");
			}
			
		});

		return status;
	}
		
	/**
	 * The maximum number of lines to display in a message box.
	 * @see #getErrorMessage(IFile, String)
	 */
	protected static final int MAX_ERROR_LINES = 10; 

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.openiaml.model.diagram.custom.actions.ProgressEnabledAction#
	 * getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(IFile individual, String message) {
		return "Could not verify '" + individual.getName() + "': " + message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.openiaml.model.diagram.custom.actions.ProgressEnabledAction#
	 * getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Verifying";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openiaml.model.diagram.custom.actions.IamlFileAction#getLoadedModel()
	 */
	@Override
	protected EObject getLoadedModel() {
		return model;
	}

	/**
	 * We want error messages to be displayed to the user.
	 */
	@Override
	public boolean shouldDisplayErrorToUser() {
		return true;
	}

}
