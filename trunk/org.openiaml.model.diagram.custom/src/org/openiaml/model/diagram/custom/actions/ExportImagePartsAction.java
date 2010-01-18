package org.openiaml.model.diagram.custom.actions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.custom.actions.ProgressEnabledUIAction;
import org.openiaml.model.diagram.part.IamlDiagramEditorUtil;

/**
 * Export all of the images in a model diagram to multiple image files.
 * This does not create clickable HTML.
 * 
 * @see ExportToClickableHtml
 * @author jmwright
 *
 */
public class ExportImagePartsAction extends ProgressEnabledUIAction<IFile> {

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getErrorMessage(java.lang.Object, java.lang.String)
	 */
	@Override
	public String getErrorMessage(IFile individual, String message) {
		return "Could not export images for '" + individual.getName() + "': " + message;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getProgressMessage()
	 */
	@Override
	public String getProgressMessage() {
		return "Exporting images";
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#getSelection(java.lang.Object[])
	 */
	@Override
	public List<IFile> getSelection(Object[] selection) {
		final List<IFile> ifiles = new ArrayList<IFile>();
		
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IFile) {
					ifiles.add((IFile) o);
				}
			}
		}
		
		return ifiles;
	}

	/* (non-Javadoc)
	 * @see org.openiaml.model.diagram.custom.actions.ProgressEnabledAction#execute(java.lang.Object, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public IStatus execute(IFile individual, IProgressMonitor monitor) {
		try {
			doExport(individual, null, monitor);
			return Status.OK_STATUS;
		} catch (ExportImageException e) {
			return errorStatus("Export image exception: " + e.getMessage(), e);
		} catch (RuntimeException e) {
			return errorStatus("Runtime exception: " + e.getMessage(), e);
		}

	}

	/**
	 * Do the export
	 * 
	 * @param targetDiagram
	 * @param monitor 
	 * @param container the container to place the exported images and HTML into, or <code>null</code> to place in the same container as <code>targetDiagram</code>
	 * @throws ExportImageException if anything went wrong
	 */
	public void doExport(IFile targetDiagram, IContainer container, IProgressMonitor monitor) throws ExportImageException {
		diagramFile = targetDiagram;
		
		// if the container is null, use the container of the diagram file
		if (container == null) {
			container = targetDiagram.getParent();
		}
		
		try {
			monitor.beginTask("Loading target diagram " + targetDiagram.getName(), getMaxImages() + 2);
			monitor.subTask("Loading target diagram " + targetDiagram.getName());
			
			// we need to load this model diagram
			// based on EclipseTestCase#loadDiagramFile(IFile)
			// try loading it up with Eclipse
			ResourceSet resSet = new ResourceSetImpl();          
			Resource res = resSet.getResource(URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), false), true);
			IamlDiagramEditorUtil.openDiagram( res );
			
			// get the active workbench editor part
			// based on IamlDiagramEditorUtil#openDiagram()
			IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
			DiagramDocumentEditor editor = (DiagramDocumentEditor) page.getActiveEditor();
			
			monitor.worked(1);
			
			// export all of the images
			imagesSaved = 0;
			recurseExportDiagram(editor, container, monitor);
			
			// only once we return are we actually done
			monitor.done();
		} catch (Exception e) {
			throw new ExportImageException(e);
		}

	}
	
	/**
	 * The maximum number of images that will be created.
	 * 
	 * @return
	 */
	public int getMaxImages() {
		return MAX_IMAGES;
	}

	/**
	 * Should we stop exporting images after exporting this many?
	 * 
	 * @param imagesSaved
	 * @return true if exporting should stop.
	 */
	protected boolean shouldHalt(int imagesSaved) {
		if (imagesSaved >= getMaxImages())
			return true;
		return false;
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
	 * Recursively export elements in this diagram. Closes the editor once it is complete.
	 * @param monitor2
	 * 
	 * @param part
	 * @param container the container to place the exported images into; cannot be null
	 * @throws CoreException 
	 * @throws ExportImageException 
	 */
	protected void recurseExportDiagram(DiagramDocumentEditor editor, IContainer container, IProgressMonitor monitor) throws CoreException, ExportImageException {
		assert(container != null);
		
		if (monitor.isCanceled())
			return;
		
		DiagramEditPart part = editor.getDiagramEditPart();
		IPath destination = generateImageDestination(container);
		
		// save this image if there is something in it
		if (part.getChildren().size() > 0) {
			IProgressMonitor saveMonitor = new SubProgressMonitor(monitor, 1);
			saveMonitor.beginTask("Saving image " + destination, 2);
			monitor.subTask("Saving image " + destination.lastSegment());
			CopyToImageUtil img = getCopyToImageUtil();
			img.copyToImage(part, destination, ImageFileFormat.PNG, new SubProgressMonitor(monitor, 1));
			saveMonitor.done();
			imagesSaved++;
		}
		
		// get children
		for (Object obj : part.getChildren()) {
			if (monitor.isCanceled())
				break;
			
			if (shouldHalt(imagesSaved)) {
				break;		// halt
			}			

			if (obj instanceof GraphicalEditPart) {
				GraphicalEditPart child = (GraphicalEditPart) obj;
				
				// only select children with open policies
				if (child.getEditPolicy(EditPolicyRoles.OPEN_ROLE) != null) {
					
					DiagramDocumentEditor newEd = openSubDiagram(child);
					if (newEd == null || newEd == editor) {
						// didn't do anything: continue
						continue;
					}
					
					// export this diagram editor
					recurseExportDiagram(newEd, container, monitor);

				}
			}
		}
		
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
	 * Maximum number of images it will export.
	 */
	public static final int MAX_IMAGES = 5;
	protected int imagesSaved = 0;
	
	/**
	 * The original model diagram file. Used to generate the image filenames.
	 */
	protected IFile diagramFile = null;
	
	/**
	 * Generate an image destination that shouldn't exist.
	 * 
	 * @param container the container to write the image to
	 * @return
	 * @throws ExportImageException 
	 */
	protected IPath generateImageDestination(IContainer container) throws ExportImageException {
		IPath ct = container.getLocation();
		String extension = diagramFile.getFileExtension();
		String fileName = diagramFile.getName();
		String append = imagesSaved == 0 ? "" : "-" + imagesSaved;
		String newFileName = fileName.substring(0, fileName.length() - extension.length() - 1) + append + ".png"; 

		IPath destination = ct.append(newFileName);
		
		return destination;
	}

	/**
	 * Open the given diagram part.
	 * Based on EclipseTestCase#openDiagram(EditPart)
	 * 
	 * @param sourcePart
	 * @returns the opened DiagramDocumentEditor, or null if it can't be opened.
	 *   it may return the same DiagramDocumentEditor if the load failed.
	 *   it is up to the client to close this new editor.
	 */
	protected DiagramDocumentEditor openSubDiagram(GraphicalEditPart sourcePart) {
		
		// based on org.eclipse.gef.tools.SelectEditPartTracker#performOpen()
		SelectionRequest request = new SelectionRequest();
		request.setLocation(null);		// the location isn't actually required
		request.setModifiers(0 /*getCurrentInput().getModifiers()*/);
		request.setType(RequestConstants.REQ_OPEN);
		if (!sourcePart.understandsRequest(request)) {
			return null;
		}
		if (sourcePart.getDiagramEditDomain() == null) {
			return null;
		}
		
		sourcePart.performRequest(request);

		// we should have loaded up a new editor
		IWorkbenchPage activePage = PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage();
		IEditorPart editor = activePage.getActiveEditor();

		return (DiagramDocumentEditor) editor;
		
	}
	
	public static class ExportImageException extends Exception {

		private static final long serialVersionUID = 1L;

		public ExportImageException(Exception e) {
			super(e.getMessage(), e);
		}

		public ExportImageException(String message) {
			super(message);
		}
		
	}

}
