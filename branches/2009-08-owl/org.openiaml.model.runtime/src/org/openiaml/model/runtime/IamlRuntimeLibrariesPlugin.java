/**
 * 
 */
package org.openiaml.model.runtime;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Plugin;

/**
 * @author jmwright
 *
 */
public class IamlRuntimeLibrariesPlugin extends Plugin implements IFileCopyListener {

	private static IamlRuntimeLibrariesPlugin instance;
	
	private List<IFileCopyListener> listeners = new ArrayList<IFileCopyListener>();
	
	public static IamlRuntimeLibrariesPlugin getInstance() {
		return instance;
	}
	
	/**
	 * This method should only be called by the Eclipse framework.
	 * 
	 * @deprecated Only the Eclipse framework should use this; see {@link #getInstance()}. 
	 */
	public IamlRuntimeLibrariesPlugin() {
		super();
		instance = this;
	}
	
	public void addFileCopyListener(IFileCopyListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Copy over all runtime files from this plugin into the 
	 * target directory.
	 * 
	 * @param project target project
	 * @param monitor 
	 * @throws IOException 
	 * @throws CoreException 
	 */
	public void copyRuntimeFiles(IProject project, IProgressMonitor monitor) throws IOException, CoreException {
		monitor.beginTask("Copying over runtime files...", 10);
		
		URL root = getBundle().getEntry("src");
		root = FileLocator.resolve(root);
		
		Enumeration<?> e = getBundle().findEntries("src", "*.*", true);
		while (e.hasMoreElements()) {
			URL url = (URL) e.nextElement();
			url = FileLocator.resolve(url);
			if (url.toExternalForm().contains(".svn"))
				continue;	// ignore svn files
			
			// work out the target for this entry
			String fileTarget = url.toExternalForm().replace(root.toExternalForm(), "");
			monitor.subTask("Copying " + fileTarget);
			IFile towrite = project.getFile("output/runtime/" + fileTarget);
			
			// copy it over
			createParentsRecursively(towrite.getParent(), new NullProgressMonitor());
			towrite.create(url.openStream(), true, new NullProgressMonitor());
			
			// notify listeners
			fileCopied( new File(url.toExternalForm()), towrite.getLocation().toFile() );
		}
		
		monitor.done();
	}
	
	/**
	 * Get the given file in our current bundle. 
	 * 
	 * @param filename
	 * @return
	 * @throws IOException 
	 */
	public URL getResolvedFile(String filename) throws IOException {
		URL file = getBundle().getEntry(filename);
		
		return FileLocator.resolve(file);
	}

	/**
	 * Create the parents of the given file recursively.
	 * 
	 * @param towrite
	 * @throws CoreException 
	 */
	private void createParentsRecursively(IResource f, IProgressMonitor monitor) throws CoreException {
		if (f == null || f.exists()) 
			return;
		
		if (!f.getParent().exists()) {
			createParentsRecursively(f.getParent(), monitor);
		}
		
		if (f instanceof IFolder) {
			((IFolder) f).create(true, true, monitor);
		} else {
			throw new RuntimeException("Cannot create the parent '" + f + "' as it is not a folder");
		}
	}

	/**
	 * Notify all of the listeners of the IFileCopyListener event.
	 * 
	 * @see org.openiaml.model.runtime.IFileCopyListener#fileCopied(java.io.File, java.io.File)
	 */
	@Override
	public void fileCopied(File source, File target) {
		for (IFileCopyListener listener : listeners) {
			listener.fileCopied(source, target);
		}
	}
	
}
