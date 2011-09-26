/**
 * 
 */
package org.openiaml.modeltesting;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import junit.framework.TestCase;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;

/**
 * Contains all of the methods necessary to create projects.
 * 
 * @author jmwright
 *
 */
public class EclipseProject extends TestCase {
	
	private static final String PLUGIN_ID = "org.openiaml.modeltesting";

	private IProject project;
	private String projectName;
	
	public EclipseProject(String projectName) {
		if (projectName == null)
			throw new IllegalArgumentException("Project name cannot be null");
		
		this.projectName = projectName;
	}
	
	/**
	 * Get the project created.
	 * 
	 * @return
	 */
	public IProject getProject() {
		return project;
	}

	/**
	 * Close the project.
	 * @throws CoreException 
	 */
	public void close() throws CoreException {
		project.close(getMonitor());
	}

	/**
	 * Get a monitor to output results to.
	 * @return
	 */
	protected IProgressMonitor getMonitor() {
		return new NullProgressMonitor();
	}
	
	private boolean created = false;
	
	/**
	 * Create a new project in our testing environment,
	 * allowing our code generator to output there.
	 * 
	 * <p>We can also copy files directly from our testing environment
	 * to this new project by using
	 * {@link #copyFileIntoWorkspace(String, IFile)}.
	 * 
	 * @see #getProjectName()
	 * @see #copyFileIntoWorkspace(String, IFile)
	 * @return the created project
	 * @throws CoreException
	 */
	public IProject createProject() throws CoreException {
		if (created)
			throw new RuntimeException("Project '" + getProjectName() + "' was already created");
		
		IProgressMonitor monitor = getMonitor();
		
		// create a new project automatically
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		root.refreshLocal(5, monitor);
		project = root.getProject(getProjectName());
		// delete any existing ones
		if (project.exists()) {
			project.delete(true, monitor);
		}
		
		// create it
		project.create(monitor);
		assertTrue(project.exists());
		project.open(monitor);
		
		created = true;
		
		return project;
	}
	
	/**
	 * Force a complete refresh of the entire project, and
	 * halt execution until it's completed.
	 * 
	 * @return
	 * @throws CoreException
	 */
	public IStatus refreshProject() throws CoreException {
		
		HaltProgressMonitor m = new HaltProgressMonitor();
		project.refreshLocal(IResource.DEPTH_INFINITE, m);
		try {
			while (!m.isDone()) {
				Thread.sleep(300);
			}
		} catch (InterruptedException e) {
			return new Status(Status.ERROR, PLUGIN_ID , "refresh thread was interrupted", e);
		}

		return Status.OK_STATUS;
	}

	protected class HaltProgressMonitor extends NullProgressMonitor {
		@Override
		public void setCanceled(boolean cancelled) {
			isDone = true;	// bail early
			super.setCanceled(cancelled);
		}

		private boolean isDone = false;
		public synchronized boolean isDone() {
			return isDone;
		}

		@Override
		public void done() {
			isDone = true;
			super.done();
		}
	}

	public String getProjectName() {
		return projectName;
	}

	public IFile getFile(String filename) {
		return project.getFile(filename);
	}

	public IFolder getFolder(String string) {
		return project.getFolder(string);
	}

	/**
	 * Delete the given file and halt until it's finished.
	 * 
	 * @param modelNew
	 * @throws CoreException 
	 */
	public void haltDelete(IFile modelNew) throws CoreException {
		HaltProgressMonitor m = new HaltProgressMonitor();
		modelNew.delete(true, m);
		halt(m);
	}
	
	protected void halt(HaltProgressMonitor m) {
		try {
			while (!m.isDone()) {
				Thread.sleep(300);
			}
		} catch (InterruptedException e) {
			e.printStackTrace(System.err);
		}
	}
	

	/**
	 * Copy a local file into the Eclipse workspace. Makes sure it doesn't
	 * already exist, and that it does exist once this method is completed.
	 * 
	 * This method also creates any necessary sub-folders recursively. 
	 * 
	 * @param source
	 * @param target
	 * @param monitor 
	 * @return the target file
	 * @throws CoreException 
	 * @throws FileNotFoundException 
	 */
	public static IFile copyFileIntoWorkspace(File sourceFile, IFile target, IProgressMonitor monitor) throws CoreException, FileNotFoundException {
		createFolderRecursively(target.getParent());
		
		assertTrue("source file '" + sourceFile + "' should exist", sourceFile.exists());

		assertFalse("target file '" + target + "' should not exist yet", target.exists());
		
		// copy
		target.create(new BufferedInputStream(new FileInputStream(sourceFile)), true, monitor);
		
		// check
		assertTrue("target file '" + target + "' should now exist", target.exists());
		
		return target;
	}

	/**
	 * If the given argument is an IFolder, create it if it does not yet
	 * exist. This method will
	 * also create any parent IFolders if they need to be created first.
	 * 
	 * @param parent
	 * @throws CoreException 
	 */
	protected static void createFolderRecursively(IContainer parent) throws CoreException {
		if (!(parent instanceof IFolder))
			return;
		
		IFolder folder = (IFolder) parent;
		
		if (folder.exists())
			return;
		
		// it doesn't exist
		// make sure the parent exists
		createFolderRecursively(folder.getParent());
		
		// create this one
		folder.create(true, true, new NullProgressMonitor());
	}
	
}
