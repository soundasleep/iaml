/**
 * 
 */
package org.openiaml.docs.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.openiaml.docs.tools.InitialiseDiagram.InitializeDiagramException;
import org.openiaml.model.diagram.custom.helpers.DiagramRegistry;
import org.openiaml.model.model.ModelPackage;

/**
 * @author jmwright
 *
 */
public class ExportIAMLImagesJob extends AbstractIAMLDocJob {

	// the package we will search for (we can change this if necessary)
	private EPackage rootPackage = ModelPackage.eINSTANCE;
	
	private IProject project;
	
	public ExportIAMLImagesJob(IProject project) {
		super("Export IAML element images");
		this.project = project;
		setUser(true);		// we are a user job, so we should get some feedback
	}

	/**
	 * Do the actual work.
	 */
	@Override
	protected IStatus runActual(IProgressMonitor monitor) throws CoreException {
		// new task
		monitor.beginTask(this.getName(), 100);
		
		// find out the object we should search for
		monitor.subTask("Compiling list of EMF objects to search for");
		List<EClass> objects = getObjectsToSearchFor(rootPackage);
		if (objects.isEmpty()) {
			// no objects to search for
			return errorStatus("No EMF objects to search for; expected at least one.");
		}
		monitor.worked(10);	// 10%
		
		// check that at least one exists
		monitor.subTask("Checking project for at least one EMF object");
		if (!checkAtLeastOneExists(objects)) {
			// failed
			return errorStatus("Could not find any matching root files for any EMF object in this project.");
		}
		monitor.worked(10);	// 10%
		
		// finally, if we got all the way here, do the search
		IStatus status = doExport(objects, new SubProgressMonitor(monitor, 80));
		monitor.done();
		
		return status;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected IStatus run(final IProgressMonitor monitor) {
			
		  Display.getDefault().syncExec(new Runnable() {
		    @Override
		    public void run() {
		    	try {
			    	IStatus status = runActual(monitor);
			    	
			    	if (!status.isOK()) {
						DocToolsPlugin.getInstance().getLog().log(status);
					}
		    	} catch (CoreException e) {
					IStatus error = new Status(Status.ERROR, DocToolsPlugin.PLUGIN_ID, "An exception occured: " + e.getMessage(), e);
					DocToolsPlugin.getInstance().getLog().log(error);
					
				}
		    }
		  });
			
		// can't get the real status yet
		return Status.OK_STATUS;
			
		
	}

	/**
	 * For the given class: Try and find a corresponding model file; 
	 * initialise the diagram file and try to open it; export the
	 * root image (and <em>only</em> the root); and then delete the diagram file.
	 * 
	 * @see DiagramRegistry#initializeModelFile(IFile, IFile)
	 * @see #exportDiagramThenClose(EClass, DiagramDocumentEditor, IProgressMonitor)
	 */
	protected IStatus doExport(List<EClass> classes, IProgressMonitor monitor) throws CoreException {
		
		// count of images exported
		int exported = 0;
		
		// we collect up all uninitialisable diagrams, so we can
		// quickly see which ones were unable to open
		MultiStatus errorResult = new MultiStatus(DocToolsPlugin.PLUGIN_ID, Status.ERROR, "Could not export example images: multiple errors occured", null);
		
		for (EClass cls : classes) {
			monitor.beginTask("Exporting class " + cls.getName(), 105);
			
			monitor.subTask("Finding file for class " + cls.getName());
			IFile file = getFileFor(cls);
			if (getFileFor(cls) == null)	
				continue;		// skip
			
			monitor.worked(10);
			
			InitialiseDiagram init = new InitialiseDiagram();
			IFile diagram;
			try {
				diagram = init.initialize(project, file, true /* open new diagram */, new SubProgressMonitor(monitor, 50));
			} catch (InitializeDiagramException e) {
				// if we couldn't initialise it, skip it
				errorResult.add(errorStatus("Class '" + cls.getName() + "'", e));
				continue;
			}
			
			// the diagram should now be opened
			monitor.subTask("Exporting root image");
			
			// get the active workbench editor part
			// based on IamlDiagramEditorUtil#openDiagram()
			IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
			DiagramDocumentEditor editor = (DiagramDocumentEditor) page.getActiveEditor();
			
			// export only one image
			exportDiagramThenClose(cls, editor, new SubProgressMonitor(monitor, 40));
			
			// finally, delete the diagram file
			monitor.subTask("Deleting diagram file");
			diagram.delete(true, new SubProgressMonitor(monitor, 5));
			monitor.done();
			
			exported++;

		}
		
		// any errors in initialising?
		if (errorResult.getChildren().length != 0) {
			return errorResult;
		}
		
		// done
		if (exported == 0) {
			// we didn't export any! throw a warning
			return new Status(Status.WARNING, DocToolsPlugin.PLUGIN_ID, "Did not export any root element images");
		}

		return Status.OK_STATUS;
	}
	
	/**
	 * For the currently open editor: export the image to PNG; then try and 
	 * force the editor closed (if supported).
	 */
	protected void exportDiagramThenClose(EClass cls, DiagramDocumentEditor editor, IProgressMonitor monitor) throws CoreException {
		DiagramEditPart part = editor.getDiagramEditPart();
		IPath destination = generateImageDestination(cls);
		
		// save this image
		// (even if there isn't anything in it)
		IProgressMonitor saveMonitor = new SubProgressMonitor(monitor, 1);
		saveMonitor.beginTask("Saving image " + destination, 2);
		monitor.subTask("Saving image " + destination.lastSegment());
		CopyToImageUtil img = getCopyToImageUtil();
		img.copyToImage(part, destination, ImageFileFormat.PNG, new SubProgressMonitor(monitor, 1));
		saveMonitor.done();
		
		// close the editor once we're done
		// (this is done asynchronously, so there might still be things going on in the
		// editor when the monitor is marked 'done')
		
		// if we have a closeBlocking method (Jevon extension), invoke that instead
		try {
			Method method = editor.getClass().getMethod("closeBlocking", new Class[] { boolean.class} );
			method.invoke(editor, new Object[] { false } );
			return;
		} catch (SecurityException e) {
			// ignore
		} catch (NoSuchMethodException e) {
			// ignore
		} catch (IllegalArgumentException e) {
			// ignore
		} catch (IllegalAccessException e) {
			// ignore
		} catch (InvocationTargetException e) {
			// ignore
		}
		
		// do normal close
		editor.close(false);
		
	}

	/**
	 * Create a new {@link CopyToImageUtil}, which will be used
	 * to export images.
	 * 
	 * @see CopyToImageUtil#copyToImage(DiagramEditPart, IPath, ImageFileFormat, IProgressMonitor)
	 * @return
	 */
	protected CopyToImageUtil getCopyToImageUtil() {
		return new CopyToImageUtil();
	}
	
	/**
	 * @return
	 */
	protected IPath generateImageDestination(EClass cls) {
		return project.getFile(cls.getName() + ".png").getLocation();
	}

	/**
	 * @param classes the classes to search for
	 * @return true if at least one exists; false otherwise
	 * @throws CoreException 
	 */
	protected boolean checkAtLeastOneExists(List<EClass> classes) throws CoreException {
		
		for (EClass cls : classes) {
			if (getFileFor(cls) != null)
				return true;	// found one
		}

		// none found
		return false;
	}
	
	/**
	 * Search for the matching file for the given class. Ignores
	 * <code>.foo_diagram</code> files.
	 * 
	 * @param cls
	 * @return The file found <code>object_name.something</code>, or <code>null</code> if none was found
	 * @throws CoreException 
	 */
	protected IFile getFileFor(EClass cls) throws CoreException {
		// get all the root files in this project
		IResource[] resources = project.members();
		for (IResource res : resources) {
			if (res instanceof IFile) {
				IFile file = (IFile) res;
				
				// ignore *.foo_diagram files
				if (file.getFileExtension().endsWith("_diagram"))
					continue;
				
				// does the name match (case-sensitive) any object name?
				if (file.getName().startsWith(cls.getName() + ".")) {
					return file;
				}
			}
		}
		
		// none found
		return null;
	}

	/**
	 * Get all non-abstract, non-interface EClasses in this package and subpackages (recursive).
	 * 
	 * @return A list of non-abstract, non-interface EClasses in this package and subpackages.
	 */
	protected List<EClass> getObjectsToSearchFor(EPackage pkg) {
		List<EClass> result = new ArrayList<EClass>();
		
		for (EClassifier cf : pkg.getEClassifiers()) {
			if (cf instanceof EClass) {
				EClass cls = (EClass) cf;
				if (cls.isAbstract()) continue;
				if (cls.isInterface()) continue;
				
				// ok
				result.add(cls);
			}
		}
		
		// subpackages
		for (EPackage sub : pkg.getESubpackages()) {
			result.addAll(getObjectsToSearchFor(sub));
		}
		
		return result;
	}
	
}