package org.openiaml.model.diagram.custom.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorPlugin;
import org.openiaml.model.model.diagram.part.IamlDiagramEditorUtil;

/**
 * Export all of the images in a model diagram to multiple image files
 * 
 * @author jmwright
 *
 */
public class ExportImagePartsAction implements IViewActionDelegate {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
	 */
	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		if (selection != null) {
			for (Object o : selection) {
				if (o instanceof IFile) {
					diagramFile = (IFile) o;
					
					try {
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
						
						// export all of the images
						imagesSaved = 0;
						recurseExportDiagram(editor);
						
					} catch (Exception e) {
						IamlDiagramEditorPlugin.getInstance().logError("Could not copy image to '" + generateImageDestination() + "': " + e.getMessage(), e);
					}
					
				}
			}
		}

	}
	
	/**
	 * Recursively export elements in this diagram. Closes the editor once it is complete.
	 * 
	 * @param part
	 * @throws CoreException 
	 */
	protected void recurseExportDiagram(DiagramDocumentEditor editor) throws CoreException {
		DiagramEditPart part = editor.getDiagramEditPart();
		IPath destination = generateImageDestination();
		
		// save this image if there is something in it
		if (part.getChildren().size() > 0) {
			CopyToImageUtil img = new CopyToImageUtil();
			IProgressMonitor monitor = new NullProgressMonitor();
			img.copyToImage(part, destination, ImageFileFormat.PNG, monitor);
			imagesSaved++;
		}
		
		// get children
		for (Object obj : part.getChildren()) {
			if (imagesSaved >= MAX_IMAGES) {
				break;		// halt
			}			

			if (obj instanceof EditPart) {
				EditPart child = (EditPart) obj;
				
				DiagramDocumentEditor newEd = openSubDiagram(child);
				if (newEd == null || newEd == editor) {
					// didn't do anything: continue
					continue;
				}
				
				// export this diagram editor
				recurseExportDiagram(newEd);
			}
		}
			
		// close the editor once we're done
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
	 * @return
	 */
	protected IPath generateImageDestination() {
		// TODO Auto-generated method stub
		IPath container = diagramFile.getLocation().removeLastSegments(1);
		String extension = diagramFile.getFileExtension();
		String fileName = diagramFile.getName();
		String append = imagesSaved == 0 ? "" : "-" + imagesSaved;
		String newFileName = fileName.substring(0, fileName.length() - extension.length() - 1) + append + ".png"; 

		IPath destination = container.append(newFileName);
		
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
	protected DiagramDocumentEditor openSubDiagram(EditPart sourcePart) {
		
		// based on org.eclipse.gef.tools.SelectEditPartTracker#performOpen()
		SelectionRequest request = new SelectionRequest();
		request.setLocation(null);		// the location isn't actually required
		request.setModifiers(0 /*getCurrentInput().getModifiers()*/);
		request.setType(RequestConstants.REQ_OPEN);
		if (!sourcePart.understandsRequest(request)) {
			return null;
		}
		
		sourcePart.performRequest(request);

		// we should have loaded up a new editor
		IWorkbenchPage activePage = PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage();
		IEditorPart editor = activePage.getActiveEditor();

		return (DiagramDocumentEditor) editor;
		
	}
	
	public static final String PLUGIN_ID = "org.openiaml.model.diagram.custom";

	Object[] selection;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = null;
		if (selection instanceof IStructuredSelection) {
			this.selection = ((IStructuredSelection) selection).toArray();
		}
	}

}
